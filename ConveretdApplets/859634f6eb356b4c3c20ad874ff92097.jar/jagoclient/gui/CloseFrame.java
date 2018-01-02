// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.io.InputStream;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import rene.util.list.ListElement;
import java.awt.MenuBar;
import jagoclient.Global;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import rene.util.list.ListClass;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class CloseFrame extends Frame implements WindowListener, ActionListener, DoActionListener
{
    ListClass L;
    static Hashtable Icons;
    
    public CloseFrame(final String title) {
        super("");
        this.L = new ListClass();
        this.addWindowListener(this);
        this.setTitle(title);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.close()) {
            this.doclose();
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public boolean close() {
        return true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.doAction(actionEvent.getActionCommand());
    }
    
    public void doAction(final String s) {
        if (Global.resourceString("Close").equals(s) && this.close()) {
            this.doclose();
        }
    }
    
    public void doclose() {
        if (rene.gui.Global.getParameter("menuclose", true)) {
            this.setMenuBar(null);
        }
        this.setVisible(false);
        this.dispose();
    }
    
    public void addCloseListener(final CloseListener closeListener) {
        this.L.append(new ListElement(closeListener));
    }
    
    public void inform() {
        for (ListElement listElement = this.L.first(); listElement != null; listElement = listElement.next()) {
            try {
                ((CloseListener)listElement.content()).isClosed();
            }
            catch (Exception ex) {}
        }
    }
    
    public void removeCloseListener(final CloseListener closeListener) {
        for (ListElement listElement = this.L.first(); listElement != null; listElement = listElement.next()) {
            if (listElement.content() == closeListener) {
                this.L.remove(listElement);
                return;
            }
        }
    }
    
    public void itemAction(final String s, final boolean b) {
    }
    
    public void seticon(final String s) {
        try {
            if (!rene.gui.Global.getParameter("icons", false)) {
                return;
            }
            final Image value = CloseFrame.Icons.get(s);
            if (value == null) {
                final InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + s);
                int n = 0;
                int i = resourceAsStream.available();
                final byte[] array = new byte[20000];
                while (i > 0) {
                    final int read = resourceAsStream.read(array, n, i);
                    if (read < 0) {
                        break;
                    }
                    n += read;
                    i = resourceAsStream.available();
                }
                final Image image = Toolkit.getDefaultToolkit().createImage(array, 0, n);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
                CloseFrame.Icons.put(s, image);
                this.setIconImage(image);
                return;
            }
            this.setIconImage(value);
        }
        catch (Exception ex) {
            if (!s.startsWith("jagoclient")) {
                this.seticon("jagoclient/gifs/" + s);
            }
        }
    }
    
    static {
        CloseFrame.Icons = new Hashtable();
    }
}
