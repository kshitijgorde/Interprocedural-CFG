import java.io.IOException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JViewport;
import java.net.MalformedURLException;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShowDocument extends JFrame
{
    JEditorPane html;
    
    public ShowDocument(final URL url) {
        this.displayDocument(url, "new Window");
    }
    
    public ShowDocument(final URL url, final String target) {
        this.displayDocument(url, target);
    }
    
    public void displayDocument(final URL url, final String target) {
        if (target.equals("_self")) {
            JOptionPane.showMessageDialog(null, "Opening a new Window");
        }
        try {
            if (url != null) {
                (this.html = new JEditorPane(url)).setEditable(false);
                this.html.addHyperlinkListener(this.createHyperLinkListener());
                final JScrollPane scroller = new JScrollPane();
                final JViewport vp = scroller.getViewport();
                vp.add(this.html);
                this.setTitle("Exploring " + url.toString());
                this.getContentPane().add(scroller, "Center");
            }
        }
        catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, String.valueOf(url.toString()) + " is not a valid URL");
            return;
        }
        catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error occured while opening " + url.toString());
            return;
        }
        this.pack();
        this.setSize(800, 600);
        this.show();
    }
    
    public HyperlinkListener createHyperLinkListener() {
        return new HyperlinkListener() {
            public void hyperlinkUpdate(final HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument)ShowDocument.this.html.getDocument()).processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
                    }
                    else {
                        try {
                            ShowDocument.this.html.setPage(e.getURL());
                        }
                        catch (IOException ioe) {
                            System.out.println("IOE: " + ioe);
                        }
                    }
                }
            }
        };
    }
}
