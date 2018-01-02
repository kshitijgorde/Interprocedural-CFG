// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

import java.util.regex.Matcher;
import javax.swing.text.Document;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import com.otnip.irig106.chapter9.TMATS;
import javax.swing.JPanel;

public class TMATSTextUI extends JPanel
{
    private static final long serialVersionUID = 0L;
    private TMATS tmats;
    private JTextPane textPane;
    private JScrollPane textScrollPane;
    
    public TMATSTextUI(final TMATS tmats) throws Exception {
        this.initComponents();
        this.tmats = tmats;
        this.updateDisplay();
        this.textPane.setCaretPosition(0);
    }
    
    private void initComponents() {
        this.textScrollPane = new JScrollPane();
        this.textPane = new JTextPane();
        this.setLayout(new BorderLayout());
        this.textPane.setEditable(false);
        this.textScrollPane.setViewportView(this.textPane);
        this.add(this.textScrollPane, "Center");
    }
    
    private void updateDisplay() throws Exception {
        final Pattern commentPattern = Pattern.compile("COMMENT:.*;");
        final String input = this.tmats.rawText;
        final StringTokenizer str = new StringTokenizer(input, "\n");
        while (str.hasMoreTokens()) {
            final String line = str.nextToken();
            final Document doc = this.textPane.getStyledDocument();
            final SimpleAttributeSet set = new SimpleAttributeSet();
            final Matcher matcher = commentPattern.matcher(line);
            if (matcher.find()) {
                StyleConstants.setForeground(set, Color.LIGHT_GRAY);
                StyleConstants.setBold(set, true);
                StyleConstants.setItalic(set, true);
                doc.insertString(doc.getLength(), line + "\n", set);
            }
            else {
                switch (line.charAt(0)) {
                    case 'V': {
                        StyleConstants.setForeground(set, Color.RED);
                        break;
                    }
                }
                final int startIndex = line.indexOf(":");
                if (startIndex != -1) {
                    final String a = line.substring(0, startIndex);
                    final String b = line.substring(startIndex + 1, line.length());
                    StyleConstants.setBold(set, true);
                    StyleConstants.setForeground(set, Color.BLUE);
                    doc.insertString(doc.getLength(), a + ":", set);
                    StyleConstants.setForeground(set, Color.BLACK);
                    doc.insertString(doc.getLength(), b + "\n", set);
                }
                else {
                    doc.insertString(doc.getLength(), line + "\n", set);
                }
            }
        }
    }
}
