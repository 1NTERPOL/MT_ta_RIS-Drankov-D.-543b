import java.util.Scanner;

public class Lab3 {

    static class Player {
        private String name;
        private char symbol;

        public Player(String name, char symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public char getSymbol() {
            return symbol;
        }

        public int[] makeMove(Scanner scanner) {
            System.out.println(name + ",enter the row and column number (separated by spaces) or type 'exit' to quit: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            return new int[]{row, col};
        }

        public void showInfo() {
            System.out.println("Player: " + name + ", Figure: " + symbol);
        }
    }

    static class TicTacToeBoard {
        private char[][] board;
        private int size = 3;

        public TicTacToeBoard() {
            board = new char[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = '-';
                }
            }
        }

        public void displayBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        public boolean makeMove(int row, int col, char symbol) {
            if (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == '-') {
                board[row][col] = symbol;
                return true;
            }
            System.out.println("This cell is already occupied. Try again.");
            return false;
        }

        public boolean checkWin(char symbol) {
            for (int i = 0; i < size; i++) {
                if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                    return true;
                }
            }

            if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
                return true;
            }

            return false;
        }

        public boolean isFull() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == '-') {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first player's name: ");
        Player player1 = new Player(scanner.nextLine(), 'X');

        System.out.println("Enter the second player's name: ");
        Player player2 = new Player(scanner.nextLine(), 'O');

        TicTacToeBoard board = new TicTacToeBoard();

        Player currentPlayer = player1;
        boolean gameWon = false;

        while (!board.isFull() && !gameWon) {
            board.displayBoard();
            currentPlayer.showInfo();

            boolean validMove = false;
            while (!validMove) {
                int[] move = currentPlayer.makeMove(scanner);
                validMove = board.makeMove(move[0], move[1], currentPlayer.getSymbol());
            }

            gameWon = board.checkWin(currentPlayer.getSymbol());

            if (!gameWon) {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }

        board.displayBoard();

        if (gameWon) {
            System.out.println("Congratulations, " + currentPlayer.getName() + "! You have won!");
        } else {
            System.out.println("Draw!");
        }

        scanner.close();
    }
}



