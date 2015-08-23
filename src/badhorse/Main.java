package badhorse;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 19/8/15.
 * 3.38
 *
 Input

 Output

 2
 1
 Dead_Bowie Fake_Thomas_Jefferson
 3
 Dead_Bowie Fake_Thomas_Jefferson
 Fake_Thomas_Jefferson Fury_Leika
 Fury_Leika Dead_Bowie
 Case #1: Yes
 Case #2: No


 */
public class Main {
    final static boolean log = false;
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/A-small-practice-2.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            int numPairs = Integer.valueOf(reader.readLine());
            HashMap<String, Node> map = new HashMap<String, Node>();
            for(int j=0; j<numPairs; j++){
                // generate 'graph', hashed node
                String[] names = reader.readLine().split("\\s");
                for(String key: names){
                    if(map.get(key) == null){
                        map.put(key, new Node(key));
                    }
                }
                // Link as adjacent node
                map.get(names[0]).link(map.get(names[1]));
            }
            boolean ans = true;
            for(Node node:map.values()){
                if(node.getColor() == 0 && !recursivelyColor(node,1)){
                    ans = false;
                }
            }


            writer.write(String.format("Case #%s: ", i + 1));
            writer.write(String.valueOf(ans?"Yes":"No"));
            writer.newLine();
            if (log) {
                System.out.println("Case #" + (i + 1));
//                System.out.println("Input: " + inp);
                System.out.println("Ans: "+ans);
            }
        }

        writer.close();
        reader.close();
    }

    private static boolean recursivelyColor(Node node, int color) {
        if(node.getColor() != 0 && color == -node.getColor()){
            System.out.println("Error Coloring "+node.name +" as: "+color);
            return false;
        }else if(node.getColor() == 0){
            System.out.println("Coloring "+node.name +" as: "+color);
            node.color = color;
            for (Node n: node.getAdjacentNodes()){
                if(!recursivelyColor(n, -color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Node {
        private ArrayList<Node> adj = new ArrayList<Node>();
        private int color = 0;
        public String name;

        private Node(String name) {
            this.name = name;
        }

        public void link(Node node) {
            addAdjacent(node);
            node.addAdjacent(this);
        }
        public void addAdjacent(Node node){
            adj.add(node);
        }

        public ArrayList<Node> getAdjacentNodes() {
            return adj;
        }

        public int getColor() {
            return color;
        }
    }
}
