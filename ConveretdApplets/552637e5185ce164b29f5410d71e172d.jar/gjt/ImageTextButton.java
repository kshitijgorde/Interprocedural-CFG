// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Image;
import java.awt.Event;
import MudFE.TextDisplayer;

public class ImageTextButton extends ImageButton
{
    String st;
    TextDisplayer td;
    
    public boolean mouseEnter(final Event o, final int x, final int y) {
        this.td.tdaddString(this.st);
        return super.mouseEnter(o, x, y);
    }
    
    public boolean mouseExit(final Event o, final int x, final int y) {
        this.td.tdaddString("");
        return super.mouseExit(o, x, y);
    }
    
    public ImageTextButton(final Image image, final String st, final TextDisplayer td) {
        this(image, ImageButton._defaultThickness, null, st, td);
    }
    
    public ImageTextButton(final Image image, final ImageButtonController controller, final String st, final TextDisplayer td) {
        this(image, ImageButton._defaultThickness, controller, st, td);
    }
    
    public ImageTextButton(final Image image, final int thickness, final ImageButtonController controller, final String st, final TextDisplayer td) {
        super(image, thickness, controller);
        this.st = st;
        this.td = td;
    }
}
