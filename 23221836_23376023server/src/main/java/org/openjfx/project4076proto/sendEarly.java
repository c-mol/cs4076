/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.openjfx.project4076proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



class sendEarly extends Thread {

  
   
    String[] ans;
    
    String day;
    String course;
  sendEarly(String dayofwk,String c) throws FileNotFoundException {
    course=c;
    day=dayofwk;
    }
  
  
    @Override
    public void run(){ 
        try {
           this.ans= findschedulefordateandmod(this.course);
                    
                    
                    
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(sendEarly.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(sendEarly.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    } 
    
      
synchronized  private String[] findschedulefordateandmod(String splitMessage) throws FileNotFoundException, IOException {
    //sendMessageToServer("Display Schedule"+"£"+classyear.getText());
    String[] monday=new String[19];
    String[] dayremove=new String[9];
    ArrayList<String> modules=getModules(splitMessage);//moduels class takes
    //mod day hrs room
    int x=0;
    String Schedule="";
    Scanner scedscan = new Scanner(new File("src/scedule.csv"));
    int j=0;
    while(scedscan.hasNext()){
    String input=scedscan.nextLine();
    String[] CheckinScedule=input.split(",");
    if(modules.contains(CheckinScedule[0])){
        //this is a module taken by this class
        if(CheckinScedule[1].equalsIgnoreCase(this.day))
        {int time=j*100+900;
            monday[j]=CheckinScedule[0]+","+CheckinScedule[1]+","+time+","+CheckinScedule[3];
             monday[j+10]=input;
            j++;
           
        } 
    }
    }
    
    /*for(int i=0;i<9;i++){
    //Schedule+="!";   
    if(!(monday[i]==null)){
    Schedule+=monday[i];
    add(monday[i]);
   

    }
    if(!(dayremove[i]==null))
    {remove(dayremove[i]);}
    }*/
    return monday;
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