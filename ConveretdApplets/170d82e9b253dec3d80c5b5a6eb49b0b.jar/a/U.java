// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Point;
import java.awt.Font;
import com.spilka.client.muc.AppletAbstract;
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

public class U extends Panel implements dT, l
{
    public TextArea q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private ac q;
    private ai q;
    private cV q;
    
    public U(final cV q) {
        this.q = q;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        try {
            this.q = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t2) {
            this.q = new TextArea(2, 10);
        }
        this.q.setFont(cf.w.w());
        final t t = new t(this.q);
        this.q.setBackground((!ef.e || ef.w == 4) ? cf.w.y : cf.w.y.darker());
        this.q.setForeground(cf.w.t);
        boolean b;
        if (a.q == 1) {
            b = (q.q(69) || a.w || a.e);
        }
        else {
            b = (q.q(69) || a.w || a.e);
        }
        final Panel panel = new Panel(new GridBagLayout());
        if (b) {
            final String e = cf.w.e();
            if (this.q == null) {
                this.q = dI.q(e, "italic", "italicIcon.GIF", q);
            }
            if (this.t == null) {
                this.t = dI.q(e, "bold", "boldIcon.GIF", q);
            }
            if (this.w == null) {
                this.w = dI.q(e, "emotions", "emotionsIcon.GIF", q);
            }
            if (this.e == null) {
                this.e = dI.q(e, "shortcut", "shortcutIcon.GIF", q);
            }
            if (this.r == null) {
                this.r = dI.q(e, "messColor", "messColorIcon.gif", q, 12, 50);
            }
            final R r2;
            final R r = r2 = new R(25, 25, this.q, cf.w.w);
            final Image r3 = q.r("bi.gif", false);
            final R r4 = r;
            r.q = r3;
            r4.repaint();
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (cf.w.p()) {
                gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
                gridBagConstraints2.gridwidth = 0;
                if (q.q(69)) {
                    gridBagConstraints2.fill = 1;
                    panel.add(r2, gridBagConstraints2);
                    gridBagConstraints2.fill = 0;
                    gridBagConstraints2.gridwidth = 1;
                    gridBagConstraints2.anchor = 17;
                    panel.add(this.e, gridBagConstraints2);
                    gridBagConstraints2.gridwidth = 1;
                    panel.add(this.w, gridBagConstraints2);
                }
                else {
                    gridBagConstraints2.fill = 0;
                    gridBagConstraints2.anchor = 17;
                    panel.add(this.e, gridBagConstraints2);
                    gridBagConstraints2.gridwidth = 1;
                    panel.add(this.w, gridBagConstraints2);
                }
            }
            else {
                if (q.q(69)) {
                    if (q.q(69)) {
                        gridBagConstraints2.gridwidth = 1;
                        gridBagConstraints2.gridheight = 2;
                        gridBagConstraints2.weighty = 1.0;
                        panel.add(this.r, gridBagConstraints2);
                    }
                    gridBagConstraints2.weighty = 0.0;
                    gridBagConstraints2.gridheight = 1;
                    if (q.q(69)) {
                        gridBagConstraints2.gridx = 1;
                        gridBagConstraints2.gridy = 0;
                        panel.add(this.q, gridBagConstraints2);
                        gridBagConstraints2.gridx = 1;
                        gridBagConstraints2.gridy = 1;
                        panel.add(this.t, gridBagConstraints2);
                    }
                }
                if (a.w) {
                    gridBagConstraints2.gridx = 2;
                    gridBagConstraints2.gridy = 0;
                    panel.add(this.w, gridBagConstraints2);
                }
                if (a.e) {
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
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
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
                    if (!aw.q) {
                        final aw aw;
                        (aw = new aw(this.q, this.r, null)).q(this.q.getForeground());
                        aw.w();
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
                return eb.q("Enter your message here.");
            }
            if (o == this.w) {
                return eb.q("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return eb.q("Click here to insert shortcut into your message.");
            }
            if (o == this.r) {
                return eb.q("Click here to color your message.");
            }
            if (o == this.q) {
                return eb.q("Click here to make your message font italic.");
            }
            if (o == this.t) {
                return eb.q("Click here to make your message font bold.");
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
            return dR.q(s, new dR(this.q.getFont()).q.getStyle());
        }
        return "";
    }
    
    public static boolean q(final char c, final TextArea textArea) {
        if (AppletAbstract.q().q.q(69)) {
            final String selectedText = textArea.getSelectedText();
            if ("".equals(selectedText)) {
                final dR dr = new dR(textArea.getFont());
                Font font = null;
                Label_0196: {
                    if (c == 'i') {
                        switch (dr.q.getStyle()) {
                            case 0: {
                                font = dr.e;
                                break Label_0196;
                            }
                            case 2: {
                                font = dr.w;
                                break Label_0196;
                            }
                            case 1: {
                                font = dr.t;
                                break Label_0196;
                            }
                            case 3: {
                                font = dr.r;
                                break Label_0196;
                            }
                        }
                    }
                    if (c == 'b') {
                        switch (dr.q.getStyle()) {
                            case 0: {
                                font = dr.r;
                                break Label_0196;
                            }
                            case 2: {
                                font = dr.t;
                                break Label_0196;
                            }
                            case 1: {
                                font = dr.w;
                                break Label_0196;
                            }
                            case 3: {
                                font = dr.e;
                                break Label_0196;
                            }
                        }
                    }
                    font = dr.q;
                }
                textArea.setFont(font);
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
        if (a.w && !ac.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new ac(this, locationOnScreen)).q();
            this.q.w();
        }
    }
    
    private void r() {
        if (a.e && !ai.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new ai(this, locationOnScreen)).setVisible(true);
        }
    }
    
    public final void w() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    public final TextArea q() {
        return this.q;
    }
    
    public final cM q() {
        return this.q;
    }
}
