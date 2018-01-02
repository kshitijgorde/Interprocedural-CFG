import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HBNode extends DrawableNode
{
    private static final int HIGHLIGHT_DIAMETER = 30;
    private String stringValue;
    private HBNode leftChild;
    private HBNode rightChild;
    private HBNode parent;
    private int height;
    
    public HBNode(final int level, final Position position, final HBNode parent) {
        super.level = level;
        this.reposition(position);
        this.parent = parent;
        if (level < 6) {
            this.leftChild = new HBNode(level + 1, this.leftPosition(), this);
            this.rightChild = new HBNode(level + 1, this.rightPosition(), this);
        }
        super.nullNode = true;
    }
    
    public void balanceFactors() {
        if (!super.nullNode) {
            final int factor = this.leftChild.height - this.rightChild.height;
            if (Screen.panel.isInteractive()) {
                Screen.canvas.highlight(new DrawableCircle(new Position(super.x, super.y), 30, true, false), Color.white, false);
                Screen.canvas.repaint();
                Screen.prompt.awaitInput("Enter Balance Factor: ", factor);
                Screen.canvas.clear();
            }
            else {
                Screen.controls.nextStep(true, true);
            }
            this.stringValue = this.leftChild.height + "-" + this.rightChild.height + "=" + factor;
            this.leftChild.balanceFactors();
            this.rightChild.balanceFactors();
        }
    }
    
    public void clear() {
        if (!super.nullNode) {
            this.stringValue = "";
            this.leftChild.clear();
            this.rightChild.clear();
        }
    }
    
    public String getString() {
        return this.stringValue;
    }
    
    public void heights(final boolean set) {
        if (!super.nullNode) {
            this.leftChild.heights(set);
            this.rightChild.heights(set);
            if (!Screen.panel.isInteractive()) {
                Screen.controls.nextStep(true, true);
            }
            else if (!set) {
                Screen.canvas.highlight(new DrawableCircle(new Position(super.x, super.y), 30, true, false), Color.white, false);
                Screen.canvas.repaint();
                Screen.prompt.awaitInput("Enter Height Of Subtree: ", this.height);
                Screen.canvas.clear();
            }
            this.stringValue = "   " + this.height;
        }
    }
    
    public int isHBn(final int n, final boolean checkWho) {
        if (!super.nullNode) {
            int unBalanced = 0;
            final int factor = this.leftChild.height - this.rightChild.height;
            if (Math.abs(factor) > n) {
                Screen.canvas.highlight(new DrawableCircle(new Position(super.x, super.y), 30, false, false), Color.white, checkWho);
                if (!checkWho || !Screen.panel.isInteractive()) {
                    this.stringValue = this.leftChild.height + "-" + this.rightChild.height + "=" + factor;
                }
                unBalanced = 1;
            }
            else {
                this.stringValue = "";
            }
            return this.leftChild.isHBn(n, checkWho) + this.rightChild.isHBn(n, checkWho) + unBalanced;
        }
        return 0;
    }
    
    public DrawableNode left() {
        return this.leftChild;
    }
    
    public void randomize() {
        super.stringValueNode = true;
        if (!(super.nullNode = this.isNullNode())) {
            this.stringValue = "";
            this.leftChild.randomize();
            this.rightChild.randomize();
            this.height = Math.max(this.leftChild.height, this.rightChild.height) + 1;
        }
        else {
            this.height = 0;
        }
    }
    
    public DrawableNode right() {
        return this.rightChild;
    }
}
