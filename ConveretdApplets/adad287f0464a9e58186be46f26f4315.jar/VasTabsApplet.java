import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VasTabsApplet extends Applet
{
    VasTabs tabs;
    Panel mPan0;
    Panel mPan1;
    Panel mPan2;
    Panel mPan3;
    Panel mPan4;
    Panel mPan5;
    Label lLogin;
    Label lPasswd;
    Label llState;
    Label lState;
    Label llReplay;
    Label lReplay;
    TextField eLogin;
    TextField ePasswd;
    Button bSend;
    
    public String getAppletInfo() {
        return new String("VasTabsApplet v2.1 March, 31 1998, (C) 1997, 98 Vasile Calmatui");
    }
    
    public VasTabsApplet() {
        this.setLayout(null);
        this.resize(400, 400);
        this.setBackground(Color.lightGray);
        this.tabs = new VasTabs(25, 25, 350, 350);
        (this.mPan0 = new Panel()).setLayout(null);
        this.lLogin = new Label("Login :");
        this.mPan0.add(this.lLogin);
        this.lLogin.reshape(10, 30, 100, 21);
        this.eLogin = new TextField("vasile");
        this.mPan0.add(this.eLogin);
        this.eLogin.reshape(110, 30, 130, 21);
        this.lPasswd = new Label("Password :");
        this.mPan0.add(this.lPasswd);
        this.lPasswd.reshape(10, 60, 100, 21);
        this.ePasswd = new TextField("password_here");
        this.mPan0.add(this.ePasswd);
        this.ePasswd.reshape(110, 60, 130, 21);
        this.ePasswd.setEchoCharacter('*');
        (this.mPan1 = new Panel()).setLayout(null);
        this.llState = new Label("State :");
        this.mPan1.add(this.llState);
        this.llState.reshape(10, 160, 100, 21);
        this.lState = new Label("Just a test");
        this.mPan1.add(this.lState);
        this.lState.reshape(110, 160, 390, 21);
        this.llReplay = new Label("Replay :");
        this.mPan1.add(this.llReplay);
        this.llReplay.reshape(10, 200, 100, 21);
        this.lReplay = new Label("Register Now !");
        this.mPan1.add(this.lReplay);
        this.lReplay.reshape(110, 200, 390, 21);
        this.bSend = new Button("Action !");
        this.mPan1.add(this.bSend);
        this.bSend.reshape(110, 90, 80, 30);
        this.mPan2 = new Panel();
        this.mPan3 = new Panel();
        this.mPan4 = new Panel();
        this.mPan5 = new Panel();
        this.tabs.addTab("Login", this.mPan0);
        this.tabs.addTab("One", this.mPan1);
        this.tabs.addTab("2", this.mPan2);
        this.tabs.addTab("RedOne", this.mPan3);
        this.tabs.setColorTab("RedOne", Color.red);
        this.mPan3.setBackground(Color.red);
        this.tabs.addTab("Disabled", this.mPan4);
        this.tabs.disableTab("Disabled");
        this.tabs.addTab("the 5th", this.mPan5);
        this.tabs.setColorTab("the 5th", Color.green);
        this.add(this.tabs);
    }
}
