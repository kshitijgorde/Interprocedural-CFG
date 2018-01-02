import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class DrawableGraph extends Drawable implements Selectable
{
    private static final int RADIUS = 3;
    private static final int SELECTION_RADIUS = 10;
    protected static final Color GRAPH_COLOR;
    protected static final Color TEXT_COLOR;
    protected static final int X_NAME_OFFSET = 5;
    protected static final int Y_NAME_OFFSET = 10;
    protected int spacing;
    protected int rows;
    protected int cols;
    protected int noOfNodes;
    protected boolean named;
    protected boolean directed;
    protected boolean[] nodes;
    protected boolean[][] edges;
    protected String[] names;
    protected Random random;
    
    static {
        GRAPH_COLOR = Screen.DARK_BLUE;
        TEXT_COLOR = Screen.DARK_RED;
    }
    
    public DrawableGraph(final Position position, final int rows, final int cols, final boolean named, final int spacing) {
        this.directed = false;
        this.random = new Random();
        this.reposition(position);
        this.rows = rows;
        this.cols = cols;
        this.named = named;
        this.spacing = spacing;
        this.noOfNodes = rows * cols;
        this.nodes = new boolean[this.noOfNodes];
        this.edges = new boolean[this.noOfNodes][this.noOfNodes];
        this.names = new String[this.noOfNodes];
    }
    
    public void draw(final Graphics g) {
        g.setColor(DrawableGraph.GRAPH_COLOR);
        for (int node = 0; node < this.noOfNodes; ++node) {
            if (this.nodes[node]) {
                g.fillOval(this.xCoor(node) - 3, this.yCoor(node) - 3, 6, 6);
            }
        }
        for (int from = 0; from < this.noOfNodes; ++from) {
            for (int to = 0; to < this.noOfNodes; ++to) {
                if ((this.directed || to < from) && this.edges[from][to] && this.showing(from, to)) {
                    g.drawLine(this.xCoor(from), this.yCoor(from), this.xCoor(to), this.yCoor(to));
                    if (this.directed) {
                        this.drawArrow(g, from, to);
                    }
                }
            }
        }
        g.setColor(DrawableGraph.TEXT_COLOR);
        g.setFont(Screen.screen.getFont());
        if (this.named) {
            for (int node2 = 0; node2 < this.noOfNodes; ++node2) {
                if (this.nodes[node2]) {
                    g.drawString(this.names[node2], this.xCoor(node2) - 5, this.yCoor(node2) - 10);
                }
            }
        }
    }
    
    private void drawArrow(final Graphics g, final int from, final int to) {
    }
    
    public abstract boolean isSelectable(final int p0);
    
    protected int randomNo(final int limit) {
        if (limit == 0) {
            return 0;
        }
        return Math.abs(this.random.nextInt()) % limit;
    }
    
    public int select(final Position position) {
        for (int node = 0; node < this.noOfNodes; ++node) {
            if (this.isSelectable(node) && position.x() > this.xCoor(node) - 10 && position.x() < this.xCoor(node) + 10 && position.y() > this.yCoor(node) - 10 && position.y() < this.yCoor(node) + 10) {
                return node;
            }
        }
        return -1;
    }
    
    public boolean showing(final int from, final int to) {
        return true;
    }
    
    private int signOf(final int value) {
        return Math.abs(value) / value;
    }
    
    public abstract int xCoor(final int p0);
    
    public abstract int yCoor(final int p0);
}
