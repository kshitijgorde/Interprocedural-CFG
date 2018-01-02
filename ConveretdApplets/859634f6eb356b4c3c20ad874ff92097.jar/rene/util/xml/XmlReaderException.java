// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

public class XmlReaderException extends Exception
{
    String Line;
    int Pos;
    String S;
    
    public XmlReaderException(final String s, final String line, final int pos) {
        super(s);
        this.S = s;
        this.Line = line;
        this.Pos = pos;
    }
    
    public XmlReaderException(final String s) {
        this(s, "", 0);
    }
    
    public String getLine() {
        return this.Line;
    }
    
    public int getPos() {
        return this.Pos;
    }
    
    public String getText() {
        return this.S;
    }
}
