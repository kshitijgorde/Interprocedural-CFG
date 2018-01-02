// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Insets;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.Panel;

public class cz extends Panel implements dc
{
    private static final String xe = "";
    private static final int yt = 6;
    private aq wc;
    private GridBagLayout gk;
    private cx if;
    private TextField ff;
    private TextField re;
    private TextField el;
    private Button ly;
    private ab nb;
    private boolean my;
    
    public cz(final aq wc) {
        this.gk = new GridBagLayout();
        this.ff = new TextField();
        this.re = new TextField();
        this.el = new TextField();
        this.ly = new Button();
        this.my = true;
        this.wc = wc;
        this.nb = new ab(wc.lb);
        WebVolanoChat.ig(this, wc);
        WebVolanoChat.ig(this.ff, wc);
        WebVolanoChat.ig(this.re, wc);
        WebVolanoChat.ig(this.el, wc);
        WebVolanoChat.ig(this.ly, wc);
        WebVolanoChat.ig(this.nb, wc);
        final Canvas canvas = new Canvas();
        this.setLayout(this.gk);
        this.ly.setLabel(wc.fq);
        this.nb.ob("Copyright © 1996-2001 Volano LLC. All rights reserved.");
        this.ff.setText(wc.ff);
        this.re.setText(wc.re);
        this.el.setText(wc.el);
        this.el.setEchoCharacter('*');
        this.if = new cx(this.ff);
        if (wc.se) {
            this.if.sc(this.el);
            if (wc.fm) {
                this.if.sc(this.re);
            }
        }
        else {
            this.if.sc(this.re);
        }
        this.if.sc(this.ly);
        this.sc(canvas, 0, 0, 3, true, true);
        this.sc(new Label(wc.se ? wc.in : wc.zp), 0, 1, 1, false, false);
        this.sc(this.ff, 1, 1, 1, true, false);
        this.sc(this.ly, 2, 1, 1, false, false);
        int n = 3;
        if (wc.se) {
            this.sc(new Label(wc.jn), 0, 2, 1, false, false);
            this.sc(this.el, 1, 2, 2, true, false);
            if (wc.fm) {
                this.sc(new Label(wc.dq), 0, 3, 1, false, false);
                this.sc(this.re, 1, 3, 2, true, false);
                n = 4;
            }
        }
        else {
            this.sc(new Label(wc.dq), 0, 2, 1, false, false);
            this.sc(this.re, 1, 2, 2, true, false);
        }
        this.sc(this.nb, 0, n, 3, true, false);
        this.sc(this.if, 0, n + 1, 3, true, true);
    }
    
    public void sc(final Component component, final int gridx, final int gridy, final int gridwidth, final boolean b, final boolean b2) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = (b ? 1 : 0);
        gridBagConstraints.weighty = (b2 ? 1 : 0);
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = 1;
        this.gk.setConstraints(component, gridBagConstraints);
        this.add(component);
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.ff.getText().equals("")) {
            this.my = true;
            this.ff.requestFocus();
            if (this.wc.se) {
                this.nb.ob(this.wc.ls);
            }
            else {
                this.nb.ob(this.wc.js);
            }
            return true;
        }
        if (this.wc.se && this.el.getText().equals("")) {
            this.my = true;
            this.el.requestFocus();
            this.nb.ob(this.wc.ms);
            return true;
        }
        this.ye(this.ff, this.wc.on);
        this.ye(this.re, this.wc.mn);
        return false;
    }
    
    private void ye(final TextField textField, final int n) {
        if (textField.getText().length() > n) {
            textField.setText(WebVolanoChat.ye(textField.getText(), n));
        }
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (this.my) {
            this.my = false;
        }
        else if (event.target == this.ff) {
            if (this.wc.se) {
                this.nb.ob(this.wc.gs);
            }
            else {
                this.nb.ob(this.wc.zr);
            }
        }
        else if (event.target == this.el) {
            this.nb.ob(this.wc.hs);
        }
        else if (event.target == this.re) {
            this.nb.ob(this.wc.ds);
        }
        else if (event.target == this.ly) {
            this.nb.ob(this.wc.es);
        }
        this.if.mi((Component)event.target);
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        if (n == 9) {
            if (event.shiftDown()) {
                this.if.pi();
            }
            else {
                this.if.oi();
            }
            b = true;
        }
        else if (n == 10 || n == 13) {
            this.postEvent(new Event(this.ly, 1001, ""));
            b = true;
        }
        return b;
    }
    
    public void mb(final String s) {
        this.my = true;
        this.nb.ob(s);
    }
    
    public String jc() {
        return this.ff.getText();
    }
    
    public String rb() {
        return this.re.getText();
    }
    
    public String xg() {
        return this.el.getText();
    }
    
    public Insets insets() {
        return new Insets(6, 6, 6, 6);
    }
    
    public void kk(final boolean editable) {
        this.ff.setEditable(editable);
    }
    
    public void ik(final boolean editable) {
        this.re.setEditable(editable);
    }
    
    public void jk(final boolean editable) {
        this.el.setEditable(editable);
    }
}
