// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class StringBasedTransaction extends ServerTransaction
{
    private static final int BUFFER_SIZE = 16384;
    
    public StringBasedTransaction(final ServerConnection serverConnection) {
        super(serverConnection);
    }
    
    public String sendAndReceive(final String s) throws ServerCommunicationException {
        if (s == null) {
            throw new IllegalArgumentException("Request must be non-null");
        }
        try {
            this.sendString(s);
            return this.receiveString();
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
    
    private void sendString(final String s) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(super.connection.getOutputStream());
        outputStreamWriter.write(s);
        outputStreamWriter.flush();
    }
    
    private String receiveString() throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(super.connection.getInputStream()));
        final StringBuffer sb = new StringBuffer(16384);
        final char[] array = new char[16384];
        int read;
        while ((read = bufferedReader.read(array, 0, array.length)) != -1) {
            sb.append(array, 0, read);
        }
        return sb.toString().trim();
    }
}
