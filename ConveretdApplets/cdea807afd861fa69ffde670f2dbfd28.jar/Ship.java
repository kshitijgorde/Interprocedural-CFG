import java.awt.Component;
import java.awt.event.KeyEvent;
import com.next.gt.Gamelication;
import java.awt.event.KeyListener;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ship extends Actor implements KeyListener
{
    private static int MAX_NUM_BULLETS;
    public int numBullets;
    public int animationDirection;
    public boolean isAnimating;
    public boolean thrusting;
    
    Ship(final Gamelication owner) {
        this.animationDirection = 1;
        this.isAnimating = true;
        this.thrusting = false;
        (super.owner = owner).play(super.owner.getCodeBase(), "sounds/warp.au");
        super.x = ((Component)super.owner).getSize().width / 2.0;
        super.y = ((Component)super.owner).getSize().height / 2.0;
        super.velocity_x = 0.0;
        super.velocity_y = 0.0;
        this.setImage(super.owner.getImage(super.owner.getCodeBase(), "images/ship.gif"), 4, 24);
        this.isAnimating = false;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 39) {
            this.rotateRight(true);
        }
        if (keyEvent.getKeyCode() == 37) {
            this.rotateLeft(true);
        }
        if (keyEvent.getKeyCode() == 38) {
            this.thrust(true);
        }
        if (keyEvent.getKeyCode() == 32) {
            this.fire();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 39) {
            this.rotateRight(false);
        }
        if (keyEvent.getKeyCode() == 37) {
            this.rotateLeft(false);
        }
        if (keyEvent.getKeyCode() == 38) {
            this.thrust(false);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void calculateNewVelocity() {
        if (this.thrusting) {
            super.velocity_x += Math.cos(super.currentFrame * 2 * 3.141592653589793 / super.numFrames + 1.5707963267948966) * 10.0;
            super.velocity_y += Math.sin(super.currentFrame * 2 * 3.141592653589793 / super.numFrames - 1.5707963267948966) * 10.0;
            return;
        }
        super.velocity_x *= 0.99;
        super.velocity_y *= 0.99;
    }
    
    public void calculateCurrentFrame() {
        if (this.isAnimating) {
            if (this.animationDirection == -1) {
                if (--super.currentFrame <= 0) {
                    super.currentFrame = super.numFrames - 1;
                }
            }
            else if (++super.currentFrame >= super.numFrames) {
                super.currentFrame = 0;
            }
        }
    }
    
    public void rotateLeft(final boolean b) {
        if (b) {
            this.isAnimating = true;
            this.animationDirection = 1;
            return;
        }
        this.isAnimating = false;
    }
    
    public void rotateRight(final boolean b) {
        if (b) {
            this.animationDirection = -1;
            this.isAnimating = true;
            return;
        }
        this.isAnimating = false;
    }
    
    public void thrust(final boolean b) {
        if (b) {
            this.thrusting = true;
            return;
        }
        this.thrusting = false;
    }
    
    public void fire() {
        if (this.numBullets < Ship.MAX_NUM_BULLETS && !((Boinkaroids)super.owner).createNewPlayer) {
            final Bullet bullet = new Bullet(super.owner, this);
            ++this.numBullets;
            super.owner.play(super.owner.getCodeBase(), "sounds/bullet.au");
            super.owner.actorManager.addActor(bullet);
        }
    }
    
    public double getTheta() {
        return super.currentFrame * 2 * 3.141592653589793 / super.numFrames + 1.5707963267948966;
    }
    
    public double getSpeed() {
        return Math.sqrt(super.velocity_x * super.velocity_x + super.velocity_y * super.velocity_y);
    }
    
    protected void collideWithActor(final Actor actor) {
        final String name = actor.getClass().getName();
        if (name.equals("Asteroid") || name.equals("Goobie") || name.equals("Bigoobie")) {
            this.explode();
        }
    }
    
    public void explode() {
        super.owner.actorManager.removeActor(this);
        super.owner.actorManager.addActor((Actor)new Explosion(super.owner, (Actor)this));
        ((Boinkaroids)super.owner).decrementShipCount();
    }
    
    static {
        Ship.MAX_NUM_BULLETS = 5;
    }
}
