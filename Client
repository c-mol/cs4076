
package org.openjfx.project4076proto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 */
public class Server {
    public boolean stopflagraised=false;
    private static final int PORT = 12345;

    public static void main(String[] args) {
        new Server().startServer();
    }
    //sendMessageToServer(oppick+"£"+mod+"£"+pickdate.getValue()+"£"+hours.getValue()+"£"+room.getValue());
    
    /**
     * 
     * takes the information for what to add to the and checks if it clashes with anything in the csv schedule file
     * if clash return the clash message otherwise adds it to the csvfile
     * @param li info to be added to the module
     * @return String clash message/done message
     * @throws java.io.IOException 
     */
    public String addlecture(String[] li) throws IOException{
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
    /**
     * @param writein
     * Checks if there is already a class in this room at that time.
     * returns true/false
     * @return Boolean
     */
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
    //sendMessageToServer(oppick+"£"+mod+"£"+pickdate.getValue()+"£"+hours.getValue()+"£"+room.getValue());
    
    /**
     * @param writein
     * Checks if a student has a module at this time
     * returns true/false
     * @return Boolean
     */
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
    /**
     * @param module
     * Gets all the classes that take this module.
     * @return  ArrayList<String>
     * @throws java.io.FileNotFoundException
     */
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
       
    public String removelecture(String[] li) throws IOException{
    String writein=li[1]+","+li[2]+","+li[3]+","+li[4];
        ArrayList<String> temp = new ArrayList<>();
		Scanner scedscan = new Scanner(new File("src/scedule.csv"));
		
		while(scedscan.hasNext()) {
			String i = scedscan.nextLine();
			
			if((i).equalsIgnoreCase(writein))
                         {

			}
			else {temp.add(i);}
		}
		PrintWriter clear = new PrintWriter("src/scedule.csv");
		for(String x:temp) {
			PrintWriter writer = new PrintWriter(new FileWriter("src/scedule.csv",true));
			writer.println(x);
			writer.close();
        
                }
    scedscan.close();
    return "Lecture removed";
    }
    
    private String findschedulefordateandmod(String[] splitMessage) throws FileNotFoundException {
    //sendMessageToServer("Display Schedule"+"£"+classyear.getText());
    String[] monday=new String[9];
    String[] tuesday=new String[9];
    String[] wednesday=new String[9];
    String[] thursday=new String[9];
    String[] friday=new String[9];
    String[] saturday=new String[9];
    String[] sunday=new String[9];
    //String[] schedarr=new String[70];
    
    ArrayList<String> modules=getModules(splitMessage[1]);//moduels class takes
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
    Schedule+=wednesday[i]+"£";
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
    private void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started, waiting for connections...");

            while (true&&!stopflagraised) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Continuously read messages from the client
            String message;
            while ((message = reader.readLine()) != null) {
                String[] splitMessage=message.split("£");
                if(splitMessage[0].equalsIgnoreCase("stop")){
                stopflagraised=true;
                System.out.println("Received: " + message);
                message="TERMINATE";
                writer.println(message);
                }
                else if(splitMessage[0].equalsIgnoreCase("Add a Lecture")){
                System.out.println("adding lecture");
                String comfimdoness=addlecture(splitMessage);
                writer.println(comfimdoness);
                }
                else if(splitMessage[0].equalsIgnoreCase("Remove a lecture")){
                System.out.println("removing lecture");
                String comfimdoness= removelecture(splitMessage);
                writer.println(comfimdoness);
                }
                else if(splitMessage[0].equalsIgnoreCase("Display Schedule")){
               //sendMessageToServer("Display Schedule"+"£"+module.getText()+"£"+pickdatetodiplay.getValue());
               //splitMessage
                String sched=findschedulefordateandmod(splitMessage);
                writer.println(sched);
                }
                else{
                System.out.println("Received: " + message);
                writer.println(message); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Client connection closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
