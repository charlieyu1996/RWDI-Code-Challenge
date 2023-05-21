
public class AdventofCode2022 {
    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println(
                    "Incorrect arguments, please enter the day number for the question followed by a space, then the input text file. (e.g. 1 C:\\user\\Day_1_input.txt)");
        } else {
            if (args[0].equals("1")) {
                Day_1_Calorie_Counting.parseInput(args[1], 3);
            } else if (args[0].equals("2")) {
                Day_2_Rock_Paper_Scissors.parseInput(args[1]);
            } else if (args[0].equals("3")) {
                Day_3_Rucksack_Reorganization.parseInput(args[1]);
            } else if (args[0].equals("4")) {
                Day_4_Camp_Cleanup.parseInput(args[1]);
            } else if (args[0].equals("5")) {
                Day_5_Supply_Stacks.parseInput(args[1]);
            } else if (args[0].equals("6")) {
                Day_6_Tuning_Trouble.parseInput(args[1], 14);
            } else if (args[0].equals("7")) {
                Day_7_No_Space_Left_On_Device.parseInput(args[0], 70000000, 30000000);
            }
        }
    }
}
