import heros.IFDSTabulationProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import heros.InterproceduralCFG;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

import soot.Body;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.ide.JimpleIFDSSolver;
import soot.jimple.toolkits.ide.exampleproblems.IFDSPossibleTypes;
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG;

import soot.util.cfgcmd.CFGToDotGraph;
import soot.util.dot.DotGraph;
import soot.tools.CFGViewer;

import java.io.BufferedWriter;
//public class Main {
//	
//	public static void main(String[] args) {
//		Scene.v().setSootClassPath(".:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:" +
//				"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:"+"/home/kshitijgorde/workspace/ICFG/testers");
//		
//		List<String> argsList = new ArrayList<String>(Arrays.asList(args));
//		//argsList.addAll(Arrays.asList(new String[]{"-w","-main-class","testers.CallGraphs","testers.CallGraphs","testers.A","-allow-phantom-refs"}));
//		argsList.addAll(Arrays.asList(new String[]{"-w","whole-program","-process-dir","testers","-allow-phantom-refs"}));
//		PackManager.v().getPack("wjtp").add(new Transform("wjtp.icfg", new SceneTransformer() {
//			protected void internalTransform(String phaseName, @SuppressWarnings("rawtypes") Map options) {
//				
//				
//				//SootClass a=Scene.v().getSootClass("testers.A");
//				//System.out.println(a.toString());
//				InterproceduralCFG icfg = new JimpleBasedInterproceduralCFG();
//				DotGraph d = new DotGraph(icfg.toString());
//				d.plot("x");
//				G.v().out.println("abc" + d.DOT_EXTENSION);
//				System.out.println(d.toString());;
//				
////
////				IFDSTabulationProblem<Unit,?,SootMethod,InterproceduralCFG<Unit,SootMethod>> problem = new IFDSPossibleTypes(new JimpleBasedInterproceduralCFG());
////				
////				@SuppressWarnings({ "rawtypes", "unchecked" })
////				JimpleIFDSSolver<?,InterproceduralCFG<Unit,SootMethod>> solver = new JimpleIFDSSolver(problem);
////				solver.solve();
////				
//				
////				InterproceduralCFG icfg = problem.interproceduralCFG();
////				System.out.println(icfg.allNonCallStartNodes().toString());
////				
//
////				InterproceduralCFG icfg = new JimpleBasedInterproceduralCFG();
////				Set<Unit> x = icfg.allNonCallStartNodes();
////				//System.out.println(icfg.getClass().toString());
////				System.out.println("RAHUL");
////				System.out.println(x.size());
////				for (Iterator<Unit> it = x.iterator(); it.hasNext(); ) {
////					Unit f = it.next();
////					SootMethod m = (SootMethod) icfg.getMethodOf(f);
////					if(m.isMain())
////					{
////					System.out.println("Unit : "+ f.toString());
////					System.out.println("Method " +icfg.getMethodOf(f).toString());//f.toString());
////					System.out.println("Successor "+ icfg.getSuccsOf(f).toString());//f.toString());
////					break;
////
////					}
////			    }		
////				System.out.println("RAHUL_END");
//			}
//			
//		}));
//		System.out.println(Scene.v().getSootClassPath());
//		args=argsList.toArray(new String[0]);
//		soot.Main.main(args);
//		
//
//	}
//
//}
////-process-dir /home/kshitijgorde/workspace/ICFG/src/TestJars/ -w -whole-program  -allow-phantom-refs
////-process-dir src Carpus.class -src-prec c Carpus -soot-class-path "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/Carpus.class"



import heros.IFDSTabulationProblem; 
import heros.InterproceduralCFG; 

import java.util.Arrays; 
import java.util.LinkedList; 
import java.util.List; 
import java.util.Map; 

import soot.PackManager; 
import soot.Scene;
import soot.SceneTransformer; 
import soot.SootMethod; 
import soot.Transform; 
import soot.Unit; 
import soot.jimple.toolkits.ide.JimpleIFDSSolver; 
import soot.jimple.toolkits.ide.exampleproblems.IFDSPossibleTypes; 
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG; 
import soot.options.Options;

public class Main { 

