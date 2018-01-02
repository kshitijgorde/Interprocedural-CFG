import java.awt.event.ComponentListener;
import java.awt.Window;
import java.util.Vector;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Cursor;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComponent;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_fY extends rp_ae implements ItemListener
{
    private static ImageIcon a;
    private rp_r a;
    private rp_X a;
    private rp_bH a;
    private JPanel a;
    
    public rp_fY(final rp_aJ rp_aJ) {
        super(rp_aJ);
        this.a = null;
        this.a = null;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItem() != null && itemEvent.getItem() instanceof rp_ao) {
            final rp_ao rp_ao = (rp_ao)itemEvent.getItem();
            JComponent a = this.a;
            if (rp_ao.a != null) {
                a = (JComponent)rp_ao.a;
            }
            this.a.removeAll();
            this.a.add(a, "Center");
            this.a.repaint();
            this.invalidate();
        }
    }
    
    public final void a(final rp_dl rp_dl, final int n, final int n2) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public final void setVisible(final boolean visible) {
        if (visible && this.a == null) {
            this.setLayout(new BorderLayout());
            final JButton button;
            (button = new JButton(rp_fY.a)).setPreferredSize(new Dimension(20, 20));
            button.setToolTipText(rp_aJ.a(this.a.a().a(0, "sr2")));
            button.addActionListener(new rp_cj(this));
            final rp_ao rp_ao = new rp_ao(this.a.a().a(0, "sr3"), null);
            (this.a = new rp_X()).b(rp_ao);
            final JScrollPane scrollPane;
            (scrollPane = new JScrollPane(this.a)).setBorder(null);
            (this.a = new rp_r(scrollPane, this.a)).setBackground(rp_aJ.o);
            this.a.addItemListener(this);
            final JPanel panel;
            (panel = new JPanel(new BorderLayout())).add(button, "West");
            panel.add(this.a, "Center");
            this.add(panel, "North");
            this.a = new JPanel(new BorderLayout());
            this.a = new rp_bH(this);
            final rp_bH a;
            (a = this.a).setLayout(new BorderLayout());
            a.setBackground(rp_aJ.t);
            a.setCursor(Cursor.getPredefinedCursor(0));
            final Box verticalBox;
            (verticalBox = Box.createVerticalBox()).setBackground(rp_aJ.t);
            verticalBox.setBorder(BorderFactory.createLineBorder(rp_aJ.t, 6));
            verticalBox.add(Box.createVerticalStrut(6));
            final JPanel panel2;
            (panel2 = new JPanel(new BorderLayout())).setBackground(rp_aJ.t);
            panel2.add(rp_bH.a("Mfg", true), "West");
            final rp_bH rp_bH = a;
            final rp_bH rp_bH2 = a;
            final rp_eA a2 = rp_ae.a(6);
            final JComboBox a3;
            (a3 = new JComboBox()).setEditable(false);
            a3.setFont(rp_aJ.a);
            a3.setRenderer(rp_au.a().a);
            rp_bH2.a(a3, a2);
            rp_bH.a = a3;
            panel2.add(a.a, "Center");
            verticalBox.add(panel2);
            final JPanel panel3;
            (panel3 = new JPanel()).setBackground(rp_aJ.t);
            panel3.add(rp_bH.a("SKU", true), "West");
            (a.a = new JTextField(12)).setBackground(rp_aJ.l);
            panel3.add(a.a);
            panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, rp_aJ.q));
            verticalBox.add(panel3);
            verticalBox.add(Box.createVerticalStrut(10));
            final JPanel panel4;
            (panel4 = new JPanel()).setBackground(rp_aJ.t);
            final JPanel panel5;
            (panel5 = new JPanel(new GridLayout(2, 1, 0, 0))).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panel5.setBackground(rp_aJ.t);
            a.a = new ButtonGroup();
            (a.b = new JRadioButton(rp_au.a("AS_ST"), true)).setBackground(rp_aJ.t);
            a.b.setFont(rp_aJ.a);
            a.a.add(a.b);
            panel5.add(a.b);
            (a.a = new JRadioButton(rp_au.a("AS_LT"), false)).setBackground(rp_aJ.t);
            a.a.setFont(rp_aJ.a);
            a.a.add(a.a);
            panel5.add(a.a);
            panel4.add(panel5);
            verticalBox.add(panel4);
            final JPanel panel6;
            (panel6 = new JPanel()).setBackground(rp_aJ.t);
            final JPanel panel7;
            (panel7 = new JPanel(new GridLayout(3, 3, 4, 4))).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panel7.setBackground(rp_aJ.t);
            panel7.add(rp_bH.a("Wd", false));
            (a.a = new JSpinner(rp_bH.a())).setBorder(BorderFactory.createBevelBorder(1));
            a.a.setBackground(rp_aJ.l);
            a.a.setEditor(new JSpinner.NumberEditor(a.a));
            panel7.add(a.a);
            panel7.add(rp_bH.a("ins", false));
            panel7.add(rp_bH.a("Dd", false));
            (a.b = new JSpinner(rp_bH.a())).setBorder(BorderFactory.createBevelBorder(1));
            a.b.setBackground(rp_aJ.l);
            a.b.setEditor(new JSpinner.NumberEditor(a.b));
            panel7.add(a.b);
            panel7.add(rp_bH.a("ins", false));
            panel7.add(rp_bH.a("Hd", false));
            (a.c = new JSpinner(rp_bH.a())).setBorder(BorderFactory.createBevelBorder(1));
            a.c.setBackground(rp_aJ.l);
            a.c.setEditor(new JSpinner.NumberEditor(a.c));
            panel7.add(a.c);
            panel7.add(rp_bH.a("ins", false));
            panel6.add(panel7);
            verticalBox.add(panel6);
            verticalBox.add(Box.createVerticalStrut(10));
            final JPanel panel8;
            (panel8 = new JPanel()).setBackground(rp_aJ.t);
            (a.b = new rp_fO(rp_au.a("bt_rs"))).addActionListener(a);
            panel8.add(a.b);
            (a.a = new rp_fO(rp_au.a("bt_sr"))).addActionListener(a);
            panel8.add(a.a);
            verticalBox.add(panel8);
            a.add(verticalBox, "North");
            this.a.add(this.a, "Center");
            this.add(this.a, "Center");
            this.a.a(rp_ao);
        }
        super.setVisible(visible);
    }
    
    public final void a(final rp_k rp_k, final Object o) {
        if (o == null) {
            this.a.setCursor(Cursor.getPredefinedCursor(3));
            this.a.setEnabled(false);
        }
        rp_ae.a((o == null) ? this : o, new rp_aj(), rp_k);
    }
    
    public final void a(final Vector vector, final Dimension dimension, final Object o, final int n) {
        this.a.setCursor(Cursor.getPredefinedCursor(0));
        this.a.setEnabled(true);
        if (vector == null || vector.size() == 0) {
            final rp_fb a = this.a.a();
            rp_bd.a(null, a.a(0, "wrn"), a.a(0, "sr1"), a.a(0, "cl"));
            return;
        }
        if (!(o instanceof rp_k)) {
            return;
        }
        final rp_ck rp_ck;
        (rp_ck = new rp_ck(this.a, this, (rp_k)o, vector, n, dimension)).addComponentListener(new rp_cn(this, rp_ck));
        final rp_ao rp_ao = new rp_ao(rp_ck.toString(), rp_ck);
        final rp_ao b;
        final rp_ao rp_ao2 = b = this.a.b();
        final rp_ao a2 = rp_ao;
        final rp_ao rp_ao3 = rp_ao2;
        if (rp_ao2.a != null) {
            a2.a = rp_ao3.a;
        }
        rp_ao3.a = a2;
        this.a.b(b);
        this.a.a(rp_ao);
    }
    
    final void a() {
        final rp_ao b = this.a.b();
        final rp_ao a = this.a.a();
        if (b != a) {
            b.a(a);
            this.a.b(b);
            this.a.a(b);
        }
    }
    
    static {
        final ClassLoader classLoader = rp_eG.class.getClassLoader();
        try {
            rp_fY.a = new ImageIcon(classLoader.getResource("res/bt_del.png"));
        }
        catch (Exception ex) {
            System.out.println("EXC:" + ex);
        }
    }
}
