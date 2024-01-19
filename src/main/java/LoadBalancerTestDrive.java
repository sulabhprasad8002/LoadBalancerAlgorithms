import RoundRobin.RoundRobinLoadBalancer;
import WeightedRoundRobin.WeightedRoundRobinLoadBalancer;

public class LoadBalancerTestDrive {
    public static void main(String[] args) {
       testRoundRobinLoadBalancer();
       testWeightedRoundRobinLoadBalancer();
    }

    public static void testRoundRobinLoadBalancer() {
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

    public static void testWeightedRoundRobinLoadBalancer() {
        WeightedRoundRobinLoadBalancer loadBalancer = new WeightedRoundRobinLoadBalancer();

        loadBalancer.addServer("Server 1", 2);
        loadBalancer.addServer("Server 2", 4);
        loadBalancer.addServer("Server 3", 5);
        loadBalancer.addServer("Server 4", 8);

        for(int i=1; i<=20; i++) {
            WeightedRoundRobinLoadBalancer.Server server = loadBalancer.getNextServer();

            System.out.println("Request " + i + " directed to " + server.getName());
        }
    }
}
