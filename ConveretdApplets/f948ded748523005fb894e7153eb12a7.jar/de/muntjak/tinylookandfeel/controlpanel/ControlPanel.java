// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import javax.swing.JComponent;
import java.util.Hashtable;
import java.util.Collection;
import java.util.TreeSet;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import javax.swing.plaf.FontUIResource;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.InsetsUIResource;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusEvent;
import de.muntjak.tinylookandfeel.TinyTitlePane;
import javax.swing.JLayeredPane;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.image.ImageObserver;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.MouseAdapter;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JDesktopPane;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.text.Position;
import javax.swing.tree.TreeCellRenderer;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.AbstractTableModel;
import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import de.muntjak.tinylookandfeel.TinyDefaultTheme;
import javax.swing.LookAndFeel;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Frame;
import java.io.File;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Collections;
import java.util.Vector;
import javax.swing.MenuElement;
import javax.swing.JMenuItem;
import javax.swing.text.Style;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.event.ChangeEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.Insets;
import java.util.Iterator;
import java.util.Enumeration;
import javax.swing.UIDefaults;
import java.util.TreeMap;
import javax.swing.UIManager;
import java.beans.PropertyVetoException;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.JFrame;

public class ControlPanel
{
    private JFrame theFrame;
    private static final String WINDOW_TITLE = "TinyLaF 1.3.8 Controlpanel";
    private static final int PLAIN_FONT = 1;
    private static final int BOLD_FONT = 2;
    private static final int SPECIAL_FONT = 3;
    static final int CONTROLS_BUTTON = 1;
    static final int CONTROLS_WINDOW_BUTTON = 2;
    static final int CONTROLS_COMBO = 3;
    static final int CONTROLS_SCROLLBAR = 4;
    static final int CONTROLS_SPINNER = 5;
    static final int CONTROLS_ACTIVE_FRAME_CAPTION = 6;
    static final int CONTROLS_INACTIVE_FRAME_CAPTION = 7;
    private static String directoryPath;
    private static final Border sbFieldBorder;
    private static final Color infoColor;
    private static final Border infoBorder;
    private static final int menuShortcutKeyMask;
    private static Component[] windowButtons;
    private Component[] buttons;
    private Component[] combos;
    private Component[] frames;
    private Component[] scrollBars;
    private Component[] spinners;
    private Icon icon99;
    private Image image99;
    private String currentFileName;
    private ActionListener selectThemeAction;
    private ActionListener checkAction;
    private ChangeListener updateAction;
    private ChangeListener spinnerUpdateAction;
    private boolean resistUpdate;
    private JPanel widgetInfo;
    private ExamplePanel.DesktopPane desktopPane;
    private JTree tree1;
    private JTree tree2;
    private JScrollPane sp1;
    private JScrollPane sp2;
    private PopupTrigger trigger;
    private JToolBar theToolBar;
    private JMenu themesMenu;
    private JTabbedPane mainTab;
    private JTabbedPane compTab;
    private JButton updateThemeButton;
    private JCheckBoxMenuItem customStyle;
    private FontPanel plainFontPanel;
    private FontPanel boldFontPanel;
    private FontPanel specialFontPanel;
    private JComboBox fontCombo;
    private JRadioButton isPlainFont;
    private JRadioButton isBoldFont;
    private ColoredFont[] selectedFont;
    private ExamplePanel examplePanel;
    private JButton exampleButton;
    private JButton exampleDisabledButton;
    private JToggleButton exampleToggleButton;
    private Icon buttonIcon;
    private JPopupMenu thePopup;
    private JInternalFrame internalFrame;
    private JInternalFrame palette;
    private JPopupMenu hsbPopup;
    private JPopupMenu sbPopup;
    private ButtonsCP buttonsCP;
    private ScrollBarCP scrollsCP;
    private SeparatorCP separatorCP;
    private TabbedPaneCP tabsCP;
    private ComboCP comboCP;
    private MenuCP menuCP;
    private ListCP listCP;
    private SliderCP sliderCP;
    private SpinnerCP spinnerCP;
    private ProgressCP progressCP;
    private TextCP textCP;
    private TreeCP treeCP;
    private ToolBarCP toolCP;
    private TableCP tableCP;
    private JTable exampleTable;
    private FrameCP frameCP;
    private static JCheckBox decoratedFramesCheck;
    private IconCP iconCP;
    private ToolTipCP tipCP;
    private MiscCP miscCP;
    private JSlider vertSlider;
    private JSlider horzSlider;
    private SBField selectedSBField;
    private SBField mainField;
    private SBField rollField;
    private SBField backField;
    private SBField frameField;
    private SBField sub1Field;
    private SBField sub2Field;
    private SBField sub3Field;
    private SBField sub4Field;
    private SBField sub5Field;
    private SBField sub6Field;
    private SBField sub7Field;
    private SBField sub8Field;
    private HSBField selectedHSBField;
    private SBField buttonNormalBg;
    private SBField buttonRolloverBg;
    private SBField buttonPressedBg;
    private SBField buttonDisabledBg;
    private SBField buttonBorder;
    private SBField buttonDark;
    private SBField buttonLight;
    private SBField buttonRollover;
    private SBField buttonDefault;
    private SBField buttonCheck;
    private SBField buttonCheckDisabled;
    private SBField buttonDisabledBorder;
    private SBField buttonDisabledDark;
    private SBField buttonDisabledLight;
    private SBField buttonDisabledFg;
    private SBField checkDisabledFg;
    private SBField radioDisabledFg;
    SpreadControl buttonSpreadLight;
    SpreadControl buttonSpreadLightDisabled;
    SpreadControl buttonSpreadDark;
    SpreadControl buttonSpreadDarkDisabled;
    private SBField textBg;
    private SBField textSelectedBg;
    private SBField textDisabledBg;
    private SBField textBorder;
    private SBField textBorderDisabled;
    private SBField textCaret;
    private SBField textDark;
    private SBField textDisabledDark;
    private SBField textLight;
    private SBField textDisabledLight;
    private SBField textText;
    private SBField textSelectedText;
    private SBField comboBg;
    private SBField comboText;
    private SBField comboBorder;
    private SBField comboBorderDisabled;
    private SBField comboSelectedBg;
    private SBField comboDark;
    private SBField comboDisabledDark;
    private SBField comboLight;
    private SBField comboDisabledLight;
    private SBField comboArrowField;
    private SBField comboArrowDisabled;
    private SBField comboButt;
    private SBField comboButtRollover;
    private SBField comboButtPressed;
    private SBField comboButtDisabled;
    private SBField comboButtBorder;
    private SBField comboButtDark;
    private SBField comboButtLight;
    private SBField comboButtBorderDisabled;
    private SBField comboButtDarkDisabled;
    private SBField comboButtLightDisabled;
    private SBField comboSelectedText;
    SpreadControl comboSpreadLight;
    SpreadControl comboSpreadLightDisabled;
    SpreadControl comboSpreadDark;
    SpreadControl comboSpreadDarkDisabled;
    private SBField menuRolloverBg;
    private SBField menuSepDark;
    private SBField menuSepLight;
    private SBField menuRolloverFg;
    private SBField menuDisabledFg;
    private SBField menuBar;
    private SBField menuItemRollover;
    private SBField menuPopup;
    private SBField menuBorder;
    private SBField menuDark;
    private SBField menuLight;
    private SBField menuInnerHilight;
    private SBField menuInnerShadow;
    private SBField menuOuterHilight;
    private SBField menuOuterShadow;
    private SBField menuIcon;
    private SBField menuIconRollover;
    private SBField menuIconDisabled;
    private SBField menuIconShadow;
    private SBField menuSelectedText;
    private SBField listBg;
    private SBField listText;
    private SBField listSelectedBg;
    private SBField listSelectedText;
    private SBField tabNormalBg;
    private SBField tabSelectedBg;
    private SBField tabRoll;
    private SBField tabDisabled;
    private SBField tabDisabledSelected;
    private SBField tabDisabledText;
    private SBField tabBorder;
    private SBField tabDark;
    private SBField tabLight;
    private SBField tabPaneBorder;
    private SBField tabPaneDark;
    private SBField tabPaneLight;
    private SBField scrollThumbField;
    private SBField scrollButtField;
    private SBField scrollArrowField;
    private SBField trackField;
    private SBField scrollThumbRolloverBg;
    private SBField scrollThumbPressedBg;
    private SBField scrollThumbDisabledBg;
    private SBField scrollButtRolloverBg;
    private SBField scrollButtPressedBg;
    private SBField scrollButtDisabledBg;
    private SBField trackDisabled;
    private SBField trackBorder;
    private SBField trackBorderDisabled;
    private SBField scrollArrowDisabled;
    private SBField scrollGripDark;
    private SBField scrollGripLight;
    private SBField scrollPane;
    private SBField scrollBorder;
    private SBField scrollDark;
    private SBField scrollLight;
    private SBField scrollBorderDisabled;
    private SBField scrollDarkDisabled;
    private SBField scrollLightDisabled;
    SpreadControl scrollSpreadLight;
    SpreadControl scrollSpreadLightDisabled;
    SpreadControl scrollSpreadDark;
    SpreadControl scrollSpreadDarkDisabled;
    private SBField sliderThumbRolloverBg;
    private SBField sliderThumbPressedBg;
    private SBField sliderThumbDisabledBg;
    private SBField sliderBorder;
    private SBField sliderDark;
    private SBField sliderLight;
    private SBField sliderThumbField;
    private SBField sliderDisabledBorder;
    private SBField sliderDisabledDark;
    private SBField sliderDisabledLight;
    private SBField sliderTrack;
    private SBField sliderTrackBorder;
    private SBField sliderTrackDark;
    private SBField sliderTrackLight;
    private SBField sliderTick;
    private SBField sliderTickDisabled;
    private SBField sliderFocusColor;
    private SBField spinnerButtField;
    private SBField spinnerArrowField;
    private SBField spinnerButtRolloverBg;
    private SBField spinnerButtPressedBg;
    private SBField spinnerButtDisabledBg;
    private SBField spinnerBorder;
    private SBField spinnerDark;
    private SBField spinnerLight;
    private SBField spinnerArrowDisabled;
    private SBField spinnerDisabledBorder;
    private SBField spinnerDisabledDark;
    private SBField spinnerDisabledLight;
    SpreadControl spinnerSpreadLight;
    SpreadControl spinnerSpreadLightDisabled;
    SpreadControl spinnerSpreadDark;
    SpreadControl spinnerSpreadDarkDisabled;
    private Timer progressTimer;
    private JProgressBar horzProgressBar;
    private JProgressBar vertProgressBar;
    private SBField progressField;
    private SBField progressTrack;
    private SBField progressBorder;
    private SBField progressDark;
    private SBField progressLight;
    private SBField progressSelectFore;
    private SBField progressSelectBack;
    private SBField treeBg;
    private SBField treeTextBg;
    private SBField treeSelectedBg;
    private SBField treeText;
    private SBField treeSelectedText;
    private SBField treeLine;
    private SBField toolBar;
    private SBField toolBarDark;
    private SBField toolBarLight;
    private SBField toolButt;
    private SBField toolButtRollover;
    private SBField toolButtPressed;
    private SBField toolButtSelected;
    private SBField toolBorder;
    private SBField toolBorderPressed;
    private SBField toolBorderRollover;
    private SBField toolBorderSelected;
    private SBField toolBorderDark;
    private SBField toolBorderLight;
    private SBField toolGripDark;
    private SBField toolGripLight;
    private SBField toolSepDark;
    private SBField toolSepLight;
    private SBField frameCaption;
    private SBField frameCaptionDisabled;
    private SBField frameBorder;
    private SBField frameDark;
    private SBField frameLight;
    private SBField frameBorderDisabled;
    private SBField frameDarkDisabled;
    private SBField frameLightDisabled;
    private SBField frameTitle;
    private SBField frameTitleDisabled;
    private SBField frameButt;
    private SBField frameButtRollover;
    private SBField frameButtPressed;
    private SBField frameButtDisabled;
    SpreadControl frameButtSpreadLight;
    SpreadControl frameButtSpreadLightDisabled;
    SpreadControl frameButtSpreadDark;
    SpreadControl frameButtSpreadDarkDisabled;
    private SBField frameButtClose;
    private SBField frameButtCloseRollover;
    private SBField frameButtClosePressed;
    private SBField frameButtCloseDisabled;
    SpreadControl frameButtCloseSpreadLight;
    SpreadControl frameButtCloseSpreadLightDisabled;
    SpreadControl frameButtCloseSpreadDark;
    SpreadControl frameButtCloseSpreadDarkDisabled;
    private SBField frameButtBorder;
    private SBField frameButtDark;
    private SBField frameButtLight;
    private SBField frameButtBorderDisabled;
    private SBField frameButtDarkDisabled;
    private SBField frameButtLightDisabled;
    private SBField frameButtCloseBorder;
    private SBField frameButtCloseDark;
    private SBField frameButtCloseLight;
    private SBField frameButtCloseBorderDisabled;
    private SBField frameButtCloseDarkDisabled;
    private SBField frameButtCloseLightDisabled;
    private SBField frameSymbol;
    private SBField frameSymbolPressed;
    private SBField frameSymbolDisabled;
    private SBField frameSymbolDark;
    private SBField frameSymbolLight;
    private SBField frameSymbolClose;
    private SBField frameSymbolClosePressed;
    private SBField frameSymbolCloseDisabled;
    private SBField frameSymbolCloseDark;
    private SBField frameSymbolCloseLight;
    SpreadControl frameSpreadDark;
    SpreadControl frameSpreadLight;
    SpreadControl frameSpreadDarkDisabled;
    SpreadControl frameSpreadLightDisabled;
    private CheckedIcon[] iconChecks;
    private HSBField[] hsb;
    private SBField tableBack;
    private SBField tableGrid;
    private SBField tableHeaderBack;
    private SBField tableHeaderRolloverBack;
    private SBField tableHeaderRollover;
    private SBField tableHeaderArrow;
    private SBField tableSelectedBack;
    private SBField tableSelectedFore;
    private SBField tableBorderDark;
    private SBField tableBorderLight;
    private SBField tableHeaderDark;
    private SBField tableHeaderLight;
    private SBField sepDark;
    private SBField sepLight;
    private SBField tipBg;
    private SBField tipBorder;
    private SBField tipBgDis;
    private SBField tipBorderDis;
    private SBField tipText;
    private SBField tipTextDis;
    private SBField titledBorderColor;
    private SBField textPaneBg;
    private SBField editorPaneBg;
    private SBField desktopPaneBg;
    static /* synthetic */ Class class$de$muntjak$tinylookandfeel$controlpanel$NonSortableTableModel$TableColorIcon;
    static /* synthetic */ Class class$java$lang$Integer;
    
    public ControlPanel() {
        this.selectThemeAction = new SelectThemeAction();
        this.checkAction = new CheckAction();
        this.updateAction = new UpdateAction();
        this.spinnerUpdateAction = new SpinnerUpdateAction();
        this.resistUpdate = false;
        this.iconChecks = new CheckedIcon[20];
        this.hsb = new HSBField[20];
        this.icon99 = TinyLookAndFeel.loadIcon("icon99.gif", null);
        this.image99 = ((ImageIcon)this.icon99).getImage();
        this.createFrame();
    }
    
    private void createFrame() {
        if (ControlPanel.decoratedFramesCheck != null && ControlPanel.decoratedFramesCheck.isSelected()) {
            Toolkit.getDefaultToolkit().setDynamicLayout(true);
            System.setProperty("sun.awt.noerasebackground", "true");
            JFrame.setDefaultLookAndFeelDecorated(true);
        }
        else {
            Toolkit.getDefaultToolkit().setDynamicLayout(false);
            System.setProperty("sun.awt.noerasebackground", "false");
            JFrame.setDefaultLookAndFeelDecorated(false);
        }
        JDialog.setDefaultLookAndFeelDecorated(true);
        (this.theFrame = new JFrame("TinyLaF 1.3.8 Controlpanel")).setDefaultCloseOperation(3);
        boolean enabled = false;
        boolean selected = false;
        if (this.customStyle != null) {
            enabled = this.customStyle.isEnabled();
            selected = this.customStyle.isSelected();
        }
        this.setupUI();
        this.customStyle.setEnabled(enabled);
        this.customStyle.setSelected(selected);
        this.createHSBPopup();
        this.createSBPopup();
        this.initColors();
        this.initPanels();
        this.updateThemeButton.setEnabled(false);
        this.startProgressTimer();
        try {
            this.internalFrame.setSelected(true);
        }
        catch (PropertyVetoException ex) {}
    }
    
    private void startProgressTimer() {
        if (this.progressTimer == null) {
            this.progressTimer = new Timer(500, new ProgressAction());
        }
        this.vertProgressBar.setIndeterminate(true);
        this.progressTimer.start();
    }
    
    private void stopProgressTimer() {
        if (this.progressTimer == null) {
            return;
        }
        this.progressTimer.stop();
        this.horzProgressBar.setIndeterminate(false);
        this.vertProgressBar.setIndeterminate(false);
    }
    
