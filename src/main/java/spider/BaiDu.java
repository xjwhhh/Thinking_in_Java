package spider;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class BaiDu {

    private static HashMap<String, Recruitment> stringRecruitmentHashMap = new HashMap<String,Recruitment>();

    private void getJobLinks() {

        try {
            Document doc = Jsoup.connect("https://talent.baidu.com/external/baidu/campus.html#/jobList").get();

            System.out.println(doc);

            Elements listRows = doc.getElementsByClass("list-row");

            for (Element listRow : listRows) {
                System.out.println(listRow);
                Element jobLink=listRow.selectFirst("a");
                System.out.println(jobLink.attr("href"));
                stringRecruitmentHashMap.put(jobLink.attr("href"),new Recruitment());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getContents(){

    }

    public  String sendPost() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL("https://talent.baidu.com/baidu/web/httpservice/getPostList?postType=&workPlace=&recruitType=1&keyWord=&pageSize=150&curPage=1&_=1514427372110");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
//            out.print(param);
            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
//                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
//        System.out.println(result);
        return result;
    }

    private void parseJson(String jsonString){
        jsonString=jsonString.replaceAll("\\\\r<br>","");
//        jsonString=jsonString.replaceAll("","");
//        System.out.println(jsonString);
        JSONObject jsonObject=JSONObject.fromObject(jsonString);
//        System.out.println(jsonObject.getJSONArray("postList"));
                JSONArray jsonArray = jsonObject.getJSONArray("postList");
                for(int i=0;i<jsonArray.size();i++){
//                    System.out.println(jsonArray.get(i));
                    JSONObject job=(JSONObject) jsonArray.get(i);
                    Recruitment recruitment=new Recruitment();
                    recruitment.setJobName(job.getString("name"));
                    recruitment.setPublishDate(job.getString("publishDate"));
                    recruitment.setJobType(job.getString("postType"));
                    recruitment.setWorkAddress(job.getString("workPlace"));
                    recruitment.setJobDescription(job.getString("serviceCondition"));
//                    System.out.println(job.getString("serviceCondition"));
                    recruitment.setJobRequirement(job.getString("workContent"));

                    stringRecruitmentHashMap.put(job.getString("postId"),recruitment);
//                    System.out.println(job.getString("postId"));


                }
    }



    public static void main(String[] args){
        BaiDu baiDu=new BaiDu();

        baiDu.getContents();
        String jsonString=baiDu.sendPost();
        baiDu.parseJson(jsonString);
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
