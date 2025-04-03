

package org.openjfx.project4076proto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * 
 */
public class Server extends Application{
    public boolean stopflagraised=false;
    private static final int PORT = 12345;
    String[] daysofweek={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" , "Sunday"};
    public static void main(String[] args) {
       launch();
       
        
    }
    //sendMessageToServer(oppick+"£"+mod+"£"+pickdate.getValue()+"£"+hours.getValue()+"£"+room.getValue());

    private void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started, waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                new handleClient(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
      Pane pane=new Pane();
     
        GridPane schedulepane = new GridPane();
        pane.getChildren().clear();
        pane.getChildren().add(schedulepane);
                        
        Scene scene = new Scene(pane, 600, 400);
        stage.setTitle("Server");
        stage.setScene(scene);
        
        
        Button stopsced = new Button("stop");
        stopsced.setOnAction(event -> {
                
                
                stage.close();
            }
        );
        
            
        TextField classyear=new TextField();
        
        Button display = new Button("Display");
        GridPane timetable=new GridPane();
        
        display.setOnAction((ActionEvent event) -> {
        timetable.getChildren().clear();
        if (!classyear.getText().equals("")) {
            String schedule1;
            try {
            schedule1 = findschedulefordateandmod(classyear.getText());
            System.out.println(schedule1);
            String[] x1 = schedule1.split("!");
            String[] nine = x1[0].split("£");
            String[] ten = x1[1].split("£");
            String[] eleven = x1[2].split("£");
            String[] twelve = x1[3].split("£");
            String[] one = x1[4].split("£");
            String[] two = x1[5].split("£");
            String[] three = x1[6].split("£");
            String[] four = x1[7].split("£");
            String[] five = x1[8].split("£");
            for(int i2=0; i2<7; i2++){
                Labels(timetable,daysofweek[i2], i2, 3);
                if(!nine[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(nine[i2]), i2, 4);}
                else{Labels(timetable,"", i2, 4);}
                
                if(!ten[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(ten[i2]), i2, 5);}
                else{Labels(timetable,"", i2, 5);}
                
                if(!eleven[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(eleven[i2]), i2, 6);}
                else{Labels(timetable,"", i2, 6);}
                
                if(!twelve[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(twelve[i2]), i2, 7);}
                else{Labels(timetable,"", i2, 7);}
                
                if(!one[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(one[i2]), i2, 8);}
                else{Labels(timetable,"", i2, 8);}
                
                if(!two[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(two[i2]), i2, 9);}
                else{Labels(timetable,"", i2, 9);}
                
                if(!three[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(three[i2]), i2, 10);}
                else{Labels(timetable,"", i2, 10);}
                
                if(!four[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(four[i2]), i2, 11);}
                else{Labels(timetable,"", i2, 11);}
                
                if(!five[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(five[i2]), i2, 12);}
                else{Labels(timetable,"", i2, 12);}
            }//timetable.setGridLinesVisible(true);
        } catch (FileNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        
       

        timetable.setMinWidth(450.0);
        timetable.setMinHeight(200.0);
        timetable.setPrefWidth(100.0);
        timetable.setVgap(5); 
        timetable.setHgap(5);
       // timetable.setStyle("-fx-background-color: palegreen;");
      
        
        
        schedulepane.add(classyear,0,0);
        schedulepane.add(display,1,0);
        schedulepane.add(timetable,0,1,2,1);
        
        schedulepane.add(stopsced,2,2);
        stage.show();
        
         new Thread(this::startServer).start();
    }
    public void Labels(GridPane timetable,String labelname, int columnIndex, int rowIndex) {
            Label label = new Label();
            String daysofweek="MondayTuesdayWednesdayThursdayFridaySaturdaySunday"; 
            //gridPane.setColumnIndex(label, columnIndex);
            //gridPane.setRowIndex(label, rowIndex);
            timetable.add(label, columnIndex, rowIndex);
            label.setId(labelname+columnIndex);
            label.setVisible(true);
            
            //label.setId(null+columnIndex);
            //System.out.print(labelname+columnIndex);
            if(//labelname.equalsIgnoreCase("Monday")
                  daysofweek.contains(labelname)   ){
            label.setFont(Font.font(null, FontWeight.BOLD,14));
            label.setText(labelname);
            }else{
            //label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            //label.setBorder(new Border(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            label.setText(labelname);
            label.setAlignment(Pos.CENTER);
            }
            //label.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

    }
/** * @param s schedule string 
 * arranges the string to be put into the schedule
 * @return String after it was arranged
 * 
 */
    public String format(String s) {
        String[] arrs=s.split(",");
        String temp="";
        if(arrs.length==4){
        temp=arrs[2]+"\n"+arrs[0]+"\n"+arrs[3]+"\n";
        }
        s=temp;
        return s;
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




