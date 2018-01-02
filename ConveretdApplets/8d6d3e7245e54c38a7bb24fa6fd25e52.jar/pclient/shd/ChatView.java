// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.ServicePack;
import com.pchat.sc.MsgOptions;
import com.pchat.sc.GenericResponse;

public interface ChatView
{
    void vwDisconnected(final String p0);
    
    UserChoice vwChoice();
    
    GlobalChoice vwGlobal();
    
    void vwLoginStatus(final GenericResponse p0);
    
    void vwUserPass(final String p0);
    
    void vwJoinStatus(final GenericResponse p0);
    
    void vwRoomPass(final String p0);
    
    void vwInfo(final String p0);
    
    void vwReconnHint(final boolean p0);
    
    void vwWarn(final String p0);
    
    void vwPopupWarn(final String p0);
    
    void vwError(final String p0, final boolean p1);
    
    void vwInfoPrivate(final String p0, final String p1, final MsgOptions p2);
    
    void vwWarnOncePrivate(final String p0, final String p1, final MsgOptions p2);
    
    void vwSelfPublic(final String p0, final String p1, final MsgOptions p2);
    
    void vwPublic(final String p0, final String p1, final MsgOptions p2);
    
    void vwPrivate(final String p0, final String p1, final String p2, final MsgOptions p3);
    
    void vwSelfPrivate(final String p0, final String p1, final String p2, final String p3, final MsgOptions p4);
    
    void vwPrivSound(final String p0, final String p1, final String p2, final MsgOptions p3);
    
    void vwTypingPub(final String p0);
    
    void vwTypingPriv(final String p0);
    
    void vwUserList(final String[] p0);
    
    void vwCount(final String p0);
    
    void vwAvatar(final String p0, final String p1);
    
    void vwRefreshUsers();
    
    void vwRefreshUser(final String p0);
    
    void vwNewJoin(final String p0);
    
    void vwNewExit(final String p0, final String p1);
    
    void vwAddUser(final String p0);
    
    void vwRoomList(final String p0);
    
    void vwLockedList(final String p0);
    
    void vwRoamUsers(final String p0, final String p1);
    
    void vwUserSearch(final String p0);
    
    void vwTreeJoin(final String p0, final String p1);
    
    void vwTreeExit(final String p0, final String p1);
    
    void vwTreeCount(final String p0, final String p1, final boolean p2);
    
    void vwPlayback(final PlaybackMsg p0);
    
    void vwAvAsk(final String p0, final String p1);
    
    void vwAvReject(final String p0);
    
    void vwAvWeb(final String p0, final String p1);
    
    void vwAvViewPermit(final String p0, final String p1);
    
    void vwModerate(final boolean p0);
    
    void vwModPrivate(final boolean p0);
    
    void vwModPublic(final boolean p0);
    
    void vwModPop();
    
    void vwModMsg(final String p0, final String p1);
    
    void vwModAnswer(final String p0, final String p1, final String p2, final String p3);
    
    void vwAdmin(final ServicePack p0);
    
    void vwModmin(final ServicePack p0);
    
    void vwPropsChange();
    
    void vwCkBanned(final String p0);
    
    void vwCkGagged(final String p0);
    
    void vwPing(final String p0);
}
