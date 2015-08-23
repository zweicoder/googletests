package moist;

import java.io.*;

/**
 * Created by User on 19/8/15.
 */
public class Main {
    final static boolean log = false;

    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/C-small-practice-2.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            int numNames = Integer.parseInt(reader.readLine());
            String[] names = new String[numNames];
            for (int j = 0; j < numNames; j++) {
                names[j] = reader.readLine();
            }

            String ans = String.valueOf(bubbleSort(names));

            writer.write(String.format("Case #%s: ", i + 1));
            writer.write(ans);
            writer.newLine();
            if (log) {
                System.out.println("Case #" + (i + 1));
//                System.out.println("Input: " + inp);
                System.out.println("Ans: " + ans);
            }
        }

        writer.close();
        reader.close();
    }

    private static int bubbleSort(String[] names) {
        int times = 0;
        for (int i = 0; i < names.length - 1; i++) {
            if (names[i].compareTo(names[i + 1]) > 0){
                times++;
                String temp = names[i];
                names[i] = names[i+1];
                names[i+1] = temp;
            }
        }
        return times;
    }
}
