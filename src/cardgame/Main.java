package cardgame;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by User on 22/8/15.
 */
public class Main {
    static LinkedList<int[]>[] memo;
    static int[] cards;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input/C-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int numCase = 0; numCase < numCases; numCase++) {
            System.out.println(String.format("Case #%s: ", numCase + 1));
            String inp = reader.readLine();
            int N = Integer.parseInt(inp.split("\\s")[0]);
            K = Integer.parseInt(inp.split("\\s")[1]);
            String[] raw = reader.readLine().split("\\s");
            memo = new LinkedList[N];
            cards = new int[N];
            for (int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(raw[i]);
            }

            int[] base = new int[]{cards[0]};
            LinkedList<int[]> list = new LinkedList<int[]>();
            list.add(base);
            memo[0] = list;

            System.out.println("Input: "+Arrays.toString(cards));
            LinkedList<int[]> ans = dp(N);
            int min = -1;
            for(int[] arr:ans){
//                System.out.println(Arrays.toString(arr));
                min = min == -1 ? arr.length : Math.min(arr.length, min);
            }

            System.out.println("Answer: " + min);
            writer.write(String.format("Case #%s: ", numCase + 1));
            writer.write(String.valueOf(min));
            writer.newLine();
        }

        writer.close();
        reader.close();
    }

    private static LinkedList<int[]> dp(int i) {
        if (memo[i-1] == null) {
            HashSet<int[]> set = new HashSet<>();
            for (int[] choice : dp(i - 1)) {
                int size = choice.length;
                int[] others;
                // old choices
                if (size >= 2 && choice[size - 1] - choice[size - 2] == K && cards[i - 1] - choice[size - 1] == K) {
                    // minify if possible
                    others = new int[size-2];
                    System.arraycopy(choice, 0, others, 0, size - 2);
                } else {
                    //combine choice and last idx
                    others= new int[size+1];
                    System.arraycopy(choice, 0, others, 0, size);
                    others[size] = cards[i-1];
                }
                set.add(others);
            }
            // new choice
            if (i >= 3 && cards[i - 1] - cards[i - 2] == K && cards[i - 2] - cards[i - 3] == K) {
                int[] newChoice= new int[i-3];
                System.arraycopy(cards, 0, newChoice, 0, i - 3);
                set.add(newChoice);
            }
            memo[i-1] = new LinkedList<>(set);

        }
        return memo[i-1];
    }
}
