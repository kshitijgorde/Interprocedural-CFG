// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Enumeration;
import java.util.Vector;

public abstract class GoGame implements GoConstants
{
    private GoPosition oldGoPosition;
    public GoPosition goPosition;
    public int boardSize;
    protected int turn;
    protected Player blackPlayer;
    protected Player whitePlayer;
    protected ListOfStones changedLocations;
    protected Move currentMove;
    protected int gameResult;
    protected int nrCapturedStones;
    protected int consecutivePasses;
    private Vector listeners;
    
    public GoGame() {
        this.oldGoPosition = null;
        this.goPosition = null;
        this.turn = 1;
        this.blackPlayer = null;
        this.whitePlayer = null;
        this.changedLocations = null;
        this.gameResult = 100;
        this.nrCapturedStones = 0;
        this.consecutivePasses = 0;
        this.listeners = null;
        this.listeners = new Vector();
    }
    
    public GoGame(final int boardSize, final Player blackPlayer, final Player whitePlayer) {
        this.oldGoPosition = null;
        this.goPosition = null;
        this.turn = 1;
        this.blackPlayer = null;
        this.whitePlayer = null;
        this.changedLocations = null;
        this.gameResult = 100;
        this.nrCapturedStones = 0;
        this.consecutivePasses = 0;
        this.listeners = null;
        this.boardSize = boardSize;
        this.turn = 1;
        this.listeners = new Vector();
        (this.goPosition = new GoPosition(boardSize)).setLastMove(new Move(-1));
        this.changedLocations = new ListOfStones();
        this.currentMove = new Move();
        (this.blackPlayer = blackPlayer).setGame(this);
        (this.whitePlayer = whitePlayer).setGame(this);
        this.addListener(blackPlayer);
        this.addListener(whitePlayer);
        this.gameResult = 100;
        this.nrCapturedStones = 0;
        this.consecutivePasses = 0;
    }
    
    public void playGame() {
        this.newGameSetup();
        while (!this.gameOver()) {
            this.playNextMove();
        }
    }
    
    public void newGameSetup() {
        this.emptyGoban();
        this.turn = 1;
        this.gameResult = 100;
        this.blackPlayer.initPlayer();
        this.whitePlayer.initPlayer();
        this.oldGoPosition = null;
        this.goPosition = new GoPosition(this.boardSize);
    }
    
    public void playNextMove() {
        Player currentPlayer;
        if (this.turn == 1) {
            currentPlayer = this.blackPlayer;
        }
        else {
            currentPlayer = this.whitePlayer;
        }
        currentPlayer.makeMove(this.goPosition);
    }
    
    public abstract boolean gameOver();
    
    public int getGameResult() {
        if (!this.gameOver()) {
            this.gameResult = 100;
        }
        return this.gameResult;
    }
    
    public void switchColors() {
        final Player aux = this.blackPlayer;
        this.blackPlayer = this.whitePlayer;
        this.whitePlayer = aux;
        this.blackPlayer.color = 1;
        this.whitePlayer.color = -1;
    }
    
    protected void switchTurn() {
        if (this.turn == 1) {
            this.turn = -1;
        }
        else {
            this.turn = 1;
        }
    }
    
    protected int validatePosition(final GoPosition position) {
        return 1;
    }
    
    private void emptyGoban() {
        this.nrCapturedStones = 0;
        this.consecutivePasses = 0;
        for (int i = 0; i < this.boardSize; ++i) {
            for (int k = 0; k < this.boardSize; ++k) {
                this.goPosition.goban[i][k].state = 0;
                this.goPosition.goban[i][k].computeLiberties();
            }
        }
    }
    
    public int newMove(final int x, final int y) {
        if (x == -100 || y == -100) {
            ++this.consecutivePasses;
            this.switchTurn();
            return 1;
        }
        this.nrCapturedStones = 0;
        this.consecutivePasses = 0;
        final ListOfStones changedLocations = new ListOfStones();
        try {
            this.oldGoPosition = (GoPosition)this.goPosition.cloneCopy();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        final GobanLocation stone = this.goPosition.addNewStone(x, y, this.turn);
        stone.computeLiberties();
        changedLocations.deleteAllElements();
        changedLocations.add(stone);
        this.goPosition.setNewMove(stone, changedLocations);
        this.nrCapturedStones = changedLocations.length() - 1;
        this.switchTurn();
        this.notifyPositionChanged(new GoGameEvent(this, this.oldGoPosition, this.goPosition));
        return 1;
    }
    
    public void addListener(final GoGameListener goGameListener) {
        if (!this.listeners.contains(goGameListener)) {
            this.listeners.addElement(goGameListener);
        }
    }
    
    public void removeListener(final GoGameListener goGameListener) {
        this.listeners.removeElement(goGameListener);
    }
    
    private synchronized void notifyPositionChanged(final GoGameEvent goGameEvent) {
        final Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            final GoGameListener goGameListener = e.nextElement();
            goGameListener.positionChanged(goGameEvent);
        }
    }
}
