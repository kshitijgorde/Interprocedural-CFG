// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import java.nio.channels.WritableByteChannel;
import java.io.FileInputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import java.nio.channels.FileChannel;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Attr;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.io.File;

public class CreateCommand extends AbstractCommand
{
    protected final String[] helpFile;
    protected final String DEFAULT_TARGET_ACTIVEMQ_CONF = "conf/activemq.xml";
    protected final String DEFAULT_BROKERNAME_XPATH = "/beans/broker/@brokerName";
    protected final String[] BASE_SUB_DIRS;
    protected final String BROKER_NAME_REGEX = "[$][{]brokerName[}]";
    protected String amqConf;
    protected String[][] fileCopyMap;
    protected String[][] fileWriteMap;
    protected String brokerName;
    protected File amqHome;
    protected File targetAmqBase;
    private static final String winActivemqData = "@echo off\nset ACTIVEMQ_HOME=\"${activemq.home}\"\nset ACTIVEMQ_BASE=\"${activemq.base}\"\n\nset PARAM=%1\n:getParam\nshift\nif \"%1\"==\"\" goto end\nset PARAM=%PARAM% %1\ngoto getParam\n:end\n\n%ACTIVEMQ_HOME%/bin/activemq %PARAM%";
    private static final String unixActivemqData = "## Figure out the ACTIVEMQ_BASE from the directory this script was run from\nPRG=\"$0\"\nprogname=`basename \"$0\"`\nsaveddir=`pwd`\n# need this for relative symlinks\ndirname_prg=`dirname \"$PRG\"`\ncd \"$dirname_prg\"\nwhile [ -h \"$PRG\" ] ; do\n  ls=`ls -ld \"$PRG\"`\n  link=`expr \"$ls\" : '.*-> \\(.*\\)$'`\n  if expr \"$link\" : '.*/.*' > /dev/null; then\n    PRG=\"$link\"\n  else\n    PRG=`dirname \"$PRG\"`\"/$link\"\n  fi\ndone\nACTIVEMQ_BASE=`dirname \"$PRG\"`/..\ncd \"$saveddir\"\n\nACTIVEMQ_BASE=`cd \"$ACTIVEMQ_BASE\" && pwd`\n\nexport ACTIVEMQ_HOME=${activemq.home}\nexport ACTIVEMQ_BASE=$ACTIVEMQ_BASE\n\n${ACTIVEMQ_HOME}/bin/activemq \"$*\"";
    
    public CreateCommand() {
        this.helpFile = new String[] { "Task Usage: Main create path/to/brokerA [create-options]", "Description:  Creates a runnable broker instance in the specified path.", "", "List Options:", "    --amqconf <file path>   Path to ActiveMQ conf file that will be used in the broker instance. Default is: conf/activemq.xml", "    --version               Display the version information.", "    -h,-?,--help            Display the create broker help information.", "" };
        this.BASE_SUB_DIRS = new String[] { "bin", "conf" };
        this.amqConf = "conf/activemq.xml";
        this.fileCopyMap = new String[][] { { "conf/log4j.properties", "conf/log4j.properties" }, { "conf/broker.ks", "conf/broker.ks" }, { "conf/broker.ts", "conf/broker.ts" }, { "conf/camel.xml", "conf/camel.xml" }, { "conf/jetty.xml", "conf/jetty.xml" }, { "conf/jetty-realm.properties", "conf/jetty-realm.properties" }, { "conf/credentials.properties", "conf/credentials.properties" } };
        this.fileWriteMap = new String[][] { { "winActivemq", "bin/${brokerName}.bat" }, { "unixActivemq", "bin/${brokerName}" } };
    }
    
    @Override
    protected void runTask(final List<String> tokens) throws Exception {
        this.context.print("Running create broker task...");
        this.amqHome = new File(System.getProperty("activemq.home"));
        for (final String token : tokens) {
            this.targetAmqBase = new File(token);
            this.brokerName = this.targetAmqBase.getName();
            Label_0191: {
                if (this.targetAmqBase.exists()) {
                    final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                    String resp;
                    do {
                        this.context.print("Target directory (" + this.targetAmqBase.getCanonicalPath() + ") already exists. Overwrite (y/n): ");
                        resp = console.readLine();
                        if (resp.equalsIgnoreCase("y")) {
                            break Label_0191;
                        }
                        if (resp.equalsIgnoreCase("yes")) {
                            break Label_0191;
                        }
                    } while (!resp.equalsIgnoreCase("n") && !resp.equalsIgnoreCase("no"));
                    return;
                }
            }
            this.context.print("Creating directory: " + this.targetAmqBase.getCanonicalPath());
            this.targetAmqBase.mkdirs();
            this.createSubDirs(this.targetAmqBase, this.BASE_SUB_DIRS);
            this.writeFileMapping(this.targetAmqBase, this.fileWriteMap);
            this.copyActivemqConf(this.amqHome, this.targetAmqBase, this.amqConf);
            this.copyFileMapping(this.amqHome, this.targetAmqBase, this.fileCopyMap);
        }
    }
    
