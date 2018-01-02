// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

public class DemoPlayerShip extends PlayerShip
{
    protected static final double MAX_OBJECT_SEARCH_DISTANCE = 9.99999999E8;
    protected static final double THREAT_DISTANCE_THRESHOLD = 200.0;
    protected static final double PLAYER_SHIP_TARGET_SPEED = 100.0;
    protected static final long PLAYER_SHIP_DEFAULT_MOVE_TO_DURATION = 3000L;
    protected static final long PLAYER_SHIP_MISSILE_LAUNCH_INTERVAL = 3000L;
    protected int currentAIAction;
    protected PolygonGameObject currentTarget;
    protected PolygonGameObject currentThreat;
    protected HighPrecisionPoint currentDestination;
    protected double currentThrustBurstAngle;
    protected long gunFireInterval;
    PolygonGameObject nearestAsteroid;
    PolygonGameObject nearestEnemyShip;
    PolygonGameObject nearestThreat;
    PolygonGameObject nearestPowerup;
    protected static final int AI_ACTION_NULL = 0;
    protected static final int AI_ACTION_FIRE_ON_TARGET = 1;
    protected static final int AI_ACTION_MOVE_TO = 2;
    protected static final int AI_ACTION_AVOID_TARGET = 3;
    protected static final int AI_ACTION_INTERCEPT_POWERUP = 4;
    protected long nextShotTimer;
    protected long nextMissileLaunchTimer;
    protected long nextAIActionEvaluationTimer;
    protected long nextAISubActionEvaluationTimer;
    protected int currentMissileLauncher;
    
    public DemoPlayerShip(final HighPrecisionPoint createLocation) {
        super(createLocation, 60.0);
        this.currentAIAction = 0;
        this.currentTarget = null;
        this.currentDestination = null;
        this.nextShotTimer = System.currentTimeMillis();
        this.nextMissileLaunchTimer = System.currentTimeMillis();
        this.nextAIActionEvaluationTimer = 0L;
        this.nextAISubActionEvaluationTimer = 0L;
        this.nearestThreat = null;
        this.nearestPowerup = null;
        this.gunFireInterval = 200L;
    }
    
