// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.awt.Event;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.net.URL;
import java.applet.Applet;

public class Interfase extends Applet
{
    boolean local;
    URL sounds;
    URL script;
    String scrpt;
    Label dialogLabel;
    Label userLabel;
    Label inputLabel;
    TextArea dialogText;
    TextArea userText;
    TextField inputText;
    Button first;
    Panel textField01;
    Panel textField02;
    Panel textField03;
    Panel actionsButtons;
    MainBrain MainBrainobj;
    Speech Speechobj;
    
    public void init() {
        this.setLayout(new GridLayout(3, 1, 2, 2));
        this.textField01.setLayout(new BorderLayout());
        this.textField02.setLayout(new BorderLayout());
        this.textField03.setLayout(new FlowLayout());
        this.actionsButtons.setLayout(new FlowLayout());
        this.textField01.add("West", this.dialogLabel);
        this.textField01.add("Center", this.dialogText);
        this.textField02.add("West", this.userLabel);
        this.textField02.add("Center", this.userText);
        this.actionsButtons.add(this.first);
        this.textField03.add(this.inputLabel);
        this.textField03.add(this.inputText);
        this.textField03.add(this.actionsButtons);
        this.add(this.textField01);
        this.add(this.textField02);
        this.add(this.textField03);
    }
    
    public void start() {
        String s = "talk.dat";
        final String parameter;
        if ((parameter = this.getParameter("soundfile")) != null) {
            s = parameter;
        }
        try {
            this.sounds = new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {}
        String scrpt = "script";
        final String parameter2 = this.getParameter("script");
        this.scrpt = parameter2;
        if (parameter2 != null) {
            scrpt = this.scrpt;
        }
        try {
            this.script = new URL(this.getDocumentBase(), scrpt);
        }
        catch (Exception ex2) {}
        ((Thread)this.MainBrainobj).start();
        ((Thread)this.Speechobj).start();
        this.MainBrainobj.readScript(this.local, this.script);
        this.Speechobj.do_load_sounds(this.sounds, (String)null);
    }
    
    public void response(final String s) {
        this.dialogText.appendText(s);
        this.dialogText.appendText("\n");
        this.Speechobj.talk(s);
    }
    
    public void responseuser(final String s) {
        this.dialogText.appendText(s);
        this.dialogText.appendText("\n");
        this.userText.appendText(s);
        this.userText.appendText("\n");
    }
    
    public void runProgram() {
        final String s = "Hello";
        this.response(s);
        this.response(this.MainBrainobj.processInput(s));
        this.inputText.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.inputText) {
                    final String s = (String)event.arg;
                    final String processInput = this.MainBrainobj.processInput(s);
                    this.inputText.setText("");
                    this.responseuser(s);
                    this.response(processInput);
                    return true;
                }
                if (event.target.equals(this.first)) {
                    this.runProgram();
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public Interfase() {
        this.local = true;
        this.dialogLabel = new Label("Dialog");
        this.userLabel = new Label("User  ");
        this.inputLabel = new Label("Input");
        this.dialogText = new TextArea(2, 80);
        this.userText = new TextArea(2, 80);
        this.inputText = new TextField(30);
        this.first = new Button("Start");
        this.textField01 = new Panel();
        this.textField02 = new Panel();
        this.textField03 = new Panel();
        this.actionsButtons = new Panel();
        this.MainBrainobj = new MainBrain();
        this.Speechobj = new Speech();
    }
}
