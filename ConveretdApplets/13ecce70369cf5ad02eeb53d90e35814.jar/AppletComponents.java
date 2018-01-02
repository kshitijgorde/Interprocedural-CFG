import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.applet.AppletContext;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.List;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

class AppletComponents
{
    GuitarCodex guitCodex;
    ChordAnalyser analyser;
    private Button btnReset;
    private Button btnShowAllNotes;
    private Button btnShowScope;
    private Button incScopeSizeBtn;
    private Button incScopePosBtn;
    private Button decScopeSizeBtn;
    private Button decScopePosBtn;
    private List baseNotes;
    private List symbolList;
    private List scaleList;
    private Choice tuningChoice;
    private TextField scopeSizeValue;
    private TextField scopePosValue;
    private int nScopeSize;
    private int nScopePos;
    private Checkbox cbNote;
    private Checkbox cbIntervall;
    private Checkbox cbRightHand;
    private Checkbox cbLeftHand;
    private Label chordInfo;
    private Label typeInfo;
    private Label notesInfo;
    private Label intervalInfo;
    private Label appletInfo;
    private Label urlInfo;
    private Label reverseChordInfo;
    public String szChord;
    public String szType;
    public String szNotes;
    public String szIntervals;
    private Panel listLayout;
    private Panel detailLayout;
    private Panel checkLayout;
    private Panel checkBtnLay;
    private Panel detailPan;
    private Panel statusPan;
    private Panel infoPan;
    private Panel infoLayout;
    private Panel scaleImgLayout;
    private Panel displaySettings;
    private Panel scopeBtnLayout;
    private Panel resetAllBtnLayout;
    AppletContext appletContext;
    Color appColor;
    
    public AppletComponents(final AppletContext appletContext, final Color appColor, final GuitarCodex guitCodex, final ChordAnalyser chordAnalyser) {
        this.btnReset = null;
        this.btnShowAllNotes = null;
        this.btnShowScope = null;
        this.incScopeSizeBtn = new Button(">>");
        this.incScopePosBtn = new Button(">>");
        this.decScopeSizeBtn = new Button("<<");
        this.decScopePosBtn = new Button("<<");
        this.baseNotes = null;
        this.symbolList = null;
        this.scaleList = null;
        this.tuningChoice = null;
        this.listLayout = null;
        this.detailLayout = null;
        this.checkLayout = null;
        this.checkBtnLay = null;
        this.detailPan = null;
        this.statusPan = null;
        this.infoPan = null;
        this.infoLayout = null;
        this.scaleImgLayout = null;
        this.displaySettings = null;
        this.scopeBtnLayout = null;
        this.resetAllBtnLayout = null;
        this.analyser = chordAnalyser;
        guitCodex.getImageCanvas().setChordAnalyser(chordAnalyser);
        this.appletContext = appletContext;
        this.appColor = appColor;
        this.guitCodex = guitCodex;
        this.btnReset = new Button("Reset");
        this.btnShowAllNotes = new Button("Show all notes");
        this.btnShowScope = new Button("Show scope");
        this.nScopeSize = 12;
        this.nScopePos = 1;
        this.incScopeSizeBtn.setEnabled(false);
        this.decScopePosBtn.setEnabled(false);
        this.initTuningChoice();
        this.initDisplaySettings();
        this.setScopeBtnLayout();
        this.setResetAllBtnLayout();
        this.setBaseNoteList();
        this.setChordSymbolList();
        this.setScaleList();
        this.setDetailLayout();
        this.setScaleImgLayout();
        this.setListLayout();
        this.setInfoLayout();
    }
    
    public Button getShowAllNotesBtn() {
        return this.btnShowAllNotes;
    }
    