    protected void rotateTo(final double angle) {
        if (Util.anglesSimilar(super.objectHeading, angle, 5.0)) {
            super.objectHeading = angle;
            super.objectRotationalSpeed = 0.0;
        }
        else {
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
                super.objectRotationalSpeed = 120.0;
            }
            else {
                super.objectRotationalSpeed = -120.0;
            }
        }
    }
    
    protected void avoidCurrentTarget(final double gameTickInterval) {
        this.rotateTo(this.currentThrustBurstAngle = Util.fixDegrees(this.currentThrustBurstAngle));
        if (Util.anglesSimilar(super.objectHeading, this.currentThrustBurstAngle, 10.0) && super.objectMovementSpeed <= Math.max(this.currentTarget.objectMovementSpeed * 2, 100.0)) {
            this.fireThruster(gameTickInterval, PlayerShip.engineLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
        }
    }
    
    protected void interceptObject(final double gameTickInterval, final PolygonGameObject o) {
        if (o != null) {
            super.objectRotationalSpeed = 0.0;
            final double angleToDestination = super.objectLocation.getAngleBetween(o.objectLocation);
            final double distanceToDestination = super.objectLocation.getDistanceBetween(super.objectLocation);
            if (super.objectHeading < angleToDestination + 2 && super.objectHeading > angleToDestination - 2) {
                super.objectRotationalSpeed = 0.0;
                super.objectHeading = angleToDestination;
                if (super.objectMovementSpeed < 100.0) {
                    this.fireThruster(gameTickInterval, PlayerShip.engineLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
                }
            }
            else {
                this.rotateTo(angleToDestination);
            }
        }
        else {
            this.nextAIActionEvaluationTimer = 0L;
        }
    }
    
    protected void moveToCurrentDestination(final double gameTickInterval) {
        if (this.currentDestination != null) {
            super.objectRotationalSpeed = 0.0;
            final double angleToDestination = super.objectLocation.getAngleBetween(this.currentDestination);
            final double distanceToDestination = super.objectLocation.getDistanceBetween(this.currentDestination);
            if (super.objectHeading < angleToDestination + 2 && super.objectHeading > angleToDestination - 2) {
                super.objectRotationalSpeed = 0.0;
                super.objectHeading = angleToDestination;
                if (super.objectMovementSpeed < 100.0) {
                    this.fireThruster(gameTickInterval, PlayerShip.engineLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
                }
            }
            else {
                this.rotateTo(angleToDestination);
            }
        }
        else {
            this.nextAIActionEvaluationTimer = 0L;
        }
    }
    
    protected void fireOnCurrentTarget() {
        if (this.currentTarget != null && this.currentTarget.isAlive) {
            final double angleToTarget = super.objectLocation.getAngleBetween(this.currentTarget.objectLocation);
            this.rotateTo(angleToTarget);
            if (Util.anglesSimilar(super.objectHeading, angleToTarget, 5.0)) {
                this.fireGun();
                if (super.missilesRemaining > 0 && (this.currentTarget.healthLevel >= 3 * (1000 / this.gunFireInterval) || this.currentTarget instanceof EnemyShip)) {
                    this.fireMissiles();
                }
            }
        }
        else {
            this.nextAIActionEvaluationTimer = 0L;
        }
    }
    
    protected void scanGameObjects() {
        double closestThreatDistance = 9.99999999E8;
        double closestPowerupDistance = 9.99999999E8;
        double closestAsteroidDistance = 9.99999999E8;
        double closestEnemyShipDistance = 9.99999999E8;
        int i = 0;
        while (true) {
            Label_0451: {
                try {
                    final PolygonGameObject objToCheck = GameObject.asteroidsGame.collidableObjectList.elementAt(i);
                    if (objToCheck != this && (objToCheck instanceof Asteroid || (objToCheck instanceof Missile && ((Missile)objToCheck).shipFiredBy instanceof PlayerShip) || objToCheck instanceof Ship) && objToCheck.isAlive) {
                        final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                        final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                        if (objectDistance < 200.0 && objectDistance < closestThreatDistance && (!Util.anglesSimilar(angleToObject, objToCheck.objectMovementDirection, 90.0) || Util.anglesSimilar(angleToObject, super.objectMovementDirection, 90.0))) {
                            this.nearestThreat = objToCheck;
                            closestThreatDistance = objectDistance;
                        }
                    }
                    if (objToCheck != this && objToCheck instanceof Asteroid) {
                        final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                        final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                        if (this.clearLineToObject(objToCheck) && objectDistance < closestAsteroidDistance) {
                            this.nearestAsteroid = objToCheck;
                            closestAsteroidDistance = objectDistance;
                        }
                    }
                    if (objToCheck != this && objToCheck instanceof EnemyShip) {
                        final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                        final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                        if (this.clearLineToObject(objToCheck) && objectDistance < closestEnemyShipDistance && !objToCheck.isInvincible()) {
                            this.nearestEnemyShip = objToCheck;
                            closestEnemyShipDistance = objectDistance;
                        }
                    }
                    if (objToCheck == this || !(objToCheck instanceof Powerup)) {
                        break Label_0451;
                    }
                    final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                    final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                    if (this.clearLineToObject(objToCheck) && objectDistance < closestPowerupDistance) {
                        this.nearestPowerup = objToCheck;
                        closestPowerupDistance = objectDistance;
                    }
                    break Label_0451;
                }
                catch (IndexOutOfBoundsException e) {
                    if (closestThreatDistance == 9.99999999E8) {
                        this.nearestThreat = null;
                    }
                    if (closestPowerupDistance == 9.99999999E8) {
                        this.nearestPowerup = null;
                    }
                    if (closestAsteroidDistance == 9.99999999E8) {
                        this.nearestAsteroid = null;
                    }
                    if (closestEnemyShipDistance == 9.99999999E8) {
                        this.nearestEnemyShip = null;
                    }
                    return;
                    ++i;
                    continue;
                }
            }
            break;
        }
    }
    
    private HighPrecisionPoint getNewRandomDestination() {
        return new HighPrecisionPoint(100 + Math.random() * 600, 100 + Math.random() * 400);
    }
    
    protected void evaluateAIAction() {
        this.scanGameObjects();
        if (this.nearestThreat != null) {
            if (this.nearestThreat != this.currentThreat) {
                this.currentTarget = this.nearestThreat;
                this.currentThreat = this.nearestThreat;
                if ((this.nearestThreat instanceof EnemyShip || this.nearestThreat instanceof Asteroid) && this.nearestThreat.healthLevel <= 3 * (1000 / this.gunFireInterval) && Util.anglesSimilar(super.objectHeading, super.objectLocation.getAngleBetween(this.nearestThreat.objectLocation), 120.0)) {
                    this.currentAIAction = 1;
                }
                else {
                    this.currentAIAction = 3;
                    this.currentThrustBurstAngle = super.objectLocation.getAngleBetween(this.nearestThreat.objectLocation) + 150 + Math.random() * 60;
                }
            }
        }
        else if (this.nearestPowerup != null && !this.isInvincible()) {
            this.currentAIAction = 4;
        }
        else if (this.nearestEnemyShip != null) {
            this.currentTarget = this.nearestEnemyShip;
            this.currentAIAction = 1;
        }
        else if (this.nearestAsteroid != null) {
            this.currentTarget = this.nearestAsteroid;
            this.currentAIAction = 1;
        }
        else if (System.currentTimeMillis() > this.nextAISubActionEvaluationTimer) {
            this.currentAIAction = 2;
            this.currentDestination = this.getNewRandomDestination();
            this.nextAISubActionEvaluationTimer = System.currentTimeMillis() + 3000L;
            this.currentThreat = null;
        }
        this.nextAIActionEvaluationTimer = System.currentTimeMillis() + 300;
    }
    
    public void gameTick(final double gameTickInterval) {
        super.gameTick(gameTickInterval);
        if (this.nextAIActionEvaluationTimer <= System.currentTimeMillis()) {
            this.evaluateAIAction();
        }
        if (this.currentAIAction == 2) {
            this.moveToCurrentDestination(gameTickInterval);
        }
        else if (this.currentAIAction == 1) {
            this.fireOnCurrentTarget();
        }
        else if (this.currentAIAction == 3) {
            this.avoidCurrentTarget(gameTickInterval);
        }
        else if (this.currentAIAction == 4) {
            this.interceptObject(gameTickInterval, this.nearestPowerup);
        }
        if (!this.clearLineAhead(200.0)) {
            this.fireGun();
        }
    }
    
    public void fireMissiles() {
        if (System.currentTimeMillis() > this.nextMissileLaunchTimer) {
            this.fireMissile();
            this.nextMissileLaunchTimer = System.currentTimeMillis() + 3000L;
        }
    }
    
    public void fireGun() {
        if (this.nextShotTimer < System.currentTimeMillis()) {
            super.fireGun();
            if (!super.autoFireActive) {
                this.nextShotTimer = System.currentTimeMillis() + this.gunFireInterval;
            }
            else {
                this.nextShotTimer = System.currentTimeMillis() + 100L;
            }
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oHealthLevel) {
        super.collideWith(o, oHealthLevel);
        if (super.isAlive) {
            super.damageDisplayTimer = System.currentTimeMillis() + 1000;
        }
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        GameObject.asteroidsGame.addGameObject(new Powerup(super.objectLocation));
    }
}
