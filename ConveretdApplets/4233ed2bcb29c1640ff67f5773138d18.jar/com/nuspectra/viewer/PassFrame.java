// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Frame;

class PassFrame extends Frame
{
    private int myFrameWidth;
    private int myFrameHeight;
    private Label myAuthLabel;
    private Label myUserNameLabel;
    private Label myPasswordLabel;
    private TextField myUserNameField;
    private TextField myPasswordField;
    private Button myOkButton;
    private Button myCancelButton;
    public static String myUserName;
    public static String myPassword;
    private NuApplet applet;
    public static int badguess;
    public final int MAX_BAD = 10;
    public final int WAITING = 1;
    public final int OK = 2;
    public final int CANCEL = 3;
    private int state;
    
    static {
        PassFrame.myUserName = "";
        PassFrame.myPassword = "";
        PassFrame.badguess = 0;
    }
    
    protected PassFrame(final NuApplet applet) {
        super("Enter Network Password");
        this.state = 1;
        this.applet = applet;
        this.myFrameWidth = 360;
        this.myFrameHeight = 150;
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.addNotify();
        this.state = 1;
        this.resize(this.insets().left + this.insets().right + this.myFrameWidth, this.insets().top + this.insets().bottom + this.myFrameHeight);
        int x = 40;
        int y = 10 + this.insets().top;
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.addNotify();
        this.resize(this.insets().left + this.insets().right + this.myFrameWidth, this.insets().top + this.insets().bottom + this.myFrameHeight);
        (this.myAuthLabel = new Label("Please enter your username and password.", 0)).reshape(x, y, this.myFrameWidth, 17);
        this.add(this.myAuthLabel);
        y += 30;
        (this.myUserNameLabel = new Label("User Name", 0)).reshape(x, y, 80, 17);
        this.add(this.myUserNameLabel);
        (this.myUserNameField = new TextField(PassFrame.myUserName)).reshape(x + 100, y, 190, 22);
        this.add(this.myUserNameField);
        y += 30;
        (this.myPasswordLabel = new Label("Password", 0)).reshape(x, y, 60, 17);
        this.add(this.myPasswordLabel);
        (this.myPasswordField = new TextField(PassFrame.myPassword)).reshape(x + 100, y, 190, 22);
        this.myPasswordField.setEchoCharacter('*');
        this.add(this.myPasswordField);
        x = 56;
        y += 35;
        (this.myOkButton = new Button("OK")).reshape(x + 150, y, 55, 23);
        this.add(this.myOkButton);
        this.myCancelButton = new Button("Cancel");
        final Button myCancelButton = this.myCancelButton;
        x += 220;
        myCancelButton.reshape(x, y, 55, 23);
        this.add(this.myCancelButton);
    }
    
    public synchronized void hide() {
        super.hide();
    }
    
    private synchronized void keyReleasePassword(final Event event) {
        if (event.key == 10) {
            this.myOkButton.requestFocus();
            this.clickedPassOk(null);
        }
    }
    
    private synchronized void clickedPassOk(final Event event) {
        this.hide();
        PassFrame.myUserName = this.myUserNameField.getText();
        PassFrame.myPassword = this.myPasswordField.getText();
        this.applet.sendMessage(1013, this);
        this.state = 2;
    }
    
    private synchronized void clickedPassCancel() {
        this.hide();
        this.state = 3;
        this.applet.sendMessage(1014, this);
    }
    
    public synchronized void show() {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.move((dimension.width - 415) / 2, (dimension.height - 255) / 2);
        super.show();
        this.myUserNameField.requestFocus();
        this.myUserNameField.selectAll();
    }
    
    private synchronized void keyReleaseUserName(final Event event) {
        if (event.key == 10) {
            this.myPasswordField.requestFocus();
            this.myPasswordField.selectAll();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.clickedPassCancel();
            return true;
        }
        if (event.id == 402 && event.target == this.myUserNameField) {
            this.keyReleaseUserName(event);
            return true;
        }
        if (event.id == 402 && event.target == this.myPasswordField) {
            this.keyReleasePassword(event);
            return true;
        }
        if (event.id == 1001 && event.target == this.myOkButton) {
            this.clickedPassOk(event);
            return true;
        }
        if (event.id == 1001 && event.target == this.myCancelButton) {
            this.clickedPassCancel();
            return true;
        }
        return super.handleEvent(event);
    }
}
