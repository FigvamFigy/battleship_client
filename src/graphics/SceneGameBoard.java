package graphics;

import gameLogic.GameLogic;
import gameLogic.Grid;
import graphicLogic.MainLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import network.ConnectionInfo;
import network.OutgoingDataQueue;
import util.EnumCellType;
import util.EnumDataPurposeTag;
import util.parser.DataWriteParser;


public class SceneGameBoard extends Scene {


    private GridPane masterGridPane;

    public SceneGameBoard(GridPane gridPane, double xSize, double ySize) {
        super(gridPane, xSize,ySize);
        this.masterGridPane = gridPane;

        //Do the same thing but with enemy grid
        Button buttonSetupFinished = new Button("Setup Finished");
        buttonSetupFinished.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Send That the player is ready
                DataWriteParser dataWriteParser = new DataWriteParser(
                        ConnectionInfo.getClientSocketAddress(),
                        ConnectionInfo.getClientSocketAddress(),
                        EnumDataPurposeTag.PLAYER_IS_READY,
                        "READY"
                        );

                OutgoingDataQueue.addToQueue(dataWriteParser);

            }
        });

        GridPane.setConstraints(buttonSetupFinished,0,1);

        masterGridPane.getChildren().add(buttonSetupFinished);



    }

}
