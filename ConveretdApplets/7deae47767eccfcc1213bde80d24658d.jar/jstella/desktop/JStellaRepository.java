// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import javax.swing.UIManager;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.InputStream;
import java.awt.Cursor;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Arrays;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import jstella.runner.JStellaRunnerUtil;
import java.awt.Rectangle;
import jstella.runner.JStellaGame;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.awt.Container;
import java.util.Iterator;
import java.io.File;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import java.util.Comparator;
import java.util.List;
import java.awt.Color;
import javax.swing.JFrame;

public class JStellaRepository extends JFrame implements JStellaGamePanel.IfcGamePanelClient
{
    private static final long serialVersionUID = 2271542183636011052L;
    public static final Color COLOR_REGULAR;
    public static final Color COLOR_SELECTED;
    public static final Color COLOR_ROMLESS;
    public static final int COLUMN_COUNT = 6;
    public static final int ROW_COUNT = 0;
    public static final double ICON_SCALE_LARGE = 1.0;
    public static final double ICON_SCALE_MEDIUM = 0.66;
    public static final double ICON_SCALE_SMALL = 0.33;
    public static final int ICON_MAX_WIDTH = 250;
    public static final int ICON_MAX_HEIGHT = 250;
    public static final int GAME_TITLE_MAX_LENGTH = 20;
    public static final String NEGATIVE_SORT_STRING = "~~~~~~";
    public static final long ROM_LENGTH_MAX = 40000L;
    public static final int SCROLL_BAR_UNIT_INCREMENT = 20;
    private List<JStellaGamePanel> myGamePanelList;
    private JStellaGamePanel mySelectedGamePanel;
    private Comparator myCurrentComparator;
    private boolean myIconlessPanelsVisible;
    private ButtonGroup ButtonGroupIconSize;
    private ButtonGroup ButtonGroupSort;
    private JCheckBoxMenuItem CBMIShowIconlessPanels;
    private JCheckBoxMenuItem CBMIShowTitles;
    private JLabel LabelProcess;
    private JLabel LabelProgress;
    private JMenuBar MBMain;
    private JMenuItem MIAddGames;
    private JMenuItem MIConfig;
    private JMenuItem MIExitJStella;
    private JMenuItem MIExportMetadata;
    private JMenuItem MIHelpContents;
    private JMenuItem MIImportStandardMetadata;
    private JMenuItem MIRunSetupWizard;
    private JMenu MenuAdvanced;
    private JMenu MenuFile;
    private JMenu MenuHelp;
    private JMenu MenuSort;
    private JMenu MenuView;
    private JMenu MenuViewSize;
    private JMenuItem PMIDeleteSelected;
    private JMenuItem PMIEditSelected;
    private JMenuItem PMIRunSelected;
    private JPanel PanelCenter;
    private JPanel PanelGames;
    private JPopupMenu PopupMain;
    private JRadioButtonMenuItem RBIconsLarge;
    private JRadioButtonMenuItem RBIconsMed;
    private JRadioButtonMenuItem RBIconsSmall;
    private JRadioButtonMenuItem RBSortController;
    private JRadioButtonMenuItem RBSortIcon;
    private JRadioButtonMenuItem RBSortMaker;
    private JRadioButtonMenuItem RBSortTitle;
    private JRadioButtonMenuItem RBSortYear;
    private JScrollPane SPCenter;
    private JSeparator SepViewA;
    private JDialog myProgressDialog;
    
    public JStellaRepository() {
        this.myGamePanelList = new ArrayList<JStellaGamePanel>();
        this.mySelectedGamePanel = null;
        this.myCurrentComparator = new IconComparator();
        this.myIconlessPanelsVisible = true;
        this.initComponents();
        this.SPCenter.getVerticalScrollBar().setUnitIncrement(20);
        ((GridLayout)this.PanelGames.getLayout()).setColumns(6);
        ((GridLayout)this.PanelGames.getLayout()).setRows(0);
        JStellaGamePanel.setIconScaleFactor(0.66);
        this.loadGames();
        this.updateLayout(true);
    }
    
