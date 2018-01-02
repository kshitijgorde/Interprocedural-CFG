import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import java.util.Enumeration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.Vector;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bH extends JPanel implements ActionListener
{
    private rp_fY a;
    private Vector a;
    JComboBox a;
    JTextField a;
    ButtonGroup a;
    JRadioButton a;
    JRadioButton b;
    rp_fO a;
    rp_fO b;
    JSpinner a;
    JSpinner b;
    JSpinner c;
    
    public rp_bH(final rp_fY a) {
        this.a = new Vector();
        this.a = null;
        this.a = a;
    }
    
    public final void setEnabled(final boolean enabled) {
        this.a.setEnabled(enabled);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b) {
            this.a.setSelectedIndex(0);
            this.a.setText(null);
            this.a.setSelected(false);
            this.b.setSelected(true);
            this.a.setValue(new Integer(0));
            this.b.setValue(new Integer(0));
            this.c.setValue(new Integer(0));
            return;
        }
        if (actionEvent.getSource() == this.a) {
            final String text = this.a.getText();
            final int intValue = (int)this.a.getValue();
            final int intValue2 = (int)this.b.getValue();
            final int intValue3 = (int)this.c.getValue();
            final boolean b = intValue + intValue2 + intValue3 > 0;
            if (text.length() == 0 && !b) {
                rp_bd.a(rp_au.a.a().a(), rp_au.a.a(), "wrn", "ESM");
                this.a.requestFocus();
                return;
            }
            final rp_k rp_k;
            (rp_k = new rp_k()).a = text;
            final int selectedIndex = this.a.getSelectedIndex();
            rp_k.b = (String)this.a.elementAt(selectedIndex);
            rp_k.c = this.a.getItemAt(selectedIndex).toString();
            rp_k.a = (this.b.isSelected() ? rp_dQ.b : rp_dQ.a);
            rp_k.a = intValue;
            rp_k.b = intValue2;
            rp_k.c = intValue3;
            this.a.a(rp_k, null);
        }
    }
    
    void a(final JComboBox comboBox, final rp_eA rp_eA) {
        comboBox.addItem(rp_au.a("AllBr"));
        this.a.addElement("");
        if (rp_eA != null) {
            if (!rp_eA.a.equalsIgnoreCase("brands")) {
                return;
            }
            final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)rp_eA.a.elements();
            while (elements.hasMoreElements()) {
                final rp_eA rp_eA2;
                if (!(rp_eA2 = elements.nextElement()).a.equalsIgnoreCase("brand")) {
                    return;
                }
                final String a = rp_eA2.a("name", "unnamed");
                final String a2 = rp_eA2.a("id", (String)null);
                comboBox.addItem(a);
                this.a.addElement(a2);
            }
        }
    }
    
    static SpinnerNumberModel a() {
        return new SpinnerNumberModel(0, 0, 999, 1);
    }
    
    static JLabel a(final String s, final boolean b) {
        final JLabel label;
        (label = new JLabel(rp_au.a(s) + (b ? ":" : ""))).setFont(rp_aJ.a);
        return label;
    }
}
