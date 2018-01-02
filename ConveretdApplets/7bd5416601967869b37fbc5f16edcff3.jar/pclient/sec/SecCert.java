// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

import javax.security.auth.x500.X500Principal;
import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.io.IOException;
import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;

public class SecCert
{
    private static char[] hexChar;
    
    public static String toHexString(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            sb.append(SecCert.hexChar[(array[i] & 0xF0) >>> 4]);
            sb.append(SecCert.hexChar[array[i] & 0xF]);
        }
        return sb.toString();
    }
    
    public static X509Certificate cast509(final Certificate certificate) {
        X509Certificate x509Certificate = null;
        if (certificate.getType().indexOf("509") >= 0) {
            try {
                x509Certificate = (X509Certificate)certificate;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                x509Certificate = null;
            }
        }
        return x509Certificate;
    }
    
    public static Certificate getFirst(final SSLSession sslSession) throws IOException {
        if (sslSession == null) {
            return null;
        }
        final Certificate[] peerCertificates = sslSession.getPeerCertificates();
        if (peerCertificates.length == 0) {
            return null;
        }
        return peerCertificates[0];
    }
    
    public static CertGeneral getCert(final SSLSession sslSession) throws IOException {
        final CertGeneral certGeneral = new CertGeneral();
        createCert(sslSession, certGeneral, true);
        return certGeneral;
    }
    
    public static CertGeneral getCertIssuer(final SSLSession sslSession) throws IOException {
        final CertGeneral certGeneral = new CertGeneral();
        createCert(sslSession, certGeneral, false);
        return certGeneral;
    }
    
    public static CertGeneral[] getCertChain(final SSLSession sslSession) throws IOException {
        if (sslSession == null) {
            return null;
        }
        final Certificate[] peerCertificates = sslSession.getPeerCertificates();
        if (peerCertificates.length == 0) {
            return null;
        }
        final CertGeneral[] array = new CertGeneral[peerCertificates.length];
        for (int i = 0; i < peerCertificates.length; ++i) {
            populateOneOfChain(array[i] = new CertGeneral(), peerCertificates[peerCertificates.length - 1 - i]);
        }
        return array;
    }
    
    private static void populateOneOfChain(final CertGeneral certGeneral, final Certificate certificate) throws IOException {
        final X509Certificate cast509 = cast509(certificate);
        if (cast509 == null) {
            return;
        }
        popuCert(cast509, certGeneral, true);
    }
    
    private static void createCert(final SSLSession sslSession, final CertGeneral certGeneral, final boolean b) throws IOException {
        if (sslSession == null) {
            return;
        }
        certGeneral.sCipher = sslSession.getCipherSuite();
        certGeneral.sProto = sslSession.getProtocol();
        final Certificate[] peerCertificates = sslSession.getPeerCertificates();
        if (peerCertificates.length == 0) {
            return;
        }
        System.out.println("certs=" + peerCertificates.length);
        if (peerCertificates[0].getType().indexOf("509") >= 0) {
            X509Certificate x509Certificate = null;
            Label_0103: {
                try {
                    x509Certificate = (X509Certificate)peerCertificates[0];
                    break Label_0103;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                return;
            }
            popuCert(x509Certificate, certGeneral, b);
        }
    }
    
    private static void popuCert(final X509Certificate x509Certificate, final CertGeneral certGeneral, final boolean b) throws IOException {
        certGeneral.xType = x509Certificate.getType();
        certGeneral.xClass = x509Certificate.getClass().getName();
        certGeneral.serial = x509Certificate.getSerialNumber().toString(16);
        certGeneral.validFrom = x509Certificate.getNotBefore().toString();
        certGeneral.validTo = x509Certificate.getNotAfter().toString();
        certGeneral.version = x509Certificate.getVersion();
        certGeneral.sigAlgName = x509Certificate.getSigAlgName();
        certGeneral.signature = toHexString(x509Certificate.getSignature());
        certGeneral.pubkey = x509Certificate.getPublicKey().toString();
        popuCertMain(x509Certificate, certGeneral, b);
    }
    
    private static void popuCertMain(final X509Certificate x509Certificate, final CertGeneral certGeneral, final boolean b) {
        X500Principal x500Principal;
        if (b) {
            x500Principal = x509Certificate.getSubjectX500Principal();
        }
        else {
            x500Principal = x509Certificate.getIssuerX500Principal();
        }
        certGeneral.xName = x500Principal.getName();
        ArrayList list;
        try {
            list = (ArrayList)new CertRfc(certGeneral.xName).parseDn();
        }
        catch (InvalidNameException ex) {
            ex.printStackTrace();
            return;
        }
        popuRdn(list, certGeneral);
    }
    
    private static void popuRdn(final ArrayList list, final CertGeneral certGeneral) {
        for (int i = 0; i < list.size(); ++i) {
            final CertRdn certRdn = list.get(i);
            final String type = certRdn.getType();
            final String string = certRdn.getValue().toString();
            if (type.equals("CN")) {
                certGeneral.cn = string;
            }
            else if (type.equals("O")) {
                certGeneral.org = string;
            }
            else if (type.equals("OU")) {
                certGeneral.unit = string;
            }
            else if (type.equals("ST")) {
                certGeneral.state = string;
            }
            else if (type.equals("C")) {
                certGeneral.country = string;
            }
        }
    }
    
    public static String getCertificates(final Certificate[] array) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer(4096);
        for (int i = 0; i < array.length; ++i) {
            final Certificate certificate = array[i];
            sb.append("public key=");
            sb.append(certificate.getPublicKey().toString());
            sb.append("\n");
            sb.append("certificate=");
            sb.append(certificate.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    static {
        SecCert.hexChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
