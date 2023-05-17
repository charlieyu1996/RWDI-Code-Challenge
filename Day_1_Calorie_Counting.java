import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Day_1_Calorie_Counting {
    public static int getMostCalories(ArrayList<ArrayList<Integer>> elfCalories){
        int currMax = 0;
        // Create a PriorityQueue that sorts in Descending order
        // PriorityQueue<Integer> pq = new PriorityQueue<>(3, (calorie1, calorie2) -> calorie1 >= calorie2 ? -1 : 1 ); 

        for (ArrayList<Integer> currElf : elfCalories){
            int total = 0;
            for (int currCalorie : currElf){
                total += currCalorie;
            }
            currMax = Math.max(currMax, total);
        }
        return currMax;
    }

    public static void main(String[] args){
        try {
            // "Day_1_input.txt"
            String fileName = args[0];

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

            System.out.println(getMostCalories(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



