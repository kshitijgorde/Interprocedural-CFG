// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Component;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CompUtil
{
    public static EmptyBorder border5;
    public static Border loweredBorder;
    
    public static JPanel createVerticalPanel(final boolean b) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setAlignmentY(0.0f);
        panel.setAlignmentX(0.0f);
        if (b) {
            panel.setBorder(CompUtil.loweredBorder);
        }
        return panel;
    }
    
    public static JPanel createHorizontalPanel(final boolean b) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setAlignmentY(0.0f);
        panel.setAlignmentX(0.0f);
        if (b) {
            panel.setBorder(CompUtil.loweredBorder);
        }
        return panel;
    }
    
    public static void makeCompactGrid(final Container container, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        SpringLayout springLayout;
        try {
            springLayout = (SpringLayout)container.getLayout();
        }
        catch (ClassCastException ex) {
            System.err.println("not SpringLayout.");
            return;
        }
        Spring x = Spring.constant(n3);
        for (int i = 0; i < n2; ++i) {
            Spring width = Spring.constant(0);
            for (int j = 0; j < n; ++j) {
                width = Spring.max(width, getConstraintsForCell(j, i, container, n2).getWidth());
            }
            for (int k = 0; k < n; ++k) {
                final SpringLayout.Constraints constraintsForCell = getConstraintsForCell(k, i, container, n2);
                constraintsForCell.setX(x);
                constraintsForCell.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(n5)));
        }
        Spring y = Spring.constant(n4);
        for (int l = 0; l < n; ++l) {
            Spring height = Spring.constant(0);
            for (int n7 = 0; n7 < n2; ++n7) {
                height = Spring.max(height, getConstraintsForCell(l, n7, container, n2).getHeight());
            }
            for (int n8 = 0; n8 < n2; ++n8) {
                final SpringLayout.Constraints constraintsForCell2 = getConstraintsForCell(l, n8, container, n2);
                constraintsForCell2.setY(y);
                constraintsForCell2.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(n6)));
        }
        final SpringLayout.Constraints constraints = springLayout.getConstraints(container);
        constraints.setConstraint("South", y);
        constraints.setConstraint("East", x);
    }
    
    private static SpringLayout.Constraints getConstraintsForCell(final int n, final int n2, final Container container, final int n3) {
        return ((SpringLayout)container.getLayout()).getConstraints(container.getComponent(n * n3 + n2));
    }
    
    static {
        CompUtil.border5 = new EmptyBorder(5, 5, 5, 5);
        CompUtil.loweredBorder = new CompoundBorder(new SoftBevelBorder(1), new EmptyBorder(5, 5, 5, 5));
    }
}
