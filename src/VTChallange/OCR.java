package VTChallange;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class OCR {

    public static DataOutputStream readFileByBytes(String fileName, DataOutputStream outputStream)  {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(
                    fileName));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                outputStream.write(bufferOut, 0, bytes);
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        return outputStream;
    }

    public static void main(String[] args){
        DataOutputStream dataOut = null;
        BufferedReader in =null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/ml/ocr/ocr/jobs";


            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("POST");

            //adding headers
            connection.setRequestProperty("content-type","multipart/form-data; boundary=---011000010111000001101001");
            connection.setRequestProperty("Accept","application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey","KuOLPsQSA01teDWPT9xdHkWOrnMkEi1y");


            connection.setDoInput(true);

            //sending POST request
            connection.setDoOutput(true);
            dataOut = new DataOutputStream(connection.getOutputStream());
            dataOut.writeBytes("-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"files\"; filename=\"C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\src\\VTChallange\\test.png\"\r\nContent-Type: image/png\r\n\r\n");
            dataOut=readFileByBytes("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\src\\VTChallange\\test.png",dataOut);
            dataOut.writeBytes("\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"options\"\r\n\r\n"+"{\"lang\": \"en,de\", \"output_type\": \"txt\", \"page_seg_mode\": \"1\", \"model_type\": \"lstm_standard\"} "+"\r\n-----011000010111000001101001--");
            dataOut.flush();

            int responseCode = connection.getResponseCode();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //printing response
            System.out.println(response.toString());

        } catch (Exception e) {
            //do something with exception
            e.printStackTrace();
        } finally {
            try {
                if(dataOut != null) {
                    dataOut.close();
                }
                if(in != null) {
                    in.close();
                }

            } catch (IOException e) {
                //do something with exception
                e.printStackTrace();
            }
        }
    }
}
