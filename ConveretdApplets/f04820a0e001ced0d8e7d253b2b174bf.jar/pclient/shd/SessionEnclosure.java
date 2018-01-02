// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.awt.Component;
import com.pchat.sc.WindowUtil;
import com.pchat.sc.ServParam;
import com.pchat.sc.PackComposer;
import com.pchat.sc.MsgOptions;
import com.pchat.sc.GenericResponse;
import com.pchat.sc.StringUtil;
import java.util.Hashtable;

public class SessionEnclosure
{
    private static final String CONN_WAIT = "Please wait while communicating with server";
    protected RankBasis rankData;
    protected SessionStatus sessionData;
    protected ClientBeat heartBeat;
    public ChatView chatView;
    public Config paraConf;
    private SessionReader sessionReader;
    protected String myName;
    protected String myRoom;
    protected Hashtable ignoreList;
    protected Hashtable invisibleList;
    protected Hashtable userList;
    protected SessionProps sessionProps;
    protected boolean localEcho;
    protected boolean pubTyping;
    protected boolean privTyping;
    protected boolean moderationOn;
    protected boolean modAllowPrivate;
    protected boolean modAllowPublic;
    private ConnectExec connThread;
    protected long loginTime;
    protected long joinTime;
    
    public SessionEnclosure(final ChatView chatView, final Config paraConf) {
        this.heartBeat = null;
        this.chatView = null;
        this.paraConf = null;
        this.sessionReader = null;
        this.myName = null;
        this.myRoom = null;
        this.invisibleList = null;
        this.localEcho = true;
        this.pubTyping = false;
        this.privTyping = true;
        this.moderationOn = false;
        this.modAllowPrivate = true;
        this.modAllowPublic = false;
        this.connThread = null;
        this.loginTime = 0L;
        this.joinTime = 0L;
        this.chatView = chatView;
        this.paraConf = paraConf;
        this.ignoreList = new Hashtable();
        this.invisibleList = new Hashtable();
        this.userList = new Hashtable();
        this.initState();
    }
    
    public void notifyDisconnection(final int n) {
        this.paraConf.printer().print("disconnect Notif," + n);
        if (n == 2) {
            this.chatView.vwInfo("SC873. Error occurred during secure connection.");
        }
        this.setDisconnect("   " + this.paraConf.get("Msg.Conn.Disconnect", "Disconnected."));
    }
    
    public void notifyIncoming(final byte[] array) {
        this.paraConf.printer().print("incoming," + StringUtil.showByte(array));
        this.sessionReader.process(array);
    }
    
    public SessionProps cmProps() {
        return this.sessionProps;
    }
    
    public void cmCheckCk(final String s) {
        this.send(this.cmGenTransPack((short)10003, (short)11253, (short)20002, s));
    }
    
    public void cmCheckCkGag(final String s) {
        this.send(this.cmGenTransPack((short)10003, (short)11255, (short)20002, s));
    }
    
    public boolean cmLocalIsAdmin(final String s) {
        return this.rankData.isAdmin(s);
    }
    
    public boolean cmLocalIsMod(final String s) {
        return this.rankData.isMod(s);
    }
    
    public boolean cmLocalIsSpk(final String s) {
        return this.rankData.isSpk(s);
    }
    
    public String[] cmModerators() {
        return this.rankData.moderatorNames();
    }
    
    public String[] cmSpeakers() {
        return this.rankData.speakerNames();
    }
    
    public String cmUserSelf() {
        return this.myName;
    }
    
    public boolean cmIsSelf(final String s) {
        return this.myName != null && this.myName.equals(s);
    }
    
    public String cmRoomName() {
        return this.myRoom;
    }
    
    public UserAttr cmUserAttr(final String s) {
        return this.userList.get(s);
    }
    
    public int cmCompare(final String s, final String s2) {
        return this.rankData.compareUsers(s, s2);
    }
    
    protected void exit(final ChatView chatView) {
        this.chatView.vwDisconnected(null);
        this.chatView = chatView;
        this.sendExit();
        this.setDisconnect(null);
    }
    
    private void sendExit() {
        this.send(this.cmGenTransPack((short)10001, (short)11001));
    }
    
    public boolean cmIsModerated() {
        return this.moderationOn;
    }
    
    public boolean cmIsModPrivate() {
        return this.modAllowPrivate;
    }
    
    public boolean cmIsModPublic() {
        return this.modAllowPublic;
    }
    
