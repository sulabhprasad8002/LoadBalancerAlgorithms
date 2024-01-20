    package LeastResponse;

import java.util.HashMap;
import java.util.Map;

public class LeastResponseLoadBalancer {
        private Map<String, Integer> responseTimes;

        public LeastResponseLoadBalancer() {
            responseTimes = new HashMap<>();
        }

        public void addServer(String server) {
            responseTimes.put(server, Integer.MAX_VALUE);
        }

        public void updateResponse(String server, int response) {
            if(responseTimes.containsKey(server)) {
                responseTimes.put(server, response);
            }
        }

        public String getLeastResponseTimeServer() {
            String server = null;
            int response = Integer.MAX_VALUE;

            for(Map.Entry<String, Integer> entry : responseTimes.entrySet()) {
                String curServer = entry.getKey();
                int curResponse = entry.getValue();

                if(curResponse < response) {
                    response = curResponse;
                    server = curServer;
                }
            }

            return server;
        }
    }