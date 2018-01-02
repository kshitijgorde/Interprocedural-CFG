// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Component;
import javax.swing.JOptionPane;
import com.pchat.sc.StringUtil;
import com.pchat.sc.ServicePack;
import pclient.shd.Config;
import pclient.adv.AppletSpice;
import pclient.adv.ModInter;

public class ModManager implements ModInter
{
    protected AppletSpice paraApplet;
    private Config paraConf;
    private ModWindow modWindow;
    
    public void setPara(final AppletSpice appletSpice) {
        this.paraApplet = appletSpice;
        this.paraConf = appletSpice.paraConf;
        (this.modWindow = new ModWindow(this)).setPara(appletSpice);
    }
    
    public void startUp() {
        this.modWindow.startUp();
    }
    
    public void clearAll() {
        this.modWindow.setVisible(false);
        this.modWindow.dispose();
    }
    
    public void serverMsg(final ServicePack servicePack) {
        this.paraApplet.paraConf.printer().print("mod service=" + servicePack);
        if (servicePack.category != 400) {
            System.out.println("Error: #582.");
            return;
        }
        String pvalue = null;
        if (servicePack.paramCount > 0) {
            pvalue = servicePack.paramList[0].pvalue;
        }
        switch (servicePack.command) {
            case 800: {
                if (pvalue != null) {
                    this.displayError(pvalue);
                    break;
                }
                break;
            }
            case 802: {
                if (pvalue != null) {
                    this.modWindow.printInfo(pvalue);
                    break;
                }
                break;
            }
            case 804: {
                if (pvalue != null) {
                    this.modWindow.countLabel.setText(pvalue);
                    break;
                }
                break;
            }
            case 806: {
                if (servicePack.paramCount <= 0) {
                    break;
                }
                final String value = servicePack.getValue((short)20012);
                final String value2 = servicePack.getValue((short)20004);
                final String value3 = servicePack.getValue((short)20002);
                if (!this.modWindow.modIgnore.isIgnored(value2)) {
                    this.modWindow.addItem(value, value2, value3);
                    break;
                }
                break;
            }
            case 808: {
                if (pvalue != null) {
                    this.modWindow.modMsg.populateList(StringUtil.splitString(pvalue, "\n", false));
                    break;
                }
                break;
            }
            default: {
                System.out.println("Err482. unknown=." + servicePack.command);
                break;
            }
        }
    }
    
    private void displayError(final String s) {
        JOptionPane.showMessageDialog(this.modWindow, s);
    }
}
