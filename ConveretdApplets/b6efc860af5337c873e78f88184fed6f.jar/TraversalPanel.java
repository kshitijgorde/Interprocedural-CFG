import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TraversalPanel extends ScreenPanel
{
    private static final int MAKE_TREE_BUTTON = 0;
    private static final int LEVEL_ORDER_BUTTON = 4;
    private static final Position ROOT_POSITION;
    private static final Position LIST_POSITION;
    private static final int TRACER_DIAMETER = 30;
    private TraversalNode binaryTree;
    private Vector queue;
    private TraversalList traversalList;
    private DrawableCircle tracer;
    private DrawableString nodeLabel;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(273, 20);
        LIST_POSITION = new Position(0, 250);
    }
    
    public TraversalPanel(final String[] names, final int first, final Color backgroundColor) {
        this.binaryTree = new TraversalNode(1, TraversalPanel.ROOT_POSITION);
        this.queue = new Vector();
        this.traversalList = new TraversalList(TraversalPanel.LIST_POSITION, 32);
        this.tracer = new DrawableCircle(TraversalPanel.ROOT_POSITION, 30, false, false);
        this.nodeLabel = new DrawableString(TraversalPanel.ROOT_POSITION);
        this.showHelp = new String[] { "Generates a new binary tree with a random number of nodes and random values.", "Demonstrates a preorder traversal of the binary tree on the screen.", "Demonstrates an inorder traversal of the binary tree on the screen.", "Demonstrates a postorder traversal of the binary tree on the screen.", "Demonstrates a level order traversal of the binary tree on the screen." };
        this.tryHelp = new String[] { "Generates a new binary tree with a random number of nodes and random values.", "You will be asked to click the nodes of this binary tree in preorder.", "You will be asked to click the nodes of this binary tree in inorder.", "You will be asked to click the nodes of this binary tree in postorder.", "You will be asked to click the nodes of this binary tree in level order." };
        super.init(names, this.showHelp, this.tryHelp, first, null, false, backgroundColor);
        this.binaryTree.randomize(1);
        Screen.canvas.add(this.binaryTree);
        Screen.canvas.add(this.traversalList);
        Screen.canvas.add(this.tracer);
        this.tracer.setColor(Color.white);
        Screen.canvas.add(this.nodeLabel);
        this.binaryTree.show();
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        if (buttonNumber == 0) {
            this.traversalList.hide();
            this.binaryTree.randomize(1);
        }
        else {
            if (this.isInteractive()) {
                this.tracer.hide();
            }
            else {
                this.tracer.show();
            }
            this.tracer.reposition(TraversalPanel.ROOT_POSITION);
            this.traversalList.init();
            this.traversalList.show();
            Screen.canvas.repaint();
            if (buttonNumber == 4) {
                this.binaryTree.levelTraversal(this.queue, this);
            }
            else {
                this.binaryTree.traversal(buttonNumber, this);
                this.binaryTree.clearChecks();
            }
            this.tracer.hide();
        }
        this.binaryTree.resetColor();
        Screen.canvas.repaint();
    }
    
    public void repositionTracer(final Position position) {
        this.tracer.reposition(position);
    }
    
    public void traversalMove(final TraversalNode node, final boolean visitNode, final Position position, final int value, final boolean checkNode) {
        node.move("Click the Next Node", visitNode, this.tracer);
        if (checkNode) {
            node.check();
        }
        if (visitNode) {
            this.nodeLabel.reposition(position);
            node.changeColor();
            this.traversalList.append(String.valueOf(value), this.nodeLabel);
        }
        Screen.controls.nextStep(visitNode, true);
    }
}
