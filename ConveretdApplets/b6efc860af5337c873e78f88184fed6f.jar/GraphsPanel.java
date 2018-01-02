import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class GraphsPanel extends ScreenPanel
{
    private static final int MAKE_GRAPH_BUTTON = 0;
    private static final int CONNECTED_BUTTON = 1;
    private static final int CYCLES_BUTTON = 2;
    private static final int TREE_BUTTON = 3;
    private static final Position GRAPH_POSITION;
    private GraphCategories graph;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        GRAPH_POSITION = new Position(80, 65);
    }
    
    public GraphsPanel(final String[] names, final int first, final Color backgroundColor) {
        this.graph = new GraphCategories(GraphsPanel.GRAPH_POSITION, 4, 8, false);
        this.showHelp = new String[] { "Generates a undirected graph with a random number of nodes.", "A message will appear at the top of the screen indicating whether or not the graph is connected.", "A message will appear at the top of the screen indicating whether or not the graph has cycles.  If there are any cycles, they will be highlighted in white.", "A message will appear at the top of the screen indicating whether or not the graph on the screen is an undirected tree ." };
        this.tryHelp = new String[] { "Generates a undirected graph with a random number of nodes.", "You will be asked to click on the button 'All Connected To Blue' if the graph on the screen is connected, otherwise you are to click on any node unreachable from the node circled in blue.", "You will be asked to click on the button 'It Has No Cycles' if the graph on the screen contains no cycles, otherwise you are to click on the edge of any cycle in the graph.", "You will be asked to click on the appropriate button at the top of the screen indicating whether or not the graph on the screen is an undirected tree." };
        super.init(names, this.showHelp, this.tryHelp, first, null, false, backgroundColor);
        Screen.canvas.add(this.graph);
        this.graph.makeGraph(3, 3);
        this.graph.show();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        Screen.canvas.clear();
        if (buttonNumber == 0) {
            this.graph.makeGraph(3, 3);
        }
        else if (buttonNumber == 1) {
            if (!Screen.answer.oneButton(this.graph.isConnected(true, true), "Click Unreachable From Blue or ", "All Connected To Blue", "Blue Disconnected From White", "That Node Is Unreachable")) {
                this.graph.isConnected(false, true);
            }
        }
        else if (buttonNumber == 2) {
            if (!Screen.answer.oneButton(this.graph.hasNoCycles(true, true), "Click Edge Of Cycle or", "It Has No Cycles", "It Has Cycles", "That Is A Cycle Edge")) {
                this.graph.hasNoCycles(false, true);
            }
        }
        else if (buttonNumber == 3) {
            final boolean connected = this.graph.isConnected(false, false);
            final boolean acyclic = this.graph.hasNoCycles(false, false);
            final boolean isTree = connected && acyclic;
            Screen.answer.twoButtons("Click One Of The Choices", "It Is A Tree", "It Is Not A Tree", "It Is A Tree", "It Is Not A Tree", isTree, true);
        }
        Screen.canvas.repaint();
    }
}
