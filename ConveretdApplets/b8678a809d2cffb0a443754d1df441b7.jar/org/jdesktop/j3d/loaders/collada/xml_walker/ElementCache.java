// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import org.collada.colladaschema.Asset;
import java.net.URL;
import org.collada.colladaschema.CommonNewparamType;
import java.util.HashMap;

public class ElementCache
{
    private HashMap<String, SourceProcessor> sourceMap;
    private HashMap<String, VerticesProcessor> verticesMap;
    private HashMap<String, float[]> floatArrayMap;
    private HashMap<String, LightProcessor> lightMap;
    private HashMap<String, String> materialMap;
    private HashMap<String, String> imageMap;
    private HashMap<String, CommonNewparamType> newparamMap;
    private HashMap<String, Processor> map;
    private static ElementCache elementCache;
    private URL loadingURL;
    private Asset asset;
    
    static {
        ElementCache.elementCache = new ElementCache();
    }
    
    private ElementCache() {
        this.sourceMap = new HashMap<String, SourceProcessor>();
        this.verticesMap = new HashMap<String, VerticesProcessor>();
        this.floatArrayMap = new HashMap<String, float[]>();
        this.lightMap = new HashMap<String, LightProcessor>();
        this.materialMap = new HashMap<String, String>();
        this.imageMap = new HashMap<String, String>();
        this.newparamMap = new HashMap<String, CommonNewparamType>();
        this.map = new HashMap<String, Processor>();
        this.loadingURL = null;
    }
    
    public static ElementCache cache() {
        return ElementCache.elementCache;
    }
    
    public void putSource(final String id, final SourceProcessor element) {
        System.out.println("---> adding source " + id);
        this.sourceMap.put(id, element);
    }
    
    public SourceProcessor getSource(final String id) {
        return this.sourceMap.get(this.trim(id));
    }
    
    public void putVertices(final String id, final VerticesProcessor element) {
        this.verticesMap.put(id, element);
    }
    
    public VerticesProcessor getVertices(final String id) {
        return this.verticesMap.get(this.trim(id));
    }
    
    public void putFloatArray(final String id, final float[] floatArray) {
        assert id != null;
        this.floatArrayMap.put(id, floatArray);
    }
    
    public float[] getFloatArray(final String id) {
        return this.floatArrayMap.get(this.trim(id));
    }
    
    public void putLight(final String id, final LightProcessor light) {
        this.lightMap.put(id, light);
    }
    
    public LightProcessor getLight(final String id) {
        return this.lightMap.get(this.trim(id));
    }
    
    public void putMaterial(final String symbol, final String target) {
        this.materialMap.put(symbol, target);
    }
    
    public String getMaterial(final String symbol) {
        return this.materialMap.get(symbol);
    }
    
    public void putImage(final String symbol, final String target) {
        this.imageMap.put(symbol, target);
    }
    
    public String getImage(final String symbol) {
        return this.imageMap.get(symbol);
    }
    
    public void putNewParam(final String symbol, final CommonNewparamType target) {
        this.newparamMap.put(symbol, target);
    }
    
    public CommonNewparamType getNewParam(final String symbol) {
        return this.newparamMap.get(symbol);
    }
    
    public void put(final String id, final Processor proc) {
        this.map.put(id, proc);
    }
    
    public Processor get(final String id) {
        return this.map.get(this.trim(id));
    }
    
    private String trim(final String id) {
        if (id == null) {
            return "";
        }
        return id.substring(1, id.length());
    }
    
    public URL getLoadingURL() {
        return this.loadingURL;
    }
    
    public void setLoadingURL(final URL loadingURL) {
        this.loadingURL = loadingURL;
    }
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset asset) {
        this.asset = asset;
    }
}
