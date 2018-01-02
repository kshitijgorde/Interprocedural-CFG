// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.pop3;

public final class POP3MessageInfo
{
    public int number;
    public int size;
    public String identifier;
    
    public POP3MessageInfo() {
        final boolean b = false;
        this.size = (b ? 1 : 0);
        this.number = (b ? 1 : 0);
        this.identifier = null;
    }
    
    public POP3MessageInfo(final int num, final int octets) {
        this.number = num;
        this.size = octets;
        this.identifier = null;
    }
    
    public POP3MessageInfo(final int num, final String uid) {
        this.number = num;
        this.size = -1;
        this.identifier = uid;
    }
}
