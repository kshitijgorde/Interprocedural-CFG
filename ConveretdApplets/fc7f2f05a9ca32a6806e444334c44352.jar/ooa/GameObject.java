// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

public abstract class GameObject
{
    protected static AsteroidsGame asteroidsGame;
    protected HighPrecisionPoint objectLocation;
    protected double objectHeading;
    protected double objectMovementDirection;
    protected double objectMovementSpeed;
    protected double objectRotationalSpeed;
    protected int healthLevel;
    protected int maxHealthLevel;
    protected boolean isAlive;
    
    public static void init(final AsteroidsGame g) {
        GameObject.asteroidsGame = g;
    }
    
    public GameObject(final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        this.objectLocation = new HighPrecisionPoint(createLocation);
        this.objectHeading = createHeading;
        this.objectMovementDirection = createHeading;
        this.objectMovementSpeed = createSpeed;
        this.objectRotationalSpeed = createRotationalSpeed;
        this.isAlive = true;
    }
    
    public void gameTick(final double gameTickInterval) {
        if (this.healthLevel <= 0 && this.isAlive) {
            this.explode(this);
        }
        this.updatePosition(gameTickInterval);
    }
    
    protected void updatePosition(final double gameTickInterval) {
        final double n = this.objectHeading + this.objectRotationalSpeed * gameTickInterval;
        this.objectHeading = n;
        this.objectHeading = Util.fixDegrees(n);
        this.objectLocation.directionalMove(this.objectMovementDirection, this.objectMovementSpeed * gameTickInterval);
        if (this.objectLocation.x > 850) {
            this.objectLocation.x = -50.0;
        }
        else if (this.objectLocation.x < -50) {
            this.objectLocation.x = 850.0;
        }
        if (this.objectLocation.y > 650) {
            this.objectLocation.y = -50.0;
        }
        else if (this.objectLocation.y < -50) {
            this.objectLocation.y = 650.0;
        }
    }
    
    protected boolean isInvincible() {
        return false;
    }
    
    protected void explode(final GameObject destroyingObject) {
        this.isAlive = false;
        this.healthLevel = 0;
    }
    
    public abstract void draw();
}
