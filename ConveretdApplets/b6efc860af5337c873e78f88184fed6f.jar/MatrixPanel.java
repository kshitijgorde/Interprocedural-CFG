import java.awt.event.ItemEvent;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class MatrixPanel extends ScreenPanel implements ItemListener
{
    private static final int MAKE_BUTTON = 0;
    private static final int CREATE_BUTTON = 1;
    private static final Position GRAPH_POSITION;
    private CheckboxGroup Group3;
    private Checkbox matrix;
    private Checkbox graph;
    private String matrixGraphHelp;
    private SizedComponentPair matrixGraphGroup;
    private MatrixGraph matrixgraph;
    private String[] graphShowHelp;
    private String[] graphTryHelp;
    private String[] matrixShowHelp;
    private String[] matrixTryHelp;
    
    static {
        GRAPH_POSITION = new Position(45, 65);
    }
    
    public MatrixPanel(final String[] names, final int first, final Color backgroundColor) {
        this.Group3 = new CheckboxGroup();
        this.matrix = new Checkbox("Matrix", this.Group3, true);
        this.graph = new Checkbox("Graph", this.Group3, false);
        this.matrixGraphHelp = "Selecting Matrix causes the Make One button to make a matrix, and the Create Other button to make a graph.  Selecting Graph has the opposite effect.";
        this.matrixGraphGroup = new SizedComponentPair(this.matrix, this.graph, 72, 40, this.matrixGraphHelp, "East");
        this.matrixgraph = new MatrixGraph(MatrixPanel.GRAPH_POSITION, 3, 4);
        this.graphShowHelp = new String[] { "Generates a undirected graph with a random number of edges.", "Creates the adjacency matrix that corresponding to undirected graph." };
        this.graphTryHelp = new String[] { "Generates a undirected graph with a random number of edges.", "You will be asked to click the two matrix cells that correspond to the edge whose nodes are highlighted in white, for each edge in the graph." };
        this.matrixShowHelp = new String[] { "Generates a adjacency matrix with a random number of edges.", "Creates the undirected graph that corresponds to the adjacency matrix." };
        this.matrixTryHelp = new String[] { "Generates a adjacency matrix with a random number of edges.", "You will be asked to click the pair of nodes that define the edge that corresponds to the two adjancency matrix entries that are highlighted in white, for each edge in the matrix. " };
        super.init(names, this.matrixShowHelp, this.matrixTryHelp, first, null, false, backgroundColor);
        this.addPanel(this.matrixGraphGroup);
        Screen.canvas.add(this.matrixgraph);
        this.matrixgraph.make();
        this.matrixgraph.show();
        this.matrix.addItemListener(this);
        this.graph.addItemListener(this);
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        if (buttonNumber == 0) {
            this.matrixgraph.make();
        }
        else if (buttonNumber == 1) {
            this.matrixgraph.create();
        }
        Screen.canvas.repaint();
    }
    
    public void changeMessagesSpecials() {
        if (super.running) {
            this.matrixGraphGroup.changeMessage(super.runningMessage);
        }
        else {
            this.matrixGraphGroup.changeMessage(this.matrixGraphHelp);
        }
    }
    
    public void disableSpecials() {
        this.matrix.setEnabled(false);
        this.graph.setEnabled(false);
    }
    
    public void enableSpecials() {
        this.matrix.setEnabled(true);
        this.graph.setEnabled(true);
    }
    
    public synchronized void itemStateChanged(final ItemEvent event) {
        if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.matrix.getLabel())) {
            this.matrixgraph.reset(true);
            super.shownOrTried[1] = true;
            super.showMessages = this.matrixShowHelp;
            super.tryMessages = this.matrixTryHelp;
            this.changeMessages();
            this.enableButtons();
        }
        else if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.graph.getLabel())) {
            this.matrixgraph.reset(false);
            super.shownOrTried[1] = true;
            super.showMessages = this.graphShowHelp;
            super.tryMessages = this.graphTryHelp;
            this.changeMessages();
            this.enableButtons();
        }
        super.itemStateChanged(event);
    }
}
