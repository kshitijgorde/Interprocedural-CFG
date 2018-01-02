// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.lobby;

import com.masystem.beergame.ui.graphics.StretchableImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.component.Panel;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public final class StartPage extends BeerGamePage
{
    @Override
    public final void onSetup() {
        this.addChild(new BeerGameImage("login_bg.png"));
        final Panel panel = new Panel();
        this.addChild(panel);
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel("<html><h2>Welcome to BeerGame!</h2><p>Would you like to create a new game or join an existing one?</p></html>");
        panel.addChild(beerGameTextLabel);
        beerGameTextLabel.setSizeRelativeToParent(0.3f, 0.25f);
        beerGameTextLabel.setHorizontalAlignment(0);
        final BeerGameTextButton beerGameTextButton = new BeerGameTextButton("NEW GAME");
        panel.addChild(beerGameTextButton);
        beerGameTextButton.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                StartPage.this.gotoNextPage(new NewGamePage());
            }
        });
        final BeerGameTextButton beerGameTextButton2 = new BeerGameTextButton("JOIN GAME");
        panel.addChild(beerGameTextButton2);
        beerGameTextButton2.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                StartPage.this.gotoNextPage(new JoinGamePage());
            }
        });
        StretchableImage.horizontal(beerGameTextLabel, 17, this, 17, this, -0.04f);
        StretchableImage.vertical(beerGameTextButton, 17, this, 17, this, -0.04f);
        StretchableImage.horizontal(beerGameTextButton, 17, this, 17, this, 0.24f);
        StretchableImage.vertical(beerGameTextButton2, 17, this, 17, this, 0.04f);
        StretchableImage.horizontal(beerGameTextButton2, 17, this, 17, this, 0.24f);
        panel.setPositionRelativeToParent(0.5f, 0.495f);
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
    }
}
