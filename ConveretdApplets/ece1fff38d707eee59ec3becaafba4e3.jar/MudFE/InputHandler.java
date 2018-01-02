// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.TextField;
import java.awt.Event;
import java.awt.Font;

public class InputHandler
{
    MudFrame mf;
    String[] macroStrings;
    MudTextField inputLine;
    boolean merged;
    InputHistory history;
    boolean ILselecting;
    boolean aselected;
    char ESC;
    
    public void process(final String s) {
        if (this.mf.mudbox == null) {
            this.mf.defaultDisplay.addString("You must logon to the mud first.\n");
            return;
        }
        this.mf.mudbox.sendString(s + "\r\n");
    }
    
    public void merge() {
        this.merged = true;
        this.inputLine.hide();
        this.mf.defaultDisplay.cursor = true;
    }
    
    public InputHandler(final MudFrame f) {
        this.macroStrings = new String[48];
        this.merged = false;
        this.history = new InputHistory();
        this.ILselecting = false;
        this.aselected = false;
        this.ESC = '\u001b';
        this.mf = f;
        (this.inputLine = new MudTextField(this)).setFont(new Font("Courier", 0, 10));
        for (int i = 0; i < 12; ++i) {
            this.macroStrings[i] = "Function Key " + (i + 1);
        }
        for (int i = 12; i < 24; ++i) {
            this.macroStrings[i] = "Alt + " + this.macroStrings[i - 11];
        }
        for (int i = 24; i < 36; ++i) {
            this.macroStrings[i] = "Shift + " + this.macroStrings[i - 23];
        }
        for (int i = 36; i < 48; ++i) {
            this.macroStrings[i] = "Control + " + this.macroStrings[i - 35];
        }
    }
    
    String processMacro(String mac) {
        int in;
        while ((in = mac.indexOf("^M")) != -1) {
            try {
                final String t1 = mac.substring(0, in);
                this.mf.mudbox.sendString(t1 + "\r\n");
                mac = mac.substring(in + 2);
            }
            catch (Error e) {
                System.err.println("Exception occured in processMacro");
                e.printStackTrace();
            }
        }
        return mac;
    }
    
    public void nkeyDown(final Event e, final int key) {
        if (this.merged) {
            if (this.mf.mudbox != null) {
                this.mf.mudbox.sendChar((char)key);
            }
            else {
                System.err.println("Error sending, not connected");
            }
            return;
        }
        if (e.target != this.inputLine || key == 9) {
            this.inputLine.requestFocus();
            String setting;
            if (this.aselected) {
                if (key == 9) {
                    setting = "        ";
                }
                else {
                    setting = "" + (char)key;
                }
                this.aselected = false;
            }
            else {
                if (key == 9) {
                    setting = this.inputLine.getText();
                    final int no = 8 - setting.length() % 8;
                    String addon = "";
                    for (int x = 0; x < no; ++x) {
                        addon += " ";
                    }
                    setting += addon;
                }
                else {
                    setting = this.inputLine.getText() + (char)key;
                }
                this.aselected = false;
            }
            this.inputLine.setText(setting);
            this.inputLine.select(setting.length(), 0);
        }
    }
    
    public void demerge() {
        this.merged = false;
        this.inputLine.show();
        this.mf.defaultDisplay.cursor = false;
    }
    
    public TextField getInputLine() {
        return this.inputLine;
    }
    
    public String[] getMacroStrings() {
        return this.macroStrings;
    }
    
    public boolean keyDown(final Event e, final int key) {
        if (key == 10) {
            if (this.mf.mudbox == null) {
                System.err.println("Inputhandler, no mudbox.");
                this.inputLine.setText("");
                return true;
            }
            if (this.merged) {
                this.mf.defaultDisplay.cleol();
                this.mf.mudbox.sendString("\r\n");
                return true;
            }
            this.process(this.inputLine.getText());
            this.history.addLine(this.inputLine.getText());
            if (this.ILselecting && this.mf.prompt_received) {
                this.inputLine.selectAll();
            }
            else {
                this.inputLine.setText("");
            }
            return true;
        }
        else {
            if (e.id != 403 || key == 9) {
                this.nkeyDown(e, key);
                return e.target != this.inputLine || this.merged;
            }
            int offset = 0;
            String macro = "";
            if (e.modifiers == 8) {
                offset = 12;
            }
            else if (e.modifiers == 1) {
                offset = 24;
            }
            else if (e.modifiers == 2) {
                offset = 36;
            }
            boolean fkeypressed = true;
            switch (key) {
                case 1008: {
                    macro = this.macroStrings[offset];
                    break;
                }
                case 1009: {
                    macro = this.macroStrings[offset + 1];
                    break;
                }
                case 1010: {
                    macro = this.macroStrings[offset + 2];
                    break;
                }
                case 1011: {
                    macro = this.macroStrings[offset + 3];
                    break;
                }
                case 1012: {
                    macro = this.macroStrings[offset + 4];
                    break;
                }
                case 1013: {
                    macro = this.macroStrings[offset + 5];
                    break;
                }
                case 1014: {
                    macro = this.macroStrings[offset + 6];
                    break;
                }
                case 1015: {
                    macro = this.macroStrings[offset + 7];
                    break;
                }
                case 1016: {
                    macro = this.macroStrings[offset + 8];
                    break;
                }
                case 1017: {
                    macro = this.macroStrings[offset + 9];
                    break;
                }
                case 1018: {
                    macro = this.macroStrings[offset + 10];
                    break;
                }
                case 1019: {
                    macro = this.macroStrings[offset + 11];
                    break;
                }
                case 1004: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[A");
                        return true;
                    }
                    final String temp = this.history.up(this.inputLine.getText());
                    this.inputLine.setText(temp);
                    this.inputLine.requestFocus();
                    this.inputLine.select(temp.length(), 0);
                    fkeypressed = false;
                    return true;
                }
                case 1005: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[B");
                        return true;
                    }
                    this.inputLine.setText(this.history.down());
                    this.inputLine.requestFocus();
                    fkeypressed = false;
                    this.inputLine.select(this.inputLine.getText().length(), 0);
                    return true;
                }
                case 1006: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[D");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                case 1007: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[C");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                case 1000: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[H");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                case 1001: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[F");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                case 1002: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[I");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                case 1003: {
                    if (this.merged) {
                        this.mf.mudbox.sendString(this.ESC + "[G");
                        return true;
                    }
                    fkeypressed = false;
                    break;
                }
                default: {
                    fkeypressed = false;
                    break;
                }
            }
            if (!fkeypressed) {
                return false;
            }
            if (this.merged) {
                macro = this.processMacro(macro);
                if (macro.length() != 0) {
                    this.mf.mudbox.sendString(macro);
                }
                return true;
            }
            macro = this.processMacro(macro);
            if (macro.length() != 0) {
                this.inputLine.requestFocus();
                final String temp = this.inputLine.getText();
                final String t1 = temp.substring(0, this.inputLine.getSelectionStart());
                final String t2 = temp.substring(this.inputLine.getSelectionEnd(), temp.length());
                final String t3 = t1 + macro + t2;
                this.inputLine.setText(t3);
                this.inputLine.select(t3.length(), 0);
                this.inputLine.requestFocus();
                this.aselected = false;
            }
            return true;
        }
    }
}
