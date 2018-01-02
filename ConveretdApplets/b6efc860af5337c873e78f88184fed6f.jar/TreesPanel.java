import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class TreesPanel extends ScreenPanel
{
    private static final int MAKE_TREE_BUTTON = 0;
    private static final int IS_COMPLETE_BUTTON = 1;
    private static final int IS_FULL_BUTTON = 2;
    private static final int IS_BS_TREE_BUTTON = 3;
    private static final int IS_HEAP_BUTTON = 4;
    private static final int IS_AVL_BUTTON = 5;
    private static final int MIN_NODES = 4;
    private static final Position ROOT_POSITION;
    private TreeNode binaryTree;
    private int totalNodes;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(273, 20);
    }
    
    public TreesPanel(final String[] names, final int first, final Color backgroundColor) {
        this.binaryTree = new TreeNode(1, 1, TreesPanel.ROOT_POSITION, null);
        this.showHelp = new String[] { "Generates a new binary tree with a random number of nodes and random values.", "If the tree on the screen is complete, a message is displayed at the top of the screen, otherwise all the highlighted in red.", "If the tree on the screen is full, a message is displayed at the top of the screen, otherwise all the missing nodes are highlighted in red.", "If the tree on the screen is a binary search tree, a message is displayed at the top of the screen, otherwise all of the roots of the lowest subtrees that are not binary search trees are highlighted in white.", "If the tree on the screen is a heap, a message is displayed at the top of the screen, otherwise all of the branches that violate the heap property are highlighted in white.", "If the tree on the screen is an AVL tree, a message is displayed at the top of the screen, otherwise the nodes of the tree with improper balance factors are highlighted in white." };
        this.tryHelp = new String[] { "Generates a new binary tree with a random number of nodes and random values.", "You will be asked to click on a button at the top of the screen if the tree is almost complete, otherwise you are to click on the location of any missing node.", "You will be asked to click on a button at the top of the screen if the tree is complete, otherwise you are to click on the location of any missing node.", "You will be asked to click on a button at the top of the screen if the tree on the screen is a binary search tree, otherwise you are to click on the root of any of the lowest subtrees that are not binary search trees.", "You will be asked to click on a button at the top of the screen if the tree on the screen is a heap, otherwise you are to click on any branch of the tree that violates the heap property.", "You will be asked to click on a button at the top of the screen if the tree is an AVL tree, otherwise you will be asked to click on one of the nodes with an improper balance factor." };
        super.init(names, this.showHelp, this.tryHelp, first, null, false, backgroundColor);
        Screen.canvas.add(this.binaryTree);
        this.binaryTree.show();
        this.makeTree();
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        Screen.canvas.clear();
        Screen.canvas.repaint();
        if (buttonNumber == 0) {
            this.makeTree();
        }
        else if (buttonNumber == 1) {
            final boolean isComplete = this.binaryTree.isComplete(this.totalNodes, true);
            if (!Screen.answer.oneButton(isComplete, "Click A Missing Node or", "It Is Complete", "Red Nodes Missing", "That Node Is Missing")) {
                this.binaryTree.isComplete(this.totalNodes, false);
            }
        }
        else if (buttonNumber == 2) {
            int full;
            for (full = 1; full - 1 < this.totalNodes; full *= 2) {
                final boolean nullStatement = true;
            }
            final boolean isComplete2 = this.binaryTree.isComplete(--full, true);
            if (!Screen.answer.oneButton(isComplete2, "Click A Missing Node or", "This Tree Is Full", "Red Nodes Missing", "That Node Is Missing")) {
                this.binaryTree.isComplete(full, false);
            }
        }
        else if (buttonNumber == 3) {
            final boolean isBSTree = this.binaryTree.isBSTree(true, true);
            if (!Screen.answer.oneButton(isBSTree, "Click A Wrong Subtree or", "Is Binary Search Tree", "White Subtrees Wrong", "That Subtree Is Wrong")) {
                this.binaryTree.isBSTree(true, false);
            }
        }
        else if (buttonNumber == 4) {
            final boolean isHeap = this.binaryTree.isHeap(true);
            if (!Screen.answer.oneButton(isHeap, "Click A Wrong Branch or", "This Tree Is A Heap", "White Branches Wrong", "That Branch Is Wrong")) {
                this.binaryTree.isHeap(false);
            }
        }
        else if (buttonNumber == 5) {
            final boolean isAVLTree = this.binaryTree.isBalanced() == 0 && this.binaryTree.isBSTree(false, false);
            Screen.answer.twoButtons("Click One Of The Choices", "Is AVL Tree", "Not AVL Tree", "It Is An AVL Tree", "It Is Not An AVL Tree", isAVLTree, true);
        }
        Screen.canvas.repaint();
    }
    
    public void makeTree() {
        this.binaryTree.nullify();
        final int type = Math.abs(super.random.nextInt() % 5);
        switch (type) {
            case 0: {
                this.totalNodes = Math.abs(super.random.nextInt() % 32);
                this.totalNodes = Math.max(this.totalNodes, 4);
                this.totalNodes = this.binaryTree.randomizeShape(this.totalNodes);
                this.binaryTree.randomizeValues(2);
                break;
            }
            case 1: {
                final int levels = Math.abs(super.random.nextInt() % 5 + 1);
                this.totalNodes = (int)Math.pow(2.0, levels) - 1;
                this.totalNodes = this.binaryTree.randomizeShape(this.totalNodes);
                this.binaryTree.randomizeValues(2);
                break;
            }
            case 2: {
                this.totalNodes = this.binaryTree.randomizeShape(0);
                this.binaryTree.setIncrement(this.totalNodes);
                this.binaryTree.randomizeValues(1);
                break;
            }
            case 3: {
                this.totalNodes = Math.abs(super.random.nextInt() % 32);
                this.totalNodes = Math.max(this.totalNodes, 4);
                this.totalNodes = this.binaryTree.randomizeShape(this.totalNodes);
                while (!this.binaryTree.randomizeValues(0)) {}
                break;
            }
            default: {
                this.totalNodes = this.binaryTree.randomizeShape(0);
                this.binaryTree.randomizeValues(2);
                break;
            }
        }
    }
}
