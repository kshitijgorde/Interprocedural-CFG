// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import gui.dialog.JAPDialog;
import javax.swing.text.Document;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import java.awt.Component;
import javax.swing.text.JTextComponent;
import javax.swing.JViewport;
import java.awt.Cursor;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.JScrollPane;

public class JapHtmlPane extends JScrollPane implements HyperlinkListener
{
    private JEditorPane html;
    private URL url;
    private Cursor cursor;
    private static final String MAILTO = "mailto";
    
    public JapHtmlPane(final String s, final JViewport viewport) {
        this.html = new JEditorPane("text/html", s);
        new JTextComponentToClipboardCopier(true).registerTextComponent(this.html);
        this.html.setEditable(false);
        this.html.addHyperlinkListener(this);
        if (viewport != null) {
            this.setViewport(viewport);
        }
        this.getViewport().add(this.html);
        this.cursor = this.html.getCursor();
    }
    
    public JEditorPane getPane() {
        return this.html;
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            this.linkActivated(hyperlinkEvent.getURL());
        }
        else if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ENTERED) {
            this.html.setCursor(Cursor.getPredefinedCursor(12));
        }
        else if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.EXITED) {
            this.html.setCursor(this.cursor);
        }
    }
    
    private void linkActivated(final URL url) {
        this.html.setCursor(Cursor.getPredefinedCursor(3));
        SwingUtilities.invokeLater(new PageLoader(url));
    }
    
    private void loadURL(final URL url) {
        this.html.setCursor(Cursor.getPredefinedCursor(3));
        SwingUtilities.invokeLater(new PageLoader(url));
    }
    
    private final class PageLoader implements Runnable
    {
        PageLoader(final URL url) {
            JapHtmlPane.this.url = url;
        }
        
        public void run() {
            if (JapHtmlPane.this.url == null) {
                JapHtmlPane.this.html.setCursor(JapHtmlPane.this.cursor);
                JapHtmlPane.this.html.getParent().repaint();
            }
            else {
                if (JapHtmlPane.this.url.getProtocol().startsWith("file") || JapHtmlPane.this.url.getProtocol().startsWith("zip") || JapHtmlPane.this.url.getProtocol().startsWith("jar") || JapHtmlPane.this.url.getProtocol().startsWith("systemresource")) {
                    final Document document = JapHtmlPane.this.html.getDocument();
                    try {
                        JapHtmlPane.this.html.setPage(JapHtmlPane.this.url);
                        return;
                    }
                    catch (IOException ex) {
                        JapHtmlPane.this.html.setDocument(document);
                        JapHtmlPane.this.getToolkit().beep();
                        return;
                    }
                    finally {
                        JapHtmlPane.this.url = null;
                        SwingUtilities.invokeLater(this);
                    }
                }
                if (!true) {
                    JapHtmlPane.this.html.setCursor(JapHtmlPane.this.cursor);
                }
            }
        }
        
        private class ExternalLinkedInformation extends JAPDialog.LinkedInformationAdapter
        {
            private URL m_url;
            
            public ExternalLinkedInformation(final URL url) {
                this.m_url = url;
            }
            
            public String getMessage() {
                return this.m_url.toString();
            }
            
            public int getType() {
                return 2;
            }
            
            public boolean isApplicationModalityForced() {
                return true;
            }
        }
    }
}
