package gameLogic;

import util.EnumCellState;
import util.EnumCellType;

public class Cell {

    private EnumCellState state;
    private EnumCellType type;

    private int cellID;
    private int rowNumber;
    private int colNumber;

    public Cell(EnumCellType type, int cellID,int rowNumber, int colNumber) {
        this.cellID = cellID;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.type = type;
        this.state = EnumCellState.EMPTY;
    }

    public void setState(EnumCellState state) {
        this.state = state;
    }

    public EnumCellState getState() {
        return state;
    }

    public EnumCellType getType() {
        return type;
    }

    public int getCellID() {
        return cellID;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public String toString(){
        String str = "";
        str += "\nCell Coord: " + rowNumber + " " + colNumber;
        str += "\nCell ID: " + cellID;
        str += "\nCell State: " + state;
        str += "\nCell Type: " + type;

        return str;
    }
}