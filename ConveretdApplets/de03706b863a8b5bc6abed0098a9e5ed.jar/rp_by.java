import java.awt.event.ActionEvent;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_by extends JPanel implements ActionListener
{
    private static String a;
    private ImageIcon a;
    private ImageIcon b;
    private rp_dl a;
    private JLabel a;
    private JButton a;
    private JButton b;
    
    public rp_by(final ImageIcon a, final ImageIcon b) {
        super(new BorderLayout());
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = new JLabel();
        this.a = null;
        this.b = null;
        this.a = a;
        this.b = b;
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public rp_by() {
        super(new BorderLayout());
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = new JLabel();
        this.a = null;
        this.b = null;
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public final void a(final rp_dl a) {
        this.a = a;
        this.setBackground(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        this.add(this.a, "West");
        if (this.a != null && this.b != null) {
            final JPanel panel;
            (panel = new JPanel()).setBackground(null);
            (this.a = new JButton("", this.a)).setBorder(null);
            this.a.setContentAreaFilled(false);
            this.a.setActionCommand("P");
            panel.add(this.a);
            panel.add(new JLabel("I"));
            (this.b = new JButton("", this.b)).setHorizontalTextPosition(2);
            this.b.setBorder(null);
            this.b.setContentAreaFilled(false);
            this.b.setActionCommand("N");
            panel.add(this.b);
            this.add(panel, "East");
        }
        this.a();
    }
    
    private void a(final boolean b, final boolean b2) {
        final JButton button;
        if ((button = (b ? this.a : this.b)) != null) {
            final JButton button2 = button;
            final Color a = rp_au.a.a(b2 ? "c_plist_pn" : "c_plist_pn_dis", b2 ? Color.blue : Color.gray);
            button2.setText(String.format(rp_by.a, a.getRed(), a.getGreen(), a.getBlue(), b ? "prev" : "next"));
            button.setCursor(Cursor.getPredefinedCursor(b2 ? 12 : 0));
            if (b2) {
                button.removeActionListener(this);
                button.addActionListener(this);
                return;
            }
            button.removeActionListener(this);
        }
    }
    
    final void a() {
        final int n = this.a.c + 1;
        final int b;
        final int min = Math.min(b = this.a.b(), this.a.c + this.a.a());
        this.a.setText(" Items (" + n + "-" + min + ") of " + b);
        this.a(true, n > 1);
        this.a(false, min < b);
    }
    
    public final void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (this.a != null) {
            this.a.setEnabled(enabled);
        }
        if (this.b != null) {
            this.b.setEnabled(enabled);
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("P")) {
            final rp_dl a;
            if ((a = this.a).c > 0) {
                a.c = Math.max(0, a.c - a.a());
                a.a(true);
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("N")) {
            final rp_dl a2;
            if ((a2 = this.a).c + a2.a() < a2.b()) {
                final rp_dl rp_dl = a2;
                rp_dl.c += a2.a();
                a2.a(true);
            }
        }
    }
    
    static {
        rp_by.a = "<html><a href=\"\" style=\"color: rgb(%1d,%2d,%3d)\">%4s</a></html>";
    }
}
