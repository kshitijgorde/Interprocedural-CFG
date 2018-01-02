import teletext.TeletextReply;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class TeletextClient
{
    public final int TX_OK = 100;
    public final int TX_CHANGE_COMMAND = 101;
    public final int TX_SEND_DATA = 110;
    public final int TX_RECEIVE_DATA = 120;
    public final int TX_PROTOCOL_ERROR = 300;
    public final int TX_GENERIC_ERROR = 400;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    String hostname;
    int portNumber;
    
    public TeletextClient() {
        this.socket = null;
        this.inputStream = null;
        this.outputStream = null;
        this.hostname = null;
        this.portNumber = 0;
    }
    
    public void finalize() throws Exception {
        this.disconnect();
    }
    
    public boolean sendString(String string) throws IOException {
        string += "\n";
        for (int i = 0; i < string.length(); ++i) {
            this.outputStream.write(string.charAt(i));
        }
        return true;
    }
    
    public String receiveString() throws IOException {
        final StringBuffer sb = new StringBuffer();
        while (true) {
            final int read = this.inputStream.read();
            if (read == 10) {
                break;
            }
            sb.append((char)read);
        }
        return sb.toString();
    }
    
    public char[] receiveBuffer(final int n) throws IOException {
        int i = 0;
        final char[] array = new char[n];
        do {
            array[i++] = (char)this.inputStream.read();
        } while (i != n);
        return array;
    }
    
    public boolean sendBuffer(final char[] array) throws IOException {
        this.sendString(new Integer(array.length).toString());
        for (int i = 0; i < array.length; ++i) {
            this.outputStream.write(array[i]);
        }
        return true;
    }
    
    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }
    
    public void setPortNumber(final int portNumber) {
        this.portNumber = portNumber;
    }
    
    public String getHostname() {
        return this.hostname;
    }
    
    public int getPortNumber() {
        return this.portNumber;
    }
    
    public void connect(final String hostname, final int portNumber) throws UnknownHostException, IOException {
        this.hostname = hostname;
        this.portNumber = portNumber;
        this.socket = new Socket(this.hostname, this.portNumber);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
    }
    
    public void disconnect() {
        if (this.socket != null) {
            try {
                this.socket.close();
            }
            catch (IOException ex) {}
            this.socket = null;
            this.inputStream = null;
            this.outputStream = null;
        }
    }
    
    public TeletextReply execute(final String command, final char[] array) throws IOException {
        this.sendString("ASTONCMD " + command);
        final TeletextReply teletextReply = new TeletextReply();
        teletextReply.setCommand(command);
    Label_0204:
        while (true) {
            final String receiveString = this.receiveString();
            final int int1 = Integer.parseInt(receiveString.substring(0, 3));
            final String substring = receiveString.substring(4);
            switch (int1) {
                case 100: {
                    teletextReply.setReply(substring);
                    break Label_0204;
                }
                case 101: {
                    teletextReply.setCommand(substring);
                    continue;
                }
                case 110: {
                    this.sendBuffer(array);
                    continue;
                }
                case 120: {
                    final int int2 = Integer.parseInt(this.receiveString());
                    teletextReply.setReply(substring);
                    teletextReply.setContents(this.receiveBuffer(int2));
                    break Label_0204;
                }
                case 300: {
                    teletextReply.setReply(substring);
                    break Label_0204;
                }
                case 400: {
                    teletextReply.setReply(substring);
                    break Label_0204;
                }
            }
        }
        return teletextReply;
    }
    
    public TeletextReply execute(final String s) throws IOException {
        final char[] array = new char[960];
        for (int i = 0; i < 960; ++i) {
            array[i] = ' ';
        }
        return this.execute(s, array);
    }
}
