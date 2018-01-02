import java.util.LinkedList;

// 
// Decompiled by Procyon v0.5.30
// 

public class IAXFullFrame extends IAXFrame
{
    int retransmit;
    short dcallno;
    byte oseqno;
    byte iseqno;
    FRAMETYPES frametype;
    byte subclass;
    LinkedList<IAXIE> iaxies;
    String text;
    String host;
    IAXCall icall;
    
    public IAXFullFrame(final byte[] frame, final String h) {
        this.retransmit = 0;
        this.host = h;
        this.full = true;
        this.scallno = (short)((frame[0] & 0x7F) << 8 | (frame[1] & 0xFF));
        if ((frame[2] & 0x80) == 0x1) {
            this.retransmit = 1;
        }
        else {
            this.retransmit = 0;
        }
        this.dcallno = (short)((frame[2] & 0x7F) << 8 | (frame[3] & 0xFF));
        this.timestamp = (frame[4] << 24) + (frame[5] << 16) + (frame[6] << 8) + frame[7];
        this.oseqno = frame[8];
        this.iseqno = frame[9];
        this.frametype = FRAMETYPES.values()[frame[10]];
        this.subclass = frame[11];
        if (frame.length > 12) {
            if (this.frametype == FRAMETYPES.IAX) {
                final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
                byte len;
                for (int i = 12; i < frame.length && frame[i] != 0; i += 2 + len) {
                    len = frame[i + 1];
                    final byte[] tmp = new byte[len];
                    System.arraycopy(frame, i + 2, tmp, 0, tmp.length);
                    ieList.add(new IAXIE(frame[i], tmp));
                }
                this.iaxies = ieList;
            }
            else if (this.frametype == FRAMETYPES.VOICE || this.frametype == FRAMETYPES.VIDEO) {
                System.arraycopy(frame, 12, this.streamData = new byte[frame.length - 12], 0, this.streamData.length);
            }
            else if (this.frametype == FRAMETYPES.TEXT) {
                final byte[] tmp2 = new byte[frame.length - 12];
                System.arraycopy(frame, 12, tmp2, 0, tmp2.length);
                this.text = new String(tmp2);
            }
        }
    }
    
    public IAXFullFrame(final IAXCall ic, final FRAMETYPES ftype, final int sclass) {
        this.retransmit = 0;
        this.full = true;
        this.retransmit = 0;
        this.scallno = ic.scallno;
        this.dcallno = ic.dcallno;
        this.oseqno = ic.oseqno;
        this.iseqno = ic.iseqno;
        this.frametype = ftype;
        this.subclass = (byte)sclass;
        this.icall = ic;
        this.setSeqnos();
    }
    
    public IAXFullFrame(final IAXCall ic, final FRAMETYPES ftype, final int sclass, final LinkedList<IAXIE> ies) {
        this(ic, ftype, sclass);
        this.iaxies = ies;
    }
    
    public IAXFullFrame(final IAXCall ic, final FRAMETYPES ftype, final int sclass, final byte[] media) {
        this(ic, ftype, sclass);
        System.arraycopy(media, 0, this.streamData = new byte[media.length], 0, this.streamData.length);
    }
    
    public IAXFullFrame(final IAXCall ic, final FRAMETYPES ftype, final int sclass, final String s) {
        this(ic, ftype, sclass);
        this.text = s;
    }
    
    public void setSeqnos() {
        if (this.ackFrame()) {
            this.oseqno = this.icall.oseqno;
        }
        else {
            final IAXCall icall = this.icall;
            final byte oseqno = icall.oseqno;
            icall.oseqno = (byte)(oseqno + 1);
            this.oseqno = oseqno;
        }
        this.iseqno = this.icall.iseqno;
    }
    
