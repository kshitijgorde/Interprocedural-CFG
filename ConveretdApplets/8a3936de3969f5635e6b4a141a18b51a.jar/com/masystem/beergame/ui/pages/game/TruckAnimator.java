// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import java.awt.Color;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.Config;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.network.protocol.Player;

public class TruckAnimator
{
    private final GamePage gamePage;
    private final int incoming;
    private final int outgoing;
    private final int stock;
    private final int cashflow;
    private final int supplyChainResult;
    
    public TruckAnimator(final GamePage gamePage, final int incoming, final int outgoing, final int stock, final int cashflow, final int supplyChainResult) {
        this.gamePage = gamePage;
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.stock = stock;
        this.cashflow = cashflow;
        this.supplyChainResult = supplyChainResult;
    }
    
    public final void start() {
        final LocalPlayerView localPlayerView = this.gamePage.getLocalPlayerView();
        final Player.Type type = this.gamePage.getLocalPlayer().getType();
        ((BeerTruck)this.gamePage.getById(String.format("truck-%s", GamePage.getPlayerTypeId(type)))).animate(1400);
        if (type != Player.Type.PRODUCER) {
            ((BeerTruck)this.gamePage.getById(String.format("truck-%s", GamePage.getPlayerTypeId(Player.Type.fromInt(type.toInt() + 1))))).animate(0);
        }
        final Points points;
        (points = (Points)this.gamePage.getById("pointsIncoming")).setText("+" + this.incoming);
        points.setPositionRelativeTo(localPlayerView, 0.8f, 0.3f);
        final Points points2 = points;
        final float n = 2000.0f;
        float n2 = 0.0f;
        switch (type) {
            case PRODUCER: {
                n2 = 0.2f;
                break;
            }
            case WHOLESALER: {
                n2 = 0.85f;
                break;
            }
            default: {
                n2 = 0.35f;
                break;
            }
        }
        points2.createAnimation((int)(n * n2)).start();
        final Points points3;
        (points3 = (Points)this.gamePage.getById("pointsOutgoing")).setText("-" + this.outgoing);
        points3.setPositionRelativeTo(localPlayerView, 0.2f, 0.3f);
        final NodeAnimation animation = points3.createAnimation((int)(2000.0f * getIncomeDelay(type)));
        points3.getAnimation().setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationStart() {
                localPlayerView.setStock(TruckAnimator.this.stock);
            }
        });
        animation.start();
        final Points points4 = (Points)this.gamePage.getById("pointsCashflow");
        if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
            points4.setText((this.cashflow < 0) ? ("-$" + -this.cashflow) : ("+$" + this.cashflow));
            points4.setStyle((this.cashflow < 0) ? 1 : 2);
        }
        else {
            points4.setText("$" + this.cashflow);
            points4.setStyle(0);
        }
        points4.setPositionRelativeTo(localPlayerView, 0.47f, 0.5f);
        final NodeAnimation animation2;
        (animation2 = points4.createAnimation((int)(2000.0f * (getIncomeDelay(type) + 1.0f)))).setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationStart() {
                localPlayerView.setResult(localPlayerView.getResult() + TruckAnimator.this.cashflow);
                final BeerGameTextLabel beerGameTextLabel;
                (beerGameTextLabel = (BeerGameTextLabel)TruckAnimator.this.gamePage.findById("supplyChainResult")).setText(TruckAnimator.this.supplyChainResult);
                beerGameTextLabel.setTextColor((TruckAnimator.this.supplyChainResult < 0) ? GamePage.DARK_RED : Color.BLACK);
            }
            
            @Override
            public final void onAnimationEnd() {
                localPlayerView.showReport();
                TruckAnimator.this.onFinished();
            }
        });
        animation2.start();
    }
    
    protected void onFinished() {
    }
    
    private static float getIncomeDelay(final Player.Type type) {
        switch (type) {
            case PRODUCER: {
                return 1.2f;
            }
            case WHOLESALER: {
                return 1.3f;
            }
            default: {
                return 1.0f;
            }
        }
    }
}
