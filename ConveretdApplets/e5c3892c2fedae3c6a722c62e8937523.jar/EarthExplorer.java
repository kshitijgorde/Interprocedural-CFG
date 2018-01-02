import java.util.Iterator;
import java.util.HashMap;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class EarthExplorer
{
    private static final String getBaseUrl() {
        return "http://earthexplorer.usgs.gov" + "/order/process?node=GV";
    }
    
    public static String buildShoppingCartUrl(final String s, final Vector vector) {
        if (vector.size() == 0) {
            return null;
        }
        String s2 = getBaseUrl() + "&dataset_name=" + s + "&ordered=" + vector.get(0);
        for (int i = 1; i < vector.size(); ++i) {
            s2 = s2 + "," + vector.get(i);
        }
        return s2;
    }
    
    public static String buildShoppingCartMultiDatasetUrl(final HashMap hashMap) {
        if (hashMap.isEmpty()) {
            return null;
        }
        final String baseUrl = getBaseUrl();
        final String s = new String();
        final Iterator<String> iterator = hashMap.keySet().iterator();
        String s2 = baseUrl + "&ordered={";
        while (iterator.hasNext()) {
            final String s3 = iterator.next();
            final Vector vector = (Vector)hashMap.get(s3);
            if (vector.size() > 0) {
                String s4 = s2 + "\"" + s3 + "\":[\"" + vector.get(0) + "\"";
                for (int i = 1; i < vector.size(); ++i) {
                    s4 = s4 + ",\"" + vector.get(i) + "\"";
                }
                s2 = s4 + "]";
                if (!iterator.hasNext()) {
                    continue;
                }
                s2 += ",";
            }
        }
        return s2 + "}&dataset_name=&type=json";
    }
}
