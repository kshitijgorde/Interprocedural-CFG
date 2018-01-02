// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.util.ByteArrayUtil;
import logging.LogHolder;
import logging.LogType;
import anon.mixminion.mmrdescription.MMRDescription;
import java.util.Vector;
import anon.mixminion.mmrdescription.MMRList;

public class ReplyMessage extends ReplyImplementation
{
    private MMRList m_mmrlist;
    private int m_hops;
    private byte[][] m_message_parts;
    private Vector m_replyblocks;
    private Vector m_start_server;
    
    public ReplyMessage(final byte[][] message_parts, final int hops, final Vector replyblocks, final MMRList mmrlist) {
        this.m_mmrlist = mmrlist;
        this.m_hops = hops;
        this.m_message_parts = message_parts;
        this.m_replyblocks = replyblocks;
        this.m_start_server = new Vector();
    }
    
    public Vector buildMessage() {
        final Vector<byte[]> vector = new Vector<byte[]>();
        final boolean b = this.m_message_parts.length > 1;
        if (b) {
            System.out.println("Reply und Fragmente; Decodierung wird noch nicht moeglich sein...");
        }
        Vector<Vector<MMRDescription>> byRandomWithFrag = new Vector<Vector<MMRDescription>>();
        if (!b) {
            byRandomWithFrag.addElement(this.m_mmrlist.getByRandomWithExit(this.m_hops));
        }
        else {
            byRandomWithFrag = (Vector<Vector<MMRDescription>>)this.m_mmrlist.getByRandomWithFrag(this.m_hops, this.m_message_parts.length);
        }
        if (this.m_replyblocks.size() < this.m_message_parts.length) {
            return null;
        }
        for (int i = 0; i < this.m_message_parts.length; ++i) {
            LogHolder.log(7, LogType.MISC, "[Message] make Header to Fragment_" + i);
            final byte[] headerBytes = this.m_replyblocks.elementAt(i).getHeaderBytes();
            final Vector<byte[]> vector2 = new Vector<byte[]>();
            final Vector<MMRDescription> vector3 = byRandomWithFrag.elementAt(i);
            this.m_start_server.addElement(vector3.elementAt(0));
            for (int j = 0; j < this.m_hops; ++j) {
                vector2.addElement(MixMinionCryptoUtil.randomArray(16));
            }
            final ExitInformation exitInformation = new ExitInformation();
            final RoutingInformation routing = this.m_replyblocks.elementAt(i).getRouting();
            exitInformation.m_Type = routing.m_Type;
            exitInformation.m_Content = routing.m_Content;
            final byte[] asByteArray = new Header(vector3, vector2, exitInformation).getAsByteArray();
            final byte[] sprp_Decrypt = MixMinionCryptoUtil.SPRP_Decrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(this.m_replyblocks.elementAt(i).getSharedSecret(), "PAYLOAD ENCRYPT".getBytes())), this.m_message_parts[i]);
            byte[] array = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(MixMinionCryptoUtil.hash(sprp_Decrypt), "HIDE HEADER".getBytes())), headerBytes);
            byte[] array2 = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(MixMinionCryptoUtil.hash(array), "HIDE PAYLOAD".getBytes())), sprp_Decrypt);
            for (int k = vector2.size() - 1; k >= 0; --k) {
                final byte[] array3 = vector2.elementAt(k);
                array = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array3, "HEADER ENCRYPT".getBytes())), array);
                array2 = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array3, "PAYLOAD ENCRYPT".getBytes())), array2);
            }
            final byte[] conc = ByteArrayUtil.conc(asByteArray, array, array2);
            LogHolder.log(7, LogType.MISC, "[Message] the Messagesize = " + conc.length + " Bytes");
            vector.addElement(conc);
        }
        return vector;
    }
    
    public Vector getStartServers() {
        return this.m_start_server;
    }
}
