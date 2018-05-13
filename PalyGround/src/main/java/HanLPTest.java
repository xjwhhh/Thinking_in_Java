import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.List;

public class HanLPTest {
    public static void main(String[] args){
        String a="Runtime Error Long Text\n@. Teiama\u00ae editor\n\n~ G Runtime Error\n~ G3 System Environment\n* [&) System environment\n\n \n\n(\u20ac] User and Transaction\n[\u20ac] REC Caller Information\n\n \n\n~ Gi User view\n(E) what happened?\n\nShore Text\nMo more memory available to add rows to an internal table.\n\n \n\n[Z) what can you do?\n~ Eu ABP Developer View\n\n \n\nShort Text\nError analysis\n\nHow to correct the erm\nInformation on where t\n\nwnat nappened\u00bb\nAn attempt was made to add rous to an internal table.\nmemory available for this however.\n\nTnere was no\n\n \n\nSource Code Extract (\u00a9\nContents of system fiek\n\n \n\nChosen variables\nActive Calsf\u00a3vents\nApplestion Cals\nList of ABAP programs \u00ab\n\nSiS Developer view\nInternal notes\nActive Cals in SAP Kem:\nDirectory of Application\nABAP Control Blocks (c:\n\n \n\nwat can you dos\note which actions and entries caused the error to occur.\n\nConsurt your SAP administrator.\n\nUsing transaction STaz for ABAP dump analysis, you can view,\nand retain termination messages for longer periods.\n\nTey to find out (using specific data selection for example)\ntransaction vill run vith less main memory.\n\nmanage,\n\nwhether the\n\nTE the problem is due to a temporary bottleneck, run the transaction\n\nagain.\n\nIf the error persists, ask your system administrator to check the\nforiowing profile parameters:\n\no (1.000. 000\nper user and internal session o | ztta/roll extension\n\n15.000.000) ciassic roll area\n(10.000. 000\n\nzi o\n(in Im) o abap/heap_ares_cotal (100.000. 000\navailable memory (malloc) for all users of an application server.\nseveral background processes are running on one server, temporary\n\n00.000.000) available memory/user in extended memory\n\netta/max menreq HB maximum size of an individual memory request\n1. soo. 000. 000)\n\nze\n\n   \n\n \n\n \n\n \n\f";
        a=a.replaceAll("\n{2,}\\s+","\n");
//        a=a.replaceAll("\f",",");
//        a=a.replaceAll(",{2,}",",");

        a= a.replaceAll("[^0-9a-zA-Z\\s,.]","");
        System.out.println(a);

//        List<String> sentenceList = HanLP.extractSummary(a, 3);
//        System.out.println(sentenceList);

    }
}
