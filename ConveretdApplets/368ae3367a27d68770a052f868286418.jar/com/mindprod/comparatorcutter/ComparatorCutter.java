// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.comparatorcutter;

import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.ItemEvent;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import com.mindprod.fastcat.FastCat;
import javax.swing.Icon;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import java.awt.Insets;
import com.mindprod.common15.FontFactory;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.AbstractButton;
import java.awt.Component;
import java.awt.AWTEvent;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import com.mindprod.common13.JEButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public class ComparatorCutter extends JApplet
{
    private static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 910;
    private static final int APPLET_WIDTH = 582;
    private static final int MAX_FIELDS = 5;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2009-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-01-02";
    private static final String TITLE_STRING = "Comparator Cutter";
    private static final String VERSION_STRING = "1.5";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_PANEL;
    private static final Color FOREGROUND_FOR_ENTER;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_LABELS_SMALL;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final ImageIcon ASCENDING_ICON;
    private static final ImageIcon DESCENDING_ICON;
    private static final ImageIcon MOVE_DOWN_DISABLED_ICON;
    private static final ImageIcon MOVE_DOWN_ICON;
    private static final ImageIcon MOVE_UP_DISABLED_ICON;
    private static final ImageIcon MOVE_UP_ICON;
    private JButton[] fieldMoveDowns;
    private JButton[] fieldMoveUps;
    private JButton about;
    private JCheckBox[] fieldIsDescendings;
    private JCheckBox useGenericsBox;
    private JComboBox[] fieldTypes;
    private JComboBox interfaceSelection;
    private JEButton generateButton;
    private JEditorPane instructions;
    private JLabel[] fieldNumbers;
    private JLabel commentLabel;
    private JLabel fieldAscDescLabel;
    private JLabel fieldMoveDownLabel;
    private JLabel fieldMoveLabel;
    private JLabel fieldMoveUpLabel;
    private JLabel fieldNameLabel;
    private JLabel fieldSortLabel;
    private JLabel fieldTypeLabel;
    private JLabel gClassLabel;
    private JLabel generateLabel;
    private JLabel instructionsLabel;
    private JLabel oClassLabel;
    private JLabel resultsLabel;
    private JLabel title;
    private JLabel title2;
    private JPanel fieldPanel;
    private JScrollPane instructionsScrollPane;
    private JScrollPane resultsScrollPane;
    private JTextArea results;
    private JTextField[] fieldNames;
    private JTextField comment;
    private JTextField gClass;
    private JTextField oClass;
    private boolean defibrillate;
    
    public void destroy() {
        this.about = null;
        this.comment = null;
        this.commentLabel = null;
        this.fieldAscDescLabel = null;
        this.fieldIsDescendings = null;
        this.fieldMoveDownLabel = null;
        this.fieldMoveDowns = null;
        this.fieldMoveLabel = null;
        this.fieldMoveUpLabel = null;
        this.fieldMoveUps = null;
        this.fieldNameLabel = null;
        this.fieldNames = null;
        this.fieldNumbers = null;
        this.fieldPanel = null;
        this.fieldSortLabel = null;
        this.fieldTypeLabel = null;
        this.fieldTypes = null;
        this.gClass = null;
        this.gClassLabel = null;
        this.generateButton = null;
        this.generateLabel = null;
        this.instructions = null;
        this.instructionsLabel = null;
        this.instructionsScrollPane = null;
        this.interfaceSelection = null;
        this.oClass = null;
        this.oClassLabel = null;
        this.results = null;
        this.resultsLabel = null;
        this.resultsScrollPane = null;
        this.title = null;
        this.title2 = null;
        this.useGenericsBox = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(ComparatorCutter.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.layoutApplet(contentPane);
        this.validate();
        this.setVisible(true);
    }
    
    private static String getName(final AWTEvent e) {
        try {
            final String result = ((Component)e.getSource()).getName();
            if (result == null || result.length() == 0) {
                return "unknown" + e.getSource().getClass().toString() + " Component";
            }
            return result;
        }
        catch (ClassCastException ex) {
            return "unknown " + e.getSource().getClass().toString() + " source";
        }
    }
    
    static void recordFieldRowIndex(final AbstractButton jButton, final int i) {
        jButton.setActionCommand(String.valueOf((char)i));
    }
    
    static int retrieveFieldRowIndex(final AbstractButton jButton) {
        return jButton.getActionCommand().charAt(0);
    }
    
    private void compose() {
        if (this.defibrillate) {
            return;
        }
        this.defibrillate = true;
        this.tidyFields();
        final SortKey[] sortKeys = this.extractUsedSortKeys();
        String tidiedGClass = this.gClass.getText().trim();
        if (tidiedGClass.length() == 0) {
            tidiedGClass = "InMyOrder";
        }
        String tidiedOClass = this.oClass.getText().trim();
        if (tidiedOClass.length() == 0) {
            tidiedOClass = "Thing";
        }
        String tidiedComment = this.comment.getText().trim();
        if (tidiedComment.length() == 0) {
            tidiedComment = "To come";
        }
        final InterfaceType interfaceType = (InterfaceType)this.interfaceSelection.getSelectedItem();
        final boolean useGenerics = this.useGenericsBox.isSelected();
        this.results.setText(interfaceType.compose(useGenerics, tidiedGClass, tidiedOClass, tidiedComment, sortKeys));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ComparatorCutter.this.resultsScrollPane.getVerticalScrollBar().setValue(interfaceType.scrollTo());
            }
        });
        this.validate();
        this.defibrillate = false;
    }
    
    private void createComponents() {
        (this.about = new JEButton("About")).setToolTipText("About Comparator Cutter 1.5");
        this.about.setName("about");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Comparator Cutter", "1.5", "Generates Java source for Comparators or Comparables,", "for sorting ascending or descending, multiple fields.", "freeware", "2010-01-02", 2009, "Roedy Green", "COMPARATORCUTTER", "1.5");
            }
        });
        (this.title = new JLabel("Comparator Cutter 1.5")).setName("title");
        this.title.setFont(ComparatorCutter.FONT_FOR_TITLE);
        this.title.setForeground(ComparatorCutter.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-01-02 build:9411")).setFont(ComparatorCutter.FONT_FOR_TITLE2);
        this.title2.setForeground(ComparatorCutter.FOREGROUND_FOR_TITLE);
        (this.generateLabel = new JLabel("Generate", 4)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.generateLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.interfaceSelection = new JComboBox((E[])InterfaceType.values())).setName("interfaceSelection");
        this.interfaceSelection.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        this.interfaceSelection.setBackground(ComparatorCutter.BACKGROUND_FOR_APPLET);
        this.interfaceSelection.setToolTipText("Generate a Comparator to be nested in another class");
        (this.useGenericsBox = new JCheckBox("Generics", true)).setName("useGenericsBox");
        this.useGenericsBox.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        this.useGenericsBox.setBackground(ComparatorCutter.BACKGROUND_FOR_APPLET);
        this.useGenericsBox.setToolTipText("JDK 1.5+ generics, or without generics for JDK 1.4-");
        (this.oClassLabel = new JLabel("Object class", 4)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.oClassLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.oClass = new JTextField("Thing")).setName("oClass");
        this.oClass.setEditable(true);
        this.oClass.setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.oClass.setForeground(ComparatorCutter.FOREGROUND_FOR_ENTER);
        this.oClass.setBackground(ComparatorCutter.BACKGROUND_FOR_EDITABLE);
        this.oClass.setHorizontalAlignment(2);
        this.oClass.setToolTipText("class name of Objects being compared");
        (this.commentLabel = new JLabel("Comment", 4)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.commentLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.comment = new JTextField("Describe/summarise the comparison here.", 30)).setName("comment");
        this.comment.setEditable(true);
        this.comment.setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.comment.setForeground(ComparatorCutter.FOREGROUND_FOR_ENTER);
        this.comment.setBackground(ComparatorCutter.BACKGROUND_FOR_EDITABLE);
        this.comment.setHorizontalAlignment(2);
        this.comment.setToolTipText("Describe/summarise the comparison here.");
        (this.gClassLabel = new JLabel("Comparator class", 4)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.gClassLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.gClass = new JTextField("InMyOrder", 30)).setName("gClass");
        this.gClass.setEditable(true);
        this.gClass.setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.gClass.setForeground(ComparatorCutter.FOREGROUND_FOR_ENTER);
        this.gClass.setBackground(ComparatorCutter.BACKGROUND_FOR_EDITABLE);
        this.gClass.setHorizontalAlignment(2);
        this.gClass.setToolTipText("class name of the generated Comparator, name of this variant way of comparing Objects.");
        this.createFieldPanelComponents();
        (this.resultsLabel = new JLabel("Generated Java Code", 2)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.resultsLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.generateButton = new JEButton("Generate")).setToolTipText("Generate Java source code");
        this.generateButton.setName("generate");
        (this.results = new JTextArea("to come", 0, 0)).setName("results");
        this.results.setEditable(false);
        this.results.setFont(FontFactory.build("Dialog", 0, 15));
        this.results.setForeground(ComparatorCutter.FOREGROUND_FOR_RESULT);
        this.results.setToolTipText("Generated Java code.");
        this.resultsScrollPane = new JScrollPane(this.results, 20, 30);
        (this.instructionsLabel = new JLabel("Instructions", 2)).setFont(ComparatorCutter.FONT_FOR_LABELS);
        this.instructionsLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.instructions = new JEditorPane()).setName("instructions");
        this.instructions.setContentType("text/html");
        this.instructions.setBackground(ComparatorCutter.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setForeground(ComparatorCutter.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setFont(ComparatorCutter.FONT_FOR_INSTRUCTIONS);
        this.instructions.setAlignmentX(0.5f);
        this.instructions.setAlignmentY(0.5f);
        this.instructions.setMargin(new Insets(4, 4, 4, 4));
        this.instructions.setEnabled(true);
        this.instructions.setEditable(false);
        final HTMLDocument htmlDocument = new HTMLDocument();
        this.instructions.setDocument(htmlDocument);
        this.instructions.setText("to come");
        this.instructionsScrollPane = new JScrollPane(this.instructions, 20, 30);
        this.hookListeners();
        this.compose();
    }
    
    private void createFieldPanelComponents() {
        (this.fieldPanel = new JPanel()).setLayout(new GridBagLayout());
        this.fieldPanel.setBackground(ComparatorCutter.BACKGROUND_FOR_PANEL);
        this.createFieldPanelLabels();
        this.fieldNumbers = new JLabel[5];
        this.fieldTypes = new JComboBox[5];
        this.fieldNames = new JTextField[5];
        this.fieldIsDescendings = new JCheckBox[5];
        this.fieldMoveUps = new JButton[5];
        this.fieldMoveDowns = new JButton[5];
        for (int i = 0; i < 5; ++i) {
            (this.fieldNumbers[i] = new JLabel(Integer.toString(i + 1) + ".")).setFont(ComparatorCutter.FONT_FOR_LABELS);
            this.fieldNumbers[i].setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
            (this.fieldTypes[i] = new JComboBox((E[])FieldType.values())).setName("fieldTypes[" + i + "]");
            this.fieldTypes[i].setMaximumRowCount(FieldType.values().length);
            this.fieldTypes[i].setEditable(false);
            this.fieldTypes[i].setToolTipText("Type of field to use in comparing.");
            (this.fieldIsDescendings[i] = new JCheckBox("", false)).setName("fieldIsDescendings[" + i + "]");
            this.fieldIsDescendings[i].setIcon(ComparatorCutter.ASCENDING_ICON);
            this.fieldIsDescendings[i].setToolTipText("to come");
            (this.fieldNames[i] = new JTextField("to come", 30)).setName("fieldNames[" + i + "]");
            this.fieldNames[i].setEditable(true);
            this.fieldNames[i].setFont(ComparatorCutter.FONT_FOR_LABELS);
            this.fieldNames[i].setForeground(ComparatorCutter.FOREGROUND_FOR_ENTER);
            this.fieldNames[i].setBackground(ComparatorCutter.BACKGROUND_FOR_EDITABLE);
            this.fieldNames[i].setHorizontalAlignment(2);
            this.fieldNames[i].setToolTipText("Name of field/getter to use in comparing, or leave it blank to compare the Object as a whole.");
            (this.fieldMoveUps[i] = new JButton()).setName("fieldMoveUps[" + i + "]");
            recordFieldRowIndex(this.fieldMoveUps[i], i);
            if (i == 0) {
                this.fieldMoveUps[i].setVisible(true);
                this.fieldMoveUps[i].setEnabled(false);
            }
            this.fieldMoveUps[i].setIcon(ComparatorCutter.MOVE_UP_ICON);
            this.fieldMoveUps[i].setDisabledIcon(ComparatorCutter.MOVE_UP_DISABLED_ICON);
            this.fieldMoveUps[i].setBorderPainted(false);
            this.fieldMoveUps[i].setContentAreaFilled(false);
            this.fieldMoveUps[i].setDefaultCapable(false);
            this.fieldMoveUps[i].setFocusPainted(false);
            this.fieldMoveUps[i].setMargin(new Insets(3, 3, 3, 3));
            this.fieldMoveUps[i].setMultiClickThreshhold(100L);
            this.fieldMoveUps[i].setOpaque(false);
            this.fieldMoveUps[i].setToolTipText("Click to move this field up to higher priority.");
            (this.fieldMoveDowns[i] = new JButton()).setName("fieldMoveDowns[" + i + "]");
            recordFieldRowIndex(this.fieldMoveDowns[i], i);
            if (i == 4) {
                this.fieldMoveDowns[i].setVisible(true);
                this.fieldMoveDowns[i].setEnabled(false);
            }
            this.fieldMoveDowns[i].setIcon(ComparatorCutter.MOVE_DOWN_ICON);
            this.fieldMoveDowns[i].setDisabledIcon(ComparatorCutter.MOVE_DOWN_DISABLED_ICON);
            this.fieldMoveDowns[i].setBorderPainted(false);
            this.fieldMoveDowns[i].setContentAreaFilled(false);
            this.fieldMoveDowns[i].setDefaultCapable(false);
            this.fieldMoveDowns[i].setFocusPainted(false);
            this.fieldMoveDowns[i].setMargin(new Insets(3, 3, 3, 3));
            this.fieldMoveDowns[i].setMultiClickThreshhold(100L);
            this.fieldMoveDowns[i].setOpaque(false);
            this.fieldMoveDowns[i].setToolTipText("Click to move this field down to lower priority.");
        }
    }
    
    private void createFieldPanelLabels() {
        (this.fieldTypeLabel = new JLabel("Field Type", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldTypeLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldSortLabel = new JLabel("Sort", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldSortLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldAscDescLabel = new JLabel("Direction", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldAscDescLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldNameLabel = new JLabel("Field/Getter Name", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldNameLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldMoveLabel = new JLabel("Move", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldMoveLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldMoveUpLabel = new JLabel("Up", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldMoveUpLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
        (this.fieldMoveDownLabel = new JLabel("Down", 0)).setFont(ComparatorCutter.FONT_FOR_LABELS_SMALL);
        this.fieldMoveDownLabel.setForeground(ComparatorCutter.FOREGROUND_FOR_LABEL);
    }
    
    private void customiseInstructions(final InterfaceType interfaceType) {
        final FastCat sb = new FastCat(3);
        sb.append("<html><body><ol style=\"margin-left: 15px\"><li>Select whether you want to generate a Comparable or Comparator, nested or top-level.</li><li>Fill in the class name of the Objects being compared.</li><li>Fill in a comment about what the generated code will do e.g. \"sort Customers geographically\".</li>");
        if (interfaceType == InterfaceType.COMPARATOR_TOP_LEVEL || interfaceType == InterfaceType.COMPARATOR_NESTED) {
            sb.append("<li>Fill in the class name of the Comparator.</li>");
        }
        sb.append("<li>For the first field relevant to the comparison, select the field type.</li><li>Select whether you want <span style=\"color:green;font-weight:bold\">ascending</span> order or <span style=\"color:red;font-weight:bold\">descending</span> order by  clicking/toggling the corresponding sort direction green or red icon (not the blue move up/down icons on the right).</li><li>Fill in the sort key field name/getter (without lead .) e.g. width, getWidth(), custName.length(), prevVehicle or leave it blank to compare the Object as a whole, possibly unboxed.</li><li>Repeat for tie-breaking secondary sort keys.</li><li>Use the up/down arrows to reorder the sort keys, if necessary.</li><li>To insert a key, add it at the bottom and move it up with the up arrow button beside it.</li><li>To delete a sort key, set its type to <em>spare</em>.</li><li>When you have the code the way you want, hit <strong>Generate</strong>.</li><li>Copy/paste the generated Java code into your own program.</li></ol></body></html>");
        final String instructionsHTML = sb.toString();
        try {
            this.instructions.setText(instructionsHTML);
        }
        catch (Exception e) {
            System.err.println("Because of Sun bug, unable to display instructions, please exit browser and restart");
            e.printStackTrace(System.err);
            System.err.println(instructionsHTML);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ComparatorCutter.this.instructionsScrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }
    
    private void displayFields(final String where) {
    }
    
    private SortKey[] extractUsedSortKeys() {
        final ArrayList<SortKey> nonSpare = new ArrayList<SortKey>(5);
        for (int i = 0; i < 5; ++i) {
            final FieldType ft = (FieldType)this.fieldTypes[i].getSelectedItem();
            if (ft != FieldType.SPARE) {
                final String fieldName = this.fieldNames[i].getText().trim();
                nonSpare.add(new SortKey(ft, this.fieldIsDescendings[i].isSelected(), fieldName));
            }
        }
        return nonSpare.toArray(new SortKey[nonSpare.size()]);
    }
    
    private void hookListeners() {
        final GeneralListener generalListener = new GeneralListener();
        final DescendingToggleListener descendingToggleListener = new DescendingToggleListener();
        final LostFocusListener lostFocusListener = new LostFocusListener();
        final MoveUpListener moveUpListener = new MoveUpListener();
        final MoveDownListener moveDownListener = new MoveDownListener();
        this.interfaceSelection.addItemListener(generalListener);
        this.useGenericsBox.addItemListener(generalListener);
        this.oClass.addActionListener(generalListener);
        this.oClass.addFocusListener(lostFocusListener);
        this.comment.addActionListener(generalListener);
        this.comment.addFocusListener(lostFocusListener);
        this.gClass.addActionListener(generalListener);
        this.generateButton.addActionListener(generalListener);
        for (int i = 0; i < 5; ++i) {
            this.fieldTypes[i].addActionListener(generalListener);
            this.fieldIsDescendings[i].addItemListener(descendingToggleListener);
            this.fieldNames[i].addActionListener(generalListener);
            this.fieldNames[i].addFocusListener(lostFocusListener);
            this.fieldMoveUps[i].addActionListener(moveUpListener);
            this.fieldMoveDowns[i].addActionListener(moveDownListener);
        }
    }
    
    private void layoutApplet(final Container contentPane) {
        int topLine = 0;
        contentPane.add(this.title, new GridBagConstraints(0, topLine, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, topLine + 1, 2, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(2, topLine, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 10, 5, 10), 10, 2));
        topLine += 2;
        contentPane.add(this.generateLabel, new GridBagConstraints(0, topLine, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.interfaceSelection, new GridBagConstraints(1, topLine, 1, 1, 1.0, 1.0, 17, 0, new Insets(0, 5, 5, 5), 0, 0));
        contentPane.add(this.useGenericsBox, new GridBagConstraints(2, topLine, 1, 1, 1.0, 1.0, 17, 0, new Insets(0, 5, 5, 5), 0, 0));
        ++topLine;
        contentPane.add(this.oClassLabel, new GridBagConstraints(0, topLine, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.oClass, new GridBagConstraints(1, topLine, 1, 1, 1.0, 1.0, 17, 2, new Insets(0, 5, 5, 10), 0, 0));
        ++topLine;
        contentPane.add(this.commentLabel, new GridBagConstraints(0, topLine, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.comment, new GridBagConstraints(1, topLine, 2, 1, 90.0, 1.0, 17, 2, new Insets(0, 5, 5, 10), 0, 0));
        ++topLine;
        contentPane.add(this.gClassLabel, new GridBagConstraints(0, topLine, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.gClass, new GridBagConstraints(1, topLine, 2, 1, 1.0, 1.0, 17, 2, new Insets(0, 5, 5, 10), 0, 0));
        ++topLine;
        contentPane.add(this.fieldPanel, new GridBagConstraints(0, topLine, 3, 1, 1.0, 0.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
        this.layoutFieldPanel();
        int belowLine = topLine + 1;
        contentPane.add(this.resultsLabel, new GridBagConstraints(0, belowLine, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.generateButton, new GridBagConstraints(2, belowLine++, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 5, 5, 10), 0, 0));
        contentPane.add(this.resultsScrollPane, new GridBagConstraints(0, belowLine++, 3, 1, 1.0, 75.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.instructionsLabel, new GridBagConstraints(0, belowLine++, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.instructionsScrollPane, new GridBagConstraints(0, belowLine, 3, 1, 1.0, 30.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    private void layoutFieldPanel() {
        this.layoutFieldPanelLabels();
        for (int i = 0; i < 5; ++i) {
            final int sortKeyLine = i + 2;
            this.fieldPanel.add(this.fieldNumbers[i], new GridBagConstraints(0, sortKeyLine, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 10, (i < 4) ? 2 : 5, 5), 0, 0));
            this.fieldPanel.add(this.fieldTypes[i], new GridBagConstraints(1, sortKeyLine, 1, 1, 1.0, 1.0, 17, 0, new Insets(0, 5, (i < 4) ? 2 : 5, 5), 0, 0));
            this.fieldPanel.add(this.fieldIsDescendings[i], new GridBagConstraints(2, sortKeyLine, 1, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, (i < 4) ? 1 : 4, 5), 0, 0));
            this.fieldPanel.add(this.fieldNames[i], new GridBagConstraints(3, sortKeyLine, 1, 1, 100.0, 1.0, 17, 2, new Insets(0, 5, (i < 4) ? 2 : 5, 5), 0, 0));
            this.fieldPanel.add(this.fieldMoveUps[i], new GridBagConstraints(4, sortKeyLine, 1, 1, 1.0, 1.0, 10, 2, new Insets(0, 5, (i < 4) ? 1 : 4, 5), 0, 0));
            this.fieldPanel.add(this.fieldMoveDowns[i], new GridBagConstraints(5, sortKeyLine, 1, 1, 1.0, 1.0, 10, 2, new Insets(0, 5, 1, 10), 0, 0));
        }
    }
    
    private void layoutFieldPanelLabels() {
        final int topLabelLine = 0;
        final int bottomLabelLine = 1;
        this.fieldPanel.add(this.fieldTypeLabel, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, 2, 5), 0, 0));
        this.fieldPanel.add(this.fieldSortLabel, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, 2, 5), 0, 0));
        this.fieldPanel.add(this.fieldAscDescLabel, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, 13, 0, new Insets(0, 5, 2, 5), 0, 0));
        this.fieldPanel.add(this.fieldNameLabel, new GridBagConstraints(3, 1, 1, 1, 1.0, 1.0, 10, 2, new Insets(0, 5, 2, 5), 0, 0));
        this.fieldPanel.add(this.fieldMoveLabel, new GridBagConstraints(4, 0, 2, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, 2, 10), 0, 0));
        this.fieldPanel.add(this.fieldMoveUpLabel, new GridBagConstraints(4, 1, 1, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, 2, 5), 0, 0));
        this.fieldPanel.add(this.fieldMoveDownLabel, new GridBagConstraints(5, 1, 1, 1, 1.0, 1.0, 10, 0, new Insets(0, 5, 2, 10), 0, 0));
    }
    
    private void swapFieldRows(final int i, final int j) {
        if (this.defibrillate) {
            return;
        }
        this.defibrillate = true;
        final FieldType swap1a = (FieldType)this.fieldTypes[i].getSelectedItem();
        final FieldType swap1b = (FieldType)this.fieldTypes[j].getSelectedItem();
        this.fieldTypes[i].setSelectedItem(swap1b);
        this.fieldTypes[j].setSelectedItem(swap1a);
        final boolean swap2a = this.fieldIsDescendings[i].isSelected();
        final boolean swap2b = this.fieldIsDescendings[j].isSelected();
        this.fieldIsDescendings[i].setSelected(swap2b);
        this.fieldIsDescendings[j].setSelected(swap2a);
        final String swap3a = this.fieldNames[i].getText();
        final String swap3b = this.fieldNames[j].getText();
        this.fieldNames[i].setText(swap3b);
        this.fieldNames[j].setText(swap3a);
        this.defibrillate = false;
    }
    
    private void tidyFields() {
        final InterfaceType interfaceType = (InterfaceType)this.interfaceSelection.getSelectedItem();
        switch (interfaceType) {
            case COMPARABLE: {
                this.interfaceSelection.setToolTipText("Generate a Comparable class");
                this.gClassLabel.setText("Comparable class");
                this.gClassLabel.setVisible(false);
                this.gClass.setVisible(false);
                break;
            }
            case COMPARATOR_TOP_LEVEL: {
                this.interfaceSelection.setToolTipText("Generate a Comparator top-level class");
                this.gClassLabel.setText("Comparator class");
                this.gClassLabel.setVisible(true);
                this.gClass.setVisible(true);
                break;
            }
            case COMPARATOR_NESTED: {
                this.interfaceSelection.setToolTipText("Generate a Comparator nested class");
                this.gClassLabel.setText("Comparator class");
                this.gClassLabel.setVisible(true);
                this.gClass.setVisible(true);
                break;
            }
        }
        for (int i = 0; i < 5; ++i) {
            final FieldType ft = (FieldType)this.fieldTypes[i].getSelectedItem();
            final String fieldName = this.fieldNames[i].getText().trim();
            if (ft == FieldType.SPARE) {
                this.fieldIsDescendings[i].setEnabled(false);
                this.fieldIsDescendings[i].setVisible(false);
                this.fieldNames[i].setEnabled(false);
                this.fieldNames[i].setVisible(false);
                if (fieldName.length() > 0) {
                    this.fieldNames[i].setText("");
                }
            }
            else {
                this.fieldIsDescendings[i].setEnabled(true);
                this.fieldIsDescendings[i].setVisible(true);
                this.fieldNames[i].setEnabled(true);
                this.fieldNames[i].setVisible(true);
            }
        }
        this.customiseInstructions(interfaceType);
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new ComparatorCutter(), "Comparator Cutter 1.5", 582, 910);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16773360);
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_INSTRUCTIONS = new Color(16771304);
        BACKGROUND_FOR_PANEL = new Color(16771304);
        FOREGROUND_FOR_ENTER = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_RESULT = Color.BLACK;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_LABELS_SMALL = FontFactory.build("Dialog", 1, 12);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        ASCENDING_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/ascending.png"));
        DESCENDING_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/descending.png"));
        MOVE_DOWN_DISABLED_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/movedowndisabled.png"));
        MOVE_DOWN_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/movedown.png"));
        MOVE_UP_DISABLED_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/moveupdisabled.png"));
        MOVE_UP_ICON = new ImageIcon(ComparatorCutter.class.getResource("image/moveup.png"));
    }
    
    private class DescendingToggleListener implements ItemListener
    {
        public void itemStateChanged(final ItemEvent e) {
            final JCheckBox isFieldDescending = (JCheckBox)e.getSource();
            if (isFieldDescending.isSelected()) {
                isFieldDescending.setIcon(ComparatorCutter.DESCENDING_ICON);
                isFieldDescending.setToolTipText("This field will be sorted in descrending order (largest first). Click to toggle to ascending order.");
            }
            else {
                isFieldDescending.setIcon(ComparatorCutter.ASCENDING_ICON);
                isFieldDescending.setToolTipText("This field will be sorted in ascending order (smallest first). Click to toggle to descending order.");
            }
            ComparatorCutter.this.compose();
        }
    }
    
    private class GeneralListener implements ActionListener, ItemListener
    {
        public void actionPerformed(final ActionEvent e) {
            ComparatorCutter.this.compose();
        }
        
        public void itemStateChanged(final ItemEvent e) {
            ComparatorCutter.this.compose();
        }
    }
    
    private class LostFocusListener extends FocusAdapter
    {
        public void focusLost(final FocusEvent e) {
            super.focusLost(e);
            ComparatorCutter.this.compose();
        }
    }
    
    private class MoveDownListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            final JButton moveFieldDown = (JButton)e.getSource();
            final int i = ComparatorCutter.retrieveFieldRowIndex(moveFieldDown);
            ComparatorCutter.this.swapFieldRows(i, i + 1);
            ComparatorCutter.this.compose();
        }
    }
    
    private class MoveUpListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            final JButton moveFieldUp = (JButton)e.getSource();
            final int i = ComparatorCutter.retrieveFieldRowIndex(moveFieldUp);
            ComparatorCutter.this.swapFieldRows(i, i - 1);
            ComparatorCutter.this.compose();
        }
    }
}
