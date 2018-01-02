// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Window;
import com.daysofwonder.util.t;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.daysofwonder.util.UIProperties;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class S extends WindowAdapter implements D, ActionListener, WindowListener
{
    private Frame a;
    private Dialog b;
    private TextField c;
    private Label d;
    private Label e;
    private Button f;
    private Button g;
    private UIProperties h;
    private boolean i;
    private String j;
    
    public S(final Frame a, final UIProperties h) {
        this.a = a;
        this.h = h;
    }
    
    public void a() {
        this.b = new Dialog(this.a, this.h.b("tablepass.title"), true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        panel.setLayout(layout);
        this.b.add(panel, "Center");
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        (this.e = new Label(this.h.b("tablepass.expl"))).setVisible(true);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.d = new Label(this.h.b("tablepass.password_label")), gridBagConstraints);
        this.c = new TextField("", 25);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.c, gridBagConstraints);
        this.c.setEchoChar('*');
        panel.add(this.d);
        panel.add(this.c);
        gridBagConstraints.gridwidth = 1;
        (this.f = new Button(this.h.b("tablepass.ok"))).addActionListener(this);
        panel.add(this.f);
        (this.g = new Button(this.h.b("tablepass.cancel"))).addActionListener(this);
        panel.add(this.g);
        this.b.addWindowListener(this);
        this.b.setResizable(false);
        t.a("pack");
        this.b.pack();
        a(this.b);
        this.b.setVisible(true);
    }
    
    public void d() {
        t.a("dispose");
        this.b.dispose();
        t.a("dispose end");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        t.a("action: " + actionEvent);
        if (actionEvent.getSource() == this.f) {
            t.a("OK button");
            if (this.c.getText().length() == 0) {
                System.out.println("**** Tried to join without password");
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            try {
                this.j = this.c.getText();
                this.i = true;
                t.a("auto dispose");
                this.d();
            }
            catch (Exception ex) {
                System.out.println(ex.getClass().toString());
                t.a("exception");
                this.e();
            }
        }
        else if (actionEvent.getSource() == this.g) {
            this.i = false;
            t.a("cancel called");
            this.b.dispose();
        }
        t.a("end action");
    }
    
    public boolean b() {
        return this.i;
    }
    
    public String c() {
        return this.j;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.d();
    }
    
    public static void a(final Window window) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width < window.getSize().width) {
            window.setSize(screenSize.width, window.getSize().height);
        }
        if (screenSize.height < window.getSize().height) {
            window.setSize(window.getSize().width, screenSize.height);
        }
        window.setLocation((screenSize.width - window.getSize().width) / 2, (screenSize.height - window.getSize().height) / 2);
    }
    
    public void e() {
        this.f.setEnabled(true);
        this.g.setEnabled(true);
        this.d.setVisible(true);
        this.c.setVisible(true);
        this.c.setEnabled(true);
        this.c.setText("");
    }
}
