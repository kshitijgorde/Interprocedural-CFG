// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import java.util.StringTokenizer;
import javax.management.openmbean.CompositeData;
import javax.management.ObjectName;
import java.util.Iterator;
import javax.management.ObjectInstance;
import org.apache.activemq.console.util.JmxMBeansUtil;
import java.util.ArrayList;
import java.util.List;

public class PurgeCommand extends AbstractJmxCommand
{
    protected String[] helpFile;
    private final List<String> queryAddObjects;
    private final List<String> querySubObjects;
    
    public PurgeCommand() {
        this.helpFile = new String[] { "Task Usage: Main purge [browse-options] <destinations>", "Description: Delete selected destination's messages that matches the message selector.", "", "Purge Options:", "    --msgsel <msgsel1,msglsel2>   Add to the search list messages matched by the query similar to", "                                  the messages selector format.", "    --jmxurl <url>                Set the JMX URL to connect to.", "    --pid <pid>                   Set the pid to connect to (only on Sun JVM).", "    --jmxuser <user>              Set the JMX user used for authenticating.", "    --jmxpassword <password>      Set the JMX password used for authenticating.", "    --jmxlocal                    Use the local JMX server instead of a remote one.", "    --version                     Display the version information.", "    -h,-?,--help                  Display the browse broker help information.", "", "Examples:", "    Main purge FOO.BAR", "        - Delete all the messages in queue FOO.BAR", "    Main purge --msgsel JMSMessageID='*:10',JMSPriority>5 FOO.*", "        - Delete all the messages in the destinations that matches FOO.* and has a JMSMessageID in", "          the header field that matches the wildcard *:10, and has a JMSPriority field > 5 in the", "          queue FOO.BAR", "        * To use wildcard queries, the field must be a string and the query enclosed in ''", "" };
        this.queryAddObjects = new ArrayList<String>(10);
        this.querySubObjects = new ArrayList<String>(10);
    }
    
    @Override
    protected void runTask(final List<String> tokens) throws Exception {
        try {
            if (tokens.isEmpty()) {
                tokens.add("*");
            }
            final Iterator<String> i = tokens.iterator();
            while (i.hasNext()) {
                final List queueList = JmxMBeansUtil.queryMBeans(this.createJmxConnection(), "Type=Queue,Destination=" + i.next() + ",*");
                final Iterator j = queueList.iterator();
                while (j.hasNext()) {
                    final ObjectName queueName = j.next().getObjectName();
                    if (this.queryAddObjects.isEmpty()) {
                        this.purgeQueue(queueName);
                    }
                    else {
                        final List messages = JmxMBeansUtil.createMessageQueryFilter(this.createJmxConnection(), queueName).query(this.queryAddObjects);
                        this.purgeMessages(queueName, messages);
                    }
                }
            }
        }
        catch (Exception e) {
            this.context.printException(new RuntimeException("Failed to execute purge task. Reason: " + e));
            throw new Exception(e);
        }
    }
    
    public void purgeQueue(final ObjectName queue) throws Exception {
        this.context.printInfo("Purging all messages in queue: " + queue.getKeyProperty("Destination"));
        this.createJmxConnection().invoke(queue, "purge", new Object[0], new String[0]);
    }
    
    public void purgeMessages(final ObjectName queue, final List messages) throws Exception {
        final Object[] param = { null };
        for (final CompositeData msg : messages) {
            param[0] = "" + msg.get("JMSMessageID");
            this.context.printInfo("Removing message: " + param[0] + " from queue: " + queue.getKeyProperty("Destination"));
            this.createJmxConnection().invoke(queue, "removeMessage", param, new String[] { "java.lang.String" });
        }
    }
    
    @Override
    protected void handleOption(final String token, final List<String> tokens) throws Exception {
        if (token.startsWith("--msgsel")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("Message selector not specified"));
                return;
            }
            final StringTokenizer queryTokens = new StringTokenizer(tokens.remove(0), ",");
            while (queryTokens.hasMoreTokens()) {
                this.queryAddObjects.add(queryTokens.nextToken());
            }
        }
        else if (token.startsWith("--xmsgsel")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("Message selector not specified"));
                return;
            }
            final StringTokenizer queryTokens = new StringTokenizer(tokens.remove(0), ",");
            while (queryTokens.hasMoreTokens()) {
                this.querySubObjects.add(queryTokens.nextToken());
            }
        }
        else {
            super.handleOption(token, tokens);
        }
    }
    
    @Override
    protected void printHelp() {
        this.context.printHelp(this.helpFile);
    }
}
