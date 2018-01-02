import java.io.DataOutput;
import COM.jscape.mailwidgets.JSMessage;
import java.io.ObjectOutput;
import java.rmi.server.RemoteCall;
import java.rmi.UnmarshalException;
import java.rmi.UnexpectedException;
import java.rmi.RemoteException;
import java.io.IOException;
import java.rmi.MarshalException;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.Operation;
import java.rmi.Remote;
import java.rmi.server.RemoteStub;

// 
// Decompiled by Procyon v0.5.30
// 

public final class JMPopServer_Stub extends RemoteStub implements PopMailServer, Remote
{
    private static Operation[] operations;
    private static final long interfaceHash = 7345710019745669721L;
    
    public JMPopServer_Stub() {
    }
    
    public JMPopServer_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public boolean authenticate(final String s, final String s2) throws RemoteException {
        final int n = 0;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        boolean boolean1;
        try {
            boolean1 = call.getInputStream().readBoolean();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return boolean1;
    }
    
    public void cleanup(final String s, final int[] array, final int n, final String s2) throws RemoteException {
        final int n2 = 1;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(array);
            outputStream.writeInt(n);
            outputStream.writeObject(s2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        ref.done(call);
    }
    
    public void close() throws RemoteException {
        final int n = 2;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new UnexpectedException("Unexpected exception", ex2);
        }
        ref.done(call);
    }
    
    public boolean confirm() throws RemoteException {
        final int n = 3;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new UnexpectedException("Unexpected exception", ex2);
        }
        boolean boolean1;
        try {
            boolean1 = call.getInputStream().readBoolean();
        }
        catch (IOException ex3) {
            throw new UnmarshalException("Error unmarshaling return", ex3);
        }
        catch (Exception ex4) {
            throw new UnexpectedException("Unexpected exception", ex4);
        }
        finally {
            ref.done(call);
        }
        return boolean1;
    }
    
    public void deleteAttachments(final String s, final String s2) throws RemoteException {
        final int n = 4;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        ref.done(call);
    }
    
    public void deleteMessage(final String s, final int n) throws RemoteException {
        final int n2 = 5;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeInt(n);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        ref.done(call);
    }
    
    public String getAttachmentData(final String s, final int n, final int n2) throws RemoteException {
        final int n3 = 6;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n3, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((DataOutput)outputStream).writeInt(n);
            ((DataOutput)outputStream).writeInt(n2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String)outputStream;
    }
    
