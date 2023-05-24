import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Day_1_Calorie_Counting implements PuzzleInterface{

    // returns the top N calories from the list of calories
    // Runtime: O(n) where n is the length of the calorie list
    public int getMostCalories(int[][] elfCalories, int topN){
        int currMax = 0;
        // Create a PriorityQueue that sorts in descending order with an initial capacity of topN
        PriorityQueue<Integer> pq = new PriorityQueue<>(topN, (calorie1, calorie2) -> calorie1 >= calorie2 ? 1 : -1); 

        for (int[] currElf : elfCalories){
            int total = 0;
            for (int currCalorie : currElf){
                total += currCalorie;
            }

            // offer the value to pq first, and remove the smallest number if over capacity
            pq.offer(total);
            if (pq.size() > topN)
                pq.poll();
        }
        
        // aggregate the topN calories from priority queue
        for (Integer i : pq){
            currMax += i;
        }

        return currMax;
    }

    // example input:
    // 1000
    // 2000
    // 3000

    // 4000

    // 5000
    // 6000

    // 7000
    // 8000
    // 9000

    // 10000
    // topN represents the top N of Elves carrying the most Calories
    public int[][] parseInput(String fileName){
        ArrayList<ArrayList<Integer>> completeArray = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            ArrayList<Integer> currArray = new ArrayList<>();            
            while((line=br.readLine())!=null){
                if (line.equals("")){
                    // if the line is empty, it is a new Elf
                    completeArray.add(currArray);
                    currArray = new ArrayList<>();
                }else{
                    int currInt = Integer.parseInt(line);
                    currArray.add(currInt);
                }
            }

            if (currArray.size() > 0)
                completeArray.add(currArray);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // convert arraylist of arraylists to array of arrays
        return completeArray.stream().map(k -> k.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
    }

    @Override
    public void printPart1(String fileName){
        int[][] completeArray = parseInput(fileName);
        System.out.println(getMostCalories(completeArray, 1));
    }

    @Override
    public void printPart2(String fileName){
        int[][] completeArray = parseInput(fileName);
        System.out.println(getMostCalories(completeArray, 3));
    }
}