    public void initDisplaySettings() {
        (this.displaySettings = new Panel()).setLayout(null);
        this.displaySettings.setBackground(this.guitCodex.displayColor);
        this.displaySettings.setSize(500, 40);
        final Label label = new Label();
        label.setBackground(Color.black);
        label.setForeground(Color.orange);
        label.setFont(new Font("SansSerif", 1, 11));
        label.setText(" D I S P L A Y");
        label.setBounds(5, 3, 500, 15);
        this.displaySettings.add(label);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(5, 20, 500, 75);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cbNote = new Checkbox("Notes", checkboxGroup, true);
        this.cbIntervall = new Checkbox("Intervals", checkboxGroup, false);
        this.cbNote.setFont(new Font("SansSerif", 0, 10));
        this.cbIntervall.setFont(new Font("SansSerif", 0, 10));
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        this.cbRightHand = new Checkbox("Right-handed", checkboxGroup2, true);
        this.cbLeftHand = new Checkbox("Left-handed", checkboxGroup2, false);
        this.cbRightHand.setFont(new Font("SansSerif", 0, 10));
        this.cbLeftHand.setFont(new Font("SansSerif", 0, 10));
        this.getTuningChoice().setBounds(0, 0, 200, 20);
        panel.add(this.getTuningChoice());
        this.cbNote.setBounds(205, 0, 50, 20);
        panel.add(this.cbNote);
        this.cbIntervall.setBounds(255, 0, 70, 20);
        panel.add(this.cbIntervall);
        this.cbRightHand.setBounds(325, 0, 85, 20);
        panel.add(this.cbRightHand);
        this.cbLeftHand.setBounds(415, 0, 85, 20);
        panel.add(this.cbLeftHand);
        this.displaySettings.add(panel);
    }
    
    public void setScopeBtnLayout() {
        (this.scopeBtnLayout = new Panel()).setLayout(null);
        this.scopeBtnLayout.setSize(350, 35);
        this.scopeBtnLayout.setBackground(this.guitCodex.scopeColor);
        final Color color = new Color(180, 180, 100);
        final Label label = new Label("Set size of scope");
        final Label label2 = new Label("Scope fret position");
        this.scopeSizeValue = new TextField(String.valueOf(this.nScopeSize));
        this.scopePosValue = new TextField(String.valueOf(this.nScopePos));
        label.setFont(new Font("SansSerif", 0, 9));
        label.setBounds(5, 0, 90, 11);
        this.scopeBtnLayout.add(label);
        this.decScopeSizeBtn.setBounds(5, 12, 30, 15);
        this.scopeBtnLayout.add(this.decScopeSizeBtn);
        this.scopeSizeValue.setFont(new Font("SansSerif", 0, 10));
        this.scopeSizeValue.setBounds(38, 11, 20, 18);
        this.scopeBtnLayout.add(this.scopeSizeValue);
        this.incScopeSizeBtn.setBounds(60, 12, 30, 15);
        this.scopeBtnLayout.add(this.incScopeSizeBtn);
        label2.setFont(new Font("SansSerif", 0, 9));
        label2.setBounds(100, 0, 90, 11);
        this.scopeBtnLayout.add(label2);
        this.decScopePosBtn.setBounds(100, 12, 30, 15);
        this.scopeBtnLayout.add(this.decScopePosBtn);
        this.scopePosValue.setFont(new Font("SansSerif", 0, 10));
        this.scopePosValue.setBounds(133, 11, 20, 18);
        this.scopeBtnLayout.add(this.scopePosValue);
        this.incScopePosBtn.setBounds(155, 12, 30, 15);
        this.scopeBtnLayout.add(this.incScopePosBtn);
        this.btnShowScope.setBounds(200, 8, 120, 20);
        this.scopeBtnLayout.add(this.btnShowScope);
    }
    
    public Panel getScopeBtnLayout() {
        return this.scopeBtnLayout;
    }
    
    public Panel getDisplaySettings() {
        return this.displaySettings;
    }
    