    public void cmModSubmit(final String s) {
        this.paraConf.printer().print("ask Q," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11225, (short)20000, s));
        this.chatView.vwInfo(this.paraConf.get("Msg.QuestionPre", "Q: ") + s + "\n" + this.paraConf.get("Msg.QuestionSub", "Your question has been submitted."));
    }
    
    public void cmLogin(final ConnData connData) {
        System.out.println("U=" + connData.username + " S=" + connData.sitename);
        final int state = this.sessionData.getState();
        if (state != 1 && state != 0) {
            if (state == 2 && this.connThread != null && System.currentTimeMillis() - this.connThread.getConnTime() > 5000L) {
                this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), false);
            }
            return;
        }
        if (this.connThread != null) {
            System.out.println("W-8902.");
            return;
        }
        this.clearState();
        this.sessionData.setState(2);
        (this.connThread = new ConnectExec(this, connData)).start();
    }
    
    public void cmSendUserPass(final String s) {
        this.send(this.cmGenTransPack((short)10001, (short)11023, (short)20000, s));
    }
    
    protected void connectedLogin(final ConnData connData, final GenericResponse genericResponse, final ConnectExec connectExec) {
        if (!genericResponse.successful) {
            this.setDisconnect(null);
            genericResponse.message = this.paraConf.get("Msg.Conn.CanNot", "cannot connect to server");
            this.chatView.vwLoginStatus(genericResponse);
            return;
        }
        this.loginAuth(connData, genericResponse);
        if (!this.sessionData.isError() && connectExec.keepGoing) {
            this.sessionData.setState(2);
        }
    }
    
    protected void joinNotify() {
        this.send(this.cmGenTransPack((short)10001, (short)11063, (short)20000, "J=" + (this.joinTime - this.loginTime + 500L) / 1000L));
    }
    
    public void cmJoin(final String s, final String s2, final boolean b) {
        if (this.sessionData.getState() != 3) {
            this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), false);
            return;
        }
        this.sessionData.setState(4);
        this.send(this.cmGenTransPack((short)10001, (short)11043, (short)20000, this.paraConf.getApplet().getDocumentBase().toString()));
        if (this.connThread != null) {
            this.send(this.cmGenTransPack((short)10001, (short)11063, (short)20000, "L=" + (this.loginTime - this.connThread.getConnTime() + 500L) / 1000L));
        }
        if (b) {
            this.send(this.cmGenTransPack((short)10001, (short)11041, (short)20000, null));
        }
        if (!StringUtil.isTrimmedEmpty(s2)) {
            this.send(this.cmGenTransPack((short)10001, (short)11045, (short)20000, s2));
        }
        this.send(this.cmGenTransPack((short)10001, (short)11047, (short)20000, s));
    }
    
    public void cmSendRoomPass(final String s) {
        this.send(this.cmGenTransPack((short)10001, (short)11045, (short)20000, s));
    }
    
    public void cmSwitch(final String s, final String s2) {
        if (this.sessionData.getState() != 5) {
            this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), false);
            return;
        }
        if (s.equals(this.cmRoomName())) {
            return;
        }
        this.sessionData.setState(7);
        this.send(this.cmGenTransPack((short)10003, (short)11233, (short)20008, s, (short)20006, s2));
    }
    
    public void cmCancelRoomPass() {
        this.paraConf.printer().print("cancel room pass, st=" + this.sessionData.getState());
        if (this.sessionData.getState() == 7) {
            this.sessionData.setState(5);
            this.send(this.cmGenTransPack((short)10001, (short)11051));
            return;
        }
        this.sendExit();
        this.setDisconnect(this.paraConf.get("Msg.Conn.Disconnect", "Disconnected."));
    }
    
    public void cmRoamCreate(final String s, final String s2) {
        if (this.sessionData.getState() != 3 && this.sessionData.getState() != 5) {
            this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), false);
            return;
        }
        if (this.sessionData.getState() == 5) {
            this.cmSwitch(s, s2);
            return;
        }
        if (this.sessionData.getState() == 3) {
            this.sessionData.setState(4);
            this.send(this.cmGenTransPack((short)10001, (short)11049, (short)20008, s, (short)20006, s2));
        }
    }
    
    public void cmPublic(final String s, final MsgOptions msgOptions) {
        this.paraConf.printer().print("public chat," + s);
        if (!this.checkJoin()) {
            return;
        }
        String ops = null;
        if (msgOptions != null) {
            ops = msgOptions.getOps();
        }
        if (this.localEcho) {
            this.chatView.vwSelfPublic(this.myName, s, msgOptions);
        }
        byte[] array;
        if (StringUtil.isEmpty(ops)) {
            array = this.cmGenTransPack((short)10003, (short)11201, (short)20002, s);
        }
        else {
            array = this.cmGenTransPack((short)10003, (short)11201, (short)20002, s, (short)20006, ops);
        }
        this.send(array);
    }
    
    public void cmPrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.paraConf.printer().print("private," + s2 + " " + s);
        if (!this.checkJoin()) {
            return;
        }
        if (this.localEcho) {
            this.chatView.vwSelfPrivate(s2, this.cmUserSelf(), s, null, msgOptions);
        }
        String ops = null;
        if (msgOptions != null) {
            ops = msgOptions.getOps();
        }
        byte[] array;
        if (StringUtil.isEmpty(ops)) {
            array = this.cmGenTransPack((short)10003, (short)11203, (short)20004, s2, (short)20002, s);
        }
        else {
            array = this.cmGenTransPack((short)10003, (short)11203, (short)20004, s2, (short)20006, ops, (short)20002, s);
        }
        this.send(array);
    }
    
    public void cmAvReq(final String s) {
        this.paraConf.printer().print("av req," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11237, (short)20000, s));
    }
    
    public void cmAvAccept(final String s) {
        this.paraConf.printer().print("av ack," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11239, (short)20000, s));
    }
    
    public void cmAvDeny(final String s) {
        this.paraConf.printer().print("av deny," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11241, (short)20000, s));
    }
    
    public void cmAvBcast() {
        this.paraConf.printer().print("av bcast");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11243));
    }
    
    public void cmAvViewBc(final String s) {
        this.paraConf.printer().print("av view bc");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11245, (short)20000, s));
    }
    
    public void cmAvAcceptBc(final String s) {
        this.paraConf.printer().print("av Apt bc");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11247, (short)20000, s));
    }
    
    public void cmAvRejBc(final String s) {
        this.paraConf.printer().print("av rej bc");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11249, (short)20000, s));
    }
    
    public void cmAvShowVid(final boolean b) {
        this.paraConf.printer().print("av show vid");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11251, (short)20000, StringUtil.boolText(b)));
    }
    
    public void cmTypingPub() {
        if (!this.pubTyping) {
            return;
        }
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11223));
    }
    
    public void cmTypingPriv(final String s) {
        if (!this.privTyping) {
            return;
        }
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11205, (short)20000, s));
    }
    
    public void cmAddIgnore(final String s) {
        if (this.myName != null && s.equals(this.myName)) {
            return;
        }
        this.ignoreList.put(s, s);
        this.chatView.vwRefreshUsers();
    }
    
    public void cmDeleteIgnore(final String s) {
        this.ignoreList.remove(s);
        this.chatView.vwRefreshUsers();
    }
    
    public boolean cmIsIgnored(final String s) {
        return this.ignoreList.containsKey(s);
    }
    
    public boolean cmIsInvisible(final String s) {
        return this.invisibleList.containsKey(s);
    }
    
    protected void setInvisible(final String s) {
        this.invisibleList.clear();
        if (s == null) {
            return;
        }
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int i = 0; i < splitString.length; ++i) {
            this.invisibleList.put(splitString[i], splitString[i]);
        }
        this.paraConf.printer().print("invisible=" + this.invisibleList);
    }
    
    public void cmPage() {
        this.paraConf.printer().print("paging");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11207));
    }
    
    public void cmAudio(final String s) {
        this.paraConf.printer().print("public audio," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11209, (short)20000, s));
    }
    
    public void cmAudio(final String s, final String s2) {
        this.paraConf.printer().print("private audio," + s2);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11231, (short)20004, s, (short)20010, s2));
    }
    
    public void cmStatus(final String s, final boolean b) {
        this.paraConf.printer().print("status," + s + " " + b);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11227, (short)20002, s, (short)20006, StringUtil.boolText(b)));
    }
    
    public void cmBlock(final boolean b) {
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11229, (short)20000, StringUtil.boolText(b)));
    }
    
    public void cmAvatar(final String s) {
        this.paraConf.printer().print("avatar," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11221, (short)20010, s));
    }
    
    public void cmQueryProfile(final String s) {
        this.paraConf.printer().print("check profile," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11211, (short)20000, s));
    }
    
    public void cmQueryList() {
        this.paraConf.printer().print("get room list");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11213));
    }
    
    public void cmQueryUsers(final String s) {
        this.paraConf.printer().print("get user list for," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11215, (short)20000, s));
    }
    
    public void cmQuerySearch(final String s) {
        this.paraConf.printer().print("search for," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11217, (short)20000, s));
    }
    
    public void cmPlayback() {
        this.paraConf.printer().print("playback request");
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11235));
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s) {
        return PackComposer.genTransPack(n, n2, this.createEnc(), n3, s);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2) {
        return PackComposer.genTransPack(n, n2, this.createEnc(), null);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s, final short n4, final String s2) {
        return PackComposer.genTransPack(n, n2, this.createEnc(), n3, s, n4, s2);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s, final short n4, final String s2, final short n5, final String s3) {
        return PackComposer.genTransPack(n, n2, this.createEnc(), n3, s, n4, s2, n5, s3);
    }
    
    public void cmSend(final byte[] array) {
        this.send(array);
    }
    
    public boolean cmIsConnected() {
        return this.sessionData.getState() == 5;
    }
    
    public boolean cmIsTunnel() {
        return this.connThread != null && this.connThread.tunnelConn;
    }
    
    public boolean cmSecConnected() {
        return this.connThread != null && this.connThread.secureConn;
    }
    
    public Object cmSecSession() {
        if (this.connThread == null) {
            return null;
        }
        return this.connThread.secSession();
    }
    
    public void cmSendDocIP(final String s) {
        this.paraConf.printer().print("send IP," + s);
        if (!this.checkJoin()) {
            return;
        }
        this.send(this.cmGenTransPack((short)10003, (short)11257, (short)20000, s));
    }
    
    public void cmPing() {
        this.paraConf.printer().print("ping");
        this.send(this.cmGenTransPack((short)10001, (short)11065, (short)20000, "" + System.currentTimeMillis()));
    }
    
    private void loginAuth(final ConnData connData, final GenericResponse genericResponse) {
        this.myName = connData.username;
        String s = "Adv";
        if (this.paraConf.mainClient.isBasic()) {
            s = "Lite";
        }
        this.send(this.cmGenTransPack((short)10001, (short)11003, (short)20000, "9.12,rel-9090," + s));
        this.send(this.cmGenTransPack((short)10001, (short)11005, (short)20000, WindowUtil.clientProps(this.paraConf.getApplet(), "9.12", "rel-9090-" + s)));
        if (!StringUtil.isEmpty(connData.cookie)) {
            this.send(this.cmGenTransPack((short)10001, (short)11025, (short)20000, connData.cookie));
        }
        if (!StringUtil.isEmpty(connData.passwd)) {
            this.send(this.cmGenTransPack((short)10001, (short)11023, (short)20000, connData.passwd));
        }
        this.send(this.cmGenTransPack((short)10001, (short)11021, (short)20000, connData.sitename));
        this.paraConf.printer().print("guest? " + connData.isGuest);
        if (connData.isGuest) {
            this.send(this.cmGenTransPack((short)10001, (short)11069));
        }
        this.send(this.cmGenTransPack((short)10001, (short)11027, (short)20000, connData.username));
    }
    
    private boolean checkJoin() {
        this.paraConf.printer().print("state=" + this.sessionData.getState());
        if (this.sessionData.getState() == 1) {
            this.chatView.vwError(this.paraConf.get("Msg.NotConnected", "Not connected."), true);
            return false;
        }
        if (this.sessionData.getState() == 2 || this.sessionData.getState() == 2) {
            this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), false);
            return false;
        }
        if (this.sessionData.getState() != 5) {
            System.out.println("Warn JN8934.");
            this.chatView.vwError(this.paraConf.get("Msg.Conn.Wait", "Please wait while communicating with server"), true);
            return false;
        }
        return true;
    }
    
    private Hashtable createEnc() {
        Hashtable<String, String> hashtable = null;
        final String value = this.paraConf.get("Net.Encode");
        if (value != null) {
            hashtable = new Hashtable<String, String>();
            hashtable.put("encoding", value);
        }
        return hashtable;
    }
    
    private void send(final byte[] array) {
        if (this.connThread == null) {
            System.err.println("Alert 879275.");
            return;
        }
        this.connThread.send(array);
    }
    
    protected void setDisconnect(final String s) {
        this.clearState();
        this.chatView.vwDisconnected(s);
    }
    
    public boolean heartBeat() {
        return true;
    }
    
    public long heartSeconds() {
        return 150L;
    }
    
    protected byte[] getHeart() {
        return this.cmGenTransPack((short)10003, (short)11219);
    }
    
    private void clearState() {
        if (this.heartBeat != null) {
            this.heartBeat.stopIt();
        }
        if (this.connThread != null) {
            this.connThread.discontinue();
            this.connThread = null;
        }
        this.sessionData.setState(1);
    }
    
    private void initState() {
        this.rankData = new RankBasis(this.paraConf, this);
        this.sessionData = new SessionStatus(this);
        this.sessionReader = new SessionReader(this, this.chatView, this.paraConf);
        this.localEcho = true;
        this.myName = null;
        this.myRoom = null;
        this.sessionProps = new SessionProps();
        this.userList = new Hashtable();
        this.invisibleList = new Hashtable();
        this.localEcho = false;
        this.pubTyping = false;
        this.privTyping = true;
        this.moderationOn = false;
        this.modAllowPrivate = true;
        this.modAllowPublic = false;
        this.loginTime = 0L;
        this.joinTime = 0L;
    }
}
