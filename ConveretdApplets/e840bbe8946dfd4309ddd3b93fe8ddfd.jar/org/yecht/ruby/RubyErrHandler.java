// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import java.io.UnsupportedEncodingException;
import org.yecht.Parser;
import org.jruby.Ruby;
import org.yecht.ErrorHandler;

public class RubyErrHandler implements ErrorHandler
{
    private Ruby runtime;
    
    public RubyErrHandler(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public void handle(final Parser p, final String msg) {
        int endl;
        for (endl = p.cursor; p.buffer.buffer[endl] != 0 && p.buffer.buffer[endl] != 10; ++endl) {}
        try {
            int lp = p.lineptr;
            if (lp < 0) {
                lp = 0;
            }
            int len = endl - lp;
            if (len < 0) {
                len = 0;
            }
            final String line = new String(p.buffer.buffer, lp, len, "ISO-8859-1");
            final String m1 = msg + " on line " + p.linect + ", col " + (p.cursor - lp) + ": `" + line + "'";
            throw this.runtime.newArgumentError(m1);
        }
        catch (UnsupportedEncodingException e) {}
    }
}
