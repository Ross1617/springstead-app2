package baseline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//saves the File depending on what is selected
public class saveFileTSV {
    public void fileSave(File file, ArrayList<String> list) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        int lines = list.size();
        for(int i = 0; i < lines; i++){
            String old = list.get(i);
            String words[] = old.split("\t");
            String serialNumber = words[0];
            String name = words[1];
            String value = words[2];
            String tabSep = words[0] + "\t" + words[1] + "\t" + words[2];
            printWriter.write(tabSep);
            printWriter.write("\n");
        }
        printWriter.close();

    }
        //create newPrinter
        //get the size of the arrayList
        //check to see what save is called
        //for however long print line by line
            //depending on what was selected
        //close the printer
}

