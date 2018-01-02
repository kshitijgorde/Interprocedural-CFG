// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.ItemEvent;
import logging.LogType;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Button;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class ClipFrame extends JAPDialog implements ActionListener, ItemListener
{
    private TextArea m_TextArea;
    private Choice chooser;
    private ClipChoice[] choices;
    
    public ClipFrame(final Component component, final String s, final boolean b, final ClipChoice[] array) {
        super(component, s);
        this.init(b, array);
    }
    
    public ClipFrame(final Component component, final String s, final boolean b) {
        super(component, s);
        this.init(b, null);
    }
    
    private void init(final boolean b, final ClipChoice[] choices) {
        this.choices = choices;
        if (this.choices == null) {
            this.chooser = null;
        }
        else {
            this.chooser = new Choice();
            for (int i = 0; i < this.choices.length; ++i) {
                this.chooser.add(this.choices[i].name);
            }
            this.getContentPane().add(this.chooser, "North");
            this.chooser.addItemListener(this);
        }
        (this.m_TextArea = new TextArea(30, 80)).setText("");
        this.getContentPane().add(this.m_TextArea, "Center");
        if (b) {
            final Button button = new Button("Open");
            button.addActionListener(this);
            button.setActionCommand("open");
            this.getContentPane().add(button, "South");
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                ClipFrame.this.dispose();
            }
        });
        this.pack();
    }
    
    public void setText(final String text) {
        this.m_TextArea.setText(text);
    }
    
    public String getText() {
        return this.m_TextArea.getText();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("open")) {
            if (this.m_TextArea.getText().equals("")) {
                JAPDialog.showErrorDialog(this.getOwner(), "The Text Area is empty!", LogType.GUI);
            }
            else {
                this.dispose();
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.setText(this.choices[this.chooser.getSelectedIndex()].text);
    }
    
    public static class ClipChoice
    {
        public String name;
        public String text;
        
        public ClipChoice(final String name, final String text) {
            this.name = name;
            this.text = text;
        }
    }
}
