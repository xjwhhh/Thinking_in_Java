package VTChallange;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Feature {
    public static void main(String[] args){
        DataOutputStream dataOut = null;
        BufferedReader in =null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/ml/featureextraction/inference_sync";


            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("POST");

            //adding headers
            connection.setRequestProperty("Content-Type","multipart/form-data");
            connection.setRequestProperty("Accept","application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey","GLnnEt6i25UHue0Uxx35GNybe0eHU5vB");


            connection.setDoInput(true);

            //sending POST request
            connection.setDoOutput(true);
            connection.setRequestProperty("files","test.png");
            // Optional Multipart/Form-data parameters: "files"
            // For more details, see the API definition

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
