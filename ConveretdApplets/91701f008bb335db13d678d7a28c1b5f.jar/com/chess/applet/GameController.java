// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet;

import com.chess.game.Player;
import com.chess.chessboard.ChessboardModel;

public interface GameController
{
    void startNewGame(final ChessboardModel.PieceColor p0, final Player.Level p1, final String p2, final String p3);
    
    String getGamePGN();
    
    void setComputerLevel(final Player.Level p0);
}
