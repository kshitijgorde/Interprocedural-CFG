// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import javax.swing.undo.AbstractUndoableEdit;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import edu.hws.eck.umb.palette.Palette;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import edu.hws.eck.umb.palette.PaletteEditDialog;
import java.awt.datatransfer.Clipboard;
import java.math.BigDecimal;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.hws.eck.umb.util.I18n;
import javax.swing.JMenu;
import edu.hws.eck.umb.util.Util;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoableEdit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import edu.hws.eck.umb.util.SimpleFileChooser;
import java.awt.Dimension;
import javax.swing.JRadioButtonMenuItem;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.undo.UndoManager;
import javax.swing.JMenuBar;

public class MandelbrotMenus extends JMenuBar
{
    private MandelbrotDisplay display;
    private boolean isApplet;
    private boolean isFrame;
    private UndoManager undoManager;
    private StatusBar statusBar;
    private boolean addToUndoManagerIsSuspended;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JCheckBoxMenuItem enableHighPrecision;
    private JCheckBoxMenuItem enableSubpixelSampling;
    private JDialog overviewDialog;
    private JDialog paletteEditorDialog;
    private int[] maxIterationMenuValues;
    private ArrayList<JRadioButtonMenuItem> maxIterationMenuItems;
    private Dimension[] imageSizes;
    private ArrayList<JRadioButtonMenuItem> imageSizeMenuItems;
    private JRadioButtonMenuItem[] mandelbrotColorMenuItems;
    private JRadioButtonMenuItem[] toolsMenuItems;
    private int[] toolCodes;
    private SimpleFileChooser fileChooser;
    private static final String ALL_SETTINGS = "MB_CHANGE_ALL_SETTINGS";
    
