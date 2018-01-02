// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Container;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.Icon;
import java.awt.Dimension;
import javax.swing.JPanel;

public class bD extends aM
{
    private JPanel a;
    
    public bD(final String title) {
        this.setClosable(true);
        this.setResizable(true);
        this.setIconifiable(false);
        this.setMaximizable(false);
        this.setMinimumSize(new Dimension(100, 100));
        this.setTitle(title);
        this.setDefaultCloseOperation(0);
        this.setOpaque(false);
        this.getContentPane().setBackground(h.b());
        this.setFrameIcon(null);
        this.setVisible(false);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
    }
    
    protected final JPanel f() {
        if (this.a == null) {
            final JPanel a;
            (a = new JPanel()).setOpaque(false);
            final FlowLayout layout;
            (layout = new FlowLayout()).setHgap(6);
            layout.setAlignment(0);
            a.setLayout(layout);
            this.a = a;
            this.b();
        }
        return this.a;
    }
    
    protected void b() {
    }
    
    public final JButton a(final String s, final String s2, final String s3, final String s4) {
        final JButton a = h.a(s, s2, s3, s4);
        this.f().add(a);
        return a;
    }
    
    protected static JPanel a(final LayoutManager layoutManager) {
        final JPanel panel;
        (panel = new JPanel(layoutManager)).setOpaque(false);
        return panel;
    }
    
    public void setVisible(final boolean b) {
        this.a(b, true);
    }
    
    public final void a(final boolean visible, final boolean b) {
        final Container parent = this.getParent();
        if (visible) {
            if (b && parent != null) {
                this.setSize(Math.min(Math.max(parent.getWidth() * 3 / 4, this.getMinimumSize().width), Math.min(parent.getWidth(), this.getMaximumSize().width)), Math.min(Math.max(parent.getHeight() * 3 / 4, this.getMinimumSize().height), Math.min(parent.getHeight(), this.getMaximumSize().height)));
            }
            else {
                final Dimension preferredSize = this.getPreferredSize();
                if (parent != null) {
                    this.setSize(Math.min(preferredSize.width, parent.getWidth()), Math.min(preferredSize.height, parent.getHeight()));
                }
                else {
                    this.setSize(preferredSize);
                }
            }
            this.revalidate();
            if (parent != null) {
                ca.a(parent, this);
            }
        }
        super.setVisible(visible);
        if (!visible && parent != null) {
            parent.requestFocus();
        }
    }
}
