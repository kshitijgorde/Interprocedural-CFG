// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CParaFormatList extends CDoubleLinkList implements Cloneable
{
    private CFormatInfo cInfo;
    private int cOffset;
    private int cLength;
    
    CParaFormatList(final int cOffset, final int cLength) {
        this.cOffset = cOffset;
        this.cLength = cLength;
        this.cInfo = new CFormatInfo();
    }
    
    CParaFormatList(final int cOffset, final int cLength, final CFormatInfo cInfo) {
        this.cOffset = cOffset;
        this.cLength = cLength;
        this.cInfo = cInfo;
    }
    
    final int getOffset() {
        return this.cOffset;
    }
    
    final void setOffset(final int cOffset) {
        this.cOffset = cOffset;
    }
    
    final void setLength(final int cLength) {
        this.cLength = cLength;
    }
    
    final int getLength() {
        return this.cLength;
    }
    
    final CFormatInfo getFormatInfo() {
        return this.cInfo;
    }
    
    final void setFormatInfo(final CFormatInfo cInfo) {
        this.cInfo = cInfo;
    }
    
    public Object clone() {
        CParaFormatList list;
        try {
            list = new CParaFormatList(this.cOffset, this.cLength, (CFormatInfo)this.cInfo.clone());
            final CParaFormatList list2 = (CParaFormatList)this.getNext();
            if (list2 != null) {
                list.insertChain((CDoubleLinkList)list2.clone());
            }
        }
        catch (Exception ex) {
            throw new InternalError();
        }
        return list;
    }
    
    boolean isFor(final int n) {
        return n >= this.cOffset && ((this.cLength == 0) ? (n <= this.cOffset + this.cLength) : (n < this.cOffset + this.cLength));
    }
    
    int getEnd() {
        return this.cOffset + this.cLength;
    }
}
