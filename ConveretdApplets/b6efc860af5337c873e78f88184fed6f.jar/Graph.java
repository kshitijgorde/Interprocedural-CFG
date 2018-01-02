import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Graph extends DrawableGraph
{
    protected static final int RADIUS = 5;
    protected boolean selectAny;
    protected boolean[] marked;
    protected Vector currentNextQueue;
    private int[] edgeList;
    private int edgeCount;
    
    public Graph(final Position position, final int rows, final int cols, final boolean named) {
        super(position, rows, cols, named, 55);
        this.selectAny = true;
        this.marked = new boolean[super.noOfNodes];
        this.edgeList = new int[super.noOfNodes * (super.noOfNodes - 1) / 2];
    }
    
    private void addEdge(final int from, final int to) {
        super.edges[from][to] = (super.edges[to][from] = true);
        this.edgeList[this.edgeCount++] = from * super.noOfNodes + to;
    }
    
    private int distance(final int from, final int to) {
        return Math.abs(from % super.cols - to % super.cols) + Math.abs(from / super.cols - to / super.cols);
    }
    
    public boolean isSelectable(final int node) {
        if (this.selectAny) {
            return super.nodes[node];
        }
        return this.currentNextQueue.contains(new Integer(node));
    }
    
    public void makeGraph(final int more, final int less) {
        final int[] nodeList = new int[super.noOfNodes];
        int nodeCount = 0;
        char lowerLetter = 'a';
        char upperLetter = 'A';
        for (int node = 0; node < super.noOfNodes; ++node) {
            final boolean[] nodes = super.nodes;
            final int n = node;
            final boolean b = super.random.nextInt() > 0;
            nodes[n] = b;
            if (b) {
                nodeList[nodeCount] = node;
                if (super.named) {
                    if (nodeCount < 26) {
                        final String[] names = super.names;
                        final int n2 = node;
                        final char c = upperLetter;
                        upperLetter = (char)(c + '\u0001');
                        names[n2] = String.valueOf(c);
                    }
                    else {
                        final String[] names2 = super.names;
                        final int n3 = node;
                        final char c2 = lowerLetter;
                        lowerLetter = (char)(c2 + '\u0001');
                        names2[n3] = String.valueOf(c2);
                    }
                }
                ++nodeCount;
            }
        }
        this.edgeCount = 0;
        for (int from = 0; from < super.noOfNodes; ++from) {
            for (int to = 0; to < super.noOfNodes; ++to) {
                super.edges[from][to] = false;
            }
        }
        final boolean[] nodeConnected = new boolean[nodeCount];
        for (int node2 = 0; node2 < nodeCount; ++node2) {
            nodeConnected[node2] = false;
        }
        nodeConnected[0] = true;
        int notConnected = nodeCount - 1;
        for (int edgeNo = nodeCount - 1 + this.randomNo(more); edgeNo > 0; --edgeNo) {
            int minDistance = Integer.MAX_VALUE;
            int minFrom = -1;
            int minTo = -1;
            for (int from2 = 0; from2 < nodeCount; ++from2) {
                if (nodeConnected[from2]) {
                    for (int to2 = 0; to2 < nodeCount; ++to2) {
                        boolean acceptEdge;
                        if (notConnected > 0) {
                            acceptEdge = (nodeConnected[to2] ^ true);
                        }
                        else {
                            acceptEdge = this.nonOverlap(nodeList[from2], nodeList[to2]);
                        }
                        if (acceptEdge) {
                            final int thisDistance = this.distance(nodeList[from2], nodeList[to2]);
                            if (thisDistance < minDistance) {
                                minFrom = from2;
                                minTo = to2;
                                minDistance = thisDistance;
                            }
                        }
                    }
                }
            }
            nodeConnected[minTo] = true;
            this.addEdge(nodeList[minFrom], nodeList[minTo]);
            --notConnected;
        }
        for (int fewerEdges = this.randomNo(less); fewerEdges > 0; --fewerEdges) {
            final int removalEdge = this.edgeList[this.randomNo(this.edgeCount)];
            this.removeEdge(removalEdge / super.noOfNodes, removalEdge % super.noOfNodes);
        }
    }
    
    protected Position namePosition(final int node) {
        return new Position(this.xCoor(node) - 5, this.yCoor(node) - 10);
    }
    
    protected Position nodePosition(final int node) {
        return new Position(this.xCoor(node), this.yCoor(node));
    }
    
    private boolean nonOverlap(final int from, final int to) {
        if (super.edges[from][to] || from == to) {
            return false;
        }
        int fromRow = from / super.cols;
        int toRow = to / super.cols;
        if (toRow < fromRow) {
            fromRow = to / super.cols;
            toRow = from / super.cols;
        }
        int fromCol = from % super.cols;
        int toCol = to % super.cols;
        if (toCol < fromCol) {
            fromCol = to % super.cols;
            toCol = from % super.cols;
        }
        if (fromRow == toRow) {
            for (int col1 = fromCol; col1 <= toCol; ++col1) {
                for (int col2 = fromCol; col2 <= col1; ++col2) {
                    if (super.edges[fromRow * super.cols + col1][fromRow * super.cols + col2]) {
                        return false;
                    }
                }
            }
        }
        if (fromCol == toCol) {
            for (int row1 = fromRow; row1 <= toRow; ++row1) {
                for (int row2 = fromRow; row2 <= row1; ++row2) {
                    if (super.edges[row1 * super.cols + fromCol][row2 * super.cols + fromCol]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void removeEdge(final int from, final int to) {
        super.edges[from][to] = (super.edges[to][from] = false);
        --this.edgeCount;
    }
    
    public int xCoor(final int node) {
        return node % super.cols * super.spacing + super.x;
    }
    
    public int yCoor(final int node) {
        return node / super.cols * super.spacing + super.y;
    }
}
