import java.io.IOException;
import java.net.Socket;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class HTTPConnectSocketFactory implements SocketFactory
{
    public Socket createSocket(final String s, final int n, final Applet applet) throws IOException {
        return this.createSocket(s, n, applet.getParameter("PROXYHOST1"), applet.getParameter("PROXYPORT1"));
    }
    
    public Socket createSocket(final String s, final int n, final String[] array) throws IOException {
        return this.createSocket(s, n, this.readArg(array, "PROXYHOST1"), this.readArg(array, "PROXYPORT1"));
    }
    
    public Socket createSocket(final String s, final int n, final String s2, final String s3) throws IOException {
        int int1 = 0;
        if (s3 != null) {
            try {
                int1 = Integer.parseInt(s3);
            }
            catch (NumberFormatException ex) {}
        }
        if (s2 == null || int1 == 0) {
            System.out.println("Incomplete parameter list for HTTPConnectSocket");
            return new Socket(s, n);
        }
        System.out.println("HTTP CONNECT via proxy " + s2 + " port " + int1);
        return new HTTPConnectSocket(s, n, s2, int1);
    }
    
    private String readArg(final String[] array, final String s) {
        for (int i = 0; i < array.length; i += 2) {
            if (array[i].equalsIgnoreCase(s)) {
                try {
                    return array[i + 1];
                }
                catch (Exception ex) {
                    return null;
                }
            }
        }
        return null;
    }
}
