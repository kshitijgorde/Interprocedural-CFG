// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Hashtable;

public class aA
{
    Hashtable q;
    
    public aA() {
    }
    
    public static String q(final String s) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(h.q().getCodeBase(), s).openStream());
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
    
    public aA(final String s) {
        this.q = new Hashtable();
    }
    
    public void q(final String s, final Image image) {
        this.q.put(s, image);
    }
    
    public Image q(final String s) {
        return this.q.get(s);
    }
}
