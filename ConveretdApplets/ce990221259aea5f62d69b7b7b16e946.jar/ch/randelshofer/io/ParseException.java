// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.io;

import java.io.IOException;

public class ParseException extends IOException
{
    private int startpos;
    private int endpos;
    
    public ParseException(final String s, final int startpos, final int endpos) {
        super(s);
        this.startpos = startpos;
        this.endpos = endpos;
    }
    
    public int getStartPosition() {
        return this.startpos;
    }
    
    public int getEndPosition() {
        return this.endpos;
    }
}
