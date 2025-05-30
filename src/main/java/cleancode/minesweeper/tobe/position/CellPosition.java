package cleancode.minesweeper.tobe.position;

public class CellPosition {

    private final int rowIndex;
    private final int colIndex;

    private CellPosition(int rowIndex, int colIndex) {
        if(rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public static CellPosition of(int rowIndex, int colIndex) {
        return new CellPosition(rowIndex, colIndex);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public boolean isRowIndexMoreThanOrEqual(int rowSize) {
        return rowIndex >= rowSize;
    }

    public boolean isColIndexMoreThanOrEqual(int colSize) {
        return colIndex >= colSize;
    }


    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return rowIndex + relativePosition.getDeltaRow() >= 0 
        && colIndex + relativePosition.getDeltaCol() >= 0;

       
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        if(canCalculatePositionBy(relativePosition)) {
            return CellPosition.of(
                rowIndex + relativePosition.getDeltaRow(), 
                colIndex + relativePosition.getDeltaCol()
            );
        }

        throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
    }

    public boolean isRowIndexLessThan(int rowSize) {
        return rowIndex < rowSize;
    }

    public boolean isColIndexLessThan(int colSize) {
        return colIndex < colSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rowIndex;
        result = prime * result + colIndex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellPosition other = (CellPosition) obj;
        if (rowIndex != other.rowIndex)
            return false;
        if (colIndex != other.colIndex)
            return false;
        return true;
    }

    
}
