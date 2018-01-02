import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class cm extends Socket
{
    public static final String[] nz;
    public static final String[] ny;
    public String nx;
    
    private cm(final String s, final int n, final String s2, final int n2) throws IOException, UnknownHostException {
        super(s2, n2);
    }
    
    public static cm nq(final String s, final int n, final String s2, final int n2, final String s3) throws IOException, UnknownHostException {
        final cm cm = new cm(s, n, s2, n2);
        try {
            final InputStream inputStream = cm.getInputStream();
            final OutputStream outputStream = cm.getOutputStream();
            final InetAddress byName = InetAddress.getByName(s);
            outputStream.write(4);
            outputStream.write(1);
            outputStream.write(n >>> 8 & 0xFF);
            outputStream.write(n & 0xFF);
            outputStream.write(byName.getAddress());
            outputStream.write(s3.getBytes());
            outputStream.write(0);
            outputStream.flush();
            final int read = inputStream.read();
            if (read == -1) {
                throw new IOException("SOCKS4 server " + s2 + ":" + n2 + " disconnected");
            }
            if (read != 0) {
                throw new IOException("Invalid response from SOCKS4 server (" + read + ") " + s2 + ":" + n2);
            }
            final int read2 = inputStream.read();
            if (read2 != 90) {
                if (read2 > 90 && read2 < 93) {
                    throw new IOException("SOCKS4 server unable to connect, reason: " + cm.ny[read2 - 91]);
                }
                throw new IOException("SOCKS4 server unable to connect, reason: " + read2);
            }
            else {
                final byte[] array = new byte[6];
                if (inputStream.read(array, 0, 6) != 6) {
                    throw new IOException("SOCKS4 error reading destination address/port");
                }
                cm.nx = String.valueOf(array[2]) + "." + array[3] + "." + array[4] + "." + array[5] + ":" + (array[0] << 8 | array[1]);
            }
        }
        catch (SocketException ex) {
            throw new SocketException("Error communicating with SOCKS4 server " + s2 + ":" + n2 + ", " + ex.getMessage());
        }
        return cm;
    }
    
    public static cm np(final String s, final int n, final String s2, final int n2, final boolean b, final as as) throws IOException, UnknownHostException {
        final cm cm = new cm(s, n, s2, n2);
        try {
            final InputStream inputStream = cm.getInputStream();
            final OutputStream outputStream = cm.getOutputStream();
            final byte[] array = { 5, 2, 0, 2 };
            final byte[] array2 = new byte[2];
            outputStream.write(array);
            outputStream.flush();
            final int read = inputStream.read();
            if (read == -1) {
                throw new IOException("SOCKS5 server " + s2 + ":" + n2 + " disconnected");
            }
            if (read != 5) {
                throw new IOException("Invalid response from SOCKS5 server (" + read + ") " + s2 + ":" + n2);
            }
            switch (inputStream.read()) {
                default: {
                    throw new IOException("SOCKS5 server does not support our authentication methods");
                }
                case 2: {
                    no(inputStream, outputStream, as, s2, n2);
                }
                case 0: {
                    if (b) {
                        InetAddress byName;
                        try {
                            byName = InetAddress.getByName(s);
                        }
                        catch (UnknownHostException ex2) {
                            throw new IOException("Can't do local lookup on: " + s + ", try socks5 without local lookup");
                        }
                        outputStream.write(new byte[] { 5, 1, 0, 1 });
                        outputStream.write(byName.getAddress());
                    }
                    else {
                        outputStream.write(new byte[] { 5, 1, 0, 3 });
                        outputStream.write(s.length());
                        outputStream.write(s.getBytes());
                    }
                    outputStream.write(n >>> 8 & 0xFF);
                    outputStream.write(n & 0xFF);
                    outputStream.flush();
                    final int read2 = inputStream.read();
                    if (read2 != 5) {
                        throw new IOException("Invalid response from SOCKS5 server (" + read2 + ") " + s2 + ":" + n2);
                    }
                    final int read3 = inputStream.read();
                    if (read3 != 0) {
                        if (read3 > 0 && read3 < 9) {
                            throw new IOException("SOCKS5 server unable to connect, reason: " + cm.nz[read3]);
                        }
                        throw new IOException("SOCKS5 server unable to connect, reason: " + read3);
                    }
                    else {
                        inputStream.read();
                        final int read4 = inputStream.read();
                        final byte[] array3 = new byte[255];
                        switch (read4) {
                            case 1: {
                                if (inputStream.read(array3, 0, 4) != 4) {
                                    throw new IOException("SOCKS5 error reading address");
                                }
                                cm.nx = String.valueOf(array3[0]) + "." + array3[1] + "." + array3[2] + "." + array3[3];
                                break;
                            }
                            case 3: {
                                final int read5 = inputStream.read();
                                if (inputStream.read(array3, 0, read5) != read5) {
                                    throw new IOException("SOCKS5 error reading address");
                                }
                                cm.nx = new String(array3);
                                break;
                            }
                            default: {
                                throw new IOException("SOCKS5 gave unsupported address type: " + read4);
                            }
                        }
                        if (inputStream.read(array3, 0, 2) != 2) {
                            throw new IOException("SOCKS5 error reading port");
                        }
                        final cm cm2 = cm;
                        cm2.nx = String.valueOf(cm2.nx) + ":" + (array3[0] << 8 | array3[1]);
                        break;
                    }
                    break;
                }
            }
        }
        catch (SocketException ex) {
            throw new SocketException("Error communicating with SOCKS5 server " + s2 + ":" + n2 + ", " + ex.getMessage());
        }
        return cm;
    }
    
    public static void no(final InputStream inputStream, final OutputStream outputStream, final as as, final String s, final int n) throws IOException {
        final String ge = as.ge("SOCKS5", null);
        final String gd = as.gd("SOCKS5", null);
        outputStream.write(1);
        outputStream.write(ge.length());
        outputStream.write(ge.getBytes());
        outputStream.write(gd.length());
        outputStream.write(gd.getBytes());
        final int read = inputStream.read();
        if (read != 5) {
            throw new IOException("Invalid response from SOCKS5 server (" + read + ") " + s + ":" + n);
        }
        if (inputStream.read() != 0) {
            throw new IOException("Invalid username/password for SOCKS5 server");
        }
    }
    
    public final String toString() {
        return "SocksProxySocket[addr=" + this.getInetAddress() + ",port=" + this.getPort() + ",localport=" + this.getLocalPort() + "]";
    }
    
    static {
        nz = new String[] { "Success", "General SOCKS server failure", "Connection not allowed by ruleset", "Network unreachable", "Host unreachable", "Connection refused", "TTL expired", "Command not supported", "Address type not supported" };
        ny = new String[] { "Request rejected or failed", "SOCKS server cannot connect to identd on the client", "The client program and identd report different user-ids" };
    }
}
