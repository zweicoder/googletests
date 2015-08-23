import java.io.*;
import java.util.Arrays;

/**
 * Created by User on 18/8/15.
 */
public class Template {
    final static boolean log = false;
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/asd.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int numCase = 0; numCase < numCases; numCase++) {
            System.out.println(String.format("Case #%s: ", numCase + 1));
            String inp = reader.readLine();
//            int N = Integer.parseInt(inp.split("\\s")[0]);


            writer.write(String.format("Case #%s: ", numCase+1));
            writer.newLine();
            int ans = 0;

            System.out.println("Answer: " + ans);
        }


        writer.close();
        reader.close();
    }
}
