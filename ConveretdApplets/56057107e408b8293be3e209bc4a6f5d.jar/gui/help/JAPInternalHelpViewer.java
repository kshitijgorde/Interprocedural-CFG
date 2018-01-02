// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import javax.swing.text.Document;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import logging.LogHolder;
import logging.LogType;
import anon.util.ResourceLoader;
import javax.swing.text.JTextComponent;
import gui.JTextComponentToClipboardCopier;
import java.util.Vector;
import java.awt.Cursor;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import gui.JAPHelpContext;
import java.net.URL;
import platform.AbstractOS;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import java.util.Locale;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.Icon;
import gui.GUIUtils;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.awt.Component;
import anon.util.JAPMessages;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import anon.util.LanguageMapper;
import gui.dialog.JAPDialog;

public class JAPInternalHelpViewer extends JAPDialog
{
    private String m_helpPath;
    private LanguageMapper m_language;
    private JComboBox m_comBoxLanguage;
    private HtmlPane m_htmlpaneTheHelpPane;
    private JButton m_closeButton;
    private JButton m_backButton;
    private JButton m_forwardButton;
    private JButton m_homeButton;
    private boolean m_initializing;
    private JAPInternalHelpDelegator m_delegator;
    
    JAPInternalHelpViewer(final Frame frame) {
        super(frame, JAPMessages.getString(JAPHelp.MSG_HELP_WINDOW), false);
        this.m_helpPath = " ";
        this.m_language = new LanguageMapper();
        this.setDefaultCloseOperation(1);
        this.m_initializing = true;
        (this.m_htmlpaneTheHelpPane = new HtmlPane()).addPropertyChangeListener(new HelpListener());
        final JPanel panel = new JPanel(new FlowLayout(0));
        this.m_comBoxLanguage = new JComboBox();
        (this.m_backButton = new JButton(GUIUtils.loadImageIcon(JAPHelp.IMG_PREVIOUS, true))).setBackground(Color.gray);
        this.m_backButton.setOpaque(false);
        this.m_backButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.m_backButton.setFocusPainted(false);
        (this.m_forwardButton = new JButton(GUIUtils.loadImageIcon(JAPHelp.IMG_NEXT, true))).setBackground(Color.gray);
        this.m_forwardButton.setOpaque(false);
        this.m_forwardButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.m_forwardButton.setFocusPainted(false);
        (this.m_homeButton = new JButton(GUIUtils.loadImageIcon(JAPHelp.IMG_HOME, true))).setBackground(Color.gray);
        this.m_homeButton.setOpaque(false);
        this.m_homeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.m_homeButton.setFocusPainted(false);
        this.m_closeButton = new JButton(JAPMessages.getString(JAPHelp.MSG_CLOSE_BUTTON));
        this.m_forwardButton.setEnabled(false);
        this.m_backButton.setEnabled(false);
        panel.add(this.m_homeButton);
        panel.add(this.m_backButton);
        panel.add(this.m_forwardButton);
        panel.add(new JLabel("   "));
        panel.add(this.m_comBoxLanguage);
        panel.add(new JLabel("   "));
        panel.add(this.m_closeButton);
        this.getContentPane().add(this.m_htmlpaneTheHelpPane, "Center");
        this.getContentPane().add(panel, "North");
        this.getRootPane().setDefaultButton(this.m_closeButton);
        this.m_closeButton.addActionListener(new HelpListener());
        this.m_backButton.addActionListener(new HelpListener());
        this.m_forwardButton.addActionListener(new HelpListener());
        this.m_homeButton.addActionListener(new HelpListener());
        this.m_comBoxLanguage.addActionListener(new HelpListener());
        int n = 1;
        while (true) {
            try {
                final String string = JAPMessages.getString(JAPHelp.MSG_LANGUAGE_CODE + String.valueOf(n));
                final LanguageMapper language = new LanguageMapper(string, new Locale(string, ""));
                this.m_comBoxLanguage.addItem(language);
                if ((this.m_helpPath.equals(" ") && this.m_language.getISOCode().length() == 0) || language.getISOCode().equals(JAPMessages.getLocale().getLanguage())) {
                    this.m_helpPath = "help/";
                    this.m_language = language;
                    this.m_comBoxLanguage.setSelectedIndex(n - 1);
                }
            }
            catch (Exception ex) {
                break;
            }
            ++n;
        }
        ((JComponent)this.getContentPane()).setPreferredSize(new Dimension(Math.min(Toolkit.getDefaultToolkit().getScreenSize().width - 50, 600), Math.min(Toolkit.getDefaultToolkit().getScreenSize().height - 80, 350)));
        this.pack();
        this.m_initializing = false;
        this.m_delegator = new JAPInternalHelpDelegator(this);
    }
    