    public void setResetAllBtnLayout() {
        (this.resetAllBtnLayout = new Panel()).setLayout(null);
        this.resetAllBtnLayout.setSize(350, 30);
        this.resetAllBtnLayout.setBackground(this.guitCodex.showResetColor);
        this.btnShowAllNotes.setBounds(10, 2, 110, 18);
        this.resetAllBtnLayout.add(this.btnShowAllNotes);
        this.btnReset.setBounds(200, 2, 110, 18);
        this.resetAllBtnLayout.add(this.btnReset);
    }
    
    public Panel getResetAllBtnLayout() {
        return this.resetAllBtnLayout;
    }
    
    public void setScaleImgLayout() {
        final Panel panel = new Panel(new BorderLayout());
        (this.scaleImgLayout = new Panel()).setLayout(null);
        this.scaleImgLayout.setSize(160, 400);
        this.scaleImgLayout.setBackground(Color.white);
        panel.add(this.guitCodex.getImageCanvas());
        panel.setBounds(0, 3, 160, 430);
        this.scaleImgLayout.add(panel);
    }
    
    public Panel getScaleImgLayout() {
        return this.scaleImgLayout;
    }
    
    public void setListLayout() {
        (this.listLayout = new Panel()).setLayout(null);
        this.listLayout.setSize(350, 500);
        this.listLayout.setBackground(this.guitCodex.chordScaleColor);
        final Label label = new Label();
        label.setBackground(Color.black);
        label.setForeground(Color.orange);
        label.setFont(new Font("SansSerif", 1, 11));
        label.setText(" C H O R D S");
        final Label label2 = new Label();
        label2.setBackground(Color.black);
        label2.setForeground(Color.orange);
        label2.setFont(new Font("SansSerif", 1, 11));
        label2.setText(" S C A L E S");
        label.setBounds(5, 3, 130, 15);
        this.listLayout.add(label);
        label2.setBounds(140, 3, 190, 15);
        this.listLayout.add(label2);
        this.getBaseNoteList().setBounds(5, 20, 55, 160);
        this.listLayout.add(this.getBaseNoteList());
        this.getChordSymbols().setBounds(65, 20, 75, 160);
        this.listLayout.add(this.getChordSymbols());
        this.getScaleList().setBounds(140, 20, 190, 160);
        this.listLayout.add(this.getScaleList());
    }
    
    public List getScaleList() {
        return this.scaleList;
    }
    
    public Panel getListLayout() {
        return this.listLayout;
    }
    
    public void setInfoLayout() {
        this.appletInfo = new Label("GuitarCodex v.2.1 (Freeware) © 2002 Robert Leh");
        this.urlInfo = new Label("www.microtools.de");
        (this.infoLayout = new Panel()).setLayout(null);
        this.infoLayout.setFont(new Font("SansSerif", 0, 9));
        this.infoLayout.setForeground(Color.black);
        this.infoLayout.setBounds(0, 0, 500, 10);
        this.appletInfo.setBounds(0, 0, 210, 10);
        this.urlInfo.setBounds(210, 0, 150, 10);
        this.urlInfo.setForeground(Color.blue);
        this.infoLayout.add(this.appletInfo);
        this.infoLayout.add(this.urlInfo);
    }
    
