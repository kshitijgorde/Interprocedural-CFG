// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.beans.PropertyDescriptor;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import com.itt.J2KViewer.util.J2KViewerBeanInfo;
import javax.swing.UIManager;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import java.net.BindException;
import com.itt.J2KViewer.util.Log;
import java.net.ServerSocket;

public class EnterpriseViewer
{
    private static final int LISTEN_PORT = 12345;
    private static boolean m_anotherInstanceRunning;
    private static ServerSocket m_ss;
    private static ConnectionHandler m_ch;
    private static Thread m_listenThread;
    private static Log log;
    private static EnterpriseViewerFrame mainFrame;
    static /* synthetic */ Class class$com$itt$J2KViewer$EnterpriseViewer;
    
    public static void openImage(final String s, final String s2) {
        EnterpriseViewer.mainFrame.openImage(s, s2);
    }
    
    protected static void registerThisIntanceOfTheApplicationAsRunning() {
        try {
            EnterpriseViewer.m_ss = new ServerSocket(12345);
            EnterpriseViewer.m_anotherInstanceRunning = false;
            EnterpriseViewer.m_ch = new ConnectionHandler();
            (EnterpriseViewer.m_listenThread = new Thread(EnterpriseViewer.m_ch)).start();
        }
        catch (BindException ex2) {
            EnterpriseViewer.m_anotherInstanceRunning = true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected static void sendURLToOtherApplicationInstance(final String s) {
        try {
            final Socket socket = new Socket("localhost", 12345);
            new PrintWriter(socket.getOutputStream(), true).println(s);
            socket.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(final String[] array) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            EnterpriseViewer.log.info(ex.getMessage());
        }
        registerThisIntanceOfTheApplicationAsRunning();
        String s = null;
        String s2 = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals("-propfile")) {
                s = array[++i];
                EnterpriseViewer.log.debug(s);
            }
            else if (array[i].equals("-help") || array[i].equals("-h")) {
                System.out.println("\nUsage for EnterpriseViewer. No arguments are required.\n  For help info:\n    -help  or -h\n  To specify an alternate properties file. File should be in classpath:\n    -propfile <file name>");
                System.exit(0);
            }
            else if (array[i].equals("-proplist")) {
                final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
                System.out.println("\nThese are the available properties for use in IAS Viewer properties file:");
                for (int j = 0; j < propertyDescriptors.length; ++j) {
                    if (propertyDescriptors[j].getWriteMethod() != null) {
                        System.out.println(propertyDescriptors[j].getName() + " - Type = " + propertyDescriptors[j].getPropertyType());
                    }
                }
                System.exit(0);
            }
            else if (s2 == null) {
                s2 = array[i];
            }
        }
        if (!EnterpriseViewer.m_anotherInstanceRunning) {
            EnterpriseViewer.mainFrame = new EnterpriseViewerFrame();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final int n = (int)(screenSize.width * 0.95);
            final int n2 = (int)(screenSize.height * 0.8);
            EnterpriseViewer.mainFrame.setBounds(screenSize.width / 2 - n / 2, screenSize.height / 2 - n2 / 2, n, n2);
            EnterpriseViewer.mainFrame.setIconImage(Helper.loadImage("IASLogoIcon.gif", "IAS Logo").getImage());
            if (s2 != null && s2.trim().length() != 0) {
                EnterpriseViewer.mainFrame.setVisible(true);
                openImage(s, s2);
            }
            else {
                EnterpriseViewer.mainFrame.setVisible(true);
            }
        }
        else {
            sendURLToOtherApplicationInstance(s2);
            System.exit(0);
        }
        if (!EnterpriseViewer.mainFrame.isVisible()) {
            EnterpriseViewer.mainFrame.setVisible(true);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        EnterpriseViewer.m_anotherInstanceRunning = false;
        EnterpriseViewer.log = new Log((EnterpriseViewer.class$com$itt$J2KViewer$EnterpriseViewer == null) ? (EnterpriseViewer.class$com$itt$J2KViewer$EnterpriseViewer = class$("com.itt.J2KViewer.EnterpriseViewer")) : EnterpriseViewer.class$com$itt$J2KViewer$EnterpriseViewer);
    }
    
    public static class OpenFileMessage implements Runnable
    {
        private String m_url_string;
        
        public OpenFileMessage(final String url_string) {
            this.m_url_string = url_string;
        }
        
        public void run() {
            if (this.m_url_string != null) {
                EnterpriseViewer.openImage(null, this.m_url_string);
            }
        }
    }
    
    public static class ConnectionHandler implements Runnable
    {
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    String line;
                    while ((line = new BufferedReader(new InputStreamReader(EnterpriseViewer.m_ss.accept().getInputStream())).readLine()) != null) {
                        SwingUtilities.invokeLater(new OpenFileMessage(line));
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
}
