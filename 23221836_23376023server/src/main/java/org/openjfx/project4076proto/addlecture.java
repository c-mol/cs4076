/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.project4076proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 *
 * @author donag
 */
public class addlecture extends Task<String>  {

private String[] splitMessage;
   public addlecture (String[] s) {
    splitMessage=s;
   }

   @Override
   protected String call() throws Exception {
      updateMessage("    Processing... ");
      String result = add(splitMessage);
      updateMessage("    Done.  ");
      return result;
   }

   synchronized public String add(String[] li) throws IOException{
        
     
     String writein=li[1]+","+li[2]+","+li[3]+","+li[4];
     System.out.print(writein);
    if(clashingWithOtherModule(writein)){
    return "scedule clash!(other module is already scheduled here at this time)";
    }
    else if(clashingStudentHasTwoModules(writein)){
    return "scedule clash!(Students in a module have a class at this time)";
    }
    else{
    PrintWriter w=new PrintWriter(new FileWriter("src/scedule.csv",true));
    
    w.print(writein);
    w.println();
    w.close();
    return "done";
    }  
    }
    public boolean clashingWithOtherModule(String writein) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("src/scedule.csv"));
    String[] toadd = writein.split(",");
    while (scan.hasNext()) {
        String input = scan.nextLine();
        String[] inschedule = input.split(",");
        
         if (inschedule[3].equalsIgnoreCase(toadd[3]) // room
                   && inschedule[1].equalsIgnoreCase(toadd[1]) // date
                   && inschedule[2].equalsIgnoreCase(toadd[2])) { // time
            scan.close();
             return true;
        }
    }
    scan.close();
    return false;
}
   public boolean clashingStudentHasTwoModules(String writein) throws FileNotFoundException {
    Scanner scansched = new Scanner(new File("src/scedule.csv"));
    
    String[] writinsplit=writein.split(",");
    ArrayList<String> classesthattakethismodule=findClassesThatTakeThisModule(writinsplit[0]);
    String[] Alreadyinsched;
    while(scansched.hasNext()){
    Alreadyinsched=scansched.nextLine().split(",");
    if ((writinsplit[1].equalsIgnoreCase(Alreadyinsched[1]))
    && (writinsplit[2].equalsIgnoreCase(Alreadyinsched[2])))
    {//date and time are the same as this module
        ArrayList<String> othermodule=findClassesThatTakeThisModule(Alreadyinsched[0]);
        for(String s:classesthattakethismodule){
        if(othermodule.contains(s)){
            scansched.close();
            return true;
        }
        }
        }
    }
    return false;

    }
    public ArrayList<String> findClassesThatTakeThisModule(String module) throws FileNotFoundException{
    ArrayList<String> classesthattakethismodule=new ArrayList<>();
    Scanner scanclassinyear = new Scanner(new File("src/classyearinmod.csv"));
    String[] ctmoduleln;
    while(scanclassinyear.hasNext()){
     ctmoduleln=scanclassinyear.nextLine().split(",");
    if(module.equalsIgnoreCase(ctmoduleln[0])){//module to be written in will equal the module in the class list
       classesthattakethismodule.add(ctmoduleln[1]);
    }
    }
    scanclassinyear.close();
    return classesthattakethismodule;
}
}
