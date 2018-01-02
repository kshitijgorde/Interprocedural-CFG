import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.AbstractButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.border.Border;
import java.awt.CardLayout;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.SpringLayout;
import java.awt.event.ComponentListener;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bl extends rp_bW implements ActionListener
{
    boolean a;
    int a;
    private Component a;
    private rp_eB a;
    rp_bZ a;
    private JTextField b;
    JTextField a;
    private JTextField c;
    private JTextField d;
    private JTextField e;
    private JTextField f;
    private JTextField g;
    private JTextField h;
    private JTextField i;
    private JTextField j;
    private JTextField k;
    private JTextField l;
    int b;
    
    public rp_bl(final rp_bZ a, final boolean a2) {
        super(rp_au.a("accT1"));
        this.a = false;
        this.a = 0;
        this.b = 0;
        this.a = a2;
        this.a = a;
        this.a = this.getGlassPane();
        (this.a = new rp_eB(a)).setOpaque(false);
        this.a.setBackground(new Color(225, 221, 221, 175));
    }
    
    public final void a() {
        super.a();
        rp_C.a(10, "LoginDlg.flags: " + this.a);
        this.addComponentListener(new rp_bQ(this));
        final Container contentPane;
        (contentPane = this.getContentPane()).removeAll();
        contentPane.setLayout(new SpringLayout());
        final rp_cP rp_cP = new rp_cP("<html>" + rp_au.a("accD1") + "</html>");
        contentPane.add(rp_cP);
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final CardLayout layout = new CardLayout();
        panel3.setLayout(layout);
        if (this.a) {
            final JPanel panel4 = new JPanel();
            final rp_fO rp_fO;
            (rp_fO = new rp_fO(rp_au.a("accd"))).addActionListener(new rp_bS(this, layout, panel3));
            panel4.add(rp_fO);
            panel3.add(panel4, "DELETE_FIRST_CONFIRM_PANEL");
        }
        final JPanel panel5 = new JPanel();
        panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, rp_aJ.o));
        panel5.setLayout(new BoxLayout(panel5, 1));
        a(panel5, this.a ? "accD" : "LIN");
        (this.b = a(panel5, "accEA", false, false, true)).addActionListener(new rp_bP(this));
        (this.a = a(panel5, "accPw", true, false, true)).setActionCommand(this.a ? "D" : "L");
        this.a.addActionListener(this);
        final JPanel panel6 = new JPanel();
        final rp_fO rp_fO2;
        (rp_fO2 = new rp_fO(rp_au.a(this.a ? "accd" : "accLg"))).setActionCommand(this.a ? "D" : "L");
        rp_fO2.addActionListener(this);
        panel6.add(rp_fO2);
        final rp_fO rp_fO3;
        (rp_fO3 = new rp_fO(rp_au.a("Cancel"))).setActionCommand("C");
        rp_fO3.addActionListener(this);
        panel6.add(rp_fO3);
        panel5.add(panel6);
        if (!this.a) {
            final JPanel panel7 = new JPanel();
            final rp_fO rp_fO4;
            (rp_fO4 = new rp_fO(rp_au.a("accLP"))).setActionCommand("W");
            rp_fO4.addActionListener(this);
            panel7.add(rp_fO4);
            panel5.add(panel7);
        }
        panel3.add(panel5, "ACCT_LOGIN_PANEL");
        panel2.add(Box.createHorizontalGlue());
        panel2.add(panel3);
        panel2.add(Box.createHorizontalGlue());
        final JPanel panel8 = new JPanel();
        final JPanel panel9 = new JPanel(new BorderLayout());
        final JPanel panel10 = new JPanel();
        final JPanel panel11 = new JPanel();
        final JPanel panel12 = new JPanel();
        panel10.setLayout(new GridLayout(1, 2, 12, 0));
        panel11.setLayout(new GridLayout(6, 1));
        panel12.setLayout(new GridLayout(6, 1));
        final JPanel panel13;
        (panel13 = new JPanel()).setLayout(new BoxLayout(panel13, 1));
        a(panel13, this.a ? "accMd" : "accCr");
        panel9.add(panel13, "North");
        final boolean b = this.a ? (!this.a(18)) : (!this.a(1));
        final boolean b2 = this.a ? (!this.a(34)) : (!this.a(1));
        final boolean enabled = this.a ? (!this.a(2)) : (!this.a(1));
        this.c = a(panel11, "accNm", false, true, b);
        this.d = a(panel11, "accAd", false, false, !this.a(35));
        this.e = a(panel11, "accCt", false, false, enabled);
        this.f = a(panel11, "accSt", false, false, enabled);
        this.g = a(panel11, "accZp", false, true, enabled);
        this.h = a(panel11, "accPh", false, true, enabled);
        this.i = a(panel12, "accEA", false, true, b2);
        if (this.a) {
            this.j = a(panel12, "accOp", true, true, enabled);
        }
        this.k = a(panel12, "accPw", true, true, enabled);
        this.l = a(panel12, "accRP", true, true, enabled);
        final JPanel panel14 = new JPanel();
        final AbstractButton abstractButton;
        (abstractButton = new rp_fO(rp_au.a("accSu"))).setEnabled(enabled);
        abstractButton.setActionCommand("S");
        abstractButton.addActionListener(this);
        panel14.add(abstractButton);
        panel14.add(new JLabel(rp_au.a("accRq")));
        panel12.add(panel14);
        panel10.add(panel11);
        panel10.add(panel12);
        panel9.add(panel10, "Center");
        panel8.add(Box.createHorizontalGlue());
        panel8.add(panel9);
        panel8.add(Box.createHorizontalGlue());
        panel.add(panel2);
        panel.add(panel8);
        contentPane.add(panel);
        final rp_cP rp_cP2;
        final Font font = (rp_cP2 = new rp_cP("<html>" + rp_au.a("accD2") + "</html>")).getFont();
        rp_cP2.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 2));
        contentPane.add(rp_cP2);
        final int n = panel.getPreferredSize().width + 20;
        rp_cP.a = n;
        rp_cP2.a = n;
        rp_ah.a(contentPane, 3, 1, 3, 3, 3, 3);
        this.pack();
        final rp_ap a;
        if (this.a && (a = this.a.a()) != null) {
            a(this.c, a.a);
            a(this.d, a.b);
            a(this.e, a.c);
            a(this.f, a.d);
            a(this.g, a.e);
            a(this.h, a.f);
            a(this.i, a.g);
        }
        this.b();
        this.setLocation(this.getLocation().x, 0);
    }
    
    private static void a(final JTextField textField, final String text) {
        if (text != null && text.length() > 0 && textField != null) {
            textField.setText(text);
        }
    }
    
    private static void a(final Container container, final String s) {
        final JLabel label;
        (label = new JLabel(rp_au.a(s))).setAlignmentX(0.5f);
        label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(rp_aJ.q, 2), BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        container.add(label);
        container.add(Box.createVerticalStrut(25));
    }
    
    private static JTextField a(final Container container, final String s, final boolean b, final boolean b2, final boolean enabled) {
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 1));
        final JPanel panel2 = new JPanel(new FlowLayout(0, 8, 0));
        final JLabel label;
        (label = new JLabel(rp_au.a(s) + (b2 ? "*" : ""))).setAlignmentX(0.0f);
        panel2.add(label);
        panel.add(panel2);
        final JPanel panel3 = new JPanel(new FlowLayout(0, 4, 0));
        final JTextField textField;
        (textField = (b ? new JPasswordField(14) : new JTextField(14))).setEnabled(enabled);
        panel3.add(textField);
        panel.add(panel3);
        panel.add(Box.createRigidArea(new Dimension(6, 6)));
        panel.validate();
        container.add(panel);
        return textField;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("C")) {
            this.dispose();
            return;
        }
        if (actionEvent.getActionCommand().equals("L")) {
            final rp_bl rp_bl = this;
            final String text = this.b.getText();
            final String text2 = this.a.getText();
            final String s = text;
            this = rp_bl;
            if (s.length() == 0) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNE");
                return;
            }
            if (!rp_C.a(s)) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accIE");
                return;
            }
            if (text2.length() == 0) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNP");
                return;
            }
            new rp_bM(this, s, text2).c();
        }
        else if (actionEvent.getActionCommand().equals("W")) {
            final rp_bl rp_bl2 = this;
            final String text3 = this.b.getText();
            this = rp_bl2;
            if (text3.length() == 0) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNE");
                return;
            }
            if (!rp_C.a(text3)) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accIE");
                return;
            }
            new rp_bK(this, text3).c();
        }
        else if (actionEvent.getActionCommand().equals("D")) {
            final rp_bl rp_bl3 = this;
            final String text4 = this.b.getText();
            final String text5 = this.a.getText();
            final String s2 = text4;
            this = rp_bl3;
            if (s2.length() == 0) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNE");
                return;
            }
            if (!rp_C.a(s2)) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accIE");
                return;
            }
            if (text5.length() == 0) {
                rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNP");
                return;
            }
            if (rp_bd.b(this.a.a(), rp_au.a.a(), "wrn", "accDRUS", false) == 1) {
                new rp_bL(this, s2, text5).c();
            }
        }
        else if (actionEvent.getActionCommand().equals("S")) {
            this.a(this.c.getText(), this.d.getText(), this.e.getText(), this.f.getText(), this.g.getText(), this.h.getText(), this.i.getText(), (this.j != null) ? this.j.getText() : "", this.k.getText(), this.l.getText());
        }
    }
    
    private boolean a(final int n) {
        return (this.a & n) != 0x0;
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        if (s.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc1");
            return;
        }
        if (s5.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc2");
            return;
        }
        if (s6.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc3");
            return;
        }
        if (s6.length() < 10) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc4");
            return;
        }
        if (s7.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNE");
            return;
        }
        if (!rp_C.a(s7)) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accIE");
            return;
        }
        if (this.a && s8.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc7");
            return;
        }
        if (s9.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "accNP");
            return;
        }
        if (s10.length() == 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc5");
            return;
        }
        if (s9.compareTo(s10) != 0) {
            rp_bd.a(this.a.a(), rp_au.a.a(), "wrn", "acc6");
            return;
        }
        new rp_bN(this, s, s2, s3, s4, s5, s6, s7, s8, s9).c();
    }
    
    protected final boolean b() {
        if (this.a) {
            this.a(this.c.getText(), this.d.getText(), this.e.getText(), this.f.getText(), this.g.getText(), this.h.getText(), this.i.getText(), (this.j != null) ? this.j.getText() : "", this.k.getText(), this.l.getText());
            return true;
        }
        return false;
    }
    
    protected final boolean c() {
        this.b = false;
        this.dispose();
        return true;
    }
}
