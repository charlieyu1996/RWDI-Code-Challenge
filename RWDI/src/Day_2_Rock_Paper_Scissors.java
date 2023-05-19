import java.util.ArrayList;
import java.io.*;

public class Day_2_Rock_Paper_Scissors {
    static final int rock = 1;
    static final int paper = 2;
    static final int scissor = 3;

    static final int lose = 0;
    static final int draw = 3;
    static final int win = 6;


    // Helper mmethod that fives the score for one round
    // p1 = {what player one played}
    // p2 = {whaat player two played}
    public static int calculateScoreHelper(String p1, String p2){
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


    public static int calculateScore(ArrayList<String[]> input){
        int score = 0;
        for (String[] currRound : input){
            score += calculateScoreHelper(currRound[0], currRound[1]);
        }
        return score;
    }

    // Helper method that gives the score for one round
    // p1 = {what player one played}
    // result = {the result the player needs to get}
    public static int calculateScoreHelperPart2(String p1, String result){
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


    public static int calculateScorePart2(ArrayList<String[]> input){
        int score = 0;

        for (String[] currRound : input){
            score += calculateScoreHelper(currRound[0], currRound[1]);
        }

        return score;
    }


    // example input:
    // A Y
    // B X
    // C Z
    public static void parseInput(String fileName){
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();

            String line;

            ArrayList<String[]> completeArray = new ArrayList<>();
            while((line=br.readLine())!=null){
                String[] split = line.split("\\s+");
                completeArray.add(split);
            }

            System.out.println(calculateScorePart2(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
