import java.util.*;

public class Main {

    static void bfs(Map<String, List<String>> graph,
                    String source, int maxDepth) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> depth = new HashMap<>();

        queue.add(source);
        visited.add(source);
        depth.put(source, 0);

        System.out.println("Users reached within depth "
                + maxDepth + ":");

        while (!queue.isEmpty()) {

            String current = queue.poll();
            int currentDepth = depth.get(current);

            System.out.println(current +
                    " (Depth " + currentDepth + ")");

            if (currentDepth == maxDepth)
                continue;

            for (String neighbor :
                    graph.getOrDefault(current,
                            new ArrayList<>())) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.add(neighbor);
                    depth.put(neighbor,
                            currentDepth + 1);
                }
            }
        }

        System.out.println("\nTotal Reach = "
                + visited.size() + " users");
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new HashMap<>();

        // Graph connections
        graph.put("A",
                Arrays.asList("B", "C"));

        graph.put("B",
                Arrays.asList("D", "E"));

        graph.put("C",
                Arrays.asList("E", "F"));

        graph.put("D",
                Arrays.asList("G"));

        graph.put("E",
                Arrays.asList("G", "H"));

        graph.put("F",
                Arrays.asList("H", "I"));

        graph.put("G", new ArrayList<>());
        graph.put("H", new ArrayList<>());
        graph.put("I", new ArrayList<>());

        // BFS from A with depth 3
        bfs(graph, "A", 3);
    }
}