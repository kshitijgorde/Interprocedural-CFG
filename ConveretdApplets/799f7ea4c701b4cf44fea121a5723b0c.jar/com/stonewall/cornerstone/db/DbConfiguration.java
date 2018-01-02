// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import com.stonewall.cornerstone.entity.Tag;
import com.stonewall.cornerstone.entity.db.TagDbAccess;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.IPService;
import com.stonewall.cornerstone.entity.db.UserDbAccess;
import com.stonewall.cornerstone.entity.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.stonewall.cornerstone.entity.Role;
import com.stonewall.cornerstone.security.Permission;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.StringTokenizer;
import com.stonewall.cornerstone.security.FeatureAccess;
import com.stonewall.cornerstone.entity.db.RoleDbAccess;
import com.stonewall.cornerstone.entity.policy.security.AdminPolicy;
import com.stonewall.cornerstone.entity.policy.security.SitePolicy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.entity.Site;
import com.stonewall.cornerstone.entity.db.QueueDbAccess;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.PolicyServer;
import com.stonewall.cornerstone.utility.Server;
import com.stonewall.cornerstone.entity.db.PolicyServerDbAccess;
import com.stonewall.cornerstone.entity.Network;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import com.stonewall.cornerstone.db.mysql.Configuration;
import org.xmodel.log.Log;

public class DbConfiguration
{
    static final Log log;
    
    static {
        log = Log.getLog(DbConfiguration.class);
    }
    
    public static void load() throws Exception {
        try {
            validateDatabase();
            validateNetworks();
            validatePolicyServer();
            validateQueues();
            validateSite();
            validateRoles();
            validateAdminUser();
            validateServices();
            validateTags();
        }
        catch (Exception e) {
            DbConfiguration.log.error("Database Configuration", e);
            throw e;
        }
    }
    
    public static void makeSnapshot(final String src, final String dst) throws Exception {
        new Configuration().makeSnapshot(src, dst);
    }
    
    public static void reloadLatest(final String src) throws Exception {
        final String name = DbSession.DB.latest.name();
        final Configuration cfg = new Configuration();
        dropDatabase(name);
        cfg.validate(name);
        cfg.copyData(src, name);
    }
    
