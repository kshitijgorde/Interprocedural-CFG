// 
// Decompiled by Procyon v0.5.30
// 

package jstella.debug;

import javax.swing.text.BadLocationException;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HelpTool extends JFrame
{
    private static final long serialVersionUID = -745646729658300886L;
    private static final int MAX_CHARS_PER_CODE_LINE = 80;
    private JButton ButtonClear;
    private JButton ButtonCopyToClipboard;
    private JLabel LabelContent;
    private JLabel LabelTopicName;
    private JMenuItem MIPaste;
    private JPopupMenu PUContent;
    private JPanel PanelCenter;
    private JPanel PanelSouth;
    private JScrollPane SPContent;
    private JTextArea TAContent;
    private JTextField TFTopicName;
    
    public HelpTool() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.PUContent = new JPopupMenu();
        this.MIPaste = new JMenuItem();
        this.PanelCenter = new JPanel();
        this.LabelTopicName = new JLabel();
        this.TFTopicName = new JTextField();
        this.LabelContent = new JLabel();
        this.SPContent = new JScrollPane();
        this.TAContent = new JTextArea();
        this.PanelSouth = new JPanel();
        this.ButtonCopyToClipboard = new JButton();
        this.ButtonClear = new JButton();
        this.MIPaste.setText("Paste");
        this.MIPaste.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                HelpTool.this.MIPasteActionPerformed(evt);
            }
        });
        this.PUContent.add(this.MIPaste);
        this.setDefaultCloseOperation(3);
        this.setTitle("Help Tool");
        this.PanelCenter.setLayout(new GridBagLayout());
        this.LabelTopicName.setText("Topic Name");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelCenter.add(this.LabelTopicName, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelCenter.add(this.TFTopicName, gridBagConstraints);
        this.LabelContent.setText("Topic Content");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 0;
        this.PanelCenter.add(this.LabelContent, gridBagConstraints);
        this.TAContent.setColumns(20);
        this.TAContent.setComponentPopupMenu(this.PUContent);
        this.TAContent.setLineWrap(true);
        this.TAContent.setRows(5);
        this.TAContent.setWrapStyleWord(true);
        this.SPContent.setViewportView(this.TAContent);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 100.0;
        this.PanelCenter.add(this.SPContent, gridBagConstraints);
        this.getContentPane().add(this.PanelCenter, "Center");
        this.ButtonCopyToClipboard.setText("Copy code to clipboard");
        this.ButtonCopyToClipboard.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                HelpTool.this.ButtonCopyToClipboardActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonCopyToClipboard);
        this.ButtonClear.setText("Clear");
        this.ButtonClear.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                HelpTool.this.ButtonClearActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonClear);
        this.getContentPane().add(this.PanelSouth, "South");
        this.pack();
    }
    
    private void MIPasteActionPerformed(final ActionEvent evt) {
        this.TAContent.paste();
    }
    
    private void ButtonCopyToClipboardActionPerformed(final ActionEvent evt) {
        this.copyCodeToClipboard();
    }
    
    private void ButtonClearActionPerformed(final ActionEvent evt) {
        this.TFTopicName.setText("");
        this.TAContent.setText("");
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelpTool().setVisible(true);
            }
        });
    }
    
    private String getNewLineConvertedString(final String aString) {
        final StringBuffer zSB = new StringBuffer();
        for (int zCharCount = aString.length(), i = 0; i < zCharCount; ++i) {
            if (i > 0 && i % 80 == 0) {
                zSB.append("\"\n\t\t + \"");
            }
            final String zChar = "" + aString.charAt(i);
            if (zChar.equals("\n")) {
                zSB.append("\\n");
            }
            else {
                zSB.append(zChar);
            }
        }
        return zSB.toString();
    }
    
    private void copyCodeToClipboard() {
        try {
            final String zTopicName = this.TFTopicName.getText();
            final StringBuffer zSB = new StringBuffer();
            for (int zLineCount = this.TAContent.getLineCount(), i = 0; i < zLineCount; ++i) {
                final int zStart = this.TAContent.getLineStartOffset(i);
                final int zEnd = this.TAContent.getLineEndOffset(i);
                final String zLine = this.TAContent.getText(zStart, zEnd - zStart);
                final String zConvertedLine = this.getNewLineConvertedString(zLine);
                zSB.append("\t\t");
                if (i >= 1) {
                    zSB.append(" + ");
                }
                zSB.append("\"" + zConvertedLine + "\"");
                if (i < zLineCount - 1) {
                    zSB.append("\n");
                }
            }
            final String zFinalString = "myHelpTopicList.add( new JSHelpTopic(\"" + zTopicName + "\", " + "\n" + zSB.toString() + ")); //constructed with HelpTool";
            final StringSelection zContents = new StringSelection(zFinalString);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(zContents, null);
        }
        catch (BadLocationException ex) {}
    }
}
