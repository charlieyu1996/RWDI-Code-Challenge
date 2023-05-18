import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Day_7_No_Space_Left_On_Device {

    public static class TreeNode {
        String fileName;
        int fileSize;
        HashMap<String, TreeNode> dirChildren;
        ArrayList<TreeNode> fileChildren;
        TreeNode parent;

        TreeNode(String fileName, TreeNode parent){
            this.fileName = fileName;
            this.fileSize = 0;
            this.parent = parent;
        }

        // used for files
        TreeNode(String fileName, int fileSize, TreeNode parent){
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.parent = parent;
        }

        // used for directories
        TreeNode(String fileName, int fileSize, HashMap<String, TreeNode> dirChildren, ArrayList<TreeNode> fileChildren, TreeNode parent){
            this.fileName = fileName; 
            this.fileSize = fileSize;
            this.dirChildren = dirChildren;
            this.fileChildren = fileChildren;
            this.parent = parent;
        }
    }

    // traverse through the file system and calculate total sum for each directory
    public static int calculateFileSizeSum(TreeNode node){
        int totalSize = 0;
        if (node.fileChildren != null){
            for (TreeNode currChildren : node.fileChildren){
                totalSize += currChildren.fileSize;
            }
        }

        if (node.dirChildren != null){
            for (HashMap.Entry<String, TreeNode> entry : node.dirChildren.entrySet()){
                totalSize += calculateFileSizeSum(entry.getValue());
            }
        }
        node.fileSize = totalSize;
        return totalSize;
    }

    // calculate the total size of directories that are less than equal to limit
    public static int sumOfFileSize(TreeNode node, int limit){
        int totalSum = 0;
        if (node.fileSize <= limit && node.dirChildren != null) totalSum+=node.fileSize;
        if (node.dirChildren != null){
            for (HashMap.Entry<String, TreeNode> entry : node.dirChildren.entrySet()){
                totalSum += sumOfFileSize(entry.getValue(), limit);
            }
        }
        return totalSum;
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

            TreeNode root = new TreeNode("/", 0, new HashMap<>(), new ArrayList<>(), null);
            TreeNode currParent = root;
            boolean listMode = false;

            while((line=br.readLine())!=null){
                String[] splitLine = line.split("\\s+");
                if (splitLine[0].equals("$")){
                    if (splitLine[1].equals("cd")){
                        // this is a directory switch command
                        if (splitLine[2].equals("/")){
                            //  go to root
                            currParent = root;
                        }else if (splitLine[2].equals("..")){
                            // go to outer level directory
                            currParent = currParent.parent;
                        }else {
                            // assume the user want to go to an existing directory
                            currParent = currParent.dirChildren.get(splitLine[2]);
                        }
                    }else if (splitLine[1].equals("ls")){
                        // the user lists all the files and directories
                        listMode = true;
                    }
                }else if (listMode){
                    // read all the directoies and files and store them into TreeNode
                    if (splitLine[0].equals("dir")){
                        // it is a dir
                        TreeNode tempNode = new TreeNode(splitLine[1], 0, new HashMap<>(), new ArrayList<>(), currParent);
                        currParent.dirChildren.put(splitLine[1], tempNode);
                    }else{
                        // it is a file
                        TreeNode tempNode = new TreeNode(splitLine[1], Integer.parseInt(splitLine[0]), currParent);
                        currParent.fileChildren.add(tempNode);
                    }
                }else {
                    listMode = false;
                }
            }

            calculateFileSizeSum(root);
            
            System.out.println(sumOfFileSize(root, 100000));

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + args[0] + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
