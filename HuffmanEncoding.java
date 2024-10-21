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

/*
Time Complexity
The time complexity of the Huffman Coding algorithm can be broken down as follows:

Building the Min-Heap:

You first insert all n characters (nodes) into a min-heap (priority queue), which takes O(n log n) time. Inserting an element into a heap requires O(log n) time, and we perform this insertion n times.
Extracting nodes from the Min-Heap:

Each time you extract the two smallest elements from the min-heap, it takes O(log n) time, and we do this n-1 times, as we need to combine nodes until there's only one node left. Thus, this step takes O(n log n) time as well.
Inserting the new combined node into the Min-Heap:

After extracting two nodes and combining them, we insert the new node back into the min-heap. This takes O(log n) time for each of the n-1 operations, resulting in O(n log n) for this step as well.
Therefore, the total time complexity is:

𝑂
(
𝑛
log
⁡
𝑛
)
O(nlogn)

Space Complexity
The space complexity of Huffman Coding includes:

Min-Heap:

The priority queue (min-heap) stores all the nodes, and its maximum size is n. Thus, the space required for the heap is O(n).
Huffman Tree:

In the worst case, the Huffman tree can have up to 2n-1 nodes (including both internal and leaf nodes). Since we are building a binary tree, the space complexity for the tree is also O(n).
Auxiliary Space for Recursion:

The recursive function printCodes can go as deep as the height of the tree. In the worst case, the height of the Huffman tree can be O(n), but typically it is O(log n). Therefore, in the worst case, the recursion stack requires O(n) space.
Thus, the overall space complexity is:

𝑂
(
𝑛
)
O(n)

Summary:
Time Complexity: O(n log n)
Space Complexity: O(n)
*/
