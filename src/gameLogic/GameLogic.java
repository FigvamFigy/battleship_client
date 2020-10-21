package gameLogic;


/**
 * This will be responsible for handling the game logic.
 *
 * Thread: JavaFX Application
 */
public class GameLogic {


    private Player enemyPlayer;
    private Player clientPlayer;

    public GameLogic(Player enemyPlayer, Player clientPlayer) {
        this.enemyPlayer = enemyPlayer;
        this.clientPlayer = clientPlayer;
    }


    public GameLogic(){
        this(null,null);
    }

    /**
     * This method is called in VisualCell.java. It sends its cell to be processed
     *
     * @param cellClicked
     */
    public static void cellClicked(Cell cellClicked){
        System.out.println("GameLogic Cell Clicked: " + cellClicked.getRowNumber() + " " + cellClicked.getColNumber());
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
    }

    public void setClientPlayer(Player clientPlayer) {
        this.clientPlayer = clientPlayer;
    }


    public Player getEnemyPlayer() {
        return enemyPlayer;
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }
}
