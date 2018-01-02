import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Cubie extends Applet implements ActionListener, ItemListener, FocusListener, Runnable
{
    final int VIEWWIDTH = 300;
    final int VIEWHEIGHT = 350;
    public static Settings settings;
    int symType;
    int viewer;
    Viewer[] cubeViewers;
    Solver[] solvers;
    Panel rightPanel;
    Button mixBut;
    Button resetBut;
    Button editBut;
    Button solveBut;
    Button viewBut;
    Button viewResetBut;
    Panel boxPanel;
    Button gensolBut;
    TextField textBox;
    Button playBut;
    Button revBut;
    Button stepBut;
    Button backBut;
    Button endBut;
    Button beginBut;
    TabSet tabSet;
    Panel[] tabPanel;
    Checkbox[] groupCheckBox;
    Checkbox superBox;
    CheckboxGroup cubeGroup;
    Checkbox[] groupRadioBox;
    Checkbox symTwoColBox;
    SymButton[] symButton;
    SymButton symAllImage;
    SymButton symCurrentImage;
    Button symResetBut;
    Color[] colors;
    boolean solution;
    boolean symTwoCol;
    MoveSequence generator;
    int seqPos;
    boolean playFw;
    boolean moveInProgress;
    boolean isPlaying;
    
    public void init() {
        this.setLayout(null);
        this.setBackground(this.colors[3]);
        this.add(this.rightPanel);
        this.add(this.boxPanel);
        for (int i = 0; i < this.cubeViewers.length; ++i) {
            this.add(this.cubeViewers[i]);
            this.cubeViewers[i].setBounds(0, 0, 300, 350);
            this.cubeViewers[i].setVisible(i == 0);
            this.cubeViewers[i].setBackground(this.colors[3]);
        }
        this.rightPanel.setBounds(300, 0, 220, 350);
        this.boxPanel.setBounds(0, 350, 520, 50);
        this.rightPanel.setLayout(null);
        this.rightPanel.setBackground(this.colors[3]);
        this.rightPanel.add(this.mixBut);
        this.mixBut.setBounds(0, 0, 55, 20);
        this.rightPanel.add(this.resetBut);
        this.resetBut.setBounds(55, 0, 55, 20);
        this.rightPanel.add(this.editBut);
        this.editBut.setBounds(110, 0, 55, 20);
        this.rightPanel.add(this.solveBut);
        this.solveBut.setBounds(165, 0, 55, 20);
        this.rightPanel.add(this.viewBut);
        this.viewBut.setBounds(0, 330, 110, 20);
        this.rightPanel.add(this.viewResetBut);
        this.viewResetBut.setBounds(110, 330, 110, 20);
        this.mixBut.addActionListener(this);
        this.resetBut.addActionListener(this);
        this.editBut.addActionListener(this);
        this.solveBut.addActionListener(this);
        this.viewBut.addActionListener(this);
        this.viewResetBut.addActionListener(this);
        (this.tabSet = new TabSet(this, new Color(128, 128, 128), this.colors[1])).setBackground(this.colors[3]);
        this.rightPanel.add(this.tabSet);
        this.tabSet.setBounds(0, 21, 220, 19);
        this.tabSet.addTab("Groups", this.tabPanel[0]);
        this.tabSet.addTab("Symmetries", this.tabPanel[1]);
        for (int j = 0; j < 2; ++j) {
            this.tabPanel[j].setLayout(null);
            this.tabPanel[j].setBounds(0, 40, 220, 288);
            this.tabPanel[j].setBackground(this.colors[1]);
            this.rightPanel.add(this.tabPanel[j]);
        }
        final int n = this.tabPanel[0].getSize().height / (this.solvers.length * 2 + 3);
        for (int k = 0; k < this.solvers.length; ++k) {
            (this.groupCheckBox[k] = new Checkbox()).setState(true);
            this.groupCheckBox[k].setEnabled(false);
            this.groupRadioBox[k].addItemListener(this);
            this.tabPanel[0].add(this.groupCheckBox[k]);
            this.tabPanel[0].add(this.groupRadioBox[k]);
            this.groupRadioBox[k].setBounds(0, 3 * n + 2 * n * k, 195, 2 * n);
            this.groupCheckBox[k].setBounds(195, 3 * n + 2 * n * k, 25, 2 * n);
            this.groupRadioBox[k].setBackground(this.colors[0]);
            this.groupCheckBox[k].setBackground(this.colors[0]);
        }
        this.tabPanel[0].add(this.superBox);
        this.superBox.setBounds(25, n, 195, n + n);
        this.superBox.setBackground(this.colors[1]);
        this.superBox.addItemListener(this);
        final int n2 = this.tabPanel[1].getSize().height / 9;
        for (int l = 0; l < 29; ++l) {
            this.symButton[l] = new SymButton(this, 1 << l);
            this.tabPanel[1].add(this.symButton[l]);
            this.symButton[l].setBackground(new Color(208, 208, 208));
            int n3 = l;
            int n4;
            if (n3 >= 23) {
                n3 -= 23;
                n4 = 4;
            }
            else if (n3 >= 19) {
                n3 -= 17;
                n4 = 2;
            }
            else if (n3 >= 13) {
                n3 -= 13;
                n4 = 3;
            }
            else if (n3 >= 7) {
                n3 -= 7;
                n4 = 1;
            }
            else if (n3 > 0) {
                --n3;
                n4 = 0;
            }
            else {
                n4 = 2;
            }
            this.symButton[l].setBounds(n4 * 44, n3 * n2 + n2 + n2 + n2, 43, n2 - 1);
        }
        final Label label = new Label("Current:");
        label.setBounds(0, 0, 55, n2 + n2);
        this.tabPanel[1].add(label);
        this.symCurrentImage.setBounds(55, 0, 55, n2 + n2);
        this.symCurrentImage.setEnabled(false);
        this.tabPanel[1].add(this.symCurrentImage);
        final Label label2 = new Label("Selected:");
        label2.setBounds(110, 0, 55, n2 + n2);
        this.tabPanel[1].add(label2);
        this.symAllImage.setBounds(165, 0, 55, n2 + n2);
        this.symAllImage.setEnabled(false);
        this.tabPanel[1].add(this.symAllImage);
        this.symTwoColBox.setBounds(5, n2 + n2, 160, 20);
        this.tabPanel[1].add(this.symTwoColBox);
        this.symResetBut.setBounds(165, n2 + n2, 55, 20);
        this.tabPanel[1].add(this.symResetBut);
        this.symResetBut.setBackground(new Color(208, 208, 208));
        this.symTwoColBox.addItemListener(this);
        this.symResetBut.addActionListener(this);
        this.boxPanel.setLayout(null);
        this.boxPanel.add(this.gensolBut);
        this.gensolBut.setBounds(0, 0, 75, 20);
        this.boxPanel.add(this.textBox);
        this.textBox.setBounds(75, 0, 445, 20);
        this.boxPanel.add(this.beginBut);
        this.beginBut.setBounds(75, 20, 55, 16);
        this.boxPanel.add(this.backBut);
        this.backBut.setBounds(130, 20, 55, 16);
        this.boxPanel.add(this.revBut);
        this.revBut.setBounds(185, 20, 55, 16);
        this.boxPanel.add(this.playBut);
        this.playBut.setBounds(240, 20, 55, 16);
        this.boxPanel.add(this.stepBut);
        this.stepBut.setBounds(295, 20, 55, 16);
        this.boxPanel.add(this.endBut);
        this.endBut.setBounds(350, 20, 55, 16);
        this.boxPanel.setBackground(this.colors[3]);
        this.gensolBut.addActionListener(this);
        this.textBox.addActionListener(this);
        this.textBox.addFocusListener(this);
        this.beginBut.addActionListener(this);
        this.backBut.addActionListener(this);
        this.revBut.addActionListener(this);
        this.playBut.addActionListener(this);
        this.stepBut.addActionListener(this);
        this.endBut.addActionListener(this);
        for (int n5 = 0; n5 < this.solvers.length; ++n5) {
            new Thread(this.solvers[n5]).start();
        }
        this.cubeViewers[this.viewer].repaint();
        this.updateStatus(false);
    }
    
    public void stop() {
        if (this.isPlaying) {
            this.isPlaying = false;
            while (this.moveInProgress) {
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex) {}
            }
            Cubie.settings.lockViewer = false;
        }
    }
    
    public void run() {
        this.isPlaying = true;
        Cubie.settings.lockViewer = true;
        do {
            if (this.playFw) {
                if (this.cubeViewers[this.viewer].showMove(this.generator.moves[this.seqPos], this.generator.amount[this.seqPos])) {
                    this.moveInProgress = (this.viewer == 0);
                    ++this.seqPos;
                }
                if (this.seqPos >= this.generator.len) {
                    this.isPlaying = false;
                }
            }
            else {
                --this.seqPos;
                if (!this.cubeViewers[this.viewer].showMove(this.generator.moves[this.seqPos], 4 - this.generator.amount[this.seqPos])) {
                    ++this.seqPos;
                }
                else {
                    this.moveInProgress = (this.viewer == 0);
                }
                if (this.seqPos <= 0) {
                    this.isPlaying = false;
                }
            }
            this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
            if (this.isPlaying) {
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex) {}
                do {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (Exception ex2) {}
                } while (this.moveInProgress);
            }
        } while (this.isPlaying);
        Cubie.settings.lockViewer = false;
    }
    
    private void updateStatus(final boolean b) {
        boolean b3;
        if (this.tabSet.currentTab == 0) {
            boolean b2 = false;
            for (int i = 0; i < this.solvers.length; ++i) {
                boolean state;
                if (b) {
                    state = this.solvers[i].setPosition(Cubie.settings.cubePos, true);
                    this.groupCheckBox[i].setState(state);
                }
                else {
                    state = this.groupCheckBox[i].getState();
                }
                if (i == Cubie.settings.group) {
                    b2 = state;
                }
                int n = 1;
                if (!this.solvers[i].isPrepared()) {
                    n = 0;
                }
                else if (this.solvers[i].isRunning()) {
                    n = 2;
                }
                this.groupCheckBox[i].setBackground(this.colors[n]);
                this.groupRadioBox[i].setBackground(this.colors[n]);
            }
            b3 = (this.solvers[Cubie.settings.group].isPrepared() && b2);
        }
        else {
            boolean b4;
            if (b) {
                b4 = this.solvers[0].setPosition(Cubie.settings.cubePos, true);
            }
            else {
                b4 = this.groupCheckBox[0].getState();
            }
            b3 = (this.solvers[0].isPrepared() && b4);
            final SymButton symCurrentImage = this.symCurrentImage;
            symCurrentImage.type = symCurrentImage.checkSym(Cubie.settings.cubePos.getSym());
            symCurrentImage.repaint();
        }
        this.solveBut.setLabel(Cubie.settings.solving ? "Stop" : "Solve");
        this.solveBut.setEnabled(b3 || Cubie.settings.solving);
    }
    
    public void destroy() {
        this.stop();
        if (Cubie.settings.solving) {
            this.solvers[Cubie.settings.group].stopSolving();
            while (Cubie.settings.solving) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex) {}
            }
        }
        for (int i = 0; i < this.cubeViewers.length; ++i) {
            this.remove(this.cubeViewers[i]);
        }
        this.remove(this.rightPanel);
        this.remove(this.boxPanel);
    }
    
    public String getAppletInfo() {
        return "Title: Cubie \nAuthor: Jaap Scherphuis";
    }
    
    public void solve() {
        final int n = (this.tabSet.currentTab == 0) ? Cubie.settings.group : 0;
        if (Cubie.settings.solving) {
            for (int i = 0; i < this.solvers.length; ++i) {
                this.solvers[i].stopSolving();
            }
            return;
        }
        if (this.solvers[n].setPosition(Cubie.settings.cubePos, false)) {
            this.startSolving();
            new Thread(this.solvers[n]).start();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.mixBut) {
            if (!Cubie.settings.solving) {
                this.stop();
                if (this.tabSet.currentTab == 0) {
                    this.solvers[Cubie.settings.group].mix(Cubie.settings.cubePos);
                }
                else {
                    Cubie.settings.cubePos.mix(this.symType, Cubie.settings.superGroup, this.symTwoCol);
                }
                this.setSequencePosition(-1);
                this.updateStatus(true);
                this.cubeViewers[this.viewer].repaint();
            }
        }
        else if (source == this.resetBut) {
            if (!Cubie.settings.solving) {
                this.stop();
                Cubie.settings.cubePos.reset();
                this.setSequencePosition(-1);
                this.updateStatus(true);
                this.cubeViewers[this.viewer].repaint();
            }
        }
        else {
            if (source == this.solveBut) {
                this.stop();
                this.solve();
                return;
            }
            if (source == this.gensolBut) {
                this.solution = !this.solution;
                this.gensolBut.setLabel(this.solution ? "Solution" : "Generator");
                if (this.generator == null) {
                    this.textBox.setText("");
                    return;
                }
                this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
            }
            else {
                if (source == this.viewBut) {
                    ++this.viewer;
                    if (this.viewer >= this.cubeViewers.length) {
                        this.viewer = 0;
                    }
                    for (int i = 0; i < this.cubeViewers.length; ++i) {
                        this.cubeViewers[i].setVisible(i == this.viewer);
                    }
                    return;
                }
                if (source == this.viewResetBut) {
                    Cubie.settings.cubePos.resetView();
                    for (int j = 0; j < this.cubeViewers.length; ++j) {
                        this.cubeViewers[j].reset();
                    }
                    this.updateStatus(false);
                    return;
                }
                if (source == this.editBut) {
                    Cubie.settings.edit = !Cubie.settings.edit;
                    this.editBut.setLabel(Cubie.settings.edit ? "Play" : "Edit");
                    this.seqPos = -1;
                    return;
                }
                if (source == this.textBox) {
                    this.groupRadioBox[Cubie.settings.group].requestFocus();
                    return;
                }
                if (source == this.beginBut) {
                    this.stop();
                    this.setSequencePosition((this.generator != null && this.solution) ? this.generator.len : 0);
                    this.cubeViewers[this.viewer].repaint();
                    return;
                }
                if (source == this.backBut) {
                    if (this.solution) {
                        this.stepForward();
                        return;
                    }
                    this.stepBackward();
                }
                else if (source == this.revBut) {
                    if (this.solution) {
                        this.playForward();
                        return;
                    }
                    this.playBackward();
                }
                else if (source == this.playBut) {
                    if (this.solution) {
                        this.playBackward();
                        return;
                    }
                    this.playForward();
                }
                else if (source == this.stepBut) {
                    if (this.solution) {
                        this.stepBackward();
                        return;
                    }
                    this.stepForward();
                }
                else {
                    if (source == this.endBut) {
                        this.stop();
                        this.setSequencePosition((this.generator == null || this.solution) ? 0 : this.generator.len);
                        this.cubeViewers[this.viewer].repaint();
                        return;
                    }
                    if (source == this.symResetBut) {
                        for (int k = 0; k < 29; ++k) {
                            final SymButton symButton = this.symButton[k];
                            symButton.pressed = false;
                            symButton.repaint();
                        }
                        this.symType = 0;
                        final SymButton symAllImage = this.symAllImage;
                        symAllImage.type = symAllImage.checkSym(this.symType);
                        symAllImage.repaint();
                        return;
                    }
                    if (source == this.tabSet) {
                        this.updateStatus(true);
                        return;
                    }
                    for (int l = 0; l < this.cubeViewers.length; ++l) {
                        if (source == this.cubeViewers[l]) {
                            this.updateStatus(true);
                            if (actionEvent.getActionCommand() == "user") {
                                this.seqPos = -1;
                                if (this.generator != null) {
                                    this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
                                }
                            }
                            else {
                                this.moveInProgress = false;
                            }
                            return;
                        }
                    }
                    for (int n = 0; n < this.solvers.length; ++n) {
                        if (source == this.solvers[n]) {
                            if (actionEvent.getActionCommand() != "a") {
                                if (actionEvent.getActionCommand() == "b") {
                                    this.stoppedSolving();
                                    this.generator = Cubie.settings.generator;
                                    Cubie.settings.generator = null;
                                    this.seqPos = this.generator.len;
                                    this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
                                    final CubePosition cubePos = Cubie.settings.cubePos;
                                    final MoveSequence generator = this.generator;
                                    cubePos.doSequence(generator, generator.len);
                                }
                                else if (actionEvent.getActionCommand() == "c") {
                                    this.stoppedSolving();
                                }
                                else if (actionEvent.getActionCommand() == "d") {
                                    this.stoppedSolving();
                                }
                                else {
                                    actionEvent.getActionCommand();
                                }
                            }
                            this.updateStatus(false);
                            return;
                        }
                    }
                    for (int n2 = 0; n2 < 29; ++n2) {
                        if (source == this.symButton[n2]) {
                            if (actionEvent.getActionCommand() != "") {
                                if (!Cubie.settings.lockViewer) {
                                    if (this.seqPos >= 0 && this.generator != null) {
                                        this.generator.doSym(n2);
                                        this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
                                    }
                                    Cubie.settings.cubePos.doSym(n2, actionEvent.getActionCommand() == "c");
                                    this.updateStatus(true);
                                    this.cubeViewers[this.viewer].repaint();
                                }
                            }
                            else {
                                if (!this.symButton[n2].pressed) {
                                    this.symType |= 1 << n2;
                                    final SymButton symButton2 = this.symButton[n2];
                                    symButton2.pressed = true;
                                    symButton2.repaint();
                                }
                                else {
                                    this.symType &= ~(1 << n2);
                                    final SymButton symButton3 = this.symButton[n2];
                                    symButton3.pressed = false;
                                    symButton3.repaint();
                                }
                                final SymButton symAllImage2 = this.symAllImage;
                                symAllImage2.type = symAllImage2.checkSym(this.symType);
                                symAllImage2.repaint();
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
    
    void stoppedSolving() {
        Cubie.settings.lockViewer = false;
        this.textBox.setEnabled(true);
        this.mixBut.setEnabled(true);
        this.resetBut.setEnabled(true);
        this.superBox.setEnabled(true);
        this.playBut.setEnabled(true);
        this.revBut.setEnabled(true);
        this.stepBut.setEnabled(true);
        this.backBut.setEnabled(true);
        this.endBut.setEnabled(true);
        this.beginBut.setEnabled(true);
    }
    
    void startSolving() {
        Cubie.settings.lockViewer = true;
        this.textBox.setEnabled(false);
        this.mixBut.setEnabled(false);
        this.resetBut.setEnabled(false);
        this.superBox.setEnabled(false);
        this.playBut.setEnabled(false);
        this.revBut.setEnabled(false);
        this.stepBut.setEnabled(false);
        this.backBut.setEnabled(false);
        this.endBut.setEnabled(false);
        this.beginBut.setEnabled(false);
    }
    
    private void stepForward() {
        if (this.isPlaying) {
            this.stop();
            return;
        }
        if (this.generator != null && this.seqPos < this.generator.len) {
            if (this.seqPos < 0) {
                this.setSequencePosition(0);
            }
            if (this.cubeViewers[this.viewer].showMove(this.generator.moves[this.seqPos], this.generator.amount[this.seqPos])) {
                ++this.seqPos;
            }
            this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
        }
    }
    
    private void stepBackward() {
        if (this.isPlaying) {
            this.stop();
            return;
        }
        if (this.generator != null) {
            if (this.seqPos < 0) {
                this.setSequencePosition(this.generator.len);
            }
            if (this.seqPos > 0) {
                --this.seqPos;
                if (!this.cubeViewers[this.viewer].showMove(this.generator.moves[this.seqPos], 4 - this.generator.amount[this.seqPos])) {
                    ++this.seqPos;
                }
                this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
            }
        }
    }
    
    private void playForward() {
        if (this.isPlaying) {
            this.stop();
            return;
        }
        if (this.generator != null && this.seqPos < this.generator.len) {
            if (this.seqPos < 0) {
                this.setSequencePosition(0);
            }
            this.playFw = true;
            new Thread(this).start();
        }
    }
    
    private void playBackward() {
        if (this.isPlaying) {
            this.stop();
            return;
        }
        if (this.generator != null) {
            if (this.seqPos < 0) {
                this.setSequencePosition(this.generator.len);
            }
            if (this.seqPos > 0) {
                this.playFw = false;
                new Thread(this).start();
            }
        }
    }
    
    private void setSequencePosition(int len) {
        if (this.generator == null) {
            this.textBox.setText("");
            this.seqPos = -1;
            return;
        }
        if (len > this.generator.len) {
            len = this.generator.len;
        }
        if ((this.seqPos = len) >= 0) {
            Cubie.settings.cubePos.doSequence(this.generator, len);
        }
        this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
        this.updateStatus(true);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.superBox) {
            Cubie.settings.superGroup = !Cubie.settings.superGroup;
            this.superBox.setState(Cubie.settings.superGroup);
            this.updateStatus(true);
            this.cubeViewers[this.viewer].repaint();
            return;
        }
        if (source == this.symTwoColBox) {
            this.symTwoCol = !this.symTwoCol;
            this.symTwoColBox.setState(this.symTwoCol);
            return;
        }
        for (int i = 0; i < this.solvers.length; ++i) {
            if (source == this.groupRadioBox[i]) {
                Cubie.settings.group = i;
                this.cubeGroup.setSelectedCheckbox(this.groupRadioBox[Cubie.settings.group]);
                this.updateStatus(false);
                return;
            }
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getSource() == this.textBox) {
            this.textChanged();
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    private void textChanged() {
        if (!Cubie.settings.solving) {
            if (this.generator == null) {
                this.generator = new MoveSequence();
            }
            this.generator.parse(this.textBox.getText(), this.solution);
            this.seqPos = this.generator.len;
            this.textBox.setText(this.generator.toString(this.solution, this.seqPos));
            final CubePosition cubePos = Cubie.settings.cubePos;
            final MoveSequence generator = this.generator;
            cubePos.doSequence(generator, generator.len);
            this.updateStatus(true);
            this.cubeViewers[this.viewer].repaint();
        }
    }
    
    public Cubie() {
        this.cubeViewers = new Viewer[] { new Viewer3D(300, 350, this), new ViewerDiag(300, 350, this), new ViewerBox(300, 350, this), new ViewerFlat(300, 350, this) };
        this.solvers = new Solver[] { new SolverKociemba(this), new SolverSquare(this), new SolverSlice(this), new SolverAntiSlice(this), new SolverTwoGen(this) };
        this.rightPanel = new Panel();
        this.mixBut = new Button("Mix");
        this.resetBut = new Button("Reset");
        this.editBut = new Button("Edit");
        this.solveBut = new Button("Solve");
        this.viewBut = new Button("Change view");
        this.viewResetBut = new Button("Reset view");
        this.boxPanel = new Panel();
        this.gensolBut = new Button("Solution");
        this.textBox = new TextField("", 50);
        this.playBut = new ImButton(1);
        this.revBut = new ImButton(0);
        this.stepBut = new ImButton(5);
        this.backBut = new ImButton(4);
        this.endBut = new ImButton(7);
        this.beginBut = new ImButton(6);
        this.tabPanel = new Panel[] { new Panel(), new Panel() };
        this.groupCheckBox = new Checkbox[this.solvers.length];
        this.superBox = new Checkbox("supergroup");
        this.cubeGroup = new CheckboxGroup();
        this.groupRadioBox = new Checkbox[] { new Checkbox("Normal", this.cubeGroup, true), new Checkbox("Square", this.cubeGroup, false), new Checkbox("Slice", this.cubeGroup, false), new Checkbox("Anti-Slice", this.cubeGroup, false), new Checkbox("2-generator", this.cubeGroup, false) };
        this.symTwoColBox = new Checkbox("Two colors");
        this.symButton = new SymButton[29];
        this.symAllImage = new SymButton(null, 0);
        this.symCurrentImage = new SymButton(null, 0);
        this.symResetBut = new Button("Clear");
        this.colors = new Color[] { new Color(255, 0, 0), new Color(192, 192, 192), new Color(0, 255, 0), new Color(160, 160, 160) };
        this.solution = true;
        this.symTwoCol = false;
        this.playFw = true;
        this.moveInProgress = false;
        this.isPlaying = false;
    }
    
    static {
        Cubie.settings = new Settings();
    }
}
