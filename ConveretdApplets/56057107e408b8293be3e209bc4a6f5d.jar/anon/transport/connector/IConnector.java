// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connector;

import anon.transport.connection.ConnectionException;
import anon.transport.connection.IConnection;
import anon.transport.address.IAddress;

public interface IConnector
{
    IConnection connect(final IAddress p0) throws ConnectionException;
}
