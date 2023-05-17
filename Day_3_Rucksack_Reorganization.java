import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Day_3_Rucksack_Reorganization {

    public static int convertCharactertoNum(Character c){
        if (c >= 97 && c <= 122){
            return c - 96;
        }else if (c >= 65 && c <= 90){
            return c - 38;
        }
        return 0;
    }


    public static int calculatePriority(ArrayList<String> rucksacks){
        int sum = 0;

        for (String currSack : rucksacks){
            HashSet<Character> items = new HashSet<>();

            int length = currSack.length() / 2;
            for (int i = 0; i < length; i++){
                items.add(currSack.charAt(i));
            }

            for (int i = length; i < currSack.length(); i++){
                if (items.contains(currSack.charAt(i))){
                    sum += convertCharactertoNum(currSack.charAt(i));
                    break;
                }
            }
        }
        return sum;
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

            ArrayList<String> completeArray = new ArrayList<>();
            while((line=br.readLine())!=null){
                completeArray.add(line);
            }

            System.out.println(calculatePriority(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
