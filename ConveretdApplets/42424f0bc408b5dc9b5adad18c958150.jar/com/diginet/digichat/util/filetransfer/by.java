// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import java.awt.Event;
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
import java.awt.Frame;
import com.diginet.digichat.client.i;
import com.diginet.digichat.common.j;
import com.diginet.ui.ce;
import java.awt.Label;
import java.awt.Button;
import java.awt.FileDialog;
import com.diginet.digichat.awt.aa;

public class by extends aa implements ab, Runnable
{
    protected boolean a;
    protected final int b;
    protected final int c;
    protected final int d;
    protected final int e;
    protected final int f;
    protected final int g;
    protected int h;
    protected final int i;
    protected FileDialog j;
    protected Button k;
    protected Label l;
    protected Label m;
    protected Label n;
    protected Label o;
    protected ce p;
    protected final String q;
    protected final String r;
    protected final String s;
    protected final String t;
    protected final String u;
    protected final String v;
    protected final String w;
    protected final String x;
    protected final String y;
    protected final String z;
    protected final String aa;
    private Thread ab;
    protected String ac;
    protected String ad;
    protected String ae;
    protected j af;
    protected i ag;
    protected int ah;
    
    protected void g() {
        this.j = new FileDialog(new Frame(), this.a ? "Select File to Send" : "Select Location to Save File", this.a ? 0 : 1);
        if (!this.a) {
            this.j.setFile(this.ac);
        }
        for (int i = 0; i == 0; i = ((!this.a || this.h()) ? 1 : 0)) {
            this.j.show();
            if (this.j.getFile() == null && this.j.getDirectory() == null) {
                this.e();
                return;
            }
            this.ac = this.j.getDirectory() + this.j.getFile();
        }
        this.m = new Label(this.ac);
        this.l = new Label(this.a ? "Selected File:" : "File to Receive:");
        this.k = new Button("Cancel");
        this.n = new Label("Status:");
        this.o = new Label(this.a ? "Waiting for acknowledgement" : "Sent acknowledgement");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(this.l, gridBagConstraints);
        this.add(this.l);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.m, gridBagConstraints);
        this.add(this.m);
        (this.p = new ce()).setForeground(new Color(0, 0, 128));
        this.p.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.p, gridBagConstraints);
        this.add(this.p);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(this.n, gridBagConstraints);
        this.add(this.n);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.o, gridBagConstraints);
        this.add(this.o);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.k, gridBagConstraints);
        this.add(this.k);
        this.resize(this.a ? 350 : 375, 180);
        this.p.resize(this.size().width, this.p.preferredSize().height);
        this.setResizable(false);
        this.setBackground(this.ag.ca.j);
        this.setVisible(true);
        if (!this.a) {
            this.k();
        }
        else {
            this.l();
        }
    }
    
    public String a() {
        return this.ae;
    }
    
    public String b() {
        return this.ad;
    }
    
    public j c() {
        return this.af;
    }
    
    protected void k() {
        this.h = 5;
        this.ag.a(this.ah, this.af);
        (this.ab = new Thread(this)).start();
    }
    
    protected void l() {
        this.h = 1;
        this.ae = new File(this.ac).getName();
        this.ah = this.ag.b(this);
    }
    
    protected boolean h() {
        if (this.ac == null) {
            return false;
        }
        final File file = new File(this.ac);
        this.ad = file.length() + "";
        return file.exists();
    }
    
    protected boolean i() {
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            final Socket socket = new Socket(this.af.e, 9000);
            this.o.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(this.ac);
            final long long1 = dataInputStream.readLong();
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            fileOutputStream = new FileOutputStream(file);
            int n = 0;
            while (n < long1 && this.h != 2) {
                final int read = dataInputStream.read(array);
                n += read;
                fileOutputStream.write(array, 0, read);
                this.p.a((int)(100.0f * (n / long1)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    this.o.setText("Transferring file " + this.a(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileOutputStream.close();
            socket.close();
            if (this.h == 2) {
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
    
    protected String a(final long n, final long n2, final int n3) {
        boolean b = false;
        double n4 = n3 / 1024 / ((n2 - n) / 1000L);
        if (n4 > 1024.0) {
            n4 /= 1024.0;
            b = true;
        }
        String s = Math.floor(n4 * 1000.0 + 5.0) / 1000.0 + "";
        final int index = s.indexOf(46);
        final int n5 = s.length() - 1;
        if (index < 0) {
            s += ".00";
        }
        else if (index == 0) {
            s = "0" + s;
        }
        else if (index == n5) {
            s += "00";
        }
        else if (index == n5 - 1) {
            s += "0";
        }
        else if (index + 2 < n5) {
            s = s.substring(0, index + 3);
        }
        return s + (b ? " MB/sec" : " KB/sec");
    }
    
    protected boolean j() {
        try {
            final ServerSocket serverSocket = new ServerSocket(9000, 1);
            serverSocket.setSoTimeout(30000);
            final Socket accept = serverSocket.accept();
            this.o.setText("Transferring file");
            final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            final File file = new File(this.ac);
            dataOutputStream.writeLong(file.length());
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            final FileInputStream fileInputStream = new FileInputStream(file);
            final int available = fileInputStream.available();
            int n = 0;
            while (n < available && this.h != 2) {
                final int read = fileInputStream.read(array);
                n += read;
                dataOutputStream.write(array, 0, read);
                this.p.a((int)(100.0f * (n / available)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    this.o.setText("Transferring file " + this.a(currentTimeMillis2, currentTimeMillis, n));
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
    
    public void e() {
        if (this.h != 4) {
            this.ag.a(this.c());
            this.ag.e(this.ah);
        }
        this.h = 2;
        this.dispose();
    }
    
    public void f() {
        this.h = 3;
        this.o.setText("Received acknowledgement");
        (this.ab = new Thread(this)).start();
    }
    
    public void run() {
        if (this.h == 6) {
            this.g();
            return;
        }
        if (this.a) {
            if (this.j()) {
                this.h = 4;
                this.o.setText("Completed");
                this.k.setLabel("Close");
            }
        }
        else if (this.i()) {
            this.h = 4;
            this.o.setText("Completed");
            this.k.setLabel("Close");
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.k) {
                    switch (this.h) {
                        case 1:
                        case 3:
                        case 5: {
                            this.e();
                            return true;
                        }
                        case 4: {
                            this.dispose();
                            break;
                        }
                    }
                    return true;
                }
                break;
            }
            case 201: {
                this.e();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public by(final i i, final bu bu) {
        this(i, bu.a, bu.b, bu.c, false);
        this.ah = bu.d;
        this.ae = bu.b;
    }
    
    public by(final i i, final j j) {
        this(i, j, null, null, true);
    }
    
    protected by(final i ag, final j af, final String ac, final String ad, final boolean a) {
        this.b = 1;
        this.c = 2;
        this.d = 3;
        this.e = 4;
        this.f = 5;
        this.g = 6;
        this.h = 6;
        this.i = 9000;
        this.q = "Select File to Send";
        this.r = "Select Location to Save File";
        this.s = "Selected File:";
        this.t = "File to Receive:";
        this.u = "Cancel";
        this.v = "Close";
        this.w = "Waiting for acknowledgement";
        this.x = "Received acknowledgement";
        this.y = "Sent acknowledgement";
        this.z = "Transferring file";
        this.aa = "Completed";
        this.ah = -1;
        this.af = af;
        this.ag = ag;
        this.ad = ad;
        this.ac = ac;
        this.a = a;
        if (a && !ag.a((ab)this)) {
            this.dispose();
        }
        else {
            (this.ab = new Thread(this)).start();
        }
    }
}
