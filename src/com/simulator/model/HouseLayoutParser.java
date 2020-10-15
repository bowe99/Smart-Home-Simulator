package src.com.simulator.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HouseLayoutParser {

    private static House houseObject;
    private static File layoutFile;
    private static String currentWord = "";

    public static void loadFile() throws Exception{
        layoutFile = new File("house_layout_txt.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(layoutFile))){
            String line = reader.readLine();
            int linenumber = 0;

        
            //To scan through the lines of information
            while(line !=null){
                ++linenumber;

                //Identifying the key word between quotation marks
                //remove whitespace first******
                //Removed quotation marks
                line = line.trim();
                if(line.charAt(0) == '\"'){
                    currentWord="";
                    for(int i=1;!(line.charAt(i) == '\"'); i++){
                        currentWord = currentWord + line.charAt(i);
                    }
                    //currentWord should now contain the key word between the quotation marks
                    //depending on the word at hand, the "handleWord" method will process what to do next
                    HouseLayoutParser.handleWord(reader, line, currentWord);
                }
                //moving onto the next line of information
                line = reader.readLine();
            }
            reader.close();


        }
        catch(IOException e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
        } 
    }


    //method to handle the word that was provided
    private static void handleWord(BufferedReader reader, String line, String currentWord){
        switch(currentWord){

            case "address":
                String address = "";
                for(int i = 9; i<line.length(); ++i){
                    if(line.charAt(i) == '"'){
                        ++i;
                        while(line.charAt(i) != '"'){
                        address = address + line.charAt(i);
                        ++i;
                        }
                    }
                }
                houseObject = new House(address);
                break;
            //handle the rooms case
            case "rooms":
                //if there are open brackets next to the rooms section
                if(line.indexOf('{')!=-1){
                    //then identify what the next word is

                    /* 
                    line = reader.readLine(); 
                    */ 

                }
            break;
            default: System.out.print("error");

        }

    }














}
