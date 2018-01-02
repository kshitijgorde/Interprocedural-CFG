// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.telnet;

public class ScriptHandler
{
    private static final int debug = 0;
    private int matchPos;
    private byte[] match;
    private boolean done;
    
    public ScriptHandler() {
        this.done = true;
    }
    
    public void setup(final String match) {
        if (match == null) {
            return;
        }
        this.match = match.getBytes();
        this.matchPos = 0;
        this.done = false;
    }
    
    public boolean match(final byte[] s, final int length) {
        if (this.done) {
            return true;
        }
        for (int i = 0; !this.done && i < length; ++i) {
            if (s[i] == this.match[this.matchPos]) {
                if (++this.matchPos >= this.match.length) {
                    return this.done = true;
                }
            }
            else {
                this.matchPos = 0;
            }
        }
        return false;
    }
}
