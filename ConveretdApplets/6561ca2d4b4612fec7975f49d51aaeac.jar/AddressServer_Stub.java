import java.rmi.UnmarshalException;
import java.io.ObjectOutput;
import java.rmi.server.RemoteCall;
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

public final class AddressServer_Stub extends RemoteStub implements AddressBookServer, Remote
{
    private static Operation[] operations;
    private static final long interfaceHash = 4356316244344889932L;
    
    public AddressServer_Stub() {
    }
    
    public AddressServer_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public void add(final String s, final String s2, final String s3, final String s4) throws RemoteException {
        final int n = 0;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, AddressServer_Stub.operations, n, 4356316244344889932L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
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
    
    public String[] getAddresses(final String s) throws RemoteException {
        final int n = 1;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, AddressServer_Stub.operations, n, 4356316244344889932L);
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
    
    public void remove(final String s, final String s2, final String s3, final String s4) throws RemoteException {
        final int n = 2;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, AddressServer_Stub.operations, n, 4356316244344889932L);
        try {
            final ObjectOutput outputStream = call.getOutputStream();
            outputStream.writeObject(s);
            outputStream.writeObject(s2);
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
    
    static {
        AddressServer_Stub.operations = new Operation[] { new Operation("void add(java.lang.String, java.lang.String, java.lang.String, java.lang.String)"), new Operation("java.lang.String getAddresses(java.lang.String)[]"), new Operation("void remove(java.lang.String, java.lang.String, java.lang.String, java.lang.String)") };
    }
}
