package t9spelling;

import java.io.*;
import java.util.HashMap;

/**
 * Created by User on 18/8/15.
 */
public class T9Spelling {
    final static boolean log = true;

    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/C-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            String inp = reader.readLine();
            String ans = new Keypad().parse(inp);

            writer.write(String.format("Case #%s: ", i + 1));
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

    private static class Keypad {
        private HashMap<Character, Integer> keymap = new HashMap<Character, Integer>();

        public Keypad() {
            keymap.put('a', 2);
            keymap.put('b', 22);
            keymap.put('c', 222);
            keymap.put('d', 3);
            keymap.put('e', 33);
            keymap.put('f', 333);
            keymap.put('g', 4);
            keymap.put('h', 44);
            keymap.put('i', 444);
            keymap.put('j', 5);
            keymap.put('k', 55);
            keymap.put('l', 555);
            keymap.put('m', 6);
            keymap.put('n', 66);
            keymap.put('o', 666);
            keymap.put('p', 7);
            keymap.put('q', 77);
            keymap.put('r', 777);
            keymap.put('s', 7777);
            keymap.put('t', 8);
            keymap.put('u', 88);
            keymap.put('v', 888);
            keymap.put('w', 9);
            keymap.put('x', 99);
            keymap.put('y', 999);
            keymap.put('z', 9999);
            keymap.put(' ', 0);
        }

        private int lastPressed = -1;

        public String parse(String inp) {
            String res = "";
            for (char c : inp.toCharArray()) {
                int key = keymap.get(c);
                int phoneKey = downsize(key);
                if (phoneKey == lastPressed) {
                    res = res.concat(" ");
                }
                res = res.concat(String.valueOf(key));
                lastPressed = phoneKey;
            }
            return res;
        }
        private int downsize(int key){
            if (key < 10) {
                return key;
            } else if (key < 100) {
                return key / 10;
            } else if (key < 1000) {
                return key / 100;
            } else if (key < 10000) {
                return key / 1000;
            }
            return key;
        }
    }
}
