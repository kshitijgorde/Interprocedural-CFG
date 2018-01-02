// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsort;

import com.stonewall.cornerstone.entity.util.PolicyRuleSorter;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import com.stonewall.cornerstone.entity.policy.security.FilterRule;
import com.stonewall.cornerstone.entity.policy.security.FilterAction;
import com.stonewall.cornerstone.entity.policy.security.RuleSet;
import java.util.Comparator;
import junit.framework.TestCase;

public class Test extends TestCase
{
    String[] strings;
    
    public Test() {
        this.strings = new String[] { "apple", "ab", "ac", "fruit", "angie", "jeff", "joe", "cow patty", "cx", "book", "aa" };
    }
    
    public void test() {
        final SortedCollection<String> list = new SortedCollection<String>();
        final StringComparator c = new StringComparator();
        list.setComparator(c);
        String[] strings;
        for (int length = (strings = this.strings).length, i = 0; i < length; ++i) {
            final String s = strings[i];
            list.add(s);
        }
        System.out.println(list);
        list.sort();
        System.out.println(list);
    }
    
    public void testPolicySort() {
        final RuleSet set = new RuleSet();
        set.add(new FilterRule("filter1", 0, FilterAction.Access.permit));
        set.add(new FilterRule("filter2", 0, FilterAction.Access.permit));
        set.add(new FilterRule("filter3", 0, FilterAction.Access.permit));
        final PolicyRuleSorter sorter = new PolicyRuleSorter();
        sorter.sort(set);
        System.out.println("Filter1, Filter2, Filter3:" + set);
    }
    
    class StringComparator implements Comparator<String>
    {
        @Override
        public int compare(final String a, final String b) {
            return a.compareTo(b);
        }
    }
}
