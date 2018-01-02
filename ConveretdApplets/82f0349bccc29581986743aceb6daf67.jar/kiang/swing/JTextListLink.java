// 
// Decompiled by Procyon v0.5.30
// 

package kiang.swing;

import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentListener;
import javax.swing.JList;
import javax.swing.JTextField;

public class JTextListLink
{
    private JTextField textField;
    private JList list;
    
    public JTextListLink(final JTextField textField, final JList list, final boolean initiallyMatchListToText) {
        this.textField = textField;
        this.list = list;
        final UpdateListener updateListener = new UpdateListener((UpdateListener)null);
        if (initiallyMatchListToText) {
            updateListener.matchListToText();
        }
        else {
            updateListener.matchTextToList();
        }
        this.textField.getDocument().addDocumentListener(updateListener);
        this.list.addListSelectionListener(updateListener);
    }
    
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final String[] listData = { "america", "aardvark", "abate", "alabaster", "asynchronous", "amen", "alimony", "ack", "acknowledge", "ache", "ape", "antidisestablishmentarianism" };
        final JTextField textField = new JTextField();
        final JList list = new JList((E[])listData);
        final JTextListLink link = new JTextListLink(textField, list, true);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(list, "Center");
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setVisible(true);
    }
    
    private class UpdateListener implements ListSelectionListener, DocumentListener
    {
        private boolean isAdjusting;
        
        private UpdateListener() {
            this.isAdjusting = false;
        }
        
        private synchronized boolean toggleIsAdjustingLock() {
            return this.isAdjusting = !this.isAdjusting;
        }
        
        public void valueChanged(final ListSelectionEvent e) {
            this.matchTextToList();
        }
        
        public void insertUpdate(final DocumentEvent e) {
            this.matchListToText();
        }
        
        public void removeUpdate(final DocumentEvent e) {
            this.matchListToText();
        }
        
        public void changedUpdate(final DocumentEvent e) {
            this.matchListToText();
        }
        
        private int getStringMatchScore(String string1, String string2) {
            final int compareLength = Math.min(string1.length(), string2.length());
            string1 = string1.substring(0, compareLength).toUpperCase();
            string2 = string2.substring(0, compareLength).toUpperCase();
            int characterMatchCount = 0;
            for (int i = 0; i < compareLength && string1.charAt(i) == string2.charAt(i); ++i) {
                ++characterMatchCount;
            }
            return characterMatchCount;
        }
        
        private boolean matchListToText() {
            if (this.toggleIsAdjustingLock()) {
                final String text = JTextListLink.this.textField.getText();
                final ListModel model = JTextListLink.this.list.getModel();
                final int modelSize = model.getSize();
                if (modelSize > 0) {
                    int bestMatchCount = this.getStringMatchScore(text, model.getElementAt(0).toString());
                    int selectedIndex = 0;
                    for (int i = 1; i < modelSize; ++i) {
                        final int matchCount = this.getStringMatchScore(text, model.getElementAt(i).toString());
                        if (matchCount > bestMatchCount) {
                            bestMatchCount = matchCount;
                            selectedIndex = i;
                        }
                    }
                    JTextListLink.this.list.setSelectedIndex(selectedIndex);
                    JTextListLink.this.list.ensureIndexIsVisible(selectedIndex);
                }
                return true;
            }
            return false;
        }
        
        private boolean matchTextToList() {
            if (this.toggleIsAdjustingLock()) {
                final Object selectedValue = JTextListLink.this.list.getSelectedValue();
                if (selectedValue != null) {
                    JTextListLink.this.textField.setText(selectedValue.toString());
                }
                else {
                    JTextListLink.this.textField.setText("");
                }
                return true;
            }
            return false;
        }
    }
}
