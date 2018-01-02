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

public final class JMBroker_Stub extends RemoteStub implements BrokerServer, Remote
{
    private static Operation[] operations;
    private static final long interfaceHash = 7366998803203272459L;
    
    public JMBroker_Stub() {
    }
    
    public JMBroker_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public String authenticateImap(final String s, final String s2) throws RemoteException {
        final int n = 0;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
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
    
    public String authenticateImap(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 1;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(s3);
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
    
    public String authenticatePop(final String s, final String s2) throws RemoteException {
        final int n = 2;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
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
    
    public String authenticatePop(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 3;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(s3);
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
    
    public boolean confirm() throws RemoteException {
        final int n = 4;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
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
    
    public String getImapServer(final String s, final String s2) throws RemoteException {
        final int n = 5;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
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
    
    public String getImapServer(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 6;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(s3);
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
    
    public int getNumberOfMessagesImap(final String s, final String s2) throws RemoteException {
        final int n = 7;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
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
    
    public int getNumberOfMessagesImap(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 8;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
            outputStream.writeObject(s3);
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
    
    public int getNumberOfMessagesPop(final String s, final String s2) throws RemoteException {
        final int n = 9;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
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
    
    public int getNumberOfMessagesPop(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 10;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
            outputStream.writeObject(s3);
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
    
    public int getNumberOfNewMessages(final String s, final String s2) throws RemoteException {
        final int n = 11;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
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
    
    public String getPopServer(final String s, final String s2) throws RemoteException {
        final int n = 12;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
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
    
    public String getPopServer(final String s, final String s2, final String s3) throws RemoteException {
        final int n = 13;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(s3);
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
    
    public void removeServer(final String s) throws RemoteException {
        final int n = 14;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, JMBroker_Stub.operations, n, 7366998803203272459L);
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
    
    static {
        JMBroker_Stub.operations = new Operation[] { new Operation("java.lang.String authenticateImap(java.lang.String, java.lang.String)"), new Operation("java.lang.String authenticateImap(java.lang.String, java.lang.String, java.lang.String)"), new Operation("java.lang.String authenticatePop(java.lang.String, java.lang.String)"), new Operation("java.lang.String authenticatePop(java.lang.String, java.lang.String, java.lang.String)"), new Operation("boolean confirm()"), new Operation("java.lang.String getImapServer(java.lang.String, java.lang.String)"), new Operation("java.lang.String getImapServer(java.lang.String, java.lang.String, java.lang.String)"), new Operation("int getNumberOfMessagesImap(java.lang.String, java.lang.String)"), new Operation("int getNumberOfMessagesImap(java.lang.String, java.lang.String, java.lang.String)"), new Operation("int getNumberOfMessagesPop(java.lang.String, java.lang.String)"), new Operation("int getNumberOfMessagesPop(java.lang.String, java.lang.String, java.lang.String)"), new Operation("int getNumberOfNewMessages(java.lang.String, java.lang.String)"), new Operation("java.lang.String getPopServer(java.lang.String, java.lang.String)"), new Operation("java.lang.String getPopServer(java.lang.String, java.lang.String, java.lang.String)"), new Operation("void removeServer(java.lang.String)") };
    }
}
