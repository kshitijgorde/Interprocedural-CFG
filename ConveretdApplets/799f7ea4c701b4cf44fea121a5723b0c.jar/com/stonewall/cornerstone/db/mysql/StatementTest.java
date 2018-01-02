// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.stonewall.cornerstone.db.DbSession;
import org.xmodel.IModelObject;
import java.util.Date;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.db.DbSessionFactory;
import com.stonewall.cornerstone.entity.policy.compliance.CompliancePolicy;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class StatementTest extends TestCase
{
    static final String update = "update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ";
    public static String complianceRule;
    
    static {
        StatementTest.complianceRule = "<en:compliancePolicy id=\"-1\" xmlns:en=\"http://www.stonewallnetworks.com/ns/entity\"><en:ruleSet><en:complianceRule id=\"-1\" >   <en:name>Phase1/Cipher content</en:name>  <en:element>let $id := ./en:action/en:ike/en:phase1/en:p1Proposals/en:p1Proposal/@id; $references/en:p1Proposals/en:p1Proposal[@id=$id]/en:cipher</en:element>   <en:input>     <en:entry>3DES</en:entry>     <en:entry>DES</en:entry>   </en:input>   <en:test>text() = $input/en:entry</en:test>   <en:violation>let $targetPath := create-path(../.., .); printf('%s : %s must be set to one of %s', $targetDesc, $targetPath, $input/entry )</en:violation> </en:complianceRule></en:ruleSet></en:compliancePolicy>";
    }
    
    public void test() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final ModelBuilder builder = new ModelBuilder();
            final IModelObject root = builder.buildModel(StatementTest.complianceRule);
            final CompliancePolicy policy = new CompliancePolicy(root);
            final DbSession session = DbSessionFactory.getConnection();
            session.setDatabase(Label.latest.getDatabase());
            try {
                final DbStatement stmt = session.createStatement("update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ");
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", policy.getId());
                stmt.setString("content", policy.getRoot());
                stmt.set("timestamp", new Date().getTime());
                System.out.println(stmt.toString());
            }
            finally {
                session.close();
            }
            session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testRegex() {
        final Pattern p = Pattern.compile("[$]\\bid\\b");
        final Matcher m = p.matcher("select $id from $table id where $identifier equals fromblah");
        while (m.find()) {
            final MatchResult result = m.toMatchResult();
            System.out.println(m.start());
            System.out.println(m.end());
            System.out.println(result);
        }
    }
}
