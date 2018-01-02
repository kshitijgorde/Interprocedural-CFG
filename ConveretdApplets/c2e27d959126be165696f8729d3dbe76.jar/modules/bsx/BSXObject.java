// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

public class BSXObject
{
    public int position;
    public int layer;
    public boolean visible;
    
    public BSXObject(final int p, final int l) {
        this.position = 0;
        this.layer = 0;
        this.visible = true;
        this.position = p;
        this.layer = l;
    }
}
