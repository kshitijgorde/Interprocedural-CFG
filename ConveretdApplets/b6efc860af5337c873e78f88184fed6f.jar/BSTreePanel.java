import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class BSTreePanel extends ScreenPanel
{
    private static final int INSERT_BUTTON = 0;
    private static final int DELETE_BUTTON = 1;
    private static final int FIND_BUTTON = 2;
    private static final int DIGITS = 2;
    private static final int LOWER = 0;
    private static final int UPPER = 99;
    private static final Position ROOT_POSITION;
    private static final Position LIST_POSITION;
    private static final int TRACER_DIAMETER = 30;
    private BSNode binaryTree;
    private DrawableCircle tracer;
    private DrawableString nodeLabel;
    private String randomPickHelp;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(275, 20);
        LIST_POSITION = new Position(2, 257);
    }
    
    public BSTreePanel(final String[] names, final int first, final Color backgroundColor) {
        this.binaryTree = new BSNode(1, BSTreePanel.ROOT_POSITION, null);
        this.tracer = new DrawableCircle(BSTreePanel.ROOT_POSITION, 30, false, false);
        this.nodeLabel = new DrawableString(BSTreePanel.ROOT_POSITION);
        this.randomPickHelp = "Selecting the Random button causes values to be randomly generated.  Selecting I'll Pick allows you to enter the value in a box the will appear at the top of the screen when a value is required.";
        this.showHelp = new String[] { "Demonstrates the insertion of a new value into the binary search tree.  A search is performed to find the location and a new node is added at the bottom of the tree.  If the value is already in the tree or the branch is full, a message is displayed.", "Demonstrates the deletion of a node in the binary search tree.  The steps required to complete the deletion depend upon the number of children of the node containing the value to be deleted.", "Demonstrates the find operation of a binary search tree.  The nodes of the path followed to search for the value turn red as it is traced.  A message indicates whether it was found." };
        this.tryHelp = new String[] { "You will be asked to click the location on the screen where the node containing the new value should be inserted.  If the value is already in the tree or the branch where it would be added is full, you are to click on the appropriate button at the top of the screen.", "You will be asked to click the node containing the value to delete, or the button indicating it is not in the tree.  If the node has two children, you will also be asked to click the node containing the value to be moved to the node containing the value to be deleted.", "You will be asked to click the nodes beginning at the root that trace the path that is followed to search for the specified value.  Finally, you be asked to click the appropriate button at the top of the screen, depending upon whether the value was found or not." };
        super.init(names, this.showHelp, this.tryHelp, first, this.randomPickHelp, false, backgroundColor);
        Screen.canvas.add(this.binaryTree);
        Screen.canvas.add(this.tracer);
        Screen.canvas.add(this.nodeLabel);
        this.binaryTree.show();
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        if (this.isInteractive()) {
            this.tracer.hide();
        }
        else {
            this.tracer.show();
        }
        this.tracer.reposition(BSTreePanel.ROOT_POSITION);
        Screen.canvas.repaint();
        if (buttonNumber == 0) {
            if (Screen.panel.userPicks()) {
                Screen.prompt.displayPrompt("Enter Value To Insert: ");
            }
            final int value = Screen.prompt.getData(2, 0, 99, false);
            if (value >= 0) {
                if (!Screen.panel.isInteractive()) {
                    Screen.prompt.displayPrompt("Number To Insert: " + value);
                }
                this.binaryTree.insert(value, this.tracer);
            }
        }
        else if (buttonNumber == 1) {
            int value;
            if (Screen.panel.userPicks()) {
                Screen.prompt.displayPrompt("Enter Value To Delete: ");
                value = Screen.prompt.getData(2, 0, 99, false);
            }
            else {
                value = this.binaryTree.getValue();
            }
            if (value >= 0) {
                if (!Screen.panel.isInteractive()) {
                    Screen.prompt.displayPrompt("Number To Delete: " + value);
                }
                this.binaryTree.delete(value, this.nodeLabel, this.tracer);
            }
        }
        else {
            if (!this.isInteractive()) {
                this.tracer.show();
            }
            int value;
            if (Screen.panel.userPicks()) {
                Screen.prompt.displayPrompt("Enter Value To Find: ");
                value = Screen.prompt.getData(2, 0, 99, false);
            }
            else {
                value = this.binaryTree.getValue();
            }
            if (value >= 0) {
                if (!Screen.panel.isInteractive()) {
                    Screen.prompt.displayPrompt("Number To Find: " + value);
                }
                this.binaryTree.find(value, this.tracer);
            }
        }
        this.tracer.hide();
        Screen.canvas.repaint();
    }
}
