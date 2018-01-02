import java.util.Date;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class Shareware
{
    static boolean bolLicensed;
    static String thisCodeBase;
    static String thisDocumentBase;
    static String HOMEURL;
    
    static boolean CheckDomain(final Applet thisParent) {
        URL urlTemp = thisParent.getCodeBase();
        (Shareware.thisCodeBase = urlTemp.getHost()).trim();
        urlTemp = thisParent.getDocumentBase();
        (Shareware.thisDocumentBase = urlTemp.getHost()).trim();
        if (Shareware.thisCodeBase.equals("") && Shareware.thisDocumentBase.equals("")) {
            return Shareware.bolLicensed = true;
        }
        if (Shareware.thisCodeBase.equalsIgnoreCase(Shareware.HOMEURL)) {
            return Shareware.bolLicensed = true;
        }
        return Shareware.bolLicensed = false;
    }
    
    static boolean CheckExpiry() {
        if (new Date().after(new Date(98, 4, 1))) {
            return Shareware.bolLicensed = false;
        }
        return Shareware.bolLicensed = true;
    }
    
    static {
        Shareware.HOMEURL = "www.celerity.demon.co.uk";
    }
}
