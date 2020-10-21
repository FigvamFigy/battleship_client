package graphics;

import gameLogic.GameLogic;
import gameLogic.Grid;
import graphicLogic.MainLogic;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import util.EnumCellType;

public class SceneGameBoard extends Scene {


    private GridPane masterGridPane;

    public SceneGameBoard(GridPane gridPane, double xSize, double ySize) {
        super(gridPane, xSize,ySize);
        this.masterGridPane = gridPane;


    }

    public void updateBoard(){

        GameLogic gameLogic = MainLogic.getGameLogic();

        Grid grid = gameLogic.getClientPlayer().getGrid();
        RenderGrid renderGrid = new RenderGrid(grid);
        GridPane gridPaneRendered = renderGrid.render();

        GridPane.setConstraints(gridPaneRendered,0,0);
        masterGridPane.getChildren().add(gridPaneRendered);

        //Do the same thing but with enemy grid

    }

}
