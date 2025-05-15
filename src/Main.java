import model.Board;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static util.BoardTemplate.BOARD_TEMPLATE;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var option = -1;
        do{
            System.out.println("Selecione umas das opções");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Insira um novo numero");
            System.out.println("3 - Remover um numero");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");
            option = scanner.nextInt();

            switch (option){
                case 1 -> StartGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção invalida");
            }
        }while(option != 8);
    }

    // Método responsável por iniciar o jogo a partir do mapa de posições (posição -> valor,fixo)
    private static void StartGame(Map<String, String> positions) {
        // Se o tabuleiro já foi iniciado, evita reinicializá-lo
        if(nonNull(board)){
            System.out.println("O jogo foi iniciado");
            return;
        }

        // Cria a estrutura bidimensional do tabuleiro (9x9)
        List<List<Space>> spaces = new ArrayList<>();
        for(int i = 0; i < BOARD_LIMIT; i++){
            spaces.add(new ArrayList<>()); // adiciona uma nova linha
            for(int j = 0; j < BOARD_LIMIT; j++){
                // Busca a configuração da posição no mapa (ex: "0,0" -> "4,true")
                var positionConfig = positions.get("%s,%s".formatted(i,j));

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

        // Cria o tabuleiro com todas as células preenchidas
        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }

    // Método que permite o usuário inserir um número no tabuleiro
    private static void inputNumber() {
        // Verifica se o tabuleiro foi inicializado
        if(isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Solicita ao usuário a coluna onde deseja inserir o número (0 a 8)
        System.out.println("Informe a coluna que o numero sera inserido");
        var col = runUtilGetValidNumber(0,8);

        // Solicita a linha (0 a 8)
        System.out.println("Informe a linha que o numero sera inserido");
        var row = runUtilGetValidNumber(0,8);

        // Solicita o número (valor de 1 a 9) a ser inserido na posição escolhida
        System.out.printf("Informe o numero que vai entrar na posição [%s,%s]\n", col, row);
        var value = runUtilGetValidNumber(1, 9);

        // Tenta alterar o valor. Se não for possível, a posição é fixa.
        if(!board.changeValue(col,row,value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    // Método para remover (limpar) um número inserido pelo usuário
    private static void removeNumber() {
        // Verifica se o tabuleiro foi inicializado
        if(isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Solicita ao usuário a coluna onde deseja remover o número
        System.out.println("Informe a coluna que o numero sera inserido");
        var col = runUtilGetValidNumber(0,8);

        // Solicita a linha
        System.out.println("Informe a linha que o numero sera inserido");
        var row = runUtilGetValidNumber(0,8);

        // Confirma a posição escolhida
        System.out.printf("Informe o numero que vai entrar na posição [%s,%s]\n", col, row);

        // Tenta limpar o valor da célula. Se for fixa, não pode limpar.
        if(!board.clearValue(col,row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    // Mostra o estado atual do tabuleiro
    private static void showCurrentGame() {
        // Verifica se o tabuleiro foi inicializado
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Cria um array de 81 posições (9x9) para preencher o template com os valores atuais
        var args = new Object[81];
        var argsPos = 0;

        // Itera pelas posições do tabuleiro para montar o array de valores
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : board.getSpaces()) {
                // Para cada posição, verifica se o valor é nulo. Se for, coloca espaço em branco.
                args[argsPos++] = " " + ((isNull(col.get(i).getValorAtual())) ? " " : col.get(i).getValorAtual());
            }
        }

        // Imprime o estado atual do jogo
        System.out.println("Seu jogo se encontra assim: ");
        System.out.printf(BOARD_TEMPLATE(), args); // Usa o template do tabuleiro com os valores preenchidos
    }

    // Mostra o status atual do jogo
    private static void showGameStatus() {
        // Verifica se o tabuleiro foi inicializado
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Mostra o status atual do jogo (ex: Incompleto, Completo, Não Iniciado)
        System.out.printf("O jogo atualmente se encontra no status %s\n", board.getStatus().getLabel());

        // Verifica se há erros no jogo (valores errados comparados ao esperado)
        if (board.hasErrors()) {
            System.out.println("O jogo contem erros");
        } else {
            System.out.println("O jogo não tem erros");
        }
    }

    // Permite limpar o jogo (reiniciar)
    private static void clearGame() {
        // Verifica se o tabuleiro foi inicializado
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Solicita confirmação ao usuário para limpar o jogo
        System.out.println("Tem certeza que deseja limpar seu jogo? Você perdera todo o progresso");
        var confirm = scanner.next();

        // Valida a resposta, aceitando apenas "sim" ou "nao"
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("nao")) {
            System.out.println("Informe 'sim' ou 'nao'");
            confirm = scanner.next();
        }

        // Se o usuário confirmar, reseta o tabuleiro (valores não fixos são apagados)
        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
        }
    }

    // Finaliza o jogo (validação final)
    private static void finishGame() {
        // Verifica se o tabuleiro foi inicializado
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        // Verifica se o jogo está finalizado corretamente (sem erros e completo)
        if (board.gameIsFinished()) {
            System.out.println("Parabens você ganhou");
            showCurrentGame(); // Mostra o tabuleiro final
            board = null; // Reseta o tabuleiro para permitir novo jogo
        } else if (board.hasErrors()) {
            // Informa se o jogador errou algum valor
            System.out.println("Seu jogo contem erros");
        } else {
            // Informa que o jogo ainda está incompleto
            System.out.println("Você não completou tudo");
        }
    }


    // Método utilitário que garante que o número digitado pelo usuário esteja dentro do intervalo permitido
    private static int runUtilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt(); // Lê um número do usuário
        // Enquanto o número for inválido (menor que o mínimo ou MAIOR que o máximo)
        while(current < min || current > max){
            System.out.printf("Informe o numero entre %s e %s\n", min,max);
            current = scanner.nextInt(); // Pede novamente
        }
        return current; // Retorna um número válido
    }
}