    public void setDetailLayout() {
        this.detailPan = new Panel(new GridLayout(10, 1));
        final Panel panel = new Panel(new GridLayout());
        final Panel panel2 = new Panel(new GridLayout());
        final Panel panel3 = new Panel(new GridLayout());
        final Panel panel4 = new Panel(new GridLayout());
        final Panel panel5 = new Panel(new GridLayout());
        final Panel panel6 = new Panel(new GridLayout());
        final Panel panel7 = new Panel(new GridLayout());
        final Panel panel8 = new Panel(new GridLayout());
        final Panel panel9 = new Panel(new GridLayout());
        final Panel panel10 = new Panel(new GridLayout());
        this.detailPan.setBackground(new Color(197, 212, 231));
        panel.setBackground(new Color(102, 153, 201));
        panel2.setBackground(new Color(102, 153, 201));
        panel3.setBackground(new Color(102, 153, 201));
        panel4.setBackground(new Color(102, 153, 201));
        panel5.setBackground(new Color(102, 153, 201));
        panel.setForeground(Color.white);
        panel2.setForeground(Color.white);
        panel3.setForeground(Color.white);
        panel4.setForeground(Color.white);
        panel5.setForeground(Color.white);
        panel.setFont(new Font("SansSerif", 1, 11));
        panel2.setFont(new Font("SansSerif", 1, 11));
        panel3.setFont(new Font("SansSerif", 1, 11));
        panel4.setFont(new Font("SansSerif", 1, 11));
        panel5.setFont(new Font("SansSerif", 1, 11));
        panel.add(new Label("Chord:"));
        panel2.add(new Label("Type:"));
        panel3.add(new Label("Intervals:"));
        panel4.add(new Label("Notes:"));
        panel5.add(new Label("Reverse chord(s):"));
        panel6.setFont(new Font("SansSerif", 1, 12));
        panel7.setFont(new Font("SansSerif", 1, 12));
        panel8.setFont(new Font("SansSerif", 1, 12));
        panel9.setFont(new Font("SansSerif", 1, 12));
        panel10.setFont(new Font("SansSerif", 1, 12));
        panel6.add(this.chordInfo = new Label());
        panel7.add(this.typeInfo = new Label());
        panel8.add(this.intervalInfo = new Label());
        panel9.add(this.notesInfo = new Label());
        panel10.add(this.reverseChordInfo = new Label());
        this.detailPan.add(panel);
        this.detailPan.add(panel6);
        this.detailPan.add(panel2);
        this.detailPan.add(panel7);
        this.detailPan.add(panel3);
        this.detailPan.add(panel8);
        this.detailPan.add(panel4);
        this.detailPan.add(panel9);
        this.detailPan.add(panel5);
        this.detailPan.add(panel10);
        (this.detailLayout = new Panel()).setLayout(null);
        this.detailLayout.setSize(325, 155);
        this.detailLayout.setBackground(this.appColor);
        final Label label = new Label();
        label.setAlignment(1);
        label.setBackground(Color.black);
        label.setForeground(Color.orange);
        label.setFont(new Font("SansSerif", 1, 11));
        label.setText("D - E - T - A - I - L - S");
        label.setBounds(5, 3, 325, 15);
        this.detailLayout.add(label);
        this.detailPan.setBounds(5, 18, 325, 155);
        this.detailLayout.setBackground(this.guitCodex.detailColor);
        this.detailLayout.add(this.detailPan);
    }
    
    public Panel getDetails() {
        return this.detailLayout;
    }
    
    public Button getResetBtn() {
        return this.btnReset;
    }
    
    public void setBaseNoteList() {
        (this.baseNotes = new List(12, false)).add("C");
        this.baseNotes.add("C#/Db");
        this.baseNotes.add("D");
        this.baseNotes.add("D#/Eb");
        this.baseNotes.add("E");
        this.baseNotes.add("F");
        this.baseNotes.add("F#/Gb");
        this.baseNotes.add("G");
        this.baseNotes.add("G#/Ab");
        this.baseNotes.add("A");
        this.baseNotes.add("A#/Bb");
        this.baseNotes.add("B");
    }
    
    public List getBaseNoteList() {
        return this.baseNotes;
    }
    
    public void setChordSymbolList() {
        this.symbolList = new List(12, false);
        final String[] chordSymbolList = this.analyser.getCList().getChordSymbolList();
        for (int i = 0; i < chordSymbolList.length; ++i) {
            this.symbolList.add(chordSymbolList[i]);
        }
    }
    
    public void setScaleList() {
        this.scaleList = new List(12, false);
        final String[] scaleList = this.analyser.getCList().getScaleList();
        for (int i = 0; i < scaleList.length; ++i) {
            this.scaleList.add(scaleList[i]);
        }
    }
    
