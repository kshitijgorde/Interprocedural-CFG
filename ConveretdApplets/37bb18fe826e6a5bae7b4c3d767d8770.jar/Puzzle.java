import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

class Puzzle implements Runnable
{
    private ArrayList polyominoList;
    private Board board;
    private ArrayList solutions;
    private ActionListener listener;
    private GridType gridType;
    private Solution startPosition;
    private int maxSpacers;
    private boolean solutionExact;
    private boolean changed;
    Object[] rowcols;
    boolean[] type;
    boolean running;
    boolean wantToStop;
    
    Puzzle() {
        this.polyominoList = new ArrayList();
        this.board = new Board();
        this.solutions = new ArrayList();
        this.gridType = new GridTypeSquare();
        this.startPosition = null;
        this.changed = true;
        this.running = false;
        this.wantToStop = false;
    }
    
    public boolean isChanged() {
        if (this.changed) {
            return true;
        }
        if (this.board.isChanged()) {
            return true;
        }
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            if (this.getPolyomino(i).isChanged()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validate() {
        if (this.isChanged()) {
            this.solutions.clear();
            this.startPosition = null;
            if (this.board.validate()) {
                return true;
            }
            if (this.polyominoList.size() == 0) {
                return true;
            }
            int id = 1;
            for (int i = 0; i < this.polyominoList.size(); ++i) {
                final Polyomino polyomino = this.getPolyomino(i);
                polyomino.setId(id);
                if (polyomino.validate(this.gridType)) {
                    return true;
                }
                id += polyomino.getNumber();
            }
            this.changed = false;
        }
        return false;
    }
    
    public void setGridType(final GridType gridType) {
        this.gridType = gridType;
    }
    
    public GridType getGridType() {
        return this.gridType;
    }
    
    public int getNumPolyomino() {
        return this.polyominoList.size();
    }
    
    public int getNumPieces() {
        int n = 0;
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            n += this.getPolyomino(i).getNumber();
        }
        return n;
    }
    
    public Polyomino getPolyomino(final int n) {
        return this.polyominoList.get(n);
    }
    
    public int getNumSolutions() {
        return this.solutions.size();
    }
    
    public void setStartPosition(final Solution startPosition) {
        this.startPosition = startPosition;
    }
    
    public Solution getSolution(final int n) {
        return (n == 0) ? new Solution(this) : this.solutions.get(n - 1);
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public void setActionListener(final ActionListener listener) {
        this.listener = listener;
    }
    
    public void addPolyomino(final Polyomino polyomino) {
        this.polyominoList.add(polyomino);
        this.changed = true;
    }
    
    public void removePolyomino(final Polyomino polyomino) {
        this.polyominoList.remove(polyomino);
        this.changed = true;
    }
    
    public void setBoard(final Board board) {
        this.board.setBoard(board);
        this.changed = true;
    }
    
    public int getMinNumBlocks() {
        int n = 0;
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            n += this.getPolyomino(i).getMinNumBlocks();
        }
        return n;
    }
    
    public int getMaxNumBlocks() {
        int n = 0;
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            n += this.getPolyomino(i).getMaxNumBlocks();
        }
        return n;
    }
    
