package graphics;

import gameLogic.Grid;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import util.EnumCellType;

public class SceneGridTest extends Scene {

    private GridPane masterGridPane;

    public SceneGridTest(GridPane gridPane, double xSize, double ySize) {
        super(gridPane, xSize,ySize);
        this.masterGridPane = gridPane;

        Grid grid = new Grid(EnumCellType.FRIENDLY,7,7);
        RenderGrid renderGrid = new RenderGrid(grid);
        GridPane gridPaneRendered = renderGrid.render();

        GridPane.setConstraints(gridPaneRendered,0,0);
        masterGridPane.getChildren().add(gridPaneRendered);

    }
}
