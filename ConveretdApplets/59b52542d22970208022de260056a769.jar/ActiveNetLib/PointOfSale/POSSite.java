// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSSite implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int site_id;
    public String site_name;
    
    public POSSite() {
        this.site_id = 0;
        this.site_name = null;
    }
    
    public String toString() {
        return this.site_name;
    }
}
