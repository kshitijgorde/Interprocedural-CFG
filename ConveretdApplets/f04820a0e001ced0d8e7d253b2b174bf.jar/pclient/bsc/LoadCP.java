// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

public class LoadCP extends Thread
{
    private BaseChat pChat;
    private int pCode;
    private String[] pCommand;
    
    public LoadCP(final BaseChat pChat, final int pCode, final String[] pCommand) {
        this.pChat = pChat;
        this.pCode = pCode;
        this.pCommand = pCommand;
    }
    
    public void run() {
        try {
            this.pChat.controlPanel = (CommonInter)Class.forName("pclient.bsx.ControlPanel").newInstance();
        }
        catch (Exception ex) {
            this.pChat.parentComp.showLocalInfo("Cannot load control panel. Low memory or no connection.\n");
            ex.printStackTrace();
            this.pChat.controlPanel = null;
            return;
        }
        this.pChat.controlPanel.setPara(this.pChat);
        this.pChat.controlPanel.restart();
        this.pChat.controlPanel.process(this.pCode, this.pCommand);
    }
}
