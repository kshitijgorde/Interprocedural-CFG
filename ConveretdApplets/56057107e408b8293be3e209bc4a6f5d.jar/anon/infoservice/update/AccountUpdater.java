// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.pay.PayAccount;
import java.util.Observable;
import anon.pay.PayAccountsFile;
import anon.util.Updater;

public class AccountUpdater extends Updater
{
    private boolean m_successfulUpdate;
    private boolean m_bFirstUpdateDone;
    static /* synthetic */ Class class$anon$pay$xml$XMLAccountInfo;
    
    public AccountUpdater() {
        super(new IUpdateInterval() {
            public long getUpdateInterval() {
                return 300000L;
            }
        }, new ObservableInfo() {
            public Integer getUpdateChanged() {
                return PayAccountsFile.CHANGED_AUTO_UPDATE;
            }
            
            public boolean isUpdateDisabled() {
                return !PayAccountsFile.getInstance().isBalanceAutoUpdateEnabled();
            }
            
            public boolean updateImmediately() {
                return true;
            }
        });
        this.m_successfulUpdate = false;
        this.m_bFirstUpdateDone = false;
    }
    
    public Class getUpdatedClass() {
        return (AccountUpdater.class$anon$pay$xml$XMLAccountInfo == null) ? (AccountUpdater.class$anon$pay$xml$XMLAccountInfo = class$("anon.pay.xml.XMLAccountInfo")) : AccountUpdater.class$anon$pay$xml$XMLAccountInfo;
    }
    
    protected void updateInternal() {
        this.m_successfulUpdate = false;
        if (Thread.currentThread().isInterrupted()) {
            this.m_successfulUpdate = true;
            return;
        }
        final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
        while (accounts.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
            final PayAccount payAccount = accounts.nextElement();
            try {
                if (!payAccount.shouldUpdateAccountInfo()) {
                    continue;
                }
                LogHolder.log(7, LogType.PAY, "Fetching statement for account: " + payAccount.getAccountNumber());
                payAccount.fetchAccountInfo(false);
                if (payAccount.getAccountInfo() == null) {
                    continue;
                }
                this.m_successfulUpdate = true;
                this.m_bFirstUpdateDone = true;
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.PAY, "Could not fetch statement for account: " + payAccount.getAccountNumber(), ex);
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            this.m_successfulUpdate = true;
        }
    }
    
    public boolean isFirstUpdateDone() {
        return this.m_bFirstUpdateDone;
    }
    
    protected boolean wasUpdateSuccessful() {
        return this.m_successfulUpdate;
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
