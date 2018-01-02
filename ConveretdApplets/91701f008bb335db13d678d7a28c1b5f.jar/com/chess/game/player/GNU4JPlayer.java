// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.game.player;

import java.util.LinkedList;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.PrintStream;
import com.chess.game.ChessGame;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import org.chessbox.gnu4j.GNU4JInterface;
import com.chess.chessboard.MoveListener;
import java.io.IOException;
import java.io.FilterOutputStream;
import org.chessbox.gnu4j.Common;
import org.chessbox.gnu4j.Debug;
import com.chess.util.Util;
import com.chess.chessboard.Chessboard;
import org.apache.log4j.Logger;
import com.chess.chessboard.ChessboardModel;
import java.io.OutputStream;
import com.chess.io.InputOutputStream;
import com.chess.game.MoveUndoer;
import com.chess.game.Player;

public class GNU4JPlayer extends Player implements MoveUndoer
{
    private MoveInputStream movesInputStream;
    private InputOutputStream ioStreamForOutput;
    private OutputStream outputStream;
    private final ThinkerRunnable thinker;
    private Thread thinkerThread;
    private static final String MOVE_PATTERN_1 = "\\d+\\..*\\w\\d\\w\\d";
    private static final String MOVE_PATTERN_2 = "My move is: \\w\\d\\w\\d";
    private static final String GAME_WON_PATTERN = "(0-1|1-0) \\{computer (wins|loses) as (white|black)\\}";
    private static final String STALEMATE_PATTERN = "1/2-1/2 \\{stalemate\\}";
    private static final String DRAW_PATTERN = "1/2-1/2 \\{draw\\}";
    private static final String ILLEGAL_MOVE = "Illegal Move";
    private ChessboardModel.PieceColor winner;
    private String finalPGN;
    private Logger logger;
    
    public GNU4JPlayer() {
        this(null, null, null, Level.MEDIUM);
    }
    
    public GNU4JPlayer(final Chessboard chessboard, final String name, final ChessboardModel.PieceColor color, final Level level) {
        super(chessboard, name, color);
        this.logger = Util.getLogger((Class)this.getClass());
        this.winner = null;
        this.thinker = new ThinkerRunnable();
        Debug.disable();
        this.setLevel(level);
        try {
            this.movesInputStream = new MoveInputStream();
            Common.stdout = new MovesSourcePrintStream(this.movesInputStream);
            this.ioStreamForOutput = new InputOutputStream();
            this.outputStream = new FilterOutputStream(this.ioStreamForOutput.getOutputStream()) {
                public void write(final byte[] bytes, final int offset, final int length) throws IOException {
                    super.write(bytes, offset, length);
                    GNU4JPlayer.this.logger.trace((Object)("writing: " + new String(bytes, offset, length - 1)));
                }
            };
            Common.stdin = this.ioStreamForOutput;
            this.thinkerThread = new Thread(this.thinker, "Thinker Thread");
            this.logger.trace((Object)("thinker " + System.identityHashCode(this.thinker) + " started"));
            this.thinkerThread.start();
            synchronized (this.thinker) {
                if (!this.thinker.initialized) {
                    this.thinker.wait();
                }
            }
        }
        catch (IOException ex) {
            throw new IllegalStateException("Can not create streams to deal with engine", ex);
        }
        catch (InterruptedException ex2) {
            if (!this.gameOver) {
                throw new IllegalStateException("error initializing engine", ex2);
            }
        }
    }
    
