// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.security.SignatureException;
import anon.pay.PayAccountsFile;
import java.util.Vector;
import anon.infoservice.Database;
import anon.infoservice.MixCascade;
import java.util.Random;
import java.util.Hashtable;
import anon.infoservice.AbstractMixCascadeContainer;

public abstract class AbstractAutoSwitchedMixCascadeContainer extends AbstractMixCascadeContainer
{
    private Hashtable m_alreadyTriedCascades;
    private Random m_random;
    private MixCascade m_initialCascade;
    private MixCascade m_currentCascade;
    private boolean m_bKeepCurrentCascade;
    private boolean m_bSkipInitialCascade;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    
    public AbstractAutoSwitchedMixCascadeContainer(final boolean bSkipInitialCascade, final MixCascade initialCascade) {
        this.m_bSkipInitialCascade = bSkipInitialCascade;
        this.m_alreadyTriedCascades = new Hashtable();
        (this.m_random = new Random(System.currentTimeMillis())).nextInt();
        this.m_initialCascade = initialCascade;
        this.m_bKeepCurrentCascade = false;
    }
    
    public final MixCascade getInitialCascade() {
        return this.m_initialCascade;
    }
    
    public final MixCascade getNextRandomCascade() {
        return this.getNextCascade(true);
    }
    
    public final MixCascade getNextCascade() {
        return this.getNextCascade(false);
    }
    
    private final MixCascade getNextCascade(final boolean b) {
        synchronized (this.m_alreadyTriedCascades) {
            if (!this.isServiceAutoSwitched() && !b) {
                this.m_alreadyTriedCascades.clear();
                this.m_bKeepCurrentCascade = false;
                if (this.m_currentCascade == null) {
                    this.m_currentCascade = this.m_initialCascade;
                }
            }
            else if (this.m_bKeepCurrentCascade && !b) {
                this.m_bKeepCurrentCascade = false;
                if (this.m_currentCascade == null) {
                    this.m_currentCascade = this.m_initialCascade;
                }
                if (this.m_currentCascade != null) {
                    this.m_alreadyTriedCascades.put(this.m_currentCascade.getId(), this.m_currentCascade);
                }
            }
            else if (this.m_bSkipInitialCascade || this.m_initialCascade == null || this.m_alreadyTriedCascades.containsKey(this.m_initialCascade.getId())) {
                MixCascade currentCascade = null;
                boolean b2 = true;
                final Vector entryList = Database.getInstance((AbstractAutoSwitchedMixCascadeContainer.class$anon$infoservice$MixCascade == null) ? (AbstractAutoSwitchedMixCascadeContainer.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : AbstractAutoSwitchedMixCascadeContainer.class$anon$infoservice$MixCascade).getEntryList();
                if (entryList.size() > 0) {
                    int nextInt = this.m_random.nextInt();
                    if (nextInt < 0) {
                        nextInt *= -1;
                        b2 = false;
                    }
                    int n = nextInt % entryList.size();
                    int i;
                    for (i = 0; i < entryList.size(); ++i) {
                        currentCascade = entryList.elementAt(n);
                        if (!this.m_alreadyTriedCascades.containsKey(currentCascade.getId())) {
                            this.m_alreadyTriedCascades.put(currentCascade.getId(), currentCascade);
                            if (this.isSuitableCascade(currentCascade)) {
                                break;
                            }
                        }
                        if (b2) {
                            n = (n + 1) % entryList.size();
                        }
                        else if (--n < 0) {
                            n = entryList.size() - 1;
                        }
                    }
                    if (i == entryList.size()) {
                        if (this.m_alreadyTriedCascades.size() == 0) {}
                        currentCascade = null;
                    }
                }
                else if (this.m_initialCascade == null) {
                    return null;
                }
                if (currentCascade == null) {
                    this.m_bSkipInitialCascade = false;
                    this.m_alreadyTriedCascades.clear();
                    currentCascade = this.getNextCascade();
                    if (currentCascade == null && this.m_initialCascade != null) {
                        currentCascade = this.m_initialCascade;
                        this.m_alreadyTriedCascades.put(this.m_initialCascade.getId(), this.m_initialCascade);
                    }
                }
                this.m_currentCascade = currentCascade;
            }
            else {
                this.m_alreadyTriedCascades.put(this.m_initialCascade.getId(), this.m_initialCascade);
                this.m_currentCascade = this.m_initialCascade;
            }
            if (this.m_bSkipInitialCascade) {
                this.m_initialCascade = this.m_currentCascade;
            }
            this.m_bSkipInitialCascade = false;
        }
        return this.m_currentCascade;
    }
    
    public abstract boolean isServiceAutoSwitched();
    
    public abstract boolean isReconnectedAutomatically();
    
    public abstract boolean isPaidServiceAllowed();
    
    private final boolean isSuitableCascade(final MixCascade mixCascade) {
        return mixCascade != null && (!mixCascade.isPayment() || TrustModel.getCurrentTrustModel().isPaymentForced() || PayAccountsFile.getInstance().getChargedAccount(mixCascade.getPIID()) != null || (!TrustModel.getCurrentTrustModel().isEditable() && this.isPaidServiceAllowed())) && (this.m_initialCascade == null || !this.m_bSkipInitialCascade || !mixCascade.equals(this.m_initialCascade)) && this.isTrusted(mixCascade);
    }
    
    public final MixCascade getCurrentCascade() {
        return this.m_currentCascade;
    }
    
    public final boolean setCurrentCascade(final MixCascade currentCascade) {
        if (!this.isTrusted(currentCascade)) {
            return false;
        }
        synchronized (this.m_alreadyTriedCascades) {
            this.m_bKeepCurrentCascade = true;
            this.m_currentCascade = currentCascade;
        }
        return true;
    }
    
    public final void keepCurrentService(final boolean bKeepCurrentCascade) {
        synchronized (this.m_alreadyTriedCascades) {
            this.m_bKeepCurrentCascade = bKeepCurrentCascade;
        }
    }
    
    public final void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
        TrustModel.getCurrentTrustModel().checkTrust(mixCascade);
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
