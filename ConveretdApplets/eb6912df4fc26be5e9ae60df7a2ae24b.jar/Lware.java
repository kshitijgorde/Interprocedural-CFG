import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Label;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Lware extends Frame
{
    private AppletContext appletContext;
    static final char[] anurl;
    
    public Lware(final AppletContext appletContext, final Label label) {
        this.appletContext = appletContext;
        this.setFont(new Font("System", 1, 12));
        this.setLayout(new GridLayout(5, 1, 1, 2));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        final Label label2 = new Label("You can connect to my java applets page");
        final Label label3 = new Label("if you want to download my latest applets!");
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(new Button("Connect"));
        this.add(new Button("Cancel"));
        this.pack();
    }
    
    public final void show() {
        super.show();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (o.equals("Cancel")) {
            this.hide();
            return true;
        }
        if (o.equals("Connect")) {
            this.hide();
            URL url = null;
            this.appletContext.showStatus("Linking with anfyjava.com");
            try {
                url = new URL(new String(Lware.anurl));
            }
            catch (MalformedURLException ex) {
                this.appletContext.showStatus("Error linking www.anfyjava.com");
            }
            this.appletContext.showDocument(url, "_blank");
            return true;
        }
        return false;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 203 || event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    static {
        anurl = new char[] { 'h', 't', 't', 'p', ':', '/', '/', 'w', 'w', 'w', '.', 'a', 'n', 'f', 'y', 'j', 'a', 'v', 'a', '.', 'c', 'o', 'm' };
    }
}
