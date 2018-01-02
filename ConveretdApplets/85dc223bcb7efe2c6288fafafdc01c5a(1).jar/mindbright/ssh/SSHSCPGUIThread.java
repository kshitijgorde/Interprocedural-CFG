// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.Container;
import mindbright.util.AWTConvenience;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.awt.Button;
import java.awt.Label;
import mindbright.gui.ProgressBar;
import java.awt.Dialog;
import java.awt.Frame;

public final class SSHSCPGUIThread extends Thread implements SSHSCPIndicator
{
    String curDir;
    String localFile;
    String remoteFile;
    String remoteHost;
    int remotePort;
    SSHAuthenticator authenticator;
    SSHClientUser mainUser;
    SSHInteractor interactor;
    boolean recursive;
    boolean background;
    boolean toRemote;
    Frame parent;
    String[] localFileList;
    Dialog copyIndicator;
    ProgressBar progress;
    SSHSCP scp;
    Thread copyThread;
    Label srcLbl;
    Label dstLbl;
    Label sizeLbl;
    Label nameLbl;
    Label speedLbl;
    Button cancB;
    long startTime;
    long lastTime;
    int totTransSize;
    int fileTransSize;
    int curFileSize;
    int lastSize;
    int fileCnt;
    boolean doneCopying;
    
    public SSHSCPGUIThread(final String remoteHost, final int remotePort, final SSHAuthenticator authenticator, final SSHClientUser mainUser, final SSHInteractor interactor, final Frame parent, String curDir, String localFile, final String remoteFile, final boolean recursive, final boolean background, final boolean toRemote) throws Exception {
        this.localFileList = spaceSplit(localFile);
        if (this.localFileList == null) {
            throw new Exception("Unbalanced quotes in local files list");
        }
        final File lf = new File(this.localFileList[0]);
        if (lf.isAbsolute()) {
            curDir = lf.getParent();
            if (curDir == null) {
                curDir = lf.getAbsolutePath();
            }
        }
        this.localFileList = starExpand(this.localFileList, curDir);
        if (this.localFileList.length > 1 && !toRemote) {
            throw new Exception("Ambiguos local target");
        }
        if (!toRemote) {
            localFile = this.localFileList[0];
        }
        this.remoteHost = remoteHost;
        this.curDir = curDir;
        this.remotePort = remotePort;
        this.authenticator = authenticator;
        this.mainUser = mainUser;
        this.interactor = interactor;
        this.parent = parent;
        this.localFile = localFile;
        this.remoteFile = remoteFile;
        this.recursive = recursive;
        this.background = background;
        this.toRemote = toRemote;
        this.fileCnt = 0;
        this.doneCopying = false;
        this.startTime = 0L;
        this.lastTime = 0L;
        this.totTransSize = 0;
        this.fileTransSize = 0;
        this.lastSize = 0;
        this.start();
    }
    
