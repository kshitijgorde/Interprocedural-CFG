import java.awt.Component;
import com.next.gt.Gamelication;
import java.awt.Image;
import com.next.gt.Actor;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ball extends Actor
{
    int bounceCount;
    Image theImage;
    int MAX_VELOLCITY_X;
    int MAX_VELOLCITY_Y;
    
    Ball(final Gamelication owner) {
        this.MAX_VELOLCITY_X = 200;
        this.MAX_VELOLCITY_Y = 1200;
        super.owner = owner;
        super.x = Math.random() * ((Component)super.owner).getSize().width;
        super.y = Math.random() * ((Component)super.owner).getSize().height;
        super.velocity_x = ((int)Gamelication.randBetween(0.5, 1.5) * 2 - 1) * Math.random() * this.MAX_VELOLCITY_X;
        super.velocity_y = Math.random() * this.MAX_VELOLCITY_Y;
        this.bounceCount = 0;
        this.setImage(this.theImage = super.owner.getImage(super.owner.getCodeBase(), "images/pingPong.gif"), 4, 16);
        super.currentFrame = (int)Gamelication.randBetween(0.0, (double)super.numFrames);
        super.wrapAround = false;
    }
    
    public void calculateNewPosition() {
        super.calculateNewPosition();
        if (super.x > ((Component)super.owner).getSize().width - super.width) {
            super.velocity_x = -super.velocity_x;
            super.x = ((Component)super.owner).getSize().width - super.width;
        }
        else if (super.x < 0.0) {
            super.velocity_x = -super.velocity_x;
            super.x = 0.0;
        }
        if (super.y > ((Component)super.owner).getSize().height - super.height) {
            super.velocity_y = -super.velocity_y * 0.75;
            super.y = ((Component)super.owner).getSize().height - super.height;
            if (this.bounceCount++ > 8) {
                this.bounceCount = 0;
                super.velocity_y = -Math.random() * this.MAX_VELOLCITY_Y;
            }
        }
        else if (super.y < 0.0) {
            super.velocity_y = -super.velocity_y * 0.75;
            super.y = 0.0;
        }
        super.velocity_y += 5.0;
    }
}
