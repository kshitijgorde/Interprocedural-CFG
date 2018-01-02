// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import Go.strategy.AlphaBetaPruningGoStrategy;
import Go.strategy.GoStrategy;
import Go.strategy.GoEstimatorInterface;
import Go.strategy.MinMaxGoStrategy;
import Go.strategy.AtariGoEstimatorCho;
import Go.AtariGoMinMaxPlayer;
import Go.AtariGoGreedyPlayer;
import Go.MoveInputDeviceListener;
import Go.HumanGoPlayer;
import Goban.GobanView;
import Go.AtariGoGame;
import Go.Player;
import Go.GoGame;

public class GameManager
{
    private int boardSize;
    private GoGame goGame;
    private Player blackPlayer;
    private Player whitePlayer;
    
    public GameManager() {
        this.boardSize = 5;
    }
    
    public GoGame createGame(final int boardSize, final String blackPlayerType, final String whitePlayerType) {
        if (this.blackPlayer != null) {
            this.blackPlayer = null;
        }
        if (this.whitePlayer != null) {
            this.whitePlayer = null;
        }
        this.boardSize = boardSize;
        if (blackPlayerType.equals("Human player")) {
            this.blackPlayer = this.createHumanPlayer(1);
        }
        else if (blackPlayerType.equals("Greedy Computer player")) {
            this.blackPlayer = this.createGreedyPlayer(1);
        }
        else if (blackPlayerType.equals("MinMax Computer player")) {
            this.blackPlayer = this.createMinMaxPlayer(1);
        }
        else if (blackPlayerType.equals("Alpha-Beta Computer player")) {
            this.blackPlayer = this.createAlphaBetaPlayer(1);
        }
        if (whitePlayerType.equals("Human player")) {
            this.whitePlayer = this.createHumanPlayer(-1);
        }
        else if (whitePlayerType.equals("Greedy Computer player")) {
            this.whitePlayer = this.createGreedyPlayer(-1);
        }
        else if (whitePlayerType.equals("MinMax Computer player")) {
            this.whitePlayer = this.createMinMaxPlayer(-1);
        }
        else if (whitePlayerType.equals("Alpha-Beta Computer player")) {
            this.whitePlayer = this.createAlphaBetaPlayer(-1);
        }
        this.goGame = new AtariGoGame(boardSize, this.blackPlayer, this.whitePlayer);
        this.playerSettings(this.blackPlayer, this.goGame);
        this.playerSettings(this.whitePlayer, this.goGame);
        return this.goGame;
    }
    
    public void registerGobanView(final GobanView gobanView) {
        if (this.blackPlayer instanceof HumanGoPlayer) {
            gobanView.addMoveInputDeviceListener((MoveInputDeviceListener)this.blackPlayer);
        }
        if (this.whitePlayer instanceof HumanGoPlayer) {
            gobanView.addMoveInputDeviceListener((MoveInputDeviceListener)this.whitePlayer);
        }
    }
    
    public void unregisterGobanView(final GobanView gobanView) {
        gobanView.removeAllMoveInputDeviceListeners();
    }
    
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }
    
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }
    
    protected HumanGoPlayer createHumanPlayer(final int color) {
        return new HumanGoPlayer(color);
    }
    
    protected AtariGoGreedyPlayer createGreedyPlayer(final int color) {
        return new AtariGoGreedyPlayer(color);
    }
    
    protected AtariGoMinMaxPlayer createMinMaxPlayer(final int color) {
        return new AtariGoMinMaxPlayer(color, new MinMaxGoStrategy(color, new AtariGoEstimatorCho()));
    }
    
    protected AtariGoMinMaxPlayer createAlphaBetaPlayer(final int color) {
        return new AtariGoMinMaxPlayer(color, new AlphaBetaPruningGoStrategy(color, new AtariGoEstimatorCho()));
    }
    
    protected void playerSettings(final Player player, final GoGame goGame) {
        if (!(player instanceof HumanGoPlayer)) {
            if (player instanceof AtariGoGreedyPlayer || player instanceof AtariGoMinMaxPlayer) {
                player.setGame(goGame);
            }
        }
    }
}
