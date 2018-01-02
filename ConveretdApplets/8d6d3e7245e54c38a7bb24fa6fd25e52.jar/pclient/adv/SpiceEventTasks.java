// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.ServicePack;
import javax.swing.SwingUtilities;

public class SpiceEventTasks implements Runnable
{
    private AppletSpice parentArea;
    private SimpleBankQueue itemQueue;
    private SimpleBankQueue compQueue;
    
    public SpiceEventTasks(final AppletSpice parentArea) {
        this.parentArea = parentArea;
        this.itemQueue = new SimpleBankQueue();
        this.compQueue = new SimpleBankQueue();
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void setConn() {
        this.queueComp(14, null);
    }
    
    protected void setDis() {
        this.queueComp(16, null);
    }
    
    protected void pubMod(final boolean b) {
        this.queueComp(8, new Boolean(b));
    }
    
    protected void onMod() {
        this.queueComp(2, null);
    }
    
    protected void offMod() {
        this.queueComp(4, null);
    }
    
    private void queueComp(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.compQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    protected void userPass(final String s) {
        this.queueItem(14, s);
    }
    
    protected void roomPass(final String s) {
        this.queueItem(16, s);
    }
    
    protected void createMod() {
        this.queueItem(8, null);
    }
    
    protected void openMod() {
        this.queueItem(10, null);
    }
    
    protected void serviceMod(final ServicePack servicePack) {
        this.queueItem(2, servicePack);
    }
    
    protected void promptAdmin() {
        this.queueItem(12, null);
    }
    
    protected void createAdmin() {
        this.queueItem(18, null);
    }
    
    protected void serviceAdmin(final ServicePack servicePack) {
        this.queueItem(20, servicePack);
    }
    
    protected void verifySec() {
        this.queueItem(22, null);
    }
    
    protected void avViewPermit(final String s, final String s2) {
        this.queueItem(24, new String[] { s, s2 });
    }
    
    private void queueItem(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void doChanges() {
        final SimpleQueueItem[] dequeueAll = this.itemQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            this.parentArea.paraConf.printer().print("event," + simpleQueueItem);
            switch (simpleQueueItem.type) {
                case 12: {
                    this.parentArea.handlePromptForAdmin();
                    break;
                }
                case 18: {
                    this.parentArea.handleAdminCreate();
                    break;
                }
                case 20: {
                    this.parentArea.handleVwAdmin((ServicePack)simpleQueueItem.obj);
                    break;
                }
                case 22: {
                    this.parentArea.handleSec();
                    break;
                }
                case 24: {
                    final String[] array = (String[])simpleQueueItem.obj;
                    this.parentArea.handleAvViewBcast(array[0], array[1]);
                    break;
                }
                case 8: {
                    this.parentArea.handleModCreate();
                    break;
                }
                case 10: {
                    this.parentArea.handleModPop();
                    break;
                }
                case 2: {
                    this.parentArea.handleModmin((ServicePack)simpleQueueItem.obj);
                    break;
                }
                case 14: {
                    this.parentArea.handleVwUserPass((String)simpleQueueItem.obj);
                    break;
                }
                case 16: {
                    this.parentArea.handleVwRoomPass((String)simpleQueueItem.obj);
                    break;
                }
                default: {
                    System.err.println("Err98," + simpleQueueItem);
                    break;
                }
            }
        }
        final SimpleQueueItem[] dequeueAll2 = this.compQueue.dequeueAll();
        if (dequeueAll2 == null) {
            return;
        }
        for (int j = 0; j < dequeueAll2.length; ++j) {
            final SimpleQueueItem simpleQueueItem2 = dequeueAll2[j];
            this.parentArea.paraConf.printer().print("event:" + simpleQueueItem2);
            switch (simpleQueueItem2.type) {
                case 8: {
                    this.parentArea.handleVwModPublic((boolean)simpleQueueItem2.obj);
                    break;
                }
                case 2: {
                    this.parentArea.handleTurnOnMod();
                    break;
                }
                case 4: {
                    this.parentArea.handleTurnOffMod();
                    break;
                }
                case 14: {
                    this.parentArea.handleSetConnected();
                    break;
                }
                case 16: {
                    this.parentArea.handleSetDisconnected();
                    break;
                }
                default: {
                    System.err.println("Err398," + simpleQueueItem2);
                    break;
                }
            }
        }
    }
}
