// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.DERSequence;
import java.util.Vector;

public final class X509SubjectAlternativeName extends AbstractX509AlternativeName
{
    public static final String IDENTIFIER;
    
    public X509SubjectAlternativeName(final String s, final Integer n) {
        super(X509SubjectAlternativeName.IDENTIFIER, s, n);
    }
    
    public X509SubjectAlternativeName(final boolean b, final String s, final Integer n) {
        super(X509SubjectAlternativeName.IDENTIFIER, b, s, n);
    }
    
    public X509SubjectAlternativeName(final Vector vector, final Vector vector2) {
        super(X509SubjectAlternativeName.IDENTIFIER, vector, vector2);
    }
    
    public X509SubjectAlternativeName(final boolean b, final Vector vector, final Vector vector2) {
        super(X509SubjectAlternativeName.IDENTIFIER, b, vector, vector2);
    }
    
    public X509SubjectAlternativeName(final DERSequence derSequence) {
        super(derSequence);
    }
    
    public String getName() {
        return "SubjectAlternativeName";
    }
    
    static {
        IDENTIFIER = X509Extensions.SubjectAlternativeName.getId();
    }
}
