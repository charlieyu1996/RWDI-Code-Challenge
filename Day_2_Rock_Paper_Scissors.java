import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Day_2_Rock_Paper_Scissors {
    static Map<String, Integer> rockPaperScissorScore = new HashMap<>();
    
    public static void initMap(){
        rockPaperScissorScore.put("A",1);
        rockPaperScissorScore.put("B",2);
        rockPaperScissorScore.put("C",3);
        rockPaperScissorScore.put("X",1);
        rockPaperScissorScore.put("Y",2);
        rockPaperScissorScore.put("Z",3);
    }


    public static int calculateScore(ArrayList<String[]> input){
        int score = 0;
        for (String[] currRound : input){
            score += rockPaperScissorScore.get(currRound[1]);
            if (currRound[0].equals("A")){
                if (currRound[1].equals("X")){
                    score += 3;
                }else if (currRound[1].equals("Y")){
                    score += 6;
                }
            }else if (currRound[0].equals("B")){
                if (currRound[1].equals("Y")){
                    score += 3;
                }else if (currRound[1].equals("Z")){
                    score += 6;
                }
            }else if (currRound[0].equals("C")){
                if (currRound[1].equals("Z")){
                    score += 3;
                }else if (currRound[1].equals("X")){
                    score += 6;
                }
            }
        }
        return score;
    }
    
    public static void main(String[] args){
        try {
            // example input: "Day_2_input.txt"
            String fileName = args[0];

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

            initMap();
            System.out.println(calculateScore(completeArray));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
