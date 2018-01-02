// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.util.Vector;

class TeletextKeymap
{
    Vector keyMap;
    
    public TeletextKeymap() {
        this.keyMap = new Vector();
    }
    
    public void finalize() throws Exception {
        this.keyMap = null;
    }
    
    public void addKey(final TeletextKeyAssociation teletextKeyAssociation) {
        this.keyMap.addElement(teletextKeyAssociation);
    }
}
