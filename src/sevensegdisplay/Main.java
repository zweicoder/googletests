package sevensegdisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by User on 19/8/15. 10.12
 */
public class Main {
    final static boolean log = false;
    final static String[] digits = new String[]{
            "1111110",
            "0110000", "1101101", "1111001",
            "0110011", "1011011", "1011111",
            "1110000", "1111111", "1110011"
    };

    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/asd.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            System.out.println("Case #" + (i + 1));
            String inp = reader.readLine();
            System.out.println("Input: " + inp);
            String[] values = inp.split("\\s");
            HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
            int[] workingSegments = new int[7];
            // Find out which ones are faulty
            int size = Integer.parseInt(values[0]);
            for (int j = 1; j <= Math.min(size, 10); j++) {
                String raw = values[j];
                char[] chars = raw.toCharArray();
                System.out.println("Chars: " + Arrays.toString(chars));
                for (int k = 0; k < chars.length; k++) {
                    if (chars[k] == '1') {
                        workingSegments[k] = 1;
                    }
                }
            }
            System.out.println("Working segments: " + Arrays.toString(workingSegments));
            // Get the processed correct values
            char[][] processedDigits = new char[10][7];
            for (int j = 0; j < digits.length; j++) {
                processedDigits[j] = doAndOperation(workingSegments, digits[j].toCharArray());
//                System.out.println("Processed Digits: " + Arrays.toString(processedDigits[j]));
            }

            //do AND to find possible values
            for (int j = 1; j <= Math.min(size, 10); j++) {
                String raw = values[j];
                char[] processed = doAndOperation(workingSegments, raw.toCharArray());

                // Get the digit if any
                for (int k = 0; k < processedDigits.length; k++) {
                    char[] arr = processedDigits[k];
                    if (Arrays.equals(arr, processed)) {
                        ArrayList<Integer> list = map.containsKey(raw) ? map.get(raw) : new ArrayList<Integer>();
                        if (!list.contains(k)) {
                            list.add(k);
                        }
                        map.put(raw, list);
                    }
                }
            }
            System.out.println("Map: " + map);
            int[] convertedValues = new int[10];
            for (int j = 1; j <= 10; j++) {
                if (map.get(values[j]).size() == 1) {
                    convertedValues[j-1] = map.get(values[j]).get(0);
                    int k = j+1;
                    while (k!=j){
                        if(map.get(values[k]).contains(convertedValues[k-2]))
                        if(k==10){
                            k=1;
                        }else {
                            k++;
                        }
                    }
                    break;
                }
            }
            System.out.println("Converted Values: " + Arrays.toString(convertedValues));


            String ans;

            writer.write(String.format("Case #%s: ", i + 1));
//            writer.write(ans);
            writer.newLine();
            if (log) {
                System.out.println("Case #" + (i + 1));
                System.out.println("Input: " + inp);
                System.out.println("Ans: " + ans);
            }
        }

        writer.close();
        reader.close();
    }

    private static char[] doAndOperation(int[] workingSegments, char[] chars) {
        char[] ret = chars.clone();
        for (int k = 0; k < ret.length; k++) {
            if (!(ret[k] == '1' && workingSegments[k] == 1)) {
                ret[k] = '0';
            }
        }
        return ret;
    }
}
