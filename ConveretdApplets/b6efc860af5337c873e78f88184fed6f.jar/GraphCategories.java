import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class GraphCategories extends Graph
{
    private int[] path;
    private int pathLength;
    private boolean cycles;
    
    public GraphCategories(final Position position, final int rows, final int cols, final boolean named) {
        super(position, rows, cols, named);
        this.pathLength = 0;
        this.path = new int[super.noOfNodes + 1];
    }
    
    private void checkCycles(final int node, final boolean checkWho, final boolean markCycles) {
        for (int pathIndex = this.pathLength - 3; pathIndex >= 0; --pathIndex) {
            if (super.edges[node][this.path[pathIndex]]) {
                this.path[this.pathLength] = this.path[pathIndex];
                this.cycles = true;
                if (markCycles) {
                    for (int edge = pathIndex; edge < this.pathLength; ++edge) {
                        Screen.canvas.highlight(new DrawableLine(this.nodePosition(this.path[edge]), this.nodePosition(this.path[edge + 1]), 1), Color.white, checkWho);
                    }
                }
            }
        }
    }
    
    private int getStart(final boolean first) {
        this.pathLength = 0;
        if (first) {
            for (int node = 0; node < super.noOfNodes; ++node) {
                super.marked[node] = false;
            }
        }
        for (int node = 0; node < super.noOfNodes; ++node) {
            if (super.nodes[node] && !super.marked[node]) {
                return node;
            }
        }
        return -1;
    }
    
    public boolean hasNoCycles(final boolean checkWho, final boolean markCycles) {
        this.cycles = false;
        int nextStart = this.getStart(true);
        this.search(nextStart, checkWho, markCycles);
        while ((nextStart = this.getStart(false)) >= 0) {
            this.search(nextStart, checkWho, markCycles);
        }
        return this.cycles ^ true;
    }
    
    public boolean isConnected(final boolean checkWho, final boolean showConnections) {
        boolean connected = true;
        final int start = this.getStart(true);
        this.search(start, checkWho, false);
        if (showConnections) {
            Screen.canvas.highlight(new DrawableCircle(this.nodePosition(start), 10, false, false), Color.blue, false);
        }
        Screen.canvas.repaint();
        for (int node = 0; node < super.noOfNodes; ++node) {
            if (super.nodes[node] && !super.marked[node]) {
                if (showConnections) {
                    Screen.canvas.highlight(new DrawableCircle(this.nodePosition(node), 10, false, true), Color.white, checkWho);
                }
                connected = false;
            }
        }
        return connected;
    }
    
    private void search(final int current, final boolean checkWho, final boolean markCycles) {
        this.path[this.pathLength++] = current;
        final int currentLength = this.pathLength;
        super.marked[current] = true;
        this.checkCycles(current, checkWho, markCycles);
        for (int next = 0; next < super.noOfNodes; ++next) {
            if (super.edges[current][next] && !super.marked[next]) {
                this.search(next, checkWho, markCycles);
                this.pathLength = currentLength;
            }
        }
    }
}
