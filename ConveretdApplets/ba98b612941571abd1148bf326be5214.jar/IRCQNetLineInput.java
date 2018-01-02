import java.awt.Event;
import java.util.Vector;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetLineInput extends TextField
{
    IRCQNet theApp;
    String Label;
    Vector History;
    int Possition;
    String tString;
    int Select;
    
    public IRCQNetLineInput(final String label, final IRCQNet theApp) {
        this.History = new Vector(20, 20);
        this.tString = "";
        this.theApp = theApp;
        this.Label = label;
    }
    
    public void enterDown() {
        this.Possition = 0;
        final String text = this.getText();
        this.setText("");
        if (text.length() < 1) {
            return;
        }
        if (this.History.size() == 0) {
            this.History.addElement(text);
        }
        if (!text.equals(this.History.elementAt(this.History.size() - 1))) {
            this.History.addElement(text);
        }
        if (!this.theApp.MPanel.getParams().Connected) {
            return;
        }
        if (Character.isDigit(text.charAt(0))) {
            this.deliverEvent(new Event(this, 10008, this.Label + ";" + this.theApp.MPanel.procText(" " + text)));
            return;
        }
        this.deliverEvent(new Event(this, 10008, this.Label + ";" + this.theApp.MPanel.procText(text)));
    }
    
    public boolean handleEvent(final Event event) {
        Label_0941: {
            switch (event.id) {
                case 10014: {
                    this.tString = "";
                    final String string = "\u0003" + (int)event.arg;
                    this.Select = this.getSelectionStart();
                    this.tString = this.getText().substring(0, this.getSelectionStart());
                    this.tString = this.tString + "\u0003" + (int)event.arg;
                    this.setText(this.tString += this.getText().substring(this.getSelectionStart()));
                    this.select(this.Select + string.length(), this.Select + string.length());
                    this.requestFocus();
                    return true;
                }
                case 401: {
                    switch (event.key) {
                        case 10: {
                            this.enterDown();
                            return true;
                        }
                        case 2: {
                            this.tString = "";
                            this.Select = this.getSelectionStart();
                            this.tString = this.getText().substring(0, this.getSelectionStart());
                            this.tString += "\u0002";
                            this.setText(this.tString += this.getText().substring(this.getSelectionStart()));
                            this.select(this.Select + 1, this.Select + 1);
                            this.requestFocus();
                            return true;
                        }
                        case 3:
                        case 11: {
                            this.tString = "";
                            this.Select = this.getSelectionStart();
                            this.tString = this.getText().substring(0, this.getSelectionStart());
                            this.tString += "\u0003";
                            this.setText(this.tString += this.getText().substring(this.getSelectionStart()));
                            this.select(this.Select + 1, this.Select + 1);
                            return true;
                        }
                        case 21: {
                            this.tString = "";
                            this.Select = this.getSelectionStart();
                            this.tString = this.getText().substring(0, this.getSelectionStart());
                            this.tString += "\u001f";
                            this.setText(this.tString += this.getText().substring(this.getSelectionStart()));
                            this.select(this.Select + 1, this.Select + 1);
                            this.requestFocus();
                            return true;
                        }
                        default: {
                            this.postEvent(new Event(this, 10020, null));
                            break Label_0941;
                        }
                    }
                    break;
                }
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            if (this.History.size() < this.Possition + 1) {
                                return true;
                            }
                            ++this.Possition;
                            this.setText(this.History.elementAt(this.History.size() - this.Possition));
                            this.select(this.getText().length(), this.getText().length());
                            return true;
                        }
                        case 1005: {
                            if (this.Possition - 1 < 0) {
                                return false;
                            }
                            if (this.Possition - 1 == 0) {
                                this.setText("");
                                --this.Possition;
                            }
                            else {
                                --this.Possition;
                                this.setText(this.History.elementAt(this.History.size() - this.Possition));
                            }
                            this.select(this.getText().length(), this.getText().length());
                            return true;
                        }
                        default: {
                            break Label_0941;
                        }
                    }
                    break;
                }
                case 10009: {
                    if ((int)event.arg == 10) {
                        this.enterDown();
                        return true;
                    }
                    this.setText(this.getText() + (char)(int)event.arg);
                    this.select(this.getText().length(), this.getText().length());
                    this.requestFocus();
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}
