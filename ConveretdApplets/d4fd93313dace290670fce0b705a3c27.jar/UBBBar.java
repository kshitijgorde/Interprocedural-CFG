import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBBar extends UBBArea
{
    protected boolean bypassInit;
    protected char align;
    protected char justify;
    protected int vSpace;
    protected int hSpace;
    protected boolean spaceEnds;
    
    public String[][] create() {
        int n = -1;
        final String[][] array = new String[(super.attributes != null) ? super.attributes.length : 1][2];
        super.attributes = super.create();
        int x = 0;
        int width = 0;
        int y = 0;
        int height = 0;
        boolean b = super.bounds.width > super.bounds.height;
        if (super.attributes != null) {
            for (int i = 0; i < super.attributes.length; ++i) {
                final String s = super.attributes[i][0];
                final String s2 = super.attributes[i][1];
                if (s != null && s2 != null) {
                    final int hashString = UBB.hashString(s);
                    try {
                        if (hashString == 3530) {
                            this.align = s2.toUpperCase().charAt(0);
                            if (this.align != 'N' && this.align != 'T' && this.align != 'M' && this.align != 'B') {
                                this.align = 'T';
                                throw new IllegalArgumentException();
                            }
                            super.attributes[i][0] = null;
                        }
                        else if (hashString == 6905) {
                            this.justify = s2.toUpperCase().charAt(0);
                            if (this.justify != 'N' && this.justify != 'L' && this.justify != 'C' && this.justify != 'R') {
                                this.justify = 'L';
                                throw new IllegalArgumentException();
                            }
                            super.attributes[i][0] = null;
                        }
                        else if (hashString == 5304) {
                            final char char1 = s2.charAt(0);
                            if (char1 == 'h' || char1 == 'H') {
                                b = true;
                            }
                            else {
                                if (char1 != 'v' && char1 != 'V') {
                                    throw new IllegalArgumentException();
                                }
                                b = false;
                            }
                            super.attributes[i][0] = null;
                        }
                        else if (hashString == 5097) {
                            this.hSpace = Integer.parseInt(s2);
                        }
                        else if (hashString == 5489) {
                            this.vSpace = Integer.parseInt(s2);
                        }
                        else if (hashString == 10952) {
                            this.spaceEnds = (s2.charAt(0) == 't');
                        }
                        else {
                            array[++n][0] = s;
                            array[n][1] = s2;
                        }
                    }
                    catch (Exception ex) {
                        super.error.notify(super.name, 1, "BAR attribute error " + s + "=" + s2, ex);
                    }
                }
            }
        }
        final int n2 = (super.children != null) ? super.children.length : 0;
        if (n2 > 0) {
            int n3 = 0;
            int n4 = 0;
            for (int j = n2 - 1; j >= 0; --j) {
                final UBBComponent ubbComponent = super.children[j];
                final Rectangle bounds = ubbComponent.getBounds();
                if (bounds.x < 0) {
                    final Rectangle rectangle = bounds;
                    rectangle.x += super.bounds.width;
                    if (bounds.x < 0) {
                        final Rectangle rectangle2 = bounds;
                        rectangle2.x -= super.bounds.width;
                    }
                }
                if (bounds.y < 0) {
                    final Rectangle rectangle3 = bounds;
                    rectangle3.y += super.bounds.height;
                    if (bounds.y < 0) {
                        final Rectangle rectangle4 = bounds;
                        rectangle4.y -= super.bounds.height;
                    }
                }
                if (bounds.width < 0) {
                    bounds.width = bounds.width + super.bounds.width - bounds.x;
                    if (bounds.x < 0) {
                        super.error.notify(super.name, 1, "Check width: might need absolute x", null);
                    }
                }
                if (bounds.height < 0) {
                    bounds.height = bounds.height + super.bounds.height - bounds.y;
                    if (bounds.y < 0) {
                        super.error.notify(super.name, 1, "Check height: might need absolute y", null);
                    }
                }
                if (b) {
                    if (bounds.height == 0) {
                        bounds.height = super.bounds.height - bounds.y - this.vSpace;
                        if (this.align == 'M') {
                            final Rectangle rectangle5 = bounds;
                            rectangle5.height -= this.vSpace;
                        }
                        if (bounds.height < 0) {
                            bounds.height = 5;
                            super.error.notify(super.name, 1, "Height for " + ubbComponent.getName() + " forced to 5", null);
                        }
                    }
                }
                else if (bounds.width == 0) {
                    bounds.width = super.bounds.width - bounds.x - this.hSpace;
                    if (this.justify == 'C') {
                        final Rectangle rectangle6 = bounds;
                        rectangle6.width -= this.hSpace;
                    }
                    if (bounds.width < 0) {
                        bounds.width = 5;
                        super.error.notify(super.name, 1, "Width for " + ubbComponent.getName() + " forced to 5", null);
                    }
                }
                if (b) {
                    if (bounds.width == 0) {
                        ++n4;
                    }
                    else {
                        if (bounds.x > n3) {
                            n3 += bounds.x - n3;
                        }
                        n3 += bounds.width;
                    }
                }
                else if (bounds.height == 0) {
                    ++n4;
                }
                else {
                    if (bounds.y > n3) {
                        n3 += bounds.y - n3;
                    }
                    n3 += bounds.height;
                }
            }
            int n5 = 0;
            int n6 = 0;
            if (this.align != 'N' || this.justify != 'N') {
                if (b) {
                    x = (this.spaceEnds ? this.hSpace : 0);
                    if (n4 > 0) {
                        final int n7 = this.spaceEnds ? (n2 + 1) : (n2 - 1);
                        width = (super.bounds.width - n3 - n7 * this.hSpace) / n4;
                        if (width < 0) {
                            super.error.notify(super.name, 1, "Buttons with no width forced to 5", null);
                            width = 5;
                        }
                        else {
                            n6 = super.bounds.width - n7 * this.hSpace - (width * n4 + n3);
                        }
                    }
                    else if (this.justify == 'C') {
                        x = (super.bounds.width - n3 - (n2 - 1) * this.hSpace) / 2;
                    }
                    else if (this.justify == 'R') {
                        x = super.bounds.width - n3 - (n2 - 1) * this.hSpace;
                    }
                }
                else {
                    y = (this.spaceEnds ? this.vSpace : 0);
                    if (n4 > 0) {
                        final int n8 = this.spaceEnds ? (n2 + 1) : (n2 - 1);
                        height = (super.bounds.height - n3 - n8 * this.vSpace) / n4;
                        if (height < 0) {
                            super.error.notify(super.name, 1, "Buttons with no height forced to 5", null);
                            height = 5;
                        }
                        else {
                            n5 = super.bounds.height - n8 * this.vSpace - (height * n4 + n3);
                        }
                    }
                    else if (this.align == 'M') {
                        y = (super.bounds.height - n3 - (n2 - 1) * this.vSpace) / 2;
                    }
                    else if (this.align == 'B') {
                        y = super.bounds.height - n3 - (n2 - 1) * this.vSpace;
                    }
                }
                for (int k = n2 - 1; k >= 0; --k) {
                    final UBBComponent ubbComponent2 = super.children[k];
                    final Rectangle bounds2 = ubbComponent2.getBounds();
                    if (this.align != 'N') {
                        if (b) {
                            if (this.align == 'T') {
                                bounds2.y = this.vSpace;
                            }
                            else if (this.align == 'M') {
                                bounds2.y = (super.bounds.height - bounds2.height) / 2;
                            }
                            else {
                                bounds2.y = super.bounds.height - bounds2.height - this.vSpace;
                            }
                        }
                        else {
                            bounds2.y = y;
                            if (bounds2.height == 0) {
                                bounds2.height = height;
                                if (k == 0) {
                                    final Rectangle rectangle7 = bounds2;
                                    rectangle7.height += n5;
                                }
                            }
                            y = y + bounds2.height + this.vSpace;
                        }
                    }
                    if (this.justify != 'N') {
                        if (b) {
                            bounds2.x = x;
                            if (bounds2.width == 0) {
                                bounds2.width = width;
                                if (k == 0) {
                                    final Rectangle rectangle8 = bounds2;
                                    rectangle8.width += n6;
                                }
                            }
                            x = x + bounds2.width + this.hSpace;
                        }
                        else if (this.justify == 'L') {
                            bounds2.x = this.hSpace;
                        }
                        else if (this.justify == 'C') {
                            bounds2.x = (super.bounds.width - bounds2.width) / 2;
                        }
                        else {
                            bounds2.x = super.bounds.width - bounds2.width - this.hSpace;
                        }
                    }
                    int n9 = bounds2.y + bounds2.height - super.bounds.height;
                    if (n9 < 0) {
                        n9 = 0;
                    }
                    int n10 = bounds2.x + bounds2.width - super.bounds.width;
                    if (n10 < 0) {
                        n10 = 0;
                    }
                    if (n9 > 0 || n10 > 0) {
                        super.error.notify(super.name, 1, ubbComponent2.getName() + " extends off bar: v=" + n9 + " h=" + n10, null);
                    }
                }
            }
        }
        if (!this.bypassInit && super.children != null) {
            for (int l = 0; l < super.children.length; ++l) {
                final String[][] create = super.children[l].create();
                if (create != null) {
                    for (int n11 = 0; n11 < create.length; ++n11) {
                        if (create[n11][0] != null) {
                            super.error.notify(super.children[l].getName(), 1, "Bad attribute " + create[n11][0] + "=" + create[n11][1], null);
                        }
                    }
                }
            }
        }
        if (n > -1) {
            return array;
        }
        return null;
    }
    
    public UBBBar() {
        this.align = 'T';
        this.justify = 'L';
    }
    
    public void destroy() {
        super.destroy();
    }
    
    public void childEvent(final UBBComponent ubbComponent, final int n) {
        if (n == 1 && (ubbComponent.getState() & 0x10) != 0x0) {
            for (int i = 0; i < super.children.length; ++i) {
                final UBBComponent ubbComponent2 = super.children[i];
                if (ubbComponent2 != ubbComponent && (ubbComponent2.getState() & 0x12) == 0x12 && ubbComponent2.setState(ubbComponent2.getName(), 1)) {
                    super.drawUpdatePending = true;
                }
            }
            if (super.drawUpdatePending) {
                this.updateUBBListeners(false);
            }
        }
    }
    
    public void init(final UBB ubb, final String s, final int n, final int n2, final String[][] array, final String s2, final UBBTag[] array2, final UBBImageFactory ubbImageFactory, final UBBTextTools ubbTextTools, final UBBAnimationTimer ubbAnimationTimer, final UBBErrorHandler ubbErrorHandler, final boolean bypassInit) {
        super.backgroundColor = null;
        super.init(ubb, s, n, n2, array, s2, array2, ubbImageFactory, ubbTextTools, ubbAnimationTimer, ubbErrorHandler, true);
        this.bypassInit = bypassInit;
    }
}
