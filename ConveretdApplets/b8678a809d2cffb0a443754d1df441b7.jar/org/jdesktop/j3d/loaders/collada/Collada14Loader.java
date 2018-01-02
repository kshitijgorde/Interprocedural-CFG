// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada;

import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Asset;
import com.sun.j3d.loaders.SceneBase;
import javax.media.j3d.Node;
import org.jdesktop.j3d.loaders.collada.xml_walker.Processor;
import org.jdesktop.j3d.loaders.collada.xml_walker.ProcessorFactory;
import javax.media.j3d.BranchGroup;
import java.io.Reader;
import org.jdesktop.j3d.loaders.collada.xml_walker.ElementCache;
import java.net.URL;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import java.io.FileNotFoundException;
import javax.xml.bind.Unmarshaller;
import java.net.URISyntaxException;
import javax.xml.bind.JAXBException;
import java.util.logging.Level;
import java.io.File;
import java.net.URI;
import org.collada.colladaschema.COLLADA;
import javax.xml.bind.JAXBContext;
import com.sun.j3d.loaders.Scene;
import java.util.logging.Logger;
import com.sun.j3d.loaders.LoaderBase;

public class Collada14Loader extends LoaderBase
{
    private static final Logger logger;
    
    static {
        logger = Logger.getLogger("collada.processor");
    }
    
    public Scene load(final String filename) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        try {
            final JAXBContext jc = JAXBContext.newInstance("org.collada.colladaschema");
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            final COLLADA collada = (COLLADA)unmarshaller.unmarshal(new File(new URI(this.getBaseUrl() + filename)));
            return this.doLoad(collada);
        }
        catch (JAXBException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        catch (URISyntaxException ez) {
            Logger.getLogger("global").log(Level.SEVERE, null, ez);
        }
        return null;
    }
    
    public Scene load(final URL url) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        try {
            ElementCache.cache().setLoadingURL(url);
            final JAXBContext jc = JAXBContext.newInstance("org.collada.colladaschema");
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            final COLLADA collada = (COLLADA)unmarshaller.unmarshal(url);
            return this.doLoad(collada);
        }
        catch (JAXBException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Scene load(final Reader arg0) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Scene doLoad(final COLLADA collada) {
        System.out.println("Base " + collada.getBase());
        System.out.println("Version " + collada.getVersion());
        final Asset asset = collada.getAsset();
        final Asset.Unit unit = asset.getUnit();
        if (unit != null) {
            System.out.println("Units " + unit.getMeter() + "  " + unit.getName());
        }
        ElementCache.cache().setAsset(asset);
        final List<Object> lib = collada.getLibraryLightsAndLibraryGeometriesAndLibraryAnimationClips();
        final BranchGroup root = new BranchGroup();
        Processor lgtp = null;
        for (final Object o : lib) {
            ProcessorFactory.createProcessor(o, null);
        }
        lgtp = ProcessorFactory.createProcessor(collada.getScene(), null);
        lgtp.create((Node)root);
        final SceneBase ret = new SceneBase();
        ret.setSceneGroup(root);
        return (Scene)ret;
    }
}
