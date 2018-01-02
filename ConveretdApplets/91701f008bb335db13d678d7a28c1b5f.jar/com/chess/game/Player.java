// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.game;

import com.chess.chessboard.Chessboard;
import com.chess.chessboard.ChessboardModel;
import com.chess.chessboard.MoveListener;

public abstract class Player implements MoveListener
{
    protected String name;
    protected ChessboardModel.PieceColor color;
    protected Chessboard chessboard;
    protected boolean gameOver;
    protected Level level;
    
    public Player() {
    }
    
    public Player(final Chessboard chessboard, final String name, final ChessboardModel.PieceColor color) {
        this.chessboard = chessboard;
        this.name = name;
        this.color = color;
    }
    
    public abstract MoveListener.Move doMove();
    
    public boolean movePlayed(final MoveListener.Move move, final boolean animate) {
        return true;
    }
    
    public ChessboardModel.PieceColor getColor() {
        return this.color;
    }
    
    public void setColor(final ChessboardModel.PieceColor color) {
        this.color = color;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getFEN() {
        return null;
    }
    
    public String getCompleteFEN() {
        return null;
    }
    
    public void setEPD(final String epd) {
    }
    
    public ChessboardModel.PieceColor getWinner() {
        return null;
    }
    
    public void startNewGame() {
        this.gameOver = false;
    }
    
    public void endGame() {
        this.gameOver = true;
    }
    
    public String getMovesList() {
        return null;
    }
    
    public void close() {
    }
    
    public void takeBack(final int numOfMoves) {
    }
    
    public String getScore() {
        return null;
    }
    
    public void setMoveList(final String moveList) {
    }
    
    public enum Level
    {
        BASIC, 
        EASY, 
        MEDIUM, 
        HARD;
    }
}
