import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_df extends JPanel implements ActionListener, MouseListener
{
    private JPanel a;
    private rp_dg a;
    private JComboBox a;
    private rp_al a;
    int a;
    
    public rp_df(final rp_al a, final rp_au rp_au, final rp_dg a2, final rp_dW rp_dW) {
        this.a = new JPanel();
        this.a = null;
        this.a = 0;
        this.a = a;
        this.a = a2;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.a.setLayout(new GridLayout(2, 2, 0, 0));
        final rp_cD rp_cD;
        (rp_cD = new rp_cD(0, false)).addMouseListener(this);
        this.a.add(rp_cD);
        this.a(rp_cD);
        final rp_cD rp_cD2;
        (rp_cD2 = new rp_cD(1, false)).addMouseListener(this);
        this.a.add(rp_cD2);
        final rp_cD rp_cD3;
        (rp_cD3 = new rp_cD(2, false)).addMouseListener(this);
        this.a.add(rp_cD3);
        final rp_cD rp_cD4;
        (rp_cD4 = new rp_cD(3, false)).addMouseListener(this);
        this.a.add(rp_cD4);
        this.add(this.a, "Center");
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("<html>" + rp_au.a("RWN1") + rp_au.a("RWN") + "</html>"), "North");
        final JPanel panel2 = new JPanel();
        final rp_fO rp_fO;
        (rp_fO = new rp_fO(rp_au.a("Rot"))).addActionListener(new rp_fN(this));
        panel2.add(rp_fO);
        panel.add(panel2, "Center");
        this.add(panel, "South");
        if (rp_dW != null) {
            final int a3 = rp_dW.a;
            this.a = a3;
            rp_cD.a = a3;
            this.a(rp_dW.b);
        }
        this.validate();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(360, 390);
    }
    
    private void a(final int n) {
        final Component[] components = this.a.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final rp_cD rp_cD;
            if ((rp_cD = (rp_cD)components[i]).a() == n) {
                this.a(rp_cD);
            }
        }
    }
    
    private void a(final rp_cD rp_cD) {
        final Component[] components = this.a.getComponents();
        for (int i = 0; i < components.length; ++i) {
            ((rp_cD)components[i]).a(false);
        }
        rp_cD.a(true);
        final rp_dg a = this.a;
        final int a2 = rp_cD.a();
        final rp_dg rp_dg = a;
        if (a.a != null) {
            rp_dg.remove(rp_dg.a);
        }
        rp_dg.add(rp_dg.a = new rp_cD(a2, true), "Center");
        final rp_cR a3;
        (a3 = rp_au.a.a()).a(true);
        for (int j = 0; j < 6; ++j) {
            if (j < rp_dg.a.b()) {
                int n;
                if (rp_dg.a != null && a2 == rp_dg.a.b) {
                    n = rp_dg.a.a[j];
                }
                else {
                    n = rp_au.a.a(a3.a(), a2)[j];
                }
                final rp_dg rp_dg2 = rp_dg;
                final int n2 = j;
                final int n3 = n;
                final int n4 = n2;
                final rp_dg rp_dg3 = rp_dg2;
                final rp_cR a4 = rp_au.a.a();
                if (rp_dg3.a) {
                    final boolean b = a4.b(false);
                    final rp_eE a5 = a4.a(n3);
                    rp_dg3.a[n4].setText("" + a5.a);
                    rp_dg3.b[n4].setText("" + a5.b);
                    a4.b(b);
                }
                else {
                    rp_dg3.a[n4].setText(a4.a(n3));
                }
                rp_dg.a[j].setVisible(true);
                rp_dg.a[j].setVisible(true);
                rp_dg.a[j].setBackground(rp_dg.a[0].getBackground());
                if (rp_dg.a) {
                    rp_dg.b[j].setVisible(true);
                    rp_dg.b[j].setBackground(rp_dg.b[0].getBackground());
                }
            }
            else {
                rp_dg.a[j].setVisible(false);
                rp_dg.a[j].setVisible(false);
                rp_dg.a[j].setText("");
                if (rp_dg.a) {
                    rp_dg.b[j].setVisible(false);
                    rp_dg.b[j].setText("");
                }
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        this.a();
    }
    
    void a() {
        final Component[] components = this.a.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].repaint();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        actionEvent.getActionCommand().equals("ld");
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a((rp_cD)mouseEvent.getSource());
        this.a();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
