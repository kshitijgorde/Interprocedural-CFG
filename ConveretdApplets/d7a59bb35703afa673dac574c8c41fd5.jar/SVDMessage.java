import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDMessage
{
    public byte[] btHeader;
    public byte[] btMessage;
    public byte btSecurity;
    public byte btSenderType;
    public byte btCommand;
    public byte btReserved;
    public int idSesiune;
    public int idSender;
    public int idDest;
    public int nSizeOfMessage;
    private int nOffset;
    SVDCommunicator Owner;
    public static final byte SVDNEWVIZSES = 12;
    public static final byte SVDVIZSESTEXT = 24;
    public static final byte SVDVIDEOOK = 25;
    public static final byte SVDTYPE = 101;
    public static final byte SVDSENDKEY = 104;
    public static final byte REGISTRATION = 26;
    public static final byte NOOPERATORONLINE = 35;
    public static final byte SESSIONEXISTS = 36;
    public static final byte SESENDOP = 38;
    public static final byte SVDDISC = 100;
    public static final byte SVDOPVIDEO = 4;
    public static final byte SVDOPCHAT = 5;
    
    SVDMessage(final SVDCommunicator aOwner) {
        this.Owner = aOwner;
        (this.btHeader = new byte[20])[0] = (this.btSecurity = 64);
        this.btHeader[1] = (this.btSenderType = 2);
        this.nOffset = 0;
    }
    
    public boolean readHeader(final DataInputStream input) {
        if (input == null) {
            return false;
        }
        int nCount = 0;
        try {
            nCount = input.read(this.btHeader, 0, 1);
        }
        catch (IOException ioe) {
            return false;
        }
        if (nCount != 1) {
            return false;
        }
        if (this.btHeader[0] != 64 && this.btHeader[0] != 35) {
            return false;
        }
        if (this.btHeader[0] != 64) {
            return false;
        }
        try {
            nCount = input.read(this.btHeader, 1, 19);
        }
        catch (IOException ioe) {
            return false;
        }
        if (nCount != 19) {
            return false;
        }
        this.btSecurity = this.btHeader[0];
        this.btSenderType = this.btHeader[1];
        this.btCommand = this.btHeader[2];
        this.btReserved = this.btHeader[3];
        this.idSesiune = this.readInt(this.btHeader, 4);
        this.idSender = this.readInt(this.btHeader, 8);
        this.idDest = this.readInt(this.btHeader, 12);
        this.nSizeOfMessage = this.readInt(this.btHeader, 16);
        this.btMessage = new byte[this.nSizeOfMessage - 20];
        return true;
    }
    
    public boolean writeHeader() {
        this.btHeader[0] = this.btSecurity;
        this.btHeader[1] = this.btSenderType;
        this.btHeader[2] = this.btCommand;
        this.btHeader[3] = this.btReserved;
        this.writeInt(this.idSesiune, this.btHeader, 4);
        this.writeInt(this.idSender, this.btHeader, 8);
        this.writeInt(this.idDest, this.btHeader, 12);
        this.writeInt(this.nSizeOfMessage, this.btHeader, 16);
        if (this.nSizeOfMessage > 20) {
            this.btMessage = new byte[this.nSizeOfMessage - 20];
        }
        return true;
    }
    
    public boolean readMessage(final DataInputStream input) {
        if (this.nSizeOfMessage == 20) {
            return true;
        }
        int nCount = 0;
        try {
            if (this.btCommand == 4) {
                input.readFully(this.btMessage, 0, this.nSizeOfMessage - 20);
                nCount = this.nSizeOfMessage - 20;
            }
            else {
                nCount = input.read(this.btMessage, 0, this.nSizeOfMessage - 20);
            }
        }
        catch (IOException ioe) {
            return false;
        }
        return nCount == this.nSizeOfMessage - 20;
    }
    
    public boolean sendMessage(final DataOutputStream output) {
        try {
            output.write(this.btHeader, 0, 20);
            if (this.nSizeOfMessage > 20) {
                output.write(this.btMessage, 0, this.nSizeOfMessage - 20);
            }
            output.flush();
        }
        catch (IOException ioe) {
            return false;
        }
        return true;
    }
    
    private int readInt(final byte[] byteArray, final int nReadOffset) {
        return (byteArray[nReadOffset + 3] & 0xFF) << 24 | (byteArray[nReadOffset + 2] & 0xFF) << 16 | (byteArray[nReadOffset + 1] & 0xFF) << 8 | (byteArray[nReadOffset] & 0xFF);
    }
    
    private void writeInt(final int nValue, final byte[] byteArray, final int nWriteOffset) {
        byteArray[nWriteOffset] = (byte)(0xFF & nValue);
        byteArray[nWriteOffset + 1] = (byte)(0xFF & nValue >> 8);
        byteArray[nWriteOffset + 2] = (byte)(0xFF & nValue >> 16);
        byteArray[nWriteOffset + 3] = (byte)(0xFF & nValue >> 24);
    }
    
    public void writeByte(final byte btValue, final byte[] byteArray, final int nWriteOffset) {
        byteArray[nWriteOffset] = btValue;
    }
    
    public void writeInt(final int nValue) {
        this.writeInt(nValue, this.btMessage, this.nOffset);
        this.nOffset += 4;
    }
    
    public void writeString(final String sValue) {
        for (int i = 0; i < sValue.length(); ++i) {
            this.btMessage[this.nOffset + i] = (byte)sValue.charAt(i);
        }
        this.btMessage[this.nOffset + sValue.length()] = 0;
        this.nOffset += sValue.length() + 1;
    }
    
    public void writeByte(final byte btValue) {
        this.writeByte(btValue, this.btMessage, this.nOffset);
        ++this.nOffset;
    }
}
