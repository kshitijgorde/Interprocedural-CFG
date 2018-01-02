// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.util.ByteArrayUtil;
import anon.mixminion.mmrdescription.MMRDescription;
import logging.LogHolder;
import logging.LogType;
import anon.mixminion.mmrdescription.MMRList;
import java.util.Vector;

public class NoReplyMessage extends ReplyImplementation
{
    private byte[][] m_message_parts;
    private int m_hops;
    private String[] m_recipient;
    private Vector m_start_server;
    private MMRList m_mmrlist;
    
    public NoReplyMessage(final byte[][] message_parts, final int hops, final String[] recipient, final MMRList mmrlist) {
        this.m_message_parts = message_parts;
        this.m_hops = hops;
        this.m_recipient = recipient;
        this.m_start_server = new Vector();
        this.m_mmrlist = mmrlist;
    }
    
    public Vector buildMessage() {
        final Vector<byte[]> vector = new Vector<byte[]>();
        final boolean b = this.m_message_parts.length > 1;
        Vector<Vector> byRandomWithFrag = new Vector<Vector>();
        final int n = this.m_hops / 2;
        final int n2 = this.m_hops - n;
        if (!b) {
            byRandomWithFrag.addElement(this.m_mmrlist.getByRandomWithExit(this.m_hops));
        }
        else {
            byRandomWithFrag = (Vector<Vector>)this.m_mmrlist.getByRandomWithFrag(this.m_hops, this.m_message_parts.length);
        }
        for (int i = 0; i < this.m_message_parts.length; ++i) {
            LogHolder.log(7, LogType.MISC, "[Message] make Header to Fragment_" + i);
            final Vector vector2 = byRandomWithFrag.elementAt(i);
            final Vector vector3 = new Vector();
            final Vector vector4 = new Vector();
            final Vector subVector = MixMinionCryptoUtil.subVector(vector2, 0, n);
            final Vector subVector2 = MixMinionCryptoUtil.subVector(vector2, n, n2);
            this.m_start_server.addElement(subVector.elementAt(0));
            final Vector<byte[]> vector5 = new Vector<byte[]>();
            final Vector<byte[]> vector6 = new Vector<byte[]>();
            for (int j = 0; j < this.m_hops / 2; ++j) {
                vector5.addElement(MixMinionCryptoUtil.randomArray(16));
                vector6.addElement(MixMinionCryptoUtil.randomArray(16));
            }
            if (vector6.size() < n2) {
                vector6.addElement(MixMinionCryptoUtil.randomArray(16));
            }
            ExitInformation exitInformation = new ExitInformation();
            if (b) {
                exitInformation.m_Type = 259;
                exitInformation.m_Content = new byte[0];
            }
            else {
                exitInformation = MMRDescription.getExitInformation(this.m_recipient, null);
            }
            final ExitInformation exitInformation2 = new ExitInformation();
            exitInformation2.m_Type = 4;
            exitInformation2.m_Content = subVector2.elementAt(0).getRoutingInformation().m_Content;
            final Header header = new Header(subVector, vector5, exitInformation2);
            final Header header2 = new Header(subVector2, vector6, exitInformation);
            final byte[] asByteArray = header.getAsByteArray();
            final byte[] asByteArray2 = header2.getAsByteArray();
            byte[] sprp_Encrypt = this.m_message_parts[i];
            for (int k = vector6.size() - 1; k >= 0; --k) {
                sprp_Encrypt = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(vector6.elementAt(k), "PAYLOAD ENCRYPT".getBytes())), sprp_Encrypt);
            }
            byte[] array = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(MixMinionCryptoUtil.hash(sprp_Encrypt), "HIDE HEADER".getBytes())), asByteArray2);
            byte[] array2 = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(MixMinionCryptoUtil.hash(array), "HIDE PAYLOAD".getBytes())), sprp_Encrypt);
            for (int l = vector5.size() - 1; l >= 0; --l) {
                final byte[] array3 = vector5.elementAt(l);
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
