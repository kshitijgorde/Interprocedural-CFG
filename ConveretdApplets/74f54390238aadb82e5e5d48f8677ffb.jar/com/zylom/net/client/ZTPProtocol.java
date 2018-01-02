// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.net.SocketException;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import com.zylom.cipher.CipherByteArray;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class ZTPProtocol extends Protocol implements Runnable
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Location location;
    private Thread thread;
    private Connection connection;
    
    void open(final Connection connection, final Location location, final Socket socket, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.connection = connection;
        this.location = location;
        this.socket = socket;
        if (connection.getUseBrowser()) {
            throw new IOException("Browser support not available for hte ZTP protocol at this time.");
        }
        this.in = new DataInputStream(inputStream);
        this.out = new DataOutputStream(outputStream);
        this.sendHeader(new OutputStreamWriter(outputStream));
        (this.thread = new Thread(this, "ZTPProtocol")).setDaemon(true);
        this.thread.start();
    }
    
    void close(final Connection connection) {
        this.socket = null;
        this.in = null;
        this.out = null;
        this.thread = null;
    }
    
    void sendData(final Connection connection, final PDU pdu) throws IOException {
        if (!connection.isOpen()) {
            connection.open();
        }
        if (pdu != null) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(pdu.size());
            final Enumeration names = pdu.names();
            while (names.hasMoreElements()) {
                final String s = names.nextElement();
                final byte[] bytes = s.getBytes();
                dataOutputStream.writeByte(bytes.length);
                dataOutputStream.write(bytes);
                final Object object = pdu.getObject(s);
                int n = 0;
                if (object instanceof Integer) {
                    n = 1;
                }
                else if (object instanceof Long) {
                    n = 4;
                }
                else if (object instanceof Double) {
                    n = 2;
                }
                else if (object instanceof Boolean) {
                    n = 3;
                }
                else if (object instanceof byte[]) {
                    n = 5;
                }
                dataOutputStream.writeByte(n);
                switch (n) {
                    case 0: {
                        final byte[] bytes2 = object.toString().getBytes();
                        dataOutputStream.writeShort(bytes2.length);
                        dataOutputStream.write(bytes2);
                        continue;
                    }
                    case 1: {
                        dataOutputStream.writeInt((int)object);
                        continue;
                    }
                    case 4: {
                        dataOutputStream.writeLong((long)object);
                        continue;
                    }
                    case 5: {
                        final byte[] array = (byte[])object;
                        dataOutputStream.writeShort(array.length);
                        dataOutputStream.write(array);
                        continue;
                    }
                    case 2: {
                        dataOutputStream.writeDouble((double)object);
                        continue;
                    }
                    case 3: {
                        dataOutputStream.writeBoolean((boolean)object);
                        continue;
                    }
                }
            }
            dataOutputStream.flush();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final byte[] cipher = CipherByteArray.cipher(byteArray, byteArray.length);
            this.out.writeByte(pdu.getType());
            this.out.writeInt(cipher.length);
            this.out.write(cipher);
            this.out.flush();
            connection.fireDataSend(new ConnectionEvent(connection, pdu));
        }
    }
    
    private void sendHeader(final OutputStreamWriter outputStreamWriter) throws IOException {
        String s = "ZTP\r\nversion: 1.0\r\n";
        if (this.location.getParameters().get("sessionID") != null) {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("sessionID: ").append(this.location.getParameters().get("sessionID")).append("\r\n"))))));
        }
        if (this.location.getParameters().get("ttl") != null) {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ttl: ").append(this.location.getParameters().get("ttl")).append("\r\n"))))));
        }
        if (this.location.getParameters().get("displayName") != null) {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("displayName: ").append(this.location.getParameters().get("displayName")).append("\r\n"))))));
        }
        final String concat = String.valueOf(String.valueOf(s)).concat("\r\n");
        outputStreamWriter.write(concat, 0, concat.length());
        outputStreamWriter.flush();
    }
    
    public void run() {
        while (this.thread == Thread.currentThread()) {
            final PDU pdu = new PDU();
            try {
                pdu.setType(this.in.readByte());
                pdu.setServerReceiveTime(this.in.readLong());
                pdu.setServerSendTime(this.in.readLong());
                final byte[] array = new byte[this.in.readInt()];
                this.in.readFully(array);
                final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(CipherByteArray.cipher(array, array.length)));
                for (byte byte1 = dataInputStream.readByte(), b = 0; b < byte1; ++b) {
                    final byte[] array2 = new byte[dataInputStream.readByte()];
                    dataInputStream.read(array2);
                    final String s = new String(array2);
                    switch (dataInputStream.readByte()) {
                        case 3: {
                            pdu.add(s, dataInputStream.readBoolean());
                            break;
                        }
                        case 4: {
                            pdu.add(s, dataInputStream.readLong());
                            break;
                        }
                        case 5: {
                            final byte[] array3 = new byte[dataInputStream.readShort()];
                            dataInputStream.read(array3);
                            pdu.add(s, array3);
                            break;
                        }
                        case 2: {
                            pdu.add(s, dataInputStream.readDouble());
                            break;
                        }
                        case 1: {
                            pdu.add(s, dataInputStream.readInt());
                            break;
                        }
                        case 0: {
                            final byte[] array4 = new byte[dataInputStream.readShort()];
                            dataInputStream.read(array4);
                            pdu.add(s, new String(array4));
                            break;
                        }
                    }
                }
                if (this.connection == null) {
                    continue;
                }
                this.connection.queuePDU(pdu);
            }
            catch (Exception ex) {
                if (!(ex instanceof SocketException) || ex.toString().toLowerCase().indexOf("by peer") < 0) {
                    this.connection.fireReadError(new ConnectionEvent(this.connection, ex));
                }
                this.thread = null;
                if (this.connection == null) {
                    continue;
                }
                this.connection.close();
            }
        }
    }
}
