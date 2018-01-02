// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.SwingUtilities;

public class AreaEventTasks implements Runnable
{
    private DisplayArea parentArea;
    private SimpleBankQueue itemQueue;
    
    public AreaEventTasks(final DisplayArea parentArea) {
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
    
    protected void setCount(final String obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 8;
        simpleQueueItem.obj = obj;
        this.itemQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    protected void setTyping(final String obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 12;
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
                    this.parentArea.typingLabel.setText((String)simpleQueueItem.obj);
                    this.parentArea.typingLabel.invalidate();
                    this.parentArea.chatToolbar.validate();
                    break;
                }
                case 8: {
                    this.parentArea.countLabel.setText((String)simpleQueueItem.obj);
                    this.parentArea.countLabel.invalidate();
                    this.parentArea.chatToolbar.validate();
                    break;
                }
                default: {
                    System.err.println("Err5398," + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
