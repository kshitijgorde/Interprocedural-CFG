// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.FileInputStream;
import java.io.File;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.net.Socket;

public class NFSmtpClient
{
    protected Socket socket;
    protected String host;
    protected int port;
    protected OutputStream os;
    protected PrintStream ps;
    protected InputStream is;
    protected BufferedInputStream bis;
    protected DataInputStream dis;
    protected boolean reuseConnection;
    protected String statusMsg;
    protected static final String MIME_MESSAGE = "This is a multi-part message in MIME format.";
    
    public NFSmtpClient() {
        this.socket = null;
        this.host = "nowhere.com";
        this.port = 25;
        this.os = null;
        this.ps = null;
        this.is = null;
        this.bis = null;
        this.dis = null;
        this.reuseConnection = false;
        this.statusMsg = null;
    }
    
    public void setHost(final String host) {
        if (this.host.equals(host)) {
            return;
        }
        this.close();
        this.host = host;
    }
    
    public void setPort(final int port) {
        if (this.port == port) {
            return;
        }
        this.close();
        this.port = port;
    }
    
    public String breakStringIntoLines(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        int i = 0;
        int n = 2;
        while (i < sb.length()) {
            if (n % 75 == 0) {
                sb.insert(i, "\r\n");
                n = 1;
            }
            else {
                ++n;
            }
            ++i;
        }
        return sb.toString();
    }
    
    public int sendMsgWithAttachment(final String s, final String s2, final String s3, final String s4, final String s5, final Object o, final String s6, final String s7, final Object o2) throws Exception {
        return this.sendMsgWithAttachment(s, s2, s3, s4, s5, o, s6, s7, null, null, o2);
    }
    
    public int sendMsgWithAttachment(final String s, final String s2, final String s3, final String s4, final String s5, final Object o, final String s6, final String s7, final String s8, final String s9, final Object o2) throws Exception {
        if (s4 != null && s5 != null && s6 != null && s7 != null && o2 != null) {
            return this.a(true, s, s2, s3, s4, s5, o, "------------BOUNDARYFORATTACHMENT", s6, s7, s8, s9, o2);
        }
        throw new Exception("Could not send message with attachment with null parameter.");
    }
    
    public int sendMsg(final String s, final String s2, final String s3, final Object o) throws Exception {
        return this.a(false, s, s2, s3, null, null, o, null, null, null, null, null, null);
    }
    
    private int a(final boolean b, final String s, final String s2, final String s3, final String s4, final String s5, Object breakStringIntoLines, final String s6, final String s7, final String s8, final String s9, final String s10, Object breakStringIntoLines2) throws Exception {
        if (breakStringIntoLines != null && breakStringIntoLines instanceof String && ((String)breakStringIntoLines).indexOf("\r\n") == -1) {
            breakStringIntoLines = this.breakStringIntoLines((String)breakStringIntoLines);
        }
        if (breakStringIntoLines2 != null && breakStringIntoLines2 instanceof String && ((String)breakStringIntoLines2).indexOf("\r\n") == -1) {
            breakStringIntoLines2 = this.breakStringIntoLines((String)breakStringIntoLines2);
        }
        try {
            this.statusMsg = "Failed to connect";
            if (this.a(this.host, this.port) != 0) {
                throw new Exception();
            }
            this.statusMsg = "Session refused";
            if (this.a(s, s2) != 0) {
                throw new Exception();
            }
            String s11;
            if (b) {
                s11 = this.a(s, s2, s3, s6);
            }
            else {
                s11 = this.a(s, s2, s3);
            }
            if (s11 == null) {
                throw new Exception();
            }
            this.statusMsg = "Data transmission failed";
            int n;
            if (b) {
                n = this.a(s11, s4, s5, breakStringIntoLines, "--" + s6, s7, s8, s9, s10, breakStringIntoLines2);
            }
            else {
                n = this.a(s11, breakStringIntoLines);
            }
            if (n != 0) {
                throw new Exception();
            }
            if (this.a() != 0) {
                throw new Exception();
            }
            if (!this.reuseConnection) {
                this.close();
            }
            return 0;
        }
        catch (Exception ex) {
            this.close();
            if (this.statusMsg == null) {
                this.statusMsg = "Unknown Problem";
            }
            throw new Exception("Unable to send message to " + this.host + ":" + this.port + " Status = " + this.statusMsg);
        }
    }
    
    private int a(final String host, final int port) throws Exception {
        if (this.socket != null && host.equals(this.host) && port == this.port) {
            return 0;
        }
        this.close();
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
        this.os = this.socket.getOutputStream();
        this.ps = new PrintStream(this.os);
        this.is = this.socket.getInputStream();
        this.bis = new BufferedInputStream(this.is);
        this.dis = new DataInputStream(this.bis);
        final int b = this.b();
        if (b != 220) {
            return b;
        }
        return 0;
    }
    
    private int a(final String s, final String s2) throws Exception {
        this.ps.print("HELO " + InetAddress.getLocalHost().getHostName() + "\r\n");
        this.os.flush();
        final int b = this.b();
        if (b != 250) {
            return b;
        }
        this.ps.print("MAIL From: ");
        this.ps.print(s);
        this.ps.print("\r\n");
        this.os.flush();
        final int b2 = this.b();
        if (b2 != 250) {
            return b2;
        }
        this.ps.print("RCPT To: ");
        this.ps.print(s2);
        this.ps.print("\r\n");
        this.os.flush();
        final int b3 = this.b();
        if (b3 != 250) {
            return b3;
        }
        return 0;
    }
    
