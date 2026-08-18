class Solution {
    public int largestInteger(int[] nums, int k) {
                int[] count = new int[51];

        // Check every subarray of size k
        for (int i = 0; i <= nums.length - k; i++) {

            // To avoid counting the same number twice
            // in the same subarray
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                int num = nums[j];

                if (!seen[num]) {
                    count[num]++;
                    seen[num] = true;
                }
            }
        }

        // Find the largest number appearing in exactly one subarray
        for (int num = 50; num >= 0; num--) {
            if (count[num] == 1) {
                return num;
            }
        }

        return -1;
    }
}