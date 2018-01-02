// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

final class CHtmlTagStackElement
{
    private int cTagID;
    private int cChanged;
    
    CHtmlTagStackElement(final int cTagID, final int cChanged) {
        this.cTagID = cTagID;
        this.cChanged = cChanged;
    }
    
    boolean isTagID(final int n) {
        return this.cTagID == n;
    }
    
    int getTagID() {
        return this.cTagID;
    }
    
    int getChanged() {
        return this.cChanged;
    }
}
