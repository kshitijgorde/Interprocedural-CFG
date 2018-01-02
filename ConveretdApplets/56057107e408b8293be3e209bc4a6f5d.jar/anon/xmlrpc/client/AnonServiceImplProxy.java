// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.client;

import java.io.IOException;
import org.apache.xmlrpc.XmlRpcClientLite;
import anon.AnonServiceEventListener;
import java.net.ConnectException;
import anon.AnonChannel;
import anon.infoservice.IMutableProxyInterface;
import anon.terms.TermsAndConditionConfirmation;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import java.util.Vector;
import anon.AnonService;

public class AnonServiceImplProxy implements AnonService
{
    String m_RpcServerHost;
    int m_RpcServerPort;
    int m_ClientID;
    
    public AnonServiceImplProxy(final String rpcServerHost, final int rpcServerPort) throws Exception {
        this.m_RpcServerHost = rpcServerHost;
        this.m_RpcServerPort = rpcServerPort;
        this.m_ClientID = (int)this.doRemote("registerClient", new Vector());
    }
    
    public int initialize(final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        return 0;
    }
    
    public boolean isConnected() {
        return true;
    }
    
    public int setProxy(final IMutableProxyInterface mutableProxyInterface) {
        return -1;
    }
    
    public void shutdown(final boolean b) {
    }
    
    public AnonChannel createChannel(final int n) throws ConnectException {
        try {
            final Vector<Integer> vector = new Vector<Integer>(1);
            vector.addElement(new Integer(this.m_ClientID));
            return new ChannelProxy((int)this.doRemote("createChannel", vector), this);
        }
        catch (Exception ex) {
            throw new ConnectException("Could not connect");
        }
    }
    
    public AnonChannel createChannel(final String s, final int n) throws ConnectException {
        return null;
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListeners() {
    }
    
    private Object doRemote(final String s, final Vector vector) throws IOException {
        try {
            return new XmlRpcClientLite("http://localhost:8889/RPC2").execute("ANONXMLRPC." + s, vector);
        }
        catch (Exception ex) {
            throw new IOException("Error processing XML-RCP: " + s);
        }
    }
    
    protected void send(final int n, final byte[] array, final int n2, final int n3) throws IOException {
        final Vector<Integer> vector = new Vector<Integer>();
        vector.addElement(new Integer(this.m_ClientID));
        vector.addElement(new Integer(n));
        final byte[] array2 = new byte[n3];
        System.arraycopy(array, n2, array2, 0, n3);
        vector.addElement((Integer)(Object)array2);
        this.doRemote("channelOutputStreamWrite", vector);
    }
    
    protected int recv(final int n, final byte[] array, final int n2, int length) throws IOException {
        final Vector<Integer> vector = new Vector<Integer>();
        vector.addElement(new Integer(this.m_ClientID));
        vector.addElement(new Integer(n));
        vector.addElement(new Integer(length));
        final Object doRemote = this.doRemote("channelInputStreamRead", vector);
        if (doRemote instanceof byte[]) {
            length = ((byte[])doRemote).length;
            System.arraycopy(doRemote, 0, array, n2, length);
            return length;
        }
        return -1;
    }
}
