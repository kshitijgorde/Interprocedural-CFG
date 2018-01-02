// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import java.util.Enumeration;
import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.missions.Mission;
import java.util.Vector;
import gravity.tools.Vector2D;

public class Probe extends GameObject
{
    private Vector2D _speed;
    private Vector _scannedObjects;
    private GameObject _scanningObject;
    private GameObject _hitObject;
    private Mission _mission;
    private int _ticks;
    private boolean _leavingShip;
    
    public Probe(final Vector2D vector2D, final Vector2D speed, final Mission mission) throws AssertionException {
        super(vector2D);
        this._ticks = 0;
        this._leavingShip = true;
        this._speed = speed;
        this._mission = mission;
        this._scannedObjects = new Vector();
        Assert.assert(this._speed != null && this._mission != null);
        Assert.assert(this._speed.x != 0.0 || this._speed.y != 0.0);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        doubleGraphics.setColor(super._color);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 1.0, 1.0);
    }
    
    public Vector2D getSpeed() {
        return (Vector2D)this._speed.clone();
    }
    
    public Mission getMission() {
        return this._mission;
    }
    
    public int getTicks() {
        return this._ticks;
    }
    
    public boolean getTargetScanned() {
        final Enumeration<GameObject> elements = this._scannedObjects.elements();
        while (elements.hasMoreElements()) {
            final GameObject gameObject = elements.nextElement();
            if (gameObject instanceof Target || gameObject instanceof UFO) {
                return true;
            }
        }
        return false;
    }
    
    public int getScannedPlanetsNr() {
        int n = 0;
        final Enumeration elements = this._scannedObjects.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement() instanceof Planet) {
                ++n;
            }
        }
        return n;
    }
    
    public boolean isLeaving() {
        return this._leavingShip;
    }
    
    public void move(final double n) {
        ++this._ticks;
        final Vector2D coord = super._coord;
        coord.x += this._speed.x * n;
        final Vector2D coord2 = super._coord;
        coord2.y += this._speed.y * n;
    }
    
    public boolean didHit(final GameObject hitObject) {
        try {
            if (hitObject != null && hitObject.isInside(this) && !this._leavingShip) {
                this._hitObject = hitObject;
                return true;
            }
            if (this._leavingShip && hitObject instanceof Ship && !hitObject.isInside(this)) {
                this._leavingShip = false;
            }
            return false;
        }
        catch (AssertionException ex) {
            return false;
        }
    }
    
    public void setSpeed(final Vector2D speed) throws AssertionException {
        this._speed = speed;
        Assert.assert(this._speed != null);
        Assert.assert(!this._speed.equals(new Vector2D(0.0, 0.0)));
    }
    
    public GameObject scan(final GameObject scanningObject) {
        final GameObject scanningObject2 = this._scanningObject;
        this._scanningObject = scanningObject;
        if (scanningObject2 != this._scanningObject) {
            if (scanningObject2 != null && !this._scannedObjects.contains(scanningObject2)) {
                this._scannedObjects.addElement(scanningObject2);
            }
            return scanningObject2;
        }
        return null;
    }
    
    public GameObject hitWhat() {
        return this._hitObject;
    }
    
    public void abort() {
        this._hitObject = this;
    }
    
    public boolean equals(final Probe probe) {
        return super.equals(probe) && this._speed.equals(probe.getSpeed()) && this._ticks == probe.getTicks();
    }
}
