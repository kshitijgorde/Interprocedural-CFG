// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.util.ClassUtil;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERBoolean;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.util.Vector;

public abstract class AbstractX509Extension
{
    public static final String IDENTIFIER;
    private static Class[] AVAILABLE_EXTENSIONS;
    private static Vector ms_classExtensions;
    private DERObjectIdentifier m_identifier;
    private boolean m_critical;
    private byte[] m_value;
    private DERSequence m_extension;
    static /* synthetic */ Class class$anon$crypto$X509UnknownExtension;
    static /* synthetic */ Class class$anon$crypto$X509SubjectKeyIdentifier;
    static /* synthetic */ Class class$anon$crypto$X509AuthorityKeyIdentifier;
    static /* synthetic */ Class class$anon$crypto$X509SubjectAlternativeName;
    static /* synthetic */ Class class$anon$crypto$X509IssuerAlternativeName;
    static /* synthetic */ Class class$anon$crypto$X509BasicConstraints;
    static /* synthetic */ Class class$anon$crypto$X509KeyUsage;
    static /* synthetic */ Class class$org$bouncycastle$asn1$DERSequence;
    
    public AbstractX509Extension(final String s, final boolean critical, final byte[] value) {
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        this.m_identifier = new DERObjectIdentifier(s);
        this.m_critical = critical;
        this.m_value = value;
        asn1EncodableVector.add(this.m_identifier);
        asn1EncodableVector.add(new DERBoolean(critical));
        asn1EncodableVector.add(new DEROctetString(value));
        this.m_extension = new DERSequence(asn1EncodableVector);
    }
    
    public AbstractX509Extension(final DERSequence extension) {
        int n = 1;
        this.m_extension = extension;
        this.m_identifier = (DERObjectIdentifier)extension.getObjectAt(0);
        if (extension.size() == 3) {
            this.m_critical = ((DERBoolean)extension.getObjectAt(1)).isTrue();
            n = 2;
        }
        else {
            this.m_critical = false;
        }
        this.m_value = ((DEROctetString)extension.getObjectAt(n)).getOctets();
    }
    
    static AbstractX509Extension getInstance(final DERSequence derSequence) {
        final DERObjectIdentifier derObjectIdentifier = (DERObjectIdentifier)derSequence.getObjectAt(0);
        final Object[] array = { derSequence };
        final Class[] array2 = { (AbstractX509Extension.class$org$bouncycastle$asn1$DERSequence == null) ? (AbstractX509Extension.class$org$bouncycastle$asn1$DERSequence = class$("org.bouncycastle.asn1.DERSequence")) : AbstractX509Extension.class$org$bouncycastle$asn1$DERSequence };
        if (AbstractX509Extension.ms_classExtensions == null) {
            try {
                AbstractX509Extension.ms_classExtensions = ClassUtil.findSubclasses(ClassUtil.getClassStatic());
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.CRYPTO, t);
            }
            if (AbstractX509Extension.ms_classExtensions == null) {
                AbstractX509Extension.ms_classExtensions = new Vector();
            }
            if (AbstractX509Extension.ms_classExtensions.size() < AbstractX509Extension.AVAILABLE_EXTENSIONS.length) {
                int n;
                if (ClassUtil.isFindSubclassesEnabled()) {
                    n = 2;
                }
                else {
                    n = 5;
                }
                LogHolder.log(n, LogType.CRYPTO, "X509 extension classes have not been loaded automatically!");
                for (int i = 0; i < AbstractX509Extension.AVAILABLE_EXTENSIONS.length; ++i) {
                    if (!AbstractX509Extension.ms_classExtensions.contains(AbstractX509Extension.AVAILABLE_EXTENSIONS[i])) {
                        AbstractX509Extension.ms_classExtensions.addElement(AbstractX509Extension.AVAILABLE_EXTENSIONS[i]);
                    }
                }
            }
        }
        final Enumeration<Class<AbstractX509Extension>> elements = (Enumeration<Class<AbstractX509Extension>>)AbstractX509Extension.ms_classExtensions.elements();
        while (elements.hasMoreElements()) {
            final Class<AbstractX509Extension> clazz = elements.nextElement();
            try {
                if (clazz.getDeclaredField("IDENTIFIER").get(null).equals(derObjectIdentifier.getId())) {
                    return clazz.getConstructor((Class<?>[])array2).newInstance(array);
                }
                continue;
            }
            catch (Exception ex) {}
        }
        return new X509UnknownExtension(derSequence);
    }
    
    public abstract String getName();
    
    public final boolean isCritical() {
        return this.m_critical;
    }
    
    public final String getIdentifier() {
        return this.m_identifier.getId();
    }
    
    public final byte[] getDEROctets() {
        return this.m_value;
    }
    
    public final int hashCode() {
        return this.getIdentifier().hashCode();
    }
    
    public final boolean equals(final Object o) {
        return o != null && o instanceof AbstractX509Extension && this.getIdentifier().equals(((AbstractX509Extension)o).getIdentifier());
    }
    
    public abstract Vector getValues();
    
    public final String toString() {
        return this.getName();
    }
    
    final DERSequence getBCExtension() {
        return this.m_extension;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        IDENTIFIER = null;
        AbstractX509Extension.AVAILABLE_EXTENSIONS = new Class[] { (AbstractX509Extension.class$anon$crypto$X509UnknownExtension == null) ? (AbstractX509Extension.class$anon$crypto$X509UnknownExtension = class$("anon.crypto.X509UnknownExtension")) : AbstractX509Extension.class$anon$crypto$X509UnknownExtension, (AbstractX509Extension.class$anon$crypto$X509SubjectKeyIdentifier == null) ? (AbstractX509Extension.class$anon$crypto$X509SubjectKeyIdentifier = class$("anon.crypto.X509SubjectKeyIdentifier")) : AbstractX509Extension.class$anon$crypto$X509SubjectKeyIdentifier, (AbstractX509Extension.class$anon$crypto$X509AuthorityKeyIdentifier == null) ? (AbstractX509Extension.class$anon$crypto$X509AuthorityKeyIdentifier = class$("anon.crypto.X509AuthorityKeyIdentifier")) : AbstractX509Extension.class$anon$crypto$X509AuthorityKeyIdentifier, (AbstractX509Extension.class$anon$crypto$X509SubjectAlternativeName == null) ? (AbstractX509Extension.class$anon$crypto$X509SubjectAlternativeName = class$("anon.crypto.X509SubjectAlternativeName")) : AbstractX509Extension.class$anon$crypto$X509SubjectAlternativeName, (AbstractX509Extension.class$anon$crypto$X509IssuerAlternativeName == null) ? (AbstractX509Extension.class$anon$crypto$X509IssuerAlternativeName = class$("anon.crypto.X509IssuerAlternativeName")) : AbstractX509Extension.class$anon$crypto$X509IssuerAlternativeName, (AbstractX509Extension.class$anon$crypto$X509BasicConstraints == null) ? (AbstractX509Extension.class$anon$crypto$X509BasicConstraints = class$("anon.crypto.X509BasicConstraints")) : AbstractX509Extension.class$anon$crypto$X509BasicConstraints, (AbstractX509Extension.class$anon$crypto$X509KeyUsage == null) ? (AbstractX509Extension.class$anon$crypto$X509KeyUsage = class$("anon.crypto.X509KeyUsage")) : AbstractX509Extension.class$anon$crypto$X509KeyUsage };
    }
}
