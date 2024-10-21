import java.util.PriorityQueue;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }
}

public class Main {

    public static void printCodes(Node root, String code) {
        if (root == null) {
            return;
        }
        if (root.ch != '$') {
            System.out.println(root.ch + ": " + code);
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    static class NodeComparator implements java.util.Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.freq - b.freq;
        }
    }

    public static void huffmanCoding(char[] chars, int[] freqs) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < chars.length; i++) {
            minHeap.add(new Node(chars[i], freqs[i]));
        }

        while (minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            Node newNode = new Node('$', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            minHeap.add(newNode);
        }

        printCodes(minHeap.peek(), "");
    }

    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] freqs = {50, 10, 30, 5, 3, 2};

        huffmanCoding(chars, freqs);
    }
}
