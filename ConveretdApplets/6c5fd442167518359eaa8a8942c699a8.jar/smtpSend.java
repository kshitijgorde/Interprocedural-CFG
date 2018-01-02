import java.util.Date;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.TextArea;
import java.io.PrintStream;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class smtpSend
{
    static final short PORT = 25;
    String lastline;
    DataInputStream in;
    PrintStream p;
    String mailhost;
    String receiver;
    String sender;
    TextArea scroller;
    Socket socket;
    
    smtpSend(final String mailhost, final String s) {
        this.mailhost = mailhost;
        this.receiver = s;
        this.sender = s;
    }
    
    public void setScroller(final TextArea scroller) {
        this.scroller = scroller;
    }
    
    void expect(final String s, final String s2) throws Exception {
        this.lastline = this.in.readLine();
        if (!this.lastline.startsWith(s)) {
            throw new Exception("Error: " + s2 + " (Expected " + s + ")");
        }
        while (this.lastline.startsWith(s + "-")) {
            this.lastline = this.in.readLine();
        }
    }
    
    private void scrollOK() {
        this.scroller.appendText("OK");
    }
    
    private void openSocket() throws Exception {
        this.display("Connecting to " + this.mailhost + "...");
        try {
            this.socket = new Socket(this.mailhost, 25);
        }
        catch (Exception ex) {
            throw new Exception("Socket Error: Can't connect!");
        }
        this.scroller.appendText("OK");
    }
    
    private void openInputStream() throws Exception {
        this.display("Opening input stream...");
        try {
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        catch (Exception ex) {
            throw new Exception("Connection Error: Cannot open for input.");
        }
        this.scroller.appendText("OK");
    }
    
    private PrintStream openOutputStream() throws Exception {
        this.display("Opening output stream...");
        try {
            this.p = new PrintStream(this.socket.getOutputStream());
        }
        catch (Exception ex) {
            throw new Exception("Connection Error: Cannot open for output.");
        }
        this.scroller.appendText("OK");
        return this.p;
    }
    
    private String getHeloHost() throws Exception {
        this.display("Getting Local Host Name...");
        String string;
        try {
            string = InetAddress.getLocalHost().toString();
        }
        catch (Exception ex) {
            throw new Exception("Network Error: Unknown Local Host.");
        }
        this.scroller.appendText("OK");
        this.display("Local Host: " + string + "\r\n");
        return string;
    }
    
    private void display(final String s) {
        if (this.scroller != null) {
            this.scroller.appendText("\r\n" + s);
        }
        System.out.println(s);
    }
    
    private void sendData(final String s, final String s2) throws Exception {
        try {
            String s3 = this.getHeloHost();
            this.p.print("HELO " + s3 + "\r\n");
            this.expect("250", "HELO");
            final int index;
            if ((index = this.lastline.indexOf("Hello ")) != -1) {
                final String substring = this.lastline.substring(index + "Hello ".length());
                s3 = substring.substring(0, substring.indexOf(32));
            }
            this.p.print("MAIL FROM: " + this.sender + "\r\n");
            this.expect("250", "MAIL FROM:");
            this.p.print("RCPT TO: " + this.receiver + "\r\n");
            this.expect("250", "RCPT TO:");
            this.p.print("DATA\r\n");
            this.expect("354", "DATA");
            this.p.print("Subject: " + s);
            this.p.print(" (" + s3 + ")");
            this.p.print("\r\n\r\n");
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s2));
            while (dataInputStream.available() > 0) {
                String line = dataInputStream.readLine();
                if (line.equals(".")) {
                    line = "..";
                }
                this.p.println(line);
            }
            this.p.print(new Date().toGMTString());
            this.p.print("\r\n.\r\n");
            this.expect("250", "end of data");
            this.p.print("QUIT\r\n");
            this.expect("221", "QUIT");
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void mailMessage(final String s, final String s2) throws Exception {
        if (this.receiver == null) {
            throw new Exception("Parameter Error: No RECEIVER");
        }
        try {
            this.openSocket();
            this.openOutputStream();
            this.openInputStream();
            this.expect("220", "No greeting");
            this.display("Sending message via SMTP...");
            this.sendData(s, s2);
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            }
            catch (Exception ex2) {}
        }
        this.scroller.appendText("OK");
        this.display("Message Sent - Thank You!");
        this.display("Press 'Quit' to close this window.");
    }
}
