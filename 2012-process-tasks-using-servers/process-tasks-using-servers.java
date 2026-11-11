
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] answer = new int[m];

        // Available servers: [weight, index]
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [finishTime, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                int cmp = Long.compare(a[0], b[0]);

                if (cmp != 0) {
                    return cmp;
                }

                return Long.compare(a[1], b[1]);
            }
        );

        // Initially, all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long currentTime = 0;

        // Process tasks in original order
        for (int i = 0; i < m; i++) {

            // Task cannot start before its arrival
            currentTime = Math.max(currentTime, i);

            // Release completed servers
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] completed = busy.poll();

                int serverIndex = (int) completed[1];

                free.offer(new int[]{
                    servers[serverIndex],
                    serverIndex
                });
            }

            // If no server is free, wait
            if (free.isEmpty()) {

                currentTime = busy.peek()[0];

                // Release all servers completed by this time
                while (!busy.isEmpty() &&
                       busy.peek()[0] <= currentTime) {

                    long[] completed = busy.poll();

                    int serverIndex = (int) completed[1];

                    free.offer(new int[]{
                        servers[serverIndex],
                        serverIndex
                    });
                }
            }

            // Select the best available server
            int[] server = free.poll();

            int serverIndex = server[1];

            answer[i] = serverIndex;

            // Calculate completion time
            long finishTime = currentTime + tasks[i];

            busy.offer(new long[]{
                finishTime,
                serverIndex
            });
        }

        return answer;
    }
}

