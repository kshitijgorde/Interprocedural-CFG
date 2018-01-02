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

public final class OrderEnvelope extends BeerGameRotatedImage
{
    private static final float[][] PATH_CUSTOMER_REMOTE_LOCAL;
    private static final float[][] PATH_CUSTOMER_REMOTE_REMOTE;
    private static final float[][] PATH_RETAILER_LOCAL_REMOTE;
    private static final float[][] PATH_RETAILER_REMOTE_LOCAL;
    private static final float[][] PATH_RETAILER_REMOTE_REMOTE;
    private static final float[][] PATH_WHOLESALER_LOCAL_REMOTE;
    private static final float[][] PATH_WHOLESALER_REMOTE_LOCAL;
    private static final float[][] PATH_WHOLESALER_REMOTE_REMOTE;
    private static final float[][] PATH_DISTRIBUTOR_LOCAL_REMOTE;
    private static final float[][] PATH_DISTRIBUTOR_REMOTE_LOCAL;
    private static final float[][] PATH_DISTRIBUTOR_REMOTE_REMOTE;
    private static final float[][] PATH_PRODUCER_LOCAL_REMOTE;
    private static final float[][] PATH_PRODUCER_REMOTE_REMOTE;
    private final Player.Type playerType;
    private final Player.Type localPlayerType;
    private NodeAnimation anim;
    
    public OrderEnvelope(final Player.Type playerType, final Player.Type localPlayerType) {
        super(ResourceManager.getOptimizedImage("order_envelope_tilted.png"));
        this.playerType = playerType;
        this.localPlayerType = localPlayerType;
        this.setVisible(false);
    }
    
    public final NodeAnimation getAnimation() {
        return this.anim;
    }
    
    public final void animate(final int startDelay) {
        final float[] array = getPath(this.playerType, this.localPlayerType)[0];
        final float[] array2 = getPath(this.playerType, this.localPlayerType)[1];
        final Player.Type playerType = this.playerType;
        final Player.Type localPlayerType = this.localPlayerType;
        final Player.Type type = playerType;
        this.anim = new NodeAnimation(this) {
            private /* synthetic */ boolean val$toLocalPlayer = (playerType == null) ? (localPlayerType == Player.Type.RETAILER) : (type.toInt() + 1 == localPlayerType.toInt());
            
            @Override
            protected final void startAnimation() {
                this.stepAnimation(0.0f);
                OrderEnvelope.this.setVisible(true);
            }
            
            @Override
            protected final void stepAnimation(float n) {
                final float evaluate = Animation.AnimationTimingTarget.evaluate(n, array);
                final float evaluate2 = Animation.AnimationTimingTarget.evaluate(n, array2);
                final double rotation = -Math.atan2(Animation.AnimationTimingTarget.evaluate(n + 0.05f, array2) - evaluate2, Animation.AnimationTimingTarget.evaluate(n + 0.05f, array) - evaluate);
                if (this.val$toLocalPlayer) {
                    n *= n;
                    OrderEnvelope.this.setRotation(rotation * (1.0f - n) + 0.0 * n);
                }
                else {
                    OrderEnvelope.this.setRotation(rotation);
                }
                OrderEnvelope.this.setPositionRelativeToScene(evaluate, evaluate2);
            }
            
            @Override
            protected final void endAnimation() {
                if (!this.val$toLocalPlayer) {
                    OrderEnvelope.this.setVisible(false);
                }
            }
        };
        final NodeAnimation anim = this.anim;
        int duration = 0;
        Label_0169: {
            final Player.Type playerType2;
            if ((playerType2 = this.playerType) != null) {
                switch (playerType2) {
                    case PRODUCER: {
                        duration = 1000;
                        break Label_0169;
                    }
                    case DISTRIBUTOR: {
                        duration = 1000;
                        break Label_0169;
                    }
                    case WHOLESALER: {
                        duration = 1000;
                        break Label_0169;
                    }
                }
            }
            duration = 1000;
        }
        anim.setDuration(duration);
        this.anim.setStartDelay(startDelay);
        this.anim.start();
    }
    
