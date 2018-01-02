// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import pclient.adv.SimpleQueueItem;
import com.pchat.sc.MsgOptions;
import java.util.Enumeration;
import pclient.adv.SimpleBankQueue;
import java.util.Hashtable;
import pclient.adv.AppletSpice;
import pclient.adv.PrivInter;

public class PrivManager implements PrivInter, Runnable
{
    protected AppletSpice paraApplet;
    protected boolean isBeep;
    private Hashtable thirdParties;
    private SimpleBankQueue approveQueue;
    
    public PrivManager() {
        this.isBeep = true;
        this.approveQueue = new SimpleBankQueue();
    }
    
    public void run() {
        try {
            this.doQueueChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.thirdParties = new Hashtable();
    }
    
    public void checkTyping() {
        final Enumeration<PrivWindow> elements = this.thirdParties.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().eraseTyping();
        }
    }
    
    protected void setBeep(final boolean isBeep) {
        this.isBeep = isBeep;
        final Enumeration<PrivWindow> elements = this.thirdParties.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().topMenu.checkBeep.setState(this.isBeep);
        }
    }
    
    private void taskPrivMessage(final String nickTo, final String nickFrom, final String message, final String roomname, final MsgOptions mop) {
        final PrivItem obj = new PrivItem();
        obj.nickTo = nickTo;
        obj.nickFrom = nickFrom;
        obj.message = message;
        obj.roomname = roomname;
        obj.mop = mop;
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 2;
        simpleQueueItem.obj = obj;
        this.approveQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void taskPrivSound(final String nickTo, final String nickFrom, final String sound) {
        final PrivItem obj = new PrivItem();
        obj.nickTo = nickTo;
        obj.nickFrom = nickFrom;
        obj.sound = sound;
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 8;
        simpleQueueItem.obj = obj;
        this.approveQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    public void receivedPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        if (!this.exists(s2) && !this.paraApplet.globalChoice.approvePrivate) {
            this.taskPrivMessage(s, s2, s3, s4, msgOptions);
            return;
        }
        this.doReceivedPrivate(s, s2, s3, s4, msgOptions);
    }
    
    private void doReceivedPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.getNewPrivate(s2, s).receivedPrivate(s, s2, s3, s4, msgOptions);
    }
    
    private void doReceivedSound(final String s, final String s2, final String s3) {
        this.getNewPrivate(s2, s).receivedSound(s, s2, s3);
    }
    
    public void receivedSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.getNewPrivate(s, s2).receivedSelfPrivate(s, s2, s3, s4, msgOptions);
    }
    
    public void receivedPrivSound(final String s, final String s2, final String s3) {
        if (!this.exists(s2) && !this.paraApplet.globalChoice.approvePrivate) {
            this.taskPrivSound(s, s2, s3);
            return;
        }
        this.doReceivedSound(s, s2, s3);
    }
    
    public void initPrivate(final String s, final String s2, final String s3) {
        PrivWindow privWindow;
        if (this.thirdParties.containsKey(s2)) {
            privWindow = this.thirdParties.get(s2);
        }
        else {
            if (!this.allowPrivate(s)) {
                this.showNoPrivate();
                return;
            }
            if (!this.allowInitiate(s)) {
                this.showNoInitiate();
                return;
            }
            privWindow = new PrivWindow(this, s, s2);
            this.thirdParties.put(s2, privWindow);
        }
        privWindow.setVisible(true);
    }
    
    public void initAv(final String s, final String s2, final String s3) {
        this.initPrivate(s, s2, s3);
        final PrivWindow privWindow = this.thirdParties.get(s2);
        if (privWindow != null) {
            privWindow.avStart();
        }
    }
    
    public void receivedAvReq(final String s, final String s2, final String s3) {
        this.getNewPrivate(s2, s).receivedAvReq(s, s2, s3);
    }
    
    public void receivedAvReject(final String s, final String s2) {
        this.getNewPrivate(s2, s).displayText(this.paraApplet.paraConf.get("Msg.Av.Rjt", "Your party has declined your video chat."), (MsgOptions)null);
    }
    
    public void receivedAvWeb(final String s, final String s2, final String s3) {
        final PrivWindow newPrivate = this.getNewPrivate(s2, s);
        newPrivate.displayText(this.paraApplet.paraConf.get("Msg.Av.Bker", "If you have popup blocker, copy the URL below and open it in a web browser."), (MsgOptions)null);
        newPrivate.displayText(s3, (MsgOptions)null);
        this.paraApplet.paraConf.loadPage(s3);
    }
    
    public void showTyping(final String s) {
        if (this.thirdParties.containsKey(s)) {
            this.thirdParties.get(s).showTyping();
        }
    }
    
    public void joinLeave(final String s, final boolean b) {
        final Enumeration<String> keys = this.thirdParties.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s2.equals(s)) {
                ((PrivWindow)this.thirdParties.get(s2)).joinLeave(s2, b);
            }
        }
    }
    
    public void info(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        PrivWindow privWindow;
        if (this.thirdParties.containsKey(s2)) {
            privWindow = this.thirdParties.get(s2);
        }
        else {
            privWindow = new PrivWindow(this, s, s2);
            this.thirdParties.put(s2, privWindow);
        }
        privWindow.displayText(s3, msgOptions);
    }
    
    public void warnOnce(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        PrivWindow privWindow;
        if (this.thirdParties.containsKey(s2)) {
            privWindow = this.thirdParties.get(s2);
        }
        else {
            privWindow = new PrivWindow(this, s, s2);
            this.thirdParties.put(s2, privWindow);
        }
        privWindow.warnOnce(s3, msgOptions);
    }
    
    public void broadcast(final String s, final MsgOptions msgOptions) {
        if (s == null) {
            return;
        }
        final Enumeration<String> keys = this.thirdParties.keys();
        while (keys.hasMoreElements()) {
            final PrivWindow privWindow = this.thirdParties.get(keys.nextElement());
            if (privWindow != null) {
                privWindow.displayText(s, msgOptions);
            }
        }
    }
    
    public void clearAll() {
        final Enumeration<PrivWindow> elements = this.thirdParties.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().dispose();
        }
        this.thirdParties.clear();
    }
    
    private boolean allowPrivate(final String s) {
        return this.paraApplet.chatModel.cmLocalIsAdmin(s) || this.paraApplet.chatModel.cmLocalIsMod(s) || this.paraApplet.chatModel.cmLocalIsSpk(s) || !this.paraApplet.chatModel.cmIsModerated() || this.paraApplet.chatModel.cmIsModPrivate();
    }
    
    private boolean allowInitiate(final String s) {
        return this.paraApplet.chatModel.cmLocalIsAdmin(s) || this.paraApplet.chatModel.cmLocalIsMod(s) || this.paraApplet.chatModel.cmLocalIsSpk(s) || this.paraApplet.paraConf.getBool("Ctrl.InitPc", true);
    }
    
    private void showNoPrivate() {
        JOptionPane.showMessageDialog(this.paraApplet.getApplet(), this.paraApplet.paraConf.get("Msg.PrivateOff", "Private chat is disabled."));
    }
    
    private void showNoInitiate() {
        JOptionPane.showMessageDialog(this.paraApplet.getApplet(), this.paraApplet.paraConf.get("Msg.Prv.Init", "Only subscribers may start a private chat."));
    }
    
    private boolean exists(final String s) {
        return this.thirdParties.containsKey(s);
    }
    
    private PrivWindow getNewPrivate(final String s, final String s2) {
        PrivWindow privWindow;
        if (this.thirdParties.containsKey(s)) {
            privWindow = this.thirdParties.get(s);
        }
        else {
            privWindow = new PrivWindow(this, s2, s);
            this.thirdParties.put(s, privWindow);
        }
        return privWindow;
    }
    
    private void doQueueChanges() {
        final SimpleQueueItem[] dequeueAll = this.approveQueue.dequeueAll();
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 2: {
                    this.handlePrivMessage((PrivItem)simpleQueueItem.obj);
                    break;
                }
                case 8: {
                    this.handlePrivSound((PrivItem)simpleQueueItem.obj);
                    break;
                }
                default: {
                    System.err.println("Err7323," + simpleQueueItem);
                    break;
                }
            }
        }
    }
    
    private void handlePrivMessage(final PrivItem privItem) {
        if (this.exists(privItem.nickFrom) || this.paraApplet.globalChoice.approvePrivate) {
            this.doReceivedPrivate(privItem.nickTo, privItem.nickFrom, privItem.message, privItem.roomname, privItem.mop);
            return;
        }
        final String string = this.paraApplet.paraConf.get("Msg.Pv.NewPrmp", "You have receive a private message from: ") + privItem.nickFrom;
        final String string2 = this.paraApplet.paraConf.get("Msg.Pv.Tt", "Private Chat from: ") + privItem.nickFrom;
        final Object[] array = { this.paraApplet.paraConf.get("Lb.Pv.Acpt", "Accept"), this.paraApplet.paraConf.get("Lb.Pv.Ri", "Reject and Ignore this User"), this.paraApplet.paraConf.get("Lb.Pv.Cn", "Cancel") };
        final int showOptionDialog = JOptionPane.showOptionDialog(this.paraApplet.getApplet(), string, string2, 1, 3, null, array, array[2]);
        if (showOptionDialog == 0) {
            this.doReceivedPrivate(privItem.nickTo, privItem.nickFrom, privItem.message, privItem.roomname, privItem.mop);
        }
        else if (showOptionDialog == 1) {
            this.paraApplet.chatModel.cmAddIgnore(privItem.nickFrom);
        }
        else if (showOptionDialog != 2) {
            if (showOptionDialog != -1) {
                System.out.println("privP:" + showOptionDialog);
            }
        }
    }
    
    private void handlePrivSound(final PrivItem privItem) {
        if (this.exists(privItem.nickFrom) || this.paraApplet.globalChoice.approvePrivate) {
            this.doReceivedSound(privItem.nickTo, privItem.nickFrom, privItem.sound);
            return;
        }
        final String string = this.paraApplet.paraConf.get("Msg.Ps.NewPt", "You have receive a private sound from: ") + privItem.nickFrom;
        final String string2 = this.paraApplet.paraConf.get("Msg.Ps.NewTt", "Private Sound from: ") + privItem.nickFrom;
        final Object[] array = { this.paraApplet.paraConf.get("Lb.Pv.Acpt", "Accept"), this.paraApplet.paraConf.get("Lb.Pv.Ri", "Reject and Ignore this User"), this.paraApplet.paraConf.get("Lb.Pv.Cn", "Cancel") };
        final int showOptionDialog = JOptionPane.showOptionDialog(this.paraApplet.getApplet(), string, string2, 1, 3, null, array, array[2]);
        if (showOptionDialog == 0) {
            this.doReceivedSound(privItem.nickTo, privItem.nickFrom, privItem.sound);
        }
        else if (showOptionDialog == 1) {
            this.paraApplet.chatModel.cmAddIgnore(privItem.nickFrom);
        }
        else if (showOptionDialog != 2) {
            if (showOptionDialog != -1) {
                System.out.println("privS:" + showOptionDialog);
            }
        }
    }
}
