import java.io.IOException;
import java.net.Socket;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class HTTPConnectSocketFactory implements SocketFactory
{
    public Socket createSocket(final String host, final int port, final Applet applet) throws IOException {
        return this.createSocket(host, port, applet.getParameter("PROXYHOST1"), applet.getParameter("PROXYPORT1"));
    }
    
    public Socket createSocket(final String host, final int port, final String[] args) throws IOException {
        return this.createSocket(host, port, this.readArg(args, "PROXYHOST1"), this.readArg(args, "PROXYPORT1"));
    }
    
    public Socket createSocket(final String host, final int port, final String proxyHost, final String proxyPortStr) throws IOException {
        int proxyPort = 0;
        if (proxyPortStr != null) {
            try {
                proxyPort = Integer.parseInt(proxyPortStr);
            }
            catch (NumberFormatException ex) {}
        }
        if (proxyHost == null || proxyPort == 0) {
            System.out.println("Incomplete parameter list for HTTPConnectSocket");
            return new Socket(host, port);
        }
        System.out.println("HTTP CONNECT via proxy " + proxyHost + " port " + proxyPort);
        final HTTPConnectSocket s = new HTTPConnectSocket(host, port, proxyHost, proxyPort);
        return s;
    }
    
    private String readArg(final String[] args, final String name) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equalsIgnoreCase(name)) {
                try {
                    return args[i + 1];
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }
}
