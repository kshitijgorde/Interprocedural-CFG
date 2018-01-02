import java.awt.Component;
import java.awt.event.ComponentEvent;
import com.next.gt.Gamelication;
import com.next.gt.DisplayManager;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class WindowResizer extends ComponentAdapter
{
    private DisplayManager displayManager;
    private Gamelication owner;
    
    public WindowResizer(final DisplayManager displayManager, final Gamelication owner) {
        this.displayManager = displayManager;
        this.owner = owner;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.displayManager.paint(((Component)this.owner).getGraphics());
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
        this.displayManager.paint(((Component)this.owner).getGraphics());
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        ((Component)this.owner).setSize(componentEvent.getComponent().getSize().width - 5, componentEvent.getComponent().getSize().height - 25);
        this.displayManager.setBackgroundTile(this.owner.getImage(this.owner.getCodeBase(), "images/background.gif"));
        this.displayManager.paint(((Component)this.owner).getGraphics());
    }
}
