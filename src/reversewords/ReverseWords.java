package reversewords;

import java.io.*;

/**
 * Created by User on 18/8/15.
 */
public class ReverseWords {
    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/B-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
//            System.out.println("Case #" + i);
            String inp = reader.readLine();
            String[] arr = inp.split("\\s");
            StringBuilder builder = new StringBuilder();
            builder.append(arr[arr.length-1]);
            for(int j= arr.length-2; j>=0; j--){
                builder.append(" ").append(arr[j]);
            }
            writer.write(String.format("Case #%s: ", i + 1));
            writer.write(builder.toString());
            writer.newLine();
        }

        writer.close();
        reader.close();
    }
}
