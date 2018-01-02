import com.next.gt.Actor;
import com.next.gt.ImageManager;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import com.next.gt.WindowDestroyer;
import java.awt.Component;
import java.awt.Frame;
import com.next.gt.Gamelication;

// 
// Decompiled by Procyon v0.5.30
// 

public class Boink extends Gamelication
{
    public void init() {
        super.init(320, 240);
        final Frame frame = new Frame("Gamelication");
        frame.setSize(320, 240);
        frame.add((Component)this);
        frame.addWindowListener(new WindowDestroyer(frame, this));
        frame.addComponentListener((ComponentListener)new WindowResizer(super.displayManager, (Gamelication)this));
        frame.setResizable(true);
        frame.show();
        new ImageManager(this);
        for (int i = 0; i < 12; ++i) {
            super.actorManager.addActor(new Ball(this));
        }
        super.displayManager.setBackgroundTile(this.getImage(this.getCodeBase(), "images/background.gif"));
        this.start();
        this.run();
    }
    
    public static void main(final String[] array) {
        new Boink().init();
    }
}
