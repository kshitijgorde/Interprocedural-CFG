import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TreeNode extends DrawableNode
{
    public static final int HEAP = 0;
    public static final int BINARY_SEARCH_TREE = 1;
    public static final int RANDOM_VALUE = 2;
    private static final int MAX_VALUE = 80;
    private static final int HIGHLIGHT_DIAMETER = 30;
    private static Vector highlighted;
    private static int nextMin;
    private static int increment;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;
    private int height;
    private int nodeNumber;
    private static boolean completeFullCheck;
    
    static {
        TreeNode.highlighted = new Vector();
        TreeNode.completeFullCheck = false;
    }
    
    public TreeNode(final int level, final int nodeNumber, final Position position, final TreeNode parent) {
        super.level = level;
        this.nodeNumber = nodeNumber;
        this.reposition(position);
        this.parent = parent;
        if (level < 6) {
            this.leftChild = new TreeNode(level + 1, nodeNumber * 2, this.leftPosition(), this);
            this.rightChild = new TreeNode(level + 1, nodeNumber * 2 + 1, this.rightPosition(), this);
        }
        super.nullNode = true;
    }
    
    public void draw(final Graphics g) {
        if (Screen.canvas.showClickables() && TreeNode.completeFullCheck) {
            this.drawClickable(g);
        }
        super.draw(g);
    }
    
    public void drawClickable(final Graphics g) {
        if (super.nullNode && super.level < 6) {
            final Position mouse = Screen.canvas.getMousePosition();
            if (mouse != null && this.inside(mouse)) {
                g.setColor(Color.white);
                g.fillOval(super.x - 15, super.y - 15, 30, 30);
            }
        }
        if (this.left() != null) {
            ((TreeNode)this.left()).drawClickable(g);
        }
        if (this.right() != null) {
            ((TreeNode)this.right()).drawClickable(g);
        }
    }
    
    public boolean isBSTree(final boolean highlight, final boolean checkWho) {
        boolean bstree = true;
        boolean leftBSTree = true;
        boolean rightBSTree = true;
        TreeNode.completeFullCheck = false;
        if (!this.leftChild.nullNode) {
            bstree &= (super.value > this.leftChild.maxValue());
            leftBSTree = this.leftChild.isBSTree(highlight, checkWho);
        }
        if (!this.rightChild.nullNode) {
            bstree &= (super.value < this.rightChild.minValue());
            rightBSTree = this.rightChild.isBSTree(highlight, checkWho);
        }
        final boolean subtrees = leftBSTree && rightBSTree;
        if (!subtrees) {
            return false;
        }
        if (!bstree && highlight) {
            Screen.canvas.highlight(new DrawableCircle(new Position(super.x, super.y), 30, false, false), Color.white, checkWho);
        }
        return bstree;
    }
    
    public int isBalanced() {
        TreeNode.completeFullCheck = false;
        if (!super.nullNode) {
            int unBalanced = 0;
            final int factor = this.leftChild.height - this.rightChild.height;
            if (Math.abs(factor) > 1) {
                unBalanced = 1;
            }
            return this.leftChild.isBalanced() + this.rightChild.isBalanced() + unBalanced;
        }
        return 0;
    }
    
    public boolean isComplete(final int totalNodes, final boolean checkWho) {
        TreeNode.completeFullCheck = true;
        if (this.nodeNumber <= totalNodes) {
            boolean complete;
            if (super.nullNode) {
                Screen.canvas.highlight(new HiddenNode(new Position(super.x, super.y), super.level), Color.white, checkWho);
                complete = false;
            }
            else {
                complete = true;
            }
            final boolean leftComplete = this.leftChild.isComplete(totalNodes, checkWho);
            final boolean rightComplete = this.rightChild.isComplete(totalNodes, checkWho);
            return leftComplete && rightComplete && complete;
        }
        return true;
    }
    
    public boolean isHeap(final boolean checkWho) {
        TreeNode.completeFullCheck = false;
        boolean heap;
        if (this.parent == null) {
            heap = true;
        }
        else {
            if (super.nullNode) {
                return true;
            }
            heap = (super.value < this.parent.value);
            if (!heap) {
                Screen.canvas.highlight(new DrawableLine(new Position(super.x, super.y), new Position(this.parent.x, this.parent.y), 3), Color.white, checkWho);
            }
        }
        final boolean leftHeap = this.leftChild.isHeap(checkWho);
        final boolean rightHeap = this.rightChild.isHeap(checkWho);
        return leftHeap && rightHeap && heap;
    }
    
    public DrawableNode left() {
        return this.leftChild;
    }
    
    private int maxValue() {
        int maximum = super.value;
        if (!this.leftChild.nullNode) {
            maximum = Math.max(maximum, this.leftChild.maxValue());
        }
        if (!this.rightChild.nullNode) {
            maximum = Math.max(maximum, this.rightChild.maxValue());
        }
        return maximum;
    }
    
    private int minValue() {
        int minimum = super.value;
        if (!this.leftChild.nullNode) {
            minimum = Math.min(minimum, this.leftChild.minValue());
        }
        if (!this.rightChild.nullNode) {
            minimum = Math.min(minimum, this.rightChild.minValue());
        }
        return minimum;
    }
    
    public void nullify() {
        super.nullNode = true;
        if (this.leftChild != null) {
            this.leftChild.nullify();
        }
        if (this.rightChild != null) {
            this.rightChild.nullify();
        }
    }
    
    public int randomizeShape(final int totalNodes) {
        if (totalNodes == 0) {
            super.nullNode = this.isNullNode();
        }
        else {
            super.nullNode = (this.nodeNumber > totalNodes);
        }
        if (!super.nullNode) {
            final int biggest = Math.max(this.leftChild.randomizeShape(totalNodes), this.rightChild.randomizeShape(totalNodes));
            this.height = Math.max(this.leftChild.height, this.rightChild.height) + 1;
            return Math.max(biggest, this.nodeNumber);
        }
        return this.height = 0;
    }
    
    public boolean randomizeValues(final int constraint) {
        if (super.nullNode) {
            return true;
        }
        if (constraint == 1) {
            this.leftChild.randomizeValues(constraint);
            super.value = Screen.panel.getRandom(TreeNode.nextMin, TreeNode.nextMin + TreeNode.increment);
            TreeNode.nextMin = super.value + 1;
            this.rightChild.randomizeValues(constraint);
            return true;
        }
        if (constraint == 0) {
            boolean valid;
            do {
                super.value = Math.abs(DrawableNode.random.nextInt() % 80);
                if (this.parent == null) {
                    valid = true;
                }
                else {
                    if (this.parent.value == 0) {
                        return false;
                    }
                    valid = (super.value < this.parent.value);
                }
            } while (!valid);
        }
        else {
            super.value = Math.abs(DrawableNode.random.nextInt() % 80);
        }
        return this.leftChild.randomizeValues(constraint) && this.rightChild.randomizeValues(constraint);
    }
    
    public DrawableNode right() {
        return this.rightChild;
    }
    
    public void setIncrement(final int totalNodes) {
        TreeNode.nextMin = 0;
        TreeNode.increment = 160 / totalNodes;
    }
}
