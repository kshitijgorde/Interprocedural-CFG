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

public final class PropertiesServerImpl_Stub extends RemoteStub implements PropertiesServer, Remote
{
    private static Operation[] operations;
    private static final long interfaceHash = -4090380515121132621L;
    
    public PropertiesServerImpl_Stub() {
    }
    
    public PropertiesServerImpl_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public JMUserData getUserProps(final String s) throws RemoteException {
        final int n = 0;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PropertiesServerImpl_Stub.operations, n, -4090380515121132621L);
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
        return (JMUserData)outputStream;
    }
    
    public void saveProps(final String s, final String[] array) throws RemoteException {
        final int n = 1;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PropertiesServerImpl_Stub.operations, n, -4090380515121132621L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(array);
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
    
    public void saveProps(final String s, final String[] array, final String s2) throws RemoteException {
        final int n = 2;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PropertiesServerImpl_Stub.operations, n, -4090380515121132621L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(array);
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
    
    static {
        PropertiesServerImpl_Stub.operations = new Operation[] { new Operation("JMUserData getUserProps(java.lang.String)"), new Operation("void saveProps(java.lang.String, java.lang.String[])"), new Operation("void saveProps(java.lang.String, java.lang.String[], java.lang.String)") };
    }
}
