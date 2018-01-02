// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Button;
import java.awt.Label;
import java.awt.FileDialog;
import java.awt.Frame;
import netscape.security.PrivilegeManager;

public final class cQ extends cP
{
    protected final void q() {
        try {
            if (dN.q == 0) {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
                PrivilegeManager.enablePrivilege("UniversalFileAccess");
            }
        }
        catch (Exception ex) {
            this.t();
            return;
        }
        if (!(super.q = new FileDialog(new Frame(), super.q ? "Select File to Send" : "Select Location to Save File", super.q ? 0 : 1))) {
            super.q.setFile(super.q);
        }
        for (int i = 0; i == 0; i = ((!super.q || this.q()) ? 1 : 0)) {
            super.q.show();
            if (super.q.getFile() == null && super.q.getDirectory() == null) {
                this.t();
                return;
            }
            super.q = super.q.getDirectory() + super.q.getFile();
        }
        super.w = new Label(super.q);
        super.q = new Label(super.q ? "Selected File:" : "File to Receive:");
        super.q = new Button("Cancel");
        super.e = new Label("Status:");
        super.r = new Label(super.q ? "Waiting for acknowledgement" : "Sent acknowledgement");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(super.q, gridBagConstraints);
        this.add(super.q);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.w, gridBagConstraints);
        this.add(super.w);
        (super.q = new cZ()).setForeground(new Color(0, 0, 128));
        super.q.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.q, gridBagConstraints);
        this.add(super.q);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(super.e, gridBagConstraints);
        this.add(super.e);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(super.r, gridBagConstraints);
        this.add(super.r);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.q, gridBagConstraints);
        this.add(super.q);
        this.resize(super.q ? 350 : 375, 180);
        super.q.resize(this.size().width, super.q.preferredSize().height);
        this.setResizable(false);
        this.setBackground(bC.w.i);
        this.setVisible(true);
        if (!super.q) {
            this.w();
            return;
        }
        this.r();
    }
    
    protected final boolean q() {
        if (dN.q == 0) {
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        if (super.q == null) {
            return false;
        }
        final File file = new File(super.q);
        super.w = file.length() + "";
        return file.exists();
    }
    
    protected final boolean w() {
        if (dN.q == 0) {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            final Socket socket = new Socket(super.q.u, 9000);
            super.r.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(super.q);
            final long long1 = dataInputStream.readLong();
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            fileOutputStream = new FileOutputStream(file);
            int n = 0;
            while (n < long1 && super.q != 2) {
                final int read = dataInputStream.read(array);
                n += read;
                fileOutputStream.write(array, 0, read);
                super.q.q((int)(100.0f * (n / long1)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    super.r.setText("Transferring file " + cP.q(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileOutputStream.close();
            socket.close();
            if (super.q == 2) {
                file.delete();
            }
            return true;
        }
        catch (Exception ex2) {
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
    
    protected final boolean e() {
        if (dN.q == 0) {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        try {
            final ServerSocket serverSocket;
            (serverSocket = new ServerSocket(9000, 1)).setSoTimeout(30000);
            final Socket accept = serverSocket.accept();
            super.r.setText("Transferring file");
            final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            final File file;
            dataOutputStream.writeLong((file = new File(super.q)).length());
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            final FileInputStream fileInputStream;
            final int available = (fileInputStream = new FileInputStream(file)).available();
            int n = 0;
            while (n < available && super.q != 2) {
                final int read = fileInputStream.read(array);
                n += read;
                dataOutputStream.write(array, 0, read);
                super.q.q((int)(100.0f * (n / available)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    super.r.setText("Transferring file " + cP.q(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileInputStream.close();
            accept.close();
            serverSocket.close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public cQ(final dH dh, final di di) {
        super(dh, di.q, di.q, di.w, false);
        super.w = di.q;
        super.e = di.q;
    }
    
    public cQ(final dH dh, final p p2) {
        super(dh, p2, null, null, true);
    }
}
