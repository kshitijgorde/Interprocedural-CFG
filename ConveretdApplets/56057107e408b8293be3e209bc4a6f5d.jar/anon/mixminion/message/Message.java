// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.mixminion.FirstMMRConnection;
import anon.mixminion.Mixminion;
import anon.mixminion.mmrdescription.MMRDescription;
import logging.LogHolder;
import logging.LogType;
import anon.mixminion.mmrdescription.PlainMMRListFetcher;
import anon.mixminion.mmrdescription.MMRListFetcher;
import anon.mixminion.mmrdescription.MMRList;
import anon.mixminion.mmrdescription.InfoServiceMMRListFetcher;
import java.io.IOException;
import java.util.Vector;
import anon.mixminion.EMail;

public class Message
{
    private EMail m_email;
    private int m_hops;
    private String m_address;
    private String m_decoded;
    private String m_keyringpassword;
    private int m_rbs;
    int MAX_FRAGMENTS_PER_CHUNK;
    double EXP_FACTOR;
    
    public Message(final EMail email, final int hops, final String address, final String keyringpassword, final int rbs) {
        this.m_email = null;
        this.m_hops = 0;
        this.m_decoded = null;
        this.MAX_FRAGMENTS_PER_CHUNK = 16;
        this.EXP_FACTOR = 1.3333333333333333;
        this.m_email = email;
        this.m_hops = hops;
        this.m_address = address;
        this.m_keyringpassword = keyringpassword;
        this.m_rbs = rbs;
    }
    
    public boolean send() {
        return this.encodeMessage();
    }
    
    boolean encodeMessage() {
        String decode = null;
        if (this.m_email.getType().equals("ENC")) {
            final Decoder decoder = new Decoder(this.m_email.getPayload(), this.m_keyringpassword);
            final Vector<String> vector = new Vector<String>();
            try {
                decode = decoder.decode();
            }
            catch (IOException ex) {
                System.out.println("Decodier-Exception...");
            }
            vector.addElement(decode);
            this.m_decoded = vector.elementAt(0);
            return false;
        }
        boolean b = true;
        Vector replyBlocks = null;
        if (this.m_email.getType().equals("RPL")) {
            replyBlocks = this.m_email.getReplyBlocks();
        }
        MMRList list = null;
        if (list == null) {
            list = new MMRList(new InfoServiceMMRListFetcher());
            if (!list.updateList()) {
                list = new MMRList(new PlainMMRListFetcher());
                if (!list.updateList()) {
                    return false;
                }
                System.out.println("Groesse: " + list.size());
            }
        }
        for (int i = 0; i < this.m_rbs; ++i) {
            final ReplyBlock replyBlock = new ReplyBlock(this.m_address, list.getByRandomWithExit(this.m_hops), new Keyring(this.m_keyringpassword).getNewSecret());
            replyBlock.buildBlock();
            this.m_email.addRBtoPayload(replyBlock.getReplyBlockasString());
        }
        final byte[] compressData = MixMinionCryptoUtil.compressData(this.m_email.getPayload().getBytes());
        LogHolder.log(7, LogType.MISC, "[Message] Compressed Size = " + compressData.length);
        MessageImplementation messageImplementation;
        if (compressData.length + 22 <= 28672) {
            messageImplementation = new SingleBlockMessage(compressData);
        }
        else {
            System.out.println("fragmente!");
            messageImplementation = new FragmentedMessage(this.m_email.getReceiver(), this.m_email.getPayload().getBytes());
        }
        final byte[][] buildPayload = messageImplementation.buildPayload();
        if (buildPayload.length == 0) {
            LogHolder.log(3, LogType.MISC, "[Message] Compression failure--> 0 packets ");
            return false;
        }
        ReplyImplementation replyImplementation;
        if (this.m_email.getType().equals("RPL")) {
            replyImplementation = new ReplyMessage(buildPayload, this.m_hops, replyBlocks, list);
        }
        else {
            replyImplementation = new NoReplyMessage(buildPayload, this.m_hops, this.m_email.getReceiver(), list);
        }
        final Vector buildMessage = replyImplementation.buildMessage();
        final Vector startServers = replyImplementation.getStartServers();
        for (int j = 0; j < buildMessage.size(); ++j) {
            b = (b && this.sendToMixMinionServer(buildMessage.elementAt(j), startServers.elementAt(j)));
        }
        return b;
    }
    
    private boolean sendToMixMinionServer(final byte[] array, final MMRDescription mmrDescription) {
        boolean sendMessage = false;
        try {
            final FirstMMRConnection firstMMRConnection = new FirstMMRConnection(mmrDescription, Mixminion.getInstance());
            System.out.println("   connecting...");
            firstMMRConnection.connect();
            System.out.println("   sending...");
            sendMessage = firstMMRConnection.sendMessage(array);
            System.out.println("   Value of SendingMethod = " + sendMessage);
            System.out.println("   close connection");
            firstMMRConnection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sendMessage;
    }
    
    private int ceilDiv(final double n, final double n2) {
        return (int)Math.ceil(n / n2);
    }
    
    public String getDecoded() {
        return this.m_decoded;
    }
}
