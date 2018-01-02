// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import com.diginet.digichat.common.User;
import com.diginet.digichat.client.h;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.File;
import java.awt.Color;
import com.diginet.ui.cz;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.aa;
import com.diginet.ui.b1;
import java.awt.Label;
import java.awt.TextField;
import com.diginet.digichat.awt.a9;
import java.awt.FileDialog;
import java.awt.Frame;
import netscape.security.PrivilegeManager;

public class cc extends cd
{
    protected final void f() {
        try {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        catch (Exception ex) {
            this.d();
            return;
        }
        super.j = new FileDialog(new Frame(), super.a ? "Select File to Send" : "Select Location to Save File", super.a ? 0 : 1);
        if (!super.a) {
            super.j.setFile(super.ad);
        }
        for (int i = 0; i == 0; i = ((!super.a || this.g()) ? 1 : 0)) {
            super.j.show();
            if (super.j.getFile() == null && super.j.getDirectory() == null) {
                this.d();
                return;
            }
            super.ad = super.j.getDirectory() + super.j.getFile();
        }
        final a9 a9 = new a9();
        if (super.ah.df.borderImgs != null) {
            a9.a(super.ah.df.borderImgs);
        }
        (super.m = new TextField(super.ad, 25)).setEditable(false);
        super.l = new Label(super.a ? "Selected File:" : "File to Receive:");
        super.k = new b1("CANCEL", this.getFont());
        super.n = new Label("Status:");
        super.o = new Label(super.a ? "Waiting for acknowledgement" : "Sent acknowledgement");
        (super.q = new aa()).b(super.ah.df.fileTransferLogo);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        a9.setLayout(layout);
        final Insets insets = new Insets(3, 10, 3, 10);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = insets;
        layout.setConstraints(super.q, gridBagConstraints);
        a9.add(super.q);
        final Panel panel = new Panel();
        panel.setLayout(layout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        layout.setConstraints(super.l, gridBagConstraints);
        panel.add(super.l);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.m, gridBagConstraints);
        panel.add(super.m);
        gridBagConstraints.insets = insets;
        layout.setConstraints(panel, gridBagConstraints);
        a9.add(panel);
        (super.p = new cz()).setForeground(new Color(255, 201, 92));
        super.p.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.p, gridBagConstraints);
        a9.add(super.p);
        final Panel panel2 = new Panel();
        panel2.setLayout(layout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 0;
        layout.setConstraints(super.n, gridBagConstraints);
        panel2.add(super.n);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(super.o, gridBagConstraints);
        panel2.add(super.o);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(super.k, gridBagConstraints);
        panel2.add(super.k);
        gridBagConstraints.insets = insets;
        layout.setConstraints(panel2, gridBagConstraints);
        a9.add(panel2);
        this.add(a9);
        this.resize(375, 180);
        super.p.resize(this.size().width, super.p.preferredSize().height);
        this.setResizable(false);
        this.setBackground(super.ah.df.tabsBackground);
        this.setVisible(true);
        if (!super.a) {
            this.k();
        }
        else {
            this.l();
        }
    }
    
    protected final boolean g() {
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        if (super.ad == null) {
            return false;
        }
        final File file = new File(super.ad);
        super.ae = file.length() + "";
        return file.exists();
    }
    
    protected final boolean i() {
        PrivilegeManager.enablePrivilege("TerminalEmulator");
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            final Socket socket = new Socket(super.ag.e, 9000);
            super.o.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(super.ad);
            final long long1 = dataInputStream.readLong();
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            fileOutputStream = new FileOutputStream(file);
            int n = 0;
            while (n < long1 && super.h != 2) {
                final int read = dataInputStream.read(array);
                n += read;
                fileOutputStream.write(array, 0, read);
                super.p.a((int)(100.0f * (n / long1)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    super.o.setText("Transferring file" + " " + this.a(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileOutputStream.close();
            socket.close();
            if (super.h == 2) {
                file.delete();
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (file != null) {
                    file.delete();
                }
            }
            catch (Exception ex3) {}
            return false;
        }
    }
    
    protected final boolean j() {
        PrivilegeManager.enablePrivilege("TerminalEmulator");
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        try {
            final ServerSocket serverSocket = new ServerSocket(9000, 1);
            serverSocket.setSoTimeout(30000);
            final Socket accept = serverSocket.accept();
            super.o.setText("Transferring file");
            final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            final File file = new File(super.ad);
            dataOutputStream.writeLong(file.length());
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            final FileInputStream fileInputStream = new FileInputStream(file);
            final int available = fileInputStream.available();
            int n = 0;
            while (n < available && super.h != 2) {
                final int read = fileInputStream.read(array);
                n += read;
                dataOutputStream.write(array, 0, read);
                super.p.a((int)(100.0f * (n / available)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    super.o.setText("Transferring file" + " " + this.a(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileInputStream.close();
            accept.close();
            serverSocket.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public cc(final h h, final b6 b6) {
        super(h, b6.a, b6.b, b6.c, false);
        super.ai = b6.d;
        super.af = b6.b;
    }
    
    public cc(final h h, final User user) {
        super(h, user, null, null, true);
    }
}
