// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.MsgOptions;

public class UserSession implements ChatModel
{
    public ChatView chatView;
    private static ChatView dummyView;
    public Config paraConf;
    private SessionEnclosure sessionObj;
    private long lastLogin;
    
    public UserSession(final ChatView chatView, final Config paraConf) {
        this.chatView = null;
        this.paraConf = null;
        this.lastLogin = 0L;
        this.chatView = chatView;
        this.paraConf = paraConf;
        this.sessionObj = new SessionEnclosure(chatView, paraConf);
    }
    
    public SessionProps cmProps() {
        return this.sessionObj.sessionProps;
    }
    
    public void cmCheckCk(final String s) {
        this.sessionObj.cmCheckCk(s);
    }
    
    public void cmCheckCkGag(final String s) {
        this.sessionObj.cmCheckCkGag(s);
    }
    
    public boolean cmLocalIsAdmin(final String s) {
        return this.sessionObj.rankData.isAdmin(s);
    }
    
    public boolean cmLocalIsBcaster(final String s) {
        return this.sessionObj.rankData.isBcaster(s);
    }
    
    public boolean cmLocalIsMod(final String s) {
        return this.sessionObj.rankData.isMod(s);
    }
    
    public boolean cmLocalIsSpk(final String s) {
        return this.sessionObj.rankData.isSpk(s);
    }
    
    public boolean cmLocalIsRegular(final String s) {
        return this.sessionObj.rankData.isRegular(s);
    }
    
    public String[] cmModerators() {
        return this.sessionObj.rankData.moderatorNames();
    }
    
    public String[] cmSpeakers() {
        return this.sessionObj.rankData.speakerNames();
    }
    
    public String cmUserSelf() {
        return this.sessionObj.myName;
    }
    
    public boolean cmIsSelf(final String s) {
        return this.sessionObj.myName != null && this.sessionObj.myName.equals(s);
    }
    
    public String cmRoomName() {
        return this.sessionObj.myRoom;
    }
    
    public UserAttr cmUserAttr(final String s) {
        return this.sessionObj.userList.get(s);
    }
    
    public int cmCompare(final String s, final String s2) {
        return this.sessionObj.rankData.compareUsers(s, s2);
    }
    
    public void cmExit() {
        this.sessionObj.exit(UserSession.dummyView);
    }
    
    public boolean cmIsModerated() {
        return this.sessionObj.moderationOn;
    }
    
    public boolean cmIsModPrivate() {
        return this.sessionObj.modAllowPrivate;
    }
    
    public boolean cmIsModPublic() {
        return this.sessionObj.modAllowPublic;
    }
    
    public void cmModSubmit(final String s) {
        this.sessionObj.cmModSubmit(s);
    }
    
