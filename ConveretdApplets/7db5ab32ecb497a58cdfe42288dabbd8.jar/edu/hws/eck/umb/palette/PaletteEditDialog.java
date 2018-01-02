// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.math.BigDecimal;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import edu.hws.eck.umb.util.I18n;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import edu.hws.eck.umb.MandelbrotDisplay;
import javax.swing.JDialog;

public class PaletteEditDialog extends JDialog
{
    private MandelbrotDisplay owner;
    private MandelbrotDisplay preview;
    private HistogramPanel histogramPanel;
    private Palette originalPalette;
    private Palette paletteInEditor;
    private PaletteMapping originalPaletteMapping;
    private PaletteMapping paletteMappingInEditor;
    private PaletteEditPanel paletteEditor;
    private JButton closeButton;
    private JButton revertButton;
    private JButton applyButton;
    private JButton helpButton;
    private JButton addButton;
    private JButton deleteSelectedButton;
    private JButton editSelectedButton;
    private JCheckBox paletteMatchesMaxIterations;
    private JTextField paletteLengthInput;
    private JTextField paletteOffsetInput;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem undoTransform;
    private MandelbrotDisplay.PaletteInfo paletteForUndoTransform;
    
    public static JDialog createDialog(final MandelbrotDisplay mandelbrotDisplay) {
        Container parent;
        for (parent = mandelbrotDisplay; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        return new PaletteEditDialog((Frame)parent, mandelbrotDisplay);
    }
    
    private PaletteEditDialog(final Frame frame, final MandelbrotDisplay owner) {
        super(frame, I18n.tr("paletteEditDialog.title", new Object[0]), false);
        this.paletteInEditor = owner.getCopyOfPalette();
        this.originalPalette = this.paletteInEditor.clone();
        this.paletteMappingInEditor = new PaletteMapping(owner.getPaletteLength(), owner.getPaletteOffset());
        this.originalPaletteMapping = new PaletteMapping(owner.getPaletteLength(), owner.getPaletteOffset());
        this.owner = owner;
        this.paletteEditor = new PaletteEditPanel(this.paletteInEditor);
        this.closeButton = new JButton(I18n.tr("buttonName.Close", new Object[0]));
        this.revertButton = new JButton(I18n.tr("buttonName.Revert", new Object[0]));
        this.applyButton = new JButton(I18n.tr("buttonName.Apply", new Object[0]));
        this.helpButton = new JButton(I18n.tr("buttonName.Help", new Object[0]));
        this.deleteSelectedButton = new JButton(this.paletteEditor.actionDeleteSelected);
        this.editSelectedButton = new JButton(this.paletteEditor.actionEditSelected);
        this.addButton = new JButton(this.paletteEditor.actionAddColor);
        final ButtonHandler buttonHandler = new ButtonHandler();
        this.closeButton.addActionListener(buttonHandler);
        this.revertButton.addActionListener(buttonHandler);
        this.applyButton.addActionListener(buttonHandler);
        this.helpButton.addActionListener(buttonHandler);
        this.deleteSelectedButton.setEnabled(false);
        this.editSelectedButton.setEnabled(false);
        this.revertButton.setEnabled(false);
        this.applyButton.setEnabled(false);
        (this.paletteMatchesMaxIterations = new JCheckBox(I18n.tr("paletteEditDialog.buttonName.lockPaletteLengthToMaxIterations", new Object[0]))).setSelected(this.paletteMappingInEditor.getLength() == 0);
        this.paletteMatchesMaxIterations.setBackground(Color.LIGHT_GRAY);
        this.paletteMatchesMaxIterations.addActionListener(buttonHandler);
        (this.paletteLengthInput = new JTextField("" + this.paletteMappingInEditor.getLength(), 5)).setEditable(this.paletteMappingInEditor.getLength() != 0);
        this.paletteOffsetInput = new JTextField("" + this.paletteMappingInEditor.getOffset(), 5);
        this.paletteLengthInput.addActionListener(buttonHandler);
        this.paletteOffsetInput.addActionListener(buttonHandler);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        contentPane.setLayout(new BorderLayout());
        this.setContentPane(contentPane);
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout(3, 3));
        panel.add(this.paletteEditor, "Center");
        final JPanel panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setLayout(new FlowLayout(1, 3, 3));
        panel2.add(this.addButton);
        panel2.add(this.deleteSelectedButton);
        panel2.add(this.editSelectedButton);
        panel.add(panel2, "South");
        contentPane.add(panel, "Center");
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(1, 3, 3));
        panel3.setBackground(Color.LIGHT_GRAY);
        panel3.add(this.helpButton);
        panel3.add(this.applyButton);
        panel3.add(this.revertButton);
        panel3.add(this.closeButton);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(1, 3, 3));
        panel4.setBackground(Color.LIGHT_GRAY);
        panel4.add(new JLabel(I18n.tr("paletteEditDialog.textInputLabel.PaletteOffset", new Object[0]) + " = "));
        panel4.add(this.paletteOffsetInput);
        panel4.add(Box.createHorizontalStrut(15));
        panel4.add(new JLabel(I18n.tr("paletteEditDialog.textInputLabel.PaletteLength", new Object[0]) + " = "));
        panel4.add(this.paletteLengthInput);
        panel4.add(Box.createHorizontalStrut(5));
        panel4.add(this.paletteMatchesMaxIterations);
        final JPanel panel5 = new JPanel();
        panel5.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panel5.setBackground(Color.LIGHT_GRAY);
        panel5.setLayout(new BorderLayout(3, 3));
        panel5.add(this.histogramPanel = new HistogramPanel(this.paletteInEditor, this.paletteMappingInEditor), "West");
        (this.preview = new MandelbrotDisplay(false, false)).setPreferredSize(new Dimension(180, 180));
        this.preview.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.preview.setMaxIterations(this.owner.getMaxIterations());
        this.preview.setLimits(this.owner.getLimitsRequested());
        this.preview.setPaletteOffset(this.owner.getPaletteOffset());
        this.preview.setPaletteLength(this.owner.getPaletteLength());
        this.preview.setPalette(this.paletteInEditor);
        this.preview.setMandelbrotColor(this.owner.getMandelbrotColor());
        panel5.add(this.preview, "Center");
        panel5.add(panel3, "South");
        panel5.add(panel4, "North");
        contentPane.add(panel5, "South");
        this.setJMenuBar(this.makeMenuBar(buttonHandler));
        this.paletteEditor.addPropertyChangeListener("PaletteEditPanel_Selected_Index", new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final int intValue = (int)propertyChangeEvent.getNewValue();
                PaletteEditDialog.this.deleteSelectedButton.setEnabled(intValue > 0 && intValue < PaletteEditDialog.this.paletteInEditor.getDivisionPointCount() - 1);
                PaletteEditDialog.this.editSelectedButton.setEnabled(intValue >= 0);
            }
        });
        final PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final String propertyName = propertyChangeEvent.getPropertyName();
                if (propertyName.equals("mb_stauts")) {
                    if (((MandelbrotDisplay.StatusInfo)propertyChangeEvent.getNewValue()).status != 3) {
                        PaletteEditDialog.this.histogramPanel.setHistogram(PaletteEditDialog.this.owner.createIterationCountHistogram());
                    }
                }
                else if (propertyName.equals("mb_property_palette")) {
                    final Palette palette = ((MandelbrotDisplay.PaletteInfo)propertyChangeEvent.getNewValue()).palette;
                    final PaletteMapping paletteMapping = ((MandelbrotDisplay.PaletteInfo)propertyChangeEvent.getNewValue()).paletteMapping;
                    if (!palette.equals(PaletteEditDialog.this.paletteEditor) || !paletteMapping.equals(PaletteEditDialog.this.paletteMappingInEditor)) {
                        PaletteEditDialog.this.originalPalette = palette;
                        PaletteEditDialog.this.originalPaletteMapping = paletteMapping;
                        PaletteEditDialog.this.paletteInEditor.copyFrom(palette);
                        PaletteEditDialog.this.paletteMappingInEditor.setLength(paletteMapping.getLength());
                        PaletteEditDialog.this.paletteMappingInEditor.setOffset(paletteMapping.getOffset());
                        PaletteEditDialog.this.applyButton.setEnabled(false);
                        PaletteEditDialog.this.revertButton.setEnabled(false);
                    }
                }
                else if (propertyName.equals("mb_property_limits")) {
                    PaletteEditDialog.this.preview.setLimits((BigDecimal[])propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mb_property_hp_enabled")) {
                    PaletteEditDialog.this.preview.setHighPrecisionEnabled((boolean)propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mp_propery_mb_color")) {
                    PaletteEditDialog.this.preview.setMandelbrotColor((Color)propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mb_property_iterations")) {
                    PaletteEditDialog.this.preview.setMaxIterations((int)propertyChangeEvent.getNewValue());
                }
            }
        };
        this.owner.addPropertyChangeListener(propertyChangeListener);
        this.histogramPanel.setHistogram(this.owner.createIterationCountHistogram());
        final ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                PaletteEditDialog.this.revertButton.setEnabled(true);
                PaletteEditDialog.this.applyButton.setEnabled(true);
                PaletteEditDialog.this.undoTransform.setEnabled(false);
                PaletteEditDialog.this.paletteForUndoTransform = null;
                if (changeEvent.getSource() instanceof Palette) {
                    PaletteEditDialog.this.preview.setPalette(PaletteEditDialog.this.paletteInEditor);
                }
                else if (changeEvent.getSource() instanceof PaletteMapping) {
                    PaletteEditDialog.this.preview.setPaletteLength(PaletteEditDialog.this.paletteMappingInEditor.getLength());
                    PaletteEditDialog.this.preview.setPaletteOffset(PaletteEditDialog.this.paletteMappingInEditor.getOffset());
                    PaletteEditDialog.this.paletteLengthInput.setText("" + PaletteEditDialog.this.paletteMappingInEditor.getLength());
                    PaletteEditDialog.this.paletteOffsetInput.setText("" + PaletteEditDialog.this.paletteMappingInEditor.getOffset());
                    PaletteEditDialog.this.paletteMatchesMaxIterations.setSelected(PaletteEditDialog.this.paletteMappingInEditor.getLength() == 0);
                    PaletteEditDialog.this.paletteLengthInput.setEditable(PaletteEditDialog.this.paletteMappingInEditor.getLength() != 0);
                }
            }
        };
        this.paletteInEditor.addChangeListener(changeListener);
        this.paletteMappingInEditor.addChangeListener(changeListener);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                PaletteEditDialog.this.preview.closing();
                PaletteEditDialog.this.paletteEditor.closing();
                PaletteEditDialog.this.paletteInEditor.removeChangeListener(changeListener);
                PaletteEditDialog.this.owner.removePropertyChangeListener(propertyChangeListener);
            }
        });
        final DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(final DocumentEvent documentEvent) {
            }
            
            public void insertUpdate(final DocumentEvent documentEvent) {
                PaletteEditDialog.this.applyButton.setEnabled(true);
            }
            
            public void removeUpdate(final DocumentEvent documentEvent) {
                PaletteEditDialog.this.applyButton.setEnabled(true);
            }
        };
        this.paletteLengthInput.getDocument().addDocumentListener(documentListener);
        this.paletteOffsetInput.getDocument().addDocumentListener(documentListener);
        contentPane.getInputMap(2).put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        contentPane.getActionMap().put("cancel", new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                PaletteEditDialog.this.closeButton.doClick();
            }
        });
        this.setDefaultCloseOperation(2);
        this.pack();
        this.setResizable(false);
        if (frame != null) {
            final Rectangle bounds = frame.getBounds();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = bounds.x + bounds.width + 8;
            if (n + this.getWidth() > screenSize.width) {
                n = screenSize.width - this.getWidth() - 5;
            }
            int n2 = bounds.y + bounds.height - this.getHeight() - 5;
            if (n2 + this.getHeight() > screenSize.height) {
                n2 = screenSize.height - this.getHeight() - 5;
            }
            this.setLocation(n, n2);
        }
    }
    
    private void doTransform(final String s) {
        final ArrayList<Double> list = new ArrayList<Double>();
        final ArrayList<float[]> list2 = new ArrayList<float[]>();
        int colorType = this.paletteInEditor.getColorType();
        final int offset = this.paletteMappingInEditor.getOffset();
        int length = this.paletteMappingInEditor.getLength();
        final int divisionPointCount = this.paletteInEditor.getDivisionPointCount();
        if (s.equals("Transform/Flip")) {
            for (int i = divisionPointCount - 1; i >= 0; --i) {
                list.add(1.0 - this.paletteInEditor.getDivisionPoint(i));
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(i));
            }
        }
        else if (s.equals("Transform/Extend")) {
            for (int j = 0; j < divisionPointCount; ++j) {
                list.add(this.paletteInEditor.getDivisionPoint(j) / 2.0);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(j));
            }
            list.add(1.0);
            list2.add(this.paletteInEditor.getDivisionPointColorComponents(divisionPointCount - 1));
            if (length > 0) {
                length *= 2;
            }
        }
        else if (s.equals("Transform/ExtendDuplicate")) {
            for (int k = 0; k < divisionPointCount; ++k) {
                list.add(this.paletteInEditor.getDivisionPoint(k) / 2.0);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(k));
            }
            final float[] divisionPointColorComponents = this.paletteInEditor.getDivisionPointColorComponents(divisionPointCount - 1);
            final float[] divisionPointColorComponents2 = this.paletteInEditor.getDivisionPointColorComponents(0);
            if (divisionPointColorComponents[0] != divisionPointColorComponents2[0] || divisionPointColorComponents[1] != divisionPointColorComponents2[1] || divisionPointColorComponents[2] != divisionPointColorComponents2[2]) {
                list.add(0.5001);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(0));
            }
            for (int l = 1; l < divisionPointCount; ++l) {
                list.add(0.5 + this.paletteInEditor.getDivisionPoint(l) / 2.0);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(l));
            }
            if (length > 0) {
                length *= 2;
            }
        }
        else if (s.equals("Transform/ExtendMirror")) {
            for (int n = 0; n < divisionPointCount; ++n) {
                list.add(this.paletteInEditor.getDivisionPoint(n) / 2.0);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(n));
            }
            for (int n2 = divisionPointCount - 2; n2 >= 0; --n2) {
                list.add(1.0 - this.paletteInEditor.getDivisionPoint(n2) / 2.0);
                list2.add(this.paletteInEditor.getDivisionPointColorComponents(n2));
            }
            if (length > 0) {
                length *= 2;
            }
        }
        else {
            final int n3 = 3 + (int)(Math.random() * 5.0);
            final double[] array = new double[n3];
            array[0] = 0.0;
            for (int n4 = 1; n4 < n3 - 1; ++n4) {
                boolean b;
                double n5;
                do {
                    n5 = (int)(10000.0 * Math.random()) / 10000.0;
                    b = false;
                    for (int n6 = 0; n6 < n4; ++n6) {
                        if (Math.abs(n5 - array[n6]) < 0.05) {
                            b = true;
                        }
                    }
                } while (b);
                array[n4] = n5;
            }
            array[n3 - 1] = 1.0;
            Arrays.sort(array);
            for (int n7 = 0; n7 < n3; ++n7) {
                list.add(array[n7]);
                list2.add(new float[] { (float)Math.random(), (float)Math.random(), (float)Math.random() });
            }
            colorType = ((Math.random() < 0.5) ? 1 : 0);
        }
        final MandelbrotDisplay.PaletteInfo paletteForUndoTransform = new MandelbrotDisplay.PaletteInfo(this.paletteInEditor.clone(), this.paletteMappingInEditor.clone());
        this.paletteInEditor.copyFrom(new Palette(colorType, true, list, list2));
        this.paletteMappingInEditor.setLength(length);
        this.paletteMappingInEditor.setOffset(offset);
        this.undoTransform.setEnabled(true);
        this.paletteForUndoTransform = paletteForUndoTransform;
    }
    
    private JMenuBar makeMenuBar(final ActionListener actionListener) {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu(I18n.tr("menuName.File", new Object[0]));
        this.open = new JMenuItem(I18n.tr("menuCommand.FileMenu.Open", new Object[0]));
        this.save = new JMenuItem(I18n.tr("menuCommand.FileMenu.Save", new Object[0]));
        menu.add(this.open);
        menu.add(this.save);
        this.open.addActionListener(actionListener);
        this.save.addActionListener(actionListener);
        menuBar.add(menu);
        final JMenu menu2 = new JMenu(I18n.tr("paletteEditDialog.menuName.LoadDefault", new Object[0]));
        for (final String actionCommand : new String[] { "Spectrum", "PaleSpectrum", "Grayscale", "CyclicGrayscale", "CyclicRedCyan", "EarthSky", "HotCold", "Fire" }) {
            final JMenuItem menuItem = new JMenuItem(I18n.tr("paletteEditDialog.menuCommand.LoadDefault." + actionCommand, new Object[0]));
            menuItem.setActionCommand(actionCommand);
            menuItem.addActionListener(actionListener);
            menu2.add(menuItem);
        }
        menuBar.add(menu2);
        final JMenu menu3 = new JMenu(I18n.tr("paletteEditDialog.menuName.Transform", new Object[0]));
        (this.undoTransform = new JMenuItem(I18n.tr("paletteEditDialog.menuCommand.UndoTransform", new Object[0]))).addActionListener(actionListener);
        this.undoTransform.setEnabled(false);
        for (final String s : new String[] { "Flip", "Extend", "ExtendDuplicate", "ExtendMirror", "Random" }) {
            final JMenuItem menuItem2 = new JMenuItem(I18n.tr("paletteEditDialog.menuCommand.Transform." + s, new Object[0]));
            menuItem2.setActionCommand("Transform/" + s);
            menuItem2.addActionListener(actionListener);
            menu3.add(menuItem2);
        }
        menu3.addSeparator();
        menu3.add(this.undoTransform);
        menuBar.add(menu3);
        return menuBar;
    }
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            final String actionCommand = actionEvent.getActionCommand();
            if (source == PaletteEditDialog.this.closeButton) {
                PaletteEditDialog.this.dispose();
            }
            else if (source == PaletteEditDialog.this.revertButton) {
                PaletteEditDialog.this.paletteInEditor.copyFrom(PaletteEditDialog.this.originalPalette);
                PaletteEditDialog.this.paletteMappingInEditor.setLength(PaletteEditDialog.this.originalPaletteMapping.getLength());
                PaletteEditDialog.this.paletteMappingInEditor.setOffset(PaletteEditDialog.this.originalPaletteMapping.getOffset());
                PaletteEditDialog.this.paletteMatchesMaxIterations.setSelected(PaletteEditDialog.this.originalPaletteMapping.getLength() == 0);
                PaletteEditDialog.this.paletteLengthInput.setText("" + PaletteEditDialog.this.originalPaletteMapping.getLength());
                PaletteEditDialog.this.paletteOffsetInput.setText("" + PaletteEditDialog.this.originalPaletteMapping.getOffset());
                PaletteEditDialog.this.paletteLengthInput.setEditable(PaletteEditDialog.this.originalPaletteMapping.getLength() != 0);
                PaletteEditDialog.this.revertButton.setEnabled(false);
                PaletteEditDialog.this.applyButton.setEnabled(false);
            }
            else {
                if (source != PaletteEditDialog.this.applyButton) {
                    if (!(source instanceof JTextField)) {
                        if (source == PaletteEditDialog.this.paletteMatchesMaxIterations) {
                            if (PaletteEditDialog.this.paletteMatchesMaxIterations.isSelected()) {
                                PaletteEditDialog.this.paletteLengthInput.setText("0");
                                PaletteEditDialog.this.paletteLengthInput.setEditable(false);
                                PaletteEditDialog.this.paletteMappingInEditor.setLength(0);
                                return;
                            }
                            PaletteEditDialog.this.paletteLengthInput.setText("" + PaletteEditDialog.this.owner.getMaxIterations());
                            PaletteEditDialog.this.paletteLengthInput.setEditable(true);
                            PaletteEditDialog.this.paletteMappingInEditor.setLength(PaletteEditDialog.this.owner.getMaxIterations());
                            return;
                        }
                        else {
                            if (source == PaletteEditDialog.this.helpButton) {
                                JOptionPane.showMessageDialog(PaletteEditDialog.this, new JLabel(I18n.tr("paletteEditDialog.helpText", new Object[0])));
                                return;
                            }
                            if (source == PaletteEditDialog.this.open) {
                                final Palette doOpen = PaletteIO.doOpen(PaletteEditDialog.this);
                                if (doOpen != null) {
                                    PaletteEditDialog.this.paletteInEditor.copyFrom(doOpen);
                                }
                                return;
                            }
                            if (source == PaletteEditDialog.this.save) {
                                PaletteIO.doSave(PaletteEditDialog.this, PaletteEditDialog.this.paletteInEditor);
                                return;
                            }
                            if (source == PaletteEditDialog.this.undoTransform) {
                                if (PaletteEditDialog.this.paletteForUndoTransform != null) {
                                    final MandelbrotDisplay.PaletteInfo access$1400 = PaletteEditDialog.this.paletteForUndoTransform;
                                    PaletteEditDialog.this.paletteInEditor.copyFrom(access$1400.palette);
                                    PaletteEditDialog.this.paletteMappingInEditor.setLength(access$1400.paletteMapping.getLength());
                                    PaletteEditDialog.this.paletteMappingInEditor.setOffset(access$1400.paletteMapping.getOffset());
                                }
                                return;
                            }
                            else {
                                if (actionCommand.startsWith("Transform/")) {
                                    PaletteEditDialog.this.doTransform(actionCommand);
                                    return;
                                }
                                PaletteEditDialog.this.paletteInEditor.copyFrom(Palette.makeDefaultPalette(actionCommand));
                                return;
                            }
                        }
                    }
                }
                int int1;
                try {
                    int1 = Integer.parseInt(PaletteEditDialog.this.paletteLengthInput.getText().trim());
                    if (int1 < 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PaletteEditDialog.this, I18n.tr("paletteEditDialog.error.BadPaletteLength", new Object[0]));
                    PaletteEditDialog.this.paletteLengthInput.selectAll();
                    PaletteEditDialog.this.paletteLengthInput.requestFocus();
                    return;
                }
                int int2;
                try {
                    int2 = Integer.parseInt(PaletteEditDialog.this.paletteOffsetInput.getText().trim());
                    if (int2 < 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex2) {
                    JOptionPane.showMessageDialog(PaletteEditDialog.this, I18n.tr("paletteEditDialog.error.BadPaletteOffset", new Object[0]));
                    PaletteEditDialog.this.paletteOffsetInput.selectAll();
                    PaletteEditDialog.this.paletteOffsetInput.requestFocus();
                    return;
                }
                PaletteEditDialog.this.paletteMappingInEditor.setLength(int1);
                PaletteEditDialog.this.paletteMappingInEditor.setOffset(int2);
                if (source == PaletteEditDialog.this.applyButton) {
                    PaletteEditDialog.this.owner.setPaletteInfo(PaletteEditDialog.this.paletteInEditor, PaletteEditDialog.this.paletteMappingInEditor);
                }
            }
        }
    }
}
