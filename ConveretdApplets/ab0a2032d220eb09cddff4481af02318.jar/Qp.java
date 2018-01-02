import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Qp extends Dialog implements WindowListener, ActionListener
{
    private Button uDb;
    private Vo wDb;
    private Vo xDb;
    private boolean vDb;
    private volatile yDb;
    private Frame tDb;
    private To zDb;
    private String ADb;
    private String[] BDb;
    private TextField name;
    private TextField CDb;
    private TextField[] DDb;
    private TextArea EDb;
    private TextArea FDb;
    
    public Qp(final Frame tDb, final String s, To zDb, final String[] bDb, final volatile yDb) {
        super(tDb, s, true);
        this.vDb = false;
        this.ADb = null;
        this.BDb = null;
        this.yDb = yDb;
        this.tDb = tDb;
        if (zDb == null) {
            zDb = new To();
        }
        if ((this.zDb = zDb) != null) {
            this.ADb = zDb.getName();
        }
        this.BDb = bDb;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.xDb = this.b(), "Center");
        this.wDb = new Vo(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.uDb = new Button(this.yDb._("btnOK"));
        final Button button = new Button(this.yDb._("btnCancel"));
        this.uDb.addActionListener(this);
        button.addActionListener(this);
        this.wDb.add(this.uDb);
        this.wDb.add(button);
        this.add(this.wDb, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    private Vo b() {
        final Vo vo = new Vo(1, new Insets(5, 5, 5, 5));
        vo.setLayout(new BorderLayout());
        final Vo vo2 = new Vo(new FlowLayout(0), 4, new Insets(0, 0, 0, 0));
        vo2.add(new Label(this.yDb._("strJSIndicatorName")));
        (this.name = new Rp(6)).setText(this.zDb.getName());
        vo2.add(this.name);
        vo2.add(new Label(this.yDb._("strJSIndicatorDescription")));
        (this.CDb = new jp(40, false)).setText(this.zDb.b());
        vo2.add(this.CDb);
        vo2.add(new Label(this.yDb._("strJSIndicatorParams")));
        final int[] a = this.zDb.a();
        this.DDb = new TextField[3];
        for (int i = 0; i < this.DDb.length; ++i) {
            this.DDb[i] = new jp(3, true);
            if (a != null && i < a.length) {
                this.DDb[i].setText(String.valueOf(a[i]));
            }
            vo2.add(this.DDb[i]);
        }
        vo.add(vo2, "North");
        this.EDb = new TextArea(15, 76);
        final Font font = new Font("Monospaced", 0, 12);
        this.EDb.setFont(font);
        this.EDb.setText(this.zDb.h());
        if (this.EDb.getText().length() == 0) {
            this.EDb.setText("/* " + this.yDb._("strEnterIndicatorCodeHere") + " */" + System.getProperty("line.separator"));
        }
        vo.add(this.EDb, "Center");
        (this.FDb = new TextArea(10, 76)).setFont(font);
        this.FDb.setText(this.yDb._("strJSIndicatorCodeHelp"));
        this.FDb.setEditable(false);
        vo.add(this.FDb, "South");
        return vo;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.uDb) {
            if (this.m()) {
                this.S();
                this.vDb = true;
                this.dispose();
            }
        }
        else {
            this.dispose();
        }
    }
    
    private boolean _(final String s) {
        if (s.length() > 40) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '_' && char1 != '%' && char1 != '-' && char1 != '.' && char1 != ':' && char1 != '(' && char1 != ')' && char1 != '&' && !Character.isLetterOrDigit(char1)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean i(final String s) {
        return s.indexOf("//") == -1 && s.indexOf("<![CDATA[") == -1 && s.indexOf("]]>") == -1;
    }
    
    private boolean j(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if ((char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z') && (char1 < '0' || char1 > '9') && char1 != '%' && char1 != '&') {
                return false;
            }
        }
        return true;
    }
    
    private boolean a(final String s, final int n, final int n2) {
        try {
            final int int1 = Integer.parseInt(s);
            if (int1 < n || int1 > n2) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    private boolean m() {
        Component component = null;
        String s = null;
        if (this.name.getText().length() == 0) {
            s = this.yDb._("strJSIndicatorNameFieldEmpty");
            component = this.name;
        }
        else if (!this.j(this.name.getText()) || this.name.getText().length() > 6) {
            s = this.yDb._("strJSIndicatorNameFieldInvalid");
            component = this.name;
        }
        else if (!this._(this.CDb.getText())) {
            s = this.yDb._("strJSIndicatorDescriptionFieldInvalid");
            component = this.CDb;
        }
        else if (this.EDb.getText().length() == 0) {
            s = this.yDb._("strJSIndicatorCodeFieldEmpty");
            component = this.EDb;
        }
        else if (!this.i(this.EDb.getText())) {
            s = this.yDb._("strJSIndicatorCodeFieldInvalid");
            component = this.EDb;
        }
        else {
            for (int i = 0; i < this.DDb.length; ++i) {
                if (this.DDb[i].getText().length() > 0 && !this.a(this.DDb[i].getText(), 2, 999)) {
                    s = this.yDb._("strJSIndicatorParamFieldInvalid");
                    component = this.DDb[i];
                    break;
                }
            }
        }
        if (s == null) {
            if (this.k(this.name.getText()) && (this.ADb == null || !this.name.getText().equals(this.ADb))) {
                final Sp sp = new Sp(this.tDb, this.yDb._("strConfirmIndicatorOverwriteTitle") + ": " + this.name.getText(), this.yDb._("strConfirmIndicatorOverwriteMessage"), this.yDb._("btnYes"), this.yDb._("btnNo"));
                sp.show();
                if (!sp.l()) {
                    this.name.requestFocus();
                    return false;
                }
            }
            return true;
        }
        new Sp(this.tDb, this.yDb._("strJSIndicatorDefinitionError"), s, this.yDb._("btnOK"), null).show();
        component.requestFocus();
        return false;
    }
    
    private boolean k(final String s) {
        if (s == null || s.length() == 0 || this.BDb == null) {
            return false;
        }
        for (int i = 0; i < this.BDb.length; ++i) {
            if (s.equals(this.BDb[i])) {
                return true;
            }
        }
        return false;
    }
    
    private void S() {
        this.zDb.setName(this.name.getText());
        this.zDb.f(this.CDb.getText());
        String string = "";
        for (int i = 0; i < this.DDb.length; ++i) {
            if (this.DDb[i].getText().length() > 0) {
                string = string + this.DDb[i].getText() + ',';
            }
        }
        this.zDb.g(string);
        this.zDb.h(this.EDb.getText());
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public boolean l() {
        return this.vDb;
    }
    
    public To a() {
        return this.zDb;
    }
}
