package service;

import model.Board;
import model.GameStatus;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final static int BOARD_LIMIT = 9;

    private final Board board;

    private boolean modoRascunho = false;

    public BoardService(final Map<String, String> gameConfig){
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpace(){
        return this.board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatus getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(Map<String, String> gameConfig) {
        // Cria a estrutura bidimensional do tabuleiro (9x9)
        List<List<Space>> spaces = new ArrayList<>();
        for(int i = 0; i < BOARD_LIMIT; i++){
            spaces.add(new ArrayList<>()); // adiciona uma nova linha
            for(int j = 0; j < BOARD_LIMIT; j++){
                // Busca a configuração da posição no mapa (ex: "0,0" -> "4,true")
                var positionConfig = gameConfig.get("%s,%s".formatted(i,j));

                // Separa os valores: primeiro é o número esperado (valor correto do Sudoku)
                int expected = Integer.parseInt(positionConfig.split(",")[0]);

                // Segundo valor é um booleano indicando se é fixo (pré-definido)
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);

                // Cria o espaço da célula com valor esperado e se é fixo
                var currentSpace = new Space(expected, fixed);

                // Adiciona o espaço na posição correta da linha
                spaces.get(i).add(currentSpace);
            }
        }

        return spaces;
    }

    public void setModoRascunho(boolean ativo) {
        this.modoRascunho = ativo;
    }

    public boolean isModoRascunho() {
        return this.modoRascunho;
    }

}