    public MoveListener.Move doMove() {
        try {
            this.outputStream.write("go\n".getBytes());
            final String move = this.movesInputStream.readMove();
            this.logger.trace((Object)("  playing move: " + move));
            if (move == null || this.gameOver) {
                return null;
            }
            final int fromFile = move.charAt(0) - 'a';
            final int fromRank = move.charAt(1) - '1';
            final int toFile = move.charAt(2) - 'a';
            final int toRank = move.charAt(3) - '1';
            this.checkForGameOver();
            final ChessboardModel.Piece piece = this.chessboard.getPieceAt(fromFile, fromRank);
            final MoveListener.Move playedMove = new MoveListener.Move(piece, fromFile, fromRank, toFile, toRank);
            if (piece == null) {
                this.logger.debug((Object)("dumping out the board!. thread: " + Thread.currentThread().getName()));
                this.logger.debug((Object)("move is: " + move));
                for (int i = 7; i >= 0; --i) {
                    final StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < 8; ++j) {
                        final ChessboardModel.Piece p = this.chessboard.getPieceAt(j, i);
                        stringBuilder.append((p == null) ? ". " : (p.toLetter() + " "));
                    }
                    this.logger.debug((Object)stringBuilder.toString());
                }
            }
            return playedMove;
        }
        catch (IOException ex) {
            throw new IllegalStateException("error reading move from engine", ex);
        }
    }
    
    private void checkForGameOver() throws IOException {
        try {
            this.logger.trace((Object)"checking for game over");
            Thread.sleep(50L);
            if (this.movesInputStream.reader.ready()) {
                final String line = this.movesInputStream.readLine(true);
                this.checkForWinner(line);
            }
        }
        catch (InterruptedException ex) {
            if (!this.gameOver) {
                throw new IllegalStateException("error reading move from engine", ex);
            }
        }
    }
    
    public boolean movePlayed(final MoveListener.Move move, final boolean animate) {
        final String moveString = move.toString();
        final byte[] bytes = (moveString + "\n").getBytes();
        try {
            this.outputStream.write("manual\n".getBytes());
            this.outputStream.write(bytes);
            String engineResponse;
            do {
                engineResponse = this.movesInputStream.readLineDirectlry();
            } while (!engineResponse.endsWith(moveString));
            this.checkForGameOver();
            return !engineResponse.toLowerCase().startsWith("illegal move");
        }
        catch (IOException ex) {
            throw new IllegalStateException("error writing move to engine", ex);
        }
    }
    
    private boolean isBoardLine(final String line) {
        for (int i = 1; i < line.length(); i += 2) {
            if (line.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
    
    public void undoMoves(final int movesCount) {
        this.takeBack(movesCount);
        this.chessboard.setPosition(this.getFEN());
        this.chessboard.undoMove(movesCount);
    }
    
    public void redoMoves(final String[] moves) {
        try {
            for (final String move : moves) {
                if (move != null) {
                    this.outputStream.write("manual\n".getBytes());
                    this.outputStream.write((move + "\n").getBytes());
                    this.chessboard.redoMove(1);
                }
            }
            this.chessboard.setPosition(this.getFEN());
        }
        catch (IOException ex) {
            throw new IllegalStateException("error redoing moves", ex);
        }
    }
    
    private void initDumbValues() {
        Common.ValueP = 100;
        Common.ValueN = 100;
        Common.ValueB = 100;
        Common.ValueR = 100;
        Common.ValueQ = 100;
        Common.ValueK = 2000;
        Common.Value = new int[] { 0, Common.ValueP, Common.ValueN, Common.ValueB, Common.ValueR, Common.ValueQ, Common.ValueK };
    }
    
    private void initEasyValues() {
        Common.ValueP = 100;
        Common.ValueN = 110;
        Common.ValueB = 110;
        Common.ValueR = 130;
        Common.ValueQ = 210;
        Common.ValueK = 2000;
        Common.Value = new int[] { 0, Common.ValueP, Common.ValueN, Common.ValueB, Common.ValueR, Common.ValueQ, Common.ValueK };
    }
    
    private void initMediumValues() {
        Common.ValueP = 100;
        Common.ValueN = 200;
        Common.ValueB = 200;
        Common.ValueR = 300;
        Common.ValueQ = 500;
        Common.ValueK = 2000;
        Common.Value = new int[] { 0, Common.ValueP, Common.ValueN, Common.ValueB, Common.ValueR, Common.ValueQ, Common.ValueK };
    }
    
    private void initHardValues() {
        Common.ValueP = 100;
        Common.ValueN = 350;
        Common.ValueB = 350;
        Common.ValueR = 550;
        Common.ValueQ = 1100;
        Common.ValueK = 2000;
        Common.Value = new int[] { 0, Common.ValueP, Common.ValueN, Common.ValueB, Common.ValueR, Common.ValueQ, Common.ValueK };
    }
    
    public final void setLevel(final Level level) {
        this.level = level;
        switch (level) {
            case BASIC: {
                this.initDumbValues();
                break;
            }
            case EASY: {
                this.initEasyValues();
                break;
            }
            default: {
                this.initMediumValues();
                break;
            }
            case HARD: {
                this.initHardValues();
                break;
            }
        }
    }
    
    public void setEngineParameters() throws IOException {
        if (this.level == Level.EASY || this.level == Level.BASIC) {
            this.outputStream.write("hash off\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("easy\n".getBytes());
            this.outputStream.write("depth 1\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("time 0.01\n".getBytes());
            this.outputStream.write("book random\n".getBytes());
            this.readNonEmptyLine();
            GNU4JInterface.level = GNU4JInterface.Level.EASY;
        }
        else if (this.level == Level.MEDIUM) {
            this.outputStream.write("hash on\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("easy\n".getBytes());
            this.outputStream.write("depth 3\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("time 0.1\n".getBytes());
            this.outputStream.write("book random\n".getBytes());
            this.readNonEmptyLine();
            GNU4JInterface.level = GNU4JInterface.Level.MEDIUM;
        }
        else if (this.level == Level.HARD) {
            this.outputStream.write("hash on\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("hard\n".getBytes());
            this.outputStream.write("depth 7\n".getBytes());
            this.readNonEmptyLine();
            this.outputStream.write("time 10\n".getBytes());
            this.outputStream.write("book random\n".getBytes());
            this.readNonEmptyLine();
            GNU4JInterface.level = GNU4JInterface.Level.HARD;
        }
    }
    
    private String readNonEmptyLine() throws IOException {
        String line;
        do {
            line = this.movesInputStream.readLine();
        } while ("".equals(line));
        return line;
    }
    
    public void setEPD(final String epd) {
        try {
            this.outputStream.write(("epdload stdin " + epd.replace(' ', '_') + "\n").getBytes());
            for (int i = 0; i < 13; ++i) {
                this.movesInputStream.readLine();
            }
        }
        catch (IOException ex) {
            throw new IllegalStateException("error sending epd ro engine", ex);
        }
    }
    
    public String getFEN() {
        try {
            this.outputStream.write("show board\n".getBytes());
            final String[] boardPosition = new String[8];
            for (int i = 0; i < boardPosition.length; ++i) {
                do {
                    boardPosition[i] = this.movesInputStream.readLine();
                } while (boardPosition[i].length() != 16 || !this.isBoardLine(boardPosition[i]));
            }
            final StringBuilder fenBuffer = new StringBuilder();
            for (int j = 0; j < 8; ++j) {
                int empty = 0;
                final char[] chars = boardPosition[j].toCharArray();
                for (int k = 0; k < chars.length; k += 2) {
                    if (chars[k] == '.') {
                        ++empty;
                    }
                    else {
                        if (empty > 0) {
                            fenBuffer.append(empty);
                            empty = 0;
                        }
                        fenBuffer.append(chars[k]);
                    }
                }
                if (empty > 0) {
                    fenBuffer.append(empty);
                }
                fenBuffer.append('/');
            }
            return fenBuffer.toString();
        }
        catch (IOException ex) {
            throw new IllegalStateException("error reading board position from engine", ex);
        }
    }
    
    public String getCompleteFEN() {
        if (this.finalPGN != null) {
            return this.finalPGN;
        }
        try {
            this.outputStream.write("epdsave stdout\n".getBytes());
            String fen;
            do {
                fen = this.movesInputStream.readLine();
            } while (fen.equals(""));
            final int index = fen.indexOf("bm 1; id 1;");
            fen = fen.substring(0, index).trim();
            return fen;
        }
        catch (IOException ex) {
            throw new IllegalStateException("error reading fen position from engine", ex);
        }
    }
    
    public ChessboardModel.PieceColor getWinner() {
        return this.winner;
    }
    
    public void startNewGame() {
    }
    
    public void endGame() {
        if (this.finalPGN == null) {
            this.finalPGN = this.getCompleteFEN();
        }
        super.endGame();
    }
    
    private boolean checkForWinner(final String line) {
        if (Pattern.matches("(0-1|1-0) \\{computer (wins|loses) as (white|black)\\}", line)) {
            this.winner = (line.startsWith("1-0") ? ChessboardModel.PieceColor.WHITE : ChessboardModel.PieceColor.BLACK);
            this.endGame();
            return true;
        }
        if (Pattern.matches("1/2-1/2 \\{stalemate\\}", line) || Pattern.matches("1/2-1/2 \\{draw\\}", line)) {
            this.winner = ChessboardModel.PieceColor.DRAW;
            this.endGame();
            return true;
        }
        return false;
    }
    
    public String getMovesList() {
        final StringBuilder buffer = new StringBuilder();
        final String noGameLine = "--empty game--";
        try {
            this.outputStream.write("show game\n".getBytes());
            String line;
            do {
                line = this.movesInputStream.readLine();
                if (line.equals(noGameLine)) {
                    return "";
                }
            } while (!Pattern.matches(".*White.*Black.*", line));
            do {
                line = this.movesInputStream.readLine();
                buffer.append(line);
            } while (line.length() > 0);
            return buffer.toString();
        }
        catch (IOException ex) {
            throw new IllegalStateException("can not get moves list from the engine", ex);
        }
    }
    
    public void close() {
        try {
            this.outputStream.write("quit\n".getBytes());
            this.outputStream.close();
            this.movesInputStream.close();
            while (true) {
                try {
                    this.thinkerThread.join();
                }
                catch (InterruptedException ex2) {
                    continue;
                }
                break;
            }
            this.logger.trace((Object)"finished closing");
        }
        catch (IOException ex) {
            throw new IllegalStateException("error finalizing player", ex);
        }
    }
    
    public void takeBack(final int numOfMoves) {
        try {
            for (int i = 0; i < numOfMoves; ++i) {
                this.outputStream.write("undo\n".getBytes());
            }
        }
        catch (IOException ex) {
            throw new IllegalStateException("error undoing move", ex);
        }
    }
    
    public String getScore() {
        try {
            this.outputStream.write("show score\n".getBytes());
            String line;
            do {
                line = this.movesInputStream.readLine();
            } while (!Pattern.matches("Phase:.*score = .*", line));
            final String scoreIndicator = "score = ";
            final String score = line.substring(line.indexOf(scoreIndicator) + scoreIndicator.length());
            double scoreDouble = Double.parseDouble(score);
            scoreDouble /= 100.0;
            return new DecimalFormat("0.00").format(scoreDouble);
        }
        catch (IOException ex) {
            throw new IllegalStateException("error getting score", ex);
        }
    }
    
    public void setMoveList(final String moveList) {
        try {
            final String[] moves = ChessGame.splitMoveList(moveList);
            this.outputStream.write("new\n".getBytes());
            this.outputStream.write("manual\n".getBytes());
            for (int i = 1; i < moves.length; i += i % 3) {
                this.outputStream.write((moves[i] + "\n").getBytes());
                final String line = this.readNonEmptyLine();
                if (line.startsWith("Illegal Move")) {
                    throw new IllegalStateException("Incorrect move in the move list: " + moves[i]);
                }
            }
        }
        catch (IOException ex) {
            throw new IllegalStateException("error setting move list", ex);
        }
    }
    
    public String toString() {
        return "GNU4JPlayer " + GNU4JInterface.level;
    }
    
    private class ThinkerRunnable implements Runnable
    {
        private volatile boolean initialized;
        
        private ThinkerRunnable() {
            this.initialized = false;
        }
        
        public void run() {
            new Thread("Engine Initializer Thread") {
                public void run() {
                    synchronized (ThinkerRunnable.this) {
                        try {
                            GNU4JPlayer.this.movesInputStream.readLine();
                            GNU4JPlayer.this.movesInputStream.readLine();
                            GNU4JPlayer.this.setEngineParameters();
                            GNU4JPlayer.this.outputStream.write("level 100 2 1\n".getBytes());
                            ThinkerRunnable.this.initialized = true;
                            ThinkerRunnable.this.notify();
                        }
                        catch (IOException ex) {
                            throw new IllegalStateException("error initializing engine", ex);
                        }
                    }
                }
            }.start();
            try {
                GNU4JInterface.main(new String[] { "-x", "10" });
            }
            catch (Exception ex) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex2) {}
                if (!GNU4JPlayer.this.gameOver) {
                    throw new IllegalStateException("exception occured in thinker thread while it should be running", ex);
                }
            }
            finally {
                GNU4JPlayer.this.logger.trace((Object)("thinker " + System.identityHashCode(GNU4JPlayer.this.thinker) + " finishes"));
                GNU4JPlayer.this.logger.trace((Object)"thinker thread is exiting");
            }
        }
    }
    
    private class MovesSourcePrintStream extends PrintStream
    {
        public MovesSourcePrintStream(final InputOutputStream stream) {
            super(stream.getOutputStream());
        }
    }
    
    private class MoveInputStream extends InputOutputStream
    {
        private BufferedReader reader;
        private Queue<String> pendingMovesQueue;
        private String lastMoveLine;
        
        public MoveInputStream() throws IOException {
            this.reader = new BufferedReader(new InputStreamReader(this));
            this.pendingMovesQueue = new LinkedList<String>();
        }
        
        public synchronized String readMove() throws IOException {
            final String moveStart = "my move is: ";
            String move = null;
            boolean again = false;
            String line;
            boolean directly;
            do {
                if (!this.pendingMovesQueue.isEmpty()) {
                    directly = false;
                    line = this.pendingMovesQueue.poll();
                    GNU4JPlayer.this.logger.trace((Object)("getting: " + line + " out of buffer"));
                }
                else {
                    directly = true;
                    line = this.readLineDirectlry();
                }
                if (line == null) {
                    return null;
                }
                GNU4JPlayer.this.logger.trace((Object)("read line: " + line));
                if (GNU4JPlayer.this.checkForWinner(line)) {
                    break;
                }
                if (line.toLowerCase().startsWith(moveStart)) {
                    move = line.substring(moveStart.length());
                }
                again = line.equals(this.lastMoveLine);
                if (again) {
                    GNU4JPlayer.this.logger.debug((Object)("--------> repetitive move! " + move));
                }
                this.lastMoveLine = line;
            } while (!line.toLowerCase().startsWith(moveStart) || again);
            GNU4JPlayer.this.logger.trace((Object)(directly ? "directly" : "from buffer"));
            return move;
        }
        
        private synchronized String readLineDirectlry() throws IOException {
            try {
                final String line = this.reader.readLine();
                return line;
            }
            catch (IOException ex) {
                if (GNU4JPlayer.this.movesInputStream.isClosed()) {
                    return null;
                }
                throw ex;
            }
        }
        
        public synchronized String readLine() throws IOException {
            return this.readLine(false);
        }
        
        public synchronized String readLine(final boolean readOneLineOnly) throws IOException {
            boolean readAnother;
            String line;
            do {
                readAnother = false;
                line = this.readLineDirectlry();
                if (Pattern.matches("\\d+\\..*\\w\\d\\w\\d", line) || Pattern.matches("My move is: \\w\\d\\w\\d", line) || line.startsWith("my rating")) {
                    GNU4JPlayer.this.logger.trace((Object)("adding " + line + " to buffer"));
                    final StackTraceElement[] arr$;
                    final StackTraceElement[] elements = arr$ = Thread.currentThread().getStackTrace();
                    for (final StackTraceElement element : arr$) {
                        GNU4JPlayer.this.logger.trace((Object)element.toString());
                    }
                    GNU4JPlayer.this.logger.trace((Object)"------------------------");
                    this.pendingMovesQueue.offer(line);
                    readAnother = true;
                }
            } while (!readOneLineOnly && (readAnother || line.toLowerCase().indexOf("hash collisions") >= 0));
            GNU4JPlayer.this.logger.trace((Object)("returning: " + line));
            return line;
        }
    }
}
