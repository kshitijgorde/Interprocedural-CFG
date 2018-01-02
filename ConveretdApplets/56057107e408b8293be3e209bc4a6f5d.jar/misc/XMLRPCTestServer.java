// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import anon.AnonService;
import anon.terms.TermsAndConditionConfirmation;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import anon.infoservice.MixCascade;
import anon.xmlrpc.server.AnonServiceImplRemote;
import anon.AnonServiceFactory;

public class XMLRPCTestServer
{
    public static void main(final String[] array) {
        try {
            final AnonService anonServiceInstance = AnonServiceFactory.getAnonServiceInstance("AN.ON");
            final AnonServiceImplRemote anonServiceImplRemote = new AnonServiceImplRemote(anonServiceInstance);
            anonServiceInstance.initialize(new MixCascade(null, null, "mix.inf.tu-dresden.de", 6544), null, null);
            anonServiceImplRemote.startService();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
