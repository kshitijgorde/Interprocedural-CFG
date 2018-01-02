// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataInputStream;
import java.net.URL;

public class be
{
    public static o q;
    
    public be() {
    }
    
    public static String q(final String s) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(m.q().getCodeBase(), s).openStream());
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                sb.append(line);
            }
            dataInputStream.close();
            return sb.toString();
        }
        catch (Exception ex) {
            System.out.println("got error:" + ex.getMessage());
            return "";
        }
    }
    
    public static String w(String s) {
        if (be.q == null) {
            return s;
        }
        s = new String(s);
        final String q;
        if ((q = be.q.q(s)) == null) {
            return new String(s);
        }
        return q;
    }
    
    public be(final o q) {
        be.q = q;
    }
}
