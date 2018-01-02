// 
// Decompiled by Procyon v0.5.30
// 

class MatrixHeader
{
    public MatrixHeader left;
    public MatrixHeader right;
    public MatrixCell first;
    public int numberCells;
    private Polyomino poly;
    private Coord coord;
    private int value;
    
    public MatrixHeader() {
        this.init(null, 0, null);
    }
    
    public MatrixHeader(final int n) {
        this.init(null, n, null);
    }
    
    public MatrixHeader(final Polyomino polyomino) {
        this.init(polyomino, polyomino.getAvailable(), null);
    }
    
    public MatrixHeader(final Coord coord) {
        this.init(null, 1, coord);
    }
    
    private void init(final Polyomino poly, final int value, final Coord coord) {
        this.poly = poly;
        this.coord = ((coord == null) ? null : new Coord(coord));
        this.value = value;
        this.right = this;
        this.left = this;
        this.first = new MatrixCell(null, null);
    }
    
    public boolean isPoly() {
        return this.coord == null;
    }
    
    public int getNumberCells() {
        return this.numberCells;
    }
    
    public boolean isSatisfied() {
        return this.value == 0;
    }
    
    public void incValue() {
        ++this.value;
    }
    
    public void decValue() {
        --this.value;
    }
    
    public void unlinkColumn() {
        this.left.right = this.right;
        this.right.left = this.left;
        this.first.unlinkColumn();
    }
    
    public void linkColumn() {
        this.left.right = this;
        this.right.left = this;
        this.first.linkColumn();
    }
    
    public void insert(final MatrixHeader matrixHeader) {
        matrixHeader.left = this.left;
        matrixHeader.right = this;
        this.left.right = matrixHeader;
        this.left = matrixHeader;
    }
    
    public MatrixHeader find(final Polyomino polyomino) {
        if (this.poly == polyomino) {
            return this;
        }
        MatrixHeader matrixHeader;
        for (matrixHeader = this.right; matrixHeader != this && matrixHeader.poly != polyomino; matrixHeader = matrixHeader.right) {}
        return (matrixHeader == this) ? null : matrixHeader;
    }
    
    public MatrixHeader find(final Coord coord) {
        if (coord.equals(this.coord)) {
            return this;
        }
        MatrixHeader matrixHeader;
        for (matrixHeader = this.right; matrixHeader != this && !coord.equals(matrixHeader.coord); matrixHeader = matrixHeader.right) {}
        return (matrixHeader == this) ? null : matrixHeader;
    }
}
