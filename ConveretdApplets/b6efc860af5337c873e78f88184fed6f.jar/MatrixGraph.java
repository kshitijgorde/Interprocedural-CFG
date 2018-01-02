import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class MatrixGraph extends DrawableGraph
{
    private static final int BOX_SIZE = 18;
    private static final int TOP_LABEL_OFFSET = 6;
    private static final int SIDE_LABEL_OFFSET = 12;
    private static final int X_OFFSET = 270;
    private static final int Y_OFFSET = 20;
    private static final int RADIUS = 5;
    private int xVal;
    private int yVal;
    private boolean makeMatrix;
    private boolean[][] matrixShowing;
    private boolean[][] graphShowing;
    private DrawableCircle tracer1;
    private DrawableCircle tracer2;
    private DrawableCircle correct1;
    private DrawableCircle correct2;
    
    public MatrixGraph(final Position position, final int rows, final int cols) {
        super(position, rows, cols, true, 65);
        this.xVal = super.x + 270;
        this.yVal = super.y - 20;
        this.makeMatrix = true;
        this.tracer1 = new DrawableCircle(new Position(0, 0), 10, false, false);
        this.tracer2 = new DrawableCircle(new Position(0, 0), 10, false, false);
        this.correct1 = new DrawableCircle(new Position(0, 0), 10, false, true);
        this.correct2 = new DrawableCircle(new Position(0, 0), 10, false, true);
        this.matrixShowing = new boolean[super.noOfNodes][super.noOfNodes];
        this.graphShowing = new boolean[super.noOfNodes][super.noOfNodes];
        Screen.canvas.add(this.tracer1);
        Screen.canvas.add(this.tracer2);
        Screen.canvas.add(this.correct1);
        Screen.canvas.add(this.correct2);
    }
    
    private void action(final String prompt, final Position start1, final Position finish1, final Position start2, final Position finish2) {
        this.click(this.tracer1, this.correct1, start1, finish1);
        this.click(this.tracer2, this.correct2, start2, finish2);
        if (Screen.panel.isInteractive()) {
            Screen.canvas.awaitClick(prompt, true, false);
        }
        Screen.controls.nextStep(true, true);
        this.move(this.tracer1, finish1);
        this.move(this.tracer2, finish2);
        Screen.canvas.awaitMovingCompletion();
        this.tracer1.hide();
        this.tracer2.hide();
        this.correct1.hide();
        this.correct2.hide();
    }
    
    private void click(final DrawableCircle tracer, final DrawableCircle correct, final Position start, final Position finish) {
        tracer.reposition(start);
        tracer.show();
        tracer.setColor(Color.white);
        if (Screen.panel.isInteractive()) {
            correct.reposition(finish);
            Screen.canvas.acceptClick(correct);
        }
    }
    
    public void create() {
        this.reset(this.makeMatrix);
        for (int from = 0; from < super.noOfNodes; ++from) {
            for (int to = 0; to < from; ++to) {
                if (super.edges[from][to]) {
                    if (this.makeMatrix) {
                        this.action("Click Edge Nodes", this.matrixPosition(from, to), this.nodePosition(from), this.matrixPosition(to, from), this.nodePosition(to));
                        this.graphShowing[from][to] = (this.graphShowing[to][from] = true);
                    }
                    else {
                        this.action("Click Matrix Cells", this.nodePosition(from), this.matrixPosition(from, to), this.nodePosition(to), this.matrixPosition(to, from));
                        this.matrixShowing[from][to] = (this.matrixShowing[to][from] = true);
                    }
                }
            }
        }
    }
    
    public void draw(final Graphics g) {
        super.draw(g);
        final int xLeft = this.xVal;
        final int xRight = this.xVal + super.noOfNodes * 18;
        final int yTop = this.yVal;
        final int yBottom = this.yVal + super.noOfNodes * 18;
        g.setColor(DrawableGraph.GRAPH_COLOR);
        for (int row = 0; row <= super.noOfNodes; ++row) {
            g.drawLine(xLeft, this.yRow(row), xRight, this.yRow(row));
        }
        for (int col = 0; col <= super.noOfNodes; ++col) {
            g.drawLine(this.xCol(col), yTop, this.xCol(col), yBottom);
        }
        g.setColor(DrawableGraph.TEXT_COLOR);
        char label = 'A';
        for (int node = 0; node < super.noOfNodes; ++node) {
            g.drawString(String.valueOf(label), xLeft - 12, this.yVal + node * 18 + 12);
            g.drawString(String.valueOf(label), this.xVal + node * 18 + 6, yTop - 6);
            ++label;
        }
        for (int from = 0; from < super.noOfNodes; ++from) {
            for (int to = 0; to < super.noOfNodes; ++to) {
                if (super.edges[from][to] && this.matrixShowing[from][to]) {
                    g.drawString("x", this.xVal + to * 18 + 6, this.yVal + from * 18 + 12);
                }
            }
        }
    }
    
    public boolean isSelectable(final int node) {
        return true;
    }
    
    public void make() {
        char upperLetter = 'A';
        for (int node = 0; node < super.noOfNodes; ++node) {
            super.nodes[node] = true;
            final String[] names = super.names;
            final int n = node;
            final char c = upperLetter;
            upperLetter = (char)(c + '\u0001');
            names[n] = String.valueOf(c);
        }
        for (int from = 0; from < super.noOfNodes; ++from) {
            for (int to = 0; to < super.noOfNodes; ++to) {
                super.edges[from][to] = false;
                this.matrixShowing[from][to] = false;
                this.graphShowing[from][to] = false;
            }
        }
        for (int noOfEdges = this.randomNo(super.noOfNodes), edge = 0; edge < noOfEdges; ++edge) {
            final int from2 = this.randomNo(super.noOfNodes);
            final int to2 = this.randomNo(super.noOfNodes);
            super.edges[from2][to2] = (super.edges[to2][from2] = true);
            if (this.makeMatrix) {
                this.matrixShowing[from2][to2] = (this.matrixShowing[to2][from2] = true);
            }
            else {
                this.graphShowing[from2][to2] = (this.graphShowing[to2][from2] = true);
            }
        }
    }
    
    private Position matrixPosition(final int from, final int to) {
        return new Position(this.xCol(from) + 9, this.yRow(to) + 9);
    }
    
    private void move(final DrawableCircle tracer, final Position finish) {
        final Position[] positions = { finish };
        tracer.moveTo(positions);
    }
    
    private Position nodePosition(final int node) {
        return new Position(this.xCoor(node), this.yCoor(node));
    }
    
    public void reset(final boolean makeMatrix) {
        this.makeMatrix = makeMatrix;
        for (int from = 0; from < super.noOfNodes; ++from) {
            for (int to = 0; to < from; ++to) {
                if (super.edges[from][to]) {
                    if (makeMatrix) {
                        this.matrixShowing[from][to] = (this.matrixShowing[to][from] = true);
                        this.graphShowing[from][to] = (this.graphShowing[to][from] = false);
                    }
                    else {
                        this.matrixShowing[from][to] = (this.matrixShowing[to][from] = false);
                        this.graphShowing[from][to] = (this.graphShowing[to][from] = true);
                    }
                }
            }
        }
    }
    
    public boolean showing(final int from, final int to) {
        return this.graphShowing[from][to];
    }
    
    private int xCol(final int col) {
        return this.xVal + col * 18;
    }
    
    public int xCoor(final int node) {
        final int col = node % super.cols;
        final int row = node / super.cols;
        return col * super.spacing + row % 2 * (super.spacing / 2) + super.x;
    }
    
    public int yCoor(final int node) {
        final int col = node % super.cols;
        final int row = node / super.cols;
        return row * super.spacing + col % 2 * (super.spacing / 3) + super.y;
    }
    
    private int yRow(final int row) {
        return this.yVal + row * 18;
    }
}
