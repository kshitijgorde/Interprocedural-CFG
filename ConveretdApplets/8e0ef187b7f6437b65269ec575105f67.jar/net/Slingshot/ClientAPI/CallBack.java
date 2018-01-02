// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

class CallBack
{
    public Short fid;
    public RequestFidCallback update;
    public InsertCallback insert;
    public String value;
    public int offset;
    long sequence;
    long mySeq;
    long timeout;
    short type;
    public CallBack next;
    
    public CallBack(final Short f, final RequestFidCallback c) {
        this.fid = null;
        this.update = null;
        this.insert = null;
        this.value = null;
        this.offset = 0;
        this.sequence = 0L;
        this.mySeq = 0L;
        this.timeout = 0L;
        this.type = 0;
        this.next = null;
        this.fid = f;
        this.update = c;
    }
    
    public CallBack(final Short f, final String v, final int o, final long seq, final InsertCallback c, final long time) {
        this.fid = null;
        this.update = null;
        this.insert = null;
        this.value = null;
        this.offset = 0;
        this.sequence = 0L;
        this.mySeq = 0L;
        this.timeout = 0L;
        this.type = 0;
        this.next = null;
        this.fid = f;
        this.insert = c;
        this.value = v;
        this.offset = o;
        this.sequence = seq;
        if (time != 0L) {
            this.timeout = System.currentTimeMillis() + time;
        }
    }
}
