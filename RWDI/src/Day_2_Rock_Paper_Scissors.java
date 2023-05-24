import java.util.ArrayList;
import java.io.*;

public class Day_2_Rock_Paper_Scissors implements PuzzleInterface{
    final int rock = 1;
    final int paper = 2;
    final int scissor = 3;

    final int lose = 0;
    final int draw = 3;
    final int win = 6;


    // Helper mmethod that fives the score for one round
    // p1 = {what player one played}
    // p2 = {whaat player two played}
    public int calculateScoreHelper(String p1, String p2){
        if (p1.equals("A")){
            if (p2.equals("X")){
                return rock + draw;
            }else if (p2.equals("Y")){
                return paper + win;
            }else{
                return scissor;
            }
        }else if (p1.equals("B")){
            if (p2.equals("X")){
                return rock;
            }else if (p2.equals("Y")){
                return paper + draw;
            }else{
                return scissor + win;
            }
        } else{
            if (p2.equals("X")){
                return rock + win;
            }else if (p2.equals("Y")){
                return paper;
            }else{
                return scissor + draw;
            }
        }
    }


    public int calculateScore(String[][] input){
        int score = 0;
        for (String[] currRound : input){
            score += calculateScoreHelper(currRound[0], currRound[1]);
        }
        return score;
    }

    // Helper method that gives the score for one round
    // p1 = {what player one played}
    // result = {the result the player needs to get}
    public int calculateScoreHelperPart2(String p1, String result){
        if (p1.equals("A")){
            if (result.equals("X")){
                return scissor;
            }else if (result.equals("Y")){
                return rock + draw;
            }else{
                return paper + win;
            }
        }else if (p1.equals("B")){
            if (result.equals("X")){
                return rock;
            }else if (result.equals("Y")){
                return paper + draw;
            }else{
                return scissor + win;
            }
        } else{
            if (result.equals("X")){
                return paper;
            }else if (result.equals("Y")){
                return scissor + draw;
            }else{
                return rock + win;
            }
        }
    }


    public int calculateScorePart2(String[][] input){
        int score = 0;

        for (String[] currRound : input){
            score += calculateScoreHelperPart2(currRound[0], currRound[1]);
        }

        return score;
    }


    // example input:
    // A Y
    // B X
    // C Z
    public String[][] parseInput(String fileName){
        ArrayList<String[]> completeArray = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while((line=br.readLine())!=null){
                String[] split = line.split("\\s+");
                completeArray.add(split);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return completeArray.stream().map(k -> k).toArray(String[][]::new);
    }


    @Override
    public void printPart1(String fileName) {
        String[][] completeArray = parseInput(fileName);
        System.out.println(calculateScore(completeArray));
    }


    @Override
    public void printPart2(String fileName) {
        String[][] completeArray = parseInput(fileName);
        System.out.println(calculateScorePart2(completeArray));
    }
}
