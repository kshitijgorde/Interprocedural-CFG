// 
// Decompiled by Procyon v0.5.30
// 

package kiang.swing;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.GraphicsEnvironment;
import java.awt.Component;
import java.util.Set;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComponent;

public class JFontChooser extends JComponent
{
    private JTextField fontChooserField;
    private JList fontChooserList;
    private JTextListLink fontChooserLink;
    private JTextField sizeChooserField;
    private JList sizeChooserList;
    private JTextListLink sizeChooserLink;
    private JCheckBox boldCheckBox;
    private JCheckBox italicCheckBox;
    private JLabel previewLabel;
    private Font[] fontOptions;
    private Set appliedFilters;
    
    public static Font showDialog(final Component owner) {
        final Font initialFont = owner.getFont();
        final String defaultPreviewString = "The quick brown fox jumps over the lazy dog.  123456790";
        final int[] defaultSizeOptions = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
        final Font[] systemFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        return showDialog(owner, initialFont, systemFonts, defaultSizeOptions, new FontFilter[0], defaultPreviewString);
    }
    
    public static Font showDialog(final Component owner, final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final FontFilter[] fontFilters, final String previewString) {
        final JFontChooser fontChooser = new JFontChooser(initialFont, fontOptions, sizeOptions, fontFilters, previewString);
        final FontReturner returner = new FontReturner((FontReturner)null);
        final FontChooserDialog dialog = new FontChooserDialog(owner, "Choose a Font", true, fontChooser, returner, null, null);
        dialog.setLocation(owner.getLocationOnScreen());
        dialog.pack();
        dialog.setVisible(true);
        return returner.getFont();
    }
    
    public JFontChooser(final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final FontFilter[] filters, final String previewString) {
        this.init(initialFont, fontOptions, sizeOptions, filters, previewString);
    }
    
    private void init(final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final FontFilter[] filters, final String previewString) {
        this.fontOptions = fontOptions;
        final JComponent optionsPanel = this.buildOptionsPanel(initialFont, fontOptions, sizeOptions, filters);
        final JComponent previewPanel = this.buildPreviewPanel(initialFont, previewString);
        this.setLayout(new BorderLayout());
        this.add(optionsPanel, "Center");
        this.add(previewPanel, "South");
    }
    
    public Font getSelectedFont() {
        Font font = null;
        final String fontName = this.fontChooserList.getSelectedValue();
        if (fontName != null) {
            int style = 0;
            style |= (this.boldCheckBox.isSelected() ? 1 : 0);
            style |= (this.italicCheckBox.isSelected() ? 2 : 0);
            int fontSize = -1;
            try {
                fontSize = Integer.parseInt(this.sizeChooserField.getText());
            }
            catch (NumberFormatException ex) {}
            if (fontSize <= 0) {
                fontSize = this.previewLabel.getFont().getSize();
            }
            font = new Font(fontName, style, fontSize);
        }
        return font;
    }
    
    private JComponent buildOptionsPanel(final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final FontFilter[] filters) {
        final JPanel chooserPanel = new JPanel();
        chooserPanel.setLayout(new BoxLayout(chooserPanel, 0));
        final JComponent fontChooser = this.buildFontChooserPanel(initialFont, fontOptions);
        final JComponent sizeChooser = this.buildSizeChooserPanel(initialFont, sizeOptions);
        final JComponent styleChooser = this.buildStyleChooserPanel(initialFont);
        chooserPanel.add(fontChooser);
        chooserPanel.add(sizeChooser);
        chooserPanel.add(styleChooser);
        JComponent returnComponent = null;
        if (filters.length > 0) {
            final JComponent filterChooser = this.buildFilterChooser(filters);
            filterChooser.setBorder(BorderFactory.createTitledBorder("Filters"));
            final JPanel optionsWrapperPanel = new JPanel(new BorderLayout());
            optionsWrapperPanel.add(chooserPanel, "Center");
            optionsWrapperPanel.add(filterChooser, "South");
            returnComponent = optionsWrapperPanel;
        }
        else {
            returnComponent = chooserPanel;
        }
        this.loadFilteredFonts(initialFont);
        this.setupFontSelectionListener();
        return returnComponent;
    }
    
    private void setupFontSelectionListener() {
        final FontSelectionListener fontSelectionListener = new FontSelectionListener((FontSelectionListener)null);
        this.fontChooserField.getDocument().addDocumentListener(fontSelectionListener);
        this.fontChooserList.addListSelectionListener(fontSelectionListener);
        this.sizeChooserField.getDocument().addDocumentListener(fontSelectionListener);
        this.sizeChooserList.addListSelectionListener(fontSelectionListener);
        this.boldCheckBox.addActionListener(fontSelectionListener);
        this.italicCheckBox.addActionListener(fontSelectionListener);
    }
    
