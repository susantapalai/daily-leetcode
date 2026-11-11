
class Solution {
    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                boolean communicates = false;

                // Check same row
                for (int col = 0; col < n; col++) {
                    if (col != j && grid[i][col] == 1) {
                        communicates = true;
                        break;
                    }
                }

                // Check same column
                if (!communicates) {
                    for (int row = 0; row < m; row++) {
                        if (row != i && grid[row][j] == 1) {
                            communicates = true;
                            break;
                        }
                    }
                }

                if (communicates) {
                    result++;
                }
            }
        }

        return result;
    }
}
