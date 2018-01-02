// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.SwingUtilities;
import pclient.adv.SimpleQueueItem;
import pclient.adv.SimpleBankQueue;

public class PrivEventTasks implements Runnable
{
    private PrivWindow parentArea;
    private SimpleBankQueue itemQueue;
    
    public PrivEventTasks(final PrivWindow parentArea) {
        this.parentArea = parentArea;
        this.itemQueue = new SimpleBankQueue();
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void postReceived(final String obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 12;
        simpleQueueItem.obj = obj;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    protected void showTyping() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 2;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    protected void eraseTyping() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 4;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    protected void receivedAvReq(final String s, final String s2, final String s3) {
        final String[] obj = { s, s2, s3 };
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 8;
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
            switch (simpleQueueItem.type) {
                case 12: {
                    this.parentArea.handlePostReceived((String)simpleQueueItem.obj);
                    break;
                }
                case 2: {
                    this.parentArea.handleShowTyping();
                    break;
                }
                case 4: {
                    this.parentArea.handleEraseTyping();
                    break;
                }
                case 8: {
                    final String[] array = (String[])simpleQueueItem.obj;
                    this.parentArea.handleAvReq(array[0], array[1], array[2]);
                    break;
                }
                default: {
                    System.err.println("Err698," + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
