// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.infoservice.MixInfo;
import java.util.Date;
import anon.crypto.SignatureVerifier;
import java.security.SignatureException;
import anon.util.JAPMessages;
import anon.infoservice.MixCascade;
import java.util.Observable;

public class BasicTrustModel extends Observable implements ITrustModel
{
    public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
        int n = 0;
        Object o = null;
        if (mixCascade == null || (!mixCascade.isUserDefined() && !mixCascade.isVerified())) {
            throw new SignatureException(JAPMessages.getString("invalidSignature"));
        }
        if (SignatureVerifier.getInstance().isCheckSignatures()) {
            o = new SignatureException(JAPMessages.getString("invalidSignature"));
            for (int i = 0; i < mixCascade.getNumberOfMixes(); ++i) {
                if (mixCascade.getMixInfo(i) != null && mixCascade.getMixInfo(i).getCertPath() != null && mixCascade.getMixInfo(i).getCertPath().isValid(new Date()) && (i == 0 || i == mixCascade.getNumberOfMixes() - 1)) {
                    o = null;
                    break;
                }
            }
            if (o != null) {
                throw o;
            }
        }
        for (int j = 0; j < mixCascade.getNumberOfMixes(); ++j) {
            final MixInfo mixInfo = mixCascade.getMixInfo(j);
            if ((mixInfo == null && SignatureVerifier.getInstance().isCheckSignatures()) || (mixInfo != null && !mixInfo.isVerified())) {
                ++n;
                if (o == null) {
                    o = new SignatureException(JAPMessages.getString("invalidSignature") + " (Mix " + (j + 1) + ")");
                }
            }
        }
        if (o != null) {
            if (n > 1 || mixCascade.getNumberOfOperatorsShown() == 1 || mixCascade.getNumberOfMixes() <= 1) {
                o = new SignatureException(JAPMessages.getString("invalidSignature"));
            }
            throw o;
        }
    }
    
    public final boolean isTrusted(final MixCascade mixCascade) {
        if (mixCascade == null) {
            return false;
        }
        if (mixCascade != null && mixCascade.isShownAsTrusted()) {
            return true;
        }
        try {
            this.checkTrust(mixCascade);
            return true;
        }
        catch (TrustException ex) {
            return false;
        }
        catch (SignatureException ex2) {
            return false;
        }
    }
    
    public final boolean isTrusted(final MixCascade mixCascade, final StringBuffer sb) {
        if (mixCascade != null && mixCascade.isShownAsTrusted()) {
            return true;
        }
        try {
            this.checkTrust(mixCascade);
            return true;
        }
        catch (TrustException ex) {
            sb.append(ex.getMessage());
            return false;
        }
        catch (SignatureException ex2) {
            sb.append(ex2.getMessage());
            return false;
        }
    }
}
