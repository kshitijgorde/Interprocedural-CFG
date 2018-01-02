// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

final class CStringReader
{
    private String cStr;
    private String cDelims;
    private int cPos;
    private int cLength;
    
    CStringReader(final String cStr, final String cDelims) {
        this.cStr = cStr;
        this.cDelims = cDelims;
        this.cPos = 0;
        this.cLength = cStr.length();
    }
    
    boolean atEnd() {
        return this.cPos >= this.cLength;
    }
    
    void setDelimiters(final String cDelims) {
        this.cDelims = cDelims;
    }
    
    void skipDelimiters(final String s) {
        while (!this.atEnd() && s.indexOf(this.cStr.charAt(this.cPos)) >= 0) {
            ++this.cPos;
        }
    }
    
    String nextToken() {
        return this.nextToken(false);
    }
    
    String nextToken(final boolean b) {
        if (this.atEnd()) {
            return null;
        }
        final int cPos = this.cPos;
        while (this.cPos < this.cLength && this.cDelims.indexOf(this.cStr.charAt(this.cPos)) == -1) {
            ++this.cPos;
        }
        if (cPos == this.cPos) {
            if (b) {
                return "";
            }
            ++this.cPos;
        }
        return this.cStr.substring(cPos, this.cPos);
    }
    
    String nextToken(final String s) {
        return this.nextToken(s, false);
    }
    
    String nextToken(final String cDelims, final boolean b) {
        final String cDelims2 = this.cDelims;
        this.cDelims = cDelims;
        final String nextToken = this.nextToken(b);
        this.cDelims = cDelims2;
        return nextToken;
    }
    
    char peekChar() {
        if (this.atEnd()) {
            return '\0';
        }
        return this.cStr.charAt(this.cPos);
    }
    
    void skipChar() {
        if (!this.atEnd()) {
            ++this.cPos;
        }
    }
}
