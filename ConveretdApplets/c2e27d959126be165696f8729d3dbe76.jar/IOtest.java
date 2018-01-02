import java.io.IOException;
import socket.TelnetIO;

// 
// Decompiled by Procyon v0.5.30
// 

class IOtest
{
    static TelnetIO tio;
    
    static {
        IOtest.tio = new TelnetIO();
    }
    
    public static void main(final String[] args) {
        try {
            IOtest.tio.connect("localhost");
            wait("login:");
            send("<YOUR LOGIN NAME>\r");
            wait("Password:");
            send("<YOUR PASSWORD>\r");
            wait("<YOUR SHELL PROMPT>");
            send("touch /tmp/THIS_WAS_AN_APPLET\r");
            IOtest.tio.disconnect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void send(final String str) {
        final byte[] buf = new byte[str.length()];
        str.getBytes(0, str.length(), buf, 0);
        try {
            IOtest.tio.send(buf);
        }
        catch (IOException ex) {}
    }
    
    private static void wait(final String prompt) {
        String tmp = "";
        do {
            try {
                tmp = new String(IOtest.tio.receive(), 0);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(tmp);
        } while (tmp.indexOf(prompt) == -1);
    }
}
