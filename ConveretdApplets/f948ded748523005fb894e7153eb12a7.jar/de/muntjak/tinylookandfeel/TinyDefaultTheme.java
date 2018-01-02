// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import de.muntjak.tinylookandfeel.controlpanel.HSBReference;
import javax.swing.Icon;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.UIDefaults;
import javax.swing.plaf.FontUIResource;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class TinyDefaultTheme extends DefaultMetalTheme
{
    public static ColorUIResource secondary3;
    private final ColorUIResource lightBackground;
    public static final ColorUIResource darkControl;
    private final ColorUIResource primary1;
    private final ColorUIResource primary2;
    private final ColorUIResource primary3;
    private final ColorUIResource secondary1;
    private final ColorUIResource secondary2;
    private final ColorUIResource secondary4;
    private final Color gradientReflection;
    private final Color gradientShadow;
    private final Color gradientTranslucentReflection;
    private final Color gradientTranslucentShadow;
    
    public TinyDefaultTheme() {
        this.lightBackground = new ColorUIResource(252, 252, 254);
        this.primary1 = new ColorUIResource(0, 0, 0);
        this.primary2 = new ColorUIResource(213, 211, 209);
        this.primary3 = new ColorUIResource(213, 211, 209);
        this.secondary1 = new ColorUIResource(167, 165, 163);
        this.secondary2 = new ColorUIResource(167, 165, 163);
        this.secondary4 = new ColorUIResource(190, 188, 186);
        this.gradientReflection = new Color(255, 255, 255, 86);
        this.gradientShadow = new Color(188, 186, 184, 100);
        this.gradientTranslucentReflection = new Color(this.gradientReflection.getRGB() & 0xFFFFFF, true);
        this.gradientTranslucentShadow = new Color(this.gradientShadow.getRGB() & 0xFFFFFF, true);
    }
    
    public Color getGradientReflection() {
        return this.gradientReflection;
    }
    
    public Color getGradientShadow() {
        return this.gradientShadow;
    }
    
    public Color getGradientTranslucentReflection() {
        return this.gradientTranslucentReflection;
    }
    
    public Color getGradientTranslucentShadow() {
        return this.gradientTranslucentShadow;
    }
    
    public FontUIResource getControlTextFont() {
        return Theme.plainFont[Theme.style].getFont();
    }
    
    public FontUIResource getMenuTextFont() {
        return Theme.plainFont[Theme.style].getFont();
    }
    
    public FontUIResource getSystemTextFont() {
        return Theme.plainFont[Theme.style].getFont();
    }
    
    public FontUIResource getUserTextFont() {
        return Theme.plainFont[Theme.style].getFont();
    }
    
    public FontUIResource getWindowTitleFont() {
        return Theme.boldFont[Theme.style].getFont();
    }
    
    public void addCustomEntriesToTable(final UIDefaults uiDefaults) {
        super.addCustomEntriesToTable(uiDefaults);
        uiDefaults.put("Button.margin", new InsetsUIResource(Theme.buttonMarginTop[Theme.style], Theme.buttonMarginLeft[Theme.style], Theme.buttonMarginBottom[Theme.style], Theme.buttonMarginRight[Theme.style]));
        uiDefaults.put("CheckBox.margin", new InsetsUIResource(Theme.checkMarginTop[Theme.style], Theme.checkMarginLeft[Theme.style], Theme.checkMarginBottom[Theme.style], Theme.checkMarginRight[Theme.style]));
        uiDefaults.put("RadioButton.margin", new InsetsUIResource(Theme.checkMarginTop[Theme.style], Theme.checkMarginLeft[Theme.style], Theme.checkMarginBottom[Theme.style], Theme.checkMarginRight[Theme.style]));
        uiDefaults.put("Button.background", Theme.buttonNormalColor[Theme.style].getColor());
        uiDefaults.put("Button.font", Theme.buttonFont[Theme.style].getFont());
        uiDefaults.put("CheckBox.font", Theme.checkFont[Theme.style].getFont());
        uiDefaults.put("CheckBoxMenuItem.font", Theme.menuItemFont[Theme.style].getFont());
        uiDefaults.put("ComboBox.font", Theme.comboFont[Theme.style].getFont());
        uiDefaults.put("Label.font", Theme.labelFont[Theme.style].getFont());
        uiDefaults.put("List.font", Theme.listFont[Theme.style].getFont());
        uiDefaults.put("Menu.font", Theme.menuFont[Theme.style].getFont());
        uiDefaults.put("MenuItem.font", Theme.menuItemFont[Theme.style].getFont());
        uiDefaults.put("ProgressBar.font", Theme.progressBarFont[Theme.style].getFont());
        uiDefaults.put("RadioButton.font", Theme.radioFont[Theme.style].getFont());
        uiDefaults.put("RadioButtonMenuItem.font", Theme.menuItemFont[Theme.style].getFont());
        uiDefaults.put("Table.font", Theme.tableFont[Theme.style].getFont());
        uiDefaults.put("TableHeader.font", Theme.tableHeaderFont[Theme.style].getFont());
        uiDefaults.put("TitledBorder.font", Theme.titledBorderFont[Theme.style].getFont());
        uiDefaults.put("ToolTip.font", Theme.toolTipFont[Theme.style].getFont());
        uiDefaults.put("Tree.font", Theme.treeFont[Theme.style].getFont());
        uiDefaults.put("PasswordField.font", Theme.passwordFont[Theme.style].getFont());
        uiDefaults.put("TextArea.font", Theme.textAreaFont[Theme.style].getFont());
        uiDefaults.put("TextField.font", Theme.textFieldFont[Theme.style].getFont());
        uiDefaults.put("FormattedTextField.font", Theme.textFieldFont[Theme.style].getFont());
        uiDefaults.put("TextPane.font", Theme.textPaneFont[Theme.style].getFont());
        uiDefaults.put("EditorPane.font", Theme.editorFont[Theme.style].getFont());
        uiDefaults.put("InternalFrame.font", Theme.editorFont[Theme.style].getFont());
        uiDefaults.put("InternalFrame.normalTitleFont", Theme.internalFrameTitleFont[Theme.style].getFont());
        uiDefaults.put("InternalFrame.paletteTitleFont", Theme.internalPaletteTitleFont[Theme.style].getFont());
        uiDefaults.put("InternalFrame.titleFont", Theme.frameTitleFont[Theme.style].getFont());
        uiDefaults.put("TabbedPane.font", Theme.tabFont[Theme.style].getFont());
        uiDefaults.put("Button.foreground", Theme.buttonFontColor[Theme.style].getColor());
        uiDefaults.put("CheckBox.foreground", Theme.checkFontColor[Theme.style].getColor());
        uiDefaults.put("Menu.foreground", Theme.menuFontColor[Theme.style].getColor());
        uiDefaults.put("MenuItem.foreground", Theme.menuItemFontColor[Theme.style].getColor());
        uiDefaults.put("CheckBoxMenuItem.foreground", Theme.menuItemFontColor[Theme.style].getColor());
        uiDefaults.put("RadioButtonMenuItem.foreground", Theme.menuItemFontColor[Theme.style].getColor());
        uiDefaults.put("RadioButton.foreground", Theme.radioFontColor[Theme.style].getColor());
        uiDefaults.put("TabbedPane.foreground", Theme.tabFontColor[Theme.style].getColor());
        uiDefaults.put("TitledBorder.titleColor", Theme.titledBorderFontColor[Theme.style].getColor());
        uiDefaults.put("Label.foreground", Theme.labelFontColor[Theme.style].getColor());
        uiDefaults.put("TableHeader.foreground", Theme.tableHeaderFontColor[Theme.style].getColor());
        uiDefaults.put("TableHeader.background", Theme.tableHeaderBackColor[Theme.style].getColor());
        uiDefaults.put("Table.foreground", Theme.tableFontColor[Theme.style].getColor());
        uiDefaults.put("Table.background", Theme.tableBackColor[Theme.style].getColor());
        uiDefaults.put("Table.selectionForeground", Theme.tableSelectedForeColor[Theme.style].getColor());
        uiDefaults.put("Table.selectionBackground", Theme.tableSelectedBackColor[Theme.style].getColor());
        uiDefaults.put("Table.gridColor", Theme.tableGridColor[Theme.style].getColor());
        uiDefaults.put("ProgressBar.foreground", Theme.progressColor[Theme.style].getColor());
        uiDefaults.put("ProgressBar.background", Theme.progressTrackColor[Theme.style].getColor());
        uiDefaults.put("ProgressBar.selectionForeground", Theme.progressSelectForeColor[Theme.style].getColor());
        uiDefaults.put("ProgressBar.selectionBackground", Theme.progressSelectBackColor[Theme.style].getColor());
        uiDefaults.put("PopupMenu.background", Theme.menuPopupColor[Theme.style]);
        uiDefaults.put("TabbedPane.background", Theme.tabNormalColor[Theme.style].getColor());
        uiDefaults.put("TabbedPane.tabAreaInsets", Theme.tabAreaInsets[Theme.style]);
        uiDefaults.put("TabbedPane.tabInsets", Theme.tabInsets[Theme.style]);
        uiDefaults.put("MenuBar.background", Theme.menuBarColor[Theme.style].getColor());
        uiDefaults.put("ToolBar.background", Theme.toolBarColor[Theme.style].getColor());
        uiDefaults.put("EditorPane.caretForeground", Theme.textCaretColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.caretForeground", Theme.textCaretColor[Theme.style].getColor());
        uiDefaults.put("TextArea.caretForeground", Theme.textCaretColor[Theme.style].getColor());
        uiDefaults.put("TextField.caretForeground", Theme.textCaretColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.caretForeground", Theme.textCaretColor[Theme.style].getColor());
        uiDefaults.put("List.foreground", Theme.listTextColor[Theme.style].getColor());
        uiDefaults.put("List.background", Theme.listBgColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.foreground", Theme.comboTextColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.background", Theme.comboBgColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.disabledBackground", Theme.textDisabledBgColor[Theme.style].getColor());
        uiDefaults.put("EditorPane.background", Theme.textBgColor[Theme.style].getColor());
        uiDefaults.put("EditorPane.foreground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.background", Theme.textBgColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.foreground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.inactiveBackground", Theme.textDisabledBgColor[Theme.style].getColor());
        uiDefaults.put("TextArea.background", Theme.textBgColor[Theme.style].getColor());
        uiDefaults.put("TextArea.foreground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("TextArea.inactiveBackground", Theme.textDisabledBgColor[Theme.style].getColor());
        uiDefaults.put("TextField.background", Theme.textBgColor[Theme.style].getColor());
        uiDefaults.put("TextField.foreground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("TextField.inactiveBackground", Theme.textDisabledBgColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.background", Theme.textBgColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.foreground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.inactiveBackground", Theme.textDisabledBgColor[Theme.style].getColor());
        uiDefaults.put("TextPane.background", Theme.textPaneBgColor[Theme.style].getColor());
        uiDefaults.put("EditorPane.background", Theme.editorPaneBgColor[Theme.style].getColor());
        uiDefaults.put("OptionPane.messageForeground", Theme.textTextColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.selectionBackground", Theme.textSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.selectionForeground", Theme.textSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("TextField.selectionBackground", Theme.textSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("TextField.selectionForeground", Theme.textSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.selectionBackground", Theme.textSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.selectionForeground", Theme.textSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("TextArea.selectionBackground", Theme.textSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("TextArea.selectionForeground", Theme.textSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("TextPane.selectionBackground", Theme.textSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("TextPane.selectionForeground", Theme.textSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.selectionBackground", Theme.comboSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.selectionForeground", Theme.comboSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.focusBackground", Theme.comboSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("List.selectionForeground", Theme.listSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("List.selectionBackground", Theme.listSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("Tree.background", Theme.treeBgColor[Theme.style].getColor());
        uiDefaults.put("Tree.textBackground", Theme.treeTextBgColor[Theme.style].getColor());
        uiDefaults.put("Tree.textForeground", Theme.treeTextColor[Theme.style].getColor());
        uiDefaults.put("Tree.selectionBackground", Theme.treeSelectedBgColor[Theme.style].getColor());
        uiDefaults.put("Tree.selectionForeground", Theme.treeSelectedTextColor[Theme.style].getColor());
        uiDefaults.put("Tree.hash", Theme.treeLineColor[Theme.style].getColor());
        uiDefaults.put("Tree.line", Theme.treeLineColor[Theme.style].getColor());
        uiDefaults.put("Button.disabledText", Theme.buttonDisabledFgColor[Theme.style].getColor());
        uiDefaults.put("CheckBox.disabledText", Theme.checkDisabledFgColor[Theme.style].getColor());
        uiDefaults.put("RadioButton.disabledText", Theme.radioDisabledFgColor[Theme.style].getColor());
        uiDefaults.put("ToggleButton.disabledText", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("ToggleButton.disabledSelectedText", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("TextArea.inactiveForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("TextField.inactiveForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("FormattedTextField.inactiveForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("TextPane.inactiveForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("PasswordField.inactiveForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("ComboBox.disabledForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("Label.disabledForeground", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("textInactiveText", Theme.disColor[Theme.style].getColor());
        uiDefaults.put("Desktop.background", Theme.desktopPaneBgColor[Theme.style].getColor());
        uiDefaults.put("Separator.background", Theme.sepDarkColor[Theme.style].getColor());
        uiDefaults.put("Separator.foreground", Theme.sepLightColor[Theme.style].getColor());
        uiDefaults.put("TitledBorder.border", new LineBorder(Theme.titledBorderColor[Theme.style].getColor()));
        uiDefaults.put("ToolTip.background", Theme.tipBgColor[Theme.style].getColor());
        uiDefaults.put("ToolTip.backgroundInactive", Theme.tipBgDis[Theme.style].getColor());
        uiDefaults.put("ToolTip.foreground", Theme.tipTextColor[Theme.style].getColor());
        uiDefaults.put("ToolTip.foregroundInactive", Theme.tipTextDis[Theme.style].getColor());
        uiDefaults.put("Panel.background", Theme.backColor[Theme.style].getColor());
        TinyDefaultTheme.secondary3 = new ColorUIResource(Theme.backColor[Theme.style].getColor());
        for (int i = 0; i < 20; ++i) {
            if (Theme.colorize[Theme.style][i]) {
                final Icon uncolorizedSystemIcon = TinyLookAndFeel.getUncolorizedSystemIcon(i);
                if (uncolorizedSystemIcon != null && uncolorizedSystemIcon instanceof ImageIcon) {
                    final HSBReference hsbReference = Theme.colorizer[i][Theme.style];
                    uiDefaults.put(TinyLookAndFeel.getSystemIconName(i), DrawRoutines.colorize(((ImageIcon)uncolorizedSystemIcon).getImage(), hsbReference.getHue(), hsbReference.getSaturation(), hsbReference.getBrightness(), hsbReference.isPreserveGrey()));
                }
                else {
                    uiDefaults.put(TinyLookAndFeel.getSystemIconName(i), uncolorizedSystemIcon);
                }
            }
        }
    }
    
    public ColorUIResource getMenuSelectedBackground() {
        return new ColorUIResource(200, 200, 255);
    }
    
    public ColorUIResource getSeparatorForeground() {
        return this.getSecondary3();
    }
    
    public String getName() {
        return "TinyLaF Default Theme";
    }
    
    protected ColorUIResource getPrimary1() {
        return this.primary1;
    }
    
    protected ColorUIResource getPrimary2() {
        return this.primary2;
    }
    
    protected ColorUIResource getPrimary3() {
        return this.primary3;
    }
    
    protected ColorUIResource getSecondary1() {
        return this.secondary1;
    }
    
    protected ColorUIResource getSecondary2() {
        return this.secondary2;
    }
    
    protected ColorUIResource getSecondary3() {
        return TinyDefaultTheme.secondary3;
    }
    
    public ColorUIResource getLigthBackground() {
        return this.lightBackground;
    }
    
    public ColorUIResource getDarkControl() {
        return TinyDefaultTheme.darkControl;
    }
    
    static {
        TinyDefaultTheme.secondary3 = Theme.backColor[Theme.style].getColor();
        darkControl = new ColorUIResource(161, 161, 148);
    }
}
