// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

final class CWriteHtmlTagInfo
{
    private int cFlag;
    private String cName;
    private int cStyleIndex;
    private boolean bNeedsEndTag;
    
    CWriteHtmlTagInfo(final int cFlag, final String cName) {
        this.cStyleIndex = -1;
        this.bNeedsEndTag = true;
        this.cFlag = cFlag;
        this.cName = cName;
    }
    
    CWriteHtmlTagInfo(final int n, final String s, final int cStyleIndex, final boolean bNeedsEndTag) {
        this(n, s);
        this.cStyleIndex = cStyleIndex;
        this.bNeedsEndTag = bNeedsEndTag;
    }
    
    int getFlag() {
        return this.cFlag;
    }
    
    String getName() {
        return this.cName;
    }
    
    int getStyleIndex() {
        return this.cStyleIndex;
    }
    
    boolean needsEndTag() {
        return this.bNeedsEndTag;
    }
}
