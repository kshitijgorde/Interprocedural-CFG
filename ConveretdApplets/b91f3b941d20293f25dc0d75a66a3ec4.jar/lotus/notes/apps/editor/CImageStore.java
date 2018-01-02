// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Image;

class CImageStore
{
    private Image cImageBits;
    private int cCount;
    private int cType;
    
    public CImageStore(final Image cImageBits, final int cType) {
        this.cImageBits = cImageBits;
        this.cType = cType;
        this.cCount = 1;
    }
    
    public Image getImage() {
        return this.cImageBits;
    }
    
    public int getType() {
        return this.cType;
    }
    
    public int getCount() {
        return this.cCount;
    }
    
    public void addRef() {
        ++this.cCount;
    }
    
    public int release() {
        return --this.cCount;
    }
}
