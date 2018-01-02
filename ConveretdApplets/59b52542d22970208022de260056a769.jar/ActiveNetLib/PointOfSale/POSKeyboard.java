// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.util.Vector;
import java.io.Serializable;

public class POSKeyboard implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int pos_group_id;
    public int site_id;
    public Vector<POSKeyboardButton> buttons;
    
    public POSKeyboard() {
        this.pos_group_id = 0;
        this.site_id = 0;
        this.buttons = null;
    }
}
