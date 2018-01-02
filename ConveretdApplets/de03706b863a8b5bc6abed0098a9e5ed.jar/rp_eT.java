import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eT extends rp_fG implements ItemListener
{
    private rp_fx a;
    private String a;
    private JCheckBox a;
    private JCheckBox b;
    private JCheckBox c;
    private JCheckBox d;
    private JCheckBox e;
    private JTextField a;
    private JCheckBox f;
    private JCheckBox g;
    private ButtonGroup a;
    private JRadioButton a;
    private JRadioButton b;
    private JRadioButton c;
    private JTextField b;
    
    public rp_eT(final rp_fx a, final String a2) {
        super(rp_au.a(0, a2));
        this.a = a;
        this.a = a2;
        (this = this).a(rp_au.a.a());
        this.a();
        this.b();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        panel.add(a((this.a == "PrnOptSI") ? "PrSAA" : "PrnAA"));
        if (this.a == "PrnOptSI") {
            panel.add(a("PrSN"));
        }
        final rp_fw rp_fw = new rp_fw(rp_au.a("GAurl"), rp_au.a("PrnGA"));
        final JPanel panel2;
        (panel2 = new JPanel()).setAlignmentX(0.5f);
        panel2.add(rp_fw);
        panel.add(panel2);
        final JPanel panel3 = new JPanel(new GridLayout(1, 2, 8, 8));
        final JPanel panel4;
        (panel4 = new JPanel(new GridLayout(4, 1))).add(a("PrnRO"));
        panel4.add(this.a = new JCheckBox(rp_au.a("PrnGr"), rp_aJ.f));
        panel4.add(this.b = new JCheckBox(rp_au.a("PrnDim"), rp_aJ.g));
        panel3.add(panel4);
        final JPanel panel5;
        (panel5 = new JPanel(new GridLayout(4, 1))).add(a("PrnI"));
        if (this.a == "PrnOpt") {
            (this.c = new JCheckBox(rp_au.a("PrnIL"), rp_aJ.i)).addItemListener(this);
            panel5.add(this.c);
            panel5.add(this.d = new JCheckBox(rp_au.a("PrnSep"), rp_aJ.j));
        }
        panel5.add(this.e = new JCheckBox(rp_au.a("PrnIT"), rp_aJ.k));
        panel3.add(panel5);
        panel.add(panel3);
        if (this.a == "PrnOpt") {
            final JPanel panel6;
            (panel6 = new JPanel(new BorderLayout())).add(a("PrnHdr"), "North");
            (this.a = new JTextField()).setBackground(rp_aJ.l);
            this.a.setBorder(BorderFactory.createBevelBorder(1));
            this.a.setText(rp_aJ.g);
            panel6.add(this.a, "Center");
            panel.add(panel6);
            final JPanel panel7;
            (panel7 = new JPanel(new GridLayout(1, 3))).setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            panel7.setAlignmentX(0.5f);
            panel7.add(a("PrnAl"));
            panel7.add(this.f = new JCheckBox(rp_au.a("PrnHSc"), rp_aJ.l));
            panel7.add(this.g = new JCheckBox(rp_au.a("PrnHD"), rp_aJ.m));
            panel.add(panel7);
            final JPanel panel8 = new JPanel(new GridLayout(3, 2, 0, 0));
            this.a = new ButtonGroup();
            this.c = new JRadioButton(rp_au.a("PrnCS"), rp_aJ.e == 0);
            this.a.add(this.c);
            this.c.addItemListener(this);
            panel8.add(this.c);
            final JPanel panel9;
            (panel9 = new JPanel()).setAlignmentX(1.0f);
            if (rp_au.a.a().a()) {
                panel9.add(new JLabel("1\" equals to "));
            }
            else {
                panel9.add(new JLabel("1 : "));
            }
            (this.b = new JTextField()).setBackground(rp_aJ.l);
            this.b.setBorder(BorderFactory.createBevelBorder(1));
            this.b.setColumns(6);
            String text;
            if (rp_au.a.a().a()) {
                text = rp_au.a.a().a(2540 * (rp_aJ.f / 1000));
            }
            else {
                text = "" + rp_aJ.f / 1000;
            }
            this.b.setText(text);
            panel9.add(this.b);
            panel8.add(panel9);
            this.b = new JRadioButton(rp_au.a("PrnFit"), rp_aJ.e == 2);
            this.a.add(this.b);
            this.b.addItemListener(this);
            panel8.add(this.b);
            panel8.add(a(""));
            ((ButtonGroup)(this.a = new JRadioButton(rp_au.a("PrnSS"), rp_aJ.e == 1))).add(this.a);
            this.a.addItemListener(this);
            panel8.add(this.a);
            panel.add(panel8);
            this.itemStateChanged(null);
        }
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (this.a == "PrnOpt") {
            this.d.setEnabled(this.c.isSelected());
            this.b.setEnabled(this.c.isSelected());
            this.b.setEditable(this.c.isSelected());
        }
    }
    
    private static JLabel a(final String s) {
        final JLabel label;
        (label = new JLabel(rp_au.a(0, s))).setAlignmentX(0.5f);
        return label;
    }
    
    public final boolean a() {
        rp_aJ.f = this.a.isSelected();
        rp_aJ.g = this.b.isSelected();
        rp_aJ.h = true;
        rp_aJ.k = this.e.isSelected();
        if (this.a == "PrnOpt") {
            rp_aJ.i = this.c.isSelected();
            rp_aJ.j = this.d.isSelected();
            rp_aJ.g = this.a.getText();
            rp_aJ.l = this.f.isSelected();
            rp_aJ.m = this.g.isSelected();
            if (this.b.isSelected()) {
                rp_aJ.e = 2;
            }
            if (this.a.isSelected()) {
                rp_aJ.e = 1;
            }
            if (this.c.isSelected()) {
                rp_aJ.e = 0;
            }
            try {
                final int a = rp_au.a.a().a(this.b.getText());
                if (rp_au.a.a().a()) {
                    rp_aJ.f = a * 1000 / 2540;
                }
                else {
                    rp_aJ.f = a * 1000;
                }
            }
            catch (rp_fS rp_fS) {
                return false;
            }
        }
        if (this.a != null) {
            if (this.a == "PrnOpt") {
                this.a.b("print-page");
            }
            if (this.a == "PrnOptSI") {
                this.a.b("saveimage-page");
            }
        }
        return true;
    }
}
