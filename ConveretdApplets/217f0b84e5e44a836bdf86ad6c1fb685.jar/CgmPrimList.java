import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmPrimList extends Vector implements Visibility
{
    public CgmPrimList() {
    }
    
    public CgmPrimList(final int n) {
        super(n);
    }
    
    public CgmPrimList(final int n, final int n2) {
        super(n, n2);
    }
    
    final synchronized int find(final double n, final double n2) {
        for (int i = super.elementCount - 1; i >= 0; --i) {
            if (((CgmPrimitive)super.elementData[i]).find(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    final synchronized int find(final String s, int n) {
        for (int i = 0; i < super.elementCount; ++i) {
            final CgmPrimitive cgmPrimitive = (CgmPrimitive)super.elementData[i];
            if (cgmPrimitive instanceof CgmText && cgmPrimitive.find(s, n) == 0) {
                n = i;
            }
        }
        return n;
    }
    
    final synchronized void render(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b) {
        for (int i = 0; i < super.elementCount; ++i) {
            final CgmPrimitive cgmPrimitive = (CgmPrimitive)super.elementData[i];
            final int visibility = cgmPrimitive.visibility;
            if (visibility > 1 && (cgmPrimitive.noclip || (cgmPrimitive.x + cgmPrimitive.Width >= n3 && cgmPrimitive.x <= n4 && cgmPrimitive.y + cgmPrimitive.Height >= n5 && cgmPrimitive.y <= n6))) {
                cgmPrimitive.draw(graphics, n, n2, b & visibility == 3);
            }
        }
    }
    
    final synchronized int replaceText(int n, final String content) {
        for (int i = 0; i < super.elementCount; ++i) {
            final CgmPrimitive cgmPrimitive = (CgmPrimitive)super.elementData[i];
            if (cgmPrimitive instanceof CgmText && --n <= 0) {
                ((CgmText)cgmPrimitive).Content = content;
                return -1;
            }
        }
        return n;
    }
}
