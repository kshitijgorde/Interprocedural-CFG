import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.FocusListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bi extends rp_eD implements FocusListener
{
    private JTextField a;
    private JTextField b;
    private JTextField c;
    private JComboBox a;
    private int a;
    private int b;
    
    rp_bi(final Frame frame, final rp_dC rp_dC, final rp_dv rp_dv, final rp_dK rp_dK) {
        super(frame, rp_dC, rp_dv, rp_dK);
    }
    
    final Component a() {
        final JPanel panel;
        (panel = new JPanel(new GridLayout(4, 2, 2, 4))).setBorder(rp_ap.a(rp_au.a("ii2"), rp_aJ.o));
        rp_au.a.a().a(false);
        panel.add(new JLabel(rp_au.a("Nm") + ": "), "West");
        (this.a = new JTextField()).setBackground(rp_aJ.l);
        this.a.addFocusListener(this);
        this.a.setText(this.a.b());
        panel.add(this.a, "Center");
        panel.add(new JLabel(rp_au.a("Wd")));
        (this.b = new JTextField(6)).setBackground(rp_aJ.l);
        this.b.addFocusListener(this);
        panel.add(this.b);
        panel.add(new JLabel(rp_au.a("Dd")));
        (this.c = new JTextField(6)).setBackground(rp_aJ.l);
        this.c.addFocusListener(this);
        panel.add(this.c);
        panel.add(new JLabel(rp_au.a("Layer") + ":"));
        (this.a = new JComboBox()).setRenderer(rp_au.a().a);
        this.a.setToolTipText(rp_au.a.b("tt_lo"));
        for (int i = 1; i <= 10; ++i) {
            this.a.addItem("" + i);
        }
        this.a.setSelectedItem("" + this.a.a());
        panel.add(this.a);
        this.a(this.a.a(), this.a.b());
        this.a(this.a.a(), this.a.b());
        return panel;
    }
    
    private void a(final int n, final int n2) {
        this.b.setText(rp_au.a.a().a(n));
        this.c.setText(rp_au.a.a().a(n2));
    }
    
    private boolean e() {
        try {
            this.a = rp_au.a.a().a(this.b.getText());
            this.b = rp_au.a.a().a(this.c.getText());
        }
        catch (rp_fS rp_fS) {
            return false;
        }
        return true;
    }
    
    public final boolean a() {
        if (!this.e()) {
            return false;
        }
        final String text = super.a.getText();
        final int n = 1 + this.a.getSelectedIndex();
        final String text2 = this.a.getText();
        final Color a = this.a.a();
        final Color b = this.a.b();
        if (this.a != this.a.a() || this.b != this.a.b() || !text.equals(this.a.a()) || n != this.a.a() || !text2.equals(this.a.b()) || (a == null && this.a.a() != null) || (a != null && !a.equals(this.a.a())) || (b == null && this.a.b() != null) || (b != null && !b.equals(this.a.b()))) {
            final rp_cH rp_cH = new rp_cH(this.a, this.a, this.b, text, n, text2, a, b);
            if (this.a.h > 0) {
                rp_au.a.a.a(rp_cH);
            }
            else {
                rp_cH.a();
            }
            rp_au.a.a(a, b);
        }
        return true;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getSource() == this.c && this.e()) {
            final int b = this.a.b();
            if (this.b == 0 && b != 0) {
                this.b = b;
                this.a(this.a, this.b);
                this.c.requestFocus();
            }
        }
        if (focusEvent.getSource() == this.b && this.e()) {
            final int a = this.a.a();
            if (this.a == 0 && a != 0) {
                this.a(this.a = a, this.b);
                this.b.requestFocus();
            }
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
