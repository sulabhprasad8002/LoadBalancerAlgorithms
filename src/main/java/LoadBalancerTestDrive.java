import LeastConnection.LeastConnectionLoadBalancer;
import LeastResponse.LeastResponseLoadBalancer;
import RoundRobin.RoundRobinLoadBalancer;
import WeightedLeastConnection.WeightedLeastConnectionLoadBalancer;
import WeightedRoundRobin.WeightedRoundRobinLoadBalancer;

public class LoadBalancerTestDrive {
    public static void main(String[] args) {
    //    testRoundRobinLoadBalancer();
    //    testWeightedRoundRobinLoadBalancer();
    //    testLeastConnectionLoadBalancer();
        // testLeastResponseLoadBalancer();
        testWeightedLeastConnectionLoadBalancer();
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

    public static void testLeastConnectionLoadBalancer() {
        LeastConnectionLoadBalancer loadBalancer = new LeastConnectionLoadBalancer();

        loadBalancer.addServer("Server 1");
        loadBalancer.addServer("Server 2");
        loadBalancer.addServer("Server 3");
        loadBalancer.addServer("Server 4");

        for(int i=1; i<=20; i++) {
            String server = loadBalancer.leastConnectionServer();
            System.out.println("Request " + i + " directed to " + server);

            loadBalancer.addConnection(server);
        }
    }

    public static void testLeastResponseLoadBalancer() {
        LeastResponseLoadBalancer loadBalancer = new LeastResponseLoadBalancer();

        loadBalancer.addServer("Server 1");
        loadBalancer.addServer("Server 2");
        loadBalancer.addServer("Server 3");
        loadBalancer.addServer("Server 4");

        loadBalancer.updateResponse("Server 1", 100);
        loadBalancer.updateResponse("Server 2", 1000);
        loadBalancer.updateResponse("Server 3", 200);
        loadBalancer.updateResponse("Server 4", 300);
        
        for(int i=1; i<=20; i++) {
            String server = loadBalancer.getLeastResponseTimeServer();
            
            System.out.println("Request " + i + " directed to " + server);
        }
    }

    public static void testWeightedLeastConnectionLoadBalancer() {
        WeightedLeastConnectionLoadBalancer loadBalancer = new WeightedLeastConnectionLoadBalancer();

        loadBalancer.addServer("Server 1", 2);
        loadBalancer.addServer("Server 2", 4);
        loadBalancer.addServer("Server 3", 5);
        loadBalancer.addServer("Server 4", 8);

        for(int i=1; i<=20; i++) {
            String server = loadBalancer.getWeightedLeastConnectionServer();

            System.out.println("Request " + i + " directed to " + server);

            loadBalancer.addConnection(server);
        }
    }

}
