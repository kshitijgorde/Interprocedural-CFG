// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

import javax.swing.Icon;
import com.otnip.tools.IconFactory;
import com.otnip.irig106.chapter9.r.Data;
import java.awt.Font;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

class TMATSTreeCellRenderer extends DefaultTreeCellRenderer
{
    private static final long serialVersionUID = 0L;
    
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        final JLabel label = (JLabel)super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        final Object userObject = node.getUserObject();
        if (userObject instanceof TMATSTreeField) {
            try {
                final TMATSTreeField field = (TMATSTreeField)userObject;
                String string = field.textDisplay;
                if (field.fieldName != null) {
                    string = string + ":  " + field.object.getClass().getField(field.fieldName).get(field.object);
                }
                else {
                    Font font = label.getFont();
                    font = new Font(font.getName(), font.getStyle() | 0x1, font.getSize());
                    label.setFont(font);
                    if (field.object instanceof Data && field.textDisplay.equals("Data")) {
                        if (((Data)field.object).channelEnable.equals("T")) {
                            label.setIcon(IconFactory.getIcon("/IconExperience/icons/24/plain/keyboard2_cordless.png"));
                        }
                        else {
                            label.setIcon(IconFactory.getIcon("/IconExperience/icons/24/plain/keyboard2.png"));
                        }
                    }
                }
                label.setText(string);
                if (leaf) {
                    label.setIcon(IconFactory.getIcon("dot.png"));
                }
                if (field.iconPath != null) {
                    label.setIcon(IconFactory.getIcon(field.iconPath));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return label;
    }
}
