class Solution {
    public int stoneGameVIII(int[] stones) {
        
        int n = stones.length;

        long[] prefix = new long[n + 1];
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }

        dp[n] = 0;

        // Value available for states j >= 2
        long answer = prefix[n];

        // Value available for j > i
        long best = prefix[n];

        for (int i = n - 1; i >= 1; i--) {
            dp[i] = best;

            long value = prefix[i] - dp[i];

            best = Math.max(best, value);

            if (i >= 2) {
                answer = Math.max(answer, value);
            }
        }

        return (int)answer;


    }
}