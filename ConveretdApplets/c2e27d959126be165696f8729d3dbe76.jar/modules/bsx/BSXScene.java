// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

import java.util.Hashtable;

public class BSXScene
{
    public BSXGraphic background;
    public Hashtable objects;
    
    public BSXScene() {
        this.objects = new Hashtable();
        this.background = null;
    }
    
    public BSXScene(final BSXGraphic pic) {
        this.objects = new Hashtable();
        this.background = pic;
    }
}
