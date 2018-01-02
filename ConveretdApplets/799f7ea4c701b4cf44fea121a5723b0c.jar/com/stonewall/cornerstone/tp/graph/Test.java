// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import com.stonewall.cornerstone.entity.util.InternetAddress;
import com.stonewall.cornerstone.tp.query.NetQuery;
import com.stonewall.cornerstone.tp.query.PathQuery;
import java.util.Collection;
import com.stonewall.cornerstone.tp.query.TunnelQuery;
import com.stonewall.cornerstone.tp.query.Item;
import com.stonewall.cornerstone.tp.query.NodeQuery;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;
import junit.framework.TestCase;

public class Test extends TestCase
{
    public void testAll() {
        try {
            Graph.log.setLevel(32);
            final Graph graph = this.init();
            this.getItemsBetween2(graph);
            this.nat(graph);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nat(final Graph g) throws Exception {
        final NatPolicy nat = new NatPolicy(new Node("nat-test"));
        final ObjectGroup poolA = new ObjectGroup("poolA");
        poolA.add(new IpAddr("66.1.1.1"));
        poolA.add(new IpAddr("66.1.1.2"));
        poolA.add(new IpAddr("66.1.1.3"));
        poolA.add(new IpAddr("66.1.1.4"));
        final NatAction aA = new NatAction(poolA);
        final ObjectGroup poolB = new ObjectGroup("poolB");
        poolB.add(new IpAddr("67.1.1.1"));
        poolB.add(new IpAddr("67.1.1.2"));
        poolB.add(new IpAddr("67.1.1.3"));
        poolB.add(new IpAddr("67.1.1.4"));
        final NatAction aB = new NatAction(poolB);
        nat.add(new NatRule("nat-1", new NatSelector("a", "b", new InetAddress("10.1.1.1/32")), new NatAction(new IpAddr("66.1.2.3/32"))));
        nat.add(new NatRule("nat-2", new NatSelector("a", "b", new InetAddress("10.1.1.2/32")), new NatAction(new ObjectGroup(new IpAddr("66.1.2.44/32")))));
        nat.add(new NatRule("nat-3", new NatSelector("a", "b", new InetAddress("10.1.1.3/32")), aA));
        nat.add(new NatRule("nat-1", new NatSelector("a", "b", Any.any, new InetAddress("192.168.0.0/16")), aA, aB));
        System.out.println(nat);
        Packet p = new Packet(new IpAddr("10.1.1.1/32"), new IpAddr("23.1.4.1/32"));
        System.out.println("xlate: " + p);
        for (final Packet tp : nat.xlate("a", "b", p)) {
            System.out.println("\txlated: " + tp);
        }
        System.out.println("--------------------------------------------");
        p = new Packet(new IpAddr("10.1.1.3/32"), new IpAddr("34.19.7.54/32"));
        System.out.println("xlate: " + p);
        for (final Packet tp : nat.xlate("a", "b", p)) {
            System.out.println("\txlated: " + tp);
        }
        System.out.println("--------------------------------------------");
        p = new Packet(new IpAddr("192.168.1.100/32"), new IpAddr("33.1.0.0/16"));
        System.out.println("xlate: " + p);
        for (final Packet tp : nat.xlate("a", "b", p)) {
            System.out.println("\txlated: " + tp);
        }
        System.out.println("--------------------------------------------");
        p = new Packet(new IpAddr("192.168.1.100/32"), new IpAddr("192.168.1.101/32"));
        System.out.println("xlate: " + p);
        for (final Packet tp : nat.xlate("a", "b", p)) {
            System.out.println("\txlated: " + tp);
        }
        System.out.println("--------------------------------------------");
    }
    
    public void _testReentrant() throws Exception {
        Graph.log.setLevel(32);
        final Graph graph = this.init();
        for (int i = 0; i < 100; ++i) {
            final Thread t = new Thread("test-" + i) {
                @Override
                public void run() {
                    System.out.println(this);
                    Test.this.getItemsBetween(graph);
                }
            };
            t.start();
        }
        Thread.sleep(5000L);
    }
    
    void containsQuery(final Graph graph) throws Exception {
        final GraphQuery q = new GraphQuery(graph);
        String a = "net-2";
        String b = "h-1";
        System.out.println("endpointContains(): " + a + "/" + b);
        System.out.println(q.endpointContains(a, b));
        System.out.println("--------------------------------------------");
        a = "net-2a";
        b = "h-2a";
        System.out.println("endpointContains(): " + a + "/" + b);
        System.out.println(q.endpointContains(a, b));
        System.out.println("--------------------------------------------");
        IpAddr addr = new IpAddr("10.2.2.0/24");
        System.out.println("findByAddress(): " + addr);
        System.out.println(graph.inspector().findByAddress(addr));
        System.out.println("--------------------------------------------");
        final Host h = graph.getHost("h-1");
        addr = new IpAddr("10.1.1.200");
        h.setAddress(addr);
        System.out.println("findByAddress(): " + addr);
        System.out.println(graph.inspector().findByAddress(addr));
        System.out.println("--------------------------------------------");
    }
    
    void nodeQuery(final Graph graph) throws Exception {
        final NodeQuery dq = new NodeQuery(graph);
        final String nid = "net-4";
        System.out.println("getDirectlyConnected(net-1): " + nid);
        System.out.println(dq.getDirectlyConnected(nid));
        System.out.println("--------------------------------------------");
        final String eA = "net-4";
        final String eB = "net-8";
        System.out.println("findBetween(): " + eA + "/" + eB);
        System.out.println(dq.getNodesBetween(eA, eB));
        System.out.println("--------------------------------------------");
        final String nA = "10.1.6.33";
        final String nB = "10.1.5.66";
        System.out.println("findBetween(): " + nA + "/" + nB);
        System.out.println(dq.getNodesBetween(nA, nB));
        System.out.println("--------------------------------------------");
    }
    
    void nodeQuery2(final Graph graph) throws Exception {
        final NodeQuery dq = new NodeQuery(graph);
        String nA = "10.1.6.33";
        String nB = "10.1.5.66";
        System.out.println("findBetween(): " + nA + "/" + nB);
        System.out.println(dq.getNodesBetween(nA, nB));
        System.out.println("--------------------------------------------");
        nA = "10.1.4.33";
        nB = "23.33.22.11";
        System.out.println("findBetween(): " + nA + "/" + nB);
        System.out.println(dq.getNodesBetween(nA, nB));
        System.out.println("--------------------------------------------");
    }
    
    void getItemsBetween(final Graph graph) {
        final NodeQuery dq = new NodeQuery(graph);
        String eA = "n-1.4";
        String eB = "n-7.1";
        System.out.println(Thread.currentThread() + ": getItemsBetween(): " + eA + "/" + eB);
        for (final Item item : dq.getItemsBetween(eA, eB)) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
        eA = "n-1.4";
        eB = "n-7.4";
        System.out.println(Thread.currentThread() + "getItemsBetween(): " + eA + "/" + eB);
        for (final Item item : dq.getItemsBetween(eA, eB)) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
    }
    
    void getItemsBetween2(final Graph graph) {
        final NodeQuery dq = new NodeQuery(graph);
        String nA = "h-2.1";
        String nB = "h-8.1";
        System.out.println("getItemsBetween(): " + nA + "/" + nB);
        for (final Item item : dq.getItemsBetween(nA, nB)) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
        nA = "h-8.1";
        nB = "h-2.1";
        System.out.println("getItemsBetween2(): " + nA + "/" + nB);
        for (final Item item : dq.getItemsBetween(nA, nB)) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
        nA = "h-8.2";
        nB = "h-2.1";
        System.out.println("getItemsBetween2(): " + nA + "/" + nB);
        for (final Item item : dq.getItemsBetween(nA, nB)) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
    }
    
    void special(final Graph graph) {
        final NodeQuery dq = new NodeQuery(graph);
        final String nA = "net-2";
        final String nB = "net-5";
        System.out.println("getItemsBetween(basic): " + nA + "/" + nB);
        Collection<Item> result = dq.getItemsBetween(nA, nB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        System.out.println("--------------- ( paths ) ---------------");
        System.out.println(dq.paths().pathCollection());
        System.out.println("--------------------------------------------");
        System.out.println("-------------- ( tunnels ) --------------");
        final TunnelQuery tq = new TunnelQuery(graph);
        final Collection<TunnelQuery.Tunnel> tunnels = tq.enabledTunnelsBetween(nA, nB);
        for (final TunnelQuery.Tunnel t : tunnels) {
            System.out.println(t);
        }
        System.out.println("--------------------------------------------");
        System.out.println("getItemsBetween(secure): " + nA + "/" + nB);
        dq.setMode(GraphQuery.Mode.Secure);
        result = dq.getItemsBetween(nA, nB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        System.out.println("--------------- ( paths ) ---------------");
        System.out.println(dq.paths().pathCollection());
        System.out.println("--------------------------------------------");
        System.out.println("getClosestNode(): " + nA);
        final TunnelQuery tqr = new TunnelQuery(graph);
        System.out.println("result: " + tqr.getClosestNode("t-1", nA));
        System.out.println("--------------------------------------------");
        System.out.println("getClosestNode(): " + nB);
        System.out.println("result: " + tqr.getClosestNode("t-1", nA));
        System.out.println("--------------------------------------------");
        final String nC = "net-2";
        System.out.println("getClosestNode(): " + nC);
        System.out.println("result: " + tqr.getClosestNode("t-1", nA));
        System.out.println("--------------------------------------------");
    }
    
    void getPathsBetween(final Graph graph) {
        final PathQuery pq = new PathQuery(graph);
        String eA = "net-7";
        String eB = "net-5";
        System.out.println("getPathsBetween(): " + eA + "/" + eB);
        Collection<PathQuery.Path> result = pq.getPathsBetween(eA, eB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        eA = "net-5";
        eB = "net-7";
        System.out.println("getPathsBetween(): " + eA + "/" + eB);
        result = pq.getPathsBetween(eA, eB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        eA = "net-5";
        eB = "net-8";
        System.out.println("getPathsBetween(): " + eA + "/" + eB);
        result = pq.getPathsBetween(eA, eB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        eA = "net-2";
        eB = "net-8";
        System.out.println("getPathsBetween(): " + eA + "/" + eB);
        result = pq.getPathsBetween(eA, eB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
        eA = "net-6";
        eB = "net-6";
        System.out.println("getPathsBetween(): " + eA + "/" + eB);
        result = pq.getPathsBetween(eA, eB);
        System.out.println(result);
        System.out.println("--------------------------------------------");
    }
    
    void getDirectlyConnected(final Graph graph) throws Exception {
        final NodeQuery dq = new NodeQuery(graph);
        String nid = "n-4";
        String eA = "net-4";
        System.out.println("getIntfsDirectlyConnected(): " + nid + "/" + eA);
        for (final Interface intf : dq.getIntfsDirectlyConnected(nid, eA)) {
            System.out.println(intf);
        }
        System.out.println("--------------------------------------------");
        nid = "n-4";
        eA = "net-5";
        System.out.println("getIntfsDirectlyConnected(): " + nid + "/" + eA);
        for (final Interface intf : dq.getIntfsDirectlyConnected(nid, eA)) {
            System.out.println(intf);
        }
        System.out.println("--------------------------------------------");
        nid = "n-2";
        eA = "h-2.1";
        System.out.println("getIntfsDirectlyConnected(): " + nid + "/" + eA);
        for (final Interface intf : dq.getIntfsDirectlyConnected(nid, eA)) {
            System.out.println(intf);
        }
        System.out.println("--------------------------------------------");
    }
    
    void endpointsLinked(final Graph graph) throws Exception {
        final NodeQuery dq = new NodeQuery(graph);
        String eA = "n-4.3";
        final String eB = "n-2.1";
        System.out.println("endpointsLinked(): " + eA + "/" + eB);
        System.out.println(dq.endpointsLinked(eA, eB));
        System.out.println("--------------------------------------------");
        eA = "n-6.1";
        System.out.println("endpointsLinked(): " + eA + "/" + eB);
        System.out.println(dq.endpointsLinked(eA, eB));
        System.out.println("--------------------------------------------");
    }
    
    void getDirectNetworks(final Graph graph) {
        final NetQuery q = new NetQuery(graph);
        final String ifid = "n-1.1";
        System.out.println("Get directly connected networks on :" + ifid);
        System.out.println(q.getDirectlyLinked(ifid));
        System.out.println("--------------------------------------------");
    }
    
    void getEnabledTunnelsBetween(final Graph graph) throws Exception {
        final TunnelQuery q = new TunnelQuery(graph);
        final String a = "net-5";
        final String b = "net-3";
        System.out.println("tunnelsBetween(): " + a + "/" + b);
        System.out.println(q.enabledTunnelsBetween(a, b));
    }
    
    void getAllTunnelsBetween(final Graph graph) throws Exception {
        final TunnelQuery q = new TunnelQuery(graph);
        final String a = "net-6";
        final String b = "net-1";
        System.out.println("allTunnelsBetween(): " + a + "/" + b);
        System.out.println(q.allTunnelsBetween(a, b));
    }
    
    void getClosestNode(final Graph graph) throws Exception {
        final TunnelQuery q = new TunnelQuery(graph);
        final String a = "t-1";
        final String b = "net-5";
        System.out.println("getClosestNode(): " + a + "/" + b);
        System.out.println(q.getClosestNode(a, b));
    }
    
    Graph init() throws Exception {
        Graph.log.setLevel(32);
        BasicWalker.log.setLevel(32);
        NodeQuery.log.setLevel(32);
        NetQuery.log.setLevel(32);
        final Graph graph = new Graph();
        final InternetAddress internet = InternetAddress.getInstance();
        graph.addNetwork(new Network("internet", internet));
        this.init10(graph);
        this.init192(graph);
        graph.sortNetworks();
        graph.refreshNetworkAssociations();
        System.out.println(graph + "\n");
        return graph;
    }
    
    void init10(final Graph graph) throws Exception {
        Node n = new Node("n-1");
        n.addInterface(new Interface("n-1.1", new IpAddr("10.1.7.1/32")));
        n.addInterface(new Interface("n-1.2", new IpAddr("10.1.1.1/32")));
        n.addInterface(new Interface("n-1.3", (IpAddr)null));
        n.addInterface(new Interface("n-1.4", new IpAddr("10.2.1.1/32")));
        graph.addNode(n);
        n = new Node("n-2");
        n.addInterface(new Interface("n-2.1", new IpAddr("10.1.1.2/32")));
        n.addInterface(new Interface("n-2.2", (IpAddr)null));
        n.addInterface(new Interface("n-2.3", new IpAddr("10.1.2.1/32")));
        n.addInterface(new Interface("n-2.4", new IpAddr("10.1.3.1/32")));
        graph.addNode(n);
        n = new Node("n-3");
        n.addInterface(new Interface("n-3.1", new IpAddr("10.2.1.2/32")));
        n.addInterface(new Interface("n-3.2", new IpAddr("10.1.3.2/32")));
        n.addInterface(new Interface("n-3.3", (IpAddr)null));
        n.addInterface(new Interface("n-3.4", new IpAddr("10.1.4.1/32")));
        graph.addNode(n);
        n = new Node("n-4");
        n.addInterface(new Interface("n-4.1", (IpAddr)null));
        n.addInterface(new Interface("n-4.2", (IpAddr)null));
        n.addInterface(new Interface("n-4.3", new IpAddr("10.1.4.3/32")));
        n.addInterface(new Interface("n-4.4", new IpAddr("10.1.5.1/32")));
        graph.addNode(n);
        n = new Node("n-5");
        n.addInterface(new Interface("n-5.1", (IpAddr)null));
        n.addInterface(new Interface("n-5.2", new IpAddr("10.1.4.2/32")));
        n.addInterface(new Interface("n-5.3", new IpAddr("10.1.6.1/32")));
        n.addInterface(new Interface("n-5.4", (IpAddr)null));
        graph.addNode(n);
        n = new Node("n-6");
        n.addInterface(new Interface("n-6.1", new IpAddr("10.1.6.3/32")));
        n.addInterface(new Interface("n-6.2", new IpAddr("66.1.1.1/32")));
        n.addInterface(new Interface("n-6.3", new IpAddr("10.2.1.4/32")));
        n.addInterface(new Interface("n-6.4", new IpAddr("10.2.1.5/32")));
        graph.addNode(n);
        n = new Node("n-6A");
        n.addInterface(new Interface("n-6A.1", (IpAddr)null));
        n.addInterface(new Interface("n-6A.2", new IpAddr("10.1.6.2/32")));
        n.addInterface(new Interface("n-6A.3", (IpAddr)null));
        n.addInterface(new Interface("n-6A.4", new IpAddr("10.2.1.3/32")));
        graph.addNode(n);
        n = new Node("n-7");
        n.addInterface(new Interface("n-7.1", new IpAddr("66.1.1.2/32")));
        n.addInterface(new Interface("n-7.2", (IpAddr)null));
        n.addInterface(new Interface("n-7.3", new IpAddr("10.8.1.3/32")));
        n.addInterface(new Interface("n-7.4", new IpAddr("10.1.8.1/32")));
        graph.addNode(n);
        n = new Node("n-8");
        n.addInterface(new Interface("n-8.1", new IpAddr("66.2.1.2/32")));
        n.addInterface(new Interface("n-8.2", (IpAddr)null));
        n.addInterface(new Interface("n-8.3", (IpAddr)null));
        n.addInterface(new Interface("n-8.4", new IpAddr("10.1.12.1/32")));
        final Router r = n.router();
        r.add(new Route(new IpAddr("66.2.1.2"), new IpAddr("10.1.12.1/32"), n.getInterface("n-8.4")));
        graph.addNode(n);
        graph.addNetwork(new Network("net-1", new IpAddr("10.1.1.0/24")));
        graph.addNetwork(new Network("net-2", new IpAddr("10.1.2.0/24")));
        graph.addNetwork(new Network("net-3", new IpAddr("10.1.3.0/24")));
        graph.addNetwork(new Network("net-4", new IpAddr("10.1.4.0/24")));
        graph.addNetwork(new Network("net-5", new IpAddr("10.1.5.0/24")));
        graph.addNetwork(new Network("net-6", new IpAddr("10.1.6.0/24")));
        graph.addNetwork(new Network("net-7", new IpAddr("10.1.7.0/24")));
        graph.addNetwork(new Network("net-8", new IpAddr("10.1.8.0/24")));
        final Network net2 = graph.getNetwork("net-2");
        net2.addHost(new Host("h-2.1", new IpAddr("10.1.2.100/32"), net2));
        net2.addHost(new Host("h-2.2", new IpAddr("10.1.2.101/32"), net2));
        final Network net3 = graph.getNetwork("net-8");
        net3.addHost(new Host("h-8.1", new IpAddr("10.1.8.100/32"), net3));
        net3.addHost(new Host("h-8.2", new IpAddr("10.1.8.200/32"), net3));
        final Network n2a = new Network("net-2a", new IpAddr("10.2.2.0/24"));
        n2a.setParent(graph.getNetwork("net-2"));
        graph.addNetwork(n2a);
        n2a.addHost(new Host("h-2a.1", new IpAddr("10.2.2.100/32")));
        final Network net4 = graph.getNetwork("net-6");
        net4.addHost(new Host("h-6.1", new IpAddr("10.1.6.100/32"), net4));
        graph.addLink(new Link("l-1", graph.getInterface("n-1.2"), graph.getNetwork("net-1")));
        graph.addLink(new Link("l-2", graph.getInterface("n-2.1"), graph.getNetwork("net-1")));
        graph.addLink(new Link("l-3", graph.getInterface("n-2.3"), graph.getNetwork("net-2")));
        graph.addLink(new Link("l-4", graph.getInterface("n-2.4"), graph.getNetwork("net-3")));
        graph.addLink(new Link("l-5", graph.getInterface("n-3.2"), graph.getNetwork("net-3")));
        graph.addLink(new Link("l-6", graph.getInterface("n-3.4"), graph.getNetwork("net-4")));
        graph.addLink(new Link("l-7", graph.getInterface("n-3.1"), graph.getInterface("n-1.4")));
        graph.addLink(new Link("l-8", graph.getInterface("n-4.3"), graph.getNetwork("net-4")));
        graph.addLink(new Link("l-9", graph.getInterface("n-4.4"), graph.getNetwork("net-5")));
        graph.addLink(new Link("l-10", graph.getInterface("n-5.2"), graph.getNetwork("net-4")));
        graph.addLink(new Link("l-11", graph.getInterface("n-5.3"), graph.getNetwork("net-6")));
        graph.addLink(new Link("l-12", graph.getInterface("n-1.1"), graph.getNetwork("net-7")));
        graph.addLink(new Link("l-13", graph.getInterface("n-6.1"), graph.getNetwork("net-6")));
        graph.addLink(new Link("l-13A", graph.getInterface("n-6.3"), graph.getInterface("n-6A.4")));
        graph.addLink(new Link("l-13B", graph.getInterface("n-6A.2"), graph.getNetwork("net-6")));
        graph.addLink(new Link("l-14", graph.getInterface("n-6.2"), graph.getNetwork("internet")));
        graph.addLink(new Link("l-15", graph.getInterface("n-7.1"), graph.getNetwork("internet")));
        graph.addLink(new Link("l-16", graph.getInterface("n-7.4"), graph.getNetwork("net-8")));
        final Link tunnel = Link.tunnel("t-1", graph.getInterface("n-3.4"), graph.getInterface("n-4.3"));
        tunnel.enabled(true);
        graph.addLink(tunnel);
        this.grp10(graph);
        this.nat10(graph);
    }
    
    void grp10(final Graph graph) throws Exception {
        final ObjectGroup grp = new ObjectGroup("G-8");
        grp.add(new InetAddress("10.1.8.200/32"));
        grp.add(new InetAddress("10.1.8.201/32"));
        grp.add(new InetAddress("10.1.8.202/32"));
        graph.addGroup(grp);
    }
    
    void nat10(final Graph graph) throws Exception {
        Node n = graph.getNode("n-5");
        final ObjectGroup p5 = new ObjectGroup("p5");
        p5.add(new IpAddr("10.1.4.200/32"));
        p5.add(new IpAddr("10.1.4.201/32"));
        final NatAction a5 = new NatAction(p5);
        n.nat().add(new NatRule("nat-1", new NatSelector("n-5.3", "n-5.2", new InetAddress("10.1.6.0/24"), Any.any), a5, NatPolicy.None));
        n.nat().add(new NatRule("nat-1", new NatSelector("n-5.4", "n-5.2", new InetAddress("10.1.6.0/24"), Any.any), a5, NatPolicy.None));
        n = graph.getNode("n-7");
        n.nat().add(new NatRule("nat-1", new NatSelector("n-7.4", "n-7.1", graph.getGroup("G-8"), Any.any), new NatAction(new IpAddr("23.0.0.1/32")), NatPolicy.None));
        n.nat().add(new NatRule("nat-2", new NatSelector("n-7.4", "n-7.1", new InetAddress("10.1.8.0/24"), Any.any), new NatAction(NatAction.Method.egress), NatPolicy.None));
        n = graph.getNode("n-2");
        n.nat().add(new NatRule("nat-1", new NatSelector("n-2.3", "n-2.1", new InetAddress("10.1.2.100/32"), Any.any), new NatAction(new IpAddr("66.1.2.100/32")), NatPolicy.None));
        n.nat().add(new NatRule("nat-2", new NatSelector("n-2.3", "n-2.4", new InetAddress("10.1.2.100/32"), Any.any), new NatAction(new IpAddr("66.1.2.100/32")), NatPolicy.None));
    }
    
    void init192(final Graph graph) throws Exception {
        Node n = new Node("xn-1");
        n.addInterface(new Interface("xn-1.1", new IpAddr("192.1.1.1/32")));
        n.addInterface(new Interface("xn-1.2", new IpAddr("23.1.2.1/32")));
        n.addInterface(new Interface("xn-1.3", new IpAddr("192.168.0.1/32")));
        n.addInterface(new Interface("xn-1.4", new IpAddr("192.1.1.4/32")));
        graph.addNode(n);
        n = new Node("xn-2");
        n.addInterface(new Interface("xn-2.1", new IpAddr("23.2.1.1/32")));
        n.addInterface(new Interface("xn-2.2", new IpAddr("192.2.1.4/32")));
        n.addInterface(new Interface("xn-2.3", new IpAddr("192.2.1.2/32")));
        n.addInterface(new Interface("xn-2.4", new IpAddr("172.1.1.1/32")));
        graph.addNode(n);
        n = new Node("xn-3");
        n.addInterface(new Interface("xn-3.1", new IpAddr("192.3.1.1/32")));
        n.addInterface(new Interface("xn-3.2", new IpAddr("192.2.1.1/32")));
        n.addInterface(new Interface("xn-3.3", new IpAddr("192.3.1.3/32")));
        n.addInterface(new Interface("xn-3.4", new IpAddr("192.131.1.4/32")));
        graph.addNode(n);
        n = new Node("xn-4");
        n.addInterface(new Interface("xn-4.1", new IpAddr("192.0.0.1/32")));
        n.addInterface(new Interface("xn-4.2", new IpAddr("192.0.0.2/32")));
        n.addInterface(new Interface("xn-4.3", new IpAddr("192.0.0.3/32")));
        n.addInterface(new Interface("xn-4.4", new IpAddr("192.0.0.4/32")));
        graph.addNode(n);
        graph.addNetwork(new Network("xnet-0", new IpAddr("192.0.0.0/8")));
        graph.addNetwork(new Network("xnet-1", new IpAddr("192.168.0.0/16")).setParent(graph.getNetwork("xnet-0")));
        graph.addNetwork(new Network("xnet-2", new IpAddr("192.2.1.0/24")));
        graph.addNetwork(new Network("xnet-3", new IpAddr("192.3.0.0/16")).setParent(graph.getNetwork("xnet-0")));
        graph.addNetwork(new Network("xnet-3.1", new IpAddr("192.131.1.0/24")).setParent(graph.getNetwork("xnet-3")));
        graph.addNetwork(new Network("xnet-172", new IpAddr("172.1.1.0/24")));
        graph.addLink(new Link("Xl-1", graph.getInterface("xn-1.3"), graph.getNetwork("xnet-1")));
        graph.addLink(new Link("Xl-2", graph.getInterface("xn-3.4"), graph.getNetwork("xnet-3.1")));
        graph.addLink(new Link("Xl-3", graph.getInterface("xn-2.4"), graph.getNetwork("xnet-172")));
        graph.addLink(new Link("Xl-4", graph.getInterface("xn-2.1"), graph.getNetwork("internet")));
        graph.addLink(new Link("Xl-5", graph.getInterface("xn-1.2"), graph.getNetwork("internet")));
        graph.addLink(new Link("Xl-6", graph.getInterface("xn-3.1"), graph.getNetwork("internet")));
        graph.addLink(new Link("Xl-7", graph.getInterface("xn-4.2"), graph.getNetwork("internet")));
        graph.addLink(new Link("Xl-8", graph.getInterface("xn-4.3"), graph.getNetwork("xnet-0")));
        graph.addLink(new Link("Xl-9", graph.getInterface("xn-3.2"), graph.getNetwork("xnet-2")));
    }
}
