import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HBTreePanel extends ScreenPanel
{
    private static final int MAKE_TREE_BUTTON = 0;
    private static final int HEIGHT_BUTTON = 1;
    private static final int BALANCE_FACTORS_BUTTON = 2;
    private static final int IS_BALANCED_BUTTON = 3;
    private static final int IS_HBn_BUTTON = 4;
    private static final Position ROOT_POSITION;
    private HBNode binaryTree;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(273, 20);
    }
    
    public HBTreePanel(final String[] names, final int first, final Color backgroundColor) {
        this.binaryTree = new HBNode(1, HBTreePanel.ROOT_POSITION, null);
        this.showHelp = new String[] { "Generates a binary tree with a random number of nodes.", "Each node is labeled with the height of the subtree of which it is the root.", "Each node is labeled with the computation of its balance factor as the difference between the height of its left and right subtrees.", "If the tree on the screen is an balanced tree, a message is displayed at the top of the screen, otherwise the nodes of the tree with improper balance factors are labeled.", "You will be prompted first to enter the value of n in a box in the upper left corner. If the tree on the screen is a height-balanced n, a message will be displayed at the top of the screen, otherwise the nodes of the tree with improper balance factors are labeled." };
        this.tryHelp = new String[] { "Generates a binary tree with a random number of nodes.", "You will be asked to input in a box in the upper left corner the height of the subtree whose root will be highlighted in white.", "You will be asked to input in a box in the upper left corner the balance factor of the node that will be highlighted in white.", "You will be asked to click on a button at the top of the screen if the tree is an balanced tree, otherwise you will be asked to click on one of the nodes with an improper balance factor.", "You will be prompted first to enter the value of n in a box in the upper left corner. You will then be asked to click on a button at the top of the screen if the tree is height-balanced n, otherwise you will be asked to click on one of the nodes with an improper balance factor." };
        super.init(names, this.showHelp, this.tryHelp, first, null, true, backgroundColor);
        Screen.canvas.add(this.binaryTree);
        this.binaryTree.randomize();
        this.binaryTree.show();
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        this.binaryTree.clear();
        Screen.canvas.clear();
        Screen.canvas.repaint();
        if (buttonNumber == 0) {
            this.binaryTree.randomize();
        }
        else if (buttonNumber == 1) {
            this.binaryTree.heights(false);
        }
        else if (buttonNumber == 2) {
            if (this.isInteractive()) {
                this.binaryTree.heights(true);
            }
            this.binaryTree.balanceFactors();
        }
        else if (buttonNumber == 3 || buttonNumber == 4) {
            int n = 1;
            String correctResponse;
            if (buttonNumber == 4) {
                Screen.prompt.displayPrompt("Enter Value for n (2-4)");
                n = Screen.prompt.getData(1, 2, 4, true);
                correctResponse = "It Is Height Balanced - " + n;
            }
            else {
                correctResponse = "It Is An Balanced Tree";
            }
            final int unBalanced = this.binaryTree.isHBn(n, true);
            if (!Screen.answer.oneButton(unBalanced == 0, "Click Unbalanced Node or", correctResponse, "No, " + unBalanced + " Unbalanced Nodes", "That Node Is Unbalanced")) {
                this.binaryTree.isHBn(n, false);
            }
        }
        Screen.canvas.repaint();
    }
}
