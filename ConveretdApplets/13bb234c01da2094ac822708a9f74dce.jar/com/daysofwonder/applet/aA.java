// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Window;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.EventQueue;
import com.daysofwonder.util.UIProperties;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class aA extends Dialog implements ActionListener, KeyListener
{
    private Frame a;
    private TextField b;
    private TextField c;
    private Label d;
    private Label e;
    private Label f;
    private Button g;
    private UIProperties h;
    private String i;
    private String j;
    private EventQueue k;
    
    public aA(final Frame a, final UIProperties h) {
        super(a);
        this.a = a;
        this.h = h;
    }
    
    public void a() {
        this.setTitle(this.h.b("login.title"));
        this.enableEvents(64L);
        this.setResizable(false);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        panel.setLayout(layout);
        this.add(panel, "Center");
        this.e = new Label(this.h.b("login.login_label"));
        this.c = new TextField("", 25);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.c, gridBagConstraints);
        panel.add(this.c);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.d = new Label(this.h.b("login.password_label")), gridBagConstraints);
        this.b = new TextField("", 25);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        this.b.setEchoChar('*');
        panel.add(this.d);
        panel.add(this.b);
        (this.f = new Label("")).setVisible(false);
        layout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        (this.g = new Button(this.h.b("login.button"))).addActionListener(this);
        this.addKeyListener(this);
        panel.add(this.g);
        this.k = new EventQueue();
        this.setResizable(false);
        this.setModal(true);
        a(this);
        this.pack();
        this.show();
    }
    
    public void dispose() {
        super.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.g) {
            if (this.b.getText().length() == 0) {
                System.out.println("**** Tried to login without username");
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            try {
                this.setCursor(Cursor.getPredefinedCursor(3));
                this.g.setEnabled(false);
                this.e.setVisible(false);
                this.c.setVisible(false);
                this.c.setEnabled(false);
                this.d.setVisible(false);
                this.b.setVisible(false);
                this.b.setEnabled(false);
                this.f.setVisible(true);
                this.f.setText(this.h.b("login.logintext"));
                this.j = this.c.getText();
                this.i = this.b.getText();
                this.dispose();
            }
            catch (Exception ex) {
                System.out.println(ex.getClass().toString());
                this.b();
            }
        }
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        super.processWindowEvent(windowEvent);
        switch (windowEvent.getID()) {
            case 201: {
                this.dispose();
                break;
            }
        }
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
    
    public void b() {
        this.setCursor(Cursor.getDefaultCursor());
        this.g.setEnabled(true);
        this.e.setVisible(true);
        this.c.setVisible(true);
        this.c.setEnabled(true);
        this.d.setVisible(true);
        this.b.setVisible(true);
        this.b.setEnabled(true);
        this.f.setVisible(false);
        this.c.setText("");
        this.b.setText("");
        this.f.setText("");
    }
    
    public String c() {
        return this.j;
    }
    
    public String d() {
        return this.i;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.g) {
            this.k.postEvent(new MouseEvent(this.g, 501, System.currentTimeMillis(), 0, 5, 5, 1, false));
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.g && keyEvent.getKeyCode() == 10) {
            this.k.postEvent(new MouseEvent(this.g, 502, System.currentTimeMillis(), 0, 5, 5, 1, false));
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
