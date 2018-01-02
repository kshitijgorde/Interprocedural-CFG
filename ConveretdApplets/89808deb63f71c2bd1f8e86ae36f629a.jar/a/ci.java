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

public class ci extends Panel implements aA, ai
{
    public TextArea q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private co q;
    private B q;
    private W q;
    
    public ci(final W q) {
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
        this.q.setFont(aU.w.w());
        final bd bd = new bd(this.q);
        this.q.setBackground((!bF.e || bF.w == 4) ? aU.w.y : aU.w.y.darker());
        this.q.setForeground(aU.w.t);
        boolean b;
        if (cu.q == 1) {
            b = (cu.y || cu.w || cu.e || cu.r);
        }
        else {
            b = (cu.t || cu.q || cu.e || cu.r);
        }
        final Panel panel = new Panel(new GridBagLayout());
        if (b) {
            final String e = aU.w.e();
            if (this.q == null) {
                this.q = aD.q(e, "italic", "italicIcon.GIF", q);
            }
            if (this.t == null) {
                this.t = aD.q(e, "bold", "boldIcon.GIF", q);
            }
            if (this.w == null) {
                this.w = aD.q(e, "emotions", "emotionsIcon.GIF", q);
            }
            if (this.e == null) {
                this.e = aD.q(e, "shortcut", "shortcutIcon.GIF", q);
            }
            if (this.r == null) {
                this.r = aD.q(e, "messColor", "messColorIcon.gif", q, 12, 50);
            }
            final E e3;
            final E e2 = e3 = new E(25, 25, this.q, aU.w.w);
            final Image e4 = q.e("bi.gif", false);
            final E e5 = e2;
            e2.q = e4;
            e5.repaint();
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (aU.w.a()) {
                gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
                gridBagConstraints2.gridwidth = 0;
                gridBagConstraints2.fill = 1;
                panel.add(e3, gridBagConstraints2);
                gridBagConstraints2.fill = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.anchor = 17;
                panel.add(this.e, gridBagConstraints2);
                gridBagConstraints2.gridwidth = 1;
                panel.add(this.w, gridBagConstraints2);
            }
            else {
                if ((cu.q == 0 && cu.t) || (cu.q == 1 && cu.y)) {
                    gridBagConstraints2.gridwidth = 1;
                    gridBagConstraints2.gridheight = 2;
                    gridBagConstraints2.weighty = 1.0;
                    panel.add(this.r, gridBagConstraints2);
                }
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.gridheight = 1;
                if ((cu.q == 0 && cu.q) || (cu.q == 1 && cu.w)) {
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.q, gridBagConstraints2);
                    gridBagConstraints2.gridx = 1;
                    gridBagConstraints2.gridy = 1;
                    panel.add(this.t, gridBagConstraints2);
                }
                if (cu.e) {
                    gridBagConstraints2.gridx = 2;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.w, gridBagConstraints2);
                }
                if (cu.r) {
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
        layout.setConstraints(bd, gridBagConstraints);
        this.add(bd);
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
                    if (!bG.q) {
                        final bG bg;
                        (bg = new bG(this.q, this.r, null)).q(this.q.getForeground());
                        bg.w();
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
                return ak.q("Enter your message here.");
            }
            if (o == this.w) {
                return ak.q("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return ak.q("Click here to insert shortcut into your message.");
            }
            if (o == this.r) {
                return ak.q("Click here to color your message.");
            }
            if (o == this.q) {
                return ak.q("Click here to make your message font italic.");
            }
            if (o == this.t) {
                return ak.q("Click here to make your message font bold.");
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
            return new aK(this.q.getFont()).q(s);
        }
        return "";
    }
    
    public static boolean q(final char c, final TextArea textArea) {
        if ((cu.q == 0 && cu.q) || (cu.q == 1 && cu.w)) {
            final String selectedText = textArea.getSelectedText();
            if ("".equals(selectedText)) {
                textArea.setFont(new aK(textArea.getFont()).q(c));
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
        if (cu.e && !co.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            this.q = new co(this, locationOnScreen);
            final co q;
            (q = this.q).setBackground(aU.w.q);
            final cy cy = new cy();
            final m m = new m();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label(ak.q("Select emotion"));
            q.setResizable(false);
            q.setLayout(gridBagLayout);
            cy.setLayout(gridBagLayout);
            cy.setBackground(aU.w.i);
            cy.setForeground(aU.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 17;
            label.setFont(bf.q);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            cy.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(m, gridBagConstraints);
            cy.add(m);
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            (q.q = new X(q)).setSize(350, 200);
            q.q();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            cy.add(q.q);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(cy, gridBagConstraints);
            q.add(cy);
            if (co.q == null) {
                (co.q = new Checkbox(ak.q("Close after selection"))).setForeground(aU.w.u);
                co.q.setState(true);
            }
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.gridwidth = -2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            gridBagLayout.setConstraints(co.q, gridBagConstraints);
            q.add(co.q);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 0;
            (q.q = new M(ak.q("Close"))).t();
            gridBagLayout.setConstraints(q.q, gridBagConstraints);
            q.add(q.q);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.weightx = 0.0;
            (q.w = new M(ak.q("Insert"))).t();
            gridBagLayout.setConstraints(q.w, gridBagConstraints);
            q.add(q.w);
            q.pack();
            this.q.w();
        }
    }
    
    private void r() {
        if (cu.r && !B.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new B(this, locationOnScreen)).setVisible(true);
        }
    }
    
    public final void w() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    public final TextArea q() {
        return this.q;
    }
    
    public final br q() {
        return this.q;
    }
}
