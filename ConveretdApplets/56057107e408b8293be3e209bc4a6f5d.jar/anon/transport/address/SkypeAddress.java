// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.address;

public class SkypeAddress implements IAddress
{
    public static final String TRANSPORT_IDENTIFIER = "skype";
    private static final String USER_PARAMETER = "user";
    private static final String APP_PARAMETER = "application";
    protected String m_user;
    protected String m_app;
    
    public SkypeAddress(final String user, final String app) {
        this.m_user = user;
        this.m_app = app;
    }
    
    public SkypeAddress(final Endpoint endpoint) throws AddressMappingException {
        this.m_user = endpoint.getParameter("user");
        if (this.m_user == null) {
            throw new AddressMappingException("User-ID Parameter is missing");
        }
        this.m_app = endpoint.getParameter("application");
        if (this.m_app == null) {
            throw new AddressMappingException("Applicationname Parameter is missing");
        }
    }
    
    public String getUserID() {
        return this.m_user;
    }
    
    public String getApplicationName() {
        return this.m_app;
    }
    
    public String getTransportIdentifier() {
        return "skype";
    }
    
    public AddressParameter[] getAllParameters() {
        return new AddressParameter[] { new AddressParameter("user", this.m_user), new AddressParameter("application", this.m_app) };
    }
}
