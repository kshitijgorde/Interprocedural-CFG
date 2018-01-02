// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import com.diginet.digichat.common.j;
import com.diginet.digichat.client.i;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.File;
import java.awt.Color;
import com.diginet.ui.ce;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Button;
import java.awt.Label;
import java.awt.FileDialog;
import java.awt.Frame;
import netscape.security.PrivilegeManager;

public class bx extends by
{
    protected void g() {
        try {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        catch (Exception ex) {
            this.e();
            return;
        }
        super.j = new FileDialog(new Frame(), super.a ? "Select File to Send" : "Select Location to Save File", super.a ? 0 : 1);
        if (!super.a) {
            super.j.setFile(super.ac);
        }
        for (int i = 0; i == 0; i = ((!super.a || this.h()) ? 1 : 0)) {
            super.j.show();
            if (super.j.getFile() == null && super.j.getDirectory() == null) {
                this.e();
                return;
            }
            super.ac = super.j.getDirectory() + super.j.getFile();
        }
        super.m = new Label(super.ac);
        super.l = new Label(super.a ? "Selected File:" : "File to Receive:");
        super.k = new Button("Cancel");
        super.n = new Label("Status:");
        super.o = new Label(super.a ? "Waiting for acknowledgement" : "Sent acknowledgement");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(super.l, gridBagConstraints);
        this.add(super.l);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.m, gridBagConstraints);
        this.add(super.m);
        (super.p = new ce()).setForeground(new Color(0, 0, 128));
        super.p.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.p, gridBagConstraints);
        this.add(super.p);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(super.n, gridBagConstraints);
        this.add(super.n);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(super.o, gridBagConstraints);
        this.add(super.o);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.k, gridBagConstraints);
        this.add(super.k);
        this.resize(super.a ? 350 : 375, 180);
        super.p.resize(this.size().width, super.p.preferredSize().height);
        this.setResizable(false);
        this.setBackground(super.ag.ca.j);
        this.setVisible(true);
        if (!super.a) {
            this.k();
        }
        else {
            this.l();
        }
    }
    
    protected boolean h() {
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        if (super.ac == null) {
            return false;
        }
        final File file = new File(super.ac);
        super.ad = file.length() + "";
        return file.exists();
    }
    
    protected boolean i() {
        PrivilegeManager.enablePrivilege("TerminalEmulator");
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            final Socket socket = new Socket(super.af.e, 9000);
            super.o.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(super.ac);
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
                    super.o.setText("Transferring file " + this.a(currentTimeMillis2, currentTimeMillis, n));
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
    
    protected boolean j() {
        PrivilegeManager.enablePrivilege("TerminalEmulator");
        PrivilegeManager.enablePrivilege("UniversalFileAccess");
        try {
            final ServerSocket serverSocket = new ServerSocket(9000, 1);
            serverSocket.setSoTimeout(30000);
            final Socket accept = serverSocket.accept();
            super.o.setText("Transferring file");
            final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            final File file = new File(super.ac);
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
                    super.o.setText("Transferring file " + this.a(currentTimeMillis2, currentTimeMillis, n));
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
    
    public bx(final i i, final bu bu) {
        super(i, bu.a, bu.b, bu.c, false);
        super.ah = bu.d;
        super.ae = bu.b;
    }
    
    public bx(final i i, final j j) {
        super(i, j, null, null, true);
    }
}
