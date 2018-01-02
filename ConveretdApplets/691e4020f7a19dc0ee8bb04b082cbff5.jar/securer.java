import java.awt.Button;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class securer extends Applet implements ActionListener
{
    private static TextField display;
    private static String target;
    private static String badURL;
    
    public void init() {
        securer.target = this.getParameter("target");
        securer.badURL = this.getParameter("badURL");
        this.setLayout(new BorderLayout());
        (securer.display = new TextField("")).setEditable(true);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(securer.display);
        this.addButton(panel, "ENTER");
        this.add(panel, "North");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        boolean b = true;
        try {
            final AppletContext appletContext = this.getAppletContext();
            final URL url = new URL(String.valueOf(securer.target) + securer.display.getText() + "/");
            try {
                url.getContent();
            }
            catch (Exception ex) {
                b = false;
                System.out.println("NULL: " + ex);
            }
            if (b) {
                appletContext.showDocument(url);
            }
        }
        catch (Exception ex2) {
            this.showStatus("ERROR " + ex2);
        }
        if (!b) {
            try {
                this.getAppletContext().showDocument(new URL(securer.badURL));
            }
            catch (Exception ex3) {
                this.showStatus("ERROR " + ex3 + " bad badURL parameter to securer applet");
            }
        }
    }
    
    public void addButton(final Container container, final String s) {
        final Button button = new Button(s);
        container.add(button);
        button.addActionListener(this);
    }
}
