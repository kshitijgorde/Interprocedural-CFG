// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import anon.util.Util;
import java.util.Vector;
import org.bouncycastle.asn1.DERSequence;

public final class X509UnknownExtension extends AbstractX509Extension
{
    public static final String IDENTIFIER;
    
    X509UnknownExtension(final DERSequence derSequence) {
        super(derSequence);
    }
    
    public String getName() {
        return "UnknownExtension";
    }
    
    public Vector getValues() {
        return Util.toVector(this.getIdentifier());
    }
    
    static {
        IDENTIFIER = null;
    }
}
