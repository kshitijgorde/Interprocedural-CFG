import jap.JAPController;
import com.apple.eawt.ApplicationEvent;
import com.apple.eawt.ApplicationAdapter;
import com.apple.mrj.MRJAboutHandler;
import com.apple.mrj.MRJQuitHandler;
import com.apple.mrj.MRJApplicationUtils;
import com.apple.eawt.ApplicationListener;
import com.apple.eawt.Application;

// 
// Decompiled by Procyon v0.5.30
// 

public class JAPMacintosh extends JAP
{
    JAPMacintosh(final String[] array) {
        super(array);
    }
    
    protected void registerMRJHandlers() {
        try {
            final Application application = Application.getApplication();
            application.addApplicationListener((ApplicationListener)new AppListener());
            application.addPreferencesMenuItem();
            application.setEnabledAboutMenu(true);
            application.setEnabledPreferencesMenu(true);
        }
        catch (Exception ex) {
            final MRJI mrji = new MRJI();
            MRJApplicationUtils.registerQuitHandler((MRJQuitHandler)mrji);
            MRJApplicationUtils.registerAboutHandler((MRJAboutHandler)mrji);
        }
    }
    
    public static void main(final String[] array) {
        final JAPMacintosh japMacintosh = new JAPMacintosh(array);
        japMacintosh.registerMRJHandlers();
        japMacintosh.startJAP();
    }
    
    class AppListener extends ApplicationAdapter
    {
        public void handleAbout(final ApplicationEvent applicationEvent) {
            JAPController.aboutJAP();
            applicationEvent.setHandled(true);
        }
        
        public void handleQuit(final ApplicationEvent applicationEvent) {
            applicationEvent.setHandled(true);
            JAPController.goodBye(true);
        }
        
        public void handlePreferences(final ApplicationEvent applicationEvent) {
            applicationEvent.setHandled(true);
            JAPController.getInstance().showConfigDialog();
        }
    }
    
    class MRJI implements MRJQuitHandler, MRJAboutHandler
    {
        public void handleQuit() {
            JAPController.goodBye(true);
        }
        
        public void handleAbout() {
            JAPController.aboutJAP();
        }
    }
}
