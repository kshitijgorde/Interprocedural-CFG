// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.FileFilter;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.io.PrintStream;
import java.io.File;
import java.io.InputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.net.URL;
import java.util.Map;
import java.net.ServerSocket;

public class SchemaServer implements Runnable
{
    static Level level;
    private int port;
    private ServerSocket listener;
    private SearchAlgorithm searchAlgorithm;
    private final Map<String, URL> inventory;
    
    static {
        SchemaServer.level = Level.err;
    }
    
    public SchemaServer() {
        this(defaultPort());
    }
    
    public SchemaServer(final int port) {
        this.listener = null;
        this.searchAlgorithm = SearchAlgorithm.files;
        this.inventory = new ConcurrentHashMap<String, URL>();
        this.port = port;
        this.buildInventory();
    }
    
    public Thread start() throws IOException {
        final InetSocketAddress addr = new InetSocketAddress(defaultAddress(), this.port);
        (this.listener = new ServerSocket()).bind(addr);
        final Thread thread = new Thread(this, "schema server");
        thread.start();
        return thread;
    }
    
    @Override
    public void run() {
        trace(Level.info, "Listening on 127.0.0.1:" + this.port);
    Label_0025_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        final Socket client = this.listener.accept();
                        this.processRequest(client);
                        client.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    continue Label_0025_Outer;
                }
                continue;
            }
        }
    }
    
    public void setSearchAlgorithm(final SearchAlgorithm alg) {
        this.searchAlgorithm = alg;
    }
    
    public InputStream processRequest(final URL url) throws IOException {
        final URL decoded = this.inventory.get(url.getPath());
        return (decoded != null) ? decoded.openStream() : null;
    }
    
    public void buildInventory() {
        this.inventory.clear();
        final String schemaJar = System.getProperty("xsd.server.jar");
        if (schemaJar != null) {
            this.searchAlgorithm = SearchAlgorithm.xsdjar;
        }
        switch (this.searchAlgorithm) {
            case all: {
                this.processDirectory(this.getBaseDir());
                this.processClasspath();
                break;
            }
            case files: {
                this.processDirectory(this.getBaseDir());
                break;
            }
            case classpath: {
                this.processClasspath();
                break;
            }
            case xsdjar: {
                this.processJar(new File(schemaJar));
                break;
            }
        }
        trace(Level.debug, this);
    }
    
    public void storeInventory(final File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        final PrintStream ps = new PrintStream(file);
        for (final String key : this.inventory.keySet()) {
            ps.print(key);
            ps.print(' ');
            ps.println(this.inventory.get(key));
        }
        ps.close();
    }
    
    public Map<String, URL> getInventory() {
        return this.inventory;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nschema server: inventory -\n");
        for (final String key : this.inventory.keySet()) {
            sb.append(key);
            sb.append(" --> ");
            sb.append(this.inventory.get(key));
            sb.append('\n');
        }
        return sb.toString();
    }
    
    private void processRequest(final Socket client) {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            final Map<String, String> header = this.readheader(reader);
            final String method = header.get("method");
            final String path = header.get("path");
            if (!method.equals("GET")) {
                trace(Level.err, "Method=" + method + " - not supported");
                reader.close();
                client.close();
                return;
            }
            final PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            final boolean encoded = !header.get("user-agent:").contains("Java");
            this.copySchema(path, writer, encoded);
            writer.close();
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Map<String, String> readheader(final BufferedReader reader) throws IOException {
        final Map<String, String> result = new ConcurrentHashMap<String, String>();
        result.put("content-type:", "");
        result.put("user-agent:", "");
        String line = reader.readLine();
        StringTokenizer tk = new StringTokenizer(line);
        result.put("method", tk.nextToken());
        result.put("path", tk.nextToken());
        result.put("version", tk.nextToken());
        while (true) {
            line = reader.readLine();
            if (line == null || line.length() == 0) {
                break;
            }
            tk = new StringTokenizer(line);
            final String key = tk.nextToken();
            final String value = tk.nextToken();
            result.put(key.toLowerCase(), value);
        }
        return result;
    }
    
    private void copySchema(final String path, final PrintWriter writer, final boolean encoded) throws IOException {
        final URL url = this.inventory.get(path);
        if (url == null) {
            if (encoded) {
                writer.println("HTTP/1.1 400");
            }
            return;
        }
        if (encoded) {
            writer.println("HTTP/1.1 200");
            writer.println("Content-type: text/plain\n");
        }
        final char[] bfr = new char[10240];
        final InputStreamReader reader = new InputStreamReader(url.openStream());
        while (true) {
            final int n = reader.read(bfr);
            if (n == -1) {
                break;
            }
            writer.write(bfr, 0, n);
        }
        reader.close();
    }
    
    private void processClasspath() {
        final String classpath = System.getProperty("java.class.path");
        final String pathSeparator = System.getProperty("path.separator");
        trace(Level.debug, "Process classpath: " + classpath);
        final StringTokenizer tk = new StringTokenizer(classpath, pathSeparator);
        while (tk.hasMoreTokens()) {
            final String item = tk.nextToken();
            if (item.endsWith(".jar")) {
                this.processJar(new File(item));
            }
        }
    }
    
    private void processDirectory(final File file) {
        if (this.invalidDirectory(file)) {
            return;
        }
        trace(Level.debug, "Process directory: " + file);
        final File[] list = file.listFiles(new SchemaFilter());
        for (int i = 0; i < list.length; ++i) {
            if (list[i].isDirectory()) {
                this.processDirectory(list[i]);
            }
            else {
                this.processSchema(list[i]);
            }
        }
    }
    
    private void processSchema(final File file) {
        trace(Level.debug, "Process (.xsd): " + file);
        try {
            final Parser parser = new Parser();
            parser.parse(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void processJar(final File file) {
        if (this.invalid(file)) {
            return;
        }
        trace(Level.debug, "Process (.jar): " + file);
        try {
            final JarFile jf = new JarFile(file);
            final Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                final JarEntry je = en.nextElement();
                if (je.isDirectory()) {
                    continue;
                }
                if (!je.getName().endsWith(".xsd")) {
                    continue;
                }
                final Parser parser = new Parser();
                parser.parse(file, je);
            }
            jf.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private File getBaseDir() {
        final String homeDir = System.getProperty("cornerstone.home", System.getProperty("user.dir"));
        final File file = new File(homeDir);
        if (this.invalidDirectory(file)) {
            return null;
        }
        File result = file;
        trace(Level.debug, "Root directory=" + file);
        while (result != null && !result.getName().equals("cornerstone")) {
            result = result.getParentFile();
        }
        return (result == null) ? file : result;
    }
    
    private boolean invalid(final File f) {
        if (f == null || !f.canRead()) {
            trace(Level.err, "File '" + f + "' invalid");
            return true;
        }
        return false;
    }
    
    private boolean invalidDirectory(final File d) {
        if (d == null || !d.canRead() || !d.isDirectory()) {
            trace(Level.err, "Directory '" + d + "' invalid");
            return true;
        }
        return false;
    }
    
    private static void trace(final Level at, final Object object) {
        if (at.toInt() <= SchemaServer.level.toInt()) {
            System.out.println(object);
        }
    }
    
    public static void main(final String[] args) {
        int port = 80;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        try {
            final SchemaServer server = new SchemaServer(port);
            server.start();
            Thread.currentThread().join();
        }
        catch (Exception e) {
            e.printStackTrace();
            trace(Level.err, "Terminated");
        }
    }
    
    static String defaultAddress() {
        return System.getProperty("xsd.server.address", "127.0.0.1");
    }
    
    static int defaultPort() {
        final String property = System.getProperty("xsd.server.port", "0");
        return Integer.parseInt(property);
    }
    
    enum Level
    {
        err("err", 0, 0), 
        info("info", 1, 1), 
        debug("debug", 2, 2);
        
        int v;
        
        private Level(final String s, final int n2, final int n) {
            this.v = n;
        }
        
        int toInt() {
            return this.v;
        }
    }
    
    enum SearchAlgorithm
    {
        all("all", 0), 
        files("files", 1), 
        classpath("classpath", 2), 
        xsdjar("xsdjar", 3);
        
        private SearchAlgorithm(final String s, final int n) {
        }
    }
    
    class SchemaFilter implements FileFilter
    {
        @Override
        public boolean accept(final File f) {
            return f.isDirectory() || f.getName().endsWith(".xsd");
        }
    }
    
    class Parser
    {
        private URL url;
        
        void parse(final File file) throws Exception {
            this.url = this.getURL(file);
            this.parse(this.url.openStream());
        }
        
        void parse(final File jf, final JarEntry je) throws Exception {
            this.url = this.getURL(jf, je);
            this.parse(this.url.openStream());
        }
        
        public void parse(final InputStream istr) throws Exception {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(istr));
            while (true) {
                final String line = reader.readLine();
                if (line == null) {
                    break;
                }
                int start = line.indexOf("targetNamespace");
                if (start == -1) {
                    continue;
                }
                start = line.indexOf(34, start);
                final int end = line.indexOf(34, ++start);
                if (end != -1) {
                    final String tns = line.substring(start, end);
                    final String key = String.valueOf(this.stripDomain(tns)) + this.stripPath(this.url);
                    SchemaServer.this.inventory.put(key, this.url);
                    break;
                }
                break;
            }
        }
        
        String stripPath(final URL url) {
            final String name = url.getPath();
            return name.substring(name.lastIndexOf(47));
        }
        
        URL getURL(final File file) {
            try {
                return file.toURI().toURL();
            }
            catch (MalformedURLException e) {
                trace(Level.err, file);
                e.printStackTrace();
                return null;
            }
        }
        
        URL getURL(final File jf, final JarEntry je) {
            String path = "jar:file:///" + jf + "!/" + je.getName();
            try {
                path = path.replace('\\', '/');
                return new URL(path);
            }
            catch (Exception e) {
                trace(Level.err, path);
                e.printStackTrace();
                return null;
            }
        }
        
        private String stripDomain(final String ns) {
            return ns.replaceFirst("[a-z:]+//[.a-zA-Z-_0-9]+", "");
        }
    }
}