    private void initComponents() {
        this.PopupMain = new JPopupMenu();
        this.PMIRunSelected = new JMenuItem();
        this.PMIEditSelected = new JMenuItem();
        this.PMIDeleteSelected = new JMenuItem();
        this.ButtonGroupIconSize = new ButtonGroup();
        this.ButtonGroupSort = new ButtonGroup();
        this.myProgressDialog = new JDialog();
        this.PanelCenter = new JPanel();
        this.LabelProcess = new JLabel();
        this.LabelProgress = new JLabel();
        this.SPCenter = new JScrollPane();
        this.PanelGames = new JPanel();
        this.MBMain = new JMenuBar();
        this.MenuFile = new JMenu();
        this.MIRunSetupWizard = new JMenuItem();
        this.MIAddGames = new JMenuItem();
        this.MIConfig = new JMenuItem();
        this.MIExitJStella = new JMenuItem();
        this.MenuView = new JMenu();
        this.MenuSort = new JMenu();
        this.RBSortTitle = new JRadioButtonMenuItem();
        this.RBSortMaker = new JRadioButtonMenuItem();
        this.RBSortYear = new JRadioButtonMenuItem();
        this.RBSortIcon = new JRadioButtonMenuItem();
        this.RBSortController = new JRadioButtonMenuItem();
        this.SepViewA = new JSeparator();
        this.MenuViewSize = new JMenu();
        this.RBIconsSmall = new JRadioButtonMenuItem();
        this.RBIconsMed = new JRadioButtonMenuItem();
        this.RBIconsLarge = new JRadioButtonMenuItem();
        this.CBMIShowTitles = new JCheckBoxMenuItem();
        this.CBMIShowIconlessPanels = new JCheckBoxMenuItem();
        this.MenuAdvanced = new JMenu();
        this.MIImportStandardMetadata = new JMenuItem();
        this.MIExportMetadata = new JMenuItem();
        this.MenuHelp = new JMenu();
        this.MIHelpContents = new JMenuItem();
        this.PopupMain.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(final PopupMenuEvent evt) {
            }
            
            public void popupMenuWillBecomeInvisible(final PopupMenuEvent evt) {
            }
            
            public void popupMenuWillBecomeVisible(final PopupMenuEvent evt) {
                JStellaRepository.this.PopupMainPopupMenuWillBecomeVisible(evt);
            }
        });
        this.PMIRunSelected.setText("Run");
        this.PMIRunSelected.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.PMIRunSelectedActionPerformed(evt);
            }
        });
        this.PopupMain.add(this.PMIRunSelected);
        this.PMIEditSelected.setText("Edit");
        this.PMIEditSelected.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.PMIEditSelectedActionPerformed(evt);
            }
        });
        this.PopupMain.add(this.PMIEditSelected);
        this.PMIDeleteSelected.setText("Delete");
        this.PMIDeleteSelected.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.PMIDeleteSelectedActionPerformed(evt);
            }
        });
        this.PopupMain.add(this.PMIDeleteSelected);
        this.myProgressDialog.setTitle("Importing");
        this.myProgressDialog.setMinimumSize(new Dimension(300, 150));
        this.PanelCenter.setDoubleBuffered(false);
        this.PanelCenter.setLayout(new GridBagLayout());
        this.LabelProcess.setText("Importing metadata...");
        this.PanelCenter.add(this.LabelProcess, new GridBagConstraints());
        this.LabelProgress.setText("...");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        this.PanelCenter.add(this.LabelProgress, gridBagConstraints);
        this.myProgressDialog.getContentPane().add(this.PanelCenter, "Center");
        this.setDefaultCloseOperation(0);
        this.setTitle("JStella Game Repository");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent evt) {
                JStellaRepository.this.formWindowClosing(evt);
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent evt) {
                JStellaRepository.this.formComponentResized(evt);
            }
        });
        this.PanelGames.setComponentPopupMenu(this.PopupMain);
        this.PanelGames.setLayout(new GridLayout(1, 0));
        this.SPCenter.setViewportView(this.PanelGames);
        this.getContentPane().add(this.SPCenter, "Center");
        this.MenuFile.setText("File");
        this.MIRunSetupWizard.setText("Run setup wizard");
        this.MIRunSetupWizard.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIRunSetupWizardActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIRunSetupWizard);
        this.MIAddGames.setText("Add ROMs to repository");
        this.MIAddGames.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIAddGamesActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIAddGames);
        this.MIConfig.setText("Configure");
        this.MIConfig.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIConfigActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIConfig);
        this.MIExitJStella.setText("Exit JStella");
        this.MIExitJStella.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIExitJStellaActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIExitJStella);
        this.MBMain.add(this.MenuFile);
        this.MenuView.setText("View");
        this.MenuSort.setText("Arrange by...");
        this.ButtonGroupSort.add(this.RBSortTitle);
        this.RBSortTitle.setSelected(true);
        this.RBSortTitle.setText("Title");
        this.RBSortTitle.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBSortTitleActionPerformed(evt);
            }
        });
        this.MenuSort.add(this.RBSortTitle);
        this.ButtonGroupSort.add(this.RBSortMaker);
        this.RBSortMaker.setText("Maker");
        this.RBSortMaker.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBSortMakerActionPerformed(evt);
            }
        });
        this.MenuSort.add(this.RBSortMaker);
        this.ButtonGroupSort.add(this.RBSortYear);
        this.RBSortYear.setText("Year");
        this.RBSortYear.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBSortYearActionPerformed(evt);
            }
        });
        this.MenuSort.add(this.RBSortYear);
        this.ButtonGroupSort.add(this.RBSortIcon);
        this.RBSortIcon.setText("Icon");
        this.RBSortIcon.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBSortIconActionPerformed(evt);
            }
        });
        this.MenuSort.add(this.RBSortIcon);
        this.ButtonGroupSort.add(this.RBSortController);
        this.RBSortController.setText("Controller type");
        this.RBSortController.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBSortControllerActionPerformed(evt);
            }
        });
        this.MenuSort.add(this.RBSortController);
        this.MenuView.add(this.MenuSort);
        this.MenuView.add(this.SepViewA);
        this.MenuViewSize.setText("Icon size");
        this.ButtonGroupIconSize.add(this.RBIconsSmall);
        this.RBIconsSmall.setText("Small");
        this.RBIconsSmall.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBIconsSmallActionPerformed(evt);
            }
        });
        this.MenuViewSize.add(this.RBIconsSmall);
        this.ButtonGroupIconSize.add(this.RBIconsMed);
        this.RBIconsMed.setSelected(true);
        this.RBIconsMed.setText("Medium");
        this.RBIconsMed.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBIconsMedActionPerformed(evt);
            }
        });
        this.MenuViewSize.add(this.RBIconsMed);
        this.ButtonGroupIconSize.add(this.RBIconsLarge);
        this.RBIconsLarge.setText("Large");
        this.RBIconsLarge.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.RBIconsLargeActionPerformed(evt);
            }
        });
        this.MenuViewSize.add(this.RBIconsLarge);
        this.MenuView.add(this.MenuViewSize);
        this.CBMIShowTitles.setSelected(true);
        this.CBMIShowTitles.setText("Show game titles");
        this.CBMIShowTitles.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.CBMIShowTitlesActionPerformed(evt);
            }
        });
        this.MenuView.add(this.CBMIShowTitles);
        this.CBMIShowIconlessPanels.setSelected(true);
        this.CBMIShowIconlessPanels.setText("Include iconless games");
        this.CBMIShowIconlessPanels.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.CBMIShowIconlessPanelsActionPerformed(evt);
            }
        });
        this.MenuView.add(this.CBMIShowIconlessPanels);
        this.MBMain.add(this.MenuView);
        this.MenuAdvanced.setText("Advanced");
        this.MIImportStandardMetadata.setText("Import standard metadata");
        this.MIImportStandardMetadata.setEnabled(false);
        this.MIImportStandardMetadata.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIImportStandardMetadataActionPerformed(evt);
            }
        });
        this.MenuAdvanced.add(this.MIImportStandardMetadata);
        this.MIExportMetadata.setText("Export metadata");
        this.MIExportMetadata.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIExportMetadataActionPerformed(evt);
            }
        });
        this.MenuAdvanced.add(this.MIExportMetadata);
        this.MBMain.add(this.MenuAdvanced);
        this.MenuHelp.setText("Help");
        this.MIHelpContents.setText("Help contents");
        this.MIHelpContents.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaRepository.this.MIHelpContentsActionPerformed(evt);
            }
        });
        this.MenuHelp.add(this.MIHelpContents);
        this.MBMain.add(this.MenuHelp);
        this.setJMenuBar(this.MBMain);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 505) / 2, (screenSize.height - 478) / 2, 505, 478);
    }
    
    private void PMIEditSelectedActionPerformed(final ActionEvent evt) {
        this.editSelected();
    }
    
    private void PopupMainPopupMenuWillBecomeVisible(final PopupMenuEvent evt) {
        this.PMIEditSelected.setEnabled(this.getSelectedGamePanel() != null);
        this.PMIRunSelected.setEnabled(this.getSelectedGamePanel() != null);
        this.PMIDeleteSelected.setEnabled(this.getSelectedGamePanel() != null);
    }
    
    private void PMIRunSelectedActionPerformed(final ActionEvent evt) {
        this.runSelected();
    }
    
    private void RBIconsSmallActionPerformed(final ActionEvent evt) {
        this.setIconScaleFactor(0.33);
    }
    
    private void RBIconsMedActionPerformed(final ActionEvent evt) {
        this.setIconScaleFactor(0.66);
    }
    
    private void RBIconsLargeActionPerformed(final ActionEvent evt) {
        this.setIconScaleFactor(1.0);
    }
    
    private void CBMIShowTitlesActionPerformed(final ActionEvent evt) {
        this.setShowTitle(this.CBMIShowTitles.isSelected());
    }
    
    private void PMIDeleteSelectedActionPerformed(final ActionEvent evt) {
        this.deleteSelected();
    }
    
    private void formComponentResized(final ComponentEvent evt) {
        this.readjustGrid();
    }
    
    private void MIConfigActionPerformed(final ActionEvent evt) {
        final File zOldReposDir = JStellaDesktop.getRepositoryDirectory();
        JStellaDesktop.showConfigurationDialog(this);
        final File zNewReposDir = JStellaDesktop.getRepositoryDirectory();
        if (!zNewReposDir.equals(zOldReposDir)) {
            this.loadGames();
            this.updateLayout(true);
        }
    }
    
    private void formWindowClosing(final WindowEvent evt) {
        JStellaDesktop.closeFrame(this);
    }
    
    private void MIExitJStellaActionPerformed(final ActionEvent evt) {
        JStellaDesktop.closeFrame(this);
    }
    
    private void RBSortTitleActionPerformed(final ActionEvent evt) {
        this.sortGamePanels(new TitleComparator());
    }
    
    private void RBSortMakerActionPerformed(final ActionEvent evt) {
        this.sortGamePanels(new ConfigKeyComparator("jstella.game.maker"));
    }
    
    private void RBSortYearActionPerformed(final ActionEvent evt) {
        this.sortGamePanels(new ConfigKeyComparator("jstella.game.year"));
    }
    
    private void RBSortIconActionPerformed(final ActionEvent evt) {
        this.sortGamePanels(new IconComparator());
    }
    
    private void CBMIShowIconlessPanelsActionPerformed(final ActionEvent evt) {
        this.setIconlessPanelsVisible(this.CBMIShowIconlessPanels.isSelected());
    }
    
    private void MIExportMetadataActionPerformed(final ActionEvent evt) {
        this.exportMetadata();
    }
    
    private void MIImportStandardMetadataActionPerformed(final ActionEvent evt) {
        this.importStandardMetadata();
    }
    
    private void MIHelpContentsActionPerformed(final ActionEvent evt) {
        JStellaHelp.runJStellaHelp(this);
    }
    
    private void RBSortControllerActionPerformed(final ActionEvent evt) {
        this.sortGamePanels(new ConfigKeyComparator("jstella.controller.left.type"));
    }
    
    private void MIAddGamesActionPerformed(final ActionEvent evt) {
        this.addROMsToRepository();
    }
    
    private void MIRunSetupWizardActionPerformed(final ActionEvent evt) {
        JStellaDesktop.doWizard();
        this.setVisible(false);
    }
    
    private void setIconScaleFactor(final double aFactor) {
        JStellaGamePanel.setIconScaleFactor(aFactor);
        this.updateLayout(false);
    }
    
    private void setShowTitle(final boolean aShowTitle) {
        JStellaGamePanel.setShowTitle(aShowTitle);
        this.updateLayout(true);
    }
    
    private void updateLayout(final boolean aUpdatePanels) {
        for (final JStellaGamePanel zPanel : this.myGamePanelList) {
            if (aUpdatePanels) {
                zPanel.updateGamePanel();
            }
            zPanel.setPreferredSize(null);
            zPanel.revalidate();
        }
        this.PanelGames.validate();
        this.PanelGames.repaint();
    }
    
    private void readjustGrid() {
        int zCol = 6;
        int zRow = 0;
        final GridLayout zGL = (GridLayout)this.PanelGames.getLayout();
        if (this.PanelGames.isVisible()) {
            final Dimension zPrefDim = zGL.preferredLayoutSize(this.PanelGames);
            final int zItemWidth = zPrefDim.width / zGL.getColumns();
            final int zViewWidth = this.SPCenter.getViewport().getSize().width;
            final int zPotentialWidth = (int)Math.round(zViewWidth / zItemWidth);
            zCol = Math.max(zPotentialWidth, 2);
            zRow = 0;
        }
        else {
            zCol = 6;
            zRow = 0;
        }
        zGL.setColumns(zCol);
        zGL.setRows(zRow);
        zGL.layoutContainer(this.PanelGames);
        this.updateLayout(false);
    }
    
    private void sortGamePanels(final Comparator aComparator, final boolean aShowIconlessPanels) {
        this.myCurrentComparator = aComparator;
        Collections.sort(this.myGamePanelList, aComparator);
        this.PanelGames.removeAll();
        for (final JStellaGamePanel zGP : this.myGamePanelList) {
            if (aShowIconlessPanels || zGP.hasIcon()) {
                this.PanelGames.add(zGP);
            }
        }
        this.updateLayout(true);
    }
    
    private void sortGamePanels(final Comparator aComparator) {
        this.sortGamePanels(aComparator, this.myIconlessPanelsVisible);
    }
    
    private void sortGamePanels() {
        this.sortGamePanels(this.myCurrentComparator, this.myIconlessPanelsVisible);
    }
    
    private void setIconlessPanelsVisible(final boolean aVisible) {
        this.myIconlessPanelsVisible = aVisible;
        this.sortGamePanels();
    }
    
    private Map<String, JStellaGamePanel> getROMlessPanelMap() {
        final Map<String, JStellaGamePanel> zReturn = new HashMap<String, JStellaGamePanel>();
        for (final JStellaGamePanel zPanel : this.myGamePanelList) {
            final JStellaGame zGame = zPanel.getGame();
            if (zGame.getROMData() == null && zGame.getMD5() != null && !zGame.getMD5().equals("")) {
                zReturn.put(zGame.getMD5(), zPanel);
            }
        }
        return zReturn;
    }
    
    private Map<String, String> getConfiguration() {
        return JStellaDesktop.getConfiguration();
    }
    
    public JStellaGamePanel getSelectedGamePanel() {
        return this.mySelectedGamePanel;
    }
    
    public void setSelectedGamePanel(final JStellaGamePanel aGamePanel) {
        if (aGamePanel != this.mySelectedGamePanel) {
            final JStellaGamePanel zPrevSelection = this.mySelectedGamePanel;
            this.mySelectedGamePanel = aGamePanel;
            final Rectangle zDebugRect = new Rectangle(0, 0, 10, 10);
            if (zPrevSelection != null) {
                zPrevSelection.repaint();
            }
            if (aGamePanel != null) {
                aGamePanel.repaint();
            }
        }
    }
    
    public Color getRegularColor() {
        return JStellaRepository.COLOR_REGULAR;
    }
    
    public Color getSelectedColor() {
        return JStellaRepository.COLOR_SELECTED;
    }
    
    public Color getROMlessColor() {
        return JStellaRepository.COLOR_ROMLESS;
    }
    
    public boolean isSelected(final JStellaGamePanel aGamePanel) {
        return aGamePanel == this.mySelectedGamePanel;
    }
    
    private void addROMsToRepository() {
        final File zRepositoryDir = JStellaDesktop.getRepositoryDirectory();
        if (zRepositoryDir != null) {
            final File zROMDir = JStellaRunnerUtil.getValidDirectory("jstella.dir.roms", this.getConfiguration());
            JStellaDesktop.configureFileBrowser(true, false, true, zROMDir, null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS);
            final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
            if (zResult == 0) {
                this.setWaitingMode(true);
                this.importROMsAndStandardMetadata(JStellaDesktop.getFileBrowser().getSelectedFiles());
                System.out.println("Loading games");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "You must first select a valid repository directory");
        }
    }
    
    public void loadGames() {
        try {
            this.PanelGames.removeAll();
            this.myGamePanelList.clear();
            ((GridLayout)this.PanelGames.getLayout()).setColumns(6);
            final File zDir = JStellaDesktop.getRepositoryDirectory();
            if (zDir != null) {
                this.addGameFiles(this.createGameFileList(zDir));
                Collections.sort(this.myGamePanelList, new TitleComparator());
                this.sortGamePanels();
            }
            else {
                System.out.println("Error: invalid repository directory");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
    
    private void addGameFile(final File aGameFile, final boolean aSort) throws IOException, ClassNotFoundException {
        final JStellaGamePanel zGPanel = new JStellaGamePanel(this, aGameFile);
        this.myGamePanelList.add(zGPanel);
        if (!aSort) {
            this.PanelGames.add(zGPanel);
        }
        else {
            this.sortGamePanels();
        }
    }
    
    private void addGameFiles(final List<File> aGameFileList) throws IOException, ClassNotFoundException {
        for (final File zFile : aGameFileList) {
            final JStellaGamePanel zGPanel = new JStellaGamePanel(this, zFile);
            this.myGamePanelList.add(zGPanel);
        }
    }
    
    private List<File> createGameFileList(final File aRepositoryDir) {
        final List<File> zGameFileList = new ArrayList<File>();
        final File[] zFileArray = this.createGameFileArray(aRepositoryDir);
        zGameFileList.addAll(Arrays.asList(zFileArray));
        return zGameFileList;
    }
    
    private File[] createGameFileArray(final File aRepositoryDir) {
        return aRepositoryDir.listFiles(JStellaDesktop.JSFileNameExtensionFilter.FILTER_JSTELLAGAME);
    }
    
    private void saveGame(final JStellaGame aGame) {
        try {
            final File zGameFile = new File(JStellaDesktop.getRepositoryDirectory(), aGame.getGameFilename());
            final ObjectOutputStream zOOS = new ObjectOutputStream(new FileOutputStream(zGameFile));
            zOOS.writeObject(aGame);
            zOOS.close();
            System.out.println("Game saved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void editSelected() {
        if (this.mySelectedGamePanel != null) {
            final boolean zKeepChanges = JStellaGamePanelEditor.runGamePanelEditor(this, this.mySelectedGamePanel.getGame());
            if (zKeepChanges) {
                this.saveGame(this.mySelectedGamePanel.getGame());
                this.mySelectedGamePanel.updateGamePanel();
                this.sortGamePanels();
            }
            else {
                try {
                    this.mySelectedGamePanel.reloadGame(JStellaDesktop.getRepositoryDirectory());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            this.repaint();
        }
    }
    
    private void deleteSelected() {
        if (this.mySelectedGamePanel != null) {
            final int zResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete: " + this.mySelectedGamePanel.getGame().getGameTitle() + "?", "Delete game?", 0);
            if (zResult == 0) {
                final JStellaGamePanel zDelPanel = this.mySelectedGamePanel;
                this.mySelectedGamePanel = null;
                this.PanelGames.remove(zDelPanel);
                this.PanelGames.validate();
                final File zGameFile = new File(JStellaDesktop.getRepositoryDirectory(), zDelPanel.getGame().getGameFilename());
                if (zGameFile.getName().toLowerCase().endsWith("j26")) {
                    System.out.println("Deleting file: " + zGameFile.getName());
                    if (zGameFile.exists()) {
                        zGameFile.delete();
                    }
                }
            }
        }
    }
    
    private void updateGamePanels() {
        for (final JStellaGamePanel zPanel : this.myGamePanelList) {
            zPanel.updateGamePanel();
        }
    }
    
    private void runSelected() {
        if (this.mySelectedGamePanel != null) {
            this.runGame(this.mySelectedGamePanel);
        }
    }
    
    public void runGame(final JStellaGamePanel aGamePanel) {
        if (aGamePanel != null && aGamePanel.getGame().getROMData() != null) {
            JStellaDesktop.playJStellaGame(aGamePanel.getGame());
        }
    }
    
    public JPopupMenu getPopupMenu() {
        return this.PopupMain;
    }
    
    private void postProgressUpdate(final String aMessage) {
        this.LabelProgress.setText(aMessage);
    }
    
    public void setWaitingMode(final boolean aWait) {
        this.setCursor(aWait ? Cursor.getPredefinedCursor(3) : null);
        this.MBMain.setEnabled(!aWait);
        this.getGlassPane().setVisible(aWait);
    }
    
    private JStellaGame[] createMetadata() {
        final List<JStellaGame> zMetadataList = new ArrayList<JStellaGame>();
        final Component[] zComponents = this.PanelGames.getComponents();
        for (int i = 0; i < zComponents.length; ++i) {
            if (zComponents[i] instanceof JStellaGamePanel) {
                final JStellaGamePanel zGamePanel = (JStellaGamePanel)zComponents[i];
                zMetadataList.add(zGamePanel.getGame().createMetadataObject());
            }
        }
        JStellaGame[] zReturn = new JStellaGame[zMetadataList.size()];
        zReturn = zMetadataList.toArray(zReturn);
        return zReturn;
    }
    
    private void exportMetadata() {
        JStellaDesktop.configureFileBrowser(false, false, true, null, null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_METADATA_COLLECTION);
        final int zResult = JStellaDesktop.getFileBrowser().showSaveDialog(this);
        if (zResult == 0) {
            File zSelected = JStellaDesktop.getFileBrowser().getSelectedFile();
            final String zMainExtension = "." + JStellaDesktop.JSFileNameExtensionFilter.FILTER_METADATA_COLLECTION.getMainExtension();
            if (!zSelected.getPath().endsWith(zMainExtension)) {
                zSelected = new File(zSelected.getPath() + zMainExtension);
            }
            final JStellaGame[] zMetadata = this.createMetadata();
            addMetadataToCollection(zMetadata, zSelected);
        }
    }
    
    public void importStandardMetadata() {
        JOptionPane.showMessageDialog(this, "Preparing to import metadata.  This may take a few minutes.");
        this.myProgressDialog.setVisible(true);
        final File[] zFileArray = JStellaDesktop.getRepositoryDirectory().listFiles(JStellaDesktop.JSFileNameExtensionFilter.FILTER_JSTELLAGAME);
        this.importStandardMetadata(zFileArray);
    }
    
    public void importStandardMetadata(final File[] aGameFiles) {
        try {
            final InputStream zStream = JStellaImporter.class.getResourceAsStream("/jstella/resources/metadata/metadata.j26mc");
            final Map<String, File> zMD5Map = JStellaImporter.createMD5FileMap(aGameFiles);
            JStellaImporter.launchMetadataImporter(this, zMD5Map, zStream, new ResumeRepositoryOperations());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void importROMsAndStandardMetadata(final File[] aROMFiles) {
        try {
            final InputStream zStream = JStellaImporter.class.getResourceAsStream("/jstella/resources/metadata/metadata.j26mc");
            JStellaImporter.launchROMAndMetadataImporter(this, aROMFiles, zStream, new ResumeRepositoryOperations());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void exportMetadataItem(final ZipOutputStream aZOS, final JStellaGame aMetadata) throws IOException {
        final ZipEntry zEntry = new ZipEntry(aMetadata.getGameFilename());
        aZOS.putNextEntry(zEntry);
        final byte[] zData = JStellaDesktop.toByteArray(aMetadata);
        aZOS.write(zData);
        aZOS.closeEntry();
    }
    
    protected static void addMetadataToCollection(final JStellaGame[] aMetadata, final File aMetadataCollection) {
        try {
            final ZipOutputStream zZOS = new ZipOutputStream(new FileOutputStream(aMetadataCollection));
            zZOS.setMethod(8);
            for (int i = 0; i < aMetadata.length; ++i) {
                exportMetadataItem(zZOS, aMetadata[i]);
            }
            zZOS.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }
    
    static {
        COLOR_REGULAR = UIManager.getDefaults().getColor("Panel.background");
        COLOR_SELECTED = Color.GREEN;
        COLOR_ROMLESS = Color.RED;
    }
    
    private class TitleComparator implements Comparator<JStellaGamePanel>
    {
        public int compare(final JStellaGamePanel o1, final JStellaGamePanel o2) {
            return o1.getGame().getGameTitle().compareTo(o2.getGame().getGameTitle());
        }
    }
    
    private class ConfigKeyComparator implements Comparator<JStellaGamePanel>
    {
        private String myConfigKey;
        
        public ConfigKeyComparator(final String aConfigKey) {
            this.myConfigKey = "";
            this.myConfigKey = aConfigKey;
        }
        
        private String getValue(final JStellaGamePanel zGP) {
            String zValue = zGP.getGame().getGameConfig().get(this.myConfigKey);
            if (zValue == null || zValue.equals("")) {
                zValue = "~~~~~~";
            }
            return zValue;
        }
        
        public int compare(final JStellaGamePanel o1, final JStellaGamePanel o2) {
            return this.getValue(o1).compareTo(this.getValue(o2));
        }
    }
    
    private class IconComparator implements Comparator<JStellaGamePanel>
    {
        private int getIconValue(final JStellaGamePanel aGP) {
            final int zValue = (aGP.getGame().getGraphicData() == null) ? 1 : -1;
            return zValue;
        }
        
        public int compare(final JStellaGamePanel o1, final JStellaGamePanel o2) {
            return this.getIconValue(o1) - this.getIconValue(o2);
        }
    }
    
    public class ResumeRepositoryOperations implements Runnable
    {
        public void run() {
            JStellaRepository.this.loadGames();
            JStellaRepository.this.updateLayout(true);
            JStellaRepository.this.setWaitingMode(false);
        }
    }
}
