// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Rectangle;
import java.util.Vector;

public class ActorManager
{
    protected Gamelication owner;
    public Vector actors;
    private Vector actorsScheduledForRemoval;
    private Vector actorsScheduledForInsertion;
    private boolean removeAllActors;
    
    public ActorManager(final Gamelication owner) {
        this.actors = new Vector();
        this.actorsScheduledForRemoval = new Vector();
        this.actorsScheduledForInsertion = new Vector();
        this.removeAllActors = false;
        this.owner = owner;
    }
    
    public void addActor(final Actor actor) {
        this.actorsScheduledForInsertion.addElement(actor);
    }
    
    public void removeActor(final Actor actor) {
        this.actorsScheduledForRemoval.addElement(actor);
    }
    
    public void removeAllActors() {
        for (int i = 0; i < this.actors.size(); ++i) {}
        this.actorsScheduledForRemoval.removeAllElements();
        this.actorsScheduledForInsertion.removeAllElements();
        this.actors.removeAllElements();
    }
    
    public boolean isActorAt(final double n, final double n2) {
        boolean b = false;
        for (int i = 0; i < this.actors.size(); ++i) {
            final Actor actor = this.actors.elementAt(i);
            if (n < actor.x) {
                break;
            }
            if (n <= actor.x + actor.width && n2 >= actor.y && n2 <= actor.y + actor.height) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean isActorIn(final Rectangle rectangle) {
        boolean b = false;
        final double n = rectangle.x + rectangle.width;
        for (int i = 0; i < this.actors.size(); ++i) {
            final Actor actor = this.actors.elementAt(i);
            if (n < actor.x) {
                break;
            }
            if (rectangle.y <= actor.y) {
                if (rectangle.y + rectangle.height > actor.y) {
                    b = true;
                }
            }
            else if (actor.y + actor.height > rectangle.y) {
                b = true;
            }
        }
        return b;
    }
    
    private final void sortActorsByXCoordinate() {
        for (int size = this.actors.size(), i = 1; i < size; ++i) {
            final Actor actor = this.actors.elementAt(i);
            int j;
            for (j = i - 1; j >= 0; --j) {
                final Actor actor2 = this.actors.elementAt(j);
                if (actor.x >= actor2.x) {
                    break;
                }
                this.actors.setElementAt(actor2, j + 1);
            }
            if (i != j + 1) {
                this.actors.setElementAt(actor, j + 1);
            }
        }
    }
    
    private final void detectCollision() {
        for (int size = this.actors.size(), n = 0; n + 1 < size; ++n) {
            final Actor actor = this.actors.elementAt(n);
            final double n2 = actor.x + actor.width;
            for (int i = n + 1; i < size; ++i) {
                final Actor actor2 = this.actors.elementAt(i);
                if (n2 < actor2.x) {
                    break;
                }
                if (actor.y <= actor2.y) {
                    if (actor.y + actor.height > actor2.y) {
                        this.handleBBCollision(actor, actor2);
                    }
                }
                else if (actor2.y + actor2.height > actor.y) {
                    this.handleBBCollision(actor, actor2);
                }
            }
        }
    }
    
    protected void handleBBCollision(final Actor actor, final Actor actor2) {
        actor.collideWithActor(actor2);
        actor2.collideWithActor(actor);
    }
    
    public void tick() {
        if (this.actorsScheduledForInsertion.size() > 0) {
            for (int i = 0; i < this.actorsScheduledForInsertion.size(); ++i) {
                this.actors.addElement(this.actorsScheduledForInsertion.elementAt(i));
            }
            this.actorsScheduledForInsertion.removeAllElements();
        }
        if (this.actorsScheduledForRemoval.size() > 0) {
            for (int j = 0; j < this.actorsScheduledForRemoval.size(); ++j) {
                this.actors.removeElement(this.actorsScheduledForRemoval.elementAt(j));
            }
            this.actorsScheduledForRemoval.removeAllElements();
        }
        for (int k = 0; k < this.actors.size(); ++k) {
            ((Actor)this.actors.elementAt(k)).tick();
        }
        this.sortActorsByXCoordinate();
        this.detectCollision();
    }
}
