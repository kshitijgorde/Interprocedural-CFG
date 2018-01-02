// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Polygon;

public class betterPolygon extends Polygon
{
    public int leftX;
    public int topX;
    public int rightX;
    public int bottomX;
    public int leftY;
    public int topY;
    public int rightY;
    public int bottomY;
    
    public betterPolygon(final int[] array, final int[] array2) {
        super(array, array2, 5);
        this.defineCorners(array, array2);
    }
    
    public betterPolygon(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this(createArray(n, n3, n5, n7), createArray(n2, n4, n6, n8));
    }
    
    public betterPolygon(final int n, final int n2, final int n3, final int n4) {
        this(createArray(n, n + n3, n + n3, n), createArray(n2, n2, n2 + n4, n2 + n4));
    }
    
    static int[] createArray(final int n, final int n2, final int n3, final int n4) {
        return new int[] { n, n2, n3, n4, n };
    }
    
    void defineCorners(final int[] array, final int[] array2) {
        this.leftX = array[0];
        this.topX = array[1];
        this.rightX = array[2];
        this.bottomX = array[3];
        this.leftY = array2[0];
        this.topY = array2[1];
        this.rightY = array2[2];
        this.bottomY = array2[3];
    }
}
