import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Day_1_Calorie_Counting {

    // returns the top N calories from the list of calories
    public static int getMostCalories(ArrayList<ArrayList<Integer>> elfCalories, int topN){
        int currMax = 0;
        // Create a PriorityQueue that sorts in ascending order
        PriorityQueue<Integer> pq = new PriorityQueue<>((calorie1, calorie2) -> calorie1 >= calorie2 ? 1 : -1); 

        for (ArrayList<Integer> currElf : elfCalories){
            int total = 0;
            for (int currCalorie : currElf){
                total += currCalorie;
            }

            // offer the value to pq first, and remove the smallest number if over capacity
            pq.offer(total);
            if (pq.size() > topN){
                pq.poll();
            }
        }
        
        for (Integer i : pq){
            System.out.println(i);
            currMax += i;
        }

        return currMax;
    }

    public static void main(String[] args){
        try {
            // example input: "Day_1_input.txt"
            String fileName = args[0];

            // example input: 3
            int topN = Integer.parseInt(args[1]);

            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();

            String line;

            ArrayList<Integer> currArray = new ArrayList<>();
            ArrayList<ArrayList<Integer>> completeArray = new ArrayList<>();
            while((line=br.readLine())!=null){
                if (line.equals("")){
                    completeArray.add(currArray);
                    currArray = new ArrayList<>();
                }else{
                    int currInt = Integer.parseInt(line);
                    currArray.add(currInt);
                }
            }

            if (currArray.size() > 0){
                completeArray.add(currArray);
            }

            System.out.println(getMostCalories(completeArray, topN));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



