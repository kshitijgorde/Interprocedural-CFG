import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TraversalNode extends DrawableNode
{
    private static final int PREORDER = 1;
    private static final int INORDER = 2;
    private static final int POSTORDER = 3;
    private TraversalNode leftChild;
    private TraversalNode rightChild;
    
    public TraversalNode(final int level, final Position position) {
        super.level = level;
        this.reposition(position);
        if (level < 6) {
            this.leftChild = new TraversalNode(level + 1, this.leftPosition());
            this.rightChild = new TraversalNode(level + 1, this.rightPosition());
        }
        else {
            super.nullNode = true;
        }
    }
    
    public void check() {
        ++super.numberChecked;
    }
    
    public void clearChecks() {
        if (!super.nullNode) {
            super.numberChecked = 0;
            this.leftChild.clearChecks();
            this.rightChild.clearChecks();
        }
    }
    
    public DrawableNode left() {
        return this.leftChild;
    }
    
    public void levelTraversal(final Vector queue, final TraversalPanel panel) {
        if (!this.leftChild.nullNode) {
            queue.addElement(this.leftChild);
        }
        if (!this.rightChild.nullNode) {
            queue.addElement(this.rightChild);
        }
        panel.traversalMove(this, true, this.newPosition(), super.value, false);
        if (queue.size() > 0) {
            final TraversalNode next = queue.firstElement();
            queue.removeElement(next);
            next.levelTraversal(queue, panel);
        }
        panel.repositionTracer(new Position(super.x, super.y));
    }
    
    private Position newPosition() {
        return new Position(super.x - 7, super.y + 5);
    }
    
    public void randomize(final int level) {
        super.numberChecked = 0;
        if (!(super.nullNode = this.isNullNode())) {
            super.value = Math.abs(DrawableNode.random.nextInt() % 100);
            this.leftChild.randomize(level + 1);
            this.rightChild.randomize(level + 1);
        }
    }
    
    public DrawableNode right() {
        return this.rightChild;
    }
    
    public void traversal(final int order, final TraversalPanel panel) {
        if (!super.nullNode) {
            panel.traversalMove(this, order == 1, this.newPosition(), super.value, true);
            this.leftChild.traversal(order, panel);
            panel.traversalMove(this, order == 2, this.newPosition(), super.value, true);
            this.rightChild.traversal(order, panel);
            panel.traversalMove(this, order == 3, this.newPosition(), super.value, true);
        }
    }
}
