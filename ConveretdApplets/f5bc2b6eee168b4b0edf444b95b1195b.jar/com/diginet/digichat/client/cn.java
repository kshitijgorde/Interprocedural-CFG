// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.c3;
import com.diginet.digichat.awt.a9;
import java.awt.Event;
import com.esial.util.LanguageSupport;
import java.awt.Color;
import com.diginet.digichat.exceptions.c0;
import java.awt.Font;
import com.diginet.digichat.util.q;
import com.diginet.digichat.awt.co;
import java.awt.Component;
import com.diginet.digichat.awt.al;

public class cn extends al
{
    private Component a;
    private Component b;
    private Component c;
    private Component d;
    private Component e;
    private Component f;
    private Component g;
    private Component h;
    protected co i;
    protected co j;
    protected co k;
    private g l;
    
    public final void a() throws c0 {
        final Font font = new Font(q.a(this.a), q.b(this.h), Integer.parseInt(q.a(this.g)));
        this.l.df.normalMessages = q.d(this.b);
        this.l.df.flaggedMessages = q.d(this.d);
        this.l.df.normalBackground = q.d(this.c);
        this.l.df.privateBackground = q.d(this.f);
        this.l.df.privateMessages = q.d(this.e);
        this.l.a(font);
    }
    
    public final void b() {
        final Font font = this.l.df.getFont();
        final String fontName = this.l.df.fontName;
        final int fontSize = this.l.df.fontSize;
        final int fontStyle = this.l.df.fontStyle;
        final Font font2 = new Font(fontName, fontStyle | 0x1, fontSize);
        q.c(this.a, fontName);
        q.c(this.g, String.valueOf(fontSize));
        q.a(this.h, fontStyle);
        q.a(this.b, this.l.df.normalMessages);
        q.a(this.e, this.l.df.privateMessages);
        q.a(this.f, this.l.df.privateBackground);
        q.a(this.c, this.l.df.normalBackground);
        q.a(this.d, this.l.df.flaggedMessages);
        this.i.a(font, font);
        this.i.setBackground(this.l.df.normalBackground);
        this.i.a(this.l.df.normalMessages, Color.black);
        this.j.a(font2, font2);
        if (this.l.c0) {
            this.j.setBackground(this.l.df.flaggedMessages);
            this.j.a(this.l.df.normalMessages, Color.black);
        }
        else {
            this.j.setBackground(this.l.df.normalBackground);
            this.j.a(this.l.df.flaggedMessages, Color.black);
        }
        this.k.a(font2, font2);
        this.k.setBackground(this.l.df.privateBackground);
        this.k.a(this.l.df.privateMessages, Color.black);
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return LanguageSupport.translate("Select a font for chat messages.");
        }
        if (o == this.g) {
            return LanguageSupport.translate("Select a font size for chat messages.");
        }
        if (o == this.h) {
            return LanguageSupport.translate("Select a font style for chat messages.");
        }
        if (o == this.b) {
            return LanguageSupport.translate("Select a font color for chat messages.");
        }
        if (o == this.c) {
            return LanguageSupport.translate("Select a background color for chat messages.");
        }
        if (o == this.d) {
            if (this.l.c0) {
                return LanguageSupport.translate("Select a background color for messages from flagged users.");
            }
            return LanguageSupport.translate("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.e) {
                return LanguageSupport.translate("Select a color for private messages.");
            }
            if (o == this.f) {
                return LanguageSupport.translate("Select a background color for private messages.");
            }
            if (o == this.i) {
                return LanguageSupport.translate("Preview your font settings here.");
            }
            return null;
        }
    }
    
    public final boolean action(final Event event, final Object o) {
        final int int1 = Integer.parseInt(q.a(this.g));
        final int b = q.b(this.h);
        final String a = q.a(this.a);
        final Font font = new Font(a, b | 0x1, int1);
        this.i.a(new Font(a, b, int1), new Font(a, b, int1));
        this.i.setBackground(q.d(this.c));
        this.i.a(q.d(this.b), Color.black);
        this.j.a(font, font);
        if (this.l.c0) {
            this.j.setBackground(q.d(this.d));
            this.j.a(q.d(this.b), Color.black);
        }
        else {
            this.j.setBackground(q.d(this.c));
            this.j.a(q.d(this.d), Color.black);
        }
        this.k.a(font, font);
        this.k.setBackground(q.d(this.f));
        this.k.a(q.d(this.e), Color.black);
        return true;
    }
    
    public cn(final g l) {
        super(LanguageSupport.translate("Fonts"), l);
        this.b = q.d();
        this.c = q.d();
        this.d = q.d();
        this.e = q.d();
        this.f = q.d();
        this.g = q.b();
        this.h = q.b();
        this.i = new co(true);
        this.j = new co(true);
        this.k = new co(true);
        this.l = l;
        this.a = q.e();
        this.i.a(LanguageSupport.translate("Sample Message Text"), null);
        this.j.a(LanguageSupport.translate("Sample Flagged Users Text"), null);
        this.k.a(LanguageSupport.translate("Sample Private Message Text"), null);
        final a9 a9 = new a9(2, 2, 2, 2);
        a9.add("North", this.i);
        a9.add("Center", this.j);
        a9.add("South", this.k);
        q.b(this.g, "9");
        q.b(this.g, "10");
        q.b(this.g, "12");
        q.b(this.g, "14");
        q.b(this.g, "16");
        q.b(this.g, "18");
        q.b(this.g, "20");
        q.b(this.g, "22");
        q.b(this.g, "24");
        q.b(this.h, LanguageSupport.translate("Plain"));
        q.b(this.h, LanguageSupport.translate("Bold"));
        q.b(this.h, LanguageSupport.translate("Italic"));
        q.b(this.h, LanguageSupport.translate("Bold Italic"));
        this.a(LanguageSupport.translate("Font"), this.a);
        this.a(LanguageSupport.translate("Size"), this.g);
        this.a(LanguageSupport.translate("Style"), this.h);
        this.a(LanguageSupport.translate("Normal Message"), this.b);
        if (l.c0) {
            this.a(LanguageSupport.translate("Flagged User Background"), this.d);
        }
        else {
            this.a(LanguageSupport.translate("Flagged User"), this.d);
        }
        this.a(LanguageSupport.translate("Private Message"), this.e);
        this.a(LanguageSupport.translate("Private Message Background"), this.f);
        this.a(LanguageSupport.translate("Background"), this.c);
        this.a(a9, 2);
        this.h.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.h.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.g.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.g.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.b.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.b.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.c.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.c.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.d.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.d.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.e.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.e.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.f.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.f.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        this.a.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.a.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
    }
}
