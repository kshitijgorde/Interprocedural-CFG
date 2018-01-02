import com.next.gt.Gamelication;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bullet extends Actor
{
    long startTime;
    long ttl;
    Ship explodee;
    
    Bullet(final Gamelication owner, final Ship explodee) {
        this.ttl = 1750L;
        final double speed = explodee.getSpeed();
        final double theta = explodee.getTheta();
        super.owner = owner;
        this.explodee = explodee;
        super.x = ((Actor)this.explodee).x + ((Actor)this.explodee).width / 2.0;
        super.y = ((Actor)this.explodee).y + ((Actor)this.explodee).height / 2.0;
        this.setImage(super.owner.getImage(super.owner.getCodeBase(), "images/bullet.gif"), 4, 16);
        super.velocity_x = Math.cos(theta) * (speed + 150.0);
        super.velocity_y = Math.sin(theta + 3.141592653589793) * (speed + 150.0);
        super.x += super.velocity_x * 0.1;
        super.y += super.velocity_y * 0.1;
        this.startTime = super.owner.currentTickTimeMillis;
    }
    
    public void tick() {
        super.tick();
        if (super.owner.currentTickTimeMillis - this.startTime > this.ttl) {
            if (this.explodee.numBullets > 0) {
                final Ship explodee = this.explodee;
                --explodee.numBullets;
            }
            super.owner.actorManager.removeActor(this);
        }
    }
    
    protected void collideWithActor(final Actor actor) {
        final String name = actor.getClass().getName();
        if (name.equals("Asteroid") || name.equals("Bigoobie")) {
            if (this.explodee.numBullets > 0) {
                final Ship explodee = this.explodee;
                --explodee.numBullets;
            }
            super.owner.actorManager.removeActor(this);
        }
    }
}
