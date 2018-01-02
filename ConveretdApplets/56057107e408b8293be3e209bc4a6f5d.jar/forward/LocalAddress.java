// 
// Decompiled by Procyon v0.5.30
// 

package forward;

import anon.transport.address.AddressParameter;
import anon.transport.address.IAddress;

public class LocalAddress implements IAddress
{
    public static final String TRANSPORT_IDENTIFIER = "local";
    
    public AddressParameter[] getAllParameters() {
        return new AddressParameter[0];
    }
    
    public String getTransportIdentifier() {
        return "local";
    }
}
