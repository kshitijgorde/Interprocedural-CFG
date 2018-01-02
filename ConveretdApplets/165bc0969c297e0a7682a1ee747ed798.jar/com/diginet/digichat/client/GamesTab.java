// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import com.esial.util.c;
import com.diginet.digichat.awt.ap;
import java.awt.Component;
import com.diginet.digichat.awt.t;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.util.ScaleIcon;
import com.diginet.digichat.common.Game;
import com.diginet.digichat.awt.ao;
import java.awt.Panel;

public class GamesTab extends Panel
{
    private ao aoGames;
    private h hUser;
    
    public void setGame(final Game game) {
        if (this.hUser.fLogos && game.imgLogo == null) {
            game.imgLogo = this.hUser.a(game.getLogo(), true);
        }
        game.imgIcon = ScaleIcon.scale(game.imgLogo);
        final int b;
        if ((b = this.aoGames.b(game)) < 0) {
            this.aoGames.d(game);
        }
        else {
            this.aoGames.b(game, b);
        }
    }
    
    public GamesTab(final h hUser) {
        this.hUser = hUser;
        this.setBackground(hUser.cc.j);
        this.setForeground(hUser.cc.i);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(this.aoGames = new ao());
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(c.a("Game"), "name");
        ap.d(24);
        ap.c(0);
        ap2.c(true);
        this.aoGames.a(true);
        this.aoGames.b(ap);
        this.aoGames.b(ap2);
        this.aoGames.a(ap2);
        this.aoGames.l(24);
        hUser.games.a(true);
        try {
            for (int i = 0; i < hUser.games.b(); ++i) {
                this.setGame((Game)hUser.games.d(i));
            }
        }
        finally {
            hUser.games.a();
        }
        hUser.fLogos = false;
    }
    
    public void removeGame(final Game game) {
        this.aoGames.e(game);
    }
    
    public Game getSelect() {
        return (Game)this.aoGames.g();
    }
    
    public void show() {
        super.show();
        this.aoGames.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.aoGames) {
            final Game select;
            if ((select = this.getSelect()) != null) {
                this.hUser.callGame(select);
            }
            return true;
        }
        return super.handleEvent(event);
    }
}
