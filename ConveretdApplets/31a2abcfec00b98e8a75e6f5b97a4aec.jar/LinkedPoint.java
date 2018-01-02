// 
// Decompiled by Procyon v0.5.30
// 

class LinkedPoint
{
    private double x;
    private double y;
    LinkedPoint next;
    LinkedPoint left;
    LinkedPoint right;
    
    LinkedPoint(final double x, final double y) {
        this.next = null;
        this.left = this;
        this.right = this;
        this.x = x;
        this.y = y;
    }
    
    double[] getPoint(double n) {
        n = n * 3.141592653589793 / 180.0;
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        return new double[] { this.x * cos - this.y * sin, this.x * sin + this.y * cos };
    }
    
    LinkedPoint getLinkedPoint(final double n) {
        final double[] point = this.getPoint(n);
        return new LinkedPoint(point[0], point[1]);
    }
    
    double distance(final LinkedPoint linkedPoint) {
        final double[] point = linkedPoint.getPoint(0.0);
        return Math.sqrt(Math.pow(this.x - point[0], 2.0) + Math.pow(this.y - point[1], 2.0));
    }
    
    double getx() {
        return this.x;
    }
    
    static boolean allLeft(final LinkedPoint[] array) {
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getx() > 0.0) {
                b = false;
            }
        }
        return b;
    }
    
    static boolean allRight(final LinkedPoint[] array) {
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getx() < 0.0) {
                b = false;
            }
        }
        return b;
    }
    
    double gety() {
        return this.y;
    }
    
    boolean below(final LinkedPoint[] array) {
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            if (this.y > array[i].gety()) {
                b = false;
            }
        }
        return b;
    }
    
    static boolean equal(final LinkedPoint[] array, final LinkedPoint[] array2) {
        boolean b = true;
        for (int i = 0; i < Math.min(array.length, array2.length); ++i) {
            if (array[i] != array2[i]) {
                b = false;
            }
        }
        return b;
    }
}
