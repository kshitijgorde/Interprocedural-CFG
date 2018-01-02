// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CSizeInfo
{
    int cWidth;
    int cAscent;
    int cDescent;
    
    final void clear() {
        final boolean cWidth = false;
        this.cDescent = (cWidth ? 1 : 0);
        this.cAscent = (cWidth ? 1 : 0);
        this.cWidth = (cWidth ? 1 : 0);
    }
    
    final int getHeight() {
        return this.cAscent + this.cDescent;
    }
    
    final void union(final CSizeInfo cSizeInfo) {
        if (cSizeInfo.cAscent > this.cAscent) {
            this.cAscent = cSizeInfo.cAscent;
        }
        if (cSizeInfo.cDescent > this.cDescent) {
            this.cDescent = cSizeInfo.cDescent;
        }
    }
}
