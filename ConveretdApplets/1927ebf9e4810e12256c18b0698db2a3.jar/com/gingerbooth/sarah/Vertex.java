// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

public class Vertex
{
    public int angleIn;
    public int angleOut;
    public double x;
    public double y;
    public static final int PT_OUTSIDE = 0;
    public static final int PT_ON_EDGE = 1;
    public static final int PT_ON_VERTEX = 2;
    public static final int PT_INSIDE = 4;
    private static double unitAngle;
    private static double[] sines;
    private static double[] cosines;
    
    static {
        Vertex.unitAngle = 0.5235987755982988;
        Vertex.sines = new double[] { Math.sin(0.0), Math.sin(Vertex.unitAngle), Math.sin(2.0 * Vertex.unitAngle), Math.sin(3.0 * Vertex.unitAngle), Math.sin(4.0 * Vertex.unitAngle), Math.sin(5.0 * Vertex.unitAngle), Math.sin(6.0 * Vertex.unitAngle), Math.sin(7.0 * Vertex.unitAngle), Math.sin(8.0 * Vertex.unitAngle), Math.sin(9.0 * Vertex.unitAngle), Math.sin(10.0 * Vertex.unitAngle), Math.sin(11.0 * Vertex.unitAngle), Math.sin(0.0) };
        Vertex.cosines = new double[] { Math.cos(0.0), Math.cos(Vertex.unitAngle), Math.cos(2.0 * Vertex.unitAngle), Math.cos(3.0 * Vertex.unitAngle), Math.cos(4.0 * Vertex.unitAngle), Math.cos(5.0 * Vertex.unitAngle), Math.cos(6.0 * Vertex.unitAngle), Math.cos(7.0 * Vertex.unitAngle), Math.cos(8.0 * Vertex.unitAngle), Math.cos(9.0 * Vertex.unitAngle), Math.cos(10.0 * Vertex.unitAngle), Math.cos(11.0 * Vertex.unitAngle), Math.cos(0.0) };
    }
    
    public Vertex(final int n, final int n2, final double x, final double y) {
        this.angleIn = (n + 12) % 12;
        this.angleOut = (n2 + 12) % 12;
        this.x = x;
        this.y = y;
    }
    
    public Vertex(final Vertex vertex, final int n) {
        this.angleOut = (n + 12) % 12;
        this.angleIn = (vertex.getAngleOut() + 6 + 12) % 12;
        this.x = vertex.nextX();
        this.y = vertex.nextY();
    }
    
    public boolean angleBetween(final double n, final double n2, final double n3) {
        double n4 = n2 - n3;
        if (n4 < 0.0) {
            n4 += 6.283185307179586;
        }
        double n5 = n - n3;
        if (n5 < 0.0) {
            n5 += 6.283185307179586;
        }
        return n4 < n5;
    }
    
    public double angleToward(final double n, final double n2) {
        final double distanceTo = this.distanceTo(n, n2);
        double acos = 0.0;
        if (distanceTo > 0.0) {
            double abs = Math.abs((n - this.x) / distanceTo);
            if (abs > 1.0) {
                abs = 1.0;
            }
            acos = Math.acos(abs);
            if (n - this.x < 0.0) {
                acos = 3.141592653589793 - acos;
            }
            if (n2 - this.y < 0.0) {
                acos = 6.283185307179586 - acos;
            }
        }
        return acos;
    }
    
    public Object clone() {
        return new Vertex(this.angleIn, this.angleOut, this.x, this.y);
    }
    
    public double distanceTo(final double n, final double n2) {
        final double n3 = n - this.x;
        final double n4 = n2 - this.y;
        return Math.sqrt(n3 * n3 + n4 * n4);
    }
    
    public boolean equalAngles(final double n, final double n2) {
        return Math.abs((n - n2) / (2.0 * (n + n2))) <= 0.01;
    }
    
    public int getAngle() {
        return (12 + this.angleOut - this.angleIn) % 12;
    }
    
    public int getAngleIn() {
        return this.angleIn;
    }
    
    public int getAngleOut() {
        return this.angleOut;
    }
    
    public static double getCos(final int n) {
        return Vertex.cosines[n];
    }
    
    public static int getOppAngle(final int n) {
        return (6 + n) % 12;
    }
    
    public static double getRadian(final int n) {
        return n * Vertex.unitAngle;
    }
    
    public static double getSin(final int n) {
        return Vertex.sines[n];
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double nextX() {
        return this.x + Tableau.edgeLength * Vertex.cosines[this.angleOut];
    }
    
    public double nextY() {
        return this.y + Tableau.edgeLength * Vertex.sines[this.angleOut];
    }
    
    public boolean onVertex(final double n, final double n2) {
        final double n3 = n - this.x;
        final double n4 = n2 - this.y;
        return Math.sqrt(n3 * n3 + n4 * n4) < Tableau.getMinResolution();
    }
    
    public double prevX() {
        return this.x + Tableau.edgeLength * Vertex.cosines[this.angleIn];
    }
    
    public double prevY() {
        return this.y + Tableau.edgeLength * Vertex.sines[this.angleIn];
    }
    
    public int ptInAngle(final double n, final double n2) {
        if (this.onVertex(n, n2)) {
            return 2;
        }
        final double radian = getRadian(this.angleIn);
        final double radian2 = getRadian(this.angleOut);
        final double angleToward = this.angleToward(n, n2);
        if (this.equalAngles(radian2, angleToward) && this.distanceTo(n, n2) < Tableau.edgeLength) {
            return 1;
        }
        if (this.angleBetween(radian, angleToward, radian2)) {
            return 4;
        }
        return 0;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public String toString() {
        return "Vertex[" + this.x + "," + this.y + ", in=" + this.angleIn + ", out=" + this.angleOut + "]";
    }
}
