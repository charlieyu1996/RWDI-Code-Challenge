import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Day_7_No_Space_Left_On_Device implements  PuzzleInterface{

    // a tree structure that contains the file name, file size, a list of directories
    // and a list of files for the current directory (node)
    public class TreeNode {
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
    public int calculateFileSizeSum(TreeNode node){
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
    public int sumOfFileSize(TreeNode node, int limit){
        int totalSum = 0;
        if (node.dirChildren != null){
            if (node.fileSize <= limit) totalSum+=node.fileSize;
            for (HashMap.Entry<String, TreeNode> entry : node.dirChildren.entrySet()){
                totalSum += sumOfFileSize(entry.getValue(), limit);
            }
        }
        return totalSum;
    }


    // helper recursion method to find the smallest directory size to delete
    public int findSmallestDirToDeleteRec(TreeNode node, int spaceNeeded){
        int bestDirForNow = Integer.MAX_VALUE;
        if (node.dirChildren != null && node.fileSize >= spaceNeeded){
            // if it is a directory and the size of the directory is greater than equal to the space needed
            bestDirForNow = node.fileSize;
            for (HashMap.Entry<String, TreeNode> entry : node.dirChildren.entrySet()){
                bestDirForNow = Math.min(findSmallestDirToDeleteRec(entry.getValue(), spaceNeeded), bestDirForNow);
            }
        }
        return bestDirForNow;
    }

    // find the smallest directory size to delete
    public int findSmallestDirToDelete(TreeNode node, int totalDiskSpace, int updateSpaceNeeded){
        // calculate how much space is needed to delete
        int spaceNeeded = updateSpaceNeeded - (totalDiskSpace - node.fileSize) ;
        return findSmallestDirToDeleteRec(node, spaceNeeded);
    }


    // Example input:
    // $ cd /
    // $ ls
    // dir a
    // 14848514 b.txt
    // 8504156 c.dat
    // dir d
    // $ cd a
    // $ ls
    // dir e
    // 29116 f
    // 2557 g
    // 62596 h.lst
    // $ cd e
    // $ ls
    // 584 i
    // $ cd ..
    // $ cd ..
    // $ cd d
    // $ ls
    // 4060174 j
    // 8033020 d.log
    // 5626152 d.ext
    // 7214296 k
    // 
    // totalDiskSpace = the size of the disk space
    //  updateSpaceNeeded = the size needed to update
    public TreeNode parseInput(String fileName){
        TreeNode root = new TreeNode("/", 0, new HashMap<>(), new ArrayList<>(), null);
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
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
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + fileName + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void printPart1(String fileName) {
        TreeNode root = parseInput(fileName);
        calculateFileSizeSum(root); // update the directory tree with total size for each directory
        System.out.println(sumOfFileSize(root, 100000));
    }

    @Override
    public void printPart2(String fileName) {
        TreeNode root = parseInput(fileName);
        calculateFileSizeSum(root); // update the directory tree with total size for each directory
        System.out.println(findSmallestDirToDelete(root, 70000000, 30000000));
    }
}