    public void loadCurrentContext() {
        this.setVisible(true);
    }
    
    public void setVisible(final boolean visible) {
        boolean b = false;
        if (visible) {
            final JAPHelpContext.IHelpContext helpContext = this.m_delegator.getHelpContext();
            final String s = (helpContext != null) ? helpContext.getHelpContext() : "index";
            try {
                if (AbstractOS.getInstance().openURL(new URL(s))) {
                    b = true;
                }
            }
            catch (Exception ex) {}
            if (!b) {
                try {
                    this.m_htmlpaneTheHelpPane.loadContext(this.m_helpPath, this.m_delegator.getHelpContext().getHelpContext(), this.m_language);
                }
                catch (Exception ex2) {}
            }
        }
        if (!b || !visible) {
            super.setVisible(visible);
        }
    }
    
    private void homePressed() {
        this.m_htmlpaneTheHelpPane.loadContext(this.m_helpPath, "index", this.m_language);
    }
    
    private void closePressed() {
        this.setVisible(false);
    }
    
    private void backPressed() {
        this.m_htmlpaneTheHelpPane.goBack();
        this.checkNavigationButtons();
    }
    
    private void forwardPressed() {
        this.m_htmlpaneTheHelpPane.goForward();
        this.checkNavigationButtons();
    }
    
    private void checkNavigationButtons() {
        if (this.m_htmlpaneTheHelpPane.backAllowed()) {
            this.m_backButton.setEnabled(true);
        }
        else {
            this.m_backButton.setEnabled(false);
        }
        if (this.m_htmlpaneTheHelpPane.forwardAllowed()) {
            this.m_forwardButton.setEnabled(true);
        }
        else {
            this.m_forwardButton.setEnabled(false);
        }
    }
    
    JAPHelp getHelp() {
        return this.m_delegator;
    }
    
