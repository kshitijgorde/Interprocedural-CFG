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
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.a9;
import java.awt.Frame;
import com.diginet.digichat.client.h;
import com.diginet.digichat.common.User;
import com.diginet.digichat.awt.aa;
import com.diginet.ui.cz;
import java.awt.TextField;
import java.awt.Label;
import com.diginet.ui.b1;
import java.awt.FileDialog;
import com.diginet.digichat.awt.ae;

public class cd extends ae implements ag, Runnable
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
    protected b1 k;
    protected Label l;
    protected TextField m;
    protected Label n;
    protected Label o;
    protected cz p;
    protected aa q;
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
    protected final String ab;
    private Thread ac;
    protected String ad;
    protected String ae;
    protected String af;
    protected User ag;
    protected h ah;
    protected int ai;
    
    protected void f() {
        this.j = new FileDialog(new Frame(), this.a ? "Select File to Send" : "Select Location to Save File", this.a ? 0 : 1);
        if (!this.a) {
            this.j.setFile(this.ad);
        }
        for (int i = 0; i == 0; i = ((!this.a || this.g()) ? 1 : 0)) {
            this.j.show();
            if (this.j.getFile() == null && this.j.getDirectory() == null) {
                this.d();
                return;
            }
            this.ad = this.j.getDirectory() + this.j.getFile();
        }
        final a9 a9 = new a9();
        if (this.ah.df.borderImgs != null) {
            a9.a(this.ah.df.borderImgs);
        }
        (this.m = new TextField(this.ad, 25)).setEditable(false);
        this.l = new Label(this.a ? "Selected File:" : "File to Receive:");
        this.k = new b1("CANCEL", this.getFont());
        this.n = new Label("Status:");
        this.o = new Label(this.a ? "Waiting for acknowledgement" : "Sent acknowledgement");
        (this.q = new aa()).b(this.ah.df.fileTransferLogo);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        a9.setLayout(layout);
        final Insets insets = new Insets(3, 10, 3, 10);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = insets;
        layout.setConstraints(this.q, gridBagConstraints);
        a9.add(this.q);
        final Panel panel = new Panel();
        panel.setLayout(layout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        layout.setConstraints(this.l, gridBagConstraints);
        panel.add(this.l);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.m, gridBagConstraints);
        panel.add(this.m);
        gridBagConstraints.insets = insets;
        layout.setConstraints(panel, gridBagConstraints);
        a9.add(panel);
        (this.p = new cz()).setForeground(new Color(255, 201, 92));
        this.p.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.p, gridBagConstraints);
        a9.add(this.p);
        final Panel panel2 = new Panel();
        panel2.setLayout(layout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.n, gridBagConstraints);
        panel2.add(this.n);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.o, gridBagConstraints);
        panel2.add(this.o);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(this.k, gridBagConstraints);
        panel2.add(this.k);
        gridBagConstraints.insets = insets;
        layout.setConstraints(panel2, gridBagConstraints);
        a9.add(panel2);
        this.add(a9);
        this.resize(375, 180);
        this.p.resize(this.size().width, this.p.preferredSize().height);
        this.setResizable(false);
        this.setBackground(this.ah.df.tabsBackground);
        this.setVisible(true);
        if (!this.a) {
            this.k();
        }
        else {
            this.l();
        }
    }
    
    public final String a() {
        return this.af;
    }
    
    public final String b() {
        return this.ae;
    }
    
    public final User c() {
        return this.ag;
    }
    
    protected final void k() {
        this.h = 5;
        this.ah.a(this.ai, this.ag);
        (this.ac = new Thread(this)).start();
    }
    
    protected final void l() {
        this.h = 1;
        this.af = new File(this.ad).getName();
        this.ai = this.ah.b(this);
    }
    
    protected boolean g() {
        if (this.ad == null) {
            return false;
        }
        final File file = new File(this.ad);
        this.ae = file.length() + "";
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
            final Socket socket = new Socket(this.ag.e, 9000);
            this.o.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(this.ad);
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
                    this.o.setText("Transferring file" + " " + this.a(currentTimeMillis2, currentTimeMillis, n));
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
    
    protected final String a(final long n, final long n2, final int n3) {
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
            final File file = new File(this.ad);
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
                    this.o.setText("Transferring file" + " " + this.a(currentTimeMillis2, currentTimeMillis, n));
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
    
    public final void d() {
        if (this.h != 4) {
            this.ah.a(this.c());
            this.ah.e(this.ai);
        }
        this.h = 2;
        this.dispose();
    }
    
    public final void e() {
        this.h = 3;
        this.o.setText("Received acknowledgement");
        (this.ac = new Thread(this)).start();
    }
    
    public final void run() {
        if (this.h == 6) {
            this.f();
            return;
        }
        if (this.a) {
            if (this.j()) {
                this.h = 4;
                this.o.setText("Completed");
                this.k.a("CLOSE");
            }
        }
        else if (this.i()) {
            this.h = 4;
            this.o.setText("Completed");
            this.k.a("CLOSE");
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.k) {
                    switch (this.h) {
                        case 1:
                        case 3:
                        case 5: {
                            this.d();
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
                this.d();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cd(final h h, final b6 b6) {
        this(h, b6.a, b6.b, b6.c, false);
        this.ai = b6.d;
        this.af = b6.b;
    }
    
    public cd(final h h, final User user) {
        this(h, user, null, null, true);
    }
    
    protected cd(final h ah, final User ag, final String ad, final String ae, final boolean a) {
        this.b = 1;
        this.c = 2;
        this.d = 3;
        this.e = 4;
        this.f = 5;
        this.g = 6;
        this.h = 6;
        this.i = 9000;
        this.r = "Select File to Send";
        this.s = "Select Location to Save File";
        this.t = "Selected File:";
        this.u = "File to Receive:";
        this.v = "CANCEL";
        this.w = "CLOSE";
        this.x = "Waiting for acknowledgement";
        this.y = "Received acknowledgement";
        this.z = "Sent acknowledgement";
        this.aa = "Transferring file";
        this.ab = "Completed";
        this.ai = -1;
        this.setTitle("File Transfer " + (a ? "to" : "from") + " " + ag.getName());
        this.ag = ag;
        this.ah = ah;
        this.ae = ae;
        this.ad = ad;
        this.a = a;
        if (a && !ah.a((ag)this)) {
            this.dispose();
        }
        else {
            (this.ac = new Thread(this)).start();
        }
    }
}
