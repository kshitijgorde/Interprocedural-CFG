// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;

public class JumpPanel extends Panel
{
    Label dayLabel;
    Label monthLabel;
    Label yearLabel;
    TextField dayField;
    TextField monthField;
    TextField yearField;
    Button acceptButton;
    Label msgLabel;
    
    public JumpPanel() {
        this.dayLabel = new Label();
        this.monthLabel = new Label();
        this.yearLabel = new Label();
        this.dayField = new TextField();
        this.monthField = new TextField();
        this.yearField = new TextField();
        this.acceptButton = new Button();
        this.msgLabel = new Label();
        this.dayField.setColumns(2);
        this.monthField.setColumns(2);
        this.yearField.setColumns(4);
        this.dayLabel.setText("Day");
        this.monthLabel.setText("Month");
        this.yearLabel.setText("Year");
        this.msgLabel.setText("Date is invalid");
        this.acceptButton.setLabel("Go");
        this.msgLabel.setVisible(false);
        this.add(this.dayLabel);
        this.add(this.dayField);
        this.add(this.monthLabel);
        this.add(this.monthField);
        this.add(this.yearLabel);
        this.add(this.yearField);
        this.add(this.acceptButton);
        this.add(this.msgLabel);
    }
    
    public void setText_Day(final String str) {
        this.dayLabel.setText(str);
    }
    
    public void setText_Month(final String str) {
        this.monthLabel.setText(str);
    }
    
    public void setText_Year(final String str) {
        this.yearLabel.setText(str);
    }
    
    public void setText_Go(final String str) {
        this.acceptButton.setLabel(str);
    }
    
    public void setText_InvalidDate(final String str) {
        this.msgLabel.setText(str);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.dayField.setForeground(color);
        this.monthField.setForeground(color);
        this.yearField.setForeground(color);
        this.acceptButton.setForeground(color);
        this.dayLabel.setForeground(color);
        this.monthLabel.setForeground(color);
        this.yearLabel.setForeground(color);
        this.msgLabel.setForeground(color);
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.dayField.setBackground(color);
        this.monthField.setBackground(color);
        this.yearField.setBackground(color);
        this.acceptButton.setBackground(color);
        this.dayLabel.setBackground(color);
        this.monthLabel.setBackground(color);
        this.yearLabel.setBackground(color);
        this.msgLabel.setBackground(color);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.dayField.setFont(font);
        this.monthField.setFont(font);
        this.yearField.setFont(font);
        this.acceptButton.setFont(font);
        this.dayLabel.setFont(font);
        this.monthLabel.setFont(font);
        this.yearLabel.setFont(font);
        this.msgLabel.setFont(font);
    }
    
    public void setAcceptListener(final MouseAdapter listener) {
        this.acceptButton.addMouseListener(listener);
    }
    
    public long getJumpTime() throws BadDateException {
        try {
            final int day = Integer.parseInt(this.dayField.getText());
            final int month = Integer.parseInt(this.monthField.getText()) - 1;
            final int year = Integer.parseInt(this.yearField.getText());
            final Calendar date = new GregorianCalendar(year, month, day);
            if (this.msgLabel.isVisible()) {
                this.msgLabel.setVisible(false);
                this.validate();
            }
            this.setJumpTime(date.getTime().getTime());
            return date.getTime().getTime();
        }
        catch (RuntimeException rex) {
            if (!this.msgLabel.isVisible()) {
                this.msgLabel.setVisible(true);
                this.validate();
            }
            throw new BadDateException(rex.toString());
        }
    }
    
    public void setJumpTime(final long time) {
        final Calendar date = new GregorianCalendar();
        date.setTime(new Date(time));
        this.dayField.setText("" + date.get(5));
        this.monthField.setText("" + (date.get(2) + 1));
        this.yearField.setText("" + date.get(1));
    }
    
    public long getPrecision() {
        return 86400000L;
    }
    
    public static class BadDateException extends Exception
    {
        public BadDateException(final String msg) {
            super(msg);
        }
    }
}
