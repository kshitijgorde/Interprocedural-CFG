// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class EnemyShip extends Ship
{
    protected static final double MAX_OBJECT_SEARCH_DISTANCE = 9.99999999E8;
    protected static final double THREAT_DISTANCE_THRESHOLD = 200.0;
    protected static final double ENEMY_SHIP_ROTATIONAL_SPEED = 190.0;
    protected static final double ENEMY_SHIP_THRUST_ACCELERATION = 85.0;
    protected static final double ENEMY_SHIP_DECELERATION = 60.0;
    protected static final double ENEMY_SHIP_TARGET_SPEED = 90.0;
    protected static final int ENEMY_SHIP_BASE_HITS_TO_DESTROY = 3;
    protected static final double ENEMY_SHIP_MAX_ENERGY = 100.0;
    protected static final double ENEMY_SHIP_ENERGY_RECHARGE_RATE = 6.0;
    protected static final int ENEMY_SHIP_BASE_SCORE = 1000;
    protected static final long ENEMY_SHIP_DEFAULT_MOVE_TO_DURATION = 3000L;
    protected static final long ENEMY_SHIP_MISSILE_LAUNCH_INTERVAL = 1000L;
    private static SuperPolygon[] enemyShipOutline;
    private static Rectangle[] enemyShipOutlineBounds;
    private static HighPrecisionPoint[] engine1Location;
    private static HighPrecisionPoint[] engine2Location;
    private static HighPrecisionPoint[] gunLocation;
    private static HighPrecisionPoint[] superGun1Location;
    private static HighPrecisionPoint[] superGun2Location;
    private static HighPrecisionPoint[] missileLauncher1Location;
    private static HighPrecisionPoint[] missileLauncher2Location;
    protected int currentAIAction;
    protected PolygonGameObject currentTarget;
    protected PolygonGameObject currentThreat;
    protected HighPrecisionPoint currentDestination;
    protected double currentThrustBurstAngle;
    protected long gunFireInterval;
    PolygonGameObject nearestThreat;
    PolygonGameObject nearestPowerup;
    PolygonGameObject nearestPlayerShip;
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
    
    public void fireMissiles() {
        if (System.currentTimeMillis() > this.nextMissileLaunchTimer) {
            if (this.currentMissileLauncher == 1) {
                super.fireMissile(EnemyShip.missileLauncher1Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
                this.currentMissileLauncher = 2;
            }
            else {
                super.fireMissile(EnemyShip.missileLauncher2Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
                this.currentMissileLauncher = 1;
            }
            this.nextMissileLaunchTimer = System.currentTimeMillis() + 1000L;
        }
    }
    
    public EnemyShip(final HighPrecisionPoint createLocation) {
        super(EnemyShip.enemyShipOutline, EnemyShip.enemyShipOutlineBounds, createLocation, 0.0, 0.0, 0.0, 100.0, (3 + GameObject.asteroidsGame.roundNumber) * 3, 85.0, 60.0, 6.0);
        this.currentAIAction = 0;
        this.currentTarget = null;
        this.currentDestination = null;
        this.nextShotTimer = System.currentTimeMillis();
        this.nextMissileLaunchTimer = System.currentTimeMillis();
        this.nextAIActionEvaluationTimer = 0L;
        this.nextAISubActionEvaluationTimer = 0L;
        super.damageDisplayTimer = 0L;
        this.nearestThreat = null;
        this.nearestPowerup = null;
        super.missilesRemaining = 0;
        this.currentMissileLauncher = 1;
        this.gunFireInterval = 700 - Math.min(300, GameObject.asteroidsGame.roundNumber * 100);
    }
    
    protected void fireThrusters(final double gameTickInterval) {
        this.fireThruster(gameTickInterval, EnemyShip.engine1Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
        this.fireThruster(gameTickInterval, EnemyShip.engine2Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
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
                super.objectRotationalSpeed = 190.0;
            }
            else {
                super.objectRotationalSpeed = -190.0;
            }
        }
    }
    
    protected void avoidCurrentTarget(final double gameTickInterval) {
        this.rotateTo(this.currentThrustBurstAngle = Util.fixDegrees(this.currentThrustBurstAngle));
        if (Util.anglesSimilar(super.objectHeading, this.currentThrustBurstAngle, 10.0) && super.objectMovementSpeed <= Math.max(this.currentTarget.objectMovementSpeed * 2, 90.0)) {
            this.fireThrusters(gameTickInterval);
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
                if (super.objectMovementSpeed < 90.0) {
                    this.fireThrusters(gameTickInterval);
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
                if (super.objectMovementSpeed < 90.0) {
                    this.fireThrusters(gameTickInterval);
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
    
    void fireGun() {
        if (this.nextShotTimer < System.currentTimeMillis()) {
            if (!super.superGunActive) {
                super.fireGun(EnemyShip.gunLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
            }
            else {
                super.fireGun(EnemyShip.superGun1Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
                super.fireGun(EnemyShip.superGun2Location[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
            }
            if (!super.autoFireActive) {
                this.nextShotTimer = System.currentTimeMillis() + this.gunFireInterval;
            }
            else {
                this.nextShotTimer = System.currentTimeMillis() + 100L;
            }
        }
    }
    
    protected void fireOnCurrentTarget() {
        if (this.currentTarget != null && this.currentTarget.isAlive) {
            final double angleToTarget = super.objectLocation.getAngleBetween(this.currentTarget.objectLocation);
            this.rotateTo(angleToTarget);
            if (Util.anglesSimilar(super.objectHeading, angleToTarget, 5.0)) {
                this.fireGun();
                if (super.missilesRemaining > 0) {
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
        double closestPlayerShipDistance = 9.99999999E8;
        int i = 0;
        while (true) {
            Label_0355: {
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
                    if (objToCheck != this && objToCheck instanceof PlayerShip) {
                        final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                        final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                        if (this.clearLineToObject(objToCheck) && objectDistance < closestPlayerShipDistance) {
                            this.nearestPlayerShip = objToCheck;
                            closestPlayerShipDistance = objectDistance;
                        }
                    }
                    if (objToCheck == this || !(objToCheck instanceof Powerup)) {
                        break Label_0355;
                    }
                    final double objectDistance = super.objectLocation.getDistanceBetween(objToCheck.objectLocation) - Math.max(objToCheck.objectLocationBounds.width, objToCheck.objectLocationBounds.height) / 2;
                    final double angleToObject = super.objectLocation.getAngleBetween(objToCheck.objectLocation);
                    if (this.clearLineToObject(objToCheck) && objectDistance < closestPowerupDistance) {
                        this.nearestPowerup = objToCheck;
                        closestPowerupDistance = objectDistance;
                    }
                    break Label_0355;
                }
                catch (IndexOutOfBoundsException e) {
                    if (closestThreatDistance == 9.99999999E8) {
                        this.nearestThreat = null;
                    }
                    if (closestPowerupDistance == 9.99999999E8) {
                        this.nearestPowerup = null;
                    }
                    if (closestPlayerShipDistance == 9.99999999E8) {
                        this.nearestPlayerShip = null;
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
                if ((this.nearestThreat instanceof PlayerShip && !this.nearestThreat.isInvincible()) || (this.nearestThreat instanceof Asteroid && this.nearestThreat.healthLevel <= 3 * (1000 / this.gunFireInterval) && Util.anglesSimilar(super.objectHeading, super.objectLocation.getAngleBetween(this.nearestThreat.objectLocation), 120.0))) {
                    this.currentAIAction = 1;
                }
                else if (this.nearestThreat instanceof Missile && Util.anglesSimilar(super.objectHeading, super.objectLocation.getAngleBetween(this.nearestThreat.objectLocation), 10.0)) {
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
        else if (super.objectLocation.x < 100 || super.objectLocation.x > 700 || super.objectLocation.y < 100 || super.objectLocation.y > 500) {
            this.currentThreat = null;
            if (System.currentTimeMillis() > this.nextAISubActionEvaluationTimer) {
                this.currentAIAction = 2;
                this.currentDestination = this.getNewRandomDestination();
                this.nextAISubActionEvaluationTimer = System.currentTimeMillis() + 3000L;
            }
        }
        else if (this.nearestPlayerShip != null && this.nearestPlayerShip.isAlive && !this.nearestPlayerShip.isInvincible()) {
            this.currentAIAction = 1;
            this.currentTarget = this.nearestPlayerShip;
            this.currentThreat = null;
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
    
    protected void collideWith(final PolygonGameObject o, final int oHealthLevel) {
        super.collideWith(o, oHealthLevel);
        if (super.isAlive) {
            super.damageDisplayTimer = System.currentTimeMillis() + 1000;
        }
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        if (destroyingObject instanceof PlayerShip || (destroyingObject instanceof Projectile && ((Projectile)destroyingObject).shipFiredBy instanceof PlayerShip)) {
            final AsteroidsGame asteroidsGame = GameObject.asteroidsGame;
            asteroidsGame.playerScore += 1000 * GameObject.asteroidsGame.roundNumber;
        }
        GameObject.asteroidsGame.addGameObject(new Powerup(super.objectLocation));
    }
    
    static {
        EnemyShip.enemyShipOutline = new SuperPolygon[360];
        EnemyShip.enemyShipOutlineBounds = new Rectangle[360];
        EnemyShip.engine1Location = new HighPrecisionPoint[360];
        EnemyShip.engine2Location = new HighPrecisionPoint[360];
        EnemyShip.gunLocation = new HighPrecisionPoint[360];
        EnemyShip.superGun1Location = new HighPrecisionPoint[360];
        EnemyShip.superGun2Location = new HighPrecisionPoint[360];
        EnemyShip.missileLauncher1Location = new HighPrecisionPoint[360];
        EnemyShip.missileLauncher2Location = new HighPrecisionPoint[360];
        (EnemyShip.enemyShipOutline[0] = new SuperPolygon()).addPoint(0, -10);
        EnemyShip.enemyShipOutline[0].addPoint(5, 0);
        EnemyShip.enemyShipOutline[0].addPoint(13, -15);
        EnemyShip.enemyShipOutline[0].addPoint(13, 12);
        EnemyShip.enemyShipOutline[0].addPoint(5, 5);
        EnemyShip.enemyShipOutline[0].addPoint(4, 5);
        EnemyShip.enemyShipOutline[0].addPoint(2, 2);
        EnemyShip.enemyShipOutline[0].addPoint(0, 5);
        EnemyShip.enemyShipOutline[0].addPoint(-2, 2);
        EnemyShip.enemyShipOutline[0].addPoint(-4, 5);
        EnemyShip.enemyShipOutline[0].addPoint(-5, 5);
        EnemyShip.enemyShipOutline[0].addPoint(-13, 12);
        EnemyShip.enemyShipOutline[0].addPoint(-13, -15);
        EnemyShip.enemyShipOutline[0].addPoint(-5, 0);
        EnemyShip.enemyShipOutline[0].addPoint(0, -10);
        PolygonGameObject.setupReferenceOutlineArrays(EnemyShip.enemyShipOutline, EnemyShip.enemyShipOutlineBounds);
        EnemyShip.engine1Location[0] = new HighPrecisionPoint(-2.0, 2.0);
        EnemyShip.engine2Location[0] = new HighPrecisionPoint(2.0, 2.0);
        EnemyShip.gunLocation[0] = new HighPrecisionPoint(0.0, -15.0);
        EnemyShip.superGun1Location[0] = new HighPrecisionPoint(4.0, -7.0);
        EnemyShip.superGun2Location[0] = new HighPrecisionPoint(-4.0, -7.0);
        EnemyShip.missileLauncher1Location[0] = new HighPrecisionPoint(13.0, -17.0);
        EnemyShip.missileLauncher2Location[0] = new HighPrecisionPoint(-13.0, -17.0);
        for (int i = 1; i < 360; ++i) {
            EnemyShip.engine1Location[i] = EnemyShip.engine1Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.engine2Location[i] = EnemyShip.engine2Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.gunLocation[i] = EnemyShip.gunLocation[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.superGun1Location[i] = EnemyShip.superGun1Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.superGun2Location[i] = EnemyShip.superGun2Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.missileLauncher1Location[i] = EnemyShip.missileLauncher1Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            EnemyShip.missileLauncher2Location[i] = EnemyShip.missileLauncher2Location[0].cloneRotate(i);
        }
    }
}
