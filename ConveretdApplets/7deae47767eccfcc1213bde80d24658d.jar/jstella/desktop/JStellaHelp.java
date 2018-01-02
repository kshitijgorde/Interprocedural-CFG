// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import java.awt.Frame;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.util.List;
import javax.swing.JDialog;

public class JStellaHelp extends JDialog
{
    private static final long serialVersionUID = 2061886447370587328L;
    private static List<JSHelpTopic> myHelpTopicList;
    private static final String RESOURCE_DOCS = "/jstella/resources/help/";
    private static final String DOC_GENERAL = "/jstella/resources/help/jstella_general.html";
    private static final String DOC_CONTROLS = "/jstella/resources/help/jstella_controls.html";
    private static final String DOC_METADATA = "/jstella/resources/help/jstella_metadata.html";
    private static final String DOC_CREDITS = "/jstella/resources/help/jstella_credits.html";
    private TopicListModel myTopicListModel;
    private JList ListBoxTopics;
    private JScrollPane SPContent;
    private JScrollPane SPTopics;
    private JSplitPane SplitPaneCenter;
    private JEditorPane myHTMLPane;
    
    private JStellaHelp(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.myTopicListModel = new TopicListModel();
        this.initComponents();
        this.ListBoxTopics.setModel(this.myTopicListModel);
        this.ListBoxTopics.setSelectedIndex(0);
    }
    
    private void initComponents() {
        this.SplitPaneCenter = new JSplitPane();
        this.SPTopics = new JScrollPane();
        this.ListBoxTopics = new JList();
        this.SPContent = new JScrollPane();
        this.myHTMLPane = new JEditorPane();
        this.setDefaultCloseOperation(2);
        this.setTitle("JStella Help");
        this.SplitPaneCenter.setDividerLocation(150);
        this.ListBoxTopics.setModel(new AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            
            public int getSize() {
                return this.strings.length;
            }
            
            public Object getElementAt(final int i) {
                return this.strings[i];
            }
        });
        this.ListBoxTopics.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent evt) {
                JStellaHelp.this.ListBoxTopicsValueChanged(evt);
            }
        });
        this.SPTopics.setViewportView(this.ListBoxTopics);
        this.SplitPaneCenter.setLeftComponent(this.SPTopics);
        this.myHTMLPane.setContentType("text/html");
        this.myHTMLPane.setEditable(false);
        this.SPContent.setViewportView(this.myHTMLPane);
        this.SplitPaneCenter.setRightComponent(this.SPContent);
        this.getContentPane().add(this.SplitPaneCenter, "Center");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 623) / 2, (screenSize.height - 431) / 2, 623, 431);
    }
    
    private void ListBoxTopicsValueChanged(final ListSelectionEvent evt) {
        this.updateContents();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JStellaHelp(new JFrame(), true, null).setVisible(true);
            }
        });
    }
    
    private void updateContents() {
        try {
            final int zSelectedIndex = this.ListBoxTopics.getSelectedIndex();
            if (zSelectedIndex == -1) {
                this.myHTMLPane.setText("");
            }
            else {
                this.myHTMLPane.setPage(JStellaHelp.myHelpTopicList.get(zSelectedIndex).getHelpPageURL());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void runJStellaHelp(final Frame aParent) {
        final JStellaHelp zJSH = new JStellaHelp(aParent, true);
        zJSH.setLocationRelativeTo(aParent);
        zJSH.setVisible(true);
    }
    
    static {
        (JStellaHelp.myHelpTopicList = new ArrayList<JSHelpTopic>()).add(new JSHelpTopic("General", "/jstella/resources/help/jstella_general.html"));
        JStellaHelp.myHelpTopicList.add(new JSHelpTopic("Default controls", "/jstella/resources/help/jstella_controls.html"));
        JStellaHelp.myHelpTopicList.add(new JSHelpTopic("Metadata", "/jstella/resources/help/jstella_metadata.html"));
        JStellaHelp.myHelpTopicList.add(new JSHelpTopic("Credits", "/jstella/resources/help/jstella_credits.html"));
    }
    
    public class TopicListModel extends AbstractListModel
    {
        private static final long serialVersionUID = -1323852623896830395L;
        
        public int getSize() {
            return JStellaHelp.myHelpTopicList.size();
        }
        
        public Object getElementAt(final int index) {
            return JStellaHelp.myHelpTopicList.get(index);
        }
    }
    
    public static class JSHelpTopic
    {
        private String myTopicName;
        private URL myHelpPageURL;
        
        public JSHelpTopic(final String aTopicName, final String aHelpPageResource) {
            this.myTopicName = "";
            this.myHelpPageURL = null;
            this.myTopicName = aTopicName;
            this.myHelpPageURL = this.getClass().getResource(aHelpPageResource);
        }
        
        public String toString() {
            return this.myTopicName;
        }
        
        public URL getHelpPageURL() {
            return this.myHelpPageURL;
        }
    }
}
