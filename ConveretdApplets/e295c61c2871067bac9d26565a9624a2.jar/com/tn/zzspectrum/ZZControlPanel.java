// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.beans.Beans;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import com.tn.z80.ZXStripesPanel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class ZZControlPanel extends Applet implements ActionListener, ItemListener
{
    private Button ivjButtonLoad;
    private Button ivjButtonOpenUrl;
    private Checkbox ivjChkFullSpeed;
    private Checkbox ivjChkNormalSpeed;
    private Checkbox ivjChkSound;
    private Label ivjLblOpenUrl;
    private Label ivjLblSnap;
    private Label ivjLblSound;
    private Label ivjLblSpeed;
    private TextField ivjTextFieldUrl;
    private ZXStripesPanel ivjPanelUrl;
    private Button ivjButtonSave;
    private Panel ivjPanel1;
    private ZZSpectrumController ivZZController;
    private CheckboxGroup ivjChkGroupSpeed;
    private ZXStripesPanel ivjPanelSnapshot;
    private ZXStripesPanel ivjPanelSound;
    private ZXStripesPanel ivjPanelSpeed;
    private Label ivjLblErrorText1;
    private Label ivjLblJoystick;
    private ZXStripesPanel ivjPanelJoystick;
    private Choice ivjChoiceJoystick;
    private Choice ivjChoiceVideo;
    private Label ivjLblVideo;
    private ZXStripesPanel ivjPanelVideo;
    
    public ZZControlPanel() {
        this.ivjButtonLoad = null;
        this.ivjButtonOpenUrl = null;
        this.ivjChkFullSpeed = null;
        this.ivjChkNormalSpeed = null;
        this.ivjChkSound = null;
        this.ivjLblOpenUrl = null;
        this.ivjLblSnap = null;
        this.ivjLblSound = null;
        this.ivjLblSpeed = null;
        this.ivjTextFieldUrl = null;
        this.ivjPanelUrl = null;
        this.ivjButtonSave = null;
        this.ivjPanel1 = null;
        this.ivjChkGroupSpeed = null;
        this.ivjPanelSnapshot = null;
        this.ivjPanelSound = null;
        this.ivjPanelSpeed = null;
        this.ivjLblErrorText1 = null;
        this.ivjLblJoystick = null;
        this.ivjPanelJoystick = null;
        this.ivjChoiceJoystick = null;
        this.ivjChoiceVideo = null;
        this.ivjLblVideo = null;
        this.ivjPanelVideo = null;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.getButtonSave()) {
            this.connEtoC1();
        }
        if (e.getSource() == this.getButtonLoad()) {
            this.connEtoC2();
        }
        if (e.getSource() == this.getButtonOpenUrl()) {
            this.connEtoC3();
        }
        if (e.getSource() == this.getTextFieldUrl()) {
            this.connEtoC7();
        }
    }
    
    private void buttonLoad() throws IOException {
        this.ivZZController.snapshotLoadInternal();
        this.refresh();
    }
    
    private void buttonOpenUrl() {
        try {
            this.ivZZController.loadSnap(this.getTextFieldUrl().getText(), false);
            this.refresh();
        }
        catch (FileNotFoundException fnfe) {
            this.getLblErrorText1().setText("Snapshot file not found. " + fnfe.getMessage());
        }
        catch (IOException ioe) {
            this.getLblErrorText1().setText("IOException. " + ioe.getMessage());
        }
        catch (Exception e) {
            this.getLblErrorText1().setText(e.getClass().getName());
        }
    }
    
    private void buttonSave() {
        this.ivZZController.snapshotSaveInternal();
        this.refresh();
    }
    
    private void chkFullSpeed(final ItemEvent itemEvent) {
        this.ivZZController.setModeTrueSpeed(false);
        this.refresh();
    }
    
    private void chkNormalSpeed(final ItemEvent itemEvent) {
        this.ivZZController.setModeTrueSpeed(true);
        this.refresh();
    }
    
    private void chkSound(final ItemEvent itemEvent) {
        this.ivZZController.setSoundEnabled(this.getChkSound().getState());
        this.refresh();
    }
    
    private void choiceJoystick(final ItemEvent itemEvent) {
        this.ivZZController.setJoystick1(this.getChoiceJoystick().getSelectedItem());
        this.refresh();
    }
    
    private void choiceVideo(final ItemEvent itemEvent) {
        this.ivZZController.setPalette(this.getChoiceVideo().getSelectedItem());
        this.refresh();
    }
    
    private void connectToEmulator() {
        for (int i = 0; i < 10; ++i) {
            final Enumeration e = this.getAppletContext().getApplets();
            while (e.hasMoreElements()) {
                final Object o = e.nextElement();
                if (o instanceof ZZSpectrumApplet && ((ZZSpectrumApplet)o).setZZControlPanel(this)) {
                    this.ivZZController = ((ZZSpectrumApplet)o).getController();
                    System.out.println("Control panel attached");
                    this.refresh();
                    return;
                }
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
        System.out.println("Control panel could not be attached!");
    }
    
    private void connEtoC1() {
        try {
            this.buttonSave();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC2() {
        try {
            this.buttonLoad();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC3() {
        try {
            this.buttonOpenUrl();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC4(final ItemEvent arg1) {
        try {
            this.chkNormalSpeed(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC5(final ItemEvent arg1) {
        try {
            this.chkFullSpeed(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC6(final ItemEvent arg1) {
        try {
            this.chkSound(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC7() {
        try {
            this.textFieldUrl();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC8(final ItemEvent arg1) {
        try {
            this.choiceJoystick(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC9(final ItemEvent arg1) {
        try {
            this.choiceVideo(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    @Override
    public void doLayout() {
        final int xsize = this.getSize().width;
        final int ysize = this.getSize().height;
        final int dx = (xsize > ysize) ? 1 : 0;
        final int dy = 1 - dx;
        this.getPanel1().removeAll();
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(1, 1, 1, 1);
        this.getPanel1().add(this.getPanelSnapshot(), constraints);
        final GridBagConstraints gridBagConstraints = constraints;
        gridBagConstraints.gridx += dx;
        final GridBagConstraints gridBagConstraints2 = constraints;
        gridBagConstraints2.gridy += dy;
        this.getPanel1().add(this.getPanelSpeed(), constraints);
        final GridBagConstraints gridBagConstraints3 = constraints;
        gridBagConstraints3.gridx += dx;
        final GridBagConstraints gridBagConstraints4 = constraints;
        gridBagConstraints4.gridy += dy;
        this.getPanel1().add(this.getPanelVideo(), constraints);
        final GridBagConstraints gridBagConstraints5 = constraints;
        gridBagConstraints5.gridx += dx;
        final GridBagConstraints gridBagConstraints6 = constraints;
        gridBagConstraints6.gridy += dy;
        this.getPanel1().add(this.getPanelSound(), constraints);
        final GridBagConstraints gridBagConstraints7 = constraints;
        gridBagConstraints7.gridx += dx;
        final GridBagConstraints gridBagConstraints8 = constraints;
        gridBagConstraints8.gridy += dy;
        this.getPanel1().add(this.getPanelJoystick(), constraints);
        final GridBagConstraints gridBagConstraints9 = constraints;
        gridBagConstraints9.gridx += dx;
        final GridBagConstraints gridBagConstraints10 = constraints;
        gridBagConstraints10.gridy += dy;
        constraints.weightx = 2.0;
        this.getPanel1().add(this.getPanelUrl(), constraints);
        final GridBagConstraints gridBagConstraints11 = constraints;
        gridBagConstraints11.gridx += dx;
        final GridBagConstraints gridBagConstraints12 = constraints;
        gridBagConstraints12.gridy += dy;
        super.doLayout();
    }
    
    @Override
    public String getAppletInfo() {
        return "ZZControlPanel";
    }
    
    private static void getBuilderData() {
    }
    
    private Button getButtonLoad() {
        if (this.ivjButtonLoad == null) {
            try {
                (this.ivjButtonLoad = new Button()).setName("ButtonLoad");
                this.ivjButtonLoad.setLabel("load (F2)");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjButtonLoad;
    }
    
    private Button getButtonOpenUrl() {
        if (this.ivjButtonOpenUrl == null) {
            try {
                (this.ivjButtonOpenUrl = new Button()).setName("ButtonOpenUrl");
                this.ivjButtonOpenUrl.setLabel("Open");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjButtonOpenUrl;
    }
    
    private Button getButtonSave() {
        if (this.ivjButtonSave == null) {
            try {
                (this.ivjButtonSave = new Button()).setName("ButtonSave");
                this.ivjButtonSave.setLabel("save (F1)");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjButtonSave;
    }
    
    private Checkbox getChkFullSpeed() {
        if (this.ivjChkFullSpeed == null) {
            try {
                (this.ivjChkFullSpeed = new Checkbox()).setName("ChkFullSpeed");
                this.ivjChkFullSpeed.setCheckboxGroup(this.getChkGroupSpeed());
                this.ivjChkFullSpeed.setForeground(Color.white);
                this.ivjChkFullSpeed.setLabel("full");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChkFullSpeed;
    }
    
    private CheckboxGroup getChkGroupSpeed() {
        if (this.ivjChkGroupSpeed == null) {
            try {
                this.ivjChkGroupSpeed = new CheckboxGroup();
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChkGroupSpeed;
    }
    
    private Checkbox getChkNormalSpeed() {
        if (this.ivjChkNormalSpeed == null) {
            try {
                (this.ivjChkNormalSpeed = new Checkbox()).setName("ChkNormalSpeed");
                this.ivjChkNormalSpeed.setCheckboxGroup(this.getChkGroupSpeed());
                this.ivjChkNormalSpeed.setForeground(Color.white);
                this.ivjChkNormalSpeed.setLabel("normal");
                this.ivjChkNormalSpeed.setState(true);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChkNormalSpeed;
    }
    
    private Checkbox getChkSound() {
        if (this.ivjChkSound == null) {
            try {
                (this.ivjChkSound = new Checkbox()).setName("ChkSound");
                this.ivjChkSound.setForeground(Color.white);
                this.ivjChkSound.setLabel("enabled");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChkSound;
    }
    
    private Choice getChoiceJoystick() {
        if (this.ivjChoiceJoystick == null) {
            try {
                (this.ivjChoiceJoystick = new Choice()).setName("ChoiceJoystick");
                this.ivjChoiceJoystick.setFont(new Font("Arial", 1, 12));
                this.ivjChoiceJoystick.setBackground(Color.gray);
                this.ivjChoiceJoystick.add(" ");
                this.ivjChoiceJoystick.add("KEMPSTON");
                this.ivjChoiceJoystick.add("CURSOR");
                this.ivjChoiceJoystick.add("SINCLAIR1");
                this.ivjChoiceJoystick.add("SINCLAIR2");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChoiceJoystick;
    }
    
    private Choice getChoiceVideo() {
        if (this.ivjChoiceVideo == null) {
            try {
                (this.ivjChoiceVideo = new Choice()).setName("ChoiceVideo");
                this.ivjChoiceVideo.setFont(new Font("Arial", 1, 12));
                this.ivjChoiceVideo.setBackground(Color.gray);
                this.ivjChoiceVideo.add("B/W");
                this.ivjChoiceVideo.add("COLOUR");
                this.ivjChoiceVideo.add("ISSUE2");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjChoiceVideo;
    }
    
    private Label getLblErrorText1() {
        if (this.ivjLblErrorText1 == null) {
            try {
                (this.ivjLblErrorText1 = new Label()).setName("LblErrorText1");
                this.ivjLblErrorText1.setFont(new Font("dialog", 0, 10));
                this.ivjLblErrorText1.setText("");
                this.ivjLblErrorText1.setForeground(Color.red);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblErrorText1;
    }
    
    private Label getLblJoystick() {
        if (this.ivjLblJoystick == null) {
            try {
                (this.ivjLblJoystick = new Label()).setName("LblJoystick");
                this.ivjLblJoystick.setFont(new Font("Arial", 1, 12));
                this.ivjLblJoystick.setAlignment(1);
                this.ivjLblJoystick.setText("Joystick");
                this.ivjLblJoystick.setForeground(new Color(170, 255, 255));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblJoystick;
    }
    
    private Label getLblOpenUrl() {
        if (this.ivjLblOpenUrl == null) {
            try {
                (this.ivjLblOpenUrl = new Label()).setName("LblOpenUrl");
                this.ivjLblOpenUrl.setFont(new Font("Arial", 1, 12));
                this.ivjLblOpenUrl.setAlignment(1);
                this.ivjLblOpenUrl.setText("Open snapshot URL");
                this.ivjLblOpenUrl.setForeground(new Color(255, 255, 171));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblOpenUrl;
    }
    
    private Label getLblSnap() {
        if (this.ivjLblSnap == null) {
            try {
                (this.ivjLblSnap = new Label()).setName("LblSnap");
                this.ivjLblSnap.setFont(new Font("Arial", 1, 12));
                this.ivjLblSnap.setAlignment(1);
                this.ivjLblSnap.setText("Snapshot");
                this.ivjLblSnap.setForeground(new Color(255, 170, 170));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblSnap;
    }
    
    private Label getLblSound() {
        if (this.ivjLblSound == null) {
            try {
                (this.ivjLblSound = new Label()).setName("LblSound");
                this.ivjLblSound.setFont(new Font("Arial", 1, 12));
                this.ivjLblSound.setAlignment(1);
                this.ivjLblSound.setText("Sound");
                this.ivjLblSound.setForeground(new Color(170, 255, 170));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblSound;
    }
    
    private Label getLblSpeed() {
        if (this.ivjLblSpeed == null) {
            try {
                (this.ivjLblSpeed = new Label()).setName("LblSpeed");
                this.ivjLblSpeed.setFont(new Font("Arial", 1, 12));
                this.ivjLblSpeed.setAlignment(1);
                this.ivjLblSpeed.setText("Speed");
                this.ivjLblSpeed.setForeground(new Color(255, 170, 255));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblSpeed;
    }
    
    private Label getLblVideo() {
        if (this.ivjLblVideo == null) {
            try {
                (this.ivjLblVideo = new Label()).setName("LblVideo");
                this.ivjLblVideo.setFont(new Font("Arial", 1, 12));
                this.ivjLblVideo.setAlignment(1);
                this.ivjLblVideo.setText("Video");
                this.ivjLblVideo.setForeground(new Color(170, 170, 255));
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLblVideo;
    }
    
    private Panel getPanel1() {
        if (this.ivjPanel1 == null) {
            try {
                (this.ivjPanel1 = new Panel()).setName("Panel1");
                this.ivjPanel1.setLayout(new GridBagLayout());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanel1;
    }
    
    private ZXStripesPanel getPanelJoystick() {
        if (this.ivjPanelJoystick == null) {
            try {
                (this.ivjPanelJoystick = new ZXStripesPanel()).setName("PanelJoystick");
                this.ivjPanelJoystick.setLayout(new GridBagLayout());
                this.ivjPanelJoystick.setBackground(Color.darkGray);
                this.ivjPanelJoystick.setBounds(550, 59, 138, 86);
                this.ivjPanelJoystick.setStripesVisible(false);
                final GridBagConstraints constraintsLblJoystick = new GridBagConstraints();
                constraintsLblJoystick.gridx = 0;
                constraintsLblJoystick.gridy = 0;
                constraintsLblJoystick.fill = 2;
                constraintsLblJoystick.insets = new Insets(4, 4, 1, 4);
                this.getPanelJoystick().add(this.getLblJoystick(), constraintsLblJoystick);
                final GridBagConstraints constraintsChoiceJoystick = new GridBagConstraints();
                constraintsChoiceJoystick.gridx = 0;
                constraintsChoiceJoystick.gridy = 1;
                constraintsChoiceJoystick.fill = 2;
                constraintsChoiceJoystick.anchor = 11;
                constraintsChoiceJoystick.weighty = 1.0;
                constraintsChoiceJoystick.insets = new Insets(4, 4, 4, 4);
                this.getPanelJoystick().add(this.getChoiceJoystick(), constraintsChoiceJoystick);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelJoystick;
    }
    
    private ZXStripesPanel getPanelSnapshot() {
        if (this.ivjPanelSnapshot == null) {
            try {
                (this.ivjPanelSnapshot = new ZXStripesPanel()).setName("PanelSnapshot");
                this.ivjPanelSnapshot.setLayout(new GridBagLayout());
                this.ivjPanelSnapshot.setStripesVisible(false);
                this.ivjPanelSnapshot.setBounds(188, 21, 103, 97);
                this.ivjPanelSnapshot.setBackground(Color.darkGray);
                final GridBagConstraints constraintsLblSnap = new GridBagConstraints();
                constraintsLblSnap.gridx = 0;
                constraintsLblSnap.gridy = 0;
                constraintsLblSnap.fill = 2;
                constraintsLblSnap.insets = new Insets(4, 4, 1, 4);
                this.getPanelSnapshot().add(this.getLblSnap(), constraintsLblSnap);
                final GridBagConstraints constraintsButtonSave = new GridBagConstraints();
                constraintsButtonSave.gridx = 0;
                constraintsButtonSave.gridy = 1;
                constraintsButtonSave.fill = 2;
                constraintsButtonSave.insets = new Insets(4, 1, 4, 1);
                this.getPanelSnapshot().add(this.getButtonSave(), constraintsButtonSave);
                final GridBagConstraints constraintsButtonLoad = new GridBagConstraints();
                constraintsButtonLoad.gridx = 0;
                constraintsButtonLoad.gridy = 2;
                constraintsButtonLoad.fill = 2;
                constraintsButtonLoad.anchor = 11;
                constraintsButtonLoad.weighty = 1.0;
                constraintsButtonLoad.insets = new Insets(1, 1, 1, 1);
                this.getPanelSnapshot().add(this.getButtonLoad(), constraintsButtonLoad);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelSnapshot;
    }
    
    private ZXStripesPanel getPanelSound() {
        if (this.ivjPanelSound == null) {
            try {
                (this.ivjPanelSound = new ZXStripesPanel()).setName("PanelSound");
                this.ivjPanelSound.setLayout(new GridBagLayout());
                this.ivjPanelSound.setBackground(Color.darkGray);
                this.ivjPanelSound.setBounds(187, 231, 105, 79);
                this.ivjPanelSound.setStripesVisible(false);
                final GridBagConstraints constraintsLblSound = new GridBagConstraints();
                constraintsLblSound.gridx = 0;
                constraintsLblSound.gridy = 0;
                constraintsLblSound.fill = 2;
                constraintsLblSound.insets = new Insets(4, 4, 1, 4);
                this.getPanelSound().add(this.getLblSound(), constraintsLblSound);
                final GridBagConstraints constraintsChkSound = new GridBagConstraints();
                constraintsChkSound.gridx = 0;
                constraintsChkSound.gridy = 1;
                constraintsChkSound.fill = 2;
                constraintsChkSound.anchor = 11;
                constraintsChkSound.weighty = 1.0;
                constraintsChkSound.insets = new Insets(1, 1, 1, 1);
                this.getPanelSound().add(this.getChkSound(), constraintsChkSound);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelSound;
    }
    
    private ZXStripesPanel getPanelSpeed() {
        if (this.ivjPanelSpeed == null) {
            try {
                (this.ivjPanelSpeed = new ZXStripesPanel()).setName("PanelSpeed");
                this.ivjPanelSpeed.setLayout(new GridBagLayout());
                this.ivjPanelSpeed.setBackground(Color.darkGray);
                this.ivjPanelSpeed.setStripesVisible(false);
                this.ivjPanelSpeed.setBounds(188, 125, 103, 93);
                final GridBagConstraints constraintsLblSpeed = new GridBagConstraints();
                constraintsLblSpeed.gridx = 0;
                constraintsLblSpeed.gridy = 0;
                constraintsLblSpeed.fill = 2;
                constraintsLblSpeed.insets = new Insets(4, 4, 1, 4);
                this.getPanelSpeed().add(this.getLblSpeed(), constraintsLblSpeed);
                final GridBagConstraints constraintsChkNormalSpeed = new GridBagConstraints();
                constraintsChkNormalSpeed.gridx = 0;
                constraintsChkNormalSpeed.gridy = 1;
                constraintsChkNormalSpeed.fill = 2;
                constraintsChkNormalSpeed.insets = new Insets(1, 1, 1, 1);
                this.getPanelSpeed().add(this.getChkNormalSpeed(), constraintsChkNormalSpeed);
                final GridBagConstraints constraintsChkFullSpeed = new GridBagConstraints();
                constraintsChkFullSpeed.gridx = 0;
                constraintsChkFullSpeed.gridy = 2;
                constraintsChkFullSpeed.fill = 2;
                constraintsChkFullSpeed.anchor = 11;
                constraintsChkFullSpeed.weighty = 1.0;
                constraintsChkFullSpeed.insets = new Insets(1, 1, 1, 1);
                this.getPanelSpeed().add(this.getChkFullSpeed(), constraintsChkFullSpeed);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelSpeed;
    }
    
    private ZXStripesPanel getPanelUrl() {
        if (this.ivjPanelUrl == null) {
            try {
                (this.ivjPanelUrl = new ZXStripesPanel()).setName("PanelUrl");
                this.ivjPanelUrl.setLayout(new GridBagLayout());
                this.ivjPanelUrl.setBackground(Color.darkGray);
                this.ivjPanelUrl.setBounds(191, 327, 336, 101);
                this.ivjPanelUrl.setStripesVisible(true);
                final GridBagConstraints constraintsTextFieldUrl = new GridBagConstraints();
                constraintsTextFieldUrl.gridx = 1;
                constraintsTextFieldUrl.gridy = 1;
                constraintsTextFieldUrl.fill = 2;
                constraintsTextFieldUrl.weightx = 1.0;
                constraintsTextFieldUrl.insets = new Insets(1, 4, 1, 1);
                this.getPanelUrl().add(this.getTextFieldUrl(), constraintsTextFieldUrl);
                final GridBagConstraints constraintsButtonOpenUrl = new GridBagConstraints();
                constraintsButtonOpenUrl.gridx = 2;
                constraintsButtonOpenUrl.gridy = 1;
                constraintsButtonOpenUrl.insets = new Insets(1, 4, 1, 4);
                this.getPanelUrl().add(this.getButtonOpenUrl(), constraintsButtonOpenUrl);
                final GridBagConstraints constraintsLblOpenUrl = new GridBagConstraints();
                constraintsLblOpenUrl.gridx = 0;
                constraintsLblOpenUrl.gridy = 0;
                constraintsLblOpenUrl.gridwidth = 3;
                constraintsLblOpenUrl.fill = 2;
                constraintsLblOpenUrl.insets = new Insets(4, 4, 4, 4);
                this.getPanelUrl().add(this.getLblOpenUrl(), constraintsLblOpenUrl);
                final GridBagConstraints constraintsLblErrorText1 = new GridBagConstraints();
                constraintsLblErrorText1.gridx = 0;
                constraintsLblErrorText1.gridy = 2;
                constraintsLblErrorText1.gridwidth = 2;
                constraintsLblErrorText1.fill = 2;
                constraintsLblErrorText1.anchor = 11;
                constraintsLblErrorText1.weighty = 1.0;
                constraintsLblErrorText1.insets = new Insets(4, 4, 0, 4);
                this.getPanelUrl().add(this.getLblErrorText1(), constraintsLblErrorText1);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelUrl;
    }
    
    private ZXStripesPanel getPanelVideo() {
        if (this.ivjPanelVideo == null) {
            try {
                (this.ivjPanelVideo = new ZXStripesPanel()).setName("PanelVideo");
                this.ivjPanelVideo.setLayout(new GridBagLayout());
                this.ivjPanelVideo.setBackground(Color.darkGray);
                this.ivjPanelVideo.setBounds(666, 149, 99, 70);
                this.ivjPanelVideo.setStripesVisible(false);
                final GridBagConstraints constraintsLblVideo = new GridBagConstraints();
                constraintsLblVideo.gridx = 0;
                constraintsLblVideo.gridy = 0;
                constraintsLblVideo.fill = 2;
                constraintsLblVideo.insets = new Insets(4, 4, 1, 4);
                this.getPanelVideo().add(this.getLblVideo(), constraintsLblVideo);
                final GridBagConstraints constraintsChoiceVideo = new GridBagConstraints();
                constraintsChoiceVideo.gridx = 0;
                constraintsChoiceVideo.gridy = 1;
                constraintsChoiceVideo.fill = 2;
                constraintsChoiceVideo.anchor = 11;
                constraintsChoiceVideo.weighty = 1.0;
                constraintsChoiceVideo.insets = new Insets(4, 4, 4, 4);
                this.getPanelVideo().add(this.getChoiceVideo(), constraintsChoiceVideo);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanelVideo;
    }
    
    private TextField getTextFieldUrl() {
        if (this.ivjTextFieldUrl == null) {
            try {
                (this.ivjTextFieldUrl = new TextField()).setName("TextFieldUrl");
                this.ivjTextFieldUrl.setFont(new Font("dialog", 0, 12));
                this.ivjTextFieldUrl.setBackground(new Color(85, 85, 85));
                this.ivjTextFieldUrl.setColumns(10);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTextFieldUrl;
    }
    
    private void handleException(final Throwable exception) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        exception.printStackTrace(System.out);
    }
    
    @Override
    public void init() {
        try {
            super.init();
            this.setName("ZZControlPanel");
            this.setLayout(new GridBagLayout());
            this.setBackground(Color.black);
            final GridBagConstraints constraintsPanel1 = new GridBagConstraints();
            constraintsPanel1.gridx = 0;
            constraintsPanel1.gridy = 0;
            constraintsPanel1.gridwidth = 5;
            constraintsPanel1.gridheight = 4;
            constraintsPanel1.fill = 1;
            constraintsPanel1.weightx = 1.0;
            constraintsPanel1.weighty = 1.0;
            this.add(this.getPanel1(), constraintsPanel1);
            this.initConnections();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void initConnections() throws Exception {
        this.getButtonSave().addActionListener(this);
        this.getButtonLoad().addActionListener(this);
        this.getButtonOpenUrl().addActionListener(this);
        this.getChkNormalSpeed().addItemListener(this);
        this.getChkFullSpeed().addItemListener(this);
        this.getChkSound().addItemListener(this);
        this.getTextFieldUrl().addActionListener(this);
        this.getChoiceJoystick().addItemListener(this);
        this.getChoiceVideo().addItemListener(this);
    }
    
    @Override
    public void itemStateChanged(final ItemEvent e) {
        if (e.getSource() == this.getChkNormalSpeed()) {
            this.connEtoC4(e);
        }
        if (e.getSource() == this.getChkFullSpeed()) {
            this.connEtoC5(e);
        }
        if (e.getSource() == this.getChkSound()) {
            this.connEtoC6(e);
        }
        if (e.getSource() == this.getChoiceJoystick()) {
            this.connEtoC8(e);
        }
        if (e.getSource() == this.getChoiceVideo()) {
            this.connEtoC9(e);
        }
    }
    
    public static void main(final String[] args) {
        try {
            final Frame frame = new Frame();
            final Class iiCls = Class.forName("com.tn.z80.ZZControlPanel");
            final ClassLoader iiClsLoader = iiCls.getClassLoader();
            final ZZControlPanel aZZControlPanel = (ZZControlPanel)Beans.instantiate(iiClsLoader, "com.tn.z80.ZZControlPanel");
            frame.add("Center", aZZControlPanel);
            frame.setSize(aZZControlPanel.getSize());
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.show();
            final Insets insets = frame.getInsets();
            frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
            frame.setVisible(true);
        }
        catch (Throwable exception) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
            exception.printStackTrace(System.out);
        }
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
    }
    
    public void refresh() {
        if (this.ivZZController.isInitialized()) {
            if (this.ivZZController.getModeTrueSpeed()) {
                this.getChkNormalSpeed().setState(true);
            }
            else {
                this.getChkFullSpeed().setState(true);
            }
            this.getChkSound().setEnabled(this.ivZZController.getSoundSupported());
            this.getChkSound().setState(this.ivZZController.getSoundEnabled());
            this.getChoiceVideo().select(this.ivZZController.getPaletteId());
            String joystickId = this.ivZZController.getJoystick1Id();
            if (joystickId == null) {
                joystickId = " ";
            }
            this.getChoiceJoystick().select(joystickId);
            this.getLblErrorText1().setText("");
            this.ivZZController.setFocus();
        }
    }
    
    @Override
    public void start() {
        this.connectToEmulator();
    }
    
    public void textFieldUrl() {
        this.buttonOpenUrl();
    }
}
