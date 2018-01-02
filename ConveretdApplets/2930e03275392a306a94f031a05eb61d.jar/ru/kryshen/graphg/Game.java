// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import java.io.IOException;
import ru.kryshen.util.ResourceLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;

public class Game
{
    public static final String TITLE = "Graph Game";
    public static final String VERSION = "1.0.92";
    public static final String AUTHOR = "Mikhail A. Kryshen";
    private static final int WIDTH = 494;
    private static final int HEIGHT = 344;
    private int fieldX;
    private int fieldY;
    private int fieldWidth;
    private int fieldHeight;
    private int statusX;
    private int statusY;
    private int statusWidth;
    private int statusHeight;
    private Font statusFont;
    private Color statusColor1;
    private Color statusColor2;
    private Color statusColor3;
    private String status1;
    private String status2;
    private String status3;
    Container container;
    Applet applet;
    private Image about;
    private Image background;
    private Image urlBorder;
    Image fieldBackground;
    private AboutPane aboutPane;
    private GraphCanvas field;
    private SettingsPane settingsPane;
    private NICButton newGameButton;
    private NICButton aboutButton;
    
    public Game() {
        this.fieldX = 27;
        this.fieldY = 70;
        this.fieldWidth = 439;
        this.fieldHeight = 247;
        this.statusX = 212;
        this.statusY = 20;
        this.statusWidth = 254;
        this.statusHeight = 26;
        this.statusFont = new Font("Helvetica", 1, 12);
        this.statusColor1 = Color.red;
        this.statusColor2 = Color.blue;
        this.statusColor3 = Color.blue;
        this.status1 = "Graph Game 1.0.92";
        this.status2 = "Loading...";
        this.status3 = null;
        this.applet = null;
        this.about = null;
        this.background = null;
        this.urlBorder = null;
        this.fieldBackground = null;
    }
    
    public static void main(final String[] array) {
        final Game game = new Game();
        final Frame frame = new Frame("Graph Game 1.0.92") {
            private RenderingManager rm = new RenderingManager();
            
            public void update(final Graphics graphics) {
                this.paint(graphics);
            }
            
            public void paint(final Graphics graphics) {
                this.rm.updateBuffer(this, graphics, this.getSize());
                game.render(this.rm.offscreen);
                super.paint(this.rm.offscreen);
                graphics.drawImage(this.rm.buffer, 0, 0, null);
            }
            
            public Dimension getPreferredSize() {
                final Insets insets = this.getInsets();
                return new Dimension(494 + insets.left + insets.right, 344 + insets.top + insets.bottom);
            }
        };
        frame.addWindowListener(new WindowAdapter() {
            private final /* synthetic */ Frame val$frame = frame;
            
            public void windowClosing(final WindowEvent windowEvent) {
                game.destroy();
                this.val$frame.dispose();
            }
        });
        frame.pack();
        frame.setResizable(false);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        frame.setLocation((defaultToolkit.getScreenSize().width - frame.getSize().width) / 2, (defaultToolkit.getScreenSize().height - frame.getSize().height) / 2);
        frame.show();
        game.init(frame);
        try {
            game.load(new FileInputStream("skins/default.bin"));
        }
        catch (Exception ex) {
            game.displayError(ex);
        }
    }
    
