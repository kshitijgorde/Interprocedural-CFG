// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.util.Date;
import com.pchat.sc.MsgOptions;
import com.pchat.sc.StringUtil;
import pclient.shd.Config;
import pclient.shd.ChatModel;
import pclient.adv.AppletSpice;

public class AdmHandler
{
    private static final String NOT_SUPER = "This feature is reserved for super users only.";
    protected AppletSpice paraApplet;
    protected ChatModel chatModel;
    protected Config paraConf;
    protected AdmManager theMan;
    private AmInfoBox infoBox;
    
    public AdmHandler(final AppletSpice paraApplet, final AdmManager theMan) {
        this.chatModel = null;
        this.paraApplet = paraApplet;
        this.paraConf = paraApplet.paraConf;
        this.chatModel = paraApplet.chatModel;
        this.theMan = theMan;
    }
    
    public void error(final String s) {
        this.theMan.printInfo(s);
        this.log(s);
    }
    
    public boolean isSuper() {
        return "super".equals(this.theMan.paraApplet.chatModel.cmProps().role);
    }
    
    public void info(final String s) {
        this.theMan.printInfo(s);
        this.log(s);
    }
    
    public void popInfo(final String s) {
        this.displayPopupInfo(s);
        this.log(s);
    }
    
    public void clearInfo() {
        this.theMan.printInfo(" ");
    }
    
    public void browser(final String s) {
        this.log("loading: " + s);
        this.paraApplet.vwInfo(s);
        this.paraConf.loadPage(s);
    }
    
    protected String[] userNames() {
        this.checkConnection();
        return this.paraApplet.getUserNames();
    }
    
