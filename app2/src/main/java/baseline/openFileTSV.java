package baseline;
//opens a file depending on what is selected
public class openFileTSV {
    private String number;
    private String value;
    private String name;
    public void parseInformation(String words){
        //check to see which open was called
        //parse through the information
        //and set it to the private word
        String word[] = words.split("\t");
        number = word[0];
        name = word[1];
        value = word[2];
    }
    public String getName(){
        return name;
    }
    public String getValue(){
        return value;
    }
    public String getNumber(){
        return number;
    }
}