    private void showUIVariables() {
        final UIDefaults defaults = UIManager.getDefaults();
        int n = 0;
        final TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)defaults).keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            treeMap.put(string, defaults.get(string));
        }
        final Iterator<String> iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()) {
            final String string2 = iterator.next().toString();
            System.out.print("#" + n++ + " : " + string2);
            System.out.println(" = " + treeMap.get(string2));
        }
        System.out.println();
    }
    
    void showUIVariables(final String s) {
        final UIDefaults defaults = UIManager.getDefaults();
        int n = 0;
        final TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)defaults).keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            if (s == null || string.indexOf(s) != -1) {
                treeMap.put(string, defaults.get(string));
            }
        }
        final Iterator<String> iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()) {
            final String string2 = iterator.next().toString();
            treeMap.get(string2);
            System.out.print("#" + n++ + " : " + string2);
            System.out.println(" = " + treeMap.get(string2));
        }
    }
    
    private void showUIValues(final String s) {
        final UIDefaults defaults = UIManager.getDefaults();
        int n = 0;
        final TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)defaults).keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            treeMap.put(string, defaults.get(string));
        }
        final Iterator<String> iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()) {
            final String string2 = iterator.next().toString();
            final Object value = treeMap.get(string2);
            if (value != null && value.toString().indexOf(s) != -1) {
                System.out.print("#" + n++ + " : " + string2);
                System.out.println(" = " + value);
            }
        }
        System.out.println();
    }
    
    private void showInsets() {
        final UIDefaults defaults = UIManager.getDefaults();
        int n = 0;
        final TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)defaults).keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            final Object value = defaults.get(string);
            if (value instanceof Insets) {
                treeMap.put(string, value);
            }
        }
        final Iterator<String> iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()) {
            final String string2 = iterator.next().toString();
            System.out.print("#" + n++ + " : " + string2);
            System.out.println(" = " + treeMap.get(string2));
        }
        System.out.println();
    }
    
    private void showSystemProperties() {
        final Enumeration<Object> keys = ((Hashtable<Object, V>)System.getProperties()).keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            System.out.println(nextElement.toString() + " : " + System.getProperty(nextElement.toString()).toString());
        }
    }
    
    private void showMessageDialog() {
        JOptionPane.showMessageDialog(this.theFrame, "No messages today.");
    }
    
    private void showConfirmationDialog() {
        JOptionPane.showConfirmDialog(this.theFrame, "Do you really have a choice?");
    }
    
    private void showWarningDialog() {
        JOptionPane.showMessageDialog(this.theFrame, "You have been warned!", "Warning", 2);
    }
    
    private void showErrorDialog() {
        JOptionPane.showMessageDialog(this.theFrame, "Unknown software error. Panic!", "Error", 0);
    }
    
    private void showInternalWarningDialog(final String s) {
        JOptionPane.showMessageDialog(this.theFrame, s, "Warning", 2);
    }
    
    private void setupUI() {
        final JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(this.createFileMenu());
        jMenuBar.add(this.createThemeMenu());
        jMenuBar.add(this.createStyleMenu());
        jMenuBar.add(this.createDisabledMenu());
        jMenuBar.add(this.createTestMenu());
        jMenuBar.add(this.createHelpMenu());
        this.theFrame.setJMenuBar(jMenuBar);
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(this.createToolBar(), "North");
        (this.mainTab = new JTabbedPane(2)).add("Colors", this.createColorPanel());
        this.mainTab.add("Fonts", this.createFontPanel());
        this.mainTab.add("Decoration", this.createDecorationPanel());
        this.mainTab.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                if (ControlPanel.this.mainTab.getSelectedIndex() == 2) {
                    ((CardLayout)ControlPanel.this.widgetInfo.getLayout()).show(ControlPanel.this.widgetInfo, "show");
                }
                else {
                    ((CardLayout)ControlPanel.this.widgetInfo.getLayout()).show(ControlPanel.this.widgetInfo, "hide");
                }
            }
        });
        panel2.add(this.mainTab, "Center");
        panel.add(panel2, "North");
        final JPanel panel3 = new JPanel(new FlowLayout(0, 92, 4));
        final JPanel panel4 = new JPanel(new BorderLayout(3, 0));
        panel4.setBackground(ControlPanel.infoColor);
        panel4.setBorder(ControlPanel.infoBorder);
        final JLabel label = new JLabel("<html><b>Note: </b>Properties marked with");
        label.setForeground(Color.BLACK);
        panel4.add(label, "West");
        panel4.add(new JLabel(this.icon99), "Center");
        final JLabel label2 = new JLabel("apply to 99 Style only.");
        label2.setForeground(Color.BLACK);
        panel4.add(label2, "East");
        (this.widgetInfo = new JPanel(new CardLayout())).add(new JLabel(), "hide");
        this.widgetInfo.add(panel4, "show");
        panel3.add(this.widgetInfo);
        this.updateThemeButton = new JButton("Apply Settings");
        this.buttons[14] = this.updateThemeButton;
        this.theFrame.getRootPane().setDefaultButton(this.updateThemeButton);
        this.updateThemeButton.addActionListener(new SetThemeAction());
        panel3.add(this.updateThemeButton);
        panel.add(panel3, "South");
        this.theFrame.getContentPane().add(panel, "North");
        this.examplePanel = new ExamplePanel();
        final JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setBorder(new TitledBorder("Examples"));
        panel5.add(this.examplePanel, "Center");
        this.theFrame.getContentPane().add(panel5, "Center");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.theFrame.pack();
        this.theFrame.setLocation((screenSize.width - this.theFrame.getWidth()) / 2, (screenSize.height - this.theFrame.getHeight()) / 3);
        this.theFrame.setVisible(true);
    }
    
    private void decorateFrame(final boolean b) {
        this.theFrame.dispose();
        this.createFrame();
        this.mainTab.setSelectedComponent(this.compTab);
        this.compTab.setSelectedComponent(this.frameCP);
    }
    
    private JPanel createColorPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 0, 4);
        panel2.add(new JLabel("Main Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 0, 4);
        panel2.add(new JLabel("Background Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel2.add(new JLabel("Disabled Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        panel2.add(new JLabel("Frame Color"), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        gridBagConstraints.insets = new Insets(2, 4, 8, 4);
        panel2.add(this.mainField = new SBField(Theme.mainColor, 24), gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 8, 4);
        panel2.add(this.backField = new SBField(Theme.backColor, 24), gridBagConstraints);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridx;
        panel2.add(this.rollField = new SBField(Theme.disColor, 24), gridBagConstraints);
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridx;
        (this.frameField = new SBField(Theme.frameColor, 24)).setName("ff");
        panel2.add(this.frameField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridy;
        gridBagConstraints.insets = new Insets(2, 4, 0, 4);
        panel2.add(new JLabel("Sub1 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        ++gridBagConstraints10.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 0, 4);
        panel2.add(new JLabel("Sub2 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
        ++gridBagConstraints11.gridx;
        panel2.add(new JLabel("Sub3 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
        ++gridBagConstraints12.gridx;
        panel2.add(new JLabel("Sub4 Color"), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
        ++gridBagConstraints13.gridy;
        gridBagConstraints.insets = new Insets(2, 4, 8, 4);
        panel2.add(this.sub1Field = new SBField(Theme.sub1Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
        ++gridBagConstraints14.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 8, 4);
        panel2.add(this.sub2Field = new SBField(Theme.sub2Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
        ++gridBagConstraints15.gridx;
        panel2.add(this.sub3Field = new SBField(Theme.sub3Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
        ++gridBagConstraints16.gridx;
        panel2.add(this.sub4Field = new SBField(Theme.sub4Color, true), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
        ++gridBagConstraints17.gridy;
        gridBagConstraints.insets = new Insets(2, 4, 0, 4);
        panel2.add(new JLabel("Sub5 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
        ++gridBagConstraints18.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 0, 4);
        panel2.add(new JLabel("Sub6 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
        ++gridBagConstraints19.gridx;
        panel2.add(new JLabel("Sub7 Color"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
        ++gridBagConstraints20.gridx;
        panel2.add(new JLabel("Sub8 Color"), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
        ++gridBagConstraints21.gridy;
        gridBagConstraints.insets = new Insets(2, 4, 8, 4);
        panel2.add(this.sub5Field = new SBField(Theme.sub5Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
        ++gridBagConstraints22.gridx;
        gridBagConstraints.insets = new Insets(2, 8, 8, 4);
        panel2.add(this.sub6Field = new SBField(Theme.sub6Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
        ++gridBagConstraints23.gridx;
        panel2.add(this.sub7Field = new SBField(Theme.sub7Color, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
        ++gridBagConstraints24.gridx;
        panel2.add(this.sub8Field = new SBField(Theme.sub8Color, true), gridBagConstraints);
        final JPanel panel3 = new JPanel(new FlowLayout(1, 0, 12));
        panel3.add(panel2);
        panel.add(panel3, "North");
        return panel;
    }
    
    private JToolBar createToolBar() {
        this.theToolBar = new JToolBar();
        final ButtonGroup buttonGroup = new ButtonGroup();
        final Dimension dimension = new Dimension(16, 18);
        for (int i = 0; i < 6; ++i) {
            final JToggleButton toggleButton = new JToggleButton("", new ColorIcon(dimension));
            buttonGroup.add(toggleButton);
            this.theToolBar.add(toggleButton);
        }
        this.theToolBar.addSeparator();
        for (int j = 0; j < 5; ++j) {
            final JToggleButton toggleButton2 = new JToggleButton("", new ColorIcon(dimension));
            buttonGroup.add(toggleButton2);
            this.theToolBar.add(toggleButton2);
        }
        this.theToolBar.addSeparator();
        for (int k = 0; k < 4; ++k) {
            final JToggleButton toggleButton3 = new JToggleButton("", new ColorIcon(dimension));
            buttonGroup.add(toggleButton3);
            this.theToolBar.add(toggleButton3);
        }
        this.theToolBar.add(new JToggleButton("TB_Button"));
        return this.theToolBar;
    }
    
    private StyledDocument createStyledDocument() {
        final DefaultStyledDocument defaultStyledDocument = new DefaultStyledDocument();
        final Style addStyle = defaultStyledDocument.addStyle("regular", StyleContext.getDefaultStyleContext().getStyle("default"));
        StyleConstants.setFontFamily(addStyle, "SansSerif");
        StyleConstants.setFontSize(addStyle, 12);
        StyleConstants.setForeground(addStyle, Color.BLACK);
        StyleConstants.setUnderline(addStyle, false);
        StyleConstants.setBold(addStyle, false);
        StyleConstants.setItalic(addStyle, false);
        defaultStyledDocument.setLogicalStyle(0, addStyle);
        try {
            defaultStyledDocument.insertString(0, "         JTextPane with\n", addStyle);
        }
        catch (BadLocationException ex) {}
        int n = 24;
        final Color color = new Color(132, 0, 0);
        final Style addStyle2 = defaultStyledDocument.addStyle("red24", addStyle);
        StyleConstants.setFontSize(addStyle2, 24);
        StyleConstants.setUnderline(addStyle2, true);
        StyleConstants.setForeground(addStyle2, color);
        try {
            defaultStyledDocument.insertString(n++, "S", addStyle2);
        }
        catch (BadLocationException ex2) {}
        final Style addStyle3 = defaultStyledDocument.addStyle("red22", addStyle2);
        StyleConstants.setFontSize(addStyle3, 22);
        StyleConstants.setUnderline(addStyle3, true);
        StyleConstants.setForeground(addStyle3, color);
        try {
            defaultStyledDocument.insertString(n++, "t", addStyle3);
        }
        catch (BadLocationException ex3) {}
        final Style addStyle4 = defaultStyledDocument.addStyle("red20", addStyle3);
        StyleConstants.setFontSize(addStyle4, 20);
        StyleConstants.setUnderline(addStyle4, true);
        StyleConstants.setForeground(addStyle4, color);
        try {
            defaultStyledDocument.insertString(n++, "y", addStyle4);
        }
        catch (BadLocationException ex4) {}
        final Style addStyle5 = defaultStyledDocument.addStyle("red18", addStyle4);
        StyleConstants.setFontSize(addStyle5, 18);
        StyleConstants.setUnderline(addStyle5, true);
        StyleConstants.setForeground(addStyle5, color);
        try {
            defaultStyledDocument.insertString(n++, "l", addStyle5);
        }
        catch (BadLocationException ex5) {}
        final Style addStyle6 = defaultStyledDocument.addStyle("red16", addStyle5);
        StyleConstants.setFontSize(addStyle6, 16);
        StyleConstants.setUnderline(addStyle6, true);
        StyleConstants.setForeground(addStyle6, color);
        try {
            defaultStyledDocument.insertString(n++, "e", addStyle6);
        }
        catch (BadLocationException ex6) {}
        final Style addStyle7 = defaultStyledDocument.addStyle("red14", addStyle6);
        StyleConstants.setFontSize(addStyle7, 14);
        StyleConstants.setUnderline(addStyle7, true);
        StyleConstants.setForeground(addStyle7, color);
        try {
            defaultStyledDocument.insertString(n++, "d ", addStyle7);
        }
        catch (BadLocationException ex7) {}
        ++n;
        final Color color2 = new Color(0, 130, 132);
        final Style addStyle8 = defaultStyledDocument.addStyle("green12", addStyle7);
        StyleConstants.setFontSize(addStyle8, 12);
        StyleConstants.setUnderline(addStyle8, true);
        StyleConstants.setForeground(addStyle8, color2);
        try {
            defaultStyledDocument.insertString(n++, "D", addStyle8);
        }
        catch (BadLocationException ex8) {}
        final Style addStyle9 = defaultStyledDocument.addStyle("green13", addStyle8);
        StyleConstants.setFontSize(addStyle9, 13);
        StyleConstants.setUnderline(addStyle9, true);
        StyleConstants.setForeground(addStyle9, color2);
        try {
            defaultStyledDocument.insertString(n++, "o", addStyle9);
        }
        catch (BadLocationException ex9) {}
        final Style addStyle10 = defaultStyledDocument.addStyle("green14", addStyle9);
        StyleConstants.setFontSize(addStyle10, 14);
        StyleConstants.setUnderline(addStyle10, true);
        StyleConstants.setForeground(addStyle10, color2);
        try {
            defaultStyledDocument.insertString(n++, "c", addStyle10);
        }
        catch (BadLocationException ex10) {}
        final Style addStyle11 = defaultStyledDocument.addStyle("green16", addStyle10);
        StyleConstants.setFontSize(addStyle11, 16);
        StyleConstants.setUnderline(addStyle11, true);
        StyleConstants.setForeground(addStyle11, color2);
        try {
            defaultStyledDocument.insertString(n++, "u", addStyle11);
        }
        catch (BadLocationException ex11) {}
        final Style addStyle12 = defaultStyledDocument.addStyle("green18", addStyle11);
        StyleConstants.setFontSize(addStyle12, 18);
        StyleConstants.setUnderline(addStyle12, true);
        StyleConstants.setForeground(addStyle12, color2);
        try {
            defaultStyledDocument.insertString(n++, "m", addStyle12);
        }
        catch (BadLocationException ex12) {}
        final Style addStyle13 = defaultStyledDocument.addStyle("green20", addStyle12);
        StyleConstants.setFontSize(addStyle13, 20);
        StyleConstants.setUnderline(addStyle13, true);
        StyleConstants.setForeground(addStyle13, color2);
        try {
            defaultStyledDocument.insertString(n++, "e", addStyle13);
        }
        catch (BadLocationException ex13) {}
        final Style addStyle14 = defaultStyledDocument.addStyle("green22", addStyle13);
        StyleConstants.setFontSize(addStyle14, 22);
        StyleConstants.setUnderline(addStyle14, true);
        StyleConstants.setForeground(addStyle14, color2);
        try {
            defaultStyledDocument.insertString(n++, "n", addStyle14);
        }
        catch (BadLocationException ex14) {}
        final Style addStyle15 = defaultStyledDocument.addStyle("green24", addStyle14);
        StyleConstants.setFontSize(addStyle15, 24);
        StyleConstants.setUnderline(addStyle15, true);
        StyleConstants.setForeground(addStyle15, color2);
        try {
            defaultStyledDocument.insertString(n++, "t", addStyle15);
        }
        catch (BadLocationException ex15) {}
        return defaultStyledDocument;
    }
    
    private JPopupMenu createSBPopup() {
        if (this.sbPopup != null) {
            return this.sbPopup;
        }
        final SBPopupAction sbPopupAction = new SBPopupAction();
        this.sbPopup = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem("Absolute Color");
        menuItem.setActionCommand("1");
        menuItem.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem);
        this.sbPopup.addSeparator();
        final JMenuItem menuItem2 = new JMenuItem("Derive from Main Color");
        menuItem2.setActionCommand("2");
        menuItem2.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Derive from Back Color");
        menuItem3.setActionCommand("3");
        menuItem3.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Derive from Disabled Color");
        menuItem4.setActionCommand("4");
        menuItem4.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem4);
        final JMenuItem menuItem5 = new JMenuItem("Derive from Frame Color");
        menuItem5.setActionCommand("5");
        menuItem5.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem5);
        final JMenuItem menuItem6 = new JMenuItem("Derive from Sub1 Color");
        menuItem6.setActionCommand("6");
        menuItem6.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem6);
        final JMenuItem menuItem7 = new JMenuItem("Derive from Sub2 Color");
        menuItem7.setActionCommand("7");
        menuItem7.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem7);
        final JMenuItem menuItem8 = new JMenuItem("Derive from Sub3 Color");
        menuItem8.setActionCommand("8");
        menuItem8.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem8);
        final JMenuItem menuItem9 = new JMenuItem("Derive from Sub4 Color");
        menuItem9.setActionCommand("9");
        menuItem9.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem9);
        final JMenuItem menuItem10 = new JMenuItem("Derive from Sub5 Color");
        menuItem10.setActionCommand("10");
        menuItem10.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem10);
        final JMenuItem menuItem11 = new JMenuItem("Derive from Sub6 Color");
        menuItem11.setActionCommand("11");
        menuItem11.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem11);
        final JMenuItem menuItem12 = new JMenuItem("Derive from Sub7 Color");
        menuItem12.setActionCommand("12");
        menuItem12.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem12);
        final JMenuItem menuItem13 = new JMenuItem("Derive from Sub8 Color");
        menuItem13.setActionCommand("13");
        menuItem13.addActionListener(sbPopupAction);
        this.sbPopup.add(menuItem13);
        return this.sbPopup;
    }
    
    private void updateSBPopupIcons() {
        final MenuElement[] subElements = this.sbPopup.getSubElements();
        ((JMenuItem)subElements[0]).setIcon(ColorReference.getAbsoluteIcon());
        ((JMenuItem)subElements[1]).setIcon(Theme.mainColor[Theme.style].getIcon());
        ((JMenuItem)subElements[2]).setIcon(Theme.backColor[Theme.style].getIcon());
        ((JMenuItem)subElements[3]).setIcon(Theme.disColor[Theme.style].getIcon());
        ((JMenuItem)subElements[4]).setIcon(Theme.frameColor[Theme.style].getIcon());
        ((JMenuItem)subElements[5]).setIcon(Theme.sub1Color[Theme.style].getIcon());
        ((JMenuItem)subElements[6]).setIcon(Theme.sub2Color[Theme.style].getIcon());
        ((JMenuItem)subElements[7]).setIcon(Theme.sub3Color[Theme.style].getIcon());
        ((JMenuItem)subElements[8]).setIcon(Theme.sub4Color[Theme.style].getIcon());
        ((JMenuItem)subElements[9]).setIcon(Theme.sub5Color[Theme.style].getIcon());
        ((JMenuItem)subElements[10]).setIcon(Theme.sub6Color[Theme.style].getIcon());
        ((JMenuItem)subElements[11]).setIcon(Theme.sub7Color[Theme.style].getIcon());
        ((JMenuItem)subElements[12]).setIcon(Theme.sub8Color[Theme.style].getIcon());
        for (int i = 0; i < 13; ++i) {
            ((JMenuItem)subElements[i]).setSelected(false);
        }
        for (int j = 5; j < 13; ++j) {
            ((JMenuItem)subElements[j]).setEnabled(true);
        }
    }
    
    private void showSBPopup(final SBField selectedSBField) {
        this.updateSBPopupIcons();
        this.selectedSBField = selectedSBField;
        final int n = selectedSBField.getColorReference().getReference() - 1;
        final MenuElement[] subElements = this.sbPopup.getSubElements();
        ((JMenuItem)subElements[n]).setSelected(true);
        if (selectedSBField.equals(this.sub1Field)) {
            ((JMenuItem)subElements[5]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub2Field)) {
            ((JMenuItem)subElements[6]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub3Field)) {
            ((JMenuItem)subElements[7]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub4Field)) {
            ((JMenuItem)subElements[8]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub5Field)) {
            ((JMenuItem)subElements[9]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub6Field)) {
            ((JMenuItem)subElements[10]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub7Field)) {
            ((JMenuItem)subElements[11]).setEnabled(false);
        }
        else if (selectedSBField.equals(this.sub8Field)) {
            ((JMenuItem)subElements[12]).setEnabled(false);
        }
        this.sbPopup.show(selectedSBField, 0, selectedSBField.getHeight() + 2);
    }
    
    private JPopupMenu createHSBPopup() {
        if (this.hsbPopup != null) {
            return this.hsbPopup;
        }
        final HSBPopupAction hsbPopupAction = new HSBPopupAction();
        this.hsbPopup = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem("Derive from Main Color");
        menuItem.setActionCommand("2");
        menuItem.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem);
        final JMenuItem menuItem2 = new JMenuItem("Derive from Back Color");
        menuItem2.setActionCommand("3");
        menuItem2.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Derive from Disabled Color");
        menuItem3.setActionCommand("4");
        menuItem3.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Derive from Frame Color");
        menuItem4.setActionCommand("5");
        menuItem4.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem4);
        final JMenuItem menuItem5 = new JMenuItem("Derive from Sub1 Color");
        menuItem5.setActionCommand("6");
        menuItem5.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem5);
        final JMenuItem menuItem6 = new JMenuItem("Derive from Sub2 Color");
        menuItem6.setActionCommand("7");
        menuItem6.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem6);
        final JMenuItem menuItem7 = new JMenuItem("Derive from Sub3 Color");
        menuItem7.setActionCommand("8");
        menuItem7.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem7);
        final JMenuItem menuItem8 = new JMenuItem("Derive from Sub4 Color");
        menuItem8.setActionCommand("9");
        menuItem8.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem8);
        final JMenuItem menuItem9 = new JMenuItem("Derive from Sub5 Color");
        menuItem9.setActionCommand("10");
        menuItem9.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem9);
        final JMenuItem menuItem10 = new JMenuItem("Derive from Sub6 Color");
        menuItem10.setActionCommand("11");
        menuItem10.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem10);
        final JMenuItem menuItem11 = new JMenuItem("Derive from Sub7 Color");
        menuItem11.setActionCommand("12");
        menuItem11.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem11);
        final JMenuItem menuItem12 = new JMenuItem("Derive from Sub8 Color");
        menuItem12.setActionCommand("13");
        menuItem12.addActionListener(hsbPopupAction);
        this.hsbPopup.add(menuItem12);
        return this.hsbPopup;
    }
    
    private void updateHSBPopupIcons() {
        final MenuElement[] subElements = this.hsbPopup.getSubElements();
        ((JMenuItem)subElements[0]).setIcon(Theme.mainColor[Theme.style].getIcon());
        ((JMenuItem)subElements[1]).setIcon(Theme.backColor[Theme.style].getIcon());
        ((JMenuItem)subElements[2]).setIcon(Theme.disColor[Theme.style].getIcon());
        ((JMenuItem)subElements[3]).setIcon(Theme.frameColor[Theme.style].getIcon());
        ((JMenuItem)subElements[4]).setIcon(Theme.sub1Color[Theme.style].getIcon());
        ((JMenuItem)subElements[5]).setIcon(Theme.sub2Color[Theme.style].getIcon());
        ((JMenuItem)subElements[6]).setIcon(Theme.sub3Color[Theme.style].getIcon());
        ((JMenuItem)subElements[7]).setIcon(Theme.sub4Color[Theme.style].getIcon());
        ((JMenuItem)subElements[8]).setIcon(Theme.sub5Color[Theme.style].getIcon());
        ((JMenuItem)subElements[9]).setIcon(Theme.sub6Color[Theme.style].getIcon());
        ((JMenuItem)subElements[10]).setIcon(Theme.sub7Color[Theme.style].getIcon());
        ((JMenuItem)subElements[11]).setIcon(Theme.sub8Color[Theme.style].getIcon());
        for (int i = 0; i < 12; ++i) {
            ((JMenuItem)subElements[i]).setSelected(false);
        }
        for (int j = 4; j < 12; ++j) {
            ((JMenuItem)subElements[j]).setEnabled(true);
        }
    }
    
    private void showHSBPopup(final HSBField selectedHSBField) {
        this.updateHSBPopupIcons();
        this.selectedHSBField = selectedHSBField;
        final int n = selectedHSBField.getReference() - 2;
        final MenuElement[] subElements = this.hsbPopup.getSubElements();
        ((JMenuItem)subElements[n]).setSelected(true);
        if (selectedHSBField.equals(this.sub1Field)) {
            ((JMenuItem)subElements[4]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub2Field)) {
            ((JMenuItem)subElements[5]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub3Field)) {
            ((JMenuItem)subElements[6]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub4Field)) {
            ((JMenuItem)subElements[7]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub5Field)) {
            ((JMenuItem)subElements[8]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub6Field)) {
            ((JMenuItem)subElements[9]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub7Field)) {
            ((JMenuItem)subElements[10]).setEnabled(false);
        }
        else if (selectedHSBField.equals(this.sub8Field)) {
            ((JMenuItem)subElements[11]).setEnabled(false);
        }
        this.hsbPopup.show(selectedHSBField, 0, selectedHSBField.getHeight() + 2);
    }
    
    private JPanel createFontPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        panel.add(this.plainFontPanel = new FontPanel(1), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(this.boldFontPanel = new FontPanel(2), gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.insets = new Insets(11, 2, 0, 2);
        final JPanel panel2 = new JPanel(new FlowLayout(0, 0, 0));
        panel2.add(this.createFontCombo());
        panel2.add(new JLabel("    "));
        (this.isPlainFont = new JRadioButton("is Plain Font")).addActionListener(new DerivedFontAction());
        panel2.add(this.isPlainFont);
        panel2.add(new JLabel("    "));
        (this.isBoldFont = new JRadioButton("is Bold Font")).addActionListener(new DerivedFontAction());
        panel2.add(this.isBoldFont);
        panel.add(panel2, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.insets = new Insets(2, 2, 0, 2);
        (this.specialFontPanel = new FontPanel(3)).init(this.selectedFont[Theme.style]);
        panel.add(this.specialFontPanel, gridBagConstraints);
        return panel;
    }
    
    private JComboBox createFontCombo() {
        final Vector<String> vector = new Vector<String>();
        vector.add("Button Font");
        vector.add("CheckBox Font");
        vector.add("ComboBox Font");
        vector.add("EditorPane Font");
        vector.add("FrameTitle Font");
        vector.add("InternalFrameTitle Font");
        vector.add("InternalPaletteTitle Font");
        vector.add("Label Font");
        vector.add("List Font");
        vector.add("Menu Font");
        vector.add("MenuItem Font");
        vector.add("Password Font");
        vector.add("ProgressBar Font");
        vector.add("RadioButton Font");
        vector.add("Table Font");
        vector.add("TableHeader Font");
        vector.add("TextArea Font");
        vector.add("TextField Font");
        vector.add("TextPane Font");
        vector.add("TitledBorder Font");
        vector.add("ToolTip Font");
        vector.add("Tree Font");
        vector.add("TabbedPane Font");
        Collections.sort(vector);
        (this.fontCombo = new JComboBox((Vector<E>)vector)).addActionListener(new SelectSpecialFontAction());
        this.selectedFont = Theme.buttonFont;
        return this.fontCombo;
    }
    
    private JTabbedPane createDecorationPanel() {
        this.compTab = new JTabbedPane();
        this.buttonsCP = new ButtonsCP();
        this.compTab.add("Button", this.buttonsCP);
        this.compTab.setMnemonicAt(0, 66);
        this.compTab.setToolTipTextAt(0, "<html>JButton<br>JToggleButton<br>JRadioButton<br>JCheckBox");
        this.comboCP = new ComboCP();
        this.compTab.add("ComboBox", this.comboCP);
        this.compTab.setMnemonicAt(1, 67);
        this.frameCP = new FrameCP();
        this.compTab.add("Frame", this.frameCP);
        this.compTab.setMnemonicAt(2, 70);
        this.compTab.setToolTipTextAt(2, "<html>JFrame<br>JInternalFrame<br>JDialog<br>JOptionPane");
        this.iconCP = new IconCP();
        this.compTab.add("Icons", this.iconCP);
        this.compTab.setMnemonicAt(3, 73);
        this.listCP = new ListCP();
        this.compTab.add("List", this.listCP);
        this.compTab.setMnemonicAt(4, 76);
        this.menuCP = new MenuCP();
        this.compTab.add("Menu", this.menuCP);
        this.compTab.setMnemonicAt(5, 77);
        this.compTab.setToolTipTextAt(5, "<html>JMenu<br>JMenuItem<br>JCheckBoxMenuItem<br>JRadioButtonMenuItem");
        this.miscCP = new MiscCP();
        this.compTab.add("Miscellaneous", this.miscCP);
        this.progressCP = new ProgressCP();
        this.compTab.add("ProgressBar", this.progressCP);
        this.compTab.setMnemonicAt(7, 80);
        this.scrollsCP = new ScrollBarCP();
        this.compTab.add("ScrollBar", this.scrollsCP);
        this.compTab.setMnemonicAt(8, 83);
        this.compTab.setToolTipTextAt(8, "<html>JScrollPane<br>JScrollBar");
        this.separatorCP = new SeparatorCP();
        this.compTab.add("Separator", this.separatorCP);
        this.sliderCP = new SliderCP();
        this.compTab.add("Slider", this.sliderCP);
        this.spinnerCP = new SpinnerCP();
        this.compTab.add("Spinner", this.spinnerCP);
        this.tabsCP = new TabbedPaneCP();
        this.compTab.add("TabbedPane", this.tabsCP);
        this.tableCP = new TableCP();
        this.compTab.add("Table", this.tableCP);
        this.textCP = new TextCP();
        this.compTab.add("Text", this.textCP);
        this.compTab.setToolTipTextAt(14, "<html>JTextField<br>JFormattedTextField<br>JTextArea<br>JPasswordField<br>JSpinner.Editor<br>JComboBox.Editor");
        this.toolCP = new ToolBarCP();
        this.compTab.add("ToolBar", this.toolCP);
        this.compTab.setToolTipTextAt(15, "<html>JToolBar<br>ToolBar Button<br>JToolBar.Separator");
        this.tipCP = new ToolTipCP();
        this.compTab.add("ToolTip", this.tipCP);
        this.treeCP = new TreeCP();
        this.compTab.add("Tree", this.treeCP);
        return this.compTab;
    }
    
    private JMenu createFileMenu() {
        final JMenu menu = new JMenu("File");
        menu.setMnemonic(70);
        final JMenuItem menuItem = new JMenuItem("Open Theme...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.openTheme();
            }
        });
        menuItem.setMnemonic(79);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(79, ControlPanel.menuShortcutKeyMask));
        menu.add(menuItem);
        menu.addSeparator();
        final JMenuItem menuItem2 = new JMenuItem("Save");
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.saveTheme(false);
            }
        });
        menuItem2.setMnemonic(83);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(83, ControlPanel.menuShortcutKeyMask));
        menu.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Save as Default");
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.saveDefaults();
            }
        });
        menuItem3.setMnemonic(68);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(68, ControlPanel.menuShortcutKeyMask));
        menu.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Save as...");
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.saveTheme(true);
            }
        });
        menuItem4.setMnemonic(65);
        menuItem4.setDisplayedMnemonicIndex(5);
        menuItem4.setAccelerator(KeyStroke.getKeyStroke(83, ControlPanel.menuShortcutKeyMask | 0x1));
        menu.add(menuItem4);
        menu.addSeparator();
        final JMenuItem menuItem5 = new JMenuItem("Quit");
        menuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menuItem5.setMnemonic(81);
        menuItem5.setAccelerator(KeyStroke.getKeyStroke(81, ControlPanel.menuShortcutKeyMask));
        menu.add(menuItem5);
        return menu;
    }
    
    private Vector loadThemes() {
        final Vector<String> vector = new Vector<String>();
        final File[] listFiles = new File(System.getProperty("user.dir")).listFiles();
        for (int i = 0; i < listFiles.length; ++i) {
            if (listFiles[i].getName().endsWith(".theme")) {
                vector.add(listFiles[i].getName());
            }
        }
        return vector;
    }
    
    private JMenu createThemeMenu() {
        (this.themesMenu = new JMenu("Themes")).setMnemonic(84);
        for (final String s : this.loadThemes()) {
            final JMenuItem menuItem = new JMenuItem(s.substring(0, s.lastIndexOf(".")));
            menuItem.addActionListener(this.selectThemeAction);
            this.themesMenu.add(menuItem);
        }
        return this.themesMenu;
    }
    
    private void updateThemeMenu() {
        this.themesMenu.removeAll();
        for (final String s : this.loadThemes()) {
            final JMenuItem menuItem = new JMenuItem(s.substring(0, s.lastIndexOf(".")));
            menuItem.addActionListener(this.selectThemeAction);
            this.themesMenu.add(menuItem);
        }
    }
    
    private JMenu createStyleMenu() {
        final JMenu menu = new JMenu("Style");
        menu.setMnemonic(83);
        final ButtonGroup buttonGroup = new ButtonGroup();
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Tiny", Theme.style == 0);
        buttonGroup.add(checkBoxMenuItem);
        checkBoxMenuItem.setEnabled(false);
        checkBoxMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.updateStyle(0);
            }
        });
        final JCheckBoxMenuItem checkBoxMenuItem2 = new JCheckBoxMenuItem("99 Style", Theme.style == 1);
        buttonGroup.add(checkBoxMenuItem2);
        checkBoxMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.updateStyle(1);
            }
        });
        menu.add(checkBoxMenuItem2);
        final JCheckBoxMenuItem checkBoxMenuItem3 = new JCheckBoxMenuItem("YQ Style", Theme.style == 2);
        buttonGroup.add(checkBoxMenuItem3);
        checkBoxMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.updateStyle(2);
            }
        });
        menu.add(checkBoxMenuItem3);
        (this.customStyle = new JCheckBoxMenuItem("Custom", false)).setEnabled(false);
        buttonGroup.add(this.customStyle);
        this.customStyle.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ControlPanel.this.updateStyle(3);
            }
        });
        menu.add(this.customStyle);
        return menu;
    }
    
    private JMenu createDisabledMenu() {
        final JMenu menu = new JMenu("DisabledMenu");
        menu.setEnabled(false);
        return menu;
    }
    
    private JMenu createTestMenu() {
        final JMenu menu = new JMenu("Test Menu");
        menu.setMnemonic(77);
        final JMenuItem menuItem = new JMenuItem("Open Dialog...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                new TestDialog(ControlPanel.this.theFrame);
            }
        });
        menu.add(menuItem);
        menu.addSeparator();
        menu.add(new JMenuItem("<html><b>Note: </b>For JMenuItems displaying HTML text<br><font color=\"#0000ff\">Decoration | Menu | Selected Foreground<br></font><font color=\"#000000\">will have </font><font color=\"#ff0000\">no</font><font color=\"#000000\"> effect."));
        menu.add(new JCheckBoxMenuItem("Selected CheckBoxMenuItem", true));
        menu.add(new JCheckBoxMenuItem("Deselected CheckBoxMenuItem", false));
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Disabled Selected CheckBoxMenuItem", true);
        checkBoxMenuItem.setEnabled(false);
        menu.add(checkBoxMenuItem);
        final JCheckBoxMenuItem checkBoxMenuItem2 = new JCheckBoxMenuItem("Disabled CheckBoxMenuItem", false);
        checkBoxMenuItem2.setEnabled(false);
        menu.add(checkBoxMenuItem2);
        menu.addSeparator();
        final ButtonGroup buttonGroup = new ButtonGroup();
        final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("Selected RadioButtonMenuItem", true);
        buttonGroup.add(radioButtonMenuItem);
        menu.add(radioButtonMenuItem);
        final JRadioButtonMenuItem radioButtonMenuItem2 = new JRadioButtonMenuItem("Deselected RadioButtonMenuItem");
        buttonGroup.add(radioButtonMenuItem2);
        menu.add(radioButtonMenuItem2);
        final JRadioButtonMenuItem radioButtonMenuItem3 = new JRadioButtonMenuItem("Disabled Selected Item", true);
        radioButtonMenuItem3.setEnabled(false);
        menu.add(radioButtonMenuItem3);
        final JRadioButtonMenuItem radioButtonMenuItem4 = new JRadioButtonMenuItem("Disabled Item", false);
        radioButtonMenuItem4.setEnabled(false);
        menu.add(radioButtonMenuItem4);
        menu.addSeparator();
        menu.add(new JMenuItem("Java version: " + System.getProperty("java.version")));
        final JMenuItem menuItem2 = new JMenuItem("Disabled MenuItem");
        menuItem2.setEnabled(false);
        menu.add(menuItem2);
        menu.addSeparator();
        final JMenu menu2 = new JMenu("Sub-menu 1");
        menu2.setMnemonic(49);
        final JMenuItem menuItem3 = new JMenuItem("Item 1");
        menuItem3.setMnemonic(49);
        menu2.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Item 2");
        menuItem4.setMnemonic(50);
        menu2.add(menuItem4);
        menu2.addSeparator();
        final JMenu menu3 = new JMenu("Disabled Submenu");
        menu3.setEnabled(false);
        menu3.add(new JMenuItem("SubmenuItem"));
        menu2.add(menu3);
        menu.add(menu2);
        return menu;
    }
    
    private JMenu createHelpMenu() {
        final JMenu menu = new JMenu("Help");
        menu.setMnemonic(72);
        final JMenuItem menuItem = new JMenuItem("About TinyLaF...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                new AboutDialog();
            }
        });
        menuItem.setMnemonic(65);
        menu.add(menuItem);
        menu.addSeparator();
        final JMenuItem menuItem2 = new JMenuItem("Check for Updates...");
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CheckForUpdatesDialog.showDialog(ControlPanel.this.theFrame);
            }
        });
        menuItem2.setMnemonic(67);
        menu.add(menuItem2);
        return menu;
    }
    
    private void addButtonIcons(final boolean b) {
        if (b && this.exampleButton.getIcon() == null) {
            if (this.buttonIcon == null) {
                this.buttonIcon = new ImageIcon(ClassLoader.getSystemResource("de/muntjak/tinylookandfeel/icons/theIcon.gif"));
            }
            this.exampleButton.setIcon(this.buttonIcon);
            this.exampleDisabledButton.setIcon(this.buttonIcon);
            this.exampleToggleButton.setIcon(this.buttonIcon);
        }
        else if (!b && this.exampleButton.getIcon() != null) {
            this.exampleButton.setIcon(null);
            this.exampleDisabledButton.setIcon(null);
            this.exampleToggleButton.setIcon(null);
        }
    }
    
    private void updateFont(final int n) {
        if (n == 1) {
            Theme.plainFont[Theme.style].setFont(this.plainFontPanel.getCurrentFont());
        }
        else if (n == 2) {
            Theme.boldFont[Theme.style].setFont(this.boldFontPanel.getCurrentFont());
        }
        else {
            this.selectedFont[Theme.style].setFont(this.specialFontPanel.getCurrentFont());
        }
        this.examplePanel.update(true);
    }
    
    private void updateSpecialFont() {
        switch (this.fontCombo.getSelectedIndex()) {
            case 0: {
                this.selectedFont = Theme.buttonFont;
                break;
            }
            case 1: {
                this.selectedFont = Theme.checkFont;
                break;
            }
            case 2: {
                this.selectedFont = Theme.comboFont;
                break;
            }
            case 3: {
                this.selectedFont = Theme.editorFont;
                break;
            }
            case 4: {
                this.selectedFont = Theme.frameTitleFont;
                break;
            }
            case 5: {
                this.selectedFont = Theme.internalFrameTitleFont;
                break;
            }
            case 6: {
                this.selectedFont = Theme.internalPaletteTitleFont;
                break;
            }
            case 7: {
                this.selectedFont = Theme.labelFont;
                break;
            }
            case 8: {
                this.selectedFont = Theme.listFont;
                break;
            }
            case 9: {
                this.selectedFont = Theme.menuFont;
                break;
            }
            case 10: {
                this.selectedFont = Theme.menuItemFont;
                break;
            }
            case 11: {
                this.selectedFont = Theme.passwordFont;
                break;
            }
            case 12: {
                this.selectedFont = Theme.progressBarFont;
                break;
            }
            case 13: {
                this.selectedFont = Theme.radioFont;
                break;
            }
            case 14: {
                this.selectedFont = Theme.tabFont;
                break;
            }
            case 15: {
                this.selectedFont = Theme.tableFont;
                break;
            }
            case 16: {
                this.selectedFont = Theme.tableHeaderFont;
                break;
            }
            case 17: {
                this.selectedFont = Theme.textAreaFont;
                break;
            }
            case 18: {
                this.selectedFont = Theme.textFieldFont;
                break;
            }
            case 19: {
                this.selectedFont = Theme.textPaneFont;
                break;
            }
            case 20: {
                this.selectedFont = Theme.titledBorderFont;
                break;
            }
            case 21: {
                this.selectedFont = Theme.toolTipFont;
                break;
            }
            case 22: {
                this.selectedFont = Theme.treeFont;
                break;
            }
        }
        this.specialFontPanel.init(this.selectedFont[Theme.style]);
        Theme.buttonFontColor[Theme.style].update();
        Theme.labelFontColor[Theme.style].update();
        Theme.menuFontColor[Theme.style].update();
        Theme.menuItemFontColor[Theme.style].update();
        Theme.radioFontColor[Theme.style].update();
        Theme.checkFontColor[Theme.style].update();
        Theme.tableFontColor[Theme.style].update();
        Theme.tableHeaderFontColor[Theme.style].update();
        Theme.titledBorderFontColor[Theme.style].update();
        Theme.tabFontColor[Theme.style].update();
        if (Theme.toolTipFontColor[Theme.style] != null) {
            Theme.toolTipFontColor[Theme.style].update();
        }
    }
    
    private void setTheme() {
        this.updateTheme();
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        }
        catch (Exception ex) {
            System.err.println(ex.toString());
        }
        SwingUtilities.updateComponentTreeUI(this.theFrame);
        if (this.sbPopup != null) {
            SwingUtilities.updateComponentTreeUI(this.sbPopup);
        }
        if (this.hsbPopup != null) {
            SwingUtilities.updateComponentTreeUI(this.hsbPopup);
        }
        this.updateThemeButton.setEnabled(false);
        this.iconCP.init(true);
        this.trigger.updateColors();
        this.sp1.setViewportBorder(BorderFactory.createLineBorder(Theme.treeBgColor[Theme.style].getColor(), 2));
        this.sp2.setViewportBorder(BorderFactory.createLineBorder(Theme.treeBgColor[Theme.style].getColor(), 2));
        this.theFrame.pack();
        PSColorChooser.deleteInstance();
        SBChooser.deleteInstance();
        HSBChooser.deleteInstance();
    }
    
    private void updateTheme() {
        TinyDefaultTheme.secondary3 = Theme.backColor[Theme.style].getColor();
        this.updatePanels();
        this.updateSpecialFont();
    }
    
    private void updatePanels() {
        this.buttonsCP.updateTheme();
        this.scrollsCP.updateTheme();
        this.separatorCP.updateTheme();
        this.tabsCP.updateTheme();
        this.comboCP.updateTheme();
        this.listCP.updateTheme();
        this.sliderCP.updateTheme();
        this.spinnerCP.updateTheme();
        this.progressCP.updateTheme();
        this.menuCP.updateTheme();
        this.textCP.updateTheme();
        this.treeCP.updateTheme();
        this.toolCP.updateTheme();
        this.tableCP.updateTheme();
        this.frameCP.updateTheme();
        this.iconCP.updateTheme();
        this.tipCP.updateTheme();
        this.miscCP.updateTheme();
    }
    
    private void updateStyle(final int style) {
        this.stopProgressTimer();
        Theme.style = style;
        this.initColors();
        this.initPanels();
        this.setTheme();
        this.startProgressTimer();
    }
    
    private String getDescription() {
        if (Theme.style == 3) {
            return this.currentFileName;
        }
        if (Theme.style == 1) {
            return "99 Style";
        }
        if (Theme.style == 2) {
            return "YQ Style";
        }
        if (Theme.style == 0) {
            return "Tiny Style";
        }
        return "";
    }
    
    private void initPanels() {
        this.resistUpdate = true;
        this.buttonsCP.init(true);
        this.scrollsCP.init(true);
        this.separatorCP.init(true);
        this.tabsCP.init(true);
        this.comboCP.init(true);
        this.menuCP.init(true);
        this.listCP.init(true);
        this.sliderCP.init(true);
        this.spinnerCP.init(true);
        this.progressCP.init(true);
        this.textCP.init(true);
        this.treeCP.init(true);
        this.toolCP.init(true);
        this.tableCP.init(true);
        this.frameCP.init(true);
        this.iconCP.init(true);
        this.tipCP.init(true);
        this.miscCP.init(true);
        this.initFonts();
        this.resistUpdate = false;
        this.theFrame.setTitle("TinyLaF 1.3.8 Controlpanel - " + this.getDescription());
    }
    
    private void initColors() {
        this.mainField.setBackground(Theme.mainColor[Theme.style].getColor());
        this.rollField.setBackground(Theme.disColor[Theme.style].getColor());
        this.backField.setBackground(Theme.backColor[Theme.style].getColor());
        this.frameField.setBackground(Theme.frameColor[Theme.style].getColor());
        this.mainField.update();
        this.rollField.update();
        this.backField.update();
        this.frameField.update();
        this.sub1Field.update();
        this.sub2Field.update();
        this.sub3Field.update();
        this.sub4Field.update();
        this.sub5Field.update();
        this.sub6Field.update();
        this.sub7Field.update();
        this.sub8Field.update();
    }
    
    private void initFonts() {
        this.plainFontPanel.init(Theme.plainFont[Theme.style]);
        this.boldFontPanel.init(Theme.boldFont[Theme.style]);
        this.updateSpecialFont();
    }
    
    private void repaintTargets(final Component[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i].repaint();
        }
    }
    
    public static void setWindowButtons(final JButton[] windowButtons) {
        ControlPanel.windowButtons = windowButtons;
    }
    
    private void openTheme() {
        final JFileChooser fileChooser = new JFileChooser(ControlPanel.directoryPath);
        fileChooser.setFileFilter(new ThemeFileFilter());
        if (fileChooser.showOpenDialog(this.theFrame) != 0) {
            return;
        }
        final File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        if (!Theme.loadTheme(selectedFile, 3)) {
            JOptionPane.showMessageDialog(this.theFrame, "This file is no valid TinyLaF theme.", "Error loading file", 0);
            return;
        }
        this.currentFileName = selectedFile.getAbsolutePath();
        if (selectedFile.getParent() != null) {
            ControlPanel.directoryPath = selectedFile.getParent();
        }
        this.customStyle.setEnabled(true);
        this.customStyle.setSelected(true);
        this.updateStyle(3);
    }
    
    private void openTheme(final String currentFileName) {
        if (!Theme.loadTheme(new File(currentFileName), 3)) {
            JOptionPane.showMessageDialog(this.theFrame, "This file is no valid TinyLaF theme.", "Error loading file", 0);
            return;
        }
        this.currentFileName = currentFileName;
        this.customStyle.setEnabled(true);
        this.customStyle.setSelected(true);
        this.updateStyle(3);
    }
    
    private void saveTheme(final boolean b) {
        if (this.currentFileName != null && !b && Theme.style == 3) {
            Theme.saveTheme(this.currentFileName);
            this.updateThemeMenu();
            return;
        }
        final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new ThemeFileFilter());
        fileChooser.setSelectedFile(new File(System.getProperty("user.dir") + File.separator + "Untitled.theme"));
        if (fileChooser.showSaveDialog(this.theFrame) == 1) {
            return;
        }
        final File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        Theme.saveTheme(this.currentFileName = this.createFileExtension(selectedFile, ".theme"));
        this.updateThemeMenu();
        if (Theme.style != 3) {
            this.openTheme(this.currentFileName);
        }
        this.theFrame.setTitle("TinyLaF 1.3.8 Controlpanel - " + this.getDescription());
    }
    
    private String createFileExtension(final File file, final String s) {
        final String absolutePath = file.getAbsolutePath();
        if (absolutePath.endsWith(s)) {
            return absolutePath;
        }
        if (absolutePath.lastIndexOf(".") < absolutePath.lastIndexOf(File.separator)) {
            return absolutePath + s;
        }
        return absolutePath.substring(0, absolutePath.lastIndexOf(".")) + s;
    }
    
    private void saveDefaults() {
        Theme.saveTheme("Default.theme");
        this.updateThemeMenu();
    }
    
    public static void main(final String[] array) {
        TinyLookAndFeel.controlPanelInstantiated = true;
        try {
            UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ControlPanel();
            }
        });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ControlPanel.directoryPath = System.getProperty("user.dir");
        sbFieldBorder = new LineBorder(Color.DARK_GRAY, 1);
        infoColor = new Color(208, 239, 255);
        infoBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(108, 108, 147)), BorderFactory.createEmptyBorder(3, 3, 3, 3));
        menuShortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    }
    
    private class AboutDialog extends JDialog
    {
        AboutDialog() {
            super(ControlPanel.this.theFrame, "About TinyLaF", true);
            this.setDefaultCloseOperation(2);
            final String s = "<html>TinyLaF v1.3.8 (2007-6-17)<br>Author: Hans Bickel<br>TinyLaF Home: www.muntjak.de/hans/java/tinylaf/";
            this.getContentPane().setLayout(new BorderLayout());
            final JPanel panel = new JPanel(new FlowLayout(1, 12, 8));
            panel.add(new JLabel(s));
            this.getContentPane().add(panel, "Center");
            final JPanel panel2 = new JPanel(new FlowLayout(1, 8, 10));
            final JButton button = new JButton("Copy Link");
            button.addActionListener(new ActionListener() {
                private final /* synthetic */ AboutDialog this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    if (systemClipboard == null) {
                        JOptionPane.showMessageDialog(this.this$1, "System Clipboard not available.", "Error", 0);
                    }
                    else {
                        final StringSelection stringSelection = new StringSelection("http://www.muntjak.de/hans/java/tinylaf/");
                        systemClipboard.setContents(stringSelection, stringSelection);
                    }
                }
            });
            panel2.add(button);
            final JButton defaultButton = new JButton("Close");
            this.getRootPane().setDefaultButton(defaultButton);
            defaultButton.addActionListener(new ActionListener() {
                private final /* synthetic */ AboutDialog this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.dispose();
                }
            });
            panel2.add(defaultButton);
            this.getContentPane().add(panel2, "South");
            this.pack();
            final Point locationOnScreen;
            final Point location = locationOnScreen = ControlPanel.this.theFrame.getLocationOnScreen();
            locationOnScreen.x += (ControlPanel.this.theFrame.getWidth() - this.getWidth()) / 2;
            final Point point = location;
            point.y += (ControlPanel.this.theFrame.getHeight() - this.getHeight()) / 2;
            this.setLocation(location);
            this.setVisible(true);
        }
    }
    
    private class SmallTableModel extends AbstractTableModel
    {
        String[] columnNames;
        
        private SmallTableModel() {
            this.columnNames = new String[] { "C1", "C2", "C3", "C4" };
        }
        
        public int getRowCount() {
            return 50;
        }
        
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public Class getColumnClass(final int n) {
            return (ControlPanel.class$java$lang$Integer == null) ? (ControlPanel.class$java$lang$Integer = ControlPanel.class$("java.lang.Integer")) : ControlPanel.class$java$lang$Integer;
        }
        
        public Object getValueAt(final int n, final int n2) {
            return new Integer(n * this.getColumnCount() + n2 + 1);
        }
    }
    
    class PopupTrigger extends JPanel implements MouseListener
    {
        JLabel label;
        
        PopupTrigger() {
            this.setLayout(new FlowLayout(1, 4, 2));
            this.setBackground(Color.ORANGE);
            (this.label = new JLabel("Popup trigger")).setForeground(Color.BLUE);
            this.add(this.label);
            this.addMouseListener(this);
        }
        
        void updateColors() {
            this.label.setForeground(Color.BLUE);
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (ControlPanel.this.thePopup != null && ControlPanel.this.thePopup.isShowing()) {
                return;
            }
            ControlPanel.this.thePopup = new JPopupMenu("Popup Menu");
            ControlPanel.this.thePopup.add(new JMenuItem("Popup item #1"));
            ControlPanel.this.thePopup.add(new JMenuItem("Popup item #2"));
            ControlPanel.this.thePopup.addSeparator();
            ControlPanel.this.thePopup.add(new JMenuItem("Popup item #3"));
            final JMenuItem menuItem = new JMenuItem("Popup disabled item");
            menuItem.setEnabled(false);
            ControlPanel.this.thePopup.add(menuItem);
            ControlPanel.this.thePopup.show(mouseEvent.getComponent(), 0, -ControlPanel.this.thePopup.getPreferredSize().height - 1);
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
    }
    
    class SpinnerUpdateAction implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            ControlPanel.this.updateThemeButton.setEnabled(true);
        }
    }
    
    class UpdateAction implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            ControlPanel.this.examplePanel.update(true);
        }
    }
    
    class ExamplePanel extends JPanel
    {
        private JTabbedPane exampleTb;
        private final /* synthetic */ ControlPanel this$0;
        
        ExamplePanel() {
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new BorderLayout());
            final JPanel panel = new JPanel(new BorderLayout(4, 0));
            final JPanel panel2 = new JPanel(new GridLayout(2, 2, 4, 4));
            final JPanel panel3 = new JPanel(new BorderLayout(4, 4));
            ControlPanel.this.scrollBars = new Component[7];
            final JScrollPane scrollPane = new JScrollPane(new SizedPanel(70, 130), 22, 32);
            scrollPane.setPreferredSize(new Dimension(96, 96));
            scrollPane.getVerticalScrollBar().setUnitIncrement(4);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(4);
            panel2.add(scrollPane);
            ControlPanel.this.scrollBars[0] = scrollPane;
            final JScrollPane scrollPane2 = new JScrollPane(new SizedPanel(130, 130), 22, 32);
            scrollPane2.setPreferredSize(new Dimension(96, 96));
            scrollPane2.getVerticalScrollBar().setUnitIncrement(4);
            scrollPane2.getHorizontalScrollBar().setUnitIncrement(4);
            panel2.add(scrollPane2);
            ControlPanel.this.scrollBars[1] = scrollPane2;
            final JList list = this.createList();
            list.setSelectedIndex(1);
            list.setVisibleRowCount(4);
            final JScrollPane scrollPane3 = new JScrollPane(list, 20, 30);
            panel2.add(scrollPane3);
            ControlPanel.this.scrollBars[2] = scrollPane3;
            final JPanel panel4 = new JPanel(new GridLayout(3, 1));
            panel4.add(new JTextArea("  JTextArea\n  enabled"));
            final JTextArea textArea = new JTextArea("  JTextArea\n  non-editable");
            textArea.setEditable(false);
            panel4.add(textArea);
            final JTextArea textArea2 = new JTextArea("  JTextArea\n  disabled");
            textArea2.setEnabled(false);
            panel4.add(textArea2);
            final JScrollPane scrollPane4 = new JScrollPane(panel4, 22, 30);
            panel2.add(scrollPane4);
            ControlPanel.this.scrollBars[3] = scrollPane4;
            panel3.add(panel2, "Center");
            final JTextPane textPane = new JTextPane(ControlPanel.this.createStyledDocument());
            textPane.setEditable(false);
            final JPanel panel5 = new JPanel(new FlowLayout(1, 0, 3));
            panel5.add(textPane);
            panel3.add(panel5, "South");
            panel.add(panel3, "West");
            final JPanel panel6 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(0, 2, 4, 2);
            ControlPanel.this.exampleButton = new JButton("JButton");
            ControlPanel.this.exampleButton.setToolTipText("Enabled JButton");
            ControlPanel.this.buttons[0] = ControlPanel.this.exampleButton;
            panel6.add(ControlPanel.this.exampleButton, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            ControlPanel.this.exampleDisabledButton = new JButton("Disabled");
            ControlPanel.this.exampleDisabledButton.setToolTipText("Disabled JButton");
            ControlPanel.this.buttons[1] = ControlPanel.this.exampleDisabledButton;
            ControlPanel.this.exampleDisabledButton.setEnabled(false);
            panel6.add(ControlPanel.this.exampleDisabledButton, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            ControlPanel.this.exampleToggleButton = new JToggleButton("JToggleButton");
            ControlPanel.this.buttons[2] = ControlPanel.this.exampleToggleButton;
            panel6.add(ControlPanel.this.exampleToggleButton, gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridx;
            final JCheckBox checkBox = new JCheckBox("Buttons w/icon", false);
            ((AbstractButton)(ControlPanel.this.buttons[3] = checkBox)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.addButtonIcons(((AbstractButton)actionEvent.getSource()).isSelected());
                }
            });
            panel6.add(checkBox, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 0, 2);
            panel6.add(ControlPanel.this.buttons[4] = new JCheckBox("JCheckBox", false), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridx;
            final JCheckBox checkBox2 = new JCheckBox("Disabled", true);
            ((AbstractButton)(ControlPanel.this.buttons[5] = checkBox2)).setEnabled(false);
            panel6.add(checkBox2, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            panel6.add(ControlPanel.this.buttons[6] = new JRadioButton("JRadioButton"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridx;
            final JRadioButton radioButton = new JRadioButton("Disabled", true);
            ((AbstractButton)(ControlPanel.this.buttons[7] = radioButton)).setEnabled(false);
            panel6.add(radioButton, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.insets = new Insets(4, 2, 4, 2);
            panel6.add(new JSeparator(), gridBagConstraints);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 0;
            ControlPanel.this.combos = new Component[4];
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 4, 2);
            panel6.add(ControlPanel.this.combos[0] = this.createCombo("JComboBox"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridx;
            final JComboBox combo = this.createCombo("Disabled Combo");
            ((JComboBox)(ControlPanel.this.combos[1] = combo)).setEnabled(false);
            panel6.add(combo, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 1, 2);
            final JComboBox combo2 = this.createCombo("Editable JComboBox");
            ((JComboBox)(ControlPanel.this.combos[2] = combo2)).setEditable(true);
            panel6.add(combo2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridx;
            final JComboBox combo3 = this.createCombo("Disabled Editable");
            ((JComboBox)(ControlPanel.this.combos[3] = combo3)).setEditable(true);
            combo3.setEnabled(false);
            panel6.add(combo3, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.insets = new Insets(4, 2, 4, 2);
            panel6.add(new JSeparator(), gridBagConstraints);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 4, 2);
            panel6.add(new JTextField("JTextField"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridx;
            final JTextField textField = new JTextField("Disabled");
            textField.setEnabled(false);
            panel6.add(textField, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            final JTextField textField2 = new JTextField("Non-editable Textfield");
            textField2.setEditable(false);
            panel6.add(textField2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridx;
            final JTextField textField3 = new JTextField("Disabled non-editable");
            textField3.setEditable(false);
            textField3.setEnabled(false);
            panel6.add(textField3, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            panel6.add(new JFormattedTextField((Object)"JFormattedTextField"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridx;
            final JFormattedTextField formattedTextField = new JFormattedTextField((Object)"Disabled");
            formattedTextField.setEditable(false);
            formattedTextField.setEnabled(false);
            panel6.add(formattedTextField, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 1, 2);
            panel6.add(new JPasswordField("JPasswordField"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridx;
            final JPasswordField passwordField = new JPasswordField("Disabled");
            passwordField.setEnabled(false);
            panel6.add(passwordField, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.insets = new Insets(4, 2, 4, 2);
            panel6.add(new JSeparator(), gridBagConstraints);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 0;
            ControlPanel.this.spinners = new Component[4];
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(0, 2, 2, 2);
            final JPanel panel7 = new JPanel(new FlowLayout(0, 0, 0));
            panel7.add(ControlPanel.this.spinners[0] = new JSpinner(new SpinnerNumberModel(42, 0, 99, 1)));
            panel7.add(new JLabel(" "));
            panel7.add(ControlPanel.this.spinners[1] = new JSpinner(new SpinnerDateModel()));
            panel6.add(panel7, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridx;
            final JPanel panel8 = new JPanel(new FlowLayout(0, 0, 0));
            final JSpinner spinner = new JSpinner(new SpinnerNumberModel(42, 0, 42, 1));
            ((JComponent)(ControlPanel.this.spinners[2] = spinner)).setEnabled(false);
            panel8.add(spinner);
            panel8.add(new JLabel(" "));
            final JSpinner spinner2 = new JSpinner(new SpinnerDateModel(new Date(), null, null, 7));
            ((JComponent)(ControlPanel.this.spinners[3] = spinner2)).setEnabled(false);
            panel8.add(spinner2);
            panel6.add(panel8, gridBagConstraints);
            final JPanel panel9 = new JPanel(new FlowLayout(0, 0, 0));
            panel9.add(panel6);
            panel.add(panel9, "Center");
            final JPanel panel10 = new JPanel(new BorderLayout());
            final JPanel panel11 = new JPanel(new GridLayout(1, 2));
            ControlPanel.this.tree1 = new JTree();
            ControlPanel.this.tree1.setCellRenderer(new SwitchTreeIcons(true));
            ControlPanel.this.tree1.setEditable(true);
            ControlPanel.this.tree1.expandPath(ControlPanel.this.tree1.getNextMatch("colors", 0, Position.Bias.Forward));
            ControlPanel.this.tree1.expandPath(ControlPanel.this.tree1.getNextMatch("food", 0, Position.Bias.Forward));
            ControlPanel.this.tree1.setVisibleRowCount(10);
            ControlPanel.this.sp1 = new JScrollPane(ControlPanel.this.tree1, 20, 30);
            ControlPanel.this.sp1.setViewportBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            panel11.add(ControlPanel.this.sp1);
            ControlPanel.this.scrollBars[4] = ControlPanel.this.sp1;
            ControlPanel.this.tree2 = new JTree();
            ControlPanel.this.tree2.setCellRenderer(new SwitchTreeIcons(true));
            final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)ControlPanel.this.tree2.getModel().getRoot();
            defaultMutableTreeNode.setUserObject("JTree disabled");
            ControlPanel.this.tree2.getModel().valueForPathChanged(new TreePath(defaultMutableTreeNode.getPath()), "JTree disabled");
            ControlPanel.this.tree2.expandPath(ControlPanel.this.tree2.getNextMatch("sports", 0, Position.Bias.Forward));
            ControlPanel.this.tree2.setEnabled(false);
            ControlPanel.this.tree2.setVisibleRowCount(10);
            ControlPanel.this.sp2 = new JScrollPane(ControlPanel.this.tree2, 20, 30);
            ControlPanel.this.sp2.setViewportBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            panel11.add(ControlPanel.this.sp2);
            ControlPanel.this.scrollBars[5] = ControlPanel.this.sp2;
            final JPanel panel12 = new JPanel(new BorderLayout(3, 0));
            panel12.add(panel11, "Center");
            final JPanel panel13 = new JPanel(new FlowLayout(1, 12, 3));
            final JCheckBox checkBox3 = new JCheckBox("Show Tree Icons", true);
            checkBox3.addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((SwitchTreeIcons)this.this$1.this$0.tree1.getCellRenderer()).setShowIcons(((AbstractButton)actionEvent.getSource()).isSelected());
                    ((SwitchTreeIcons)this.this$1.this$0.tree2.getCellRenderer()).setShowIcons(((AbstractButton)actionEvent.getSource()).isSelected());
                    this.this$1.this$0.tree1.revalidate();
                    this.this$1.this$0.tree2.revalidate();
                    this.this$1.repaint();
                }
            });
            panel13.add(checkBox3);
            ControlPanel.this.trigger = new PopupTrigger();
            panel13.add(ControlPanel.this.trigger);
            panel12.add(panel13, "North");
            panel12.add(new JSeparator(1), "West");
            final URL resource = this.getClass().getResource("/de/muntjak/tinylookandfeel/html/default.html");
            JEditorPane editorPane;
            try {
                editorPane = new JEditorPane(resource);
                editorPane.setEditable(false);
                editorPane.setPreferredSize(new Dimension(150, 70));
            }
            catch (IOException ex) {
                editorPane = new JEditorPane("text", "Plain Document");
            }
            final JPanel panel14 = new JPanel(new FlowLayout(1, 0, 3));
            panel14.add(editorPane);
            panel12.add(panel14, "South");
            panel10.add(panel12, "Center");
            final JPanel panel15 = new JPanel(new BorderLayout(8, 0));
            final JPanel panel16 = new JPanel(new GridBagLayout());
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 3;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(0, 12, 0, 2);
            ControlPanel.this.vertProgressBar = new JProgressBar(1, 0, 20);
            ControlPanel.this.vertProgressBar.setValue(0);
            ControlPanel.this.vertProgressBar.setStringPainted(true);
            ControlPanel.this.vertProgressBar.addMouseListener(new ProgressBarAction());
            ControlPanel.this.vertProgressBar.setToolTipText("Click to start/stop");
            panel16.add(ControlPanel.this.vertProgressBar, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridx;
            ControlPanel.this.vertSlider = new JSlider(1, 0, 100, 40);
            ControlPanel.this.vertSlider.setMajorTickSpacing(20);
            ControlPanel.this.vertSlider.setMinorTickSpacing(10);
            ControlPanel.this.vertSlider.setPaintTicks(true);
            ControlPanel.this.vertSlider.setPaintLabels(true);
            final Dimension preferredSize = ControlPanel.this.vertSlider.getPreferredSize();
            preferredSize.height = 183;
            ControlPanel.this.vertSlider.setPreferredSize(preferredSize);
            panel16.add(ControlPanel.this.vertSlider, gridBagConstraints);
            panel15.add(panel16, "West");
            final JPanel panel17 = new JPanel(new GridBagLayout());
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 0, 2, 0);
            ControlPanel.this.horzProgressBar = new JProgressBar(0, 20);
            ControlPanel.this.horzProgressBar.setValue(0);
            ControlPanel.this.horzProgressBar.setStringPainted(true);
            ControlPanel.this.horzProgressBar.addMouseListener(new ProgressBarAction());
            ControlPanel.this.horzProgressBar.setToolTipText("Click to start/stop");
            panel17.add(ControlPanel.this.horzProgressBar, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            ControlPanel.this.horzSlider = new JSlider(0, 0, 100, 40);
            final Dimension preferredSize2 = ControlPanel.this.horzSlider.getPreferredSize();
            ControlPanel.this.horzSlider.setPreferredSize(preferredSize2);
            preferredSize2.width = 183;
            panel17.add(ControlPanel.this.horzSlider, gridBagConstraints);
            panel15.add(panel17, "North");
            final JPanel panel18 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
            gridBagConstraints28.anchor = 17;
            gridBagConstraints28.fill = 2;
            gridBagConstraints28.gridx = 0;
            gridBagConstraints28.gridy = 0;
            gridBagConstraints28.insets = new Insets(0, 2, 0, 0);
            final JCheckBox checkBox4 = new JCheckBox("stringPainted", true);
            ((AbstractButton)(ControlPanel.this.buttons[22] = checkBox4)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.vertProgressBar.setStringPainted(((AbstractButton)actionEvent.getSource()).isSelected());
                    this.this$1.this$0.horzProgressBar.setStringPainted(((AbstractButton)actionEvent.getSource()).isSelected());
                }
            });
            panel18.add(checkBox4, gridBagConstraints28);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints28;
            ++gridBagConstraints29.gridy;
            final JCheckBox checkBox5 = new JCheckBox("Sliders Enabled", true);
            ((AbstractButton)(ControlPanel.this.buttons[23] = checkBox5)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (this.this$1.this$0.horzSlider.isEnabled()) {
                        this.this$1.this$0.horzSlider.setEnabled(false);
                        this.this$1.this$0.vertSlider.setEnabled(false);
                    }
                    else {
                        this.this$1.this$0.horzSlider.setEnabled(true);
                        this.this$1.this$0.vertSlider.setEnabled(true);
                    }
                }
            });
            panel18.add(checkBox5, gridBagConstraints28);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints28;
            ++gridBagConstraints30.gridy;
            gridBagConstraints28.insets = new Insets(6, 2, 0, 0);
            final JButton button = new JButton("MessageDialog");
            ((AbstractButton)(ControlPanel.this.buttons[8] = button)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.showMessageDialog();
                }
            });
            panel18.add(button, gridBagConstraints28);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints28;
            ++gridBagConstraints31.gridy;
            final JButton button2 = new JButton("ConfirmDialog");
            ((AbstractButton)(ControlPanel.this.buttons[9] = button2)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.showConfirmationDialog();
                }
            });
            panel18.add(button2, gridBagConstraints28);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints28;
            ++gridBagConstraints32.gridy;
            final JButton button3 = new JButton("WarningDialog");
            ((AbstractButton)(ControlPanel.this.buttons[10] = button3)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.showWarningDialog();
                }
            });
            panel18.add(button3, gridBagConstraints28);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints28;
            ++gridBagConstraints33.gridy;
            final JButton button4 = new JButton("ErrorDialog");
            ((AbstractButton)(ControlPanel.this.buttons[11] = button4)).addActionListener(new ActionListener() {
                private final /* synthetic */ ExamplePanel this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.showErrorDialog();
                }
            });
            panel18.add(button4, gridBagConstraints28);
            panel15.add(panel18, "Center");
            panel10.add(panel15, "East");
            panel.add(panel10, "East");
            final JPanel panel19 = new JPanel(new FlowLayout(0, 4, 4));
            panel19.setBorder(new EtchedBorder());
            panel19.add(panel);
            this.add(panel19, "North");
            ControlPanel.this.desktopPane = new DesktopPane();
            this.add(ControlPanel.this.desktopPane, "Center");
        }
        
        private JList createList() {
            return new JList((E[])new String[] { "A JList", "can have", "zero to", "many items", "and can be", "scrolled", "(or not)" });
        }
        
        private JComboBox createCombo(final String s) {
            return new JComboBox((E[])new String[] { s, "can have", "zero to", "many items", "and can be", "triggered", "many times" });
        }
        
        public void update(final boolean b) {
            ControlPanel.this.updateTheme();
            if (b) {
                ControlPanel.this.updateThemeButton.setEnabled(true);
            }
            this.repaint(0L);
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
        }
        
        class DesktopPane extends JDesktopPane
        {
            Dimension preferredSize;
            private final /* synthetic */ ExamplePanel this$1;
            
            DesktopPane() {
                this.preferredSize = new Dimension(780, 160);
                this.setupUI();
            }
            
            public Dimension getPreferredSize() {
                return this.preferredSize;
            }
            
            private void setupUI() {
                new JPanel().setBounds(0, 0, this.preferredSize.width, this.preferredSize.height);
                ControlPanel.this.exampleTable = new JTable(new TinyTableModel());
                ControlPanel.this.exampleTable.setRowSelectionAllowed(true);
                ControlPanel.this.exampleTable.setColumnSelectionAllowed(true);
                ControlPanel.this.exampleTable.setColumnSelectionInterval(2, 2);
                ControlPanel.this.exampleTable.setRowSelectionInterval(0, 2);
                ControlPanel.this.exampleTable.setDefaultRenderer((ControlPanel.class$de$muntjak$tinylookandfeel$controlpanel$NonSortableTableModel$TableColorIcon == null) ? (ControlPanel.class$de$muntjak$tinylookandfeel$controlpanel$NonSortableTableModel$TableColorIcon = ControlPanel.class$("de.muntjak.tinylookandfeel.controlpanel.NonSortableTableModel$TableColorIcon")) : ControlPanel.class$de$muntjak$tinylookandfeel$controlpanel$NonSortableTableModel$TableColorIcon, new IconRenderer());
                final JScrollPane scrollPane = new JScrollPane(ControlPanel.this.exampleTable);
                scrollPane.setBounds(10, 10, 192, 132);
                this.add(scrollPane, JDesktopPane.DEFAULT_LAYER);
                ControlPanel.this.scrollBars[6] = scrollPane;
                ExamplePanel.this.exampleTb = new JTabbedPane();
                ExamplePanel.this.exampleTb.add("Disabled", new ContentLabel());
                ExamplePanel.this.exampleTb.add("Tabbed", new ContentLabel());
                ExamplePanel.this.exampleTb.add("Pane", new ContentLabel());
                ExamplePanel.this.exampleTb.setEnabled(false);
                ExamplePanel.this.exampleTb.setPreferredSize(new Dimension(180, 60));
                ExamplePanel.this.exampleTb.setBounds(210, 40, 180, 60);
                this.add(ExamplePanel.this.exampleTb, JDesktopPane.DEFAULT_LAYER);
                ControlPanel.this.frames = new Component[2];
                ControlPanel.this.internalFrame = new JInternalFrame("InternalFrame", true, true, true, true);
                ControlPanel.this.frames[0] = ControlPanel.this.internalFrame;
                ControlPanel.this.internalFrame.updateUI();
                ControlPanel.this.internalFrame.setDefaultCloseOperation(0);
                ControlPanel.this.internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
                    private final /* synthetic */ DesktopPane this$2 = this$2;
                    
                    public void internalFrameClosing(final InternalFrameEvent internalFrameEvent) {
                        JOptionPane.showInternalMessageDialog(this.this$2.this$1.this$0.internalFrame, "This internal frame cannot be closed.");
                        internalFrameEvent.getInternalFrame().show();
                    }
                });
                ControlPanel.this.internalFrame.getContentPane().add(new SizedPanel(200, 100));
                ControlPanel.this.internalFrame.pack();
                final Dimension preferredSize = ControlPanel.this.internalFrame.getPreferredSize();
                ControlPanel.this.internalFrame.setBounds(400, 10, preferredSize.width, preferredSize.height);
                ControlPanel.this.internalFrame.show();
                this.add(ControlPanel.this.internalFrame, JDesktopPane.PALETTE_LAYER);
                ControlPanel.this.palette = new JInternalFrame("Palette", false, true, true, true);
                ControlPanel.this.frames[1] = ControlPanel.this.palette;
                ControlPanel.this.palette.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
                ControlPanel.this.palette.setDefaultCloseOperation(0);
                ControlPanel.this.palette.addInternalFrameListener(new InternalFrameAdapter() {
                    private final /* synthetic */ DesktopPane this$2 = this$2;
                    
                    public void internalFrameClosing(final InternalFrameEvent internalFrameEvent) {
                        JOptionPane.showInternalMessageDialog(this.this$2.this$1.this$0.palette, "This internal palette cannot be closed.");
                        internalFrameEvent.getInternalFrame().show();
                    }
                });
                final JPanel panel = new JPanel(new FlowLayout(1, 0, 0));
                final JButton defaultButton = new JButton("InternalMessageDialog");
                ((AbstractButton)(ControlPanel.this.buttons[12] = defaultButton)).addActionListener(new ActionListener() {
                    private final /* synthetic */ DesktopPane this$2 = this$2;
                    
                    public void actionPerformed(final ActionEvent actionEvent) {
                        JOptionPane.showInternalMessageDialog(this.this$2.this$1.this$0.palette, "Life is a while(true) loop.");
                    }
                });
                ControlPanel.this.palette.getRootPane().setDefaultButton(defaultButton);
                final JButton button = new JButton("InternalConfirmDialog ");
                ((AbstractButton)(ControlPanel.this.buttons[13] = button)).addActionListener(new ActionListener() {
                    private final /* synthetic */ DesktopPane this$2 = this$2;
                    
                    public void actionPerformed(final ActionEvent actionEvent) {
                        JOptionPane.showInternalConfirmDialog(this.this$2.this$1.this$0.palette, "Is programming art?");
                    }
                });
                final JPanel panel2 = new JPanel(new GridLayout(4, 1));
                panel2.add(new JLabel());
                panel2.add(defaultButton);
                panel2.add(new JLabel());
                panel2.add(button);
                panel.add(panel2);
                ControlPanel.this.palette.getContentPane().add(panel);
                ControlPanel.this.palette.setBounds(400 + ControlPanel.this.internalFrame.getWidth() + 12, 10, 180, 140);
                ControlPanel.this.palette.show();
                this.add(ControlPanel.this.palette, JDesktopPane.PALETTE_LAYER);
            }
        }
        
        class ContentLabel extends JLabel
        {
            ContentLabel() {
                super("Content");
                this.setOpaque(true);
                this.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
                this.setBackground(new Color(211, 225, 255));
            }
        }
        
        class SwitchTreeIcons extends DefaultTreeCellRenderer
        {
            private boolean showIcons;
            
            SwitchTreeIcons(final boolean showIcons) {
                this.showIcons = showIcons;
            }
            
            void setShowIcons(final boolean showIcons) {
                this.showIcons = showIcons;
            }
            
            public Icon getClosedIcon() {
                if (this.showIcons) {
                    return super.getClosedIcon();
                }
                return null;
            }
            
            public Icon getOpenIcon() {
                if (this.showIcons) {
                    return super.getOpenIcon();
                }
                return null;
            }
            
            public Icon getLeafIcon() {
                if (this.showIcons) {
                    return super.getLeafIcon();
                }
                return null;
            }
            
            public Icon getDisabledIcon() {
                if (this.showIcons) {
                    return super.getDisabledIcon();
                }
                return null;
            }
        }
    }
    
    class SizedPanel extends JPanel
    {
        private Dimension size;
        private Color grey;
        
        SizedPanel(final int n, final int n2) {
            this.grey = new Color(204, 204, 204);
            this.size = new Dimension(n, n2);
            this.setBackground(Color.WHITE);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            final int width = this.getWidth();
            final int height = this.getHeight();
            int n = 0;
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(this.grey);
            for (int i = 0; i < height; i += 8) {
                for (int j = 0; j < width; j += 16) {
                    graphics.fillRect(j + n, i, 8, 8);
                }
                if (n == 0) {
                    n = 8;
                }
                else {
                    n = 0;
                }
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
    }
    
    class ProgressBarAction extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (ControlPanel.this.progressTimer == null) {
                ControlPanel.this.startProgressTimer();
            }
            else if (ControlPanel.this.progressTimer.isRunning()) {
                ControlPanel.this.stopProgressTimer();
            }
            else {
                ControlPanel.this.startProgressTimer();
            }
        }
    }
    
    class CheckUpdateAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.examplePanel.update(true);
        }
    }
    
    class CheckAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.examplePanel.update(false);
        }
    }
    
    class DecorateFrameAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.decorateFrame(((AbstractButton)actionEvent.getSource()).isSelected());
        }
    }
    
    class CheckedIcon extends JPanel implements ActionListener
    {
        private HSBField field;
        private JLabel iconLabel;
        private JCheckBox check;
        private Icon icon;
        
        CheckedIcon(final boolean b, final HSBField field, final String toolTipText) {
            this.field = field;
            this.setLayout(new FlowLayout(0, 0, 0));
            (this.check = new JCheckBox("", b)).addActionListener(this);
            this.add(this.check);
            this.add(this.iconLabel = new JLabel(""));
            super.setToolTipText(toolTipText);
        }
        
        public void setIcon(final Icon icon) {
            this.icon = icon;
            this.iconLabel.setIcon(this.icon);
        }
        
        public void setSelected(final boolean selected) {
            this.check.setSelected(selected);
        }
        
        public boolean isSelected() {
            return this.check.isSelected();
        }
        
        public Icon getIcon() {
            return this.icon;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.iconCP.colorizeIcon(this.field, this.check.isSelected());
            ControlPanel.this.examplePanel.update(true);
        }
    }
    
    class HSBField extends JPanel
    {
        private ActionListener action;
        private int hue;
        private int bri;
        private int sat;
        private int reference;
        private boolean preserveGrey;
        private boolean forceUpdate;
        private Dimension size;
        private HSBReference[] ref;
        private int index;
        
        HSBField(final HSBReference[] ref, final ActionListener action, final int index) {
            this.sat = 25;
            this.forceUpdate = false;
            this.size = new Dimension(33, 16);
            this.ref = ref;
            this.action = action;
            this.index = index;
            this.hue = ref[Theme.style].getHue();
            this.sat = ref[Theme.style].getSaturation();
            this.bri = ref[Theme.style].getBrightness();
            this.preserveGrey = ref[Theme.style].isPreserveGrey();
            this.reference = ref[Theme.style].getReference();
            this.forceUpdate = true;
            this.setBorder(new LineBorder(Color.DARK_GRAY, 1));
            if (ref == null) {
                return;
            }
            this.update();
            this.addMouseListener(new Mousey());
        }
        
        public HSBReference getColorReference() {
            return this.ref[Theme.style];
        }
        
        public int getHue() {
            return this.hue;
        }
        
        public int getSaturation() {
            return this.sat;
        }
        
        public int getBrightness() {
            return this.bri;
        }
        
        public boolean isPreserveGrey() {
            return this.preserveGrey;
        }
        
        public void setPreserveGrey(final boolean preserveGrey) {
            this.preserveGrey = preserveGrey;
        }
        
        public int getReference() {
            return this.reference;
        }
        
        public void setReference(final int reference, final boolean b) {
            this.reference = reference;
            if (b) {
                this.hue = ColorRoutines.calculateHue(ColorReference.getReferenceColor(this.reference));
            }
            this.update();
            if (ControlPanel.this.iconChecks[this.index].isSelected()) {
                this.getAction().actionPerformed(new ActionEvent(this, 1001, ""));
            }
        }
        
        public void setHue(final int hue) {
            this.hue = hue;
        }
        
        public void setSaturation(final int sat) {
            this.sat = sat;
        }
        
        public void setBrightness(final int bri) {
            this.bri = bri;
        }
        
        public void update() {
            if (this.ref != null) {
                this.setBackground(this.calculateBackground(ColorReference.getReferenceColor(this.reference)));
            }
            this.repaint();
            this.updateTTT();
        }
        
        private Color calculateBackground(final Color color) {
            final float[] rgBtoHSB = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            rgBtoHSB[0] = (float)(this.hue / 360.0);
            return Color.getHSBColor(rgBtoHSB[0], rgBtoHSB[1], rgBtoHSB[2]);
        }
        
        public void calculateHue() {
            this.hue = ColorRoutines.calculateHue(this.ref[Theme.style].getReferenceColor());
        }
        
        private void updateTTT() {
            if (this.ref == null) {
                this.setToolTipText(null);
                return;
            }
            final StringBuffer sb = new StringBuffer();
            sb.append("H:" + this.hue);
            sb.append(" S:" + this.sat);
            sb.append(" B:" + this.bri);
            sb.append(" (" + this.ref[Theme.style].getReferenceString() + ")");
            this.setToolTipText(sb.toString());
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            if (this.ref == null) {
                graphics.setColor(Theme.backColor[Theme.style].getColor());
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                return;
            }
            super.paint(graphics);
            final int n = this.getWidth() - 17;
            int n2 = 255;
            graphics.setColor(Color.BLACK);
            graphics.drawLine(n - 1, 1, n - 1, this.getHeight() - 2);
            for (int i = 0; i < 16; ++i) {
                graphics.setColor(new Color(n2, n2, n2));
                graphics.drawLine(n + i, 1, n + i, this.getHeight() - 2);
                n2 -= 15;
            }
        }
        
        public ActionListener getAction() {
            return this.action;
        }
        
        class Mousey extends MouseAdapter
        {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger() && !HSBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showSBPopup((SBField)mouseEvent.getSource());
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (HSBField.this.ref == null) {
                    return;
                }
                final HSBField hsbField = (HSBField)mouseEvent.getSource();
                hsbField.requestFocus();
                if (mouseEvent.isPopupTrigger() && !HSBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showHSBPopup(hsbField);
                    return;
                }
                if (mouseEvent.getX() > hsbField.getWidth() - 19 && !HSBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showHSBPopup(hsbField);
                    return;
                }
                if (mouseEvent.getButton() != 1) {
                    return;
                }
                if (!ControlPanel.this.iconChecks[hsbField.index].isSelected()) {
                    ControlPanel.this.iconChecks[hsbField.index].setSelected(true);
                }
                final int access$2100 = hsbField.hue;
                final int access$2101 = hsbField.sat;
                final int access$2102 = hsbField.bri;
                final boolean access$2103 = hsbField.preserveGrey;
                if (!HSBChooser.showColorizeDialog(ControlPanel.this.theFrame, hsbField)) {
                    hsbField.hue = access$2100;
                    hsbField.sat = access$2101;
                    hsbField.bri = access$2102;
                    hsbField.preserveGrey = access$2103;
                    ControlPanel.this.iconCP.colorizeIcon(hsbField, true);
                    return;
                }
                HSBField.this.hue = HSBChooser.getHue();
                HSBField.this.sat = HSBChooser.getSaturation();
                HSBField.this.bri = HSBChooser.getBrightness();
                HSBField.this.preserveGrey = HSBChooser.isPreserveGrey();
                HSBField.this.update();
                HSBField.this.updateTTT();
                ControlPanel.this.updateThemeButton.setEnabled(true);
            }
        }
    }
    
    public class SBField extends JPanel
    {
        private boolean forceUpdate;
        private boolean only99;
        private Dimension size;
        private ColorReference[] ref;
        
        SBField(final ControlPanel controlPanel, final ColorReference[] array) {
            this(controlPanel, array, false);
        }
        
        SBField(final ColorReference[] ref, final boolean forceUpdate) {
            this.forceUpdate = false;
            this.only99 = false;
            this.size = new Dimension(64, 18);
            this.ref = ref;
            this.forceUpdate = forceUpdate;
            this.setBorder(ControlPanel.sbFieldBorder);
            if (ref == null) {
                return;
            }
            this.update();
            this.addMouseListener(new Mousey());
        }
        
        SBField(final ColorReference[] ref, final boolean forceUpdate, final boolean only99) {
            this.forceUpdate = false;
            this.only99 = false;
            this.size = new Dimension(64, 18);
            this.ref = ref;
            this.forceUpdate = forceUpdate;
            this.only99 = only99;
            this.setBorder(ControlPanel.sbFieldBorder);
            if (ref == null) {
                return;
            }
            this.update();
            this.addMouseListener(new Mousey());
        }
        
        SBField(final ColorReference[] ref, final int height) {
            this.forceUpdate = false;
            this.only99 = false;
            this.size = new Dimension(64, 18);
            this.ref = ref;
            this.forceUpdate = true;
            this.size.height = height;
            this.setBorder(ControlPanel.sbFieldBorder);
            if (ref == null) {
                return;
            }
            this.update();
            this.addMouseListener(new Mousey());
        }
        
        public ColorReference getColorReference() {
            return this.ref[Theme.style];
        }
        
        public void resetReference() {
            if (this.ref == null) {
                return;
            }
            this.ref[Theme.style].reset();
        }
        
        public Color getColor() {
            return this.ref[Theme.style].getColor();
        }
        
        public void setBackground(final Color background) {
            if (this.ref == null) {
                super.setBackground(background);
            }
            else {
                super.setBackground(this.ref[Theme.style].getColor());
            }
        }
        
        public void update() {
            if (this.ref != null) {
                this.setBackground(this.ref[Theme.style].update());
            }
            this.repaint();
            this.updateTTT();
        }
        
        private void updateTTT() {
            if (this.ref == null) {
                this.setToolTipText(null);
                return;
            }
            final ColorUIResource color = this.ref[Theme.style].getColor();
            final StringBuffer sb = new StringBuffer();
            if (this.ref[Theme.style].isAbsoluteColor()) {
                sb.append("R:" + color.getRed());
                sb.append(" G:" + color.getGreen());
                sb.append(" B:" + color.getBlue());
            }
            else {
                sb.append("S:" + this.ref[Theme.style].getSaturation());
                sb.append(" B:" + this.ref[Theme.style].getBrightness());
                sb.append(" (" + this.ref[Theme.style].getReferenceString() + ")");
                sb.append(" R:" + color.getRed());
                sb.append(" G:" + color.getGreen());
                sb.append(" B:" + color.getBlue());
            }
            this.setToolTipText(sb.toString());
        }
        
        public void setColorReference(final ColorReference[] ref) {
            this.ref = ref;
            this.update();
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            if (this.ref == null) {
                graphics.setColor(Theme.backColor[Theme.style].getColor());
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                return;
            }
            super.paint(graphics);
            if (this.ref[Theme.style].isLocked()) {
                return;
            }
            if (this.only99) {
                graphics.drawImage(ControlPanel.this.image99, 1, 1, this);
            }
            if (this.ref[Theme.style].isAbsoluteColor()) {
                final int n = this.getWidth() - 19;
                float n2 = 0.0f;
                graphics.setColor(Color.BLACK);
                graphics.drawLine(n - 1, 1, n - 1, this.getHeight() - 2);
                for (int i = 0; i < 18; ++i) {
                    graphics.setColor(Color.getHSBColor(n2, 0.5f, 1.0f));
                    graphics.drawLine(n + i, 1, n + i, this.getHeight() - 2);
                    n2 += 0.05263157894736842;
                }
            }
            else {
                final int n3 = this.getWidth() - 19;
                int n4 = 255;
                graphics.setColor(Color.BLACK);
                graphics.drawLine(n3 - 1, 1, n3 - 1, this.getHeight() - 2);
                for (int j = 0; j < 18; ++j) {
                    graphics.setColor(new Color(n4, n4, n4));
                    graphics.drawLine(n3 + j, 1, n3 + j, this.getHeight() - 2);
                    n4 -= 14;
                }
            }
        }
        
        class Mousey extends MouseAdapter
        {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger() && !SBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showSBPopup((SBField)mouseEvent.getSource());
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (SBField.this.ref == null) {
                    return;
                }
                final SBField sbField = (SBField)mouseEvent.getSource();
                sbField.requestFocus();
                if (mouseEvent.isPopupTrigger() && !SBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showSBPopup(sbField);
                    return;
                }
                if (mouseEvent.getX() > sbField.getWidth() - 19 && !SBField.this.ref[Theme.style].isLocked()) {
                    ControlPanel.this.showSBPopup(sbField);
                    return;
                }
                if (mouseEvent.getButton() != 1) {
                    return;
                }
                if (SBField.this.ref[Theme.style].isAbsoluteColor()) {
                    final Color showColorChooser = PSColorChooser.showColorChooser(ControlPanel.this.theFrame, sbField.getColor());
                    if (showColorChooser == null) {
                        return;
                    }
                    sbField.getColorReference().setColor(showColorChooser);
                    sbField.setBackground(showColorChooser);
                }
                else {
                    final Color showSBChooser = SBChooser.showSBChooser(ControlPanel.this.theFrame, sbField);
                    if (showSBChooser == null) {
                        return;
                    }
                    sbField.getColorReference().setColor(SBChooser.getSat(), SBChooser.getBri());
                    sbField.setBackground(showSBChooser);
                }
                SBField.this.updateTTT();
                ControlPanel.this.initPanels();
                ControlPanel.this.examplePanel.update(SBField.this.forceUpdate);
            }
        }
    }
    
    class IconCP extends JPanel
    {
        private boolean inited;
        
        IconCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            final ColorizeAction colorizeAction = new ColorizeAction();
            for (int i = 0; i < 20; ++i) {
                ControlPanel.this.hsb[i] = new HSBField(Theme.colorizer[i], colorizeAction, i);
                ControlPanel.this.iconChecks[i] = new CheckedIcon(Theme.colorize[Theme.style][i], ControlPanel.this.hsb[i], TinyLookAndFeel.getSystemIconName(i));
            }
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            final JPanel panel2 = new JPanel(new FlowLayout(0, 8, 6));
            panel2.add(new JLabel("Tree "));
            for (int j = 1; j < 6; ++j) {
                panel2.add(new CombiPanel(ControlPanel.this.hsb[j], ControlPanel.this.iconChecks[j]));
            }
            panel.add(panel2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            final JPanel panel3 = new JPanel(new FlowLayout(0, 8, 6));
            panel3.add(new JLabel("FileView "));
            for (int k = 6; k < 11; ++k) {
                panel3.add(new CombiPanel(ControlPanel.this.hsb[k], ControlPanel.this.iconChecks[k]));
            }
            panel.add(panel3, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            final JPanel panel4 = new JPanel(new FlowLayout(0, 8, 6));
            panel4.add(new JLabel("FileChooser "));
            for (int l = 11; l < 16; ++l) {
                panel4.add(new CombiPanel(ControlPanel.this.hsb[l], ControlPanel.this.iconChecks[l]));
            }
            panel.add(panel4, gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            final JPanel panel5 = new JPanel(new FlowLayout(0, 8, 6));
            panel5.add(new JLabel("OptionPane "));
            for (int n = 16; n < 20; ++n) {
                panel5.add(new CombiPanel(ControlPanel.this.hsb[n], ControlPanel.this.iconChecks[n]));
            }
            panel5.add(new JLabel("   InternalFrame "));
            panel5.add(new CombiPanel(ControlPanel.this.hsb[0], ControlPanel.this.iconChecks[0]));
            panel.add(panel5, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            for (int i = 0; i < 20; ++i) {
                ControlPanel.this.iconChecks[i].setIcon(TinyLookAndFeel.getUncolorizedSystemIcon(i));
                ControlPanel.this.iconChecks[i].setSelected(Theme.colorize[Theme.style][i]);
                ControlPanel.this.hsb[i].setHue(Theme.colorizer[i][Theme.style].getHue());
                ControlPanel.this.hsb[i].setSaturation(Theme.colorizer[i][Theme.style].getSaturation());
                ControlPanel.this.hsb[i].setBrightness(Theme.colorizer[i][Theme.style].getBrightness());
                ControlPanel.this.hsb[i].setPreserveGrey(Theme.colorizer[i][Theme.style].isPreserveGrey());
                ControlPanel.this.hsb[i].setReference(Theme.colorizer[i][Theme.style].getReference(), false);
                ControlPanel.this.hsb[i].update();
            }
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            for (int i = 0; i < 20; ++i) {
                Theme.colorize[Theme.style][i] = ControlPanel.this.iconChecks[i].isSelected();
                Theme.colorizer[i][Theme.style].setHue(ControlPanel.this.hsb[i].getHue());
                Theme.colorizer[i][Theme.style].setSaturation(ControlPanel.this.hsb[i].getSaturation());
                Theme.colorizer[i][Theme.style].setBrightness(ControlPanel.this.hsb[i].getBrightness());
                Theme.colorizer[i][Theme.style].setPreserveGrey(ControlPanel.this.hsb[i].isPreserveGrey());
                Theme.colorizer[i][Theme.style].setReference(ControlPanel.this.hsb[i].getReference());
            }
        }
        
        public void colorizeIcon(final HSBField hsbField, final boolean b) {
            for (int i = 0; i < 20; ++i) {
                if (hsbField.equals(ControlPanel.this.hsb[i])) {
                    if (b) {
                        ControlPanel.this.iconChecks[i].setIcon(DrawRoutines.colorize(((ImageIcon)TinyLookAndFeel.getUncolorizedSystemIcon(i)).getImage(), hsbField.hue, hsbField.sat, hsbField.bri, hsbField.preserveGrey));
                    }
                    else {
                        ControlPanel.this.iconChecks[i].setIcon(TinyLookAndFeel.getUncolorizedSystemIcon(i));
                    }
                }
            }
        }
        
        class CombiPanel extends JPanel
        {
            CombiPanel(final HSBField hsbField, final CheckedIcon checkedIcon) {
                this.setLayout(new FlowLayout(0, 0, 0));
                this.add(hsbField);
                this.add(checkedIcon);
            }
        }
        
        class ColorizeAction implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                IconCP.this.colorizeIcon((HSBField)actionEvent.getSource(), true);
            }
        }
    }
    
    class SelectThemeAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.openTheme(((JMenuItem)actionEvent.getSource()).getText() + ".theme");
        }
    }
    
    class ThemeFileFilter extends FileFilter
    {
        public boolean accept(final File file) {
            return file.isDirectory() || file.getName().endsWith(".theme");
        }
        
        public String getDescription() {
            return ".theme";
        }
    }
    
    class ProgressAction implements ActionListener
    {
        private int value;
        
        ProgressAction() {
            this.value = 0;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            ++this.value;
            if (this.value > 20) {
                this.value = 0;
                ControlPanel.this.horzProgressBar.setIndeterminate(!ControlPanel.this.horzProgressBar.isIndeterminate());
                ControlPanel.this.vertProgressBar.setIndeterminate(!ControlPanel.this.vertProgressBar.isIndeterminate());
            }
            ControlPanel.this.horzProgressBar.setValue(this.value);
            ControlPanel.this.vertProgressBar.setValue(this.value);
            final int n = this.value % 25;
            if (n < 8) {
                ControlPanel.this.horzProgressBar.setString("Fun");
                ControlPanel.this.vertProgressBar.setString("Fun");
            }
            else if (n < 16) {
                ControlPanel.this.horzProgressBar.setString("with");
                ControlPanel.this.vertProgressBar.setString("with");
            }
            else {
                ControlPanel.this.horzProgressBar.setString("Swing");
                ControlPanel.this.vertProgressBar.setString("Swing");
            }
        }
    }
    
    class SpreadControl extends JPanel implements FocusListener
    {
        private int controlMode;
        private final Color activeColor;
        private final Color inactiveColor;
        private int max;
        private Dimension size;
        private Font font;
        private int[] spreadRef;
        private boolean hasFocus;
        private int spread;
        private int x1;
        private int x2;
        private int paintX;
        private int y;
        
        SpreadControl(final int[] spreadRef, final int max, final int controlMode) {
            this.controlMode = 0;
            this.activeColor = Color.WHITE;
            this.inactiveColor = new Color(207, 210, 217);
            this.max = 20;
            this.size = new Dimension(18, 18);
            this.font = new Font("sansserif", 1, 12);
            this.hasFocus = false;
            this.x1 = 7;
            this.y = 7;
            this.spreadRef = spreadRef;
            this.max = max;
            this.controlMode = controlMode;
            this.addMouseListener(new MouseHandler());
            this.addMouseMotionListener(new MouseMotionHandler());
            this.addKeyListener(new ArrowKeyAction());
            this.addFocusListener(this);
            this.init();
        }
        
        public void update(final int spread) {
            this.repaint();
            if (spread == this.spread) {
                return;
            }
            this.spread = spread;
            this.spreadRef[Theme.style] = spread;
            if (ControlPanel.this.internalFrame == null) {
                return;
            }
            if (this.controlMode == 1) {
                ControlPanel.this.repaintTargets(ControlPanel.this.buttons);
            }
            else if (this.controlMode == 3) {
                ControlPanel.this.repaintTargets(ControlPanel.this.combos);
            }
            else if (this.controlMode == 6) {
                if (ControlPanel.decoratedFramesCheck.isSelected()) {
                    final Component[] componentsInLayer = ControlPanel.this.theFrame.getLayeredPane().getComponentsInLayer(JLayeredPane.FRAME_CONTENT_LAYER);
                    for (int i = 0; i < componentsInLayer.length; ++i) {
                        if (componentsInLayer[i] instanceof TinyTitlePane) {
                            componentsInLayer[i].repaint();
                            break;
                        }
                    }
                }
                ControlPanel.this.repaintTargets(ControlPanel.this.frames);
            }
            else if (this.controlMode == 7) {
                ControlPanel.this.repaintTargets(ControlPanel.this.frames);
            }
            else if (this.controlMode == 4) {
                ControlPanel.this.repaintTargets(ControlPanel.this.scrollBars);
            }
            else if (this.controlMode == 5) {
                ControlPanel.this.repaintTargets(ControlPanel.this.spinners);
            }
            else if (this.controlMode == 2) {
                ControlPanel.this.repaintTargets(ControlPanel.windowButtons);
            }
        }
        
        public void init() {
            this.paintX = -1;
            this.update(this.spreadRef[Theme.style]);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            if (this.hasFocus) {
                graphics.setColor(this.activeColor);
            }
            else {
                graphics.setColor(this.inactiveColor);
            }
            graphics.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            this.x2 = this.getWidth() - 24;
            graphics.drawLine(this.x1, this.y - 3, this.x1, this.y + 3);
            graphics.drawLine(this.x2, this.y - 3, this.x2, this.y + 3);
            graphics.drawLine(this.x1, this.y, this.x2, this.y);
            int paintX = this.paintX;
            if (paintX == -1) {
                paintX = this.spread * (this.x2 - this.x1) / this.max + this.x1;
            }
            graphics.drawLine(paintX, this.y + 2, paintX, this.y + 2);
            graphics.drawLine(paintX - 1, this.y + 3, paintX + 1, this.y + 3);
            graphics.drawLine(paintX - 2, this.y + 4, paintX + 2, this.y + 4);
            graphics.drawLine(paintX - 3, this.y + 5, paintX + 3, this.y + 5);
            graphics.drawLine(paintX - 4, this.y + 6, paintX + 4, this.y + 6);
            graphics.setFont(this.font);
            graphics.drawString(String.valueOf(this.spread), this.getWidth() - graphics.getFontMetrics().stringWidth(String.valueOf(this.spread)) - 3, this.getHeight() - 5);
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            this.hasFocus = true;
        }
        
        public void focusLost(final FocusEvent focusEvent) {
            this.hasFocus = false;
            this.repaint(0L);
        }
        
        class ArrowKeyAction extends KeyAdapter implements ActionListener
        {
            private Timer keyTimer;
            private int step;
            
            ArrowKeyAction() {
                this.keyTimer = new Timer(20, this);
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 38) {
                    if (SpreadControl.this.spread == SpreadControl.this.max) {
                        return;
                    }
                    this.step = 1;
                    this.changeVal();
                    this.keyTimer.setInitialDelay(300);
                    this.keyTimer.start();
                }
                else if (keyEvent.getKeyCode() == 40) {
                    if (SpreadControl.this.spread == 0) {
                        return;
                    }
                    this.step = -1;
                    this.changeVal();
                    this.keyTimer.setInitialDelay(300);
                    this.keyTimer.start();
                }
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
                this.keyTimer.stop();
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                this.changeVal();
            }
            
            private void changeVal() {
                if (SpreadControl.this.spread + this.step < 0 || SpreadControl.this.spread + this.step > SpreadControl.this.max) {
                    return;
                }
                SpreadControl.this.paintX = -1;
                SpreadControl.this.update(SpreadControl.this.spread + this.step);
            }
        }
        
        class MouseMotionHandler extends MouseMotionAdapter
        {
            public void mouseDragged(final MouseEvent mouseEvent) {
                int n = mouseEvent.getX();
                if (n < SpreadControl.this.x1) {
                    n = SpreadControl.this.x1;
                }
                if (n > SpreadControl.this.x2) {
                    n = SpreadControl.this.x2;
                }
                final int n2 = n - SpreadControl.this.x1;
                SpreadControl.this.paintX = n;
                SpreadControl.this.update(n2 * SpreadControl.this.max / (SpreadControl.this.x2 - SpreadControl.this.x1));
            }
        }
        
        class MouseHandler extends MouseAdapter
        {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (SpreadControl.this.equals(ControlPanel.this.frameSpreadDark) || SpreadControl.this.equals(ControlPanel.this.frameSpreadLight)) {
                    if (!ControlPanel.this.internalFrame.isSelected()) {
                        try {
                            ControlPanel.this.internalFrame.setSelected(true);
                        }
                        catch (PropertyVetoException ex) {}
                    }
                }
                else if ((SpreadControl.this.equals(ControlPanel.this.frameSpreadDarkDisabled) || SpreadControl.this.equals(ControlPanel.this.frameSpreadLightDisabled)) && ControlPanel.this.internalFrame.isSelected()) {
                    try {
                        ControlPanel.this.internalFrame.setSelected(false);
                    }
                    catch (PropertyVetoException ex2) {}
                }
                if (!SpreadControl.this.hasFocus) {
                    SpreadControl.this.requestFocus();
                    SpreadControl.this.repaint(0L);
                }
                else {
                    int n = mouseEvent.getX();
                    if (n < SpreadControl.this.x1) {
                        n = SpreadControl.this.x1;
                    }
                    if (n > SpreadControl.this.x2) {
                        n = SpreadControl.this.x2;
                    }
                    final int n2 = n - SpreadControl.this.x1;
                    SpreadControl.this.paintX = n;
                    SpreadControl.this.update(n2 * SpreadControl.this.max / (SpreadControl.this.x2 - SpreadControl.this.x1));
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                SpreadControl.this.repaint(0L);
                ControlPanel.this.examplePanel.update(false);
            }
        }
    }
    
    class FrameCloseButtonCP extends JPanel
    {
        FrameCloseButtonCP() {
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtClose = new SBField(Theme.frameButtCloseColor);
            panel.add(ControlPanel.this.frameButtClose, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtCloseRollover = new SBField(Theme.frameButtCloseRolloverColor);
            panel.add(ControlPanel.this.frameButtCloseRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Pressed Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtClosePressed = new SBField(Theme.frameButtClosePressedColor);
            panel.add(ControlPanel.this.frameButtClosePressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtCloseDisabled = new SBField(Theme.frameButtCloseDisabledColor);
            panel.add(ControlPanel.this.frameButtCloseDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtCloseSpreadLight = new SpreadControl(Theme.frameButtCloseSpreadLight, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtCloseSpreadDark = new SpreadControl(Theme.frameButtCloseSpreadDark, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtCloseSpreadLightDisabled = new SpreadControl(Theme.frameButtCloseSpreadLightDisabled, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtCloseSpreadDarkDisabled = new SpreadControl(Theme.frameButtCloseSpreadDarkDisabled, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseBorder = new SBField(Theme.frameButtCloseBorderColor);
            panel.add(ControlPanel.this.frameButtCloseBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseDark = new SBField(Theme.frameButtCloseDarkColor);
            panel.add(ControlPanel.this.frameButtCloseDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseLight = new SBField(Theme.frameButtCloseLightColor);
            panel.add(ControlPanel.this.frameButtCloseLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseBorderDisabled = new SBField(Theme.frameButtCloseBorderDisabledColor);
            panel.add(ControlPanel.this.frameButtCloseBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseDarkDisabled = new SBField(Theme.frameButtCloseDarkDisabledColor);
            panel.add(ControlPanel.this.frameButtCloseDarkDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtCloseLightDisabled = new SBField(Theme.frameButtCloseLightDisabledColor);
            panel.add(ControlPanel.this.frameButtCloseLightDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Symbol Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolClose = new SBField(Theme.frameSymbolCloseColor);
            panel.add(ControlPanel.this.frameSymbolClose, gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Pressed Symbol"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolClosePressed = new SBField(Theme.frameSymbolClosePressedColor);
            panel.add(ControlPanel.this.frameSymbolClosePressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Symbol"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolCloseDisabled = new SBField(Theme.frameSymbolCloseDisabledColor);
            panel.add(ControlPanel.this.frameSymbolCloseDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Symbol Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolCloseDark = new SBField(Theme.frameSymbolCloseDarkColor);
            panel.add(ControlPanel.this.frameSymbolCloseDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Symbol Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolCloseLight = new SBField(Theme.frameSymbolCloseLightColor);
            panel.add(ControlPanel.this.frameSymbolCloseLight, gridBagConstraints);
            this.add(panel);
        }
    }
    
    class FrameButtonsCP extends JPanel
    {
        FrameButtonsCP() {
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButt = new SBField(Theme.frameButtColor);
            panel.add(ControlPanel.this.frameButt, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtRollover = new SBField(Theme.frameButtRolloverColor);
            panel.add(ControlPanel.this.frameButtRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Pressed Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtPressed = new SBField(Theme.frameButtPressedColor);
            panel.add(ControlPanel.this.frameButtPressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameButtDisabled = new SBField(Theme.frameButtDisabledColor);
            panel.add(ControlPanel.this.frameButtDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtSpreadLight = new SpreadControl(Theme.frameButtSpreadLight, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtSpreadDark = new SpreadControl(Theme.frameButtSpreadDark, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtSpreadLightDisabled = new SpreadControl(Theme.frameButtSpreadLightDisabled, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameButtSpreadDarkDisabled = new SpreadControl(Theme.frameButtSpreadDarkDisabled, 20, 2), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtBorder = new SBField(Theme.frameButtBorderColor);
            panel.add(ControlPanel.this.frameButtBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtDark = new SBField(Theme.frameButtDarkColor);
            panel.add(ControlPanel.this.frameButtDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtLight = new SBField(Theme.frameButtLightColor);
            panel.add(ControlPanel.this.frameButtLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtBorderDisabled = new SBField(Theme.frameButtBorderDisabledColor);
            panel.add(ControlPanel.this.frameButtBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtDarkDisabled = new SBField(Theme.frameButtDarkDisabledColor);
            panel.add(ControlPanel.this.frameButtDarkDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameButtLightDisabled = new SBField(Theme.frameButtLightDisabledColor);
            panel.add(ControlPanel.this.frameButtLightDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Symbol Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbol = new SBField(Theme.frameSymbolColor);
            panel.add(ControlPanel.this.frameSymbol, gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Pressed Symbol"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolPressed = new SBField(Theme.frameSymbolPressedColor);
            panel.add(ControlPanel.this.frameSymbolPressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Symbol"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolDisabled = new SBField(Theme.frameSymbolDisabledColor);
            panel.add(ControlPanel.this.frameSymbolDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Symbol Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolDark = new SBField(Theme.frameSymbolDarkColor);
            panel.add(ControlPanel.this.frameSymbolDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Symbol Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameSymbolLight = new SBField(Theme.frameSymbolLightColor);
            panel.add(ControlPanel.this.frameSymbolLight, gridBagConstraints);
            this.add(panel);
        }
    }
    
    class FrameFrameCP extends JPanel
    {
        FrameFrameCP() {
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Caption Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameCaption = new SBField(Theme.frameCaptionColor);
            panel.add(ControlPanel.this.frameCaption, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.frameCaptionDisabled = new SBField(Theme.frameCaptionDisabledColor);
            panel.add(ControlPanel.this.frameCaptionDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameSpreadDark = new SpreadControl(Theme.frameSpreadDark, 10, 6), gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameSpreadLight = new SpreadControl(Theme.frameSpreadLight, 10, 6), gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("S. Dark Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameSpreadDarkDisabled = new SpreadControl(Theme.frameSpreadDarkDisabled, 10, 7), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("S. Light Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.frameSpreadLightDisabled = new SpreadControl(Theme.frameSpreadLightDisabled, 10, 7), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameBorder = new SBField(Theme.frameBorderColor);
            panel.add(ControlPanel.this.frameBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameDark = new SBField(Theme.frameDarkColor, false, true);
            panel.add(ControlPanel.this.frameDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameLight = new SBField(Theme.frameLightColor);
            panel.add(ControlPanel.this.frameLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            gridBagConstraints19.gridy += 2;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.insets = new Insets(0, 8, 0, 4);
            if (ControlPanel.decoratedFramesCheck == null) {
                ControlPanel.decoratedFramesCheck = new JCheckBox("Decorated Frame (experimental, not saved)");
                ControlPanel.decoratedFramesCheck.addActionListener(new DecorateFrameAction());
            }
            panel.add(ControlPanel.decoratedFramesCheck, gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameBorderDisabled = new SBField(Theme.frameBorderDisabledColor);
            panel.add(ControlPanel.this.frameBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameDarkDisabled = new SBField(Theme.frameDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.frameDarkDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameLightDisabled = new SBField(Theme.frameLightDisabledColor);
            panel.add(ControlPanel.this.frameLightDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Title Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameTitle = new SBField(Theme.frameTitleColor);
            panel.add(ControlPanel.this.frameTitle, gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Title Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.frameTitleDisabled = new SBField(Theme.frameTitleDisabledColor);
            panel.add(ControlPanel.this.frameTitleDisabled, gridBagConstraints);
            this.add(panel);
        }
    }
    
    class FrameCP extends JPanel
    {
        private boolean inited;
        private CardLayout cardLayout;
        private JPanel cardPanel;
        
        FrameCP() {
            this.inited = false;
            this.cardLayout = new CardLayout();
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new BorderLayout());
            final JPanel panel = new JPanel(new GridLayout(5, 1, 0, 0));
            final JPanel panel2 = new JPanel(new FlowLayout(0, 24, 8));
            final ButtonGroup buttonGroup = new ButtonGroup();
            final JRadioButton radioButton = new JRadioButton("Frame", true);
            buttonGroup.add(radioButton);
            radioButton.addActionListener(new ActionListener() {
                private final /* synthetic */ FrameCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.cardLayout.show(this.this$1.cardPanel, "Frame");
                }
            });
            panel.add(radioButton);
            final JRadioButton radioButton2 = new JRadioButton("Iconify/Maximize Buttons");
            buttonGroup.add(radioButton2);
            radioButton2.addActionListener(new ActionListener() {
                private final /* synthetic */ FrameCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.cardLayout.show(this.this$1.cardPanel, "FrameButtons");
                }
            });
            panel.add(radioButton2);
            final JRadioButton radioButton3 = new JRadioButton("Close Button");
            buttonGroup.add(radioButton3);
            radioButton3.addActionListener(new ActionListener() {
                private final /* synthetic */ FrameCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.cardLayout.show(this.this$1.cardPanel, "FrameCloseButton");
                }
            });
            panel.add(radioButton3);
            panel.add(new JLabel(""));
            final JButton button = new JButton("Activate/Deactivate Internal Frame");
            button.addActionListener(new DeactivateInternalFrameAction());
            panel.add(button);
            panel2.add(panel);
            this.add(panel2, "Center");
            (this.cardPanel = new JPanel(this.cardLayout)).add(new FrameFrameCP(), "Frame");
            this.cardPanel.add(new FrameButtonsCP(), "FrameButtons");
            this.cardPanel.add(new FrameCloseButtonCP(), "FrameCloseButton");
            this.cardLayout.layoutContainer(this);
            this.add(this.cardPanel, "West");
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.frameCaption.update();
            ControlPanel.this.frameCaptionDisabled.update();
            ControlPanel.this.frameBorder.update();
            ControlPanel.this.frameDark.update();
            ControlPanel.this.frameLight.update();
            ControlPanel.this.frameBorderDisabled.update();
            ControlPanel.this.frameDarkDisabled.update();
            ControlPanel.this.frameLightDisabled.update();
            ControlPanel.this.frameTitle.update();
            ControlPanel.this.frameTitleDisabled.update();
            ControlPanel.this.frameButt.update();
            ControlPanel.this.frameButtRollover.update();
            ControlPanel.this.frameButtPressed.update();
            ControlPanel.this.frameButtDisabled.update();
            ControlPanel.this.frameButtBorder.update();
            ControlPanel.this.frameButtDark.update();
            ControlPanel.this.frameButtLight.update();
            ControlPanel.this.frameButtBorderDisabled.update();
            ControlPanel.this.frameButtDarkDisabled.update();
            ControlPanel.this.frameButtLightDisabled.update();
            ControlPanel.this.frameButtSpreadDark.init();
            ControlPanel.this.frameButtSpreadLight.init();
            ControlPanel.this.frameButtSpreadDarkDisabled.init();
            ControlPanel.this.frameButtSpreadLightDisabled.init();
            ControlPanel.this.frameButtClose.update();
            ControlPanel.this.frameButtCloseRollover.update();
            ControlPanel.this.frameButtClosePressed.update();
            ControlPanel.this.frameButtCloseDisabled.update();
            ControlPanel.this.frameButtCloseBorder.update();
            ControlPanel.this.frameButtCloseDark.update();
            ControlPanel.this.frameButtCloseLight.update();
            ControlPanel.this.frameButtCloseBorderDisabled.update();
            ControlPanel.this.frameButtCloseDarkDisabled.update();
            ControlPanel.this.frameButtCloseLightDisabled.update();
            ControlPanel.this.frameButtCloseSpreadDark.init();
            ControlPanel.this.frameButtCloseSpreadLight.init();
            ControlPanel.this.frameButtCloseSpreadDarkDisabled.init();
            ControlPanel.this.frameButtCloseSpreadLightDisabled.init();
            ControlPanel.this.frameSymbol.update();
            ControlPanel.this.frameSymbolPressed.update();
            ControlPanel.this.frameSymbolDisabled.update();
            ControlPanel.this.frameSymbolDark.update();
            ControlPanel.this.frameSymbolLight.update();
            ControlPanel.this.frameSymbolClose.update();
            ControlPanel.this.frameSymbolClosePressed.update();
            ControlPanel.this.frameSymbolCloseDisabled.update();
            ControlPanel.this.frameSymbolCloseDark.update();
            ControlPanel.this.frameSymbolCloseLight.update();
            ControlPanel.this.frameSpreadDark.init();
            ControlPanel.this.frameSpreadLight.init();
            ControlPanel.this.frameSpreadDarkDisabled.init();
            ControlPanel.this.frameSpreadLightDisabled.init();
            this.inited = true;
        }
        
        void updateTheme() {
        }
        
        class DeactivateInternalFrameAction implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    ControlPanel.this.internalFrame.setSelected(!ControlPanel.this.internalFrame.isSelected());
                }
                catch (PropertyVetoException ex) {}
            }
        }
    }
    
    class ButtonsCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private JCheckBox focusEnabled;
        private JCheckBox enterEnabled;
        private JCheckBox focusBorderEnabled;
        private JCheckBox shiftTextEnabled;
        private JSpinner mTop;
        private JSpinner mLeft;
        private JSpinner mBottom;
        private JSpinner mRight;
        private JSpinner cTop;
        private JSpinner cLeft;
        private JSpinner cBottom;
        private JSpinner cRight;
        private JPanel cardPanel;
        private boolean inited;
        
        ButtonsCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Normal Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.buttonNormalBg = new SBField(Theme.buttonNormalColor, true);
            panel.add(ControlPanel.this.buttonNormalBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.buttonRolloverBg = new SBField(Theme.buttonRolloverBgColor, true);
            panel.add(ControlPanel.this.buttonRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Presssed Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.buttonPressedBg = new SBField(Theme.buttonPressedColor, true);
            panel.add(ControlPanel.this.buttonPressedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.buttonDisabledBg = new SBField(Theme.buttonDisabledColor);
            panel.add(ControlPanel.this.buttonDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.buttonSpreadLight = new SpreadControl(Theme.buttonSpreadLight, 20, 1), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.buttonSpreadDark = new SpreadControl(Theme.buttonSpreadDark, 20, 1), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.buttonSpreadLightDisabled = new SpreadControl(Theme.buttonSpreadLightDisabled, 20, 1), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.buttonSpreadDarkDisabled = new SpreadControl(Theme.buttonSpreadDarkDisabled, 20, 1), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridx;
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            --gridBagConstraints18.gridy;
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.gridheight = 2;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            panel.add(new JLabel("<html>For toolbar buttons choose <b>'ToolBar'</b>"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonBorder = new SBField(Theme.buttonBorderColor);
            panel.add(ControlPanel.this.buttonBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDark = new SBField(Theme.buttonDarkColor, false, true);
            panel.add(ControlPanel.this.buttonDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonLight = new SBField(Theme.buttonLightColor, false, true);
            panel.add(ControlPanel.this.buttonLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDisabledBorder = new SBField(Theme.buttonBorderDisabledColor);
            panel.add(ControlPanel.this.buttonDisabledBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDisabledDark = new SBField(Theme.buttonDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.buttonDisabledDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDisabledLight = new SBField(Theme.buttonLightDisabledColor, false, true);
            panel.add(ControlPanel.this.buttonDisabledLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Button Disabled Text"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDisabledFg = new SBField(Theme.buttonDisabledFgColor, true);
            panel.add(ControlPanel.this.buttonDisabledFg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("CheckBox Disabled T."), gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.checkDisabledFg = new SBField(Theme.checkDisabledFgColor, true);
            panel.add(ControlPanel.this.checkDisabledFg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("RadioButton Disabled T."), gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.radioDisabledFg = new SBField(Theme.radioDisabledFgColor, true);
            panel.add(ControlPanel.this.radioDisabledFg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Default Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonDefault = new SBField(Theme.buttonDefaultColor, true);
            panel.add(ControlPanel.this.buttonDefault, gridBagConstraints);
            final GridBagConstraints gridBagConstraints39 = gridBagConstraints;
            ++gridBagConstraints39.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Rollover Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints40 = gridBagConstraints;
            ++gridBagConstraints40.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonRollover = new SBField(Theme.buttonRolloverColor);
            panel.add(ControlPanel.this.buttonRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.anchor = 18;
            gridBagConstraints41.fill = 2;
            gridBagConstraints41.gridx = 0;
            gridBagConstraints41.gridy = 0;
            final JPanel panel2 = new JPanel(new GridBagLayout());
            gridBagConstraints41.insets = new Insets(0, 0, 0, 4);
            this.rolloverEnabled = new JCheckBox("Paint Rollover Border", true);
            ControlPanel.this.buttons = new Component[24];
            ControlPanel.this.buttons[15] = this.rolloverEnabled;
            this.rolloverEnabled.addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.rolloverEnabled, gridBagConstraints41);
            final GridBagConstraints gridBagConstraints42 = gridBagConstraints41;
            ++gridBagConstraints42.gridy;
            this.shiftTextEnabled = new JCheckBox("Shift Button Text", true);
            ControlPanel.this.buttons[16] = this.shiftTextEnabled;
            this.shiftTextEnabled.addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.shiftTextEnabled, gridBagConstraints41);
            gridBagConstraints41.gridy = 0;
            gridBagConstraints41.gridx = 1;
            this.focusEnabled = new JCheckBox("Paint Focus", true);
            ControlPanel.this.buttons[17] = this.focusEnabled;
            this.focusEnabled.addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.focusEnabled, gridBagConstraints41);
            gridBagConstraints41.gridy = 1;
            this.focusBorderEnabled = new JCheckBox("Paint Focus Border", true);
            ControlPanel.this.buttons[18] = this.focusBorderEnabled;
            this.focusBorderEnabled.addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.focusBorderEnabled, gridBagConstraints41);
            gridBagConstraints41.gridy = 2;
            gridBagConstraints41.gridx = 0;
            gridBagConstraints41.gridwidth = 2;
            this.enterEnabled = new JCheckBox("ENTER \"presses\" focused button");
            ControlPanel.this.buttons[19] = this.enterEnabled;
            this.enterEnabled.addActionListener(new CheckUpdateAction());
            panel2.add(this.enterEnabled, gridBagConstraints41);
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridheight = 4;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            panel.add(panel2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints43 = gridBagConstraints;
            ++gridBagConstraints43.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Checkmark Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints44 = gridBagConstraints;
            ++gridBagConstraints44.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonCheck = new SBField(Theme.buttonCheckColor);
            panel.add(ControlPanel.this.buttonCheck, gridBagConstraints);
            final GridBagConstraints gridBagConstraints45 = gridBagConstraints;
            ++gridBagConstraints45.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Check Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints46 = gridBagConstraints;
            ++gridBagConstraints46.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.buttonCheckDisabled = new SBField(Theme.buttonCheckDisabledColor);
            panel.add(ControlPanel.this.buttonCheckDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints47 = gridBagConstraints;
            ++gridBagConstraints47.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(0, 16, 0, 0);
            final ButtonGroup buttonGroup = new ButtonGroup();
            final JRadioButton radioButton = new JRadioButton("Button Margin", true);
            buttonGroup.add((AbstractButton)(ControlPanel.this.buttons[20] = radioButton));
            radioButton.addActionListener(new ActionListener() {
                private final /* synthetic */ ButtonsCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (!((AbstractButton)actionEvent.getSource()).isSelected()) {
                        return;
                    }
                    ((CardLayout)this.this$1.cardPanel.getLayout()).show(this.this$1.cardPanel, "buttonMargin");
                }
            });
            panel.add(radioButton, gridBagConstraints);
            final GridBagConstraints gridBagConstraints48 = gridBagConstraints;
            ++gridBagConstraints48.gridy;
            final JRadioButton radioButton2 = new JRadioButton("CheckBox/RadioButton Margin");
            buttonGroup.add((AbstractButton)(ControlPanel.this.buttons[21] = radioButton2));
            radioButton2.addActionListener(new ActionListener() {
                private final /* synthetic */ ButtonsCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (!((AbstractButton)actionEvent.getSource()).isSelected()) {
                        return;
                    }
                    ((CardLayout)this.this$1.cardPanel.getLayout()).show(this.this$1.cardPanel, "checkMargin");
                }
            });
            panel.add(radioButton2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints49 = gridBagConstraints;
            ++gridBagConstraints49.gridy;
            gridBagConstraints.insets = new Insets(4, 16, 0, 0);
            gridBagConstraints.gridheight = 5;
            (this.cardPanel = new JPanel(new CardLayout())).add(this.createButtonMarginPanel(), "buttonMargin");
            this.cardPanel.add(this.createCheckMarginPanel(), "checkMargin");
            panel.add(this.cardPanel, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.buttonRollover[Theme.style]);
            this.focusEnabled.setSelected(Theme.buttonFocus[Theme.style]);
            this.focusBorderEnabled.setSelected(Theme.buttonFocusBorder[Theme.style]);
            this.enterEnabled.setSelected(Theme.buttonEnter[Theme.style]);
            this.shiftTextEnabled.setSelected(Theme.shiftButtonText[Theme.style]);
            ControlPanel.this.buttonNormalBg.update();
            ControlPanel.this.buttonRolloverBg.update();
            ControlPanel.this.buttonPressedBg.update();
            ControlPanel.this.buttonDisabledBg.update();
            ControlPanel.this.buttonBorder.update();
            ControlPanel.this.buttonDark.update();
            ControlPanel.this.buttonLight.update();
            ControlPanel.this.buttonDisabledBorder.update();
            ControlPanel.this.buttonDisabledDark.update();
            ControlPanel.this.buttonDisabledLight.update();
            ControlPanel.this.buttonDisabledFg.update();
            ControlPanel.this.checkDisabledFg.update();
            ControlPanel.this.radioDisabledFg.update();
            ControlPanel.this.buttonRollover.update();
            ControlPanel.this.buttonDefault.update();
            ControlPanel.this.buttonCheck.update();
            ControlPanel.this.buttonCheckDisabled.update();
            ControlPanel.this.buttonSpreadDark.init();
            ControlPanel.this.buttonSpreadLight.init();
            ControlPanel.this.buttonSpreadDarkDisabled.init();
            ControlPanel.this.buttonSpreadLightDisabled.init();
            this.mTop.setValue(new Integer(Theme.buttonMarginTop[Theme.style]));
            this.mLeft.setValue(new Integer(Theme.buttonMarginLeft[Theme.style]));
            this.mBottom.setValue(new Integer(Theme.buttonMarginBottom[Theme.style]));
            this.mRight.setValue(new Integer(Theme.buttonMarginRight[Theme.style]));
            this.cTop.setValue(new Integer(Theme.checkMarginTop[Theme.style]));
            this.cLeft.setValue(new Integer(Theme.checkMarginLeft[Theme.style]));
            this.cBottom.setValue(new Integer(Theme.checkMarginBottom[Theme.style]));
            this.cRight.setValue(new Integer(Theme.checkMarginRight[Theme.style]));
            this.inited = true;
        }
        
        InsetsUIResource getButtonMargin() {
            return new InsetsUIResource((int)this.mTop.getValue(), (int)this.mLeft.getValue(), (int)this.mBottom.getValue(), (int)this.mRight.getValue());
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.buttonRollover[Theme.style] = this.rolloverEnabled.isSelected();
            Theme.buttonFocus[Theme.style] = this.focusEnabled.isSelected();
            Theme.buttonFocusBorder[Theme.style] = this.focusBorderEnabled.isSelected();
            Theme.buttonEnter[Theme.style] = this.enterEnabled.isSelected();
            Theme.shiftButtonText[Theme.style] = this.shiftTextEnabled.isSelected();
            Theme.buttonMarginTop[Theme.style] = (int)this.mTop.getValue();
            Theme.buttonMarginLeft[Theme.style] = (int)this.mLeft.getValue();
            Theme.buttonMarginBottom[Theme.style] = (int)this.mBottom.getValue();
            Theme.buttonMarginRight[Theme.style] = (int)this.mRight.getValue();
            Theme.checkMarginTop[Theme.style] = (int)this.cTop.getValue();
            Theme.checkMarginLeft[Theme.style] = (int)this.cLeft.getValue();
            Theme.checkMarginBottom[Theme.style] = (int)this.cBottom.getValue();
            Theme.checkMarginRight[Theme.style] = (int)this.cRight.getValue();
        }
        
        private JPanel createButtonMarginPanel() {
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            (this.mLeft = new JSpinner(new SpinnerNumberModel(16, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.mLeft, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            gridBagConstraints.gridy = 0;
            (this.mTop = new JSpinner(new SpinnerNumberModel(2, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.mTop, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            gridBagConstraints3.gridy += 2;
            (this.mBottom = new JSpinner(new SpinnerNumberModel(3, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.mBottom, gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridx;
            gridBagConstraints.gridy = 1;
            (this.mRight = new JSpinner(new SpinnerNumberModel(16, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.mRight, gridBagConstraints);
            return panel;
        }
        
        private JPanel createCheckMarginPanel() {
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            (this.cLeft = new JSpinner(new SpinnerNumberModel(16, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.cLeft, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            gridBagConstraints.gridy = 0;
            (this.cTop = new JSpinner(new SpinnerNumberModel(2, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.cTop, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            gridBagConstraints3.gridy += 2;
            (this.cBottom = new JSpinner(new SpinnerNumberModel(3, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.cBottom, gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridx;
            gridBagConstraints.gridy = 1;
            (this.cRight = new JSpinner(new SpinnerNumberModel(16, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel.add(this.cRight, gridBagConstraints);
            return panel;
        }
    }
    
    class ComboCP extends JPanel
    {
        private JCheckBox paintFocus;
        private JCheckBox rolloverEnabled;
        private JSpinner mTop;
        private JSpinner mLeft;
        private JSpinner mBottom;
        private JSpinner mRight;
        private boolean inited;
        
        ComboCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.comboBg = new SBField(Theme.comboBgColor, true);
            panel.add(ControlPanel.this.comboBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.comboText = new SBField(Theme.comboTextColor, true);
            panel.add(ControlPanel.this.comboText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            panel.add(new JLabel("Selected Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.comboSelectedBg = new SBField(Theme.comboSelectedBgColor, true);
            panel.add(ControlPanel.this.comboSelectedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Selected Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.comboSelectedText = new SBField(Theme.comboSelectedTextColor, true);
            panel.add(ControlPanel.this.comboSelectedText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboBorder = new SBField(Theme.comboBorderColor);
            panel.add(ControlPanel.this.comboBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboDark = new SBField(Theme.comboDarkColor, false, true);
            panel.add(ControlPanel.this.comboDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboLight = new SBField(Theme.comboLightColor, false, true);
            panel.add(ControlPanel.this.comboLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboBorderDisabled = new SBField(Theme.comboBorderDisabledColor);
            panel.add(ControlPanel.this.comboBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboDisabledDark = new SBField(Theme.comboDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.comboDisabledDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboDisabledLight = new SBField(Theme.comboLightDisabledColor, false, true);
            panel.add(ControlPanel.this.comboDisabledLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 16, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 16, 0, 4);
            ControlPanel.this.comboButt = new SBField(Theme.comboButtColor);
            panel.add(ControlPanel.this.comboButt, gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 16, 0, 4);
            panel.add(new JLabel("Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 16, 0, 4);
            ControlPanel.this.comboButtRollover = new SBField(Theme.comboButtRolloverColor);
            panel.add(ControlPanel.this.comboButtRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(4, 16, 0, 4);
            panel.add(new JLabel("Presssed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(1, 16, 0, 4);
            ControlPanel.this.comboButtPressed = new SBField(Theme.comboButtPressedColor);
            panel.add(ControlPanel.this.comboButtPressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(4, 16, 0, 4);
            panel.add(new JLabel("Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 16, 0, 4);
            ControlPanel.this.comboButtDisabled = new SBField(Theme.comboButtDisabledColor);
            panel.add(ControlPanel.this.comboButtDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.comboSpreadLight = new SpreadControl(Theme.comboSpreadLight, 20, 3), gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.comboSpreadDark = new SpreadControl(Theme.comboSpreadDark, 20, 3), gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.comboSpreadLightDisabled = new SpreadControl(Theme.comboSpreadLightDisabled, 20, 3), gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.comboSpreadDarkDisabled = new SpreadControl(Theme.comboSpreadDarkDisabled, 20, 3), gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Button Border Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints39 = gridBagConstraints;
            ++gridBagConstraints39.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtBorder = new SBField(Theme.comboButtBorderColor);
            panel.add(ControlPanel.this.comboButtBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints40 = gridBagConstraints;
            ++gridBagConstraints40.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints41 = gridBagConstraints;
            ++gridBagConstraints41.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtDark = new SBField(Theme.comboButtDarkColor, false, true);
            panel.add(ControlPanel.this.comboButtDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints42 = gridBagConstraints;
            ++gridBagConstraints42.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints43 = gridBagConstraints;
            ++gridBagConstraints43.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtLight = new SBField(Theme.comboButtLightColor, false, true);
            panel.add(ControlPanel.this.comboButtLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints44 = gridBagConstraints;
            ++gridBagConstraints44.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints45 = gridBagConstraints;
            ++gridBagConstraints45.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtBorderDisabled = new SBField(Theme.comboButtBorderDisabledColor);
            panel.add(ControlPanel.this.comboButtBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints46 = gridBagConstraints;
            ++gridBagConstraints46.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints47 = gridBagConstraints;
            ++gridBagConstraints47.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtDarkDisabled = new SBField(Theme.comboButtDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.comboButtDarkDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints48 = gridBagConstraints;
            ++gridBagConstraints48.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints49 = gridBagConstraints;
            ++gridBagConstraints49.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboButtLightDisabled = new SBField(Theme.comboButtLightDisabledColor, false, true);
            panel.add(ControlPanel.this.comboButtLightDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints50 = gridBagConstraints;
            ++gridBagConstraints50.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Arrow Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints51 = gridBagConstraints;
            ++gridBagConstraints51.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboArrowField = new SBField(Theme.comboArrowColor);
            panel.add(ControlPanel.this.comboArrowField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints52 = gridBagConstraints;
            ++gridBagConstraints52.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Arrow"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints53 = gridBagConstraints;
            ++gridBagConstraints53.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.comboArrowDisabled = new SBField(Theme.comboArrowDisabledColor);
            panel.add(ControlPanel.this.comboArrowDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints54 = gridBagConstraints;
            ++gridBagConstraints54.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            gridBagConstraints.gridheight = 4;
            gridBagConstraints.gridwidth = 3;
            final JPanel panel2 = new JPanel(new GridLayout(2, 1, 0, 2));
            (this.rolloverEnabled = new JCheckBox("Paint Rollover Border", true)).addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.rolloverEnabled);
            (this.paintFocus = new JCheckBox("Paint Focus")).addActionListener(ControlPanel.this.checkAction);
            panel2.add(this.paintFocus);
            panel.add(panel2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints55 = gridBagConstraints;
            ++gridBagConstraints55.gridx;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(8, 8, 0, 4);
            gridBagConstraints.gridheight = 4;
            gridBagConstraints.gridwidth = 1;
            final JPanel panel3 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints56 = new GridBagConstraints();
            gridBagConstraints56.anchor = 17;
            gridBagConstraints56.fill = 2;
            gridBagConstraints56.gridx = 0;
            gridBagConstraints56.gridy = 1;
            gridBagConstraints56.insets = new Insets(0, 2, 0, 2);
            (this.mLeft = new JSpinner(new SpinnerNumberModel(2, 2, 24, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.mLeft, gridBagConstraints56);
            final GridBagConstraints gridBagConstraints57 = gridBagConstraints56;
            ++gridBagConstraints57.gridx;
            gridBagConstraints56.gridy = 0;
            (this.mTop = new JSpinner(new SpinnerNumberModel(2, 2, 8, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.mTop, gridBagConstraints56);
            final GridBagConstraints gridBagConstraints58 = gridBagConstraints56;
            ++gridBagConstraints58.gridy;
            panel3.add(new JLabel("Insets"), gridBagConstraints56);
            final GridBagConstraints gridBagConstraints59 = gridBagConstraints56;
            ++gridBagConstraints59.gridy;
            (this.mBottom = new JSpinner(new SpinnerNumberModel(2, 2, 8, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.mBottom, gridBagConstraints56);
            final GridBagConstraints gridBagConstraints60 = gridBagConstraints56;
            ++gridBagConstraints60.gridx;
            gridBagConstraints56.gridy = 1;
            (this.mRight = new JSpinner(new SpinnerNumberModel(2, 2, 24, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.mRight, gridBagConstraints56);
            panel.add(panel3, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.comboRollover[Theme.style]);
            this.paintFocus.setSelected(Theme.comboFocus[Theme.style]);
            ControlPanel.this.comboBg.update();
            ControlPanel.this.comboText.update();
            ControlPanel.this.comboSelectedText.update();
            ControlPanel.this.comboArrowField.update();
            ControlPanel.this.comboSelectedBg.update();
            ControlPanel.this.comboBorder.update();
            ControlPanel.this.comboDark.update();
            ControlPanel.this.comboLight.update();
            ControlPanel.this.comboBorderDisabled.update();
            ControlPanel.this.comboDisabledDark.update();
            ControlPanel.this.comboDisabledLight.update();
            ControlPanel.this.comboButt.update();
            ControlPanel.this.comboButtRollover.update();
            ControlPanel.this.comboButtDisabled.update();
            ControlPanel.this.comboButtPressed.update();
            ControlPanel.this.comboButtBorder.update();
            ControlPanel.this.comboButtDark.update();
            ControlPanel.this.comboButtLight.update();
            ControlPanel.this.comboButtBorderDisabled.update();
            ControlPanel.this.comboButtDarkDisabled.update();
            ControlPanel.this.comboButtLightDisabled.update();
            ControlPanel.this.comboArrowField.update();
            ControlPanel.this.comboArrowDisabled.update();
            ControlPanel.this.comboSpreadDark.init();
            ControlPanel.this.comboSpreadLight.init();
            ControlPanel.this.comboSpreadDarkDisabled.init();
            ControlPanel.this.comboSpreadLightDisabled.init();
            this.mTop.setValue(new Integer(Theme.comboInsets[Theme.style].top));
            this.mLeft.setValue(new Integer(Theme.comboInsets[Theme.style].left));
            this.mBottom.setValue(new Integer(Theme.comboInsets[Theme.style].bottom));
            this.mRight.setValue(new Integer(Theme.comboInsets[Theme.style].right));
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.comboRollover[Theme.style] = this.rolloverEnabled.isSelected();
            Theme.comboFocus[Theme.style] = this.paintFocus.isSelected();
            Theme.comboInsets[Theme.style] = new Insets((int)this.mTop.getValue(), (int)this.mLeft.getValue(), (int)this.mBottom.getValue(), (int)this.mRight.getValue());
        }
    }
    
    class ProgressCP extends JPanel
    {
        private boolean inited;
        
        ProgressCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Track Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.progressTrack = new SBField(Theme.progressTrackColor, true);
            panel.add(ControlPanel.this.progressTrack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Display Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.progressField = new SBField(Theme.progressColor, true);
            panel.add(ControlPanel.this.progressField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.progressBorder = new SBField(Theme.progressBorderColor);
            panel.add(ControlPanel.this.progressBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.progressDark = new SBField(Theme.progressDarkColor);
            panel.add(ControlPanel.this.progressDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.progressLight = new SBField(Theme.progressLightColor);
            panel.add(ControlPanel.this.progressLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Text Forecolor"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.progressSelectFore = new SBField(Theme.progressSelectForeColor);
            panel.add(ControlPanel.this.progressSelectFore, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Text Backcolor"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.progressSelectBack = new SBField(Theme.progressSelectBackColor);
            panel.add(ControlPanel.this.progressSelectBack, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.progressField.update();
            ControlPanel.this.progressTrack.update();
            ControlPanel.this.progressBorder.update();
            ControlPanel.this.progressDark.update();
            ControlPanel.this.progressLight.update();
            ControlPanel.this.progressSelectFore.update();
            ControlPanel.this.progressSelectBack.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class SeparatorCP extends JPanel
    {
        private boolean inited;
        
        SeparatorCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Separator Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sepDark = new SBField(Theme.sepDarkColor, true);
            panel.add(ControlPanel.this.sepDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Separator Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sepLight = new SBField(Theme.sepLightColor, true, true);
            panel.add(ControlPanel.this.sepLight, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.sepDark.update();
            ControlPanel.this.sepLight.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class ToolTipCP extends JPanel
    {
        private boolean inited;
        
        ToolTipCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipBorder = new SBField(Theme.tipBorderColor, true);
            panel.add(ControlPanel.this.tipBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipBorderDis = new SBField(Theme.tipBorderDis, true);
            panel.add(ControlPanel.this.tipBorderDis, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Background Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipBg = new SBField(Theme.tipBgColor, true);
            panel.add(ControlPanel.this.tipBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipBgDis = new SBField(Theme.tipBgDis, true);
            panel.add(ControlPanel.this.tipBgDis, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Text Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipText = new SBField(Theme.tipTextColor, true);
            panel.add(ControlPanel.this.tipText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Text"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tipTextDis = new SBField(Theme.tipTextDis, true);
            panel.add(ControlPanel.this.tipTextDis, gridBagConstraints);
            final CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createEmptyBorder(2, 4, 2, 4));
            final JPanel panel2 = new JPanel(new BorderLayout(12, 0));
            final JLabel label = new JLabel("Display Enabled TT");
            label.setFont(label.getFont().deriveFont(1));
            label.setToolTipText("Enabled ToolTip");
            label.setBorder(compoundBorder);
            panel2.add(label, "Center");
            final JLabel label2 = new JLabel("Display Disabled TT");
            label2.setFont(label.getFont().deriveFont(1));
            label2.setToolTipText("Disabled ToolTip");
            label2.setBorder(compoundBorder);
            label2.setEnabled(false);
            panel2.add(label2, "East");
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(12, 4, 0, 4);
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 10;
            panel.add(panel2, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.tipBg.update();
            ControlPanel.this.tipBgDis.update();
            ControlPanel.this.tipBorder.update();
            ControlPanel.this.tipBorderDis.update();
            ControlPanel.this.tipText.update();
            ControlPanel.this.tipTextDis.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class MiscCP extends JPanel
    {
        private boolean inited;
        
        MiscCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("TitledBorder Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.titledBorderColor = new SBField(Theme.titledBorderColor, true);
            panel.add(ControlPanel.this.titledBorderColor, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("EditorPane Bg Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.editorPaneBg = new SBField(Theme.editorPaneBgColor, true);
            panel.add(ControlPanel.this.editorPaneBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("TextPane Bg Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.textPaneBg = new SBField(Theme.textPaneBgColor, true);
            panel.add(ControlPanel.this.textPaneBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("DesktopPane Bg Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.desktopPaneBg = new SBField(Theme.desktopPaneBgColor, true);
            panel.add(ControlPanel.this.desktopPaneBg, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.titledBorderColor.update();
            ControlPanel.this.editorPaneBg.update();
            ControlPanel.this.textPaneBg.update();
            ControlPanel.this.desktopPaneBg.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class ListCP extends JPanel
    {
        private boolean inited;
        
        ListCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.listBg = new SBField(Theme.listBgColor, true);
            panel.add(ControlPanel.this.listBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.listText = new SBField(Theme.listTextColor, true);
            panel.add(ControlPanel.this.listText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            panel.add(new JLabel("Selected Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.listSelectedBg = new SBField(Theme.listSelectedBgColor, true);
            panel.add(ControlPanel.this.listSelectedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Selected Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.listSelectedText = new SBField(Theme.listSelectedTextColor, true);
            panel.add(ControlPanel.this.listSelectedText, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.listText.update();
            ControlPanel.this.listBg.update();
            ControlPanel.this.listSelectedText.update();
            ControlPanel.this.listSelectedBg.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class TextCP extends JPanel
    {
        private JSpinner mTop;
        private JSpinner mLeft;
        private JSpinner mBottom;
        private JSpinner mRight;
        private boolean inited;
        
        TextCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Text Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.textBg = new SBField(Theme.textBgColor, true);
            panel.add(ControlPanel.this.textBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Text Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.textText = new SBField(Theme.textTextColor, true);
            panel.add(ControlPanel.this.textText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Caret Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.textCaret = new SBField(Theme.textCaretColor, true);
            panel.add(ControlPanel.this.textCaret, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Selected Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textSelectedBg = new SBField(Theme.textSelectedBgColor, true);
            panel.add(ControlPanel.this.textSelectedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Selected Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textSelectedText = new SBField(Theme.textSelectedTextColor, true);
            panel.add(ControlPanel.this.textSelectedText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textDisabledBg = new SBField(Theme.textDisabledBgColor);
            panel.add(ControlPanel.this.textDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textBorder = new SBField(Theme.textBorderColor);
            panel.add(ControlPanel.this.textBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textDark = new SBField(Theme.textBorderDarkColor, false, true);
            panel.add(ControlPanel.this.textDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textLight = new SBField(Theme.textBorderLightColor, false, true);
            panel.add(ControlPanel.this.textLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textBorderDisabled = new SBField(Theme.textBorderDisabledColor);
            panel.add(ControlPanel.this.textBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textDisabledDark = new SBField(Theme.textBorderDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.textDisabledDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.textDisabledLight = new SBField(Theme.textBorderLightDisabledColor, false, true);
            panel.add(ControlPanel.this.textDisabledLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 6;
            gridBagConstraints.insets = new Insets(2, 16, 0, 4);
            final JPanel panel2 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
            gridBagConstraints26.anchor = 18;
            gridBagConstraints26.fill = 2;
            gridBagConstraints26.gridx = 0;
            gridBagConstraints26.gridy = 1;
            gridBagConstraints26.insets = new Insets(0, 2, 0, 2);
            (this.mLeft = new JSpinner(new SpinnerNumberModel(16, 2, 24, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mLeft, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints26;
            ++gridBagConstraints27.gridx;
            gridBagConstraints26.gridy = 0;
            (this.mTop = new JSpinner(new SpinnerNumberModel(2, 1, 8, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mTop, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints26;
            ++gridBagConstraints28.gridy;
            panel2.add(new JLabel("Insets"), gridBagConstraints26);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints26;
            ++gridBagConstraints29.gridy;
            (this.mBottom = new JSpinner(new SpinnerNumberModel(3, 1, 8, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mBottom, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints26;
            ++gridBagConstraints30.gridx;
            gridBagConstraints26.gridy = 1;
            (this.mRight = new JSpinner(new SpinnerNumberModel(16, 2, 24, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mRight, gridBagConstraints26);
            panel.add(panel2, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.mTop.setValue(new Integer(Theme.textInsets[Theme.style].top));
            this.mLeft.setValue(new Integer(Theme.textInsets[Theme.style].left));
            this.mBottom.setValue(new Integer(Theme.textInsets[Theme.style].bottom));
            this.mRight.setValue(new Integer(Theme.textInsets[Theme.style].right));
            ControlPanel.this.textText.update();
            ControlPanel.this.textCaret.update();
            ControlPanel.this.textSelectedText.update();
            ControlPanel.this.textBg.update();
            ControlPanel.this.textSelectedBg.update();
            ControlPanel.this.textDisabledBg.update();
            ControlPanel.this.textBorder.update();
            ControlPanel.this.textDark.update();
            ControlPanel.this.textLight.update();
            ControlPanel.this.textDisabledDark.update();
            ControlPanel.this.textDisabledLight.update();
            ControlPanel.this.textBorderDisabled.update();
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.textInsets[Theme.style] = new Insets((int)this.mTop.getValue(), (int)this.mLeft.getValue(), (int)this.mBottom.getValue(), (int)this.mRight.getValue());
        }
    }
    
    class TabbedPaneCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private JCheckBox focusEnabled;
        private JCheckBox ignoreSelectedBg;
        private JCheckBox fixedTabs;
        private JSpinner tabTop;
        private JSpinner tabLeft;
        private JSpinner tabBottom;
        private JSpinner tabRight;
        private JSpinner areaTop;
        private JSpinner areaLeft;
        private JSpinner areaBottom;
        private JSpinner areaRight;
        private boolean inited;
        
        TabbedPaneCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Pane Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tabPaneBorder = new SBField(Theme.tabPaneBorderColor, true);
            panel.add(ControlPanel.this.tabPaneBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Pane Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tabPaneDark = new SBField(Theme.tabPaneDarkColor, true, true);
            panel.add(ControlPanel.this.tabPaneDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Pane Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tabPaneLight = new SBField(Theme.tabPaneLightColor, true, true);
            panel.add(ControlPanel.this.tabPaneLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Unselected Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabNormalBg = new SBField(Theme.tabNormalColor, true);
            panel.add(ControlPanel.this.tabNormalBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Selected Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabSelectedBg = new SBField(Theme.tabSelectedColor, true);
            panel.add(ControlPanel.this.tabSelectedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Rollover Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabRoll = new SBField(Theme.tabRolloverColor, true);
            panel.add(ControlPanel.this.tabRoll, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabDisabled = new SBField(Theme.tabDisabledColor);
            panel.add(ControlPanel.this.tabDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Selected Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabDisabledSelected = new SBField(Theme.tabDisabledSelectedColor);
            panel.add(ControlPanel.this.tabDisabledSelected, gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Text Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabDisabledText = new SBField(Theme.tabDisabledTextColor);
            panel.add(ControlPanel.this.tabDisabledText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Tab Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabBorder = new SBField(Theme.tabBorderColor, true);
            panel.add(ControlPanel.this.tabBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Tab Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabDark = new SBField(Theme.tabDarkColor, true, true);
            panel.add(ControlPanel.this.tabDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Tab Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tabLight = new SBField(Theme.tabLightColor, true, true);
            panel.add(ControlPanel.this.tabLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 11;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Tab Insets"), gridBagConstraints);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridheight = 7;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            final JPanel panel2 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
            gridBagConstraints26.anchor = 18;
            gridBagConstraints26.fill = 2;
            gridBagConstraints26.gridx = 0;
            gridBagConstraints26.gridy = 1;
            gridBagConstraints26.insets = new Insets(0, 2, 0, 2);
            (this.tabLeft = new JSpinner(new SpinnerNumberModel(6, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.tabLeft, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints26;
            ++gridBagConstraints27.gridx;
            gridBagConstraints26.gridy = 0;
            (this.tabTop = new JSpinner(new SpinnerNumberModel(1, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.tabTop, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints26;
            gridBagConstraints28.gridy += 2;
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints26;
            ++gridBagConstraints29.gridy;
            (this.tabBottom = new JSpinner(new SpinnerNumberModel(4, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.tabBottom, gridBagConstraints26);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints26;
            ++gridBagConstraints30.gridx;
            gridBagConstraints26.gridy = 1;
            (this.tabRight = new JSpinner(new SpinnerNumberModel(6, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.tabRight, gridBagConstraints26);
            panel.add(panel2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 11;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Tab Area Insets"), gridBagConstraints);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridheight = 7;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            final JPanel panel3 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
            gridBagConstraints32.anchor = 18;
            gridBagConstraints32.fill = 2;
            gridBagConstraints32.gridx = 0;
            gridBagConstraints32.gridy = 1;
            gridBagConstraints32.insets = new Insets(0, 2, 0, 2);
            (this.areaLeft = new JSpinner(new SpinnerNumberModel(2, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.areaLeft, gridBagConstraints32);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints32;
            ++gridBagConstraints33.gridx;
            gridBagConstraints32.gridy = 0;
            (this.areaTop = new JSpinner(new SpinnerNumberModel(6, 2, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.areaTop, gridBagConstraints32);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints32;
            gridBagConstraints34.gridy += 2;
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints32;
            ++gridBagConstraints35.gridy;
            (this.areaBottom = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1))).setEnabled(false);
            panel3.add(this.areaBottom, gridBagConstraints32);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints32;
            ++gridBagConstraints36.gridx;
            gridBagConstraints32.gridy = 1;
            (this.areaRight = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel3.add(this.areaRight, gridBagConstraints32);
            panel.add(panel3, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.insets = new Insets(8, 4, 0, 4);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 5;
            final JPanel panel4 = new JPanel(new FlowLayout(0, 8, 0));
            (this.rolloverEnabled = new JCheckBox("Paint Rollover", true)).addActionListener(ControlPanel.this.checkAction);
            panel4.add(this.rolloverEnabled);
            (this.focusEnabled = new JCheckBox("Paint Focus", true)).addActionListener(ControlPanel.this.checkAction);
            panel4.add(this.focusEnabled);
            (this.ignoreSelectedBg = new JCheckBox("Ignore Selected Bg", true)).addActionListener(new CheckUpdateAction());
            panel4.add(this.ignoreSelectedBg, "Center");
            (this.fixedTabs = new JCheckBox("Fixed Tab Positions", true)).addActionListener(new CheckUpdateAction());
            panel4.add(this.fixedTabs);
            panel.add(panel4, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.tabRollover[Theme.style]);
            this.focusEnabled.setSelected(Theme.tabFocus[Theme.style]);
            this.ignoreSelectedBg.setSelected(Theme.ignoreSelectedBg[Theme.style]);
            this.fixedTabs.setSelected(Theme.fixedTabs[Theme.style]);
            ControlPanel.this.tabPaneBorder.update();
            ControlPanel.this.tabPaneDark.update();
            ControlPanel.this.tabPaneLight.update();
            ControlPanel.this.tabNormalBg.update();
            ControlPanel.this.tabSelectedBg.update();
            ControlPanel.this.tabDisabled.update();
            ControlPanel.this.tabDisabledSelected.update();
            ControlPanel.this.tabDisabledText.update();
            ControlPanel.this.tabBorder.update();
            ControlPanel.this.tabDark.update();
            ControlPanel.this.tabLight.update();
            ControlPanel.this.tabRoll.update();
            this.tabTop.setValue(new Integer(Theme.tabInsets[Theme.style].top));
            this.tabLeft.setValue(new Integer(Theme.tabInsets[Theme.style].left));
            this.tabBottom.setValue(new Integer(Theme.tabInsets[Theme.style].bottom));
            this.tabRight.setValue(new Integer(Theme.tabInsets[Theme.style].right));
            this.areaTop.setValue(new Integer(Theme.tabAreaInsets[Theme.style].top));
            this.areaLeft.setValue(new Integer(Theme.tabAreaInsets[Theme.style].left));
            this.areaBottom.setValue(new Integer(Theme.tabAreaInsets[Theme.style].bottom));
            this.areaRight.setValue(new Integer(Theme.tabAreaInsets[Theme.style].right));
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.tabRollover[Theme.style] = this.rolloverEnabled.isSelected();
            Theme.tabFocus[Theme.style] = this.focusEnabled.isSelected();
            Theme.ignoreSelectedBg[Theme.style] = this.ignoreSelectedBg.isSelected();
            Theme.fixedTabs[Theme.style] = this.fixedTabs.isSelected();
            Theme.tabInsets[Theme.style] = new Insets((int)this.tabTop.getValue(), (int)this.tabLeft.getValue(), (int)this.tabBottom.getValue(), (int)this.tabRight.getValue());
            Theme.tabAreaInsets[Theme.style] = new Insets((int)this.areaTop.getValue(), (int)this.areaLeft.getValue(), (int)this.areaBottom.getValue(), (int)this.areaRight.getValue());
        }
        
        int getFirstTabDistance() {
            return 2;
        }
    }
    
    class TreeCP extends JPanel
    {
        private boolean inited;
        
        TreeCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Tree Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.treeBg = new SBField(Theme.treeBgColor, true);
            panel.add(ControlPanel.this.treeBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Text Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.treeTextBg = new SBField(Theme.treeTextBgColor, true);
            panel.add(ControlPanel.this.treeTextBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Text Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.treeText = new SBField(Theme.treeTextColor, true);
            panel.add(ControlPanel.this.treeText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Selected Text Bg"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.treeSelectedBg = new SBField(Theme.treeSelectedBgColor, true);
            panel.add(ControlPanel.this.treeSelectedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Selected Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.treeSelectedText = new SBField(Theme.treeSelectedTextColor, true);
            panel.add(ControlPanel.this.treeSelectedText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Line Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.treeLine = new SBField(Theme.treeLineColor, true);
            panel.add(ControlPanel.this.treeLine, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.treeText.update();
            ControlPanel.this.treeSelectedText.update();
            ControlPanel.this.treeBg.update();
            ControlPanel.this.treeTextBg.update();
            ControlPanel.this.treeSelectedBg.update();
            ControlPanel.this.treeLine.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class MenuCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private boolean inited;
        
        MenuCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("MenuBar Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.menuBar = new SBField(Theme.menuBarColor, true);
            panel.add(ControlPanel.this.menuBar, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Popup Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.menuPopup = new SBField(Theme.menuPopupColor);
            panel.add(ControlPanel.this.menuPopup, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            gridBagConstraints5.gridy += 2;
            gridBagConstraints.insets = new Insets(0, 4, 0, 4);
            gridBagConstraints.gridheight = 2;
            (this.rolloverEnabled = new JCheckBox("Paint Rollover", true)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.rolloverEnabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Popup Inner Hilight"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuInnerHilight = new SBField(Theme.menuInnerHilightColor);
            panel.add(ControlPanel.this.menuInnerHilight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Popup Inner Shadow"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuInnerShadow = new SBField(Theme.menuInnerShadowColor);
            panel.add(ControlPanel.this.menuInnerShadow, gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Popup Outer Hilight"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuOuterHilight = new SBField(Theme.menuOuterHilightColor);
            panel.add(ControlPanel.this.menuOuterHilight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Popup Outer Shadow"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuOuterShadow = new SBField(Theme.menuOuterShadowColor);
            panel.add(ControlPanel.this.menuOuterShadow, gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Rollover Back Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuRolloverBg = new SBField(Theme.menuRolloverBgColor);
            panel.add(ControlPanel.this.menuRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Rollover Fore Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuRolloverFg = new SBField(Theme.menuRolloverFgColor);
            panel.add(ControlPanel.this.menuRolloverFg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Fore Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuDisabledFg = new SBField(Theme.menuDisabledFgColor, true);
            panel.add(ControlPanel.this.menuDisabledFg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Menu Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuBorder = new SBField(Theme.menuBorderColor);
            panel.add(ControlPanel.this.menuBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Menu Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuDark = new SBField(Theme.menuDarkColor, false, true);
            panel.add(ControlPanel.this.menuDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Menu Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuLight = new SBField(Theme.menuLightColor, false, true);
            panel.add(ControlPanel.this.menuLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Selected Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuItemRollover = new SBField(Theme.menuItemRolloverColor);
            panel.add(ControlPanel.this.menuItemRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Selected Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuSelectedText = new SBField(Theme.menuSelectedTextColor);
            panel.add(ControlPanel.this.menuSelectedText, gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Icon Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuIcon = new SBField(Theme.menuIconColor);
            panel.add(ControlPanel.this.menuIcon, gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Icon Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuIconRollover = new SBField(Theme.menuIconRolloverColor);
            panel.add(ControlPanel.this.menuIconRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Icon Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuIconDisabled = new SBField(Theme.menuIconDisabledColor);
            panel.add(ControlPanel.this.menuIconDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Icon Disabled Shadow Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuIconShadow = new SBField(Theme.menuIconShadowColor, false, true);
            panel.add(ControlPanel.this.menuIconShadow, gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Separator Dark Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints39 = gridBagConstraints;
            ++gridBagConstraints39.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuSepDark = new SBField(Theme.menuSepDarkColor);
            panel.add(ControlPanel.this.menuSepDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints40 = gridBagConstraints;
            ++gridBagConstraints40.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Separator Light Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints41 = gridBagConstraints;
            ++gridBagConstraints41.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.menuSepLight = new SBField(Theme.menuSepLightColor, false, true);
            panel.add(ControlPanel.this.menuSepLight, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.menuSelectedText.update();
            ControlPanel.this.menuRolloverBg.update();
            ControlPanel.this.menuRolloverFg.update();
            ControlPanel.this.menuDisabledFg.update();
            ControlPanel.this.menuBar.update();
            ControlPanel.this.menuBorder.update();
            ControlPanel.this.menuDark.update();
            ControlPanel.this.menuLight.update();
            ControlPanel.this.menuPopup.update();
            ControlPanel.this.menuItemRollover.update();
            ControlPanel.this.menuInnerHilight.update();
            ControlPanel.this.menuInnerShadow.update();
            ControlPanel.this.menuOuterHilight.update();
            ControlPanel.this.menuOuterShadow.update();
            ControlPanel.this.menuIcon.update();
            ControlPanel.this.menuIconRollover.update();
            ControlPanel.this.menuIconDisabled.update();
            ControlPanel.this.menuIconShadow.update();
            ControlPanel.this.menuSepDark.update();
            ControlPanel.this.menuSepLight.update();
            this.rolloverEnabled.setSelected(Theme.menuRollover[Theme.style]);
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.menuRollover[Theme.style] = this.rolloverEnabled.isSelected();
        }
    }
    
    class SpinnerCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private boolean inited;
        
        SpinnerCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.spinnerButtField = new SBField(Theme.spinnerButtColor);
            panel.add(ControlPanel.this.spinnerButtField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.spinnerButtRolloverBg = new SBField(Theme.spinnerButtRolloverColor);
            panel.add(ControlPanel.this.spinnerButtRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Presssed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.spinnerButtPressedBg = new SBField(Theme.spinnerButtPressedColor);
            panel.add(ControlPanel.this.spinnerButtPressedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.spinnerButtDisabledBg = new SBField(Theme.spinnerButtDisabledColor);
            panel.add(ControlPanel.this.spinnerButtDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.spinnerSpreadLight = new SpreadControl(Theme.spinnerSpreadLight, 20, 5), gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.spinnerSpreadDark = new SpreadControl(Theme.spinnerSpreadDark, 20, 5), gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.spinnerSpreadLightDisabled = new SpreadControl(Theme.spinnerSpreadLightDisabled, 20, 5), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.spinnerSpreadDarkDisabled = new SpreadControl(Theme.spinnerSpreadDarkDisabled, 20, 5), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerBorder = new SBField(Theme.spinnerBorderColor);
            panel.add(ControlPanel.this.spinnerBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerDark = new SBField(Theme.spinnerDarkColor, false, true);
            panel.add(ControlPanel.this.spinnerDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerLight = new SBField(Theme.spinnerLightColor, false, true);
            panel.add(ControlPanel.this.spinnerLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerDisabledBorder = new SBField(Theme.spinnerBorderDisabledColor);
            panel.add(ControlPanel.this.spinnerDisabledBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerDisabledDark = new SBField(Theme.spinnerDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.spinnerDisabledDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerDisabledLight = new SBField(Theme.spinnerLightDisabledColor, false, true);
            panel.add(ControlPanel.this.spinnerDisabledLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Arrow Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerArrowField = new SBField(Theme.spinnerArrowColor);
            panel.add(ControlPanel.this.spinnerArrowField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Arrow"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.spinnerArrowDisabled = new SBField(Theme.spinnerArrowDisabledColor);
            panel.add(ControlPanel.this.spinnerArrowDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            gridBagConstraints33.gridy += 2;
            gridBagConstraints.gridheight = 2;
            gridBagConstraints.insets = new Insets(0, 8, 0, 4);
            (this.rolloverEnabled = new JCheckBox("Paint Rollover Border", true)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.rolloverEnabled, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.spinnerRollover[Theme.style]);
            ControlPanel.this.spinnerButtField.update();
            ControlPanel.this.spinnerArrowField.update();
            ControlPanel.this.spinnerButtRolloverBg.update();
            ControlPanel.this.spinnerButtPressedBg.update();
            ControlPanel.this.spinnerButtDisabledBg.update();
            ControlPanel.this.spinnerBorder.update();
            ControlPanel.this.spinnerDark.update();
            ControlPanel.this.spinnerLight.update();
            ControlPanel.this.spinnerDisabledBorder.update();
            ControlPanel.this.spinnerDisabledDark.update();
            ControlPanel.this.spinnerDisabledLight.update();
            ControlPanel.this.spinnerArrowDisabled.update();
            ControlPanel.this.spinnerSpreadDark.init();
            ControlPanel.this.spinnerSpreadLight.init();
            ControlPanel.this.spinnerSpreadDarkDisabled.init();
            ControlPanel.this.spinnerSpreadLightDisabled.init();
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.spinnerRollover[Theme.style] = this.rolloverEnabled.isSelected();
        }
    }
    
    class TableCP extends JPanel
    {
        private JCheckBox focusEnabled;
        private boolean inited;
        private final /* synthetic */ ControlPanel this$0;
        
        TableCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Background Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tableBack = new SBField(Theme.tableBackColor, true);
            panel.add(ControlPanel.this.tableBack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Grid Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.tableGrid = new SBField(Theme.tableGridColor, true);
            panel.add(ControlPanel.this.tableGrid, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableBorderDark = new SBField(Theme.tableBorderDarkColor);
            panel.add(ControlPanel.this.tableBorderDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableBorderLight = new SBField(Theme.tableBorderLightColor);
            panel.add(ControlPanel.this.tableBorderLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Header Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderBack = new SBField(Theme.tableHeaderBackColor, true);
            panel.add(ControlPanel.this.tableHeaderBack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("<html>H. Rollover Background <b>*</b>"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderRolloverBack = new SBField(Theme.tableHeaderRolloverBackColor);
            panel.add(ControlPanel.this.tableHeaderRolloverBack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("<html>Header Rollover Color <b>*</b>"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderRollover = new SBField(Theme.tableHeaderRolloverColor, true);
            panel.add(ControlPanel.this.tableHeaderRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            final JLabel label = new JLabel("<html><b>*</b> Considered only with tables implementing<br>de.muntjak.tinylookandfeel.table.SortableTableData");
            label.setOpaque(true);
            label.setBackground(ControlPanel.infoColor);
            label.setForeground(Color.BLACK);
            label.setBorder(ControlPanel.infoBorder);
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.insets = new Insets(3, 8, 0, 4);
            panel.add(label, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 1;
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Header Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderDark = new SBField(Theme.tableHeaderDarkColor, true);
            panel.add(ControlPanel.this.tableHeaderDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Header Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderLight = new SBField(Theme.tableHeaderLightColor, true);
            panel.add(ControlPanel.this.tableHeaderLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("<html>Header Arrow Color <b>*</b>"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableHeaderArrow = new SBField(Theme.tableHeaderArrowColor);
            panel.add(ControlPanel.this.tableHeaderArrow, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Selected Cell Background"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableSelectedBack = new SBField(Theme.tableSelectedBackColor, true);
            panel.add(ControlPanel.this.tableSelectedBack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Selected Cell Foreground"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.tableSelectedFore = new SBField(Theme.tableSelectedForeColor, true);
            panel.add(ControlPanel.this.tableSelectedFore, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 16, 0, 4);
            gridBagConstraints.anchor = 18;
            gridBagConstraints.gridheight = 6;
            final JPanel panel2 = new JPanel(new GridLayout(3, 1));
            panel2.setBorder(new TitledBorder("Not saved"));
            final JCheckBox checkBox = new JCheckBox("Sortable table model", true);
            checkBox.addActionListener(new ActionListener() {
                private final /* synthetic */ TableCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (((JCheckBox)actionEvent.getSource()).isSelected()) {
                        this.this$1.this$0.exampleTable.setModel(new TinyTableModel());
                        this.this$1.this$0.exampleTable.setColumnSelectionInterval(2, 2);
                        this.this$1.this$0.exampleTable.setRowSelectionInterval(0, 2);
                    }
                    else {
                        this.this$1.this$0.exampleTable.setModel(new NonSortableTableModel());
                        this.this$1.this$0.exampleTable.setColumnSelectionInterval(2, 2);
                        this.this$1.this$0.exampleTable.setRowSelectionInterval(0, 3);
                    }
                }
            });
            panel2.add(checkBox);
            final JCheckBox checkBox2 = new JCheckBox("Column reordering allowed", true);
            checkBox2.addActionListener(new ActionListener() {
                private final /* synthetic */ TableCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.exampleTable.getTableHeader().setReorderingAllowed(((JCheckBox)actionEvent.getSource()).isSelected());
                }
            });
            panel2.add(checkBox2);
            final JCheckBox checkBox3 = new JCheckBox("Column resizing allowed", true);
            checkBox3.addActionListener(new ActionListener() {
                private final /* synthetic */ TableCP this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.this$0.exampleTable.getTableHeader().setResizingAllowed(((JCheckBox)actionEvent.getSource()).isSelected());
                }
            });
            panel2.add(checkBox3);
            panel.add(panel2, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            ControlPanel.this.tableBack.update();
            ControlPanel.this.tableHeaderBack.update();
            ControlPanel.this.tableHeaderRolloverBack.update();
            ControlPanel.this.tableHeaderRollover.update();
            ControlPanel.this.tableHeaderArrow.update();
            ControlPanel.this.tableGrid.update();
            ControlPanel.this.tableSelectedBack.update();
            ControlPanel.this.tableSelectedFore.update();
            ControlPanel.this.tableBorderDark.update();
            ControlPanel.this.tableBorderLight.update();
            ControlPanel.this.tableHeaderDark.update();
            ControlPanel.this.tableHeaderLight.update();
            this.inited = true;
        }
        
        void updateTheme() {
        }
    }
    
    class ToolBarCP extends JPanel
    {
        private JCheckBox focusEnabled;
        private JSpinner mTop;
        private JSpinner mLeft;
        private JSpinner mBottom;
        private JSpinner mRight;
        private boolean inited;
        
        ToolBarCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("ToolBar Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.toolBar = new SBField(Theme.toolBarColor, true);
            panel.add(ControlPanel.this.toolBar, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("ToolBar Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.toolBarLight = new SBField(Theme.toolBarLightColor, true);
            panel.add(ControlPanel.this.toolBarLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("ToolBar Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.toolBarDark = new SBField(Theme.toolBarDarkColor, true);
            panel.add(ControlPanel.this.toolBarDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolButt = new SBField(Theme.toolButtColor, true);
            panel.add(ControlPanel.this.toolButt, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Button Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolButtRollover = new SBField(Theme.toolButtRolloverColor);
            panel.add(ControlPanel.this.toolButtRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Button Pressed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolButtPressed = new SBField(Theme.toolButtPressedColor);
            panel.add(ControlPanel.this.toolButtPressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Button Selected Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolButtSelected = new SBField(Theme.toolButtSelectedColor, true);
            panel.add(ControlPanel.this.toolButtSelected, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Button Border Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorder = new SBField(Theme.toolBorderColor, true);
            panel.add(ControlPanel.this.toolBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorderRollover = new SBField(Theme.toolBorderRolloverColor);
            panel.add(ControlPanel.this.toolBorderRollover, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Pressed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorderPressed = new SBField(Theme.toolBorderPressedColor);
            panel.add(ControlPanel.this.toolBorderPressed, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Selected Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorderSelected = new SBField(Theme.toolBorderSelectedColor, true);
            panel.add(ControlPanel.this.toolBorderSelected, gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorderDark = new SBField(Theme.toolBorderDarkColor, true, true);
            panel.add(ControlPanel.this.toolBorderDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolBorderLight = new SBField(Theme.toolBorderLightColor, true, true);
            panel.add(ControlPanel.this.toolBorderLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            gridBagConstraints27.gridy += 2;
            gridBagConstraints.insets = new Insets(0, 8, 0, 4);
            gridBagConstraints.gridheight = 2;
            (this.focusEnabled = new JCheckBox("Paint Focus", true)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.focusEnabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Grip Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolGripDark = new SBField(Theme.toolGripDarkColor, true);
            panel.add(ControlPanel.this.toolGripDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Grip Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolGripLight = new SBField(Theme.toolGripLightColor, true);
            panel.add(ControlPanel.this.toolGripLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Separator Dark Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolSepDark = new SBField(Theme.toolSepDarkColor, true);
            panel.add(ControlPanel.this.toolSepDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Separator Light Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.toolSepLight = new SBField(Theme.toolSepLightColor, true, true);
            panel.add(ControlPanel.this.toolSepLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridx;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridheight = 7;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(0, 16, 0, 4);
            final JPanel panel2 = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
            gridBagConstraints37.anchor = 10;
            gridBagConstraints37.fill = 0;
            gridBagConstraints37.gridwidth = 3;
            gridBagConstraints37.gridx = 0;
            gridBagConstraints37.gridy = 0;
            gridBagConstraints37.insets = new Insets(0, 0, 4, 0);
            panel2.add(new JLabel("Button Margin"), gridBagConstraints37);
            gridBagConstraints37.anchor = 18;
            gridBagConstraints37.fill = 2;
            gridBagConstraints37.gridwidth = 1;
            gridBagConstraints37.gridy = 2;
            gridBagConstraints37.insets = new Insets(0, 2, 0, 2);
            (this.mLeft = new JSpinner(new SpinnerNumberModel(4, 1, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mLeft, gridBagConstraints37);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints37;
            ++gridBagConstraints38.gridx;
            gridBagConstraints37.gridy = 1;
            (this.mTop = new JSpinner(new SpinnerNumberModel(4, 1, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mTop, gridBagConstraints37);
            final GridBagConstraints gridBagConstraints39 = gridBagConstraints37;
            ++gridBagConstraints39.gridy;
            panel2.add(new JLabel("Margin"), gridBagConstraints37);
            final GridBagConstraints gridBagConstraints40 = gridBagConstraints37;
            ++gridBagConstraints40.gridy;
            (this.mBottom = new JSpinner(new SpinnerNumberModel(4, 1, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mBottom, gridBagConstraints37);
            final GridBagConstraints gridBagConstraints41 = gridBagConstraints37;
            ++gridBagConstraints41.gridx;
            gridBagConstraints37.gridy = 2;
            (this.mRight = new JSpinner(new SpinnerNumberModel(4, 1, 99, 1))).addChangeListener(ControlPanel.this.spinnerUpdateAction);
            panel2.add(this.mRight, gridBagConstraints37);
            panel.add(panel2, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.focusEnabled.setSelected(Theme.toolFocus[Theme.style]);
            ControlPanel.this.toolBar.update();
            ControlPanel.this.toolBarDark.update();
            ControlPanel.this.toolBarLight.update();
            ControlPanel.this.toolButt.update();
            ControlPanel.this.toolButtRollover.update();
            ControlPanel.this.toolButtPressed.update();
            ControlPanel.this.toolButtSelected.update();
            ControlPanel.this.toolBorder.update();
            ControlPanel.this.toolBorderRollover.update();
            ControlPanel.this.toolBorderPressed.update();
            ControlPanel.this.toolBorderSelected.update();
            ControlPanel.this.toolBorderDark.update();
            ControlPanel.this.toolBorderLight.update();
            ControlPanel.this.toolGripDark.update();
            ControlPanel.this.toolGripLight.update();
            ControlPanel.this.toolSepDark.update();
            ControlPanel.this.toolSepLight.update();
            this.mTop.setValue(new Integer(Theme.toolMarginTop[Theme.style]));
            this.mLeft.setValue(new Integer(Theme.toolMarginLeft[Theme.style]));
            this.mBottom.setValue(new Integer(Theme.toolMarginBottom[Theme.style]));
            this.mRight.setValue(new Integer(Theme.toolMarginRight[Theme.style]));
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.toolFocus[Theme.style] = this.focusEnabled.isSelected();
            Theme.toolMarginTop[Theme.style] = (int)this.mTop.getValue();
            Theme.toolMarginLeft[Theme.style] = (int)this.mLeft.getValue();
            Theme.toolMarginBottom[Theme.style] = (int)this.mBottom.getValue();
            Theme.toolMarginRight[Theme.style] = (int)this.mRight.getValue();
        }
    }
    
    class SliderCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private JCheckBox focusEnabled;
        private boolean inited;
        
        SliderCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Thumb Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sliderThumbField = new SBField(Theme.sliderThumbColor);
            panel.add(ControlPanel.this.sliderThumbField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sliderThumbRolloverBg = new SBField(Theme.sliderThumbRolloverColor);
            panel.add(ControlPanel.this.sliderThumbRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Presssed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sliderThumbPressedBg = new SBField(Theme.sliderThumbPressedColor);
            panel.add(ControlPanel.this.sliderThumbPressedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.sliderThumbDisabledBg = new SBField(Theme.sliderThumbDisabledColor);
            panel.add(ControlPanel.this.sliderThumbDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderBorder = new SBField(Theme.sliderBorderColor);
            panel.add(ControlPanel.this.sliderBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Dark Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderDark = new SBField(Theme.sliderDarkColor);
            panel.add(ControlPanel.this.sliderDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Light Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderLight = new SBField(Theme.sliderLightColor);
            panel.add(ControlPanel.this.sliderLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderDisabledBorder = new SBField(Theme.sliderBorderDisabledColor);
            panel.add(ControlPanel.this.sliderDisabledBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Dark Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderDisabledDark = new SBField(Theme.sliderDarkDisabledColor);
            panel.add(ControlPanel.this.sliderDisabledDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Light Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderDisabledLight = new SBField(Theme.sliderLightDisabledColor);
            panel.add(ControlPanel.this.sliderDisabledLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            gridBagConstraints21.gridy += 2;
            gridBagConstraints.insets = new Insets(0, 8, 0, 4);
            (this.rolloverEnabled = new JCheckBox("Paint Rollover", true)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.rolloverEnabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Track Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTrack = new SBField(Theme.sliderTrackColor);
            panel.add(ControlPanel.this.sliderTrack, gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTrackBorder = new SBField(Theme.sliderTrackBorderColor);
            panel.add(ControlPanel.this.sliderTrackBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTrackDark = new SBField(Theme.sliderTrackDarkColor);
            panel.add(ControlPanel.this.sliderTrackDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTrackLight = new SBField(Theme.sliderTrackLightColor);
            panel.add(ControlPanel.this.sliderTrackLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Ticks Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTick = new SBField(Theme.sliderTickColor);
            panel.add(ControlPanel.this.sliderTick, gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Ticks Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderTickDisabled = new SBField(Theme.sliderTickDisabledColor);
            panel.add(ControlPanel.this.sliderTickDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Focus Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.sliderFocusColor = new SBField(Theme.sliderFocusColor);
            panel.add(ControlPanel.this.sliderFocusColor, gridBagConstraints);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            (this.focusEnabled = new JCheckBox("Paint Focus", true)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.focusEnabled, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.sliderRolloverEnabled[Theme.style]);
            this.focusEnabled.setSelected(Theme.sliderFocusEnabled[Theme.style]);
            ControlPanel.this.sliderThumbField.update();
            ControlPanel.this.sliderThumbRolloverBg.update();
            ControlPanel.this.sliderThumbPressedBg.update();
            ControlPanel.this.sliderThumbDisabledBg.update();
            ControlPanel.this.sliderBorder.update();
            ControlPanel.this.sliderDark.update();
            ControlPanel.this.sliderLight.update();
            ControlPanel.this.sliderDisabledBorder.update();
            ControlPanel.this.sliderDisabledDark.update();
            ControlPanel.this.sliderDisabledLight.update();
            ControlPanel.this.sliderTrack.update();
            ControlPanel.this.sliderTrackBorder.update();
            ControlPanel.this.sliderTrackDark.update();
            ControlPanel.this.sliderTrackLight.update();
            ControlPanel.this.sliderTick.update();
            ControlPanel.this.sliderTickDisabled.update();
            ControlPanel.this.sliderFocusColor.update();
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.sliderRolloverEnabled[Theme.style] = this.rolloverEnabled.isSelected();
            Theme.sliderFocusEnabled[Theme.style] = this.focusEnabled.isSelected();
        }
    }
    
    class ScrollBarCP extends JPanel
    {
        private JCheckBox rolloverEnabled;
        private boolean inited;
        
        ScrollBarCP() {
            this.inited = false;
            this.setupUI();
        }
        
        private void setupUI() {
            this.setLayout(new FlowLayout(0, 2, 2));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 4, 0, 4);
            panel.add(new JLabel("Thumb Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.scrollThumbField = new SBField(Theme.scrollThumbColor);
            panel.add(ControlPanel.this.scrollThumbField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.scrollThumbRolloverBg = new SBField(Theme.scrollThumbRolloverColor);
            panel.add(ControlPanel.this.scrollThumbRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Presssed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.scrollThumbPressedBg = new SBField(Theme.scrollThumbPressedColor);
            panel.add(ControlPanel.this.scrollThumbPressedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridy;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            panel.add(new JLabel("Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.insets = new Insets(1, 4, 0, 4);
            ControlPanel.this.scrollThumbDisabledBg = new SBField(Theme.scrollThumbDisabledColor);
            panel.add(ControlPanel.this.scrollThumbDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            final JLabel label = new JLabel("<html>Grip Dark Color <b>*");
            label.setIconTextGap(2);
            label.setHorizontalTextPosition(10);
            label.setVerticalTextPosition(1);
            panel.add(label, gridBagConstraints);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollGripDark = new SBField(Theme.scrollGripDarkColor);
            panel.add(ControlPanel.this.scrollGripDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            final JLabel label2 = new JLabel("<html>Grip Light Col <b>*");
            label2.setIconTextGap(2);
            label2.setHorizontalTextPosition(10);
            label2.setVerticalTextPosition(1);
            panel.add(label2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            ++gridBagConstraints12.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollGripLight = new SBField(Theme.scrollGripLightColor);
            panel.add(ControlPanel.this.scrollGripLight, gridBagConstraints);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
            ++gridBagConstraints13.gridy;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.insets = new Insets(6, 8, 0, 4);
            final JLabel label3 = new JLabel("<html><b>*</b> Only saturation<br>and lightness<br>are considered.");
            label3.setVerticalTextPosition(1);
            label3.setBackground(ControlPanel.infoColor);
            label3.setForeground(Color.BLACK);
            label3.setOpaque(true);
            label3.setIconTextGap(2);
            label3.setBorder(ControlPanel.infoBorder);
            panel.add(label3, gridBagConstraints);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
            ++gridBagConstraints14.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Button Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
            ++gridBagConstraints15.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollButtField = new SBField(Theme.scrollButtColor);
            panel.add(ControlPanel.this.scrollButtField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Rollover Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints17 = gridBagConstraints;
            ++gridBagConstraints17.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollButtRolloverBg = new SBField(Theme.scrollButtRolloverColor);
            panel.add(ControlPanel.this.scrollButtRolloverBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints18 = gridBagConstraints;
            ++gridBagConstraints18.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Presssed Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints19 = gridBagConstraints;
            ++gridBagConstraints19.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollButtPressedBg = new SBField(Theme.scrollButtPressedColor);
            panel.add(ControlPanel.this.scrollButtPressedBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints20 = gridBagConstraints;
            ++gridBagConstraints20.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints21 = gridBagConstraints;
            ++gridBagConstraints21.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollButtDisabledBg = new SBField(Theme.scrollButtDisabledColor);
            panel.add(ControlPanel.this.scrollButtDisabledBg, gridBagConstraints);
            final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
            ++gridBagConstraints22.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Spread Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
            ++gridBagConstraints23.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.scrollSpreadLight = new SpreadControl(Theme.scrollSpreadLight, 20, 4), gridBagConstraints);
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Spread Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.scrollSpreadDark = new SpreadControl(Theme.scrollSpreadDark, 20, 4), gridBagConstraints);
            final GridBagConstraints gridBagConstraints26 = gridBagConstraints;
            ++gridBagConstraints26.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints27 = gridBagConstraints;
            ++gridBagConstraints27.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.scrollSpreadLightDisabled = new SpreadControl(Theme.scrollSpreadLightDisabled, 20, 4), gridBagConstraints);
            final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
            ++gridBagConstraints28.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled S. Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
            ++gridBagConstraints29.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            panel.add(ControlPanel.this.scrollSpreadDarkDisabled = new SpreadControl(Theme.scrollSpreadDarkDisabled, 20, 4), gridBagConstraints);
            final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
            ++gridBagConstraints30.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Border Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
            ++gridBagConstraints31.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollBorder = new SBField(Theme.scrollBorderColor);
            panel.add(ControlPanel.this.scrollBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
            ++gridBagConstraints32.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints33 = gridBagConstraints;
            ++gridBagConstraints33.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollDark = new SBField(Theme.scrollDarkColor, false, true);
            panel.add(ControlPanel.this.scrollDark, gridBagConstraints);
            final GridBagConstraints gridBagConstraints34 = gridBagConstraints;
            ++gridBagConstraints34.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Border Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints35 = gridBagConstraints;
            ++gridBagConstraints35.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollLight = new SBField(Theme.scrollLightColor);
            panel.add(ControlPanel.this.scrollLight, gridBagConstraints);
            gridBagConstraints.gridy = 7;
            gridBagConstraints.insets = new Insets(0, 8, 0, 4);
            (this.rolloverEnabled = new JCheckBox("Paint Rollover", false)).addActionListener(ControlPanel.this.checkAction);
            panel.add(this.rolloverEnabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints36 = gridBagConstraints;
            ++gridBagConstraints36.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Disabled Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints37 = gridBagConstraints;
            ++gridBagConstraints37.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollBorderDisabled = new SBField(Theme.scrollBorderDisabledColor);
            panel.add(ControlPanel.this.scrollBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints38 = gridBagConstraints;
            ++gridBagConstraints38.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Dark"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints39 = gridBagConstraints;
            ++gridBagConstraints39.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollDarkDisabled = new SBField(Theme.scrollDarkDisabledColor, false, true);
            panel.add(ControlPanel.this.scrollDarkDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints40 = gridBagConstraints;
            ++gridBagConstraints40.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Disabled Light"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints41 = gridBagConstraints;
            ++gridBagConstraints41.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollLightDisabled = new SBField(Theme.scrollLightDisabledColor);
            panel.add(ControlPanel.this.scrollLightDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints42 = gridBagConstraints;
            ++gridBagConstraints42.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Track Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints43 = gridBagConstraints;
            ++gridBagConstraints43.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.trackField = new SBField(Theme.scrollTrackColor);
            panel.add(ControlPanel.this.trackField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints44 = gridBagConstraints;
            ++gridBagConstraints44.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints45 = gridBagConstraints;
            ++gridBagConstraints45.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.trackDisabled = new SBField(Theme.scrollTrackDisabledColor);
            panel.add(ControlPanel.this.trackDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints46 = gridBagConstraints;
            ++gridBagConstraints46.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Border"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints47 = gridBagConstraints;
            ++gridBagConstraints47.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.trackBorder = new SBField(Theme.scrollTrackBorderColor);
            panel.add(ControlPanel.this.trackBorder, gridBagConstraints);
            final GridBagConstraints gridBagConstraints48 = gridBagConstraints;
            ++gridBagConstraints48.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Track Border Disabled"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints49 = gridBagConstraints;
            ++gridBagConstraints49.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.trackBorderDisabled = new SBField(Theme.scrollTrackBorderDisabledColor);
            panel.add(ControlPanel.this.trackBorderDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints50 = gridBagConstraints;
            ++gridBagConstraints50.gridx;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 8, 0, 4);
            panel.add(new JLabel("Arrow Color"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints51 = gridBagConstraints;
            ++gridBagConstraints51.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollArrowField = new SBField(Theme.scrollArrowColor);
            panel.add(ControlPanel.this.scrollArrowField, gridBagConstraints);
            final GridBagConstraints gridBagConstraints52 = gridBagConstraints;
            ++gridBagConstraints52.gridy;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("Arrow Disabled Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints53 = gridBagConstraints;
            ++gridBagConstraints53.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollArrowDisabled = new SBField(Theme.scrollArrowDisabledColor);
            panel.add(ControlPanel.this.scrollArrowDisabled, gridBagConstraints);
            final GridBagConstraints gridBagConstraints54 = gridBagConstraints;
            gridBagConstraints54.gridy += 3;
            gridBagConstraints.insets = new Insets(4, 8, 0, 4);
            panel.add(new JLabel("ScrollPane Border Col"), gridBagConstraints);
            final GridBagConstraints gridBagConstraints55 = gridBagConstraints;
            ++gridBagConstraints55.gridy;
            gridBagConstraints.insets = new Insets(1, 8, 0, 4);
            ControlPanel.this.scrollPane = new SBField(Theme.scrollPaneBorderColor);
            panel.add(ControlPanel.this.scrollPane, gridBagConstraints);
            this.add(panel);
        }
        
        void init(final boolean b) {
            if (this.inited && !b) {
                return;
            }
            this.rolloverEnabled.setSelected(Theme.scrollRollover[Theme.style]);
            ControlPanel.this.scrollThumbField.update();
            ControlPanel.this.scrollButtField.update();
            ControlPanel.this.scrollArrowField.update();
            ControlPanel.this.trackField.update();
            ControlPanel.this.scrollThumbRolloverBg.update();
            ControlPanel.this.scrollThumbPressedBg.update();
            ControlPanel.this.scrollThumbDisabledBg.update();
            ControlPanel.this.trackBorder.update();
            ControlPanel.this.scrollButtRolloverBg.update();
            ControlPanel.this.scrollButtPressedBg.update();
            ControlPanel.this.scrollButtDisabledBg.update();
            ControlPanel.this.trackDisabled.update();
            ControlPanel.this.trackBorderDisabled.update();
            ControlPanel.this.scrollArrowDisabled.update();
            ControlPanel.this.scrollGripDark.update();
            ControlPanel.this.scrollGripLight.update();
            ControlPanel.this.scrollBorder.update();
            ControlPanel.this.scrollDark.update();
            ControlPanel.this.scrollLight.update();
            ControlPanel.this.scrollBorderDisabled.update();
            ControlPanel.this.scrollDarkDisabled.update();
            ControlPanel.this.scrollLightDisabled.update();
            ControlPanel.this.scrollPane.update();
            ControlPanel.this.scrollSpreadDark.init();
            ControlPanel.this.scrollSpreadLight.init();
            ControlPanel.this.scrollSpreadDarkDisabled.init();
            ControlPanel.this.scrollSpreadLightDisabled.init();
            this.inited = true;
        }
        
        void updateTheme() {
            if (!this.inited || ControlPanel.this.resistUpdate) {
                return;
            }
            Theme.scrollRollover[Theme.style] = this.rolloverEnabled.isSelected();
        }
    }
    
    class FontPanel extends JPanel
    {
        private int type;
        private JComboBox fontFamilyCombo;
        private JComboBox fontSizeCombo;
        private JCheckBox bold;
        private SBField colorField;
        
        FontPanel(final int type) {
            this.type = type;
            this.setupUI();
        }
        
        private void setupUI() {
            final UpdateFontAction updateFontAction = new UpdateFontAction();
            FontUIResource fontUIResource;
            if (this.type == 1) {
                fontUIResource = Theme.plainFont[Theme.style].getFont();
            }
            else if (this.type == 2) {
                fontUIResource = Theme.boldFont[Theme.style].getFont();
            }
            else {
                fontUIResource = ControlPanel.this.selectedFont[Theme.style].getFont();
            }
            this.setLayout(new FlowLayout(0, 3, 1));
            if (this.type == 1) {
                this.setBorder(new TitledBorder("Plain Font"));
            }
            else if (this.type == 2) {
                this.setBorder(new TitledBorder("Bold Font"));
            }
            else {
                this.setBorder(new TitledBorder("Special Font"));
            }
            this.add(new JLabel("Family"));
            (this.fontFamilyCombo = this.createSchriftarten(fontUIResource)).addActionListener(updateFontAction);
            this.add(this.fontFamilyCombo);
            this.add(new JLabel("  Size"));
            (this.fontSizeCombo = this.createSchriftgroessen(fontUIResource)).addActionListener(updateFontAction);
            this.add(this.fontSizeCombo);
            this.add(new JLabel("    "));
            (this.bold = new JCheckBox("Bold", fontUIResource.isBold())).addActionListener(updateFontAction);
            this.add(this.bold);
            if (this.type == 3) {
                this.add(this.colorField = new SBField(ControlPanel.this.selectedFont[Theme.style].getColorReference(), true));
            }
        }
        
        public String getFontFamily() {
            return (String)this.fontFamilyCombo.getSelectedItem();
        }
        
        public int getFontSize() {
            return Integer.parseInt((String)this.fontSizeCombo.getSelectedItem());
        }
        
        public int getFontType() {
            if (this.bold.isSelected()) {
                return 1;
            }
            return 0;
        }
        
        public FontUIResource getCurrentFont() {
            return new FontUIResource(this.getFontFamily(), this.getFontType(), this.getFontSize());
        }
        
        public void init(final ColoredFont coloredFont) {
            ControlPanel.this.resistUpdate = true;
            this.fontSizeCombo.setSelectedItem(String.valueOf(coloredFont.getFont().getSize()));
            this.fontFamilyCombo.setSelectedItem(coloredFont.getFont().getFamily(Locale.GERMANY));
            this.bold.setSelected(coloredFont.getFont().isBold());
            ControlPanel.this.resistUpdate = false;
            if (this.colorField == null) {
                return;
            }
            ControlPanel.this.resistUpdate = true;
            this.colorField.setColorReference(coloredFont.getColorReference());
            ControlPanel.this.isPlainFont.setSelected(coloredFont.isPlainFont());
            ControlPanel.this.isBoldFont.setSelected(coloredFont.isBoldFont());
            ControlPanel.this.resistUpdate = false;
        }
        
        private JComboBox createSchriftarten(final Font font) {
            final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
            final TreeSet<String> set = new TreeSet<String>();
            for (int i = 0; i < allFonts.length; ++i) {
                set.add(allFonts[i].getFamily(Locale.GERMANY));
            }
            final JComboBox comboBox = new JComboBox<Object>(new Vector<Object>(set));
            for (int j = 0; j < comboBox.getItemCount(); ++j) {
                if (comboBox.getItemAt(j).equals(font.getFamily(Locale.GERMANY))) {
                    comboBox.setSelectedIndex(j);
                    break;
                }
            }
            return comboBox;
        }
        
        private JComboBox createSchriftgroessen(final Font font) {
            final JComboBox comboBox = new JComboBox((E[])new String[] { "10", "11", "12", "13", "14", "16", "18", "20", "22", "24" });
            switch (font.getSize()) {
                case 10: {
                    comboBox.setSelectedIndex(0);
                    break;
                }
                case 11: {
                    comboBox.setSelectedIndex(1);
                    break;
                }
                case 12: {
                    comboBox.setSelectedIndex(2);
                    break;
                }
                case 13: {
                    comboBox.setSelectedIndex(3);
                    break;
                }
                case 14: {
                    comboBox.setSelectedIndex(4);
                    break;
                }
                case 16: {
                    comboBox.setSelectedIndex(5);
                    break;
                }
                case 18: {
                    comboBox.setSelectedIndex(6);
                    break;
                }
                case 20: {
                    comboBox.setSelectedIndex(7);
                    break;
                }
                case 22: {
                    comboBox.setSelectedIndex(8);
                    break;
                }
                case 24: {
                    comboBox.setSelectedIndex(9);
                    break;
                }
            }
            comboBox.setMaximumRowCount(10);
            return comboBox;
        }
        
        class UpdateFontAction implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (ControlPanel.this.resistUpdate) {
                    return;
                }
                if (FontPanel.this.type == 3) {
                    ControlPanel.this.selectedFont[Theme.style].setPlainFont(false);
                    ControlPanel.this.selectedFont[Theme.style].setBoldFont(false);
                }
                ControlPanel.this.updateFont(FontPanel.this.type);
                ControlPanel.this.specialFontPanel.init(ControlPanel.this.selectedFont[Theme.style]);
            }
        }
    }
    
    class SetThemeAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.setTheme();
        }
    }
    
    class HSBPopupAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final int int1 = Integer.parseInt(actionEvent.getActionCommand());
            if (!ControlPanel.this.iconChecks[ControlPanel.this.selectedHSBField.index].isSelected()) {
                ControlPanel.this.iconChecks[ControlPanel.this.selectedHSBField.index].setSelected(true);
            }
            ControlPanel.this.selectedHSBField.setReference(int1, true);
            ControlPanel.this.updateThemeButton.setEnabled(true);
        }
    }
    
    class SBPopupAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.selectedSBField.getColorReference().setReference(Integer.parseInt(actionEvent.getActionCommand()));
            ControlPanel.this.selectedSBField.resetReference();
            ControlPanel.this.selectedSBField.update();
            ControlPanel.this.initPanels();
            ControlPanel.this.examplePanel.update(ControlPanel.this.selectedSBField.forceUpdate);
        }
    }
    
    class DerivedFontAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (ControlPanel.this.resistUpdate) {
                return;
            }
            if (actionEvent.getSource().equals(ControlPanel.this.isPlainFont)) {
                if (ControlPanel.this.isPlainFont.isSelected()) {
                    ControlPanel.this.isBoldFont.setSelected(false);
                    ControlPanel.this.selectedFont[Theme.style].setPlainFont(true);
                }
                else {
                    ControlPanel.this.selectedFont[Theme.style].setPlainFont(false);
                }
            }
            else if (actionEvent.getSource().equals(ControlPanel.this.isBoldFont)) {
                if (ControlPanel.this.isBoldFont.isSelected()) {
                    ControlPanel.this.isPlainFont.setSelected(false);
                    ControlPanel.this.selectedFont[Theme.style].setBoldFont(true);
                }
                else {
                    ControlPanel.this.selectedFont[Theme.style].setBoldFont(false);
                }
            }
            ControlPanel.this.specialFontPanel.init(ControlPanel.this.selectedFont[Theme.style]);
            ControlPanel.this.updateFont(3);
        }
    }
    
    class SelectSpecialFontAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.updateSpecialFont();
        }
    }
}
