// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient.doc;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import HTTPClient.HTTPResponse;
import HTTPClient.HTTPConnection;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class HTTPClientExample extends Applet implements Runnable, ActionListener
{
    private HTTPConnection con;
    private HTTPResponse rsp;
    private String script;
    private String disp;
    private Thread thread;
    private boolean done;
    private TextArea text;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.text = new TextArea(60, 60));
        this.text.setEditable(false);
        final Button button = new Button("Doit");
        button.addActionListener(this);
        this.add("South", button);
        try {
            this.con = new HTTPConnection(this.getCodeBase());
        }
        catch (Exception ex) {
            this.disp = "Error creating HTTPConnection:\n" + ex;
            this.repaint();
        }
    }
    
    public void start() {
        if (this.thread == null) {
            this.done = false;
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void run() {
        try {
            while (true) {
                this.waitForDoit();
                if (this.done) {
                    break;
                }
                this.disp = "POSTing ...";
                this.repaint();
                this.rsp = this.con.Post(this.script, "Hello World again");
                this.repaint();
            }
        }
        catch (Exception ex) {
            this.disp = "Error POSTing: " + ex;
            ex.printStackTrace();
            this.repaint();
        }
    }
    
    private synchronized void waitForDoit() {
        try {
            this.wait();
        }
        catch (InterruptedException ex) {}
    }
    
    private synchronized void notifyDoit() {
        this.notify();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.done = true;
            this.notifyDoit();
            this.thread = null;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.notifyDoit();
    }
    
    public void paint(final Graphics graphics) {
        this.text.setText(String.valueOf(this.disp) + "\n");
        if (this.rsp == null) {
            return;
        }
        try {
            this.text.append("\n---Headers:\n" + this.rsp.toString());
            this.text.append("\n---Data:\n" + this.rsp.getText() + "\n");
        }
        catch (Exception ex) {
            this.text.append("\n---Got Exception:\n" + ex + "\n");
        }
    }
    
    public HTTPClientExample() {
        this.script = "/cgi-bin/my_script.cgi";
        this.disp = "";
        this.done = false;
    }
}
