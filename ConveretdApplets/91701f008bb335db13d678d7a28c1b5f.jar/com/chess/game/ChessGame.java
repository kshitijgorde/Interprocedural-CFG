// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.game;

import java.util.List;
import java.util.ArrayList;
import com.chess.applet.util.PGNGenerator;
import java.util.Date;
import javax.swing.SwingUtilities;
import com.chess.chessboard.MoveListener;
import com.chess.game.player.HumanPlayer;
import com.chess.game.player.GNU4JPlayer;
import com.chess.util.Util;
import org.apache.log4j.Logger;
import com.chess.chessboard.ChessboardModel;
import com.chess.chessboard.StatusViewer;
import com.chess.applet.ui.MoveListPanel;
import com.chess.applet.ui.MainPanel;
import com.chess.chessboard.Chessboard;

public class ChessGame extends Thread
{
    private Chessboard chessboard;
    private MainPanel mainPanel;
    private MoveListPanel moveListPanel;
    private StatusViewer statusViewer;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player computerPlayer;
    private boolean gameOver;
    private String movesList;
    private String score;
    private ChessboardModel.PieceColor turnPlayer;
    private Player currentPlayer;
    private Player otherPlayer;
    private final Object lock;
    private Logger logger;
    
    public ChessGame(final MainPanel mainPanel, final Chessboard chessboard, final MoveListPanel moveListPanel, final Player whitePlayer, final Player blackPlayer) {
        super("Game Thread");
        this.lock = new Object();
        this.logger = Util.getLogger((Class)this.getClass());
        this.mainPanel = mainPanel;
        this.chessboard = chessboard;
        this.moveListPanel = moveListPanel;
        this.statusViewer = chessboard.getStatusViewer();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.gameOver = false;
        if (whitePlayer instanceof GNU4JPlayer) {
            this.computerPlayer = whitePlayer;
            chessboard.setHumanPlayerColor(ChessboardModel.PieceColor.BLACK);
        }
        else {
            if (!(blackPlayer instanceof GNU4JPlayer)) {
                throw new IllegalArgumentException("One of the players must be a computer player");
            }
            this.computerPlayer = blackPlayer;
            chessboard.setHumanPlayerColor(ChessboardModel.PieceColor.WHITE);
        }
        chessboard.setPosition(this.computerPlayer.getFEN());
        this.turnPlayer = ChessboardModel.PieceColor.WHITE;
        this.currentPlayer = whitePlayer;
        this.otherPlayer = blackPlayer;
    }
    