    private JComponent buildFontChooserPanel(final Font initialFont, final Font[] fontOptions) {
        this.fontChooserField = new JTextField(initialFont.getName());
        final JComponent fontChooserListScrollPane = this.buildFontChooserListPane();
        this.fontChooserLink = new JTextListLink(this.fontChooserField, this.fontChooserList, true);
        final JPanel fontChooserPanel = new JPanel(new BorderLayout());
        fontChooserPanel.add(this.fontChooserField, "North");
        fontChooserPanel.add(fontChooserListScrollPane, "Center");
        fontChooserPanel.setBorder(BorderFactory.createTitledBorder("Font"));
        return fontChooserPanel;
    }
    
    private JComponent buildFontChooserListPane() {
        (this.fontChooserList = new JList()).setSelectionMode(0);
        final JScrollPane fontChooserListScrollPane = new JScrollPane(this.fontChooserList, 22, 30);
        return fontChooserListScrollPane;
    }
    
    private void loadFilteredFonts(final Font initialFont) {
        final List filteredFonts = new ArrayList();
        for (int i = 0; i < this.fontOptions.length; ++i) {
            boolean shouldInclude = true;
            for (final FontFilter filter : this.appliedFilters) {
                if (!filter.shouldInclude(this.fontOptions[i])) {
                    shouldInclude = false;
                    break;
                }
            }
            if (shouldInclude) {
                filteredFonts.add(this.fontOptions[i]);
            }
        }
        final String initialFontName = (initialFont != null) ? initialFont.getName() : null;
        final String[] fontNames = new String[filteredFonts.size()];
        final Iterator filteredIter = filteredFonts.iterator();
        boolean initialFontInList = false;
        int j = 0;
        while (filteredIter.hasNext()) {
            final Font font = filteredIter.next();
            fontNames[j] = font.getName();
            if (fontNames[j].equals(initialFontName)) {
                initialFontInList = true;
            }
            ++j;
        }
        Arrays.sort(fontNames, new Comparator() {
            public int compare(final Object o1, final Object o2) {
                return o1.toString().compareToIgnoreCase(o2.toString());
            }
        });
        this.fontChooserList.setListData(fontNames);
        if (initialFontInList) {
            this.fontChooserList.setSelectedValue(initialFontName, true);
        }
        else {
            this.fontChooserField.setText("");
        }
    }
    
    private JComponent buildSizeChooserPanel(final Font initialFont, final int[] sizeOptions) {
        final int initialSize = initialFont.getSize();
        this.sizeChooserField = new JTextField(Integer.toString(initialSize));
        final JComponent sizeChooserListScrollPane = this.buildSizeChooserList(initialSize, sizeOptions);
        this.sizeChooserLink = new JTextListLink(this.sizeChooserField, this.sizeChooserList, true);
        final JPanel sizeChooserPanel = new JPanel(new BorderLayout());
        sizeChooserPanel.add(this.sizeChooserField, "North");
        sizeChooserPanel.add(sizeChooserListScrollPane, "Center");
        sizeChooserPanel.setBorder(BorderFactory.createTitledBorder("Size"));
        return sizeChooserPanel;
    }
    
    private JComponent buildSizeChooserList(final int initialSize, final int[] sizeOptions) {
        final String[] sizeOptionStrings = new String[sizeOptions.length];
        for (int i = 0; i < sizeOptions.length; ++i) {
            sizeOptionStrings[i] = Integer.toString(sizeOptions[i]);
        }
        (this.sizeChooserList = new JList((E[])sizeOptionStrings)).setSelectionMode(0);
        final JScrollPane sizeChooserListScrollPane = new JScrollPane(this.sizeChooserList, 22, 30);
        return sizeChooserListScrollPane;
    }
    
    private JComponent buildStyleChooserPanel(final Font initialFont) {
        this.boldCheckBox = new JCheckBox("Bold", initialFont.isBold());
        this.italicCheckBox = new JCheckBox("Italics", initialFont.isItalic());
        final JPanel styleChooserPanel = new JPanel();
        styleChooserPanel.setLayout(new BoxLayout(styleChooserPanel, 1));
        styleChooserPanel.add(this.boldCheckBox);
        styleChooserPanel.add(this.italicCheckBox);
        styleChooserPanel.setBorder(BorderFactory.createTitledBorder("Style"));
        return styleChooserPanel;
    }
    
