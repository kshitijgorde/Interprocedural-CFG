// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.UnknownHostException;
import java.io.ByteArrayOutputStream;
import java.net.SocketException;
import java.net.InetAddress;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;

public class SocksClient implements GlobalConstants
{
    private String socks_host;
    private int socks_port;
    private int socks_version;
    private static final byte CONNECT = 1;
    private static final byte BIND = 2;
    private static final byte UDP_ASS = 3;
    private static final byte NO_AUTH = 0;
    private static final byte GSSAPI = 1;
    private static final byte USERPWD = 2;
    private static final byte NO_ACC = -1;
    private static final byte IP_V4 = 1;
    private static final byte DMNAME = 3;
    private static final byte IP_V6 = 4;
    private boolean v4A;
    private byte[] user;
    
    public SocksClient(final String socks_host, final int socks_port) {
        this.v4A = false;
        this.user = null;
        this.socks_host = socks_host;
        this.socks_port = socks_port;
        this.socks_version = -1;
    }
    
    SocksClient(final String socks_host, final int socks_port, final int socks_version) throws SocksException {
        this.v4A = false;
        this.user = null;
        this.socks_host = socks_host;
        this.socks_port = socks_port;
        if (socks_version != 4 && socks_version != 5) {
            throw new SocksException("SOCKS Version not supported: " + socks_version);
        }
        this.socks_version = socks_version;
    }
    
    public Socket getSocket(final String s, final int n) throws IOException {
        Socket socket = null;
        try {
            if (GlobalConstants.DebugSocks) {
                Util.logLine("Socks: contacting server on " + this.socks_host + ":" + this.socks_port);
            }
            socket = connect(this.socks_host, this.socks_port);
            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();
            switch (this.socks_version) {
                case 4: {
                    this.v4ProtExchg(inputStream, outputStream, s, n);
                    break;
                }
                case 5: {
                    this.v5ProtExchg(inputStream, outputStream, s, n);
                    break;
                }
                case -1: {
                    try {
                        this.v4ProtExchg(inputStream, outputStream, s, n);
                        this.socks_version = 4;
                    }
                    catch (SocksException ex) {
                        if (GlobalConstants.DebugSocks) {
                            Util.logLine("Socks: V4 request failed: " + ex.getMessage());
                        }
                        socket.close();
                        socket = connect(this.socks_host, this.socks_port);
                        this.v5ProtExchg(socket.getInputStream(), socket.getOutputStream(), s, n);
                        this.socks_version = 5;
                    }
                    break;
                }
                default: {
                    throw new Error("SocksClient internal error: unknown version " + this.socks_version);
                }
            }
            if (GlobalConstants.DebugSocks) {
                Util.logLine("Socks: connection established.");
            }
            return socket;
        }
        catch (IOException ex2) {
            if (socket != null) {
                try {
                    socket.close();
                }
                catch (IOException ex3) {}
            }
            throw ex2;
        }
    }
    
    private static final Socket connect(final String s, final int n) throws IOException {
        final InetAddress[] allByName = InetAddress.getAllByName(s);
        int i = 0;
        while (i < allByName.length) {
            try {
                return new Socket(allByName[i], n);
            }
            catch (SocketException ex) {
                if (i >= allByName.length - 1) {
                    throw ex;
                }
                ++i;
            }
        }
        return null;
    }
    
