// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Insets;
import java.awt.Dimension;
import java.net.MalformedURLException;
import ABLwidgets.abljem;
import java.net.URL;
import ABLwidgets.menu_item;
import java.awt.Event;
import ABLwidgets.utils;
import ABLwidgets.web_context;
import java.awt.Panel;

public class StyleTarget extends Panel
{
    public EmuTarget a;
    public web_context b;
    String c;
    String d;
    
    void a(final String s, final String s2) {
        this.d = utils.b(this.d, s, s2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o == this) {
            return this.a != null && this.a.a(event);
        }
        return super.action(event, o);
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        try {
            String s = this.c;
            String s2 = this.d;
            String s3 = null;
            if (event.arg instanceof menu_item) {
                s3 = ((menu_item)event.arg).c;
            }
            if (event.arg instanceof StyleEventArg) {
                final StyleEventArg styleEventArg = (StyleEventArg)event.arg;
                if (styleEventArg.b > 0) {
                    return this.a != null && this.a.handleEvent(new Event(this, event.when, event.id, event.x, event.y, event.key, event.modifiers, styleEventArg));
                }
                s3 = styleEventArg.a;
            }
            final StyleEventArg styleEventArg2 = new StyleEventArg(s3);
            styleEventArg2.d = event.target;
            if (s3 != null && s3.length() > 7) {
                final String upperCase = s3.substring(0, 7).toUpperCase();
                if (upperCase.equals("LNKVAR:")) {
                    s2 = utils.f(s2, s3.substring(7));
                    b = true;
                }
                if (upperCase.equals("LNKURL:")) {
                    s = s3.substring(7);
                    b = true;
                }
            }
            if (b) {
                if (this.a != null) {
                    s2 = utils.f(s2, this.a.j());
                }
                final String g = utils.g(s, s2);
                try {
                    final URL url = new URL(this.b.c(), g);
                    if (url.getProtocol().equals("file")) {
                        abljem.b("Full StyleTarget URL=" + url);
                    }
                    this.b.b(url, "GuiStyleFrame");
                }
                catch (MalformedURLException ex) {
                    abljem.b("Malformed StyleTarget URL=" + g);
                }
                return true;
            }
            return this.a != null && this.a.handleEvent(new Event(this, event.when, event.id, event.x, event.y, event.key, event.modifiers, styleEventArg2));
        }
        catch (Throwable t) {
            t.printStackTrace();
            return true;
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        return this.a != null && this.a.keyDown(event, n);
    }
    
    public Dimension getSize() {
        if (this.a == null) {
            return new Dimension(0, 0);
        }
        return this.a.getSize();
    }
    
    public Insets getInsets() {
        if (this.a == null) {
            return new Insets(0, 0, 0, 0);
        }
        return this.a.getInsets();
    }
}
