// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import org.apache.activemq.util.ServiceStopper;
import java.util.Map;
import java.io.DataInput;
import org.apache.activemq.util.DataByteArrayInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.nio.channels.WritableByteChannel;
import org.apache.activemq.transport.nio.NIOOutputStream;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.transport.nio.SelectorManager;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.URI;
import javax.net.SocketFactory;
import org.apache.activemq.wireformat.WireFormat;
import java.util.HashMap;
import org.apache.activemq.util.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import org.apache.activemq.transport.nio.SelectorSelection;
import java.nio.channels.SocketChannel;
import org.apache.activemq.transport.tcp.TcpTransport;

public class StompNIOTransport extends TcpTransport
{
    private SocketChannel channel;
    private SelectorSelection selection;
    private ByteBuffer inputBuffer;
    ByteArrayOutputStream currentCommand;
    boolean processedHeaders;
    String action;
    HashMap<String, String> headers;
    int contentLength;
    int readLength;
    int previousByte;
    
    public StompNIOTransport(final WireFormat wireFormat, final SocketFactory socketFactory, final URI remoteLocation, final URI localLocation) throws UnknownHostException, IOException {
        super(wireFormat, socketFactory, remoteLocation, localLocation);
        this.currentCommand = new ByteArrayOutputStream();
        this.processedHeaders = false;
        this.contentLength = -1;
        this.readLength = 0;
        this.previousByte = -1;
    }
    
    public StompNIOTransport(final WireFormat wireFormat, final Socket socket) throws IOException {
        super(wireFormat, socket);
        this.currentCommand = new ByteArrayOutputStream();
        this.processedHeaders = false;
        this.contentLength = -1;
        this.readLength = 0;
        this.previousByte = -1;
    }
    
    @Override
    protected void initializeStreams() throws IOException {
        (this.channel = this.socket.getChannel()).configureBlocking(false);
        this.selection = SelectorManager.getInstance().register(this.channel, new SelectorManager.Listener() {
            @Override
            public void onSelect(final SelectorSelection selection) {
                StompNIOTransport.this.serviceRead();
            }
            
            @Override
            public void onError(final SelectorSelection selection, final Throwable error) {
                if (error instanceof IOException) {
                    StompNIOTransport.this.onException((IOException)error);
                }
                else {
                    StompNIOTransport.this.onException(IOExceptionSupport.create(error));
                }
            }
        });
        this.inputBuffer = ByteBuffer.allocate(8192);
        final NIOOutputStream outPutStream = new NIOOutputStream(this.channel, 8192);
        this.dataOut = new DataOutputStream(outPutStream);
        this.buffOut = outPutStream;
    }
    
    private void serviceRead() {
        try {
            while (true) {
                final int readSize = this.channel.read(this.inputBuffer);
                if (readSize == -1) {
                    this.onException(new EOFException());
                    this.selection.close();
                    break;
                }
                if (readSize == 0) {
                    break;
                }
                this.inputBuffer.flip();
                final ByteArrayInputStream input = new ByteArrayInputStream(this.inputBuffer.array());
                int i = 0;
                while (i++ < readSize) {
                    final int b = input.read();
                    if (!this.processedHeaders && this.previousByte == 0 && b == 0) {
                        continue;
                    }
                    if (!this.processedHeaders) {
                        this.currentCommand.write(b);
                        if (this.previousByte == 10 && b == 10) {
                            if (this.wireFormat instanceof StompWireFormat) {
                                final DataByteArrayInputStream data = new DataByteArrayInputStream(this.currentCommand.toByteArray());
                                this.action = ((StompWireFormat)this.wireFormat).parseAction(data);
                                this.headers = ((StompWireFormat)this.wireFormat).parseHeaders(data);
                                final String contentLengthHeader = this.headers.get("content-length");
                                if (contentLengthHeader != null) {
                                    this.contentLength = ((StompWireFormat)this.wireFormat).parseContentLength(contentLengthHeader);
                                }
                                else {
                                    this.contentLength = -1;
                                }
                            }
                            this.processedHeaders = true;
                            this.currentCommand.reset();
                        }
                    }
                    else if (this.contentLength == -1) {
                        if (b == 0) {
                            this.processCommand();
                        }
                        else {
                            this.currentCommand.write(b);
                        }
                    }
                    else if (this.readLength++ == this.contentLength) {
                        this.processCommand();
                        this.readLength = 0;
                    }
                    else {
                        this.currentCommand.write(b);
                    }
                    this.previousByte = b;
                }
                this.inputBuffer.clear();
            }
        }
        catch (IOException e) {
            this.onException(e);
        }
        catch (Throwable e2) {
            this.onException(IOExceptionSupport.create(e2));
        }
    }
    
    private void processCommand() throws Exception {
        final StompFrame frame = new StompFrame(this.action, this.headers, this.currentCommand.toByteArray());
        this.doConsume(frame);
        this.processedHeaders = false;
        this.currentCommand.reset();
        this.contentLength = -1;
    }
    
    @Override
    protected void doStart() throws Exception {
        this.connect();
        this.selection.setInterestOps(1);
        this.selection.enable();
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) throws Exception {
        try {
            this.selection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        super.doStop(stopper);
    }
}
