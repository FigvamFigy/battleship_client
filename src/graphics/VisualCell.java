package graphics;

import gameLogic.Cell;
import gameLogic.GameLogic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import util.EnumCellType;

import java.io.FileInputStream;

public class VisualCell extends StackPane {


    private Cell referenceCell;

    public VisualCell(Cell referenceCell) {
        this.referenceCell = referenceCell;

        try{
            Image rawImage = getImageFromCellState();

            ImageView imageView = new ImageView(rawImage);

            getChildren().add(imageView);

            setOnMouseReleased(e -> clicked());
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private Image getImageFromCellState()throws Exception{

        if(referenceCell.getType() == EnumCellType.FRIENDLY){
            switch (referenceCell.getState()){
                case EMPTY: return new Image(new FileInputStream("C:\\Users\\vlad\\Desktop\\My Programming\\Battleship LAN Game\\BattleshipWithNetworking\\client\\src\\assets\\game_square_ship.png"));
            }

        }

        return null;
    }

    private void clicked(){
        GameLogic.cellClicked(referenceCell);
    }
}
