// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.interfaces.DSAPrivateKey;
import org.bouncycastle.asn1.x509.DSAParameter;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import org.bouncycastle.crypto.params.DSAParameters;

public final class MyDSAParams extends DSAParameters implements DSAParams
{
    public MyDSAParams() {
        super(null, null, null);
    }
    
    public MyDSAParams(final DSAParams dsaParams) {
        super(dsaParams.getP(), dsaParams.getQ(), dsaParams.getG());
    }
    
    public MyDSAParams(final DSAParameter dsaParameter) {
        super(dsaParameter.getP(), dsaParameter.getQ(), dsaParameter.getG());
    }
    
    public MyDSAParams(final DSAParameters dsaParameters) {
        super(dsaParameters.getP(), dsaParameters.getQ(), dsaParameters.getG(), dsaParameters.getValidationParameters());
    }
    
    public MyDSAParams(final DSAPrivateKey dsaPrivateKey) {
        this(dsaPrivateKey.getParams());
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof DSAParams)) {
            return false;
        }
        final DSAParams dsaParams = (DSAParams)o;
        return dsaParams.getG().equals(this.getG()) && dsaParams.getP().equals(this.getP()) && dsaParams.getQ().equals(this.getQ());
    }
}
