// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.transition;

import com.masystem.beergame.MouseBlocker;
import com.masystem.beergame.ui.scene.Scene;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.PanelComponent;
import com.masystem.beergame.ui.scene.Node;

public abstract class Transition extends Node
{
    private final Node srcNode;
    private final Node destNode;
    private int duration;
    
    public Transition(final Node srcNode, final Node destNode) {
        super(new PanelComponent());
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.duration = 600;
    }
    
    public final int getDuration() {
        return this.duration;
    }
    
    public final Node getSourceNode() {
        return this.srcNode;
    }
    
    public final Node getDestinationNode() {
        return this.destNode;
    }
    
    public final void setup(final Scene scene) {
        if (this.srcNode != null) {
            scene.removeChild(this.srcNode);
        }
        scene.addChild(this);
    }
    
    public final void start() {
        MouseBlocker.block();
        this.startTransition();
    }
    
    protected final void started() {
        final Node destNode = this.destNode;
    }
    
    protected final void ended() {
        this.removeAllChildren();
        final Scene scene;
        (scene = (Scene)this.getParent()).removeChild(this);
        final Node srcNode = this.srcNode;
        final Node destNode;
        if ((destNode = this.destNode) != null) {
            destNode.destroyCache();
            scene.addChild(destNode);
        }
        if (srcNode != null) {
            srcNode.onTearDown();
            srcNode.destroyCache();
        }
        if (destNode != null && destNode instanceof Listener) {
            ((Listener)destNode).onTransitionEnd$6db0a1c1();
        }
        MouseBlocker.unblock();
        System.gc();
    }
    
    protected abstract void startTransition();
    
    public interface Listener
    {
        void onTransitionEnd$6db0a1c1();
    }
}
