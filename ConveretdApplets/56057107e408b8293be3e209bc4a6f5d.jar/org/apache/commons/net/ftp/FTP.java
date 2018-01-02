// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.net.InetAddress;
import java.util.Enumeration;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.net.ProtocolCommandListener;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandSupport;
import java.util.Vector;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import org.apache.commons.net.telnet.TelnetClient;

public class FTP extends TelnetClient
{
    public static final int DEFAULT_DATA_PORT = 20;
    public static final int DEFAULT_PORT = 21;
    public static final int ASCII_FILE_TYPE = 0;
    public static final int EBCDIC_FILE_TYPE = 1;
    public static final int IMAGE_FILE_TYPE = 2;
    public static final int BINARY_FILE_TYPE = 2;
    public static final int LOCAL_FILE_TYPE = 3;
    public static final int NON_PRINT_TEXT_FORMAT = 4;
    public static final int TELNET_TEXT_FORMAT = 5;
    public static final int CARRIAGE_CONTROL_TEXT_FORMAT = 6;
    public static final int FILE_STRUCTURE = 7;
    public static final int RECORD_STRUCTURE = 8;
    public static final int PAGE_STRUCTURE = 9;
    public static final int STREAM_TRANSFER_MODE = 10;
    public static final int BLOCK_TRANSFER_MODE = 11;
    public static final int COMPRESSED_TRANSFER_MODE = 12;
    public static final String DEFAULT_CONTROL_ENCODING = "ISO-8859-1";
    private static final String __modes = "ABILNTCFRPSBC";
    private StringBuffer __commandBuffer;
    BufferedReader _controlInput;
    BufferedWriter _controlOutput;
    int _replyCode;
    Vector _replyLines;
    boolean _newReplyString;
    String _replyString;
    String _controlEncoding;
    protected ProtocolCommandSupport _commandSupport_;
    
    public FTP() {
        this.setDefaultPort(21);
        this.__commandBuffer = new StringBuffer();
        this._replyLines = new Vector();
        this._newReplyString = false;
        this._replyString = null;
        this._commandSupport_ = new ProtocolCommandSupport(this);
        this._controlEncoding = "ISO-8859-1";
    }
    
