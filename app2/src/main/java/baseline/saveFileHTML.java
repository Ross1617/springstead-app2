package baseline;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveFileHTML {
    private FileWriter file;
    public void saveFile(ArrayList<String> list) throws IOException {
        String html = "<!DOCTYPE html>\n  <html>\n  <style>\n  table, th, td {\n    border:1px solid black;\n  }\n  </style>\n  <body>\n  \n  <h2>Ross' Item Manager</h2>\n  \n  <table style=\"width:100%\">";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("enterName.html");
        File fileMaker = fileChooser.showSaveDialog(new Stage());
        file = new FileWriter(fileMaker);

        try{
            BufferedWriter bw = new BufferedWriter(file);
            bw.write(html);
            bw.write("<tr>");
            bw.write("<td>" + "Serial Number" +"</td>+ <td>" + "Name" +"</td> + <td>" + "Value" +"</td>");
            bw.write("</tr>");
            for(int i = 0; i< list.size();i++){
                bw.write("<tr>");
                String old = list.get(i);
                String words[] = old.split("\t");
                String serialNumber = words[0];
                String name = words[1];
                String value = words[2];
                bw.write("<td>" + serialNumber +"</td>+ <td>" + name +"</td> + <td>" + value +"</td>");
                bw.write("</tr>");
            }
            bw.write("</table>");
            bw.write("\n" +
                    "</body>\n" +
                    "</html>");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
