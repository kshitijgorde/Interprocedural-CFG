import com.next.gt.Gamelication;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Asteroid extends Actor
{
    public static final int SMALL_SIZE = 0;
    public static final int MEDIUM_SIZE = 1;
    public static final int LARGE_SIZE = 2;
    int size;
    String name;
    
    Asteroid(final Gamelication owner, final Asteroid asteroid, final String name, final int size) {
        final String[] array = { "S", "M", "L", "G" };
        super.owner = owner;
        this.size = size;
        this.name = name;
        if (size == 2) {
            super.x = Math.random() * 512.0;
            super.y = Math.random() * 512.0;
            super.velocity_x = ((int)Gamelication.randBetween(0.5, 1.5) * 2 - 1) * Gamelication.randBetween(8.0, 32.0);
            super.velocity_y = ((int)Gamelication.randBetween(0.5, 1.5) * 2 - 1) * Gamelication.randBetween(8.0, 32.0);
        }
        else {
            super.x = asteroid.x;
            super.y = asteroid.y;
            super.velocity_x = asteroid.velocity_x * Gamelication.randBetween(0.6, 1.4);
            super.velocity_y = asteroid.velocity_y * Gamelication.randBetween(0.6, 1.4);
        }
        this.setImage(super.owner.getImage(super.owner.getCodeBase(), "images/" + name + array[size] + ".gif"), 4, 32);
        super.currentFrame = (int)Gamelication.randBetween(0.0, (double)super.numFrames);
    }
    
    public void explode() {
        if (this.size == 0) {
            super.owner.actorManager.addActor((Actor)new Explosion(super.owner, (Actor)this));
        }
        else {
            super.owner.play(super.owner.getCodeBase(), "sounds/explode1.au");
        }
        if (this.size == 2) {
            for (int i = 0; i < 2; ++i) {
                super.owner.actorManager.addActor(new Asteroid(super.owner, this, this.name, 1));
                ((Boinkaroids)super.owner).addOneBadGuy();
            }
        }
        else if (this.size == 1) {
            for (int j = 0; j < 2; ++j) {
                super.owner.actorManager.addActor(new Asteroid(super.owner, this, this.name, 0));
                ((Boinkaroids)super.owner).addOneBadGuy();
            }
        }
        super.owner.scoreManager.addToScore((2 - this.size) * 200 + 100);
        super.owner.actorManager.removeActor(this);
        ((Boinkaroids)super.owner).removeOneBadGuy();
    }
    
    protected void collideWithActor(final Actor actor) {
        final String name = actor.getClass().getName();
        if (name.equals("Bullet") || name.equals("Ship")) {
            this.explode();
        }
    }
}
