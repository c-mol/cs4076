
package org.openjfx.project4076proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.concurrent.Task;

public class Schedulecture extends Task<String>{
       private String splitMessage;

    public Schedulecture (String s) {
    splitMessage=s;
   }

   @Override
   protected String call() throws Exception {
      updateMessage("    Processing... ");
      String result = findschedulefordateandmod(splitMessage);
      updateMessage("    Done.  ");
      return result;
   }
   
    
    private String findschedulefordateandmod(String splitMessage) throws FileNotFoundException {
    //sendMessageToServer("Display Schedule"+"£"+classyear.getText());
    String[] monday=new String[9];
    String[] tuesday=new String[9];
    String[] wednesday=new String[9];
    String[] thursday=new String[9];
    String[] friday=new String[9];
    String[] saturday=new String[9];
    String[] sunday=new String[9];
    //String[] schedarr=new String[70];
    
    ArrayList<String> modules=getModules(splitMessage);//moduels class takes
    //mod day hrs room
    int x=0;
    String Schedule="";
    Scanner scedscan = new Scanner(new File("src/scedule.csv"));
    while(scedscan.hasNext()){
    String input=scedscan.nextLine();
    String[] CheckinScedule=input.split(",");
    if(modules.contains(CheckinScedule[0])){
        //this is a module taken by this class
        if(CheckinScedule[1].equalsIgnoreCase("monday"))
        {monday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}
        else if(CheckinScedule[1].equalsIgnoreCase("tuesday"))
        {tuesday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}
        
        else if(CheckinScedule[1].equalsIgnoreCase("wednesday"))
        {wednesday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}
        
        else if(CheckinScedule[1].equalsIgnoreCase("thursday"))
        {thursday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}        
        else if(CheckinScedule[1].equalsIgnoreCase("friday"))
        {friday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}
        else if(CheckinScedule[1].equalsIgnoreCase("saturday"))
        {saturday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}        
        else if(CheckinScedule[1].equalsIgnoreCase("sunday"))
        {sunday[(Integer.parseInt(CheckinScedule[2])-900)/100]=input;}
        
    }
    }
    
    for(int i=0;i<9;i++){
    //Schedule+="!";
    Schedule+=monday[i]+"£";
    Schedule+=tuesday[i]+"£";
    Schedule+=wednesday[i]+"£";
    Schedule+=thursday[i]+"£";
    Schedule+=friday[i]+"£";
    Schedule+=saturday[i]+"£";
    Schedule+=sunday[i]+"£";
    Schedule+="!";
    }
    return Schedule;
    }
    
    public ArrayList<String> getModules(String classyear) throws FileNotFoundException{
    ArrayList<String> modulesinclass=new ArrayList<String>();
    Scanner scanclassinyear = new Scanner(new File("src/classyearinmod.csv"));
    String[] ctmoduleln;
    while(scanclassinyear.hasNext()){
     ctmoduleln=scanclassinyear.nextLine().split(",");
    if(classyear.equalsIgnoreCase(ctmoduleln[1])){//module to be written in will equal the module in the class list
       modulesinclass.add(ctmoduleln[0]);//add modules
    }
    }
    scanclassinyear.close();
    return modulesinclass;
}
}
