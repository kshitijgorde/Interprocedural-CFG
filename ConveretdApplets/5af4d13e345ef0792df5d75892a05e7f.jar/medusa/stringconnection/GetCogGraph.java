// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import java.util.regex.Matcher;
import medusa.graph.Edge;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import medusa.graph.Graph;

public class GetCogGraph implements Runnable
{
    private Thread me;
    private boolean done;
    private String url;
    private int port;
    private String[] queries;
    private Graph g;
    private float cutoff;
    private String status;
    int progress;
    
    public GetCogGraph(final String url, final int port, final String[] queries, final float cutoff) {
        this.status = "ok";
        this.progress = 0;
        this.url = url;
        this.port = port;
        this.queries = queries;
        this.cutoff = cutoff;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public Graph getGraph() {
        return this.g;
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
                this.getCogGraphFromString();
            }
            catch (IOException ex) {
                this.g = new Graph();
                this.status = "Could not read from STRING";
            }
            catch (NumberFormatException ex2) {
                this.g = new Graph();
                this.status = "No matches found";
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
    
    public void getCogGraphFromString() throws IOException, NumberFormatException {
        final int length = this.queries.length;
        this.progress = 0;
        this.g = new Graph();
        final Socket socket = new Socket(this.url, this.port);
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.queries.length; ++i) {
            sb.append("COG:");
            sb.append(this.queries[i]);
            sb.append("\n");
        }
        sb.append("bye");
        printWriter.println(sb.toString());
        while (!bufferedReader.ready()) {}
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() > 5) {
                this.parseCogEdge(this.g, line);
            }
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
    
    private void parseCogEdge(final Graph graph, final String s) throws NumberFormatException {
        final String[] split = s.split("\\t+");
        if (Float.parseFloat(split[split.length - 1]) / 1000.0f < this.cutoff) {
            return;
        }
        if (s.compareTo("Not found") == 0) {
            return;
        }
        final Pattern compile = Pattern.compile("^(COG\\d+)");
        String group = split[0];
        String group2 = split[1];
        final Matcher matcher = compile.matcher(split[0]);
        if (matcher.find()) {
            group = matcher.group(1);
        }
        final Matcher matcher2 = compile.matcher(split[1]);
        if (matcher2.find()) {
            group2 = matcher2.group(1);
        }
        for (int i = 2; i < split.length - 1; ++i) {
            final float n = Float.parseFloat(split[i]) / 1000.0f;
            if (n > 0.0) {
                graph.addEdge(new Edge(group, group2, n, i - 1));
                ++this.progress;
            }
        }
    }
}
