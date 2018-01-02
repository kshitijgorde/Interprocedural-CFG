import java.awt.Color;
import javax.swing.JComboBox;
import java.util.Calendar;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import javax.swing.JProgressBar;
import javax.swing.Icon;
import java.awt.event.WindowEvent;
import java.awt.Container;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.GregorianCalendar;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.net.NetworkInterface;
import java.net.InetAddress;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.net.URL;
import java.net.URLDecoder;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;
import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class XDFRead extends Applet implements ActionListener, Runnable
{
    private static final long serialVersionUID = 1L;
    private String m_strInstallPath;
    private String m_strTempPath;
    private static String READER_STARTER_PATH;
    public String m_strXDFUrl;
    public Thread checkRead;
    public String m_strBookNum;
    public String m_strUserGUID;
    public String m_strDrmServerAddress;
    public String m_strId;
    public String m_strPassword;
    public String m_strHDDVolumeNo;
    public String m_strHDDSerialNo;
    public String m_strIP;
    public String m_strMacAddress;
    public String m_strComName;
    public String m_strComUserName;
    public boolean m_bCodec;
    public String m_strTitle;
    public String m_strSearchWord;
    public String m_strAuthor;
    public String m_strPublisher;
    public String m_strPaidDate;
    public String m_strStartDate;
    public String m_strExpiredDate;
    public String m_strDrmKey;
    public String m_strLibCertURL;
    public boolean m_bUnlimited;
    public int nServiceType;
    
    public void initParam() {
        this.m_strXDFUrl = "";
        this.m_strBookNum = "";
        this.m_strUserGUID = "";
        this.m_strDrmServerAddress = "";
        this.m_strId = "Arabia";
        this.m_strPassword = "test";
        this.m_strHDDVolumeNo = "";
        this.m_strHDDSerialNo = "8df6bca32aba4e";
        this.m_strIP = "";
        this.m_strMacAddress = "";
        this.m_strSearchWord = "";
        this.m_strStartDate = "";
        this.m_strExpiredDate = "";
        this.m_strPaidDate = "";
        this.m_strPublisher = "";
        this.m_strSearchWord = "";
        this.m_strTitle = "";
        this.m_strAuthor = "";
        this.m_bUnlimited = false;
        this.nServiceType = 0;
        this.m_strLibCertURL = "";
    }
    
    @Override
    public void init() {
        this.resize(400, 600);
        this.initParam();
        (this.checkRead = new Thread(this)).start();
        final Button button = new Button("test post data");
        this.add(button);
        button.setActionCommand("33");
        button.addActionListener(this);
        final Button button2 = new Button("StartReaderEx");
        this.add(button2);
        button2.setActionCommand("1");
        button2.addActionListener(this);
        final Button button3 = new Button("StartReaderEx2");
        this.add(button3);
        button3.setActionCommand("2");
        button3.addActionListener(this);
        final Button button4 = new Button("XDFDown");
        this.add(button4);
        button4.setActionCommand("3");
        button4.addActionListener(this);
        final Button button5 = new Button("XDFDown2");
        this.add(button5);
        button5.setActionCommand("4");
        button5.addActionListener(this);
        final Button button6 = new Button("StartReaderCert");
        this.add(button6);
        button6.setActionCommand("5");
        button6.addActionListener(this);
        final Button button7 = new Button("StartReader");
        this.add(button7);
        button7.setActionCommand("6");
        button7.addActionListener(this);
        final Button button8 = new Button("\uc885\ub8cc");
        this.add(button8);
        button8.setActionCommand("99");
        button8.addActionListener(this);
        this.setHardwareInfo();
        try {
            final File tempFile = File.createTempFile("mts", ".dat");
            this.m_strTempPath = tempFile.getParent();
            tempFile.delete();
        }
        catch (IOException ex) {}
    }
    
    @Override
    public void run() {
        while (true) {
            if (this.nServiceType == 1) {
                this.setHardwareInfo();
                try {
                    final File tempFile = File.createTempFile("mts", ".dat");
                    this.m_strTempPath = tempFile.getParent();
                    tempFile.delete();
                }
                catch (IOException ex2) {}
                final String string = "hd=" + this.m_strHDDSerialNo + "&mac=" + this.m_strMacAddress + "&booknum=" + this.m_strBookNum + "&userguid=" + this.m_strUserGUID + "&vol=" + this.m_strHDDVolumeNo + "&ip=" + this.m_strIP + "&tmstart=" + this.m_strStartDate + "&tmend=" + this.m_strExpiredDate + "&comname=" + this.m_strComName + "&comusername=" + this.m_strComUserName + "&downflag=N&unlimited=N";
                final XDFDownDlg xdfDownDlg = new XDFDownDlg(this, false);
                xdfDownDlg.setTitle("\ub3c4\uc11c\ubcf4\uae30");
                xdfDownDlg.SetDrmKey(this.m_strDrmKey);
                xdfDownDlg.SetQueryParam(string);
                xdfDownDlg.setModal(true);
                xdfDownDlg.setVisible(true);
                this.m_strStartDate = "";
                this.m_strExpiredDate = "";
                this.initParam();
            }
            if (this.nServiceType == 2) {
                this.setHardwareInfo();
                try {
                    final File tempFile2 = File.createTempFile("mts", ".dat");
                    this.m_strTempPath = tempFile2.getParent();
                    tempFile2.delete();
                }
                catch (IOException ex3) {}
                final String string2 = "hd=" + this.m_strHDDSerialNo + "&mac=" + this.m_strMacAddress + "&booknum=" + this.m_strBookNum + "&userguid=" + this.m_strUserGUID + "&vol=" + this.m_strHDDVolumeNo + "&ip=" + this.m_strIP + "&tmstart=" + this.m_strStartDate + "&tmend=" + this.m_strExpiredDate + "&comname=" + this.m_strComName + "&comusername=" + this.m_strComUserName + "&downflag=N&unlimited=N";
                final XDFDownDlg xdfDownDlg2 = new XDFDownDlg(this, false);
                xdfDownDlg2.setTitle("\ub3c4\uc11c\ubcf4\uae30");
                xdfDownDlg2.SetQueryParam(string2);
                xdfDownDlg2.setModal(true);
                xdfDownDlg2.setVisible(true);
                this.m_strStartDate = "";
                this.m_strExpiredDate = "";
                this.initParam();
            }
            if (this.nServiceType == 3) {
                this.setHardwareInfo();
                try {
                    final File tempFile3 = File.createTempFile("mts", ".dat");
                    this.m_strTempPath = tempFile3.getParent();
                    tempFile3.delete();
                }
                catch (IOException ex4) {}
                final XDFConfigDlg xdfConfigDlg = new XDFConfigDlg();
                xdfConfigDlg.SetXDFDrm(this.m_strDrmKey);
                xdfConfigDlg.pack();
                xdfConfigDlg.setModal(true);
                xdfConfigDlg.setVisible(true);
                this.m_strBookNum = "";
                this.initParam();
            }
            if (this.nServiceType == 4) {
                this.setHardwareInfo();
                try {
                    final File tempFile4 = File.createTempFile("mts", ".dat");
                    this.m_strTempPath = tempFile4.getParent();
                    tempFile4.delete();
                }
                catch (IOException ex5) {}
                final XDFConfigDlg xdfConfigDlg2 = new XDFConfigDlg();
                xdfConfigDlg2.pack();
                xdfConfigDlg2.setModal(true);
                xdfConfigDlg2.setVisible(true);
                this.m_strBookNum = "";
                this.initParam();
            }
            if (this.nServiceType == 5) {
                this.setHardwareInfo();
                try {
                    final File tempFile5 = File.createTempFile("mts", ".dat");
                    this.m_strTempPath = tempFile5.getParent();
                    tempFile5.delete();
                }
                catch (IOException ex6) {}
                int i = 0;
                int index = -1;
                String substring = "";
                String s = this.m_strXDFUrl;
                while (i < this.m_strXDFUrl.length()) {
                    if ('/' == s.charAt(0)) {
                        index = s.indexOf(61);
                        if (-1 == index) {
                            break;
                        }
                    }
                    if (-1 != index) {
                        substring = s.substring(1, index);
                    }
                    if (0 == substring.compareToIgnoreCase("FILE")) {
                        if ('\"' == s.charAt(index + 1)) {
                            final int index2 = s.indexOf(34, index + 2);
                            if (-1 == index2) {
                                break;
                            }
                            s = s.substring(index + 2, index2).replace("/FILE=", "");
                            break;
                        }
                        else {
                            final int index3 = s.indexOf(32, index + 1);
                            if (-1 == index3) {
                                break;
                            }
                            s = s.substring(index + 1, index3).replace("/FILE=", "");
                            break;
                        }
                    }
                    else {
                        ++i;
                    }
                }
                final String string3 = "usermac=" + this.m_strMacAddress + "&userip=" + this.m_strIP + "&userhdd=" + this.m_strHDDSerialNo + "&userhdv=" + this.m_strHDDVolumeNo + "&xdfpath=" + s;
                new XDFDownDlg(this, false).setTitle("\ub3c4\uc11c\ubcf4\uae30");
                final EspressoBean espressoBean = new EspressoBean();
                espressoBean.setDrmServerAddress(this.m_strLibCertURL);
                espressoBean.SetRetValUseCodec(true);
                final String targetURL = espressoBean.getTargetURL(string3, true);
                if (targetURL.compareToIgnoreCase("invalid Lib") == 0) {
                    JOptionPane.showMessageDialog(null, "\uc0ac\uc6a9\ud558\uace0 \uacc4\uc2e0 \ub3c4\uc11c\uad00\uc758 \uc778\uc99d\uc815\ubcf4\ub97c \ud655\uc778 \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. \uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758 \ud558\uc2ed\uc2dc\uc624.", "\uc624\ub958", 0);
                }
                else if (targetURL.compareToIgnoreCase("invalid LibCnt") == 0) {
                    JOptionPane.showMessageDialog(null, "\ub2e4\ub978 \uc0ac\uc6a9\uc790\uac00 \ubaa8\ub450 \uc5f4\ub78c \uc911\uc785\ub2c8\ub2e4.", "\uc624\ub958", 0);
                }
                else if (targetURL.compareToIgnoreCase("invalid") == 0) {
                    JOptionPane.showMessageDialog(null, "\uc11c\ubc84\uc640\uc758 \ud1b5\uc2e0\uc911 \uc624\ub958\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4. \uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758 \ud558\uc2ed\uc2dc\uc624.", "\uc624\ub958", 0);
                }
                else if (targetURL.compareToIgnoreCase("valid") == 0) {
                    this.StartReader(this.m_strXDFUrl);
                    this.initParam();
                }
                else {
                    JOptionPane.showMessageDialog(null, "\uc798\ubabb\ub41c \uc694\uccad\uc785\ub2c8\ub2e4. \uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758 \ud558\uc2ed\uc2dc\uc624.", "\uc624\ub958", 0);
                }
                this.initParam();
            }
            if (this.nServiceType == 33) {
                try {
                    URLDecoder.decode("http://ebl.bluemountainsoft.com:8080/test.php");
                    final URLConnection openConnection = new URL("http", "210.219.252.213", 8080, "/test.php").openConnection();
                    openConnection.setDoOutput(true);
                    openConnection.setDoInput(true);
                    openConnection.setAllowUserInteraction(false);
                    final String s2 = "param=test";
                    final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openConnection.getOutputStream());
                    bufferedOutputStream.write(s2.getBytes());
                    bufferedOutputStream.close();
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                    String string4 = "";
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        string4 += line;
                    }
                    bufferedReader.close();
                }
                catch (Exception ex7) {}
                this.nServiceType = 0;
            }
            if (this.m_strXDFUrl != "" && this.nServiceType != 5) {
                this.StartReader();
                this.initParam();
            }
            try {
                Thread.sleep(300L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void SetXDFUrl(final String strXDFUrl) {
        this.m_strXDFUrl = strXDFUrl;
    }
    
    public void SetXDFEx(final String strBookNum, final String strUserGUID, final String strDrmServerAddress, final String strTitle, final String strAuthor, final String strPublisher, final String strPaidDate, final String strSearchWord, final String strDrmKey) {
        this.m_strBookNum = strBookNum;
        this.m_strUserGUID = strUserGUID;
        this.m_strDrmServerAddress = strDrmServerAddress;
        this.m_strTitle = strTitle;
        this.m_strAuthor = strAuthor;
        this.m_strPublisher = strPublisher;
        this.m_strPaidDate = strPaidDate;
        this.m_strSearchWord = strSearchWord;
        this.m_strDrmKey = strDrmKey;
        this.m_bCodec = true;
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        final long currentTimeMillis = System.currentTimeMillis();
        this.m_strStartDate = simpleDateFormat.format(currentTimeMillis);
        this.m_strExpiredDate = simpleDateFormat.format(currentTimeMillis + 600000L);
        this.nServiceType = 1;
    }
    
    public void SetXDFEx2(final String strBookNum, final String strUserGUID, final String strDrmServerAddress, final String strTitle, final String strAuthor, final String strPublisher, final String strPaidDate, final String strSearchWord, final String s) {
        this.m_strBookNum = strBookNum;
        this.m_strUserGUID = strUserGUID;
        this.m_strDrmServerAddress = strDrmServerAddress;
        this.m_strTitle = strTitle;
        this.m_strAuthor = strAuthor;
        this.m_strPublisher = strPublisher;
        this.m_strPaidDate = strPaidDate;
        this.m_strSearchWord = strSearchWord;
        this.m_bCodec = s.toLowerCase().contains("codec");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        final long currentTimeMillis = System.currentTimeMillis();
        this.m_strStartDate = simpleDateFormat.format(currentTimeMillis);
        this.m_strExpiredDate = simpleDateFormat.format(currentTimeMillis + 600000L);
        this.nServiceType = 2;
    }
    
    public void SetXDFForDownload(final String strBookNum, final String strUserGUID, final String strDrmServerAddress, final String strTitle, final String strAuthor, final String strPublisher, final String strPaidDate, final String strSearchWord, final String strDrmKey) {
        this.m_strBookNum = strBookNum;
        this.m_strUserGUID = strUserGUID;
        this.m_strDrmServerAddress = strDrmServerAddress;
        this.m_strTitle = strTitle;
        this.m_strAuthor = strAuthor;
        this.m_strPublisher = strPublisher;
        this.m_strPaidDate = strPaidDate;
        this.m_strSearchWord = strSearchWord;
        this.m_strDrmKey = strDrmKey;
        this.m_bCodec = true;
        this.nServiceType = 3;
    }
    
    public void SetXDFForDownload2(final String strBookNum, final String strUserGUID, final String strDrmServerAddress, final String strTitle, final String strAuthor, final String strPublisher, final String strPaidDate, final String strSearchWord, final String s) {
        this.m_strBookNum = strBookNum;
        this.m_strUserGUID = strUserGUID;
        this.m_strDrmServerAddress = strDrmServerAddress;
        this.m_strTitle = strTitle;
        this.m_strAuthor = strAuthor;
        this.m_strPublisher = strPublisher;
        this.m_strPaidDate = strPaidDate;
        this.m_strSearchWord = strSearchWord;
        this.m_bCodec = s.toLowerCase().contains("codec");
        this.nServiceType = 4;
        this.setHardwareInfo();
        try {
            final File tempFile = File.createTempFile("mts", ".dat");
            this.m_strTempPath = tempFile.getParent();
            tempFile.delete();
        }
        catch (IOException ex) {}
    }
    
    public void SetXDFCert(final String strLibCertURL, final String strXDFUrl) {
        this.m_strLibCertURL = strLibCertURL;
        this.m_strXDFUrl = strXDFUrl;
        this.nServiceType = 5;
    }
    
    public String getVolumeSerialId() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cmd /C dir").getInputStream()));
            int n = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (++n < 2) {
                    continue;
                }
                if (n > 2) {
                    break;
                }
                return line.substring(line.lastIndexOf(32) + 1).replace("-", "");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    public void getNetworkInfo() {
        String string = "";
        try {
            final InetAddress localHost = InetAddress.getLocalHost();
            final NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(localHost);
            this.m_strIP = localHost.getHostAddress();
            final byte[] hardwareAddress = byInetAddress.getHardwareAddress();
            for (int i = 0; i < hardwareAddress.length; ++i) {
                string += String.format("%02x", hardwareAddress[i]);
            }
            this.m_strMacAddress = string;
        }
        catch (Exception ex) {
            this.m_strIP = "";
            this.m_strMacAddress = "";
        }
    }
    
    public void setHardwareInfo() {
        this.m_strHDDVolumeNo = this.getVolumeSerialId();
        this.getNetworkInfo();
        this.m_strComUserName = System.getProperty("user.name");
        try {
            this.m_strComName = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception ex) {
            this.m_strComName = "";
            this.m_strComUserName = "";
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final int int1 = Integer.parseInt(actionEvent.getActionCommand());
        if (int1 != 0) {
            this.SetXDFEx("380CB769-8869-4613-A88E-051BF34B0BD0", "31DAEC46-DB13-4451-A435-3568D4003CDF", "http://ebl.bluemountainsoft.com:8080/xdfdrm/SetXDFDRM.php", "test", "\uc800\uc790\uba85", "\ucd9c\ud310\uc0ac", "200904071626", "", "E76F0DAB-FEA1-4895-8DEC-BCB31BB6BABF");
        }
        else if (2 == int1) {
            this.SetXDFEx2("09032738", "31DAEC46-DB13-4451-A435-3568D4003CDF", "http://xdfdrm.coffeebook.co.kr/SetXDFDRM.asp", "\ub3c5\uc11c\uacbd\uc601", "\uc800\uc790\uba85", "\ucd9c\ud310\uc0ac", "200904071626", "", "codec");
        }
        else if (3 == int1) {
            this.SetXDFForDownload("380CB769-8869-4613-A88E-051BF34B0BD0", "31DAEC46-DB13-4451-A435-3568D4003CDF", "http://ebl.bluemountainsoft.com:8080/xdfdrm/SetXDFDRM.php", "test", "\uc800\uc790\uba85", "\ucd9c\ud310\uc0ac", "200904071626", "", "E76F0DAB-FEA1-4895-8DEC-BCB31BB6BABF");
        }
        else if (4 == int1) {
            this.SetXDFForDownload2("09032738", "31DAEC46-DB13-4451-A435-3568D4003CDF", "http://xdfdrm.coffeebook.co.kr/SetXDFDRM.asp", "\ub3c5\uc11c\uacbd\uc601", "\uc800\uc790\uba85", "\ucd9c\ud310\uc0ac..", "200904071626", "", "codec");
        }
        else if (5 == int1) {
            this.SetXDFCert("http://cbt.changwon-lib.or.kr/EXEBLibCertify/verify.asp", "/FILE=\"http://cbt.changwon-lib.or.kr/EXEBLibCertify/xdf/2009/05/15/0001/07B9311F-3BC7-450c-BC6F-DACC421972CD/07B9311F-3BC7-450c-BC6F-DACC421972CD.xdf\" /PAGE=\"2\" /TITLE=\"title\" /WORD=\"xdf\"");
        }
        else if (6 == int1) {
            this.SetXDFUrl("/FILE=\"http://sumatra.bluemountainsoft.com/testfilter/1.xdf\" /PAGE=\"5\" /TITLE=\"title\" /WORD=\"xdf\"");
        }
        else if (33 == int1) {
            this.nServiceType = 33;
        }
        else if (99 == int1) {
            System.exit(0);
        }
    }
    
    @Override
    public void paint(final Graphics graphics) {
        graphics.drawString("Espresso Agent Java, Blue Mountain Soft Inc.", 10, 150);
        graphics.drawString(this.m_strHDDVolumeNo, 10, 170);
        graphics.drawString(this.m_strIP, 10, 190);
        graphics.drawString(this.m_strMacAddress, 10, 210);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        final String format = simpleDateFormat.format(new Date());
        graphics.drawString(this.m_strStartDate, 10, 230);
        graphics.drawString(this.m_strExpiredDate, 10, 260);
        graphics.drawString("\ud604\uc7ac\uc2dc\uac04:" + format, 10, 280);
    }
    
    public String getReaderInstallPath() {
        final File[] listRoots = File.listRoots();
        for (int i = 1; i <= listRoots.length; ++i) {
            if (listRoots[i - 1].getPath().charAt(0) != 'A' && listRoots[i - 1].getPath().charAt(0) != 'B') {
                final String string = listRoots[i - 1].getPath() + XDFRead.READER_STARTER_PATH;
                if (new File(string).exists()) {
                    return string;
                }
            }
        }
        return "";
    }
    
    public void StartReader() {
        if (this.m_strXDFUrl == "") {
            return;
        }
        this.m_strInstallPath = this.getReaderInstallPath();
        String s;
        if (this.m_strXDFUrl.indexOf("FILE") == -1) {
            s = "/FILE=\"" + this.m_strXDFUrl + "\"";
        }
        else {
            s = this.m_strXDFUrl;
        }
        if (this.m_strTitle.length() > 0) {
            s = s + " /TITLE=\"" + this.m_strTitle + "\"";
        }
        if (this.m_strSearchWord.length() > 0) {
            s = s + " /WORD=\"" + this.m_strSearchWord + "\"";
        }
        if (this.m_strInstallPath == "") {
            final File file = new File(this.m_strTempPath + "\\updater.exe");
            if (file.exists() && !file.delete()) {
                JOptionPane.showMessageDialog(this, "\uc2dc\uc2a4\ud15c \uc784\uc2dc \ud3f4\ub354\ub85c Espresso Auto Updater\ub97c\n\ub2e4\uc6b4\ub85c\ub4dc\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.\n\uc774\ubbf8 \uc2e4\ud589 \uc911\uc778\uc9c0 \ud655\uc778\ud558\uc5ec \uc8fc\uc2ed\uc2dc\uc624.", "Espresso Agent", 0);
                return;
            }
            final DownDlg downDlg = new DownDlg(this.m_strTempPath);
            downDlg.dlg.setLayout(downDlg.bl1);
            downDlg.dlg.add("Center", downDlg.progressBar);
            downDlg.p.setLayout(downDlg.gb1);
            downDlg.p.add(downDlg.btncancel);
            downDlg.dlg.add("South", downDlg.p);
            downDlg.dlg.setVisible(true);
            try {
                String s2 = this.m_strTempPath + "\\updater.exe \"@install\" \"@app=ReaderStarter.exe\"";
                if (this.m_strXDFUrl != "") {
                    s2 = s2 + " \"@cmd=" + s + "\"";
                }
                Runtime.getRuntime().exec(s2);
            }
            catch (IOException ex2) {}
        }
        else {
            try {
                if (this.m_strXDFUrl.compareToIgnoreCase("install") == 0) {
                    this.m_strXDFUrl = "";
                }
                Runtime.getRuntime().exec(this.m_strInstallPath + " " + s);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void StartReader(final String s) {
        if (s == "") {
            return;
        }
        this.m_strInstallPath = this.getReaderInstallPath();
        if (this.m_strInstallPath == "") {
            final File file = new File(this.m_strTempPath + "\\updater.exe");
            if (file.exists() && !file.delete()) {
                JOptionPane.showMessageDialog(this, "\uc2dc\uc2a4\ud15c \uc784\uc2dc \ud3f4\ub354\ub85c Espresso Auto Updater\ub97c\n\ub2e4\uc6b4\ub85c\ub4dc\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.\n\uc774\ubbf8 \uc2e4\ud589 \uc911\uc778\uc9c0 \ud655\uc778\ud558\uc5ec \uc8fc\uc2ed\uc2dc\uc624.", "Espresso Agent", 0);
                return;
            }
            final DownDlg downDlg = new DownDlg(this.m_strTempPath);
            downDlg.dlg.setLayout(downDlg.bl1);
            downDlg.dlg.add("Center", downDlg.progressBar);
            downDlg.p.setLayout(downDlg.gb1);
            downDlg.p.add(downDlg.btncancel);
            downDlg.dlg.add("South", downDlg.p);
            downDlg.dlg.setVisible(true);
            try {
                String s2 = this.m_strTempPath + "\\updater.exe \"@install\" \"@app=ReaderStarter.exe\"";
                if (this.m_strXDFUrl != "") {
                    s2 = s2 + " \"@cmd=" + s + "\"";
                }
                Runtime.getRuntime().exec(s2);
            }
            catch (IOException ex2) {}
        }
        else {
            try {
                if (this.m_strXDFUrl.compareToIgnoreCase("install") == 0) {
                    this.m_strXDFUrl = "";
                }
                Runtime.getRuntime().exec(this.m_strInstallPath + " " + s);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static {
        XDFRead.READER_STARTER_PATH = "Program Files\\Blue Mountain Soft\\Espresso XDF\\Reader\\ReaderStarter.exe";
    }
    
    class XDFConfigDlg extends JDialog implements WindowListener, Runnable, ActionListener
    {
        private static final long serialVersionUID = 1L;
        private JSpinner m_spinnerDate;
        private JSpinner m_spinnerTime;
        private JButton m_jbtnCal;
        private SpinnerDateModel m_dateModel;
        private JTextField m_jtfPath;
        private JTextField m_jtfName;
        private JCheckBox m_jcbAfterOpen;
        private String m_strDrmKey;
        private JRadioButton m_jrbtnLastDown;
        
        public void SetXDFDrm(final String strDrmKey) {
            if (strDrmKey.length() > 0) {
                this.m_strDrmKey = strDrmKey;
            }
            else {
                this.m_strDrmKey = "";
            }
        }
        
        public void setDate(final int n, final int n2, final int n3) {
            this.m_dateModel.setValue(new GregorianCalendar(n, n2, n3).getTime());
            this.m_spinnerDate.updateUI();
        }
        
        private void setCenter() {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = this.getSize();
            this.setLocation((int)(screenSize.getWidth() - size.getWidth()) / 2, (int)(screenSize.getHeight() - size.getHeight()) / 2);
        }
        
        public XDFConfigDlg() {
            this.m_strDrmKey = "";
            this.buildUI();
            this.pack();
            this.setCenter();
        }
        
        public void buildUI() {
            this.setTitle("XDF \ub2e4\uc6b4\ub85c\ub4dc");
            this.setPreferredSize(new Dimension(450, 610));
            final Container contentPane = this.getContentPane();
            contentPane.setLayout(new BorderLayout());
            final JPanel panel = new JPanel();
            panel.setLayout(null);
            final JPanel panel2 = new JPanel();
            panel2.setBounds(10, 10, 420, 120);
            panel2.setLayout(new GridLayout(4, 0, 0, 0));
            panel2.setBorder(new TitledBorder("\uc11c\uc9c0\uc815\ubcf4"));
            final JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout(0));
            panel3.add(new JLabel("\uc81c\ubaa9 : ", 2));
            panel3.add(new JLabel(XDFRead.this.m_strTitle, 2));
            final JPanel panel4 = new JPanel();
            panel4.setLayout(new FlowLayout(0));
            panel4.add(new JLabel("\uc800\uc790 :", 2));
            panel4.add(new JLabel(XDFRead.this.m_strAuthor, 2));
            final JPanel panel5 = new JPanel();
            panel5.setLayout(new FlowLayout(0));
            panel5.add(new JLabel("\ucd9c\ud310\uc0ac :", 2));
            panel5.add(new JLabel(XDFRead.this.m_strPublisher, 2));
            final JPanel panel6 = new JPanel();
            panel6.setLayout(new FlowLayout(0));
            panel6.add(new JLabel("\uad6c\uc785\uc77c :", 2));
            panel6.add(new JLabel(XDFRead.this.m_strPaidDate.substring(0, 4) + "\ub144 " + XDFRead.this.m_strPaidDate.substring(4, 6) + "\uc6d4 " + XDFRead.this.m_strPaidDate.substring(6, 8) + "\uc77c " + XDFRead.this.m_strPaidDate.substring(8, 10) + "\uc2dc " + XDFRead.this.m_strPaidDate.substring(10, 12) + "\ubd84 ", 2));
            panel2.add(panel3);
            panel2.add(panel4);
            panel2.add(panel5);
            panel2.add(panel6);
            final JPanel panel7 = new JPanel();
            panel7.setBorder(new TitledBorder("\uc720\ud6a8 \uae30\uac04 \uc124\uc815"));
            panel7.setBounds(10, 140, 420, 260);
            panel7.setLayout(null);
            final JLabel label = new JLabel("\ub3c4\uc11c\ub97c \ud604\uc7ac \ucef4\ud4e8\ud130\uc5d0\uc11c \uc5b8\uc81c\uae4c\uc9c0 \uc77d\uc744 \uac83\uc778\uc9c0 \uc124\uc815\ud574 \uc8fc\uc138\uc694.", 2);
            final JRadioButton radioButton = new JRadioButton("\uc77c\ubc18 \ub2e4\uc6b4\ub85c\ub4dc\ud558\uae30");
            radioButton.setSelected(true);
            radioButton.setActionCommand("normaldownload");
            radioButton.addActionListener(this);
            final JLabel label2 = new JLabel("<html>\ud604\uc7ac\ub0a0\uc9dc\ub85c\ubd80\ud130 1\ub144 \ub0b4\uc5d0\uc11c \uc790\uc720\ub86d\uac8c \uae30\uac04\uc744 \uc124\uc815\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4.<br>\uc124\uc815\ud55c \uae30\uac04\ub3d9\uc548 \ub2e4\ub978 \ucef4\ud4e8\ud130\ub85c \ub2e4\uc6b4\ub85c\ub4dc\ud560 \uc218 \uc5c6\uc73c\uba70 \uc778\ud130\ub137<BR> \uc11c\uc7ac\uc5d0\uc11c\ub3c4 \ub3c4\uc11c\ubcf4\uae30\ub97c \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.</html>", 2);
            final Date date = new Date(System.currentTimeMillis() + 600000L);
            (this.m_dateModel = new SpinnerDateModel()).setValue(date);
            (this.m_spinnerDate = new JSpinner(this.m_dateModel)).setEditor(new JSpinner.DateEditor(this.m_spinnerDate, "yyyy-MM-dd"));
            this.m_spinnerDate.setPreferredSize(new Dimension(110, 25));
            final SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
            spinnerDateModel.setValue(date);
            (this.m_spinnerTime = new JSpinner(spinnerDateModel)).setEditor(new JSpinner.DateEditor(this.m_spinnerTime, "a h:m:s"));
            this.m_spinnerTime.setPreferredSize(new Dimension(120, 25));
            (this.m_jbtnCal = new JButton("\ub2ec\ub825")).setActionCommand("cal");
            this.m_jbtnCal.addActionListener(this);
            final JPanel panel8 = new JPanel(new FlowLayout(1));
            panel8.add(this.m_spinnerDate);
            panel8.add(this.m_jbtnCal);
            panel8.add(this.m_spinnerTime);
            this.m_jrbtnLastDown = new JRadioButton("\ub9c8\uc9c0\ub9c9\uc73c\ub85c \ub2e4\uc6b4\ub85c\ub4dc\ud558\uae30");
            final JLabel label3 = new JLabel("<html>\uc720\ud6a8 \uae30\uac04\uc774 '\ubb34\uc81c\ud55c'\uc73c\ub85c \uc124\uc815\ub429\ub2c8\ub2e4.<br>\ub354 \uc774\uc0c1 \ub2e4\ub978 \ucef4\ud4e8\ud130\uc5d0\uc11c\ub294 \uc774 \ub3c4\uc11c\ub97c \ub2e4\uc6b4\ub85c\ub4dc\ud558\uac70\ub098 \uc77d\uc744 \uc218<BR>\uc5c6\uc2b5\ub2c8\ub2e4.</html>", 2);
            this.m_jrbtnLastDown.setActionCommand("lastdownload");
            this.m_jrbtnLastDown.addActionListener(this);
            label.setBounds(10, 20, 400, 30);
            radioButton.setBounds(10, 50, 400, 25);
            label2.setBounds(15, 55, 400, 90);
            panel8.setBounds(10, 125, 350, 35);
            this.m_jrbtnLastDown.setBounds(10, 170, 400, 25);
            label3.setBounds(15, 175, 400, 90);
            panel7.add(label);
            panel7.add(radioButton);
            panel7.add(label2);
            panel7.add(panel8);
            panel7.add(this.m_jrbtnLastDown);
            panel7.add(label3);
            final ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radioButton);
            buttonGroup.add(this.m_jrbtnLastDown);
            final JPanel panel9 = new JPanel();
            panel9.setBounds(10, 410, 420, 120);
            panel9.setBorder(new TitledBorder("\ub2e4\uc6b4\ub85c\ub4dc \uc704\uce58 \uc124\uc815"));
            panel9.setLayout(null);
            final JLabel label4 = new JLabel("\uc800\uc7a5\uc704\uce58 : ", 2);
            (this.m_jtfPath = new JTextField(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + "XDF \uc804\uc790\ucc45 \uc11c\uc7ac")).setPreferredSize(new Dimension(260, 25));
            final JButton button = new JButton("\ubcc0\uacbd");
            button.setActionCommand("finddir");
            button.addActionListener(this);
            final JPanel panel10 = new JPanel(new FlowLayout(0));
            panel10.setBounds(10, 20, 400, 40);
            panel10.add(label4);
            panel10.add(this.m_jtfPath);
            panel10.add(button);
            final JLabel label5 = new JLabel("\ud30c\uc77c\uc774\ub984 :", 2);
            (this.m_jtfName = new JTextField(XDFRead.this.m_strTitle + ".xdf")).setPreferredSize(new Dimension(320, 25));
            final JPanel panel11 = new JPanel(new FlowLayout(0));
            panel11.setBounds(10, 55, 400, 30);
            panel11.add(label5);
            panel11.add(this.m_jtfName);
            (this.m_jcbAfterOpen = new JCheckBox("\ub2e4\uc6b4\ub85c\ub4dc \uc644\ub8cc \ud6c4 \ub3c4\uc11c \uc5f4\uae30")).setActionCommand("afteropenxdf");
            this.m_jcbAfterOpen.addActionListener(this);
            final JPanel panel12 = new JPanel(new FlowLayout(0));
            panel12.setBounds(10, 85, 400, 30);
            panel12.add(this.m_jcbAfterOpen);
            panel9.add(panel10);
            panel9.add(panel11);
            panel9.add(panel12);
            final JPanel panel13 = new JPanel(new FlowLayout(1));
            panel13.setBounds(10, 530, 420, 50);
            panel13.setLayout(null);
            final JPanel panel14 = new JPanel();
            panel14.setBounds(10, 5, 420, 40);
            panel14.setAlignmentX(0.5f);
            final JButton button2 = new JButton("\ub2e4\uc6b4\ub85c\ub4dc");
            button2.setActionCommand("download");
            button2.addActionListener(this);
            final JButton button3 = new JButton("\ucde8\uc18c");
            button3.setActionCommand("cancel");
            button3.addActionListener(this);
            panel14.add(button2);
            panel14.add(button3);
            panel13.add(panel14);
            panel.add(panel2);
            panel.add(panel7);
            panel.add(panel9);
            panel.add(panel13);
            contentPane.add("Center", panel);
        }
        
        @Override
        public void windowOpened(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowActivated(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
        
        public void windowGainedFocus(final WindowEvent windowEvent) {
        }
        
        public void windowLostFocus(final WindowEvent windowEvent) {
        }
        
        public void windowStateChanged(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
        }
        
        @Override
        public void run() {
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand == "download") {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                final String[] array = { "\uc608", "\uc544\ub2c8\uc624" };
                if (JOptionPane.showOptionDialog(null, "\uc774 \ub3c4\uc11c\ub294 [ " + simpleDateFormat.format(this.m_spinnerDate.getValue()) + " ]\uae4c\uc9c0 " + " \uc778\ud130\ub137 \uc11c\uc7ac\uc5d0\uc11c \ub3c4\uc11c\ubcf4\uae30\ub97c \ud560 \uc218 \uc5c6\uc73c\uba70\n" + "\ub2e4\ub978 \ucef4\ud4e8\ud130\uc5d0\uc11c\ub294 \ub2e4\uc6b4\ub85c\ub4dc\ub3c4 \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. ", "\ud655\uc778", 0, 2, null, array, array[1]) != 0) {
                    return;
                }
                if (JOptionPane.showOptionDialog(null, "\ub2e4\uc6b4\ub85c\ub4dc\ub97c \uc2dc\uc791\ud558\uba74 \ucde8\uc18c\ud560 \uc218 \uc5c6\uc73c\uba70 \uac15\uc81c\ub85c \ub2e4\uc6b4\ub85c\ub4dc\ub97c \uc885\ub8cc\ud558\ub354\ub77c\ub3c4 \ucde8\uc18c\ub418\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.\n\n\uc815\ub9d0 \ub2e4\uc6b4\ub85c\ub4dc \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?", "\ud655\uc778", 0, 2, null, array, array[1]) == 0) {
                    final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmm");
                    final SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyyMMdd");
                    final SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("HHmm");
                    simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                    simpleDateFormat3.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                    simpleDateFormat4.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                    final String string = "hd=" + XDFRead.this.m_strHDDSerialNo + "&mac=" + XDFRead.this.m_strMacAddress + "&booknum=" + XDFRead.this.m_strBookNum + "&userguid=" + XDFRead.this.m_strUserGUID + "&vol=" + XDFRead.this.m_strHDDVolumeNo + "&ip=" + XDFRead.this.m_strIP + "&tmstart=" + simpleDateFormat2.format(System.currentTimeMillis()) + "&tmend=" + (simpleDateFormat3.format(this.m_spinnerDate.getValue()) + simpleDateFormat4.format(this.m_spinnerTime.getValue())) + "&comname=" + XDFRead.this.m_strComName + "&comusername=" + XDFRead.this.m_strComUserName + "&downflag=Y";
                    String s;
                    if (this.m_jrbtnLastDown.isSelected()) {
                        s = string + "&unlimited=Y";
                    }
                    else {
                        s = string + "&unlimited=N";
                    }
                    final XDFDownDlg xdfDownDlg = new XDFDownDlg(null, true);
                    xdfDownDlg.setTitle("XDF \ub2e4\uc6b4\ub85c\ub4dc");
                    if (this.m_strDrmKey.length() > 0) {
                        xdfDownDlg.SetDrmKey(this.m_strDrmKey);
                    }
                    xdfDownDlg.SetDownloadInfo(this.m_jtfPath.getText(), this.m_jtfName.getText(), this.m_jcbAfterOpen.isSelected());
                    xdfDownDlg.SetQueryParam(s);
                    xdfDownDlg.setModal(true);
                    xdfDownDlg.setVisible(true);
                    this.dispose();
                }
            }
            else if (actionCommand == "lastdownload") {
                this.m_spinnerDate.setEnabled(false);
                this.m_spinnerTime.setEnabled(false);
                this.m_jbtnCal.setEnabled(false);
            }
            else if (actionCommand == "normaldownload") {
                this.m_spinnerDate.setEnabled(true);
                this.m_spinnerTime.setEnabled(true);
                this.m_jbtnCal.setEnabled(true);
            }
            else if (actionCommand == "cal") {
                final Cal cal = new Cal();
                cal.setModal(true);
                cal.setVisible(true);
                if (!cal.iscancel) {
                    this.setDate(cal.yy, cal.mm, cal.dd);
                }
            }
            else if (actionCommand == "finddir") {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(null);
                fileChooser.setDialogTitle("\uc800\uc7a5\ud560 \uc704\uce58 \uc124\uc815");
                fileChooser.setFileSelectionMode(1);
                fileChooser.setDialogType(1);
                fileChooser.setApproveButtonText("\uc120\ud0dd");
                if (fileChooser.showSaveDialog(this) != 0) {
                    return;
                }
                this.m_jtfPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
            else if (actionCommand == "cancel") {
                this.dispose();
            }
            else if (actionCommand == "afteropenxdf") {}
        }
    }
    
    class XDFDownDlg extends JDialog implements WindowListener, Runnable
    {
        private static final long serialVersionUID = 1L;
        public JProgressBar progressBar;
        private JLabel m_lbStatus;
        public Thread workthread;
        public boolean bCancel;
        public boolean m_bDownWork;
        public XDFRead m_parent;
        public String m_strQuery;
        public boolean m_bReplySvr;
        private String m_strTargetURL;
        private String m_strLocalPath;
        private String m_strFileName;
        private boolean m_bRunReader;
        private String m_strDrmKey;
        
        public XDFDownDlg(final XDFRead parent, final boolean bDownWork) {
            this.m_lbStatus = new JLabel();
            this.bCancel = false;
            this.m_bDownWork = false;
            this.m_bReplySvr = false;
            this.m_strTargetURL = "";
            this.m_strDrmKey = "";
            this.m_bDownWork = bDownWork;
            this.m_parent = parent;
            this.buildUI();
            this.setCenter();
            this.workthread = new Thread(this);
            this.m_bRunReader = true;
        }
        
        @Override
        public void windowOpened(final WindowEvent windowEvent) {
            this.workthread.start();
            final EspressoBean espressoBean = new EspressoBean();
            espressoBean.setDrmServerAddress(XDFRead.this.m_strDrmServerAddress);
            if (this.m_strDrmKey.length() > 0) {
                espressoBean.SetDrmKey(this.m_strDrmKey);
            }
            this.m_strTargetURL = espressoBean.getTargetURL(this.m_strQuery, XDFRead.this.m_bCodec);
            if (this.m_strTargetURL.length() > 0) {
                if (this.m_strTargetURL.toUpperCase().contains("HTTP://")) {
                    if (!this.m_bDownWork) {
                        XDFRead.this.SetXDFUrl(this.m_strTargetURL);
                        XDFRead.this.StartReader();
                    }
                    this.m_bReplySvr = true;
                }
                else {
                    if (this.m_strTargetURL.toUpperCase().contains("NO_INFO")) {
                        JOptionPane.showMessageDialog(null, "\uc798\ubabb\ub41c \uc694\uccad\uc785\ub2c8\ub2e4.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("NO_USER")) {
                        JOptionPane.showMessageDialog(null, "\ub4f1\ub85d\ub41c \ud68c\uc6d0\uc774 \uc544\ub2d9\ub2c8\ub2e4.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("NO_USER_BOOK")) {
                        JOptionPane.showMessageDialog(null, "\uad6c\ub9e4\ud558\uc2e0 \ub3c4\uc11c\uac00 \uc544\ub2d9\ub2c8\ub2e4.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("FAIL_OTHER_PC_EBL")) {
                        JOptionPane.showMessageDialog(null, "\uc544\uc9c1 \uc774 \ub3c4\uc11c\ub97c \uc77d\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.\n\n\uac19\uc740 \ub3c4\uc11c\ub97c \ub2e4\ub978 \ucef4\ud4e8\ud130\uc5d0\uc11c \uc77d\uc73c\ub824\uba74 \uc774\uc804 \ucef4\ud4e8\ud130\uc5d0\uc11c \n\uc5f4\uc5c8\ub358 \uc2dc\uac04\uc73c\ub85c\ubd80\ud130 10\ubd84\uc774 \uacbd\uacfc\ud574\uc57c \ud569\ub2c8\ub2e4.\n", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("FAIL_OTHER_PC_UNLIMITED_DOWN")) {
                        JOptionPane.showMessageDialog(null, "[\ub9c8\uc9c0\ub9c9\uc73c\ub85c \ub2e4\uc6b4\ub85c\ub4dc\ud558\uae30]\ub97c \uc0ac\uc6a9\ud558\uc5ec \ub2e4\uc6b4\ub85c\ub4dc\ud55c \ub3c4\uc11c\uc785\ub2c8\ub2e4.\n\uc774\uc804\uc5d0 \ub2e4\uc6b4\ub85c\ub4dc\ud55c \ucef4\ud4e8\ud130\ub85c\ub9cc \ub2e4\uc2dc \ub2e4\uc6b4\ub85c\ub4dc \ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("FAIL_OTHER_PC_DOWN")) {
                        JOptionPane.showMessageDialog(null, "\uc774\ubbf8 \ub2e4\ub978 \ucef4\ud4e8\ud130\uc5d0 \ub2e4\uc6b4\ub85c\ub4dc\ud55c \ub3c4\uc11c\uc785\ub2c8\ub2e4.\n\uc720\ud6a8\uae30\uac04\uc774 \uc9c0\ub09c \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc2ed\uc2dc\uc624.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("FAIL_PC_DOWN")) {
                        JOptionPane.showMessageDialog(null, "\ub2e4\uc6b4\ub85c\ub4dc\ud55c \ub3c4\uc11c\ub294 \ub3c4\uc11c\ubcf4\uae30\ub97c \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    if (this.m_strTargetURL.toUpperCase().contains("FAIL_PC_READ")) {
                        JOptionPane.showMessageDialog(null, "\ub3c4\uc11c\ubcf4\uae30\ub97c \ud55c \ud6c4 10\ubd84\uc774 \uacbd\uacfc\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4. \n\uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc138\uc694.", "\uc624\ub958", 0);
                        this.dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "\uc815\uc0c1\uc801\uc778 \uc11c\ube44\uc2a4\ub97c \ud560 \uc218 \uc5c6\ub294 \uc0c1\ud0dc\uc785\ub2c8\ub2e4.\n\ucee4\ud53c\ubd81 \uace0\uac1d\ub9cc\uc871\uc13c\ud130\ub85c \ubb38\uc758\ud574 \uc8fc\uc138\uc694.\n\uc804\ud654: 02-3016-0513\n\uc774\uba54\uc77c(e-mail): help@coffeebook.co.kr", "\uc624\ub958", 0);
                    this.dispose();
                }
            }
        }
        
        private void setCenter() {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = this.getSize();
            this.setLocation((int)(screenSize.getWidth() - size.getWidth()) / 2, (int)(screenSize.getHeight() - size.getHeight()) / 2);
        }
        
        public void SetDrmKey(final String strDrmKey) {
            this.m_strDrmKey = strDrmKey;
        }
        
        public void SetDownloadInfo(final String strLocalPath, String strFileName, final boolean bRunReader) {
            this.m_strLocalPath = strLocalPath;
            if (strFileName.substring(strFileName.length() - 4, strFileName.length()).toLowerCase().compareToIgnoreCase(".xdf") != 0) {
                strFileName += ".xdf";
            }
            strFileName = strFileName.replace('\\', ' ');
            strFileName = strFileName.replace('/', ' ');
            strFileName = strFileName.replace(':', ' ');
            strFileName = strFileName.replace('?', ' ');
            strFileName = strFileName.replace('\"', ' ');
            strFileName = strFileName.replace('<', ' ');
            strFileName = strFileName.replace('>', ' ');
            strFileName = strFileName.replace('|', ' ');
            this.m_strFileName = strFileName;
            this.m_bRunReader = bRunReader;
        }
        
        public void SetQueryParam(final String strQuery) {
            this.m_strQuery = strQuery;
        }
        
        private void buildUI() {
            this.addWindowListener(this);
            this.setLayout(new BorderLayout());
            this.setSize(450, 260);
            final JPanel panel = new JPanel();
            panel.setLayout(null);
            final JPanel panel2 = new JPanel();
            panel2.setBounds(10, 10, 420, 120);
            panel2.setLayout(new GridLayout(4, 0, 0, 0));
            panel2.setBorder(new TitledBorder("\uc11c\uc9c0\uc815\ubcf4"));
            final JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout(0));
            panel3.add(new JLabel("\uc81c\ubaa9 : ", 2));
            panel3.add(new JLabel(XDFRead.this.m_strTitle, 2));
            final JPanel panel4 = new JPanel();
            panel4.setLayout(new FlowLayout(0));
            panel4.add(new JLabel("\uc800\uc790 :", 2));
            panel4.add(new JLabel(XDFRead.this.m_strAuthor, 2));
            final JPanel panel5 = new JPanel();
            panel5.setLayout(new FlowLayout(0));
            panel5.add(new JLabel("\ucd9c\ud310\uc0ac :", 2));
            panel5.add(new JLabel(XDFRead.this.m_strPublisher, 2));
            final JPanel panel6 = new JPanel();
            panel6.setLayout(new FlowLayout(0));
            panel6.add(new JLabel("\uad6c\uc785\uc77c :", 2));
            panel6.add(new JLabel(XDFRead.this.m_strPaidDate, 2));
            panel2.add(panel3);
            panel2.add(panel4);
            panel2.add(panel5);
            panel2.add(panel6);
            final JPanel panel7 = new JPanel();
            panel7.setBorder(new TitledBorder("\uc9c4\ud589 \uc0c1\ud0dc"));
            panel7.setBounds(10, 140, 420, 80);
            panel7.setLayout(new GridLayout(1, 0, 0, 0));
            this.m_lbStatus.setText("\ud30c\uc77c \uc0dd\uc131 \uc911 ");
            panel7.add(this.m_lbStatus);
            if (this.m_bDownWork) {
                panel7.setBounds(10, 140, 420, 60);
                panel7.setLayout(new GridLayout(2, 0, 0, 0));
                (this.progressBar = new JProgressBar(0, 100)).setValue(0);
                this.progressBar.setMinimum(0);
                this.progressBar.setStringPainted(true);
                panel7.add(this.progressBar);
            }
            panel.add(panel2);
            panel.add(panel7);
            this.add("Center", panel);
        }
        
        @Override
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowActivated(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
        
        public void windowGainedFocus(final WindowEvent windowEvent) {
        }
        
        public void windowLostFocus(final WindowEvent windowEvent) {
        }
        
        public void windowStateChanged(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
        }
        
        @Override
        public void run() {
            this.DownloadXDF();
        }
        
        public void DownloadXDF() {
            int value = 0;
            String text = "\ud30c\uc77c \uc0dd\uc131 \uc911 ";
            do {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                final String substring = text.substring(text.length() - 1, text.length());
                String s;
                if (substring.compareTo("\u2015") == 0) {
                    s = "\uff0f";
                }
                else if (substring.compareTo("\uff0f") == 0) {
                    s = "\uff5c";
                }
                else if (substring.compareTo("\uff5c") == 0) {
                    s = "\uff3c";
                }
                else if (substring.compareTo("\uff3c") == 0) {
                    s = ".";
                }
                else {
                    s = "\u2015";
                }
                if (s == ".") {
                    text = text.substring(0, text.length() - 1) + ".\u2015";
                }
                else {
                    text = text.substring(0, text.length() - 1) + s;
                }
                this.m_lbStatus.setText(text);
            } while (!this.m_bReplySvr);
            Label_0446: {
                if (this.m_bDownWork & this.m_strTargetURL.length() > 0 & this.m_bReplySvr) {
                    this.m_lbStatus.setText("XDF \ub2e4\uc6b4\ub85c\ub4dc \uc911 ");
                    this.m_lbStatus.updateUI();
                    Label_0451: {
                        try {
                            final URLConnection openConnection = new URL(this.m_strTargetURL).openConnection();
                            this.progressBar.setMaximum(openConnection.getContentLength());
                            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                            final String string = this.m_strLocalPath + "\\" + this.m_strFileName;
                            final FileOutputStream fileOutputStream = new FileOutputStream(string);
                            final byte[] array = new byte[1024];
                            int read;
                            while ((read = bufferedInputStream.read(array, 0, 1024)) >= 0 && !this.bCancel) {
                                value += read;
                                fileOutputStream.write(array, 0, read);
                                this.progressBar.setValue(value);
                                this.progressBar.repaint();
                            }
                            fileOutputStream.close();
                            if (this.m_bRunReader) {
                                XDFRead.this.SetXDFUrl(string);
                                XDFRead.this.StartReader();
                            }
                            break Label_0451;
                        }
                        catch (Exception ex2) {
                            JOptionPane.showMessageDialog(null, "\ub2e4\uc6b4\ub85c\ub4dc \uc911 \uc5d0\ub7ec\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4.\n\ub2e4\uc2dc \uc2dc\ub3c4\ud558\uc5ec \uc8fc\uc2ed\uc2dc\uc624.", "\uc624\ub958", 0);
                            this.dispose();
                            return;
                        }
                        break Label_0446;
                    }
                    this.dispose();
                    return;
                }
            }
            this.dispose();
        }
    }
    
    class DownDlg extends Frame implements WindowListener, Runnable, ActionListener
    {
        private static final long serialVersionUID = 1L;
        public JDialog dlg;
        public JProgressBar progressBar;
        public Button btncancel;
        public BorderLayout bl1;
        public Panel p;
        public GridBagLayout gb1;
        public Thread workthread;
        public Thread connectThread;
        public boolean bCancel;
        private String m_strTempPath;
        
        public DownDlg(final String strTempPath) {
            this.dlg = new JDialog(this, "Espresso Reader Install", true);
            this.btncancel = new Button("Cancel");
            this.bl1 = new BorderLayout();
            this.p = new Panel();
            this.gb1 = new GridBagLayout();
            this.bCancel = false;
            this.m_strTempPath = strTempPath;
            if (this.m_strTempPath == "") {
                this.m_strTempPath = "c:";
            }
            this.dlg.addWindowListener(this);
            (this.progressBar = new JProgressBar(0, 100)).setValue(0);
            this.progressBar.setMinimum(0);
            this.progressBar.setStringPainted(true);
            this.dlg.setLocation(300, 300);
            this.dlg.setSize(300, 80);
            this.add(this.btncancel);
            this.btncancel.setActionCommand("cancel");
            this.btncancel.addActionListener(this);
            this.workthread = new Thread(this);
        }
        
        @Override
        public void windowOpened(final WindowEvent windowEvent) {
            this.workthread.start();
        }
        
        @Override
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowActivated(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
        
        public void windowGainedFocus(final WindowEvent windowEvent) {
        }
        
        public void windowLostFocus(final WindowEvent windowEvent) {
        }
        
        public void windowStateChanged(final WindowEvent windowEvent) {
        }
        
        @Override
        public void windowClosed(final WindowEvent windowEvent) {
            this.bCancel = true;
        }
        
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
            this.bCancel = true;
        }
        
        public void DownloadUpdater() {
            try {
                final String url = "http://update.bluemountainsoft.com/ReaderU/updater.exe";
                final String url2 = "http://update2.bluemountainsoft.com/ReaderU/updater.exe";
                final String url3 = "http://update3.bluemountainsoft.com/ReaderU/updater.exe";
                final String url4 = "http://update.bluemountainsoft.com/ReaderU/resUpdaterKOR.dll";
                final String url5 = "http://update2.bluemountainsoft.com/ReaderU/resUpdaterKOR.dll";
                final String url6 = "http://update3.bluemountainsoft.com/ReaderU/resUpdaterKOR.dll";
                final XDFRead.URLConnect urlConnect = new XDFRead.URLConnect(XDFRead.this);
                final XDFRead.URLConnect urlConnect2 = new XDFRead.URLConnect(XDFRead.this);
                urlConnect.setURL(url);
                urlConnect2.setURL(url4);
                URLConnection urlConnection;
                if (urlConnect.m_URLConnect != null) {
                    urlConnection = urlConnect.m_URLConnect;
                }
                else {
                    urlConnect.setURL(url2);
                    if (urlConnect.m_URLConnect != null) {
                        urlConnection = urlConnect.m_URLConnect;
                    }
                    else {
                        urlConnect.setURL(url3);
                        if (urlConnect.m_URLConnect == null) {
                            this.dispose();
                            return;
                        }
                        urlConnection = urlConnect.m_URLConnect;
                    }
                }
                URLConnection urlConnection2;
                if (urlConnect2.m_URLConnect != null) {
                    urlConnection2 = urlConnect2.m_URLConnect;
                }
                else {
                    urlConnect2.setURL(url5);
                    if (urlConnect2.m_URLConnect != null) {
                        urlConnection2 = urlConnect2.m_URLConnect;
                    }
                    else {
                        urlConnect2.setURL(url6);
                        if (urlConnect2.m_URLConnect == null) {
                            this.dispose();
                            return;
                        }
                        urlConnection2 = urlConnect2.m_URLConnect;
                    }
                }
                this.progressBar.setMaximum(urlConnection.getContentLength() + urlConnection2.getContentLength());
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                final FileOutputStream fileOutputStream = new FileOutputStream(this.m_strTempPath + "\\updater.exe");
                final byte[] array = new byte[1024];
                int n = 0;
                int read;
                while ((read = bufferedInputStream.read(array, 0, 1024)) >= 0 && !this.bCancel) {
                    n += read;
                    fileOutputStream.write(array, 0, read);
                    this.progressBar.setValue(n);
                    this.progressBar.repaint();
                }
                fileOutputStream.close();
                final BufferedInputStream bufferedInputStream2 = new BufferedInputStream(urlConnection2.getInputStream());
                final FileOutputStream fileOutputStream2 = new FileOutputStream(this.m_strTempPath + "\\ResUpdaterKOR.dll");
                final byte[] array2 = new byte[1024];
                int read2;
                while ((read2 = bufferedInputStream2.read(array2, 0, 1024)) >= 0 && !this.bCancel) {
                    n += read2;
                    fileOutputStream2.write(array2, 0, read2);
                    this.progressBar.setValue(n);
                    this.progressBar.repaint();
                }
                fileOutputStream2.close();
                this.dispose();
            }
            catch (Exception ex) {
                this.dispose();
            }
        }
        
        @Override
        public void run() {
            this.DownloadUpdater();
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getActionCommand().equals("cancel")) {
                this.bCancel = true;
            }
        }
    }
    
    public class Cal extends JDialog implements ActionListener
    {
        protected int yy;
        protected int mm;
        protected int dd;
        protected JButton[][] labs;
        protected int leadGap;
        Calendar calendar;
        protected final int thisYear;
        protected final int thisMonth;
        private JButton b0;
        private JComboBox monthChoice;
        private JComboBox yearChoice;
        public boolean iscancel;
        public int[] dom;
        String[] months;
        private int activeDay;
        
        Cal() {
            this.leadGap = 0;
            this.calendar = new GregorianCalendar();
            this.thisYear = this.calendar.get(1);
            this.thisMonth = this.calendar.get(2);
            this.iscancel = false;
            this.dom = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            this.months = new String[] { "1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4" };
            this.activeDay = -1;
            this.setYYMMDD(this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
            this.buildGUI();
            this.recompute();
            this.pack();
            this.setCenter();
        }
        
        Cal(final int n, final int n2, final int n3) {
            this.leadGap = 0;
            this.calendar = new GregorianCalendar();
            this.thisYear = this.calendar.get(1);
            this.thisMonth = this.calendar.get(2);
            this.iscancel = false;
            this.dom = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            this.months = new String[] { "1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4" };
            this.activeDay = -1;
            this.setYYMMDD(n, n2, n3);
            this.buildGUI();
            this.recompute();
        }
        
        private void setYYMMDD(final int yy, final int mm, final int dd) {
            this.yy = yy;
            this.mm = mm;
            this.dd = dd;
        }
        
        private void setCenter() {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = this.getSize();
            this.setLocation((int)(screenSize.getWidth() - size.getWidth()) / 2, (int)(screenSize.getHeight() - size.getHeight()) / 2);
        }
        
        private void buildGUI() {
            this.getAccessibleContext().setAccessibleDescription("Calendar not accessible yet. Sorry!");
            this.setLayout(new BorderLayout());
            final JPanel panel = new JPanel();
            panel.add(this.monthChoice = new JComboBox());
            for (int i = 0; i < this.months.length; ++i) {
                this.monthChoice.addItem(this.months[i]);
            }
            this.monthChoice.setSelectedItem(this.months[this.mm]);
            this.monthChoice.addActionListener((ActionListener)new Cal.XDFRead$Cal$1(this));
            this.monthChoice.getAccessibleContext().setAccessibleName("Months");
            this.monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year");
            panel.add(this.yearChoice = new JComboBox());
            this.yearChoice.setEditable(true);
            for (int j = this.yy - 5; j < this.yy + 5; ++j) {
                this.yearChoice.addItem(Integer.toString(j));
            }
            this.yearChoice.setSelectedItem(Integer.toString(this.yy));
            this.yearChoice.addActionListener((ActionListener)new Cal.XDFRead$Cal$2(this));
            this.add("North", panel);
            final JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(7, 7));
            this.labs = new JButton[6][7];
            panel2.add(this.b0 = new JButton("S"));
            panel2.add(new JButton("M"));
            panel2.add(new JButton("T"));
            panel2.add(new JButton("W"));
            panel2.add(new JButton("R"));
            panel2.add(new JButton("F"));
            panel2.add(new JButton("S"));
            final Cal.XDFRead$Cal$3 xdfRead$Cal$3 = new Cal.XDFRead$Cal$3(this);
            for (int k = 0; k < 6; ++k) {
                for (int l = 0; l < 7; ++l) {
                    panel2.add(this.labs[k][l] = new JButton(""));
                    this.labs[k][l].addActionListener((ActionListener)xdfRead$Cal$3);
                }
            }
            this.add("Center", panel2);
            final JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout(1));
            final JButton button = new JButton("\ud655\uc778");
            button.setActionCommand("ok");
            button.addActionListener(this);
            final JButton button2 = new JButton("\ucde8\uc18c");
            button2.setActionCommand("cancel");
            button2.addActionListener(this);
            panel3.add(button);
            panel3.add(button2);
            this.add("South", panel3);
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand == "ok") {
                this.dispose();
            }
            else if (actionCommand == "cancel") {
                this.iscancel = true;
                this.dispose();
            }
        }
        
        protected void recompute() {
            if (this.mm < 0 || this.mm > 11) {
                throw new IllegalArgumentException("Month " + this.mm + " bad, must be 0-11");
            }
            this.clearDayActive();
            this.calendar = new GregorianCalendar(this.yy, this.mm, this.dd);
            this.leadGap = new GregorianCalendar(this.yy, this.mm, 1).get(7) - 1;
            int n = this.dom[this.mm];
            if (this.isLeap(this.calendar.get(1)) && this.mm > 1) {
                ++n;
            }
            for (int i = 0; i < this.leadGap; ++i) {
                this.labs[0][i].setText("");
            }
            for (int j = 1; j <= n; ++j) {
                this.labs[(this.leadGap + j - 1) / 7][(this.leadGap + j - 1) % 7].setText(Integer.toString(j));
            }
            for (int k = this.leadGap + 1 + n; k < 42; ++k) {
                this.labs[k / 7][k % 7].setText("");
            }
            if (this.thisYear == this.yy && this.mm == this.thisMonth) {
                this.setDayActive(this.dd);
            }
            this.repaint();
        }
        
        public boolean isLeap(final int n) {
            return (n % 4 == 0 && n % 100 != 0) || n % 400 == 0;
        }
        
        public void setDate(final int yy, final int mm, final int dd) {
            this.yy = yy;
            this.mm = mm;
            this.dd = dd;
            this.recompute();
        }
        
        private void clearDayActive() {
            if (this.activeDay > 0) {
                final JButton button = this.labs[(this.leadGap + this.activeDay - 1) / 7][(this.leadGap + this.activeDay - 1) % 7];
                button.setBackground(this.b0.getBackground());
                button.repaint();
                this.activeDay = -1;
            }
        }
        
        public void setDayActive(final int n) {
            this.clearDayActive();
            if (n <= 0) {
                this.dd = new GregorianCalendar().get(5);
            }
            else {
                this.dd = n;
            }
            final JButton button = this.labs[(this.leadGap + n - 1) / 7][(this.leadGap + n - 1) % 7];
            button.setBackground(Color.red);
            button.repaint();
            this.activeDay = n;
        }
    }
}
