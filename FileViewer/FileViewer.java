import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/*
Eric Adams
COP 3252
Assignment 4
 */
public class FileViewer {
    static final String errorMsg = "Usage: java -jar hw4.jar [-i[<file>|<directory>]|-v <file>|-c <sourceFile> <destFile>]";
    public static void main(String[] args){
        String sourceFile = null, destFile = null, vFile = null, iFile = null;
        int mode = 0;  // 1 for -i no 2nd parameter, 2 for -i w/ 2nd parameter, 3 for -v, 4 for -c
        if(args.length == 0){
            //same as -i with no 2nd parameter
            mode = 1;
        }
        else if(args.length < 4){
            //check for -i, -v, -c
            if(args.length == 3){
                //should be -c
                if(args[0].equals("-c")){
                    sourceFile = args[1];
                    destFile = args[2];
                    mode = 4;
                }
                else{
                    //improper format for command line args
                    System.out.println(errorMsg);
                }
            }
            else if(args.length == 2){
                //could be -i or -v
                if(args[0].equals("-v")){
                    vFile = args[1];
                    mode = 3;
                }
                else if(args[0].equals("-i")){
                    iFile = args[1];
                    mode = 2;
                }
                else{
                    System.out.println(errorMsg);
                }
            }
            else if(args.length == 1){
                //should be -i
                if(!args[0].equals("-i")){
                    System.out.println(errorMsg);
                }
                else{
                    mode = 1;
                }
            }
        }
        else{
            //too many command line args
            System.out.println(errorMsg);
        }
        //check mode, if 0 exit otherwise handle accordingly
        if(mode == 0){
            System.exit(0);
        }
        else if(mode == 1){ //-i
            File info = new File(".");
            information(info);
        }
        else if(mode == 2){ //-i x
            File info = new File(iFile);
            information(info);

        }
        else if(mode == 3){ //-v x
            File view = new File(vFile);
            View(view);

        }
        else{ //-c x y
            File source = new File(sourceFile);
            File dest = new File(destFile);
            Copy(source, dest);
        }
    }

    private static void information(File info){
        if(!info.exists()){
            //file doesn't exist
            System.out.println("Invalid File");
            System.exit(0);
        }
        else if(info.isDirectory()){
            //file is a directory, so print out all nested directories and files along with sizes
            System.out.println("Size\tFilename\n");
            File[] nested = info.listFiles();  //gets all files and directories in info
            if(nested.length > 0) {
                Arrays.sort(nested, new SizeSort());
                for (File i : nested) {
                    if(i.isDirectory()){
                        System.out.print("*\t");
                    }
                    else{
                        System.out.print(i.length() + "\t");
                    }
                    System.out.println(i.getName());
                }
            }
        }
        else{ //prints out information about the file
            System.out.println("File Path: " + info.getAbsolutePath());
            System.out.println("Is executable? " + info.canExecute());
            System.out.println("Size: " + info.length());
            Date modified = new Date(info.lastModified());
            System.out.println("Last Modified Date: " + modified.toString());
        }
    }
    private static void View(File view){
        FileInputStream vfs = null;
        if(view.exists()){
            if(view.isFile()){
                //File exists, use file stream to print it to screen
                try {
                    vfs = new FileInputStream(view);
                    int i;
                    while((i = vfs.read()) != -1 ){
                        System.out.print((char)i);
                    }
                }
                catch(java.io.FileNotFoundException e){
                    System.out.println("File not found");
                    System.exit(0);
                }
                catch(java.io.IOException f){
                    System.out.println("Unable to read from file");
                    System.exit(0);
                }
                finally {
                    try {
                        if (vfs != null) {
                            vfs.close();
                        }
                    }
                    catch(java.io.IOException e){
                        System.out.println(e);
                    }
                }
            }
            else{
                System.out.println("File is a directory");
                System.exit(0);
            }
        }
        else{
            System.out.println("File not found");
            System.exit(0);
        }
    }
    private static void Copy(File source, File dest){
        FileInputStream cpy = null;
        FileOutputStream wrt = null;
        if(dest.exists()){
            //file already exists, exit
            System.out.println("Destination file already exists, unable to copy");
            System.exit(0);
        }
        else if(!source.exists()){
            System.out.println("Source file doesn't exist, unable to copy");
            System.exit(0);
        }
        else if(source.isDirectory() || dest.isDirectory()){
            System.out.println("Unable to copy a directory");
            System.exit(0);
        }
        else{
            //perform copying here
            try {
                cpy = new FileInputStream(source);
                wrt = new FileOutputStream(dest);
                int i;
                while((i = cpy.read()) != -1 ){
                    wrt.write(i);
                }
            }
            catch(java.io.FileNotFoundException e){
                System.out.println("File not found");
                System.exit(0);
            }
            catch(java.io.IOException f){
                System.out.println("Unable to read from file");
                System.exit(0);
            }
            finally {
                try {
                    if (cpy != null) {
                        cpy.close();
                    }
                    if(wrt != null){
                        wrt.close();
                    }
                }
                catch(java.io.IOException e){
                    System.out.println(e);
                }
            }
        }
    }
    private static class SizeSort implements Comparator<File>{
        //used to sort by file size from least to greatest.
        @Override
        public int compare(File f1, File f2) {
            if (f1.length() == f2.length()) {
                return 0;
            } else if (f1.length() > f2.length()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
