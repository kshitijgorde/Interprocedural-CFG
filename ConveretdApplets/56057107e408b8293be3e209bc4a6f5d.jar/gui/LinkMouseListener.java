// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Cursor;
import javax.swing.JComponent;
import java.net.MalformedURLException;
import logging.LogHolder;
import logging.LogType;
import java.net.URL;
import platform.AbstractOS;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class LinkMouseListener extends MouseAdapter
{
    private ILinkGenerator m_linkToOpen;
    
    public LinkMouseListener() {
        this.m_linkToOpen = null;
    }
    
    public LinkMouseListener(final String s) {
        this.m_linkToOpen = null;
        this.m_linkToOpen = new ImmutableLinkGenerator(s);
    }
    
    public LinkMouseListener(final ILinkGenerator linkToOpen) {
        this.m_linkToOpen = null;
        this.m_linkToOpen = linkToOpen;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        String s;
        if (this.m_linkToOpen != null) {
            s = this.m_linkToOpen.createLink();
        }
        else {
            if (!(mouseEvent.getSource() instanceof JLabel)) {
                return;
            }
            s = ((JLabel)mouseEvent.getSource()).getText();
        }
        try {
            AbstractOS.getInstance().openURL(new URL(s));
        }
        catch (ClassCastException ex) {
            LogHolder.log(3, LogType.PAY, "opening a link failed, reason: called on non-JLabel component");
        }
        catch (MalformedURLException ex2) {
            LogHolder.log(3, LogType.PAY, "opening a link failed, reason: malformed URL");
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((JComponent)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((JComponent)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    private class ImmutableLinkGenerator implements ILinkGenerator
    {
        private String m_LinkToOpen;
        
        public ImmutableLinkGenerator(final String linkToOpen) {
            this.m_LinkToOpen = linkToOpen;
        }
        
        public String createLink() {
            return this.m_LinkToOpen;
        }
    }
    
    public interface ILinkGenerator
    {
        String createLink();
    }
}