    private JComponent buildFilterChooser(final FontFilter[] filters) {
        this.appliedFilters = new HashSet();
        final JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());
        for (int i = 0; i < filters.length; ++i) {
            if (filters[i].isDefaultOn()) {
                this.appliedFilters.add(filters[i]);
            }
            final JCheckBox filterCheckBox = this.buildFilterCheckBox(filters[i]);
            filterPanel.add(filterCheckBox);
        }
        return filterPanel;
    }
    
    private JCheckBox buildFilterCheckBox(final FontFilter filter) {
        final ActionListener checkBoxListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JCheckBox source = (JCheckBox)e.getSource();
                if (source.isSelected()) {
                    JFontChooser.this.appliedFilters.add(filter);
                }
                else {
                    JFontChooser.this.appliedFilters.remove(filter);
                }
                JFontChooser.this.loadFilteredFonts(JFontChooser.this.getSelectedFont());
            }
        };
        final JCheckBox filterCheckBox = new JCheckBox(filter.getDisplayName(), filter.isDefaultOn());
        filterCheckBox.addActionListener(checkBoxListener);
        return filterCheckBox;
    }
    
    private JComponent buildPreviewPanel(final Font initialFont, final String previewString) {
        (this.previewLabel = new JLabel(previewString)).setFont(initialFont);
        this.previewLabel.setHorizontalAlignment(0);
        this.previewLabel.setVerticalAlignment(0);
        this.previewLabel.setBorder(BorderFactory.createTitledBorder("Preview"));
        final JScrollPane previewScrollPane = new JScrollPane(this.previewLabel, 20, 30);
        return previewScrollPane;
    }
    
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setVisible(true);
        Font font = new Font("Simsun", 0, 36);
        final int[] defaultSizeOptions = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
        final String defaultPreviewString = "\u6c49  \u6f22";
        final Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        final ArrayList chineseFonts = new ArrayList();
        for (int i = 0; i < fonts.length; ++i) {
            if (fonts[i].canDisplay('\u6c49') || fonts[i].canDisplay('\u6f22')) {
                chineseFonts.add(fonts[i]);
            }
        }
        final Font[] chinFonts = new Font[chineseFonts.size()];
        final Iterator fontIter = chineseFonts.iterator();
        int j = 0;
        while (fontIter.hasNext()) {
            chinFonts[j] = fontIter.next();
            ++j;
        }
        final FontFilter testFilter1 = new FontFilter() {
            public boolean isDefaultOn() {
                return true;
            }
            
            public boolean shouldInclude(final Font font) {
                return font.canDisplay('\u1000');
            }
            
            public String getDisplayName() {
                return "Test1";
            }
        };
        final FontFilter testFilter2 = new FontFilter() {
            public boolean isDefaultOn() {
                return false;
            }
            
            public boolean shouldInclude(final Font font) {
                return font.canDisplay('\u6f22');
            }
            
            public String getDisplayName() {
                return "Test2";
            }
        };
        font = showDialog(frame, font, fonts, defaultSizeOptions, new FontFilter[] { testFilter1, testFilter2 }, defaultPreviewString);
    }
    
    private class FontSelectionListener implements DocumentListener, ListSelectionListener, ActionListener
    {
        public void updatePreviewFont() {
            JFontChooser.this.previewLabel.setFont(JFontChooser.this.getSelectedFont());
        }
        
        public void insertUpdate(final DocumentEvent e) {
            this.updatePreviewFont();
        }
        
        public void removeUpdate(final DocumentEvent e) {
            this.updatePreviewFont();
        }
        
        public void changedUpdate(final DocumentEvent e) {
            this.updatePreviewFont();
        }
        
        public void valueChanged(final ListSelectionEvent e) {
            this.updatePreviewFont();
        }
        
        public void actionPerformed(final ActionEvent e) {
            this.updatePreviewFont();
        }
    }
    
    private static class FontChooserDialog extends JDialog
    {
        private FontChooserDialog(final Component owner, final String title, final boolean modal, final JFontChooser fontChooser, final ActionListener okListener, final ActionListener cancelListener) {
            super(JOptionPane.getFrameForComponent(owner), title, modal);
            final JButton okButton = new JButton("OK");
            final JButton cancelButton = new JButton("Cancel");
            final ActionListener hideListener = new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    FontChooserDialog.this.setVisible(false);
                }
            };
            okButton.addActionListener(hideListener);
            cancelButton.addActionListener(hideListener);
            if (okListener != null) {
                okButton.addActionListener(okListener);
            }
            if (cancelListener != null) {
                cancelButton.addActionListener(cancelListener);
            }
            final JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            final Container contentPane = this.getContentPane();
            contentPane.setLayout(new BorderLayout());
            contentPane.add(fontChooser, "Center");
            contentPane.add(buttonPanel, "South");
        }
    }
    
    private class FontReturner implements ActionListener
    {
        private Font font;
        
        public void actionPerformed(final ActionEvent e) {
            this.font = JFontChooser.this.getSelectedFont();
        }
        
        public Font getFont() {
            return this.font;
        }
    }
    
    public interface FontFilter
    {
        String getDisplayName();
        
        boolean isDefaultOn();
        
        boolean shouldInclude(final Font p0);
    }
}
