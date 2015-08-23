package captainhammer;

import java.io.*;

/**
 * Created by User on 19/8/15.
 */
public class Main {
    final static boolean log = false;
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/B-small-practice (1).in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            String inp = reader.readLine();
            String[] values = inp.split("\\s");
            float velocity = Float.valueOf(values[0]);
            float distance= Float.valueOf(values[1]);
            double x = distance * 9.8 / Math.pow(velocity, 2.0);

            if(x > 1) x = 1;
            float angle = (float) (Math.asin(x)/2.0);
//            System.out.println(angle);

            String ans = String.format("%.7f%n",angle/Math.PI*180);

            writer.write(String.format("Case #%s: ", i+1));
            writer.write(ans);
            writer.newLine();
            if (log) {
                System.out.println("Case #" + (i + 1));
                System.out.println("Input: " + inp);
                System.out.println("Ans: "+ans);
            }
        }

        writer.close();
        reader.close();
    }
}
