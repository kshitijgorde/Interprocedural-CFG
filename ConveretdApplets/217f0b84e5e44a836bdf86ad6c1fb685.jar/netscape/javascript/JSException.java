// 
// Decompiled by Procyon v0.5.30
// 

package netscape.javascript;

public class JSException extends Exception
{
    String filename;
    int lineno;
    String source;
    int tokenIndex;
    
    public JSException() {
        this.filename = "unknown";
        this.lineno = 0;
        this.source = "";
        this.tokenIndex = 0;
    }
    
    public JSException(final String s) {
        super(s);
        this.filename = "unknown";
        this.lineno = 0;
        this.source = "";
        this.tokenIndex = 0;
    }
    
    public JSException(final String s, final String filename, final int lineno, final String source, final int tokenIndex) {
        super(s);
        this.filename = filename;
        this.lineno = lineno;
        this.source = source;
        this.tokenIndex = tokenIndex;
    }
}
