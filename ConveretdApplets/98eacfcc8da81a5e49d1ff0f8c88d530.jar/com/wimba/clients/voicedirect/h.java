// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import javax.swing.text.BadLocationException;
import java.awt.Color;
import javax.swing.text.StyledDocument;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import com.hw.client.util.a;
import java.awt.Insets;
import VT_6_1_0_11.aq;
import java.util.Hashtable;
import javax.swing.text.SimpleAttributeSet;
import java.util.Random;
import javax.swing.text.AbstractDocument;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public final class h extends JScrollPane
{
    private JTextPane a;
    private AbstractDocument b;
    private Random c;
    private SimpleAttributeSet[] d;
    private Hashtable e;
    
    public h() {
        (this.a = new aq()).setCaretPosition(0);
        this.a.setMargin(new Insets(5, 5, 5, 5));
        this.a.setEditable(false);
        final StyledDocument styledDocument;
        if ((styledDocument = this.a.getStyledDocument()) instanceof AbstractDocument) {
            this.b = (AbstractDocument)styledDocument;
        }
        else {
            com.hw.client.util.a.e("ChatArea: text pane's document isn't an AbstractDocument!");
        }
        this.c = new Random(System.currentTimeMillis());
        this.d = new SimpleAttributeSet[3];
        StyleConstants.setFontFamily(this.d[0] = new SimpleAttributeSet(), VT_6_1_0_11.h.a());
        StyleConstants.setFontSize(this.d[0], 12);
        StyleConstants.setBold(this.d[1] = new SimpleAttributeSet(this.d[0]), true);
        StyleConstants.setItalic(this.d[2] = new SimpleAttributeSet(this.d[0]), true);
        this.e = new Hashtable();
        this.setViewportView(this.a);
    }
    
    public final void a(final String s, final String s2) {
        try {
            if (s != null && s.length() > 0) {
                final SimpleAttributeSet set = this.d[1];
                Color color;
                if ((color = this.e.get(s)) == null) {
                    color = new Color(this.a(), this.a(), this.a());
                    this.e.put(s, color);
                }
                StyleConstants.setForeground(set, color);
                this.b.insertString(this.b.getLength(), s + ": ", this.d[1]);
                this.b.insertString(this.b.getLength(), s2 + "\n", this.d[0]);
            }
            else {
                this.b.insertString(this.b.getLength(), s2 + "\n", this.d[2]);
            }
            this.a.setCaretPosition(this.b.getLength());
        }
        catch (BadLocationException ex) {
            com.hw.client.util.a.b("ChatArea.addText: BadLocationException", ex);
        }
    }
    
    private int a() {
        return Math.abs(this.c.nextInt()) % 200;
    }
}
