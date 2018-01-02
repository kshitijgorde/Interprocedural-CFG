// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.cli;

public class ScriptHandler
{
    private int matchPos;
    private byte[] match;
    private boolean done;
    
    public ScriptHandler() {
        this.done = true;
    }
    
    public void setup(final String match) {
        if (match == null || match.equals("")) {
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
    
    public boolean matchIgnoreCase(final byte[] s, final int length) {
        String str1 = "";
        String str2 = "";
        if (this.done) {
            return true;
        }
        for (int i = 0; !this.done && i < length; ++i) {
            str1 = new String(s, i, 1);
            str2 = new String(this.match, this.matchPos, 1);
            if (str1.equalsIgnoreCase(str2)) {
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
