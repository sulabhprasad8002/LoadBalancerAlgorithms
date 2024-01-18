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

    public static void main(String[] args) {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        loadBalancer.addServer("Server 1");
        loadBalancer.addServer("Server 2");
        loadBalancer.addServer("Server 3");
        loadBalancer.addServer("Server 4");

        for(int i=1; i<=10; i++) {
            String server = loadBalancer.nextServer();

            System.out.println("Request " + i + " directed to " + server);
        }
    }
}
