// 
// Decompiled by Procyon v0.5.30
// 

class MatrixRowHeader
{
    public Polyomino poly;
    public Coord coord;
    public MatrixCell first;
    public MatrixRowHeader up;
    public MatrixRowHeader down;
    
    public MatrixRowHeader(final Polyomino poly, final Coord coord) {
        this.poly = poly;
        if (coord != null) {
            this.coord = new Coord(coord);
        }
        this.first = new MatrixCell(null, null);
        this.down = this;
        this.up = this;
    }
    
    public void place(final Board board) {
        if (this.poly != null) {
            this.poly.placeS(this.coord);
        }
        else {
            board.setContents(this.coord, -2);
        }
    }
    
    public void remove(final Board board) {
        if (this.poly != null) {
            this.poly.removeS();
        }
        else {
            board.setContents(this.coord, 0);
        }
    }
    
    public void insert(final MatrixRowHeader matrixRowHeader) {
        matrixRowHeader.up = this.up;
        matrixRowHeader.down = this;
        this.up.down = matrixRowHeader;
        this.up = matrixRowHeader;
    }
}
