// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.awt.Event;
import java.io.IOException;
import java.io.InputStream;
import modules.bsx.BSXInputStream;
import java.io.StringBufferInputStream;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Container;
import modules.bsx.BSXDisplay;
import java.awt.Panel;

public class BSXModule extends Panel implements Module
{
    protected String clientVersion;
    private BSXDisplay bsxWindow;
    private telnet parent;
    private Container toplevel;
    private Button bsxButton;
    private Button logButton;
    private boolean bsxCheckIt;
    private boolean logging;
    private static int debug;
    private String lastCommand;
    private int lastState;
    private String strIdentifier;
    private String strXPos;
    private int intXPos;
    private String strYPos;
    private int intYPos;
    private String strPolys;
    private int intPolys;
    private String strEdges;
    private int intEdges;
    private String BSXData;
    
    static {
        BSXModule.debug = 0;
    }
    
    public BSXModule() {
        this.clientVersion = "Foob 0.5";
        this.lastCommand = "";
        this.strIdentifier = "";
        this.strXPos = "";
        this.intXPos = 0;
        this.strYPos = "";
        this.intYPos = 0;
        this.strPolys = "";
        this.intPolys = 0;
        this.strEdges = "";
        this.intEdges = 0;
        this.BSXData = "";
    }
    
    public void addNotify() {
        this.setLayout(new GridLayout(1, 0));
        if (this.bsxWindow == null) {
            this.add(this.bsxButton = new Button("BSX aus!"));
            this.bsxCheckIt = true;
            this.add(this.logButton = new Button("Logging an!"));
            this.logging = false;
            this.bsxWindow = new BSXDisplay();
        }
        super.addNotify();
    }
    
    private int asciiHexToInt(final String s) {
        if (s.length() == 2) {
            try {
                int h = s.charAt(0);
                if (h >= 65) {
                    h -= 55;
                }
                else {
                    h -= 48;
                }
                int l = s.charAt(1);
                if (l >= 65) {
                    l -= 55;
                }
                else {
                    l -= 48;
                }
                if (BSXModule.debug > 9) {
                    this.warn("Converting Hex: " + s + " to int: " + (h * 16 + l));
                }
                return h * 16 + l;
            }
            catch (Exception ex) {
                return 0;
            }
        }
        return 0;
    }
    
    public void connect(final String host, final int port) {
    }
    
