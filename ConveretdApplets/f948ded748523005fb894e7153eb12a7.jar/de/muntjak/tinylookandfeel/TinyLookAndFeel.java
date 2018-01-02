// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import de.muntjak.tinylookandfeel.borders.TinyFrameBorder;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import java.awt.Font;
import javax.swing.plaf.InsetsUIResource;
import de.muntjak.tinylookandfeel.borders.TinyScrollPaneBorder;
import de.muntjak.tinylookandfeel.borders.TinyPopupMenuBorder;
import de.muntjak.tinylookandfeel.borders.TinyInternalFrameBorder;
import de.muntjak.tinylookandfeel.borders.TinyToolTipBorder;
import de.muntjak.tinylookandfeel.borders.TinyToolBarBorder;
import de.muntjak.tinylookandfeel.borders.TinyProgressBarBorder;
import java.awt.Insets;
import de.muntjak.tinylookandfeel.borders.TinyTableHeaderRolloverBorder;
import de.muntjak.tinylookandfeel.borders.TinyTableHeaderBorder;
import de.muntjak.tinylookandfeel.borders.TinyTableScrollPaneBorder;
import de.muntjak.tinylookandfeel.borders.TinyTextFieldBorder;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicBorders;
import de.muntjak.tinylookandfeel.borders.TinyButtonBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.UIDefaults;
import java.net.URL;
import java.security.AccessControlException;
import java.net.MalformedURLException;
import java.io.File;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import javax.swing.UIManager;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class TinyLookAndFeel extends MetalLookAndFeel
{
    public static boolean controlPanelInstantiated;
    static final int MINIMUM_FRAME_WIDTH = 104;
    static final int MINIMUM_INTERNAL_FRAME_WIDTH = 32;
    private static int is1dot4;
    public static final String VERSION_STRING = "1.3.8";
    public static final String DATE_STRING = "2007-6-17";
    protected static TinyDefaultTheme defaultTheme;
    private static boolean isInstalled;
    private static boolean themeHasBeenSet;
    static /* synthetic */ Class class$de$muntjak$tinylookandfeel$TinyLookAndFeel;
    
    public void initialize() {
        super.initialize();
        if (!TinyLookAndFeel.isInstalled) {
            TinyLookAndFeel.isInstalled = true;
            if (TinyLookAndFeel.is1dot4 == -1) {
                final String s = AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
                    public Object run() {
                        return System.getProperty("java.version");
                    }
                });
                if (s != null) {
                    TinyLookAndFeel.is1dot4 = ((!s.startsWith("1.0") && !s.startsWith("1.1") && !s.startsWith("1.2") && !s.startsWith("1.3") && !s.startsWith("1.4")) ? 1 : 0);
                }
                else {
                    TinyLookAndFeel.is1dot4 = 1;
                }
            }
            this.searchDefaultTheme();
            UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo("TinyLookAndFeel", "de.muntjak.tinylookandfeel.TinyLookAndFeel"));
        }
    }
    
    public static boolean is1dot4() {
        return TinyLookAndFeel.is1dot4 == 0;
    }
    
    public void uninitialize() {
        super.uninitialize();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(TinyMenuUI.altProcessor);
    }
    
    private void searchDefaultTheme() {
        if (TinyLookAndFeel.controlPanelInstantiated) {
            return;
        }
        String s = null;
        final URL resource = ((TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel == null) ? (TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel = class$("de.muntjak.tinylookandfeel.TinyLookAndFeel")) : TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel).getResource("/Default.theme");
        if (Theme.loadTheme(resource, 3)) {
            Theme.style = 3;
            s = resource.toExternalForm();
        }
        else {
            final URL resource2 = Thread.currentThread().getContextClassLoader().getResource("Default.theme");
            if (Theme.loadTheme(resource2, 3)) {
                Theme.style = 3;
                s = resource2.toExternalForm();
            }
            else {
                try {
                    final URL url = new File(System.getProperty("user.home"), "Default.theme").toURI().toURL();
                    if (Theme.loadTheme(url, 3)) {
                        Theme.style = 3;
                        s = url.toExternalForm();
                    }
                    else {
                        final URL url2 = new File(System.getProperty("user.dir"), "Default.theme").toURI().toURL();
                        if (Theme.loadTheme(url2, 3)) {
                            Theme.style = 3;
                            s = url2.toExternalForm();
                        }
                    }
                }
                catch (MalformedURLException ex) {}
                catch (AccessControlException ex2) {}
            }
        }
        final String s2 = "TinyLaF v1.3.8\n";
        if (s == null) {
            System.out.println(s2 + "'Default.theme' not found - using YQ default theme.");
        }
        else {
            System.out.println(s2 + "Theme: " + s);
        }
    }
    
    public String getID() {
        return "TinyLookAndFeel";
    }
    
    public String getName() {
        return "TinyLookAndFeel";
    }
    
    public String getDescription() {
        return "TinyLookAndFeel";
    }
    
    public boolean isNativeLookAndFeel() {
        return false;
    }
    
    public final boolean isSupportedLookAndFeel() {
        return true;
    }
    
    public boolean getSupportsWindowDecorations() {
        return true;
    }
    
    protected void initClassDefaults(final UIDefaults uiDefaults) {
        super.initClassDefaults(uiDefaults);
        uiDefaults.putDefaults(new Object[] { "ButtonUI", "de.muntjak.tinylookandfeel.TinyButtonUI", "CheckBoxUI", "de.muntjak.tinylookandfeel.TinyCheckBoxUI", "TextFieldUI", "de.muntjak.tinylookandfeel.TinyTextFieldUI", "TextAreaUI", "de.muntjak.tinylookandfeel.TinyTextAreaUI", "PasswordFieldUI", "de.muntjak.tinylookandfeel.TinyPasswordFieldUI", "EditorPaneUI", "de.muntjak.tinylookandfeel.TinyEditorPaneUI", "TextPaneUI", "de.muntjak.tinylookandfeel.TinyTextPaneUI", "SliderUI", "de.muntjak.tinylookandfeel.TinySliderUI", "SpinnerUI", "de.muntjak.tinylookandfeel.TinySpinnerUI", "ToolBarUI", "de.muntjak.tinylookandfeel.TinyToolBarUI", "ToolBarSeparatorUI", "de.muntjak.tinylookandfeel.TinyToolBarSeparatorUI", "MenuBarUI", "de.muntjak.tinylookandfeel.TinyMenuBarUI", "MenuUI", "de.muntjak.tinylookandfeel.TinyMenuUI", "MenuItemUI", "de.muntjak.tinylookandfeel.TinyMenuItemUI", "CheckBoxMenuItemUI", "de.muntjak.tinylookandfeel.TinyCheckBoxMenuItemUI", "RadioButtonMenuItemUI", "de.muntjak.tinylookandfeel.TinyRadioButtonMenuItemUI", "ScrollBarUI", "de.muntjak.tinylookandfeel.TinyScrollBarUI", "TabbedPaneUI", "de.muntjak.tinylookandfeel.TinyTabbedPaneUI", "ToggleButtonUI", "de.muntjak.tinylookandfeel.TinyButtonUI", "ScrollPaneUI", "de.muntjak.tinylookandfeel.TinyScrollPaneUI", "ProgressBarUI", "de.muntjak.tinylookandfeel.TinyProgressBarUI", "InternalFrameUI", "de.muntjak.tinylookandfeel.TinyInternalFrameUI", "RadioButtonUI", "de.muntjak.tinylookandfeel.TinyRadioButtonUI", "ComboBoxUI", "de.muntjak.tinylookandfeel.TinyComboBoxUI", "PopupMenuSeparatorUI", "de.muntjak.tinylookandfeel.TinyPopupMenuSeparatorUI", "SeparatorUI", "de.muntjak.tinylookandfeel.TinySeparatorUI", "SplitPaneUI", "de.muntjak.tinylookandfeel.TinySplitPaneUI", "FileChooserUI", "de.muntjak.tinylookandfeel.TinyFileChooserUI", "ListUI", "de.muntjak.tinylookandfeel.TinyListUI", "TreeUI", "de.muntjak.tinylookandfeel.TinyTreeUI", "LabelUI", "de.muntjak.tinylookandfeel.TinyLabelUI", "TableUI", "de.muntjak.tinylookandfeel.TinyTableUI", "TableHeaderUI", "de.muntjak.tinylookandfeel.TinyTableHeaderUI", "ToolTipUI", "de.muntjak.tinylookandfeel.TinyToolTipUI", "RootPaneUI", "de.muntjak.tinylookandfeel.TinyRootPaneUI", "DesktopPaneUI", "de.muntjak.tinylookandfeel.TinyDesktopPaneUI" });
    }
    
    protected void createDefaultTheme() {
        setCurrentTheme(TinyLookAndFeel.defaultTheme = new TinyDefaultTheme());
    }
    
    public static void setCurrentTheme(final MetalTheme currentTheme) {
        MetalLookAndFeel.setCurrentTheme(currentTheme);
        TinyLookAndFeel.themeHasBeenSet = true;
    }
    
    protected void initComponentDefaults(final UIDefaults uiDefaults) {
        super.initComponentDefaults(uiDefaults);
        final EmptyBorder emptyBorder = new EmptyBorder(0, 0, 0, 0);
        uiDefaults.put("Button.border", new BorderUIResource.CompoundBorderUIResource(new TinyButtonBorder(), new BasicBorders.MarginBorder()));
        uiDefaults.put("FormattedTextField.border", new TinyTextFieldBorder());
        uiDefaults.put("TextField.border", new TinyTextFieldBorder());
        uiDefaults.put("PasswordField.border", new TinyTextFieldBorder());
        uiDefaults.put("ComboBox.border", emptyBorder);
        uiDefaults.put("Table.scrollPaneBorder", new TinyTableScrollPaneBorder());
        uiDefaults.put("TableHeader.cellBorder", new TinyTableHeaderBorder());
        uiDefaults.put("TableHeader.cellRolloverBorder", new TinyTableHeaderRolloverBorder());
        uiDefaults.put("Spinner.border", new TinyTextFieldBorder(new Insets(2, 2, 2, 2)));
        uiDefaults.put("ProgressBar.border", new TinyProgressBarBorder());
        uiDefaults.put("ToolBar.border", new TinyToolBarBorder());
        uiDefaults.put("ToolTip.border", new BorderUIResource(new TinyToolTipBorder(true)));
        uiDefaults.put("ToolTip.borderInactive", new BorderUIResource(new TinyToolTipBorder(false)));
        final TinyInternalFrameBorder tinyInternalFrameBorder = new TinyInternalFrameBorder();
        uiDefaults.put("InternalFrame.border", tinyInternalFrameBorder);
        uiDefaults.put("InternalFrame.paletteBorder", tinyInternalFrameBorder);
        uiDefaults.put("InternalFrame.optionDialogBorder", tinyInternalFrameBorder);
        final EmptyBorder emptyBorder2 = new EmptyBorder(2, 4, 2, 4);
        uiDefaults.put("Menu.border", emptyBorder2);
        uiDefaults.put("MenuItem.border", emptyBorder2);
        uiDefaults.put("CheckBoxMenuItem.border", emptyBorder2);
        uiDefaults.put("RadioButtonMenuItem.border", emptyBorder2);
        uiDefaults.put("PopupMenu.border", new TinyPopupMenuBorder());
        uiDefaults.put("ScrollPane.border", new TinyScrollPaneBorder());
        uiDefaults.put("Slider.trackWidth", new Integer(4));
        uiDefaults.put("CheckBox.border", new BasicBorders.MarginBorder());
        uiDefaults.put("RadioButton.border", new BasicBorders.MarginBorder());
        uiDefaults.put("RadioButton.margin", new InsetsUIResource(2, 2, 2, 2));
        uiDefaults.put("CheckBox.margin", new InsetsUIResource(2, 2, 2, 2));
        uiDefaults.put("SplitPane.dividerSize", new Integer(7));
        uiDefaults.put("InternalFrame.normalTitleFont", new Font("dialog", 1, 13));
        if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
            uiDefaults.put("FileChooser.readOnly", Boolean.TRUE);
        }
        uiDefaults.put("TabbedPane.tabInsets", new Insets(1, 6, 4, 6));
        uiDefaults.put("TabbedPane.selectedTabPadInsets", new Insets(2, 2, 1, 2));
        uiDefaults.put("TabbedPane.unselected", new ColorUIResource(0, 0, 0));
        uiDefaults.put("TabbedPane.tabAreaInsets", new Insets(6, 2, 0, 0));
        uiDefaults.put("TabbedPane.contentBorderInsets", new Insets(1, 1, 3, 3));
        uiDefaults.put("PopupMenu.foreground", new Color(255, 0, 0));
        uiDefaults.put("RootPane.colorChooserDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.errorDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.fileChooserDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.frameBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.informationDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.plainDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.questionDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("RootPane.warningDialogBorder", TinyFrameBorder.getInstance());
        uiDefaults.put("CheckBoxMenuItem.checkIcon", MenuItemIconFactory.getCheckBoxMenuItemIcon());
        uiDefaults.put("RadioButtonMenuItem.checkIcon", MenuItemIconFactory.getRadioButtonMenuItemIcon());
        uiDefaults.put("Menu.arrowIcon", MenuItemIconFactory.getMenuArrowIcon());
        uiDefaults.put("InternalFrame.frameTitleHeight", new Integer(25));
        uiDefaults.put("InternalFrame.paletteTitleHeight", new Integer(16));
        uiDefaults.put("InternalFrame.icon", loadIcon("InternalFrameIcon.png", this));
        uiDefaults.put("Tree.expandedIcon", loadIcon("TreeMinusIcon.png", this));
        uiDefaults.put("Tree.collapsedIcon", loadIcon("TreePlusIcon.png", this));
        uiDefaults.put("Tree.openIcon", loadIcon("TreeFolderOpenedIcon.png", this));
        uiDefaults.put("Tree.closedIcon", loadIcon("TreeFolderClosedIcon.png", this));
        uiDefaults.put("Tree.leafIcon", loadIcon("TreeLeafIcon.png", this));
        uiDefaults.put("FileView.directoryIcon", loadIcon("DirectoryIcon.png", this));
        uiDefaults.put("FileView.computerIcon", loadIcon("ComputerIcon.png", this));
        uiDefaults.put("FileView.fileIcon", loadIcon("FileIcon.png", this));
        uiDefaults.put("FileView.floppyDriveIcon", loadIcon("FloppyIcon.png", this));
        uiDefaults.put("FileView.hardDriveIcon", loadIcon("HarddiskIcon.png", this));
        uiDefaults.put("FileChooser.detailsViewIcon", loadIcon("FileDetailsIcon.png", this));
        uiDefaults.put("FileChooser.homeFolderIcon", loadIcon("HomeFolderIcon.png", this));
        uiDefaults.put("FileChooser.listViewIcon", loadIcon("FileListIcon.png", this));
        uiDefaults.put("FileChooser.newFolderIcon", loadIcon("NewFolderIcon.png", this));
        uiDefaults.put("FileChooser.upFolderIcon", loadIcon("ParentDirectoryIcon.png", this));
        uiDefaults.put("OptionPane.errorIcon", loadIcon("ErrorIcon.png", this));
        uiDefaults.put("OptionPane.informationIcon", loadIcon("InformationIcon.png", this));
        uiDefaults.put("OptionPane.warningIcon", loadIcon("WarningIcon.png", this));
        uiDefaults.put("OptionPane.questionIcon", loadIcon("QuestionIcon.png", this));
    }
    
    public static Icon getUncolorizedSystemIcon(final int n) {
        switch (n) {
            case 0: {
                return loadIcon("InternalFrameIcon.png", null);
            }
            case 1: {
                return loadIcon("TreeFolderClosedIcon.png", null);
            }
            case 2: {
                return loadIcon("TreeFolderOpenedIcon.png", null);
            }
            case 3: {
                return loadIcon("TreeLeafIcon.png", null);
            }
            case 4: {
                return loadIcon("TreeMinusIcon.png", null);
            }
            case 5: {
                return loadIcon("TreePlusIcon.png", null);
            }
            case 6: {
                return loadIcon("ComputerIcon.png", null);
            }
            case 7: {
                return loadIcon("FloppyIcon.png", null);
            }
            case 8: {
                return loadIcon("HarddiskIcon.png", null);
            }
            case 9: {
                return loadIcon("DirectoryIcon.png", null);
            }
            case 10: {
                return loadIcon("FileIcon.png", null);
            }
            case 11: {
                return loadIcon("ParentDirectoryIcon.png", null);
            }
            case 12: {
                return loadIcon("HomeFolderIcon.png", null);
            }
            case 13: {
                return loadIcon("NewFolderIcon.png", null);
            }
            case 14: {
                return loadIcon("FileListIcon.png", null);
            }
            case 15: {
                return loadIcon("FileDetailsIcon.png", null);
            }
            case 16: {
                return loadIcon("InformationIcon.png", null);
            }
            case 17: {
                return loadIcon("QuestionIcon.png", null);
            }
            case 18: {
                return loadIcon("WarningIcon.png", null);
            }
            default: {
                return loadIcon("ErrorIcon.png", null);
            }
        }
    }
    
    public static String getSystemIconName(final int n) {
        switch (n) {
            case 0: {
                return "InternalFrame.icon";
            }
            case 1: {
                return "Tree.closedIcon";
            }
            case 2: {
                return "Tree.openIcon";
            }
            case 3: {
                return "Tree.leafIcon";
            }
            case 4: {
                return "Tree.expandedIcon";
            }
            case 5: {
                return "Tree.collapsedIcon";
            }
            case 6: {
                return "FileView.computerIcon";
            }
            case 7: {
                return "FileView.floppyDriveIcon";
            }
            case 8: {
                return "FileView.hardDriveIcon";
            }
            case 9: {
                return "FileView.directoryIcon";
            }
            case 10: {
                return "FileView.fileIcon";
            }
            case 11: {
                return "FileChooser.upFolderIcon";
            }
            case 12: {
                return "FileChooser.homeFolderIcon";
            }
            case 13: {
                return "FileChooser.newFolderIcon";
            }
            case 14: {
                return "FileChooser.listViewIcon";
            }
            case 15: {
                return "FileChooser.detailsViewIcon";
            }
            case 16: {
                return "OptionPane.informationIcon";
            }
            case 17: {
                return "OptionPane.questionIcon";
            }
            case 18: {
                return "OptionPane.warningIcon";
            }
            default: {
                return "OptionPane.errorIcon";
            }
        }
    }
    
    public static ImageIcon loadIcon(final String s, final Object o) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("de/muntjak/tinylookandfeel/icons/" + s);
        if (url == null) {
            url = ((TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel == null) ? (TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel = class$("de.muntjak.tinylookandfeel.TinyLookAndFeel")) : TinyLookAndFeel.class$de$muntjak$tinylookandfeel$TinyLookAndFeel).getResource("/de/muntjak/tinylookandfeel/icons/" + s);
            if (url == null) {
                System.err.println("TinyLaF: Icon directory could not be resolved.");
                return null;
            }
        }
        return new ImageIcon(url);
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
        TinyLookAndFeel.controlPanelInstantiated = false;
        TinyLookAndFeel.is1dot4 = -1;
        TinyLookAndFeel.isInstalled = false;
        TinyLookAndFeel.themeHasBeenSet = false;
    }
}
