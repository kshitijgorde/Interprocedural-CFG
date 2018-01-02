// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import java.util.Arrays;

public class ObjectBasedTransaction extends ServerTransaction
{
    public ObjectBasedTransaction(final ServerConnection serverConnection) {
        super(serverConnection);
    }
    
    public Object sendAndReceive(final Object[] array) throws ServerCommunicationException {
        if (array == null) {
            throw new IllegalArgumentException("Request must be non-null");
        }
        return this.sendAndReceive(new Vector(Arrays.asList(array)));
    }
    
    public Object sendAndReceive(final Object o) throws ServerCommunicationException {
        if (o == null) {
            throw new IllegalArgumentException("Request must be non-null");
        }
        try {
            this.sendObject(o);
            return this.receiveObject();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new ServerCommunicationException(ex.getMessage());
        }
        catch (ClassNotFoundException ex2) {
            ex2.printStackTrace();
            throw new ServerCommunicationException(ex2.getMessage());
        }
        catch (SecurityException ex3) {
            ex3.printStackTrace();
            throw new ServerCommunicationException(ex3.getMessage());
        }
        catch (RuntimeException ex4) {
            ex4.printStackTrace();
            throw new ServerCommunicationException(ex4.getMessage());
        }
        finally {
            super.connection.cleanUp();
        }
    }
    
    private void sendObject(final Object o) throws IOException {
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(super.connection.getOutputStream()));
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
    
    private Object receiveObject() throws ClassNotFoundException, IOException {
        final ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(super.connection.getInputStream()));
        final Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}
