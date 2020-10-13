package graphics;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import network.ConnectionInfo;
import util.Constants;
import util.EnumScene;

/**
 * This is the class that will be responsible for the actual GUI logic. It will create things needed for JavaFX.
 * This class will not have any buttons/text fields/etc. The purpose this class will hold is to hold scenes that will be shown when needed.
 * It is essentially like a general, it does not actually do the dirty work but instead it commands other people (classes) to do it
 *
 * Thread: JavaFX Application
 */
public class MainWindow {


    private Stage mainStage;

    ///Scenes - Different scenes will be added when different things are needed
    private SceneLogin sceneLogin;

    public MainWindow(Stage mainStage) {
        this.mainStage = mainStage;

        createScenes();
        setupWindow();
    }

    /**
     * Creates a new instances of all the Scenes that will be used
     *
     */
    private void createScenes(){
        GridPane gridPaneLogin = new GridPane();
        gridPaneLogin.setPadding(new Insets(50));


        this.sceneLogin = new SceneLogin(gridPaneLogin, Constants.WINDOW_X,Constants.WINDOW_Y);

    }

    /**
     * This sets up the basics for a JavaFX window
     */
    private void setupWindow(){
        mainStage.setTitle("Battleship - CLIENT");
        mainStage.setHeight(Constants.WINDOW_Y);
        mainStage.setWidth(Constants.WINDOW_X);

        mainStage.show();

    }

    /**
     * This will use a switch case with the EnumScene to quickly change the scenes in the application
     *
     * @param scene The type of scene to be shown
     */
    public void showScene(EnumScene scene){
        switch (scene){
            case sceneLogin:
                mainStage.setScene(sceneLogin);
                break;
        }
    }


}
