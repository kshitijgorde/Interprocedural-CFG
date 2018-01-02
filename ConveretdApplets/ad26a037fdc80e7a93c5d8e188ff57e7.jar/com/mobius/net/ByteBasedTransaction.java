// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class ByteBasedTransaction extends ServerTransaction
{
    private static final int BUFFER_SIZE = 16384;
    
    public ByteBasedTransaction(final ServerConnection serverConnection) {
        super(serverConnection);
    }
    
    public byte[] sendAndReceive(final String s) throws ServerCommunicationException {
        return this.sendAndReceive(s.getBytes());
    }
    
    public byte[] sendAndReceive(final byte[] array) throws ServerCommunicationException {
        if (array == null) {
            throw new IllegalArgumentException("Request must be non-null");
        }
        try {
            this.sendBytes(array);
            return this.receiveBytes();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new ServerCommunicationException(ex.getMessage());
        }
        catch (SecurityException ex2) {
            ex2.printStackTrace();
            throw new ServerCommunicationException(ex2.getMessage());
        }
        catch (RuntimeException ex3) {
            ex3.printStackTrace();
            throw new ServerCommunicationException(ex3.getMessage());
        }
        finally {
            super.connection.cleanUp();
        }
    }
    
    private void sendBytes(final byte[] array) throws IOException {
        final OutputStream outputStream = super.connection.getOutputStream();
        outputStream.write(array);
        outputStream.flush();
    }
    
    private byte[] receiveBytes() throws IOException {
        final InputStream inputStream = super.connection.getInputStream();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[16384];
        int read;
        while ((read = inputStream.read(array, 0, array.length)) != -1) {
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
