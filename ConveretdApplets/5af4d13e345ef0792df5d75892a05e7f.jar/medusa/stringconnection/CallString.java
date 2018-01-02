// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JRadioButton;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JPanel;

public class CallString implements Runnable
{
    private Thread me;
    private boolean done;
    private String url;
    private int port;
    private String[] queries;
    private JPanel resultPanel;
    int progress;
    
    public CallString(final String url, final int port, final String[] queries) {
        this.progress = 0;
        this.url = url;
        this.port = port;
        this.queries = queries;
    }
    
    public JPanel getPanel() {
        return this.resultPanel;
    }
    
    public void start() {
        if (this.me == null) {
            this.me = new Thread(this);
        }
        this.me.start();
    }
    
    public void stop() {
        if (this.me != null) {
            this.me = null;
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        this.done = false;
        if (this.me == currentThread) {
            try {
                this.resultPanel = this.callString();
            }
            catch (IOException ex) {
                this.resultPanel = this.errorPanel("Database not responding");
            }
        }
        this.done = true;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public JPanel callString() throws IOException {
        this.progress = 0;
        final JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new BoxLayout(panel, 1));
        final Socket socket = new Socket(this.url, this.port);
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int n = 0;
        for (int i = 0; i < this.queries.length; ++i) {
            ++this.progress;
            printWriter.println("GN:" + this.queries[i]);
            while (!bufferedReader.ready()) {}
            final String line = bufferedReader.readLine();
            if (line != null) {
                final String[] split = line.split("[\\n\\t]");
                for (int j = 0; j < split.length; ++j) {
                    if (this.checkError(split[j])) {
                        printWriter.close();
                        bufferedReader.close();
                        socket.close();
                        return this.errorPanel("Database is not responding");
                    }
                    split[j] = split[j].replaceAll(" ", "");
                    if (split[j].length() > 1) {
                        final JRadioButton radioButton = new JRadioButton(split[j]);
                        radioButton.setBackground(new Color(255, 255, 255));
                        panel.add(radioButton);
                        ++n;
                    }
                }
            }
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();
        if (n == 0) {
            panel.add(new JLabel("Sorry, no matches found"));
        }
        return panel;
    }
    
    private JPanel errorPanel() {
        return this.errorPanel("STRING is not responding: server may be down");
    }
    
    private JPanel errorPanel(final String s) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        final JLabel label = new JLabel(s);
        panel.add(label);
        panel.add(new JLabel("If this problem persists, please contact"));
        panel.add(new JLabel("hooper@embl.de"));
        panel.add(label);
        return panel;
    }
    
    private boolean checkError(final String s) {
        return s.startsWith("Error");
    }
}
