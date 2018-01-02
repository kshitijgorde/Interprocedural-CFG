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

public class xea extends Dialog implements WindowListener, ActionListener
{
    private Button pa;
    private continue ra;
    private continue Ja;
    private boolean wa;
    private switch xa;
    private Frame ya;
    private gea Ka;
    private String La;
    private String[] Ma;
    private TextField name;
    private TextField Na;
    private TextField[] Oa;
    private TextArea Pa;
    private TextArea Qa;
    
    public xea(final Frame ya, final String s, gea ka, final String[] ma, final switch xa) {
        super(ya, s, true);
        this.wa = false;
        this.La = null;
        this.Ma = null;
        this.xa = xa;
        this.ya = ya;
        if (ka == null) {
            ka = new gea();
        }
        if ((this.Ka = ka) != null) {
            this.La = ka.getName();
        }
        this.Ma = ma;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.Ja = this._(), "Center");
        this.ra = new continue(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.pa = new Button(this.xa.a("btnOK"));
        final Button button = new Button(this.xa.a("btnCancel"));
        this.pa.addActionListener(this);
        button.addActionListener(this);
        this.ra.add(this.pa);
        this.ra.add(button);
        this.add(this.ra, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    private continue _() {
        final continue continue1 = new continue(1, new Insets(5, 5, 5, 5));
        continue1.setLayout(new BorderLayout());
        final continue continue2 = new continue(new FlowLayout(0), 4, new Insets(0, 0, 0, 0));
        continue2.add(new Label(this.xa.a("strJSIndicatorName")));
        (this.name = new Sea(6)).setText(this.Ka.getName());
        continue2.add(this.name);
        continue2.add(new Label(this.xa.a("strJSIndicatorDescription")));
        (this.Na = new const(40, false)).setText(this.Ka.i());
        continue2.add(this.Na);
        continue2.add(new Label(this.xa.a("strJSIndicatorParams")));
        final int[] b = this.Ka.b();
        this.Oa = new TextField[3];
        for (int i = 0; i < this.Oa.length; ++i) {
            this.Oa[i] = new const(3, true);
            if (b != null && i < b.length) {
                this.Oa[i].setText(String.valueOf(b[i]));
            }
            continue2.add(this.Oa[i]);
        }
        continue1.add(continue2, "North");
        this.Pa = new TextArea(15, 76);
        final Font font = new Font("Monospaced", 0, 12);
        this.Pa.setFont(font);
        this.Pa.setText(this.Ka.j());
        if (this.Pa.getText().length() == 0) {
            this.Pa.setText("/* " + this.xa.a("strEnterIndicatorCodeHere") + " */" + System.getProperty("line.separator"));
        }
        continue1.add(this.Pa, "Center");
        (this.Qa = new TextArea(10, 76)).setFont(font);
        this.Qa.setText(this.xa.a("strJSIndicatorCodeHelp"));
        this.Qa.setEditable(false);
        continue1.add(this.Qa, "South");
        return continue1;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pa) {
            if (this.k()) {
                this.n();
                this.wa = true;
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
    
    private boolean h(final String s) {
        return s.indexOf("//") == -1 && s.indexOf("<![CDATA[") == -1 && s.indexOf("]]>") == -1;
    }
    
    private boolean i(final String s) {
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
    
    private boolean b(final String s, final int n, final int n2) {
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
    
    private boolean k() {
        Component component = null;
        String s = null;
        if (this.name.getText().length() == 0) {
            s = this.xa.a("strJSIndicatorNameFieldEmpty");
            component = this.name;
        }
        else if (!this.i(this.name.getText()) || this.name.getText().length() > 6) {
            s = this.xa.a("strJSIndicatorNameFieldInvalid");
            component = this.name;
        }
        else if (!this._(this.Na.getText())) {
            s = this.xa.a("strJSIndicatorDescriptionFieldInvalid");
            component = this.Na;
        }
        else if (this.Pa.getText().length() == 0) {
            s = this.xa.a("strJSIndicatorCodeFieldEmpty");
            component = this.Pa;
        }
        else if (!this.h(this.Pa.getText())) {
            s = this.xa.a("strJSIndicatorCodeFieldInvalid");
            component = this.Pa;
        }
        else {
            for (int i = 0; i < this.Oa.length; ++i) {
                if (this.Oa[i].getText().length() > 0 && !this.b(this.Oa[i].getText(), 2, 999)) {
                    s = this.xa.a("strJSIndicatorParamFieldInvalid");
                    component = this.Oa[i];
                    break;
                }
            }
        }
        if (s == null) {
            if (this.j(this.name.getText()) && (this.La == null || !this.name.getText().equals(this.La))) {
                final default default1 = new default(this.ya, this.xa.a("strConfirmIndicatorOverwriteTitle") + ": " + this.name.getText(), this.xa.a("strConfirmIndicatorOverwriteMessage"), this.xa.a("btnYes"), this.xa.a("btnNo"));
                default1.show();
                if (!default1._()) {
                    this.name.requestFocus();
                    return false;
                }
            }
            return true;
        }
        new default(this.ya, this.xa.a("strJSIndicatorDefinitionError"), s, this.xa.a("btnOK"), null).show();
        component.requestFocus();
        return false;
    }
    
    private boolean j(final String s) {
        if (s == null || s.length() == 0 || this.Ma == null) {
            return false;
        }
        for (int i = 0; i < this.Ma.length; ++i) {
            if (s.equals(this.Ma[i])) {
                return true;
            }
        }
        return false;
    }
    
    private void n() {
        this.Ka.setName(this.name.getText());
        this.Ka.g(this.Na.getText());
        String string = "";
        for (int i = 0; i < this.Oa.length; ++i) {
            if (this.Oa[i].getText().length() > 0) {
                string = string + this.Oa[i].getText() + ',';
            }
        }
        this.Ka.h(string);
        this.Ka.i(this.Pa.getText());
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
    
    public boolean _() {
        return this.wa;
    }
    
    public gea a() {
        return this.Ka;
    }
}
