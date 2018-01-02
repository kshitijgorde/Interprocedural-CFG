import java.util.Random;
import java.io.File;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class explorer extends Applet
{
    private String m_SAppletStatus;
    private URL m_UNextPage_URL;
    private String m_SServer_URL;
    private String m_SCurrentPage_URL;
    private String m_SDID;
    private String m_SCCNName;
    private String m_SDName;
    private String m_SAccID;
    private String m_MACaddr;
    private String m_MachineName;
    private boolean bError;
    private String SError;
    private String SActionMode;
    
    private void deleteAccID() {
        new ReadRegistry().DeleteFromRegistry();
    }
    
    private void sendDID() {
        try {
            this.m_SCurrentPage_URL = this.m_SServer_URL + "WebMvrLoginDll.jsp?DID=" + this.m_SDID + "&" + "DName=" + this.m_SDName + "&" + "CCNName=" + this.m_SCCNName + "&AccID=" + this.m_SAccID + "&MacAddr=" + this.m_MACaddr + "&MachineName=" + this.m_MachineName + "&SErrorCode=" + this.SError;
            this.getAppletContext().showDocument(new URL(this.m_SCurrentPage_URL));
        }
        catch (Exception ex) {
            this.bError = true;
            this.SError = "D04";
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString(this.m_SAppletStatus, 0, 50);
    }
    
    public explorer() {
        this.m_SDID = "";
        this.m_SCCNName = "";
        this.m_SDName = "";
        this.m_SAccID = "";
        this.m_MACaddr = "";
        this.m_MachineName = "";
        this.bError = false;
        this.SError = null;
        this.SActionMode = null;
    }
    
    private void showNextPage(final String s) {
        this.m_SCurrentPage_URL = this.m_SServer_URL + s;
        boolean b = false;
        try {
            this.m_UNextPage_URL = new URL(this.m_SCurrentPage_URL);
        }
        catch (Exception ex) {
            b = true;
        }
        if (!b) {
            this.getAppletContext().showDocument(this.m_UNextPage_URL);
        }
        else {
            this.m_SAppletStatus = "----ERROR----\nGet in touch with our Technical People";
            this.repaint();
        }
    }
    
    private void readFromReg() {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(new ReadRegistry().ReadFromRegistry(), ",");
            int n = 1;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                switch (n) {
                    case 1: {
                        this.m_SDName = nextToken;
                        if (Character.isWhitespace(this.m_SDName.charAt(0)) || this.m_SDName.trim().length() == 0) {
                            this.m_SDName = null;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.m_SAccID = nextToken;
                        if (Character.isWhitespace(this.m_SAccID.charAt(0)) || this.m_SAccID.trim().length() == 0) {
                            this.m_SAccID = null;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.m_SCCNName = nextToken;
                        if (Character.isWhitespace(this.m_SCCNName.charAt(0)) || this.m_SCCNName.trim().length() == 0) {
                            this.m_SCCNName = null;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        this.m_SDID = nextToken;
                        if (Character.isWhitespace(this.m_SDID.charAt(0)) || this.m_SDID.trim().length() == 0) {
                            this.m_SDID = null;
                            break;
                        }
                        break;
                    }
                }
                ++n;
            }
        }
        catch (Exception ex) {}
    }
    
    private void updateDID_InDB() {
        try {
            this.m_SCurrentPage_URL = this.m_SServer_URL + "WebMvrUpdtDll.jsp?DeviceID=" + this.m_SDID + "&CCNName=" + this.m_SCCNName + "&AccID=" + this.m_SAccID + "&MacAddr=" + this.m_MACaddr + "&MachineName=" + this.m_MachineName + "&AM=Updt";
            this.getAppletContext().showDocument(new URL(this.m_SCurrentPage_URL));
        }
        catch (Exception ex) {
            this.bError = true;
            this.SError = "D07";
        }
    }
    
    public void init() {
        System.out.println("<ExplorerJar><init()> ----BIGIN----");
        this.m_SAppletStatus = "Redirecting...";
        this.m_SServer_URL = this.getCodeBase().toExternalForm();
        this.setBackground(Color.white);
        this.setSize(100, 100);
        if (this.getParameter("am") != null) {
            this.SActionMode = this.getParameter("am");
            System.out.println("......SActionMode...." + this.SActionMode);
        }
        if (this.SActionMode != null && !this.SActionMode.equalsIgnoreCase("cr") && !this.SActionMode.equalsIgnoreCase("dl") && !this.SActionMode.equalsIgnoreCase("rd")) {
            this.m_SAppletStatus = "----Illegal Operation----";
            return;
        }
        if (!this.bError) {
            try {
                if (!this.SActionMode.equalsIgnoreCase("find")) {}
                if (this.SActionMode.equalsIgnoreCase("rd")) {
                    System.out.println("......SActionMode....1.........");
                    this.readLMI();
                    final String string = ReadSET.getEnv("windir") + "\\ReadReg.dll";
                    System.out.println("<ExpolrerJar><init()> PATH : " + string);
                    File file = null;
                    try {
                        file = new File(string);
                    }
                    catch (Exception ex2) {
                        System.out.println("......SActionMode....2.........");
                    }
                    if (file.exists()) {
                        this.readFromReg();
                        this.sendDID();
                    }
                    else {
                        this.sendDID();
                        System.out.println("......SActionMode....3.........");
                    }
                }
                else if (this.SActionMode.equalsIgnoreCase("cr")) {
                    this.m_SCCNName = this.getParameter("DName");
                    this.m_SAccID = this.getParameter("AccID");
                    if (this.getParameter("cr") != null) {
                        this.m_SDID = this.getParameter("cr");
                        this.m_SAppletStatus = "................";
                        this.repaint();
                        this.showNextPage("WebMvrUpdt.jsp?AM=UpdtCheck");
                    }
                    else {
                        do {
                            this.m_SDID = "W" + new Random().nextLong() + new Random().nextLong();
                            this.m_SDID = this.m_SDID.replace('-', '0');
                        } while (this.m_SDID.length() <= 32);
                        this.m_SDID = this.m_SDID.substring(0, 32);
                        this.m_SDID = "";
                        this.m_SAppletStatus = "................";
                        this.repaint();
                        this.updateDID_InDB();
                    }
                }
                else {
                    this.m_SAppletStatus = "................";
                    this.repaint();
                    this.showNextPage("AdminWebLogin.jsp");
                }
            }
            catch (Exception ex) {
                System.out.println("......SActionMode....4.........");
                ex.printStackTrace();
                this.bError = true;
                this.SError = "D04";
            }
            finally {
                this.m_SAppletStatus = "Connecting...";
                this.repaint();
            }
        }
        System.out.println("<ExpolrerJar><init()> ----END----");
    }
    
    private void writeToReg() {
        new ReadRegistry().WriteToRegistry(this.m_SAccID, this.m_SDID);
    }
    
    private void readLMI() {
        System.out.println("......readLMI....1.........");
        this.m_MACaddr = ReadLocalMachineInformation.getMACAddress().trim();
        System.out.println("......readLMI....MAC........." + this.m_MACaddr);
        this.m_MachineName = ReadLocalMachineInformation.getMachineName().trim();
    }
}
