import java.awt.Window;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dg extends JPanel
{
    boolean a;
    JLabel[] a;
    JTextField[] a;
    JTextField[] b;
    rp_cD a;
    rp_dW a;
    
    rp_dg(final rp_dW a) {
        this.a = true;
        this.a = new JLabel[6];
        this.a = new JTextField[6];
        this.b = new JTextField[6];
        this.a = null;
        this.a = rp_au.a.a().a();
        this.a = a;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        final JPanel panel = new JPanel();
        if (this.a) {
            panel.setLayout(new GridLayout(7, 1));
            final JPanel panel2;
            (panel2 = new JPanel()).add(new JLabel(""));
            panel2.add(new JLabel(rp_au.a("ft") + "  "));
            panel2.add(new JLabel(rp_au.a("Ins")));
            panel.add(panel2);
            for (int i = 0; i < 6; ++i) {
                final JPanel panel3 = new JPanel();
                panel3.add(this.a[i] = new JLabel(rp_cD.a(i)));
                (this.a[i] = new JTextField(4)).setBorder(BorderFactory.createBevelBorder(1));
                this.a[i].setBackground(rp_aJ.l);
                panel3.add(this.a[i]);
                (this.b[i] = new JTextField(4)).setBorder(BorderFactory.createBevelBorder(1));
                this.b[i].setBackground(rp_aJ.l);
                panel3.add(this.b[i]);
                panel.add(panel3);
            }
        }
        else {
            panel.setLayout(new GridLayout(6, 1));
            for (int j = 0; j < 6; ++j) {
                final JPanel panel4 = new JPanel();
                panel4.add(this.a[j] = new JLabel(rp_cD.a(j)));
                (this.a[j] = new JTextField(10)).setBorder(BorderFactory.createBevelBorder(1));
                this.a[j].setBackground(rp_aJ.l);
                panel4.add(this.a[j]);
                panel.add(panel4);
            }
        }
        this.add(panel, "East");
        this.add(new JLabel("<html>" + rp_au.a("RWN2") + rp_au.a("RWN") + "</html>"), "South");
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(360, 390);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        this.a.repaint();
    }
    
    private int a(int a) {
        if (this.a) {
            final String text = this.a[a].getText();
            final String text2 = this.b[a].getText();
            try {
                a = rp_C.a(text);
                return 2540 * (a * 12 + rp_C.a(text2));
            }
            catch (NumberFormatException ex) {
                throw new rp_fS();
            }
        }
        return rp_au.a.a().a(this.a[a].getText());
    }
    
    final boolean a() {
        int[] a;
        try {
            a = this.a(true);
        }
        catch (rp_fS rp_fS) {
            System.out.println("RoomShapePage2.checkEntries(): UnitParserException");
            return false;
        }
        for (int i = 0; i < this.a.b(); ++i) {
            if (a[i] <= 0) {
                return false;
            }
        }
        final rp_dg rp_dg = this;
        final int[] array = a;
        this = rp_dg;
        int n2 = 0;
        int n = 0;
        Label_0292: {
            switch (rp_dg.a.a()) {
                case 0: {
                    if (array[0] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[1] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    break;
                }
                case 1: {
                    if (array[2] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[1] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[0] >= array[2]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    if (array[1] <= array[3]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    break;
                }
                case 2: {
                    if (array[2] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[1] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[0] + array[4] >= array[2]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    if (array[5] >= array[1]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    if (array[3] >= array[1]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    break;
                }
                case 3: {
                    if (array[0] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[4] + array[5] > 7620000) {
                        n = (n2 = 1);
                        break Label_0292;
                    }
                    if (array[2] + array[3] >= array[0]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    if (array[1] >= array[4] + array[5]) {
                        n = (n2 = 2);
                        break Label_0292;
                    }
                    break;
                }
            }
            n = (n2 = 0);
        }
        final int n3 = n2;
        if (n != 0) {
            String a2 = null;
            String s = null;
            if (n3 == 1) {
                a2 = "Room Size Error";
                final rp_cR a3;
                (a3 = rp_au.a.a()).a(true);
                s = rp_au.a("rs1") + "  X: " + a3.a(7620000) + "\n  Y: " + a3.a(7620000);
            }
            if (n3 == 2) {
                a2 = rp_au.a("rs2");
                s = rp_au.a("rs3");
            }
            rp_bd.a(rp_au.a.a().a(), a2, s, rp_au.a("cl"));
            return false;
        }
        return true;
    }
    
    public final rp_dW a() {
        int[] a;
        try {
            a = this.a(false);
        }
        catch (rp_fS rp_fS) {
            System.out.println("RoomShapePage2.checkEntries(): UnitParserException");
            return null;
        }
        final rp_dW rp_dW;
        (rp_dW = new rp_dW()).a = rp_cD.a;
        rp_dW.b = this.a.a();
        rp_dW.c = 14080;
        rp_dW.a = a;
        return rp_dW;
    }
    
    private int[] a(final boolean b) {
        final int[] array = new int[this.a.b()];
        for (int i = 0; i < this.a.b(); ++i) {
            array[i] = -1;
            try {
                array[i] = this.a(i);
            }
            catch (rp_fS rp_fS) {
                if (b) {
                    this.a[i].selectAll();
                    this.a[i].requestFocus();
                    rp_bd.a(rp_au.a.a().a(), rp_au.a("rs4"), rp_au.a("rs5") + " " + this.a[i].getText(), rp_au.a("cl"));
                }
                throw rp_fS;
            }
        }
        return array;
    }
}
