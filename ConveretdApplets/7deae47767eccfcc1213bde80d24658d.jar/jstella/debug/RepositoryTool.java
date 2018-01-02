// 
// Decompiled by Procyon v0.5.30
// 

package jstella.debug;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFileChooser;
import jstella.runner.JStellaGame;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Map;
import javax.swing.JFrame;

public class RepositoryTool extends JFrame
{
    public static final String REGEX_PROPERTY = "\"(.+?)\" +\"(.+?)\"";
    public static final String REGEX_YEAR = "\\((\\d{4})\\)";
    public static final String REGEX_PARENTH = "\\((.+?)\\)";
    public static final String STELLA_KEY_MD5 = "Cartridge.MD5";
    public static final String STELLA_KEY_NAME = "Cartridge.Name";
    private static Map<String, String> myKeyConversion;
    private Map<String, GameItem> myGameItemMap;
    private Pattern myPropertyPattern;
    private Pattern myParenthPattern;
    private Pattern myYearPattern;
    private List<JStellaGame> myGameList;
    private List<String> myMD5List;
    private JFileChooser FCMain;
    private JFileChooser FCRepository;
    private JMenuBar MBMain;
    private JMenuItem MICleanGames;
    private JMenuItem MIImportROMs;
    private JMenuItem MILoadProperties;
    private JMenuItem MIMergeRepositories;
    private JMenu MenuFile;
    private JScrollPane SPCenter;
    
    public RepositoryTool() {
        this.myGameItemMap = new HashMap<String, GameItem>();
        this.myPropertyPattern = Pattern.compile("\"(.+?)\" +\"(.+?)\"");
        this.myParenthPattern = Pattern.compile("\\((.+?)\\)");
        this.myYearPattern = Pattern.compile("\\((\\d{4})\\)");
        this.myGameList = new ArrayList<JStellaGame>();
        this.myMD5List = new ArrayList<String>();
        this.initComponents();
    }
    
    private void initComponents() {
        this.FCMain = new JFileChooser();
        this.FCRepository = new JFileChooser();
        this.SPCenter = new JScrollPane();
        this.MBMain = new JMenuBar();
        this.MenuFile = new JMenu();
        this.MILoadProperties = new JMenuItem();
        this.MIImportROMs = new JMenuItem();
        this.MICleanGames = new JMenuItem();
        this.MIMergeRepositories = new JMenuItem();
        this.FCRepository.setFileSelectionMode(1);
        this.setDefaultCloseOperation(3);
        this.setTitle("JStella Repository Tool");
        this.getContentPane().add(this.SPCenter, "Center");
        this.MenuFile.setText("File");
        this.MILoadProperties.setText("Load properties");
        this.MILoadProperties.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                RepositoryTool.this.MILoadPropertiesActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MILoadProperties);
        this.MIImportROMs.setText("Import ROMs");
        this.MIImportROMs.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                RepositoryTool.this.MIImportROMsActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIImportROMs);
        this.MICleanGames.setText("Clean games");
        this.MICleanGames.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                RepositoryTool.this.MICleanGamesActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MICleanGames);
        this.MIMergeRepositories.setText("Merge repositories");
        this.MIMergeRepositories.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                RepositoryTool.this.MIMergeRepositoriesActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIMergeRepositories);
        this.MBMain.add(this.MenuFile);
        this.setJMenuBar(this.MBMain);
        this.pack();
    }
    
    private void MILoadPropertiesActionPerformed(final ActionEvent evt) {
    }
    
    private void MIImportROMsActionPerformed(final ActionEvent evt) {
    }
    
    private void MICleanGamesActionPerformed(final ActionEvent evt) {
    }
    
    private void MIMergeRepositoriesActionPerformed(final ActionEvent evt) {
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RepositoryTool().setVisible(true);
            }
        });
    }
    
    static {
        (RepositoryTool.myKeyConversion = new HashMap<String, String>()).put("Cartridge.Manufacturer", "jstella.game.maker");
        RepositoryTool.myKeyConversion.put("Cartridge.ModelNo", "jstella.game.cartridge.model");
        RepositoryTool.myKeyConversion.put("Cartridge.Note", "jstella.game.note");
        RepositoryTool.myKeyConversion.put("Cartridge.Rarity", "jstella.game.cartridge.rarity");
        RepositoryTool.myKeyConversion.put("Cartridge.Type", "jstella.game.cartridge.type");
        RepositoryTool.myKeyConversion.put("Controller.Left", "jstella.controller.left.type");
        RepositoryTool.myKeyConversion.put("Controller.Right", "jstella.controller.right.type");
        RepositoryTool.myKeyConversion.put("Display.Format", "jstella.display.format");
        RepositoryTool.myKeyConversion.put("Display.FrameRate", "jstella.display.framerate");
        RepositoryTool.myKeyConversion.put("Console.RightDifficulty", "jstella.console.switch.difficulty.right");
        RepositoryTool.myKeyConversion.put("Console.LeftDifficulty", "jstella.console.switch.difficulty.left");
        RepositoryTool.myKeyConversion.put("Display.Height", "jstella.display.height");
        RepositoryTool.myKeyConversion.put("Display.Width", "jstella.display.width");
        RepositoryTool.myKeyConversion.put("Display.XStart", "jstella.display.xstart");
        RepositoryTool.myKeyConversion.put("Display.YStart", "jstella.display.ystart");
    }
    
    public class GameItem
    {
        private Map<String, String> myPropertyMap;
        
        public GameItem() {
            this.myPropertyMap = new HashMap<String, String>();
        }
        
        public String getMD5() {
            return this.myPropertyMap.get("Cartridge.MD5");
        }
        
        public String getName() {
            return this.myPropertyMap.get("Cartridge.Name");
        }
    }
}
