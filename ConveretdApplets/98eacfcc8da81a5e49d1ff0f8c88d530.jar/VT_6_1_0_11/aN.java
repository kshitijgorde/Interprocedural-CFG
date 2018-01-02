// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public final class aN extends bD implements ActionListener
{
    private l a;
    private JTextField b;
    private JTextField c;
    private JPasswordField d;
    private JButton e;
    private JButton f;
    private JLabel g;
    
    public aN(final l a) {
        super(a.e("auth_frame_name"));
        this.a = a;
        this.setMinimumSize(new Dimension(300, 400));
        this.setClosable(false);
        this.b = new aV(a.a());
        (this.d = new JPasswordField()).setEchoChar('*');
        this.b.setNextFocusableComponent(this.d);
        this.e = new cP(a.e("btn_submit"));
        this.c = new aV(a.a());
        this.d.setNextFocusableComponent(this.c);
        this.c.setNextFocusableComponent(this.b);
        this.f = new cP(a.e("btn_submit"));
        if (this.e != null) {
            this.e.addActionListener(this);
        }
        if (this.f != null) {
            this.f.addActionListener(this);
        }
        if (this.b != null) {
            this.b.addActionListener(this);
        }
        if (this.d != null) {
            this.d.addActionListener(this);
        }
        if (this.c != null) {
            this.c.addActionListener(this);
        }
        (this.g = new aT("<html>", 0)).setForeground(Color.red);
        final JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.add(this.g, new GridBagConstraints(0, 0, 2, 1, 0.1, 0.1, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(new aT(this.a.e("auth_lbl_enter_info_screen"), 0), new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(new aT(this.a.e("auth_lbl_screen"), 4), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 50, 10, 10), 0, 0));
        contentPane.add(this.c, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, 17, 2, new Insets(10, 10, 10, 50), 0, 0));
        contentPane.add(this.f, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        contentPane.add(panel, new GridBagConstraints(0, 4, 2, 1, 1.0, 0.5, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(new aT(this.a.e("auth_lbl_enter_info_account"), 0), new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(new aT(this.a.e("auth_lbl_login"), 4), new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 50, 2, 10), 0, 0));
        contentPane.add(this.b, new GridBagConstraints(1, 6, 1, 1, 1.0, 0.0, 17, 2, new Insets(10, 10, 2, 50), 0, 0));
        contentPane.add(new aT(this.a.e("auth_lbl_password"), 4), new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, 13, 0, new Insets(2, 50, 10, 10), 0, 0));
        contentPane.add(this.d, new GridBagConstraints(1, 7, 1, 1, 1.0, 0.0, 17, 2, new Insets(2, 10, 10, 50), 0, 0));
        contentPane.add(this.e, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        final JPanel panel2;
        (panel2 = new JPanel()).setOpaque(false);
        contentPane.add(panel2, new GridBagConstraints(0, 9, 2, 1, 1.0, 1.0, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
        this.setContentPane(contentPane);
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.revalidate();
            if (bj.e()) {
                if (this.c != null && this.c.isVisible()) {
                    this.c.requestFocus();
                    return;
                }
                if (this.b != null && this.b.isVisible()) {
                    this.b.requestFocus();
                }
            }
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final Object source;
        if ((source = actionEvent.getSource()) == this.e || source == this.d) {
            final String s = "";
            final String trim = this.b.getText().trim();
            final String s2 = new String(this.d.getPassword());
            if (trim.equals("")) {
                this.a(this.a.e("error_invalid_login"));
                return;
            }
            this.a.a(trim, s2, s);
        }
        else {
            if (source != this.f && source != this.c) {
                if (source == this.b && this.d != null && this.d.isVisible() && bj.e()) {
                    this.d.requestFocus();
                }
                return;
            }
            final String s3 = "";
            final String s4 = "";
            final String trim2;
            if ((trim2 = this.c.getText().trim()).length() < 2 || trim2.length() > 160) {
                this.a(this.a.e("error_invalid_screen_name"));
                return;
            }
            this.a.a(s3, s4, trim2);
        }
    }
    
    public final void a(final String text) {
        this.g.setText(text);
    }
}
