import java.util.ArrayList;
import java.io.*;


public class Day_4_Camp_Cleanup implements PuzzleInterface{

    // find the number of pairs where one pair fully overlaps another
    public int getFullOverlapPairs(ArrayList<String[][]> pairs){
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


    // find the number of pairs where the pairs overlap each other
    public int getOverlapPairs(ArrayList<String[][]> pairs){
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

    // example input:
    // 2-4,6-8
    // 2-3,4-5
    // 5-7,7-9
    // 2-8,3-7
    // 6-6,4-6
    // 2-6,4-8
    public ArrayList<String[][]> parseInput(String fileName){
        ArrayList<String[][]> completeArray = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line=br.readLine())!=null){
                String[] currPair = line.split(",");
                String[] pair1 = currPair[0].split("-");
                String[] pair2 = currPair[1].split("-");
                String[][] splitCurrPair = new String[][]{pair1, pair2};
                completeArray.add(splitCurrPair);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return completeArray;
    }


    @Override
    public void printPart1(String fileName) {
        ArrayList<String[][]> completeArray =  parseInput(fileName);
        System.out.println(getFullOverlapPairs(completeArray));

    }


    @Override
    public void printPart2(String fileName) {
        ArrayList<String[][]> completeArray =  parseInput(fileName);
        System.out.println(getOverlapPairs(completeArray));
    }
}
