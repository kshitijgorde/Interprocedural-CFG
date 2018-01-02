// 
// Decompiled by Procyon v0.5.30
// 

package lib;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public abstract class DChSuper
{
    static final int DCh_HEADER_SIZE = 48;
    static final int DCh_COMMAND_SEND = 2;
    static final int DCh_COMMAND_RECEIVE = 3;
    static final int DCh_COMMAND_CREATE = 5;
    static final int DCh_COMMAND_LIST = 7;
    static final int DCh_COMMAND_DELETE = 8;
    static final int DCh_MIN1 = 1;
    static final int DCh_MAX1 = 1;
    static final int DCh_BYTE_SIZE = 1;
    static final int DCh_SHORT_SIZE = 2;
    static final int DCh_INT_SIZE = 4;
    static final int DCh_FLOAT_SIZE = 4;
    static final int DCh_DOUBLE_SIZE = 8;
    short TotalLen;
    short CommandNumber;
    String ChannelName;
    String MemberName;
    short Min;
    short Max;
    short ReturnCode;
    short Dummy;
    
    public int getTotalLen() {
        return this.TotalLen;
    }
    
    public int getCommandNumber() {
        return this.CommandNumber;
    }
    
    public int getMin() {
        return this.Min;
    }
    
    public int getMax() {
        return this.Max;
    }
    
    public int getReturnCode() {
        return this.ReturnCode;
    }
    
    public String getChannelName() {
        return this.ChannelName;
    }
    
    public String getMemberName() {
        return this.MemberName;
    }
    
    public void setChannelName(final String s) {
        final StringBuffer sb = new StringBuffer("                ");
        sb.insert(0, s);
        sb.setLength(16);
        this.ChannelName = sb.toString();
    }
    
    public void setMemberName(final String s) {
        final StringBuffer sb = new StringBuffer("                ");
        sb.insert(0, s);
        sb.setLength(16);
        this.MemberName = sb.toString();
    }
    
    public void setChannelMember(final String channelName, final String memberName) {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
    }
    
    abstract DataInputStream readDCh(final byte[] p0, final int p1, final int p2, final int p3, final int p4) throws IOException;
    
    public int read(final byte[] array, final int n) throws IOException {
        return this.readDCh(array, n, 3, 1, 1).read(array, 0, n);
    }
    
    public int read(final String memberName, final byte[] array, final int n) throws IOException {
        this.setMemberName(memberName);
        return this.read(array, n);
    }
    
    public int read(final String channelName, final String memberName, final byte[] array, final int n) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.read(array, n);
    }
    
    public byte readByte() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);
        new DataOutputStream(byteArrayOutputStream).writeByte(0);
        return this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, 1, 1).readByte();
    }
    
    public byte readByte(final String memberName) throws IOException {
        this.setMemberName(memberName);
        return this.readByte();
    }
    
    public byte readByte(final String channelName, final String memberName) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readByte();
    }
    
    public byte[] readByte(final int n, final int n2) throws IOException {
        final byte[] array = new byte[n2 - n + 1];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1 * (n2 - n + 1));
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = n; i <= n2; ++i) {
            dataOutputStream.writeByte(array[i]);
        }
        final DataInputStream dCh = this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, n + 1, n2 + 1);
        for (int j = n; j <= n2; ++j) {
            array[j] = dCh.readByte();
        }
        return array;
    }
    
    public byte[] readByte(final String memberName, final int n, final int n2) throws IOException {
        this.setMemberName(memberName);
        return this.readByte(n, n2);
    }
    
    public byte[] readByte(final String channelName, final String memberName, final int n, final int n2) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readByte(n, n2);
    }
    
    public short readShort() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2);
        new DataOutputStream(byteArrayOutputStream).writeShort(0);
        return this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, 1, 1).readShort();
    }
    
    public short readShort(final String memberName) throws IOException {
        this.setMemberName(memberName);
        return this.readShort();
    }
    
    public short readShort(final String channelName, final String memberName) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readShort();
    }
    
    public short[] readShort(final int n, final int n2) throws IOException {
        final short[] array = new short[n2 - n + 1];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2 * (n2 - n + 1));
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = n; i <= n2; ++i) {
            dataOutputStream.writeShort(array[i]);
        }
        final DataInputStream dCh = this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, n + 1, n2 + 1);
        for (int j = n; j <= n2; ++j) {
            array[j] = dCh.readShort();
        }
        return array;
    }
    
    public short[] readShort(final String memberName, final int n, final int n2) throws IOException {
        this.setMemberName(memberName);
        return this.readShort(n, n2);
    }
    
    public short[] readShort(final String channelName, final String memberName, final int n, final int n2) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readShort(n, n2);
    }
    
    public int readInt() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        new DataOutputStream(byteArrayOutputStream).writeInt(0);
        return this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, 1, 1).readInt();
    }
    
    public int readInt(final String memberName) throws IOException {
        this.setMemberName(memberName);
        return this.readInt();
    }
    
    public int readInt(final String channelName, final String memberName) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readInt();
    }
    
    public int[] readInt(final int n, final int n2) throws IOException {
        final int[] array = new int[n2 - n + 1];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * (n2 - n + 1));
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = n; i <= n2; ++i) {
            dataOutputStream.writeInt(array[i]);
        }
        final DataInputStream dCh = this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, n + 1, n2 + 1);
        for (int j = n; j <= n2; ++j) {
            array[j] = dCh.readInt();
        }
        return array;
    }
    
    public int[] readInt(final String memberName, final int n, final int n2) throws IOException {
        this.setMemberName(memberName);
        return this.readInt(n, n2);
    }
    
    public int[] readInt(final String channelName, final String memberName, final int n, final int n2) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readInt(n, n2);
    }
    
    public float readFloat() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        new DataOutputStream(byteArrayOutputStream).writeFloat(0.0f);
        return this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, 1, 1).readFloat();
    }
    
    public float readFloat(final String memberName) throws IOException {
        this.setMemberName(memberName);
        return this.readFloat();
    }
    
    public float readFloat(final String channelName, final String memberName) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readFloat();
    }
    
    public float[] readFloat(final int n, final int n2) throws IOException {
        final float[] array = new float[n2 - n + 1];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * (n2 - n + 1));
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = n; i <= n2; ++i) {
            dataOutputStream.writeFloat(array[i]);
        }
        final DataInputStream dCh = this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, n + 1, n2 + 1);
        for (int j = n; j <= n2; ++j) {
            array[j] = dCh.readFloat();
        }
        return array;
    }
    
    public float[] readFloat(final String memberName, final int n, final int n2) throws IOException {
        this.setMemberName(memberName);
        return this.readFloat(n, n2);
    }
    
    public float[] readFloat(final String channelName, final String memberName, final int n, final int n2) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readFloat(n, n2);
    }
    
    public double readDouble() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        new DataOutputStream(byteArrayOutputStream).writeDouble(0.0);
        return this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, 1, 1).readDouble();
    }
    
    public double readDouble(final String memberName) throws IOException {
        this.setMemberName(memberName);
        return this.readDouble();
    }
    
    public double readDouble(final String channelName, final String memberName) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readDouble();
    }
    
    public double[] readDouble(final int n, final int n2) throws IOException {
        final double[] array = new double[n2 - n + 1];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8 * (n2 - n + 1));
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = n; i <= n2; ++i) {
            dataOutputStream.writeDouble(array[i]);
        }
        final DataInputStream dCh = this.readDCh(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), 3, n + 1, n2 + 1);
        for (int j = n; j <= n2; ++j) {
            array[j] = dCh.readDouble();
        }
        return array;
    }
    
    public double[] readDouble(final String memberName, final int n, final int n2) throws IOException {
        this.setMemberName(memberName);
        return this.readDouble(n, n2);
    }
    
    public double[] readDouble(final String channelName, final String memberName, final int n, final int n2) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readDouble(n, n2);
    }
    
    public String readString(final int n) throws IOException {
        final byte[] array = new byte[n];
        this.readDCh(array, n, 3, 1, 1).read(array, 0, n);
        return new String(array, 0, n);
    }
    
    public String readString(final String memberName, final int n) throws IOException {
        this.setMemberName(memberName);
        return this.readString(n);
    }
    
    public String readString(final String channelName, final String memberName, final int n) throws IOException {
        this.setChannelName(channelName);
        this.setMemberName(memberName);
        return this.readString(n);
    }
}
