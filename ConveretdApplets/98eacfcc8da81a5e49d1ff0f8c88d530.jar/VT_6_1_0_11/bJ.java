// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import com.hw.client.util.c;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

public final class bJ extends cS
{
    private JButton a;
    private JPanel b;
    private JPanel c;
    private boolean d;
    private int e;
    
    public bJ(final boolean d) {
        super("/images/common/bg_buttonbar_left.png");
        this.e = -1;
        this.d = d;
        this.setOpaque(true);
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        this.b = new cS("/images/common/bg_buttonbar_left.png");
        final FlowLayout layout;
        (layout = new FlowLayout()).setHgap(6);
        layout.setAlignment(0);
        this.b.setLayout(layout);
        final cS cs;
        com.hw.client.util.c.a(cs = new cS("/images/common/bg_buttonbar_separator.png"), new Dimension(cs.c().getIconWidth(), cs.c().getIconHeight()));
        this.c = new cS("/images/common/bg_buttonbar_right.png");
        final FlowLayout layout2;
        (layout2 = new FlowLayout()).setHgap(6);
        layout2.setAlignment(0);
        this.c.setLayout(layout2);
        this.setLayout(new GridBagLayout());
        this.add(this.b, new GridBagConstraints(++this.e, 0, 1, 1, 0.0, 1.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panel, new GridBagConstraints(++this.e, 0, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        if (d) {
            this.add(cs, new GridBagConstraints(++this.e, 0, 1, 1, 0.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
            this.add(this.c, new GridBagConstraints(++this.e, 0, 1, 1, 0.0, 1.0, 13, 2, new Insets(0, 0, 0, 0), 0, 0));
        }
    }
    
    public final void a() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints(++this.e, 0, 1, 1, 0.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0);
        new JPanel().setOpaque(false);
        JPanel panel;
        if (this.d) {
            panel = new cS("/images/common/bg_buttonbar_right.png");
        }
        else {
            (panel = new JPanel()).setOpaque(false);
        }
        panel.setLayout(new GridBagLayout());
        panel.add(this.b(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 12, 2, new Insets(6, 0, 0, 4), 0, 0));
        panel.add(com.hw.client.util.c.a(), new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
        panel.setPreferredSize(new Dimension(26, this.c().getIconHeight()));
        panel.setMaximumSize(new Dimension(26, this.c().getIconHeight()));
        panel.setMinimumSize(new Dimension(0, 0));
        this.add(panel, gridBagConstraints);
    }
    
    public final void a(final JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        this.b.add(button);
        this.revalidate();
    }
    
    public final void b(final JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        this.c.add(button);
        this.revalidate();
    }
    
    public final JButton b() {
        if (this.a == null) {
            final cP a;
            (a = new cP()).setIcon(com.hw.client.util.c.a("/images/common/btn_detach.png"));
            a.setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_detach_rollover.png"));
            a.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_detach_pressed.png"));
            h.a(a);
            com.hw.client.util.c.a(a);
            a.setActionCommand("ACTION_DETACH");
            this.a = a;
        }
        return this.a;
    }
}
