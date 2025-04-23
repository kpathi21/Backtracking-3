public class WordSearch {
    int[][] dirs;
    int m, n;

    public boolean exist(char[][] board, String word) {
        this.dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        this.m = board.length;
        this.n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        //base
        if (idx == word.length())
            return true;
        if (i < 0 || j < 0 || i == m || j == n || board[i][j] == '#')
            return false;
        if (board[i][j] != word.charAt(idx))
            return false;

        //action
        board[i][j] = '#'; //visited

        //recurse
        for (int[] dir : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;

            if (dfs(board, r, c, word, idx + 1))
                return true;
        }

        //backtrack
        board[i][j] = word.charAt(idx);

        return false;
    }
}

//TC: O(3^L), SC: O(L)
