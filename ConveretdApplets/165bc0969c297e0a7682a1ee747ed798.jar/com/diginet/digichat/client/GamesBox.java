// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.Game;
import java.awt.Event;
import com.esial.util.c;
import com.diginet.digichat.awt.bj;

public class GamesBox extends SwitchBox
{
    private GamesSwitch swtGames;
    
    protected bj createSwitch() {
        return this.swtGames = new GamesSwitch(super.iUser, false);
    }
    
    public GamesBox(final i i) {
        super(i.y.b(), i, com.esial.util.c.a("Games"), false);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && this.swtGames.btnGames != null) {
            for (int i = 0; i < this.swtGames.btnGames.length; ++i) {
                if (event.target == this.swtGames.btnGames[i]) {
                    final Game game;
                    if ((game = (Game)super.iUser.games.e(this.swtGames.nGames[i])) != null) {
                        super.iUser.callGame(game);
                    }
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void dispose() {
        super.dispose();
        super.iUser.gamesBox = null;
    }
}
