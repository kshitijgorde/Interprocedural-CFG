import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.net.URLConnection;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MainPanel extends JPanel implements GameHandler
{
    private String puzzle_file;
    private boolean isApplet;
    private PuzzleRecord puzzle;
    private String author;
    private Color backColor;
    private Color textColor;
    private boolean noSound;
    public Image upImg;
    public Image downImg;
    public Image leftImg;
    public Image rightImg;
    private JLabel errorLabel;
    private LabelPanel puzzleTitle;
    private CluePanel cluePanel;
    private GamePanel gamePanel;
    private String docBase;
    
    public MainPanel(final String puzzle_file, final boolean isApplet, final String author, final Color backColor, final Color textColor, final boolean noSound) {
        this.puzzle_file = puzzle_file;
        this.isApplet = isApplet;
        this.author = author;
        this.noSound = noSound;
        this.backColor = backColor;
        this.textColor = textColor;
        this.docBase = "";
        this.puzzle = new PuzzleRecord();
        if (puzzle_file != null) {
            if (isApplet) {
                try {
                    final URLConnection openConnection = new URL(puzzle_file).openConnection();
                    openConnection.setDoInput(true);
                    openConnection.setUseCaches(false);
                    final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                    this.puzzle.loadFromStream(dataInputStream);
                    dataInputStream.close();
                }
                catch (MalformedURLException ex) {}
                catch (IOException ex2) {}
            }
            else {
                this.puzzle.loadFromFile(new File(puzzle_file));
            }
        }
        this.setLayout(new GridBagLayout());
        this.initComponents();
        if (this.puzzle.isLoaded()) {
            this.gamePanel.registerEventHandler(this);
            this.gamePanel.setPuzzle(this.puzzle.puzzle, this.puzzle.begSquare, this.puzzle.endSquare, this.puzzle.width, this.puzzle.height);
            this.cluePanel.setWords(this.puzzle.words, this.puzzle.clues, this.puzzle.useClues);
        }
    }
    
    private void initComponents() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        if (this.puzzle.isLoaded()) {
            this.puzzleTitle = new LabelPanel(this.puzzle.name, this.author, this.backColor, this.textColor);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            final boolean b = false;
            gridBagConstraints3.gridy = (b ? 1 : 0);
            gridBagConstraints2.gridx = (b ? 1 : 0);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.gridheight = 1;
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            final double n = 0.5;
            gridBagConstraints5.weighty = n;
            gridBagConstraints4.weightx = n;
            this.addComponent(this.puzzleTitle, gridBagConstraints);
            this.cluePanel = new CluePanel(this.backColor);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            final double n2 = 8.0;
            gridBagConstraints7.weighty = n2;
            gridBagConstraints6.weightx = n2;
            this.addComponent(this.cluePanel, gridBagConstraints);
            this.gamePanel = new GamePanel(this.backColor);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            final boolean b2 = true;
            gridBagConstraints9.gridy = (b2 ? 1 : 0);
            gridBagConstraints8.gridx = (b2 ? 1 : 0);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.weightx = 24.0;
            gridBagConstraints.weighty = 16.0;
            this.addComponent(this.gamePanel, gridBagConstraints);
        }
        else {
            if (this.puzzle_file == null) {
                this.errorLabel = new JLabel("There was no puzzle file specified", 0);
            }
            else if (this.puzzle.hasReadError()) {
                this.errorLabel = new JLabel("The puzzle, \"" + this.puzzle_file + "\", could not be read", 0);
            }
            else {
                this.errorLabel = new JLabel("The puzzle, \"" + this.puzzle_file + "\", does not exist", 0);
            }
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            this.addComponent(this.errorLabel, gridBagConstraints);
        }
    }
    
    private void addComponent(final Component component, final GridBagConstraints gridBagConstraints) {
        ((GridBagLayout)this.getLayout()).setConstraints(component, gridBagConstraints);
        this.add(component);
    }
    
    public void gameEvent(final GameEvent gameEvent) {
        if (gameEvent.getID() == 1) {
            if (this.cluePanel.setWordFound(gameEvent.getWordIndex())) {
                this.gamePanel.setGameOver(true);
                this.puzzleTitle.setGameOver(true);
                if (!this.noSound) {
                    SoundFile.play(this.docBase + "win.wav", this.isApplet);
                }
                final MessageBox messageBox = new MessageBox("Game Over");
            }
            else if (!this.noSound) {
                SoundFile.play(this.docBase + "Complete.wav", this.isApplet);
            }
        }
        else if (gameEvent.getID() == 2) {
            final String s = this.puzzle.words[gameEvent.getWordIndex()];
            int result = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != ' ') {
                    ++result;
                }
            }
            gameEvent.setResult(result);
        }
    }
    
    public void setClueColors(final Color color, final Color color2, final Color color3) {
        if (this.puzzle.isLoaded()) {
            this.cluePanel.setColors(color, color2, color3);
        }
    }
    
    public void setGameColors(final Color color, final Color color2, final Color color3) {
        if (this.puzzle.isLoaded()) {
            this.gamePanel.setColors(color, color2, color3);
        }
    }
    
    public void setTimer(final boolean timer) {
        if (this.puzzle.isLoaded()) {
            this.puzzleTitle.setTimer(timer);
        }
    }
    
    public void setDocBase(final String docBase) {
        this.docBase = docBase;
    }
    
    private BufferedImage adjustImage(final Image image) {
        final BufferedImage bufferedImage = new BufferedImage(image.getWidth(this), image.getHeight(this), 1);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        return bufferedImage;
    }
    
    public void updateArrows() {
        if (this.puzzle.isLoaded()) {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.upImg, 0);
                mediaTracker.waitForID(0);
                mediaTracker.addImage(this.downImg, 0);
                mediaTracker.waitForID(0);
                mediaTracker.addImage(this.leftImg, 0);
                mediaTracker.waitForID(0);
                mediaTracker.addImage(this.rightImg, 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                System.out.println(ex.getStackTrace());
                System.exit(1);
            }
            this.cluePanel.upImg = this.adjustImage(this.upImg);
            this.cluePanel.downImg = this.adjustImage(this.downImg);
            this.gamePanel.upImg = this.cluePanel.upImg;
            this.gamePanel.downImg = this.cluePanel.downImg;
            this.gamePanel.leftImg = this.adjustImage(this.leftImg);
            this.gamePanel.rightImg = this.adjustImage(this.rightImg);
        }
    }
}
