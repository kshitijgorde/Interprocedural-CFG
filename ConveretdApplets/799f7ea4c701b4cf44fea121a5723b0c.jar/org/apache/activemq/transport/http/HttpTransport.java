// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.http;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.Callback;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.util.ByteSequence;
import java.io.InputStream;
import org.apache.activemq.util.ByteArrayOutputStream;
import java.io.Writer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.command.Command;
import java.net.MalformedURLException;
import java.net.URI;
import org.apache.activemq.transport.util.TextWireFormat;
import java.net.URL;
import java.net.HttpURLConnection;
import org.slf4j.Logger;

@Deprecated
public class HttpTransport extends HttpTransportSupport
{
    private static final Logger LOG;
    private HttpURLConnection sendConnection;
    private HttpURLConnection receiveConnection;
    private URL url;
    private String clientID;
    private volatile int receiveCounter;
    
    public HttpTransport(final TextWireFormat wireFormat, final URI remoteUrl) throws MalformedURLException {
        super(wireFormat, remoteUrl);
        this.url = new URL(remoteUrl.toString());
    }
    
    @Override
    public void oneway(final Object o) throws IOException {
        final Command command = (Command)o;
        try {
            if (command.getDataStructureType() == 3) {
                final boolean startGetThread = this.clientID == null;
                this.clientID = ((ConnectionInfo)command).getClientId();
                if (startGetThread && this.isStarted()) {
                    try {
                        super.doStart();
                    }
                    catch (Exception e) {
                        throw IOExceptionSupport.create(e);
                    }
                }
            }
            final HttpURLConnection connection = this.getSendConnection();
            final String text = this.getTextWireFormat().marshalText(command);
            final Writer writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(text);
            writer.flush();
            final int answer = connection.getResponseCode();
            if (answer != 200) {
                throw new IOException("Failed to post command: " + command + " as response was: " + answer);
            }
        }
        catch (IOException e2) {
            throw IOExceptionSupport.create("Could not post command: " + command + " due to: " + e2, e2);
        }
    }
    
    @Override
    public void run() {
        HttpTransport.LOG.trace("HTTP GET consumer thread starting for transport: " + this);
        final URI remoteUrl = this.getRemoteUrl();
        while (!this.isStopped()) {
            try {
                final HttpURLConnection connection = this.getReceiveConnection();
                final int answer = connection.getResponseCode();
                if (answer != 200) {
                    if (answer == 408) {
                        HttpTransport.LOG.trace("GET timed out");
                    }
                    else {
                        HttpTransport.LOG.warn("Failed to perform GET on: " + remoteUrl + " as response was: " + answer);
                    }
                }
                else {
                    ++this.receiveCounter;
                    final InputStream is = connection.getInputStream();
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream((connection.getContentLength() > 0) ? connection.getContentLength() : 1024);
                    int c = 0;
                    while ((c = is.read()) >= 0) {
                        baos.write(c);
                    }
                    final ByteSequence sequence = baos.toByteSequence();
                    final String data = new String(sequence.data, sequence.offset, sequence.length, "UTF-8");
                    final Command command = (Command)this.getTextWireFormat().unmarshalText(data);
                    if (command == null) {
                        HttpTransport.LOG.warn("Received null packet from url: " + remoteUrl);
                    }
                    else {
                        this.doConsume(command);
                    }
                }
            }
            catch (Throwable e) {
                if (!this.isStopped()) {
                    HttpTransport.LOG.error("Failed to perform GET on: " + remoteUrl + " due to: " + e, e);
                }
                else {
                    HttpTransport.LOG.trace("Caught error after closed: " + e, e);
                }
            }
            finally {
                this.safeClose(this.receiveConnection);
                this.receiveConnection = null;
            }
        }
    }
    
    protected HttpURLConnection createSendConnection() throws IOException {
        final HttpURLConnection conn = (HttpURLConnection)this.getRemoteURL().openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        this.configureConnection(conn);
        conn.connect();
        return conn;
    }
    
    protected HttpURLConnection createReceiveConnection() throws IOException {
        final HttpURLConnection conn = (HttpURLConnection)this.getRemoteURL().openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        this.configureConnection(conn);
        conn.connect();
        return conn;
    }
    
    protected void configureConnection(final HttpURLConnection connection) {
        if (this.clientID != null) {
            connection.setRequestProperty("clientID", this.clientID);
        }
    }
    
    protected URL getRemoteURL() {
        return this.url;
    }
    
    protected HttpURLConnection getSendConnection() throws IOException {
        this.setSendConnection(this.createSendConnection());
        return this.sendConnection;
    }
    
    protected HttpURLConnection getReceiveConnection() throws IOException {
        this.setReceiveConnection(this.createReceiveConnection());
        return this.receiveConnection;
    }
    
    protected void setSendConnection(final HttpURLConnection conn) {
        this.safeClose(this.sendConnection);
        this.sendConnection = conn;
    }
    
    protected void setReceiveConnection(final HttpURLConnection conn) {
        this.safeClose(this.receiveConnection);
        this.receiveConnection = conn;
    }
    
    @Override
    protected void doStart() throws Exception {
        if (this.clientID != null) {
            super.doStart();
        }
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) throws Exception {
        stopper.run(new Callback() {
            @Override
            public void execute() throws Exception {
                HttpTransport.this.safeClose(HttpTransport.this.sendConnection);
            }
        });
        this.sendConnection = null;
        stopper.run(new Callback() {
            @Override
            public void execute() {
                HttpTransport.this.safeClose(HttpTransport.this.receiveConnection);
            }
        });
    }
    
    private void safeClose(final HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }
    
    @Override
    public int getReceiveCounter() {
        return this.receiveCounter;
    }
    
    static {
        LOG = LoggerFactory.getLogger(HttpTransport.class);
    }
}