    public List getChordSymbols() {
        return this.symbolList;
    }
    
    public void initTuningChoice() {
        (this.tuningChoice = new Choice()).add("EADGBE - Standard tuning");
        this.tuningChoice.add("DADGBE - Drop D tuning");
        this.tuningChoice.add("DADGBD - Double drop D tuning");
        this.tuningChoice.add("DADF#AD - Open D tuning");
        this.tuningChoice.add("DADFAD - Open D minor tuning");
        this.tuningChoice.add("DADDAD - D modal tuning");
        this.tuningChoice.add("EADGCF - Fourths tuning");
        this.tuningChoice.add("EBEAbBE - Open E tuning");
        this.tuningChoice.add("DGDGBE - G6 tuning");
        this.tuningChoice.add("DGDGBD - Open G tuning");
        this.tuningChoice.add("DGDGBbD - G minor tuning");
        this.tuningChoice.add("EAEAC#E - Open A tuning");
        this.tuningChoice.add("EAEACE - A minor tuning");
        this.tuningChoice.add("CGCGAE - C6 tuning");
        this.tuningChoice.add("CGCGCE - Open C tuning");
        this.tuningChoice.add("CGDGAD - Low C tuning");
        this.tuningChoice.add("Custom tuning");
    }
    
    public void setTuning(final int tuning) {
        this.guitCodex.getImageCanvas().setTuning(tuning);
    }
    
    public Choice getTuningChoice() {
        return this.tuningChoice;
    }
    
    public void setTuningChoice(final int n) {
        this.tuningChoice.select(n);
    }
    
    public void setDetails(final String s, final boolean b) {
        if (s != null) {
            String s2 = "";
            if (b) {
                this.analyser.analyseChordExpression(s);
                this.szChord = s;
                if (!this.szChord.equals("No matching chord found.")) {
                    this.szType = this.analyser.getChordName().trim();
                    this.szNotes = this.analyser.getSingleNotes();
                    final char[] charArray = this.analyser.getChordFormula().toCharArray();
                    for (int i = 1; i < charArray.length; ++i) {
                        if (charArray[i] == '\t') {
                            charArray[i] = ',';
                        }
                        s2 += charArray[i];
                    }
                    this.szIntervals = s2.substring(1, s2.length());
                }
                else {
                    this.szType = "";
                    this.szNotes = "";
                    this.szIntervals = "";
                }
            }
            else {
                this.analyser.analyseScaleExpression(s);
                this.szChord = s;
                if (!this.szChord.equals("No matching chord found.")) {
                    this.szType = this.analyser.getScaleName().trim();
                    this.szNotes = this.analyser.getScaleNotes();
                    final char[] charArray2 = this.analyser.getScaleFormula().toCharArray();
                    for (int j = 1; j < charArray2.length; ++j) {
                        if (charArray2[j] == '\t') {
                            charArray2[j] = ',';
                        }
                        s2 += charArray2[j];
                    }
                    this.szIntervals = s2.substring(1, s2.length());
                }
                else {
                    this.szType = "";
                    this.szNotes = "";
                    this.szIntervals = "";
                }
            }
            if (!this.szNotes.equals("")) {
                this.analyser.findReverseChords(this.szNotes);
            }
            this.analyser.analyseChordExpression(s);
        }
        this.updateDetails();
    }
    
    public void deselectItemsInList() {
        this.symbolList.deselect(this.symbolList.getSelectedIndex());
    }
    
    public void deselectItemsInScaleList() {
        this.scaleList.deselect(this.scaleList.getSelectedIndex());
    }
    
    public void deselectItemsInBaseNoteList() {
        this.baseNotes.deselect(this.baseNotes.getSelectedIndex());
    }
    