    public void print(final int rx) {
        if (Starphone.iaxDebug == 0) {
            return;
        }
        String sclass = "";
        if (this.frametype == FRAMETYPES.CONTROL) {
            if (this.subclass != -1) {
                sclass += CTRLSUBCLASS.values()[this.subclass];
            }
        }
        else if (this.frametype == FRAMETYPES.IAX) {
            sclass += IAXSUBCLASS.values()[this.subclass];
        }
        else if (this.frametype == FRAMETYPES.DTMF) {
            sclass += (char)this.subclass;
        }
        else {
            sclass += this.subclass;
        }
        if (rx == 1) {
            Starphone.log("Rx:\tOSeqno: " + (this.oseqno & 0xFF) + "\tISeqno: " + (this.iseqno & 0xFF) + "\tType: " + this.frametype + "\tSubclass: " + sclass);
        }
        else {
            Starphone.log("Tx:\tOSeqno: " + (this.oseqno & 0xFF) + "\tISeqno: " + (this.iseqno & 0xFF) + "\tType: " + this.frametype + "\tSubclass: " + sclass);
        }
        Starphone.log("\tTimestamp: " + this.timestamp + "\tSCallno: " + this.scallno + "\tDCallno: " + this.dcallno);
        if (this.iaxies != null) {
            for (int i = 0; i < this.iaxies.size(); ++i) {
                this.iaxies.get(i).print();
            }
        }
    }
    
    public byte[] asByteArray() {
        final byte[] tmp = new byte[256];
        short totalen = 0;
        totalen = 12;
        if (this.full) {
            tmp[0] = (byte)(0x80 | (this.scallno & 0x7F00) >> 8);
        }
        else {
            tmp[0] = (byte)((this.scallno & 0x7F00) >> 8);
        }
        tmp[1] = (byte)this.scallno;
        if (this.retransmit > 0) {
            tmp[2] = (byte)(0x80 | (this.dcallno & 0x7F00) >> 8);
        }
        else {
            tmp[2] = (byte)((this.dcallno & 0x7F00) >> 8);
        }
        tmp[3] = (byte)this.dcallno;
        tmp[4] = (byte)(this.timestamp >> 24);
        tmp[5] = (byte)(this.timestamp >> 16);
        tmp[6] = (byte)(this.timestamp >> 8);
        tmp[7] = (byte)this.timestamp;
        tmp[8] = this.oseqno;
        tmp[9] = this.iseqno;
        tmp[10] = (byte)this.frametype.ordinal();
        tmp[11] = this.subclass;
        if (this.iaxies != null) {
            int i = 0;
            int j = 12;
            while (i < this.iaxies.size()) {
                final byte[] tmp2 = this.iaxies.get(i).asByteArray();
                for (int k = 0; k < tmp2.length; ++k) {
                    tmp[j++] = tmp2[k];
                }
                totalen += (byte)tmp2.length;
                ++i;
            }
        }
        else if (this.frametype == FRAMETYPES.VOICE) {
            for (int i = 0; i < this.streamData.length; ++i) {
                tmp[i + 12] = this.streamData[i];
            }
            totalen += 33;
        }
        else if (this.text != null) {
            System.out.println("text = " + this.text);
            final byte[] b = this.text.getBytes();
            for (int l = 0; l < b.length; ++l) {
                System.out.println("b[" + l + "] = " + b[l]);
                tmp[l + 12] = b[l];
            }
            totalen += (short)b.length;
        }
        final byte[] msg = new byte[totalen];
        for (int m = 0; m < totalen; ++m) {
            msg[m] = tmp[m];
        }
        return msg;
    }
    
    public boolean ackFrame() {
        return this.frametype == FRAMETYPES.IAX && (this.subclass == IAXSUBCLASS.ACK.ordinal() || this.subclass == IAXSUBCLASS.INVAL.ordinal() || this.subclass == IAXSUBCLASS.TXCNT.ordinal() || this.subclass == IAXSUBCLASS.TXACC.ordinal() || this.subclass == IAXSUBCLASS.VNAK.ordinal());
    }
}
