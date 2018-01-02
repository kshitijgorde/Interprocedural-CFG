// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import java.io.IOException;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JTextArea;
import com.chess.chessboard.settings.BoardShapePanel;
import com.chess.chessboard.settings.PiecesPanel;
import com.chess.chessboard.settings.ModePanel;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.chess.chessboard.Chessboard;
import javax.swing.JDialog;

public class SettingsDialog extends JDialog
{
    private Chessboard chessboard;
    private JPanel applyAndVersionPanel;
    private JButton applyButton;
    private JPanel applyPanel;
    private ModePanel modePanel;
    private PiecesPanel piecesPanel;
    private JPanel shapeAndApplyPanel;
    private BoardShapePanel shapePanel;
    private JTextArea versionTextArea;
    
    public static SettingsDialog createSettingsDialog(final Window parent, final Chessboard chessboard) {
        try {
            JDialog.class.getConstructor(Window.class, Class.forName("java.awt.Dialog$ModalityType"));
            return new SettingsDialog(parent, chessboard);
        }
        catch (Exception ex) {
            return new SettingsDialog(chessboard);
        }
    }
    
    private SettingsDialog(final Window parent, final Chessboard chessboard) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.init(chessboard, parent);
    }
    
    private SettingsDialog(final Chessboard chessboard) {
        super((Frame)null, true);
        this.init(chessboard, null);
    }
    
    private void init(final Chessboard chessboard, final Window parent) {
        this.getContentPane().setBackground(Color.white);
        this.setChessboard(chessboard);
        this.initComponents();
        this.pack();
        this.setSize(200, 300);
        this.setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        this.shapeAndApplyPanel = new JPanel();
        this.shapePanel = new BoardShapePanel();
        this.piecesPanel = new PiecesPanel();
        this.modePanel = new ModePanel();
        this.applyAndVersionPanel = new JPanel();
        this.applyPanel = new JPanel();
        this.applyButton = new JButton();
        this.versionTextArea = new JTextArea();
        this.setTitle("Settings");
        this.setBackground(Color.white);
        this.shapeAndApplyPanel.setOpaque(false);
        this.shapeAndApplyPanel.setLayout(new BorderLayout());
        this.shapePanel.setOpaque(false);
        this.shapeAndApplyPanel.add((Component)this.shapePanel, "North");
        this.piecesPanel.setUsedImageSize("60");
        this.shapeAndApplyPanel.add((Component)this.piecesPanel, "Center");
        this.shapeAndApplyPanel.add((Component)this.modePanel, "South");
        this.getContentPane().add(this.shapeAndApplyPanel, "Center");
        this.applyAndVersionPanel.setOpaque(false);
        this.applyAndVersionPanel.setLayout(new BorderLayout());
        this.applyPanel.setOpaque(false);
        this.applyButton.setText("Apply");
        this.applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                SettingsDialog.this.applyButtonActionPerformed(evt);
            }
        });
        this.applyPanel.add(this.applyButton);
        this.applyAndVersionPanel.add(this.applyPanel, "Center");
        this.getContentPane().add(this.applyAndVersionPanel, "South");
        this.versionTextArea.setBackground(Color.white);
        this.versionTextArea.setColumns(20);
        this.versionTextArea.setEditable(false);
        this.versionTextArea.setFont(new Font("Arial", 0, 13));
        this.versionTextArea.setForeground(new Color(201, 201, 201));
        this.versionTextArea.setLineWrap(true);
        this.versionTextArea.setRows(1);
        this.versionTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.versionTextArea.setOpaque(false);
        this.getContentPane().add(this.versionTextArea, "North");
        this.pack();
    }
    
    private void applyButtonActionPerformed(final ActionEvent evt) {
        this.shapePanel.apply(this.chessboard);
        this.piecesPanel.apply(this.chessboard);
        this.modePanel.apply(this.chessboard);
        this.setVisible(false);
    }
    
    public PiecesPanel getPiecesPanel() {
        return this.piecesPanel;
    }
    
    public BoardShapePanel getShapePanel() {
        return this.shapePanel;
    }
    
    public void apply() {
        this.applyButton.doClick();
    }
    
    public void loadResources(final byte[] resourcesData) throws IOException {
        this.shapePanel.loadResources(resourcesData);
        this.piecesPanel.loadResources(resourcesData);
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public final void setChessboard(final Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    
    public ModePanel getModePanel() {
        return this.modePanel;
    }
    
    void setVersion(final String version) {
        this.versionTextArea.setText(version);
    }
}
