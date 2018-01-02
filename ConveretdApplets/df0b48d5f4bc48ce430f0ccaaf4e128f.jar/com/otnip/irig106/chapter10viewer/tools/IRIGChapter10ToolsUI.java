// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer.tools;

import com.otnip.irig106.chapter10.Packet;
import com.otnip.tools.files.FileTools;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Filter;
import com.otnip.tools.progress.Progress;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import com.otnip.tools.files.FileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class IRIGChapter10ToolsUI extends JPanel
{
    private static final long serialVersionUID = 0L;
    private JTextField channelsTextField;
    private JButton generateNewCHapter10FileButton;
    private JPanel generateNewFileUI;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JTextField outputFileTextField;
    private JButton saveTMATSButton;
    private JButton selectOutputFileButton;
    private JButton selectTMATSFileButton;
    private JComboBox tmatsSourceSelectionBox;
    private JTextField tmatsSourceTextField;
    private File chapter10File;
    
    public IRIGChapter10ToolsUI(final File chapter10File) {
        this.chapter10File = chapter10File;
        this.initComponents();
    }
    
    private void initComponents() {
        this.generateNewFileUI = new JPanel();
        this.jPanel3 = new JPanel();
        this.selectOutputFileButton = new JButton();
        this.outputFileTextField = new JTextField();
        this.jPanel4 = new JPanel();
        this.tmatsSourceSelectionBox = new JComboBox();
        this.jPanel6 = new JPanel();
        this.selectTMATSFileButton = new JButton();
        this.tmatsSourceTextField = new JTextField();
        this.jPanel5 = new JPanel();
        this.channelsTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.jPanel1 = new JPanel();
        this.saveTMATSButton = new JButton();
        this.generateNewCHapter10FileButton = new JButton();
        this.generateNewFileUI.setLayout(new GridBagLayout());
        this.jPanel3.setBorder(BorderFactory.createTitledBorder("Output Options"));
        this.jPanel3.setToolTipText("Set The Desired Output File");
        this.jPanel3.setLayout(new BorderLayout());
        this.selectOutputFileButton.setText("Set File");
        this.selectOutputFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIGChapter10ToolsUI.this.selectOutputFileButtonActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.selectOutputFileButton, "West");
        this.outputFileTextField.setColumns(80);
        this.outputFileTextField.setEditable(false);
        this.jPanel3.add(this.outputFileTextField, "Center");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.generateNewFileUI.add(this.jPanel3, gridBagConstraints);
        this.jPanel4.setBorder(BorderFactory.createTitledBorder("TMATS Options"));
        this.jPanel4.setToolTipText("Set Desired Source For TMATS");
        this.jPanel4.setLayout(new BorderLayout());
        this.tmatsSourceSelectionBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Source File", "External File" }));
        this.tmatsSourceSelectionBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                IRIGChapter10ToolsUI.this.tmatsSourceSelectionBoxItemStateChanged(evt);
            }
        });
        this.jPanel4.add(this.tmatsSourceSelectionBox, "West");
        this.jPanel6.setLayout(new BorderLayout());
        this.selectTMATSFileButton.setText("Select FIle");
        this.selectTMATSFileButton.setEnabled(false);
        this.selectTMATSFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIGChapter10ToolsUI.this.selectTMATSFileButtonActionPerformed(evt);
            }
        });
        this.jPanel6.add(this.selectTMATSFileButton, "West");
        this.tmatsSourceTextField.setColumns(60);
        this.tmatsSourceTextField.setEditable(false);
        this.jPanel6.add(this.tmatsSourceTextField, "Center");
        this.jPanel4.add(this.jPanel6, "Center");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.generateNewFileUI.add(this.jPanel4, gridBagConstraints);
        this.jPanel5.setBorder(BorderFactory.createTitledBorder("Desired Channels"));
        this.jPanel5.setToolTipText("Comma-Separated List Of Desired Channels (IDs)");
        this.jPanel5.setLayout(new BorderLayout());
        this.channelsTextField.setToolTipText("<html>Comma-Separated List Of Desired Channels (IDs)<br>A Blank Entry Indicates All Channels</html");
        this.channelsTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent evt) {
                IRIGChapter10ToolsUI.this.channelsTextFieldKeyReleased(evt);
            }
        });
        this.jPanel5.add(this.channelsTextField, "Center");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.generateNewFileUI.add(this.jPanel5, gridBagConstraints);
        this.jLabel1.setText("NOTE:  Timing Is Included By Default");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.generateNewFileUI.add(this.jLabel1, gridBagConstraints);
        this.setLayout(new GridBagLayout());
        this.jPanel1.setLayout(new GridBagLayout());
        this.saveTMATSButton.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/save_as.png")));
        this.saveTMATSButton.setText("Save TMATS");
        this.saveTMATSButton.setToolTipText("Save TMATS To A Local File");
        this.saveTMATSButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIGChapter10ToolsUI.this.saveTMATSButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        this.jPanel1.add(this.saveTMATSButton, gridBagConstraints);
        this.generateNewCHapter10FileButton.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/press.png")));
        this.generateNewCHapter10FileButton.setText("Generate New Chapter 10 File");
        this.generateNewCHapter10FileButton.setToolTipText("Set TMATS File, Select Channels/Data Types");
        this.generateNewCHapter10FileButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIGChapter10ToolsUI.this.generateNewCHapter10FileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.jPanel1.add(this.generateNewCHapter10FileButton, gridBagConstraints);
        this.add(this.jPanel1, new GridBagConstraints());
    }
    
    private void saveTMATSButtonActionPerformed(final ActionEvent evt) {
        try {
            final File outputFile = FileChooser.showSaveDialog(this, "Select Output TMATS File", "tmats", "tmats");
            if (outputFile != null) {
                final String tmatsString = IRIGChapter10Tools.getTMATS(this.chapter10File);
                final FileOutputStream fos = new FileOutputStream(outputFile);
                final BufferedOutputStream bos = new BufferedOutputStream(fos);
                final PrintStream ps = new PrintStream(bos);
                ps.print(tmatsString);
                ps.flush();
                bos.flush();
                fos.flush();
                fos.close();
                System.out.println(tmatsString);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void selectOutputFileButtonActionPerformed(final ActionEvent evt) {
        final File file = FileChooser.showSaveDialog(this, "Select Output File", "ch10, c10", "c10");
        if (file != null) {
            this.outputFileTextField.setText(file.getAbsolutePath());
        }
    }
    
    private void channelsTextFieldKeyReleased(final KeyEvent evt) {
        try {
            this.getDesiredChannelIDs();
            this.channelsTextField.setBackground(Color.WHITE);
        }
        catch (Exception e) {
            this.channelsTextField.setBackground(Color.RED);
        }
    }
    
    private void tmatsSourceSelectionBoxItemStateChanged(final ItemEvent evt) {
        final String item = (String)this.tmatsSourceSelectionBox.getSelectedItem();
        this.selectTMATSFileButton.setEnabled(item.equals("External File"));
    }
    
    private void selectTMATSFileButtonActionPerformed(final ActionEvent evt) {
        final File file = FileChooser.showOpenDialog(this, "Select TMATS Source", "tmats, c10, ch10", "tmats");
        if (file != null) {
            this.tmatsSourceTextField.setText(file.getAbsolutePath());
        }
    }
    
    private void generateNewCHapter10FileButtonActionPerformed(final ActionEvent evt) {
        final int result = JOptionPane.showConfirmDialog(this, this.generateNewFileUI, "Generate New Chapter 10 File", 2, -1);
        if (result == 0) {
            try {
                final Progress progress = new Progress("Creating Chapter 10 File", false);
                progress.display(this);
                final IRIGChapter10Filter filter = this.getFilter();
                filter.setProgress(progress);
                final Thread t = new Thread(filter);
                t.setPriority(1);
                t.start();
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error Creating File", 0);
            }
        }
    }
    
    private int[] getDesiredChannelIDs() throws Exception {
        final String text = this.channelsTextField.getText();
        final StringTokenizer str = new StringTokenizer(text.trim(), ", ");
        final ArrayList<Integer> id = new ArrayList<Integer>();
        while (str.hasMoreTokens()) {
            id.add(Integer.parseInt(str.nextToken()));
        }
        final int[] x = new int[id.size()];
        for (int i = 0; i < x.length; ++i) {
            x[i] = id.get(i);
        }
        return x;
    }
    
    public IRIGChapter10Filter getFilter() throws Exception {
        String tmatsString = null;
        if (this.tmatsSourceSelectionBox.getSelectedItem().equals("Source File")) {
            tmatsString = IRIGChapter10Tools.getTMATS(this.chapter10File);
        }
        else {
            final File file = new File(this.tmatsSourceTextField.getText());
            if (FileTools.getExtension(file).equals("tmats")) {
                tmatsString = FileTools.getText(file);
            }
            else {
                tmatsString = IRIGChapter10Tools.getTMATS(file);
            }
        }
        final IRIGChapter10Filter filter = new IRIGChapter10Filter(this.chapter10File, new File(this.outputFileTextField.getText()), tmatsString, Packet.PacketType.values(), this.getDesiredChannelIDs());
        return filter;
    }
}
