// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.DERSequence;
import java.util.Vector;

public final class X509IssuerAlternativeName extends AbstractX509AlternativeName
{
    public static final String IDENTIFIER;
    
    public X509IssuerAlternativeName(final String s, final Integer n) {
        super(X509IssuerAlternativeName.IDENTIFIER, s, n);
    }
    
    public X509IssuerAlternativeName(final boolean b, final String s, final Integer n) {
        super(X509IssuerAlternativeName.IDENTIFIER, b, s, n);
    }
    
    public X509IssuerAlternativeName(final Vector vector, final Vector vector2) {
        super(X509IssuerAlternativeName.IDENTIFIER, vector, vector2);
    }
    
    public X509IssuerAlternativeName(final boolean b, final Vector vector, final Vector vector2) {
        super(X509IssuerAlternativeName.IDENTIFIER, b, vector, vector2);
    }
    
    public X509IssuerAlternativeName(final DERSequence derSequence) {
        super(derSequence);
    }
    
    public String getName() {
        return "IssuerAlternativeName";
    }
    
    static {
        IDENTIFIER = X509Extensions.IssuerAlternativeName.getId();
    }
}