    public void updateDetails() {
        this.chordInfo.setText(this.szChord);
        this.typeInfo.setText(this.szType);
        this.intervalInfo.setText(this.szIntervals);
        this.notesInfo.setText(this.szNotes);
        String string = "";
        if (this.szNotes != null) {
            for (int i = 0; i < this.analyser.getReverseChords().length; ++i) {
                if (this.analyser.getReverseChords()[0] != null && this.analyser.getReverseChords()[0] != "") {
                    string = string + this.analyser.getReverseChords()[i] + ", ";
                }
            }
        }
        this.reverseChordInfo.setText(string);
    }
    
    public void resetDetails() {
        this.chordInfo.setText("");
        this.typeInfo.setText("");
        this.intervalInfo.setText("");
        this.notesInfo.setText("");
        this.reverseChordInfo.setText("");
    }
    
    public TextField getScopeSizeValue() {
        return this.scopeSizeValue;
    }
    
    public TextField getScopePosValue() {
        return this.scopePosValue;
    }
    
    public void setScopeSize(final int nScopeSize) {
        this.nScopeSize = nScopeSize;
        this.scopeSizeValue.setText(String.valueOf(this.nScopeSize));
    }
    
    public void setScopePos(final int nScopePos) {
        this.nScopePos = nScopePos;
        this.scopePosValue.setText(String.valueOf(this.nScopePos));
    }
    
    public int getScopeSize() {
        return this.nScopeSize;
    }
    
    public int getScopePos() {
        return this.nScopePos;
    }
    
    public Button getIncScopeSizeBtn() {
        return this.incScopeSizeBtn;
    }
    
    public Button getDecScopeSizeBtn() {
        return this.decScopeSizeBtn;
    }
    
    public Button getIncScopePosBtn() {
        return this.incScopePosBtn;
    }
    
    public Button getDecScopePosBtn() {
        return this.decScopePosBtn;
    }
    
    public Button getShowScopeBtn() {
        return this.btnShowScope;
    }
    
    public Checkbox getCBoxNote() {
        return this.cbNote;
    }
    
    public Checkbox getCBoxInterval() {
        return this.cbIntervall;
    }
    
    public Checkbox getCBoxRightHand() {
        return this.cbRightHand;
    }
    
    public Checkbox getCBoxLeftHand() {
        return this.cbLeftHand;
    }
    
    public void setUserAccessEnabled(final boolean enabled) {
        this.btnShowScope.setEnabled(enabled);
        this.scopePosValue.setEnabled(enabled);
        this.scopeSizeValue.setEnabled(enabled);
        this.decScopeSizeBtn.setEnabled(enabled);
        this.incScopePosBtn.setEnabled(enabled);
        this.cbNote.setEnabled(enabled);
        this.cbIntervall.setEnabled(enabled);
        if (!enabled) {
            this.decScopePosBtn.setEnabled(enabled);
            this.incScopeSizeBtn.setEnabled(enabled);
        }
    }
    
    public void setScopeButtonStatus(final int n, final int n2) {
        if (n >= 12 && this.incScopePosBtn.isEnabled()) {
            this.incScopePosBtn.setEnabled(false);
        }
        else if (n < 12) {
            this.incScopePosBtn.setEnabled(true);
        }
        if (n <= 1 && this.decScopePosBtn.isEnabled()) {
            this.decScopePosBtn.setEnabled(false);
        }
        else if (n > 1) {
            this.decScopePosBtn.setEnabled(true);
        }
        if (n2 >= 12 && this.incScopeSizeBtn.isEnabled()) {
            this.incScopeSizeBtn.setEnabled(false);
        }
        else if (n2 < 12) {
            this.incScopeSizeBtn.setEnabled(true);
        }
        if (n2 <= 1 && this.decScopeSizeBtn.isEnabled()) {
            this.decScopeSizeBtn.setEnabled(false);
        }
        else if (n2 > 1) {
            this.decScopeSizeBtn.setEnabled(true);
        }
    }
    
    public Label getHomeUrl() {
        return this.urlInfo;
    }
    
    public Panel getInfoLayout() {
        return this.infoLayout;
    }
}
