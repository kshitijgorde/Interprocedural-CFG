// 
// Decompiled by Procyon v0.5.30
// 

package a;

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
import java.awt.Label;
import java.awt.Button;
import java.awt.FileDialog;

public class bH extends aT implements cj, Runnable
{
    protected boolean q;
    protected int q;
    protected FileDialog q;
    protected Button q;
    protected Label q;
    protected Label w;
    protected Label e;
    protected Label r;
    protected bQ q;
    private Thread q;
    protected String q;
    protected String w;
    protected String e;
    protected l q;
    private co q;
    protected int w;
    
    protected void q() {
        if (!(this.q = new FileDialog(new Frame(), this.q ? "Select File to Send" : "Select Location to Save File", this.q ? 0 : 1))) {
            this.q.setFile(this.q);
        }
        for (int i = 0; i == 0; i = ((!this.q || this.q()) ? 1 : 0)) {
            this.q.show();
            if (this.q.getFile() == null && this.q.getDirectory() == null) {
                this.r();
                return;
            }
            this.q = this.q.getDirectory() + this.q.getFile();
        }
        this.w = new Label(this.q);
        this.q = new Label(this.q ? "Selected File:" : "File to Receive:");
        this.q = new Button("Cancel");
        this.e = new Label("Status:");
        this.r = new Label(this.q ? "Waiting for acknowledgement" : "Sent acknowledgement");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        (this.q = new bQ()).setForeground(new Color(0, 0, 128));
        this.q.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.r, gridBagConstraints);
        this.add(this.r);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.resize(this.q ? 350 : 375, 180);
        this.q.resize(this.size().width, this.q.preferredSize().height);
        this.setResizable(false);
        this.setBackground(aS.w.i);
        this.setVisible(true);
        if (!this.q) {
            this.w();
            return;
        }
        this.e();
    }
    
    public final String q() {
        return this.e;
    }
    
    public final String w() {
        return this.w;
    }
    
    public final l q() {
        return this.q;
    }
    
    protected final void w() {
        ((co)(this.q = 5)).q(this.w, this.q);
        (this.q = new Thread(this)).start();
    }
    
    protected final void e() {
        this.q = 1;
        this.e = new File(this.q).getName();
        this.w = this.q.q((cj)this);
    }
    
    protected boolean q() {
        if (this.q == null) {
            return false;
        }
        final File file = new File(this.q);
        this.w = file.length() + "";
        return file.exists();
    }
    
    protected boolean w() {
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            final Socket socket = new Socket(this.q.y, 9000);
            this.r.setText("Transferring file");
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            file = new File(this.q);
            final long long1 = dataInputStream.readLong();
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            fileOutputStream = new FileOutputStream(file);
            int n = 0;
            while (n < long1 && this.q != 2) {
                final int read = dataInputStream.read(array);
                n += read;
                fileOutputStream.write(array, 0, read);
                this.q.q((int)(100.0f * (n / long1)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    this.r.setText("Transferring file " + q(currentTimeMillis2, currentTimeMillis, n));
                }
            }
            fileOutputStream.close();
            socket.close();
            if (this.q == 2) {
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
    
    protected static String q(final long n, final long n2, final int n3) {
        boolean b = false;
        double n4;
        if ((n4 = n3 / 1024 / ((n2 - n) / 1000L)) > 1024.0) {
            n4 /= 1024.0;
            b = true;
        }
        String s;
        final int index = (s = Math.floor(n4 * 1000.0 + 5.0) / 1000.0 + "").indexOf(46);
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
    
    protected boolean e() {
        try {
            final ServerSocket serverSocket;
            (serverSocket = new ServerSocket(9000, 1)).setSoTimeout(30000);
            final Socket accept = serverSocket.accept();
            this.r.setText("Transferring file");
            final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            final File file;
            dataOutputStream.writeLong((file = new File(this.q)).length());
            final byte[] array = new byte[1000];
            final long currentTimeMillis2;
            long currentTimeMillis = currentTimeMillis2 = System.currentTimeMillis();
            final FileInputStream fileInputStream;
            final int available = (fileInputStream = new FileInputStream(file)).available();
            int n = 0;
            while (n < available && this.q != 2) {
                final int read = fileInputStream.read(array);
                n += read;
                dataOutputStream.write(array, 0, read);
                this.q.q((int)(100.0f * (n / available)));
                if (System.currentTimeMillis() > currentTimeMillis + 1000L) {
                    currentTimeMillis = System.currentTimeMillis();
                    this.r.setText("Transferring file " + q(currentTimeMillis2, currentTimeMillis, n));
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
    
    public final void r() {
        if (this.q != 4) {
            this.q.q(this.q);
            this.q.y(this.w);
        }
        this.q = 2;
        this.dispose();
    }
    
    public final void t() {
        this.q = 3;
        this.r.setText("Received acknowledgement");
        (this.q = new Thread(this)).start();
    }
    
    public void run() {
        if (this.q == 6) {
            this.q();
            return;
        }
        if (this.q) {
            if (this.e()) {
                this.q = 4;
                this.r.setText("Completed");
                this.q.setLabel("Close");
            }
        }
        else if (this.w()) {
            this.q = 4;
            this.r.setText("Completed");
            this.q.setLabel("Close");
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    switch (this.q) {
                        case 1:
                        case 3:
                        case 5: {
                            this.r();
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
                this.r();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public bH(final co co, final bW bw) {
        this(co, bw.q, bw.q, bw.w, false);
        this.w = bw.q;
        this.e = bw.q;
    }
    
    public bH(final co co, final l l) {
        this(co, l, null, null, true);
    }
    
    protected bH(final co q, final l q2, final String q3, final String w, final boolean q4) {
        super(false);
        this.q = 6;
        this.w = -1;
        this.q = q2;
        this.q = q;
        this.w = w;
        this.q = q3;
        this.q = q4;
        if (q4 && !q.q((cj)this)) {
            this.dispose();
            return;
        }
        (this.q = new Thread(this)).start();
    }
}
