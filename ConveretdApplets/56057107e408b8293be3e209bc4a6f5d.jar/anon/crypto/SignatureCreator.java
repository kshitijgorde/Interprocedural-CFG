// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Node;
import java.util.Hashtable;

public class SignatureCreator
{
    private static SignatureCreator ms_scInstance;
    private Hashtable m_signatureKeys;
    static /* synthetic */ Class class$anon$crypto$SignatureCreator;
    
    public static SignatureCreator getInstance() {
        final Class clazz = (SignatureCreator.class$anon$crypto$SignatureCreator == null) ? (SignatureCreator.class$anon$crypto$SignatureCreator = class$("anon.crypto.SignatureCreator")) : SignatureCreator.class$anon$crypto$SignatureCreator;
        synchronized (clazz) {
            if (SignatureCreator.ms_scInstance == null) {
                SignatureCreator.ms_scInstance = new SignatureCreator();
            }
        }
        return SignatureCreator.ms_scInstance;
    }
    
    private SignatureCreator() {
        this.m_signatureKeys = new Hashtable();
    }
    
    public void setSigningKey(final int n, final PKCS12 pkcs12) {
        synchronized (this.m_signatureKeys) {
            this.m_signatureKeys.put(new Integer(n), pkcs12);
        }
    }
    
    public XMLSignature getSignedXml(final int n, final Node node) {
        PKCS12 pkcs12 = null;
        XMLSignature sign = null;
        synchronized (this.m_signatureKeys) {
            pkcs12 = this.m_signatureKeys.get(new Integer(n));
        }
        if (pkcs12 != null) {
            try {
                sign = XMLSignature.sign(node, pkcs12);
            }
            catch (Exception ex) {}
        }
        return sign;
    }
    
    public boolean signXml(final int n, final Node node) {
        return this.getSignedXml(n, node) != null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
