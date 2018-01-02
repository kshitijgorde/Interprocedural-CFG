// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.ui;

import java.awt.event.KeyEvent;
import java.awt.Point;
import javax.swing.KeyStroke;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.util.Iterator;
import java.awt.Font;
import java.awt.Container;
import java.awt.Component;
import kiang.chinese.font.ChineseFontChooserFactory;
import javax.swing.JDialog;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ResourceBundle;
import java.util.Collection;
import hanzilookup.HanziLookup;

public class HanziLookupUIBuilder
{
    public static JMenu buildOptionsMenu(final HanziLookup lookup, final Collection containers, final ResourceBundle bundle) {
        final JMenu optionsMenu = new JMenu(bundle.getString("settings"));
        final JMenu modeSubMenu = buildCharacterModeMenu(lookup, bundle);
        optionsMenu.add(modeSubMenu);
        final JMenuItem lookupOptionItem = buildLookupOptionMenuItem(lookup, bundle);
        optionsMenu.add(lookupOptionItem);
        final JMenuItem fontItem = buildFontMenuItem(lookup, containers, bundle);
        optionsMenu.add(fontItem);
        return optionsMenu;
    }
    
    public static JMenu buildCharacterModeMenu(final HanziLookup lookup, final ResourceBundle bundle) {
        final int searchType = lookup.getSearchType();
        final JRadioButtonMenuItem simplifiedButton = new JRadioButtonMenuItem(bundle.getString("simplified_character_type"), 1 == searchType);
        final JRadioButtonMenuItem traditionalButton = new JRadioButtonMenuItem(bundle.getString("traditional_character_type"), 2 == searchType);
        final JRadioButtonMenuItem bothButton = new JRadioButtonMenuItem(bundle.getString("both_character_types"), searchType == 0);
        final ActionListener modeListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Object source = e.getSource();
                if (source == simplifiedButton) {
                    lookup.setSearchType(1);
                }
                else if (source == traditionalButton) {
                    lookup.setSearchType(2);
                }
                else {
                    lookup.setSearchType(0);
                }
            }
        };
        simplifiedButton.addActionListener(modeListener);
        traditionalButton.addActionListener(modeListener);
        bothButton.addActionListener(modeListener);
        final JMenu charModeMenu = new JMenu(bundle.getString("character_type_bundle_key"));
        charModeMenu.add(simplifiedButton);
        charModeMenu.add(traditionalButton);
        charModeMenu.add(bothButton);
        final ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(simplifiedButton);
        modeGroup.add(traditionalButton);
        modeGroup.add(bothButton);
        return charModeMenu;
    }
    
    public static JMenuItem buildLookupOptionMenuItem(final HanziLookup lookup, final ResourceBundle bundle) {
        final JMenuItem lookupOptionsMenu = new JMenuItem(bundle.getString("lookup_options"));
        lookupOptionsMenu.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JDialog optionsDialog = HanziLookupUIBuilder.buildLookupOptionDialog(lookup, bundle);
                optionsDialog.setVisible(true);
            }
        });
        return lookupOptionsMenu;
    }
    
    public static JMenuItem buildFontMenuItem(final HanziLookup lookup, final Collection containers, final ResourceBundle bundle) {
        final JMenuItem fontItem = new JMenuItem(bundle.getString("choose_font"));
        fontItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Font font = ChineseFontChooserFactory.showDialog(lookup);
                if (font != null) {
                    lookup.setFont(font);
                    if (containers != null) {
                        for (final Container container : containers) {
                            container.setFont(font);
                        }
                    }
                }
            }
        });
        return fontItem;
    }
    
    public static JDialog buildLookupOptionDialog(final HanziLookup lookup, final ResourceBundle bundle) {
        final JDialog optionsDialog = new JDialog();
        optionsDialog.setTitle(bundle.getString("options"));
        optionsDialog.setDefaultCloseOperation(2);
        final Container contentPane = optionsDialog.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, 1));
        final JCheckBox autoLookupCheckBox = buildAutoLookupCheckBox(lookup, bundle);
        autoLookupCheckBox.setAlignmentX(0.5f);
        autoLookupCheckBox.setSelected(lookup.getAutoLookup());
        contentPane.add(autoLookupCheckBox);
        contentPane.add(Box.createVerticalStrut(20));
        final JLabel loosenessLabel = new JLabel(bundle.getString("lookup_looseness"));
        loosenessLabel.setAlignmentX(0.5f);
        contentPane.add(loosenessLabel);
        final JSlider loosenessSlider = buildLoosenessSlider(lookup);
        loosenessSlider.setAlignmentX(0.5f);
        contentPane.add(loosenessSlider);
        contentPane.add(Box.createVerticalStrut(20));
        final JLabel matchCountLabel = new JLabel(bundle.getString("match_count"));
        matchCountLabel.setAlignmentX(0.5f);
        contentPane.add(matchCountLabel);
        final JSpinner resultsSpinner = new JSpinner(new SpinnerNumberModel(lookup.getNumResults(), 1, 100, 1));
        resultsSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                final Integer numResults = (Integer)resultsSpinner.getValue();
                lookup.setNumResults(numResults);
            }
        });
        resultsSpinner.setMaximumSize(new Dimension(50, 25));
        contentPane.add(resultsSpinner);
        contentPane.add(Box.createVerticalStrut(20));
        final KeyStroke lookupMacro = lookup.getLookupMacro();
        final KeyStroke clearMacro = lookup.getClearMacro();
        final KeyStroke undoMacro = lookup.getUndoMacro();
        final String typeMacro = bundle.getString("type_macro");
        final String lookupMacroText = (lookupMacro != null) ? getKeyStrokeText(lookupMacro.getKeyCode(), lookupMacro.getModifiers()) : typeMacro;
        final String clearMacroText = (clearMacro != null) ? getKeyStrokeText(clearMacro.getKeyCode(), clearMacro.getModifiers()) : typeMacro;
        final String undoMacroText = (undoMacro != null) ? getKeyStrokeText(undoMacro.getKeyCode(), undoMacro.getModifiers()) : typeMacro;
        final JTextField lookupMacroField = new JTextField(lookupMacroText, 10);
        final JTextField clearMacroField = new JTextField(clearMacroText, 10);
        final JTextField undoMacroField = new JTextField(undoMacroText, 10);
        final MacroKeyListener macroKeyListener = new MacroKeyListener(lookupMacroField, clearMacroField, undoMacroField, lookup, null);
        lookupMacroField.addKeyListener(macroKeyListener);
        clearMacroField.addKeyListener(macroKeyListener);
        undoMacroField.addKeyListener(macroKeyListener);
        final JLabel lookupMacroLabel = new JLabel(bundle.getString("lookup_macro"));
        final JLabel clearMacroLabel = new JLabel(bundle.getString("clear_macro"));
        final JLabel undoMacroLabel = new JLabel(bundle.getString("undo_macro"));
        lookupMacroLabel.setHorizontalAlignment(4);
        clearMacroLabel.setHorizontalAlignment(4);
        undoMacroLabel.setHorizontalAlignment(4);
        final JPanel macroPanel = new JPanel(new GridLayout(3, 2));
        macroPanel.add(lookupMacroLabel);
        macroPanel.add(lookupMacroField);
        macroPanel.add(clearMacroLabel);
        macroPanel.add(clearMacroField);
        macroPanel.add(undoMacroLabel);
        macroPanel.add(undoMacroField);
        contentPane.add(macroPanel);
        contentPane.add(Box.createVerticalStrut(20));
        final JButton okButton = new JButton(bundle.getString("ok"));
        okButton.setAlignmentX(0.5f);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                optionsDialog.dispose();
            }
        });
        contentPane.add(okButton);
        optionsDialog.pack();
        setChildComponentPosition(lookup, optionsDialog);
        return optionsDialog;
    }
    
    public static void setChildComponentPosition(final Component parentWindow, final Component childWindow) {
        final Point parentLocation = parentWindow.getLocationOnScreen();
        final int dialogX = (int)(parentLocation.getX() + parentWindow.getWidth() / 2 - childWindow.getWidth() / 2);
        final int dialogY = (int)(parentLocation.getY() + parentWindow.getHeight() / 2 - childWindow.getHeight() / 2);
        childWindow.setLocation(dialogX, dialogY);
    }
    
    public static JMenuItem buildSaveOptionsMenuItem(final HanziLookup lookup, final ResourceBundle bundle) {
        final JMenuItem fontItem = new JMenuItem(bundle.getString("save_settings"));
        fontItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            }
        });
        return fontItem;
    }
    
    private static String getKeyStrokeText(final int keyCode, final int modifiers) {
        String keyStrokeText = (modifiers != 0) ? (String.valueOf(KeyEvent.getKeyModifiersText(modifiers)) + " ") : "";
        keyStrokeText = String.valueOf(keyStrokeText) + KeyEvent.getKeyText(keyCode);
        return keyStrokeText;
    }
    
    public static JCheckBox buildAutoLookupCheckBox(final HanziLookup lookup, final ResourceBundle bundle) {
        final JCheckBox autoLookupCheckBox = new JCheckBox(bundle.getString("auto_lookup"));
        autoLookupCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                lookup.setAutoLookup(autoLookupCheckBox.isSelected());
            }
        });
        return autoLookupCheckBox;
    }
    
    public static JSlider buildLoosenessSlider(final HanziLookup lookup) {
        final JSlider loosenessSlider = new JSlider(0, 20);
        final int initialValue = (int)(loosenessSlider.getMaximum() * lookup.getLooseness());
        loosenessSlider.setValue(initialValue);
        loosenessSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                final int sliderMax = loosenessSlider.getMaximum();
                final int sliderValue = loosenessSlider.getValue();
                final double looseness = sliderValue / sliderMax;
                lookup.setLooseness(looseness);
            }
        });
        return loosenessSlider;
    }
    
    private static class MacroKeyListener implements KeyListener
    {
        private JTextField lookupField;
        private JTextField clearField;
        private JTextField undoField;
        private HanziLookup hanziLookup;
        
        private MacroKeyListener(final JTextField lookupField, final JTextField clearField, final JTextField undoField, final HanziLookup hanziLookup) {
            this.lookupField = lookupField;
            this.clearField = clearField;
            this.undoField = undoField;
            this.hanziLookup = hanziLookup;
        }
        
        public void keyPressed(final KeyEvent ke) {
            final int keyCode = ke.getKeyCode();
            final int modifiers = ke.getModifiers();
            if (keyCode != 0 && !this.isModifier(keyCode)) {
                final Object eventSource = ke.getSource();
                final String keyText = getKeyStrokeText(keyCode, modifiers);
                final KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
                if (eventSource == this.lookupField) {
                    this.hanziLookup.registerLookupMacro(keyStroke);
                    this.lookupField.setText(keyText);
                }
                else if (eventSource == this.clearField) {
                    this.hanziLookup.registerClearMacro(keyStroke);
                    this.clearField.setText(keyText);
                }
                else {
                    this.hanziLookup.registerUndoMacro(keyStroke);
                    this.undoField.setText(keyText);
                }
            }
            ke.consume();
        }
        
        public void keyReleased(final KeyEvent ke) {
            ke.consume();
        }
        
        public void keyTyped(final KeyEvent ke) {
            ke.consume();
        }
        
        private boolean isModifier(final int keyCode) {
            return 17 == keyCode || 18 == keyCode || 157 == keyCode || 16 == keyCode;
        }
    }
}
