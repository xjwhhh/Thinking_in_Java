//package structurehomewrok.sample;
//
//import cn.edu.nju.sklnst.whilex.*;
//
//public class Main {
//    public static void main(String[] args) {
//        for (String name : args) {
//            Node result = Node.readNode(name);
//            if (result != null && result instanceof Statement) {
//                PerformGenKill pg =
//                        new PerformGenKill<LiveVariableAnalysis, Variable>(new StackWorklist(), new LiveVariableAnalysis());
//                pg.run((Statement) result); //pg =
//                new PerformGenKill<AvailableExpressionAnalysis, ArithmeticExpression>(new StackWorklist(), new AvailableExpressionAnalysis());
//                System.out.println(pg);
//            }
//        }
//    }
//}
