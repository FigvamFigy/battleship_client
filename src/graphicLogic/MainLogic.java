package graphicLogic;

import gameLogic.GameLogic;
import gameLogic.Grid;
import gameLogic.Player;
import graphics.MainWindow;
import javafx.application.Platform;
import javafx.stage.Stage;
import network.ConnectionInfo;
import network.OutgoingDataQueue;
import util.Constants;
import util.EnumCellType;
import util.EnumDataPurposeTag;
import util.EnumScene;
import util.parser.DataWriteParser;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Base64;

/**
 * This class is responsible for being the middle man between the backend logic (server/client/data reading) to the front end logic (UI, user interaction, etc)
 * This class has a lot of static methods so that it can be referenced anywhere in the program
 *
 * Thread: JavaFX Application
 */
public class MainLogic {

    private static MainWindow mainWindow;

    private static GameLogic gameLogic;

    public MainLogic(Stage stage) {
        mainWindow = new MainWindow(stage);
        gameLogic = new GameLogic();

    }


    public static void showScene(EnumScene scene){
        mainWindow.showScene(scene);
    }





    public static void startGame(){
        gameLogic.startGame();

    }

    /**
     * This method creates both the client and enemy grid after the client connects to the server
     *
     */
    public static void createPlayers(){
        Player clientPlayer = new Player(new Grid(EnumCellType.FRIENDLY, Constants.CELL_ROW_COUNT,Constants.CELL_COL_COUNT));
        Player enemyPlayer = new Player(new Grid(EnumCellType.ENEMY, Constants.CELL_ROW_COUNT, Constants.CELL_COL_COUNT));

        gameLogic.setClientPlayer(clientPlayer);;
        gameLogic.setEnemyPlayer(enemyPlayer);

    }

    public static GameLogic getGameLogic() {
        return gameLogic;
    }

}
