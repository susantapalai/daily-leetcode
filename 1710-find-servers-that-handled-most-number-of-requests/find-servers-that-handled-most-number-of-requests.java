
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: [completionTime, serverId]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Number of requests handled by each server
        int[] count = new int[k];

        // Process each request
        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Step 1: Release completed servers
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] completed = busy.poll();

                int server = (int) completed[1];

                available.add(server);
            }

            // Step 2: If no server is available, drop request
            if (available.isEmpty()) {
                continue;
            }

            // Step 3: Find the next available server
            int start = i % k;

            Integer server = available.ceiling(start);

            // Circular wrap-around
            if (server == null) {
                server = available.first();
            }

            // Step 4: Assign request
            available.remove(server);

            count[server]++;

            // Step 5: Mark server as busy
            long completionTime = (long) arrival[i] + load[i];

            busy.offer(new long[]{completionTime, server});
        }

        // Step 6: Find maximum requests handled
        int maxRequests = 0;

        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        // Step 7: Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
