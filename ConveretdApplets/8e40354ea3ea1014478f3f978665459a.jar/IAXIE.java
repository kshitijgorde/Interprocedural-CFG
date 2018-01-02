// 
// Decompiled by Procyon v0.5.30
// 

public class IAXIE
{
    IE id;
    byte[] data;
    
    public IAXIE(final int i, final byte[] b) {
        this.id = IE.values()[i];
        this.data = b;
    }
    
    public IAXIE(final IE ie, final byte[] b) {
        this.id = ie;
        this.data = b;
    }
    
    public IAXIE(final IE ie, final String s) {
        this.id = ie;
        this.data = s.getBytes();
    }
    
    public IAXIE(final IE ie, final int value) {
        this.id = ie;
        (this.data = new byte[4])[0] = (byte)(value >> 24);
        this.data[1] = (byte)(value >> 16);
        this.data[2] = (byte)(value >> 8);
        this.data[3] = (byte)value;
    }
    
    public IAXIE(final IE ie, final short value) {
        this.id = ie;
        (this.data = new byte[2])[0] = (byte)(value >> 8);
        this.data[1] = (byte)value;
    }
    
    public IAXIE(final IE ie, final byte value) {
        this.id = ie;
        (this.data = new byte[1])[0] = value;
    }
    
    public void print() {
        this.printData();
    }
    
    public void printData() {
        final String s = "\tIAXIE:  " + this.id + "\t\t:  ";
        switch (this.id) {
            case IAXUNKNOWN:
            case CALLINGPRES:
            case CALLINGTON:
            case CAUSECODE: {
                Starphone.log(s + new Integer(this.dataAsByte()).toString());
            }
            case VERSION:
            case ADSICPE:
            case REFRESH:
            case DPSTATUS:
            case AUTHMETHODS:
            case CALLNO:
            case MSGCOUNT:
            case FIRMWAREVER:
            case CALLINGTNS:
            case SAMPLINGRATE:
            case ENCRYPTION:
            case RRDELAY: {
                Starphone.log(s + new Integer(this.dataAsShort()).toString());
            }
            case CAPABILITY:
            case FORMAT:
            case TRANSFERID:
            case FWBLOCKDESC:
            case ENCKEY:
            case RRJITTER:
            case RRLOSS:
            case RRPKTS:
            case RRDROPPED: {
                Starphone.log(s + new Integer(this.dataAsInt()).toString());
            }
            case APPARENTADDR: {
                this.printSockaddr();
            }
            case DATETIME: {
                this.printDatetime();
            }
            case PROVISIONING:
            case AESPROVISIONING:
            case FWBLOCKDATA: {
                Starphone.log(s);
            }
            default: {
                Starphone.log(s + this.dataAsString());
            }
        }
    }
    
    private void printDatetime() {
        final int i = (this.data[0] << 24 & 0xFF000000) | (this.data[1] << 16 & 0xFF0000) | (this.data[2] << 8 & 0xFF00) | (this.data[3] & 0xFF);
        String s = "";
        final int yr = (i >> 25 & 0x7F) + 2000;
        final int month = i >> 21 & 0xF;
        final int day = i >> 16 & 0x1F;
        final int hr = i >> 11 & 0x1F;
        final int min = i >> 5 & 0x3F;
        final int sec = (i & 0x1F) << 1;
        s = s + yr + "-" + month + "-" + day + "  ";
        if (hr < 10) {
            s += "0";
        }
        s = s + hr + ":";
        if (min < 10) {
            s += "0";
        }
        s = s + min + ":";
        if (sec < 10) {
            s += "0";
        }
        s += sec;
        Starphone.log("\tIAXIE:  " + this.id + "\t\t:  " + s);
    }
    
    private void printSockaddr() {
        final int i = (this.data[0] << 8 & 0xFF00) | (this.data[1] & 0xFF);
        String s = "";
        if (i == 512) {
            s += "IPv4 ";
        }
        s = s + (this.data[4] & 0xFF) + ".";
        s = s + (this.data[5] & 0xFF) + ".";
        s = s + (this.data[6] & 0xFF) + ".";
        s = s + (this.data[7] & 0xFF) + ":";
        s += ((this.data[2] << 8 & 0xFF00) | (this.data[3] & 0xFF));
        Starphone.log("\tIAXIE:  " + this.id + "\t\t:  " + s);
    }
    
    public byte[] asByteArray() {
        final byte[] ret = new byte[2 + this.data.length];
        ret[0] = (byte)this.id.ordinal();
        ret[1] = (byte)this.data.length;
        for (int i = 0; i < this.data.length; ++i) {
            ret[i + 2] = this.data[i];
        }
        return ret;
    }
    
    public String dataAsString() {
        return new String(this.data);
    }
    
    public int dataAsInt() {
        return this.data[0] << 24 | (this.data[1] << 16 & 0xFF0000) | (this.data[2] << 8 & 0xFF00) | (this.data[3] & 0xFF);
    }
    
    public int dataAsShort() {
        return this.data[0] << 8 | (this.data[1] & 0xFF);
    }
    
    public int dataAsByte() {
        return this.data[0];
    }
    
    public String dataAsSockaddrIP() {
        String s = "";
        s = s + (this.data[4] & 0xFF) + ".";
        s = s + (this.data[5] & 0xFF) + ".";
        s = s + (this.data[6] & 0xFF) + ".";
        s += (this.data[7] & 0xFF);
        return s;
    }
    
    public String dataAsSockaddrPort() {
        String s = "";
        s += ((this.data[2] << 8 & 0xFF00) | (this.data[3] & 0xFF));
        return s;
    }
    
    enum IE
    {
        NULL, 
        CALLEDNUMBER, 
        CALLINGNUMBER, 
        CALLINGANI, 
        CALLINGNAME, 
        CALLEDCONTEXT, 
        USERNAME, 
        PASSWORD, 
        CAPABILITY, 
        FORMAT, 
        LANGUAGE, 
        VERSION, 
        ADSICPE, 
        DNID, 
        AUTHMETHODS, 
        CHALLENGE, 
        MD5RESULT, 
        RSARESULT, 
        APPARENTADDR, 
        REFRESH, 
        DPSTATUS, 
        CALLNO, 
        CAUSE, 
        IAXUNKNOWN, 
        MSGCOUNT, 
        AUTOANSWER, 
        MUSICONHOLD, 
        TRANSFERID, 
        RDNIS, 
        PROVISIONING, 
        AESPROVISIONING, 
        DATETIME, 
        DEVICETYPE, 
        SERVICEIDENT, 
        FIRMWAREVER, 
        FWBLOCKDESC, 
        FWBLOCKDATA, 
        PROVVER, 
        CALLINGPRES, 
        CALLINGTON, 
        CALLINGTNS, 
        SAMPLINGRATE, 
        CAUSECODE, 
        ENCRYPTION, 
        ENCKEY, 
        CODECPREFS, 
        RRJITTER, 
        RRLOSS, 
        RRPKTS, 
        RRDELAY, 
        RRDROPPED, 
        RROOO;
    }
}
