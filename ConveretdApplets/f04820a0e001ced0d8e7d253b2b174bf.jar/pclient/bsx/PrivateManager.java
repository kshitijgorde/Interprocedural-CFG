// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import pclient.bsc.ChatPrevRenderer;
import com.pchat.sc.MsgOptions;
import java.util.Enumeration;
import java.util.Hashtable;
import pclient.bsc.BaseChat;
import pclient.bsc.PrivateInter;

public class PrivateManager implements PrivateInter
{
    public BaseChat paraGUI;
    public boolean isBeep;
    private Hashtable senders;
    public static final String MSG_NOT_CONNECTED = "Not Connected to Chat Room.";
    
    public PrivateManager() {
        this.isBeep = true;
    }
    
    public void setPara(final BaseChat paraGUI) {
        this.paraGUI = paraGUI;
        this.senders = new Hashtable();
    }
    
    public boolean isConnected() {
        return this.paraGUI.isConnected();
    }
    
    public String getStamp() {
        return this.paraGUI.getStamp();
    }
    
    public void setBeep(final boolean isBeep) {
        this.isBeep = isBeep;
        final Enumeration<PrivateWindow> elements = this.senders.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().setBeep(this.isBeep);
        }
    }
    
    public void joinLeave(final String s, final boolean b) {
        final Enumeration<String> keys = this.senders.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s2.equals(s)) {
                ((PrivateWindow)this.senders.get(s2)).partnerJoinLeave(s2, b, this.paraGUI.getStamp());
            }
        }
    }
    
    public void info(final String s, final String s2, final String s3) {
        if (this.senders.containsKey(s2)) {
            final PrivateWindow privateWindow = this.senders.get(s2);
            privateWindow.displayText(s3);
            this.bringUpWindow(privateWindow);
        }
    }
    
    public void warnOnce(final String s, final String s2, final String s3) {
        if (this.senders.containsKey(s2)) {
            final PrivateWindow privateWindow = this.senders.get(s2);
            privateWindow.warnOnce(s3);
            this.bringUpWindow(privateWindow);
        }
    }
    
    public void broadcast(final String s) {
        final Enumeration<String> keys = this.senders.keys();
        while (keys.hasMoreElements()) {
            final PrivateWindow privateWindow = this.senders.get(keys.nextElement());
            if (privateWindow != null) {
                privateWindow.displayText(s);
            }
        }
    }
    
    public void sendPrivate(final String s, final String s2, final String s3) {
        this.paraGUI.chatModel.cmPrivate(s3, s2, null);
    }
    
    public void setAppletVar(final ChatPrevRenderer chatPrevRenderer) {
        chatPrevRenderer.setApplet(this.paraGUI);
    }
    
    public void clearAll() {
        final Enumeration<PrivateWindow> elements = this.senders.elements();
        while (elements.hasMoreElements()) {
            final PrivateWindow privateWindow = elements.nextElement();
            privateWindow.hide();
            privateWindow.dispose();
        }
        this.senders.clear();
    }
    
    public boolean addPrivate(final String s, final String s2) {
        final boolean containsKey = this.senders.containsKey(s2);
        PrivateWindow privateWindow;
        if (containsKey) {
            privateWindow = this.senders.get(s2);
        }
        else {
            if (!this.allowPrivate(s)) {
                this.showNoPrivate();
                return false;
            }
            if (!this.allowInitiate(s)) {
                this.showNoInitiate();
                return false;
            }
            privateWindow = new PrivateWindow(this, s, s2);
            this.senders.put(s2, privateWindow);
        }
        this.bringUpWindow(privateWindow);
        return containsKey;
    }
    
    public boolean doReceivedPrivate(final String s, final String s2, final String s3) {
        final boolean containsKey = this.senders.containsKey(s);
        PrivateWindow privateWindow;
        if (containsKey) {
            privateWindow = this.senders.get(s);
        }
        else {
            privateWindow = new PrivateWindow(this, s2, s);
            this.senders.put(s, privateWindow);
        }
        privateWindow.receiveMessage(s, s2, s3, this.paraGUI.getStamp());
        return containsKey;
    }
    
    public boolean doSelfPrivate(final String s, final String s2, final String s3) {
        final boolean containsKey = this.senders.containsKey(s2);
        PrivateWindow privateWindow;
        if (containsKey) {
            privateWindow = this.senders.get(s2);
        }
        else {
            privateWindow = new PrivateWindow(this, s, s2);
            this.senders.put(s2, privateWindow);
        }
        privateWindow.selfPrivateMessage(s, s3, this.paraGUI.getStamp());
        return containsKey;
    }
    
    public void receivedAvReq(final String s, final String s2, final String s3) {
        final PrivateWindow newPrivate = this.getNewPrivate(s2, s);
        this.bringUpWindow(newPrivate);
        newPrivate.receivedAvReq(s, s2, s3);
    }
    
    public void receivedAvReject(final String s, final String s2) {
        this.getNewPrivate(s2, s).displayText("Your party has declined your video chat.");
    }
    
    public void receivedAvWeb(final String s, final String s2, final String s3) {
        final PrivateWindow newPrivate = this.getNewPrivate(s2, s);
        newPrivate.displayText("If you have popup blocker, copy the URL below and open it in a web browser.");
        newPrivate.displayText(s3);
        this.paraGUI.paraConf.loadPage(s3);
    }
    
    private void bringUpWindow(final PrivateWindow privateWindow) {
        privateWindow.setVisible(true);
    }
    
    private boolean allowPrivate(final String s) {
        return this.paraGUI.chatModel.cmLocalIsAdmin(s) || this.paraGUI.chatModel.cmLocalIsMod(s) || this.paraGUI.chatModel.cmLocalIsSpk(s) || !this.paraGUI.chatModel.cmIsModerated() || this.paraGUI.chatModel.cmIsModPrivate();
    }
    
    private boolean allowInitiate(final String s) {
        return this.paraGUI.chatModel.cmLocalIsAdmin(s) || this.paraGUI.chatModel.cmLocalIsMod(s) || this.paraGUI.chatModel.cmLocalIsSpk(s) || this.paraGUI.paraConf.getBool("Ctrl.InitPc", true);
    }
    
    private void showNoPrivate() {
        this.paraGUI.parentComp.showLocalInfo("Private chat is disabled.");
    }
    
    private void showNoInitiate() {
        this.paraGUI.parentComp.showLocalInfo(this.paraGUI.paraConf.get("Msg.Prv.Init", "Only subscribers may start a private chat."));
    }
    
    private PrivateWindow getNewPrivate(final String s, final String s2) {
        PrivateWindow privateWindow;
        if (this.senders.containsKey(s)) {
            privateWindow = this.senders.get(s);
        }
        else {
            privateWindow = new PrivateWindow(this, s2, s);
            this.senders.put(s, privateWindow);
        }
        return privateWindow;
    }
}
