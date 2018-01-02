// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.address;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class Endpoint
{
    protected String m_transportIdentifier;
    protected Hashtable m_paramters;
    
    public String getTransportIdentifier() {
        return this.m_transportIdentifier;
    }
    
    public static String toURN(final IAddress address) {
        final String transportIdentifier = address.getTransportIdentifier();
        final StringBuffer sb = new StringBuffer();
        sb.append("urn:endpoint:");
        sb.append(transportIdentifier);
        final AddressParameter[] allParameters = address.getAllParameters();
        for (int i = 0; i < allParameters.length; ++i) {
            sb.append(":");
            sb.append(allParameters[i].getName());
            sb.append("(");
            sb.append(allParameters[i].getValue());
            sb.append(")");
        }
        return sb.toString();
    }
    
    public Endpoint(final String s) throws MalformedURNException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreElements()) {
            array[n++] = stringTokenizer.nextToken();
        }
        if (array.length < 3) {
            throw new MalformedURNException("A valid Endpoint needs at least 3 Components");
        }
        if (!array[0].equals("urn")) {
            throw new MalformedURNException("URN must start with \"urn:\"");
        }
        if (!array[1].equals("endpoint")) {
            throw new MalformedURNException("Can only handle Endpoint-Namespace. Is " + array[1]);
        }
        this.m_transportIdentifier = array[2];
        this.m_paramters = new Hashtable();
        for (int length = array.length, i = 3; i < length; ++i) {
            int index = array[i].indexOf("(");
            final int n2 = array[i].length() - 1;
            final String substring = array[i].substring(0, index);
            ++index;
            this.m_paramters.put(substring, new AddressParameter(substring, array[i].substring(index, n2)));
        }
    }
    
    public String getParameter(final String s) {
        final AddressParameter addressParameter = this.m_paramters.get(s);
        return (addressParameter == null) ? null : addressParameter.getValue();
    }
    
    public AddressParameter[] getAllParameters() {
        final AddressParameter[] array = new AddressParameter[this.m_paramters.size()];
        final Enumeration<AddressParameter> elements = this.m_paramters.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            array[n++] = elements.nextElement();
        }
        return array;
    }
}
