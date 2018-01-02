// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import logging.LogHolder;
import logging.LogType;
import java.io.DataInputStream;
import anon.client.crypto.SymCipher;
import java.io.InputStream;
import java.util.Vector;
import java.security.SecureRandom;

public class MixPacket
{
    private static final int PACKET_SIZE = 998;
    private static final int NON_DATA_LENGTH = 6;
    private static SecureRandom ms_secureRandom;
    private int m_channelId;
    private short m_channelFlags;
    private byte[] m_payloadData;
    private Vector m_sendCallbackHandlers;
    
    public static int getPacketSize() {
        return 998;
    }
    
    public static int getPayloadSize() {
        return 992;
    }
    
    public MixPacket(final InputStream inputStream, final SymCipher symCipher) throws IOException {
        this.m_sendCallbackHandlers = new Vector();
        final byte[] array = new byte[998];
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        Label_0120: {
            try {
                dataInputStream.readFully(array);
            }
            catch (EOFException ex) {
                LogHolder.log(4, LogType.NET, Thread.currentThread().getName() + ": received a truncated packet from a mix: ", ex);
                throw ex;
            }
            catch (IOException ex2) {
                try {
                    if (Class.forName("java.net.SocketTimeoutException").isAssignableFrom(ex2.getClass())) {
                        dataInputStream.readFully(array);
                        break Label_0120;
                    }
                    throw ex2;
                }
                catch (ClassNotFoundException ex3) {
                    throw ex2;
                }
            }
        }
        if (symCipher != null) {
            symCipher.encryptAES1(array, 0, array, 0, 16);
        }
        final DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(array, 0, 6));
        this.m_channelId = dataInputStream2.readInt();
        this.m_channelFlags = dataInputStream2.readShort();
        System.arraycopy(array, 6, this.m_payloadData = new byte[array.length - 6], 0, array.length - 6);
    }
    
    public MixPacket(final int channelId) {
        this.m_sendCallbackHandlers = new Vector();
        this.m_channelId = channelId;
        this.m_channelFlags = 0;
        this.m_payloadData = new byte[992];
        MixPacket.ms_secureRandom.nextBytes(this.m_payloadData);
    }
    
    public int getChannelId() {
        return this.m_channelId;
    }
    
    public short getChannelFlags() {
        return this.m_channelFlags;
    }
    
    public void setChannelFlags(final short channelFlags) {
        this.m_channelFlags = channelFlags;
    }
    
    public byte[] getPayloadData() {
        return this.m_payloadData;
    }
    
    public byte[] getRawPacket() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeInt(this.m_channelId);
            dataOutputStream.writeShort(this.m_channelFlags);
            dataOutputStream.flush();
        }
        catch (IOException ex) {}
        final byte[] array = new byte[998];
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.arraycopy(byteArray, 0, array, 0, byteArray.length);
        System.arraycopy(this.m_payloadData, 0, array, byteArray.length, this.m_payloadData.length);
        return array;
    }
    
    public Vector getSendCallbackHandlers() {
        return this.m_sendCallbackHandlers;
    }
    
    static {
        MixPacket.ms_secureRandom = new SecureRandom();
    }
}
