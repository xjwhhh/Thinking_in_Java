package structurehomewrok.sample;

import cn.edu.nju.sklnst.whilex.Node;

public interface Worklist {
    void add(Node t);
    boolean hasNext();
    Node next();
}
