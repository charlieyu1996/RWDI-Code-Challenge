import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.io.*;

public class Day_5_Supply_Stacks implements PuzzleInterface{
    final int stackSize = 4;

    // instructions: first index = quantity, second index = from stack number, third index = to stack number
    // this method moves crates one at a time, reversing the stack when moving
    public String topCrate(ArrayList<Deque<Character>> stacks, ArrayList<String[]> instructions){
        StringBuilder sb = new StringBuilder();
        for (String[] currInstruction : instructions){
            Integer quantity = Integer.parseInt(currInstruction[0]);
            Integer from = Integer.parseInt(currInstruction[1])-1;
            Integer to = Integer.parseInt(currInstruction[2])-1;

            // move the crates one by one to the destination stack
            for (int i = 0; i < quantity; i++){
                Character poppedCharacter = stacks.get(from).removeLast();
                stacks.get(to).addLast(poppedCharacter);
            }
        }

        for (Deque<Character> currStack : stacks){
            sb.append(currStack.peekLast());
        }

        return sb.toString();
    }

    // generate a list of stacks given the size
    public ArrayList<Deque<Character>> generateStacks(int size){
        ArrayList<Deque<Character>> stack = new ArrayList<>();
        for(int i = 0; i < size; i++){
            stack.add(new ArrayDeque<Character>());
        }
        return stack;
    }


    // instructions: first index = quantity, second index = from stack number, third index = to stack number
    // this method moves crates together and keeps the stack order
    public String crateMover9001(ArrayList<Deque<Character>> stacks, ArrayList<String[]> instructions){
        StringBuilder sb = new StringBuilder();

        for (String[] currInstruction : instructions){
            Integer quantity = Integer.parseInt(currInstruction[0]);
            Integer from = Integer.parseInt(currInstruction[1])-1;
            Integer to = Integer.parseInt(currInstruction[2])-1;

            Deque<Character> moveTogetherStack = new ArrayDeque<Character>();

            // append the group of crates to be moved together
            for (int i = 0; i < quantity; i++){
                Character poppedCharacter = stacks.get(from).removeLast();
                moveTogetherStack.add(poppedCharacter);
            }

            // keep the order of the group crates by using another Deque
            while (!moveTogetherStack.isEmpty()){
                stacks.get(to).addLast(moveTogetherStack.removeLast());
            }
        }

        for (Deque<Character> currStack : stacks){
            sb.append(currStack.peekLast());
        }


        return sb.toString();
    }


    // example input
    //     [D]    
    // [N] [C]    
    // [Z] [M] [P]
    //  1   2   3 

    // move 1 from 2 to 1
    // move 3 from 1 to 3
    // move 2 from 2 to 1
    // move 1 from 1 to 2
    public void parseInput(String fileName, int partNum){
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            ArrayList<String[]> instructions = new ArrayList<>();
            ArrayList<Deque<Character>> stacks = new ArrayList<>();

            boolean firstLine = true;
            boolean instructionStart = false;
            int numberOfStacks = 0;
            while((line=br.readLine())!=null){
                if (firstLine){
                    // use the first line to calculate the number of stacks
                    numberOfStacks = (line.length()+1) / stackSize;
                    stacks = generateStacks(numberOfStacks);
                    firstLine = false;
                }

                if (line.equals("")){
                    // if the line is empty, the next following line must be the instructions
                    instructionStart = true;
                }else if (!instructionStart){
                    // parse the stack
                    int index = 0;
                    int counter = 0;
                    int i = 0; 
                    while (i < line.length()){
                        if (counter >= stackSize){
                            index++;
                            counter -= stackSize;
                        }    
                        if (line.charAt(i) == '['){
                            stacks.get(index).addFirst(line.charAt(++i));
                            i++;
                            counter+=2;
                        }else{
                            counter++;
                            i++;
                        }
                    }
                }else if (instructionStart){
                    // start parsing the instructions
                    String[] splitLine = line.split("\\s+");
                    String[] currInstruction = new String[]{splitLine[1], splitLine[3], splitLine[5]};
                    instructions.add(currInstruction);
                }
            }

            if (partNum == 1){
                System.out.println(topCrate(stacks, instructions));
            }else if (partNum == 2){
                System.out.println(crateMover9001(stacks, instructions));
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
        parseInput(fileName, 1);
    }

    @Override
    public void printPart2(String fileName) {
        parseInput(fileName, 2);
    }
}
