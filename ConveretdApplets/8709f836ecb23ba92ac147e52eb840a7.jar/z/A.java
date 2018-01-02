// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public final class A
{
    private Hashtable a;
    private int b;
    
    public A(final InputStream inputStream) {
        this.b = 1;
        this.a = new Hashtable();
        try {
            this.a(inputStream);
            System.out.println("Parsed " + this.a.size() + " styles");
        }
        catch (IOException ex2) {
            final IOException ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException("Could not read stylesheet", ex);
        }
        catch (W w2) {
            final W w = w2;
            w2.printStackTrace();
            throw new RuntimeException("Stylesheet: " + w.toString());
        }
    }
    
    public final String a(final String s, final String s2) {
        if (!this.a.containsKey(s)) {
            throw new RuntimeException("Unknown style: " + s);
        }
        return this.a.get(s).get(s2);
    }
    
    private void a(final InputStream inputStream) {
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        String s;
        while ((s = this.a(inputStreamReader, "{")).length() != 0) {
            if (s.charAt(0) == '.') {
                s = s.substring(1);
            }
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            this.a.put(s, hashtable);
            String a;
            while ((a = this.a(inputStreamReader, ":}")).length() != 0) {
                final String a2;
                if ((a2 = this.a(inputStreamReader, ";")).length() == 0) {
                    throw new W(this.b);
                }
                if (ap.b(a2)) {
                    ap.c(a2);
                }
                hashtable.put(a, a2);
            }
        }
    }
    
    private String a(final InputStreamReader inputStreamReader, final String s) {
        final StringBuffer sb = new StringBuffer();
        int a;
        while ((a = this.a(inputStreamReader)) != -1) {
            final char c = (char)a;
            if (s.indexOf(c) != -1) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    private int a(final InputStreamReader inputStreamReader) {
        int read;
        while ((read = inputStreamReader.read()) != -1) {
            final char c;
            if ((c = (char)read) == '\n') {
                ++this.b;
            }
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n' && read >= 32) {
                return read;
            }
        }
        return read;
    }
}
