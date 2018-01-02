// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.DataInput;
import java.io.InputStream;
import java.io.DataInputStream;
import org.apache.activemq.util.ByteArrayInputStream;
import java.io.IOException;
import java.io.DataOutput;
import java.io.OutputStream;
import java.io.DataOutputStream;
import org.apache.activemq.util.ByteArrayOutputStream;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.wireformat.WireFormat;

public class StompWireFormat implements WireFormat
{
    private static final byte[] NO_DATA;
    private static final byte[] END_OF_FRAME;
    private static final int MAX_COMMAND_LENGTH = 1024;
    private static final int MAX_HEADER_LENGTH = 10240;
    private static final int MAX_HEADERS = 1000;
    private static final int MAX_DATA_LENGTH = 104857600;
    private int version;
    
    public StompWireFormat() {
        this.version = 1;
    }
    
    @Override
    public ByteSequence marshal(final Object command) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        this.marshal(command, dos);
        dos.close();
        return baos.toByteSequence();
    }
    
    @Override
    public Object unmarshal(final ByteSequence packet) throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(packet);
        final DataInputStream dis = new DataInputStream(stream);
        return this.unmarshal(dis);
    }
    
    @Override
    public void marshal(final Object command, final DataOutput os) throws IOException {
        final StompFrame stomp = (StompFrame)command;
        final StringBuffer buffer = new StringBuffer();
        buffer.append(stomp.getAction());
        buffer.append("\n");
        for (final Map.Entry entry : stomp.getHeaders().entrySet()) {
            buffer.append(entry.getKey());
            buffer.append(":");
            buffer.append(entry.getValue());
            buffer.append("\n");
        }
        buffer.append("\n");
        os.write(buffer.toString().getBytes("UTF-8"));
        os.write(stomp.getContent());
        os.write(StompWireFormat.END_OF_FRAME);
    }
    
    @Override
    public Object unmarshal(final DataInput in) throws IOException {
        try {
            final String action = this.parseAction(in);
            final HashMap<String, String> headers = this.parseHeaders(in);
            byte[] data = StompWireFormat.NO_DATA;
            final String contentLength = headers.get("content-length");
            if (contentLength != null) {
                final int length = this.parseContentLength(contentLength);
                data = new byte[length];
                in.readFully(data);
                if (in.readByte() != 0) {
                    throw new ProtocolException("content-length bytes were read and there was no trailing null byte", true);
                }
            }
            else {
                ByteArrayOutputStream baos = null;
                byte b;
                while ((b = in.readByte()) != 0) {
                    if (baos == null) {
                        baos = new ByteArrayOutputStream();
                    }
                    else if (baos.size() > 104857600) {
                        throw new ProtocolException("The maximum data length was exceeded", true);
                    }
                    baos.write(b);
                }
                if (baos != null) {
                    baos.close();
                    data = baos.toByteArray();
                }
            }
            return new StompFrame(action, headers, data);
        }
        catch (ProtocolException e) {
            return new StompFrameError(e);
        }
    }
    
    private String readLine(final DataInput in, final int maxLength, final String errorMessage) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(maxLength);
        byte b;
        while ((b = in.readByte()) != 10) {
            if (baos.size() > maxLength) {
                throw new ProtocolException(errorMessage, true);
            }
            baos.write(b);
        }
        baos.close();
        final ByteSequence sequence = baos.toByteSequence();
        return new String(sequence.getData(), sequence.getOffset(), sequence.getLength(), "UTF-8");
    }
    
    protected String parseAction(final DataInput in) throws IOException {
        String action = null;
        do {
            action = this.readLine(in, 1024, "The maximum command length was exceeded");
            if (action == null) {
                throw new IOException("connection was closed");
            }
            action = action.trim();
        } while (action.length() <= 0);
        return action;
    }
    
    protected HashMap<String, String> parseHeaders(final DataInput in) throws IOException {
        final HashMap<String, String> headers = new HashMap<String, String>(25);
        while (true) {
            final String line = this.readLine(in, 10240, "The maximum header length was exceeded");
            if (line == null || line.trim().length() <= 0) {
                return headers;
            }
            if (headers.size() > 1000) {
                throw new ProtocolException("The maximum number of headers was exceeded", true);
            }
            try {
                final int seperatorIndex = line.indexOf(":");
                final String name = line.substring(0, seperatorIndex).trim();
                final String value = line.substring(seperatorIndex + 1, line.length()).trim();
                headers.put(name, value);
            }
            catch (Exception e) {
                throw new ProtocolException("Unable to parser header line [" + line + "]", true);
            }
        }
    }
    
    protected int parseContentLength(final String contentLength) throws ProtocolException {
        int length;
        try {
            length = Integer.parseInt(contentLength.trim());
        }
        catch (NumberFormatException e) {
            throw new ProtocolException("Specified content-length is not a valid integer", true);
        }
        if (length > 104857600) {
            throw new ProtocolException("The maximum data length was exceeded", true);
        }
        return length;
    }
    
    @Override
    public int getVersion() {
        return this.version;
    }
    
    @Override
    public void setVersion(final int version) {
        this.version = version;
    }
    
    static {
        NO_DATA = new byte[0];
        END_OF_FRAME = new byte[] { 0, 10 };
    }
}