    void init(final Container container) {
        this.container = container;
        if (container instanceof Applet) {
            this.applet = (Applet)container;
        }
        container.setLayout(null);
        final Insets insets = this.container.getInsets();
        (this.aboutPane = new AboutPane()).setBounds(this.fieldX + insets.left, this.fieldY + insets.top, this.fieldWidth, this.fieldHeight);
        this.aboutPane.setVisible(true);
        container.add(this.aboutPane);
        (this.field = new GraphCanvas(this)).setBounds(this.fieldX + insets.left, this.fieldY + insets.top, this.fieldWidth, this.fieldHeight);
        this.field.setVisible(false);
        container.add(this.field);
        (this.settingsPane = new SettingsPane()).setBounds(this.fieldX + insets.left + 15, this.fieldY + insets.top + 15, this.fieldWidth - 30, this.fieldHeight - 30);
        this.settingsPane.setVisible(false);
        container.add(this.settingsPane);
        this.settingsPane.validate();
        (this.newGameButton = new NICButton("New Game", 88, 22)).setLocation(23 + insets.left, 23 + insets.top);
        container.add(this.newGameButton);
        (this.aboutButton = new NICButton("About", 88, 22)).setLocation(114 + insets.left, 23 + insets.top);
        container.add(this.aboutButton);
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ("New Game".equals(actionEvent.getActionCommand())) {
                    Game.this.showSettings();
                }
                else if ("About".equals(actionEvent.getActionCommand())) {
                    Game.this.showAboutPane();
                }
                else if ("Continue".equals(actionEvent.getActionCommand())) {
                    Game.this.showField();
                }
            }
        };
        this.newGameButton.addActionListener(actionListener);
        this.aboutButton.addActionListener(actionListener);
    }
    
    private void showAboutPane() {
        if (this.aboutPane.isVisible()) {
            return;
        }
        if (this.field.isVisible()) {
            this.aboutButton.setText("Continue");
        }
        this.aboutPane.setVisible(true);
        this.settingsPane.setVisible(false);
        this.field.setVisible(false);
    }
    
    private void showField() {
        if (this.field.isVisible()) {
            return;
        }
        this.field.setVisible(true);
        this.settingsPane.setVisible(false);
        this.aboutPane.setVisible(false);
        this.aboutButton.setText("About");
    }
    
    private void showSettings() {
        if (this.settingsPane.isVisible()) {
            return;
        }
        if (this.field.isVisible()) {
            this.aboutButton.setText("Continue");
        }
        else {
            this.aboutButton.setText("About");
        }
        this.settingsPane.setVisible(true);
        this.field.setVisible(false);
        this.aboutPane.setVisible(false);
    }
    
    void destroy() {
        this.field.stop();
        this.container.removeAll();
    }
    
    synchronized void render(final Graphics graphics) {
        final Insets insets = this.container.getInsets();
        if (this.background != null) {
            graphics.drawImage(this.background, insets.left, insets.top, this.container);
        }
        else {
            this.container.getSize();
            graphics.clearRect(insets.left, insets.top, 494, 344);
        }
        final int n = this.statusX + insets.left;
        final int n2 = this.statusY + insets.top;
        graphics.setFont(this.statusFont);
        graphics.setColor(this.statusColor1);
        RenderingManager.drawStringInRect(this.status1, n, n2, this.statusWidth, this.statusHeight / 2, 1, graphics);
        graphics.setColor(this.statusColor2);
        RenderingManager.drawStringInRect(this.status2, n, n2 + this.statusHeight / 2, (this.status3 == null) ? this.statusWidth : (this.statusWidth / 2), this.statusHeight / 2, 1, graphics);
        if (this.status3 != null) {
            graphics.setColor(this.statusColor3);
            RenderingManager.drawStringInRect(this.status3, n + this.statusWidth / 2, n2 + this.statusHeight / 2, this.statusWidth / 2, this.statusHeight / 2, 1, graphics);
        }
    }
    
    synchronized void setStatus1(final String status1) {
        this.status1 = status1;
    }
    
    synchronized void setStatus2(final String status2) {
        this.status2 = status2;
    }
    
    synchronized void setStatus3(final String status3) {
        this.status3 = status3;
    }
    
    void repaintStatus() {
        final Insets insets = this.container.getInsets();
        this.container.repaint(this.statusX + insets.left, this.statusY + insets.top, this.statusWidth, this.statusHeight);
    }
    
    void displayError(final Exception ex) {
        System.err.println("Graph Game: " + ex);
        this.setStatus2(ex.toString());
        this.setStatus3(null);
    }
    
    void load(final InputStream inputStream) throws IOException, InterruptedException {
        final ResourceLoader resourceLoader = new ResourceLoader(this.container, "Graph Game Skin", inputStream);
        this.setStatus2("Loading...");
        this.setStatus3("0%");
        this.container.repaint();
        int loadNext;
        while ((loadNext = resourceLoader.loadNext()) >= 0) {
            switch (resourceLoader.getResourceType()) {
                case 8: {
                    if ("background".equals(resourceLoader.getResourceName())) {
                        this.background = resourceLoader.getImage();
                    }
                    if ("about".equals(resourceLoader.getResourceName())) {
                        this.about = resourceLoader.getImage();
                    }
                    if ("field".equals(resourceLoader.getResourceName())) {
                        this.fieldBackground = resourceLoader.getImage();
                    }
                    if ("url-border".equals(resourceLoader.getResourceName())) {
                        this.urlBorder = resourceLoader.getImage();
                        break;
                    }
                    break;
                }
            }
            this.setStatus2("Loading...");
            this.setStatus3((int)(loadNext * 100.0f / 255.0f) + "%");
            this.container.repaint();
        }
        Thread.currentThread();
        Thread.sleep(300L);
        this.setStatus2("Applet loaded");
        this.setStatus3(null);
        this.container.repaint();
    }
    
    private class SettingsPane extends Panel implements ActionListener
    {
        private CheckboxGroup cgPlayer1;
        private CheckboxGroup cgPlayer2;
        private Checkbox chkComp1;
        private Checkbox chkComp2;
        private Checkbox chkHuman1;
        private Checkbox chkHuman2;
        private Button btnOK;
        private Scrollbar sbDifficulty;
        private Font defaultFont;
        private Font largeFont;
        private TextField tfName1;
        private TextField tfName2;
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final GraphGenerator graphGenerator = new GraphGenerator();
            switch (this.sbDifficulty.getValue()) {
                case 1: {
                    graphGenerator.minEdgesForNode = 3;
                    graphGenerator.maxEdgesForNode = 3;
                    break;
                }
                case 2: {
                    graphGenerator.minEdgesForNode = 3;
                    graphGenerator.maxEdgesForNode = 4;
                    break;
                }
                case 3: {
                    graphGenerator.minEdgesForNode = 3;
                    graphGenerator.maxEdgesForNode = 5;
                    break;
                }
                case 4: {
                    graphGenerator.minEdgesForNode = 4;
                    graphGenerator.maxEdgesForNode = 6;
                    break;
                }
                case 5: {
                    graphGenerator.minEdgesForNode = 4;
                    graphGenerator.maxEdgesForNode = 7;
                    break;
                }
            }
            Game.this.field.graph = graphGenerator.generateGraph();
            Player player;
            if (this.chkHuman1.getState()) {
                player = new HumanPlayer(this.tfName1.getText(), 2, Game.this.field);
            }
            else {
                player = new ComputerPlayer(this.tfName1.getText(), 2, Game.this.field);
            }
            Player player2;
            if (this.chkHuman2.getState()) {
                player2 = new HumanPlayer(this.tfName2.getText(), 1, Game.this.field);
            }
            else {
                player2 = new ComputerPlayer(this.tfName2.getText(), 1, Game.this.field);
            }
            Game.this.showField();
            Game.this.field.newGame(player, player2);
        }
        
        SettingsPane() {
            this.cgPlayer1 = new CheckboxGroup();
            this.cgPlayer2 = new CheckboxGroup();
            this.chkComp1 = new Checkbox("Computer", this.cgPlayer1, true);
            this.chkComp2 = new Checkbox("Computer", this.cgPlayer2, false);
            this.chkHuman1 = new Checkbox("Human", this.cgPlayer1, false);
            this.chkHuman2 = new Checkbox("Human", this.cgPlayer2, true);
            this.btnOK = new Button("Start Game!");
            this.sbDifficulty = new Scrollbar(0, 3, 1, 1, 6);
            this.defaultFont = new Font("Dialog", 0, 12);
            this.largeFont = new Font("Dialog", 1, 14);
            this.tfName1 = new TextField("Short");
            this.tfName2 = new TextField("Cut");
            this.setFont(this.defaultFont);
            this.chkComp2.setEnabled(false);
            this.btnOK.addActionListener(this);
            final Insets insets = new Insets(5, 5, 5, 5);
            final Insets insets2 = new Insets(5, 5, 0, 5);
            final Insets insets3 = new Insets(0, 5, 5, 5);
            this.setLayout(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = insets2;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.gridwidth = 0;
            final Label label = new Label("Players");
            label.setFont(this.largeFont);
            this.add(label, gridBagConstraints);
            gridBagConstraints.insets = insets3;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 2;
            this.add(this.tfName1, gridBagConstraints);
            gridBagConstraints.gridwidth = 0;
            this.add(this.tfName2, gridBagConstraints);
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.insets = insets2;
            gridBagConstraints.fill = 0;
            this.add(this.chkHuman1, gridBagConstraints);
            gridBagConstraints.gridwidth = 0;
            this.add(this.chkHuman2, gridBagConstraints);
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.insets = insets3;
            this.add(this.chkComp1, gridBagConstraints);
            gridBagConstraints.gridwidth = 0;
            this.add(this.chkComp2, gridBagConstraints);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.insets = insets2;
            this.add(new Label("<- Short -- Difficulty -- Cut ->"), gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = insets3;
            this.add(this.sbDifficulty, gridBagConstraints);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.insets = insets;
            this.btnOK.setFont(this.largeFont);
            this.add(this.btnOK, gridBagConstraints);
        }
    }
    
    private class AboutPane extends Component implements MouseListener, MouseMotionListener
    {
        private boolean displayUrlBorder;
        
        AboutPane() {
            this.displayUrlBorder = false;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (this.displayUrlBorder && Game.this.applet != null) {
                try {
                    Game.this.applet.getAppletContext().showDocument(new URL("http://bm.org.ru/java/"));
                }
                catch (Exception ex) {
                    Game.this.displayError(ex);
                }
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (this.displayUrlBorder) {
                this.displayUrlBorder = false;
                this.repaint();
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            boolean displayUrlBorder = false;
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (x >= 3 & x <= 170 & y >= 218 & y <= 242) {
                displayUrlBorder = true;
            }
            if (displayUrlBorder != this.displayUrlBorder) {
                this.displayUrlBorder = displayUrlBorder;
                this.repaint();
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(Game.this.fieldWidth, Game.this.fieldHeight);
        }
        
        public void paint(final Graphics graphics) {
            if (Game.this.about != null) {
                graphics.drawImage(Game.this.about, 0, 0, this);
            }
            if (this.displayUrlBorder && Game.this.urlBorder != null) {
                graphics.drawImage(Game.this.urlBorder, 3, 218, this);
            }
        }
        
        public boolean isOpaque() {
            return false;
        }
    }
}
