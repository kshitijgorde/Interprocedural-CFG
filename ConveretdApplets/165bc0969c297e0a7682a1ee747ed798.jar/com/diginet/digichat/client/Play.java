// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Vector;

public class Play
{
    public static final int STATE_LOAD = 0;
    public static final int STATE_INIT = 1;
    public static final int STATE_START = 2;
    public int nOwner;
    public int nPlay;
    public int nGame;
    public int nId;
    public int nState;
    public int nLimit;
    public int nMin;
    public int nMax;
    public int mInvites;
    public int mPlayers;
    public int[] nInvites;
    public int[] nPlayers;
    public Vector vecMoves;
    public GamesPanel pnlGames;
    public Object objTop;
    public Object objFrame;
    
    public Play(final Object objTop, final int nOwner, final int nPlay, final int nGame, final int nId) {
        final int[] array = null;
        this.nPlayers = array;
        this.nInvites = array;
        final boolean b = false;
        this.mPlayers = (b ? 1 : 0);
        this.mInvites = (b ? 1 : 0);
        this.vecMoves = new Vector();
        this.nState = 0;
        this.pnlGames = null;
        this.objFrame = null;
        this.objTop = objTop;
        this.nLimit = -1;
        this.nOwner = nOwner;
        this.nPlay = nPlay;
        this.nGame = nGame;
        this.nId = nId;
    }
}
