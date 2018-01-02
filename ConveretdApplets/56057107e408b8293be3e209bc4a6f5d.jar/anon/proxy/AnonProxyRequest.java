// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import anon.TooMuchDataForPacketException;
import anon.NotConnectedToMixException;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import anon.AnonChannel;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

public final class AnonProxyRequest implements Runnable
{
    private static int ms_nrOfRequests;
    private static final long TIMEOUT_RECONNECT = 60000L;
    private static final int CHUNK_SIZE = 1000;
    private static int ms_currentRequest;
    private InputStream m_InChannel;
    private OutputStream m_OutChannel;
    private InputStream m_InSocket;
    private OutputStream m_OutSocket;
    private Socket m_clientSocket;
    private Thread m_threadResponse;
    private Thread m_threadRequest;
    private AnonChannel m_Channel;
    private AnonProxy m_Proxy;
    private volatile boolean m_bRequestIsAlive;
    private int m_iProtocol;
    private Object m_syncObject;
    private ProxyCallbackHandler m_callbackHandler;
    private String[] contentEncodings;
    private boolean internalEncodingRequired;
    
    AnonProxyRequest(final AnonProxy proxy, final Socket clientSocket, final Object syncObject, final ProxyCallbackHandler callbackHandler) throws IOException {
        this.m_callbackHandler = null;
        this.internalEncodingRequired = false;
        this.m_Proxy = proxy;
        this.m_clientSocket = clientSocket;
        this.m_syncObject = syncObject;
        this.m_clientSocket.setSoTimeout(0);
        this.m_InSocket = clientSocket.getInputStream();
        this.m_OutSocket = clientSocket.getOutputStream();
        this.m_threadRequest = new Thread(this, "JAP - AnonProxy Request " + Integer.toString(AnonProxyRequest.ms_currentRequest));
        ++AnonProxyRequest.ms_currentRequest;
        this.m_callbackHandler = callbackHandler;
        this.m_threadRequest.setDaemon(true);
        this.m_threadRequest.start();
    }
    
    public static int getNrOfRequests() {
        return AnonProxyRequest.ms_nrOfRequests;
    }
    
