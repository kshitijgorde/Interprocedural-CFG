import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

class IconCellRenderer extends JLabel implements TreeCellRenderer
{
    protected Color m_textSelectionColor;
    protected Color m_textNonSelectionColor;
    protected Color m_bkSelectionColor;
    protected Color m_bkNonSelectionColor;
    protected Color m_borderSelectionColor;
    protected boolean m_selected;
    
    public IconCellRenderer() {
        this.m_textSelectionColor = UIManager.getColor("Tree.selectionForeground");
        this.m_textNonSelectionColor = UIManager.getColor("Tree.textForeground");
        this.m_bkSelectionColor = UIManager.getColor("Tree.selectionBackground");
        this.m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
        this.m_borderSelectionColor = UIManager.getColor("Tree.selectionBorderColor");
        this.setOpaque(false);
    }
    
    public Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean selected, final boolean b, final boolean b2, final int n, final boolean b3) {
        final Object userObject = ((DefaultMutableTreeNode)o).getUserObject();
        this.setText(userObject.toString());
        if (userObject instanceof Boolean) {
            this.setText("Retrieving data...");
        }
        if (userObject instanceof IconData) {
            final IconData iconData = (IconData)userObject;
            if (b) {
                this.setIcon(iconData.getExpandedIcon());
            }
            else {
                this.setIcon(iconData.getIcon());
            }
        }
        else {
            this.setIcon(null);
        }
        this.setFont(tree.getFont());
        this.setForeground(selected ? this.m_textSelectionColor : this.m_textNonSelectionColor);
        this.setBackground(selected ? this.m_bkSelectionColor : this.m_bkNonSelectionColor);
        this.m_selected = selected;
        return this;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Color background = this.getBackground();
        final Icon icon = this.getIcon();
        graphics.setColor(background);
        int n = 0;
        if (icon != null && this.getText() != null) {
            n = icon.getIconWidth() + this.getIconTextGap();
        }
        graphics.fillRect(n, 0, this.getWidth() - 1 - n, this.getHeight() - 1);
        if (this.m_selected) {
            graphics.setColor(this.m_borderSelectionColor);
            graphics.drawRect(n, 0, this.getWidth() - 1 - n, this.getHeight() - 1);
        }
        super.paintComponent(graphics);
    }
}