    @Override
    protected void handleOption(final String token, final List<String> tokens) throws Exception {
        if (token.startsWith("--amqconf")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("Attributes to amqconf not specified"));
                return;
            }
            this.amqConf = tokens.remove(0);
        }
        else {
            super.handleOption(token, tokens);
        }
    }
    
    protected void createSubDirs(final File target, final String[] subDirs) throws IOException {
        for (final String subDir : this.BASE_SUB_DIRS) {
            final File subDirFile = new File(target, subDir);
            this.context.print("Creating directory: " + subDirFile.getCanonicalPath());
            subDirFile.mkdirs();
        }
    }
    
    protected void writeFileMapping(final File targetBase, final String[][] fileWriteMapping) throws IOException {
        for (final String[] fileWrite : fileWriteMapping) {
            final File dest = new File(targetBase, this.resolveParam("[$][{]brokerName[}]", this.brokerName, fileWrite[1]));
            this.context.print("Creating new file: " + dest.getCanonicalPath());
            this.writeFile(fileWrite[0], dest);
        }
    }
    
    protected void copyFileMapping(final File srcBase, final File targetBase, final String[][] fileMapping) throws IOException {
        for (final String[] fileMap : fileMapping) {
            final File src = new File(srcBase, fileMap[0]);
            final File dest = new File(targetBase, this.resolveParam("[$][{]brokerName[}]", this.brokerName, fileMap[1]));
            this.context.print("Copying from: " + src.getCanonicalPath() + "\n          to: " + dest.getCanonicalPath());
            this.copyFile(src, dest);
        }
    }
    
    protected void copyActivemqConf(final File srcBase, final File targetBase, final String activemqConf) throws IOException, ParserConfigurationException, SAXException, TransformerException, XPathExpressionException {
        final File src = new File(srcBase, activemqConf);
        if (!src.exists()) {
            throw new FileNotFoundException("File: " + src.getCanonicalPath() + " not found.");
        }
        final File dest = new File(targetBase, "conf/activemq.xml");
        this.context.print("Copying from: " + src.getCanonicalPath() + "\n          to: " + dest.getCanonicalPath());
        final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        final Element docElem = builder.parse(src).getDocumentElement();
        final XPath xpath = XPathFactory.newInstance().newXPath();
        final Attr brokerNameAttr = (Attr)xpath.evaluate("/beans/broker/@brokerName", docElem, XPathConstants.NODE);
        brokerNameAttr.setValue(this.brokerName);
        this.writeToFile(new DOMSource(docElem), dest);
    }
    
    @Override
    protected void printHelp() {
        this.context.printHelp(this.helpFile);
    }
    
    private void writeFile(final String typeName, final File dest) throws IOException {
        String data;
        if (typeName.equals("winActivemq")) {
            data = "@echo off\nset ACTIVEMQ_HOME=\"${activemq.home}\"\nset ACTIVEMQ_BASE=\"${activemq.base}\"\n\nset PARAM=%1\n:getParam\nshift\nif \"%1\"==\"\" goto end\nset PARAM=%PARAM% %1\ngoto getParam\n:end\n\n%ACTIVEMQ_HOME%/bin/activemq %PARAM%";
            data = this.resolveParam("[$][{]activemq.home[}]", this.amqHome.getCanonicalPath().replaceAll("[\\\\]", "/"), data);
            data = this.resolveParam("[$][{]activemq.base[}]", this.targetAmqBase.getCanonicalPath().replaceAll("[\\\\]", "/"), data);
        }
        else {
            if (!typeName.equals("unixActivemq")) {
                throw new IllegalStateException("Unknown file type: " + typeName);
            }
            data = "## Figure out the ACTIVEMQ_BASE from the directory this script was run from\nPRG=\"$0\"\nprogname=`basename \"$0\"`\nsaveddir=`pwd`\n# need this for relative symlinks\ndirname_prg=`dirname \"$PRG\"`\ncd \"$dirname_prg\"\nwhile [ -h \"$PRG\" ] ; do\n  ls=`ls -ld \"$PRG\"`\n  link=`expr \"$ls\" : '.*-> \\(.*\\)$'`\n  if expr \"$link\" : '.*/.*' > /dev/null; then\n    PRG=\"$link\"\n  else\n    PRG=`dirname \"$PRG\"`\"/$link\"\n  fi\ndone\nACTIVEMQ_BASE=`dirname \"$PRG\"`/..\ncd \"$saveddir\"\n\nACTIVEMQ_BASE=`cd \"$ACTIVEMQ_BASE\" && pwd`\n\nexport ACTIVEMQ_HOME=${activemq.home}\nexport ACTIVEMQ_BASE=$ACTIVEMQ_BASE\n\n${ACTIVEMQ_HOME}/bin/activemq \"$*\"";
            data = this.resolveParam("[$][{]activemq.home[}]", this.amqHome.getCanonicalPath().replaceAll("[\\\\]", "/"), data);
            data = this.resolveParam("[$][{]activemq.base[}]", this.targetAmqBase.getCanonicalPath().replaceAll("[\\\\]", "/"), data);
        }
        final ByteBuffer buf = ByteBuffer.allocate(data.length());
        buf.put(data.getBytes());
        buf.flip();
        final FileChannel destinationChannel = new FileOutputStream(dest).getChannel();
        destinationChannel.write(buf);
        destinationChannel.close();
    }
    
    private void writeToFile(final Source src, final File file) throws TransformerException {
        final TransformerFactory tFactory = TransformerFactory.newInstance();
        final Transformer fileTransformer = tFactory.newTransformer();
        final Result res = new StreamResult(file);
        fileTransformer.transform(src, res);
    }
    
    private void copyFile(final File from, final File dest) throws IOException {
        if (!from.exists()) {
            return;
        }
        final FileChannel sourceChannel = new FileInputStream(from).getChannel();
        final FileChannel destinationChannel = new FileOutputStream(dest).getChannel();
        sourceChannel.transferTo(0L, sourceChannel.size(), destinationChannel);
        sourceChannel.close();
        destinationChannel.close();
    }
    
    private String resolveParam(final String paramName, final String paramValue, final String target) {
        return target.replaceAll(paramName, paramValue);
    }
}