    public void clearBoard() {
        this.board.clear();
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            this.getPolyomino(i).reset();
        }
    }
    
    public boolean addPlacement(final int n, final Coord coord) {
        Polyomino polyomino = null;
        int i;
        for (i = 0; i < this.polyominoList.size(); ++i) {
            polyomino = this.getPolyomino(i);
            if (n >= polyomino.getId() && n < polyomino.getId() + polyomino.getNumber()) {
                break;
            }
        }
        if (i >= this.polyominoList.size()) {
            return true;
        }
        if (!polyomino.canPlace(this.board, coord)) {
            return true;
        }
        polyomino.placeS(coord);
        polyomino.place(this.board, coord);
        return false;
    }
    
    public void removeDuplicates(final boolean b) {
        for (int i = 0; i < this.getNumPolyomino(); ++i) {
            this.getPolyomino(i).validate(this.gridType);
        }
        for (int j = 0; j < this.getNumPolyomino(); ++j) {
            final Polyomino polyomino = this.getPolyomino(j);
            for (int k = j + 1; k < this.getNumPolyomino(); ++k) {
                final Polyomino polyomino2 = this.getPolyomino(k);
                if (polyomino.sameShape(polyomino2)) {
                    int number = polyomino.getNumber();
                    if (b) {
                        number += polyomino2.getNumber();
                    }
                    else if (number < polyomino2.getNumber()) {
                        number = polyomino2.getNumber();
                    }
                    polyomino.setNumber(number);
                    this.polyominoList.remove(k);
                }
            }
        }
    }
    
    public static Puzzle parse(final Parser parser) throws IOException {
        final Puzzle puzzle = new Puzzle();
        final String string = parser.readString(true);
        if (string == null) {
            return puzzle;
        }
        GridType gridType = GridType.factory(string);
        if (gridType == null) {
            if (!string.equalsIgnoreCase("board") && !string.equalsIgnoreCase("tile")) {
                throw new IOException("'" + string + "' is not a recognised grid type.");
            }
            parser.pushback();
            gridType = GridType.factory("square");
        }
        puzzle.setGridType(gridType);
        String string2;
        while (true) {
            string2 = parser.readString(true);
            if (string2 == null) {
                break;
            }
            if (string2.equalsIgnoreCase("board")) {
                puzzle.setBoard(Board.parse(parser));
            }
            else {
                if (!string2.equalsIgnoreCase("tile")) {
                    break;
                }
                puzzle.addPolyomino(Polyomino.parse(parser, gridType));
            }
        }
        if (string2 != null && string2.equalsIgnoreCase("place")) {
            puzzle.setStartPosition(Solution.parse(parser, puzzle));
        }
        else {
            parser.pushback();
        }
        return puzzle;
    }
    
    public String textRepr() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.gridType + "\n");
        sb.append(this.board.textRepr());
        for (int i = 0; i < this.polyominoList.size(); ++i) {
            sb.append(this.getPolyomino(i).textRepr());
        }
        if (this.startPosition != null) {
            sb.append(this.startPosition.textRepr(this));
        }
        return sb.toString();
    }
    
    public boolean prepareSolve() {
        if (this.validate()) {
            return true;
        }
        this.clearBoard();
        if (this.startPosition != null) {
            for (int i = 0; i < this.startPosition.getNumPoly(); ++i) {
                final Polyomino poly = this.startPosition.getPoly(i);
                final Coord[] placement = this.startPosition.getPlacement(i);
                for (int j = 0; j < placement.length; ++j) {
                    if (placement[j] != null) {
                        poly.place(this.board, placement[j]);
                        poly.placeS(placement[j]);
                    }
                }
            }
        }
        int n = 0;
        int n2 = 0;
        for (int k = 0; k < this.polyominoList.size(); ++k) {
            final Polyomino polyomino = this.getPolyomino(k);
            n += polyomino.getMinNumBlocks();
            n2 += polyomino.getMaxNumBlocks();
        }
        final int numEmptyBlocks = this.board.getNumEmptyBlocks();
        this.maxSpacers = numEmptyBlocks - n;
        final int n3 = numEmptyBlocks - n2;
        this.solutionExact = (n3 == this.maxSpacers && n3 >= 0);
        this.solutions.clear();
        return false;
    }
    
    private void solveDLX() {
        int n = 0;
        final MatrixHeader matrixHeader = new MatrixHeader();
        final MatrixRowHeader matrixRowHeader = new MatrixRowHeader(null, null);
        int n2 = 0;
        final Iterator iterator = this.board.getIterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, Integer> entry = iterator.next();
            if (entry.getValue() == 0) {
                matrixHeader.insert(new MatrixHeader(entry.getKey()));
                ++n2;
            }
        }
        final Coord coord = new Coord();
        final Coord coord2 = new Coord();
        this.gridType.getTranslationRange(this.board, coord, coord2);
        for (int i = 0; i < this.getNumPolyomino(); ++i) {
            final Polyomino polyomino = this.getPolyomino(i);
            if (!polyomino.isUsed()) {
                final MatrixHeader matrixHeader2 = new MatrixHeader(polyomino);
                matrixHeader.insert(matrixHeader2);
                ++n2;
                final Coord coord3 = new Coord(coord);
                do {
                    for (int j = 0; j < polyomino.getNumOrient(); ++j) {
                        coord3.ori = j;
                        if (polyomino.canPlace(this.board, coord3)) {
                            final MatrixRowHeader matrixRowHeader2 = new MatrixRowHeader(polyomino, coord3);
                            matrixRowHeader.insert(matrixRowHeader2);
                            ++n2;
                            ++n;
                            new MatrixCell(matrixHeader2, matrixRowHeader2);
                            for (int k = 0; k < polyomino.getNumBlocks(j); ++k) {
                                new MatrixCell(matrixHeader.find(polyomino.getCoord(k, coord3)), matrixRowHeader2);
                            }
                        }
                    }
                } while (this.gridType.getNextTranslation(coord3, coord, coord2));
            }
        }
        if (this.maxSpacers > 0) {
            final MatrixHeader matrixHeader3 = new MatrixHeader(this.maxSpacers);
            matrixHeader.insert(matrixHeader3);
            ++n2;
            final Iterator iterator2 = this.board.getIterator();
            while (iterator2.hasNext()) {
                final Coord coord4 = iterator2.next().getKey();
                if (this.board.getContents(coord4) == 0) {
                    final MatrixRowHeader matrixRowHeader3 = new MatrixRowHeader(null, coord4);
                    matrixRowHeader.insert(matrixRowHeader3);
                    ++n2;
                    ++n;
                    new MatrixCell(matrixHeader3, matrixRowHeader3);
                    new MatrixCell(matrixHeader.find(coord4), matrixRowHeader3);
                }
            }
        }
        this.rowcols = new Object[n2];
        this.type = new boolean[n2];
        System.out.println("num rows=" + n);
        this.solveDLX(matrixHeader, 0, 0);
        for (int l = 0; l < n2; ++l) {
            this.rowcols[l] = null;
        }
        this.rowcols = null;
        this.type = null;
        MatrixHeader matrixHeader4 = matrixHeader;
        do {
            final MatrixCell first;
            MatrixCell matrixCell = first = matrixHeader4.first;
            if (first != null) {
                do {
                    final MatrixCell down = matrixCell.down;
                    final MatrixCell matrixCell2 = matrixCell;
                    final MatrixCell matrixCell3 = matrixCell;
                    final MatrixCell matrixCell4 = matrixCell;
                    final MatrixCell matrixCell5 = matrixCell;
                    final MatrixCell matrixCell6 = null;
                    matrixCell5.down = matrixCell6;
                    matrixCell4.up = matrixCell6;
                    matrixCell3.right = matrixCell6;
                    matrixCell2.left = matrixCell6;
                    matrixCell.header = null;
                    matrixCell = down;
                } while (matrixCell != first);
            }
            final MatrixHeader right = matrixHeader4.right;
            final MatrixHeader matrixHeader5 = matrixHeader4;
            final MatrixHeader matrixHeader6 = matrixHeader4;
            final MatrixHeader matrixHeader7 = null;
            matrixHeader6.right = matrixHeader7;
            matrixHeader5.left = matrixHeader7;
            matrixHeader4.first = null;
            matrixHeader4 = right;
        } while (matrixHeader4 != matrixHeader);
    }
    
    private boolean solveDLX(final MatrixHeader matrixHeader, final int n, final int n2) {
        if (this.wantToStop) {
            return false;
        }
        MatrixHeader matrixHeader2;
        MatrixHeader right = matrixHeader2 = matrixHeader;
        int n3 = -1;
        while (true) {
            right = right.right;
            if (right == matrixHeader) {
                break;
            }
            final int numberCells = right.getNumberCells();
            if (numberCells > n3 && n3 >= 0) {
                continue;
            }
            if (numberCells == 0 && right.isPoly() && !this.solutionExact) {
                continue;
            }
            n3 = numberCells;
            matrixHeader2 = right;
        }
        final MatrixHeader matrixHeader3 = matrixHeader2;
        if (n3 <= 0) {
            if (n3 < 0) {
                if (n >= 0) {
                    this.foundSolution();
                }
                return true;
            }
            return false;
        }
        else {
            if (n == -1) {
                return true;
            }
            boolean b = false;
            int i = n2;
            for (MatrixCell matrixCell = matrixHeader3.first.down; matrixCell != matrixHeader3.first && !b; matrixCell = matrixCell.down) {
                matrixCell.rowHeader.place(this.board);
                matrixCell.unlinkRow();
                for (MatrixCell matrixCell2 = matrixCell.rowHeader.first.right; matrixCell2 != matrixCell.rowHeader.first; matrixCell2 = matrixCell2.right) {
                    matrixCell2.header.decValue();
                    if (matrixCell2.header.isSatisfied()) {
                        matrixCell2.header.unlinkColumn();
                        this.rowcols[i] = matrixCell2.header;
                        this.type[i] = false;
                        ++i;
                    }
                    for (MatrixCell matrixCell3 = matrixCell2.header.first.down; matrixCell3 != matrixCell2.header.first; matrixCell3 = matrixCell3.down) {
                        if (!matrixCell3.isAllowed()) {
                            matrixCell3.unlinkRow();
                            this.rowcols[i] = matrixCell3;
                            this.type[i] = true;
                            ++i;
                        }
                    }
                }
                if (n >= 0) {
                    this.solveDLX(matrixHeader, n + 1, i);
                }
                else {
                    b |= this.solveDLX(matrixHeader, n + 1, i);
                }
                while (i > n2) {
                    --i;
                    if (this.type[i]) {
                        ((MatrixCell)this.rowcols[i]).linkRow();
                    }
                    else {
                        ((MatrixHeader)this.rowcols[i]).linkColumn();
                    }
                }
                matrixCell.linkRow();
                for (MatrixCell matrixCell4 = matrixCell.rowHeader.first.right; matrixCell4 != matrixCell.rowHeader.first; matrixCell4 = matrixCell4.right) {
                    matrixCell4.header.incValue();
                }
                matrixCell.rowHeader.remove(this.board);
            }
            if (matrixHeader3.isPoly() && !this.solutionExact && !b) {
                for (MatrixCell matrixCell5 = matrixHeader3.first.down; matrixCell5 != matrixHeader3.first; matrixCell5 = matrixCell5.down) {
                    matrixCell5.unlinkRow();
                    this.rowcols[i] = matrixCell5;
                    ++i;
                }
                if (n >= 0) {
                    this.solveDLX(matrixHeader, n + 1, i);
                }
                else {
                    b |= this.solveDLX(matrixHeader, n + 1, i);
                }
                while (i > n2) {
                    --i;
                    ((MatrixCell)this.rowcols[i]).linkRow();
                }
            }
            return b;
        }
    }
    
    private void foundSolution() {
        this.solutions.add(new Solution(this));
        if (this.listener != null) {
            this.listener.actionPerformed(new ActionEvent(this, 0, ""));
        }
    }
    
    public void run() {
        this.solutions.clear();
        this.running = true;
        this.wantToStop = false;
        if (!this.prepareSolve()) {
            this.listener.actionPerformed(new ActionEvent(this, 2, ""));
            this.solveDLX();
        }
        this.running = false;
        this.listener.actionPerformed(new ActionEvent(this, 1, ""));
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void stopSolve() {
        this.wantToStop = true;
        while (this.isRunning()) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        this.running = false;
        this.listener.actionPerformed(new ActionEvent(this, 1, ""));
    }
}
