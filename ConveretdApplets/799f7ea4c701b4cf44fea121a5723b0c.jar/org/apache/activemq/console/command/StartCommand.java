// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Iterator;
import org.apache.activemq.broker.BrokerFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.activemq.broker.BrokerService;
import java.util.List;
import java.net.URI;

public class StartCommand extends AbstractCommand
{
    public static final String DEFAULT_CONFIG_URI = "xbean:activemq.xml";
    protected String[] helpFile;
    private URI configURI;
    private List<BrokerService> brokers;
    
    public StartCommand() {
        this.helpFile = new String[] { "Task Usage: Main start [start-options] [uri]", "Description: Creates and starts a broker using a configuration file, or a broker URI.", "", "Start Options:", "    -D<name>=<value>      Define a system property.", "    --version             Display the version information.", "    -h,-?,--help          Display the start broker help information.", "", "URI:", "", "    XBean based broker configuration:", "", "        Example: Main xbean:file:activemq.xml", "            Loads the xbean configuration file from the current working directory", "        Example: Main xbean:activemq.xml", "            Loads the xbean configuration file from the classpath", "", "    URI Parameter based broker configuration:", "", "        Example: Main broker:(tcp://localhost:61616, tcp://localhost:5000)?useJmx=true", "            Configures the broker with 2 transport connectors and jmx enabled", "        Example: Main broker:(tcp://localhost:61616, network:tcp://localhost:5000)?persistent=false", "            Configures the broker with 1 transport connector, and 1 network connector and persistence disabled", "" };
        this.brokers = new ArrayList<BrokerService>(5);
    }
    
    @Override
    protected void runTask(final List<String> brokerURIs) throws Exception {
        try {
            if (brokerURIs.isEmpty()) {
                this.setConfigUri(new URI("xbean:activemq.xml"));
                this.startBroker(this.getConfigUri());
            }
            else {
                while (!brokerURIs.isEmpty()) {
                    final String strConfigURI = brokerURIs.remove(0);
                    try {
                        this.setConfigUri(new URI(strConfigURI));
                    }
                    catch (URISyntaxException e) {
                        this.context.printException(e);
                        return;
                    }
                    this.startBroker(this.getConfigUri());
                }
            }
        }
        catch (Exception e2) {
            this.context.printException(new RuntimeException("Failed to execute start task. Reason: " + e2, e2));
            throw new Exception(e2);
        }
        this.waitForShutdown();
    }
    
    public void startBroker(final URI configURI) throws Exception {
        System.out.println("Loading message broker from: " + configURI);
        final BrokerService broker = BrokerFactory.createBroker(configURI);
        this.brokers.add(broker);
        broker.start();
    }
    
    protected void waitForShutdown() throws Exception {
        final boolean[] shutdown = { false };
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                final Iterator<BrokerService> i = StartCommand.this.brokers.iterator();
                while (i.hasNext()) {
                    try {
                        final BrokerService broker = i.next();
                        broker.stop();
                    }
                    catch (Exception e) {}
                }
            }
        });
        final AtomicInteger brokerCounter = new AtomicInteger(this.brokers.size());
        for (final BrokerService bs : this.brokers) {
            bs.addShutdownHook(new Runnable() {
                @Override
                public void run() {
                    if (brokerCounter.decrementAndGet() == 0) {
                        synchronized (shutdown) {
                            shutdown[0] = true;
                            shutdown.notify();
                        }
                    }
                }
            });
        }
        synchronized (shutdown) {
            while (!shutdown[0]) {
                try {
                    shutdown.wait();
                }
                catch (InterruptedException e) {}
            }
        }
    }
    
    public void setConfigUri(final URI uri) {
        this.configURI = uri;
    }
    
    public URI getConfigUri() {
        return this.configURI;
    }
    
    @Override
    protected void printHelp() {
        this.context.printHelp(this.helpFile);
    }
}
