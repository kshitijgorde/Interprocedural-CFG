// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.MsgOptions;
import java.util.Hashtable;
import java.util.Date;
import com.pchat.sc.GenericResponse;
import com.pchat.sc.TransPack;
import com.pchat.sc.ServicePack;
import com.pchat.sc.ByteUtil;
import com.pchat.sc.TransDef;
import com.pchat.sc.StringUtil;
import com.pchat.sc.TransParser;

public class SessionReader
{
    private TransParser transParser;
    private SessionEnclosure chatSession;
    private ChatView chatView;
    private Config paraConf;
    
    public SessionReader(final SessionEnclosure chatSession, final ChatView chatView, final Config paraConf) {
        this.chatSession = chatSession;
        this.chatView = chatView;
        this.paraConf = paraConf;
        this.transParser = new TransParser();
    }
    
    public void process(final byte[] array) {
        if (array == null) {
            System.err.println("Err928023.");
            return;
        }
        this.paraConf.printer().print("interpreting," + StringUtil.showByte(array));
        this.transParser.add(array, array.length);
        if (!this.transParser.isValid()) {
            System.err.println("Err 738225.TP");
            this.chatSession.setDisconnect("Internal Error 738225.");
            return;
        }
        for (TransPack transPack = this.transParser.getNext(); transPack != null; transPack = this.transParser.getNext()) {
            if (!ByteUtil.bytesEqual(transPack.ident, TransDef.TYPE_CHAT, 2)) {
                System.err.println("Err 738272.TP");
                this.chatSession.setDisconnect("Internal Error 738272.");
                return;
            }
            if (!transPack.isValid) {
                System.err.println("Err 7373218.TP");
                this.chatSession.setDisconnect("Internal Error 7373218.");
                return;
            }
            final ServicePack parse = ServicePack.parse(transPack);
            this.paraConf.printer().print("incoming serv pack," + parse);
            if (!parse.isValid) {
                System.err.println("Err 734.SP");
                this.chatSession.setDisconnect("Internal Error SP734.");
                return;
            }
            this.intService(parse);
        }
    }
    
    private void intService(final ServicePack servicePack) {
        switch (servicePack.category) {
            case 100: {
                this.doConnCat(servicePack);
                break;
            }
            case 200: {
                this.doCtCat(servicePack);
                break;
            }
            case 300: {
                this.chatView.vwAdmin(servicePack);
                break;
            }
            case 400: {
                this.chatView.vwModmin(servicePack);
                break;
            }
            default: {
                System.err.println("Err222582. unknown com." + servicePack.category);
                break;
            }
        }
    }
    
