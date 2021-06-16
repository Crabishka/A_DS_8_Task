import java.util.*;

public class TicTacToeGraph {

    // 0 - null
    // 1 - cross
    // 2 - zero


    // 0th level - 2
    // 1th level - 1
    ArrayList<HashMap<Integer, Node>> graph = new ArrayList<>(10);
    Node root = new Node(0);

    public void createGraphOfState() {
        for (int i = 0; i < 10; i++) {
            graph.add(i, new HashMap<>());
        }

        graph.get(0).put(root.hashCode(), root);
        recFunc(0, root);
        countFactor();
    }

    public void recFunc(int level, Node curr) {
        if (isItWin(curr)) {
            curr.winFactor = 1;
            return;
        }
        if (isItLose(curr)) {
            curr.loseFactor = 1;
            return;
        }
        if (level == 9) {
            curr.drawFactor = 1;
            return;
        }


        for (int i = 0; i < 9; i++) {
            Node temp = doTurn(i, curr.field, level);
            if (temp == null) continue;
            if (graph.get(level + 1).get(temp.hashCode()) == null) {
                graph.get(level + 1).put(temp.hashCode(), temp);
                curr.neighbors.add(temp);
            } else {
                curr.neighbors.add(graph.get(level + 1).get(temp.hashCode()));
            }
            recFunc(level + 1, temp);
        }

    }

    public void countFactor() {
        for (int i = 8; i >= 0; i--) {
            for (Node node : graph.get(i).values()) {
                int size = node.neighbors.size();
                if (size == 0) continue;
                double l = 0;
                double w = 0;
                double d = 0;
                for (Node curr : node.neighbors) {
                    l += curr.loseFactor;
                    w += curr.winFactor;
                    d += curr.drawFactor;
                }
                node.loseFactor = l / size;
                node.winFactor = w / size;
                node.drawFactor = d / size;
            }
        }
    }

    public Node doTurn(int i, int field, int level) {
        int c = 100000000; // 100 000 000
        c = c / (int) Math.pow(10, i);
        if (field / c % 10 == 0) field += c * (level % 2 + 1);
        else return null;
        return new Node(field);
    }

    public boolean isItWin(Node curr) { // for 1
        // маски победы
        // 111хххххх
        // ххх111ххх
        // хххххх111
        // 1ххх1ххх1
        // хх1х1х1хх
        // 1хх1хх1хх
        // х1хх1хх1х
        // хх1хх1хх1
        int a = curr.field;
        if (curr.field / 1000000 == 111) return true;
        if (curr.field / 1000 % 1000 == 111) return true;
        if (curr.field % 1000 == 111) return true;
        if (getK(a, 8) == 1 && getK(a, 4) == 1 && getK(a, 0) == 1) return true;
        if (getK(a, 6) == 1 && getK(a, 4) == 1 && getK(a, 2) == 1) return true;
        if (getK(a, 8) == 1 && getK(a, 5) == 1 && getK(a, 2) == 1) return true;
        if (getK(a, 7) == 1 && getK(a, 4) == 1 && getK(a, 1) == 1) return true;
        if (getK(a, 6) == 1 && getK(a, 3) == 1 && getK(a, 0) == 1) return true;
        return false;
    }

    public int getK(int number, int i) {
        return (int) (number / Math.pow(10, i) % 10);
    }

    public boolean isItLose(Node curr) { // for 2
        int a = curr.field;
        if (curr.field / 1000000 == 222) return true;
        if (curr.field / 1000 % 1000 == 222) return true;
        if (curr.field % 1000 == 222) return true;
        if (getK(a, 8) == 2 && getK(a, 4) == 2 && getK(a, 0) == 2) return true;
        if (getK(a, 6) == 2 && getK(a, 4) == 2 && getK(a, 2) == 2) return true;
        if (getK(a, 8) == 2 && getK(a, 5) == 2 && getK(a, 2) == 2) return true;
        if (getK(a, 7) == 2 && getK(a, 4) == 2 && getK(a, 1) == 2) return true;
        if (getK(a, 6) == 2 && getK(a, 3) == 2 && getK(a, 0) == 2) return true;
        return false;
    }

    public Pair getTheBestNeighbor(int inputField) {  // заменить нормальным поиском
        double max = 0;
        int result = 0;
        Node temp = null;
        for (int i = 0; i < 10; i++) {
            if (graph.get(i).get(inputField) != null) temp = graph.get(i).get(inputField);
        }
        for (Node node : temp.neighbors) {
            if (node.winFactor > max) {
                max = node.winFactor;
                result = node.field;
            }
        }
        return new Pair(result, max);
    }

    public class Pair {
        int value;
        double prob;

        Pair(int a, double b) {
            value = a;
            prob = b;
        }
    }

    public class Node {


        @Override
        public int hashCode() {
            return field;
        }

        int field;
        double loseFactor;
        double drawFactor;
        double winFactor;


        Node(int field) {
            this.field = field;
        }


        ArrayList<Node> neighbors = new ArrayList<>();

    }
}