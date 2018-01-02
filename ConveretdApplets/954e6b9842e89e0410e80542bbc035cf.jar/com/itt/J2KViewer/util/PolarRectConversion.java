// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

public class PolarRectConversion
{
    protected double x;
    protected double y;
    protected double origx;
    protected double origy;
    protected double theta;
    protected double rho;
    protected boolean polar;
    protected boolean rectangular;
    protected double m_width;
    protected double m_height;
    protected int quadrant;
    
    public PolarRectConversion() {
        this.x = 0.0;
        this.y = 0.0;
        this.origx = 0.0;
        this.origy = 0.0;
        this.theta = 0.0;
        this.rho = 0.0;
        this.polar = true;
        this.rectangular = true;
        this.m_width = 100.0;
        this.m_height = 100.0;
        this.quadrant = 0;
    }
    
    public void setWidth(final int n) {
        this.m_width = n;
    }
    
    public void setHeight(final int n) {
        this.m_height = n;
    }
    
    public double getX() {
        this.makeRectangular();
        return this.x;
    }
    
    public double getY() {
        this.makeRectangular();
        return this.y;
    }
    
    public double getTheta() {
        this.makePolar();
        return this.theta;
    }
    
    public double getThetaDegrees() {
        this.makePolar();
        return this.theta * 180.0 / 3.141592653589793;
    }
    
    public double getRho() {
        this.makePolar();
        return this.rho;
    }
    
    public void setRectangular(final double origx, final double origy) {
        this.origx = origx;
        this.origy = origy;
        this.x = origx - this.m_width / 2.0;
        this.y = this.m_height / 2.0 - origy;
        this.rectangular = true;
        this.polar = false;
    }
    
    public void setPolar(final double theta, final double rho) {
        this.theta = theta;
        this.rho = rho;
        this.rectangular = false;
        this.polar = true;
    }
    
    public void rotate(final double n) {
        this.setPolar(this.theta + n, this.rho);
    }
    
    public void offset(final double n, final double n2) {
        this.setRectangular(this.x + n, this.y + n2);
    }
    
    protected void makePolar() {
        if (!this.polar) {
            this.theta = Math.atan2(this.y, this.x);
            this.rho = this.y / Math.sin(this.theta);
            this.polar = true;
        }
    }
    
    protected void makeRectangular() {
        if (!this.rectangular) {
            this.x = this.rho * Math.sin(this.theta);
            this.y = this.rho * Math.cos(this.theta);
            this.rectangular = true;
        }
    }
    
    public String toString() {
        return "(" + this.origx + ", " + this.origy + ")(" + this.getX() + ", " + this.getY() + ")[" + this.getTheta() + " : " + this.getRho() + "]";
    }
    
    public static void main(final String[] array) {
        final PolarRectConversion polarRectConversion = new PolarRectConversion();
        polarRectConversion.setWidth(100);
        polarRectConversion.setHeight(100);
        polarRectConversion.setRectangular(75.0, 25.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
        polarRectConversion.setRectangular(75.0, 75.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
        polarRectConversion.setRectangular(25.0, 75.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
        polarRectConversion.setRectangular(25.0, 25.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
        polarRectConversion.setRectangular(25.0, 25.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
        polarRectConversion.setRectangular(25.0, 50.0);
        System.out.println("p1 =" + polarRectConversion);
        System.out.println("theta =" + polarRectConversion.getThetaDegrees());
    }
    
    public int getQuadrant() {
        final double n = 22.5;
        final double thetaDegrees = this.getThetaDegrees();
        int n2;
        if (n * -1.0 <= thetaDegrees && thetaDegrees < n) {
            n2 = 2;
        }
        else if (n <= thetaDegrees && thetaDegrees < n * 3.0) {
            n2 = 1;
        }
        else if (n * 3.0 <= thetaDegrees && thetaDegrees < n * 5.0) {
            n2 = 0;
        }
        else if (n * 5.0 <= thetaDegrees && thetaDegrees < n * 7.0) {
            n2 = 7;
        }
        else if (n * -7.0 <= thetaDegrees && thetaDegrees < n * -5.0) {
            n2 = 5;
        }
        else if (n * -5.0 <= thetaDegrees && thetaDegrees < n * -3.0) {
            n2 = 4;
        }
        else if (n * -3.0 <= thetaDegrees && thetaDegrees < n * -1.0) {
            n2 = 3;
        }
        else {
            n2 = 6;
        }
        return n2;
    }
}
