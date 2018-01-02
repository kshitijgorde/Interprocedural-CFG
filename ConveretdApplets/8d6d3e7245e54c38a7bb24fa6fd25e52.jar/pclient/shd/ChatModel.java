// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.MsgOptions;

public interface ChatModel
{
    String cmUserSelf();
    
    boolean cmIsSelf(final String p0);
    
    String cmRoomName();
    
    int cmCompare(final String p0, final String p1);
    
    void cmExit();
    
    void cmLogin(final ConnData p0);
    
    void cmSendUserPass(final String p0);
    
    void cmSendRoomPass(final String p0);
    
    void cmCancelRoomPass();
    
    UserAttr cmUserAttr(final String p0);
    
    void cmJoin(final String p0, final String p1, final boolean p2);
    
    void cmPublic(final String p0, final MsgOptions p1);
    
    void cmPrivate(final String p0, final String p1, final MsgOptions p2);
    
    void cmAvReq(final String p0);
    
    void cmAvAccept(final String p0);
    
    void cmAvDeny(final String p0);
    
    void cmAvBcast();
    
    void cmAvViewBc(final String p0);
    
    void cmAvAcceptBc(final String p0);
    
    void cmAvRejBc(final String p0);
    
    void cmAvShowVid(final boolean p0);
    
    void cmTypingPriv(final String p0);
    
    void cmTypingPub();
    
    void cmAddIgnore(final String p0);
    
    void cmDeleteIgnore(final String p0);
    
    boolean cmIsIgnored(final String p0);
    
    boolean cmIsInvisible(final String p0);
    
    boolean cmIsModerated();
    
    boolean cmIsModPrivate();
    
    boolean cmIsModPublic();
    
    void cmModSubmit(final String p0);
    
    void cmPage();
    
    void cmAudio(final String p0);
    
    void cmAudio(final String p0, final String p1);
    
    void cmStatus(final String p0, final boolean p1);
    
    void cmAvatar(final String p0);
    
    void cmBlock(final boolean p0);
    
    void cmQueryProfile(final String p0);
    
    void cmSwitch(final String p0, final String p1);
    
    void cmRoamCreate(final String p0, final String p1);
    
    void cmQueryList();
    
    void cmQueryUsers(final String p0);
    
    void cmQuerySearch(final String p0);
    
    void cmPlayback();
    
    SessionProps cmProps();
    
    void cmCheckCk(final String p0);
    
    void cmCheckCkGag(final String p0);
    
    boolean cmLocalIsAdmin(final String p0);
    
    boolean cmLocalIsBcaster(final String p0);
    
    boolean cmLocalIsMod(final String p0);
    
    boolean cmLocalIsSpk(final String p0);
    
    boolean cmLocalIsRegular(final String p0);
    
    String[] cmModerators();
    
    String[] cmSpeakers();
    
    byte[] cmGenTransPack(final short p0, final short p1, final short p2, final String p3);
    
    byte[] cmGenTransPack(final short p0, final short p1);
    
    byte[] cmGenTransPack(final short p0, final short p1, final short p2, final String p3, final short p4, final String p5);
    
    void cmSend(final byte[] p0);
    
    boolean cmIsConnected();
    
    boolean cmIsTunnel();
    
    boolean cmSecConnected();
    
    Object cmSecSession();
    
    void cmSendDocIP(final String p0);
    
    void cmPing();
}
