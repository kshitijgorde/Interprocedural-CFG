// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.List;
import com.chess.chessboard.settings.ModePanel;
import com.chess.chessboard.settings.BoardShapePanel;
import java.lang.reflect.Method;
import java.awt.Window;
import com.chess.game.MoveUndoer;
import com.chess.game.player.GNU4JPlayer;
import com.chess.game.player.HumanPlayer;
import com.chess.chessboard.MoveListener;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import com.chess.util.DownloadProgressListener;
import java.lang.reflect.Constructor;
import com.chess.applet.downloader.DownloadWorkerJava5;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.Color;
import org.apache.log4j.Level;
import javax.swing.UIManager;
import com.chess.util.Util;
import org.apache.log4j.Logger;
import com.chess.chessboard.ChessboardModel;
import com.chess.game.Player;
import com.chess.chessboard.Chessboard;
import com.chess.applet.ui.MainPanel;
import com.chess.game.ChessGame;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.JLabel;
import com.chess.applet.downloader.DownloadAgent;
import javax.swing.JApplet;

public class ChessApplet extends JApplet implements GameController, DownloadAgent<byte[], Object>
{
    private JLabel downloadImageLabel;
    private CardLayout cards;
    private Container contentPane;
    private ChessGame game;
    private MainPanel mainPanel;
    private Chessboard chessboard;
    private Player humanPlayer;
    private Player computerPlayer;
    private ChessboardModel.PieceColor humanColor;
    private Player.Level computerLevel;
    private String version;
    private String shortVersion;
    private Logger logger;
    
    public ChessApplet() {
        this.logger = Util.getLogger((Class)this.getClass());
    }
    
