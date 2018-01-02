// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Image;

public class DisplayManager
{
    private Image background;
    private Image offScreenBuffer;
    private DirtyRectSet dirtyRects;
    protected Gamelication owner;
    
    public DisplayManager(final Gamelication owner) {
        this.dirtyRects = new DirtyRectSet();
        this.owner = owner;
    }
    
    public void refresh() {
        this.dirtyRects.addRect(new Rectangle(0, 0, ((Component)this.owner).getSize().width, ((Component)this.owner).getSize().height));
    }
    
    public void setBackground(final Image background) {
        final MediaTracker mediaTracker = new MediaTracker((Component)this.owner);
        mediaTracker.addImage(background, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.background = background;
        this.offScreenBuffer = ((Component)this.owner).createImage(((Component)this.owner).getSize().width, ((Component)this.owner).getSize().height);
        this.offScreenBuffer.getGraphics().drawImage(background, 0, 0, (ImageObserver)this.owner);
        this.dirtyRects.addRect(new Rectangle(0, 0, ((Component)this.owner).getSize().width, ((Component)this.owner).getSize().height));
    }
    
    public void setBackgroundTile(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker((Component)this.owner);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.setBackground(TiledImage.createTiledImage(image, ((Component)this.owner).getSize().width, ((Component)this.owner).getSize().height, this.owner));
    }
    
    public void paint(final Graphics graphics) {
        Graphics graphics2;
        if (this.offScreenBuffer == null) {
            graphics2 = null;
        }
        else {
            graphics2 = this.offScreenBuffer.getGraphics();
        }
        this.dirtyRects.drawImage(graphics2, this.background, this.owner);
        final DirtyRectSet dirtyRects = this.dirtyRects;
        this.dirtyRects = new DirtyRectSet(this.owner.actorManager.actors.size());
        for (int i = 0; i < this.owner.actorManager.actors.size(); ++i) {
            final Actor actor = this.owner.actorManager.actors.elementAt(i);
            final Rectangle rectangle = new Rectangle((int)actor.x, (int)actor.y, actor.width, actor.height);
            this.dirtyRects.addRect(rectangle);
            dirtyRects.addRect(rectangle);
            actor.draw(graphics2);
        }
        dirtyRects.drawImage(graphics, this.offScreenBuffer, this.owner);
    }
}
