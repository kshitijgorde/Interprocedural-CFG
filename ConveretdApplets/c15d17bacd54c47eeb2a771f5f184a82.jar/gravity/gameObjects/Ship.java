// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.missions.Mission;
import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.tools.Vector2D;

public class Ship extends GameObject
{
    private int _numberOfProbes;
    private AngleIndicator _angleIndicator;
    
    public Ship(final Vector2D vector2D, final double size, final int numberOfProbes) throws AssertionException {
        super(vector2D);
        super._size = size;
        this._numberOfProbes = numberOfProbes;
        (this._angleIndicator = new AngleIndicator(super._coord)).setColor(super._color);
        Assert.assert(super._size > 0.0);
        Assert.assert(this._numberOfProbes > 0);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        doubleGraphics.setColor(super._color);
        final double[] array = new double[3];
        final double[] array2 = new double[3];
        array[0] = super._coord.x + super._size / 4.0;
        array2[0] = super._coord.y - super._size / 2.0;
        array[1] = array[0];
        array2[1] = super._coord.y + super._size / 2.0;
        array[2] = super._coord.x + super._size;
        array2[2] = super._coord.y;
        doubleGraphics.drawPolygon(array, array2, 3);
        doubleGraphics.drawRoundRect(super._coord.x - super._size, super._coord.y - super._size / 2.0, super._size, super._size / 3.0, super._size / 3.0, super._size / 3.0);
        doubleGraphics.drawRect(super._coord.x - super._size / 3.0, super._coord.y - super._size / 6.0, super._size * 7.0 / 12.0, super._size / 3.0);
        doubleGraphics.drawRoundRect(super._coord.x - super._size, super._coord.y + super._size / 6.0, super._size, super._size / 3.0, super._size / 3.0, super._size / 3.0);
    }
    
    public double grav() {
        return super._size * super._size / 36.0;
    }
    
    public int getNumberOfProbes() {
        return this._numberOfProbes;
    }
    
    public boolean isInside(final GameObject gameObject) {
        final Vector2D coord = gameObject.getCoord();
        return coord.x >= super._coord.x - super._size && coord.x <= super._coord.x + super._size && coord.y >= super._coord.y - super._size / 2.0 && coord.y <= super._coord.y + super._size / 2.0;
    }
    
    public Probe launch(final Mission mission) throws AssertionException {
        Assert.assert(this._numberOfProbes > 0);
        --this._numberOfProbes;
        return new Probe((Vector2D)super._coord.clone(), this._angleIndicator.getVector(), mission);
    }
    
    public void setAngle(final double angle) {
        this._angleIndicator.setAngle(angle);
    }
    
    public double getAngle() {
        return this._angleIndicator.getAngle();
    }
    
    public AngleIndicator getAngleIndicator() {
        return this._angleIndicator;
    }
    
    public Vector2D getAngleVector() {
        return (Vector2D)this._angleIndicator.getVector().clone();
    }
    
    public void setAngleIndicatorVisible(final boolean visible) {
        this._angleIndicator.setVisible(visible);
    }
    
    public boolean isAngleIndicatorVisible() {
        return this._angleIndicator.isVisible();
    }
    
    public void setProbeInitialSpeed(final double absolute) {
        this._angleIndicator.setAbsolute(absolute);
    }
    
    public double getProbeInitialSpeed() {
        return this._angleIndicator.getAbsolute();
    }
    
    public void changeIntegrity(final double n) {
        super._integrity -= n;
    }
    
    public boolean equals(final Ship ship) {
        return super.equals(ship) && this._numberOfProbes == ship.getNumberOfProbes();
    }
}