    public MandelbrotMenus(final MandelbrotDisplay display, final JFrame frame, final StatusBar statusBar, final boolean isApplet) {
        this.maxIterationMenuValues = new int[] { 50, 100, 250, 500, 1000, 2000, 5000, 10000, 50000 };
        this.imageSizes = new Dimension[] { null, new Dimension(160, 120), new Dimension(640, 480), new Dimension(800, 600), new Dimension(1024, 768), new Dimension(1280, 800), new Dimension(1280, 1024), new Dimension(1440, 900), new Dimension(1680, 1050), new Dimension(1920, 1200) };
        this.display = display;
        this.statusBar = statusBar;
        this.isApplet = isApplet;
        this.isFrame = (frame != null);
        (this.undoManager = new UndoManager()).setLimit(100);
        if (!this.isApplet && this.isFrame) {
            this.add(this.makeFileMenu(frame));
        }
        this.add(this.makeEditMenu());
        this.add(this.makeControlMenu());
        this.add(this.makeIterationsMenu());
        this.add(this.makeImageSizeMenu());
        this.add(this.makeToolsMenu());
        this.add(this.makeExamplesMenu());
        display.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final String propertyName = propertyChangeEvent.getPropertyName();
                if (propertyName.equals("mb_property_size")) {
                    MandelbrotMenus.this.checkImageSize((Dimension)propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mb_property_iterations")) {
                    MandelbrotMenus.this.checkMaxIterations((int)propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mb_property_hp_enabled")) {
                    MandelbrotMenus.this.enableHighPrecision.setSelected(MandelbrotMenus.this.display.getHighPrecisionEnabled());
                }
                else if (propertyName.equals("mb_subpixel_sampling")) {
                    MandelbrotMenus.this.enableSubpixelSampling.setSelected(MandelbrotMenus.this.display.getSubpixelSamplingEnabled());
                }
                else if (propertyName.equals("mp_propery_mb_color")) {
                    MandelbrotMenus.this.checkMandelbrotColor();
                }
                if (!MandelbrotMenus.this.addToUndoManagerIsSuspended && (propertyName.equals("mb_property_size") || propertyName.equals("mb_property_limits") || propertyName.equals("mp_propery_mb_color") || propertyName.equals("mb_property_iterations") || propertyName.equals("mb_property_palette"))) {
                    MandelbrotMenus.this.undoManager.addEdit(new UndoableChange(propertyName, propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue()));
                    MandelbrotMenus.this.updateUndoRedoItems();
                }
            }
        });
    }
    
    private void setStatusBarText(final String s, final int n) {
        if (this.statusBar != null) {
            this.statusBar.setTempText(s, n);
        }
    }
    
    private KeyStroke getAccelerator(final String s) {
        if (this.isFrame) {
            return Util.getAccelerator(s);
        }
        return null;
    }
    
    private JMenu makeFileMenu(final JFrame frame) {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.File", new Object[0]));
        final JMenuItem menuItem = new JMenuItem(I18n.tr("mandelbrotMenu.command.SaveSettings", new Object[0]));
        final JMenuItem menuItem2 = new JMenuItem(I18n.tr("mandelbrotMenu.command.OpenSettings", new Object[0]));
        final JMenuItem menuItem3 = new JMenuItem(I18n.tr("mandelbrotMenu.command.SavePNGImage", new Object[0]));
        final JMenuItem menuItem4 = new JMenuItem(I18n.tr("mandelbrotMenu.command.SaveJPEGImage", new Object[0]));
        final JMenuItem menuItem5 = new JMenuItem(I18n.tr("mandelbrotMenu.command.Quit", new Object[0]));
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                if (source == menuItem) {
                    MandelbrotMenus.this.doSave();
                }
                else if (source == menuItem2) {
                    final MandelbrotSettings access$1000 = MandelbrotMenus.this.doOpen();
                    if (access$1000 != null) {
                        final MandelbrotSettings mandelbrotSettings = new MandelbrotSettings(MandelbrotMenus.this.display);
                        MandelbrotMenus.this.addToUndoManagerIsSuspended = true;
                        MandelbrotMenus.this.display.applySettings(access$1000);
                        MandelbrotMenus.this.addToUndoManagerIsSuspended = false;
                        MandelbrotMenus.this.undoManager.addEdit(new UndoableChange("MB_CHANGE_ALL_SETTINGS", mandelbrotSettings, access$1000));
                        MandelbrotMenus.this.updateUndoRedoItems();
                    }
                }
                else if (source == menuItem3) {
                    MandelbrotMenus.this.doSaveImage("PNG");
                }
                else if (source == menuItem4) {
                    MandelbrotMenus.this.doSaveImage("JPEG");
                }
                else if (source == menuItem5) {
                    frame.dispose();
                }
            }
        };
        menuItem.addActionListener(actionListener);
        menuItem.setAccelerator(this.getAccelerator("S"));
        menuItem2.addActionListener(actionListener);
        menuItem2.setAccelerator(this.getAccelerator("O"));
        menuItem3.addActionListener(actionListener);
        menuItem3.setAccelerator(this.getAccelerator("shift S"));
        menuItem4.addActionListener(actionListener);
        menuItem5.addActionListener(actionListener);
        menuItem5.setAccelerator(this.getAccelerator("Q"));
        menu.add(menuItem);
        menu.add(menuItem2);
        menu.addSeparator();
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.addSeparator();
        menu.add(menuItem5);
        return menu;
    }
    
    private JMenu makeEditMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.Edit", new Object[0]));
        (this.undoMenuItem = new JMenuItem(this.undoManager.getUndoPresentationName())).setAccelerator(this.getAccelerator("Z"));
        this.undoMenuItem.setEnabled(false);
        (this.redoMenuItem = new JMenuItem(this.undoManager.getRedoPresentationName())).setAccelerator(this.getAccelerator("shift Z"));
        this.redoMenuItem.setEnabled(false);
        final JMenuItem menuItem = new JMenuItem(I18n.tr("mandelbrotMenu.command.CopyLimitsToClipboard", new Object[0]));
        menuItem.setAccelerator(this.getAccelerator("C"));
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                if (source == MandelbrotMenus.this.undoMenuItem) {
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = true;
                    MandelbrotMenus.this.undoManager.undo();
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = false;
                    MandelbrotMenus.this.updateUndoRedoItems();
                }
                else if (source == MandelbrotMenus.this.redoMenuItem) {
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = true;
                    MandelbrotMenus.this.undoManager.redo();
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = false;
                    MandelbrotMenus.this.updateUndoRedoItems();
                }
                else if (source == menuItem) {
                    try {
                        final BigDecimal[] limits = MandelbrotMenus.this.display.getLimits();
                        final StringWriter stringWriter = new StringWriter();
                        final PrintWriter printWriter = new PrintWriter(stringWriter);
                        printWriter.println(I18n.tr("term.MinimumX", new Object[0]) + ": " + limits[0]);
                        printWriter.println(I18n.tr("term.MaximumX", new Object[0]) + ": " + limits[1]);
                        printWriter.println(I18n.tr("term.MinimumY", new Object[0]) + ": " + limits[2]);
                        printWriter.println(I18n.tr("term.MaximumY", new Object[0]) + ": " + limits[3]);
                        printWriter.close();
                        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        final StringSelection stringSelection = new StringSelection(stringWriter.toString());
                        systemClipboard.setContents(stringSelection, stringSelection);
                        MandelbrotMenus.this.setStatusBarText(I18n.tr("mandlebrotMenus.statusText.LimitsCopied", new Object[0]), 2);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(MandelbrotMenus.this.display, I18n.tr("mandelbrotMenu.error.CannotCopyToClipboard", new Object[0]));
                    }
                }
            }
        };
        this.undoMenuItem.addActionListener(actionListener);
        this.redoMenuItem.addActionListener(actionListener);
        menuItem.addActionListener(actionListener);
        menu.add(this.undoMenuItem);
        menu.add(this.redoMenuItem);
        menu.addSeparator();
        menu.add(menuItem);
        return menu;
    }
    
    private JMenu makeControlMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.Control", new Object[0]));
        final String[] array = { "Spectrum", "PaleSpectrum", "Grayscale", "CyclicGrayscale", "CyclicRedCyan", "EarthSky", "HotCold", "Fire" };
        final JMenuItem menuItem = new JMenuItem(I18n.tr("mandelbrotMenu.command.ShowPaletteEditor", new Object[0]));
        final JMenu menu2 = new JMenu(I18n.tr("mandelbrotMenu.menuName.MandelbrotColor", new Object[0]));
        final JMenu menu3 = new JMenu(I18n.tr("mandelbrotMenu.menuName.ApplyDefaultPalette", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(I18n.tr("colorName.Black", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem2 = new JRadioButtonMenuItem(I18n.tr("colorName.Gray", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem3 = new JRadioButtonMenuItem(I18n.tr("colorName.White", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem4 = new JRadioButtonMenuItem(I18n.tr("colorName.Blue", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem5 = new JRadioButtonMenuItem(I18n.tr("menuCommand.Custom", new Object[0]));
        final JMenuItem menuItem2 = new JMenuItem(I18n.tr("mandelbrotMenu.command.RestoreDefaultLimits", new Object[0]));
        final JMenuItem menuItem3 = new JMenuItem(I18n.tr("mandelbrotMenu.command.RestoreAllDefaults", new Object[0]));
        final JMenuItem menuItem4 = new JMenuItem(I18n.tr("mandelbrotMenu.command.ZoomIn", new Object[0]));
        final JMenuItem menuItem5 = new JMenuItem(I18n.tr("mandelbrotMenu.command.ZoomOut", new Object[0]));
        final JMenuItem menuItem6 = new JMenuItem(I18n.tr("mandelbrotMenu.command.SetLimits", new Object[0]));
        final JMenuItem menuItem7 = new JMenuItem(I18n.tr("mandelbrotMenu.command.ShowOverviewWindow", new Object[0]));
        final JMenuItem menuItem8 = new JMenuItem(I18n.tr("mandelbrotMenu.command.ConfigureMultiprocessing", new Object[0]));
        final JMenuItem menuItem9 = new JMenuItem(I18n.tr("mandelbrotMenu.command.ControlMenu.help", new Object[0]));
        this.enableHighPrecision = new JCheckBoxMenuItem(I18n.tr("mandelbrotMenu.command.EnableHighPrecision", new Object[0]));
        this.enableSubpixelSampling = new JCheckBoxMenuItem(I18n.tr("mandelbrotMenu.command.EnableSubpixelSampling", new Object[0]));
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                if (source == menuItem) {
                    if (MandelbrotMenus.this.paletteEditorDialog == null) {
                        MandelbrotMenus.this.paletteEditorDialog = PaletteEditDialog.createDialog(MandelbrotMenus.this.display);
                        MandelbrotMenus.this.paletteEditorDialog.setVisible(true);
                        menuItem.setText(I18n.tr("mandelbrotMenu.command.HidePaletteEditor", new Object[0]));
                        MandelbrotMenus.this.paletteEditorDialog.addWindowListener(new WindowAdapter() {
                            public void windowClosed(final WindowEvent windowEvent) {
                                MandelbrotMenus.this.paletteEditorDialog = null;
                                menuItem.setText(I18n.tr("mandelbrotMenu.command.ShowPaletteEditor", new Object[0]));
                            }
                        });
                    }
                    else {
                        MandelbrotMenus.this.paletteEditorDialog.dispose();
                    }
                }
                else if (source == radioButtonMenuItem) {
                    MandelbrotMenus.this.display.setMandelbrotColor(Color.BLACK);
                }
                else if (source == radioButtonMenuItem2) {
                    MandelbrotMenus.this.display.setMandelbrotColor(Color.GRAY);
                }
                else if (source == radioButtonMenuItem3) {
                    MandelbrotMenus.this.display.setMandelbrotColor(Color.WHITE);
                }
                else if (source == radioButtonMenuItem4) {
                    MandelbrotMenus.this.display.setMandelbrotColor(Color.BLUE);
                }
                else if (source == radioButtonMenuItem5) {
                    final Color showDialog = JColorChooser.showDialog(MandelbrotMenus.this.display, I18n.tr("", new Object[0]), MandelbrotMenus.this.display.getMandelbrotColor());
                    if (showDialog != null) {
                        MandelbrotMenus.this.display.setMandelbrotColor(showDialog);
                    }
                }
                else if (source == menuItem2) {
                    MandelbrotMenus.this.display.setLimits(new BigDecimal(-2.333), new BigDecimal(1), new BigDecimal(-1.25), new BigDecimal(1.25));
                }
                else if (source == menuItem3) {
                    final MandelbrotSettings mandelbrotSettings = new MandelbrotSettings(MandelbrotMenus.this.display);
                    final MandelbrotSettings mandelbrotSettings2 = new MandelbrotSettings();
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = true;
                    MandelbrotMenus.this.display.applySettings(mandelbrotSettings2);
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = false;
                    MandelbrotMenus.this.undoManager.addEdit(new UndoableChange("MB_CHANGE_ALL_SETTINGS", mandelbrotSettings, mandelbrotSettings2));
                    MandelbrotMenus.this.updateUndoRedoItems();
                    MandelbrotMenus.this.display.setDefaultMouseAction(0);
                    MandelbrotMenus.this.display.setSubpixelSamplingEnabled(false);
                }
                else if (source == menuItem4) {
                    MandelbrotMenus.this.display.doZoom(0.5);
                }
                else if (source == menuItem5) {
                    MandelbrotMenus.this.display.doZoom(2.0);
                }
                else if (source == MandelbrotMenus.this.enableHighPrecision) {
                    MandelbrotMenus.this.display.setHighPrecisionEnabled(MandelbrotMenus.this.enableHighPrecision.isSelected());
                }
                else if (source == MandelbrotMenus.this.enableSubpixelSampling) {
                    MandelbrotMenus.this.display.setSubpixelSamplingEnabled(MandelbrotMenus.this.enableSubpixelSampling.isSelected());
                }
                else if (source == menuItem7) {
                    if (MandelbrotMenus.this.overviewDialog == null) {
                        MandelbrotMenus.this.overviewDialog = MandelbrotOverviewDisplay.createDialog(MandelbrotMenus.this.display);
                        MandelbrotMenus.this.overviewDialog.setVisible(true);
                        menuItem7.setText(I18n.tr("mandelbrotMenu.command.HideOverviewWindow", new Object[0]));
                        MandelbrotMenus.this.overviewDialog.addWindowListener(new WindowAdapter() {
                            public void windowClosed(final WindowEvent windowEvent) {
                                MandelbrotMenus.this.overviewDialog = null;
                                menuItem7.setText(I18n.tr("mandelbrotMenu.command.ShowOverviewWindow", new Object[0]));
                            }
                        });
                    }
                    else {
                        MandelbrotMenus.this.overviewDialog.dispose();
                    }
                }
                else if (source == menuItem6) {
                    final BigDecimal[] showDialog2 = SetLimitsDialog.showDialog(MandelbrotMenus.this.display, MandelbrotMenus.this.display.getLimitsAsStrings());
                    if (showDialog2 != null) {
                        MandelbrotMenus.this.display.setLimits(showDialog2);
                    }
                }
                else if (source == menuItem8) {
                    MultiprocessingConfigDialog.showDialog(MandelbrotMenus.this.display);
                }
                else if (source == menuItem9) {
                    JOptionPane.showMessageDialog(MandelbrotMenus.this.display, I18n.tr("mandelbrotMenu.ControlMenu.HelpText", new Object[0]));
                }
                else {
                    MandelbrotMenus.this.display.setPalette(Palette.makeDefaultPalette(actionEvent.getActionCommand()));
                }
            }
        };
        menuItem.addActionListener(actionListener);
        menuItem.setAccelerator(this.getAccelerator("P"));
        final ButtonGroup buttonGroup = new ButtonGroup();
        radioButtonMenuItem.addActionListener(actionListener);
        buttonGroup.add(radioButtonMenuItem);
        menu2.add(radioButtonMenuItem);
        radioButtonMenuItem.setAccelerator(this.getAccelerator("K"));
        radioButtonMenuItem2.addActionListener(actionListener);
        buttonGroup.add(radioButtonMenuItem2);
        menu2.add(radioButtonMenuItem2);
        radioButtonMenuItem3.addActionListener(actionListener);
        buttonGroup.add(radioButtonMenuItem3);
        menu2.add(radioButtonMenuItem3);
        radioButtonMenuItem4.addActionListener(actionListener);
        buttonGroup.add(radioButtonMenuItem4);
        menu2.add(radioButtonMenuItem4);
        radioButtonMenuItem5.addActionListener(actionListener);
        buttonGroup.add(radioButtonMenuItem5);
        menu2.add(radioButtonMenuItem5);
        this.mandelbrotColorMenuItems = new JRadioButtonMenuItem[] { radioButtonMenuItem, radioButtonMenuItem2, radioButtonMenuItem3, radioButtonMenuItem4, radioButtonMenuItem5 };
        this.checkMandelbrotColor();
        for (final String actionCommand : array) {
            final JMenuItem menuItem10 = new JMenuItem(I18n.tr("paletteEditDialog.menuCommand.LoadDefault." + actionCommand, new Object[0]));
            menuItem10.setActionCommand(actionCommand);
            menuItem10.addActionListener(actionListener);
            menu3.add(menuItem10);
        }
        menuItem2.addActionListener(actionListener);
        menuItem2.setAccelerator(this.getAccelerator("R"));
        menuItem3.addActionListener(actionListener);
        menuItem3.setAccelerator(this.getAccelerator("shift R"));
        menuItem4.addActionListener(actionListener);
        menuItem4.setAccelerator(this.getAccelerator("I"));
        menuItem5.addActionListener(actionListener);
        menuItem5.setAccelerator(this.getAccelerator("shift I"));
        menuItem6.addActionListener(actionListener);
        menuItem7.addActionListener(actionListener);
        this.enableHighPrecision.addActionListener(actionListener);
        this.enableHighPrecision.setSelected(this.display.getHighPrecisionEnabled());
        this.enableSubpixelSampling.addActionListener(actionListener);
        this.enableSubpixelSampling.setSelected(this.display.getSubpixelSamplingEnabled());
        menuItem8.addActionListener(actionListener);
        menuItem9.addActionListener(actionListener);
        menu.add(menuItem);
        menu.add(menu2);
        menu.add(menu3);
        menu.addSeparator();
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        menu.add(menuItem7);
        menu.addSeparator();
        menu.add(this.enableHighPrecision);
        menu.add(this.enableSubpixelSampling);
        if (!this.isApplet) {
            menu.add(menuItem8);
        }
        menu.addSeparator();
        menu.add(menuItem9);
        return menu;
    }
    
    private void checkMandelbrotColor() {
        final Color mandelbrotColor = this.display.getMandelbrotColor();
        if (mandelbrotColor.equals(Color.BLACK)) {
            this.mandelbrotColorMenuItems[0].setSelected(true);
        }
        else if (mandelbrotColor.equals(Color.GRAY)) {
            this.mandelbrotColorMenuItems[1].setSelected(true);
        }
        else if (mandelbrotColor.equals(Color.WHITE)) {
            this.mandelbrotColorMenuItems[2].setSelected(true);
        }
        else if (mandelbrotColor.equals(Color.BLUE)) {
            this.mandelbrotColorMenuItems[3].setSelected(true);
        }
        else {
            this.mandelbrotColorMenuItems[4].setSelected(true);
        }
    }
    
    private JMenu makeIterationsMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.MaximumIterationCount", new Object[0]));
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.maxIterationMenuItems = new ArrayList<JRadioButtonMenuItem>();
        final int[] maxIterationMenuValues = this.maxIterationMenuValues;
        for (int length = maxIterationMenuValues.length, i = 0; i < length; ++i) {
            this.maxIterationMenuItems.add(new JRadioButtonMenuItem("" + maxIterationMenuValues[i]));
        }
        this.maxIterationMenuItems.add(new JRadioButtonMenuItem(I18n.tr("menuCommand.Custom", new Object[0])));
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                for (int i = 0; i < MandelbrotMenus.this.maxIterationMenuValues.length; ++i) {
                    if (source == MandelbrotMenus.this.maxIterationMenuItems.get(i)) {
                        MandelbrotMenus.this.display.setMaxIterations(MandelbrotMenus.this.maxIterationMenuValues[i]);
                        return;
                    }
                }
                final String showInputDialog = JOptionPane.showInputDialog(MandelbrotMenus.this.display, I18n.tr("mandelbrotMenus.question.GetCustomMaxIterations", new Object[0]), "" + MandelbrotMenus.this.display.getMaxIterations());
                if (showInputDialog == null) {
                    return;
                }
                try {
                    final int int1 = Integer.parseInt(showInputDialog);
                    if (int1 < 2) {
                        throw new NumberFormatException();
                    }
                    MandelbrotMenus.this.display.setMaxIterations(int1);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MandelbrotMenus.this.display, I18n.tr("mandelbrotMenus.error.BadMaxIterationValue", new Object[0]));
                }
            }
        };
        for (final JRadioButtonMenuItem radioButtonMenuItem : this.maxIterationMenuItems) {
            buttonGroup.add(radioButtonMenuItem);
            menu.add(radioButtonMenuItem);
            radioButtonMenuItem.addActionListener(actionListener);
        }
        this.checkMaxIterations(this.display.getMaxIterations());
        return menu;
    }
    
    private void checkMaxIterations(final int n) {
        final JRadioButtonMenuItem radioButtonMenuItem = this.maxIterationMenuItems.get(this.maxIterationMenuItems.size() - 1);
        for (int i = 0; i < this.maxIterationMenuValues.length; ++i) {
            if (n == this.maxIterationMenuValues[i]) {
                this.maxIterationMenuItems.get(i).setSelected(true);
                radioButtonMenuItem.setText(I18n.tr("menuCommand.Custom", new Object[0]));
                return;
            }
        }
        radioButtonMenuItem.setText(I18n.tr("menuCommand.CustomWithCurrentValue", "" + n));
        radioButtonMenuItem.setSelected(true);
    }
    
    private JMenu makeImageSizeMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.ImageSize", new Object[0]));
        (this.imageSizeMenuItems = new ArrayList<JRadioButtonMenuItem>()).add(new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.ImageSizeMatchesWindowSize", new Object[0])));
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        boolean b = false;
        for (int i = 1; i < this.imageSizes.length; ++i) {
            final Dimension dimension = this.imageSizes[i];
            String s = I18n.tr("mandelbrotMenu.command.ImageSizeWithWidthAndHeight", "" + dimension.width, "" + dimension.height);
            if (dimension.equals(screenSize)) {
                s = "*" + s;
                b = true;
            }
            this.imageSizeMenuItems.add(new JRadioButtonMenuItem(s));
        }
        if (!b) {
            this.imageSizeMenuItems.add(new JRadioButtonMenuItem("*" + I18n.tr("mandelbrotMenu.command.ImageSizeWithWidthAndHeight", "" + screenSize.width, "" + screenSize.height)));
            final Dimension[] imageSizes = new Dimension[this.imageSizes.length + 1];
            System.arraycopy(this.imageSizes, 0, imageSizes, 0, this.imageSizes.length);
            imageSizes[imageSizes.length - 1] = screenSize;
            this.imageSizes = imageSizes;
        }
        this.imageSizeMenuItems.add(new JRadioButtonMenuItem(I18n.tr("menuCommand.Custom", new Object[0])));
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                for (int i = 0; i < MandelbrotMenus.this.imageSizeMenuItems.size() - 1; ++i) {
                    if (source == MandelbrotMenus.this.imageSizeMenuItems.get(i)) {
                        MandelbrotMenus.this.display.setImageSize(MandelbrotMenus.this.imageSizes[i]);
                        return;
                    }
                }
                final Dimension showDialog = ImageSizeDialog.showDialog(MandelbrotMenus.this.display, MandelbrotMenus.this.display.getImageSize());
                if (showDialog != null) {
                    MandelbrotMenus.this.display.setImageSize(showDialog);
                }
            }
        };
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final JRadioButtonMenuItem radioButtonMenuItem : this.imageSizeMenuItems) {
            buttonGroup.add(radioButtonMenuItem);
            radioButtonMenuItem.addActionListener(actionListener);
            menu.add(radioButtonMenuItem);
        }
        this.imageSizeMenuItems.get(0).setAccelerator(this.getAccelerator("EQUALS"));
        this.checkImageSize(this.display.getImageSize());
        return menu;
    }
    
    private void checkImageSize(final Dimension dimension) {
        final JRadioButtonMenuItem radioButtonMenuItem = this.imageSizeMenuItems.get(this.imageSizeMenuItems.size() - 1);
        if (dimension == null) {
            this.imageSizeMenuItems.get(0).setSelected(true);
            radioButtonMenuItem.setText(I18n.tr("menuCommand.Custom", new Object[0]));
            return;
        }
        for (int i = 1; i < this.imageSizes.length; ++i) {
            if (dimension.equals(this.imageSizes[i])) {
                this.imageSizeMenuItems.get(i).setSelected(true);
                radioButtonMenuItem.setText(I18n.tr("menuCommand.Custom", new Object[0]));
                return;
            }
        }
        radioButtonMenuItem.setText(I18n.tr("menuCommand.CustomWithCurrentValue", I18n.tr("mandelbrotMenu.command.ImageSizeWithWidthAndHeight", "" + dimension.width, "" + dimension.height)));
        radioButtonMenuItem.setSelected(true);
    }
    
    private JMenu makeToolsMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.Tools", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.ZoomIn", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem2 = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.ZoomOut", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem3 = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.Drag", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem4 = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.ShowOrbit", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem5 = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.ShowCoords", new Object[0]));
        final JRadioButtonMenuItem radioButtonMenuItem6 = new JRadioButtonMenuItem(I18n.tr("mandelbrotMenu.command.tool.RecenterOnPoint", new Object[0]));
        final JMenuItem menuItem = new JMenuItem(I18n.tr("mandelbrotMenu.command.ToolMenu.help", new Object[0]));
        this.toolsMenuItems = new JRadioButtonMenuItem[] { radioButtonMenuItem, radioButtonMenuItem2, radioButtonMenuItem3, radioButtonMenuItem4, radioButtonMenuItem5, radioButtonMenuItem6 };
        this.toolCodes = new int[] { 0, 1, 2, 3, 4, 5 };
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object source = actionEvent.getSource();
                if (source == menuItem) {
                    JOptionPane.showMessageDialog(MandelbrotMenus.this.display, I18n.tr("mandelbrotMenu.ToolMenu.HelpText", new Object[0]));
                    return;
                }
                for (int i = 0; i < MandelbrotMenus.this.toolsMenuItems.length; ++i) {
                    if (source == MandelbrotMenus.this.toolsMenuItems[i]) {
                        MandelbrotMenus.this.display.setDefaultMouseAction(MandelbrotMenus.this.toolCodes[i]);
                        return;
                    }
                }
            }
        };
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final JRadioButtonMenuItem radioButtonMenuItem7 : this.toolsMenuItems) {
            menu.add(radioButtonMenuItem7);
            buttonGroup.add(radioButtonMenuItem7);
            radioButtonMenuItem7.addActionListener(actionListener);
        }
        this.checkTool();
        menu.addSeparator();
        menuItem.addActionListener(actionListener);
        radioButtonMenuItem.setAccelerator(this.getAccelerator("M"));
        menu.add(menuItem);
        return menu;
    }
    
    private void checkTool() {
        final int defaultMouseAction = this.display.getDefaultMouseAction();
        for (int i = 0; i < this.toolCodes.length; ++i) {
            if (defaultMouseAction == this.toolCodes[i]) {
                this.toolsMenuItems[i].setSelected(true);
                return;
            }
        }
    }
    
    private JMenu makeExamplesMenu() {
        final JMenu menu = new JMenu(I18n.tr("mandelbrotMenu.menuName.Examples", new Object[0]));
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String actionCommand = actionEvent.getActionCommand();
                try {
                    final MandelbrotSettings fromXML = MandelbrotSettings.createFromXML(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getClass().getClassLoader().getResource("edu/hws/eck/umb/resources/examples/" + actionCommand).openStream()).getDocumentElement());
                    final MandelbrotSettings mandelbrotSettings = new MandelbrotSettings(MandelbrotMenus.this.display);
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = true;
                    MandelbrotMenus.this.display.applySettings(fromXML);
                    MandelbrotMenus.this.addToUndoManagerIsSuspended = false;
                    MandelbrotMenus.this.undoManager.addEdit(new UndoableChange("MB_CHANGE_ALL_SETTINGS", mandelbrotSettings, fromXML));
                    MandelbrotMenus.this.updateUndoRedoItems();
                }
                catch (Exception ex) {}
            }
        };
        for (int i = 1; i <= 12; ++i) {
            try {
                final JMenuItem menuItem = new JMenuItem(new ImageIcon(Toolkit.getDefaultToolkit().createImage(classLoader.getResource("edu/hws/eck/umb/resources/examples/Example" + i + ".xml_68x51.png"))));
                menuItem.addActionListener(actionListener);
                menuItem.setActionCommand("Example" + i + ".xml");
                menu.add(menuItem);
            }
            catch (Exception ex) {}
        }
        return menu;
    }
    
    private SimpleFileChooser getFileChooser() {
        if (this.fileChooser == null) {
            this.fileChooser = new SimpleFileChooser();
            final String pref = Util.getPref("fileio.defaultDirectory");
            if (pref != null) {
                this.fileChooser.setDefaultDirectory(pref);
            }
        }
        return this.fileChooser;
    }
    
    private void saveDirectoryPref() {
        Util.setPref("fileio.defaultDirectory", this.fileChooser.getCurrentDirectory());
    }
    
    private void doSave() {
        final File outputFile = this.getFileChooser().getOutputFile(this.display, I18n.tr("mandelbrotMenus.saveDialog.title", new Object[0]), I18n.tr("mandelbrotMenus.saveDialog.defaultFileName", new Object[0]));
        if (outputFile == null) {
            return;
        }
        try {
            final PrintWriter printWriter = new PrintWriter(outputFile);
            printWriter.print("<?xml version='1.0'?>\n");
            printWriter.print(new MandelbrotSettings(this.display).toXML());
            printWriter.flush();
            printWriter.close();
            if (printWriter.checkError()) {
                throw new Exception(I18n.tr("mandelbrotMenus.saveDialog.error.genericWriteError", new Object[0]));
            }
            this.setStatusBarText(I18n.tr("statusBar.text.SettingsSavedToFile", outputFile.getAbsolutePath()), 3);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.saveDialog.error.cantWriteFile", outputFile.getName(), ex.getMessage()));
            return;
        }
        this.saveDirectoryPref();
    }
    
    private void doSaveImage(final String s) {
        final SimpleFileChooser fileChooser = this.getFileChooser();
        final String tr = I18n.tr("mandelbrotMenus.saveImageDialog.defaultFileNameWithoutExtension", new Object[0]);
        String s2;
        if (s.equals("PNG")) {
            s2 = tr + ".png";
        }
        else {
            s2 = tr + ".jpeg";
        }
        final File outputFile = fileChooser.getOutputFile(this.display, s.equals("PNG") ? I18n.tr("mandelbrotMenus.savePNGImageDialog.title", new Object[0]) : I18n.tr("mandelbrotMenus.saveJPEGImageDialog.title", new Object[0]), s2);
        if (outputFile == null) {
            return;
        }
        try {
            if (!this.display.writeImage(outputFile, s)) {
                throw new IOException(I18n.tr("mandelbrotMenus.saveImageDialog.ImageFormatNotSupported", s));
            }
            this.setStatusBarText(I18n.tr("statusBar.text.ImageSavedToFile", outputFile.getAbsolutePath()), 3);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.saveDialog.error.cantWriteFile", outputFile.getName(), ex.getMessage()));
            return;
        }
        this.saveDirectoryPref();
    }
    
    private MandelbrotSettings doOpen() {
        final File inputFile = this.getFileChooser().getInputFile(this.display, I18n.tr("mandelbrotMenus.openDialog.title", new Object[0]));
        if (inputFile == null) {
            return null;
        }
        Document parse;
        try {
            parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.openDialog.error.cantReadFile", inputFile.getName(), ex.getMessage()));
            return null;
        }
        catch (SAXException ex4) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.openDialog.error.fileIsNotXML", inputFile.getName()));
            return null;
        }
        catch (ParserConfigurationException ex2) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.openDialog.error.cantReadFile", inputFile.getName(), ex2.getMessage()));
            return null;
        }
        try {
            final MandelbrotSettings fromXML = MandelbrotSettings.createFromXML(parse.getDocumentElement());
            this.saveDirectoryPref();
            return fromXML;
        }
        catch (IOException ex3) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("mandelbrotMenus.openDialog.error.fileIsNotMandelbrotSettngs", inputFile.getName(), ex3.getMessage()));
            return null;
        }
    }
    
    private void updateUndoRedoItems() {
        this.undoMenuItem.setText(this.undoManager.getUndoPresentationName());
        this.redoMenuItem.setText(this.undoManager.getRedoPresentationName());
        this.undoMenuItem.setEnabled(this.undoManager.canUndo());
        this.redoMenuItem.setEnabled(this.undoManager.canRedo());
    }
    
    private class UndoableChange extends AbstractUndoableEdit
    {
        private String propertyName;
        private Object oldValue;
        private Object newValue;
        
        UndoableChange(final String propertyName, final Object oldValue, final Object newValue) {
            this.propertyName = propertyName;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
        
        public String getPresentationName() {
            if (this.propertyName.equals("MB_CHANGE_ALL_SETTINGS")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ChangeAllSettings", new Object[0]);
            }
            if (this.propertyName.equals("mb_property_size")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ChangeImageSize", new Object[0]);
            }
            if (this.propertyName.equals("mb_property_limits")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ChangeLimits", new Object[0]);
            }
            if (this.propertyName.equals("mp_propery_mb_color")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ChangeMandelbrotColor", new Object[0]);
            }
            if (this.propertyName.equals("mb_property_iterations")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ChangeMaxIterations", new Object[0]);
            }
            if (this.propertyName.equals("mb_property_palette")) {
                return I18n.tr("mandelbrotMenus.nameForEditAction.ModifyPalette", new Object[0]);
            }
            return null;
        }
        
        public void undo() {
            super.undo();
            this.apply(true);
        }
        
        public void redo() {
            super.redo();
            this.apply(false);
        }
        
        private void apply(final boolean b) {
            final Object o = b ? this.oldValue : this.newValue;
            if (this.propertyName.equals("MB_CHANGE_ALL_SETTINGS")) {
                MandelbrotMenus.this.display.applySettings((MandelbrotSettings)o);
            }
            else if (this.propertyName.equals("mb_property_size")) {
                MandelbrotMenus.this.display.setImageSize((Dimension)o);
            }
            else if (this.propertyName.equals("mb_property_limits")) {
                MandelbrotMenus.this.display.setLimits((BigDecimal[])o);
            }
            else if (this.propertyName.equals("mp_propery_mb_color")) {
                MandelbrotMenus.this.display.setMandelbrotColor((Color)o);
            }
            else if (this.propertyName.equals("mb_property_iterations")) {
                MandelbrotMenus.this.display.setMaxIterations((int)o);
            }
            else if (this.propertyName.equals("mb_property_palette")) {
                final MandelbrotDisplay.PaletteInfo paletteInfo = (MandelbrotDisplay.PaletteInfo)o;
                MandelbrotMenus.this.display.setPaletteInfo(paletteInfo.palette, paletteInfo.paletteMapping);
            }
        }
    }
}
