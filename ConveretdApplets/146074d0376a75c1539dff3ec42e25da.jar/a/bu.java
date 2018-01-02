// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataInputStream;
import java.net.URL;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Point;

public class bu implements aa
{
    private T q;
    private final bt q;
    
    public bu(final bt q, final T q2) {
        this.q = q;
        this.q = q2;
    }
    
    public final Point q() {
        return this.q.q.q.q().getLocation();
    }
    
    public final void q(final String s) {
        this.q.q(Long.parseLong(s));
    }
    
    public bu() {
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
