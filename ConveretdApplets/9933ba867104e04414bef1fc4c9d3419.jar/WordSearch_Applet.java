import java.awt.Container;
import java.awt.Color;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WordSearch_Applet extends JApplet
{
    private MainPanel mainPanel;
    private String puzzle_file;
    private String author;
    private boolean useTimer;
    private boolean noSound;
    private Color backColor;
    private Color textColor;
    private Color clue_color1;
    private Color clue_color2;
    private Color clue_color3;
    private Color game_color1;
    private Color game_color2;
    private Color game_color3;
    
    public void init() {
        this.backColor = Color.WHITE;
        this.textColor = Color.BLACK;
        this.noSound = false;
        this.extractParameters();
        this.initComponents();
    }
    
    private void extractParameters() {
        this.puzzle_file = this.getParameter("file");
        if (this.puzzle_file != null) {
            this.puzzle_file = this.getCodeBase() + "/" + this.puzzle_file;
        }
        this.author = this.getParameter("author");
        final String parameter = this.getParameter("bkcolor");
        if (parameter != null) {
            this.backColor = this.strToColor(parameter, Color.WHITE);
        }
        final String parameter2 = this.getParameter("titlecolor");
        if (parameter2 != null) {
            this.textColor = this.strToColor(parameter2, Color.BLACK);
        }
        final String parameter3 = this.getParameter("nosound");
        if (parameter3 != null) {
            this.noSound = (parameter3.compareToIgnoreCase("true") == 0);
        }
        this.clue_color1 = this.strToColor(this.getParameter("cluecolor"), null);
        this.clue_color2 = this.strToColor(this.getParameter("slashcolor"), null);
        this.clue_color3 = this.strToColor(this.getParameter("foundcolor"), null);
        this.game_color1 = this.strToColor(this.getParameter("lettercolor"), null);
        this.game_color2 = this.strToColor(this.getParameter("lettercolor2"), null);
        this.game_color3 = this.strToColor(this.getParameter("circlecolor"), null);
        this.useTimer = this.strToBool(this.getParameter("usetimer"), false);
    }
    
    private Color strToColor(final String s, final Color color) {
        Color color2;
        if (s != null) {
            if (s.charAt(0) == '#') {
                color2 = new Color(Integer.parseInt(s.substring(1), 16));
            }
            else {
                color2 = new Color(Integer.parseInt(s, 16));
            }
        }
        else {
            color2 = color;
        }
        return color2;
    }
    
    private boolean strToBool(final String s, final boolean b) {
        if (s != null) {
            return s.compareToIgnoreCase("TRUE") == 0;
        }
        return b;
    }
    
    private void initComponents() {
        this.setContentPane(this.mainPanel = new MainPanel(this.puzzle_file, true, this.author, this.backColor, this.textColor, this.noSound));
        this.mainPanel.setClueColors(this.clue_color1, this.clue_color2, this.clue_color3);
        this.mainPanel.setGameColors(this.game_color1, this.game_color2, this.game_color3);
        this.mainPanel.setDocBase(this.getCodeBase().toString() + "/");
        this.mainPanel.setTimer(this.useTimer);
        this.mainPanel.upImg = this.getImage(this.getCodeBase(), "upArrow.jpg");
        this.mainPanel.downImg = this.getImage(this.getCodeBase(), "downArrow.jpg");
        this.mainPanel.leftImg = this.getImage(this.getCodeBase(), "leftArrow.jpg");
        this.mainPanel.rightImg = this.getImage(this.getCodeBase(), "rightArrow.jpg");
        this.mainPanel.updateArrows();
    }
}
