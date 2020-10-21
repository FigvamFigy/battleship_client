import gameLogic.Grid;
import graphicLogic.MainLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import util.EnumCellType;
import util.EnumDataPurposeTag;
import util.EnumScene;
import util.parser.DataReadParser;
import util.parser.DataWriteParser;

import java.net.InetSocketAddress;

public class Main extends Application {


    public static void main(String[] args){

        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        MainLogic mainLogic = new MainLogic(stage);
        MainLogic.showScene(EnumScene.sceneLogin);


    }

    /**
     * This method will kill any threads when closing the application
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