    public boolean isGameOver() {
        return this.gameOver;
    }
    
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver) {
            this.interrupt();
        }
    }
    
    public void run() {
        while (true) {
            while (!this.gameOver) {
                final boolean humanTurn = this.currentPlayer instanceof HumanPlayer;
                this.chessboard.setHumanTurn(humanTurn);
                this.mainPanel.setHumanTurn(humanTurn);
                this.moveListPanel.setControlsEnabled(humanTurn);
                final MoveListener.Move move = this.makeNewMove(this.currentPlayer, this.otherPlayer);
                this.checkForWinnerAndStartNewGame();
                if (move == null) {
                    this.whitePlayer.endGame();
                    this.blackPlayer.endGame();
                    this.whitePlayer.close();
                    this.blackPlayer.close();
                    return;
                }
                synchronized (this.lock) {
                    this.turnPlayer = this.otherPlayer.getColor();
                    Player temp = this.otherPlayer;
                    this.otherPlayer = this.currentPlayer;
                    this.currentPlayer = temp;
                    temp = null;
                }
            }
            continue;
        }
    }
    
    public void setEPD(final String epd) {
        this.computerPlayer.setEPD(epd);
        this.chessboard.setPosition(this.computerPlayer.getFEN());
    }
    
    private void checkForWinnerAndStartNewGame() {
        final ChessboardModel.PieceColor winner = this.computerPlayer.getWinner();
        if (winner == null) {
            return;
        }
        this.chessboard.gameOver(winner);
        this.setGameOver(true);
    }
    
    private MoveListener.Move makeNewMove(final Player turnPlayer, final Player other) {
        boolean accepted;
        MoveListener.Move move;
        do {
            move = turnPlayer.doMove();
            if (move == null) {
                return move;
            }
            if (move.piece == null) {
                this.logger.debug((Object)("turn player is: " + turnPlayer));
            }
            move.isSpecial = this.chessboard.isSpecialMove(move);
            if (move.isPromotion && turnPlayer instanceof HumanPlayer) {
                move.promotedPiece = this.chessboard.getPieceToPromote(turnPlayer.getColor());
            }
            accepted = other.movePlayed(move, other instanceof HumanPlayer);
            if (accepted) {
                continue;
            }
            this.chessboard.clearLastMove();
            this.updateBoardPosition();
        } while (!accepted);
        this.updateMoveList();
        this.score = this.computerPlayer.getScore();
        this.statusViewer.setScore(this.score);
        this.chessboard.movePlayed(move, other instanceof HumanPlayer);
        if (move.isSpecial) {
            this.updateBoardPosition();
        }
        this.chessboard.repaint();
        return move;
    }
    
    private void updateBoardPosition() {
        final String boardPosition = this.computerPlayer.getFEN();
        this.chessboard.setPosition(boardPosition);
    }
    
    private void updateMoveList() {
        this.movesList = this.computerPlayer.getMovesList();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChessGame.this.chessboard.setMoveList(ChessGame.this.movesList);
            }
        });
    }
    
    public String getPGN() {
        final String siteName = "Chess.com";
        final Date now = new Date();
        final String whitePlayerName = this.whitePlayer.getName();
        final String blackPlayerName = this.blackPlayer.getName();
        final ChessboardModel.PieceColor winner = this.computerPlayer.getWinner();
        final String moves = (this.movesList != null) ? this.movesList.replaceAll("\\s+", " ") : "";
        return PGNGenerator.generatePGN(siteName, now, whitePlayerName, blackPlayerName, winner, moves);
    }
    
    public ChessboardModel.PieceColor getTurnPlayer() {
        return this.turnPlayer;
    }
    
    public void setTurnPlayer(final ChessboardModel.PieceColor turnPlayer) {
        this.turnPlayer = turnPlayer;
        switch (turnPlayer) {
            case WHITE: {
                this.currentPlayer = this.whitePlayer;
                this.otherPlayer = this.blackPlayer;
                break;
            }
            case BLACK: {
                this.currentPlayer = this.blackPlayer;
                this.otherPlayer = this.whitePlayer;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid turn player: " + turnPlayer);
            }
        }
    }
    
    public void switchSides() {
        synchronized (this.lock) {
            final Player temp = this.whitePlayer;
            this.whitePlayer = this.blackPlayer;
            this.blackPlayer = temp;
            this.whitePlayer.setColor(ChessboardModel.PieceColor.WHITE);
            this.blackPlayer.setColor(ChessboardModel.PieceColor.BLACK);
            if (this.turnPlayer == ChessboardModel.PieceColor.WHITE) {
                this.turnPlayer = ChessboardModel.PieceColor.BLACK;
                this.currentPlayer = this.blackPlayer;
                this.otherPlayer = this.whitePlayer;
            }
            else {
                this.turnPlayer = ChessboardModel.PieceColor.WHITE;
                this.currentPlayer = this.whitePlayer;
                this.otherPlayer = this.blackPlayer;
            }
        }
    }
    
    public void setMoveList(final String moveList) {
        this.computerPlayer.setMoveList(moveList);
        this.updateMoveList();
        this.updateBoardPosition();
    }
    
    public static String[] splitMoveList(final String moveList) {
        String[] parts = moveList.split(" ");
        final List<String> partsList = new ArrayList<String>();
        for (final String part : parts) {
            if (part.trim().length() > 0) {
                partsList.add(part);
            }
        }
        parts = new String[partsList.size()];
        return partsList.toArray(parts);
    }
}
