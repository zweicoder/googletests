package super2048;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by User on 20/8/15. 8.25?
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/B-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            String inp = reader.readLine();
            String size = inp.split("\\s")[0];
            String dir = inp.split("\\s")[1];
            Board board = new Board(Integer.parseInt(size));
            for (int j = 0; j < Integer.parseInt(size); j++) {
                String[] raw = reader.readLine().split("\\s");
                int[] row = new int[raw.length];
                for (int k = 0; k < row.length; k++) {
                    row[k] = Integer.parseInt(raw[k]);
                }
                board.setRow(j, row);
            }
            board.move(dir);


            writer.write(String.format("Case #%s: ", i + 1));
            writer.newLine();
            for (int j = 0; j < board.getSize(); j++) {
                String row = Arrays.toString(board.getRow(j));
                System.out.println(row);
                for(int r:board.getRow(j)){
                    writer.write(r+" ");
                }
                writer.newLine();
            }
        }

        writer.close();
        reader.close();
    }

    private static class Board {
        private final int[][] state;
        private int size;

        public int getSize() {
            return size;
        }

        public Board(int size) {
            this.size = size;
            this.state = new int[size][size];
        }

        public void setRow(int idx, int[] row) {
            this.state[idx] = row;
        }

        public void setCol(int idx, int[] col){
            for(int i=0; i<col.length; i++){
                this.state[i][idx] = col[i];
            }
        }

        public int[] getRow(int idx) {
            return this.state[idx];
        }

        public void move(String direction) {
            if (direction.equals("right")) {
                // FOr each row
                for (int i = 0; i < state.length; i++) {
                    // For each column, merge
                    LinkedList<Integer> nums = new LinkedList<Integer>();
                    for (int j = 0; j < state[i].length; j++) {
                        int col = state[i][j];
                        if (col != 0) {
                            nums.push(col);
                        }
                    }
                    // Move and set new row
                    int[] row = new int[getSize()];
                    Integer last = -1;
                    for (int j = row.length - 1; j >= 0; j--) {
                        if (!nums.isEmpty()) {
                            if (!nums.isEmpty()) {
                                row[j] = getNextValue(nums);
                            }
                        }
                    }
                    setRow(i, row);
                }
            }else if(direction.equals("left")){
                for (int i = 0; i < state.length; i++) {
                    // For each column, merge

                    LinkedList<Integer> nums = new LinkedList<Integer>();
                    for (int j = 0; j < state[i].length; j++) {
                        int col = state[i][j];
                        if (col != 0) {
                            nums.add(col);
                        }
                    }
//                    System.out.println(nums);
                    // Move and set new row
                    int[] row = new int[getSize()];
                    for (int j = 0; j < row.length; j++) {
                        if (!nums.isEmpty()) {
                            row[j] = getNextValue(nums);
                        }
                    }
                    setRow(i, row);
                }
            }
            else if(direction.equals("up")){
                for (int column = 0; column < getSize(); column++) {
                    // For each column, merge
                    LinkedList<Integer> nums = new LinkedList<Integer>();
                    for (int row = 0; row < getSize(); row++) {
                        int item = state[row][column];
                        if (item != 0) {
                            nums.add(item);
                        }
                    }
//                    System.out.println(nums);
                    // Move and set new row
                    int[] col = new int[getSize()];
                    for (int j = 0; j < col.length; j++) {
                        if (!nums.isEmpty()) {
                            col[j] = getNextValue(nums);
                        }
                    }
                    setCol(column, col);
                }
            }
            else if(direction.equals("down")){
                for (int column = 0; column < getSize(); column++) {
                    // For each column, merge
                    LinkedList<Integer> nums = new LinkedList<Integer>();
                    for (int row = 0; row < getSize(); row++) {
                        int item = state[row][column];
                        if (item != 0) {
                            nums.push(item);
                        }
                    }
//                    System.out.println(nums);
                    // Move and set new row
                    int[] col = new int[getSize()];
                    for (int j = col.length - 1; j >= 0; j--) {
                        if (!nums.isEmpty()) {
                            col[j] = getNextValue(nums);
                        }
                    }
                    setCol(column, col);
                }
            }
        }

        private Integer getNextValue(LinkedList<Integer> nums) {
            Integer cur = nums.pop();
            if(!nums.isEmpty() && nums.peek().equals(cur)){
                return 2 * nums.pop();
            }else{
                return cur;
            }
        }
    }
}
