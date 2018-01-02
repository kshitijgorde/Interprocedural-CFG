// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Event;
import java.awt.Container;
import javax.accessibility.Accessible;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.AWTError;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Panel;

class LogPanel extends Panel
{
    String saveContents;
    TextArea text;
    Button saveButton;
    Button clearButton;
    boolean hidden;
    String saveText;
    boolean canSave;
    boolean firstTime;
    String eoln;
    
    LogPanel() {
        this.hidden = true;
        this.saveText = "";
        this.canSave = true;
        this.firstTime = true;
        this.eoln = System.getProperty("line.separator");
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(5, 5));
        (this.text = new TextArea()).setEditable(false);
        this.add("Center", this.text);
        final Panel panel = new Panel();
        panel.setBackground(Color.lightGray);
        this.add("South", panel);
        panel.add(this.clearButton = new Button("Clear Log"));
        panel.add(this.saveButton = new Button("Save to File"));
    }
    
    void addLine(final String s) {
        if (this.hidden) {
            this.saveText = this.saveText + s + this.eoln;
        }
        else {
            this.clearMessage();
            this.text.appendText(s + this.eoln);
            this.text.select(32000, 32000);
        }
    }
    
    void addEoln() {
        if (this.hidden) {
            this.saveText += this.eoln;
        }
        else {
            this.clearMessage();
            this.text.appendText(this.eoln);
            this.text.select(32000, 32000);
        }
    }
    
    void setMessage(final String s) {
        this.saveContents = this.text.getText();
        this.text.setText(s + this.eoln + this.eoln + "Click \"Clear Log\" button to dismiss this message.");
    }
    
    void clearMessage() {
        if (this.saveContents != null) {
            this.text.setText(this.saveContents);
            this.saveContents = null;
        }
    }
    
    void aboutToShow() {
        if (!this.firstTime) {
            this.text.setText(this.saveText);
            this.saveText = "";
        }
    }
    
    void shown() {
        if (this.firstTime) {
            this.text.setText(this.saveText);
            this.firstTime = false;
        }
    }
    
    void aboutToHide() {
        this.clearMessage();
        this.saveText = this.text.getText();
    }
    
    void doSave() {
        if (!this.canSave) {
            return;
        }
        String file = null;
        try {
            FileDialog fileDialog;
            try {
                Accessible accessible = this;
                while (true) {
                    final Container parent = ((Component)accessible).getParent();
                    if (parent == null) {
                        break;
                    }
                    accessible = (Accessible)parent;
                }
                if (!(accessible instanceof Frame)) {
                    accessible = null;
                }
                fileDialog = new FileDialog((Frame)accessible, "Save as:", 1);
                fileDialog.show();
            }
            catch (AWTError awtError) {
                this.setMessage("ERROR while trying to create a file dialog box." + this.eoln + "It will not be possible to save files.");
                this.canSave = false;
                this.saveButton.disable();
                return;
            }
            catch (RuntimeException ex3) {
                this.setMessage("ERROR while trying to create a file dialog box." + this.eoln + "It will not be possible to save files.");
                this.canSave = false;
                this.saveButton.disable();
                return;
            }
            file = fileDialog.getFile();
            if (file == null) {
                return;
            }
            final PrintStream printStream = new PrintStream(new FileOutputStream(new File(fileDialog.getDirectory(), file)));
            printStream.print(this.text.getText());
            printStream.close();
        }
        catch (IOException ex) {
            this.setMessage("OUTPUT ERROR while trying to save to the file '" + file + "':  " + this.eoln + this.eoln + ex.getMessage());
        }
        catch (SecurityException ex2) {
            this.setMessage("SECURITY ERROR while trying to save to the file '" + file + "':  " + this.eoln + this.eoln + ex2.getMessage());
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.saveButton) {
            this.clearMessage();
            this.doSave();
            return true;
        }
        if (event.target == this.clearButton) {
            if (this.saveContents != null) {
                this.clearMessage();
            }
            else {
                this.text.setText("");
            }
            return true;
        }
        return super.action(event, o);
    }
}
