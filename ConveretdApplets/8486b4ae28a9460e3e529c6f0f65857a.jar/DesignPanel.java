import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DesignPanel extends Panel implements ActionListener, Observer
{
    static final String ACTION_SAVE = "save";
    static final String ACTION_CLEAR = "clear";
    static final String ACTION_COLOR = "color";
    static final String ACTION_CLEAR_LAST = "last";
    DesignCanvas myCanvas;
    Panel controlPane;
    Label statusLine;
    Button clearButton;
    Button clearLastButton;
    Button saveButton;
    Button colorButton;
    ColorPicker paperColorPicker;
    SaveManager saver;
    
    public DesignCanvas getCanvas() {
        return this.myCanvas;
    }
    
    public DesignPanel(final int n, final SaveManager saver) {
        this.saver = saver;
        if (this.saver != null) {
            this.saver.addObserver(this);
        }
        this.setLayout(new BorderLayout(4, 4));
        if (n < 100 || n > 2048) {
            throw new IllegalArgumentException("bad size");
        }
        (this.myCanvas = new DesignCanvas(n)).setBackground(Color.white);
        (this.controlPane = new Panel()).setLayout(new FlowLayout(0));
        (this.statusLine = new Label("Spiro Design Canvas", 0)).setFont(new Font("SansSerif", 0, 12));
        this.statusLine.setBackground(Color.lightGray);
        (this.clearButton = new Button("Clear")).setActionCommand("clear");
        this.clearButton.addActionListener(this);
        (this.colorButton = new Button("Set background..")).setActionCommand("color");
        this.colorButton.addActionListener(this);
        (this.saveButton = new Button("Save Image")).setActionCommand("save");
        this.saveButton.addActionListener(this);
        if (this.saver == null) {
            this.saveButton.setEnabled(false);
        }
        (this.clearLastButton = new Button("Clear Last")).setActionCommand("last");
        this.clearLastButton.addActionListener(this);
        this.controlPane.setFont(new Font("SansSerif", 0, 12));
        this.controlPane.add(new Label("Design operations:", 0));
        this.controlPane.add(this.clearButton);
        this.controlPane.add(this.clearLastButton);
        this.controlPane.add(new Label(" "));
        this.controlPane.add(this.colorButton);
        this.controlPane.add(new Label(" "));
        this.controlPane.add(this.saveButton);
        this.controlPane.setBackground(Color.lightGray);
        this.paperColorPicker = null;
        this.add("North", this.controlPane);
        this.add("Center", this.myCanvas);
        this.add("South", this.statusLine);
        this.setBackground(Color.black);
    }
    
    public void setStatus(final String text) {
        this.statusLine.setText(text);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == null || actionCommand.length() == 0) {
            return;
        }
        if (actionCommand.equals("save")) {
            this.saver.performSave(this.myCanvas, this.myCanvas.getBackground());
        }
        else if (actionCommand.equals("color")) {
            if (this.paperColorPicker == null) {
                (this.paperColorPicker = new ColorPicker(this, this.myCanvas)).setLocation(460, 140);
                this.paperColorPicker.setTitle("Set background color");
            }
            this.paperColorPicker.setModal(true);
            this.paperColorPicker.show();
            this.myCanvas.repaint();
        }
        else if (actionCommand.equals("clear")) {
            this.setStatus("Cleared all designs.");
            this.myCanvas.removeAllImages();
            this.myCanvas.repaint();
        }
        else if (actionCommand.equals("last")) {
            final int countImages = this.myCanvas.countImages();
            if (countImages > 0) {
                this.setStatus("Cleared design " + (countImages + 1) + ".");
                this.myCanvas.removeImage(countImages - 1);
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof Design) {
            this.setStatus(((Design)observable).formatMessage(o));
        }
        else if (observable instanceof SaveManager) {
            this.setStatus(((SaveManager)observable).formatMessage(o));
        }
        else if (o != null) {
            this.setStatus("Status: " + o.toString());
        }
        else {
            this.setStatus("Anomalous update from " + observable.toString());
        }
    }
}
