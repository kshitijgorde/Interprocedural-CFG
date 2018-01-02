// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.util.ResourceLoader;

public class Util
{
    public static void addDefaultCertificates(final String s, final int n, final String s2) {
        addDefaultCertificates(s, null, n, s2);
    }
    
    public static void addDefaultCertificates(final String s, final int n) {
        addDefaultCertificates(s, null, n, null);
    }
    
    public static void addDefaultCertificates(final String s, final String[] array, final int n) {
        addDefaultCertificates(s, array, n, null);
    }
    
    public static void addDefaultCertificates(final String s, final String[] array, final int n, final String s2) {
        JAPCertificate instance = null;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != null && (s2 == null || !array[i].endsWith(s2))) {
                    instance = JAPCertificate.getInstance(ResourceLoader.loadResource("certificates/" + s + array[i]));
                    if (instance != null) {
                        SignatureVerifier.getInstance().getVerificationCertificateStore().addCertificateWithoutVerification(instance, n, true, true);
                    }
                }
            }
        }
        final Enumeration<JAPCertificate> elements = (Enumeration<JAPCertificate>)JAPCertificate.getInstance("certificates/" + s, true, s2).elements();
        while (elements.hasMoreElements()) {
            instance = elements.nextElement();
            SignatureVerifier.getInstance().getVerificationCertificateStore().addCertificateWithoutVerification(instance, n, true, true);
        }
        if (instance == null) {
            LogHolder.log(3, LogType.MISC, "Error loading certificates of type '" + n + "'.");
        }
    }
}
