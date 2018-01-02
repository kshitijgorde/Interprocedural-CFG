// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.ServicePack;
import com.pchat.sc.MsgOptions;
import com.pchat.sc.GenericResponse;

public class DummyView implements ChatView
{
    public void vwDisconnected(final String s) {
    }
    
    public UserChoice vwChoice() {
        return new UserChoice();
    }
    
    public GlobalChoice vwGlobal() {
        return new GlobalChoice();
    }
    
    public void vwLoginStatus(final GenericResponse genericResponse) {
    }
    
    public void vwUserPass(final String s) {
    }
    
    public void vwJoinStatus(final GenericResponse genericResponse) {
    }
    
    public void vwRoomPass(final String s) {
    }
    
    public void vwReconnHint(final boolean b) {
    }
    
    public void vwInfo(final String s) {
    }
    
    public void vwWarn(final String s) {
    }
    
    public void vwPopupWarn(final String s) {
    }
    
    public void vwError(final String s, final boolean b) {
    }
    
    public void vwInfoPrivate(final String s, final String s2, final MsgOptions msgOptions) {
    }
    
    public void vwWarnOncePrivate(final String s, final String s2, final MsgOptions msgOptions) {
    }
    
    public void vwSelfPublic(final String s, final String s2, final MsgOptions msgOptions) {
    }
    
    public void vwPublic(final String s, final String s2, final MsgOptions msgOptions) {
    }
    
    public void vwPrivate(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
    }
    
    public void vwSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
    }
    
    public void vwPrivSound(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
    }
    
    public void vwTypingPub(final String s) {
    }
    
    public void vwTypingPriv(final String s) {
    }
    
    public void vwUserList(final String[] array) {
    }
    
    public void vwCount(final String s) {
    }
    
    public void vwAvatar(final String s, final String s2) {
    }
    
    public void vwRefreshUsers() {
    }
    
    public void vwRefreshUser(final String s) {
    }
    
    public void vwNewJoin(final String s) {
    }
    
    public void vwNewExit(final String s, final String s2) {
    }
    
    public void vwAddUser(final String s) {
    }
    
    public void vwRoomList(final String s) {
    }
    
    public void vwLockedList(final String s) {
    }
    
    public void vwRoamUsers(final String s, final String s2) {
    }
    
    public void vwUserSearch(final String s) {
    }
    
    public void vwTreeJoin(final String s, final String s2) {
    }
    
    public void vwTreeExit(final String s, final String s2) {
    }
    
    public void vwTreeCount(final String s, final String s2, final boolean b) {
    }
    
    public void vwPlayback(final PlaybackMsg playbackMsg) {
    }
    
    public void vwAvAsk(final String s, final String s2) {
    }
    
    public void vwAvReject(final String s) {
    }
    
    public void vwAvWeb(final String s, final String s2) {
    }
    
    public void vwAvViewPermit(final String s, final String s2) {
    }
    
    public void vwModerate(final boolean b) {
    }
    
    public void vwModPrivate(final boolean b) {
    }
    
    public void vwModPublic(final boolean b) {
    }
    
    public void vwModPop() {
    }
    
    public void vwModMsg(final String s, final String s2) {
    }
    
    public void vwModAnswer(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void vwAdmin(final ServicePack servicePack) {
    }
    
    public void vwModmin(final ServicePack servicePack) {
    }
    
    public void vwPropsChange() {
    }
    
    public void vwCkBanned(final String s) {
    }
    
    public void vwCkGagged(final String s) {
    }
    
    public void vwPing(final String s) {
    }
}
