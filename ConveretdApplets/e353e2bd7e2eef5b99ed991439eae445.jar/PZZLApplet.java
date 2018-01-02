import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PZZLApplet extends Applet
{
    public static final int INIT_READY = 0;
    public static final int INIT_ERROR = 1;
    public int port;
    public String host;
    public String context;
    public String servlet;
    public String initialisationData;
    public int appletInitResult;
    
    public String getParameter(final String s) {
        return super.getParameter(s);
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public void setHost(final String host) {
        this.host = host;
    }
    
    public void setServletContext(final String context) {
        this.context = context;
    }
    
    public void setServlet(final String servlet) {
        this.servlet = servlet;
    }
    
    public void setInitialisationData(final String initialisationData) {
        this.initialisationData = initialisationData;
    }
    
    public String doGet(final String s) {
        final StringBuffer sb = new StringBuffer();
        try {
            final URLConnection openConnection = new URL("http", this.host, this.port, "/" + this.context + "/" + this.servlet + "?" + s).openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            for (String s2 = dataInputStream.readLine(); s2 != null; s2 = dataInputStream.readLine()) {
                sb.append(s2);
            }
            dataInputStream.close();
        }
        catch (Exception ex) {
            return "<ERROR>" + ex.toString() + "</ERROR>";
        }
        return sb.toString();
    }
    
    public PZZLApplet() {
        this.port = 80;
    }
}
