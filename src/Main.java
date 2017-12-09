import heros.IFDSTabulationProblem;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import heros.InterproceduralCFG;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

import java.io.BufferedWriter;
public class Main {
	
	public static void main(String[] args) {
		//String[] jars = {"Carpus.class"};
		//Scene.v().setSootClassPath(".:/usr/lib/jvm/java-8-openjdk/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk/jre/lib/jce.jar:" +
		//		"/usr/lib/jvm/java-8-openjdk/jre/lib/jsse.jar");

		PackManager.v().getPack("wjtp").add(new Transform("wjtp.icfg", new SceneTransformer() {
			protected void internalTransform(String phaseName, @SuppressWarnings("rawtypes") Map options) {

/*				IFDSTabulationProblem<Unit,?,SootMethod,InterproceduralCFG<Unit,SootMethod>> problem = new IFDSPossibleTypes(new JimpleBasedInterproceduralCFG());
				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JimpleIFDSSolver<?,InterproceduralCFG<Unit,SootMethod>> solver = new JimpleIFDSSolver(problem);
				solver.solve();
*/
				InterproceduralCFG icfg = new JimpleBasedInterproceduralCFG();
				Set<Unit> x = icfg.allNonCallStartNodes();
				//System.out.println(icfg.getClass().toString());
				System.out.println("RAHUL");
				System.out.println(x.size());
				for (Iterator<Unit> it = x.iterator(); it.hasNext(); ) {
					Unit f = it.next();
					SootMethod m = (SootMethod) icfg.getMethodOf(f);
					if(m.isMain())
					{
					System.out.println("Unit : "+ f.toString());
					System.out.println("Method " +icfg.getMethodOf(f).toString());//f.toString());
					System.out.println("Successor "+ icfg.getSuccsOf(f).toString());//f.toString());
					break;

					}
			    }		
				System.out.println("RAHUL_END");
				
				/*try{
				BufferedWriter bw = new BufferedWriter(new FileWriter("tempout.txt"));
				bw.write(icfg.allNonCallStartNodes().toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}
				*/
				//System.out.println(icfg.allNonCallStartNodes().toString());	
			 	
			}
			
		}));
		
		System.out.println(Scene.v().getSootClassPath());
		soot.Main.main(args);

	}

}
