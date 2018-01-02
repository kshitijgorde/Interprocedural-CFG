import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

class Solution
{
    private Polyomino[] polyominoes;
    private Coord[][] placements;
    
    public Solution(final Puzzle puzzle) {
        final int numPolyomino = puzzle.getNumPolyomino();
        this.polyominoes = new Polyomino[numPolyomino];
        this.placements = new Coord[numPolyomino][];
        for (int i = 0; i < numPolyomino; ++i) {
            this.polyominoes[i] = puzzle.getPolyomino(i);
            this.placements[i] = this.polyominoes[i].getPlacements();
        }
    }
    
    public int getNumPoly() {
        return this.polyominoes.length;
    }
    
    public Polyomino getPoly(final int n) {
        return this.polyominoes[n];
    }
    
    public Coord[] getPlacement(final int n) {
        return this.placements[n];
    }
    
    public String textRepr(final Puzzle puzzle) {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        sb.append("place (");
        for (int i = 0; i < this.polyominoes.length; ++i) {
            final Polyomino polyomino = puzzle.getPolyomino(i);
            for (int j = 0; j < this.placements[i].length; ++j) {
                if (this.placements[i][j] != null) {
                    if (n > 0) {
                        sb.append(", ");
                    }
                    sb.append(polyomino.getId() + j).append(" ");
                    sb.append(this.placements[i][j]);
                    ++n;
                }
            }
        }
        sb.append(")\n");
        return (n == 0) ? "" : sb.toString();
    }
    
    public static Solution parse(final Parser parser, final Puzzle puzzle) throws IOException {
        if (puzzle.prepareSolve()) {
            throw new IOException("Cannot place piece in incompletely specified puzzle.");
        }
        puzzle.clearBoard();
        parser.skipOpenBracket();
        while (true) {
            final int number = parser.readNumber();
            if (puzzle.addPlacement(number, Coord.parse(parser))) {
                throw new IOException("Cannot place piece " + number + ".");
            }
            if (parser.readString().equals(")")) {
                return puzzle.getSolution(0);
            }
            parser.pushback();
        }
    }
}
