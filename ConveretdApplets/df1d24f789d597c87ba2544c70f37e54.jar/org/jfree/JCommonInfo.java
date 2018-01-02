// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree;

import org.jfree.base.Library;
import java.util.List;
import java.util.Arrays;
import org.jfree.ui.about.Contributor;
import org.jfree.ui.about.Licences;
import java.util.ResourceBundle;
import org.jfree.ui.about.ProjectInfo;

public class JCommonInfo extends ProjectInfo
{
    private static JCommonInfo singleton;
    static /* synthetic */ Class class$org$jfree$base$BaseBoot;
    
    public static JCommonInfo getInstance() {
        if (JCommonInfo.singleton == null) {
            JCommonInfo.singleton = new JCommonInfo();
        }
        return JCommonInfo.singleton;
    }
    
    private JCommonInfo() {
        final ResourceBundle bundle = ResourceBundle.getBundle("org.jfree.resources.JCommonResources");
        this.setName(bundle.getString("project.name"));
        this.setVersion(bundle.getString("project.version"));
        this.setInfo(bundle.getString("project.info"));
        this.setCopyright(bundle.getString("project.copyright"));
        this.setLicenceName("LGPL");
        this.setLicenceText(Licences.getInstance().getLGPL());
        this.setContributors(Arrays.asList(new Contributor("Anthony Boulestreau", "-"), new Contributor("Jeremy Bowman", "-"), new Contributor("J. David Eisenberg", "-"), new Contributor("Paul English", "-"), new Contributor("David Gilbert", "david.gilbert@object-refinery.com"), new Contributor("Hans-Jurgen Greiner", "-"), new Contributor("Achilleus Mantzios", "-"), new Contributor("Thomas Meier", "-"), new Contributor("Aaron Metzger", "-"), new Contributor("Thomas Morgner", "-"), new Contributor("Krzysztof Paz", "-"), new Contributor("Nabuo Tamemasa", "-"), new Contributor("Mark Watson", "-"), new Contributor("Matthew Wright", "-"), new Contributor("Hari", "-"), new Contributor("Sam (oldman)", "-")));
        this.addLibrary(new Library("JUnit", "3.8", "IBM Public Licence", "http://www.junit.org/"));
        this.setBootClass(((JCommonInfo.class$org$jfree$base$BaseBoot == null) ? (JCommonInfo.class$org$jfree$base$BaseBoot = class$("org.jfree.base.BaseBoot")) : JCommonInfo.class$org$jfree$base$BaseBoot).getName());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
