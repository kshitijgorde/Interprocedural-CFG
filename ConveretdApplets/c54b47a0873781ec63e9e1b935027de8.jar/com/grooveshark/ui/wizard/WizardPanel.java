// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.wizard;

import com.grooveshark.sharklet.Sharklet;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;
import com.grooveshark.ui.component.Button;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class WizardPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = -7429380585259267918L;
    private static final String CONTINUE_BUTTON_TEXT;
    private static final String BACK_BUTTON_TEXT;
    private static final String FINISH_BUTTON_TEXT;
    private static final String STEP_TEXT;
    private static final String UPLOAD_MESSAGE_TEXT;
    private static final Dimension PROGRESSBAR_SIZE;
    private static final ImageIcon CONTINUE_ARROW_ICON;
    private static final ImageIcon CONTINUE_LEFT_ICON;
    private static final ImageIcon CONTINUE_MIDDLE_ICON;
    private static final ImageIcon DISABLED_CONTINUE_LEFT_ICON;
    private static final ImageIcon DISABLED_CONTINUE_MIDDLE_ICON;
    private static final ImageIcon HOVER_CONTINUE_LEFT_ICON;
    private static final ImageIcon HOVER_CONTINUE_MIDDLE_ICON;
    private static final ImageIcon PRESSED_CONTINUE_LEFT_ICON;
    private static final ImageIcon PRESSED_CONTINUE_MIDDLE_ICON;
    private static final ImageIcon BACK_ARROW_ICON;
    private static final ImageIcon BACK_LEFT_ICON;
    private static final ImageIcon BACK_MIDDLE_ICON;
    private static final ImageIcon DISABLED_BACK_LEFT_ICON;
    private static final ImageIcon DISABLED_BACK_MIDDLE_ICON;
    private static final ImageIcon HOVER_BACK_LEFT_ICON;
    private static final ImageIcon HOVER_BACK_MIDDLE_ICON;
    private static final ImageIcon PRESSED_BACK_LEFT_ICON;
    private static final ImageIcon PRESSED_BACK_MIDDLE_ICON;
    private static final ImageIcon PANEL_LOGO;
    private static final Color PANEL_BACKGROUND_COLOR;
    private static final Color STEP_FOREGROUND;
    private static final Border PANEL_BORDER;
    private static final int FIRST_STEP = 1;
    private CardLayout contentLayout;
    private CardLayout progressLayout;
    private JPanel progressMessagePanel;
    private JPanel contentPanel;
    private String progressText;
    private JLabel titleLabel;
    private JLabel stepLabel;
    private JLabel descriptionLabel;
    private JLabel processDescription;
    private Button continueButton;
    private Button backButton;
    private JProgressBar progressBar;
    private List<WizardContentPanel> stepsPanels;
    private List<WizardPanelListener> listeners;
    private int currentStep;
    
    public WizardPanel() {
        this.stepsPanels = new ArrayList<WizardContentPanel>();
        this.listeners = new ArrayList<WizardPanelListener>();
        this.currentStep = 1;
        this.setupUI();
        this.updateWizardPanel();
    }
    
    private void setupUI() {
        final JPanel headerPanel = this.createHeaderPanel();
        this.contentPanel = this.createContentPanel();
        final JPanel buttonPanel = this.createButtonPanel();
        this.setLayout(new BorderLayout(10, 10));
        this.add(headerPanel, "North");
        this.add(this.contentPanel, "Center");
        this.add(buttonPanel, "South");
        this.setBorder(WizardPanel.PANEL_BORDER);
        this.setBackground(WizardPanel.PANEL_BACKGROUND_COLOR);
    }
    
    private JPanel createHeaderPanel() {
        final JPanel topPanel = this.createHeaderTopPanel();
        final JPanel bottomPanel = this.createHeaderBottomPanel();
        final JPanel headerPanel = new JPanel(new BorderLayout(0, 0));
        headerPanel.add(topPanel, "North");
        headerPanel.add(bottomPanel, "West");
        headerPanel.setOpaque(false);
        return headerPanel;
    }
    
    private JPanel createHeaderTopPanel() {
        this.titleLabel = this.createTitleLabel();
        final JLabel logoImage = new JLabel(WizardPanel.PANEL_LOGO);
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(this.titleLabel, "West");
        topPanel.add(logoImage, "East");
        topPanel.setOpaque(false);
        return topPanel;
    }
    
    private JLabel createTitleLabel() {
        final JLabel titleLabel = new JLabel();
        final Font headerFont = titleLabel.getFont().deriveFont(1).deriveFont(18.0f);
        titleLabel.setFont(headerFont);
        return titleLabel;
    }
    
    private JPanel createHeaderBottomPanel() {
        (this.stepLabel = new JLabel()).setForeground(WizardPanel.STEP_FOREGROUND);
        this.descriptionLabel = new JLabel();
        final JPanel bottomPanel = new JPanel();
        final BoxLayout layout = new BoxLayout(bottomPanel, 1);
        bottomPanel.setLayout(layout);
        bottomPanel.add(this.stepLabel);
        bottomPanel.add(Box.createVerticalStrut(15));
        bottomPanel.add(this.descriptionLabel);
        bottomPanel.add(Box.createVerticalStrut(20));
        bottomPanel.setOpaque(false);
        return bottomPanel;
    }
    
    private JPanel createContentPanel() {
        this.contentLayout = new CardLayout();
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(this.contentLayout);
        contentPanel.setOpaque(false);
        return contentPanel;
    }
    
    private JPanel createButtonPanel() {
        final JPanel progressPanel = this.createProgressPanel();
        this.continueButton = this.createContinueButton();
        this.backButton = this.createBackButton();
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(this.backButton, "West");
        buttonPanel.add(progressPanel, "Center");
        buttonPanel.add(this.continueButton, "East");
        buttonPanel.setOpaque(false);
        return buttonPanel;
    }
    
    private Button createContinueButton() {
        final Button continueButton = new Button(WizardPanel.CONTINUE_LEFT_ICON, WizardPanel.CONTINUE_MIDDLE_ICON);
        continueButton.setHoverImages(WizardPanel.HOVER_CONTINUE_LEFT_ICON, WizardPanel.HOVER_CONTINUE_MIDDLE_ICON);
        continueButton.setDisabledImages(WizardPanel.DISABLED_CONTINUE_LEFT_ICON, WizardPanel.DISABLED_CONTINUE_MIDDLE_ICON);
        continueButton.setPressedImages(WizardPanel.PRESSED_CONTINUE_LEFT_ICON, WizardPanel.PRESSED_CONTINUE_MIDDLE_ICON);
        continueButton.setText(WizardPanel.CONTINUE_BUTTON_TEXT);
        continueButton.setIcon(WizardPanel.CONTINUE_ARROW_ICON);
        continueButton.setActionCommand(WizardPanel.CONTINUE_BUTTON_TEXT);
        continueButton.addActionListener(this);
        continueButton.setForeground(Color.WHITE);
        return continueButton;
    }
    
    private Button createBackButton() {
        final Button backButton = new Button(WizardPanel.BACK_LEFT_ICON, WizardPanel.BACK_MIDDLE_ICON);
        backButton.setHoverImages(WizardPanel.HOVER_BACK_LEFT_ICON, WizardPanel.HOVER_BACK_MIDDLE_ICON);
        backButton.setDisabledImages(WizardPanel.DISABLED_BACK_LEFT_ICON, WizardPanel.DISABLED_BACK_MIDDLE_ICON);
        backButton.setPressedImages(WizardPanel.PRESSED_BACK_LEFT_ICON, WizardPanel.PRESSED_BACK_MIDDLE_ICON);
        backButton.setText(WizardPanel.BACK_BUTTON_TEXT);
        backButton.setIcon(WizardPanel.BACK_ARROW_ICON);
        backButton.setActionCommand(WizardPanel.BACK_BUTTON_TEXT);
        backButton.addActionListener(this);
        backButton.setIconOrientation(1);
        return backButton;
    }
    
    private JPanel createProgressPanel() {
        this.progressLayout = new CardLayout();
        (this.progressMessagePanel = new JPanel()).setLayout(this.progressLayout);
        this.progressMessagePanel.setOpaque(false);
        this.progressMessagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        (this.processDescription = new JLabel()).setVisible(false);
        (this.progressBar = new JProgressBar()).setStringPainted(true);
        this.progressBar.setVisible(false);
        final JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.add(this.processDescription, "West");
        progressPanel.add(this.progressBar, "Center");
        progressPanel.setPreferredSize(WizardPanel.PROGRESSBAR_SIZE);
        progressPanel.setOpaque(false);
        final JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setPreferredSize(WizardPanel.PROGRESSBAR_SIZE);
        messagePanel.setOpaque(false);
        final JLabel messageLabel = new JLabel(WizardPanel.UPLOAD_MESSAGE_TEXT);
        messageLabel.setFont(messageLabel.getFont().deriveFont(10.0f));
        messagePanel.add(messageLabel, "Center");
        this.progressMessagePanel.add(progressPanel, "progress");
        this.progressMessagePanel.add(messagePanel, "message");
        return this.progressMessagePanel;
    }
    
    public void addStep(final WizardContentPanel panel) {
        this.stepsPanels.add(panel);
        this.contentPanel.add(panel, "Step" + this.currentStep);
        final boolean isFirstPanel = this.stepsPanels.size() == 1;
        if (isFirstPanel) {
            this.changeStep(1);
        }
        this.updateWizardPanel();
    }
    
    public void setProcessDescription(final String description) {
        this.processDescription.setText(description);
    }
    
    public void setProcessTotal(final int total) {
        this.progressBar.setIndeterminate(false);
        this.progressBar.setMaximum(total);
        this.updateProgressText(0);
    }
    
    private void updateProgressText(final int value) {
        final int total = this.progressBar.getMaximum();
        final String text = String.format(this.progressText + " %s / %s", value, total);
        this.progressBar.setString(text);
    }
    
    public void setProgressText(final String text) {
        this.progressText = text;
    }
    
    public void setProcessValue(final int value) {
        this.progressBar.setValue(value);
        this.updateProgressText(value);
    }
    
    public void processStarted() {
        this.showProgressBar(true);
        this.progressBar.setIndeterminate(true);
        this.progressBar.setString("");
        this.progressBar.setValue(0);
    }
    
    public void processCompleted() {
        this.showProgressBar(false);
        if (this.currentStep == 2) {
            this.showMessage(true);
        }
    }
    
    private void showMessage(final boolean isVisible) {
        System.out.println("show " + isVisible);
        if (isVisible) {
            this.progressLayout.next(this.progressMessagePanel);
        }
        else {
            this.progressLayout.previous(this.progressMessagePanel);
        }
    }
    
    private void showProgressBar(final boolean isVisible) {
        this.processDescription.setVisible(isVisible);
        this.progressBar.setVisible(isVisible);
    }
    
    public void setStepComplete(final boolean isComplete) {
        this.continueButton.setEnabled(isComplete);
    }
    
    public void addWizardListener(final WizardPanelListener listener) {
        this.listeners.add(listener);
    }
    
    private void notifyListeners(final int newStep, final int oldStep) {
        for (final WizardPanelListener l : this.listeners) {
            l.stepChanged(newStep, oldStep);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        if (command.equals(WizardPanel.BACK_BUTTON_TEXT)) {
            this.showPreviousPanel();
        }
        else if (command.equals(WizardPanel.CONTINUE_BUTTON_TEXT)) {
            this.showNextPanel();
        }
        else if (command.equals(WizardPanel.FINISH_BUTTON_TEXT)) {
            Sharklet.redirectToFinishPage();
        }
    }
    
    private void showPreviousPanel() {
        if (this.currentStep == 1) {
            return;
        }
        this.changeStep(this.currentStep - 1);
        this.contentLayout.previous(this.contentPanel);
        this.updateWizardPanel();
    }
    
    private void showNextPanel() {
        if (this.currentStep == this.stepsPanels.size()) {
            return;
        }
        this.changeStep(this.currentStep + 1);
        this.contentLayout.next(this.contentPanel);
        this.updateWizardPanel();
    }
    
    private void changeStep(final int newStep) {
        this.stepsPanels.get(this.currentStep - 1).setParent(null);
        this.stepsPanels.get(newStep - 1).setParent(this);
        this.notifyListeners(newStep, this.currentStep);
        this.currentStep = newStep;
    }
    
    private WizardContentPanel getCurrentPanel() {
        final int currentPanelIndex = this.currentStep - 1;
        return this.stepsPanels.get(currentPanelIndex);
    }
    
    private void updateWizardPanel() {
        if (this.stepsPanels.isEmpty()) {
            return;
        }
        this.updateHeaderPanel();
        this.updateContinueButton();
        this.updateBackButton();
    }
    
    private void updateHeaderPanel() {
        final WizardContentPanel panel = this.getCurrentPanel();
        final String panelTitle = panel.getTitle();
        final String panelDescription = panel.getDescription();
        this.titleLabel.setText(panelTitle);
        this.descriptionLabel.setText(panelDescription);
        final int totalSteps = this.stepsPanels.size();
        final String stepText = String.format(WizardPanel.STEP_TEXT, this.currentStep, totalSteps);
        this.stepLabel.setText(stepText);
    }
    
    private void updateBackButton() {
        final int totalSteps = this.stepsPanels.size();
        final boolean isFirstStep = this.currentStep == 1;
        final boolean isLastStep = this.currentStep == totalSteps;
        this.backButton.setVisible(!isFirstStep && !isLastStep);
    }
    
    private void updateContinueButton() {
        final int totalSteps = this.stepsPanels.size();
        final boolean isLastStep = this.currentStep == totalSteps;
        final WizardContentPanel panel = this.getCurrentPanel();
        String buttonText = WizardPanel.CONTINUE_BUTTON_TEXT;
        if (isLastStep) {
            buttonText = WizardPanel.FINISH_BUTTON_TEXT;
        }
        this.continueButton.setText(buttonText);
        this.continueButton.setActionCommand(buttonText);
        this.continueButton.setEnabled(panel.isReady());
    }
    
    static {
        CONTINUE_BUTTON_TEXT = Sharklet.getText("CONTINUE");
        BACK_BUTTON_TEXT = Sharklet.getText("BACK");
        FINISH_BUTTON_TEXT = Sharklet.getText("FINISH");
        STEP_TEXT = Sharklet.getText("STEP_TEXT");
        UPLOAD_MESSAGE_TEXT = Sharklet.getText("UPLOAD_MESSAGE");
        PROGRESSBAR_SIZE = new Dimension(200, 40);
        CONTINUE_ARROW_ICON = Sharklet.getImage("icon_arrow_large_right_white.png");
        CONTINUE_LEFT_ICON = Sharklet.getImage("btn_blue_up_left.png");
        CONTINUE_MIDDLE_ICON = Sharklet.getImage("btn_blue_up_middle.png");
        DISABLED_CONTINUE_LEFT_ICON = Sharklet.getImage("btn_blue_disabled_left.png");
        DISABLED_CONTINUE_MIDDLE_ICON = Sharklet.getImage("btn_blue_disabled_middle.png");
        HOVER_CONTINUE_LEFT_ICON = Sharklet.getImage("btn_blue_over_left.png");
        HOVER_CONTINUE_MIDDLE_ICON = Sharklet.getImage("btn_blue_over_middle.png");
        PRESSED_CONTINUE_LEFT_ICON = Sharklet.getImage("btn_blue_down_left.png");
        PRESSED_CONTINUE_MIDDLE_ICON = Sharklet.getImage("btn_blue_down_middle.png");
        BACK_ARROW_ICON = Sharklet.getImage("icon_arrow_large_left_black.png");
        BACK_LEFT_ICON = Sharklet.getImage("btn_grey_up_left.png");
        BACK_MIDDLE_ICON = Sharklet.getImage("btn_grey_up_middle.png");
        DISABLED_BACK_LEFT_ICON = Sharklet.getImage("btn_grey_disabled_left.png");
        DISABLED_BACK_MIDDLE_ICON = Sharklet.getImage("btn_grey_disabled_middle.png");
        HOVER_BACK_LEFT_ICON = Sharklet.getImage("btn_grey_over_left.png");
        HOVER_BACK_MIDDLE_ICON = Sharklet.getImage("btn_grey_over_middle.png");
        PRESSED_BACK_LEFT_ICON = Sharklet.getImage("btn_grey_down_left.png");
        PRESSED_BACK_MIDDLE_ICON = Sharklet.getImage("btn_grey_down_middle.png");
        PANEL_LOGO = Sharklet.getImage("logo.png");
        PANEL_BACKGROUND_COLOR = Color.decode("#dedede");
        STEP_FOREGROUND = Color.decode("#666666");
        PANEL_BORDER = BorderFactory.createEmptyBorder(15, 50, 15, 50);
    }
}
