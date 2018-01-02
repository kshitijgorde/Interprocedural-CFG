// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Point;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Color;
import java.awt.Event;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Canvas;
import java.awt.TextArea;
import java.awt.Panel;

public class dw extends Panel implements aC, bf
{
    public TextArea q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private dF q;
    private O q;
    private ap q;
    
    public dw(final ap q) {
        this.q = q;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        try {
            this.q = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.q = new TextArea(2, 10);
        }
        this.q.setFont(bC.w.w());
        final bZ bz = new bZ(this.q);
        this.q.setBackground((!cK.e || cK.w == 4) ? bC.w.y : bC.w.y.darker());
        this.q.setForeground(bC.w.t);
        boolean b;
        if (dN.q == 1) {
            b = (dN.y || dN.w || dN.e || dN.r);
        }
        else {
            b = (dN.t || dN.q || dN.e || dN.r);
        }
        final Panel panel = new Panel(new GridBagLayout());
        if (b) {
            final String r = bC.w.r();
            if (this.q == null) {
                this.q = bi.q(r, "italic", "italicIcon.GIF", q);
            }
            if (this.t == null) {
                this.t = bi.q(r, "bold", "boldIcon.GIF", q);
            }
            if (this.w == null) {
                this.w = bi.q(r, "emotions", "emotionsIcon.GIF", q);
            }
            if (this.e == null) {
                this.e = bi.q(r, "shortcut", "shortcutIcon.GIF", q);
            }
            if (this.r == null) {
                this.r = bi.q(r, "messColor", "messColorIcon.gif", q, 12, 50);
            }
            final S s2;
            final S s = s2 = new S(25, 25, this.q, bC.w.w);
            final Image e = q.e("bi.gif", false);
            final S s3 = s;
            s.q = e;
            s3.repaint();
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (bC.w.s()) {
                gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
                gridBagConstraints2.gridwidth = 0;
                gridBagConstraints2.fill = 1;
                panel.add(s2, gridBagConstraints2);
                gridBagConstraints2.fill = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.anchor = 17;
                panel.add(this.e, gridBagConstraints2);
                gridBagConstraints2.gridwidth = 1;
                panel.add(this.w, gridBagConstraints2);
            }
            else {
                if ((dN.q == 0 && dN.t) || (dN.q == 1 && dN.y)) {
                    gridBagConstraints2.gridwidth = 1;
                    gridBagConstraints2.gridheight = 2;
                    gridBagConstraints2.weighty = 1.0;
                    panel.add(this.r, gridBagConstraints2);
                }
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.gridheight = 1;
                if ((dN.q == 0 && dN.q) || (dN.q == 1 && dN.w)) {
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.q, gridBagConstraints2);
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 1;
                    panel.add(this.t, gridBagConstraints2);
                }
                if (dN.e) {
                    gridBagConstraints2.gridx = 2;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.w, gridBagConstraints2);
                }
                if (dN.r) {
                    gridBagConstraints2.gridx = 2;
                    gridBagConstraints2.gridy = 1;
                    panel.add(this.e, gridBagConstraints2);
                }
            }
        }
        gridBagConstraints.insets = new Insets(2, 3, b ? 0 : 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        this.add(panel, gridBagConstraints);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.appendText("\n");
                    break;
                }
                if (event.modifiers != 2) {
                    break;
                }
                if (event.key == 18) {
                    this.r();
                    return true;
                }
                if (event.key == 5) {
                    this.e();
                    return true;
                }
                if (event.key == 9) {
                    q('i', this.q);
                    return true;
                }
                if (event.key == 2) {
                    q('b', this.q);
                    return true;
                }
                if (event.key == 10) {
                    this.q.appendText("\n");
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    q('i', this.q);
                    return true;
                }
                if (event.target == this.t) {
                    q('b', this.q);
                    return true;
                }
                if (event.target == this.w) {
                    this.e();
                    return true;
                }
                if (event.target == this.e) {
                    this.r();
                    return true;
                }
                if (event.target == this.r) {
                    if (!cM.q) {
                        final cM cm;
                        (cm = new cM(this.q, this.r, null)).q(this.q.getForeground());
                        cm.w();
                    }
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final String q(final Object o) {
        try {
            if (o == this.q) {
                return be.w("Enter your message here.");
            }
            if (o == this.w) {
                return be.w("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return be.w("Click here to insert shortcut into your message.");
            }
            if (o == this.r) {
                return be.w("Click here to color your message.");
            }
            if (o == this.q) {
                return be.w("Click here to make your message font italic.");
            }
            if (o == this.t) {
                return be.w("Click here to make your message font bold.");
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public final String q(final boolean b) {
        String s = this.q.getText().trim();
        if (b) {
            this.q.setText("");
        }
        if (s.length() > 0) {
            if (s.length() > 500) {
                s = s.substring(0, 500);
            }
            return new bu(this.q.getFont()).q(s);
        }
        return "";
    }
    
    public static boolean q(final char c, final TextArea textArea) {
        if ((dN.q == 0 && dN.q) || (dN.q == 1 && dN.w)) {
            final String selectedText = textArea.getSelectedText();
            if ("".equals(selectedText)) {
                textArea.setFont(new bu(textArea.getFont()).q(c));
                return true;
            }
            final String text;
            final int index;
            final int n = (index = (text = textArea.getText()).indexOf(selectedText)) + selectedText.length();
            textArea.setText(text.substring(0, index) + '[' + c + ']' + selectedText + '[' + '/' + c + ']' + text.substring(n));
            textArea.setSelectionStart(index + 3);
            textArea.setSelectionEnd(n + 3);
        }
        return false;
    }
    
    public final void q() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    private void e() {
        if (dN.e && !dF.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            this.q = new dF(this, locationOnScreen);
            final dF q;
            (q = this.q).setBackground(bC.w.q);
            final dT dt = new dT();
            final q q2 = new q();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label(be.w("Select emotion"));
            q.setResizable(false);
            q.setLayout(gridBagLayout);
            dt.setLayout(gridBagLayout);
            dt.setBackground(bC.w.i);
            dt.setForeground(bC.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 17;
            label.setFont(cb.q);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            dt.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(q2, gridBagConstraints);
            dt.add(q2);
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            (q.q = new aq(q)).setSize(350, 200);
            q.q();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            dt.add(q.q);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(dt, gridBagConstraints);
            q.add(dt);
            if (dF.q == null) {
                (dF.q = new Checkbox(be.w("Close after selection"))).setForeground(bC.w.u);
                dF.q.setState(true);
            }
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.gridwidth = -2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            gridBagLayout.setConstraints(dF.q, gridBagConstraints);
            q.add(dF.q);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            (q.q = new ad(be.w("Close"))).t();
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            q.add(q.q);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.weightx = 0.0;
            (q.w = new ad(be.w("Insert"))).t();
            gridBagLayout.setConstraints(q.w, gridBagConstraints);
            q.add(q.w);
            q.pack();
            this.q.w();
        }
    }
    
    private void r() {
        if (dN.r && !O.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new O(this, locationOnScreen)).setVisible(true);
        }
    }
    
    public final void w() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    public final TextArea q() {
        return this.q;
    }
    
    public final cs q() {
        return this.q;
    }
}
