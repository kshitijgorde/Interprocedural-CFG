// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import java.util.Iterator;
import medusa.graph.Node;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import medusa.graph.Graph;

public class Annotator implements Runnable
{
    private Thread me;
    private boolean done;
    private String url;
    private int port;
    private Graph selection;
    private StringBuffer sb;
    private float cutoff;
    int progress;
    
    public Annotator(final String url, final int port, final Graph selection) {
        this.progress = 0;
        this.url = url;
        this.port = port;
        this.selection = selection;
        this.cutoff = this.cutoff;
    }
    
    public int getMax() {
        return this.selection.getNodeSize();
    }
    
    public StringBuffer getAnnotation() {
        return this.sb;
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
                this.getAnnotationFromString();
            }
            catch (IOException ex) {
                this.sb = new StringBuffer();
            }
            this.done = true;
        }
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public void getAnnotationFromString() throws IOException {
        this.sb = new StringBuffer();
        final Socket socket = new Socket(this.url, this.port);
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final StringBuffer sb = new StringBuffer();
        final Iterator<Node> nodesIterator = this.selection.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            sb.append("NODE:");
            sb.append(node.getLabel());
            sb.append("\n");
        }
        sb.append("bye");
        printWriter.println(sb);
        while (!bufferedReader.ready()) {}
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() > 10) {
                final String[] split = line.split("\t");
                final Node node2 = this.selection.getNode(split[0]);
                if (node2 != null) {
                    node2.setAnnotation(split[1]);
                }
            }
            ++this.progress;
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
