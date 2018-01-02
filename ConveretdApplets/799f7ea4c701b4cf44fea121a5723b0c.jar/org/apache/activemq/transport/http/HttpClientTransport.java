// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.http;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.ServiceStopper;
import org.apache.commons.httpclient.methods.HeadMethod;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InterruptedIOException;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.command.ShutdownInfo;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.methods.RequestEntity;
import java.io.InputStream;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.IOException;
import org.apache.activemq.transport.FutureResponse;
import java.net.URI;
import org.apache.activemq.transport.util.TextWireFormat;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.HttpClient;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;

public class HttpClientTransport extends HttpTransportSupport
{
    public static final int MAX_CLIENT_TIMEOUT = 30000;
    private static final Logger LOG;
    private static final IdGenerator CLIENT_ID_GENERATOR;
    private HttpClient sendHttpClient;
    private HttpClient receiveHttpClient;
    private final String clientID;
    private boolean trace;
    private GetMethod httpMethod;
    private volatile int receiveCounter;
    private int soTimeout;
    
    public HttpClientTransport(final TextWireFormat wireFormat, final URI remoteUrl) {
        super(wireFormat, remoteUrl);
        this.clientID = HttpClientTransport.CLIENT_ID_GENERATOR.generateId();
        this.soTimeout = 30000;
    }
    
    public FutureResponse asyncRequest(final Object command) throws IOException {
        return null;
    }
    
    @Override
    public void oneway(final Object command) throws IOException {
        if (this.isStopped()) {
            throw new IOException("stopped.");
        }
        final PostMethod httpMethod = new PostMethod(this.getRemoteUrl().toString());
        this.configureMethod((HttpMethod)httpMethod);
        final String data = this.getTextWireFormat().marshalText(command);
        final byte[] bytes = data.getBytes("UTF-8");
        final InputStreamRequestEntity entity = new InputStreamRequestEntity((InputStream)new ByteArrayInputStream(bytes));
        httpMethod.setRequestEntity((RequestEntity)entity);
        try {
            final HttpClient client = this.getSendHttpClient();
            final HttpClientParams params = new HttpClientParams();
            params.setSoTimeout(this.soTimeout);
            client.setParams(params);
            final int answer = client.executeMethod((HttpMethod)httpMethod);
            if (answer != 200) {
                throw new IOException("Failed to post command: " + command + " as response was: " + answer);
            }
            if (command instanceof ShutdownInfo) {
                try {
                    this.stop();
                }
                catch (Exception e) {
                    HttpClientTransport.LOG.warn("Error trying to stop HTTP client: " + e, e);
                }
            }
        }
        catch (IOException e2) {
            throw IOExceptionSupport.create("Could not post command: " + command + " due to: " + e2, e2);
        }
        finally {
            httpMethod.getResponseBody();
            httpMethod.releaseConnection();
        }
    }
    
    @Override
    public Object request(final Object command) throws IOException {
        return null;
    }
    
    @Override
    public void run() {
        HttpClientTransport.LOG.trace("HTTP GET consumer thread starting: " + this);
        final HttpClient httpClient = this.getReceiveHttpClient();
        final URI remoteUrl = this.getRemoteUrl();
        while (!this.isStopped() && !this.isStopping()) {
            this.configureMethod((HttpMethod)(this.httpMethod = new GetMethod(remoteUrl.toString())));
            try {
                final int answer = httpClient.executeMethod((HttpMethod)this.httpMethod);
                if (answer != 200) {
                    if (answer == 408) {
                        HttpClientTransport.LOG.debug("GET timed out");
                        try {
                            Thread.sleep(1000L);
                            continue;
                        }
                        catch (InterruptedException e2) {
                            this.onException(new InterruptedIOException());
                            break;
                        }
                    }
                    this.onException(new IOException("Failed to perform GET on: " + remoteUrl + " as response was: " + answer));
                    break;
                }
                ++this.receiveCounter;
                final DataInputStream stream = new DataInputStream(this.httpMethod.getResponseBodyAsStream());
                final Object command = this.getTextWireFormat().unmarshal(stream);
                if (command == null) {
                    HttpClientTransport.LOG.debug("Received null command from url: " + remoteUrl);
                }
                else {
                    this.doConsume(command);
                }
            }
            catch (IOException e) {
                this.onException(IOExceptionSupport.create("Failed to perform GET on: " + remoteUrl + " Reason: " + e.getMessage(), e));
                break;
            }
            finally {
                this.httpMethod.releaseConnection();
            }
        }
    }
    
    public HttpClient getSendHttpClient() {
        if (this.sendHttpClient == null) {
            this.sendHttpClient = this.createHttpClient();
        }
        return this.sendHttpClient;
    }
    
    public void setSendHttpClient(final HttpClient sendHttpClient) {
        this.sendHttpClient = sendHttpClient;
    }
    
    public HttpClient getReceiveHttpClient() {
        if (this.receiveHttpClient == null) {
            this.receiveHttpClient = this.createHttpClient();
        }
        return this.receiveHttpClient;
    }
    
    public void setReceiveHttpClient(final HttpClient receiveHttpClient) {
        this.receiveHttpClient = receiveHttpClient;
    }
    
    @Override
    protected void doStart() throws Exception {
        HttpClientTransport.LOG.trace("HTTP GET consumer thread starting: " + this);
        final HttpClient httpClient = this.getReceiveHttpClient();
        final URI remoteUrl = this.getRemoteUrl();
        final HeadMethod httpMethod = new HeadMethod(remoteUrl.toString());
        this.configureMethod((HttpMethod)httpMethod);
        final int answer = httpClient.executeMethod((HttpMethod)httpMethod);
        if (answer != 200) {
            throw new IOException("Failed to perform GET on: " + remoteUrl + " as response was: " + answer);
        }
        super.doStart();
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) throws Exception {
        if (this.httpMethod != null) {
            this.httpMethod.abort();
        }
    }
    
    protected HttpClient createHttpClient() {
        final HttpClient client = new HttpClient();
        if (this.getProxyHost() != null) {
            client.getHostConfiguration().setProxy(this.getProxyHost(), this.getProxyPort());
        }
        return client;
    }
    
    protected void configureMethod(final HttpMethod method) {
        method.setRequestHeader("clientID", this.clientID);
    }
    
    public boolean isTrace() {
        return this.trace;
    }
    
    public void setTrace(final boolean trace) {
        this.trace = trace;
    }
    
    @Override
    public int getReceiveCounter() {
        return this.receiveCounter;
    }
    
    public int getSoTimeout() {
        return this.soTimeout;
    }
    
    public void setSoTimeout(final int soTimeout) {
        this.soTimeout = soTimeout;
    }
    
    static {
        LOG = LoggerFactory.getLogger(HttpClientTransport.class);
        CLIENT_ID_GENERATOR = new IdGenerator();
    }
}
