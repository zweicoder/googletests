package Addition;

import java.io.*;

/**
 * Created by User on 20/8/15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/asd.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            String inp = reader.readLine();
            String ans;

            writer.write(String.format("Case #%s: ", i+1));
//            writer.write(ans);
            writer.newLine();
        }

        writer.close();
        reader.close();
    }
}
