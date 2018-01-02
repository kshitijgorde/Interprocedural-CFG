import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aZ extends rp_fG
{
    private String[] a;
    private rp_et a;
    private JComboBox a;
    private JComboBox b;
    private AbstractButton a;
    private AbstractButton b;
    private AbstractButton c;
    private AbstractButton d;
    private AbstractButton e;
    private AbstractButton f;
    private JTextArea a;
    JButton a;
    
    public rp_aZ(final rp_et a) {
        super(rp_au.a("tx1"));
        this.a = new String[] { "8", "10", "11", "12", "14", "16", "18", "20", "24", "30", "36", "40", "48", "60", "72" };
        this.a(rp_au.a.a());
        this.a = a;
        this.a();
        this.b();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        final JPanel panel2;
        (panel2 = new JPanel()).setLayout(new BoxLayout(panel2, 0));
        this.a = new JComboBox();
        final String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < availableFontFamilyNames.length; ++i) {
            if (!availableFontFamilyNames[i].equals("padmaa") && !availableFontFamilyNames[i].equals("Rekha") && availableFontFamilyNames[i].indexOf("Lohit") <= -1 && availableFontFamilyNames[i].indexOf("aakar") <= -1) {
                this.a.addItem(availableFontFamilyNames[i]);
            }
        }
        this.a.setMaximumSize(new Dimension(130, 22));
        this.a.setRenderer(rp_au.a().a);
        this.a.setSelectedItem(this.a.a.b);
        this.a.setToolTipText(rp_aJ.a(rp_au.a.a().a(0, "txN")));
        panel2.add(this.a);
        panel2.add(Box.createHorizontalStrut(5));
        this.b = new JComboBox();
        for (int j = 0; j < this.a.length; ++j) {
            this.b.addItem(this.a[j]);
        }
        this.b.setMaximumSize(new Dimension(50, 22));
        this.b.setRenderer(rp_au.a().a);
        this.b.setSelectedItem(Integer.toString(this.a.a.a));
        this.b.setToolTipText(rp_aJ.a(rp_au.a.a().a(0, "txS")));
        panel2.add(this.b);
        panel2.add(Box.createHorizontalStrut(5));
        (this.a = new JButton("")).setBackground(this.a.a.a);
        this.a.setMaximumSize(new Dimension(20, 20));
        this.a.setBorderPainted(true);
        this.a.setToolTipText(rp_aJ.a(rp_au.a.a().a(0, "txO")));
        this.a.addActionListener(new rp_fJ(this));
        panel2.add(this.a);
        panel2.add(Box.createHorizontalStrut(5));
        (this.a = this.a("", "bold.png", "bolds.png", "txB", true)).setSelected(this.a.a.b);
        panel2.add(this.a);
        (this.b = this.a("", "ital.png", "itals.png", "txI", true)).setSelected(this.a.a.a);
        panel2.add(this.b);
        (this.c = this.a("", "under.png", "unders.png", "txU", true)).setSelected(this.a.a.c);
        panel2.add(this.c);
        panel2.add(new rp_j(7));
        (this.d = this.a("", "left.png", "lefts.png", "txL", true)).setSelected(this.a.a.b == 0);
        panel2.add(this.d);
        (this.e = this.a("", "center.png", "centers.png", "txC", true)).setSelected(this.a.a.b == 1);
        panel2.add(this.e);
        (this.f = this.a("", "right.png", "rights.png", "txR", true)).setSelected(this.a.a.b == 2);
        panel2.add(this.f);
        final ButtonGroup buttonGroup;
        (buttonGroup = new ButtonGroup()).add(this.d);
        buttonGroup.add(this.e);
        buttonGroup.add(this.f);
        panel.add(panel2, "North");
        this.a = new JTextArea(this.a.a().a, 7, 35);
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(this.a, 22, 30)).setBorder(BorderFactory.createLoweredBevelBorder());
        panel.add(scrollPane, "Center");
    }
    
    private AbstractButton a(final String s, String a, final String s2, final String s3, final boolean b) {
        AbstractButton abstractButton = null;
        if (a != null) {
            abstractButton = new JToggleButton(a(a));
        }
        if (abstractButton == null && s != null) {
            final rp_fb a2 = rp_au.a.a();
            a = s;
            a = a2.a(0, a);
            abstractButton = new JToggleButton(a);
        }
        final AbstractButton abstractButton2 = abstractButton;
        final rp_fb a3 = rp_au.a.a();
        a = s3;
        abstractButton2.setToolTipText(rp_aJ.a(a3.a(0, a)));
        abstractButton.setBorderPainted(false);
        abstractButton.setFocusPainted(false);
        abstractButton.setFont(rp_aJ.a);
        abstractButton.setContentAreaFilled(false);
        if (s2 != null) {
            abstractButton.setSelectedIcon(a(s2));
        }
        final Insets margin;
        final Insets insets = margin = abstractButton.getMargin();
        insets.left /= 3;
        final Insets insets2 = margin;
        insets2.right /= 3;
        margin.left = 0;
        margin.right = 0;
        abstractButton.setMargin(margin);
        return abstractButton;
    }
    
    private static Icon a(final String s) {
        final Image a;
        if (s != null && (a = rp_au.a.a(s)) != null) {
            return new ImageIcon(a);
        }
        return null;
    }
    
    public final boolean a() {
        final rp_bf rp_bf;
        (rp_bf = new rp_bf()).b = (String)this.a.getSelectedItem();
        rp_bf.a = Integer.parseInt((String)this.b.getSelectedItem());
        rp_bf.a = this.a.getText();
        rp_bf.a = this.a.getBackground();
        rp_bf.b = this.a.isSelected();
        rp_bf.a = this.b.isSelected();
        rp_bf.c = this.c.isSelected();
        if (this.d.isSelected()) {
            rp_bf.b = 0;
        }
        if (this.e.isSelected()) {
            rp_bf.b = 1;
        }
        if (this.f.isSelected()) {
            rp_bf.b = 2;
        }
        final rp_bf rp_bf3;
        final rp_bf rp_bf2 = rp_bf3 = rp_bf;
        final rp_bf a = this.a.a();
        final rp_bf rp_bf4 = rp_bf2;
        if (!rp_bf2.a.equals(a.a) || !rp_bf4.a.equals(a.a) || rp_bf4.a != a.a || rp_bf4.b != a.b || rp_bf4.c != a.c || !rp_bf4.b.equals(a.b) || rp_bf4.a != a.a || rp_bf4.b != a.b) {
            rp_au.a.a.a(new rp_gm(this.a, rp_bf3));
        }
        return true;
    }
}
