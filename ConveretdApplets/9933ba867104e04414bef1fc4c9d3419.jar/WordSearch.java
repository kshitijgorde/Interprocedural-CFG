import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class WordSearch extends JFrame
{
    private static WordSearch mainWindow;
    private MainPanel mainPanel;
    private int frame_width;
    private int frame_height;
    private boolean noSound;
    private String puzzle_file;
    
    public WordSearch(final String[] array) {
        super("Word Search Player");
        this.puzzle_file = array[0];
        this.frame_width = 640;
        this.frame_height = 540;
        this.noSound = false;
        this.parseParameters(array);
        this.initComponents();
        this.setDefaultCloseOperation(3);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setBounds((screenSize.width - this.frame_width) / 2, (screenSize.height - this.frame_height) / 2, this.frame_width, this.frame_height);
    }
    
    public static void main(final String[] array) {
        if (array == null || array.length == 0) {
            System.out.println("\nUsage:");
            System.out.println("\tWordSearch <puzzlefile> [options]");
            System.out.println("\nOptions:");
            System.out.println("\t--nosound    - Turns off the sound effects");
            System.out.println("\t--width=xxx  - Sets the width of the window");
            System.out.println("\t--height=xxx - Sets the height of the window");
        }
        else {
            (WordSearch.mainWindow = new WordSearch(array)).setVisible(true);
        }
    }
    
    private void parseParameters(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].indexOf("--width=") == 0) {
                this.frame_width = Integer.parseInt(array[i].substring(array[i].indexOf("=") + 1));
            }
            else if (array[i].indexOf("--height=") == 0) {
                this.frame_height = Integer.parseInt(array[i].substring(array[i].indexOf("=") + 1));
            }
            else if (array[i].indexOf("--nosound") == 0) {
                this.noSound = true;
            }
        }
    }
    
    private void initComponents() {
        this.mainPanel = new MainPanel(this.puzzle_file, false, null, Color.WHITE, Color.BLACK, this.noSound);
        this.mainPanel.upImg = this.getToolkit().getImage("upArrow.jpg");
        this.mainPanel.downImg = this.getToolkit().getImage("downArrow.jpg");
        this.mainPanel.leftImg = this.getToolkit().getImage("leftArrow.jpg");
        this.mainPanel.rightImg = this.getToolkit().getImage("rightArrow.jpg");
        this.mainPanel.updateArrows();
        this.setContentPane(this.mainPanel);
        this.pack();
    }
}