    public String[] getCachedHeaders(final String s) throws RemoteException {
        final int n = 7;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String[])outputStream;
    }
    
    public String[] getCachedHeaders(final String s, final int[] array) throws RemoteException {
        final int n = 8;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(array);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String[])outputStream;
    }
    
    public String getErrorMessage() throws RemoteException {
        final int n = 9;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new UnexpectedException("Unexpected exception", ex2);
        }
        String s;
        try {
            s = (String)call.getInputStream().readObject();
        }
        catch (IOException ex3) {
            throw new UnmarshalException("Error unmarshaling return", ex3);
        }
        catch (ClassNotFoundException ex4) {
            throw new UnmarshalException("Return value class not found", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return s;
    }
    
    public String getHeader(final String s, final int n) throws RemoteException {
        final int n2 = 10;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((DataOutput)outputStream).writeInt(n);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String)outputStream;
    }
    
    public String[] getHeaderList(final String s) throws RemoteException {
        final int n = 11;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String[])outputStream;
    }
    
    public String[] getHeaderList(final String s, final int[] array) throws RemoteException {
        final int n = 12;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(array);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String[])outputStream;
    }
    
    public JMessage getMessage(final String s, final int n, final String s2, final boolean b) throws RemoteException {
        final int n2 = 13;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((DataOutput)outputStream).writeInt(n);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((DataOutput)outputStream).writeBoolean(b);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (JMessage)outputStream;
    }
    
    public JMessage[] getMessages(final String s, final int[] array, final boolean b) throws RemoteException {
        final int n = 14;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(array);
            ((DataOutput)outputStream).writeBoolean(b);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (JMessage[])outputStream;
    }
    
    public int getNumberCachedHeaders(final String s) throws RemoteException {
        final int n = 15;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            call.getOutputStream().writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        int int1;
        try {
            int1 = call.getInputStream().readInt();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return int1;
    }
    
    public int getNumberOfMessages(final String s) throws RemoteException {
        final int n = 16;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            call.getOutputStream().writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        int int1;
        try {
            int1 = call.getInputStream().readInt();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return int1;
    }
    
    public int getNumberOfNewMessages(final String s) throws RemoteException {
        final int n = 17;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            call.getOutputStream().writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        int int1;
        try {
            int1 = call.getInputStream().readInt();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return int1;
    }
    
    public void logout() throws RemoteException {
        final int n = 18;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new UnexpectedException("Unexpected exception", ex2);
        }
        ref.done(call);
    }
    
    public void rebuildMailbox(final String s) throws RemoteException {
        final int n = 19;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            call.getOutputStream().writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        ref.done(call);
    }
    
    public String[] retrieveMessageIDs(final String s) throws RemoteException {
        final int n = 20;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String[])outputStream;
    }
    
    public void saveHeaders(final String s, final String[] array, final String s2, final int n, final String s3, final String s4) throws RemoteException {
        final int n2 = 21;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(array);
            outputStream.writeObject(s2);
            outputStream.writeInt(n);
            outputStream.writeObject(s3);
            outputStream.writeObject(s4);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        ref.done(call);
    }
    
    public String sendMail(final JSMessage jsMessage, final String s) throws RemoteException {
        final int n = 22;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(jsMessage);
            ((ObjectOutput)outputStream).writeObject(s);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String)outputStream;
    }
    
    public String sendMail(final JSMessage jsMessage, final String s, final int n, final int n2) throws RemoteException {
        final int n3 = 23;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n3, 7345710019745669721L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(jsMessage);
            ((ObjectOutput)outputStream).writeObject(s);
            ((DataOutput)outputStream).writeInt(n);
            ((DataOutput)outputStream).writeInt(n2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        Object outputStream;
        try {
            outputStream = call.getInputStream().readObject();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (ClassNotFoundException ex5) {
            throw new UnmarshalException("Return value class not found", ex5);
        }
        catch (Exception ex6) {
            throw new UnexpectedException("Unexpected exception", ex6);
        }
        finally {
            ref.done(call);
        }
        return (String)outputStream;
    }
    
    public void unbind() throws RemoteException {
        final int n = 24;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n, 7345710019745669721L);
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new UnexpectedException("Unexpected exception", ex2);
        }
        ref.done(call);
    }
    
    public boolean verifyMessage(final String s, final int n, final String s2) throws RemoteException {
        final int n2 = 25;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeInt(n);
            outputStream.writeObject(s2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        boolean boolean1;
        try {
            boolean1 = call.getInputStream().readBoolean();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return boolean1;
    }
    
    public boolean writeAttachments(final String s, final int n, final String s2) throws RemoteException {
        final int n2 = 26;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMPopServer_Stub.operations, n2, 7345710019745669721L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeInt(n);
            outputStream.writeObject(s2);
        }
        catch (IOException ex) {
            throw new MarshalException("Error marshaling arguments", ex);
        }
        try {
            ref.invoke(call);
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new UnexpectedException("Unexpected exception", ex3);
        }
        boolean boolean1;
        try {
            boolean1 = call.getInputStream().readBoolean();
        }
        catch (IOException ex4) {
            throw new UnmarshalException("Error unmarshaling return", ex4);
        }
        catch (Exception ex5) {
            throw new UnexpectedException("Unexpected exception", ex5);
        }
        finally {
            ref.done(call);
        }
        return boolean1;
    }
    
    static {
        JMPopServer_Stub.operations = new Operation[] { new Operation("boolean authenticate(java.lang.String, java.lang.String)"), new Operation("void cleanup(java.lang.String, int[], int, java.lang.String)"), new Operation("void close()"), new Operation("boolean confirm()"), new Operation("void deleteAttachments(java.lang.String, java.lang.String)"), new Operation("void deleteMessage(java.lang.String, int)"), new Operation("java.lang.String getAttachmentData(java.lang.String, int, int)"), new Operation("java.lang.String getCachedHeaders(java.lang.String)[]"), new Operation("java.lang.String getCachedHeaders(java.lang.String, int[])[]"), new Operation("java.lang.String getErrorMessage()"), new Operation("java.lang.String getHeader(java.lang.String, int)"), new Operation("java.lang.String getHeaderList(java.lang.String)[]"), new Operation("java.lang.String getHeaderList(java.lang.String, int[])[]"), new Operation("JMessage getMessage(java.lang.String, int, java.lang.String, boolean)"), new Operation("JMessage getMessages(java.lang.String, int[], boolean)[]"), new Operation("int getNumberCachedHeaders(java.lang.String)"), new Operation("int getNumberOfMessages(java.lang.String)"), new Operation("int getNumberOfNewMessages(java.lang.String)"), new Operation("void logout()"), new Operation("void rebuildMailbox(java.lang.String)"), new Operation("java.lang.String retrieveMessageIDs(java.lang.String)[]"), new Operation("void saveHeaders(java.lang.String, java.lang.String[], java.lang.String, int, java.lang.String, java.lang.String)"), new Operation("java.lang.String sendMail(COM.jscape.mailwidgets.JSMessage, java.lang.String)"), new Operation("java.lang.String sendMail(COM.jscape.mailwidgets.JSMessage, java.lang.String, int, int)"), new Operation("void unbind()"), new Operation("boolean verifyMessage(java.lang.String, int, java.lang.String)"), new Operation("boolean writeAttachments(java.lang.String, int, java.lang.String)") };
    }
}
