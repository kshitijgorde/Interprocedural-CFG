import java.awt.Window;
import pclient.main.FrameOld;
import pclient.shd.Config;
import pclient.shd.ChatApplet;
import pclient.main.MainClient;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ParaChat extends Applet implements MainClient
{
    protected boolean forceLite;
    private ChatApplet chatApplet;
    private Config paraConf;
    
    public ParaChat() {
        this.forceLite = false;
        this.chatApplet = null;
    }
    
    public static void main(final String[] array) {
        System.out.println("Client version \"9.12\"");
        System.out.println("Client release rel-9090");
    }
    
    public void init() {
        System.out.println(System.getProperty("java.vendor", "#70") + " " + System.getProperty("java.version", "#71"));
        System.out.println(System.getProperty("os.name", "#72") + " " + System.getProperty("os.version", "#73"));
        System.out.println("Applet 9.12");
        System.out.println("Release rel-9090");
        System.out.println("doc=" + this.getDocumentBase().toString());
        this.paraConf = new Config((MainClient)this, (Applet)this);
        String s;
        if (this.isBasic()) {
            s = "pclient.bsc.AppletBase";
            this.popSuggest();
        }
        else {
            s = "pclient.adv.AppletSpice";
        }
        try {
            this.chatApplet = (ChatApplet)Class.forName(s).newInstance();
        }
        catch (Exception ex) {
            System.out.println("Error CN2233. Low Memory or no connection." + ex);
            ex.printStackTrace();
            this.chatApplet = null;
            return;
        }
        this.chatApplet.setParent((MainClient)this, (Applet)this, this.paraConf);
        this.chatApplet.init();
    }
    
    public void start() {
        if (this.chatApplet != null) {
            this.chatApplet.start();
        }
    }
    
    public void stop() {
        System.out.println("Stopping Chat Applet..");
        if (this.chatApplet != null) {
            this.chatApplet.stop();
        }
    }
    
    public boolean isBasic() {
        if (this.forceLite) {
            return true;
        }
        final String property = System.getProperty("java.version", "Unknown");
        return property.startsWith("1.0") || property.startsWith("1.1") || property.startsWith("1.2") || property.startsWith("1.3");
    }
    
    public boolean isL3() {
        return System.getProperty("java.version", "Unknown").compareTo("1.5") >= 0;
    }
    
    public boolean isWND() {
        return System.getProperty("os.name", "Unknown").toLowerCase().indexOf("window") >= 0;
    }
    
    public void popSuggest() {
        if (this.forceLite) {
            return;
        }
        if (!this.paraConf.getBool("Ctrl.Pop.Upd", true)) {
            return;
        }
        ((Window)new FrameOld(this.paraConf.title(), this.paraConf.get("Msg.OldJava", "You are using an older version of Java. Although you can still access this ParaChat room, we recommend that you upgrade to the latest Java version for the best chat experience.  You may upgrade now for free by visiting: http://java.com"), this.paraConf.get("Bt.GetJava", "Get Java"), (Applet)this, this.paraConf.get("URL.Java", "http://java.com"))).show();
    }
}
