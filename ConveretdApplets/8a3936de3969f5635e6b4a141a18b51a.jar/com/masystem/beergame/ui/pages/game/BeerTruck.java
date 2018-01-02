// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import com.masystem.beergame.ui.animation.Animation;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.network.protocol.Player;
import com.masystem.beergame.ui.beergamecomponents.BeerGameRotatedImage;

public final class BeerTruck extends BeerGameRotatedImage
{
    private static final float[][] PATH_RETAILER;
    private static final float[][] PATH_WHOLESALER;
    private static final float[][] PATH_DISTRIBUTOR_TOP;
    private static final float[][] PATH_DISTRIBUTOR_BOTTOM;
    private static final float[][] PATH_PRODUCER;
    private final Player.Type playerType;
    private final int direction;
    private final int route;
    private NodeAnimation anim;
    
    public BeerTruck(final Player.Type playerType, final int route) {
        super((getDirection(playerType) == 0) ? ResourceManager.getOptimizedImage("beer_truck_left.png") : ResourceManager.getOptimizedImage("beer_truck_right.png"));
        this.playerType = playerType;
        this.route = route;
        this.direction = getDirection(playerType);
        this.setVisible(false);
    }
    
    public final NodeAnimation getAnimation() {
        return this.anim;
    }
    
    public final void animate(final int startDelay) {
        this.anim = new NodeAnimation(this) {
            private /* synthetic */ float[] val$pathX = getPath(BeerTruck.this.playerType, BeerTruck.this.route)[0];
            private /* synthetic */ float[] val$pathY = getPath(BeerTruck.this.playerType, BeerTruck.this.route)[1];
            
            @Override
            protected final void startAnimation() {
                BeerTruck.this.setVisible(true);
            }
            
            @Override
            protected final void stepAnimation(float evaluate) {
                final float evaluate2 = Animation.AnimationTimingTarget.evaluate(evaluate, this.val$pathX);
                final float evaluate3 = Animation.AnimationTimingTarget.evaluate(evaluate, this.val$pathY);
                final float evaluate4 = Animation.AnimationTimingTarget.evaluate(evaluate + 0.05f, this.val$pathX);
                evaluate = Animation.AnimationTimingTarget.evaluate(evaluate + 0.05f, this.val$pathY);
                if (BeerTruck.access$100(BeerTruck.this.playerType)) {
                    if (BeerTruck.this.direction == 1) {
                        BeerTruck.this.setRotation(-Math.atan2(evaluate - evaluate3, evaluate4 - evaluate2));
                    }
                    else {
                        BeerTruck.this.setRotation(3.141592653589793 - Math.atan2(evaluate - evaluate3, evaluate4 - evaluate2));
                    }
                }
                BeerTruck.this.setPositionRelativeToScene(evaluate2, evaluate3);
            }
            
            @Override
            protected final void endAnimation() {
                BeerTruck.this.setVisible(false);
            }
        };
        final NodeAnimation anim = this.anim;
        int duration = 0;
        switch (this.playerType) {
            case PRODUCER: {
                duration = 1600;
                break;
            }
            case DISTRIBUTOR: {
                duration = 2000;
                break;
            }
            case WHOLESALER: {
                duration = 1800;
                break;
            }
            default: {
                duration = 1000;
                break;
            }
        }
        anim.setDuration(duration);
        this.anim.setStartDelay(startDelay);
        this.anim.start();
    }
    
    private static int getDirection(final Player.Type type) {
        switch (type) {
            case PRODUCER: {
                return 0;
            }
            case DISTRIBUTOR: {
                return 1;
            }
            case WHOLESALER: {
                return 0;
            }
            default: {
                return 0;
            }
        }
    }
    
    private static float[][] getPath(final Player.Type type, final int n) {
        switch (type) {
            case PRODUCER: {
                return BeerTruck.PATH_PRODUCER;
            }
            case DISTRIBUTOR: {
                if (n == 0) {
                    return BeerTruck.PATH_DISTRIBUTOR_TOP;
                }
                return BeerTruck.PATH_DISTRIBUTOR_BOTTOM;
            }
            case WHOLESALER: {
                return BeerTruck.PATH_WHOLESALER;
            }
            default: {
                return BeerTruck.PATH_RETAILER;
            }
        }
    }
    
    static /* synthetic */ boolean access$100(Player.Type type) {
        return (type = type) == Player.Type.DISTRIBUTOR;
    }
    
    static {
        PATH_RETAILER = new float[][] { { 0.2f, 0.13f, 0.02f, -0.12f, -0.22f }, { 0.23f, 0.24f, 0.24f, 0.24f, 0.22f } };
        PATH_WHOLESALER = new float[][] { { 0.9f, 0.85f, 0.69f, 0.52f, 0.33f, 0.17f, 0.06f }, { 0.25f, 0.25f, 0.22f, 0.23f, 0.2f, 0.24f, 0.24f } };
        PATH_DISTRIBUTOR_TOP = new float[][] { { 0.14f, 0.14f, 0.13f, 0.14f, 0.23f, 0.38f, 0.55f, 0.73f, 0.84f, 0.87f, 0.88f }, { 0.91f, 0.8f, 0.63f, 0.48f, 0.42f, 0.43f, 0.43f, 0.44f, 0.42f, 0.29f, 0.17f } };
        PATH_DISTRIBUTOR_BOTTOM = new float[][] { { 0.13f, 0.13f, 0.13f, 0.21f, 0.38f, 0.55f, 0.73f, 0.85f, 0.85f, 0.85f, 0.85f }, { 0.91f, 0.76f, 0.62f, 0.56f, 0.58f, 0.58f, 0.59f, 0.56f, 0.38f, 0.27f, 0.13f } };
        PATH_PRODUCER = new float[][] { { 0.91f, 0.85f, 0.68f, 0.5f, 0.28f, 0.14f, 0.04f }, { 0.77f, 0.77f, 0.75f, 0.75f, 0.74f, 0.77f, 0.77f } };
    }
}
