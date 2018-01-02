// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.util.Vector;
import java.io.IOException;

public class b extends c
{
    public String a;
    
    public void a(final String s, final String s2, final String s3, final String s4) throws IOException {
        this.c("USER " + s + " " + s2 + " " + s4 + " :" + s3);
    }
    
    public void a(final String s, final String s2) throws IOException {
        this.a("NOTICE", s, ":" + s2);
    }
    
    public String[] a() throws IOException {
        final String line = super.c.readLine();
        if (line == null) {
            return null;
        }
        return this.d(line);
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5) throws IOException {
        if (s5 != null) {
            this.e(s5);
        }
        this.a(s);
        this.a(s2, s3, s4, this.a);
    }
    
    public b(final String a, final int n) throws IOException {
        super(a, n);
        this.a = a;
    }
    
    public b() {
    }
    
    public void a(final String s) throws IOException {
        this.b("NICK", s);
    }
    
    public void b(final String s) throws IOException {
        this.b("JOIN", s);
    }
    
    public void b() throws IOException {
        this.c("QUIT");
    }
    
    public void c(final String s) throws IOException {
        this.f(s + "\r\n");
    }
    
    public void b(final String s, final String s2) throws IOException {
        this.c(s + " " + s2);
    }
    
    public void a(final String s, final String s2, final String s3) throws IOException {
        this.c(s + " " + s2 + " " + s3);
    }
    
    public String[] d(final String s) {
        int n = 0;
        final Vector vector = new Vector<String>();
        if (s.charAt(0) != ':') {
            vector.addElement(null);
        }
        while (true) {
            do {
                final int index = s.indexOf(32, n);
                if (index == -1) {
                    vector.addElement(s.substring(n));
                    final String[] array = new String[vector.size()];
                    vector.copyInto(array);
                    return array;
                }
                vector.addElement(s.substring(n, index));
                for (n = index; n < s.length() && s.charAt(n) == ' '; ++n) {}
            } while (s.charAt(n) != ':');
            vector.addElement(s.substring(n));
            continue;
        }
    }
    
    public void e(final String s) throws IOException {
        this.b("PASS", s);
    }
}
