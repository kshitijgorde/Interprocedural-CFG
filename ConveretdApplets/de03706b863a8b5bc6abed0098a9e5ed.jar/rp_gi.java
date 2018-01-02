import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.awt.Window;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Frame;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_gi extends rp_fG
{
    private int a;
    private JTextArea b;
    private JCheckBox a;
    public JTextArea a;
    public Vector a;
    public Vector b;
    private rp_fx a;
    private Frame a;
    
    public rp_gi(final Frame a, final rp_fx a2, final int a3) {
        super("");
        this.a = 0;
        this.a = null;
        this.b = null;
        this.a = a;
        this.a = a2;
        switch (this.a = a3) {
            case 0: {
                this.setTitle(rp_au.a("EmTit"));
                break;
            }
            case 1: {
                this.setTitle(rp_au.a("RITit"));
                break;
            }
        }
        this.a(rp_au.a.a());
        this.a();
        this.b();
    }
    
    protected final boolean b() {
        return false;
    }
    
    private static JLabel a(final String s) {
        final JLabel label;
        (label = new JLabel(rp_au.a(0, s))).setAlignmentX(0.5f);
        return label;
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JPanel panel3 = new JPanel(new BorderLayout());
        if (this.a == 0) {
            panel3.add(a("Em1"), "North");
        }
        panel3.add(a("EmTo"), "Center");
        rp_ap.a(this.b = new JTextArea(3, 50));
        this.b.setBorder(BorderFactory.createBevelBorder(1));
        if (this.a == 1) {
            this.b.setText(rp_au.a.a.a);
            this.b.setEditable(false);
            this.b.setBackground(rp_aJ.c);
        }
        else {
            this.b.setBackground(rp_aJ.l);
        }
        panel3.add(this.b, "South");
        panel2.add(panel3, "North");
        final JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(this.a = new JCheckBox(rp_au.a("EmCC")));
        panel2.add(panel4, "Center");
        final JPanel panel5;
        (panel5 = new JPanel(new BorderLayout())).add(a("EmPM"), "North");
        rp_ap.a(this.a = new JTextArea(3, 50));
        this.a.setBorder(BorderFactory.createBevelBorder(1));
        panel5.add(this.a, "South");
        panel2.add(panel5, "South");
        panel.add(panel2, "North");
        panel.add(panel2, "South");
    }
    
    public final boolean a() {
        switch (this.a) {
            case 0: {
                final Vector a2;
                final Vector a = a2 = a(this.b.getText());
                boolean b = false;
                Label_0177: {
                    if (a2 != null) {
                        for (int i = 0; i < a2.size(); ++i) {
                            final String s;
                            final int index = (s = a2.elementAt(i)).indexOf(64);
                            final int lastIndex = s.lastIndexOf(46);
                            if (index <= 0 || lastIndex <= 2 || index + 1 >= lastIndex) {
                                rp_bd.a(this.a, rp_au.a("wrn"), rp_au.a("ed1") + a2.elementAt(i), rp_au.a("cl"));
                                b = false;
                                break Label_0177;
                            }
                        }
                    }
                    b = true;
                }
                if (!b) {
                    this.b.requestFocus();
                    return false;
                }
                this.a = a;
                break;
            }
            case 1: {
                this.a = new Vector();
                break;
            }
        }
        final Vector<Object> b2 = new Vector<Object>();
        if (this.a.isSelected()) {
            final Vector a3 = rp_au.a.a.a;
            for (int j = 0; j < a3.size(); ++j) {
                b2.addElement(a3.elementAt(j));
            }
        }
        this.b = b2;
        final boolean f = rp_aJ.f;
        rp_aJ.f = true;
        final boolean g = rp_aJ.g;
        rp_aJ.g = true;
        switch (this.a) {
            case 0: {
                this.a.a(this);
                break;
            }
            case 1: {
                this.a.b(this);
                break;
            }
        }
        rp_aJ.f = f;
        rp_aJ.g = g;
        return true;
    }
    
    private static Vector a(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final BufferedReader bufferedReader = new BufferedReader(new StringReader(s));
        final Vector<String> vector = new Vector<String>();
        try {
            String line;
            while (null != (line = bufferedReader.readLine())) {
                vector.addElement(line);
            }
        }
        catch (IOException ex) {
            System.out.println("EmailDlg.parseAddresses:" + ex);
        }
        return vector;
    }
}
