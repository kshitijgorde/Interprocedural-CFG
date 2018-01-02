// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.io.IOException;
import java.util.Enumeration;
import java.net.MalformedURLException;

public class RecordRequest extends Record
{
    public long seq;
    public String txt;
    public String oldKey;
    public boolean change;
    public short Flags;
    
    public RecordRequest(final String Url) throws MalformedURLException {
        super(Url);
        this.seq = 0L;
        this.txt = null;
        this.oldKey = null;
        this.change = false;
        this.Flags = 0;
    }
    
    public RecordRequest() {
        this.seq = 0L;
        this.txt = null;
        this.oldKey = null;
        this.change = false;
        this.Flags = 0;
    }
    
    RecordRequest(final short id, final byte[] b, final int offset, final int len) {
        this.seq = 0L;
        this.txt = null;
        this.oldKey = null;
        this.change = false;
        this.Flags = 0;
        this.parseBytes(b, offset, len);
    }
    
    public void setFid(final Short Fid, final RequestFidCallback Call) {
        final Enumeration f = super.Fids.elements();
        while (f.hasMoreElements()) {
            final CallBack v = f.nextElement();
            final Short s = v.fid;
            if (s == (short)Fid) {
                v.update = Call;
                return;
            }
        }
        super.Fids.addElement(new CallBack(Fid, Call));
    }
    
    public void snap() {
        super.classId = 1001;
    }
    
    public void refresh() {
        super.classId = 1002;
    }
    
    public void monitor() {
        super.classId = 1000;
    }
    
    public RecordRequest unmonitor() throws IOException, MalformedURLException {
        if (super.classId == 1001) {
            return null;
        }
        final RecordRequest Req = new RecordRequest();
        Req.setService(super.service);
        Req.setKey(super.key);
        Req.setType((short)1003);
        final Enumeration f = super.Fids.elements();
        while (f.hasMoreElements()) {
            final CallBack cbk = f.nextElement();
            final Short s = cbk.fid;
            final RequestFidCallback c = cbk.update;
            Req.setFid(s, c);
        }
        return Req;
    }
    
    public void error() {
        final Enumeration f = super.Fids.elements();
        while (f.hasMoreElements()) {
            final CallBack cbk = f.nextElement();
            final RequestFidCallback c = cbk.update;
            if (c != null) {
                c.FidUpdate(super.key, cbk.fid, null, (short)4, (short)0, 0L);
            }
        }
    }
    
    public RecordRequest unmonitor(final short fid) throws IOException, MalformedURLException {
        if (super.classId == 1001) {
            return null;
        }
        RecordRequest Req;
        if (super.myUrl == null) {
            Req = new RecordRequest();
            Req.setService(super.service);
            Req.setKey(super.key);
        }
        else {
            Req = new RecordRequest(super.myUrl.toString());
        }
        Req.setType((short)1003);
        final Short s = new Short(fid);
        final Enumeration f = super.Fids.elements();
        while (f.hasMoreElements()) {
            final CallBack cbk = f.nextElement();
            if (s == (short)cbk.fid) {
                final RequestFidCallback c = cbk.update;
                Req.setFid(s, c);
                super.Fids.removeElement(cbk);
                return Req;
            }
        }
        return null;
    }
    
    public int formatRequest(final byte[] buf, final int offset) {
        final int e;
        int p = e = MessageFormat.setEnvelope((short)(-20), (byte)11, (short)0, (short)0, buf, offset);
        byte[] b = super.service.getBytes();
        p = MessageFormat.packAny((short)(-21), (byte)9, (short)b.length, (short)0, (short)0, b, buf, p);
        b = super.key.getBytes();
        p = MessageFormat.packAny((short)(-22), (byte)9, (short)b.length, (short)0, (short)0, b, buf, p);
        p = MessageFormat.packShort((short)(-25), (short)0, super.classId, buf, p);
        final int s;
        p = (s = MessageFormat.packShort((short)(-82), (short)0, this.Flags, buf, p));
        final int d;
        p = (d = MessageFormat.setEnvelope((short)(-68), (byte)10, (short)0, (short)0, buf, p));
        final Enumeration f = super.Fids.elements();
        while (f.hasMoreElements()) {
            final CallBack cbk = f.nextElement();
            final Short sht = cbk.fid;
            p = MessageFormat.nativeToShort(sht, buf, p);
        }
        MessageFormat.setEnvelope((short)(-68), (byte)10, (short)(p - d), (short)0, buf, s);
        MessageFormat.setEnvelope((short)(-20), (byte)11, (short)(p - e), (short)0, buf, offset);
        return p - offset;
    }
    
    private void parseBytes(final byte[] b, final int offset, final int len) {
        int P = offset;
        final int E = P + len;
        while (P < E) {
            short Fid = MessageFormat.shortToNative(b, P);
            P += 2;
            final byte Type = b[P++];
            if (MessageFormat.isaArray(Type)) {
                final short Array = MessageFormat.shortToNative(b, P);
                P += 2;
            }
            else {
                final short Array = 0;
            }
            short Lgth = 0;
            switch (MessageFormat.getTypeOnly(Type)) {
                case 1:
                case 2: {
                    Lgth = 2;
                    break;
                }
                case 3:
                case 4: {
                    Lgth = 4;
                    break;
                }
                case 5: {
                    Lgth = 8;
                    break;
                }
                case 6: {
                    Lgth = 4;
                    break;
                }
                case 7: {
                    Lgth = 4;
                    break;
                }
                default: {
                    Lgth = MessageFormat.shortToNative(b, P);
                    P += 2;
                    break;
                }
            }
            int dataOffset = P;
            P += Lgth;
            switch (Fid) {
                case -21: {
                    super.service = new String(b, dataOffset, Lgth - 1);
                    continue;
                }
                case -22: {
                    super.key = new String(b, dataOffset, Lgth - 1);
                    continue;
                }
                case -81: {
                    this.oldKey = new String(b, dataOffset, Lgth - 1);
                    this.change = true;
                    continue;
                }
                case -46: {
                    super.status = MessageFormat.shortToNative(b, dataOffset);
                    continue;
                }
                case -74: {
                    this.seq = MessageFormat.longToNative(b, dataOffset);
                    continue;
                }
                case -77:
                case -56: {
                    this.txt = new String(b, dataOffset, Lgth);
                    continue;
                }
                case -68: {
                    final int end = dataOffset + Lgth;
                    while (dataOffset < end) {
                        Fid = MessageFormat.shortToNative(b, dataOffset);
                        dataOffset += 2;
                        super.Fids.addElement(new CallBack(new Short(Fid), null));
                    }
                    continue;
                }
                default: {
                    continue;
                }
            }
        }
    }
    
    public void setNoMerge() {
        this.Flags = 1;
    }
}
