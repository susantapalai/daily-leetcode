
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            int sum = 0;

            // Try subarrays of different lengths
            for (int j = i; j < n && j - i + 1 <= r; j++) {

                sum += nums.get(j);

                int length = j - i + 1;

                // Check valid length and positive sum
                if (length >= l && sum > 0) {
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
