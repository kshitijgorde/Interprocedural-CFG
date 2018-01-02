import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class AsteroidsSprite
{
    static int width;
    static int height;
    Polygon shape;
    boolean active;
    double angle;
    double deltaAngle;
    double currentX;
    double currentY;
    double deltaX;
    double deltaY;
    Polygon sprite;
    
    public AsteroidsSprite() {
        this.shape = new Polygon();
        this.active = false;
        this.angle = 0.0;
        this.deltaAngle = 0.0;
        this.currentX = 0.0;
        this.currentY = 0.0;
        this.deltaX = 0.0;
        this.deltaY = 0.0;
        this.sprite = new Polygon();
    }
    
    public void advance() {
        this.angle += this.deltaAngle;
        if (this.angle < 0.0) {
            this.angle += 6.283185307179586;
        }
        if (this.angle > 6.283185307179586) {
            this.angle -= 6.283185307179586;
        }
        this.currentX += this.deltaX;
        if (this.currentX < -AsteroidsSprite.width / 2) {
            this.currentX += AsteroidsSprite.width;
        }
        if (this.currentX > AsteroidsSprite.width / 2) {
            this.currentX -= AsteroidsSprite.width;
        }
        this.currentY -= this.deltaY;
        if (this.currentY < -AsteroidsSprite.height / 2) {
            this.currentY += AsteroidsSprite.height;
        }
        if (this.currentY > AsteroidsSprite.height / 2) {
            this.currentY -= AsteroidsSprite.height;
        }
    }
    
    public void render() {
        this.sprite = new Polygon();
        for (int i = 0; i < this.shape.npoints; ++i) {
            this.sprite.addPoint((int)Math.round(this.shape.xpoints[i] * Math.cos(this.angle) + this.shape.ypoints[i] * Math.sin(this.angle)) + (int)Math.round(this.currentX) + AsteroidsSprite.width / 2, (int)Math.round(this.shape.ypoints[i] * Math.cos(this.angle) - this.shape.xpoints[i] * Math.sin(this.angle)) + (int)Math.round(this.currentY) + AsteroidsSprite.height / 2);
        }
    }
    
    public boolean isColliding(final AsteroidsSprite asteroidsSprite) {
        for (int i = 0; i < asteroidsSprite.sprite.npoints; ++i) {
            if (this.sprite.inside(asteroidsSprite.sprite.xpoints[i], asteroidsSprite.sprite.ypoints[i])) {
                return true;
            }
        }
        for (int j = 0; j < this.sprite.npoints; ++j) {
            if (asteroidsSprite.sprite.inside(this.sprite.xpoints[j], this.sprite.ypoints[j])) {
                return true;
            }
        }
        return false;
    }
}
