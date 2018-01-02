// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.net.URL;
import java.awt.Rectangle;

class UrlInfo
{
    UrlInfo next;
    Rectangle rect;
    URL url;
    
    UrlInfo(final URL url) {
        this.next = null;
        this.rect = new Rectangle();
        this.url = url;
    }
}
