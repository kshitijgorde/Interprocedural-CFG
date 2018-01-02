import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HttpGetReader
{
    private Applet ptv;
    private String host;
    private int port;
    
    public HttpGetReader(final Applet ptv) {
        this.ptv = ptv;
        this.port = ptv.getCodeBase().getPort();
        if (this.port < 0) {
            this.port = 80;
        }
        this.host = ptv.getCodeBase().getHost();
    }
    
    private int getStatusCode(final String s) {
        return Integer.valueOf(s.substring(8).trim().substring(0, 3));
    }
    
    public byte[] doPartialGet(final String s, final int n, final int n2) {
        try {
            final Socket socket = new Socket(this.host, this.port);
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            new DataOutputStream(socket.getOutputStream()).writeBytes(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer("GET ").append(new URL(this.ptv.getDocumentBase(), s).getFile()).append(" HTTP/1.1\r\n").toString())).append("User-Agent: PTViewer\r\n").toString())).append(new StringBuffer("Range: bytes=").append(n).append("-").append(n + n2 - 1).toString()).append("\r\n").toString())).append("Connection: close\r\n").toString()) + "Host: " + this.host + ":" + this.port + "\r\n\r\n");
            int intValue = -1;
            int i = 100;
            while (i == 100) {
                i = -1;
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    if (line.length() == 0) {
                        break;
                    }
                    if (i == -1) {
                        i = this.getStatusCode(line);
                    }
                    if (!line.toLowerCase().startsWith("content-length:")) {
                        continue;
                    }
                    try {
                        intValue = Integer.valueOf(line.substring(15).trim());
                    }
                    catch (Exception ex2) {}
                }
            }
            if (i != 206) {
                System.out.println("PTViewer: unexpected response code: " + i);
                return null;
            }
            if (intValue != n2) {
                System.out.println("PTViewer: number of returned bytes does not match. Requested: " + n2 + "   Returned in header: " + intValue);
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream;
            if (intValue < 0) {
                byteArrayOutputStream = new ByteArrayOutputStream();
            }
            else {
                byteArrayOutputStream = new ByteArrayOutputStream(intValue);
            }
            final byte[] array = new byte[4096];
            int read;
            while ((read = dataInputStream.read(array)) >= 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final int length = byteArray.length;
            if (length != n2) {
                System.out.println("PTViewer: number of returned bytes does not match. Requested: " + n2 + "   Returned in body: " + length);
                return null;
            }
            return byteArray;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
