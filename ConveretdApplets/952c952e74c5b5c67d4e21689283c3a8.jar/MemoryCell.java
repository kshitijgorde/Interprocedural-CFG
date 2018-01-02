import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MemoryCell extends Panel implements ActionListener, FocusListener
{
    public static final int CELL_WIDTH = 3;
    private TextField contentField;
    private Label addressLabel;
    private int address;
    private int data;
    private Messenger messages;
    
    public MemoryCell() {
        this(0, 0, null);
    }
    
    public MemoryCell(final int address, final int data, final Messenger messages) {
        this.setBackground((address % 2 != 0) ? LittleMan.LIGHT_GREEN : LittleMan.LIGHT_PINK);
        this.setLayout(new GridLayout(2, 1));
        this.addressLabel = new Label("" + address, 1);
        this.address = address;
        this.contentField = new TextField("" + data);
        this.data = data;
        this.add(this.addressLabel);
        this.add(this.contentField);
        this.contentField.addActionListener(this);
        this.contentField.addFocusListener(this);
        this.messages = messages;
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public int getData() {
        return this.data;
    }
    
    public void setData(final int data) {
        this.data = data;
        this.contentField.setText("" + this.data);
    }
    
    public void setAsCurrentInstruction() {
        this.contentField.setBackground(Color.YELLOW);
    }
    
    public void unSetAsCurrentInstruction() {
        this.contentField.setBackground(Color.WHITE);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.updateCell();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.contentField.selectAll();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.contentField.select(0, 0);
        this.updateCell();
    }
    
    private void updateCell() {
        final String text = this.contentField.getText();
        int data = 0;
        boolean b = false;
        try {
            data = Integer.parseInt(text);
            if (Math.abs(data) <= 999) {
                b = true;
            }
            else {
                data = this.data;
            }
        }
        catch (NumberFormatException ex) {
            b = false;
            data = this.data;
        }
        finally {
            if (!b) {
                this.messages.sendMessage("\"" + text + "\"" + " is not valid in Address: " + this.address);
            }
            this.setData(data);
        }
    }
}
