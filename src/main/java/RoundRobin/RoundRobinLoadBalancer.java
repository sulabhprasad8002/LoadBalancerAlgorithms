package RoundRobin;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancer {
    private List<String> servers;
    private int currentIndex;

    public RoundRobinLoadBalancer() {
        servers = new ArrayList<>();
        currentIndex = 0;
    }

    public void addServer(String server) {
        servers.add(server);
    }

    public String nextServer() {
        if(servers.isEmpty()) {
            throw new IllegalStateException("No Server available");
        }

        String nextServer = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();

        return nextServer;
    }
}
