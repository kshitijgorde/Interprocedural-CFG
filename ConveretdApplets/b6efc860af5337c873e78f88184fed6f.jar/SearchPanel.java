import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SearchPanel extends ScreenPanel
{
    private static final int MAKE_GRAPH_BUTTON = 0;
    private static final int DEPTH_FIRST_BUTTON = 1;
    private static final int BREADTH_FIRST_BUTTON = 2;
    private static final Position GRAPH_POSITION;
    private SearchGraph graph;
    private String randomPickHelp;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        GRAPH_POSITION = new Position(80, 45);
    }
    
    public SearchPanel(final String[] names, final int first, final Color backgroundColor) {
        this.graph = new SearchGraph(SearchPanel.GRAPH_POSITION, 4, 8, true);
        this.randomPickHelp = "Selecting the Random button causes the searches to begin at a randomly selected node.  Selecting I'll Pick allows you to select the starting node.";
        this.showHelp = new String[] { "Generates a undirected graph with a random number of nodes.", "Demonstrates a breadth-first search of the graph on the screen.", "Demonstrates a depth-first search of the graph on the screen." };
        this.tryHelp = new String[] { "Generates a undirected graph with a random number of nodes.", "You will be asked to click the nodes of this graph in the order of a depth-first search.", "You will be asked to click the nodes of this graph in the order of a breadth-first search." };
        super.init(names, this.showHelp, this.tryHelp, first, this.randomPickHelp, false, backgroundColor);
        Screen.canvas.add(this.graph);
        this.graph.init();
        this.graph.makeGraph(2, 2);
        this.graph.show();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        Screen.canvas.clear();
        if (buttonNumber == 0) {
            this.graph.makeGraph(2, 2);
        }
        else if (buttonNumber == 1) {
            this.graph.doDepthFirstSearch();
        }
        else if (buttonNumber == 2) {
            this.graph.doBreadthFirstSearch();
        }
        Screen.canvas.repaint();
    }
}
