package newyearseve;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 22/8/15.
 */
public class Main {
    private static BigDecimal[][] memo;
    private static GlassNode[][] tree;

    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/B-large-practice (1).in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int numCase = 0; numCase < numCases; numCase++) {
            String inp = reader.readLine();
            int B = Integer.parseInt(inp.split("\\s")[0]); //750ml p bottle
            int L = Integer.parseInt(inp.split("\\s")[1]); //level
            int N = Integer.parseInt(inp.split("\\s")[2]); //numglass 250ml p glass
            System.out.println(String.format("Case #%s: ", numCase + 1));
            //construct glass tree of bottom-most level
            tree = new GlassNode[L][L];
            int count = 1;
            tree[0][0] = new GlassNode(count);
            for (int i = 1; i < L; i++) {
                for (int j = 0; j < L; j++) {
                    GlassNode node = new GlassNode(++count);
                    tree[i][j] = node;
                    if (j == 0) {
                        node.addParent(tree[i - 1][j]);
                    } else if (j == i) {
                        node.addParent(tree[i - 1][j - 1]);
                        break;
                    } else {
                        node.addParent(tree[i - 1][j]);
                        node.addParent(tree[i - 1][j - 1]);
                    }
                }
            }
//            for (int i = 0; i < L; i++) {
//                System.out.println(Arrays.toString(tree[i]));
//            }
            memo = new BigDecimal[L + 1][N + 1];
            for (int j = 0; j < N + 1; j++) {
                memo[1][j] = BigDecimal.ZERO;
            }

            memo[1][1] = BigDecimal.valueOf(B * 750);
            BigDecimal val = dp(L, N);
            BigDecimal ans = val.compareTo(glassSize) >= 1 ? glassSize : val;
            System.out.println("Answer: " + ans);
//            printMemo();
            writer.write(String.format("Case #%s: ", numCase + 1));
            writer.write(String.format("%.7f%n", ans));
            writer.newLine();
        }

        writer.close();
        reader.close();
    }

    private static void printMemo() {
        for (int i = 0; i < memo.length; i++) {
            System.out.println(Arrays.toString(memo[i]));
        }
    }

    private static BigDecimal dp(int l, int n) {
        //dp[l][n] = dp[l-1][node.getParents[0].count]+...dp[l-1][node.getParents[1]]
        // if dp[l][n]!=0
        if (memo[l][n] == null) {
            GlassNode node = getGlassNodeByIndex(n);
            ArrayList<GlassNode> parents = node.getParents();
            memo[l][n] = getExcess(dp(l - 1, node.getCount()));

            for (int i = 0; i < parents.size(); i++) {
                GlassNode parent = parents.get(i);

                memo[l][n] = memo[l][n].add(getExcess(dp(l - 1, parent.getCount())));
            }

        }
        return memo[l][n];
    }

    static final BigDecimal glassSize = BigDecimal.valueOf(250);

    private static BigDecimal getExcess(BigDecimal dp) {
//        System.out.println(dp+" : "+dp.compareTo(glassSize));
        if (dp.compareTo(glassSize) >= 1) {
            BigDecimal subtract = dp.subtract(glassSize);
            return subtract.divide(BigDecimal.valueOf(3),7,BigDecimal.ROUND_HALF_EVEN);
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    private static GlassNode getGlassNodeByIndex(int n) {
        int carryForward = n;
        for(int i=0; i<tree.length; i++){
            if(carryForward>i+1){
                carryForward-=(i+1);
            }else{
                return tree[i][carryForward-1];
            }
        }

        return null;
    }

    private static class GlassNode {
        private int count;
        private ArrayList<GlassNode> parents = new ArrayList<GlassNode>();

        public GlassNode(int count) {
            this.count = count;
        }

        public void addParent(GlassNode node) {
            this.parents.add(node);

        }

        public ArrayList<GlassNode> getParents() {
            return parents;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return String.valueOf(getCount());
        }
    }
}
