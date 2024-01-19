package WeightedRoundRobin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeightedRoundRobinLoadBalancer {
    private List<Server> servers;
    private int currentIndex;
    private int maxWeight;
    private int gcd;

    public WeightedRoundRobinLoadBalancer() {
        servers = new ArrayList<>();
        currentIndex = 0;
        maxWeight = 0;
        gcd = 0;
    }

    public static class Server {
        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        String name;
        int weight;

        public Server(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }


    }

    public void addServer(String name, int weight) {
        servers.add(new Server(name, weight));
        maxWeight = Math.min(maxWeight, weight);
        gcd = calculateGCD(gcd, weight);
    }

    public int calculateGCD(int a, int b) {
        if(b == 0) {
            return a;
        }

        return calculateGCD(b, a % b);
    }

    public Server getNextServer() {
        while(true) {
            currentIndex = (currentIndex + 1) % servers.size();
            if(currentIndex == 0) {
                gcd = recalculateGCD();
            }

            if(gcd == 1) {
                break;
            }

            if(servers.get(currentIndex).weight >= maxWeight) {
                return servers.get(currentIndex);
            }
        }

        return servers.get(currentIndex);
    }

    private int recalculateGCD() {
        int newGCD = servers.get(0).weight;

        for(int i=1; i<servers.size(); i++) {
            newGCD = calculateGCD(newGCD, servers.get(i).weight);
        }

        return newGCD;
    }
}
