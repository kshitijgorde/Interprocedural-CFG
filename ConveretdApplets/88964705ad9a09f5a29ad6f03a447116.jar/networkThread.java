import java.net.SocketException;
import java.net.NoRouteToHostException;
import java.net.SocketAddress;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

public class networkThread implements Runnable
{
    private final int MAXLEN = 1500;
    private final int MAXCALLS = 20;
    private byte[] recvbuf;
    private ByteBuffer recvByteBuffer;
    private int port;
    private static DatagramChannel dc;
    private Selector sel;
    private InetSocketAddress sa;
    private static byte[] msg;
    private static int refresh;
    private static int debug;
    private static final int QUEUESTARTSIZE = 20;
    public static List<qent> txq;
    private static Vector<IAXCall> callarray;
    private static IAXEventListener iel;
    
    public networkThread(final IAXEventListener iaxel) {
        this.recvbuf = new byte[1500];
        this.recvByteBuffer = ByteBuffer.wrap(this.recvbuf);
        setIAXEventListener(iaxel);
        networkThread.callarray = IAXCall.getCallarray();
        networkThread.txq = Collections.synchronizedList(new ArrayList<qent>(20));
        try {
            if (networkThread.debug > 1) {
                Starphone.log("called networkThread constructor");
            }
            (networkThread.dc = DatagramChannel.open()).configureBlocking(false);
            this.sel = Selector.open();
            networkThread.dc.register(this.sel, 1);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void run() {
        if (networkThread.debug > 1) {
            Starphone.log("called networkThread's run()");
        }
        while (true) {
            for (int i = 0; i < networkThread.txq.size(); ++i) {
                final qent q = networkThread.txq.get(i);
                if (q.txtime == 0L) {
                    send(q);
                }
                final long now = System.currentTimeMillis();
                if (q.acked && q.txtime != 0L) {
                    networkThread.txq.remove(i--);
                }
                else if (now - q.txtime > 5000L && !q.acked) {
                    Starphone.log("Retransmitting call " + ((IAXFullFrame)q.ifr).scallno + ", seqno " + q.seqno + ":" + ((IAXFullFrame)q.ifr).frametype + "/" + ((IAXFullFrame)q.ifr).subclass);
                    if (((IAXFullFrame)q.ifr).retransmit > 4) {
                        Starphone.log("Hanging up call " + ((IAXFullFrame)q.ifr).scallno + " due to excessive retransmits.");
                        networkThread.iel.hangup(((IAXFullFrame)q.ifr).scallno);
                        ((IAXFullFrame)q.ifr).icall.hangup();
                    }
                    final IAXFullFrame iaxFullFrame = (IAXFullFrame)q.ifr;
                    ++iaxFullFrame.retransmit;
                    send(q);
                }
            }
            try {
                this.sa = (InetSocketAddress)networkThread.dc.receive(this.recvByteBuffer);
                if (this.sa == null) {
                    Thread.sleep(1L);
                    continue;
                }
                if (networkThread.debug > 1) {
                    Starphone.log("Received a datagram from " + this.sa + ":");
                }
                final byte[] rbuf = new byte[this.recvByteBuffer.position()];
                if (networkThread.debug > 1) {
                    this.extensiveDebug();
                }
                this.recvByteBuffer.flip();
                this.recvByteBuffer.get(rbuf);
                if (rbuf[0] < 0) {
                    this.handleFullFrame(new IAXFullFrame(rbuf, this.sa.getAddress().getHostAddress()));
                }
                else {
                    this.handleMiniFrame(new IAXMiniFrame(rbuf, this.sa.getAddress().getHostAddress()));
                }
                this.recvByteBuffer.clear();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            final Iterator it = this.sel.selectedKeys().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
    }
    
    public static void qadd(final qent q) {
        networkThread.txq.add(q);
    }
    
    public void extensiveDebug() {
        Starphone.log("recvByteBuffer.limit = " + this.recvByteBuffer.limit());
        Starphone.log("recvByteBuffer.capacity = " + this.recvByteBuffer.capacity());
        Starphone.log("recvByteBuffer.position = " + this.recvByteBuffer.position());
        Starphone.log("recvByteBuffer.remaining = " + this.recvByteBuffer.remaining());
        Starphone.log("recvByteBuffer.arrayOffset = " + this.recvByteBuffer.arrayOffset());
    }
    
    public void handleMiniFrame(final IAXFrame ifr) {
        int callidx = -1;
        callidx = getCallIndex(ifr.scallno, ((IAXMiniFrame)ifr).host, 1);
        networkThread.callarray.get(callidx).playAudio(ifr);
        final IAXMiniFrame ofr = (IAXMiniFrame)networkThread.callarray.get(callidx).sendAudio(0);
        if (ofr != null) {
            sendFrame(ofr, callidx);
        }
    }
    
    public void handleFullFrame(final IAXFullFrame ifr) {
        ifr.print(1);
        int callidx = -1;
        if (ifr.dcallno == 0) {
            callidx = getCallIndex(ifr.scallno, ifr.host, 1);
        }
        else {
            callidx = getCallIndex(ifr.dcallno, 0);
        }
        if (callidx == -1) {
            IAXCall.addCall(new IAXCall(ifr.host, ifr.scallno));
            callidx = getCallIndex(ifr.scallno, ifr.host, 1);
            if (networkThread.debug > 0) {
                Starphone.log("Generating a new call from handleFullFrame");
                Starphone.log("callidx = " + callidx);
                Starphone.log("scallno = " + networkThread.callarray.get(callidx).scallno);
                Starphone.log("dcallno = " + networkThread.callarray.get(callidx).dcallno);
            }
        }
        if (networkThread.callarray.get(callidx).dcallno == 0) {
            networkThread.callarray.get(callidx).dcallno = ifr.scallno;
        }
        if (ifr.full && !ifr.ackFrame()) {
            if (ifr.oseqno > networkThread.callarray.get(callidx).iseqno) {
                final IAXFullFrame iaxFullFrame2;
                final IAXFullFrame iaxFullFrame;
                final IAXFullFrame ofr = iaxFullFrame = (iaxFullFrame2 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.VNAK.ordinal()));
                final byte iseqno = networkThread.callarray.get(callidx).iseqno;
                iaxFullFrame.iseqno = iseqno;
                iaxFullFrame2.oseqno = iseqno;
                sendFrame(ofr, callidx);
            }
            else if (ifr.oseqno == networkThread.callarray.get(callidx).iseqno) {
                final IAXCall iaxCall = networkThread.callarray.get(callidx);
                ++iaxCall.iseqno;
            }
        }
        Label_3464: {
            switch (ifr.frametype) {
                case DTMF: {
                    Starphone.log("Received DTMF " + ifr.subclass);
                    this.sendAck(callidx);
                    final IAXCall iaxCall2 = networkThread.callarray.get(callidx);
                    IAXCall.playDTMF((char)ifr.subclass);
                    break;
                }
                case VOICE: {
                    this.sendAck(callidx);
                    if (ifr.subclass == 2) {
                        networkThread.callarray.get(callidx).playAudio(ifr);
                        final IAXFullFrame ofr = (IAXFullFrame)networkThread.callarray.get(callidx).sendAudio(1);
                        if (ofr != null) {
                            sendFrame(ofr, callidx);
                        }
                        break;
                    }
                    Starphone.log("ERROR: non-gsm frame received");
                    Starphone.log("Audio codec received was " + ifr.subclass);
                    break;
                }
                case CONTROL: {
                    if (ifr.subclass == -1) {
                        this.sendAck(callidx);
                        break;
                    }
                    switch (IAXFrame.CTRLSUBCLASS.values()[ifr.subclass]) {
                        case HANGUP: {
                            Starphone.log("received HANGUP control frame for call number " + callidx);
                            networkThread.iel.hangup(callidx);
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case RESERVED1:
                        case RESERVED2:
                        case RESERVED3:
                        case RESERVED4: {
                            break Label_3464;
                        }
                        case RINGING: {
                            break Label_3464;
                        }
                        case ANSWER: {
                            networkThread.callarray.get(callidx).setCallState(IAXCall.STATE.UP);
                            this.sendAck(callidx);
                            if (!networkThread.callarray.get(callidx).metacall) {
                                networkThread.iel.answer(callidx);
                                networkThread.iel.mute(callidx, getMute(callidx));
                                break Label_3464;
                            }
                            break Label_3464;
                        }
                        case BUSY: {
                            Starphone.log("BUSY ctrl frame received");
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case CONGESTION: {
                            Starphone.log("CONGESTION ctrl frame received");
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case FLASHHOOK: {
                            break Label_3464;
                        }
                        case OPTION: {
                            break Label_3464;
                        }
                        case KEYRADIO: {
                            break Label_3464;
                        }
                        case UNKEYRADIO: {
                            break Label_3464;
                        }
                        case CALLPROGRESS: {
                            break Label_3464;
                        }
                        case CALLPROCEEDING: {
                            break Label_3464;
                        }
                        case HOLD: {
                            break Label_3464;
                        }
                        case UNHOLD: {
                            break Label_3464;
                        }
                        default: {
                            Starphone.log("Illegal ctrl frame " + ifr.subclass + " received.");
                            break Label_3464;
                        }
                    }
                    break;
                }
                case NULLFRAME: {
                    Starphone.log("Uhh... received a NULL frame?");
                    break;
                }
                case IAX: {
                    switch (IAXFrame.IAXSUBCLASS.values()[ifr.subclass]) {
                        case NEW: {
                            String from = null;
                            final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
                            ieList.add(new IAXIE(IAXIE.IE.FORMAT, 2));
                            IAXFullFrame ofr2 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.ACCEPT.ordinal(), ieList);
                            sendFrame(ofr2, callidx);
                            for (int i = 0; i < ifr.iaxies.size(); ++i) {
                                if (ifr.iaxies.get(i).id == IAXIE.IE.CALLINGNAME) {
                                    from = ifr.iaxies.get(i).dataAsString();
                                    break;
                                }
                            }
                            if (jsListener.isCallActive) {
                                sendHangup(callidx, 17, "User Busy");
                                break Label_3464;
                            }
                            System.out.println("********************IAX:NEW:" + networkThread.callarray.get(callidx).scallno);
                            networkThread.callarray.get(callidx).setCallState(IAXCall.STATE.RINGING);
                            jsListener.indicateRinging(from, networkThread.callarray.get(callidx).scallno);
                            ofr2 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.CONTROL, IAXFrame.CTRLSUBCLASS.RINGING.ordinal());
                            sendFrame(ofr2, callidx);
                            break Label_3464;
                        }
                        case PING: {
                            final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.PONG.ordinal());
                            ofr.timestamp = ifr.timestamp;
                            sendFrame(ofr, callidx);
                            break Label_3464;
                        }
                        case PONG: {
                            seqInc(ifr);
                            break Label_3464;
                        }
                        case ACK: {
                            seqInc(ifr);
                            break Label_3464;
                        }
                        case HANGUP: {
                            this.sendAck(callidx);
                            networkThread.iel.hangup(callidx);
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case REJECT: {
                            seqInc(ifr);
                            this.sendAck(callidx);
                            break Label_3464;
                        }
                        case ACCEPT: {
                            seqInc(ifr);
                            this.sendAck(callidx);
                            break Label_3464;
                        }
                        case AUTHREQ: {
                            seqInc(ifr);
                            final String username = "<default>";
                            String challenge = "<no challenge received>";
                            for (int j = 0; j < ifr.iaxies.size(); ++j) {
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CHALLENGE) {
                                    challenge = ifr.iaxies.get(j).dataAsString();
                                }
                            }
                            final LinkedList<IAXIE> ieList2 = new LinkedList<IAXIE>();
                            String md5sum = "";
                            md5sum = genSum(challenge, Starphone.secret);
                            ieList2.add(new IAXIE(IAXIE.IE.MD5RESULT, md5sum));
                            final IAXFullFrame ofr3 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.AUTHREP.ordinal(), ieList2);
                            sendFrame(ofr3, callidx);
                            break Label_3464;
                        }
                        case AUTHREP: {
                            this.sendInval(callidx);
                            break Label_3464;
                        }
                        case INVAL: {
                            Starphone.log("Received INVAL");
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case LAGRQ: {
                            final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.LAGRP.ordinal());
                            ofr.timestamp = ifr.timestamp;
                            sendFrame(ofr, callidx);
                            break Label_3464;
                        }
                        case LAGRP: {
                            seqInc(ifr);
                            break Label_3464;
                        }
                        case REGREQ: {
                            seqInc(ifr);
                            final LinkedList<IAXIE> ieList3 = new LinkedList<IAXIE>();
                            ieList3.add(new IAXIE(IAXIE.IE.CAUSECODE, (byte)69));
                            ieList3.add(new IAXIE(IAXIE.IE.CAUSE, "Registration Refused"));
                            final IAXFullFrame ofr4 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.REGREJ.ordinal(), ieList3);
                            sendFrame(ofr4, callidx);
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case REGAUTH: {
                            seqInc(ifr);
                            String username = "<default>";
                            String challenge = "<no challenge received>";
                            for (int j = 0; j < ifr.iaxies.size(); ++j) {
                                if (ifr.iaxies.get(j).id == IAXIE.IE.USERNAME) {
                                    username = ifr.iaxies.get(j).dataAsString();
                                }
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CHALLENGE) {
                                    challenge = ifr.iaxies.get(j).dataAsString();
                                }
                            }
                            final LinkedList<IAXIE> ieList2 = new LinkedList<IAXIE>();
                            ieList2.add(new IAXIE(IAXIE.IE.USERNAME, username));
                            if (regThread.state == 3) {
                                ieList2.add(new IAXIE(IAXIE.IE.REFRESH, (short)networkThread.refresh));
                            }
                            String md5sum = "";
                            md5sum = genSum(challenge, Starphone.secret);
                            ieList2.add(new IAXIE(IAXIE.IE.MD5RESULT, md5sum));
                            IAXFullFrame ofr3 = null;
                            if (regThread.state == 3) {
                                ofr3 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.REGREQ.ordinal(), ieList2);
                            }
                            else if (regThread.state == 4) {
                                ofr3 = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.REGREL.ordinal(), ieList2);
                            }
                            if (ofr3 != null) {
                                sendFrame(ofr3, callidx);
                                break Label_3464;
                            }
                            break Label_3464;
                        }
                        case REGACK: {
                            seqInc(ifr);
                            if (ifr.iaxies != null) {
                                for (int k = 0; k < ifr.iaxies.size(); ++k) {
                                    if (ifr.iaxies.get(k).id == IAXIE.IE.REFRESH) {
                                        networkThread.refresh = ifr.iaxies.get(k).dataAsShort();
                                    }
                                }
                            }
                            if (regThread.state == 3) {
                                regThread.setState(1, networkThread.refresh);
                                networkThread.iel.register(regThread.getRegUsername());
                            }
                            this.sendAck(callidx);
                            networkThread.callarray.get(callidx).hangup();
                            if (regThread.state == 4 && regThread.exiting) {
                                final schedThread st = new schedThread(500);
                                new Thread(st).start();
                                break Label_3464;
                            }
                            break Label_3464;
                        }
                        case REGREJ: {
                            seqInc(ifr);
                            regThread.setState(2, 0);
                            networkThread.iel.register("Login failed");
                            String cause = "";
                            int causecode = 0;
                            for (int j = 0; j < ifr.iaxies.size(); ++j) {
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CAUSE) {
                                    cause = ifr.iaxies.get(j).dataAsString();
                                }
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CAUSECODE) {
                                    causecode = ifr.iaxies.get(j).dataAsByte();
                                }
                            }
                            if (networkThread.debug > 0) {
                                Starphone.log("REGREJ: cause " + cause + ", causecode " + causecode);
                            }
                            this.sendAck(callidx);
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case REGREL: {
                            regThread.setState(2, 0);
                            String cause = "";
                            int causecode = 0;
                            for (int j = 0; j < ifr.iaxies.size(); ++j) {
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CAUSE) {
                                    cause = ifr.iaxies.get(j).dataAsString();
                                }
                                if (ifr.iaxies.get(j).id == IAXIE.IE.CAUSECODE) {
                                    causecode = ifr.iaxies.get(j).dataAsInt();
                                }
                            }
                            if (networkThread.debug > 0) {
                                Starphone.log("REGREL: cause " + cause + ", causecode " + causecode);
                            }
                            this.sendAck(callidx);
                            networkThread.callarray.get(callidx).hangup();
                            break Label_3464;
                        }
                        case VNAK: {
                            for (int k = 0; k < networkThread.txq.size(); ++k) {
                                final qent q = networkThread.txq.get(k);
                                if (ifr.dcallno == q.ifr.scallno && (ifr.iseqno & 0xFF) <= (q.seqno & 0xFF) && q.seqno != -1) {
                                    q.txtime = 0L;
                                    Starphone.log("Scheduling q.seqno " + q.seqno + " for retransmit due to VNAK");
                                }
                            }
                            final int ifroseqno = ifr.oseqno;
                            final int ifriseqno = ifr.iseqno;
                            final int icoseqno = networkThread.callarray.get(callidx).oseqno;
                            final int iciseqno = networkThread.callarray.get(callidx).iseqno;
                            Starphone.log("Received a VNAK with oseqno " + ifroseqno + ", iseqno " + ifriseqno + ". Call's oseqno is " + icoseqno + ", iseqno is " + iciseqno);
                            ifr.print(1);
                            break Label_3464;
                        }
                        case DPREQ: {
                            this.sendUnsupport(callidx);
                            networkThread.callarray.get(callidx).setCallState(IAXCall.STATE.DOWN);
                            break Label_3464;
                        }
                        case DPREP: {
                            seqInc(ifr);
                            this.sendInval(callidx);
                            networkThread.callarray.get(callidx).setCallState(IAXCall.STATE.DOWN);
                            break Label_3464;
                        }
                        case DIAL: {
                            seqInc(ifr);
                            this.sendUnsupport(callidx);
                            networkThread.callarray.get(callidx).setCallState(IAXCall.STATE.DOWN);
                            break Label_3464;
                        }
                        case TXREQ: {
                            if (ifr.iaxies != null) {
                                for (int k = 0; k < ifr.iaxies.size(); ++k) {
                                    if (ifr.iaxies.get(k).id == IAXIE.IE.APPARENTADDR) {
                                        networkThread.callarray.get(callidx).setTxAddress(ifr.iaxies.get(k).dataAsSockaddrIP());
                                        networkThread.callarray.get(callidx).setTxPort(ifr.iaxies.get(k).dataAsSockaddrPort());
                                    }
                                    else if (ifr.iaxies.get(k).id == IAXIE.IE.CALLNO) {
                                        networkThread.callarray.get(callidx).txCallno = ifr.iaxies.get(k).dataAsShort();
                                    }
                                    else if (ifr.iaxies.get(k).id == IAXIE.IE.TRANSFERID) {
                                        networkThread.callarray.get(callidx).txID = ifr.iaxies.get(k).dataAsInt();
                                    }
                                }
                            }
                            this.sendTxcnt(callidx);
                            break Label_3464;
                        }
                        case TXCNT: {
                            System.out.println("Got a TXCNT...");
                            seqInc(ifr);
                            if (ifr.iaxies != null) {
                                for (int k = 0; k < ifr.iaxies.size(); ++k) {
                                    if (ifr.iaxies.get(k).id == IAXIE.IE.TRANSFERID && networkThread.callarray.get(callidx).txID == ifr.iaxies.get(k).dataAsInt()) {
                                        this.sendTxacc(callidx);
                                    }
                                }
                                break Label_3464;
                            }
                            break Label_3464;
                        }
                        case TXACC: {
                            System.out.println("Got a TXACC...");
                            seqInc(ifr);
                            this.sendTxready(callidx);
                            break Label_3464;
                        }
                        case TXREADY: {
                            System.out.println("Got a TXREADY...");
                            seqInc(ifr);
                            this.sendAck(callidx);
                            break Label_3464;
                        }
                        case TXREL: {
                            System.out.println("Got a TXREL...");
                            seqInc(ifr);
                            this.sendAck(callidx);
                            networkThread.callarray.get(callidx).setPeerAddress(networkThread.callarray.get(callidx).txAddress);
                            networkThread.callarray.get(callidx).oseqno = 0;
                            networkThread.callarray.get(callidx).iseqno = 0;
                            networkThread.callarray.get(callidx).dcallno = (short)networkThread.callarray.get(callidx).txCallno;
                            break Label_3464;
                        }
                        case TXREJ: {
                            seqInc(ifr);
                            break Label_3464;
                        }
                        case QUELCH: {
                            Starphone.log("Received a QUELCH, handler not written yet.");
                            break Label_3464;
                        }
                        case UNQUELCH: {
                            Starphone.log("Received an UNQUELCH, handler not written yet.");
                            break Label_3464;
                        }
                        case POKE: {
                            final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.PONG.ordinal());
                            ofr.timestamp = ifr.timestamp;
                            sendFrame(ofr, callidx);
                            break Label_3464;
                        }
                        case PAGE: {
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        case MWI: {
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        case UNSUPPORT: {
                            break Label_3464;
                        }
                        case TRANSFER: {
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        case PROVISION: {
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        case FWDOWNL: {
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        case FWDATA: {
                            seqInc(ifr);
                            this.sendUnsupport(callidx);
                            break Label_3464;
                        }
                        default: {
                            Starphone.log("frametype " + ifr.subclass + " not handled... You were caught by final else");
                            break Label_3464;
                        }
                    }
                    break;
                }
                case TEXT: {
                    networkThread.iel.text(ifr.text);
                    break;
                }
                case IMAGE: {
                    Starphone.log("Received an IMAGE frame");
                    break;
                }
                case HTML: {
                    Starphone.log("Received an HTML frame");
                    break;
                }
                case CNG: {
                    Starphone.log("Received a CNG frame");
                    break;
                }
            }
        }
    }
    
    public static void sendHangup(final int callidx, final int causecode, final String cause) {
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.CAUSE, cause));
        ieList.add(new IAXIE(IAXIE.IE.CAUSECODE, (byte)causecode));
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.HANGUP.ordinal(), ieList);
        sendFrame(ofr, callidx);
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        networkThread.iel.hangup(callidx);
        networkThread.callarray.get(callidx).hangup();
    }
    
    public void sendAnswer(final int callidx) {
        networkThread.iel.answer(callidx);
        networkThread.iel.mute(callidx, getMute(callidx));
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.CONTROL, IAXFrame.CTRLSUBCLASS.ANSWER.ordinal());
        sendFrame(ofr, callidx);
    }
    
    public void sendAck(final int callidx) {
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.ACK.ordinal());
        sendFrame(ofr, callidx);
    }
    
    public void sendInval(final int callidx) {
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.INVAL.ordinal());
        sendFrame(ofr, callidx);
        networkThread.callarray.get(callidx).hangup();
    }
    
    public void sendQuelch(final int callidx) {
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.QUELCH.ordinal());
        sendFrame(ofr, callidx);
    }
    
    public void sendUnquelch(final int callidx) {
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.UNQUELCH.ordinal());
        sendFrame(ofr, callidx);
    }
    
    public void sendTxready(final int callidx) {
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.CALLNO, networkThread.callarray.get(callidx).scallno));
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.TXREADY.ordinal(), ieList);
        sendFrame(ofr, callidx);
    }
    
    public void sendTxacc(final int callidx) {
        System.out.println("in sendTxacc(callidx), sending to address " + networkThread.callarray.get(callidx).txAddress);
        LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList = null;
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.TXACC.ordinal(), ieList);
        ofr.dcallno = (short)networkThread.callarray.get(callidx).txCallno;
        sendFrame(ofr, callidx, networkThread.callarray.get(callidx).txAddress, networkThread.callarray.get(callidx).txPort);
    }
    
    public void sendTxcnt(final int callidx) {
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.TRANSFERID, networkThread.callarray.get(callidx).txID));
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.TXCNT.ordinal(), ieList);
        ofr.dcallno = (short)networkThread.callarray.get(callidx).txCallno;
        sendFrame(ofr, callidx, networkThread.callarray.get(callidx).txAddress, networkThread.callarray.get(callidx).txPort);
    }
    
    public void sendTxrej(final int callidx) {
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.CAUSE, "Transfer Refused"));
        ieList.add(new IAXIE(IAXIE.IE.CAUSECODE, (byte)69));
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.TXREJ.ordinal(), ieList);
        sendFrame(ofr, callidx);
    }
    
    public void sendUnsupport(final int callidx) {
        final IAXFullFrame ofr = new IAXFullFrame(networkThread.callarray.get(callidx), IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.UNSUPPORT.ordinal());
        sendFrame(ofr, callidx);
    }
    
    public static void sendFrame(final IAXFrame ifr, final int callidx, final String host, final int port) {
        if (ifr.full) {
            ifr.print(0);
        }
        qadd(new qent(host, port, ifr));
    }
    
    public static void sendFrame(final IAXFrame ifr, final int callidx) {
        if (ifr.full) {
            ifr.print(0);
        }
        qadd(new qent(networkThread.callarray.get(callidx), ifr));
    }
    
    public static int getCallIndex(final short callno, final String h, final int src) {
        if (networkThread.debug > 1) {
            Starphone.log("Looking for callno " + callno + " with host = " + h + " in getCallIndex()");
            Starphone.log("Found these calls in callarray:");
            Starphone.log("callarray.size() = " + networkThread.callarray.size());
        }
        for (int i = 0; i < networkThread.callarray.size(); ++i) {
            if (networkThread.debug > 1) {
                System.out.print("i = " + i);
                System.out.print(", scallno = " + networkThread.callarray.get(i).scallno);
                System.out.print(", dcallno = " + networkThread.callarray.get(i).dcallno);
                Starphone.log(", host = " + networkThread.callarray.get(i).peerAddress);
            }
            if (src == 0) {
                if (networkThread.callarray.get(i).scallno == callno && networkThread.callarray.get(i).peerAddress.equals(h)) {
                    return i;
                }
            }
            else if (src == 1 && networkThread.callarray.get(i).dcallno == callno && networkThread.callarray.get(i).peerAddress.equals(h)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getCallIndex(final short callno, final int src) {
        if (networkThread.debug > 1) {
            Starphone.log("Looking for callno " + callno + " in getCallIndex()");
            Starphone.log("Found these calls in callarray:");
            Starphone.log("callarray.size() = " + networkThread.callarray.size());
        }
        for (int i = 0; i < networkThread.callarray.size(); ++i) {
            if (networkThread.debug > 1) {
                System.out.print("i = " + i);
                System.out.print(", scallno = " + networkThread.callarray.get(i).scallno);
                Starphone.log(", dcallno = " + networkThread.callarray.get(i).dcallno);
            }
            if (src == 0) {
                if (networkThread.callarray.get(i).scallno == callno) {
                    return i;
                }
            }
            else if (src == 1 && networkThread.callarray.get(i).dcallno == callno) {
                return i;
            }
        }
        return -1;
    }
    
    public static String genSum(final String challenge, final String secret) {
        String s = "";
        byte[] ret = new byte[256];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = 0;
        }
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            md.update(challenge.getBytes());
            md.update(secret.getBytes());
            ret = md.digest();
        }
        catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < ret.length; ++j) {
            s = Integer.toHexString(0xFF & ret[j]);
            if (s.length() == 1) {
                sb.append("0" + s);
            }
            else {
                sb.append(s);
            }
        }
        s = sb.toString();
        return s;
    }
    
    public static IAXCall getCall(final short callno, final int src) {
        if (networkThread.debug > 1) {
            Starphone.log("Looking for callno " + callno + " in getCallIndex()");
            Starphone.log("Found these calls in callarray:");
            Starphone.log("callarray.size() = " + networkThread.callarray.size());
        }
        for (int i = 0; i < networkThread.callarray.size(); ++i) {
            if (networkThread.debug > 1) {
                System.out.print("i = " + i);
                System.out.print(", scallno = " + networkThread.callarray.get(i).scallno);
                Starphone.log(", dcallno = " + networkThread.callarray.get(i).dcallno);
            }
            if (src == 0) {
                if (networkThread.callarray.get(i).scallno == callno) {
                    return networkThread.callarray.get(i);
                }
            }
            else if (src == 1 && networkThread.callarray.get(i).dcallno == callno) {
                return networkThread.callarray.get(i);
            }
        }
        return null;
    }
    
    public void sendDTMF(final String s, final short scno) {
        final int callidx = getCallIndex(scno, 0);
        final IAXFullFrame ofr = networkThread.callarray.get(callidx).sendDTMF(s);
        if (ofr != null) {
            sendFrame(ofr, callidx);
        }
    }
    
    public void sendText(final String s, final short scno) {
        final int callidx = getCallIndex(scno, 0);
        final IAXFullFrame ofr = networkThread.callarray.get(callidx).sendText(s);
        if (ofr != null) {
            sendFrame(ofr, callidx);
        }
    }
    
    public static void seqInc(final IAXFullFrame ifr) {
        for (int i = 0; i < networkThread.txq.size(); ++i) {
            final qent q = networkThread.txq.get(i);
            if ((ifr.dcallno == q.ifr.scallno && (ifr.iseqno & 0xFF) > (q.seqno & 0xFF)) || ((ifr.iseqno & 0xFF) == 0x0 && (q.seqno & 0xFF) == 0xFF)) {
                q.acked = true;
            }
        }
    }
    
    public static void send(final qent q) {
        try {
            networkThread.msg = q.ifr.asByteArray();
            networkThread.dc.send(ByteBuffer.wrap(networkThread.msg), q.sa);
            q.txtime = System.currentTimeMillis();
            if (networkThread.debug > 1) {
                Starphone.log("Sent datagram to " + q.sa);
            }
            if (!q.ifr.full || ((IAXFullFrame)q.ifr).ackFrame()) {
                q.acked = true;
            }
            networkThread.msg = null;
        }
        catch (NoRouteToHostException nrthe) {
            System.err.println(nrthe);
            System.err.println("\nAre you connected to the Internet?\n");
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        catch (SocketException se) {
            se.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static void cancelRetransmits(final IAXCall ic) {
        for (int i = 0; i < networkThread.txq.size(); ++i) {
            if (networkThread.txq.get(i).ifr.scallno == ic.scallno && networkThread.txq.get(i).txtime != 0L) {
                networkThread.txq.remove(i--);
            }
        }
    }
    
    public static void toggleMute(final int callno) {
        networkThread.callarray.get(callno).toggleMute();
    }
    
    public static void setSpeaker(final int callno, final int value) {
        networkThread.callarray.get(callno).setSpeaker(value);
    }
    
    public static void setMic(final int callno, final int value) {
        networkThread.callarray.get(callno).setMic(value);
    }
    
    public static void setMute(final int callno, final boolean muteState) {
        networkThread.callarray.get(callno).setMute(muteState);
        networkThread.iel.mute(callno, muteState);
    }
    
    public static boolean getMute(final int callno) {
        return networkThread.callarray.get(callno).muted;
    }
    
    public static void allCallHangup() {
        if (networkThread.callarray != null) {
            for (int i = 0; i < networkThread.callarray.size(); ++i) {
                if (networkThread.callarray.get(i).getCallState() != IAXCall.STATE.DOWN) {
                    Starphone.log("Hanging up call " + i + " in allCallHangup()");
                    networkThread.iel.hangup(i);
                    sendHangup(i, 16, "Normal clearing");
                }
            }
        }
    }
    
    public static void setIAXEventListener(final IAXEventListener iaxel) {
        networkThread.iel = iaxel;
    }
    
    static {
        networkThread.refresh = 60;
        networkThread.debug = 0;
    }
}