     public static void main(String[] args) { 
    	 ArrayList<String> argsList = new ArrayList<String>();
    	 argsList.addAll(Arrays.asList(new String[]{
    			    "Kshitij",
    			 	"-w",

    			   "-cp",

    			   "/home/kshitijgorde/workspace/ICFG/testers/",

    			   "-pp",

    			   "-allow-phantom-refs",

//    			   "-process-dir",
//
//    			   "/home/kshitijgorde/workspace/ICFG/sootOutput/",
    			    			   

    			   }));


   	 Scene.v().setSootClassPath("/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:" +
			"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:/home/kshitijgorde/workspace/ICFG/testers/");
         PackManager.v().getPack("wjtp").add(new Transform("wjtp.ifds", new SceneTransformer() { 
             protected void internalTransform(String phaseName, @SuppressWarnings("rawtypes") Map options) { 
            	 
            	 //CHATransformer.v().transform();
            	 
            	 // Retrieve the method and its body
            	 //SootClass c = Scene.v().getSootClass("Kshitij");
//            	 SootMethod m = c.getMethodByName("kshitij_method1");
//            	 Body b = m.retrieveActiveBody();
//            	 System.out.println(b.toString());
            	 
            	 
            	 System.out.println(Scene.v().hasMainClass());
            	 
//            	 Scene.v().loadNecessaryClasses();
//            	 Scene.v().loadBasicClasses();
            	 //System.out.println(Scene.v().getClasses());
            	 SootClass mainClass = null;
            	 SootMethod main_method = null;
            	 int mainMethodFlag = 0;
            	 for (SootClass sc : Scene.v().getClasses()){
            		 //System.out.println(sc.getName());
            		 if (sc.getName().equals("Kshitij")){
            			 mainClass = sc;
            			 System.out.println("All methods inside are: ");
            			 System.out.println(sc.getMethods().toString());
            			 
            			 for (SootMethod methods : sc.getMethods()){
            				 if (methods.getName().contains("main")){
            					 main_method = methods;
            					 System.out.println("Main method found!. Terminating Search!");
            					 System.out.println(main_method.getName());
            					 mainMethodFlag = 1;
            					 break;
            				 }
        	 
         				}
            			if (mainMethodFlag == 1){
            				//indicates already found main method
            				System.out.println("Exiting Main Class search!");
            				break;
            			}     			 
            			             			 //break the loop
            			 
            		 }
            	 }
            	 System.out.println("Setting the Entry Point!");
            	 ArrayList<SootMethod> entry_points = new ArrayList<SootMethod>();
            	 entry_points.add(main_method);
            	 Scene.v().setEntryPoints(entry_points);
            	 System.out.println("Entry Points are: ");
            	 System.out.println(Scene.v().getEntryPoints());
            	 
            	 
            	 InterproceduralCFG<Unit, SootMethod> icfg = new JimpleBasedInterproceduralCFG();
            	 
            	 Unit startPoint = null;
            	 for (Unit temp: icfg.getStartPointsOf(main_method)){
            		 startPoint = temp;
            		 break;
            	 }
            	 
            	 //System.out.println(icfg.getSuccsOf(startPoint));
            	 
            	 
            	 
            	 
            	 DotGraph dotIcfg = new DotGraph("");
            	 SootMethod temp = main_method;
            	 
            	 
            	 for (Unit t: icfg.getSuccsOf(startPoint)){
            		 dotIcfg.drawEdge(startPoint.toString(), t.toString());
            		 startPoint = t;
            	 }
            	 
            	 
            	 dotIcfg.plot("FirstICFG");
            	 G.v().out.println("FistICFG"+dotIcfg.DOT_EXTENSION);
//            	 
//            	 System.out.println(icfg.getSuccsOf(startPoint));
            	 
            	 //System.out.println(icfg.getStartPointsOf(main_method));
            	 
            	 
//            	 CallGraph cg = Scene.v().getCallGraph();
//            	System.out.println(cg.size());
            	 
            	 //InterproceduralCFG icfg = new JimpleBasedInterproceduralCFG();
            	 //System.out.println(icfg.allNonCallStartNodes().size());
            	 
            	 
//            	 if (mainClass == null){
//            		 System.out.println("Main class is Null");
//            		 System.exit(101);
//            	 }
//            	 Scene.v().setMainClass(mainClass);
//            	 System.out.println(Scene.v().getMainClass());
            	 
            	 
//            	 CallGraph cg = Scene.v().getCallGraph();
//            	 Scene.v().setMainClassFromOptions();
//            	 SootMethod src = Scene.v().getMainClass().getMethod("main");
//            	 ArrayList<SootMethod> x = new ArrayList<SootMethod>();
//            	 x.add(src);
//            	 System.out.println(src.getName());
//            	 Scene.v().setEntryPoints(x);
            	 
            	 //System.out.println(Scene.v().getEntryPoints().toString());
            	 
//            	 DotGraph dt = new DotGraph(cg.toString());
//            	 dt.plot("temp");
//            	 G.v().out.println("temps"+dt.DOT_EXTENSION);
            	 
             } 
				         })); 
				         
				        // argsList.add("-p"); argsList.add("cg"); argsList.add("all-reachable:true");

				         args = argsList.toArray(new String[0]);
				         
				         soot.Main.main(args); 
				     } 
				
				} 
