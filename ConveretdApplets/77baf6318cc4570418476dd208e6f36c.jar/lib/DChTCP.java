// 
// Decompiled by Procyon v0.5.30
// 

package lib;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DChTCP extends DChSuper
{
    Socket socket;
    OutputStream socketos;
    InputStream socketis;
    
    public DChTCP(final String s, final int n) throws UnknownHostException, IOException {
        this.socket = new Socket(s, n);
        this.socketos = this.socket.getOutputStream();
        this.socketis = this.socket.getInputStream();
    }
    
    public void open_dch() throws IOException {
        final byte[] array = { 0 };
        this.setChannelName("open_dch");
        this.setMemberName("");
        this.readDCh(array, 0, 2, 0, 0);
    }
    
    public void exit_dch() throws IOException {
        final byte[] array = { 0 };
        this.setChannelName("exit_dch");
        this.setMemberName("");
        this.readDCh(array, 0, 2, 0, 0);
        this.socket.shutdownInput();
        this.socket.shutdownOutput();
        this.socket.close();
    }
    
    DataInputStream readDCh(final byte[] array, final int n, final int n2, final int n3, final int n4) throws IOException {
        final byte[] array2 = new byte[16];
        this.TotalLen = (short)(48 + n);
        this.CommandNumber = (short)n2;
        this.Min = (short)n3;
        this.Max = (short)n4;
        this.ReturnCode = 0;
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
        this.socketos.write(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
        final byte[] array3 = new byte[this.TotalLen];
        int i = 0;
        do {
            i += this.socketis.read(array3, i, array3.length - i);
        } while (i < this.TotalLen);
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array3));
        this.TotalLen = dataInputStream.readShort();
        this.CommandNumber = dataInputStream.readShort();
        this.Dummy = dataInputStream.readShort();
        dataInputStream.read(array2, 0, array2.length);
        this.ChannelName = new String(array2, 0, array2.length);
        dataInputStream.read(array2, 0, array2.length);
        this.MemberName = new String(array2, 0, array2.length);
        this.Min = dataInputStream.readShort();
        this.Max = dataInputStream.readShort();
        this.ReturnCode = dataInputStream.readShort();
        this.Dummy = dataInputStream.readShort();
        this.Dummy = dataInputStream.readShort();
        return dataInputStream;
    }
}
