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

public class tj extends Dialog implements WindowListener, ActionListener
{
    private Button za;
    private a Aa;
    private a Pa;
    private boolean Ba;
    private o Ca;
    private Frame Da;
    private bj Qa;
    private String Ra;
    private String[] Sa;
    private TextField name;
    private TextField Ta;
    private TextField[] Ua;
    private TextArea Va;
    private TextArea Wa;
    
    public tj(final Frame da, final String s, bj qa, final String[] sa, final o ca) {
        super(da, s, true);
        this.Ba = false;
        this.Ra = null;
        this.Sa = null;
        this.Ca = ca;
        this.Da = da;
        if (qa == null) {
            qa = new bj();
        }
        if ((this.Qa = qa) != null) {
            this.Ra = qa.getName();
        }
        this.Sa = sa;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.Pa = this.a(), "Center");
        this.Aa = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(this.Ca.b("btnOK"));
        final Button button = new Button(this.Ca.b("btnCancel"));
        this.za.addActionListener(this);
        button.addActionListener(this);
        this.Aa.add(this.za);
        this.Aa.add(button);
        this.add(this.Aa, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    private a a() {
        final a a = new a(1, new Insets(5, 5, 5, 5));
        a.setLayout(new BorderLayout());
        final a a2 = new a(new FlowLayout(0), 4, new Insets(0, 0, 0, 0));
        a2.add(new Label(this.Ca.b("strJSIndicatorName")));
        (this.name = new Oj(6)).setText(this.Qa.getName());
        a2.add(this.name);
        a2.add(new Label(this.Ca.b("strJSIndicatorDescription")));
        (this.Ta = new c(40, false)).setText(this.Qa.l());
        a2.add(this.Ta);
        a2.add(new Label(this.Ca.b("strJSIndicatorParams")));
        final int[] _ = this.Qa._();
        this.Ua = new TextField[3];
        for (int i = 0; i < this.Ua.length; ++i) {
            this.Ua[i] = new c(3, true);
            if (_ != null && i < _.length) {
                this.Ua[i].setText(String.valueOf(_[i]));
            }
            a2.add(this.Ua[i]);
        }
        a.add(a2, "North");
        this.Va = new TextArea(15, 76);
        final Font font = new Font("Monospaced", 0, 12);
        this.Va.setFont(font);
        this.Va.setText(this.Qa.m());
        if (this.Va.getText().length() == 0) {
            this.Va.setText("/* " + this.Ca.b("strEnterIndicatorCodeHere") + " */" + System.getProperty("line.separator"));
        }
        a.add(this.Va, "Center");
        (this.Wa = new TextArea(10, 76)).setFont(font);
        this.Wa.setText(this.Ca.b("strJSIndicatorCodeHelp"));
        this.Wa.setEditable(false);
        a.add(this.Wa, "South");
        return a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            if (this.j()) {
                this.n();
                this.Ba = true;
                this.dispose();
            }
        }
        else {
            this.dispose();
        }
    }
    
    private boolean b(final String s) {
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
    
    private boolean l(final String s) {
        return s.indexOf("//") == -1 && s.indexOf("<![CDATA[") == -1 && s.indexOf("]]>") == -1;
    }
    
    private boolean m(final String s) {
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
    
    private boolean j() {
        Component component = null;
        String s = null;
        if (this.name.getText().length() == 0) {
            s = this.Ca.b("strJSIndicatorNameFieldEmpty");
            component = this.name;
        }
        else if (!this.m(this.name.getText()) || this.name.getText().length() > 6) {
            s = this.Ca.b("strJSIndicatorNameFieldInvalid");
            component = this.name;
        }
        else if (!this.b(this.Ta.getText())) {
            s = this.Ca.b("strJSIndicatorDescriptionFieldInvalid");
            component = this.Ta;
        }
        else if (this.Va.getText().length() == 0) {
            s = this.Ca.b("strJSIndicatorCodeFieldEmpty");
            component = this.Va;
        }
        else if (!this.l(this.Va.getText())) {
            s = this.Ca.b("strJSIndicatorCodeFieldInvalid");
            component = this.Va;
        }
        else {
            for (int i = 0; i < this.Ua.length; ++i) {
                if (this.Ua[i].getText().length() > 0 && !this.b(this.Ua[i].getText(), 2, 999)) {
                    s = this.Ca.b("strJSIndicatorParamFieldInvalid");
                    component = this.Ua[i];
                    break;
                }
            }
        }
        if (s == null) {
            if (this.n(this.name.getText()) && (this.Ra == null || !this.name.getText().equals(this.Ra))) {
                final b b = new b(this.Da, this.Ca.b("strConfirmIndicatorOverwriteTitle") + ": " + this.name.getText(), this.Ca.b("strConfirmIndicatorOverwriteMessage"), this.Ca.b("btnYes"), this.Ca.b("btnNo"));
                b.show();
                if (!b.a()) {
                    this.name.requestFocus();
                    return false;
                }
            }
            return true;
        }
        new b(this.Da, this.Ca.b("strJSIndicatorDefinitionError"), s, this.Ca.b("btnOK"), null).show();
        component.requestFocus();
        return false;
    }
    
    private boolean n(final String s) {
        if (s == null || s.length() == 0 || this.Sa == null) {
            return false;
        }
        for (int i = 0; i < this.Sa.length; ++i) {
            if (s.equals(this.Sa[i])) {
                return true;
            }
        }
        return false;
    }
    
    private void n() {
        this.Qa.setName(this.name.getText());
        this.Qa.h(this.Ta.getText());
        String string = "";
        for (int i = 0; i < this.Ua.length; ++i) {
            if (this.Ua[i].getText().length() > 0) {
                string = string + this.Ua[i].getText() + ',';
            }
        }
        this.Qa.i(string);
        this.Qa.j(this.Va.getText());
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
    
    public boolean a() {
        return this.Ba;
    }
    
    public bj b() {
        return this.Qa;
    }
}