    public void roomStatus() {
        this.checkConnection();
        this.logClient("Request room status.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12533));
    }
    
    protected void setTopic(final String s) {
        this.checkConnection();
        this.logClient("Request changing room topic to new topic: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12531, (short)20000, s));
    }
    
    protected void openRoom() {
        this.checkConnection();
        this.logClient("Request opening a room if it is closed.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12537));
    }
    
    protected void closeRoom(final String s, final boolean b) {
        this.checkConnection();
        this.logClient("Request closing room. ");
        if (s != null) {
            this.logClient("Reason for closing room: " + s);
        }
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12535, (short)20000, s));
        if (!b) {
            return;
        }
        this.logClient("Request clearing room. ");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12539));
    }
    
    protected void roomPasswd(final String s) {
        this.checkConnection();
        this.logClient("Request new room password: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12541, (short)20000, s));
    }
    
    protected void removeRoomPasswd() {
        this.checkConnection();
        this.logClient("Request removal of room password.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12543));
    }
    
    protected void transcript(final boolean b) {
        this.checkConnection();
        this.logClient("Request transcript logging: " + b);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12583, (short)20000, StringUtil.boolText(b)));
    }
    
    protected void profanityFilter(final boolean b) {
        this.checkConnection();
        this.logClient("Request profanity filter: " + b);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12545, (short)20000, StringUtil.boolText(b)));
    }
    
    protected void shoutFilter(final boolean b) {
        this.checkConnection();
        this.logClient("Request shout filter: " + b);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12547, (short)20000, StringUtil.boolText(b)));
    }
    
    protected void clearPlayback() {
        this.checkConnection();
        this.logClient("Request to clear room recent history.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12621));
    }
    
    protected void userInfo(final String s) {
        this.checkConnection();
        this.logClient("Request user info: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12581, (short)20000, s));
    }
    
    protected void gag(final String s) {
        this.checkConnection();
        this.logClient("Request gagging user: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12573, (short)20000, s));
    }
    
    protected void ungag(final String s) {
        this.checkConnection();
        this.logClient("Request removing gag on user: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12575, (short)20000, s));
    }
    
    protected void expel(final String s, final boolean b, final boolean b2) {
        this.checkConnection();
        this.logClient("Request to expel user: " + s);
        if (b) {
            this.logClient("Request to ban IP of user: " + s);
        }
        if (b2) {
            this.logClient("Request to ban IP Class C range of user: " + s);
        }
        if (b) {
            this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12563, (short)20000, s));
        }
        if (b2) {
            this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12565, (short)20000, s));
        }
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12561, (short)20000, s));
    }
    
    protected void cookieBan(final String s, final int n) {
        this.checkConnection();
        this.logClient("Request to cookie ban user: " + s + " for " + n + " days.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12623, (short)20004, s, (short)20002, "" + n));
    }
    
    protected void cookieGag(final String s, final int n) {
        this.checkConnection();
        this.logClient("Request to cookie gag user: " + s + " for " + n + " days.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12627, (short)20004, s, (short)20002, "" + n));
    }
    
    protected void unbanIP(final String s) {
        this.checkConnection();
        this.logClient("Request unbanning IP: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12629, (short)20000, s));
    }
    
    protected void banUser(final String s) {
        this.checkConnection();
        this.logClient("Request banning user name: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12631, (short)20000, s));
    }
    
    protected void unbanUser(final String s) {
        this.checkConnection();
        this.logClient("Request unbanning user name: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12633, (short)20000, s));
    }
    
    protected void saveMessages(final String[] array) {
        this.checkConnection();
        final String catString = StringUtil.catString(array, "\n");
        this.logClient("Request to save messages: " + catString);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12513, (short)20000, catString));
    }
    
    protected void loadMessages() {
        this.checkConnection();
        this.logClient("Request saved messages. ");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12511));
    }
    
    protected void sendUser(final String s, final String s2) {
        this.checkConnection();
        this.logClient("Request private chat with user: " + s + ", " + s2);
        this.chatModel.cmPrivate(s2, s, null);
    }
    
    protected void sendRoom(final String s) {
        this.checkConnection();
        this.logClient("Request to send message to room: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12509, (short)20000, s));
    }
    
    protected void saveWebLinks(final String[] array) {
        this.checkConnection();
        final String catString = StringUtil.catString(array, "\n");
        this.logClient("Request to save web links: " + catString);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12517, (short)20000, catString));
    }
    
    protected void loadWebLinks() {
        this.checkConnection();
        this.logClient("Request to load web links. ");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12515));
    }
    
    protected void popBrowserUser(final String s, final String s2) {
        this.checkConnection();
        this.logClient("Request to send cruise URL to user: " + s + " " + s2);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12507, (short)20004, s, (short)20002, s2));
    }
    
    protected void popBrowserRoom(final String s) {
        this.checkConnection();
        this.logClient("Request to send cruise URL to room: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12505, (short)20000, s));
    }
    
    protected void setModeration(final boolean b) {
        this.checkConnection();
        this.logClient("Request moderation: " + b);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12591, (short)20000, StringUtil.boolText(b)));
    }
    
    protected void addModerator(final String s) {
        this.checkConnection();
        this.logClient("Request to add a moderator: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12593, (short)20000, s));
    }
    
    protected void delModerator(final String s) {
        this.checkConnection();
        this.logClient("Request to delete a moderator: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12595, (short)20000, s));
    }
    
    protected void showModerators() {
        this.checkConnection();
        this.logClient("Request to show moderators.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12597));
    }
    
    protected void addSpeaker(final String s) {
        this.checkConnection();
        this.logClient("Request to add a speaker: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12599, (short)20000, s));
    }
    
    protected void delSpeaker(final String s) {
        this.checkConnection();
        this.logClient("Request to delete a speaker: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12611, (short)20000, s));
    }
    
    protected void showSpeakers() {
        this.checkConnection();
        this.logClient("Request to show speakers");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12613));
    }
    
    protected void siteCast(final String s) {
        this.checkConnection();
        this.logClient("Request to broadcast a message to all rooms of site: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12503, (short)20000, s));
    }
    
    protected void siteKick(final String s) {
        this.checkConnection();
        this.logClient("Request to empty all rooms of site: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12625, (short)20000, s));
    }
    
    protected void expelServer(final String s, final boolean b, final boolean b2) {
        this.checkConnection();
        this.logClient("Request to expel user at server level: " + s);
        if (b) {
            this.logClient("Request to (at server level) ban IP of user: " + s);
        }
        if (b2) {
            this.logClient("Request to (at server level) ban IP Class C range of user: " + s);
        }
        if (!this.isSuper()) {
            this.error("This feature is reserved for super users only.");
            return;
        }
        if (b) {
            this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12567, (short)20000, s));
        }
        if (b2) {
            this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12569, (short)20000, s));
        }
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12561, (short)20000, s));
    }
    
    protected void banRoom() {
        final String cmRoomName = this.chatModel.cmRoomName();
        this.checkConnection();
        this.logClient("Request to ban room name(server level): " + cmRoomName);
        if (!this.isSuper()) {
            this.error("This feature is reserved for super users only.");
            return;
        }
        if (StringUtil.isEmpty(cmRoomName)) {
            this.error("empty room name. ignored.");
            this.logClient("empty room name. ignored.");
            return;
        }
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12571, (short)20000, cmRoomName));
    }
    
    protected void serverCast(final String s) {
        this.checkConnection();
        this.logClient("Request to broadcast a message to entire server: " + s);
        if (!this.isSuper()) {
            this.error("This feature is reserved for super users only.");
            return;
        }
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12501, (short)20000, s));
    }
    
    protected void alertUser(final String s) {
        this.checkConnection();
        this.logClient("Request to send alert to: " + s);
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12579, (short)20004, s));
    }
    
    protected void alertRoom() {
        this.checkConnection();
        this.logClient("Request to send alert to room.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12577));
    }
    
    protected void invisibleOn() {
        this.checkConnection();
        this.logClient("Request to turn on invisible mode.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12585, (short)20000, StringUtil.boolText(true)));
    }
    
    protected void invisibleOff() {
        this.checkConnection();
        this.logClient("Request to turn off invisible mode.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12585, (short)20000, StringUtil.boolText(false)));
    }
    
    protected void avatarOn() {
        this.checkConnection();
        this.logClient("Request to set admin avatar.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12587, (short)20000, StringUtil.boolText(true)));
    }
    
    protected void avatarOff() {
        this.checkConnection();
        this.logClient("Request to remove admin avatar.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12587, (short)20000, StringUtil.boolText(false)));
    }
    
    protected void topListOn() {
        this.checkConnection();
        this.logClient("Request to stay on top of user list.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12589, (short)20000, StringUtil.boolText(true)));
    }
    
    protected void topListOff() {
        this.checkConnection();
        this.logClient("Request to not stay on top of user list.");
        this.chatModel.cmSend(this.chatModel.cmGenTransPack((short)10005, (short)12589, (short)20000, StringUtil.boolText(false)));
    }
    
    private void log(final String s) {
        this.theMan.printLog("[" + new Date() + "]");
        this.theMan.printLog("\n");
        this.theMan.printLog(s);
        this.theMan.printLog("\n");
    }
    
    private void logClient(final String s) {
        this.log("Admin: " + s);
    }
    
    public boolean checkConnection() {
        this.clearInfo();
        if (this.chatModel.cmIsConnected()) {
            return true;
        }
        this.error("error: not connected.");
        return false;
    }
    
    private void displayPopupInfo(final String s) {
        if (this.infoBox == null) {
            this.infoBox = new AmInfoBox(this.paraConf.title());
        }
        this.infoBox.displayText(s);
    }
}