    private static float[][] getPath(final Player.Type type, final Player.Type type2) {
        if (type == null) {
            if (type2 == Player.Type.RETAILER) {
                return OrderEnvelope.PATH_CUSTOMER_REMOTE_LOCAL;
            }
            return OrderEnvelope.PATH_CUSTOMER_REMOTE_REMOTE;
        }
        else {
            switch (type) {
                case PRODUCER: {
                    if (type2 == Player.Type.PRODUCER) {
                        return OrderEnvelope.PATH_PRODUCER_LOCAL_REMOTE;
                    }
                    return OrderEnvelope.PATH_PRODUCER_REMOTE_REMOTE;
                }
                case DISTRIBUTOR: {
                    if (type2 == Player.Type.DISTRIBUTOR) {
                        return OrderEnvelope.PATH_DISTRIBUTOR_LOCAL_REMOTE;
                    }
                    if (type2 == Player.Type.PRODUCER) {
                        return OrderEnvelope.PATH_DISTRIBUTOR_REMOTE_LOCAL;
                    }
                    return OrderEnvelope.PATH_DISTRIBUTOR_REMOTE_REMOTE;
                }
                case WHOLESALER: {
                    if (type2 == Player.Type.WHOLESALER) {
                        return OrderEnvelope.PATH_WHOLESALER_LOCAL_REMOTE;
                    }
                    if (type2 == Player.Type.DISTRIBUTOR) {
                        return OrderEnvelope.PATH_WHOLESALER_REMOTE_LOCAL;
                    }
                    return OrderEnvelope.PATH_WHOLESALER_REMOTE_REMOTE;
                }
                default: {
                    if (type2 == Player.Type.RETAILER) {
                        return OrderEnvelope.PATH_RETAILER_LOCAL_REMOTE;
                    }
                    if (type2 == Player.Type.WHOLESALER) {
                        return OrderEnvelope.PATH_RETAILER_REMOTE_LOCAL;
                    }
                    return OrderEnvelope.PATH_RETAILER_REMOTE_REMOTE;
                }
            }
        }
    }
    
    static {
        PATH_CUSTOMER_REMOTE_LOCAL = new float[][] { { -0.13f, -0.12f, 0.13f, 0.41f, 0.5f, 0.58f, 0.58f }, { 0.02f, 0.02f, 0.14f, 0.11f, 0.09f, 0.12f, 0.17f } };
        PATH_CUSTOMER_REMOTE_REMOTE = new float[][] { { -0.13f, -0.12f, 0.05f, 0.18f, 0.15f, 0.08f }, { 0.0f, 0.07f, 0.08f, 0.1f, 0.31f, 0.4f } };
        PATH_RETAILER_LOCAL_REMOTE = new float[][] { { 0.06f, 0.12f, 0.25f, 0.64f, 0.85f, 0.94f }, { 0.49f, 0.38f, 0.22f, 0.29f, 0.29f, 0.26f } };
        PATH_RETAILER_REMOTE_LOCAL = new float[][] { { 0.07f, 0.11f, 0.38f, 0.71f, 0.9f, 0.92f, 0.85f }, { 0.35f, 0.29f, 0.16f, 0.09f, 0.08f, 0.11f, 0.22f } };
        PATH_RETAILER_REMOTE_REMOTE = new float[][] { { 0.07f, 0.13f, 0.29f, 0.64f, 0.85f, 0.94f }, { 0.31f, 0.27f, 0.21f, 0.29f, 0.29f, 0.26f } };
        PATH_WHOLESALER_LOCAL_REMOTE = new float[][] { { 0.43f, 0.47f, 0.49f, 0.36f, 0.17f, 0.13f, 0.13f }, { 0.49f, 0.39f, 0.28f, 0.26f, 0.52f, 0.84f, 0.84f } };
        PATH_WHOLESALER_REMOTE_LOCAL = new float[][] { { 0.86f, 0.86f, 0.59f, 0.44f, 0.59f, 0.59f }, { 0.29f, 0.29f, 0.13f, 0.3f, 0.56f, 0.57f } };
        PATH_WHOLESALER_REMOTE_REMOTE = new float[][] { { 0.86f, 0.86f, 0.71f, 0.49f, 0.38f, 0.13f, 0.13f }, { 0.29f, 0.29f, 0.21f, 0.46f, 0.75f, 0.84f, 0.84f } };
        PATH_DISTRIBUTOR_LOCAL_REMOTE = new float[][] { { 0.09f, 0.11f, 0.27f, 0.47f, 0.69f, 0.86f, 0.93f }, { 0.85f, 0.79f, 0.7f, 0.69f, 0.75f, 0.81f, 0.8f } };
        PATH_DISTRIBUTOR_REMOTE_LOCAL = new float[][] { { 0.07f, 0.1f, 0.31f, 0.55f, 0.77f, 0.93f, 0.93f }, { 0.88f, 0.85f, 0.62f, 0.49f, 0.48f, 0.52f, 0.56f } };
        PATH_DISTRIBUTOR_REMOTE_REMOTE = new float[][] { { 0.07f, 0.1f, 0.29f, 0.56f, 0.72f, 0.88f, 0.88f }, { 0.88f, 0.85f, 0.73f, 0.71f, 0.78f, 0.81f, 0.81f } };
        PATH_PRODUCER_LOCAL_REMOTE = new float[][] { { 0.43f, 0.46f, 0.55f, 0.77f, 1.11f, 1.12f }, { 0.83f, 0.79f, 0.66f, 0.67f, 0.75f, 0.75f } };
        PATH_PRODUCER_REMOTE_REMOTE = new float[][] { { 0.86f, 0.83f, 0.69f, 0.72f, 1.11f, 1.12f }, { 0.81f, 0.81f, 0.69f, 0.52f, 0.64f, 0.64f } };
    }
}