    public void cmLogin(final ConnData connData) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastLogin < 2000L) {
            return;
        }
        this.lastLogin = currentTimeMillis;
        this.sessionObj.exit(UserSession.dummyView);
        (this.sessionObj = new SessionEnclosure(this.chatView, this.paraConf)).cmLogin(connData);
    }
    
    public void cmSendUserPass(final String s) {
        this.sessionObj.cmSendUserPass(s);
    }
    
    public void cmJoin(final String s, final String s2, final boolean b) {
        this.sessionObj.cmJoin(s, s2, b);
    }
    
    public void cmSendRoomPass(final String s) {
        this.sessionObj.cmSendRoomPass(s);
    }
    
    public void cmSwitch(final String s, final String s2) {
        this.sessionObj.cmSwitch(s, s2);
    }
    
    public void cmCancelRoomPass() {
        this.sessionObj.cmCancelRoomPass();
    }
    
    public void cmRoamCreate(final String s, final String s2) {
        this.sessionObj.cmRoamCreate(s, s2);
    }
    
    public void cmPublic(final String s, final MsgOptions msgOptions) {
        this.sessionObj.cmPublic(s, msgOptions);
    }
    
    public void cmPrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.sessionObj.cmPrivate(s, s2, msgOptions);
    }
    
    public void cmAvReq(final String s) {
        this.sessionObj.cmAvReq(s);
    }
    
    public void cmAvAccept(final String s) {
        this.sessionObj.cmAvAccept(s);
    }
    
    public void cmAvDeny(final String s) {
        this.sessionObj.cmAvDeny(s);
    }
    
    public void cmAvBcast() {
        this.sessionObj.cmAvBcast();
    }
    
    public void cmAvViewBc(final String s) {
        this.sessionObj.cmAvViewBc(s);
    }
    
    public void cmAvAcceptBc(final String s) {
        this.sessionObj.cmAvAcceptBc(s);
    }
    
    public void cmAvRejBc(final String s) {
        this.sessionObj.cmAvRejBc(s);
    }
    
    public void cmAvShowVid(final boolean b) {
        this.sessionObj.cmAvShowVid(b);
    }
    
    public void cmTypingPub() {
        this.sessionObj.cmTypingPub();
    }
    
    public void cmTypingPriv(final String s) {
        this.sessionObj.cmTypingPriv(s);
    }
    
    public void cmAddIgnore(final String s) {
        this.sessionObj.cmAddIgnore(s);
    }
    
    public void cmDeleteIgnore(final String s) {
        this.sessionObj.cmDeleteIgnore(s);
    }
    
    public boolean cmIsIgnored(final String s) {
        return this.sessionObj.ignoreList.containsKey(s);
    }
    
    public boolean cmIsInvisible(final String s) {
        return this.sessionObj.invisibleList.containsKey(s);
    }
    
    public void cmPage() {
        this.sessionObj.cmPage();
    }
    
    public void cmAudio(final String s) {
        this.sessionObj.cmAudio(s);
    }
    
    public void cmAudio(final String s, final String s2) {
        this.sessionObj.cmAudio(s, s2);
    }
    
    public void cmStatus(final String s, final boolean b) {
        this.sessionObj.cmStatus(s, b);
    }
    
    public void cmBlock(final boolean b) {
        this.sessionObj.cmBlock(b);
    }
    
    public void cmAvatar(final String s) {
        this.sessionObj.cmAvatar(s);
    }
    
    public void cmQueryProfile(final String s) {
        this.sessionObj.cmQueryProfile(s);
    }
    
    public void cmQueryList() {
        this.sessionObj.cmQueryList();
    }
    
    public void cmQueryUsers(final String s) {
        this.sessionObj.cmQueryUsers(s);
    }
    
    public void cmQuerySearch(final String s) {
        this.sessionObj.cmQuerySearch(s);
    }
    
    public void cmPlayback() {
        this.sessionObj.cmPlayback();
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s) {
        return this.sessionObj.cmGenTransPack(n, n2, n3, s);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2) {
        return this.sessionObj.cmGenTransPack(n, n2);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s, final short n4, final String s2) {
        return this.sessionObj.cmGenTransPack(n, n2, n3, s, n4, s2);
    }
    
    public byte[] cmGenTransPack(final short n, final short n2, final short n3, final String s, final short n4, final String s2, final short n5, final String s3) {
        return this.sessionObj.cmGenTransPack(n, n2, n3, s, n4, s2, n5, s3);
    }
    
    public void cmSend(final byte[] array) {
        this.sessionObj.cmSend(array);
    }
    
    public boolean cmIsConnected() {
        return this.sessionObj.cmIsConnected();
    }
    
    public boolean cmIsTunnel() {
        return this.sessionObj.cmIsTunnel();
    }
    
    public boolean cmSecConnected() {
        return this.sessionObj.cmSecConnected();
    }
    
    public Object cmSecSession() {
        return this.sessionObj.cmSecSession();
    }
    
    public void cmSendDocIP(final String s) {
        this.sessionObj.cmSendDocIP(s);
    }
    
    public void cmPing() {
        this.sessionObj.cmPing();
    }
    
    static {
        UserSession.dummyView = new DummyView();
    }
}
