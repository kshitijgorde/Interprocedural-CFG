// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

public class HomingMissile extends Missile
{
    private static final double HOMING_SEEKER_ANGLE_OF_VIEW = 45.0;
    private static final double MISSILE_ROTATIONAL_SPEED = 20.0;
    private static final long AI_INTERVAL = 100L;
    private long nextAITimer;
    private PolygonGameObject currentTarget;
    
    public HomingMissile(final HighPrecisionPoint createLocation, final double heading, final Ship firedBy) {
        super(createLocation, heading, firedBy);
        this.currentTarget = null;
        this.nextAITimer = System.currentTimeMillis();
    }
    
    protected void homeIn(final double angle, final double gameTickInterval) {
        double angleLeft;
        if (super.objectHeading - angle > 0) {
            angleLeft = super.objectHeading - angle;
        }
        else {
            angleLeft = super.objectHeading + (360 - angle);
        }
        double angleRight;
        if (angle - super.objectHeading > 0) {
            angleRight = angle - super.objectHeading;
        }
        else {
            angleRight = 360 - super.objectHeading + angle;
        }
        if (angleRight < angleLeft) {
            final double n = super.objectHeading + 20.0 * gameTickInterval;
            super.objectHeading = n;
            super.objectHeading = Util.fixDegrees(n);
        }
        else {
            final double n2 = super.objectHeading - 20.0 * gameTickInterval;
            super.objectHeading = n2;
            super.objectHeading = Util.fixDegrees(n2);
        }
        super.objectMovementDirection = super.objectHeading;
    }
    
    private void acquireTarget() {
        double bestAngleToTarget = 9.99999999E8;
        this.currentTarget = null;
        int i = 0;
        while (true) {
            try {
                final PolygonGameObject objToCheck = GameObject.asteroidsGame.collidableObjectList.elementAt(i);
                if (objToCheck != this && (objToCheck instanceof Asteroid || objToCheck instanceof Ship)) {
                    final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                    final double targetHeadingDifference = Util.differenceBetweenAngles(super.objectMovementDirection, angleToObject);
                    if (targetHeadingDifference < 45.0 && targetHeadingDifference < bestAngleToTarget && this.clearLineToObject(objToCheck)) {
                        this.currentTarget = objToCheck;
                        bestAngleToTarget = targetHeadingDifference;
                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
            ++i;
        }
    }
    
    public void gameTick(final double gameTickInterval) {
        super.gameTick(gameTickInterval);
        if (this.nextAITimer < System.currentTimeMillis()) {
            if (this.currentTarget == null || !this.clearLineToObject(this.currentTarget) || Util.differenceBetweenAngles(super.objectLocation.getAngleBetween(this.currentTarget.objectLocation), super.objectMovementDirection) > 45.0) {
                this.acquireTarget();
            }
            this.nextAITimer = System.currentTimeMillis() + 100L;
        }
        if (this.currentTarget != null) {
            this.homeIn(super.objectLocation.getAngleBetween(this.currentTarget.objectLocation), gameTickInterval);
        }
    }
}
