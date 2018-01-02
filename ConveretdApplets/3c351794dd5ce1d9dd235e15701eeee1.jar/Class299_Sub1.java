import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Component;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class299_Sub1 extends Class299 implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private Class148 aClass148_5277;
    private int anInt5278;
    private int anInt5279;
    private int anInt5280;
    private int anInt5281;
    private int anInt5282;
    private int anInt5283;
    private Class148 aClass148_5284;
    private Component aComponent5285;
    private boolean aBoolean5286;
    
    @Override
    public final synchronized void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        try {
            this.method3517(mouseWheelEvent.getWheelRotation(), (byte)(-127), mouseWheelEvent.getX(), 6, mouseWheelEvent.getY());
            mouseWheelEvent.consume();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mousePressed(final MouseEvent mouseEvent) {
        try {
            final int method3519 = this.method3519(mouseEvent, 2);
            if (method3519 == 1) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-127), mouseEvent.getX(), 0, mouseEvent.getY());
            }
            else if (4 == method3519) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-107), mouseEvent.getX(), 2, mouseEvent.getY());
            }
            else if (method3519 == 2) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-113), mouseEvent.getX(), 1, mouseEvent.getY());
            }
            this.anInt5283 |= method3519;
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method3510(final byte b) {
        boolean b2;
        try {
            if (b != -19) {
                this.aBoolean5286 = true;
            }
            if (~(0x2 & this.anInt5280) != -1) {
                return true;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    public final synchronized void mouseReleased(final MouseEvent mouseEvent) {
        try {
            int n = this.method3519(mouseEvent, 2);
            if (~(n & this.anInt5283) == -1) {
                n = this.anInt5283;
            }
            if (0x0 != (n & 0x1)) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-111), mouseEvent.getX(), 3, mouseEvent.getY());
            }
            if (~(n & 0x4) != -1) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-114), mouseEvent.getX(), 5, mouseEvent.getY());
            }
            if (-1 != ~(0x2 & n)) {
                this.method3517(mouseEvent.getClickCount(), (byte)(-127), mouseEvent.getX(), 4, mouseEvent.getY());
            }
            this.anInt5283 &= ~n;
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final synchronized void method3516(final byte b) {
        try {
            this.anInt5280 = this.anInt5283;
            this.anInt5278 = this.anInt5282;
            this.anInt5279 = this.anInt5281;
            final Class148 aClass148_5277 = this.aClass148_5277;
            this.aClass148_5277 = this.aClass148_5284;
            this.aClass148_5284 = aClass148_5277;
            if (b < 103) {
                this.anInt5281 = -46;
            }
            this.aClass148_5284.method2422((byte)47);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method3512(final int n) {
        boolean b;
        try {
            if (n != 1) {
                this.mouseReleased(null);
            }
            if (0x0 != (this.anInt5280 & 0x4)) {
                return true;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    final int method3507(final byte b) {
        int anInt5278;
        try {
            if (b < 24) {
                this.method3518(null, (byte)(-4));
            }
            anInt5278 = this.anInt5278;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt5278;
    }
    
    @Override
    final void method3515(final int n) {
        try {
            this.method3520(-87);
            if (n >= -110) {
                this.aBoolean5286 = true;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Class98_Sub17 method3508(final int n) {
        Class98_Sub17 class98_Sub17;
        try {
            if (n != 600) {
                this.anInt5281 = -94;
            }
            class98_Sub17 = (Class98_Sub17)this.aClass148_5277.method2421(n ^ 0x1B06);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class98_Sub17;
    }
    
    private final void method3517(final int anInt5784, final byte b, final int anInt5785, final int anInt5786, final int anInt5787) {
        try {
            final Class98_Sub17_Sub2 class98_Sub17_Sub2 = new Class98_Sub17_Sub2();
            class98_Sub17_Sub2.anInt5785 = anInt5787;
            class98_Sub17_Sub2.anInt5786 = anInt5786;
            class98_Sub17_Sub2.anInt5787 = anInt5785;
            class98_Sub17_Sub2.aLong5788 = Class343.method3819(-47);
            class98_Sub17_Sub2.anInt5784 = anInt5784;
            this.aClass148_5284.method2419(class98_Sub17_Sub2, -20911);
            if (b > -106) {
                this.anInt5278 = -78;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final void method3518(final Component aComponent5285, final byte b) {
        try {
            this.method3520(b - 153);
            this.aComponent5285 = aComponent5285;
            if (b != 88) {
                this.aClass148_5284 = null;
            }
            this.aComponent5285.addMouseListener(this);
            this.aComponent5285.addMouseMotionListener(this);
            this.aComponent5285.addMouseWheelListener(this);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mouseDragged(final MouseEvent mouseEvent) {
        try {
            this.method3521(-11571, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mouseMoved(final MouseEvent mouseEvent) {
        try {
            this.method3521(-11571, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mouseExited(final MouseEvent mouseEvent) {
        try {
            this.method3521(-11571, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mouseEntered(final MouseEvent mouseEvent) {
        try {
            this.method3521(-11571, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final synchronized void mouseClicked(final MouseEvent mouseEvent) {
        try {
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final int method3519(final MouseEvent mouseEvent, final int n) {
        boolean b;
        try {
            if (n != 2) {
                return 56;
            }
            if (mouseEvent.getButton() == 1) {
                if (mouseEvent.isMetaDown()) {
                    return 4;
                }
                return 1;
            }
            else {
                if (mouseEvent.getButton() == 2) {
                    return 2;
                }
                if (mouseEvent.getButton() == 3) {
                    return 4;
                }
                b = false;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b ? 1 : 0;
    }
    
    private final void method3520(final int n) {
        try {
            if (null != this.aComponent5285) {
                this.aComponent5285.removeMouseWheelListener(this);
                this.aComponent5285.removeMouseMotionListener(this);
                this.aComponent5285.removeMouseListener(this);
                this.aClass148_5284 = null;
                final boolean anInt5281 = false;
                this.anInt5283 = (anInt5281 ? 1 : 0);
                this.anInt5282 = (anInt5281 ? 1 : 0);
                this.anInt5281 = (anInt5281 ? 1 : 0);
                final boolean anInt5282 = false;
                this.anInt5280 = (anInt5282 ? 1 : 0);
                this.anInt5278 = (anInt5282 ? 1 : 0);
                this.anInt5279 = (anInt5282 ? 1 : 0);
                this.aComponent5285 = null;
                this.aClass148_5277 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int method3514(final int n) {
        int anInt5279;
        try {
            if (n <= 4) {
                this.anInt5278 = 117;
            }
            anInt5279 = this.anInt5279;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt5279;
    }
    
    private final void method3521(final int n, final int anInt5281, final int anInt5282) {
        try {
            this.anInt5282 = anInt5282;
            this.anInt5281 = anInt5281;
            if (n != -11571) {
                this.anInt5279 = 31;
            }
            if (this.aBoolean5286) {
                this.method3517(0, (byte)(-125), anInt5281, -1, anInt5282);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method3506(final byte b) {
        boolean b2;
        try {
            if ((this.anInt5280 & 0x1) != 0x0) {
                return true;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    Class299_Sub1(final Component component, final boolean aBoolean5286) {
        this.aClass148_5277 = new Class148();
        this.aClass148_5284 = new Class148();
        try {
            this.method3518(component, (byte)88);
            this.aBoolean5286 = aBoolean5286;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
