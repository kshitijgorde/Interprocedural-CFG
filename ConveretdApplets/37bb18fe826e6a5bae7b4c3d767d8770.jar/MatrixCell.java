// 
// Decompiled by Procyon v0.5.30
// 

class MatrixCell
{
    public MatrixHeader header;
    public MatrixRowHeader rowHeader;
    public MatrixCell left;
    public MatrixCell right;
    public MatrixCell up;
    public MatrixCell down;
    
    public MatrixCell(final MatrixHeader header, final MatrixRowHeader rowHeader) {
        this.header = header;
        this.rowHeader = rowHeader;
        if (rowHeader != null) {
            final MatrixCell first = rowHeader.first;
            this.right = first;
            this.left = first.left;
            first.left.right = this;
            first.left = this;
        }
        else {
            this.right = this;
            this.left = this;
        }
        if (header != null) {
            final MatrixCell first2 = header.first;
            this.down = first2;
            this.up = first2.up;
            first2.up.down = this;
            first2.up = this;
            ++header.numberCells;
        }
        else {
            this.down = this;
            this.up = this;
        }
    }
    
    public void unlinkRow() {
        for (MatrixCell matrixCell = this.rowHeader.first.right; matrixCell != this.rowHeader.first; matrixCell = matrixCell.right) {
            final MatrixHeader header = matrixCell.header;
            --header.numberCells;
            matrixCell.up.down = matrixCell.down;
            matrixCell.down.up = matrixCell.up;
        }
    }
    
    public void linkRow() {
        for (MatrixCell matrixCell = this.rowHeader.first.left; matrixCell != this.rowHeader.first; matrixCell = matrixCell.left) {
            matrixCell.up.down = matrixCell;
            matrixCell.down.up = matrixCell;
            final MatrixHeader header = matrixCell.header;
            ++header.numberCells;
        }
    }
    
    public void unlinkColumn() {
        for (MatrixCell matrixCell = this.down; matrixCell != this; matrixCell = matrixCell.down) {
            matrixCell.left.right = matrixCell.right;
            matrixCell.right.left = matrixCell.left;
        }
    }
    
    public void linkColumn() {
        for (MatrixCell matrixCell = this.up; matrixCell != this; matrixCell = matrixCell.up) {
            matrixCell.left.right = matrixCell;
            matrixCell.right.left = matrixCell;
        }
    }
    
    public boolean isAllowed() {
        return !this.header.isSatisfied();
    }
}
