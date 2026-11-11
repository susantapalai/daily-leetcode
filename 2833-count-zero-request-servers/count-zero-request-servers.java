
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int q = queries.length;

        // Sort query indices instead of creating a 2D query array
        Integer[] indices = new Integer[q];

        for (int i = 0; i < q; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) ->
            Integer.compare(queries[a], queries[b])
        );

        int[] count = new int[n + 1];
        int[] answer = new int[q];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int idx : indices) {

            int end = queries[idx];
            int start = end - x;

            // Add requests up to the right boundary
            while (right < logs.length && logs[right][1] <= end) {

                int server = logs[right][0];

                if (count[server] == 0) {
                    active++;
                }

                count[server]++;
                right++;
            }

            // Remove requests before the left boundary
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    active--;
                }

                left++;
            }

            answer[idx] = n - active;
        }

        return answer;
    }
}
