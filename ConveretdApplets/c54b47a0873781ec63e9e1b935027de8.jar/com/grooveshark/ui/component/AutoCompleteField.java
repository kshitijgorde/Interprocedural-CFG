// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.util.Iterator;
import java.util.Collection;
import java.awt.IllegalComponentStateException;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.util.List;
import java.util.Collections;
import java.awt.Frame;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JList;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class AutoCompleteField extends JTextField
{
    private static final long serialVersionUID = -6842556443278488915L;
    private JDialog suggestionBox;
    private Point location;
    private JList list;
    private Vector<String> data;
    private Vector<String> suggestions;
    private InterruptableMatcher matcher;
    private Font busy;
    private Font regular;
    
    public AutoCompleteField(final Vector<String> data, final Frame frame) {
        Collections.sort(this.data = data);
        this.suggestions = new Vector<String>();
        this.regular = this.getFont();
        this.busy = new Font(this.getFont().getName(), 2, this.getFont().getSize());
        (this.suggestionBox = new JDialog(frame)).setUndecorated(true);
        this.suggestionBox.setFocusableWindowState(false);
        this.suggestionBox.setFocusable(false);
        this.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                AutoCompleteField.this.hideSuggest();
            }
        });
        (this.list = new JList()).addMouseListener(new MouseAdapter() {
            private int selected;
            
            public void mouseReleased(final MouseEvent e) {
                if (this.selected == AutoCompleteField.this.list.getSelectedIndex()) {
                    AutoCompleteField.this.loadSuggestion();
                    AutoCompleteField.this.hideSuggest();
                }
                this.selected = AutoCompleteField.this.list.getSelectedIndex();
            }
        });
        final JScrollPane listPane = new JScrollPane(this.list, 20, 31);
        this.suggestionBox.add(listPane);
        this.suggestionBox.pack();
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                AutoCompleteField.this.relocate();
            }
            
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    AutoCompleteField.this.hideSuggest();
                    return;
                }
                if (e.getKeyCode() == 40) {
                    if (AutoCompleteField.this.suggestionBox.isVisible()) {
                        AutoCompleteField.this.list.setSelectedIndex(AutoCompleteField.this.list.getSelectedIndex() + 1);
                        AutoCompleteField.this.list.ensureIndexIsVisible(AutoCompleteField.this.list.getSelectedIndex() + 1);
                        return;
                    }
                }
                else {
                    if (e.getKeyCode() == 38) {
                        AutoCompleteField.this.list.setSelectedIndex(AutoCompleteField.this.list.getSelectedIndex() - 1);
                        AutoCompleteField.this.list.ensureIndexIsVisible(AutoCompleteField.this.list.getSelectedIndex() - 1);
                        return;
                    }
                    if (e.getKeyCode() == 10 && AutoCompleteField.this.list.getSelectedIndex() != -1) {
                        AutoCompleteField.this.loadSuggestion();
                        AutoCompleteField.this.hideSuggest();
                        return;
                    }
                    if (e.getKeyCode() == 8 || e.getKeyCode() == 127 || e.getKeyCode() == 37 || e.getKeyCode() == 39) {
                        AutoCompleteField.this.suggestions.clear();
                    }
                }
                AutoCompleteField.this.showSuggest();
            }
        });
    }
    
    public void loadSuggestion() {
        this.setText(this.list.getSelectedValue());
    }
    
    public void showSuggest() {
        if (this.matcher != null) {
            this.matcher.stop = true;
        }
        (this.matcher = new InterruptableMatcher()).start();
    }
    
    public void hideSuggest() {
        this.suggestionBox.setVisible(false);
    }
    
    public boolean isSuggestVisible() {
        return this.suggestionBox.isVisible();
    }
    
    private void relocate() {
        try {
            this.location = this.getLocationOnScreen();
            final Point location = this.location;
            location.y += this.getHeight();
            this.suggestionBox.setLocation(this.location);
        }
        catch (IllegalComponentStateException e) {}
    }
    
    private class InterruptableMatcher extends Thread
    {
        volatile boolean stop;
        
        public void run() {
            try {
                final Vector<String> source = (Vector<String>)AutoCompleteField.this.data.clone();
                if (!AutoCompleteField.this.suggestions.isEmpty()) {
                    source.clear();
                    source.addAll(AutoCompleteField.this.suggestions);
                }
                AutoCompleteField.this.suggestions.clear();
                AutoCompleteField.this.setFont(AutoCompleteField.this.busy);
                final Iterator<String> it = source.iterator();
                final String word = AutoCompleteField.this.getText().toLowerCase();
                if (word.trim().equals("")) {
                    AutoCompleteField.this.hideSuggest();
                    return;
                }
                while (it.hasNext()) {
                    if (this.stop) {
                        System.out.println("matcher stopped");
                        return;
                    }
                    final String name = it.next();
                    if (!name.toLowerCase().startsWith(word)) {
                        continue;
                    }
                    AutoCompleteField.this.suggestions.add(name);
                }
                AutoCompleteField.this.setFont(AutoCompleteField.this.regular);
                if (AutoCompleteField.this.suggestions.equals("")) {
                    AutoCompleteField.this.hideSuggest();
                }
                else {
                    AutoCompleteField.this.list.setListData(AutoCompleteField.this.suggestions);
                    AutoCompleteField.this.list.setSelectedIndex(0);
                    AutoCompleteField.this.suggestionBox.setVisible(true);
                }
            }
            catch (Exception e) {
                AutoCompleteField.this.setFont(AutoCompleteField.this.regular);
            }
        }
    }
}
