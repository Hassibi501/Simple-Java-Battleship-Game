package battleship;


import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
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

        String[][] fogBoard = {
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

        String[] placingShips = {
                "Enter the coordinates of the Aircraft Carrier (5 cells):",
                "Enter the coordinates of the Battleship (4 cells):",
                "Enter the coordinates of the Submarine (3 cells):",
                "Enter the coordinates of the Cruiser (3 cells):",
                "Enter the coordinates of the Destroyer (2 cells):",
        };
        boolean[][] adjacentChecker = new boolean[12][12];

        String[] coords;
        char rowLetterOne, rowLetterTwo;

        int rowNumberOne;
        int colmOneNumber;

        int rowNumberTwo;
        int colmTwoNumber;

        boardDisplay(board);

        for (String msg : placingShips) {
            System.out.println(msg + "\n");

            while (true) {
                userInput = scanner.nextLine();

                if (!userInput.matches("[A-J]([1-9]|10)\s[A-J]([1-9]|10)")) {
                    System.out.println("\nError! Wrong ship location! Try again:\n");
                    continue;
                }

                coords =  userInput.split(" ");

                rowLetterOne = coords[0].charAt(0);
                rowLetterTwo = coords[1].charAt(0);

                rowNumberOne = (rowLetterOne - 'A') + 1;
                rowNumberTwo = (rowLetterTwo - 'A') + 1;

                colmOneNumber = Integer.parseInt(coords[0].substring(1));
                colmTwoNumber = Integer.parseInt(coords[1].substring(1));

                int length = getLengthOfShips(coords);

                if (rowLetterOne != rowLetterTwo && colmOneNumber != colmTwoNumber) {
                    System.out.println("\nError! Wrong ship location! Try again:\n");
                    continue;
                }

                if (board[rowNumberOne][colmOneNumber].equals("O")) {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    continue;
                }

                if (board[rowNumberTwo][colmTwoNumber].equals("O")) {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    continue;
                }

                if (adjacentChecker[rowNumberOne][colmOneNumber]) {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    continue;
                }

                if (msg.contains("5") && length != 5) {
                    System.out.println("\nError! Wrong length of the Aircraft! Try again:\n");
                    continue;
                }
                else if (msg.contains("4") && length != 4) {
                    System.out.println("\nError! Wrong length of the Battleship! Try again:\n");
                    continue;
                }
                else if (msg.contains("Submarine") && msg.contains("3") && length != 3) {
                    System.out.println("\nError! Wrong length of the Submarine! Try again:\n");
                    continue;
                }
                else if (msg.contains("Cruiser") && msg.contains("3") && length != 3) {
                    System.out.println("\nError! Wrong length of the Cruiser! Try again:\n");
                    continue;
                }
                else if (msg.contains("2") && length != 2) {
                    System.out.println("\nError! Wrong length of the Destroyer! Try again:\n");
                    continue;
                }

                // A1 A4
                if (rowLetterOne == rowLetterTwo && colmOneNumber < colmTwoNumber) {
                    for (int i = colmOneNumber; i <= colmTwoNumber; i++) {
                        board[rowNumberOne][i] = "O";

                        adjacentChecker[rowNumberOne][i - 1] = true;
                        adjacentChecker[rowNumberOne + 1][i - 1] = true;
                        adjacentChecker[rowNumberOne - 1][i - 1] = true;
                    }
                }
                // A4 A1
                else if (rowLetterOne == rowLetterTwo && colmOneNumber > colmTwoNumber) {
                    for (int i = colmOneNumber; i >= colmTwoNumber ; i--) {
                        board[rowNumberOne][i] = "O";

                        adjacentChecker[rowNumberOne][i - 1] = true;
                        adjacentChecker[rowNumberOne + 1][i - 1] = true;
                        adjacentChecker[rowNumberOne - 1][i - 1] = true;
                    }
                }
                // A1 D1
                else if (rowLetterOne != rowLetterTwo && rowNumberOne < rowNumberTwo) {
                    for (int i = rowNumberOne; i <= rowNumberTwo; i++) {
                        board[i][colmOneNumber] = "O";

                        adjacentChecker[i - 1][colmOneNumber] = true;
                        adjacentChecker[i - 1][colmOneNumber + 1] = true;
                        adjacentChecker[i - 1][colmOneNumber - 1] = true;
                    }
                }
                // D1 D3
                else if (rowLetterOne != rowLetterTwo && rowNumberOne > rowNumberTwo) {
                    for (int i = rowNumberOne; i >= rowNumberTwo; i--) {
                        board[i][colmOneNumber] = "O";

                        adjacentChecker[i - 1][colmOneNumber] = true;
                        adjacentChecker[i - 1][colmOneNumber + 1] = true;
                        adjacentChecker[i - 1][colmOneNumber - 1] = true;
                    }
                }

                boardDisplay(board);

                break;
            }


        }// for each loop

        System.out.println("The game starts!");
        boardDisplay(fogBoard);
        System.out.println("Take a shot!\n");
        while (true) {
            userInput = scanner.nextLine();

            if (!userInput.matches("[A-J]([1-9]|10)")) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                continue;
            }

            coords =  userInput.split(" ");

            rowLetterOne = coords[0].charAt(0);

            rowNumberOne = (rowLetterOne - 'A') + 1;

            colmOneNumber = Integer.parseInt(coords[0].substring(1));

            if (board[rowNumberOne][colmOneNumber].equals("O")) {
                board[rowNumberOne][colmOneNumber] = "X";
                fogBoard[rowNumberOne][colmOneNumber] = "X";
                boardDisplay(fogBoard);
                System.out.println("You hit a ship!");
            }
            else if (board[rowNumberOne][colmOneNumber].equals("X")) {
                System.out.println("\nError! You already shot here! Try again:\n");
                continue;
            }
            else {
                board[rowNumberOne][colmOneNumber] = "M";
                fogBoard[rowNumberOne][colmOneNumber] = "M";
                boardDisplay(fogBoard);
                System.out.println("You missed!");
            }

            boardDisplay(board);
            break;

        }
    }

    public static void boardDisplay(String[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            if (i < board.length - 1) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public static int getLengthOfShips (String[] coords) {
        char rowLetterOne = coords[0].charAt(0);
        char rowLetterTwo = coords[1].charAt(0);

        int rowNumberOne = (rowLetterOne - 'A') + 1;
        int rowNumberTwo = (rowLetterTwo - 'A') + 1;

        int colmOneNumber = Integer.parseInt(coords[0].substring(1));
        int colmTwoNumber = Integer.parseInt(coords[1].substring(1));
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
        return length;
    }
}