// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.net.URL;
import javax.swing.text.Element;
import javax.swing.event.HyperlinkEvent;

public class HTMLFrameHyperlinkEvent extends HyperlinkEvent
{
    private String targetFrame;
    private Element sourceElement;
    
    public HTMLFrameHyperlinkEvent(final Object o, final EventType eventType, final URL url, final String targetFrame) {
        super(o, eventType, url);
        this.targetFrame = targetFrame;
        this.sourceElement = null;
    }
    
    public HTMLFrameHyperlinkEvent(final Object o, final EventType eventType, final URL url, final String s, final String targetFrame) {
        super(o, eventType, url, s);
        this.targetFrame = targetFrame;
        this.sourceElement = null;
    }
    
    public HTMLFrameHyperlinkEvent(final Object o, final EventType eventType, final URL url, final String s, final Element sourceElement, final String targetFrame) {
        super(o, eventType, url, s);
        this.targetFrame = targetFrame;
        this.sourceElement = sourceElement;
    }
    
    public HTMLFrameHyperlinkEvent(final Object o, final EventType eventType, final URL url, final Element sourceElement, final String targetFrame) {
        super(o, eventType, url);
        this.targetFrame = targetFrame;
        this.sourceElement = sourceElement;
    }
    
    public Element getSourceElement() {
        return this.sourceElement;
    }
    
    public String getTarget() {
        return this.targetFrame;
    }
}