    private void doConnCat(final ServicePack servicePack) {
        this.paraConf.printer().print("service=" + servicePack);
        String disconnect = null;
        String pvalue = null;
        if (servicePack.paramCount > 0) {
            pvalue = servicePack.paramList[0].pvalue;
        }
        final GenericResponse genericResponse = new GenericResponse();
        switch (servicePack.command) {
            case 104: {
                if (servicePack.paramCount > 0) {
                    disconnect = servicePack.getValue((short)20002);
                    final String value = servicePack.getValue((short)20006);
                    if (value != null) {
                        this.chatView.vwReconnHint(StringUtil.getBool(value, true));
                    }
                }
                this.chatSession.setDisconnect(disconnect);
                break;
            }
            case 106: {
                if (servicePack.paramCount > 0) {
                    disconnect = servicePack.getValue((short)20002);
                    final String value2 = servicePack.getValue((short)20006);
                    if (value2 != null) {
                        this.chatView.vwReconnHint(StringUtil.getBool(value2, true));
                    }
                }
                this.chatView.vwWarn(disconnect);
                break;
            }
            case 122: {
                this.chatView.vwPopupWarn(pvalue);
                break;
            }
            case 108: {
                this.chatView.vwInfo(pvalue);
                break;
            }
            case 102: {
                this.chatSession.localEcho = StringUtil.getBool(pvalue, true);
                break;
            }
            case 110: {
                this.chatSession.sessionData.setState(3);
                genericResponse.successful = true;
                genericResponse.message = pvalue;
                this.chatSession.loginTime = System.currentTimeMillis();
                System.out.println(new Date() + " login OK");
                if (!StringUtil.isEmpty(pvalue)) {
                    this.chatSession.myName = pvalue;
                    System.out.println("Guest:" + this.chatSession.myName);
                }
                this.chatView.vwLoginStatus(genericResponse);
                break;
            }
            case 112: {
                this.chatSession.sessionData.setState(2);
                this.chatView.vwUserPass(pvalue);
                break;
            }
            case 114: {
                this.paraConf.printer().print("Join was OK.");
                if (this.chatSession.joinTime == 0L) {
                    this.chatSession.joinTime = System.currentTimeMillis();
                    this.chatSession.joinNotify();
                }
                this.chatSession.sessionData.setState(5);
                genericResponse.successful = true;
                this.chatSession.myRoom = pvalue;
                if (this.chatSession.heartBeat != null) {
                    this.chatSession.heartBeat.stopIt();
                }
                (this.chatSession.heartBeat = new ClientBeat(this.chatSession)).start();
                this.chatSession.userList = new Hashtable();
                this.chatSession.invisibleList.clear();
                System.out.println(new Date() + " join OK, " + this.chatSession.myRoom);
                this.chatView.vwJoinStatus(genericResponse);
                break;
            }
            case 118: {
                this.chatView.vwRoomPass(pvalue);
                break;
            }
            case 126: {
                System.out.println("MB pass request ");
                break;
            }
            case 120: {
                this.chatSession.sessionProps.member = StringUtil.getBool(pvalue, false);
                break;
            }
            case 124: {
                this.chatView.vwPing(pvalue);
                break;
            }
            default: {
                System.err.println("Err856723. unknown," + servicePack.command);
                break;
            }
        }
    }
    
