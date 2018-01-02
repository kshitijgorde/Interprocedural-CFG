// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Enumeration;
import com.diginet.digichat.awt.a6;
import com.esial.util.c;
import java.net.MalformedURLException;
import com.diginet.digichat.common.Game;
import com.diginet.digichat.awt.CheckButton;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

public class GamesPanel extends Panel
{
    private i iUser;
    private GamesSwitch swtGames;
    
    public GamesPanel(final i iUser) {
        this.setBackground(iUser.cc.d);
        this.setLayout(new BorderLayout());
        this.add("North", this.swtGames = new GamesSwitch(this.iUser = iUser, true));
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && this.swtGames.btnGames != null) {
            for (int i = 0; i < this.swtGames.btnGames.length; ++i) {
                if (event.target == this.swtGames.btnGames[i] && ((CheckButton)event.target).getChecked()) {
                    final Enumeration<Play> elements = this.iUser.vecPlays.elements();
                    while (elements.hasMoreElements()) {
                        final Play play;
                        if ((play = elements.nextElement()).pnlGames == this) {
                            final Game game;
                            if ((game = (Game)this.iUser.games.e(this.swtGames.nGames[i])) != null) {
                                try {
                                    if (this.iUser.getScripts().runGame(play, this.iUser.getURL(String.valueOf(String.valueOf("/Games/").concat(String.valueOf(game.strDir))).concat(String.valueOf("/index.html")), true).toExternalForm(), game.nWidth, game.nHeight, game.w())) {
                                        for (int j = 0; j < this.swtGames.btnGames.length; ++j) {
                                            final CheckButton checkButton;
                                            if (j != i && (checkButton = (CheckButton)this.swtGames.btnGames[j]).getChecked()) {
                                                checkButton.setChecked(false);
                                            }
                                        }
                                        return true;
                                    }
                                }
                                catch (MalformedURLException ex) {}
                                ((CheckButton)event.target).setChecked(false);
                                new a6(this.iUser.a3, c.a("Note"), c.a("Cannot load game."), this.iUser).setVisible(true);
                            }
                            else {
                                this.iUser.getScripts().runGame(play, "EmptyFrame.html", 0, this.iUser.gamesHeight, -1);
                            }
                            return true;
                        }
                    }
                    new a6(this.iUser.a3, c.a("Note"), c.a("Invalid games window."), this.iUser).setVisible(true);
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}
