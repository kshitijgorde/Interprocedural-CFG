// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

import java.net.HttpURLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import soht.client.java.configuration.ConfigurationManager;
import soht.client.java.configuration.Host;

public class Proxy extends Thread
{
    private Host host;
    private ConfigurationManager configurationManager;
    private boolean running;
    private ServerSocket serverSocket;
    
    public Proxy(final ConfigurationManager configurationManager, final Host host) {
        super("Proxy-" + host.getLocalPort());
        this.running = false;
        this.serverSocket = null;
        this.configurationManager = configurationManager;
        this.host = host;
    }
    
    public Host getHost() {
        return this.host;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void startProxy() {
        if (!this.running) {
            this.start();
        }
    }
    
    public void stopProxy() {
        this.running = false;
        this.closeSocket();
    }
    
    public void closeSocket() {
        if (this.serverSocket != null) {
            try {
                this.serverSocket.close();
            }
            catch (IOException e) {
                System.out.println("Unable to close socket.");
            }
        }
    }
    
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.host.getLocalPort());
            this.host.setBoundLocalPort(this.serverSocket.getLocalPort());
            this.serverSocket.setSoTimeout(1000);
            this.running = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (this.running) {
            try {
                final Socket socket = this.serverSocket.accept();
                System.out.println("Proxy.run():   ReceiveBufferSize=" + socket.getReceiveBufferSize());
                System.out.println("Proxy.run():   SendBufferSize=" + socket.getSendBufferSize());
                final long connectionId = this.openHost();
                if (this.configurationManager.isUseStatelessConnection()) {
                    System.out.println("Using ReadWrite Thread.");
                    new ProxyReadWrite(this.getName() + "-ReadWrite", this.configurationManager, connectionId, socket).start();
                }
                else {
                    System.out.println("Using seperate Read and Write threads.");
                    new ProxyReader(this.getName() + "-Reader", this.configurationManager, connectionId, socket).start();
                    new ProxyWriter(this.getName() + "-Writer", this.configurationManager, connectionId, socket).start();
                }
            }
            catch (IOException ioException) {}
            catch (Exception e2) {
                System.out.println("Error processing connection open request: " + e2.getMessage());
            }
        }
    }
    
    public long openHost() throws Exception {
        final HttpURLConnection urlConnection = this.configurationManager.getURLConnection();
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
        out.write("action=open");
        out.write("&");
        out.write("host=" + this.host.getRemoteHost());
        out.write("&");
        out.write("port=" + this.host.getRemotePort());
        if (this.configurationManager.isServerLoginRequired()) {
            out.write("&");
            out.write("username=" + this.configurationManager.getServerUsername());
            out.write("&");
            out.write("password=" + this.configurationManager.getServerPassword());
            out.write("&");
            out.write("random=" + this.configurationManager.getRandom());
        }
        out.flush();
        out.close();
        urlConnection.connect();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            final String result = reader.readLine();
            if (result.startsWith("SUCCESS")) {
                final long connectionId = Long.parseLong(reader.readLine());
                System.out.println("Connection Successful");
                return connectionId;
            }
            throw new Exception("Unable to connect to remote host: " + result);
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            urlConnection.disconnect();
        }
    }
}
