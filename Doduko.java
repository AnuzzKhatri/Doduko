import java.util.Scanner;

public class Solution {

    public boolean isSafe(int[][] sudoku, int row, int col, int number) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][col] == number) {
                return false;
            }
        }
        for (int j = 0; j < sudoku.length; j++) {
            if (sudoku[row][j] == number) {
                return false;
            }
        }
        // grid condition
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean helper(int[][] sudoku, int row, int col) {
        if (row == sudoku.length) {
            return true;
        }
        int nrow = 0;
        int ncol = 0;
        if (col == sudoku.length - 1) {
            nrow = row + 1;
            ncol = 0;
        } else {
            nrow = row;
            ncol = col + 1;
        }
        // insert a number in a cell
        if (sudoku[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(sudoku, row, col, i)) {
                    sudoku[row][col] = i;
                    if (helper(sudoku, nrow, ncol)) {
                        return true;
                    } else {
                        sudoku[row][col] = 0;
                    }
                }
            }
            return false;
        } else {
            return helper(sudoku, nrow, ncol);
        }
    }

    public static void solveSudoku(int[][] sudoku) {
        Solution solution = new Solution();
        if (solution.helper(sudoku, 0, 0)) {
            System.out.println("Solved Sudoku:");
            printSudoku(sudoku);
        } else {
            System.out.println("No solution exists for the given Sudoku.");
        }
    }

    public static void printSudoku(int[][] sudoku) {
        for (int r = 0; r < sudoku.length; r++) {
            for (int d = 0; d < sudoku[r].length; d++) {
                System.out.print(sudoku[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        System.out.println("Enter the Sudoku puzzle (9x9) row by row (use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = scanner.nextInt();
            }
        }
        solveSudoku(sudoku);
    }
}
