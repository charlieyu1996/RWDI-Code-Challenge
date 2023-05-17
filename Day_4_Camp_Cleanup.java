import java.util.ArrayList;
import java.io.*;


public class Day_4_Camp_Cleanup {
    public static int getFullOverlapPairs(ArrayList<String[][]> pairs){
        int counter = 0;
        for (String[][] currPair : pairs){
            int pair1Min = Integer.parseInt(currPair[0][0]);
            int pair1Max = Integer.parseInt(currPair[0][1]);

            int pair2Min = Integer.parseInt(currPair[1][0]);
            int pair2Max = Integer.parseInt(currPair[1][1]);

            // First case: first pair overlaps second pair completely
            // Second case: second pair overlaps first pair completely  
            if (pair1Min <= pair2Min && pair1Max >= pair2Max ||
                pair2Min <= pair1Min && pair2Max >= pair1Max){
                counter++;
            }
        }
        return counter;
    }


    public static int getOverlapPairs(ArrayList<String[][]> pairs){
        int counter = 0;

        for (String[][] currPair : pairs){
            int pair1Min = Integer.parseInt(currPair[0][0]);
            int pair1Max = Integer.parseInt(currPair[0][1]);

            int pair2Min = Integer.parseInt(currPair[1][0]);
            int pair2Max = Integer.parseInt(currPair[1][1]);

            // First case: first pair tail overlaps with second pair head
            // Second case: first pair head overlaps with second pair tail
            // Third case: first pair overlaps second pair completely
            // Forth case: second pair overlaps first pair completely  
            if (pair1Max >= pair2Min && pair2Min >= pair1Min || 
                pair1Min <= pair2Max && pair1Min >= pair2Min || 
                pair1Min <= pair2Min && pair1Max >= pair2Max || 
                pair2Min <= pair1Min && pair2Max >= pair1Max){
                counter++;
            }
        }

        return counter;
    }


    public static void main(String args[]){
        try {
            // example input: "Day_2_input.txt"
            String fileName = args[0];

            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();

            String line;

            ArrayList<String[][]> completeArray = new ArrayList<>();
            while((line=br.readLine())!=null){
                String[] currPair = line.split(",");
                String[] pair1 = currPair[0].split("-");
                String[] pair2 = currPair[1].split("-");
                String[][] splitCurrPair = new String[][]{pair1, pair2};
                completeArray.add(splitCurrPair);
            }

            System.out.println(getOverlapPairs(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