    private String decodeCommand(String s) {
        while (this.lastCommand.length() != 4 && s.length() > 0) {
            this.lastCommand = String.valueOf(this.lastCommand) + s.charAt(0);
            s = s.substring(1);
        }
        if (this.lastCommand.length() != 4) {
            this.lastState = -1;
            return "";
        }
        if (this.lastCommand.equals("@RFS")) {
            this.resetMachine();
            if (this.bsxWindow != null) {
                this.bsxWindow.repaint();
            }
            else {
                this.warn("No BSX Output Available");
            }
        }
        else if (this.lastCommand.equals("@VIO")) {
            s = this.readIdentifier(s);
            if (this.lastState == 1) {
                return "";
            }
            while (this.strXPos.length() < 2 && s.length() > 0) {
                this.strXPos = String.valueOf(this.strXPos) + s.charAt(0);
                s = s.substring(1);
            }
            if (this.strXPos.length() != 2) {
                return "";
            }
            while (this.strYPos.length() < 2 && s.length() > 0) {
                this.strYPos = String.valueOf(this.strYPos) + s.charAt(0);
                s = s.substring(1);
            }
            if (this.strYPos.length() != 2) {
                return "";
            }
            this.intXPos = this.asciiHexToInt(this.strXPos);
            this.intYPos = this.asciiHexToInt(this.strYPos);
            if (this.bsxWindow != null) {
                if (!this.bsxWindow.showObject(this.strIdentifier, this.intXPos, this.intYPos)) {
                    if (this.parent != null) {
                        this.parent.writeToSocket("#RQO " + this.strIdentifier + "\r\n");
                    }
                    else {
                        this.warn("no Socket to send to");
                    }
                }
                else if (BSXModule.debug > 0) {
                    System.out.println("Query BSX-Description for " + this.strIdentifier);
                }
            }
            else {
                this.warn("no BSX Output available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@RMO")) {
            s = this.readIdentifier(s);
            if (this.lastState == 1) {
                return "";
            }
            if (this.bsxWindow != null) {
                this.bsxWindow.removeObject(this.strIdentifier);
            }
            else {
                this.warn("no BSX Output available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@RQV")) {
            if (this.parent != null) {
                this.parent.writeToSocket("#VER " + this.clientVersion + "\r\n");
            }
            else {
                this.warn("no Socket to send to");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@PRO")) {
            if (this.parent != null) {
                this.parent.writeToUser("****************************************************************************\r\n*                                                                          *\r\n*  Regenbogen BSX - Ein MultiUser Dungeon mit Grafikunterst\u00fctzung          *\r\n*  Adresse: rb.mud.de Port: 4711 Admins: mud@rb.mud.de                     *\r\n*  Hier ist was los Leute! Kommt. Lest. Schaut. Spielt. Und Redet.         *\r\n*                                                                          *\r\n* Client: von Foobar basierend auf Arbeiten von Riwa und Hate@Morgengrauen *\r\n*                                                                          *\r\n*****************************************************************Java*rulez*\r\n");
            }
            else {
                this.warn("no User to display promotion available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@SCE")) {
            s = this.readIdentifier(s);
            if (this.lastState == 1) {
                return "";
            }
            if (this.bsxWindow != null) {
                if (!this.bsxWindow.showScene(this.strIdentifier)) {
                    if (this.parent != null) {
                        this.parent.writeToSocket("#RQS " + this.strIdentifier + "\r\n");
                    }
                    else {
                        this.warn("no Socket to send to");
                    }
                }
                else if (BSXModule.debug > 0) {
                    System.out.println("Query BSX-Scene for " + this.strIdentifier);
                }
            }
            else {
                this.warn("no BSX Output available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@DFO")) {
            s = this.readIdentifier(s);
            if (this.lastState == 1) {
                return "";
            }
            s = this.readBSXData(s);
            if (this.lastState == 2) {
                return "";
            }
            final BSXInputStream bis = new BSXInputStream(new StringBufferInputStream(this.BSXData));
            Label_0861: {
                if (this.bsxWindow != null) {
                    try {
                        this.bsxWindow.addObject(this.strIdentifier, bis.readBSXGraphic());
                        break Label_0861;
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        this.resetMachine();
                        return s;
                    }
                }
                this.warn("no BSX Output available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@DFS")) {
            s = this.readIdentifier(s);
            if (this.lastState == 1) {
                return "";
            }
            s = this.readBSXData(s);
            if (this.lastState == 2) {
                return "";
            }
            final BSXInputStream bis = new BSXInputStream(new StringBufferInputStream(this.BSXData));
            Label_0975: {
                if (this.bsxWindow != null) {
                    try {
                        this.bsxWindow.addScene(this.strIdentifier, bis.readBSXGraphic());
                        break Label_0975;
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        this.resetMachine();
                        return s;
                    }
                }
                this.warn("no BSX Output available");
            }
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@PUR")) {
            this.warn("@PUR not implemented yet");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@TXT")) {
            this.warn("@TXT not implemented (and no plan for doing that)");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@BOM")) {
            this.warn("@BOM not implemented (and no plan for doing that)");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@EOM")) {
            this.warn("@EOM not implemented (and no plan for doing that)");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@LON")) {
            this.warn("@LON not implemented yet");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@LOF")) {
            this.warn("@LOF not implemented yet");
            this.resetMachine();
        }
        else if (this.lastCommand.equals("@TMS")) {
            if (this.parent != null) {
                this.parent.writeToUser("Received @TMS to end this session.\r\n");
            }
            else {
                this.warn("@TMS received! Session not terminated. Close connection manually.");
            }
            this.resetMachine();
        }
        s = String.valueOf(this.lastCommand) + s;
        this.resetMachine();
        return s;
    }
    
    public void disconnect() {
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 1001 && e.target == this.bsxButton) {
            this.bsxCheckIt ^= true;
            if (this.bsxCheckIt) {
                this.parent.writeToSocket("#VER " + this.clientVersion + "\r\n");
                this.bsxButton.setLabel("BSX aus!");
                this.bsxWindow.show();
            }
            else {
                this.parent.writeToSocket("#VER\r\n");
                this.bsxButton.setLabel("BSX an!");
                this.bsxWindow.hide();
                if (BSXModule.debug > 1) {
                    this.parent.writeToUser(String.valueOf(this.lastCommand) + this.strIdentifier + this.BSXData);
                    System.out.println("BSX off: last Input -");
                    System.out.println(String.valueOf(this.lastCommand) + this.strIdentifier + this.BSXData);
                }
            }
            this.resetMachine();
            return true;
        }
        if (e.id == 1001 && e.target == this.logButton) {
            this.logging ^= true;
            if (this.logging) {
                this.logButton.setLabel("Logging aus!");
            }
            else {
                this.logButton.setLabel("Logging an!");
            }
            return true;
        }
        return super.handleEvent(e);
    }
    
    public static void main(final String[] args) {
        final BSXModule bsxm = new BSXModule();
        BSXModule.debug = 1;
        String res = bsxm.parseString("Testing@Hawk@RQV@SCEintro.@VIO/magier:foobar.@RFS@RMO/magier:foo");
        res = String.valueOf(res) + bsxm.parseString("bar.@RFSThis is a test.@TMS@PRO@RFS@BOMTest me Carefully.");
        System.out.println("Gefiltert: " + res);
    }
    
    private String parseString(String s) {
        if (s.indexOf("@") == -1 && this.lastState == 0) {
            if (BSXModule.debug > 0) {
                this.warn("parseString done");
            }
            return s;
        }
        if (this.lastState != 0) {
            if (BSXModule.debug > 0) {
                this.warn("parseString pending Input");
            }
            s = this.decodeCommand(s);
        }
        int i = 0;
        int pos = s.indexOf("@", i);
        String res = "";
        while (pos != -1 && pos < s.length()) {
            if (BSXModule.debug > 0) {
                this.warn("parseString Command at " + pos);
            }
            res = String.valueOf(res) + s.substring(0, pos);
            if (BSXModule.debug > 0) {
                this.warn("still to parse: " + s.substring(pos));
            }
            final String tmp = this.decodeCommand(s.substring(pos));
            if (tmp.equals(s.substring(pos))) {
                ++i;
            }
            else {
                i = 0;
            }
            s = tmp;
            pos = s.indexOf("@", i);
        }
        res = String.valueOf(res) + s;
        return res;
    }
    
    private String readBSXData(String s) {
        if (this.strPolys.equals("")) {
            this.lastState = 2;
        }
        if (this.lastState == 2) {
            while (this.strPolys.length() < 2 && s.length() > 0) {
                this.BSXData = String.valueOf(this.BSXData) + s.charAt(0);
                this.strPolys = String.valueOf(this.strPolys) + s.charAt(0);
                s = s.substring(1);
            }
            if (this.strPolys.length() < 2) {
                return "";
            }
            if (BSXModule.debug > 1) {
                this.warn("PolyCount read:" + this.strPolys);
            }
            if (this.intPolys == 0) {
                this.intPolys = this.asciiHexToInt(this.strPolys);
            }
            if (BSXModule.debug > 9) {
                this.warn("equals " + this.intPolys);
            }
            while (s.length() > 0 && this.intPolys > 0) {
                while (this.strEdges.length() < 2 && s.length() > 0) {
                    this.BSXData = String.valueOf(this.BSXData) + s.charAt(0);
                    this.strEdges = String.valueOf(this.strEdges) + s.charAt(0);
                    s = s.substring(1);
                }
                if (this.strEdges.length() < 2) {
                    return "";
                }
                if (BSXModule.debug > 1) {
                    this.warn("EdgeCount read:" + this.strEdges);
                }
                if (this.intEdges == 0) {
                    this.intEdges = (this.asciiHexToInt(this.strEdges) * 2 + 1) * 2;
                }
                while (s.length() > 0 && this.intEdges > 0) {
                    this.BSXData = String.valueOf(this.BSXData) + s.charAt(0);
                    s = s.substring(1);
                    --this.intEdges;
                }
                if (this.intEdges > 0) {
                    return "";
                }
                this.strEdges = "";
                --this.intPolys;
            }
            if (this.intPolys > 0) {
                return "";
            }
            if (this.intPolys == 0) {
                this.strPolys = "";
            }
            this.lastState = -1;
        }
        return s;
    }
    
    private String readIdentifier(final String s) {
        if (this.strIdentifier.equals("")) {
            this.lastState = 1;
        }
        if (this.lastState != 1) {
            return s;
        }
        final int i = s.indexOf(".");
        if (i == -1) {
            this.strIdentifier = String.valueOf(this.strIdentifier) + s;
            return "";
        }
        this.lastState = -1;
        this.strIdentifier = String.valueOf(this.strIdentifier) + s.substring(0, i);
        return s.substring(i + 1);
    }
    
    public String receive(final String s) {
        String res;
        if (!this.bsxCheckIt) {
            res = s;
        }
        else {
            res = this.parseString(s);
        }
        if (this.logging) {
            System.out.println(res);
        }
        return res;
    }
    
    private void resetMachine() {
        if (BSXModule.debug > 0) {
            System.err.println("BSXStateMachine statustrace:");
            System.err.println("Command:    " + this.lastCommand);
            System.err.println("State:      " + this.lastState);
            System.err.println("Identifier: " + this.strIdentifier);
        }
        this.lastCommand = "";
        this.lastState = 0;
        this.strIdentifier = "";
        this.strXPos = "";
        this.intXPos = 0;
        this.strYPos = "";
        this.intYPos = 0;
        this.strPolys = "";
        this.intPolys = 0;
        this.strEdges = "";
        this.intEdges = 0;
        this.BSXData = "";
    }
    
    public void setLoader(final Object o) {
        this.parent = (telnet)o;
    }
    
    private void warn(final String s) {
        System.err.println("Warning: " + s + ".");
    }
}