    public void init() {
        super.init();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex3) {}
        final String loggingLevel = this.getParameter("logging");
        if (loggingLevel != null && !loggingLevel.equals("")) {
            try {
                final Level level = (Level)Level.class.getField(loggingLevel.toUpperCase()).get(null);
                Util.setLoggingLevel(level);
            }
            catch (Exception ex) {
                this.logger.warn((Object)"error setting logging level. default is debug", (Throwable)ex);
            }
        }
        this.loadVersions();
        this.logger.info((Object)("Version: " + this.version));
        (this.contentPane = this.getContentPane()).setBackground(new Color(255, 255, 255));
        this.cards = new CardLayout();
        this.contentPane.setLayout(this.cards);
        final JPanel downloadImagePanel = new JPanel(null);
        this.downloadImageLabel = new JLabel(new ImageIcon(this.getClass().getResource("/load.png")));
        downloadImagePanel.setBackground(Color.white);
        downloadImagePanel.add(this.downloadImageLabel);
        final int height = (int)((this.downloadImageLabel.getIcon().getIconHeight() - 4) * 1.0625 + 4.0);
        this.downloadImageLabel.setBounds(0, 6, this.downloadImageLabel.getIcon().getIconWidth(), height);
        this.contentPane.add(downloadImagePanel, "download_panel");
        this.cards.first(this.contentPane);
        Worker worker = null;
        try {
            final Class downloadWorkerClass = Class.forName("com.chess.applet.downloader.DownloadWorker");
            final Constructor<Worker> downloadWorkerConstructor = (Constructor<Worker>)downloadWorkerClass.getConstructor(DownloadAgent.class);
            worker = downloadWorkerConstructor.newInstance(this);
        }
        catch (Throwable ex2) {
            worker = new DownloadWorkerJava5(this);
        }
        finally {
            worker.execute();
        }
    }
    
    public void start() {
        super.start();
    }
    
    public void stop() {
        super.stop();
    }
    
    private byte[] loadResources(final String url, final DownloadProgressListener listener) throws IOException {
        try {
            final URL resourcesURL = new URL(url);
            final URLConnection connection = resourcesURL.openConnection();
            final int length = connection.getContentLength();
            if (listener != null) {
                listener.sizeDetermined(length);
            }
            final InputStream inputStream = connection.getInputStream();
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Util.copyStream(inputStream, (OutputStream)outputStream, length, listener);
            outputStream.close();
            inputStream.close();
            return outputStream.toByteArray();
        }
        catch (Exception ex) {
            try {
                final Constructor<IOException> constructor = IOException.class.getConstructor(Exception.class);
                final IOException exception = constructor.newInstance(ex);
                throw exception;
            }
            catch (Exception ex2) {
                throw new IOException(ex.toString());
            }
        }
    }
    
    private void notifyPlayersGameOver() {
        if (this.humanPlayer != null) {
            this.humanPlayer.endGame();
            this.chessboard.removeMoveListener((MoveListener)this.humanPlayer);
            this.humanPlayer = null;
        }
        if (this.computerPlayer != null) {
            this.computerPlayer.endGame();
            this.computerPlayer = null;
        }
    }
    
    private static Color decodeColor(final String highlightColor) {
        final Integer color = Integer.decode(highlightColor);
        return new Color((color & 0xFF0000) >> 16, (color & 0xFF00) >> 8, color & 0xFF, (color & 0xFF000000) >> 24);
    }
    
    public void startNewGame(final ChessboardModel.PieceColor humanColor, final Player.Level computerLevel, final String fen, final String moveList) {
        this.humanColor = humanColor;
        this.computerLevel = computerLevel;
        this.notifyPlayersGameOver();
        if (this.game != null) {
            try {
                this.game.setGameOver(true);
                this.game.join();
            }
            catch (InterruptedException ex) {
                throw new IllegalStateException("error waiting thinker thread to exit", ex);
            }
        }
        this.chessboard.setReversed(humanColor == ChessboardModel.PieceColor.BLACK);
        this.doStartNewGame(humanColor, computerLevel, fen, moveList);
    }
    
    public String getGamePGN() {
        return this.game.getPGN();
    }
    
    private void doStartNewGame(final ChessboardModel.PieceColor humanColor, final Player.Level computerLevel, final String fen, final String moveList) {
        this.chessboard.startNewGame();
        this.humanPlayer = new HumanPlayer(this.chessboard, "Player", humanColor);
        ((HumanPlayer)this.humanPlayer).setChessboard(this.mainPanel.getChessboard());
        final String computerName = computerLevel.name().charAt(0) + computerLevel.name().toLowerCase().substring(1);
        this.computerPlayer = new GNU4JPlayer(this.chessboard, "Computer - " + computerName, (humanColor == ChessboardModel.PieceColor.WHITE) ? ChessboardModel.PieceColor.BLACK : ChessboardModel.PieceColor.WHITE, computerLevel);
        this.humanPlayer.startNewGame();
        this.computerPlayer.startNewGame();
        if (humanColor == ChessboardModel.PieceColor.WHITE) {
            this.game = new ChessGame(this.mainPanel, this.chessboard, this.mainPanel.getMoveListPanel(), this.humanPlayer, this.computerPlayer);
        }
        else {
            this.game = new ChessGame(this.mainPanel, this.chessboard, this.mainPanel.getMoveListPanel(), this.computerPlayer, this.humanPlayer);
        }
        if (fen != null) {
            final ChessboardModel.PieceColor activePlayer = this.getActivePlayerFromFen(fen);
            this.game.setEPD(fen);
            this.game.setTurnPlayer(activePlayer);
        }
        if (moveList != null) {
            final ChessboardModel.PieceColor activePlayer = this.getActivePlayerFromMoveList(moveList);
            this.game.setMoveList(moveList);
            this.game.setTurnPlayer(activePlayer);
        }
        this.mainPanel.setMoveUndoer((MoveUndoer)this.computerPlayer);
        this.mainPanel.setChessGame(this.game);
        this.game.start();
    }
    
    private Window getParentWindow() {
        Container parent;
        for (parent = this.getParent(); !(parent instanceof Window); parent = parent.getParent()) {}
        return (Window)parent;
    }
    
    private void loadVersions() {
        final String[] versionClassNames = { "com.chess.applet.Version", "com.chess.chessboard.Version", "org.chessbox.gnu4j.Version" };
        final String[] versions = { "ca", "cb", "gj" };
        for (int i = 0; i < versionClassNames.length; ++i) {
            String versionStr = null;
            try {
                final Class clazz = Class.forName(versionClassNames[i]);
                final Method getVersionMethod = clazz.getMethod("getVersion", (Class[])new Class[0]);
                versionStr = (String)getVersionMethod.invoke(null, new Object[0]);
            }
            catch (Exception ex) {
                versionStr = null;
            }
            finally {
                final StringBuilder sb = new StringBuilder();
                final String[] array = versions;
                final int n = i;
                array[n] = sb.append(array[n]).append(versionStr).toString();
            }
        }
        final StringBuilder builder = new StringBuilder();
        for (final String ver : versions) {
            builder.append(ver).append("-");
        }
        this.shortVersion = versions[0].substring(2);
        this.version = builder.toString().substring(0, builder.length() - 1);
    }
    
    private ChessboardModel.PieceColor getActivePlayerFromFen(final String fen) {
        final int secondFenTokenStart = fen.indexOf(32) + 1;
        if (secondFenTokenStart <= 0) {
            throw new IllegalArgumentException("malformed fen: " + fen);
        }
        final char acrivePlayer = Character.toLowerCase(fen.charAt(secondFenTokenStart));
        switch (acrivePlayer) {
            case 'w': {
                return ChessboardModel.PieceColor.WHITE;
            }
            case 'b': {
                return ChessboardModel.PieceColor.BLACK;
            }
            default: {
                throw new IllegalArgumentException("malformed fen: " + fen);
            }
        }
    }
    
    public void setComputerLevel(final Player.Level level) {
        new Thread() {
            public void run() {
                ((GNU4JPlayer)ChessApplet.this.computerPlayer).setLevel(level);
                try {
                    ((GNU4JPlayer)ChessApplet.this.computerPlayer).setEngineParameters();
                }
                catch (IOException ex) {
                    ChessApplet.this.logger.error((Object)"error setting engine parameters", (Throwable)ex);
                }
            }
        }.start();
    }
    
    private ChessboardModel.PieceColor getActivePlayerFromMoveList(final String moveList) {
        final String[] moves = ChessGame.splitMoveList(moveList);
        final int mod = moves.length % 3;
        if (mod == 2) {
            return ChessboardModel.PieceColor.BLACK;
        }
        if (mod == 0) {
            return ChessboardModel.PieceColor.WHITE;
        }
        throw new IllegalStateException("Incorrect move list");
    }
    
    public byte[] doInBackground(final DownloadProgressListener listener) throws IOException {
        final String resourcesURL = this.getParameter("resourceURL");
        return this.loadResources(resourcesURL, listener);
    }
    
    public void done(final Worker<byte[]> worker) {
        try {
            final byte[] resourcesData = worker.get();
            final String boardStyle = this.getParameter("boardStyle");
            final String boardImage = this.getParameter("boardImage");
            final String lightColor = this.getParameter("lightColor");
            final String darkColor = this.getParameter("darkColor");
            final String pieces = this.getParameter("pieces");
            final String highlightColor = this.getParameter("highlightColor");
            final String showCoordinates = this.getParameter("boardCoordinates");
            final String highlightLastMove = this.getParameter("highlightLastMove");
            final String humanPlayerColor = this.getParameter("humanPlayer");
            final String computerLevelString = this.getParameter("computerLevel");
            final String scoreVisibleString = this.getParameter("scoreVisible");
            final String fen = this.getParameter("fen");
            final String moveList = this.getParameter("moveList");
            (this.mainPanel = new MainPanel()).setFullVersion(this.version);
            this.mainPanel.setShortVersion(this.shortVersion);
            this.mainPanel.setResourcesData(resourcesData);
            this.mainPanel.setGameController(this);
            this.chessboard = this.mainPanel.getChessboard();
            if (boardStyle != null) {
                this.chessboard.setMode(Chessboard.Mode.valueOf(boardStyle.toUpperCase()));
            }
            if (lightColor != null) {
                this.chessboard.setLightColor(Color.decode(lightColor));
            }
            if (darkColor != null) {
                this.chessboard.setDarkColor(Color.decode(darkColor));
            }
            if (highlightColor != null) {
                final Color color = decodeColor(highlightColor);
                this.chessboard.setHighlightColor(color);
            }
            if (highlightLastMove != null) {
                this.chessboard.setHighlightLastMove(Boolean.parseBoolean(highlightLastMove));
            }
            if (showCoordinates != null) {
                this.chessboard.setCoordinatesPainted(Boolean.parseBoolean(showCoordinates));
            }
            this.mainPanel.setOwner(this.getParentWindow());
            final BoardShapePanel shapePanel = this.mainPanel.getShapePanel();
            shapePanel.init(this.chessboard);
            final ModePanel modePanel = this.mainPanel.getModePanel();
            modePanel.init(this.chessboard);
            if (boardImage != null) {
                shapePanel.setSelectedBoardImageName(boardImage);
            }
            if (pieces != null) {
                this.mainPanel.getPiecesPanel().setSelectedPieces(pieces);
            }
            final boolean scoreVisible = Boolean.parseBoolean(scoreVisibleString);
            this.mainPanel.setScoreVisible(scoreVisible);
            this.mainPanel.apply();
            this.contentPane.add(this.mainPanel, "main_panel");
            this.cards.last(this.contentPane);
            if (humanPlayerColor != null) {
                this.humanColor = ChessboardModel.PieceColor.valueOf(humanPlayerColor.toUpperCase());
            }
            else {
                this.humanColor = ChessboardModel.PieceColor.WHITE;
            }
            if (computerLevelString != null) {
                this.computerLevel = Player.Level.valueOf(computerLevelString.toUpperCase());
            }
            else {
                this.computerLevel = Player.Level.MEDIUM;
            }
            this.mainPanel.getGameControlPanel().init(this.humanColor, this.computerLevel);
            this.startNewGame(this.humanColor, this.computerLevel, fen, moveList);
        }
        catch (Exception ex) {
            this.logger.error((Object)"error initializing", (Throwable)ex);
        }
    }
    
    public void process(final List<Object> chunks) {
    }
    
    public interface Worker<T>
    {
        T get() throws InterruptedException, ExecutionException;
        
        void execute();
    }
}
