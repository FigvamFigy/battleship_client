package graphics;

import graphicLogic.MainLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import thread.ClientThread;
import util.EnumScene;

import java.net.InetAddress;

/**
 * A scene for the user to join a server
 *
 * Thread: JavaFX Application
 */
public class SceneLogin extends Scene {

    private GridPane masterGridPanel;

    private TextField textFieldIP;
    private TextField textFieldPort;
    private Button testButton;

    private Text textConnectionFailed;
    private Text textConnectionLost;
    private Text textInfo;


    public SceneLogin(GridPane gridPane, double xSize, double ySize){
        super(gridPane, xSize,ySize);

        masterGridPanel = gridPane;


        textFieldIP = new TextField();
        textFieldIP.setText("10.0.0.15");
        textFieldPort = new TextField();
        textFieldPort.setText("5555");
        testButton = new Button();
        testButton.setText("Testing Grid");

        createUI();

    }


    private void createUI(){
        Text textIP = new Text("IP: ");
        Text textPort = new Text("Port: ");
        Text textName = new Text("Name: ");

        textConnectionFailed = new Text("Connection failed");
        textConnectionFailed.setVisible(false);

        textConnectionLost = new Text("Connection Lost");
        textConnectionLost.setVisible(false);

        textInfo = new Text("Info");
        textInfo.setVisible(false);


        Button buttonConnect = new Button("Connect");

        //IP
        GridPane.setConstraints(textIP, 0,0);
        GridPane.setConstraints(textFieldIP, 1,0);
        masterGridPanel.getRowConstraints().add(new RowConstraints(25));

        //PORT
        GridPane.setConstraints(textPort,0,1);
        GridPane.setConstraints(textFieldPort,1,1);
        masterGridPanel.getRowConstraints().add(new RowConstraints(25));

        //NAME
        GridPane.setConstraints(textName,0,2);
        GridPane.setConstraints(testButton,1,2);
        masterGridPanel.getRowConstraints().add(new RowConstraints(25));

        //this is used for informational things + stating that the selected name is not valid
        GridPane.setConstraints(textInfo,1,3);
        masterGridPanel.getRowConstraints().add(new RowConstraints(25));

        //Connection failed + connect
        GridPane.setConstraints(buttonConnect,0,4);
        GridPane.setConstraints(textConnectionFailed,1,4);
        masterGridPanel.getRowConstraints().add(new RowConstraints(100));

        //Connection Lost
        GridPane.setConstraints(textConnectionLost,0,5);
        masterGridPanel.getRowConstraints().addAll(new RowConstraints(25));


        //Action listneners
        buttonConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    ClientThread clientThread = new ClientThread(textFieldIP.getText(),Integer.parseInt(textFieldPort.getText()));
                    clientThread.start();

                }
                catch (Exception exception){
                    exception.printStackTrace();
                }

            }
        });


        testButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainLogic.showScene(EnumScene.SCENE_RENDER_GRID_TEST);
            }
        });




        masterGridPanel.getChildren().addAll(textIP, textFieldIP, textPort, textFieldPort, textName, testButton, textConnectionFailed, textInfo, buttonConnect,
                textConnectionLost);

    }




}





