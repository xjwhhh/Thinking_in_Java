package spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class Ali {

    private static HashMap<String, Recruitment> stringRecruitmentHashMap = new HashMap<String,Recruitment>();

    private void getJobLinks() {

        try {
            Document doc = Jsoup.connect("https://campus.alibaba.com/positionList.htm").get();

            Elements jobList = doc.getElementsByClass("campus-position-table-body");
            for (Element element : jobList) {
                Elements jobs = element.select("tr");
                for (Element job : jobs) {
                    Element jobLink = job.selectFirst("th>a");
                    stringRecruitmentHashMap.put(jobLink.attr("href"), new Recruitment());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getRecruitmentDetailInfo() {
        for (String jobLink : stringRecruitmentHashMap.keySet()) {
            Recruitment recruitment = stringRecruitmentHashMap.get(jobLink);
            try {
                Document doc = Jsoup.connect(jobLink).get();
                Elements content = doc.getElementsByClass("word word_image content");
                Elements jobName = content.get(0).getElementsByTag("h5");
                recruitment.setJobName(jobName.get(0).text());

                Element element = doc.getElementById("J-jobs");
                Element jobBasicInfo = element.selectFirst("dl");

                String jobBasicInfoString = jobBasicInfo.text();
                String[] jobInfo = jobBasicInfoString.split("岗位要求");

                String jobDescription = jobInfo[0];
                String jobRequirementInfo = jobInfo[1];

                String[] jobRequirements = jobRequirementInfo.split("工作地点");
                String jobRequirement = jobRequirements[0];

                jobDescription = jobDescription.replace("岗位描述", "");
                jobDescription = jobDescription.replace("Job Description ", "");

                jobRequirement = jobRequirement.replace("Qualifications ", "");

                recruitment.setJobDescription(jobDescription);
                recruitment.setJobRequirement(jobRequirement);

                Element workAddress = element.selectFirst(".work-place");
                recruitment.setWorkAddress(workAddress.text());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void getTreatment() {

    }

    private void getCompanyIntroduction() {

    }

    public static void main(String[] args) {
        Ali test = new Ali();
        test.getJobLinks();
        test.getRecruitmentDetailInfo();

        for (String jobLink : stringRecruitmentHashMap.keySet()) {
            Recruitment recruitment = stringRecruitmentHashMap.get(jobLink);
            System.out.println("工作名称:" + recruitment.getJobName());
            System.out.println("工作地点:" + recruitment.getWorkAddress());
            System.out.println("岗位描述:" + recruitment.getJobDescription());
            System.out.println("岗位要求:" + recruitment.getJobRequirement());
            System.out.println("===================================================");

        }

    }
}
