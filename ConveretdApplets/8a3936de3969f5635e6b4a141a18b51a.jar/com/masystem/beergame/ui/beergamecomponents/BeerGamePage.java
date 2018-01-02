// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import com.masystem.beergame.ui.scene.Scene;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.transition.StackTransition;
import java.awt.Color;
import com.masystem.beergame.ui.transition.Transition;
import com.masystem.beergame.ui.component.Panel;

public class BeerGamePage extends Panel implements Transition.Listener
{
    public BeerGamePage() {
        this(Color.BLACK);
    }
    
    private BeerGamePage(final Color color) {
        super(color);
        this.setId(this.getClass().getSimpleName());
    }
    
    public static void openPage$2a8e827b(final BeerGamePage beerGamePage) {
        final StackTransition stackTransition;
        (stackTransition = new StackTransition(null, beerGamePage)).setEndScaling(1.5f);
        stackTransition.setup(Scene.getCurrentScene());
        stackTransition.start();
    }
    
    public final void gotoNextPage(BeerGamePage beerGamePage) {
        final BeerGamePage beerGamePage2 = beerGamePage;
        beerGamePage = this;
        final StackTransition stackTransition;
        (stackTransition = new StackTransition(beerGamePage, beerGamePage2)).setEndScaling(1.5f);
        stackTransition.setup(Scene.getCurrentScene());
        stackTransition.start();
    }
    
    public final void gotoPreviousPage(BeerGamePage beerGamePage) {
        final BeerGamePage beerGamePage2 = beerGamePage;
        beerGamePage = this;
        final StackTransition stackTransition;
        (stackTransition = new StackTransition(beerGamePage, beerGamePage2)).setEndScaling(0.6666667f);
        stackTransition.setup(Scene.getCurrentScene());
        stackTransition.start();
    }
    
    @Override
    public void onTransitionEnd$6db0a1c1() {
    }
}
