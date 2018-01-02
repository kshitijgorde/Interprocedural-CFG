// 
// Decompiled by Procyon v0.5.30
// 

package orcagroup.game;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

class MsgBox extends Dialog implements ActionListener
{
    boolean id;
    Button ok;
    Button can;
    
    MsgBox(final Frame frame, final String msg, final boolean okcan) {
        super(frame, "Message", true);
        this.id = false;
        this.setLayout(new BorderLayout());
        this.add("Center", new Label(msg));
        this.addOKCancelPanel(okcan);
        this.createFrame();
        this.pack();
        this.setVisible(true);
    }
    
    void addOKCancelPanel(final boolean okcan) {
        final Panel p = new Panel();
        p.setLayout(new FlowLayout());
        this.createOKButton(p);
        if (okcan) {
            this.createCancelButton(p);
        }
        this.add("South", p);
    }
    
    void createOKButton(final Panel p) {
        p.add(this.ok = new Button("OK"));
        this.ok.addActionListener(this);
    }
    
    void createCancelButton(final Panel p) {
        p.add(this.can = new Button("Cancel"));
        this.can.addActionListener(this);
    }
    
    void createFrame() {
        final Dimension d = this.getToolkit().getScreenSize();
        this.setLocation(d.width / 3, d.height / 3);
    }
    
    @Override
    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == this.ok) {
            this.id = true;
            this.setVisible(false);
        }
        else if (ae.getSource() == this.can) {
            this.setVisible(false);
        }
    }
}