    public void run() {
        String sourceFile = "localhost:" + this.localFile;
        String destFile = this.remoteHost + ":" + this.remoteFile;
        if (!this.toRemote) {
            final String tmp = sourceFile;
            sourceFile = destFile;
            destFile = tmp;
        }
        this.copyIndicator = new Dialog(this.parent, "MindTerm - File Transfer", false);
        final GridBagLayout grid = new GridBagLayout();
        final GridBagConstraints gridc = new GridBagConstraints();
        this.copyIndicator.setLayout(grid);
        gridc.fill = 2;
        gridc.anchor = 17;
        gridc.gridy = 0;
        gridc.gridwidth = 1;
        gridc.insets = new Insets(4, 4, 4, 4);
        Label lbl = new Label("Source:");
        grid.setConstraints(lbl, gridc);
        this.copyIndicator.add(lbl);
        gridc.gridwidth = 4;
        grid.setConstraints(this.srcLbl = new Label(this.cutName(sourceFile, 32)), gridc);
        this.copyIndicator.add(this.srcLbl);
        gridc.gridy = 1;
        gridc.gridwidth = 1;
        lbl = new Label("Destination:");
        grid.setConstraints(lbl, gridc);
        this.copyIndicator.add(lbl);
        gridc.gridwidth = 4;
        grid.setConstraints(this.dstLbl = new Label(this.cutName(destFile, 32)), gridc);
        this.copyIndicator.add(this.dstLbl);
        gridc.gridy = 2;
        gridc.gridwidth = 1;
        lbl = new Label("Current:");
        grid.setConstraints(lbl, gridc);
        this.copyIndicator.add(lbl);
        gridc.gridwidth = 3;
        grid.setConstraints(this.nameLbl = new Label("connecting..."), gridc);
        this.copyIndicator.add(this.nameLbl);
        gridc.gridwidth = 1;
        grid.setConstraints(this.sizeLbl = new Label(""), gridc);
        this.copyIndicator.add(this.sizeLbl);
        gridc.gridy = 3;
        gridc.gridwidth = 3;
        gridc.fill = 0;
        gridc.anchor = 10;
        gridc.insets = new Insets(4, 12, 4, 4);
        grid.setConstraints(this.progress = new ProgressBar(512, 160, 20), gridc);
        this.copyIndicator.add(this.progress);
        gridc.gridwidth = 0;
        gridc.insets = new Insets(4, 4, 4, 4);
        gridc.fill = 2;
        grid.setConstraints(this.speedLbl = new Label("0.0 kB/sec", 1), gridc);
        this.copyIndicator.add(this.speedLbl);
        gridc.gridy = 4;
        (this.cancB = new Button("Cancel")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!SSHSCPGUIThread.this.doneCopying && SSHSCPGUIThread.this.copyThread != null && SSHSCPGUIThread.this.scp != null) {
                    SSHSCPGUIThread.this.scp.abort();
                }
                SSHSCPGUIThread.this.copyIndicator.setVisible(false);
            }
        });
        gridc.fill = 0;
        gridc.gridwidth = 0;
        gridc.anchor = 10;
        gridc.ipady = 2;
        gridc.ipadx = 2;
        grid.setConstraints(this.cancB, gridc);
        this.copyIndicator.add(this.cancB);
        AWTConvenience.setBackgroundOfChildren(this.copyIndicator);
        final Dimension size;
        final Dimension d = size = this.speedLbl.getSize();
        size.width += d.width * 2;
        this.speedLbl.setSize(d);
        this.sizeLbl.setSize(d);
        this.copyIndicator.setResizable(true);
        this.copyIndicator.pack();
        AWTConvenience.placeDialog(this.copyIndicator);
        this.copyThread = new Thread(new Runnable() {
            public void run() {
                try {
                    (SSHSCPGUIThread.this.scp = new SSHSCP(SSHSCPGUIThread.this.remoteHost, SSHSCPGUIThread.this.remotePort, SSHSCPGUIThread.this.authenticator, new File(SSHSCPGUIThread.this.curDir), SSHSCPGUIThread.this.recursive, false)).setClientUser(SSHSCPGUIThread.this.mainUser);
                    SSHSCPGUIThread.this.scp.setInteractor(SSHSCPGUIThread.this.interactor);
                    SSHSCPGUIThread.this.scp.setIndicator(SSHSCPGUIThread.this);
                    if (SSHSCPGUIThread.this.toRemote) {
                        SSHSCPGUIThread.this.scp.copyToRemote(SSHSCPGUIThread.this.localFileList, SSHSCPGUIThread.this.remoteFile);
                    }
                    else {
                        SSHSCPGUIThread.this.scp.copyToLocal(SSHSCPGUIThread.this.localFile, SSHSCPGUIThread.this.remoteFile);
                    }
                    SSHSCPGUIThread.this.copyThread.setPriority(5);
                    Toolkit.getDefaultToolkit().beep();
                }
                catch (Exception e) {
                    SSHSCPGUIThread.this.interactor.alert("SCP Error: " + e.getMessage());
                    if (SSH.DEBUGMORE) {
                        System.out.println("SCP Error:");
                        e.printStackTrace();
                    }
                }
                SSHSCPGUIThread.this.nameLbl.setText("Copied " + SSHSCPGUIThread.this.fileCnt + " file" + ((SSHSCPGUIThread.this.fileCnt != 1) ? "s" : "") + ".");
                final double kSize = SSHSCPGUIThread.this.totTransSize / 1024.0;
                SSHSCPGUIThread.this.sizeLbl.setText(SSHSCPGUIThread.this.round(kSize) + " kB");
                SSHSCPGUIThread.this.doneCopying = true;
                SSHSCPGUIThread.this.cancB.setLabel("Done");
                AWTConvenience.setKeyListenerOfChildren(SSHSCPGUIThread.this.copyIndicator, new AWTConvenience.OKCancelAdapter(SSHSCPGUIThread.this.cancB, SSHSCPGUIThread.this.cancB), null);
            }
        });
        if (this.background) {
            this.copyThread.setPriority(1);
        }
        this.copyThread.start();
        this.copyIndicator.setVisible(true);
    }
    
    public static String[] spaceSplit(String str) {
        int l = 0;
        int cnt = 0;
        String[] list = new String[str.length() / 2];
        boolean lastIsQuoted = false;
        str = str.trim();
        int r;
    Label_0023:
        while ((r = str.indexOf(32, l)) >= 0) {
            if (str.charAt(l) == '\"') {
                ++l;
                r = str.indexOf(34, l);
                if (r == -1) {
                    return null;
                }
            }
            String name = str.substring(l, r);
            if (name.endsWith(File.separator)) {
                name = name.substring(0, name.length() - 1);
            }
            list[cnt++] = name;
            l = r;
            while (true) {
                while (++l != str.length()) {
                    if (str.charAt(l) != ' ') {
                        continue Label_0023;
                    }
                }
                lastIsQuoted = true;
                continue;
            }
        }
        if (!lastIsQuoted) {
            if (str.charAt(l) == '\"') {
                ++l;
                r = str.indexOf(34, l);
                if (r == -1) {
                    return null;
                }
            }
            String name = str.substring(l);
            if (name.endsWith(File.separator)) {
                name = name.substring(0, name.length() - 1);
            }
            list[cnt++] = name;
        }
        final String[] tmp = list;
        list = new String[cnt];
        System.arraycopy(tmp, 0, list, 0, cnt);
        return list;
    }
    
    public static String[] starExpand(final String[] fileList, final String curDir) {
        int cnt = 0;
        String[] newList = new String[4096];
        final String[] curDirList = new File(curDir).list();
        for (int i = 0; i < fileList.length; ++i) {
            String curFile = fileList[i];
            String path = "";
            int n = curFile.indexOf(42);
            if (n == -1) {
                cnt = addUnique(newList, curFile, cnt);
            }
            else {
                final File f = new File(curFile);
                String[] dirList;
                if (!f.isAbsolute()) {
                    dirList = curDirList;
                }
                else {
                    String dir = f.getParent();
                    if (dir == null) {
                        dir = new String(File.separator);
                    }
                    dirList = new File(dir).list();
                    curFile = f.getName();
                    path = dir + File.separator;
                    n = curFile.indexOf(42);
                }
                final String pre = curFile.substring(0, n);
                final String post = curFile.substring(n + 1);
                for (int j = 0; j < dirList.length; ++j) {
                    final String name = dirList[j];
                    if (name.startsWith(pre) && name.endsWith(post)) {
                        cnt = addUnique(newList, path + name, cnt);
                    }
                }
            }
        }
        final String[] tmp = newList;
        newList = new String[cnt];
        System.arraycopy(tmp, 0, newList, 0, cnt);
        return newList;
    }
    
    static int addUnique(final String[] list, final String str, int last) {
        int i;
        for (i = 0; i < last && !list[i].equals(str); ++i) {}
        if (i == last) {
            list[last++] = str;
        }
        return last;
    }
    
    public void connected(final String server) {
        this.nameLbl.setText("...connected");
    }
    
    public void startFile(final String file, final int size) {
        final double kSize = size / 1024.0;
        this.sizeLbl.setText(this.round(kSize) + " kB");
        this.nameLbl.setText(file);
        this.progress.setMax(size, true);
        if (this.startTime == 0L) {
            this.startTime = System.currentTimeMillis();
        }
        this.curFileSize = size;
        this.fileTransSize = 0;
        ++this.fileCnt;
    }
    
    public void startDir(String file) {
        if (this.startTime == 0L) {
            this.startTime = System.currentTimeMillis();
        }
        if (file.length() > this.curDir.length()) {
            file = file.substring(this.curDir.length());
        }
        if (this.toRemote) {
            this.srcLbl.setText(this.cutName("localhost:" + file, 32));
        }
        else {
            this.dstLbl.setText(this.cutName("localhost:" + file, 32));
        }
    }
    
    public void endFile() {
        this.progress.setValue(this.curFileSize, true);
    }
    
    public void endDir() {
    }
    
    public void progress(final int size) {
        this.totTransSize += size;
        this.fileTransSize += size;
        if (this.curFileSize > 0 && (this.totTransSize - this.lastSize) * 100 / this.curFileSize >= 1) {
            this.progress.setValue(this.fileTransSize, !this.background);
            final long now = System.currentTimeMillis();
            long totSec = (now - this.startTime) / 1000L;
            double rate = (totSec != 0L) ? (this.totTransSize / 1024.0 / totSec) : 0.0;
            totSec = now - this.lastTime;
            if (totSec != 0L) {
                final double rate2 = (this.totTransSize - this.lastSize) / 1024.0 / totSec;
                rate = (rate + rate2) / 2.0;
            }
            this.speedLbl.setText("" + this.round(rate) + " kB/sec");
            this.lastSize = this.totTransSize;
            this.lastTime = now;
        }
    }
    
    double round(double val) {
        val *= 10.0;
        val = Math.floor(val);
        val /= 10.0;
        return val;
    }
    
    String cutName(String name, int len) {
        if (name.length() > len) {
            len -= 3;
            final String pre = name.substring(0, len / 2);
            final String suf = name.substring(name.length() - len / 2);
            name = pre + "..." + suf;
        }
        return name;
    }
}
