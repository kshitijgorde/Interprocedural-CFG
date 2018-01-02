import java.io.ObjectOutput;
import java.util.Hashtable;
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

public final class PostOfficeInterface_Stub extends RemoteStub implements MailServerInterface, Remote
{
    private static Operation[] operations;
    private static final long interfaceHash = -3919808369620208655L;
    
    public PostOfficeInterface_Stub() {
    }
    
    public PostOfficeInterface_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public String createAccount(final String s, final String s2, final String s3, final String s4) throws RemoteException {
        final int n = 0;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PostOfficeInterface_Stub.operations, n, -3919808369620208655L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(s3);
            ((ObjectOutput)outputStream).writeObject(s4);
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
    
    public Hashtable getAccountData(final String s, final String s2) throws RemoteException {
        final int n = 1;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PostOfficeInterface_Stub.operations, n, -3919808369620208655L);
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
        return (Hashtable)outputStream;
    }
    
    public String saveAccountData(final String s, final String s2, final Hashtable hashtable) throws RemoteException {
        final int n = 2;
        final RemoteRef ref = super.ref;
        final RemoteCall call = ref.newCall(this, PostOfficeInterface_Stub.operations, n, -3919808369620208655L);
        try {
            final Object outputStream = call.getOutputStream();
            ((ObjectOutput)outputStream).writeObject(s);
            ((ObjectOutput)outputStream).writeObject(s2);
            ((ObjectOutput)outputStream).writeObject(hashtable);
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
    
    static {
        PostOfficeInterface_Stub.operations = new Operation[] { new Operation("java.lang.String createAccount(java.lang.String, java.lang.String, java.lang.String, java.lang.String)"), new Operation("java.util.Hashtable getAccountData(java.lang.String, java.lang.String)"), new Operation("java.lang.String saveAccountData(java.lang.String, java.lang.String, java.util.Hashtable)") };
    }
}
