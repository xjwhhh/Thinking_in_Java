//package structurehomewrok.sample;
//
//import cn.edu.nju.sklnst.whilex.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LiveVariableAnalysis implements GenKillAnalysis<Variable> {
//    public ImmutableSet<Variable> getGen(Node node) {
//        final List<Variable> l = new ArrayList<Variable>();
//        node.accept(new TreeVisitor() {
//            public Void visit(UseVariable uv) {
//                l.add(uv.var);
//                super.visit(uv);
//                return null;
//            }
//        });
//        return new ImmutableSet<Variable>(l);
//    }
//
//    public ImmutableSet<Variable> getKill(Node node) {
//        final List<Variable> l = new ArrayList<Variable>();
//        node.accept(new TreeVisitor() {
//            public Void visit(AssignStatement as) {
//                l.add(as.v);
//                super.visit(as);
//                return null;
//            }
//        });
//        return new ImmutableSet<Variable>(l);
//    }
//
//    public ImmutableSet<Variable> getInitial() {
//        return new ImmutableSet<Variable>(new ArrayList<Variable>());
//    }
//
//    public ImmutableSet<Variable> combine(ImmutableSet<Variable> s1, ImmutableSet<Variable> s2) {
//        return s1.union(s2);
//    }
//
//    public boolean isReverse() {
//        return true;
//    }
//}
