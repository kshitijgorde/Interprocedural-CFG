// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Label;

public class hyperLinkTextArea extends Label
{
    public String sometext;
    public String lnk;
    public Applet par;
    
    public hyperLinkTextArea(final Applet Parent, final String text, final String link, final Color rpt_hyperlink_colour) {
        this.sometext = text;
        this.par = Parent;
        this.lnk = link;
        this.setText(this.sometext);
        this.setForeground(rpt_hyperlink_colour);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                hyperLinkTextArea.this.textFieldUmpteen_mouseClicked(e);
            }
        });
        this.validate();
        final SymMouseMotion aSymMouseMotion = new SymMouseMotion();
        this.addMouseMotionListener(aSymMouseMotion);
    }
    
    public void textFieldUmpteen_mouseClicked(final MouseEvent e) {
        try {
            final URL toLoad = new URL(this.par.getDocumentBase(), this.lnk);
            final AppletContext context = this.par.getAppletContext();
            context.showDocument(toLoad, "_blank");
            context.showStatus(toLoad.toString());
        }
        catch (MalformedURLException ex) {}
    }
    
    void hyperLinkTextArea_MouseMoved(final MouseEvent event) {
        this.setCursor(new Cursor(12));
    }
    
    class SymMouseMotion extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent event) {
            final Object object = event.getSource();
            if (object == hyperLinkTextArea.this) {
                hyperLinkTextArea.this.hyperLinkTextArea_MouseMoved(event);
            }
        }
    }
}
