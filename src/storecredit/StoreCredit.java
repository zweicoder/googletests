package storecredit;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by User on 18/8/15.
 */
public class StoreCredit {
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("src/A-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"));

        for (int i = 0; i < numCases; i++) {
            System.out.println("Case #" + i);
            int totalCredit = Integer.valueOf(reader.readLine());
            int size = Integer.valueOf(reader.readLine());
            System.out.println("Credit: " + totalCredit);
            int[] array = parseInput(size, reader.readLine());
            Node[] nodes = process(size, array);

            int[] solution = solve(totalCredit, size, nodes);
            System.out.println("Solution: " + Arrays.toString(solution));
            assert array[solution[0]] + array[solution[1]] == totalCredit;
            Arrays.sort(solution);
            writer.write(String.format("Case #%s: ", i+1));
            writer.write(solution[0] + " " + solution[1]);
            writer.newLine();
        }

        writer.close();
        reader.close();
    }

    private static int[] solve(int totalCredit, int size, Node[] nodes) {
        for (int i = 0; i < size; ) {
            int j;
            if((j = find(totalCredit - nodes[i].value, nodes, i + 1, size)) != -1){
                return new int[]{nodes[i].idx+1, nodes[j].idx+1};
            }else{
                i++;
            }
        }
        return new int[]{-1, -1};
    }

    private static int find(int value, Node[] nodes, int start, int end) {
        int mid = (start + end) / 2;
        if (end - start == 1){
            return nodes[mid].value == value? mid: -1;
        }
        if(nodes[mid].value == value){
            return mid;
        }else if(nodes[mid].value > value){
            return find(value, nodes, start, mid);
        }else {
            return find(value, nodes, mid, end);
        }
    }

    private static Node[] process(int size,int[] array) {
        Node[] ret = new Node[size];
        for(int i=0; i<size; i++){
           ret[i] = new Node(array[i], i);
        }
        Arrays.sort(ret);
        // truncate based on max val possible
        return ret;
    }

    private static int[] parseInput(int size, String inp) {
        int[] ret = new int[size];
        int i = 0;
        for (String s : inp.split("\\s")) {
            ret[i++] = Integer.valueOf(s);
        }
        return ret;
    }

    private static class Node implements Comparable {
        int value;
        int idx;

        private Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            return this.value<= that.value? -1: 1;
        }
    }
}
