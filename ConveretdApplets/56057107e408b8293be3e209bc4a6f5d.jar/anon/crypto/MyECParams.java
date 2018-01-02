// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X962NamedCurves;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.asn1.DERObjectIdentifier;

public final class MyECParams
{
    private static final DERObjectIdentifier IMPLICIT_CURVE_ID;
    ECDomainParameters m_params;
    boolean m_isImplicitlyCA;
    boolean m_isNamedCurve;
    DERObjectIdentifier m_curveIdentifier;
    
    public MyECParams() {
        this(SECNamedCurves.getByOID(MyECParams.IMPLICIT_CURVE_ID));
        this.m_isImplicitlyCA = true;
    }
    
    public MyECParams(final ECDomainParameters params) {
        this.m_isImplicitlyCA = false;
        this.m_isNamedCurve = false;
        this.m_curveIdentifier = null;
        this.m_params = params;
    }
    
    public MyECParams(final X9ECParameters x9ECParameters) {
        this.m_isImplicitlyCA = false;
        this.m_isNamedCurve = false;
        this.m_curveIdentifier = null;
        this.m_params = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH());
    }
    
    public MyECParams(final X962Parameters x962Parameters) {
        this.m_isImplicitlyCA = false;
        this.m_isNamedCurve = false;
        this.m_curveIdentifier = null;
        X9ECParameters x9ECParameters;
        if (x962Parameters.isNamedCurve()) {
            this.m_isNamedCurve = true;
            this.m_curveIdentifier = (DERObjectIdentifier)x962Parameters.getParameters();
            x9ECParameters = SECNamedCurves.getByOID(this.m_curveIdentifier);
            if (x9ECParameters == null) {
                x9ECParameters = X962NamedCurves.getByOID(this.m_curveIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = NISTNamedCurves.getByOID(this.m_curveIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = TeleTrusTNamedCurves.getByOID(this.m_curveIdentifier);
            }
            if (x9ECParameters == null) {
                throw new IllegalArgumentException("Unknown Named Curve Identifier!");
            }
        }
        else if (x962Parameters.isImplicitlyCA()) {
            this.m_isImplicitlyCA = true;
            x9ECParameters = SECNamedCurves.getByOID(MyECParams.IMPLICIT_CURVE_ID);
            this.m_curveIdentifier = MyECParams.IMPLICIT_CURVE_ID;
        }
        else {
            x9ECParameters = new X9ECParameters((ASN1Sequence)x962Parameters.getParameters());
            this.m_isNamedCurve = false;
        }
        this.m_params = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH());
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ECDomainParameters)) {
            return false;
        }
        final ECDomainParameters ecDomainParameters = (ECDomainParameters)o;
        if (ecDomainParameters.getH().equals(this.m_params.getH()) && ecDomainParameters.getN().equals(this.m_params.getN())) {
            if (ecDomainParameters.getCurve() instanceof ECCurve.F2m) {
                return ((ECCurve.F2m)ecDomainParameters.getCurve()).equals(this.m_params.getCurve());
            }
            if (ecDomainParameters.getCurve() instanceof ECCurve.Fp) {
                return ((ECCurve.Fp)ecDomainParameters.getCurve()).equals(this.m_params.getCurve());
            }
        }
        return false;
    }
    
    protected ECDomainParameters getECDomainParams() {
        return this.m_params;
    }
    
    protected X962Parameters getX962Params() {
        if (this.m_isNamedCurve) {
            return new X962Parameters(this.m_curveIdentifier);
        }
        if (this.m_isImplicitlyCA) {
            return new X962Parameters(new DERNull());
        }
        return new X962Parameters(new X9ECParameters(this.m_params.getCurve(), this.m_params.getG(), this.m_params.getN(), this.m_params.getH()));
    }
    
    protected void setNamedCurveID(final DERObjectIdentifier curveIdentifier) {
        if (curveIdentifier != null) {
            this.m_curveIdentifier = curveIdentifier;
            this.m_isNamedCurve = true;
        }
    }
    
    protected DERObjectIdentifier getCurveID() {
        return this.m_curveIdentifier;
    }
    
    static {
        IMPLICIT_CURVE_ID = SECObjectIdentifiers.secp160r1;
    }
}