    private void __getReply() throws IOException {
        this._newReplyString = true;
        this._replyLines.setSize(0);
        String line = this._controlInput.readLine();
        if (line == null) {
            throw new FTPConnectionClosedException("Connection closed without indication.");
        }
        final int length = line.length();
        if (length < 3) {
            throw new MalformedServerReplyException("Truncated server reply: " + line);
        }
        try {
            final String code = line.substring(0, 3);
            this._replyCode = Integer.parseInt(code);
        }
        catch (NumberFormatException e) {
            throw new MalformedServerReplyException("Could not parse response code.\nServer Reply: " + line);
        }
        this._replyLines.addElement(line);
        if (length > 3 && line.charAt(3) == '-') {
            do {
                line = this._controlInput.readLine();
                if (line == null) {
                    throw new FTPConnectionClosedException("Connection closed without indication.");
                }
                this._replyLines.addElement(line);
            } while (line.length() < 4 || line.charAt(3) == '-' || !Character.isDigit(line.charAt(0)));
        }
        if (this._commandSupport_.getListenerCount() > 0) {
            this._commandSupport_.fireReplyReceived(this._replyCode, this.getReplyString());
        }
        if (this._replyCode == 421) {
            throw new FTPConnectionClosedException("FTP response 421 received.  Server closed connection.");
        }
    }
    
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        this._controlInput = new BufferedReader(new InputStreamReader(this.getInputStream(), this.getControlEncoding()));
        this._controlOutput = new BufferedWriter(new OutputStreamWriter(this.getOutputStream(), this.getControlEncoding()));
        this.__getReply();
        if (FTPReply.isPositivePreliminary(this._replyCode)) {
            this.__getReply();
        }
    }
    
    public void setControlEncoding(final String encoding) {
        this._controlEncoding = encoding;
    }
    
    public String getControlEncoding() {
        return this._controlEncoding;
    }
    
    public void addProtocolCommandListener(final ProtocolCommandListener listener) {
        this._commandSupport_.addProtocolCommandListener(listener);
    }
    
    public void removeProtocolCommandListener(final ProtocolCommandListener listener) {
        this._commandSupport_.removeProtocolCommandListener(listener);
    }
    
    public void disconnect() throws IOException {
        super.disconnect();
        this._controlInput = null;
        this._controlOutput = null;
        this._replyLines.setSize(0);
        this._newReplyString = false;
        this._replyString = null;
    }
    
    public int sendCommand(final String command, final String args) throws IOException {
        this.__commandBuffer.setLength(0);
        this.__commandBuffer.append(command);
        if (args != null) {
            this.__commandBuffer.append(' ');
            this.__commandBuffer.append(args);
        }
        this.__commandBuffer.append("\r\n");
        String message;
        try {
            this._controlOutput.write(message = this.__commandBuffer.toString());
            this._controlOutput.flush();
        }
        catch (SocketException e) {
            if (!this.isConnected() || !this.socketIsConnected(this._socket_)) {
                throw new FTPConnectionClosedException("Connection unexpectedly closed.");
            }
            throw e;
        }
        if (this._commandSupport_.getListenerCount() > 0) {
            this._commandSupport_.fireCommandSent(command, message);
        }
        this.__getReply();
        return this._replyCode;
    }
    
    private boolean socketIsConnected(final Socket socket) {
        if (socket == null) {
            return false;
        }
        try {
            final Method isConnected = socket.getClass().getMethod("isConnected", (Class<?>[])null);
            return (boolean)isConnected.invoke(socket, (Object[])null);
        }
        catch (NoSuchMethodException e) {
            return true;
        }
        catch (IllegalAccessException e2) {
            return true;
        }
        catch (InvocationTargetException e3) {
            return true;
        }
    }
    
    public int sendCommand(final int command, final String args) throws IOException {
        return this.sendCommand(FTPCommand._commands[command], args);
    }
    
    public int sendCommand(final String command) throws IOException {
        return this.sendCommand(command, null);
    }
    
    public int sendCommand(final int command) throws IOException {
        return this.sendCommand(command, null);
    }
    
    public int getReplyCode() {
        return this._replyCode;
    }
    
    public int getReply() throws IOException {
        this.__getReply();
        return this._replyCode;
    }
    
    public String[] getReplyStrings() {
        final String[] lines = new String[this._replyLines.size()];
        this._replyLines.copyInto(lines);
        return lines;
    }
    
    public String getReplyString() {
        if (!this._newReplyString) {
            return this._replyString;
        }
        final StringBuffer buffer = new StringBuffer(256);
        final Enumeration en = this._replyLines.elements();
        while (en.hasMoreElements()) {
            buffer.append(en.nextElement());
            buffer.append("\r\n");
        }
        this._newReplyString = false;
        return this._replyString = buffer.toString();
    }
    
    public int user(final String username) throws IOException {
        return this.sendCommand(0, username);
    }
    
    public int pass(final String password) throws IOException {
        return this.sendCommand(1, password);
    }
    
    public int acct(final String account) throws IOException {
        return this.sendCommand(2, account);
    }
    
    public int abor() throws IOException {
        return this.sendCommand(21);
    }
    
    public int cwd(final String directory) throws IOException {
        return this.sendCommand(3, directory);
    }
    
    public int cdup() throws IOException {
        return this.sendCommand(4);
    }
    
    public int quit() throws IOException {
        return this.sendCommand(7);
    }
    
    public int rein() throws IOException {
        return this.sendCommand(6);
    }
    
    public int smnt(final String dir) throws IOException {
        return this.sendCommand(5, dir);
    }
    
    public int port(final InetAddress host, final int port) throws IOException {
        final StringBuffer info = new StringBuffer(24);
        info.append(host.getHostAddress().replace('.', ','));
        int num = port >>> 8;
        info.append(',');
        info.append(num);
        info.append(',');
        num = (port & 0xFF);
        info.append(num);
        return this.sendCommand(8, info.toString());
    }
    
    public int pasv() throws IOException {
        return this.sendCommand(9);
    }
    
    public int type(final int fileType, final int formatOrByteSize) throws IOException {
        final StringBuffer arg = new StringBuffer();
        arg.append("ABILNTCFRPSBC".charAt(fileType));
        arg.append(' ');
        if (fileType == 3) {
            arg.append(formatOrByteSize);
        }
        else {
            arg.append("ABILNTCFRPSBC".charAt(formatOrByteSize));
        }
        return this.sendCommand(10, arg.toString());
    }
    
    public int type(final int fileType) throws IOException {
        return this.sendCommand(10, "ABILNTCFRPSBC".substring(fileType, fileType + 1));
    }
    
    public int stru(final int structure) throws IOException {
        return this.sendCommand(11, "ABILNTCFRPSBC".substring(structure, structure + 1));
    }
    
    public int mode(final int mode) throws IOException {
        return this.sendCommand(12, "ABILNTCFRPSBC".substring(mode, mode + 1));
    }
    
    public int retr(final String pathname) throws IOException {
        return this.sendCommand(13, pathname);
    }
    
    public int stor(final String pathname) throws IOException {
        return this.sendCommand(14, pathname);
    }
    
    public int stou() throws IOException {
        return this.sendCommand(15);
    }
    
    public int stou(final String pathname) throws IOException {
        return this.sendCommand(15, pathname);
    }
    
    public int appe(final String pathname) throws IOException {
        return this.sendCommand(16, pathname);
    }
    
    public int allo(final int bytes) throws IOException {
        return this.sendCommand(17, Integer.toString(bytes));
    }
    
    public int allo(final int bytes, final int recordSize) throws IOException {
        return this.sendCommand(17, Integer.toString(bytes) + " R " + Integer.toString(recordSize));
    }
    
    public int rest(final String marker) throws IOException {
        return this.sendCommand(18, marker);
    }
    
    public int rnfr(final String pathname) throws IOException {
        return this.sendCommand(19, pathname);
    }
    
    public int rnto(final String pathname) throws IOException {
        return this.sendCommand(20, pathname);
    }
    
    public int dele(final String pathname) throws IOException {
        return this.sendCommand(22, pathname);
    }
    
    public int rmd(final String pathname) throws IOException {
        return this.sendCommand(23, pathname);
    }
    
    public int mkd(final String pathname) throws IOException {
        return this.sendCommand(24, pathname);
    }
    
    public int pwd() throws IOException {
        return this.sendCommand(25);
    }
    
    public int list() throws IOException {
        return this.sendCommand(26);
    }
    
    public int list(final String pathname) throws IOException {
        return this.sendCommand(26, pathname);
    }
    
    public int nlst() throws IOException {
        return this.sendCommand(27);
    }
    
    public int nlst(final String pathname) throws IOException {
        return this.sendCommand(27, pathname);
    }
    
    public int site(final String parameters) throws IOException {
        return this.sendCommand(28, parameters);
    }
    
    public int syst() throws IOException {
        return this.sendCommand(29);
    }
    
    public int stat() throws IOException {
        return this.sendCommand(30);
    }
    
    public int stat(final String pathname) throws IOException {
        return this.sendCommand(30, pathname);
    }
    
    public int help() throws IOException {
        return this.sendCommand(31);
    }
    
    public int help(final String command) throws IOException {
        return this.sendCommand(31, command);
    }
    
    public int noop() throws IOException {
        return this.sendCommand(32);
    }
}
