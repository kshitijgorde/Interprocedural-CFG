// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Image;
import MudFE.TextDisplayer;

public class DualImageTextButton extends ImageTextButton
{
    String st;
    TextDisplayer td;
    Image image1;
    Image image2;
    
    public void paintInset() {
        this.setImage(this.image1);
        super.paintInset();
    }
    
    public DualImageTextButton(final Image image1, final Image image2, final String st, final TextDisplayer td) {
        this(image1, image2, ImageButton._defaultThickness, null, st, td);
    }
    
    public DualImageTextButton(final Image image1, final Image image2, final ImageButtonController controller, final String st, final TextDisplayer td) {
        this(image1, image2, ImageButton._defaultThickness, controller, st, td);
    }
    
    public DualImageTextButton(final Image image1, final Image image2, final int thickness, final ImageButtonController controller, final String st, final TextDisplayer td) {
        super(image1, thickness, controller, st, td);
        this.image1 = image1;
        this.image2 = image2;
        this.st = st;
        this.td = td;
    }
    
    public void paintRaised() {
        this.setImage(this.image2);
        super.paintRaised();
    }
}
