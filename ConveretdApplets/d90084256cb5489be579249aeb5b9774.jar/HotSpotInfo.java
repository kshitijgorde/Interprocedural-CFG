import java.net.URL;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class HotSpotInfo
{
    Rectangle m_bounds;
    URL m_url;
    
    public HotSpotInfo(final Rectangle bounds, final URL url) {
        this.m_bounds = bounds;
        this.m_url = url;
    }
    
    public Rectangle getBounds() {
        return this.m_bounds;
    }
    
    public URL getURL() {
        return this.m_url;
    }
}