    public static void createDatabase(final String name) throws Exception {
        final DbSession session = DbSessionFactory.getConnection();
        try {
            session.createDatabase(name);
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public static void createSandbox(final String name) throws Exception {
        final DbSession session = DbSessionFactory.getConnection();
        try {
            session.createDatabase(name);
            session.setDatabase(name);
            new Configuration().createTables(session, name);
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public static void dropDatabase(final String name) throws Exception {
        final DbSession session = DbSessionFactory.getConnection();
        try {
            session.dropDatabase(name);
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    static void validateDatabase() throws Exception {
        new Configuration().validate();
    }
    
    static void validateNetworks() throws DbException {
        if (new NetworkDbAccess().fetchById("INTERNET") == null) {
            final Network internet = new Network("INTERNET");
            internet.setName("Internet");
            internet.insert();
        }
    }
    
    static void validatePolicyServer() throws Exception {
        if (new PolicyServerDbAccess().fetchAll().size() == 0) {
            final Server host = new Server();
            final String name = System.getProperty("cornerstone.server.id");
            final PolicyServer ps = new PolicyServer();
            ps.setName(name);
            final IPInterface intf = ps.getInterface(Server.IntRole.management);
            intf.setIpAddress(new IpAddr(host.getAddress(Server.IntRole.management)));
            ps.insert();
        }
    }
    
    static void validateQueues() throws Exception {
        if (new QueueDbAccess().fetchCount(QueueDbAccess.Pool.DM) == 0) {
            new QueueDbAccess().insert(QueueDbAccess.Pool.DM, 20);
        }
        if (new QueueDbAccess().fetchCount(QueueDbAccess.Pool.CP) == 0) {
            new QueueDbAccess().insert(QueueDbAccess.Pool.CP, 20);
        }
    }
    
    static void validateSite() throws Exception {
        final Site site = new Site("SITE0");
        site.setName("Corporate");
        if (site.fetch() == null) {
            site.insert();
            SitePolicy policy = new SitePolicy(DeployablePolicy.Phase.working);
            policy.setId("WPOLICY0");
            policy.setParentId(site.getId());
            policy.insert();
            policy = new SitePolicy(DeployablePolicy.Phase.net);
            policy.setId("NPOLICY0");
            policy.setParentId(site.getId());
            policy.insert();
            final AdminPolicy apolicy = new AdminPolicy();
            apolicy.setId("APOLICY0");
            apolicy.setParentId(site.getId());
            apolicy.insert();
            policy = new SitePolicy(DeployablePolicy.Phase.deployed);
            policy.setParentId(site.getId());
            policy.insert();
        }
    }
    
    static void validateRoles() throws Exception {
        List<Permission> permissions = new RoleDbAccess().fetchAllPermissions();
        if (permissions.isEmpty()) {
            FeatureAccess.Type[] values;
            for (int length = (values = FeatureAccess.Type.values()).length, i = 0; i < length; ++i) {
                final FeatureAccess.Type t = values[i];
                try {
                    final FeatureAccess fa = t.getFeatureAccess();
                    final StringTokenizer st = new StringTokenizer(t.getValue(), ".");
                    final String token = st.nextToken();
                    String value = "*";
                    if (token.contains(EntityFactory.EntityType.site.name())) {
                        value = "SITE0";
                    }
                    new RoleDbAccess().insertPermission(new Permission(fa, value));
                }
                catch (DbException ex) {}
            }
        }
        final Role admin = new Role(Role.Name.admin);
        final Role operator = new Role(Role.Name.operator);
        admin.setDescription("A system administrator.");
        operator.setDescription("A basic operator.");
        permissions = new RoleDbAccess().fetchAllPermissions();
        if (admin.fetch() == null) {
            admin.addPermissions(permissions);
            admin.insert();
        }
        if (operator.fetch() == null) {
            for (final Permission p : permissions) {
                final FeatureAccess.Type t2 = p.getType();
                if (!t2.equals(FeatureAccess.Type.userDelete) && !t2.equals(FeatureAccess.Type.userInsert) && !t2.equals(FeatureAccess.Type.userView) && !t2.equals(FeatureAccess.Type.roleDelete) && !t2.equals(FeatureAccess.Type.roleInsert) && !t2.equals(FeatureAccess.Type.roleUpdate) && !t2.equals(FeatureAccess.Type.roleView)) {
                    operator.addPermission(p);
                }
            }
            operator.insert();
        }
    }
    
    static void validateAdminUser() throws DbException {
        final List<String> roles = new ArrayList<String>();
        final User admin = new User("admin");
        roles.add(Role.Name.admin.name());
        admin.setPassword("admin");
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setEmail("support@stonewallnetworks.com");
        admin.setPhone("919.678.6125");
        admin.setRoles(roles);
        if (new UserDbAccess().fetchById(admin.getId()) == null) {
            new UserDbAccess().insert(admin);
        }
    }
    
    static void validateServices() throws Exception {
        final EntityReference service = IPService.AllRef;
        if (service.getReferent() == null) {
            final IModelObject root = Predefined.load(EntityFactory.EntityType.ipService);
            final List<IModelObject> services = root.getChildren("en:ipService");
            for (final IModelObject s : services) {
                final IPService srv = new IPService(s);
                srv.insert();
            }
        }
    }
    
    static void validateTags() throws Exception {
        if (new TagDbAccess().fetchById("TAG1") == null) {
            final IModelObject root = Predefined.load(EntityFactory.EntityType.tag);
            final List<IModelObject> tags = root.getChildren("en:tag");
            for (final IModelObject t : tags) {
                final Tag tag = new Tag(t);
                tag.insert();
            }
        }
    }
}
