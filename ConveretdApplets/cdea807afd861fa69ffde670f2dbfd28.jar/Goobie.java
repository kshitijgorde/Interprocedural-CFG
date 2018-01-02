import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import com.next.gt.Gamelication;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Goobie extends Actor
{
    private static double GRAVITATIONAL_PULL;
    
    Goobie(final Gamelication owner, final Bigoobie bigoobie) {
        super.owner = owner;
        final Image image = super.owner.getImage(super.owner.getCodeBase(), "images/goobie.gif");
        final MediaTracker mediaTracker = new MediaTracker((Component)owner);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        super.x = (int)(bigoobie.x - (super.width - bigoobie.width) / 2.0);
        super.y = (int)(bigoobie.y - (super.height - bigoobie.height) / 2.0);
        super.velocity_x = bigoobie.velocity_x * Gamelication.randBetween(0.2, 1.4);
        super.velocity_y = bigoobie.velocity_y * Gamelication.randBetween(0.2, 1.4);
        this.setImage(image);
    }
    
    public void calculateNewPosition() {
        super.calculateNewPosition();
        if (((Boinkaroids)super.owner).player == null) {
            return;
        }
        if (super.x > ((Actor)((Boinkaroids)super.owner).player).x) {
            super.velocity_x -= Goobie.GRAVITATIONAL_PULL;
        }
        else {
            super.velocity_x += Goobie.GRAVITATIONAL_PULL;
        }
        if (super.y > ((Actor)((Boinkaroids)super.owner).player).y) {
            super.velocity_y -= Goobie.GRAVITATIONAL_PULL;
            return;
        }
        super.velocity_y += Goobie.GRAVITATIONAL_PULL;
    }
    
    public void explode() {
        super.owner.play(super.owner.getCodeBase(), "sounds/smack.au");
        super.owner.scoreManager.addToScore(1000);
        super.owner.actorManager.removeActor(this);
        ((Boinkaroids)super.owner).removeOneBadGuy();
    }
    
    protected void collideWithActor(final Actor actor) {
        final String name = actor.getClass().getName();
        if (name.equals("Bullet") || name.equals("Ship")) {
            this.explode();
        }
    }
    
    static {
        Goobie.GRAVITATIONAL_PULL = 0.5;
    }
}
