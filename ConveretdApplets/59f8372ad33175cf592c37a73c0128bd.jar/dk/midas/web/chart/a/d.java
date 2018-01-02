// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.a;

import java.net.MalformedURLException;
import java.util.Enumeration;
import java.net.URL;
import java.util.Hashtable;

public class d
{
    private String if;
    private Hashtable a;
    
    public d() {
        this.a = new Hashtable();
    }
    
    public d(final String if1) {
        this.a = new Hashtable();
        this.if = if1;
    }
    
    public String if() {
        return this.if;
    }
    
    public void a(final String if1) {
        this.if = if1;
    }
    
    public String do(final String s) {
        return this.a.get(s);
    }
    
    public void a(final String s, final String s2) {
        this.a.put(s, s2);
    }
    
    public void a(final String s, final int n) {
        this.a.put(s, "" + n);
    }
    
    public void a(final String s, final long n) {
        this.a.put(s, "" + n);
    }
    
    public URL a() throws MalformedURLException {
        final StringBuffer sb = new StringBuffer(this.if());
        for (int i = 0; i < sb.length(); ++i) {
            final char char1 = sb.charAt(i);
            switch (char1) {
                case 32: {
                    sb.setCharAt(i, '%');
                    sb.insert(i + 1, this.a(char1));
                    break;
                }
            }
        }
        int n = 1;
        final Enumeration<String> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append((n != 0) ? "?" : "&").append(s).append("=").append(this.if((String)this.a.get(s)));
            n = 0;
        }
        return new URL(sb.toString());
    }
    
    private String a(final char c) {
        return Integer.toHexString(c);
    }
    
    private String if(final String s) {
        return s;
    }
}
