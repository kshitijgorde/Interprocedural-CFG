// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.awt.Color;

public class ColourSaver extends CommHandler
{
    boolean toget;
    int got;
    int nocolour;
    int inno;
    Color fg;
    final int TO_RECEIVE = 1;
    final int TO_R_DEFFG = 2;
    final int TO_R_DEFBG = 3;
    final int TO_R_COLOURS = 4;
    final int TO_OPTIONS = 5;
    final int TO_RECEIVE_HTTP = 6;
    final int TO_RECEIVE_COLOUR = 7;
    int state;
    final int MAX = 209;
    private static final boolean debug = false;
    
    public void logstop() {
        try {
            super.o.mywriteUTF("stop");
            super.o.send();
        }
        catch (IOException e) {
            System.err.println("IOException in STOP");
        }
    }
    
    public void received(final String s) {
        Label_0716: {
            switch (this.state) {
                case 1: {
                    if (s.equals("log")) {
                        this.state = 6;
                    }
                    else {
                        this.state = 7;
                    }
                    break;
                }
                case 6: {
                    try {
                        final URL nu = new URL("http://" + super.theframe.getHost() + ":" + (super.theframe.getHostPort() + 1) + "/user%" + s);
                        super.theframe.mainapplet.getAppletContext().showDocument(nu, "_blank");
                    }
                    catch (MalformedURLException e) {
                        System.err.println("Malformed exception.");
                    }
                    this.state = 1;
                    break;
                }
                case 7: {
                    System.out.println("Colour:" + s + ":");
                    this.nocolour = Integer.parseInt(s);
                    this.got = 0;
                    if (this.nocolour == 0) {
                        this.toget = false;
                        super.theframe.defaultDisplay.addString("Error opening save file.\n");
                        this.state = 1;
                        return;
                    }
                    if (this.nocolour <= 416) {
                        DoCodes.codes[209].setAttribute(new Attribute(Color.white, Color.red));
                    }
                    this.state = 2;
                    break;
                }
                case 2: {
                    this.fg = new Color(Integer.parseInt(s));
                    this.state = 3;
                    break;
                }
                case 3: {
                    DoCodes.setDefault(this.fg, new Color(Integer.parseInt(s)));
                    this.state = 4;
                    break;
                }
                case 4: {
                    if (this.got % 2 == 0) {
                        this.fg = new Color(Integer.parseInt(s));
                    }
                    else if (this.got / 2 <= 209) {
                        DoCodes.codes[this.got / 2].setAttribute(new Attribute(this.fg, new Color(Integer.parseInt(s))));
                    }
                    else {
                        System.err.println("Received too many colours, ignoring.");
                    }
                    ++this.got;
                    if (this.got >= this.nocolour) {
                        this.toget = false;
                        this.state = 5;
                        this.inno = 0;
                        super.theframe.validate();
                        super.theframe.defaultDisplay.repaint();
                    }
                    break;
                }
                case 5: {
                    switch (this.inno++) {
                        case 0: {
                            super.theframe.playsounds = s.equals("true");
                            break Label_0716;
                        }
                        case 1: {
                            super.theframe.ILretain = s.equals("true");
                            super.theframe.doILretain();
                            break Label_0716;
                        }
                        case 2: {
                            super.theframe.showcompass = s.equals("true");
                            break Label_0716;
                        }
                        case 3: {
                            super.theframe.showshield = s.equals("true");
                            break Label_0716;
                        }
                        case 4: {
                            super.theframe.playclick = s.equals("true");
                            break Label_0716;
                        }
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17: {
                            this.got = 0;
                            break Label_0716;
                        }
                        default: {
                            if (this.got < 48) {
                                super.theframe.inputHandler.macroStrings[this.got] = s;
                            }
                            ++this.got;
                            if (this.got >= 48) {
                                this.state = 1;
                                break Label_0716;
                            }
                            break Label_0716;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void LoadClassic() {
        try {
            if (!this.toget) {
                super.o.mywriteUTF("original");
                super.o.send();
                this.toget = true;
                this.got = 0;
                this.nocolour = 0;
            }
        }
        catch (IOException e) {
            System.err.println("IOException in LoadClassic");
        }
    }
    
    public ColourSaver(final MudFrame f) {
        super(f, "Colour Saver");
        this.toget = false;
        this.got = 0;
        this.nocolour = 0;
        this.inno = 0;
        this.state = 1;
        super.exec.start();
    }
    
    public void save() {
        try {
            super.o.mywriteUTF("save");
            super.o.send();
        }
        catch (IOException e) {
            System.err.println("IOException in SAVE");
        }
    }
    
    void send(final String s) throws IOException {
        super.o.mywriteUTF(s);
        super.o.send();
    }
    
    public void send() {
        try {
            super.o.mywriteUTF("send");
            super.o.send();
        }
        catch (IOException e) {
            System.err.println("IOException in Send");
        }
    }
    
    public void SaveColours() {
        try {
            super.o.mywriteUTF("put");
            super.o.send();
            super.o.mywriteUTF("" + 420);
            super.o.send();
            super.o.mywriteUTF("" + Attribute._defaultfg.getRGB());
            super.o.send();
            super.o.mywriteUTF("" + Attribute._defaultbg.getRGB());
            super.o.send();
            for (int i = 0; i <= 209; ++i) {
                super.o.mywriteUTF("" + DoCodes.codes[i].getAttribute().getFg().getRGB());
                super.o.send();
                super.o.mywriteUTF("" + DoCodes.codes[i].getAttribute().getBg().getRGB());
                super.o.send();
            }
            super.theframe.showMessage("Colour scheme saved.");
            if (super.theframe.playsounds) {
                this.send("true");
            }
            else {
                this.send("false");
            }
            if (super.theframe.ILretain) {
                this.send("true");
            }
            else {
                this.send("false");
            }
            if (super.theframe.showcompass) {
                this.send("true");
            }
            else {
                this.send("false");
            }
            if (super.theframe.showshield) {
                this.send("true");
            }
            else {
                this.send("false");
            }
            if (super.theframe.playclick) {
                this.send("true");
            }
            else {
                this.send("false");
            }
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            this.send("false");
            super.theframe.showMessage("User options saved");
            for (int i = 0; i < 48; ++i) {
                this.send(super.theframe.inputHandler.macroStrings[i]);
            }
            super.theframe.showMessage("");
        }
        catch (IOException e) {
            System.err.println("IOException in SaveColours");
        }
    }
    
    public void LoadDefault() {
        try {
            if (!this.toget) {
                super.o.mywriteUTF("default");
                super.o.send();
                this.toget = true;
                this.got = 0;
                this.nocolour = 0;
            }
        }
        catch (IOException e) {
            System.err.println("IOException in LoadDefault");
        }
    }
}
