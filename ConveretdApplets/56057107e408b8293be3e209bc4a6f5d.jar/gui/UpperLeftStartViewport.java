// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Rectangle;
import javax.swing.JViewport;

public class UpperLeftStartViewport extends JViewport
{
    public void scrollRectToVisible(final Rectangle rectangle) {
        rectangle.y = 0;
        super.scrollRectToVisible(rectangle);
    }
}
