import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.applet.AudioClip;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Panel;
import java.util.Random;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.applet.AppletContext;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmniSlide extends Applet implements ActionListener, Runnable, ItemListener, MouseListener, MouseMotionListener
{
    String copyright;
    String linkLabel;
    String homepage;
    boolean[] imageSize;
    boolean drag;
    int loadBlockSpacing;
    int loadBlockWidth;
    int loadBlockHeight;
    Label help;
    int barWidth;
    String winText;
    String fileError;
    int imagesLoaded;
    int maxImages;
    int xOffset;
    int yOffset;
    int[] imageWidth;
    int[] imageHeight;
    int drmt;
    int minGridWidth;
    int minGridHeight;
    int maxGridWidth;
    int maxGridHeight;
    int maxPieceWidth;
    int maxPieceHeight;
    int maxImageWidth;
    int maxImageHeight;
    int gridHeight;
    int gridWidth;
    int barHeight;
    boolean hpe;
    boolean imageList;
    Button nextImage;
    Button peek;
    Label spws;
    Choice difficulty;
    Choice imageChoice;
    Label caption;
    String[] imageNames;
    String[] imageTitles;
    boolean vis;
    boolean puzzleSolved;
    String hp;
    int whichImage;
    String status;
    AppletContext ac;
    int filesLoaded;
    int filesToLoad;
    int wslWidth;
    int wslHeight;
    String erp;
    Color loadingColor;
    Color loadedColor;
    Color borderColor;
    int borderWidth;
    String wstx;
    Image loading;
    Image cover;
    Image background;
    Image winScreen;
    Image buffer;
    Image[] picture;
    Image[] loadImage;
    boolean first;
    int[][] grid;
    Image[] p;
    Dimension piece;
    int xPos;
    int yPos;
    boolean loadError;
    MediaTracker tracker;
    boolean ready;
    boolean loaded;
    Dimension ws;
    int speed;
    int speed2;
    private volatile Thread th;
    Random r;
    Panel bar;
    Label link;
    String param;
    
    public void stop() {
        this.removeAll();
        this.p = null;
        this.loading = null;
        this.cover = null;
        this.background = null;
        this.winScreen = null;
        this.buffer = null;
        this.picture = null;
        this.loadImage = null;
    }
    
    public void init() {
        this.loaded = false;
        this.ready = false;
        this.hpe = false;
        this.imagesLoaded = 0;
        this.filesLoaded = 0;
        this.filesToLoad = 0;
        this.puzzleSolved = false;
        this.imageChoice = new Choice();
        this.imageSize = new boolean[this.maxImages];
        this.drmt = 0;
    }
    
    public void start() {
        this.p = new Image[this.maxGridWidth * this.maxGridHeight];
        this.picture = new Image[this.maxImages];
        this.loadImage = new Image[this.maxImages];
        this.ready = false;
        this.loaded = false;
        this.initLoadingScreen();
        if (this.ws.width >= 300 & this.ws.height >= 300) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void run() {
        this.loadFiles();
        if (this.imagesLoaded == 0) {
            this.ws = this.getSize();
            final Graphics graphics = this.loading.getGraphics();
            graphics.setFont(new Font("Helvetica", 1, 20));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final String s = "Could not load images";
            graphics.setColor(Color.white);
            graphics.fillRect(0, this.ws.height / 2 - fontMetrics.getHeight(), this.ws.width, (fontMetrics.getHeight() + fontMetrics.getAscent()) * 2);
            graphics.setColor(Color.red);
            graphics.drawString(s, (this.ws.width - fontMetrics.stringWidth(s)) / 2, fontMetrics.getAscent() + (this.ws.height - fontMetrics.getHeight()) / 2);
            this.repaint();
        }
        else {
            this.bar.validate();
            this.bar.setVisible(true);
            this.validate();
            this.newImage();
            this.loaded = true;
            this.help.setVisible(true);
        }
    }
    
    void newImage() {
        this.spws.setVisible(false);
        this.puzzleSolved = false;
        this.first = true;
        if (this.ready) {
            this.peek.setLabel("Image");
        }
        else {
            this.peek.setLabel("Puzzle");
        }
        this.xOffset = (this.ws.width - this.imageWidth[this.whichImage]) / 2;
        this.yOffset = (this.ws.height - this.imageHeight[this.whichImage]) / 2;
        this.piece = new Dimension(this.imageWidth[this.whichImage] / (this.gridWidth + 1), this.imageHeight[this.whichImage] / (this.gridHeight + 1));
        final Graphics graphics = this.cover.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.ws.width, this.ws.height);
        graphics.drawImage(this.picture[this.whichImage], this.xOffset, this.yOffset, null);
        if (this.drmt > this.maxGridWidth * this.maxGridWidth) {
            this.hperr();
        }
        this.dissect(this.picture[this.whichImage]);
        this.scramble();
        this.repaint();
    }
    
    boolean checkPuzzle() {
        int n = 0;
        boolean b = true;
    Label_0059:
        for (int i = 0; i <= this.gridHeight; ++i) {
            for (int j = 0; j <= this.gridWidth; ++j) {
                if (this.grid[j][i] != n++) {
                    b = false;
                    break Label_0059;
                }
            }
        }
        if (this.hpe) {
            ++this.drmt;
        }
        return b;
    }
    
    void dissect(final Image image) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i <= this.gridHeight; ++i) {
            for (int j = 0; j <= this.gridWidth; ++j) {
                final Graphics graphics = this.p[n].getGraphics();
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, this.maxImageWidth / this.minGridWidth, this.maxImageHeight / this.minGridHeight);
                graphics.drawImage(image, n2, n3, null);
                this.grid[j][i] = n++;
                n2 -= this.piece.width;
            }
            n2 = 0;
            n3 -= this.piece.height;
        }
    }
    
    void scramble() {
        for (int i = 0; i < 1000; ++i) {
            final int n = (int)(this.r.nextFloat() * 4.0f);
            final int n2 = (int)(this.r.nextFloat() * (this.gridWidth + 1));
            switch (n) {
                case 0: {
                    this.shiftLeft(n2, this.gridWidth);
                }
                case 1: {
                    this.shiftRight(n2, this.gridWidth);
                }
                case 2: {
                    this.shiftUp(n2, this.gridHeight);
                }
                case 3: {
                    this.shiftDown(n2, this.gridHeight);
                    break;
                }
            }
        }
        this.drawPieces();
        this.repaint();
    }
    
    String getString(final char[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string += (char)(array[i] - (array.length - i));
        }
        return string;
    }
    
    void drawPieces() {
        final Graphics graphics = this.buffer.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.ws.width, this.ws.height);
        for (int i = 0; i <= this.gridHeight; ++i) {
            for (int j = 0; j <= this.gridWidth; ++j) {
                final int n = this.grid[j][i];
                graphics.setClip(j * this.piece.width + this.xOffset, i * this.piece.height + this.yOffset, this.piece.width, this.piece.height);
                graphics.drawImage(this.p[n], j * this.piece.width + this.xOffset, i * this.piece.height + this.yOffset, null);
            }
        }
    }
    
    void drawRow(final int n) {
        final Graphics graphics = this.buffer.getGraphics();
        for (int i = 0; i <= this.gridWidth; ++i) {
            graphics.setClip(i * this.piece.width + this.xOffset, n * this.piece.height + this.yOffset, this.piece.width, this.piece.height);
            graphics.drawImage(this.p[this.grid[i][n]], i * this.piece.width + this.xOffset, n * this.piece.height + this.yOffset, null);
        }
    }
    
    void drawColumn(final int n) {
        final Graphics graphics = this.buffer.getGraphics();
        for (int i = 0; i <= this.gridHeight; ++i) {
            graphics.setClip(n * this.piece.width + this.xOffset, i * this.piece.height + this.yOffset, this.piece.width, this.piece.height);
            graphics.drawImage(this.p[this.grid[n][i]], n * this.piece.width + this.xOffset, i * this.piece.height + this.yOffset, null);
        }
    }
    
    void shiftLeft(final int n, final int n2) {
        final int n3 = this.grid[0][n];
        for (int i = 0; i < n2; ++i) {
            this.grid[i][n] = this.grid[i + 1][n];
        }
        this.grid[n2][n] = n3;
    }
    
    void shiftRight(final int n, final int n2) {
        final int n3 = this.grid[n2][n];
        for (int i = n2; i > 0; --i) {
            this.grid[i][n] = this.grid[i - 1][n];
        }
        this.grid[0][n] = n3;
    }
    
    void shiftUp(final int n, final int n2) {
        final int n3 = this.grid[n][0];
        for (int i = 0; i < n2; ++i) {
            this.grid[n][i] = this.grid[n][i + 1];
        }
        this.grid[n][n2] = n3;
    }
    
    void shiftDown(final int n, final int n2) {
        final int n3 = this.grid[n][n2];
        for (int i = n2; i > 0; --i) {
            this.grid[n][i] = this.grid[n][i - 1];
        }
        this.grid[n][0] = n3;
    }
    
    public void paint(final Graphics graphics) {
        this.showStatus(this.copyright);
        if (this.ready) {
            graphics.drawImage(this.buffer, 0, 0, null);
            this.spws.setVisible(this.puzzleSolved);
        }
        else if (!this.loaded) {
            graphics.drawImage(this.loading, 0, 0, null);
        }
        else {
            graphics.drawImage(this.cover, 0, 0, null);
            this.spws.setVisible(this.first);
        }
    }
    
    public void fileLoaded(final AudioClip audioClip) {
        audioClip.play();
        audioClip.stop();
        this.fileLoaded();
    }
    
    public void fileLoading() {
        final Graphics graphics = this.loading.getGraphics();
        graphics.setColor(this.loadingColor);
        graphics.fillRect(1 + (this.ws.width / 2 - this.filesToLoad * this.loadBlockSpacing / 2) + this.filesLoaded * this.loadBlockSpacing, this.ws.height / 2 + 12, this.loadBlockWidth, this.loadBlockHeight);
        this.repaint();
    }
    
    public void fileLoaded() {
        final Graphics graphics = this.loading.getGraphics();
        graphics.setColor(this.loadedColor);
        graphics.fillRect(1 + (this.ws.width / 2 - this.filesToLoad * this.loadBlockSpacing / 2) + this.filesLoaded * this.loadBlockSpacing, this.ws.height / 2 + 12, this.loadBlockWidth, this.loadBlockHeight);
        ++this.filesLoaded;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.help.isVisible()) {
            this.help.setVisible(false);
        }
        final String actionCommand = actionEvent.getActionCommand();
        if (this.loaded & actionCommand.equals("peek")) {
            this.ready = !this.ready;
            if (this.ready) {
                this.first = false;
                this.peek.setLabel("Image");
            }
            else {
                this.peek.setLabel("Puzzle");
            }
            this.repaint();
        }
        if (actionCommand.equals("nextpuzzle")) {
            ++this.whichImage;
            if (this.whichImage >= this.imagesLoaded) {
                this.whichImage = 0;
            }
            this.newImage();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.help.isVisible()) {
            this.help.setVisible(false);
        }
        if (this.loaded) {
            if (this.imageList) {
                this.whichImage = this.imageChoice.getSelectedIndex();
            }
            this.gridWidth = this.difficulty.getSelectedIndex() + this.minGridWidth - 1;
            this.gridHeight = this.gridWidth;
            this.newImage();
            this.peek.requestFocus();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.help.isVisible()) {
            this.help.setVisible(false);
        }
        this.peek.requestFocus();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.peek.requestFocus();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.peek.requestFocus();
        final int n = mouseEvent.getX() - this.xOffset;
        final int n2 = mouseEvent.getY() - this.yOffset;
        if (n >= 0 && n <= this.imageWidth[this.whichImage] && n2 >= 0 && n2 <= this.imageHeight[this.whichImage]) {
            this.drag = true;
            this.xPos = (mouseEvent.getX() - this.xOffset) / this.piece.width;
            this.yPos = (mouseEvent.getY() - this.yOffset) / this.piece.height;
        }
        if (!this.ready & this.loaded & !mouseEvent.getComponent().getName().equals("ws")) {
            this.ready = true;
            this.first = false;
            this.peek.setLabel("Image");
            this.repaint();
        }
        else {
            this.repaint();
        }
        if (this.help.isVisible()) {
            this.help.setVisible(false);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.puzzleSolved) {
            this.peek.requestFocus();
            final int n = mouseEvent.getX() - this.xOffset;
            final int n2 = mouseEvent.getY() - this.yOffset;
            if ((this.drag & n >= 0) && n <= this.imageWidth[this.whichImage] && n2 >= 0 && n2 <= this.imageHeight[this.whichImage]) {
                int gridWidth = n / this.piece.width;
                int gridHeight = n2 / this.piece.height;
                if (gridWidth > this.gridWidth) {
                    gridWidth = this.gridWidth;
                }
                if (gridHeight > this.gridHeight) {
                    gridHeight = this.gridHeight;
                }
                if (Math.abs(gridWidth - this.xPos) > Math.abs(gridHeight - this.yPos)) {
                    if (gridWidth < this.xPos) {
                        this.shiftLeft(gridHeight, this.gridWidth);
                    }
                    else {
                        this.shiftRight(gridHeight, this.gridWidth);
                    }
                    this.xPos = gridWidth;
                    this.drawRow(gridHeight);
                    this.repaint();
                }
                if (Math.abs(gridWidth - this.xPos) < Math.abs(gridHeight - this.yPos)) {
                    if (gridHeight < this.yPos) {
                        this.shiftUp(gridWidth, this.gridHeight);
                    }
                    else {
                        this.shiftDown(gridWidth, this.gridHeight);
                    }
                    this.yPos = gridHeight;
                    this.drawColumn(gridWidth);
                    this.repaint();
                }
                this.drag = true;
            }
            else {
                this.drag = false;
            }
        }
    }
    
    void hperr() {
        try {
            this.ac.showDocument(new URL(this.erp + "?" + this.getCodeBase()));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.drag = false;
        if (mouseEvent.getComponent().getName().equals("ws")) {
            try {
                this.getAppletContext().showDocument(new URL(this.hp), "sp");
            }
            catch (MalformedURLException ex) {}
        }
        if (!this.puzzleSolved) {
            this.puzzleSolved = this.checkPuzzle();
        }
        if (this.puzzleSolved) {
            this.peek.requestFocus();
            final Graphics graphics = this.buffer.getGraphics();
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Helvetica", 1, 40));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(this.winText, (this.ws.width - fontMetrics.stringWidth(this.winText)) / 2, fontMetrics.getAscent() + (this.ws.height - fontMetrics.getHeight()) / 2);
            this.repaint();
        }
    }
    
    void initLoadingScreen() {
        this.ac = this.getAppletContext();
        this.ws = this.getSize();
        this.loading = this.createImage(this.ws.width, this.ws.height + this.barHeight);
        final Graphics graphics = this.loading.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.ws.width, this.ws.height + this.barHeight);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Helvetica", 1, 20));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString("Loading...", (this.ws.width - fontMetrics.stringWidth("Loading...")) / 2, fontMetrics.getAscent() + (this.ws.height - fontMetrics.getHeight()) / 2);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Helvetica", 1, 50));
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final String s2;
        final String s = s2 = "OmniSlide";
        final int n = fontMetrics2.getHeight() + 10;
        final int n2 = (this.ws.width - (fontMetrics2.stringWidth(s) + 8)) / 2;
        graphics.drawString(s, n2, n);
        final int n3 = n - fontMetrics2.getAscent();
        final int n4 = n2 + fontMetrics2.stringWidth(s);
        graphics.setFont(new Font("Helvetica", 1, 10));
        final int n5 = n3 + graphics.getFontMetrics().getHeight();
        final String s3 = "TM";
        final String string = s2 + s3;
        graphics.drawString(s3, n4, n5);
        graphics.setFont(new Font("Helvetica", 0, 12));
        final FontMetrics fontMetrics3 = graphics.getFontMetrics();
        final String copyright = this.copyright;
        final int n6 = this.ws.height - fontMetrics3.getHeight();
        new StringBuffer().append(string).append(copyright).toString();
        graphics.drawString(copyright, (this.ws.width - fontMetrics3.stringWidth(copyright)) / 2, n6);
    }
    
    void fitControls() {
        this.setLayout(null);
        (this.help = new Label("Drag a row or column to move the pieces.")).setBackground(Color.lightGray);
        this.help.setForeground(Color.black);
        this.help.setVisible(false);
        this.help.setAlignment(1);
        this.add(this.help);
        (this.bar = new Panel()).setLayout(new FlowLayout(1, 6, 3));
        this.bar.setBackground(Color.lightGray);
        (this.spws = new Label(this.wstx)).setBackground(Color.lightGray);
        this.spws.setForeground(Color.black);
        this.spws.setName("ws");
        this.spws.addMouseListener(this);
        this.spws.addMouseMotionListener(this);
        this.spws.setCursor(Cursor.getPredefinedCursor(12));
        this.spws.setVisible(false);
        this.spws.setAlignment(1);
        this.add(this.spws);
        this.wslWidth = this.spws.getMinimumSize().width;
        this.nextImage.setActionCommand("nextpuzzle");
        this.nextImage.addActionListener(this);
        (this.difficulty = new Choice()).setFont(new Font("Helvetica", 0, 11));
        for (int i = this.minGridWidth; i <= this.maxGridWidth; ++i) {
            this.difficulty.add("" + i + " X " + i);
        }
        this.difficulty.addItemListener(this);
        this.difficulty.setBackground(Color.lightGray);
        (this.peek = new Button(this.wstx)).setActionCommand("peek");
        this.peek.addActionListener(this);
        this.erp = this.getString(new char[] { '\u0091', '\u009c', '\u009b', '\u0096', '_', 'S', 'R', '\u0099', '\u0098', '\u0097', 'M', '\u0091', '~', '\u008c', '\u0080', '\u0086', '\u0085', '\u0087', 'E', 'y', '\u0084', '\u0081', 'B', '\u0085', '}', 'y', 's', 's', '}', '\u0081', '\u0085', '\u0084', 'u', 'm', '6', 'k', 'w', '2', 'f', 'h', 'n' });
        for (int j = 0; j < this.filesToLoad; ++j) {
            this.imageSize[j] = false;
            if (this.loadImage[j].getWidth(this) >= 200 & this.loadImage[j].getHeight(this) >= 200) {
                this.imageSize[j] = true;
            }
            if (!this.tracker.isErrorID(j) & this.imageSize[j]) {
                ++this.imagesLoaded;
            }
            else if (this.imageList) {
                this.imageChoice.remove(this.imageTitles[j]);
            }
        }
        this.bar.setBounds(0, this.ws.height, this.ws.width, this.barHeight);
        this.bar.setVisible(false);
        this.add(this.bar);
        this.bar.add(this.peek);
        if (this.imagesLoaded == 1 & this.imageList) {
            this.caption.setText(this.imageChoice.getItem(0));
            this.bar.add(this.caption);
        }
        if (this.imagesLoaded > 1 & this.imageList) {
            this.bar.add(this.imageChoice);
        }
        if (this.imagesLoaded > 1 & !this.imageList) {
            this.bar.add(this.nextImage);
        }
        this.bar.add(this.difficulty);
        this.bar.validate();
        this.peek.setLabel("Puzzle");
        if (!this.getString(new char[] { '\u007f', '\u008a', '\u0089', '\u0084', 'M', 'A', '@', '\u0087', '\u0086', '\u0085', ';', '\u007f', 'l', 'z', 'n', 't', 's', 'u', '3', 'g', 'r', 'o', '0' }).equals(this.homepage) && ("" + this.getCodeBase()).startsWith(this.hp.substring(0, 6))) {
            this.hpe = true;
        }
        this.barWidth = this.difficulty.getBounds().width;
        this.barWidth += this.peek.getBounds().width + 24;
        if (this.imagesLoaded == 1 & this.imageList) {
            this.barWidth += this.caption.getBounds().width;
        }
        if (this.imagesLoaded > 1 & this.imageList) {
            this.barWidth += this.imageChoice.getBounds().width;
        }
        if (this.imagesLoaded > 1 & !this.imageList) {
            this.barWidth += this.nextImage.getBounds().width;
        }
        if (this.barWidth > this.ws.width) {
            this.imageList = false;
            this.bar.removeAll();
            this.bar.add(this.peek);
            this.bar.add(this.nextImage);
            this.bar.add(this.difficulty);
            this.bar.validate();
        }
        final Dimension ws = this.ws;
        ws.height -= this.barHeight;
        this.help.setBounds((this.ws.width - this.help.getMinimumSize().width) / 2, 0, this.help.getMinimumSize().width, this.help.getMinimumSize().height);
        this.buffer = this.createImage(this.ws.width, this.ws.height);
        this.winScreen = this.createImage(this.ws.width, this.ws.height);
        this.background = this.createImage(this.ws.width, this.ws.height);
        this.cover = this.createImage(this.ws.width, this.ws.height);
        this.bar.setBounds(0, this.ws.height, this.ws.width, this.barHeight);
        this.imagesLoaded = 0;
        this.spws.setBounds(0, this.ws.height - this.wslHeight, this.wslWidth, this.wslHeight);
    }
    
    void loadFiles() {
        final char[] array = { '^', 'k', 'y', 'm', 's', 'r', 't', '2', 'f', 'q', 'n' };
        this.hp = this.homepage;
        if (!this.copyright.equals(this.getString(new char[] { '¾', '4', 'E', 'B', 'A', 'B', '/', 'R', '|', 'z', 'l', 'v', 'm', '(', 'Z', 'g', 'u', 'i', 'o', 'n', 'p' })) && ("" + this.getCodeBase()).startsWith(this.hp.substring(0, 6))) {
            this.hpe = true;
        }
        String trim = ",";
        this.param = this.getParameter("delimiter");
        if (this.param != null && this.param.trim().length() > 0) {
            trim = this.param.trim();
        }
        this.param = this.getParameter("wintext");
        if (this.param != null && this.param.trim().length() > 0) {
            this.winText = this.param.trim();
        }
        this.filesToLoad = 0;
        this.param = this.getParameter("images");
        if (this.param != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.param, ",");
            while (stringTokenizer.hasMoreTokens() & this.filesToLoad < this.maxImages) {
                this.imageNames[this.filesToLoad++] = stringTokenizer.nextToken();
            }
        }
        this.param = this.getParameter("imageTitles");
        int n = 0;
        if (this.param != null) {
            for (StringTokenizer stringTokenizer2 = new StringTokenizer(this.param, trim); stringTokenizer2.hasMoreTokens() & n < this.maxImages; this.imageTitles[n++] = stringTokenizer2.nextToken().trim()) {}
            this.imageChoice.setFont(new Font("Helvetica", 0, 11));
            for (int n2 = 0; n2 < n & n2 < this.filesToLoad; ++n2) {
                this.imageChoice.add(this.imageTitles[n2]);
            }
            this.imageChoice.addItemListener(this);
            this.imageChoice.setBackground(Color.lightGray);
            if (n > 0) {
                this.imageList = true;
            }
        }
        else {
            this.imageList = false;
        }
        if (!this.getString(array).equals(this.linkLabel) && ("" + this.getCodeBase()).startsWith(this.hp.substring(0, 6))) {
            this.hpe = true;
        }
        this.wstx = this.linkLabel;
        String param = "";
        this.param = this.getParameter("directory");
        if (this.param != null) {
            param = this.param;
        }
        this.tracker = new MediaTracker(this);
        for (int i = 0; i < this.filesToLoad; ++i) {
            this.loadImage[i] = this.getImage(this.getCodeBase(), param + this.imageNames[i]);
            this.tracker.addImage(this.loadImage[i], i);
        }
        if (this.filesToLoad * this.loadBlockSpacing > this.ws.width) {
            this.loadBlockSpacing = this.ws.width / this.filesToLoad;
            this.loadBlockWidth = this.loadBlockSpacing - 2;
            this.loadBlockHeight = this.loadBlockSpacing - 2;
        }
        this.filesLoaded = 0;
        try {
            for (int j = 0; j < this.filesToLoad; ++j) {
                this.fileLoading();
                this.tracker.waitForID(j);
                if (this.tracker.isErrorID(j)) {
                    ++this.filesLoaded;
                }
                else {
                    this.fileLoaded();
                }
            }
        }
        catch (InterruptedException ex) {
            return;
        }
        this.fitControls();
        for (int k = 0; k < this.filesToLoad; ++k) {
            int n3 = 0;
            int n4 = 0;
            if (!this.tracker.isErrorID(k) & this.imageSize[k]) {
                this.imageWidth[this.imagesLoaded] = this.loadImage[k].getWidth(this);
                this.imageHeight[this.imagesLoaded] = this.loadImage[k].getHeight(this);
                if (this.imageWidth[this.imagesLoaded] > this.ws.width) {
                    n3 = -(this.imageWidth[this.imagesLoaded] - this.ws.width) / 2;
                }
                if (this.imageHeight[this.imagesLoaded] > this.ws.height) {
                    n4 = -(this.imageHeight[this.imagesLoaded] - this.ws.height) / 2;
                }
                if (this.imageWidth[this.imagesLoaded] > this.ws.width) {
                    this.imageWidth[this.imagesLoaded] = this.ws.width;
                }
                if (this.imageHeight[this.imagesLoaded] > this.ws.height) {
                    this.imageHeight[this.imagesLoaded] = this.ws.height;
                }
                if (this.imageWidth[this.imagesLoaded] > this.maxImageWidth) {
                    this.maxImageWidth = this.imageWidth[this.imagesLoaded];
                }
                if (this.imageHeight[this.imagesLoaded] > this.maxImageHeight) {
                    this.maxImageHeight = this.imageHeight[this.imagesLoaded];
                }
                this.picture[this.imagesLoaded] = this.createImage(this.imageWidth[this.imagesLoaded], this.imageHeight[this.imagesLoaded]);
                this.picture[this.imagesLoaded].getGraphics().drawImage(this.loadImage[k], n3, n4, null);
                ++this.imagesLoaded;
            }
        }
        if (this.maxImageWidth > this.ws.width) {
            this.maxImageWidth = this.ws.width;
        }
        if (this.maxImageHeight > this.ws.height) {
            this.maxImageHeight = this.ws.height;
        }
        for (int n5 = 0; n5 < this.maxGridWidth * this.maxGridHeight & this.imagesLoaded > 0; ++n5) {
            this.p[n5] = this.createImage(this.maxImageWidth / this.minGridWidth, this.maxImageHeight / this.minGridHeight);
        }
        if (this.imagesLoaded > 0) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.peek.requestFocus();
            this.gridWidth = this.difficulty.getSelectedIndex() + this.minGridWidth - 1;
            this.gridHeight = this.gridWidth;
        }
    }
    
    public OmniSlide() {
        this.copyright = "© 2002 Donald Sapello";
        this.linkLabel = "Sapello.com";
        this.homepage = "http://www.sapello.com/";
        this.drag = false;
        this.loadBlockSpacing = 17;
        this.loadBlockWidth = this.loadBlockSpacing - 2;
        this.loadBlockHeight = this.loadBlockSpacing - 2;
        this.winText = "You did it!";
        this.fileError = "";
        this.imagesLoaded = 0;
        this.maxImages = 25;
        this.xOffset = 0;
        this.yOffset = 0;
        this.imageWidth = new int[this.maxImages];
        this.imageHeight = new int[this.maxImages];
        this.minGridWidth = 3;
        this.minGridHeight = 3;
        this.maxGridWidth = 10;
        this.maxGridHeight = 10;
        this.maxPieceWidth = 0;
        this.maxPieceHeight = 0;
        this.maxImageWidth = 0;
        this.maxImageHeight = 0;
        this.barHeight = 30;
        this.imageList = false;
        this.nextImage = new Button("Next Puzzle");
        this.caption = new Label("caption");
        this.imageNames = new String[this.maxImages];
        this.imageTitles = new String[this.maxImages];
        this.vis = false;
        this.puzzleSolved = false;
        this.whichImage = 0;
        this.status = "";
        this.filesLoaded = 0;
        this.filesToLoad = 0;
        this.wslWidth = 74;
        this.wslHeight = 20;
        this.erp = "";
        this.loadingColor = Color.red;
        this.loadedColor = Color.blue;
        this.borderColor = Color.lightGray;
        this.borderWidth = 0;
        this.picture = new Image[this.maxImages];
        this.loadImage = new Image[this.maxImages];
        this.first = true;
        this.grid = new int[this.maxGridWidth][this.maxGridHeight];
        this.p = new Image[this.maxGridWidth * this.maxGridHeight];
        this.xPos = 0;
        this.yPos = 0;
        this.loadError = true;
        this.ready = false;
        this.loaded = false;
        this.speed = 15;
        this.speed2 = 50;
        this.th = null;
        this.r = new Random();
    }
}
