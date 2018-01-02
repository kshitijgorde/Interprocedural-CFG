// 
// Decompiled by Procyon v0.5.30
// 

package buildService;

import java.beans.Expression;

public class Cid extends Expression
{
    String protocol;
    
    public Cid(final Object packer, final String method, final Object[] ips) {
        super(packer, method, ips);
        this.protocol = "CORBA";
    }
}
