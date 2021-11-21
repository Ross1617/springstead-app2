package baseline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//saves the File depending on what is selected
public class saveFileTSV {
    public void fileSave(File file, ArrayList<String> list) throws FileNotFoundException {
        //create newPrinter
        PrintWriter printWriter = new PrintWriter(file);
        //get the size of the arrayList
        int lines = list.size();
        for(int i = 0; i < lines; i++){
            //for however long print line by line
            String old = list.get(i);
            String words[] = old.split("\t");
            String tabSep = words[0] + "\t" + words[1] + "\t" + words[2];
            printWriter.write(tabSep);
            printWriter.write("\n");
        }
        //close the printer
        printWriter.close();
    }
}

