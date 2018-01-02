import com.next.gt.Gamelication;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Explosion extends Actor
{
    Explosion(final Gamelication owner, final Actor actor) {
        (super.owner = owner).play(super.owner.getCodeBase(), "sounds/explode1.au");
        this.setImage(super.owner.getImage(super.owner.getCodeBase(), "images/explosions.gif"), 60, 60, 4, 16);
        super.x = actor.x - (super.width - actor.width) / 2.0;
        super.y = actor.y - (super.height - actor.height) / 2.0;
        super.velocity_x = actor.velocity_x;
        super.velocity_y = actor.velocity_y;
    }
    
    public void calculateCurrentFrame() {
        if (++super.currentFrame >= super.numFrames) {
            super.owner.actorManager.removeActor(this);
        }
    }
}
