
package LeastConnection;

import java.util.HashMap;
import java.util.Map;

public class LeastConnectionLoadBalancer {
    Map<String, Integer> connections;

    public LeastConnectionLoadBalancer() {
        connections = new HashMap();
    }

    public void addServer(String server) {
        connections.put(server, 0);
    }

    public void addConnection(String server) {
        if(connections.containsKey(server)) {
            int currentConnections = connections.get(server);
            connections.put(server, currentConnections + 1 );
        }
    }

    public String leastConnectionServer() {
        String leastConnectionServer = null;
        int leastConnections = Integer.MAX_VALUE;

        for(Map.Entry<String, Integer> entry : connections.entrySet()) {
            String server = entry.getKey();
            int connections = entry.getValue();

            if(connections < leastConnections) {
                leastConnections = connections;
                leastConnectionServer = server;
            }
        }

        return leastConnectionServer;
    }
}