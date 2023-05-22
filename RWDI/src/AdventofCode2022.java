
public class AdventofCode2022 {
    public static void printParts(PuzzleInterface p, String fileName){
        System.out.println("Part 1:");
        p.printPart1(fileName);
        System.out.println("Part 2:");
        p.printPart2(fileName);
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println(
                    "Incorrect arguments, please enter the day number for the question followed by a space, then the input text file. (e.g. 1 C:\\user\\Day_1_input.txt)");
        } else {
            if (args[0].equals("1")) {
                PuzzleInterface p = new Day_1_Calorie_Counting();
                printParts(p, args[1]);
            } else if (args[0].equals("2")) {
                PuzzleInterface p = new Day_2_Rock_Paper_Scissors();
                printParts(p, args[1]);
            } else if (args[0].equals("3")) {
                PuzzleInterface p = new Day_3_Rucksack_Reorganization();
                printParts(p, args[1]);
            } else if (args[0].equals("4")) {
                PuzzleInterface p = new Day_4_Camp_Cleanup();
                printParts(p, args[1]);
            } else if (args[0].equals("5")) {
                PuzzleInterface p = new Day_5_Supply_Stacks();
                printParts(p, args[1]);
            } else if (args[0].equals("6")) {
                PuzzleInterface p = new Day_6_Tuning_Trouble();
                printParts(p, args[1]);
            } else if (args[0].equals("7")) {
                PuzzleInterface p = new Day_7_No_Space_Left_On_Device();
                printParts(p, args[1]);
            }
        }
    }
}
