package gameLogic;

public class Player {


    private Grid grid;

    public Player(Grid grid) {
        this.grid = grid;
    }


    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }
}
