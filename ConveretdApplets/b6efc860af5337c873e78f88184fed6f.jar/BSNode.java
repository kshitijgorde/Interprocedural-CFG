import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class BSNode extends DrawableNode
{
    private BSNode leftChild;
    private BSNode rightChild;
    private BSNode parent;
    private Vector values;
    private static boolean inserting;
    
    static {
        BSNode.inserting = false;
    }
    
    public BSNode(final int level, final Position position, final BSNode parent) {
        this.values = new Vector();
        super.level = level;
        this.reposition(position);
        this.parent = parent;
        if (level < 6) {
            this.leftChild = new BSNode(level + 1, this.leftPosition(), this);
            this.rightChild = new BSNode(level + 1, this.rightPosition(), this);
        }
        super.nullNode = true;
    }
    
    private BSNode(final BSNode copy, final BSNode to) {
        this.values = new Vector();
        super.x = copy.x;
        super.y = copy.y;
        super.level = copy.level;
        super.value = copy.value;
        super.nullNode = false;
        copy.nullNode = true;
        super.movingTreeNode = true;
        super.nodeColor = Color.gray;
        Screen.canvas.add(this);
        this.show();
        final Position[] positions = { new Position(to.x, to.y) };
        this.moveTo(positions);
        if (copy.leftChild.nullNode) {
            this.leftChild = null;
        }
        else {
            this.leftChild = new BSNode(copy.leftChild, to.leftChild);
        }
        if (copy.rightChild.nullNode) {
            this.rightChild = null;
        }
        else {
            this.rightChild = new BSNode(copy.rightChild, to.rightChild);
        }
    }
    
    public void delete(final int value, final DrawableString nodeLabel, final DrawableCircle tracer) {
        BSNode.inserting = false;
        final BSNode node = this.retrieve(value, tracer, false, false);
        if (!node.nullNode && Screen.panel.isInteractive()) {
            Screen.canvas.acceptClick(node);
        }
        Screen.answer.oneButton(node.nullNode, "Click Node With " + value + " Or", "Value Is Not In Tree", "Value Deleted", "That Is The Node To Delete");
        if (!node.nullNode) {
            this.values.removeElement(new Integer(value));
            if (node.leftChild.nullNode && node.rightChild.nullNode) {
                node.nullNode = true;
            }
            else if (node.leftChild.nullNode || node.rightChild.nullNode) {
                BSNode oneChild;
                if (node.leftChild.nullNode) {
                    oneChild = node.rightChild;
                }
                else {
                    oneChild = node.leftChild;
                }
                final BSNode subtree = new BSNode(oneChild, node);
                Screen.canvas.awaitMovingCompletion();
                subtree.removeAndCopy(node);
            }
            else {
                BSNode successor = node.rightChild;
                if (!Screen.panel.isInteractive()) {
                    Screen.canvas.highlight(new DrawableLine(new Position(node.x, node.y), new Position(successor.x, successor.y), 3), Color.white, false);
                }
                while (!successor.leftChild.nullNode) {
                    final BSNode previous = successor;
                    successor = successor.leftChild;
                    if (!Screen.panel.isInteractive()) {
                        Screen.canvas.highlight(new DrawableLine(new Position(previous.x, previous.y), new Position(successor.x, successor.y), 3), Color.white, false);
                    }
                }
                final int successorValue = successor.value;
                nodeLabel.setString(String.valueOf(successor.value));
                nodeLabel.reposition(this.NodeStringPosition(successor.x, successor.y));
                if (Screen.panel.isInteractive()) {
                    Screen.canvas.acceptClick(nodeLabel);
                    Screen.canvas.awaitClick("Click Value To Move", true, false);
                    Screen.answer.clearAnswer();
                }
                else {
                    Screen.controls.nextStep(true, false);
                }
                successor.moveValue(node, nodeLabel);
                Screen.canvas.awaitMovingCompletion();
                nodeLabel.hide();
                node.value = successorValue;
                if (successor.rightChild.nullNode) {
                    successor.nullNode = true;
                }
                else {
                    final BSNode subtree2 = new BSNode(successor.rightChild, successor);
                    Screen.canvas.awaitMovingCompletion();
                    subtree2.removeAndCopy(successor);
                }
                Screen.canvas.clear();
            }
        }
    }
    
    public void draw(final Graphics g) {
        if (Screen.canvas.showClickables() && BSNode.inserting) {
            this.drawClickable(g);
        }
        super.draw(g);
    }
    
    public void drawClickable(final Graphics g) {
        if (super.nullNode) {
            final Position mouse = Screen.canvas.getMousePosition();
            if (mouse != null && this.inside(mouse)) {
                g.setColor(Color.white);
                g.fillOval(super.x - 15, super.y - 15, 30, 30);
            }
            return;
        }
        if (this.left() != null) {
            ((BSNode)this.left()).drawClickable(g);
        }
        if (this.right() != null) {
            ((BSNode)this.right()).drawClickable(g);
        }
    }
    
    public void find(final int value, final DrawableCircle tracer) {
        BSNode.inserting = false;
        BSNode node = this.retrieve(value, tracer, true, false);
        Screen.answer.twoButtons("Click One Of Following", "Value Found", "Not In Tree", "The Value Was Found", "Value Is Not In Tree", node.nullNode ^ true, true);
        node = this.retrieve(value, tracer, false, true);
    }
    
    public int getValue() {
        final int one_in_ten = Math.abs(DrawableNode.random.nextInt()) % 10;
        if (one_in_ten == 0 || this.values.size() == 0) {
            return Math.abs(DrawableNode.random.nextInt()) % 100;
        }
        final int choice = Math.abs(DrawableNode.random.nextInt()) % this.values.size();
        final int value = this.values.elementAt(choice);
        return value;
    }
    
    public void insert(final int value, final DrawableCircle tracer) {
        BSNode.inserting = true;
        final BSNode node = this.retrieve(value, tracer, false, false);
        if (node.nullNode && node.level < 6 && Screen.panel.isInteractive()) {
            Screen.canvas.acceptClick(node);
        }
        Screen.answer.twoButtons("Click Location For " + value + " Or", "Branch Full", "In Tree", "That Branch Is Full", "Value Already In Tree", node.level >= 6, node.nullNode ^ true);
        if (node.nullNode && node.level < 6) {
            node.value = value;
            node.nullNode = false;
            this.values.addElement(new Integer(value));
        }
    }
    
    public DrawableNode left() {
        return this.leftChild;
    }
    
    private void removeAndCopy(final BSNode copy) {
        copy.value = super.value;
        copy.nullNode = false;
        Screen.canvas.remove(this);
        if (this.leftChild != null) {
            this.leftChild.removeAndCopy(copy.leftChild);
        }
        else {
            copy.leftChild.nullNode = true;
        }
        if (this.rightChild != null) {
            this.rightChild.removeAndCopy(copy.rightChild);
        }
        else {
            copy.rightChild.nullNode = true;
        }
    }
    
    private BSNode retrieve(final int value, final DrawableCircle tracer, final boolean visit, final boolean recolorOnly) {
        if (super.nullNode) {
            return this;
        }
        if (!recolorOnly) {
            this.move("Click Next Node To Find " + value, visit, tracer);
            Screen.controls.nextStep(false, true);
        }
        if (visit || recolorOnly) {
            this.changeColor();
        }
        if (value < super.value) {
            return this.leftChild.retrieve(value, tracer, visit, recolorOnly);
        }
        if (value > super.value) {
            return this.rightChild.retrieve(value, tracer, visit, recolorOnly);
        }
        return this;
    }
    
    public DrawableNode right() {
        return this.rightChild;
    }
}
