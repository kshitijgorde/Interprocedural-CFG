// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.util.Vector;

public class bg extends DigiItem
{
    public Vector a;
    public int b;
    public boolean c;
    public boolean d;
    public int e;
    
    public final void a(final String s) {
        if (s != null && s.length() > 0) {
            this.a.removeAllElements();
            final int n = 44;
            int n2 = 0;
            for (int i = s.indexOf(n, n2); i != -1; i = s.indexOf(n, n2)) {
                this.a.addElement(new Integer(s.substring(n2, i).trim()));
                n2 = i + 1;
            }
            this.a.addElement(new Integer(s.substring(n2, s.length()).trim()));
        }
        else {
            this.a.removeAllElements();
        }
    }
    
    public final Object h(final String s) {
        if ("categoryName".equals(s)) {
            String string = "";
            for (int i = 0; i < this.e; ++i) {
                string += "    ";
            }
            return string + this.getName();
        }
        return super.h(s);
    }
    
    public final String i(final String s) {
        String s2;
        if (this.d) {
            s2 = StringSubst.Substitute(LanguageSupport.translate("Currently displaying rooms for %1."), new String[] { this.getName() });
        }
        else {
            s2 = StringSubst.Substitute(LanguageSupport.translate("Click here to display the rooms for %1."), new String[] { this.getName() });
        }
        return s2;
    }
    
    public bg(final int n, final String s) {
        super(n, s);
        this.a = new Vector();
        this.b = -999;
        this.c = false;
        this.d = false;
        this.e = -1;
    }
}
