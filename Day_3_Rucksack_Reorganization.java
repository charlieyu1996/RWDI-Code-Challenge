import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Day_3_Rucksack_Reorganization {

    // convert the current character (a-z, A-Z) to a priority number
    public static int convertCharactertoNum(Character c){
        if (c >= 97 && c <= 122){
            return c - 96;
        }else if (c >= 65 && c <= 90){
            return c - 38;
        }
        return 0;
    }

    // populate a HashSet of characters from a string
    public static HashSet<Character> fillInHashSet(String s){
        HashSet<Character> items = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            items.add(s.charAt(i));
        }

        return items;
    }

    //  merge duplicate characters among the HashSet and string
    public static HashSet<Character> compareHashString(HashSet<Character> items, String s){
        HashSet<Character> sharedItems = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if (items.contains(s.charAt(i))){
                sharedItems.add(s.charAt(i));
            }
        }
        return sharedItems;
    }

    // find the first occuring duplicate in the HashSet from the string
    public static Character firstDuplicate(HashSet<Character> items, String s){
        for (int i = 0; i < s.length(); i++){
            if (items.contains(s.charAt(i))){
                return s.charAt(i);
            }
        }
        return '0';
    }

    // calculate the sum of duplicated items in a rucksack
    public static int calculatePriority(ArrayList<String> rucksacks){
        int sum = 0;

        for (String currSack : rucksacks){
            int halfLength = currSack.length() / 2;
            HashSet<Character> items = fillInHashSet(currSack.substring(0, halfLength));

            for (int i = halfLength; i < currSack.length(); i++){
                if (items.contains(currSack.charAt(i))){
                    sum += convertCharactertoNum(currSack.charAt(i));
                    break;
                }
            }
        }
        return sum;
    }

    // calculate a common item among 3 rucksacks and return the sum of the common items
    public static int calculateBadge(ArrayList<String> rucksacks){
        int sum = 0;
        int counter = 0;
        int arraySize = rucksacks.size();
        while (counter + 3 <= arraySize){            
            HashSet<Character> items1 = fillInHashSet(rucksacks.get(counter));
            HashSet<Character> items2 = compareHashString(items1, rucksacks.get(++counter));
            sum += convertCharactertoNum(firstDuplicate(items2, rucksacks.get(++counter)));
            counter++;
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

            System.out.println(calculateBadge(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