    private int a() throws Exception {
        this.ps.print("QUIT\r\n");
        this.os.flush();
        final int b = this.b();
        if (b != 221) {
            return b;
        }
        return 0;
    }
    
    private String a(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer();
        if (s != null) {
            sb.append("From: ");
            sb.append(s);
            sb.append("\r\n");
        }
        if (s2 != null) {
            sb.append("To: ");
            sb.append(s2);
            sb.append("\r\n");
        }
        if (s3 != null) {
            sb.append("Subject: ");
            sb.append(s3);
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    private String a(final String s, final String s2, final String s3, final String s4) {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.a(s, s2, s3));
        if (s4 != null) {
            sb.append("MIME-Version: 1.0");
            sb.append("\r\n");
            sb.append("Content-Type: multipart/mixed;");
            sb.append("\r\n");
            sb.append(" boundary=\"" + s4 + "\"");
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    private int a(final String s, final Object o) throws Exception {
        return this.a(s, null, null, o, null, null, null, null, null, null);
    }
    
    private int a(final String s, final String s2, final String s3, final Object o, final String s4, final String s5, final String s6, final String s7, final String s8, final Object o2) throws Exception {
        boolean b = false;
        if (s2 != null && s3 != null && s4 != null && s5 != null && s6 != null && o2 != null) {
            b = true;
        }
        this.ps.print("DATA\r\n");
        this.os.flush();
        final int b2 = this.b();
        if (b2 != 354) {
            return b2;
        }
        if (s != null && s.length() > 0) {
            this.ps.print(s);
            if (!s.endsWith("\n")) {
                this.ps.print("\r\n");
            }
            this.ps.print("\r\n");
        }
        if (b) {
            this.ps.print("This is a multi-part message in MIME format.");
            this.ps.print("\r\n");
            this.ps.print(s4);
            this.ps.print("\r\n");
            this.ps.print("Content-Type: " + s3);
            this.ps.print("\r\n");
            this.ps.print("Content-Transfer-Encoding: " + s2);
            this.ps.print("\r\n");
            this.ps.print("\r\n");
        }
        if (o != null) {
            this.ps.print(o);
            this.ps.print("\r\n");
        }
        if (b) {
            this.ps.print("\r\n");
            this.ps.print(s4);
            this.ps.print("\r\n");
            this.ps.print("Content-Type: " + s6);
            if (s7 != null) {
                this.ps.print("; name=\"" + s7 + "\"");
            }
            this.ps.print("\r\n");
            this.ps.print("Content-Transfer-Encoding: " + s5);
            this.ps.print("\r\n");
            this.ps.print("Content-Disposition: attachment");
            if (s7 != null) {
                this.ps.print("; filename=\"" + s7 + "\"");
            }
            this.ps.print("\r\n");
            if (s8 != null) {
                this.ps.print("Content-Description: \"" + s8 + "\"");
                this.ps.print("\r\n");
            }
            this.ps.print("\r\n");
            this.ps.print(o2);
            this.ps.print("\r\n");
            this.ps.print(s4 + "--");
            this.ps.print("\r\n");
        }
        this.ps.print(".\r\n");
        final int b3 = this.b();
        if (b3 != 250) {
            return b3;
        }
        return 0;
    }
    
    private int b() throws Exception {
        final String line = this.dis.readLine();
        if (line == null) {
            this.statusMsg = "Connection dropped";
            return 0;
        }
        this.statusMsg = line;
        final int index = line.indexOf(32);
        if (index == -1) {
            return Integer.parseInt(line);
        }
        return Integer.parseInt(line.substring(0, index));
    }
    
    public void close() {
        if (this.socket == null) {
            return;
        }
        try {
            this.os.close();
        }
        catch (Exception ex) {}
        try {
            this.is.close();
        }
        catch (Exception ex2) {}
        try {
            this.socket.close();
        }
        catch (Exception ex3) {}
        this.socket = null;
    }
    
    public static void main(final String[] array) {
        if (array.length < 4) {
            NFDebug.print("usage: NFSmtpClient <host> <from> <to> <msg> [attachment mimeType]");
            System.exit(-1);
        }
        final String host = array[0];
        final String s = array[1];
        final String s2 = array[2];
        final String s3 = array[3];
        File file = null;
        String s4 = null;
        if (array.length == 6) {
            file = new File(array[4]);
            s4 = array[5];
        }
        try {
            final NFSmtpClient nfSmtpClient = new NFSmtpClient();
            nfSmtpClient.setHost(host);
            if (array.length != 6) {
                nfSmtpClient.sendMsg(s, s2, "Test Msg", s3);
            }
            else {
                final FileInputStream fileInputStream = new FileInputStream(file);
                final byte[] array2 = new byte[(int)file.length()];
                fileInputStream.read(array2);
                final byte[] encode = NFBase64.encode(array2);
                final char[] array3 = new char[encode.length];
                for (int i = 0; i < encode.length; ++i) {
                    array3[i] = (char)encode[i];
                }
                nfSmtpClient.sendMsgWithAttachment(s, s2, "Subject", "7bit", "text/plain; charset=us-ascii", s3, "base64", s4, new String(array3));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
