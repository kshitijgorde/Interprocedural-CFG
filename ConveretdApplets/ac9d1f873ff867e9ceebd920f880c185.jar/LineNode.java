import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineNode extends JComponent
{
    private LabelNode _nodeParent;
    private LabelNode _nodeChild;
    private boolean _left;
    
    public LineNode(final LabelNode nodeParent, final LabelNode nodeChild, final boolean left) {
        this.setOpaque(false);
        this._nodeParent = nodeParent;
        this._nodeChild = nodeChild;
        if (this._nodeParent == null || this._nodeChild == null) {
            return;
        }
        this._left = left;
        if (this._left) {
            this.setLocation(nodeChild.getX() + nodeChild.getWidth() / 2, nodeParent.getY() + nodeParent.getHeight() / 2);
        }
        else {
            this.setLocation(nodeParent.getX() + nodeParent.getWidth() / 2, nodeParent.getY() + nodeParent.getHeight() / 2);
        }
        final int width = Math.abs(nodeChild.getX() + nodeChild.getWidth() / 2 - (nodeParent.getX() + nodeParent.getWidth() / 2));
        final int height = Math.abs(nodeChild.getY() + nodeChild.getHeight() / 2 - (nodeParent.getY() + nodeParent.getHeight() / 2));
        this.setSize(width, height);
    }
    
    protected void paintComponent(final Graphics g) {
        if (this._nodeParent == null || this._nodeChild == null) {
            return;
        }
        g.setColor(Color.black);
        final int x1 = this._left ? 0 : this.getWidth();
        final int y1 = this.getHeight();
        final int x2 = this._left ? this.getWidth() : 0;
        final int y2 = 0;
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1 + 1, y1, x2 + 1, y2);
        g.drawLine(x1 - 1, y1, x2 - 1, y2);
    }
}
