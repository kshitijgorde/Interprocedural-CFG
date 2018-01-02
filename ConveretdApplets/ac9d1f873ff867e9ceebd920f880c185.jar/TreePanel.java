import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreePanel extends JPanel
{
    private final int MARGINS_XY = 30;
    private final int BIN_HEIGHT = 70;
    private final int BIN_WIDTH = 50;
    private final int BIN_SPACE = 50;
    private BinaryTreeNode _root;
    
    public TreePanel(final BinaryTreeNode root, final int min_width) {
        final int height = root.getDepth();
        int size_w = 60 + (int)Math.pow(2.0, height) * 50 + (int)Math.pow(2.0, height - 1) * 50;
        final int size_h = 60 + height * 70;
        this._root = root;
        if (size_w < min_width) {
            size_w = min_width;
        }
        this.setLayout(null);
        this.setSize(size_w, size_h);
        this.setPreferredSize(new Dimension(size_w, size_h));
        this.setMinimumSize(new Dimension(size_w, size_h));
        final LabelNode rootNode = (LabelNode)this._root.getUserObject();
        if (rootNode == null || rootNode.toString().length() == 0) {
            return;
        }
        rootNode.setLocation((this.getWidth() - rootNode.getWidth()) / 2, 30);
        rootNode.setToolTipText("" + this._root.toDouble());
        this.add(rootNode);
        if (height <= 0) {
            return;
        }
        this.pLayoutNodes(root, height);
    }
    
    private void pLayoutNodes(final BinaryTreeNode node, final int height) {
        final LabelNode parent = (LabelNode)node.getUserObject();
        if (node == null || node.toString().length() == 0) {
            return;
        }
        parent.setToolTipText("" + node.toDouble());
        final int level = node.getLevel() + 1;
        final int ypos = parent.getY() + 70;
        final int xdelta = (int)(Math.pow(2.0, height - 2) / Math.pow(2.0, level) * 100.0);
        final LabelNode leftNode = (node.getLeftChild() == null) ? null : ((LabelNode)node.getLeftChild().getUserObject());
        if (leftNode != null && leftNode.toString().length() >= 0) {
            final int xpos = parent.getX() + parent.getWidth() / 2 - leftNode.getWidth() / 2 - ((level < height) ? xdelta : 25);
            leftNode.setLocation(xpos, ypos);
            this.add(leftNode);
            final LineNode line = new LineNode(parent, leftNode, true);
            this.add(line);
            this.pLayoutNodes(node.getLeftChild(), height);
        }
        final LabelNode rightNode = (node.getRightChild() == null) ? null : ((LabelNode)node.getRightChild().getUserObject());
        if (rightNode != null && rightNode.toString().length() >= 0) {
            final int xpos2 = parent.getX() + parent.getWidth() / 2 - rightNode.getWidth() / 2 + ((level < height) ? xdelta : 25);
            rightNode.setLocation(xpos2, ypos);
            this.add(rightNode);
            final LineNode line2 = new LineNode(parent, rightNode, false);
            this.add(line2);
            this.pLayoutNodes(node.getRightChild(), height);
        }
    }
}
