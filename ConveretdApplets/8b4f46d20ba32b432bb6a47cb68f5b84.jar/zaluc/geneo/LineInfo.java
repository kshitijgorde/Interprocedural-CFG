// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

class LineInfo
{
    LineInfo next;
    int y;
    int width;
    int numSubstrings;
    SubstringInfo substrings;
    
    LineInfo() {
        this.next = null;
        this.numSubstrings = 0;
        this.substrings = null;
    }
}
