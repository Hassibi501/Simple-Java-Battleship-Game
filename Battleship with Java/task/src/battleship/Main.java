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
        String[] coords =  new  String[5];
        char rowLetterOne, rowLetterTwo;

        int rowNumberOne, rowNumberTwo;

        int colmOneNumber, colmTwoNumber;

        //boardDisplay(board);

        board[1][1] = "O";
        board[1][2] = "O";
        board[1][3] = "O";
        board[1][4] = "O";

        board[6][3] = "O";
        board[6][4] = "O";
        board[6][5] = "O";
        board[6][6] = "O";
        board[6][7] = "O";

        board[7][10] = "O";
        board[8][10] = "O";
        board[9][10] = "O";
        board[10][10] = "O";




        boardDisplay(board);


        System.out.println("Enter the coordinates of the ship:");
        while (true) {
            userInput = scanner.nextLine();

            if (!userInput.matches("[A-J]([1-9]|10)\s[A-J]([1-9]|10)")) {
                System.out.println("Error: invalid coordinates");
                continue;
            }


            coords =  userInput.split(" ");

            rowLetterOne = coords[0].charAt(0);
            rowLetterTwo = coords[1].charAt(0);

            rowNumberOne = (rowLetterOne - 'A') + 1;
            rowNumberTwo = (rowLetterTwo - 'A') + 1;

            colmOneNumber = Integer.parseInt(coords[0].substring(1));
            colmTwoNumber = Integer.parseInt(coords[1].substring(1));

            if (colmOneNumber != colmTwoNumber) {
                System.out.println("Error2: invalid coordinates");
                continue;
            }

            break;
        }



        int length = 0;

        if (rowLetterOne == rowLetterTwo && colmOneNumber == 1) {
            length = colmTwoNumber;
        }
        else if (rowLetterOne == rowLetterTwo && colmTwoNumber == 1) {
            length = colmOneNumber;
        }
        else if (rowLetterOne == rowLetterTwo && colmOneNumber != 1 && colmOneNumber < colmTwoNumber) {
            length = (colmTwoNumber - colmOneNumber) + 1;
        }
        else if (rowLetterOne == rowLetterTwo && colmTwoNumber != 1 && colmOneNumber > colmTwoNumber) {
            length = (colmOneNumber - colmTwoNumber) + 1;
        }
        else if (rowLetterOne != rowLetterTwo && rowNumberOne < rowNumberTwo) {
            length = (rowNumberTwo - rowNumberOne) + 1;
        }
        else if (rowLetterOne != rowLetterTwo && rowNumberOne > rowNumberTwo) {
            length = (rowNumberOne - rowNumberTwo) + 1;
        }

        System.out.println("Length: " + length);
        System.out.print("Parts: ");
        if (rowLetterOne == rowLetterTwo && colmOneNumber < colmTwoNumber) {
            for (int i = 0; i < board.length; i++) {
                if (board[rowNumberOne][i].equals("O") && i <= colmTwoNumber) {
                    System.out.printf("%c%d", rowLetterOne, i);
                }
            }
        }
        else if (rowLetterOne == rowLetterTwo && colmOneNumber > colmTwoNumber) {
            for (int i = board.length - 1; i > 0; i--) {
                if (board[rowNumberOne][i].equals("O") && i <= colmTwoNumber) {
                    System.out.printf("%c%d", rowLetterOne, i);
                }
            }
        }
        else if (rowLetterOne != rowLetterTwo && rowNumberOne < rowNumberTwo) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][colmOneNumber].equals("O")) {
                    System.out.print(board[i][0] + colmOneNumber + " ");
                }
            }
        }
        else if (rowLetterOne != rowLetterTwo && rowNumberOne > rowNumberTwo) {
            for (int i = board.length - 1; i > 0; i--) {
                if (board[i][colmOneNumber].equals("O")) {
                    System.out.print(board[i][0] + colmOneNumber + " ");
                }
            }
        }

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