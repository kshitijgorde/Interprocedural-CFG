import java.net.URLConnection;
import java.awt.Event;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChartDataLoader implements Runnable
{
    public static final int DATA_ERROR = 20010;
    private Thread thread;
    Component parent;
    Applet applet;
    String script;
    int retVal;
    boolean done;
    
    ChartDataLoader(final Component component, final Applet applet1, final String s, final int i) {
        this.done = false;
        this.parent = component;
        this.applet = applet1;
        this.script = s;
        this.retVal = i;
        (this.thread = new Thread(this, "The Data Loader")).start();
    }
    
    public void run() {
        System.out.println(String.valueOf("fetching ").concat(String.valueOf(this.script)));
        try {
            String s = "";
            try {
                final URL url = new URL(this.applet.getCodeBase(), this.script);
                final URLConnection urlconnection = url.openConnection();
                final DataInputStream datainputstream = new DataInputStream(urlconnection.getInputStream());
                s = "";
                while ((s = datainputstream.readLine()) != null) {
                    this.parent.postEvent(new Event(this, this.retVal, s));
                }
                datainputstream.close();
            }
            catch (Exception exception1) {
                System.out.println("****Data loader exception");
                System.out.println(String.valueOf(exception1.toString()));
                exception1.printStackTrace();
                System.out.println(String.valueOf(String.valueOf("Inline=[").concat(String.valueOf(s))).concat(String.valueOf("]")));
                System.out.println("****End Data loader exception");
                this.parent.postEvent(new Event(this, 20010, exception1.toString()));
            }
        }
        catch (Exception ex) {}
        this.parent.postEvent(new Event(this, this.retVal + 100, null));
        this.done = true;
    }
}
