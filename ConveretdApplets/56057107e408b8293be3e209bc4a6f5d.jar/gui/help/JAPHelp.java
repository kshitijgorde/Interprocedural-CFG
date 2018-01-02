// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import java.net.URL;
import logging.LogHolder;
import logging.LogType;
import java.awt.event.ActionEvent;
import gui.dialog.JAPDialog;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import anon.util.JAPMessages;
import javax.swing.JButton;
import java.awt.Frame;
import gui.JAPHelpContext;

public abstract class JAPHelp
{
    public static final String INDEX_CONTEXT = "index";
    public static final String IMG_HELP;
    public static final String MSG_HELP_BUTTON;
    public static final String MSG_HELP_MENU_ITEM;
    public static final String MSG_CLOSE_BUTTON;
    public static final String MSG_HELP_WINDOW;
    public static final String MSG_LANGUAGE_CODE;
    public static final String MSG_ERROR_EXT_URL;
    public static final String IMG_HOME;
    public static final String IMG_PREVIOUS;
    public static final String IMG_NEXT;
    private JAPHelpContext.IHelpContext m_helpContext;
    protected static JAPHelp ms_theJAPHelp;
    static /* synthetic */ Class class$gui$help$JAPHelp;
    
    public static void init(final Frame frame, final IHelpModel helpModel) {
        if (JAPHelp.ms_theJAPHelp == null) {
            JAPHelp.ms_theJAPHelp = createJAPhelp(frame, helpModel);
        }
    }
    
    public static JAPHelp getInstance() {
        return JAPHelp.ms_theJAPHelp;
    }
    
    public static final JButton createHelpButton(final JAPHelpContext.IHelpContext helpContext) {
        final JButton button = new JButton(JAPMessages.getString(JAPHelp.MSG_HELP_BUTTON));
        button.setToolTipText(JAPMessages.getString(JAPHelp.MSG_HELP_BUTTON));
        button.addActionListener(new HelpContextActionListener(helpContext));
        return button;
    }
    
    public static JMenuItem createHelpMenuItem(final JAPHelpContext.IHelpContext helpContext) {
        final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(JAPHelp.MSG_HELP_MENU_ITEM));
        menuItem.addActionListener(new HelpContextActionListener(helpContext));
        return menuItem;
    }
    
    public abstract void loadCurrentContext();
    
    public abstract void setVisible(final boolean p0);
    
    public final void setContext(final String s, final Component component) {
        if (s == null) {
            return;
        }
        this.m_helpContext = new JAPHelpContext.IHelpContext() {
            public String getHelpContext() {
                return s;
            }
            
            public Component getHelpExtractionDisplayContext() {
                return component;
            }
        };
    }
    
    public final void setContext(final JAPHelpContext.IHelpContext helpContext) {
        this.m_helpContext = helpContext;
    }
    
    public final JAPHelpContext.IHelpContext getHelpContext() {
        return this.m_helpContext;
    }
    
    protected JAPDialog getOwnDialog() {
        return null;
    }
    
    public static final JAPDialog getHelpDialog() {
        if (JAPHelp.ms_theJAPHelp == null) {
            return null;
        }
        return JAPHelp.ms_theJAPHelp.getOwnDialog();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        IMG_HELP = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_help.gif";
        MSG_HELP_BUTTON = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_helpButton";
        MSG_HELP_MENU_ITEM = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_helpMenuItem";
        MSG_CLOSE_BUTTON = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_closeButton";
        MSG_HELP_WINDOW = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_helpWindow";
        MSG_LANGUAGE_CODE = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_languageCode";
        MSG_ERROR_EXT_URL = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_errorExtURL";
        IMG_HOME = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_home.gif";
        IMG_PREVIOUS = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_previous.gif";
        IMG_NEXT = ((JAPHelp.class$gui$help$JAPHelp == null) ? (JAPHelp.class$gui$help$JAPHelp = class$("gui.help.JAPHelp")) : JAPHelp.class$gui$help$JAPHelp).getName() + "_next.gif";
        JAPHelp.ms_theJAPHelp = null;
    }
    
    static final class HelpContextActionListener implements ActionListener
    {
        private JAPHelpContext.IHelpContext m_helpContext;
        
        public HelpContextActionListener(final JAPHelpContext.IHelpContext helpContext) {
            this.m_helpContext = helpContext;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            JAPHelp.getInstance().setContext(this.m_helpContext);
            JAPHelp.getInstance().loadCurrentContext();
            if (JAPHelp.getHelpDialog() != null) {
                JAPHelp.getHelpDialog().toFront();
                JAPHelp.getHelpDialog().requestFocus();
            }
        }
    }
    
    private static class JAPHelpFactory
    {
        private static JAPHelp createJAPhelp(final Frame frame, final IHelpModel helpModel) {
            if (helpModel != null) {
                LogHolder.log(7, LogType.GUI, "Creating external help viewer.");
                return new JAPExternalHelpViewer(frame, helpModel);
            }
            LogHolder.log(7, LogType.GUI, "Creating internal help viewer.");
            return new JAPInternalHelpViewer(frame).getHelp();
        }
    }
    
    public interface IExternalURLCaller
    {
        boolean openURL(final URL p0);
    }
    
    public interface IExternalEMailCaller
    {
        boolean openEMail(final String p0);
    }
}
