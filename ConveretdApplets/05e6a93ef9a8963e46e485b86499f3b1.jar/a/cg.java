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

public class cg extends Panel implements aB, aj
{
    public TextArea q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private cm q;
    private C q;
    private W q;
    
    public cg(final W q) {
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
        this.q.setFont(aT.w.w());
        final bc bc = new bc(this.q);
        this.q.setBackground((!bE.e || bE.w == 4) ? aT.w.y : aT.w.y.darker());
        this.q.setForeground(aT.w.t);
        boolean b;
        if (cs.q == 1) {
            b = (cs.y || cs.w || cs.e || cs.r);
        }
        else {
            b = (cs.t || cs.q || cs.e || cs.r);
        }
        final Panel panel = new Panel(new GridBagLayout());
        if (b) {
            final String e = aT.w.e();
            if (this.q == null) {
                this.q = aE.q(e, "italic", "italicIcon.GIF", q);
            }
            if (this.t == null) {
                this.t = aE.q(e, "bold", "boldIcon.GIF", q);
            }
            if (this.w == null) {
                this.w = aE.q(e, "emotions", "emotionsIcon.GIF", q);
            }
            if (this.e == null) {
                this.e = aE.q(e, "shortcut", "shortcutIcon.GIF", q);
            }
            if (this.r == null) {
                this.r = aE.q(e, "messColor", "messColorIcon.gif", q, 12, 50);
            }
            final F f2;
            final F f = f2 = new F(25, 25, this.q, aT.w.w);
            final Image e2 = q.e("bi.gif", false);
            final F f3 = f;
            f.q = e2;
            f3.repaint();
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (aT.w.a()) {
                gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
                gridBagConstraints2.gridwidth = 0;
                gridBagConstraints2.fill = 1;
                panel.add(f2, gridBagConstraints2);
                gridBagConstraints2.fill = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.anchor = 17;
                panel.add(this.e, gridBagConstraints2);
                gridBagConstraints2.gridwidth = 1;
                panel.add(this.w, gridBagConstraints2);
            }
            else {
                if ((cs.q == 0 && cs.t) || (cs.q == 1 && cs.y)) {
                    gridBagConstraints2.gridwidth = 1;
                    gridBagConstraints2.gridheight = 2;
                    gridBagConstraints2.weighty = 1.0;
                    panel.add(this.r, gridBagConstraints2);
                }
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.gridheight = 1;
                if ((cs.q == 0 && cs.q) || (cs.q == 1 && cs.w)) {
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.q, gridBagConstraints2);
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 1;
                    panel.add(this.t, gridBagConstraints2);
                }
                if (cs.e) {
                    gridBagConstraints2.gridx = 2;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.w, gridBagConstraints2);
                }
                if (cs.r) {
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
        layout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
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
                    if (!bF.q) {
                        final bF bf;
                        (bf = new bF(this.q, this.r, null)).q(this.q.getForeground());
                        bf.w();
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
                return al.q("Enter your message here.");
            }
            if (o == this.w) {
                return al.q("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return al.q("Click here to insert shortcut into your message.");
            }
            if (o == this.r) {
                return al.q("Click here to color your message.");
            }
            if (o == this.q) {
                return al.q("Click here to make your message font italic.");
            }
            if (o == this.t) {
                return al.q("Click here to make your message font bold.");
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
            return new aL(this.q.getFont()).q(s);
        }
        return "";
    }
    
    public static boolean q(final char c, final TextArea textArea) {
        if ((cs.q == 0 && cs.q) || (cs.q == 1 && cs.w)) {
            final String selectedText = textArea.getSelectedText();
            if ("".equals(selectedText)) {
                textArea.setFont(new aL(textArea.getFont()).q(c));
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
        if (cs.e && !cm.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            this.q = new cm(this.q.q.q(), this, locationOnScreen);
            final cm q;
            (q = this.q).setBackground(aT.w.q);
            final cv cv = new cv();
            final m m = new m();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label(al.q("Select emotion"));
            q.setResizable(false);
            q.setLayout(gridBagLayout);
            cv.setLayout(gridBagLayout);
            cv.setBackground(aT.w.i);
            cv.setForeground(aT.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 17;
            label.setFont(be.q);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            cv.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(m, gridBagConstraints);
            cv.add(m);
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            (q.q = new Y(q)).setSize(350, 200);
            q.q();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            cv.add(q.q);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(cv, gridBagConstraints);
            q.add(cv);
            if (cm.q == null) {
                (cm.q = new Checkbox(al.q("Close after selection"))).setForeground(aT.w.u);
                cm.q.setState(true);
            }
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.gridwidth = -2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            gridBagLayout.setConstraints(cm.q, gridBagConstraints);
            q.add(cm.q);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            (q.q = new N(al.q("Close"))).t();
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            q.add(q.q);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.weightx = 0.0;
            (q.w = new N(al.q("Insert"))).t();
            gridBagLayout.setConstraints(q.w, gridBagConstraints);
            q.add(q.w);
            q.pack();
            this.q.w();
        }
    }
    
    private void r() {
        if (cs.r && !C.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new C(this.q.q.q(), this, locationOnScreen)).setVisible(true);
        }
    }
    
    public final void w() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    public final TextArea q() {
        return this.q;
    }
    
    public final bq q() {
        return this.q;
    }
}