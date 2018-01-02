// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.util.Enumeration;
import java.net.MalformedURLException;

public class UpdateRecord extends Record
{
    public long seq;
    private boolean full;
    private short updateType;
    
    public UpdateRecord() {
        this.full = false;
        this.updateType = -18;
        super.classId = 1004;
    }
    
    public UpdateRecord(final String s) throws MalformedURLException {
        super(s);
        this.full = false;
        this.updateType = -18;
        super.classId = 1004;
    }
    
    UpdateRecord(final short n, final byte[] array, final int n2, final int n3) {
        this.full = false;
        this.updateType = -18;
        if (n == -16) {
            this.full = true;
        }
        super.classId = 1004;
        this.parseBytes(array, n2, n3);
    }
    
    public void setFid(final Short f, final String v, final int o, final long seq, final InsertCallback c, final long time) {
        super.Fids.addElement(new CallBack(f, v, o, seq, c, time));
    }
    
    public void error() {
        final Enumeration<CallBack> elements = super.Fids.elements();
        while (elements.hasMoreElements()) {
            final CallBack callBack = elements.nextElement();
            final InsertCallback insert = callBack.insert;
            if (insert != null) {
                insert.insertStatus(callBack.fid, (short)3, callBack.sequence, "Insert Request Failed");
            }
        }
    }
    
    public boolean isFull() {
        return this.full;
    }
    
    public int formatRequest(final byte[] array, final int n) {
        final int setEnvelope = MessageFormat.setEnvelope(this.updateType, (byte)11, (short)0, (short)0, array, n);
        final byte[] bytes = super.service.getBytes();
        final int packAny = MessageFormat.packAny((short)(-21), (byte)9, (short)bytes.length, (short)0, (short)0, bytes, array, setEnvelope);
        final byte[] bytes2 = super.key.getBytes();
        final int packLong;
        final int setEnvelope2;
        int packAny2 = setEnvelope2 = MessageFormat.setEnvelope((short)(-75), (byte)11, (short)0, (short)0, array, packLong = MessageFormat.packLong((short)(-74), (short)0, this.seq, array, MessageFormat.packAny((short)(-22), (byte)9, (short)bytes2.length, (short)0, (short)0, bytes2, array, packAny)));
        final Enumeration<CallBack> elements = super.Fids.elements();
        while (elements.hasMoreElements()) {
            final CallBack callBack = elements.nextElement();
            packAny2 = MessageFormat.packAny(callBack.fid, (byte)((callBack.offset != 0) ? (0x9 | MessageFormat.partialBit) : 9), (short)callBack.value.length(), (short)callBack.offset, (short)0, callBack.value.getBytes(), array, packAny2);
        }
        MessageFormat.setEnvelope((short)(-75), (byte)11, (short)(packAny2 - setEnvelope2), (short)0, array, packLong);
        MessageFormat.setEnvelope(this.updateType, (byte)11, (short)(packAny2 - setEnvelope), (short)0, array, n);
        return packAny2 - n;
    }
    
    private void parseBytes(final byte[] array, final int n, final int n2) {
        int i = n;
        while (i < i + n2) {
            final short shortToNative = MessageFormat.shortToNative(array, i);
            i += 2;
            final byte b = array[i++];
            if (MessageFormat.isaArray(b)) {
                MessageFormat.shortToNative(array, i);
                i += 2;
            }
            int shortToNative2 = 0;
            switch (MessageFormat.getTypeOnly(b)) {
                case 1:
                case 2: {
                    shortToNative2 = 2;
                    break;
                }
                case 3:
                case 4: {
                    shortToNative2 = 4;
                    break;
                }
                case 5: {
                    shortToNative2 = 8;
                    break;
                }
                case 6: {
                    shortToNative2 = 4;
                    break;
                }
                case 7: {
                    shortToNative2 = 4;
                    break;
                }
                default: {
                    shortToNative2 = MessageFormat.shortToNative(array, i);
                    i += 2;
                    break;
                }
            }
            int j = i;
            i += shortToNative2;
            switch (shortToNative) {
                default: {
                    continue;
                }
                case -21: {
                    super.service = new String(array, j, shortToNative2 - 1);
                    continue;
                }
                case -22: {
                    super.key = new String(array, j, shortToNative2 - 1);
                    continue;
                }
                case -24: {
                    while (j < j + shortToNative2) {
                        final short shortToNative3 = MessageFormat.shortToNative(array, j);
                        j += 2;
                        final byte b2 = array[j++];
                        if (MessageFormat.isaArray(b2)) {
                            MessageFormat.shortToNative(array, j);
                            j += 2;
                        }
                        if (MessageFormat.getTypeOnly(b2) != 9) {
                            return;
                        }
                        short shortToNative4 = 0;
                        boolean b3 = false;
                        boolean b4 = false;
                        int shortToNative5 = MessageFormat.shortToNative(array, j);
                        j += 2;
                        if (MessageFormat.isPartial(b2)) {
                            shortToNative4 = MessageFormat.shortToNative(array, j);
                            j += 2;
                            shortToNative5 -= 2;
                            b3 = true;
                        }
                        if (MessageFormat.isMissed(b2)) {
                            b4 = true;
                        }
                        final String s = new String(array, j, shortToNative5);
                        j += shortToNative5;
                        super.Fids.addElement(new FidValue(new Short(shortToNative3), shortToNative4, s, b3, b4));
                    }
                    continue;
                }
            }
        }
    }
    
    public void setPage() {
        this.updateType = -19;
    }
}
