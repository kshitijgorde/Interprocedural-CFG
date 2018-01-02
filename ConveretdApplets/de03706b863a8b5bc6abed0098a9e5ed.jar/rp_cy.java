import java.awt.event.ItemEvent;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cy extends rp_fG implements ItemListener
{
    private rp_aJ a;
    private JComboBox a;
    private int a;
    private JComboBox b;
    private JTextField a;
    private String a;
    private JCheckBox a;
    private JCheckBox b;
    private JCheckBox c;
    
    public rp_cy() {
        super(rp_au.a("AppName") + " " + rp_au.a("Options"));
        this.a = rp_au.a;
        this.a(this.a.a());
        this.a();
        this.b();
        this.a.requestFocus();
    }
    
    public final void a(final JPanel panel) {
        final GridLayout layout = new GridLayout();
        panel.setLayout(layout);
        layout.setColumns(1);
        final JPanel panel2;
        (panel2 = new JPanel(new FlowLayout(0))).add(new JLabel(rp_au.a("Units") + ":"));
        (this.a = new JComboBox()).setRenderer(rp_au.a().a);
        this.a.addItem(rp_au.a("ufi"));
        this.a.addItem(rp_au.a("umm"));
        this.a.addItem(rp_au.a("ucm"));
        this.a.addItem(rp_au.a("um"));
        this.a.setSelectedIndex(this.a.a().a);
        this.a.addItemListener(this);
        panel2.add((Component)(this.a = this.a.a().a));
        layout.setRows(1);
        panel.add(panel2);
        final JPanel panel3;
        (panel3 = new JPanel(new FlowLayout(0))).add(new JLabel(rp_au.a("Grid_type") + ":"));
        (this.b = new JComboBox()).setRenderer(rp_au.a().a);
        this.b.addItem(rp_au.a("None"));
        this.b.addItem(rp_au.a("Lines"));
        this.b.addItem(rp_au.a("Dots"));
        this.b.setSelectedIndex(rp_aJ.b());
        panel3.add(this.b);
        layout.setRows(2);
        panel.add(panel3);
        final JPanel panel4;
        (panel4 = new JPanel(new FlowLayout(0))).add(new JLabel(rp_au.a("Grd_sc") + ":"));
        this.a.a().a(true);
        this.a = this.a.a().a(rp_aJ.c());
        (this.a = new JTextField(this.a, 15)).setBackground(rp_aJ.l);
        this.a.setBorder(BorderFactory.createBevelBorder(1));
        panel4.add(this.a);
        layout.setRows(3);
        panel.add(panel4);
        layout.setRows(4);
        this.a = a("Keep_tape", rp_aJ.c, panel);
        layout.setRows(5);
        this.b = a("AS_tape", rp_aJ.d, panel);
        layout.setRows(6);
        this.c = a("show_tt", !this.a.b, panel);
    }
    
    private static JCheckBox a(final String s, final boolean b, final Container container) {
        final JPanel panel = new JPanel(new FlowLayout(0));
        final JCheckBox checkBox = new JCheckBox(rp_au.a(s), b);
        panel.add(checkBox);
        container.add(panel);
        return checkBox;
    }
    
    public final boolean a() {
        int a = 0;
        if (this.a.compareTo(this.a.getText()) != 0) {
            try {
                a = this.a.a().a(this.a.getText());
            }
            catch (rp_fS rp_fS) {
                return false;
            }
        }
        this.a.a().a(this.a.getSelectedIndex());
        rp_aJ.a(this.b.getSelectedIndex());
        if (this.a.compareTo(this.a.getText()) != 0) {
            rp_aJ.b(a);
        }
        rp_aJ.c = this.a.isSelected();
        rp_aJ.d = this.b.isSelected();
        this.a.c(!this.c.isSelected());
        this.a.d();
        return true;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.a) {
            int a;
            try {
                a = rp_cR.a(this.a.getText(), this.a);
            }
            catch (rp_fS rp_fS) {
                return;
            }
            this.a.setText(this.a.a().a(a, this.a.getSelectedIndex()));
            this.a = this.a.getSelectedIndex();
        }
    }
}
