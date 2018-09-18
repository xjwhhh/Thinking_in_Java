//package structurehomewrok.sample;
//
//import cn.edu.nju.sklnst.whilex.*;
//
//public class PerformGenKill<L extends GenKillAnalysis<T>, T> {
//    int numofiteration;
//    public FlowGraph fg = new FlowGraph();
//    Worklist list;
//    GenKillAnalysis<T> analysis;
//    Map<Node, ImmutableSet<T>> stateofentry = new HashMap<Node, ImmutableSet<T>>();
//    Map<Node, ImmutableSet<T>> stateofexit = new HashMap<Node, ImmutableSet<T>>();
//    Statement root;
//
//    public PerformGenKill(Worklist list,
//
//                          GenKillAnalysis<T> analysis) {
//        this.list = list;
//        this.analysis = analysis;
//    }
//
//    public boolean lineNotEmpty(String column[],
//                                int columnnum) {
//        int i = 0;
//        for (i = 0; i < columnnum; i++) {
//            if (column[i].length() > 0) return true;
//        }
//        return false;
//    }
//
//    ImmutableSet<T> getEntryResult(Node node) {
//        return stateofentry.get(node) != null ? stateofentry.get(node) : analysis.getInitial();
//    }
//
//    ImmutableSet<T> getExitResult(Node node) {
//        return stateofexit.get(node) != null ? stateofexit.get(node) : analysis.getInitial();
//    }
//
//    public String writeOneLine(String column[], final int columnnum, final int rankwidth) {
//        String retval = "";
//        int i = 0, j = 0;
//        String writecolumn = "";
//        while (lineNotEmpty(column, columnnum)) {
//            for (j = 0; j < columnnum; j++) {
//                if (column[j].length() > rankwidth - 2) {
//                    writecolumn = column[j].substring(0, rankwidth - 3);
//                    column[j] = column[j].substring(rankwidth - 2);
//                } else {
//                    writecolumn = column[j];
//                    column[j] = "";
//                }
//                retval += writecolumn;
//                for (i = rankwidth; i > writecolumn.length(); i--)
//                    retval += "";
//            }
//            retval += '\n';
//        }
//        return retval;
//    }
//
//    void run(Statement root) {
//        this.root = root;
//        Expressions.aes.clear();
//        numofiteration = 0;
//        fg.parse(root);
//        if (analysis.isReverse()) {
//            fg = FlowGraph.reverse(fg);
//        }
//        for (Node n : fg.allnodes) {
//
//            list.add(n);
//        }
//        while (list.hasNext()) {
//            numofiteration++;
//
//            Node n = list.next();
//            ImmutableSet<T> inputof_n = getEntryResult(n);
//            ImmutableSet<T> newoutputof_n =
//                    inputof_n.minus(analysis.getKill(n)).union(analysis.getGen(n));
//            ImmutableSet<T> oldoutputof_n = getExitResult(n);
//            if (!newoutputof_n.equals(oldoutputof_n)) {
//                stateofexit.put(n, newoutputof_n);
//                for (Node ns : fg.getSucessors(n)) {
//                    ImmutableSet<T> newinputof_ns = newoutputof_n;
//                    for (Node nsd : fg.getPredecessors(ns)) {
//                        newinputof_ns =
//                                analysis.combine(newinputof_ns, getExitResult(nsd));
//                    }
//                    ImmutableSet<T> oldinputof_ns = getEntryResult(ns);
//                    if (!newinputof_ns.equals(oldinputof_ns)) {
//                        stateofentry.put(ns, newinputof_ns);
//                        list.add(ns);
//                    }
//                }
//            }
//        }
//        if (analysis.isReverse()) {
//            fg = FlowGraph.reverse(fg);
//            Map<Node, ImmutableSet<T>> swapnode = stateofentry;
//            stateofentry = stateofexit;
//            stateofexit = swapnode;
//        }
//    }
//}
//
//
//
//
