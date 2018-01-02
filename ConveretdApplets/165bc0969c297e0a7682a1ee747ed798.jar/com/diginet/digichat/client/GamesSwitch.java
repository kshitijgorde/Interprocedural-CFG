// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.awt.CheckButton;
import java.awt.image.ImageObserver;
import com.diginet.digichat.common.Game;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.bj;

public class GamesSwitch extends bj
{
    protected int[] nGames;
    protected r[] btnGames;
    
    protected GamesSwitch(final i i, final boolean b) {
        this.setForeground(i.cc.clrInnText);
        this.setBackground(i.cc.d);
        final GridBagLayout gridBagLayout;
        this.setLayout(gridBagLayout = new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints3.weightx = n;
        gridBagConstraints2.weighty = n;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        i.games.a(true);
        int[] array;
        String[] array2;
        r[] array3;
        try {
            final int b2;
            array = new int[b2 = i.games.b()];
            array2 = new String[b2];
            array3 = new r[b2];
            for (int j = 0; j < b2; ++j) {
                final Game game = (Game)i.games.d(j);
                if (game.i(29) && game.i(30)) {
                    array[n2] = game.w();
                    array2[n2] = game.x();
                    int width;
                    int height;
                    r r;
                    if (game.imgLogo != null) {
                        width = game.imgLogo.getWidth(null) + 6;
                        height = game.imgLogo.getHeight(null) + 6;
                        (r = (b ? new CheckButton(width, height) : new r(width, height))).a(game.imgLogo);
                    }
                    else {
                        r = (b ? new CheckButton(game.x()) : new r(game.x()));
                        height = r.size().height;
                        width = r.size().width;
                    }
                    array3[n2++] = r;
                    String s;
                    if ((s = game.strDesc) == null || s.length() == 0) {
                        s = a5.a(c.a("Click here to play %1 game."), new String[] { game.x() });
                    }
                    r.a(s, "");
                    if (width > n3) {
                        n3 = width;
                    }
                    if (height > n4) {
                        n4 = height;
                    }
                }
            }
        }
        finally {
            i.games.a();
        }
        if (n2 > 0) {
            if (n3 < 80) {
                n3 = 80;
            }
            if (n4 < 20) {
                n4 = 20;
            }
            this.nGames = new int[n2];
            this.btnGames = new r[n2];
            gridBagConstraints.fill = 0;
            for (int k = 0; k < n2; ++k) {
                this.nGames[k] = array[k];
                final Label label;
                (label = new Label(array2[k], 0)).setFont(dw.d);
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.anchor = 17;
                gridBagLayout.setConstraints(label, gridBagConstraints);
                this.add(label);
                final r r2;
                (r2 = array3[k]).resize(n3, n4);
                gridBagConstraints.anchor = 13;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.btnGames[k] = r2, gridBagConstraints);
                this.add(r2);
            }
            gridBagConstraints.fill = 2;
        }
        else {
            this.btnGames = null;
            final Label label2;
            (label2 = new Label("No games available.", 1)).setFont(dw.d);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
        }
    }
}
