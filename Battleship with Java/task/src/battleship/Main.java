package battleship;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[][] board = {
                {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
                {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
        };


        boardDisplay(board);

        board[1][1] = "O";
        board[1][2] = "O";
        board[1][3] = "O";
        board[1][4] = "O";

        board[6][3] = "O";
        board[6][4] = "O";
        board[6][5] = "O";
        board[6][6] = "O";
        board[6][7] = "O";

        //boardDisplay(board);


        System.out.println("Enter the coordinates of the ship:");
        while (true) {
            userInput = scanner.nextLine();

            if (!userInput.matches("[A-J]([1-9]|10)\s[A-J]([1-9]|10)")) {
                System.out.println("Error: invalid coordinates");
                continue;
            }

            if ((userInput.charAt(0) != userInput.charAt(3))) {
                System.out.println("Error2: invalid coordinates");
                continue;
            }

            break;
        }

        String[] coords =  userInput.split(" ");
        char rowLetter = coords[0].charAt(0);

        int rowNumber = (rowLetter - 'A') + 1;

        int colmOneNumber = Integer.parseInt(coords[0].substring(1));
        int colmTwoNumber = Integer.parseInt(coords[1].substring(1));

        int length = 0;
        if (colmOneNumber == 1) {
            length = colmTwoNumber;
        }
        else if (colmTwoNumber == 1) {
            length = colmOneNumber;
        }
        else if (colmOneNumber != 1 && colmOneNumber < colmTwoNumber) {
          length = (colmTwoNumber - colmOneNumber) + 1;
        }
        else if (colmTwoNumber != 1 && colmOneNumber > colmTwoNumber) {
            length = (colmOneNumber - colmTwoNumber) + 1;
        }

        System.out.println("Length: " + length);
        System.out.print("Parts: ");
        if (colmOneNumber < colmTwoNumber) {
            for  (int i = 0; i < 1; i++) {
                for (int j = 0; j < colmTwoNumber + 1; j++) {
                    if (board[rowNumber][colmOneNumber] == "O" && j >= colmOneNumber) {
                        System.out.printf("%c%d ", rowLetter, j);
                    }
                }
            }
        }
        else {
            for  (int i = 0; i < 1; i++) {
                for (int j = colmOneNumber; j > 0; j--) {
                    if (board[rowNumber][colmTwoNumber] == "O" && j >= colmTwoNumber) {
                        System.out.printf("%c%d ", rowLetter, j);
                    }
                }
            }
        }
//        char rowLetter = 'F';
//
//        int rowNumber = (rowLetter - 'A') + 1;
//
//        int colmOneNumber = 3;
//        int colmTwoNumber = 7;
//
//
//
//        for  (int i = 0; i < 1; i++) {
//            for (int j = colmTwoNumber; j > 0; j--) {
//                if (board[rowNumber][colmOneNumber] == "O" && j >= colmOneNumber) {
//                    System.out.printf("%c%d ", rowLetter, j);
//                }
//            }
//        }




    }

    public static void boardDisplay(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            if (i < board.length - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