    public void run() {
        ++AnonProxyRequest.ms_nrOfRequests;
        this.m_bRequestIsAlive = true;
        AnonChannel channel = null;
        int read = 0;
        try {
            read = this.m_InSocket.read();
        }
        catch (InterruptedIOException ex6) {
            try {
                channel = this.m_Proxy.createChannel(2);
                this.m_iProtocol = 0;
                if (channel == null) {
                    this.closeRequest();
                    return;
                }
            }
            catch (Throwable t) {
                LogHolder.log(3, LogType.NET, "AnonProxyRequest - something was wrong with seting up a new SMTP channel -- Exception: " + t);
                this.closeRequest();
                return;
            }
        }
        catch (Throwable t2) {
            this.closeRequest();
            return;
        }
        if (channel == null) {
            read &= 0xFF;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    channel = null;
                    if (read == 4 || read == 5) {
                        channel = this.m_Proxy.createChannel(1);
                        this.m_iProtocol = 0;
                        break;
                    }
                    channel = this.m_Proxy.createChannel(0);
                    this.m_iProtocol = 1;
                    break;
                }
                catch (NotConnectedToMixException ex7) {
                    LogHolder.log(3, LogType.NET, "AnonProxyRequest - Connection to Mix lost");
                    final Thread thread = new Thread(new Runnable() {
                        public void run() {
                            AnonProxyRequest.this.m_Proxy.reconnect();
                        }
                    }, "Request reconnect thread");
                    thread.start();
                    final long currentTimeMillis = System.currentTimeMillis();
                    try {
                        thread.join(60000L);
                    }
                    catch (InterruptedException ex8) {
                        Thread.currentThread().interrupt();
                    }
                    boolean b = true;
                    synchronized (this.m_syncObject) {
                        if (!this.m_Proxy.isConnected() && !Thread.currentThread().isInterrupted()) {
                            final long n = currentTimeMillis + 60000L - System.currentTimeMillis();
                            if (n > 0L) {
                                try {
                                    this.m_syncObject.wait(n);
                                }
                                catch (InterruptedException ex9) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                            }
                        }
                        if (!this.m_Proxy.isConnected()) {
                            b = false;
                        }
                    }
                    if (!b) {
                        LogHolder.log(3, LogType.NET, "Requests terminated due to loss of connection to service!");
                        this.closeRequest();
                        return;
                    }
                    continue;
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.NET, "AnonProxyRequest - something was wrong with seting up a new channel Exception: " + ex);
                    this.closeRequest();
                    return;
                }
            }
            if (channel == null) {
                this.closeRequest();
                return;
            }
        }
        int length = 0;
        if (read != 0) {
            length = 1;
        }
        ProxyCallbackBuffer proxyCallbackBuffer = null;
        byte[] array;
        try {
            this.m_InChannel = channel.getInputStream();
            this.m_OutChannel = channel.getOutputStream();
            this.m_Channel = channel;
            (this.m_threadResponse = new Thread(new Response(), "JAP - AnonProxy Response for " + Thread.currentThread().getName())).start();
            array = new byte[1900];
            array[0] = (byte)read;
        }
        catch (Throwable t3) {
            this.closeRequest();
            return;
        }
        this.m_Proxy.incNumChannels();
        try {
            while (true) {
                int n2;
                try {
                    n2 = this.m_InSocket.read(array, length, Math.min(this.m_Channel.getOutputBlockSize(), 1900) - length) + length;
                }
                catch (InterruptedIOException ex2) {
                    length += ex2.bytesTransferred;
                    continue;
                }
                if (n2 <= 0) {
                    break;
                }
                try {
                    if (this.m_callbackHandler != null) {
                        proxyCallbackBuffer = new ProxyCallbackBuffer(array, 0, n2);
                        try {
                            this.m_callbackHandler.deliverUpstream(this, proxyCallbackBuffer);
                        }
                        catch (ProxyCallbackDelayException ex10) {
                            length = 0;
                            continue;
                        }
                        this.m_OutChannel.write(proxyCallbackBuffer.getChunk(), 0, proxyCallbackBuffer.getPayloadLength());
                    }
                    else {
                        this.m_OutChannel.write(array, 0, n2);
                    }
                    length = 0;
                }
                catch (TooMuchDataForPacketException ex3) {
                    if (this.m_callbackHandler != null) {
                        sendRemainingBytesRecursion(proxyCallbackBuffer, ex3.getBytesSent(), this.m_OutChannel);
                        length = 0;
                    }
                    else {
                        final byte[] array2 = new byte[array.length - ex3.getBytesSent()];
                        System.arraycopy(array, ex3.getBytesSent(), array2, 0, array2.length);
                        System.arraycopy(array2, 0, array, 0, array2.length);
                        length = array2.length;
                    }
                }
                this.m_Proxy.transferredBytes(n2 - length, this.m_iProtocol);
                Thread.yield();
            }
        }
        catch (IOException ex4) {
            LogHolder.log(7, LogType.NET, "Exception in AnonProxyRequest - upstream loop.", ex4);
        }
        catch (ProxyCallbackNotProcessableException ex5) {
            try {
                this.m_OutSocket.write(ex5.getErrorResponse());
            }
            catch (IOException ex11) {}
            LogHolder.log(3, LogType.NET, "chunk could not be processed. Terminating", ex5);
        }
        this.closeRequest();
        this.m_Proxy.decNumChannels();
    }
    
    private static void sendRemainingBytesRecursion(final ProxyCallbackBuffer proxyCallbackBuffer, final int n, final OutputStream outputStream) throws IOException {
        final byte[] chunk = new byte[proxyCallbackBuffer.getPayloadLength() - n];
        System.arraycopy(proxyCallbackBuffer.getChunk(), n, chunk, 0, chunk.length);
        System.arraycopy(chunk, 0, proxyCallbackBuffer.getChunk(), 0, chunk.length);
        try {
            outputStream.write(chunk);
        }
        catch (TooMuchDataForPacketException ex) {
            proxyCallbackBuffer.setChunk(chunk);
            sendRemainingBytesRecursion(proxyCallbackBuffer, ex.getBytesSent(), outputStream);
        }
    }
    
    private synchronized void closeRequest() {
        if (this.m_bRequestIsAlive) {
            --AnonProxyRequest.ms_nrOfRequests;
            this.m_bRequestIsAlive = false;
        }
        try {
            if (this.m_Channel != null) {
                this.m_Channel.close();
            }
        }
        catch (Throwable t) {}
        try {
            this.m_InSocket.close();
        }
        catch (Throwable t2) {}
        try {
            this.m_OutSocket.close();
        }
        catch (Throwable t3) {}
        try {
            this.m_clientSocket.close();
        }
        catch (Throwable t4) {}
        if (this.m_callbackHandler != null) {
            this.m_callbackHandler.closeRequest(this);
        }
    }
    
    public boolean isInternalEncodingRequired() {
        return this.internalEncodingRequired;
    }
    
    public void setInternalEncodingRequired(final boolean internalEncodingRequired) {
        this.internalEncodingRequired = internalEncodingRequired;
    }
    
    public String[] getContentEncodings() {
        return this.contentEncodings;
    }
    
    public void setContentEncodings(final String[] contentEncodings) {
        this.contentEncodings = contentEncodings;
    }
    
    static {
        AnonProxyRequest.ms_nrOfRequests = 0;
    }
    
    final class Response implements Runnable
    {
        public void run() {
            final byte[] array = new byte[2900];
            try {
                int read;
            Label_0039_Outer:
                do {
                    read = AnonProxyRequest.this.m_InChannel.read(array, 0, 1000);
                    if (read <= 0) {
                        break;
                    }
                    int n = 0;
                    while (true) {
                        try {
                            if (AnonProxyRequest.this.m_callbackHandler != null) {
                                final ProxyCallbackBuffer proxyCallbackBuffer = new ProxyCallbackBuffer(array, 0, read);
                                try {
                                    AnonProxyRequest.this.m_callbackHandler.deliverDownstream(AnonProxyRequest.this, proxyCallbackBuffer);
                                }
                                catch (ProxyCallbackDelayException ex3) {
                                    continue Label_0039_Outer;
                                }
                                AnonProxyRequest.this.m_OutSocket.write(proxyCallbackBuffer.getChunk(), 0, proxyCallbackBuffer.getPayloadLength());
                                if (proxyCallbackBuffer.getStatus() == 0) {
                                    break;
                                }
                            }
                            else {
                                AnonProxyRequest.this.m_OutSocket.write(array, 0, read);
                            }
                            AnonProxyRequest.this.m_OutSocket.flush();
                        }
                        catch (InterruptedIOException ex4) {
                            LogHolder.log(0, LogType.NET, "Should never be here: Timeout in sending to Browser!");
                            if (++n > 3) {
                                throw new IOException("Could not send to Browser...");
                            }
                            continue;
                        }
                        break;
                    }
                    AnonProxyRequest.this.m_Proxy.transferredBytes(read, AnonProxyRequest.this.m_iProtocol);
                    Thread.yield();
                } while (read > 0 && !AnonProxyRequest.this.m_Channel.isClosed());
            }
            catch (IOException ex) {
                LogHolder.log(3, LogType.NET, ex);
            }
            catch (ProxyCallbackNotProcessableException ex2) {
                LogHolder.log(3, LogType.NET, ex2);
                try {
                    AnonProxyRequest.this.m_OutSocket.write(ex2.getErrorResponse());
                }
                catch (IOException ex5) {}
            }
            try {
                AnonProxyRequest.this.m_clientSocket.close();
            }
            catch (IOException ex6) {}
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex7) {}
            if (AnonProxyRequest.this.m_bRequestIsAlive) {
                AnonProxyRequest.this.m_threadRequest.interrupt();
            }
        }
    }
}
