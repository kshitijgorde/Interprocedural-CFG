// 
// Decompiled by Procyon v0.5.30
// 

package jarify;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.encoders.Base64;
import java.security.SignatureException;
import anon.crypto.PKCS7SignedData;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import java.io.IOException;
import java.util.zip.ZipException;
import java.io.File;
import java.util.Hashtable;
import anon.crypto.JAPCertificate;

public final class JarVerifier
{
    private JarFile m_jarFile;
    private JarManifest m_Manifest;
    private JAPCertificate m_certRoot;
    private Hashtable digestCache;
    private Hashtable aliasSBF;
    
    private JarVerifier(final File file) throws ZipException, IOException, SecurityException {
        this.digestCache = new Hashtable();
        this.aliasSBF = new Hashtable();
        this.m_certRoot = null;
        this.m_jarFile = new JarFile(file);
        this.m_Manifest = this.m_jarFile.getManifest();
    }
    
    private void close() {
        this.m_jarFile.close();
    }
    
    private Vector InitAliases(final Vector vector) {
        final Vector<String> vector2 = new Vector<String>();
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            final JarFileEntry signatureBlockFile = this.m_jarFile.getSignatureBlockFile(s);
            if (signatureBlockFile != null) {
                LogHolder.log(7, LogType.MISC, "Checking certificate chain for alias: " + s);
                PKCS7SignedData pkcs7SignedData;
                try {
                    pkcs7SignedData = new PKCS7SignedData(signatureBlockFile.getContent());
                }
                catch (Exception ex) {
                    LogHolder.log(7, LogType.MISC, ex.getMessage());
                    continue;
                }
                if (pkcs7SignedData == null) {
                    LogHolder.log(7, LogType.MISC, "Could not get PKCS#7 data object!");
                }
                else {
                    this.aliasSBF.put(s, pkcs7SignedData);
                    final JAPCertificate[] certificates = pkcs7SignedData.getCertificates();
                    if (certificates != null) {
                        try {
                            certificates[certificates.length - 1].verify(this.m_certRoot.getPublicKey());
                        }
                        catch (Exception ex2) {
                            LogHolder.log(7, LogType.MISC, ex2.getMessage());
                            continue;
                        }
                        try {
                            for (int j = 0; j < certificates.length - 1; ++j) {
                                LogHolder.log(7, LogType.MISC, "Checking certificate No. : " + j + 1);
                                certificates[j].verify(certificates[j + 1].getPublicKey());
                                LogHolder.log(7, LogType.MISC, "Certificate No. " + j + 1 + " verified OK.");
                            }
                        }
                        catch (Exception ex3) {
                            LogHolder.log(7, LogType.MISC, ex3.getMessage());
                            continue;
                        }
                        vector2.addElement(s);
                    }
                }
            }
        }
        return vector2;
    }
    
    public static boolean verify(final File file, final JAPCertificate japCertificate) {
        try {
            final JarVerifier jarVerifier = new JarVerifier(file);
            final boolean verify = jarVerifier.verify(japCertificate);
            jarVerifier.close();
            return verify;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private boolean verify(final JAPCertificate certRoot) {
        this.m_certRoot = certRoot;
        if (this.m_certRoot == null) {
            return false;
        }
        LogHolder.log(7, LogType.MISC, "Searching for Signatures...");
        if (!this.isSignedJar()) {
            return false;
        }
        LogHolder.log(7, LogType.MISC, "This is a signed Jarfile.\n");
        LogHolder.log(7, LogType.MISC, "Verifying Manifest entries...");
        if (!this.verifyManifestDigests()) {
            return false;
        }
        LogHolder.log(7, LogType.MISC, "Manifest entries verified OK.\n");
        final Vector initAliases = this.InitAliases(this.m_jarFile.getAliasList());
        if (initAliases.size() < 1) {
            LogHolder.log(7, LogType.MISC, "\nNo Aliases present that can be validated with the given root certificate!\n");
            return false;
        }
        for (int i = 0; i < initAliases.size(); ++i) {
            final String s = initAliases.elementAt(i);
            if (s == null || s == "") {
                LogHolder.log(7, LogType.MISC, "\nAlias error");
                return false;
            }
            LogHolder.log(7, LogType.MISC, "Verifying Signature File entries for alias \"" + s + "\"...");
            if (!this.verifySFDigests(s)) {
                return false;
            }
            LogHolder.log(7, LogType.MISC, "Entries verified OK.");
            LogHolder.log(7, LogType.MISC, "Verifying Signature for alias \"" + s + "\"...");
            if (!this.verifySignature(s)) {
                return false;
            }
            LogHolder.log(7, LogType.MISC, "Signature from \"" + s + "\" is genuine.\n");
        }
        return true;
    }
    
    private boolean verifySignature(final String s) {
        boolean verify = false;
        final JarSignatureFile signatureFile = this.m_jarFile.getSignatureFile(s);
        if (signatureFile == null) {
            return false;
        }
        final JarFileEntry signatureBlockFile = this.m_jarFile.getSignatureBlockFile(s);
        if (signatureBlockFile == null) {
            return false;
        }
        final byte[] content = signatureFile.getContent();
        if (content == null) {
            return false;
        }
        final String name = signatureBlockFile.getName();
        if (name.endsWith(".DSA") || name.endsWith(".RSA")) {
            LogHolder.log(7, LogType.MISC, "Found " + name.substring(name.lastIndexOf(".") + 1) + " signature in : " + name);
            try {
                verify = this.aliasSBF.get(s).verify(content);
                if (!verify) {
                    LogHolder.log(7, LogType.MISC, "Wrong Signature in " + name);
                    return false;
                }
                LogHolder.log(7, LogType.MISC, "Signature in " + name + " verified OK.");
            }
            catch (SignatureException ex) {
                return false;
            }
        }
        return verify;
    }
    
    private boolean isSignedJar() {
        if (this.m_jarFile == null) {
            return false;
        }
        final Vector aliasList = this.m_jarFile.getAliasList();
        final String[] array = { ".DSA", ".RSA" };
        if (aliasList.size() < 1) {
            return false;
        }
        if (!this.m_jarFile.fileExists("META-INF/MANIFEST.MF")) {
            return false;
        }
        for (int i = 0; i < aliasList.size(); ++i) {
            boolean b = false;
            final String upperCase = ("META-INF/" + aliasList.elementAt(i)).toUpperCase();
            for (int j = 0; j < array.length; ++j) {
                if (this.m_jarFile.fileExists(upperCase + array[j])) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
        }
        return true;
    }
    
    private boolean verifySFDigests(final String s) {
        final JarSignatureFile signatureFile = this.m_jarFile.getSignatureFile(s);
        if (signatureFile == null) {
            return false;
        }
        final Vector manifestDigestList = signatureFile.getManifestDigestList();
        for (int i = 0; i < manifestDigestList.size(); ++i) {
            final String s2 = manifestDigestList.elementAt(i);
            final String manifestDigest = signatureFile.getManifestDigest(s2);
            final Digest digestClass = this.getDigestClass(s2);
            final byte[] array = new byte[digestClass.getDigestSize()];
            try {
                final byte[] content = this.m_Manifest.getContent();
                if (content == null) {
                    LogHolder.log(7, LogType.MISC, "Manifest file null.");
                    return false;
                }
                digestClass.update(content, 0, content.length);
                digestClass.doFinal(array, 0);
                if (!manifestDigest.equals(new String(Base64.encode(array)))) {
                    LogHolder.log(7, LogType.MISC, "Digest verify failed for manifest file.");
                    return false;
                }
            }
            catch (Exception ex) {
                return false;
            }
        }
        final Vector fileNames = signatureFile.getFileNames();
        for (int j = 0; j < fileNames.size(); ++j) {
            final String s3 = fileNames.elementAt(j);
            final Vector digestList = this.m_Manifest.getDigestList(s3);
            for (int k = 0; k < digestList.size(); ++k) {
                final String s4 = digestList.elementAt(k);
                final String digest = signatureFile.getDigest(s3, s4);
                final byte[] entry = this.m_Manifest.getEntry(s3);
                final Digest digestClass2 = this.getDigestClass(s4);
                final byte[] array2 = new byte[digestClass2.getDigestSize()];
                try {
                    digestClass2.update(entry, 0, entry.length);
                    digestClass2.doFinal(array2, 0);
                    if (!digest.equals(new String(Base64.encode(array2)))) {
                        LogHolder.log(7, LogType.MISC, "Digest verify failed for " + s3);
                        LogHolder.log(7, LogType.MISC, s4);
                        LogHolder.log(7, LogType.MISC, digest);
                        return false;
                    }
                }
                catch (Exception ex2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean verifyManifestDigests() {
        final Vector fileNames = this.m_Manifest.getFileNames();
        for (int i = 0; i < fileNames.size(); ++i) {
            final String s = fileNames.elementAt(i);
            final JarFileEntry fileByName = this.m_jarFile.getFileByName(s);
            if (fileByName == null) {
                return false;
            }
            final Vector digestList = this.m_Manifest.getDigestList(s);
            for (int j = 0; j < digestList.size(); ++j) {
                final String s2 = digestList.elementAt(j);
                final String digest = this.m_Manifest.getDigest(fileByName, s2);
                final Digest digestClass = this.getDigestClass(s2);
                final byte[] array = new byte[digestClass.getDigestSize()];
                try {
                    final byte[] content = fileByName.getContent();
                    if (content == null) {
                        return false;
                    }
                    digestClass.update(content, 0, content.length);
                    digestClass.doFinal(array, 0);
                    if (!digest.equals(new String(Base64.encode(array)))) {
                        return false;
                    }
                }
                catch (Exception ex) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private Digest getDigestClass(String string) {
        final int index = string.indexOf("-");
        if (index != -1) {
            string = string.substring(0, index) + string.substring(index + 1);
        }
        if (this.digestCache.contains(string)) {
            final Digest digest = this.digestCache.get(string);
            digest.reset();
            return digest;
        }
        try {
            final Digest digest2 = (Digest)Class.forName("org.bouncycastle.crypto.digests." + string).newInstance();
            this.digestCache.put(string, digest2);
            return digest2;
        }
        catch (ClassNotFoundException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (InstantiationException ex3) {}
        return null;
    }
}