    private class HelpListener implements ActionListener, PropertyChangeListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == JAPInternalHelpViewer.this.m_comBoxLanguage && !JAPInternalHelpViewer.this.m_initializing) {
                JAPInternalHelpViewer.this.m_helpPath = "help/";
                JAPInternalHelpViewer.this.m_language = new LanguageMapper(JAPMessages.getString(JAPHelp.MSG_LANGUAGE_CODE + String.valueOf(JAPInternalHelpViewer.this.m_comBoxLanguage.getSelectedIndex() + 1)));
                JAPInternalHelpViewer.this.m_htmlpaneTheHelpPane.loadContext(JAPInternalHelpViewer.this.m_helpPath, JAPInternalHelpViewer.this.m_delegator.getHelpContext().getHelpContext(), JAPInternalHelpViewer.this.m_language);
            }
            else if (actionEvent.getSource() == JAPInternalHelpViewer.this.m_closeButton) {
                JAPInternalHelpViewer.this.closePressed();
            }
            else if (actionEvent.getSource() == JAPInternalHelpViewer.this.m_backButton) {
                JAPInternalHelpViewer.this.backPressed();
            }
            else if (actionEvent.getSource() == JAPInternalHelpViewer.this.m_forwardButton) {
                JAPInternalHelpViewer.this.forwardPressed();
            }
            else if (actionEvent.getSource() == JAPInternalHelpViewer.this.m_homeButton) {
                JAPInternalHelpViewer.this.homePressed();
            }
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getSource() == JAPInternalHelpViewer.this.m_htmlpaneTheHelpPane) {
                JAPInternalHelpViewer.this.checkNavigationButtons();
            }
        }
    }
    
    private final class HtmlPane extends JScrollPane implements HyperlinkListener
    {
        private JAPHelp.IExternalURLCaller m_urlCaller;
        private JAPHelp.IExternalEMailCaller m_emailCaller;
        private JEditorPane html;
        private URL url;
        private Cursor cursor;
        private Vector m_history;
        private Vector m_historyViewports;
        private int m_historyPosition;
        private static final String MAILTO = "mailto";
        
        public HtmlPane() {
            this.m_urlCaller = AbstractOS.getInstance();
            this.m_emailCaller = AbstractOS.getInstance();
            this.html = new JEditorPane("text/html", "<html><body></body></html>");
            new JTextComponentToClipboardCopier(true).registerTextComponent(this.html);
            this.html.setEditable(false);
            this.html.addHyperlinkListener(this);
            this.m_history = new Vector();
            this.m_historyViewports = new Vector();
            this.m_historyPosition = -1;
            this.getViewport().add(this.html);
            this.cursor = this.html.getCursor();
        }
        
        public JEditorPane getPane() {
            return this.html;
        }
        
        public void goBack() {
            --this.m_historyPosition;
            this.loadURL(this.m_history.elementAt(this.m_historyPosition));
        }
        
        public void goForward() {
            ++this.m_historyPosition;
            this.loadURL(this.m_history.elementAt(this.m_historyPosition));
        }
        
        private void addToHistory(final URL url) {
            if (this.m_historyPosition == -1 || !url.getFile().equalsIgnoreCase(this.m_history.elementAt(this.m_historyPosition).getFile())) {
                this.m_history.insertElementAt(url, ++this.m_historyPosition);
            }
        }
        
        public boolean loadContext(final String s, final String s2, final LanguageMapper languageMapper) {
            final URL resourceURL = ResourceLoader.getResourceURL(s + languageMapper.getISOCode() + "/" + s + s2 + ".html");
            boolean loadContext = false;
            if (resourceURL != null) {
                this.linkActivated(resourceURL);
                loadContext = true;
            }
            else {
                LogHolder.log(4, LogType.GUI, "Could not load help context '" + languageMapper.getISOCode() + "/" + s + s2 + "'");
                if (s2 != null) {
                    if (!s2.equals("index")) {
                        loadContext = this.loadContext(s, "index", languageMapper);
                    }
                    else if (languageMapper.equals(new LanguageMapper("EN"))) {
                        LogHolder.log(3, LogType.GUI, "No index help file for language '" + languageMapper.getISOCode() + "', help files seem to be corrupt!");
                        return true;
                    }
                    if (!loadContext) {
                        final LanguageMapper languageMapper2 = new LanguageMapper("EN");
                        for (int i = 0; i < JAPInternalHelpViewer.this.m_comBoxLanguage.getItemCount(); ++i) {
                            if (((LanguageMapper)JAPInternalHelpViewer.this.m_comBoxLanguage.getItemAt(i)).equals(languageMapper2) && JAPInternalHelpViewer.this.m_comBoxLanguage.getSelectedIndex() != i) {
                                JAPInternalHelpViewer.this.m_comBoxLanguage.setSelectedIndex(i);
                                new HelpListener().actionPerformed(new ActionEvent(JAPInternalHelpViewer.this.m_comBoxLanguage, 0, ""));
                                break;
                            }
                        }
                        LogHolder.log(4, LogType.GUI, "No index help file for language '" + languageMapper.getISOCode() + "', switching to '" + languageMapper2.getISOCode() + "'");
                        loadContext = true;
                    }
                }
            }
            return loadContext;
        }
        
        public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
            if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                this.linkActivated(hyperlinkEvent.getURL());
            }
            else if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ENTERED) {
                this.html.setCursor(Cursor.getPredefinedCursor(12));
            }
            else if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.EXITED) {
                this.html.setCursor(this.cursor);
            }
        }
        
        private void linkActivated(final URL url) {
            this.html.setCursor(Cursor.getPredefinedCursor(3));
            SwingUtilities.invokeLater(new PageLoader(url));
            this.addToHistory(url);
            this.cleanForwardHistory();
            this.firePropertyChange("CheckButtons", false, true);
        }
        
        private void cleanForwardHistory() {
            for (int i = this.m_history.size() - 1; i > this.m_historyPosition; --i) {
                this.m_history.removeElementAt(i);
            }
        }
        
        public boolean backAllowed() {
            return this.m_historyPosition > 0;
        }
        
        public boolean forwardAllowed() {
            return this.m_history.size() - 1 > this.m_historyPosition;
        }
        
        private void loadURL(final URL url) {
            this.html.setCursor(Cursor.getPredefinedCursor(3));
            SwingUtilities.invokeLater(new PageLoader(url));
        }
        
        private final class PageLoader implements Runnable
        {
            PageLoader(final URL url) {
                HtmlPane.this.url = url;
            }
            
            public void run() {
                if (HtmlPane.this.url == null) {
                    HtmlPane.this.html.setCursor(HtmlPane.this.cursor);
                    HtmlPane.this.html.getParent().repaint();
                }
                else {
                    if (HtmlPane.this.url.getProtocol().startsWith("file") || HtmlPane.this.url.getProtocol().startsWith("zip") || HtmlPane.this.url.getProtocol().startsWith("jar") || HtmlPane.this.url.getProtocol().startsWith("systemresource")) {
                        final Document document = HtmlPane.this.html.getDocument();
                        try {
                            HtmlPane.this.html.setPage(HtmlPane.this.url);
                            return;
                        }
                        catch (IOException ex) {
                            HtmlPane.this.html.setDocument(document);
                            HtmlPane.this.getToolkit().beep();
                            return;
                        }
                        finally {
                            HtmlPane.this.url = null;
                            SwingUtilities.invokeLater(this);
                        }
                    }
                    boolean b;
                    if (HtmlPane.this.url.getProtocol().toLowerCase().startsWith("mailto")) {
                        b = HtmlPane.this.m_emailCaller.openEMail(HtmlPane.this.url.toString());
                    }
                    else {
                        b = HtmlPane.this.m_urlCaller.openURL(HtmlPane.this.url);
                    }
                    if (!b) {
                        HtmlPane.this.html.setCursor(HtmlPane.this.cursor);
                        JAPDialog.showMessageDialog(HtmlPane.this.html.getParent(), JAPMessages.getString(JAPHelp.MSG_ERROR_EXT_URL), new ExternalLinkedInformation(HtmlPane.this.url));
                    }
                    if (HtmlPane.this.m_historyPosition > 0) {
                        HtmlPane.this.m_historyPosition--;
                        HtmlPane.this.m_history.removeElementAt(HtmlPane.this.m_history.size() - 1);
                    }
                }
            }
            
            private class ExternalLinkedInformation extends LinkedInformationAdapter
            {
                private URL m_url;
                
                public ExternalLinkedInformation(final URL url) {
                    this.m_url = url;
                }
                
                public String getMessage() {
                    return this.m_url.toString();
                }
                
                public int getType() {
                    return 2;
                }
                
                public boolean isApplicationModalityForced() {
                    return true;
                }
            }
        }
    }
    
    class JAPInternalHelpDelegator extends JAPHelp
    {
        JAPInternalHelpViewer viewer;
        
        public JAPInternalHelpDelegator(final JAPInternalHelpViewer viewer) {
            this.viewer = viewer;
        }
        
        public boolean equals(final Object o) {
            return this.viewer.equals(o);
        }
        
        public int hashCode() {
            return this.viewer.hashCode();
        }
        
        public void loadCurrentContext() {
            this.viewer.loadCurrentContext();
        }
        
        public void setVisible(final boolean visible) {
            this.viewer.setVisible(visible);
        }
        
        public String toString() {
            return this.viewer.toString();
        }
        
        protected JAPDialog getOwnDialog() {
            return this.viewer;
        }
    }
}
