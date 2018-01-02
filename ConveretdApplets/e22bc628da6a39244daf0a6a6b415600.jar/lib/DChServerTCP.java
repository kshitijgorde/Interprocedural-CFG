// 
// Decompiled by Procyon v0.5.30
// 

package lib;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class DChServerTCP extends DChSuper
{
    Socket socket;
    int rlen;
    
    public DChServerTCP(final Socket socket) {
        this.rlen = 0;
        this.socket = socket;
    }
    
    public void close() throws IOException {
        this.socket.shutdownInput();
        this.socket.shutdownOutput();
        this.socket.close();
    }
    
    public int getLength() {
        return this.rlen - 48;
    }
    
    DataInputStream readDCh(final byte[] array, final int n, final int n2, final int n3, final int n4) throws IOException {
        return this.readDCh(n);
    }
    
    public DataInputStream readDCh(final int n) throws IOException {
        final byte[] array = new byte[16];
        final DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
        final byte[] array2 = new byte[2];
        int i = 0;
        do {
            i += dataInputStream.read(array2, i, 2 - i);
        } while (i < 2);
        this.TotalLen = new DataInputStream(new ByteArrayInputStream(array2)).readShort();
        final byte[] array3 = new byte[n];
        this.rlen = 0;
        do {
            this.rlen += dataInputStream.read(array3, this.rlen, array3.length - this.rlen);
        } while (this.rlen < this.TotalLen - 2);
        this.rlen += i;
        final DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(array3));
        this.CommandNumber = dataInputStream2.readShort();
        this.Dummy = dataInputStream2.readShort();
        dataInputStream2.read(array, 0, array.length);
        this.ChannelName = new String(array, 0, array.length);
        dataInputStream2.read(array, 0, array.length);
        this.MemberName = new String(array, 0, array.length);
        this.Min = dataInputStream2.readShort();
        this.Max = dataInputStream2.readShort();
        this.ReturnCode = dataInputStream2.readShort();
        this.Dummy = dataInputStream2.readShort();
        this.Dummy = dataInputStream2.readShort();
        return dataInputStream2;
    }
    
    public void writeDCh(final byte[] array, final int n, final int n2) throws IOException {
        final OutputStream outputStream = this.socket.getOutputStream();
        this.TotalLen = (short)(48 + n);
        this.ReturnCode = (short)n2;
        this.Dummy = 0;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.TotalLen);
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(this.TotalLen);
        dataOutputStream.writeShort(this.CommandNumber);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.writeBytes(this.ChannelName);
        dataOutputStream.writeBytes(this.MemberName);
        dataOutputStream.writeShort(this.Min);
        dataOutputStream.writeShort(this.Max);
        dataOutputStream.writeShort(this.ReturnCode);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.write(array, 0, n);
        outputStream.write(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
    }
    
    public void writeDChList(final byte[] array, final int n) throws IOException {
        final OutputStream outputStream = this.socket.getOutputStream();
        if (this.TotalLen < n) {
            this.ReturnCode = -90;
        }
        else {
            this.ReturnCode = 0;
        }
        this.TotalLen = (short)(48 + n);
        this.Max = (short)n;
        this.Dummy = 0;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.TotalLen);
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(this.TotalLen);
        dataOutputStream.writeShort(this.CommandNumber);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.writeBytes(this.ChannelName);
        dataOutputStream.writeBytes(this.MemberName);
        dataOutputStream.writeShort(this.Min);
        dataOutputStream.writeShort(this.Max);
        dataOutputStream.writeShort(this.ReturnCode);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.writeShort(this.Dummy);
        dataOutputStream.write(array, 0, n);
        outputStream.write(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
    }
}
