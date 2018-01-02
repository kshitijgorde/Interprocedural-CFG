// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.bio;

import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.event.ChangeEvent;
import com.mindprod.common15.FontFactory;
import javax.swing.Icon;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.VersionCheck;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import com.mindprod.spinner.DateSpinner;
import java.awt.Container;
import javax.swing.event.ChangeListener;
import com.mindprod.common11.BigDate;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Bio extends JApplet
{
    private static final int APPLET_HEIGHT = 465;
    private static final int APPLET_WIDTH = 640;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2000-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-03-24";
    private static final String TITLE_STRING = "CMP Biorhythms";
    private static final String VERSION_STRING = "2.7";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_LUCK;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private BigDate birthDate;
    private BigDate forDate;
    private BigDate today;
    private ChangeListener dateChangeListener;
    private Container contentPane;
    private DateSpinner birthDateChoice;
    private DateSpinner forDateChoice;
    private DrawingPanel drawingPanel;
    private ImageIcon logo;
    private ImageIcon luckLogo;
    private JButton about;
    private JLabel birthDatePrompt;
    private JLabel combiEvaluation;
    private JLabel combiLabel;
    private JLabel emotEvaluation;
    private JLabel emotLabel;
    private JLabel forDateDisplay;
    private JLabel forDatePrompt;
    private JLabel forLabel;
    private JLabel intelEvaluation;
    private JLabel intelLabel;
    private JLabel logoImage;
    private JLabel luckMessage;
    private JLabel physEvaluation;
    private JLabel physLabel;
    private JLabel title;
    private JLabel title2;
    
    public Bio() {
        this.birthDate = new BigDate(1955, 10, 28);
    }
    
    public void destroy() {
        this.about = null;
        this.birthDateChoice = null;
        this.birthDatePrompt = null;
        this.drawingPanel = null;
        this.combiEvaluation = null;
        this.combiLabel = null;
        this.dateChangeListener = null;
        this.emotEvaluation = null;
        this.emotLabel = null;
        this.forDate = null;
        this.forDateChoice = null;
        this.forDateDisplay = null;
        this.forDatePrompt = null;
        this.forLabel = null;
        this.intelEvaluation = null;
        this.intelLabel = null;
        this.logo = null;
        this.logoImage = null;
        this.luckLogo = null;
        this.luckMessage = null;
        this.physEvaluation = null;
        this.physLabel = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        (this.contentPane = this.getContentPane()).setBackground(Bio.BACKGROUND_FOR_APPLET);
        this.contentPane.setLayout(new GridBagLayout());
        this.buildComponents();
        this.layoutComponents();
        this.hookListeners();
        this.calc();
        this.validate();
        this.setVisible(true);
    }
    
    private void buildComponents() {
        (this.title = new JLabel("CMP Biorhythms 2.7")).setFont(Bio.FONT_FOR_TITLE);
        this.title.setForeground(Bio.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-03-24 build:9411")).setFont(Bio.FONT_FOR_TITLE2);
        this.title2.setForeground(Bio.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About CMP Biorhythms 2.7");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("CMP Biorhythms", "2.7", "Calculate Biorhythms", "for any date, past or future", "freeware", "2011-03-24", 2000, "Roedy Green", "BIO2", "1.5");
            }
        });
        this.logoImage = new JLabel();
        this.logo = new ImageIcon(Bio.class.getResource("han.jpg"));
        this.luckLogo = new ImageIcon(Bio.class.getResource("shamrock.png"));
        this.logoImage.setIcon(this.logo);
        (this.birthDatePrompt = new JLabel("Enter your birth date below:")).setFont(Bio.FONT_FOR_LABELS);
        this.birthDatePrompt.setForeground(Bio.FOREGROUND_FOR_LABEL);
        (this.birthDateChoice = new DateSpinner()).setMinimum(new BigDate(1900, 1, 1));
        this.birthDateChoice.setMaximum(BigDate.localToday());
        this.birthDateChoice.setValue(this.birthDate);
        (this.forDatePrompt = new JLabel("For biorhythms for other than today, enter that date below:")).setFont(Bio.FONT_FOR_LABELS);
        this.forDatePrompt.setForeground(Bio.FOREGROUND_FOR_LABEL);
        this.forDate = BigDate.localToday();
        (this.forDateChoice = new DateSpinner()).setMinimum(new BigDate(1900, 1, 1));
        this.forDateChoice.setMaximum(new BigDate(2030, 12, 31));
        this.forDateChoice.setValue(this.forDate);
        (this.drawingPanel = new DrawingPanel()).setFont(FontFactory.build("Dialog", 0, 10));
        (this.forLabel = new JLabel("For")).setFont(Bio.FONT_FOR_LABELS);
        this.forLabel.setForeground(Bio.FOREGROUND_FOR_LABEL);
        this.forDateDisplay = new JLabel(this.forDate.toString());
        (this.physLabel = new JLabel("Physical")).setFont(Bio.FONT_FOR_LABELS);
        this.physLabel.setForeground(CalcBiorhythms.FOREGROUND_FOR_PHYSICAL);
        this.physEvaluation = new JLabel("average");
        (this.emotLabel = new JLabel("Emotional")).setFont(Bio.FONT_FOR_LABELS);
        this.emotLabel.setForeground(CalcBiorhythms.FOREGROUND_FOR_EMOTIONAL);
        this.emotEvaluation = new JLabel("average");
        (this.intelLabel = new JLabel("Intellectual")).setFont(Bio.FONT_FOR_LABELS);
        this.intelLabel.setForeground(CalcBiorhythms.FOREGROUND_FOR_INTELLECTUAL);
        this.intelEvaluation = new JLabel("average");
        (this.combiLabel = new JLabel("Combined")).setFont(Bio.FONT_FOR_LABELS);
        this.combiLabel.setForeground(CalcBiorhythms.FOREGROUND_FOR_COMBI);
        this.combiEvaluation = new JLabel("average");
        (this.luckMessage = new JLabel("")).setFont(Bio.FONT_FOR_LABELS);
        this.luckMessage.setForeground(Bio.FOREGROUND_FOR_LUCK);
    }
    
    private void calc() {
        final BigDate from = new BigDate(this.forDate.getYYYY(), this.forDate.getMM(), -4, 2);
        final BigDate to = new BigDate(this.forDate.getYYYY(), this.forDate.getMM() + 4, 1, 2);
        this.drawingPanel.set(from.getOrdinal(), to.getOrdinal(), this.birthDate.getOrdinal(), this.forDate.getOrdinal());
        this.drawingPanel.repaint();
        final int daysSinceBirth = this.forDate.getOrdinal() - this.birthDate.getOrdinal();
        this.physEvaluation.setText(CalcBiorhythms.levelInWords(daysSinceBirth, 23));
        this.physEvaluation.invalidate();
        this.emotEvaluation.setText(CalcBiorhythms.levelInWords(daysSinceBirth, 28));
        this.emotEvaluation.invalidate();
        this.intelEvaluation.setText(CalcBiorhythms.levelInWords(daysSinceBirth, 33));
        this.intelEvaluation.invalidate();
        this.combiEvaluation.setText(CalcBiorhythms.levelInWords(daysSinceBirth, 21252));
        this.luckMessage.setText(CalcBiorhythms.luckLevelInWords(daysSinceBirth));
        this.luckMessage.invalidate();
        if (this.luckMessage.getText().length() == 0) {
            this.logoImage.setIcon(this.logo);
        }
        else {
            this.logoImage.setIcon(this.luckLogo);
        }
        this.validate();
        this.repaint();
    }
    
    private void hookListeners() {
        this.dateChangeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent event) {
                Bio.this.birthDate = Bio.this.birthDateChoice.getValue();
                Bio.this.forDate = Bio.this.forDateChoice.getValue();
                Bio.this.forDateDisplay.setText(Bio.this.forDate.toString());
                Bio.this.calc();
            }
        };
        this.birthDateChoice.addChangeListener(this.dateChangeListener);
        this.forDateChoice.addChangeListener(this.dateChangeListener);
        this.logoImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent m) {
                try {
                    final URL url = new URL("http://www.longevitycircuit.com/");
                    Bio.this.getAppletContext().showDocument(url);
                }
                catch (Exception ex) {}
            }
        });
    }
    
    private void layoutComponents() {
        this.contentPane.add(this.title, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 0, 5), 0, 0));
        this.contentPane.add(this.about, new GridBagConstraints(4, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 10, 2));
        this.contentPane.add(this.logoImage, new GridBagConstraints(0, 1, 1, 3, 0.0, 0.0, 11, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.birthDatePrompt, new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        this.contentPane.add(this.birthDateChoice, new GridBagConstraints(1, 3, 4, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.forDatePrompt, new GridBagConstraints(1, 4, 4, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        this.contentPane.add(this.forDateChoice, new GridBagConstraints(1, 5, 4, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.drawingPanel, new GridBagConstraints(0, 6, 5, 1, 0.0, 100.0, 13, 1, new Insets(10, 10, 10, 10), 0, 0));
        this.contentPane.add(this.forLabel, new GridBagConstraints(0, 7, 1, 1, 10.0, 0.0, 10, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.physLabel, new GridBagConstraints(1, 7, 1, 1, 10.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.emotLabel, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.intelLabel, new GridBagConstraints(3, 7, 1, 1, 10.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.combiLabel, new GridBagConstraints(4, 7, 1, 1, 10.0, 0.0, 10, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.contentPane.add(this.forDateDisplay, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.physEvaluation, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.emotEvaluation, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.intelEvaluation, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.combiEvaluation, new GridBagConstraints(4, 8, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.contentPane.add(this.luckMessage, new GridBagConstraints(0, 9, 5, 1, 0.0, 0.0, 10, 0, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Bio(), "CMP Biorhythms 2.7", 640, 465);
    }
    
    static {
        BACKGROUND_FOR_APPLET = Color.white;
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_LUCK = new Color(32768);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
