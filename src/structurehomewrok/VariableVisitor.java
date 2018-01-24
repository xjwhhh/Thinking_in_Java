package structurehomewrok;

import cn.edu.nju.sklnst.whilex.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class VariableVisitor extends Visitor<Void> {

    private Set<Variable> variableSet = new HashSet<>();

    private String filename;

    public VariableVisitor(String filename) {
        this.filename = filename;
    }

    @Override
    public Void visit(Node n) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(n);
        Node currentNode = null;
        while (!nodeStack.empty()) {
            currentNode = nodeStack.peek();
            if (currentNode instanceof AssignStatement) {
                variableSet.add(((AssignStatement) currentNode).v);
            }
            nodeStack.pop();
            for (Node child : currentNode) {
                if (child instanceof Program) {
                    nodeStack.push(((Program) child).body);
                } else if (child instanceof Statement) {
                    nodeStack.push(child);
                }
            }
        }
        System.out.println(filename + " has " + variableSet.size() + " variables:");
        for (Variable variable : variableSet) {
            System.out.print(variable.name + " ");
        }
        return null;
    }



}
