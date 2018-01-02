import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import epic.geophys.LatInvalidException;
import epic.geophys.Latitude;
import epic.geophys.LonInvalidException;
import java.awt.Color;
import epic.geophys.Longitude;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class EWBLocTextPanel extends Panel
{
    TextField northInput;
    TextField southInput;
    TextField westInput;
    TextField eastInput;
    EWBLocationStr defaultVal;
    ImageLonTime lonTime;
    ImageLatTime latTime;
    EWBMapCanvas mapC;
    String type;
    
    public EWBLocTextPanel(final EWBLocationStr defaultVal) {
        this.defaultVal = defaultVal;
        this.northInput = new TextField(defaultVal.n, 8);
        this.southInput = new TextField(defaultVal.s, 8);
        this.westInput = new TextField(defaultVal.w, 8);
        this.eastInput = new TextField(defaultVal.e, 8);
        this.type = "yx";
    }
    
    public EWBLocTextPanel(final EWBLocationStr defaultVal, final ImageLatTime latTime) {
        this.defaultVal = defaultVal;
        this.latTime = latTime;
        this.eastInput = new TextField(defaultVal.e, 8);
        this.westInput = new TextField(defaultVal.w, 8);
        this.southInput = new TextField(defaultVal.minDate, 8);
        this.northInput = new TextField(defaultVal.maxDate, 8);
        this.type = "tx";
    }
    
    public EWBLocTextPanel(final EWBLocationStr defaultVal, final ImageLonTime lonTime) {
        this.defaultVal = defaultVal;
        this.lonTime = lonTime;
        this.northInput = new TextField(defaultVal.n, 8);
        this.southInput = new TextField(defaultVal.s, 8);
        this.westInput = new TextField(defaultVal.minDate, 8);
        this.eastInput = new TextField(defaultVal.maxDate, 8);
        this.type = "yt";
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.getValues();
        }
        return super.action(event, o);
    }
    
    public Longitude getEast() {
        try {
            this.eastInput.setForeground(Color.black);
            String s;
            if (this.type.equals("tx") || this.type.equals("yx")) {
                s = this.eastInput.getText();
            }
            else {
                s = this.lonTime.dateToLonStr(this.eastInput.getText());
            }
            return new Longitude(s);
        }
        catch (LonInvalidException ex) {
            this.eastInput.setForeground(Color.red);
            return null;
        }
    }
    
    public String getEastText() {
        return this.eastInput.getText();
    }
    
    public Latitude getNorth() {
        try {
            this.northInput.setForeground(Color.black);
            String s;
            if (this.type.equals("yt") || this.type.equals("yx")) {
                s = this.northInput.getText();
            }
            else {
                s = this.latTime.dateToLatStr(this.northInput.getText());
            }
            return new Latitude(s);
        }
        catch (LatInvalidException ex) {
            this.northInput.setForeground(Color.red);
            return null;
        }
    }
    
    public String getNorthText() {
        return this.northInput.getText();
    }
    
    public Latitude getSouth() {
        try {
            this.southInput.setForeground(Color.black);
            String s;
            if (this.type.equals("yt") || this.type.equals("yx")) {
                s = this.southInput.getText();
            }
            else {
                s = this.latTime.dateToLatStr(this.southInput.getText());
            }
            return new Latitude(s);
        }
        catch (LatInvalidException ex) {
            this.southInput.setForeground(Color.red);
            return null;
        }
    }
    
    public String getSouthText() {
        return this.southInput.getText();
    }
    
    public boolean getValues() {
        Latitude south = null;
        Longitude west = null;
        Longitude east = null;
        boolean b = true;
        final Latitude north = this.getNorth();
        if (north == null) {
            b = false;
        }
        if (b) {
            south = this.getSouth();
            if (south == null) {
                b = false;
            }
        }
        if (b) {
            west = this.getWest();
            if (west == null) {
                b = false;
            }
        }
        if (b) {
            east = this.getEast();
            if (east == null) {
                b = false;
            }
        }
        if (b) {
            this.mapC.setValues(north, south, west, east);
        }
        return b;
    }
    
    public Longitude getWest() {
        try {
            this.westInput.setForeground(Color.black);
            String s;
            if (this.type.equals("tx") || this.type.equals("yx")) {
                s = this.westInput.getText();
            }
            else {
                s = this.lonTime.dateToLonStr(this.westInput.getText());
            }
            return new Longitude(s);
        }
        catch (LonInvalidException ex) {
            this.westInput.setForeground(Color.red);
            return null;
        }
    }
    
    public String getWestText() {
        return this.westInput.getText();
    }
    
    public void init(final EWBMapCanvas mapC) {
        this.mapC = mapC;
    }
    
    public Panel makeLayout(final EWBImgBtnCanvas ewbImgBtnCanvas) {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1, 0, 0));
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(1));
        panel2.add(this.northInput);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add(this.westInput);
        panel3.add(this.eastInput);
        final Panel panel4 = new Panel();
        panel4.setLayout(new FlowLayout(1));
        panel4.add(this.southInput);
        final Panel panel5 = new Panel();
        panel5.setLayout(new FlowLayout(1));
        panel5.add(ewbImgBtnCanvas);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        return panel;
    }
    
    public void setDefault() {
        if (this.type.equals("yt")) {
            this.northInput.setText(this.defaultVal.n);
            this.southInput.setText(this.defaultVal.s);
            this.westInput.setText(this.defaultVal.minDate);
            this.eastInput.setText(this.defaultVal.maxDate);
        }
        else if (this.type.equals("tx")) {
            this.northInput.setText(this.defaultVal.maxDate);
            this.southInput.setText(this.defaultVal.minDate);
            this.westInput.setText(this.defaultVal.w);
            this.eastInput.setText(this.defaultVal.e);
        }
        else {
            this.northInput.setText(this.defaultVal.n);
            this.southInput.setText(this.defaultVal.s);
            this.westInput.setText(this.defaultVal.w);
            this.eastInput.setText(this.defaultVal.e);
        }
    }
    
    public void setText(final EWBLocationStr ewbLocationStr) {
        if (this.type.equals("yt")) {
            this.northInput.setText(ewbLocationStr.n);
            this.southInput.setText(ewbLocationStr.s);
            this.westInput.setText(ewbLocationStr.minDate);
            this.eastInput.setText(ewbLocationStr.maxDate);
        }
        else if (this.type.equals("tx")) {
            this.northInput.setText(ewbLocationStr.minDate);
            this.southInput.setText(ewbLocationStr.maxDate);
            this.westInput.setText(ewbLocationStr.w);
            this.eastInput.setText(ewbLocationStr.e);
        }
        else {
            this.northInput.setText(ewbLocationStr.n);
            this.southInput.setText(ewbLocationStr.s);
            this.westInput.setText(ewbLocationStr.w);
            this.eastInput.setText(ewbLocationStr.e);
        }
    }
    
    public void setText4(final String text, final String text2, final String text3, final String text4) {
        this.northInput.setText(text);
        this.southInput.setText(text2);
        this.westInput.setText(text3);
        this.eastInput.setText(text4);
    }
    
    public void setText4Time(final double n, final double n2, final String text, final String text2) {
        this.northInput.setText(this.latTime.getDateStr(n));
        this.southInput.setText(this.latTime.getDateStr(n2));
        this.westInput.setText(text);
        this.eastInput.setText(text2);
    }
    
    public void setText4Time(final String text, final String text2, final double n, final double n2) {
        this.northInput.setText(text);
        this.southInput.setText(text2);
        this.westInput.setText(this.lonTime.getDateStr(n));
        this.eastInput.setText(this.lonTime.getDateStr(n2));
    }
}
