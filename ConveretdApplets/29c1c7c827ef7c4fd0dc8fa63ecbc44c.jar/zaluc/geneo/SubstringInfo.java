// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Color;

class SubstringInfo
{
    SubstringInfo next;
    Color color;
    UrlInfo url;
    int x;
    int width;
    String text;
    
    SubstringInfo(final Color color, final String text) {
        this.next = null;
        this.color = color;
        this.url = null;
        this.x = 0;
        this.width = 0;
        this.text = text;
    }
}
