import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;

// 
// Decompiled by Procyon v0.5.30
// 

public class BinaryTreeNode extends DefaultMutableTreeNode
{
    public BinaryTreeNode() {
    }
    
    public BinaryTreeNode(final Object node) {
        super(node);
    }
    
    public BinaryTreeNode(final Object node, final BinaryTreeNode left, final BinaryTreeNode right) {
        super(node);
        this.add(left);
        this.add(right);
    }
    
    public void setLeftChild(final Object left) {
        if (this.children == null || this.children.size() == 0) {
            this.add(new BinaryTreeNode(left));
            this.add(new BinaryTreeNode(new String()));
        }
        else {
            this.children.setElementAt(new BinaryTreeNode(left), 0);
        }
    }
    
    public BinaryTreeNode getLeftChild() {
        if (this.children == null || this.children.size() < 1) {
            return null;
        }
        return this.children.elementAt(0);
    }
    
    public void setRightChild(final Object right) {
        if (this.children == null || this.children.size() == 0) {
            this.add(new BinaryTreeNode(new String()));
            this.add(new BinaryTreeNode(right));
        }
        else if (this.children.size() == 1) {
            this.add(new BinaryTreeNode(right));
        }
        else {
            this.children.setElementAt(new BinaryTreeNode(right), 1);
        }
    }
    
    public BinaryTreeNode getRightChild() {
        if (this.children == null || this.children.size() < 2) {
            return null;
        }
        return this.children.elementAt(1);
    }
    
    public String toString() {
        return (this.getUserObject() == null) ? "" : this.getUserObject().toString();
    }
    
    public double toDouble() {
        final String strValue = (this.getUserObject() == null) ? "" : this.getUserObject().toString();
        final double value = 0.0;
        if (strValue.length() == 0) {
            return 0.0;
        }
        if (strValue.length() == 1 && !Character.isDigit(strValue.charAt(0))) {
            final double x = (this.getLeftChild() == null) ? 0.0 : this.getLeftChild().toDouble();
            final double y = (this.getRightChild() == null) ? 0.0 : this.getRightChild().toDouble();
            switch (strValue.charAt(0)) {
                case '+': {
                    return x + y;
                }
                case '-': {
                    return x - y;
                }
                case '*': {
                    return x * y;
                }
                case '/': {
                    return x / y;
                }
                default: {
                    return Double.NaN;
                }
            }
        }
        else {
            try {
                return Double.parseDouble(strValue);
            }
            catch (NumberFormatException e) {
                return Double.NaN;
            }
        }
    }
}
