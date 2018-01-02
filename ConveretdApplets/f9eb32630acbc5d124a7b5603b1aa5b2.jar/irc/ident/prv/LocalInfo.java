// 
// Decompiled by Procyon v0.5.30
// 

package irc.ident.prv;

class LocalInfo
{
    public int localPort;
    public String system;
    public String id;
    
    public LocalInfo(final int localPort, final String system, final String id) {
        this.localPort = localPort;
        this.system = system;
        this.id = id;
    }
}
