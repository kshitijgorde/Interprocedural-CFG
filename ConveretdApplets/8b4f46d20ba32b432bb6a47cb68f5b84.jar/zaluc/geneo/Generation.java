// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Dimension;

class Generation
{
    private PeopleList plist;
    public Generation nextGeneration;
    public DrawingObject firstInGeneration;
    public Dimension dim;
    public int leftX;
    public int spouseLineXOffset;
    public int maxVertLineOffset;
    
    public Generation(final PeopleList plist) {
        this.plist = plist;
        this.nextGeneration = null;
        this.firstInGeneration = null;
        this.dim = new Dimension(0, 0);
        this.maxVertLineOffset = 0;
    }
    
    public int calcChildDim(final boolean b, final boolean b2, final int n) {
        int n2 = Integer.MAX_VALUE;
        int max = 0;
        final Dimension dim = this.dim;
        final Dimension dim2 = this.dim;
        final boolean b3 = false;
        dim2.height = (b3 ? 1 : 0);
        dim.width = (b3 ? 1 : 0);
        for (DrawingObject drawingObject = this.firstInGeneration; drawingObject != null; drawingObject = drawingObject.nextInGeneration) {
            this.dim.width = Math.max(this.dim.width, drawingObject.rect.width);
            final Dimension dim3 = this.dim;
            dim3.height += drawingObject.rect.height;
            n2 = Math.min(n2, drawingObject.rect.width);
            int n3 = 0;
            int n4 = 0;
            DrawingObject next = null;
            DrawingObject next2 = null;
            for (int size = drawingObject.spouses.size(), i = 0; i < size; ++i) {
                final DrawingObject drawingObject2 = drawingObject.spouses.elementAt(i);
                this.dim.width = Math.max(this.dim.width, drawingObject2.rect.width);
                final Dimension dim4 = this.dim;
                dim4.height += drawingObject2.rect.height;
                n2 = Math.min(n2, drawingObject2.rect.width);
                drawingObject2.vertChildLineOffset = 0;
                if (b2) {
                    boolean b6;
                    boolean b5;
                    boolean b4 = b5 = (b6 = false);
                    for (int size2 = drawingObject2.children.size(), j = 0; j < size2; ++j) {
                        final DrawingObject drawingObject3 = drawingObject2.children.elementAt(j);
                        if (drawingObject3.rect.y < drawingObject2.rect.y - this.plist.vertSpace) {
                            b5 = true;
                        }
                        else if (drawingObject3.rect.y > drawingObject2.rect.y + this.plist.vertSpace) {
                            b4 = true;
                        }
                        else {
                            b6 = true;
                        }
                    }
                    if (!b6) {
                        if (b5 && !b4) {
                            ++n3;
                            drawingObject2.next = next;
                            next = drawingObject2;
                        }
                        else if (b4 && !b5) {
                            ++n4;
                            drawingObject2.next = next2;
                            next2 = drawingObject2;
                        }
                    }
                }
            }
            if (b2) {
                max = Math.max(max, Math.max(n3, n4));
                int vertChildLineOffset = n3 * this.plist.vertLineOffset;
                for (DrawingObject next3 = next; next3 != null; next3 = next3.next) {
                    next3.vertChildLineOffset = vertChildLineOffset;
                    vertChildLineOffset -= this.plist.vertLineOffset;
                }
                int vertChildLineOffset2 = n4 * this.plist.vertLineOffset;
                for (DrawingObject next4 = next2; next4 != null; next4 = next4.next) {
                    next4.vertChildLineOffset = vertChildLineOffset2;
                    vertChildLineOffset2 -= this.plist.vertLineOffset;
                }
            }
        }
        for (DrawingObject drawingObject4 = this.firstInGeneration; drawingObject4 != null; drawingObject4 = drawingObject4.nextInGeneration) {
            drawingObject4.rect.width = this.dim.width;
            for (int size3 = drawingObject4.spouses.size(), k = 0; k < size3; ++k) {
                ((DrawingObject)drawingObject4.spouses.elementAt(k)).rect.width = this.dim.width;
            }
        }
        this.maxVertLineOffset = max * this.plist.vertLineOffset;
        final Dimension dim5 = this.dim;
        dim5.width += this.maxVertLineOffset + this.plist.horzSpace;
        if (b) {
            this.leftX = n - this.dim.width / 2;
        }
        else {
            this.leftX = n - this.dim.width;
        }
        this.spouseLineXOffset = n2 / 2;
        return this.leftX;
    }
    
    public int calcParentDim(final int leftX) {
        final Dimension dim = this.dim;
        final Dimension dim2 = this.dim;
        final boolean b = false;
        dim2.height = (b ? 1 : 0);
        dim.width = (b ? 1 : 0);
        this.leftX = leftX;
        for (DrawingObject drawingObject = this.firstInGeneration; drawingObject != null; drawingObject = drawingObject.nextInGeneration) {
            drawingObject.setX(leftX + this.plist.trunkLen);
            drawingObject.vertChildLineOffset = 0;
            this.dim.width = Math.max(this.dim.width, drawingObject.rect.width);
            final Dimension dim3 = this.dim;
            dim3.height += drawingObject.rect.height;
        }
        for (DrawingObject drawingObject2 = this.firstInGeneration; drawingObject2 != null; drawingObject2 = drawingObject2.nextInGeneration) {
            drawingObject2.rect.width = this.dim.width;
        }
        final Dimension dim4 = this.dim;
        dim4.width += this.plist.horzSpace;
        return leftX + this.dim.width;
    }
    
    public boolean shouldDrawGenAsChild() {
        return this.leftX + this.dim.width + this.plist.shiftX >= 0;
    }
    
    public boolean shouldDrawGenAsParent() {
        return this.leftX + this.plist.shiftX <= this.plist.screenWidth;
    }
}
