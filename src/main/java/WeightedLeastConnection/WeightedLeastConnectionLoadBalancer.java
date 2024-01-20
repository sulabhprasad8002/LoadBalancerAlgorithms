package WeightedLeastConnection;

import java.util.HashMap;
import java.util.Map;

public class WeightedLeastConnectionLoadBalancer {
	private Map<String, Integer> serverConnections;
	private Map<String, Integer> serverWeights;

	public WeightedLeastConnectionLoadBalancer() {
		serverConnections = new HashMap();
		serverWeights = new HashMap();
	}

	public void addServer(String server, int weight) {
		serverConnections.put(server, 0);
		serverWeights.put(server, weight);
	}

	public void addConnection(String server) {
		if(serverConnections.containsKey(server)) {
			int curConnections = serverConnections.get(server);
			serverConnections.put(server, curConnections + 1);
		}
	}

	public String getWeightedLeastConnectionServer() {
		String weightedLeastConnectionServer = null;
		int weightedLeastConnections = Integer.MAX_VALUE;

		for(Map.Entry<String, Integer> entry : serverConnections.entrySet()) {
			String server = entry.getKey();
			int connections = entry.getValue();
			int weight = serverWeights.get(server);
			int weightedConnections = connections / weight;

			if(weightedConnections < weightedLeastConnections) {
				weightedLeastConnectionServer = server;
				weightedLeastConnections = weightedConnections;
			}
		}

		return weightedLeastConnectionServer;
	}
}