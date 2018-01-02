// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import anon.util.Util;
import java.util.Vector;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.DERSequence;

public abstract class AbstractX509KeyIdentifier extends AbstractX509Extension
{
    protected String m_value;
    
    public AbstractX509KeyIdentifier(final String s, final byte[] array) {
        super(s, false, array);
    }
    
    public AbstractX509KeyIdentifier(final DERSequence derSequence) {
        super(derSequence);
    }
    
    public String getValue() {
        return this.m_value;
    }
    
    public String getValueWithoutColon() {
        if (this.m_value == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.m_value, ":");
        String string = "";
        while (stringTokenizer.hasMoreTokens()) {
            string += stringTokenizer.nextToken();
        }
        return string;
    }
    
    public Vector getValues() {
        return Util.toVector(this.m_value);
    }
}
