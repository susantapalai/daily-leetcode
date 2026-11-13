
class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;
        int LOG = 18;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 1: Next Greater Element
        int[] nge = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            nge[i] = top >= 0 ? stack[top] : n;
            stack[++top] = i;
        }

        // Step 2: Binary lifting
        int[][] next = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {

            next[0][i] = nge[i];

            int end = nge[i];

            cost[0][i] =
                (long) nums[i] * (end - i)
                - (prefix[end] - prefix[i]);
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = next[j - 1][i];

                if (mid < n) {
                    next[j][i] = next[j - 1][mid];

                    cost[j][i] =
                        cost[j - 1][i] + cost[j - 1][mid];

                } else {
                    next[j][i] = n;
                    cost[j][i] = cost[j - 1][i];
                }
            }
        }

        // Step 3: Calculate conversion cost
        // for subarray [l, r]
        long answer = 0;

        for (int r = 0; r < n; r++) {

            int low = 0;
            int high = r;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                long required = getCost(
                    mid, r, nums, prefix, next, cost, LOG
                );

                if (required <= k) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            answer += r - low + 1;
        }

        return answer;
    }

    private long getCost(
        int l,
        int r,
        int[] nums,
        long[] prefix,
        int[][] next,
        long[][] cost,
        int LOG
    ) {

        long result = 0;
        int pos = l;

        // Skip complete maximum-value groups
        for (int j = LOG - 1; j >= 0; j--) {

            if (next[j][pos] <= r) {

                result += cost[j][pos];
                pos = next[j][pos];
            }
        }

        // Calculate the final incomplete group
        result += (long) nums[pos] * (r - pos + 1)
                - (prefix[r + 1] - prefix[pos]);

        return result;
    }
}
