// 
// Decompiled by Procyon v0.5.30
// 

public abstract class IAXFrame
{
    boolean full;
    short scallno;
    int timestamp;
    byte[] streamData;
    
    abstract void print(final int p0);
    
    abstract byte[] asByteArray();
    
    enum FRAMETYPES
    {
        NULL, 
        DTMF, 
        VOICE, 
        VIDEO, 
        CONTROL, 
        NULLFRAME, 
        IAX, 
        TEXT, 
        IMAGE, 
        HTML, 
        CNG;
    }
    
    enum CTRLSUBCLASS
    {
        NULL, 
        HANGUP, 
        RESERVED1, 
        RINGING, 
        ANSWER, 
        BUSY, 
        RESERVED2, 
        RESERVED3, 
        CONGESTION, 
        FLASHHOOK, 
        RESERVED4, 
        OPTION, 
        KEYRADIO, 
        UNKEYRADIO, 
        CALLPROGRESS, 
        CALLPROCEEDING, 
        HOLD, 
        UNHOLD, 
        VIDUPDATE;
    }
    
    enum IAXSUBCLASS
    {
        NULL, 
        NEW, 
        PING, 
        PONG, 
        ACK, 
        HANGUP, 
        REJECT, 
        ACCEPT, 
        AUTHREQ, 
        AUTHREP, 
        INVAL, 
        LAGRQ, 
        LAGRP, 
        REGREQ, 
        REGAUTH, 
        REGACK, 
        REGREJ, 
        REGREL, 
        VNAK, 
        DPREQ, 
        DPREP, 
        DIAL, 
        TXREQ, 
        TXCNT, 
        TXACC, 
        TXREADY, 
        TXREL, 
        TXREJ, 
        QUELCH, 
        UNQUELCH, 
        POKE, 
        PAGE, 
        MWI, 
        UNSUPPORT, 
        TRANSFER, 
        PROVISION, 
        FWDOWNL, 
        FWDATA;
    }
}
