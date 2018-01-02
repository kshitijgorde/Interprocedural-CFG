// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.net.URL;
import platform.AbstractOS;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class JAPHyperlinkAdapter implements HyperlinkListener
{
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            final URL url = hyperlinkEvent.getURL();
            if (url != null) {
                if (url.getProtocol().startsWith("mailto:")) {
                    AbstractOS.getInstance().openEMail(url.toString());
                }
                else {
                    AbstractOS.getInstance().openURL(url);
                }
            }
        }
    }
}
