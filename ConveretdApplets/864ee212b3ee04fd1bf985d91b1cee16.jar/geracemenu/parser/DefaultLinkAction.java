// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import java.io.IOException;
import java.net.URL;
import geracemenu.LinkActionEvent;
import java.awt.event.ActionEvent;
import geracemenu.LinkActionListener;

class DefaultLinkAction implements LinkActionListener
{
    public void actionPerformed(final ActionEvent actionEvent) {
        switch (actionEvent.getID()) {
            case 2000: {
                try {
                    final String actionTarget = ((LinkActionEvent)actionEvent).getActionTarget();
                    if (MenuBuilder.getAppletContext() != null) {
                        MenuBuilder.getAppletContext().showDocument(new URL(actionEvent.getActionCommand()), (actionTarget != null) ? actionTarget : "_blank");
                    }
                }
                catch (IOException ex) {
                    System.out.println("DefaultLinkAction(): exception [" + ex.getMessage() + ']');
                }
                break;
            }
            case 2001: {
                String s;
                if ((s = ((LinkActionEvent)actionEvent).getActionTitle()) == null) {
                    s = actionEvent.getActionCommand();
                }
                if (MenuBuilder.getAppletContext() != null) {
                    MenuBuilder.getAppletContext().showStatus((s != null) ? s : "");
                }
                break;
            }
        }
    }
}
