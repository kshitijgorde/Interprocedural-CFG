// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataInputStream;
import java.net.URL;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Color;

public class aY
{
    protected String q;
    protected String w;
    protected Color q;
    
    private aY(final aX ax, final byte b) {
        this.q = "";
        this.w = "";
        this.q = Color.black;
    }
    
    aY(final aX ax) {
        this(ax, (byte)0);
    }
    
    public aY() {
    }
    
    public static String q(final String s) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(AppletAbstract.q().getCodeBase(), s).openStream());
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
}