    private void v4ProtExchg(final InputStream inputStream, final OutputStream outputStream, final String s, final int n) throws SocksException, IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Beginning V4 Protocol Exchange for host " + s + ":" + n);
        }
        byte[] address = { 0, 0, 0, 42 };
        if (!this.v4A) {
            try {
                address = InetAddress.getByName(s).getAddress();
            }
            catch (UnknownHostException ex) {
                this.v4A = true;
            }
            catch (SecurityException ex2) {
                this.v4A = true;
            }
            if (GlobalConstants.DebugSocks && this.v4A) {
                Util.logLine("Socks: Switching to version 4A");
            }
        }
        if (this.user == null) {
            String property;
            try {
                property = System.getProperty("user.name", "");
            }
            catch (SecurityException ex3) {
                property = "";
            }
            this.user = new byte[property.length() + 1];
            System.arraycopy(property.getBytes(), 0, this.user, 0, property.length());
            this.user[property.length()] = 0;
        }
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Sending connect request for user " + new String(this.user));
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(4);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(n >> 8 & 0xFF);
        byteArrayOutputStream.write(n & 0xFF);
        byteArrayOutputStream.write(address, 0, address.length);
        byteArrayOutputStream.write(this.user, 0, this.user.length);
        if (this.v4A) {
            final byte[] bytes = s.getBytes();
            byteArrayOutputStream.write(bytes, 0, bytes.length);
            byteArrayOutputStream.write(0);
        }
        byteArrayOutputStream.writeTo(outputStream);
        final int read = inputStream.read();
        if (read == -1) {
            throw new SocksException("Connection refused by server");
        }
        if (read == 4) {
            if (GlobalConstants.DebugSocks) {
                Util.logLine("Socks: Warning: received version 4 instead of 0");
            }
            else if (read != 0) {
                throw new SocksException("Received invalid version: " + read + "; expected: 0");
            }
        }
        final int read2 = inputStream.read();
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Received response; version: " + read + "; status: " + read2);
        }
        switch (read2) {
            case 90: {
                final byte[] array = new byte[6];
                int read3;
                for (int n2 = 0; n2 < array.length && (read3 = inputStream.read(array, 0, array.length - n2)) != -1; n2 += read3) {}
            }
            case 91: {
                throw new SocksException("Connection request rejected");
            }
            case 92: {
                throw new SocksException("Connection request rejected: can't connect to identd");
            }
            case 93: {
                throw new SocksException("Connection request rejected: identd reports different user-id from " + new String(this.user));
            }
            default: {
                throw new SocksException("Connection request rejected: unknown error " + read2);
            }
        }
    }
    
    private void v5ProtExchg(final InputStream inputStream, final OutputStream outputStream, final String s, final int n) throws SocksException, IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Beginning V5 Protocol Exchange for host " + s + ":" + n);
        }
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Sending authentication request; methods No-Authentication, Username/Password");
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(5);
        byteArrayOutputStream.write(2);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(2);
        byteArrayOutputStream.writeTo(outputStream);
        final int read = inputStream.read();
        if (read == -1) {
            throw new SocksException("Connection refused by server");
        }
        if (read != 5) {
            throw new SocksException("Received invalid version: " + read + "; expected: 5");
        }
        final int read2 = inputStream.read();
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Received response; version: " + read + "; method: " + read2);
        }
        switch (read2) {
            case 0: {
                break;
            }
            case 1: {
                this.negotiate_gssapi(inputStream, outputStream);
                break;
            }
            case 2: {
                this.negotiate_userpwd(inputStream, outputStream);
                break;
            }
            case -1: {
                throw new SocksException("Server unwilling to accept any standard authentication methods");
            }
            default: {
                throw new SocksException("Cannot handle authentication method " + read2);
            }
        }
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Sending connect request");
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(5);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(s.length() & 0xFF);
        final byte[] bytes = s.getBytes();
        byteArrayOutputStream.write(bytes, 0, bytes.length);
        byteArrayOutputStream.write(n >> 8 & 0xFF);
        byteArrayOutputStream.write(n & 0xFF);
        byteArrayOutputStream.writeTo(outputStream);
        final int read3 = inputStream.read();
        if (read3 != 5) {
            throw new SocksException("Received invalid version: " + read3 + "; expected: 5");
        }
        final int read4 = inputStream.read();
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Received response; version: " + read3 + "; status: " + read4);
        }
        switch (read4) {
            case 0: {
                inputStream.read();
                final int read5 = inputStream.read();
                int read6 = 0;
                switch (read5) {
                    case 4: {
                        read6 = 16;
                        break;
                    }
                    case 1: {
                        read6 = 4;
                        break;
                    }
                    case 3: {
                        read6 = inputStream.read();
                        break;
                    }
                    default: {
                        throw new SocksException("Invalid address type received from server: " + read5);
                    }
                }
                final byte[] array = new byte[read6 + 2];
                int read7;
                for (int n2 = 0; n2 < array.length && (read7 = inputStream.read(array, 0, array.length - n2)) != -1; n2 += read7) {}
            }
            case 1: {
                throw new SocksException("General SOCKS server failure");
            }
            case 2: {
                throw new SocksException("Connection not allowed");
            }
            case 3: {
                throw new SocksException("Network unreachable");
            }
            case 4: {
                throw new SocksException("Host unreachable");
            }
            case 5: {
                throw new SocksException("Connection refused");
            }
            case 6: {
                throw new SocksException("TTL expired");
            }
            case 7: {
                throw new SocksException("Command not supported");
            }
            case 8: {
                throw new SocksException("Address type not supported");
            }
            default: {
                throw new SocksException("Unknown reply received from server: " + read4);
            }
        }
    }
    
    private void negotiate_gssapi(final InputStream inputStream, final OutputStream outputStream) throws SocksException, IOException {
        throw new SocksException("GSSAPI authentication protocol not implemented");
    }
    
    private void negotiate_userpwd(final InputStream inputStream, final OutputStream outputStream) throws SocksException, IOException {
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Entering authorization subnegotiation; method: Username/Password");
        }
        AuthorizationInfo authorization;
        try {
            authorization = AuthorizationInfo.getAuthorization(this.socks_host, this.socks_port, "SOCKS5", "USER/PASS", false, true);
        }
        catch (AuthSchemeNotImplException ex) {
            authorization = null;
        }
        if (authorization == null) {
            throw new SocksException("No Authorization info for SOCKS found (server requested username/password).");
        }
        final NVPair[] params = authorization.getParams();
        if (params == null || params.length == 0) {
            throw new SocksException("No Username/Password found in authorization info for SOCKS.");
        }
        final String name = params[0].getName();
        final String value = params[0].getValue();
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Sending authorization request for user " + name);
        }
        final byte[] array = new byte[2 + name.length() + 1 + value.length()];
        array[0] = 1;
        Util.getBytes(name, array[1] = (byte)name.length(), array, 2);
        Util.getBytes(value, array[2 + array[1]] = (byte)value.length(), array, 2 + array[1] + 1);
        outputStream.write(array);
        final int read = inputStream.read();
        if (read != 1) {
            throw new SocksException("Wrong version received in username/password subnegotiation response: " + read + "; expected: 1");
        }
        final int read2 = inputStream.read();
        if (read2 != 0) {
            throw new SocksException("Username/Password authentication failed; status: " + read2);
        }
        if (GlobalConstants.DebugSocks) {
            Util.logLine("Socks: Received response; version: " + read + "; status: " + read2);
        }
    }
    
    public String toString() {
        return this.getClass().getName() + "[" + this.socks_host + ":" + this.socks_port + "]";
    }
}
