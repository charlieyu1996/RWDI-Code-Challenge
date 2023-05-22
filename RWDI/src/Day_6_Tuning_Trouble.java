import java.io.*;
import java.util.HashMap;

public class Day_6_Tuning_Trouble implements PuzzleInterface{

    // find the first ${length} length unique sequence from ${signal}, return -1 if not found
    public int getFirstMarker(String signal, int length){
        HashMap<Character, Integer> memo = new HashMap<>();

        int startPointer = 0;
        for (int i = 0; i < signal.length(); i++){
            Character currChar = signal.charAt(i);
            if (memo.containsKey(currChar) && memo.get(currChar) + 1 > startPointer){
                // make sure the new startPointer is strictly greater the previous startPointer
                // it is a duplicate, set starting pointer to the previous duplicated charaacter index
                startPointer = memo.get(currChar) + 1;
                if (startPointer > signal.length()) return -1;
                memo.put(currChar, i); // update the position of the character
            }else{
                // the character is not a duplicate

                // found a unique sequence
                if (i - startPointer >= length-1) return i+1;
                memo.put(currChar, i);
            }
        }

        return -1;
    }

    // Example input:
    // mjqjpqmgbljsphdztnvjfqwrcgsmlb
    public void parseInput(String fileName, int length){
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();

            String line;

            while((line=br.readLine())!=null){
                System.out.println(getFirstMarker(line, length));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printPart1(String fileName) {
        parseInput(fileName, 4);
    }

    @Override
    public void printPart2(String fileName) {
        parseInput(fileName, 14);
    }
}