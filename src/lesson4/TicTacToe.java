package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {


        Random random;
        Scanner scanner;
        char[][] table;

        public static void main(String[] args) {
            new TicTacToe().game();
        }

        TicTacToe() {
            random = new Random();
            scanner = new Scanner(System.in);
            table = new char[5][5];
        }

        private void game() {
            initTable();
            printTable();
            while (true) {
                turnHuman();
                if (checkWin('x')) {
                    System.out.println("YOU WON!");
                    break;
                }
                if (isTableFull()) {
                    System.out.println("Sorry, DRAW...");
                    break;
                }
                turnAI();
                printTable();
                if (checkWin('0')) {
                    System.out.println("AI WON!");
                    break;
                }
                if (isTableFull()) {
                    System.out.println("Sorry, DRAW...");
                    break;
                }
            }
            System.out.println("GAME OVER!");
            printTable();
        }

        void initTable() {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    table[x][y] = '.';
                }
            }
        }

        void printTable() {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    System.out.print(table[x][y] + " ");
                }
                System.out.println();
            }
        }

        void turnHuman() {
            int x, y;
            do {
                System.out.print("Enter x y [1..5]:");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (!isCellValid(x, y));
            table[x][y] = 'x';
        }

        void turnAI() {
            int x, y;
            do {
                x = random.nextInt(5);
                y = random.nextInt(5);
            } while (!isCellValid(x, y));
            table[x][y] = '0';
        }

        boolean isCellValid(int x, int y) {
            if (x < 0 || y < 0 || x > 4 || y > 4) {
                return false;
            }
            return table[x][y] == '.';
        }

        boolean checkWin(char ch) {
            //
            int x, y;
            for (x = 0; x < 4; x++) {
                for (y = 0; y < 4; y++) {
                    if (table[y][x] == ch && table[y][1 + y] == ch) {
                        return true;
                    }
                }
            }
            //
            for (x = 0; x < 4; x++) {
                for (y = 0; y < 4; y++) {
                    if (table[x][y] == ch && table[1 + x][y] == ch) {
                        return true;
                    }
                }
            }
            //
            if (table[0][0] == ch && table[1][1] == ch && table[4][4] == ch) return true;
            if (table[4][0] == ch && table[1][1] == ch && table[0][4] == ch) return true;
            return false;
        }

        boolean isTableFull() {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (table[x][y] == '.') {
                        return false;
                    }
                }
            }
            return true;
        }
    }