import java.awt.Image;
import com.next.gt.Gamelication;
import java.awt.Component;
import java.awt.MediaTracker;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bigoobie extends Actor
{
    public int attackResistance;
    
    Bigoobie(final Boinkaroids owner) {
        this.attackResistance = 3;
        super.owner = (Gamelication)owner;
        final Image image = super.owner.getImage(super.owner.getCodeBase(), "images/bigoobie.gif");
        final MediaTracker mediaTracker = new MediaTracker((Component)owner);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        super.x = Math.random() * 512.0;
        super.y = Math.random() * 512.0;
        super.velocity_x = ((int)Gamelication.randBetween(0.5, 1.5) * 2 - 1) * Gamelication.randBetween(16.0, 48.0);
        super.velocity_y = ((int)Gamelication.randBetween(0.5, 1.5) * 2 - 1) * Gamelication.randBetween(16.0, 48.0);
        this.setImage(image, 4, 12);
        super.currentFrame = (int)Gamelication.randBetween(0.0, (double)super.numFrames);
    }
    
    public void explode() {
        for (int i = 0; i < 2; ++i) {
            super.owner.actorManager.addActor((Actor)new Goobie(super.owner, this));
            ((Boinkaroids)super.owner).addOneBadGuy();
        }
        super.owner.play(super.owner.getCodeBase(), "sounds/pop.au");
        super.owner.scoreManager.addToScore(500);
        super.owner.actorManager.removeActor(this);
        ((Boinkaroids)super.owner).removeOneBadGuy();
    }
    
    protected void collideWithActor(final Actor actor) {
        final String name = actor.getClass().getName();
        if (name.equals("Bullet") || name.equals("Ship")) {
            if (--this.attackResistance < 0) {
                this.explode();
                return;
            }
            super.owner.play(super.owner.getCodeBase(), "sounds/futility.au");
        }
    }
}