    private void doCtCat(final ServicePack servicePack) {
        this.paraConf.printer().print("service=" + servicePack);
        String pvalue = null;
        if (servicePack.paramCount > 0) {
            pvalue = servicePack.paramList[0].pvalue;
        }
        String s = null;
        String s2 = null;
        MsgOptions msgOptions = null;
        String s3 = null;
        String value = null;
        final GenericResponse genericResponse = new GenericResponse();
        switch (servicePack.command) {
            case 202: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                    final String value2 = servicePack.getValue((short)20006);
                    if (value2 != null) {
                        msgOptions = new MsgOptions();
                        msgOptions.setOps(value2);
                    }
                }
                if (this.chatSession.cmIsSelf(s)) {
                    this.chatView.vwSelfPublic(s, s3, msgOptions);
                    break;
                }
                if (!this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwPublic(s, s3, msgOptions);
                    break;
                }
                break;
            }
            case 206: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                    final String value3 = servicePack.getValue((short)20006);
                    if (value3 != null) {
                        msgOptions = new MsgOptions();
                        msgOptions.setOps(value3);
                    }
                }
                this.chatView.vwSelfPrivate(s, this.chatSession.cmUserSelf(), s3, null, msgOptions);
                break;
            }
            case 204: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                    s2 = servicePack.getValue((short)20008);
                    final String value4 = servicePack.getValue((short)20006);
                    if (value4 != null) {
                        msgOptions = new MsgOptions();
                        msgOptions.setOps(value4);
                    }
                }
                this.chatSession.paraConf.printer().print("private," + pvalue);
                if (!this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwPrivate(s, s3, s2, msgOptions);
                    break;
                }
                break;
            }
            case 208: {
                if (pvalue == null) {
                    break;
                }
                this.chatSession.paraConf.printer().print("private, typing," + pvalue);
                if (!this.chatSession.cmIsIgnored(pvalue)) {
                    this.chatView.vwTypingPriv(pvalue);
                    break;
                }
                break;
            }
            case 210: {
                if (pvalue != null) {
                    this.chatView.vwTypingPub(pvalue);
                    break;
                }
                break;
            }
            case 212: {
                if (pvalue != null) {
                    this.chatSession.pubTyping = StringUtil.getBool(pvalue, false);
                    break;
                }
                break;
            }
            case 214: {
                if (pvalue != null) {
                    this.chatSession.privTyping = StringUtil.getBool(pvalue, true);
                    break;
                }
                break;
            }
            case 220: {
                if (pvalue == null) {
                    break;
                }
                final String[] splitString = StringUtil.splitString(pvalue, "|", false);
                for (int i = 0; i < splitString.length; ++i) {
                    this.addUser(splitString[i]);
                }
                if (!this.paraConf.isSimpleCSR()) {
                    this.chatView.vwUserList(splitString);
                    break;
                }
                break;
            }
            case 228: {
                if (pvalue != null) {
                    this.chatView.vwCount(pvalue);
                    break;
                }
                break;
            }
            case 222: {
                this.chatSession.rankData.setAdmins(pvalue);
                break;
            }
            case 272: {
                this.chatSession.setInvisible(pvalue);
                this.chatView.vwRefreshUsers();
                break;
            }
            case 276: {
                this.chatSession.rankData.setModerators(pvalue);
                break;
            }
            case 278: {
                this.chatSession.rankData.setSpeakers(pvalue);
                break;
            }
            case 224: {
                if (!this.paraConf.isSimpleCSR()) {
                    this.userJoined(pvalue);
                    break;
                }
                break;
            }
            case 226: {
                if (!this.paraConf.isSimpleCSR()) {
                    if (servicePack.paramCount > 0) {
                        servicePack.getValue((short)20004);
                        value = servicePack.getValue((short)20006);
                    }
                    this.userLeft(pvalue, value);
                    break;
                }
                break;
            }
            case 234: {
                final String value5 = this.paraConf.get("Val.Sd.Alert", "tone");
                if (this.paraConf.getBool("Op.Alert.Snd", true)) {
                    this.chatView.vwInfo(this.chatSession.paraConf.get("Msg.SentSound", "sent sound:") + " " + value5);
                }
                this.paraConf.playServer(value5);
                break;
            }
            case 236: {
                final String value6 = servicePack.getValue((short)20002);
                final String value7 = servicePack.getValue((short)20006);
                this.chatView.vwInfo(value6);
                if (!StringUtil.isEmpty(value7)) {
                    this.paraConf.loadPage(value7);
                    break;
                }
                break;
            }
            case 238: {
                if (pvalue != null) {
                    final boolean bool = StringUtil.getBool(pvalue, false);
                    this.chatSession.moderationOn = bool;
                    this.chatView.vwModerate(bool);
                    break;
                }
                break;
            }
            case 240: {
                if (pvalue != null) {
                    final boolean bool2 = StringUtil.getBool(pvalue, false);
                    this.chatSession.modAllowPrivate = bool2;
                    this.chatView.vwModPrivate(bool2);
                    break;
                }
                break;
            }
            case 242: {
                if (pvalue != null) {
                    final boolean bool3 = StringUtil.getBool(pvalue, false);
                    this.chatSession.modAllowPublic = bool3;
                    this.chatView.vwModPublic(bool3);
                    break;
                }
                break;
            }
            case 280: {
                this.chatView.vwModPop();
                break;
            }
            case 282: {
                this.chatView.vwModMsg(servicePack.getValue((short)20004), servicePack.getValue((short)20002));
                break;
            }
            case 284: {
                this.chatView.vwModAnswer(servicePack.getValue((short)20004), servicePack.getValue((short)20002), null, null);
                break;
            }
            case 286: {
                this.chatView.vwModAnswer(servicePack.getValue((short)20004), servicePack.getValue((short)20002), servicePack.getValue((short)20016), servicePack.getValue((short)20018));
                break;
            }
            case 288: {
                this.paraConf.printer().print("mod/bcst to add user," + pvalue);
                this.paraConf.printer().print("user list," + this.chatSession.userList);
                if (!this.chatSession.userList.containsKey(pvalue)) {
                    this.paraConf.printer().print("mod/bcst adding user," + pvalue);
                    this.addUser(pvalue);
                    break;
                }
                break;
            }
            case 290: {
                if (this.chatSession.userList.containsKey(pvalue)) {
                    this.userLeft(pvalue, null);
                    break;
                }
                break;
            }
            case 244: {
                final String value8 = servicePack.getValue((short)20004);
                final String value9 = servicePack.getValue((short)20010);
                if (value8 != null && value9 != null && this.chatView.vwGlobal().audio && !this.chatSession.cmIsIgnored(value8)) {
                    this.chatView.vwInfo("<" + value8 + "> " + this.chatSession.paraConf.get("Msg.SentSound", "sent sound:") + " " + value9);
                    this.paraConf.play(value9);
                    break;
                }
                break;
            }
            case 246: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20010);
                }
                if (!StringUtil.isEmpty(s) && !StringUtil.isEmpty(s3) && this.chatView.vwGlobal().audio && !this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwPrivSound(s, s3, null, null);
                    break;
                }
                break;
            }
            case 248: {
                final String value10 = servicePack.getValue((short)20004);
                final String value11 = servicePack.getValue((short)20002);
                final boolean bool4 = StringUtil.getBool(servicePack.getValue((short)20006), false);
                if (value10 != null && value11 != null) {
                    final UserAttr userAttr = this.chatSession.userList.get(value10);
                    if (userAttr != null) {
                        userAttr.status = value11;
                        userAttr.busy = bool4;
                    }
                    else {
                        System.err.println("Err 72839." + value10);
                    }
                }
                if (value10 != null) {
                    this.chatView.vwRefreshUser(value10);
                    break;
                }
                break;
            }
            case 230: {
                final String value12 = servicePack.getValue((short)20004);
                final String value13 = servicePack.getValue((short)20010);
                if (value12 != null && value13 != null) {
                    final UserAttr userAttr2 = this.chatSession.userList.get(value12);
                    if (userAttr2 == null) {
                        System.out.println("Err 892384.");
                    }
                    else {
                        userAttr2.avatar = value13;
                    }
                    this.chatView.vwRefreshUser(value12);
                    break;
                }
                break;
            }
            case 258: {
                this.chatView.vwRoomList(pvalue);
                break;
            }
            case 260: {
                this.chatView.vwLockedList(pvalue);
                break;
            }
            case 262: {
                final String value14 = servicePack.getValue((short)20008);
                final String value15 = servicePack.getValue((short)20002);
                if (value14 != null && value15 != null) {
                    this.chatView.vwRoamUsers(value14, value15);
                    break;
                }
                break;
            }
            case 264: {
                this.chatView.vwUserSearch(pvalue);
                break;
            }
            case 306: {
                final String value16 = servicePack.getValue((short)20008);
                final String value17 = servicePack.getValue((short)20002);
                if (value16 != null && value17 != null) {
                    this.chatView.vwTreeJoin(value16, value17);
                    break;
                }
                break;
            }
            case 308: {
                final String value18 = servicePack.getValue((short)20008);
                final String value19 = servicePack.getValue((short)20002);
                if (value18 != null && value19 != null) {
                    this.chatView.vwTreeExit(value18, value19);
                    break;
                }
                break;
            }
            case 310: {
                final String value20 = servicePack.getValue((short)20008);
                final String value21 = servicePack.getValue((short)20002);
                final String value22 = servicePack.getValue((short)20006);
                if (value20 != null && value21 != null && value22 != null) {
                    this.chatView.vwTreeCount(value20, value21, StringUtil.getBool(value22, false));
                    break;
                }
                break;
            }
            case 266: {
                final String value23 = servicePack.getValue((short)20002);
                final String value24 = servicePack.getValue((short)20006);
                this.chatSession.sessionProps.role = value23;
                this.chatSession.sessionProps.permissions = value24;
                this.chatView.vwPropsChange();
                break;
            }
            case 268: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                }
                if (!StringUtil.isEmpty(s) && !StringUtil.isEmpty(s3)) {
                    this.chatView.vwInfoPrivate(s, s3, null);
                    break;
                }
                break;
            }
            case 270: {
                if (servicePack.paramCount > 0) {
                    s3 = servicePack.getValue((short)20002);
                }
                if (!StringUtil.isEmpty(s3)) {
                    this.chatView.vwInfo(s3);
                }
                this.paraConf.printer().print("cancel switch," + this.chatSession.sessionData.getState());
                if (this.chatSession.sessionData.getState() == 7) {
                    this.chatSession.sessionData.setState(5);
                    break;
                }
                System.err.println("Err RM424332.");
                break;
            }
            case 232: {
                if (!StringUtil.isEmpty(pvalue)) {
                    this.chatView.vwInfo(pvalue);
                    this.paraConf.loadPage(pvalue);
                    break;
                }
                break;
            }
            case 274: {
                final PlaybackMsg playbackMsg = new PlaybackMsg();
                if (servicePack.paramCount > 0) {
                    playbackMsg.date = servicePack.getValue((short)20014);
                    playbackMsg.sender = servicePack.getValue((short)20004);
                    playbackMsg.msg = servicePack.getValue((short)20002);
                }
                this.chatView.vwPlayback(playbackMsg);
                break;
            }
            case 292: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s2 = servicePack.getValue((short)20008);
                }
                this.chatSession.paraConf.printer().print("avask," + s + " " + s2);
                if (!this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwAvAsk(s, s2);
                    break;
                }
                break;
            }
            case 294: {
                this.chatSession.paraConf.printer().print("avRjt," + pvalue);
                if (!StringUtil.isEmpty(pvalue) && !this.chatSession.cmIsIgnored(pvalue)) {
                    this.chatView.vwAvReject(pvalue);
                    break;
                }
                break;
            }
            case 296: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                }
                this.chatSession.paraConf.printer().print("avPg," + s + " " + s3);
                if (!this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwAvWeb(s, s3);
                    break;
                }
                break;
            }
            case 298: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s2 = servicePack.getValue((short)20008);
                }
                this.chatSession.paraConf.printer().print("avViewReq," + s + " " + s2);
                if (!this.chatSession.cmIsIgnored(s)) {
                    this.chatView.vwAvViewPermit(s, s2);
                    break;
                }
                break;
            }
            case 300: {
                if (servicePack.paramCount <= 0) {
                    break;
                }
                final String value25 = servicePack.getValue((short)20004);
                final String value26 = servicePack.getValue((short)20002);
                final boolean bool5 = StringUtil.getBool(value26, false);
                if (value25 != null && value26 != null) {
                    final UserAttr userAttr3 = this.chatSession.userList.get(value25);
                    if (userAttr3 != null) {
                        userAttr3.showVid = bool5;
                    }
                    else {
                        System.err.println("Err 7256." + value25);
                    }
                }
                if (value25 != null) {
                    this.chatView.vwRefreshUser(value25);
                    break;
                }
                break;
            }
            case 302: {
                this.chatSession.rankData.setBcasters(pvalue);
                break;
            }
            case 304: {
                if (servicePack.paramCount > 0) {
                    s = servicePack.getValue((short)20004);
                    s3 = servicePack.getValue((short)20002);
                }
                if (!StringUtil.isEmpty(s) && !StringUtil.isEmpty(s3)) {
                    this.chatView.vwWarnOncePrivate(s, s3, null);
                    break;
                }
                break;
            }
            case 312: {
                if (!StringUtil.isEmpty(pvalue)) {
                    this.chatView.vwCkBanned(pvalue);
                    break;
                }
                break;
            }
            case 314: {
                if (!StringUtil.isEmpty(pvalue)) {
                    this.chatView.vwCkGagged(pvalue);
                    break;
                }
                break;
            }
            case 316: {
                this.paraConf.loadPage(this.paraConf.get("Adm.Hp.AdmPage", "http://www.parachat.com/help/"));
                break;
            }
            case 318: {
                this.paraConf.loadPage(this.paraConf.get("Adm.Hp.DocPage", "http://www.parachat.com/help/"));
                break;
            }
            default: {
                System.err.println("Err333893." + servicePack.command);
                break;
            }
        }
    }
    
    private void addUser(final String s) {
        if (!StringUtil.isEmpty(s)) {
            final UserAttr userAttr = new UserAttr();
            if (!this.chatSession.userList.containsKey(s)) {
                this.chatSession.userList.put(s, userAttr);
                this.chatView.vwAddUser(s);
            }
        }
        this.chatSession.paraConf.printer().print("ulist " + this.chatSession.userList);
    }
    
    private void userJoined(final String s) {
        this.addUser(s);
        this.chatView.vwNewJoin(s);
    }
    
    private void userLeft(final String s, final String s2) {
        this.chatView.vwNewExit(s, s2);
        if (!StringUtil.isEmpty(s)) {
            this.chatSession.userList.remove(s);
        }
    }
}
