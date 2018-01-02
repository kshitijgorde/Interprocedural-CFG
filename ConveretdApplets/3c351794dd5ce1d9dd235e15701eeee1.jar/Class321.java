import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Component;
import java.awt.Robot;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class321
{
    private Robot aRobot2709;
    private Component aComponent2710;
    
    final void method3666(final Component component, final int[] array, final int n, final int n2, final Point point) {
        try {
            if (array != null) {
                final BufferedImage bufferedImage = new BufferedImage(n, n2, 2);
                bufferedImage.setRGB(0, 0, n, n2, array, 0, n);
                component.setCursor(component.getToolkit().createCustomCursor(bufferedImage, point, null));
            }
            else {
                component.setCursor(null);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method3667(Component aComponent2710, final boolean b) {
        try {
            if (!b) {
                if (aComponent2710 == null) {
                    throw new NullPointerException();
                }
            }
            else {
                aComponent2710 = null;
            }
            if (aComponent2710 != this.aComponent2710) {
                if (null != this.aComponent2710) {
                    this.aComponent2710.setCursor(null);
                    this.aComponent2710 = null;
                }
                if (aComponent2710 != null) {
                    aComponent2710.setCursor(aComponent2710.getToolkit().createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), null));
                    this.aComponent2710 = aComponent2710;
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class321() throws Exception {
        try {
            this.aRobot2709 = new Robot();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method3668(final int n, final int n2) {
        try {
            this.aRobot2709.mouseMove(n, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
