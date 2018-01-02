// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import com.pchat.sc.StringUtil;
import pclient.shd.Config;
import pclient.shd.ChatModel;
import pclient.adv.AppletSpice;

public class ModHandler
{
    protected AppletSpice paraApplet;
    protected ChatModel chatModel;
    private Config paraConf;
    private ModWindow theMan;
    
    public ModHandler(final AppletSpice paraApplet, final ModWindow theMan) {
        this.chatModel = null;
        this.paraApplet = paraApplet;
        this.paraConf = paraApplet.paraConf;
        this.chatModel = paraApplet.chatModel;
        this.theMan = theMan;
    }
    
    public void error(final String s) {
    }
    
    public void info(final String s) {
    }
    
    public void clearInfo() {
    }
    
    public void browser(final String s) {
        this.paraConf.loadPage(s);
    }
    
    protected void mdFetch() {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12001));
    }
    
    public void mdPrevious() {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12003));
    }
    
    public void mdNext() {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12005));
    }
    
    protected void mdAnswer(final String s, final String s2) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12011, (short)20012, s, (short)20002, s2));
    }
    
    protected void mdShowQuestion(final String s) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12013, (short)20012, s));
    }
    
    protected void mdEdit(final String s, final String s2) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12015, (short)20012, s, (short)20002, s2));
    }
    
    protected void mdForward(final String s) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12017, (short)20012, s));
    }
    
    protected void mdForwardTo(final String s, final String s2) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12019, (short)20012, s, (short)20004, s2));
    }
    
    protected void mdForwardToAll(final String s) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12021, (short)20012, s));
    }
    
    protected void mdPost(final String s) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12023, (short)20002, s));
    }
    
    protected void mdDelete(final String s) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12025, (short)20012, s));
    }
    
    protected void mdSaveMessages(final String[] array) {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12041, (short)20000, StringUtil.catString(array, "\n")));
    }
    
    protected void mdLoadMessages() {
        this.checkConnection();
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10007, (short)12043));
    }
    
    public boolean checkConnection() {
        this.clearInfo();
        return this.chatModel.cmIsConnected();
    }
}
