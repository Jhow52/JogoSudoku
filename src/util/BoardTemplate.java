package util;

public class BoardTemplate {

    public static String BOARD_TEMPLATE() {
        String horizontal = "╔═══════╦═══════╦═══════╗";
        String meio       = "╠═══════╬═══════╬═══════╣";
        String fim        = "╚═══════╩═══════╩═══════╝";
        String linhaVazia = "║ %s %s %s ║ %s %s %s ║ %s %s %s ║";

        int[][] sudoku = new int[9][9]; // exemplo: matriz vazia

        System.out.println(horizontal);
        for (int i = 0; i < 9; i++) {
            System.out.println(String.format(linhaVazia,
                    format(sudoku[i][0]), format(sudoku[i][1]), format(sudoku[i][2]),
                    format(sudoku[i][3]), format(sudoku[i][4]), format(sudoku[i][5]),
                    format(sudoku[i][6]), format(sudoku[i][7]), format(sudoku[i][8])
            ));

            if (i == 2 || i == 5) {
                System.out.println(meio);
            }
        }
        System.out.println(fim);
        return horizontal;
    }

    private static String format(int valor) {
        return valor == 0 ? "." : String.valueOf(valor);
    }

    public static void main(String[] args) {
        BOARD_TEMPLATE();
    }
}
