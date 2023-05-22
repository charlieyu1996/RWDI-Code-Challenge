import java.io.*;
import java.util.ArrayList;

public class Day_3_Rucksack_Reorganization implements PuzzleInterface{
    final int characterSize = 53;

    // convert the current character (a-z, A-Z) to a priority number
    public int convertCharactertoNum(Character c){
        if (c >= 97 && c <= 122){
            return c - 96;
        }else if (c >= 65 && c <= 90){
            return c - 38;
        }
        return 0;
    }

    // fill in the characterList for the first run, and compare with currString to find duplicates
    public boolean[] fillCharacterListFromString(String currString, boolean[] characterList, boolean firstRun){
        boolean[] tempCharacterList = new boolean[characterSize];
        for (int i = 0; i < currString.length(); i++){
            int priorityNumber = convertCharactertoNum(currString.charAt(i));
            if (!firstRun && characterList[priorityNumber]){
                // there is a duplicate
                tempCharacterList[priorityNumber] = true;
            }else if (firstRun){
                // for the first run, fill in the characterList
                characterList[priorityNumber] = true;
            }
        }
        return tempCharacterList;
    }

    // traverse through the boolean and calculate priority if true
    public int calculatePrioritySum(boolean[] characterList){
        int sum = 0;
        for (int i = 0; i < characterList.length; i++){
            sum += characterList[i] ? i : 0; 
        }
        return sum;
    }

    // calculate a common item among 3 rucksacks and return the sum of the common items
    // Runtime: O(mn) where n is the number of rucksacks and m is the average length of a rucksack
    public int calculateBadgeSum(ArrayList<String> rucksacks, int groupSize){
        int sum = 0;
        int counter = 0;
        int arraySize = rucksacks.size();
        while (counter + groupSize <= arraySize){
            boolean[] characterList = new boolean[characterSize];
            boolean firstRun = true;
            for (int i = 0; i < groupSize; i++){
                boolean[] tempCharacterList = fillCharacterListFromString(rucksacks.get(counter), characterList, firstRun);
                if (!firstRun)
                    characterList = tempCharacterList;
                counter++;
                firstRun = false;
            }
            sum += calculatePrioritySum(characterList);
        }
        return sum;
    }

    // compares the first half of a rucksack and the other half of a rucksack to find a shared item character
    public int calculateBadgePrio(ArrayList<String> rucksacks){
        int sum = 0;
        for (String currSack : rucksacks){
            int halfLength = currSack.length() / 2;
            boolean[] characterList = new boolean[characterSize];
            fillCharacterListFromString(currSack.substring(0, halfLength), characterList, true);
            characterList = fillCharacterListFromString(currSack.substring(halfLength, currSack.length()), characterList, false);
            sum += calculatePrioritySum(characterList);
        }
        return sum;
    }

    // Example input:
    // vJrwpWtwJgWrhcsFMMfFFhFp
    public ArrayList<String> parseInput(String fileName){
        ArrayList<String> completeArray = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line=br.readLine())!=null){
                completeArray.add(line);
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
        ArrayList<String> completeArray = parseInput(fileName);
        System.out.println(calculateBadgePrio(completeArray));
    }

    @Override
    public void printPart2(String fileName) {
        ArrayList<String> completeArray = parseInput(fileName);
        System.out.println(calculateBadgeSum(completeArray, 3));
    }
}
