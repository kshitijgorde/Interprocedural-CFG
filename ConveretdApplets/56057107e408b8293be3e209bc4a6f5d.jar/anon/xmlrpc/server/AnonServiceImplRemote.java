// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.server;

import java.io.OutputStream;
import java.io.InputStream;
import anon.AnonChannel;
import anon.shared.AbstractChannel;
import java.util.Vector;
import org.apache.xmlrpc.WebServer;
import anon.AnonService;
import org.apache.xmlrpc.XmlRpcHandler;

public class AnonServiceImplRemote implements XmlRpcHandler
{
    private AnonService m_AnonService;
    private WebServer m_RpcServer;
    private ClientList m_ClientList;
    
    public AnonServiceImplRemote(final AnonService anonService) {
        this.m_AnonService = anonService;
        this.m_ClientList = new ClientList();
    }
    
    public int startService() {
        try {
            (this.m_RpcServer = new WebServer(8889)).addHandler("ANONXMLRPC", this);
            this.m_RpcServer.start();
            return 0;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public int stopService() {
        return 0;
    }
    
    public Object execute(final String s, final Vector vector) throws Exception {
        if (s.equals("registerClient")) {
            return this.doRegisterClient(vector);
        }
        if (s.equals("createChannel")) {
            return this.doCreateChannel(vector);
        }
        if (s.equals("channelInputStreamRead")) {
            return this.doChannelInputStreamRead(vector);
        }
        if (s.equals("channelOutputStreamWrite")) {
            return this.doChannelOutputStreamWrite(vector);
        }
        throw new Exception("Unknown Method");
    }
    
    private Object doRegisterClient(final Vector vector) throws Exception {
        return new Integer(this.m_ClientList.addNewClient());
    }
    
    private Object doCreateChannel(final Vector vector) throws Exception {
        final ClientEntry client = this.m_ClientList.getClient(vector.elementAt(0));
        final AnonChannel channel = this.m_AnonService.createChannel(0);
        client.addChannel(channel);
        return new Integer(((AbstractChannel)channel).hashCode());
    }
    
    private Object doChannelInputStreamRead(final Vector vector) throws Exception {
        final InputStream inputStream = this.m_ClientList.getClient(vector.elementAt(0)).getChannel(vector.elementAt(1)).getInputStream();
        final byte[] array = new byte[(int)vector.elementAt(2)];
        final int read = inputStream.read(array);
        if (read < 0) {
            return new Integer(-1);
        }
        final byte[] array2 = new byte[read];
        System.arraycopy(array, 0, array2, 0, read);
        return array2;
    }
    
    private Object doChannelOutputStreamWrite(final Vector vector) throws Exception {
        final OutputStream outputStream = this.m_ClientList.getClient(vector.elementAt(0)).getChannel(vector.elementAt(1)).getOutputStream();
        outputStream.write((byte[])(Object)vector.elementAt(2));
        outputStream.flush();
        return new Integer(0);
    }
}
