package graphics;

import gameLogic.Grid;
import javafx.scene.layout.GridPane;


/**
 * This purpose of this class is to return a GridPane with a collection of visual cells for the scene to add it to its own GridPane
 *
 * Thread: JavaFX Application
 */
public class RenderGrid {


    private Grid grid;
    private GridPane gridPane;

    public RenderGrid(Grid grid) {
        this.grid = grid;
        this.gridPane = new GridPane();
    }


    public GridPane render(){

        for (int row = 0; row < grid.getRowQuantity(); row++) {
            for (int col = 0; col < grid.getColQuantity(); col++) {

                VisualCell visualCell = new VisualCell(grid.getCell(row,col));

                GridPane.setConstraints(visualCell,row,col);
                gridPane.getChildren().add(visualCell);

            }
        }

        return gridPane;
    }

    public void updateGrid(Grid newGrid){
        this.grid = newGrid;
    }
}
