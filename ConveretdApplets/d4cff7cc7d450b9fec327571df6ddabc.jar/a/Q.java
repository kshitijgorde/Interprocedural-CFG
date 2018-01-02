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

public class Q extends Panel implements cn, j
{
    public TextArea q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private S q;
    private X q;
    private bI q;
    
    public Q(final bI q) {
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
        this.q.setFont(be.w.w());
        final r r = new r(this.q);
        this.q.setBackground((!cx.e || cx.w == 4) ? be.w.y : be.w.y.darker());
        this.q.setForeground(be.w.t);
        boolean b;
        if (a.q == 1) {
            b = (q.q(69) || a.w || a.e);
        }
        else {
            b = (q.q(69) || a.w || a.e);
        }
        final Panel panel = new Panel(new GridBagLayout());
        if (b) {
            final String w = be.w.w();
            if (this.q == null) {
                this.q = cc.q(w, "italic", "italicIcon.GIF", q);
            }
            if (this.t == null) {
                this.t = cc.q(w, "bold", "boldIcon.GIF", q);
            }
            if (this.w == null) {
                this.w = cc.q(w, "emotions", "emotionsIcon.GIF", q);
            }
            if (this.e == null) {
                this.e = cc.q(w, "shortcut", "shortcutIcon.GIF", q);
            }
            if (this.r == null) {
                this.r = cc.q(w, "messColor", "messColorIcon.gif", q, 12, 50);
            }
            final O o2;
            final O o = o2 = new O(25, 25, this.q, be.w.w);
            final Image r2 = q.r("bi.gif", false);
            final O o3 = o;
            o.q = r2;
            o3.repaint();
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (be.w.p()) {
                gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
                gridBagConstraints2.gridwidth = 0;
                if (q.q(69)) {
                    gridBagConstraints2.fill = 1;
                    panel.add(o2, gridBagConstraints2);
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
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
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
                    if (!ah.q) {
                        final ah ah;
                        (ah = new ah(this.q, this.r, null)).q(this.q.getForeground());
                        ah.w();
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
                return cv.q("Enter your message here.");
            }
            if (o == this.w) {
                return cv.q("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return cv.q("Click here to insert shortcut into your message.");
            }
            if (o == this.r) {
                return cv.q("Click here to color your message.");
            }
            if (o == this.q) {
                return cv.q("Click here to make your message font italic.");
            }
            if (o == this.t) {
                return cv.q("Click here to make your message font bold.");
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
            return cl.q(s, new cl(this.q.getFont()).q.getStyle());
        }
        return "";
    }
    
    public static boolean q(final char c, final TextArea textArea) {
        if (AppletAbstract.q().q.q(69)) {
            final String selectedText = textArea.getSelectedText();
            if ("".equals(selectedText)) {
                final cl cl = new cl(textArea.getFont());
                Font font = null;
                Label_0196: {
                    if (c == 'i') {
                        switch (cl.q.getStyle()) {
                            case 0: {
                                font = cl.e;
                                break Label_0196;
                            }
                            case 2: {
                                font = cl.w;
                                break Label_0196;
                            }
                            case 1: {
                                font = cl.t;
                                break Label_0196;
                            }
                            case 3: {
                                font = cl.r;
                                break Label_0196;
                            }
                        }
                    }
                    if (c == 'b') {
                        switch (cl.q.getStyle()) {
                            case 0: {
                                font = cl.r;
                                break Label_0196;
                            }
                            case 2: {
                                font = cl.t;
                                break Label_0196;
                            }
                            case 1: {
                                font = cl.w;
                                break Label_0196;
                            }
                            case 3: {
                                font = cl.e;
                                break Label_0196;
                            }
                        }
                    }
                    font = cl.q;
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
        if (a.w && !S.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new S(this, locationOnScreen)).q();
            this.q.w();
        }
    }
    
    private void r() {
        if (a.e && !X.q) {
            final Point locationOnScreen = this.getLocationOnScreen();
            this.getSize();
            final Point point = locationOnScreen;
            point.y -= 200;
            final Point point2 = locationOnScreen;
            point2.x += 400;
            (this.q = new X(this, locationOnScreen)).setVisible(true);
        }
    }
    
    public final void w() {
        this.q.hide();
        this.q.requestFocus();
    }
    
    public final TextArea q() {
        return this.q;
    }
    
    public final bz q() {
        return this.q;
    }
}
