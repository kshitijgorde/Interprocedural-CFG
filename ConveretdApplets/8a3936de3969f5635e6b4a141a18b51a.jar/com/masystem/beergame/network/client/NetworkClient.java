// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.client;

import javax.swing.SwingUtilities;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.future.WriteFuture;
import com.masystem.beergame.network.protocol.Request;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.network.protocol.Response;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.core.future.ConnectFuture;
import java.net.SocketAddress;
import java.util.HashMap;
import com.masystem.beergame.network.RequestListener;
import com.masystem.beergame.network.ResponseListener;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.apache.mina.core.service.IoHandlerAdapter;

public class NetworkClient extends IoHandlerAdapter
{
    private NioSocketConnector connector;
    private IoSession session;
    private final Map<Integer, ResponseListener> responseListeners;
    private RequestListener requestListener;
    
    public NetworkClient() {
        this.responseListeners = new HashMap<Integer, ResponseListener>();
    }
    
    public final ConnectFuture connect(final SocketAddress socketAddress, final ResponseListener responseListener) {
        return this.connect(new NioSocketConnector(), socketAddress, responseListener);
    }
    
    private ConnectFuture connect(NioSocketConnector connect, final SocketAddress socketAddress, final ResponseListener responseListener) {
        if (this.isConnected()) {
            throw new IllegalStateException("Already connected. Disconnect first.");
        }
        this.connector = connect;
        final DefaultIoFilterChainBuilder filterChain;
        (filterChain = connect.getFilterChain()).clear();
        filterChain.addLast("codec", new ProtocolCodecFilter(new ProtocolCodecFactory()));
        connect.setHandler(this);
        connect = (NioSocketConnector)connect.connect(socketAddress);
        try {
            ((ConnectFuture)connect).addListener((IoFutureListener<?>)new IoFutureListener<IoFuture>() {
                @Override
                public final void operationComplete(final IoFuture ioFuture) {
                    if (((ConnectFuture)ioFuture).isConnected()) {
                        NetworkClient.this.setSession(ioFuture.getSession());
                        NetworkClient.access$000(NetworkClient.this, responseListener, new Response(Response.ID_NONE, Command.SESSION_CONNECT, Status.OK, new Object[0]));
                        return;
                    }
                    NetworkClient.access$000(NetworkClient.this, responseListener, new Response(Response.ID_NONE, Command.SESSION_CONNECT, Status.ERROR_CONNECTION_FAILED, new Object[0]));
                }
            });
        }
        catch (RuntimeException ex) {
            respond(responseListener, new Response(Response.ID_NONE, Command.SESSION_CONNECT, Status.ERROR_CONNECTION_FAILED, new Object[] { ex.toString() }));
            ex.printStackTrace();
        }
        return (ConnectFuture)connect;
    }
    
    public final void disconnect(final boolean b) {
        if (this.session != null) {
            this.session.close(true);
        }
        if (this.connector != null) {
            this.connector.dispose();
        }
        if (this.session != null && b) {
            this.session.getCloseFuture().awaitUninterruptibly();
        }
    }
    
    public final void setSession(final IoSession session) {
        this.session = session;
    }
    
    public final boolean isConnected() {
        return this.session != null && this.session.isConnected();
    }
    
    public final WriteFuture request(final Request request) {
        return this.request(request, null);
    }
    
    public final WriteFuture request(final Request request, final ResponseListener responseListener) {
        request.assignId();
        if (responseListener != null) {
            this.responseListeners.put(request.getId(), responseListener);
        }
        if (this.session != null) {
            return this.session.write(request);
        }
        return null;
    }
    
    public final void setRequestListener(final RequestListener requestListener) {
        this.requestListener = requestListener;
    }
    
    @Override
    public final void messageReceived(final IoSession ioSession, final Object o) throws Exception {
        if (o instanceof Response) {
            final Response response = (Response)o;
            respond(this.responseListeners.remove(response.getId()), response);
            return;
        }
        if (o instanceof Request) {
            final Request request = (Request)o;
            if (this.requestListener != null) {
                this.requestListener.onRequest(request);
            }
        }
    }
    
    @Override
    public final void sessionClosed(final IoSession ioSession) throws Exception {
    }
    
    @Override
    public final void exceptionCaught(final IoSession ioSession, final Throwable t) throws Exception {
        Log.warn("NetworkClient.exceptionCaught(): " + t, t);
        if (this.requestListener != null) {
            this.requestListener.onRequest(new Request(Command.EXCEPTION, new Object[] { t }));
        }
    }
    
    private static void respond(final ResponseListener responseListener, final Response response) {
        if (responseListener != null) {
            SwingUtilities.invokeLater(new Responder(responseListener, response));
        }
    }
    
    static /* synthetic */ void access$000(final NetworkClient networkClient, final ResponseListener responseListener, final Response response) {
        respond(responseListener, response);
    }
    
    static class Responder implements Runnable
    {
        private final ResponseListener responseListener;
        private final Response response;
        
        public Responder(final ResponseListener responseListener, final Response response) {
            this.responseListener = responseListener;
            this.response = response;
        }
        
        @Override
        public void run() {
            this.responseListener.onResponse(this.response);
        }
    }
}
