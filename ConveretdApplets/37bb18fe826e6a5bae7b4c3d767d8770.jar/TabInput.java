import javax.swing.event.DocumentEvent;
import java.io.IOException;
import java.io.StringReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.io.FilenameFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabInput extends MyTab implements FilenameFilter, ActionListener, DocumentListener
{
    private JTextArea textBox;
    private JPanel controlPanel;
    private JComboBox fileBox;
    private JScrollPane textAreaScroll;
    private JButton acceptButton;
    private JApplet main;
    private File[] fileList;
    private String[] fileNames;
    private String fileDir;
    private boolean textChanged;
    private Puzzle newPuz;
    
    public TabInput(final JApplet main) {
        this.newPuz = null;
        this.main = main;
        this.setLayout(new BorderLayout());
        this.textBox = new JTextArea();
        this.add(this.textAreaScroll = new JScrollPane(this.textBox));
        this.textBox.getDocument().addDocumentListener(this);
        this.add(this.controlPanel = new JPanel(), "South");
        this.textChanged = false;
        if (main == null) {
            this.fileList = new File(".").listFiles(this);
            (this.fileNames = new String[this.fileList.length + 1])[0] = "None";
            for (int i = 0; i < this.fileList.length; ++i) {
                this.fileNames[i + 1] = this.fileList[i].getName();
            }
        }
        else {
            this.fileNames = ("None," + main.getParameter("files")).split(",");
            this.fileDir = main.getParameter("filedir");
            if (this.fileDir == null) {
                this.fileDir = "";
            }
        }
        this.fileBox = new JComboBox((E[])this.fileNames);
        this.controlPanel.add(this.fileBox);
        this.fileBox.addActionListener(this);
        (this.acceptButton = new JButton("Accept")).addActionListener(this);
        this.controlPanel.add(this.acceptButton);
    }
    
    public boolean accept(final File file, final String s) {
        return s.length() >= 4 && s.substring(s.length() - 4).equalsIgnoreCase(".txt");
    }
    
    public Puzzle getPuzzle() {
        return this.newPuz;
    }
    
    protected void init() {
        this.textChanged = false;
        this.acceptButton.setEnabled(false);
        this.fileBox.setSelectedIndex(0);
    }
    
    protected void exit() {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.fileBox) {
            final int selectedIndex = this.fileBox.getSelectedIndex();
            if (selectedIndex > 0) {
                final StringBuffer sb = new StringBuffer();
                try {
                    InputStream openStream;
                    if (this.main == null) {
                        openStream = new FileInputStream(this.fileList[selectedIndex - 1]);
                    }
                    else {
                        openStream = new URL(this.main.getDocumentBase(), this.fileDir + this.fileNames[selectedIndex]).openStream();
                    }
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
                    while (true) {
                        final String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line).append("\n");
                    }
                }
                catch (Exception ex) {}
                this.textBox.setText(sb.toString());
            }
            else {
                this.textBox.setText(this.puz.textRepr());
            }
        }
        else if (actionEvent.getSource() == this.acceptButton) {
            this.newPuz = this.parseText();
            this.textChanged = false;
            this.acceptButton.setEnabled(this.newPuz == null);
        }
    }
    
    private Puzzle parseText() {
        while (true) {
            final int index = this.textBox.getText().indexOf("#@");
            if (index < 0) {
                break;
            }
            int length = this.textBox.getText().indexOf("\n", index) + 1;
            if (length == 0) {
                length = this.textBox.getText().length();
            }
            this.textBox.replaceRange("", index, length);
        }
        final Parser parser = new Parser(new StringReader(this.textBox.getText()));
        Puzzle parse;
        try {
            parse = Puzzle.parse(parser);
        }
        catch (IOException ex) {
            int i = parser.getLine();
            final String message = ex.getMessage();
            int caretPosition = 0;
            while (i > 1) {
                caretPosition = this.textBox.getText().indexOf("\n", caretPosition) + 1;
                --i;
            }
            final int n = this.textBox.getText().indexOf("\n", caretPosition) + 1;
            if (n == 0) {
                this.textBox.append("\n#@ " + message + "\n");
            }
            else {
                this.textBox.insert("#@ " + message + "\n", n);
            }
            this.textBox.setCaretPosition(caretPosition);
            this.textBox.requestFocus();
            return null;
        }
        return parse;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
        this.textValueChanged();
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.textValueChanged();
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.textValueChanged();
    }
    
    public void textValueChanged() {
        this.textChanged = true;
        this.acceptButton.setEnabled(true);
    }
}
