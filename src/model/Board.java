package model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.GameStatus.*;

public class Board {
    private final List<List<Space>> spaces; // Lista de colunas com suas linhas (ou vice-versa)

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    // Retorna o status atual do jogo
    public GameStatus getStatus(){
        // Verifica se nenhuma célula não fixa foi preenchida → jogo não começou
        if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isValorFixo() && nonNull(s.getValorAtual()))){
            return NON_STARTED;
        }

        // Se houver alguma célula vazia → jogo incompleto, senão → completo
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getValorAtual())) ? INCOMPLETE : COMPLETE;
    }

    // Verifica se há erros nas células preenchidas
    public boolean hasErrors(){
        if(getStatus() == NON_STARTED){
            return false;
        }
        // Se valor preenchido for diferente do esperado, temos erro
        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getValorAtual()) && !s.getValorAtual().equals(s.getValorEsperado()));
    }

    // Altera o valor de uma célula, se ela não for fixa
    public boolean changeValue(final int col, final int row, final Integer value){
        var space = spaces.get(col).get(row);
        if(space.isValorFixo()){
            return false;
        }
        space.setValorAtual(value);
        return true;
    }

    // Limpa o valor de uma célula
    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if(space.isValorFixo()){
            return false;
        }
        space.clearSpace();
        return true;
    }

    // Limpa todo o tabuleiro (mantém apenas os valores fixos)
    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    // Verifica se o jogo está finalizado corretamente
    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(COMPLETE);
    }
}
