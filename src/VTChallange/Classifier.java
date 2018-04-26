package VTChallange;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Classifier {
    public static void main(String[] args) {
        DataOutputStream dataOut = null;
        BufferedReader in = null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/ml/imageclassifier/inference_sync";

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("POST");

            //adding headers
            connection.setRequestProperty("content-type", "multipart/form-data; boundary=---011000010111000001101001");
            connection.setRequestProperty("Accept", "application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey", "GLnnEt6i25UHue0Uxx35GNybe0eHU5vB");

            connection.setDoInput(true);

            //sending POST request
            connection.setDoOutput(true);
            // Optional Multipart/Form-data parameters: "options"
            // For more details, see the API definition
            dataOut = new DataOutputStream(connection.getOutputStream());
            dataOut.writeBytes("-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"files\"; filename=\"<file_name>\"\r\nContent-Type: <file_type>\r\n\r\n<file_contents>\r\n-----011000010111000001101001--");
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
                if (dataOut != null) {
                    dataOut.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                //do something with exception
                e.printStackTrace();
            }
        }
    }
}
