package tictactoe;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Arrays;


class Main {
    public final static Scanner scan = new Scanner(System.in);
    public static char[][] gameBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };


    public static void main(String args[]) {

        char currentPlayer = 'X';
        drawBoard(gameBoard);

        while (!gameFinished(gameBoard)) {
            playerMove(gameBoard, currentPlayer);
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            gameState(gameBoard);
        }

    }//end of main method

    public static void playerMove(char[][] gameBoard, char player) {
        int index = 0;
        int row, column;
        while (true) {
            System.out.println("Enter the coordinates: ");
            String[] coordinates = scan.nextLine().split(" ");

            try {

                row = Integer.parseInt(coordinates[0]);
                column = Integer.parseInt(coordinates[1]);

                if ((row < 1 || column < 1) || (row > 3 || column > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                row -= 1;
                column -= 1;

                if (gameBoard[row][column] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                gameBoard[row][column] = player;
                drawBoard(gameBoard);
                break;

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

        }//while loop ends here

    }// playerMove() ends

    public static void drawBoard(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");


    }//end drawBoard method

    public static boolean xWon (char[][] gameBoard) {
        char[] xWins = {'X', 'X', 'X'};
        char[][] winningCombos = {
                {gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]}, //horizontal
                {gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]}, //horizontal
                {gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]}, //horizontal
                {gameBoard[0][0], gameBoard[1][0], gameBoard[2][0]}, //vertical
                {gameBoard[0][1], gameBoard[1][1], gameBoard[2][1]}, //vertical
                {gameBoard[0][2], gameBoard[1][2], gameBoard[2][2]}, //vertical
                {gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]}, //diagonal
                {gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]}  //diagonal
        };

        for (char[] charArray : winningCombos) {
            if (Arrays.equals(xWins, charArray)) {
                return true;
            }
        }

        return false;
    }// end xWon method

    public static boolean oWon (char[][] gameBoard) {
        char[] oWins = {'O', 'O', 'O'};
        char[][] winningCombos = {
                {gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]}, //horizontal
                {gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]}, //horizontal
                {gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]}, //horizontal
                {gameBoard[0][0], gameBoard[1][0], gameBoard[2][0]}, //vertical
                {gameBoard[0][1], gameBoard[1][1], gameBoard[2][1]}, //vertical
                {gameBoard[0][2], gameBoard[1][2], gameBoard[2][2]}, //vertical
                {gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]}, //diagonal
                {gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]}  //diagonal
        };
        for (char[] charArray : winningCombos) {
            if (Arrays.equals(oWins, charArray)) {
                return true;
            }
        }

        return false;
    }// end oWon method

    public static boolean hasEmptyCells(char[][] gameBoard) {
        for (char[] chArray : gameBoard) {
            for (int i = 0; i < chArray.length; i++) {
                if (chArray[i] == ' ')
                    return  true;
            }
        }
        return false;
    } //end of hasEmptyCells method;

    public static boolean gameFinished(char[][] gameBoard) {
        if (!xWon(gameBoard) && !oWon(gameBoard)) {
            if (hasEmptyCells(gameBoard)) {
                return false;
            }
        }

        return true;
    } //end of gameFinished method.

    public static void gameState(char[][] gameBoard) {

         if (xWon(gameBoard)) {
            System.out.println("X wins");
        } else if (oWon(gameBoard)) {
            System.out.println("O wins");
        } else if (gameFinished(gameBoard)) {
            System.out.println("Draw");
        }

    }
}




