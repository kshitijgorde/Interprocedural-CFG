// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.keyexchange;

import java.math.BigInteger;
import anon.crypto.JAPCertificate;
import anon.crypto.tinytls.TLSException;
import anon.crypto.IMyPrivateKey;

public abstract class Key_Exchange
{
    public abstract byte[] generateServerKeyExchange(final IMyPrivateKey p0, final byte[] p1, final byte[] p2) throws TLSException;
    
    public abstract void processServerKeyExchange(final byte[] p0, final int p1, final int p2, final byte[] p3, final byte[] p4, final JAPCertificate p5) throws TLSException;
    
    public abstract byte[] calculateServerFinished(final byte[] p0);
    
    public abstract void processServerFinished(final byte[] p0, final int p1, final byte[] p2) throws TLSException;
    
    public abstract void processClientKeyExchange(final BigInteger p0);
    
    public abstract byte[] calculateClientKeyExchange() throws TLSException;
    
    public abstract void processClientFinished(final byte[] p0, final byte[] p1) throws TLSException;
    
    public abstract byte[] calculateClientFinished(final byte[] p0) throws TLSException;
    
    public abstract byte[] calculateKeys();
}
