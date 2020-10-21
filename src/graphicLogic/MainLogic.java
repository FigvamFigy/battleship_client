package graphicLogic;

import gameLogic.GameLogic;
import gameLogic.Grid;
import gameLogic.Player;
import graphics.MainWindow;
import javafx.stage.Stage;
import util.Constants;
import util.EnumCellType;
import util.EnumScene;

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

    }

    public static void createClientPlayer(){
        Player player = new Player(new Grid(EnumCellType.FRIENDLY, Constants.CELL_ROW_COUNT,Constants.CELL_COL_COUNT));

        gameLogic.setClientPlayer(player);

    }

    public static GameLogic getGameLogic() {
        return gameLogic;
    }

    public static void updateBoard(){
        mainWindow.updateBoard();
    }
}
