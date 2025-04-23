import java.util.ArrayList;
import java.util.List;

public class NQueens {
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        this.res = new ArrayList<>();
        boolean[][] board = new boolean[n][n];

        helper(board, 0, n);
        return res;

    }

    private void helper(boolean[][] board, int row, int n) {

        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (board[i][j]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            res.add(list);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (isValid(board, row, j, n)) {
                //action
                board[row][j] = true;
                //recurse
                helper(board, row + 1, n);
                //backtrack
                board[row][j] = false;
            }
        }
    }

    private boolean isValid(boolean[][] board, int i, int j, int n) {
        int r = i;
        int c = j;

        //Straight above
        while (r >= 0) {
            if (board[r][c])
                return false;
            r--;
        }

        r = i;
        c = j;
        //diagonal left up
        while (r >= 0 && c >= 0) {
            if (board[r][c])
                return false;
            r--;
            c--;
        }

        r = i;
        c = j;
        //diagonal right up
        while (r >= 0 && c < n) {
            if (board[r][c])
                return false;
            r--;
            c++;
        }

        return true;
    }
}

//TC: O(n(n!)) + n^2, SC: O(n^2)+ O(n)

/**
 •	At each row, you’re trying all n columns to place a queen.
 •	But due to pruning (checking validity), many branches are cut off early.
 •	In the worst case, for row 0: n options, for row 1: n-1, then n-2, …, 1.
 •	So, the total number of recursive calls is approximately n! in the worst case.
 */