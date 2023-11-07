package DAA_Practicals;

import java.util.*;

class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class HuffmanEncoding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string to be encoded: ");
        String inputString = sc.nextLine();
        int[] charFreq = new int[256];
        for (int i = 0; i < inputString.length(); i++) {
            charFreq[inputString.charAt(i)]++;
        }
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(new MyComparator());
        for (int i = 0; i < 256; i++) {
            if (charFreq[i] > 0) {
                HuffmanNode node = new HuffmanNode();
                node.c = (char) i;
                node.data = charFreq[i];
                node.left = null;
                node.right = null;
                pq.add(node);
            }
        }
        while (pq.size() > 1) {
            HuffmanNode x = pq.peek();
            pq.poll();
            HuffmanNode y = pq.peek();
            pq.poll();
            HuffmanNode z = new HuffmanNode();
            z.data = x.data + y.data;
            z.c = '-';
            z.left = x;
            z.right = y;
            pq.add(z);
        }
        HuffmanNode root = pq.peek();
        printCodes(root, "");
    }

    public static void printCodes(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ": " + s);
            return;
        }
        printCodes(root.left, s + "0");
        printCodes(root.right, s + "1");
    }
}

// Output:
// Enter the string to be encoded: ROHIT
// H: 00
// T: 01
// O: 10
// R: 110
// I: 111
