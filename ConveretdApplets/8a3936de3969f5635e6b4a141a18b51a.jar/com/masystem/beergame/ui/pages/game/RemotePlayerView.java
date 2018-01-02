// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import com.masystem.beergame.network.protocol.Player;
import java.awt.Rectangle;
import com.masystem.beergame.ui.animation.AlphaAnimation;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.scene.Scene;
import com.masystem.beergame.ui.animation.StackAnimation;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;

public final class RemotePlayerView extends BeerGameImage implements PlayerView
{
    private static float[] RETAILER_REMOTE_COORDS;
    private static float[] WHOLESALER_REMOTE_COORDS;
    private static float[] DISTRIBUTOR_REMOTE_COORDS;
    private static float[] PRODUCER_REMOTE_COORDS;
    private final BeerGameImage ghostView;
    private final BeerGameImage clickForAutoView;
    
    public RemotePlayerView(final String id) {
        super(ResourceManager.getOptimizedImage(String.format("remote_%s.png", id)));
        this.setId(id);
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel);
        beerGameTextLabel.setId("playername");
        beerGameTextLabel.setHorizontalAlignment(0);
        beerGameTextLabel.setSizeRelativeToParent(0.85f, -1.0f);
        beerGameTextLabel.setPositionRelativeToParent(0.47f, 0.55f);
        final BeerGameImage beerGameImage = new BeerGameImage("light.png");
        this.addChild(beerGameImage);
        beerGameImage.setId("light");
        beerGameImage.setPositionRelativeToParent(0.47f, 0.725f);
        beerGameImage.setVisible(false);
        this.setFiltering(2);
        (this.ghostView = new BeerGameImage(ResourceManager.getOptimizedImage(String.format("remote_%s_ghost.png", id)))).setId(String.format("%s-ghost", id));
        this.clickForAutoView = new BeerGameImage(ResourceManager.getOptimizedImage("click_for_auto.png"));
    }
    
    public final Node getGhostView() {
        return this.ghostView;
    }
    
    public final BeerGameImage getClickForAutoView() {
        return this.clickForAutoView;
    }
    
    public final void runJoinAnimation(final int startDelay) {
        final float x = this.getX();
        final float y = this.getY();
        final StackAnimation stackAnimation = new StackAnimation(this, 1.5f, 1.0f, 0.0f, 1.0f);
        final Rectangle displayBounds = this.getDisplayBounds();
        final Rectangle displayBounds2 = Scene.getCurrentScene().getDisplayBounds();
        stackAnimation.setTranslation(x + (displayBounds.x + displayBounds.width / 2 - displayBounds2.width / 2) * 0.1f, y + (displayBounds.y + displayBounds.height / 2 - displayBounds2.height / 2) * 0.1f, x, y);
        stackAnimation.setStartDelay(startDelay);
        stackAnimation.setDuration(500);
        stackAnimation.setFiltering(2);
        stackAnimation.setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationStart() {
                RemotePlayerView.this.setVisible(true);
            }
            
            @Override
            public final void onAnimationEnd() {
                RemotePlayerView.this.getClickForAutoView().setVisible(false);
                final AlphaAnimation alphaAnimation;
                (alphaAnimation = new AlphaAnimation(RemotePlayerView.this.getGhostView(), 1.0f, 0.0f)).setListener(new NodeAnimation.Adapter() {
                    @Override
                    public final void onAnimationEnd() {
                        RemotePlayerView.this.getGhostView().setVisible(false);
                    }
                });
                alphaAnimation.start();
            }
        });
        stackAnimation.start();
    }
    
    public final void runLeaveAnimation() {
        final float x = this.getX();
        final float y = this.getY();
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(this.ghostView, 0.0f, 1.0f)).setStartDelay(0);
        alphaAnimation.setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationStart() {
                RemotePlayerView.this.getGhostView().setVisible(true);
            }
            
            @Override
            public final void onAnimationEnd() {
                final StackAnimation stackAnimation = new StackAnimation(RemotePlayerView.this, 1.0f, 1.5f, 1.0f, 0.0f);
                final Rectangle displayBounds = RemotePlayerView.this.getDisplayBounds();
                final Rectangle displayBounds2 = Scene.getCurrentScene().getDisplayBounds();
                stackAnimation.setTranslation(x, y, x + (displayBounds.x + displayBounds.width / 2 - displayBounds2.width / 2) * 0.1f, y + (displayBounds.y + displayBounds.height / 2 - displayBounds2.height / 2) * 0.1f);
                stackAnimation.setDuration(500);
                stackAnimation.setFiltering(2);
                stackAnimation.setListener(new NodeAnimation.Adapter() {
                    @Override
                    public final void onAnimationEnd() {
                        RemotePlayerView.this.setVisible(false);
                        RemotePlayerView.this.setAlpha(1.0f);
                        RemotePlayerView.this.setScaling(1.0f);
                        RemotePlayerView.this.setPosition(x, y);
                    }
                });
                stackAnimation.start();
            }
        });
        alphaAnimation.start();
    }
    
    @Override
    public final void updateData(final Player player) {
        String name;
        if ((name = player.getName()).startsWith(" ghost")) {
            name = "Automatic player";
        }
        ((BeerGameTextLabel)this.getById("playername")).setText(name);
        this.getById("light").setVisible(player.isReadyForNextTurn());
    }
    
    protected static float[] getCoords(final Player.Type type) {
        switch (type) {
            case PRODUCER: {
                return RemotePlayerView.PRODUCER_REMOTE_COORDS;
            }
            case DISTRIBUTOR: {
                return RemotePlayerView.DISTRIBUTOR_REMOTE_COORDS;
            }
            case WHOLESALER: {
                return RemotePlayerView.WHOLESALER_REMOTE_COORDS;
            }
            default: {
                return RemotePlayerView.RETAILER_REMOTE_COORDS;
            }
        }
    }
    
    static {
        RemotePlayerView.RETAILER_REMOTE_COORDS = new float[] { 0.15f, 0.26f };
        RemotePlayerView.WHOLESALER_REMOTE_COORDS = new float[] { 0.86f, 0.26f };
        RemotePlayerView.DISTRIBUTOR_REMOTE_COORDS = new float[] { 0.15f, 0.79f };
        RemotePlayerView.PRODUCER_REMOTE_COORDS = new float[] { 0.86f, 0.79f };
    }
}
