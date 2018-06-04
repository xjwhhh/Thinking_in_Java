import com.sap.apibhub.sdk.client.*;
import com.sap.apibhub.sdk.client.auth.*;
import com.sap.apibhub.sdk.img_feature_extraction_api.model.*;
import com.sap.apibhub.sdk.img_feature_extraction_api.api.InferenceSyncApi;

import java.io.File;
import java.util.*;

public class ImageFeature {
    public static void main(String[] args) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        Map<String, Authentication> authentications = defaultClient.getAuthentications();

        //Currently Base Path points to sandbox system, change it to call your API Endpoint
        defaultClient.setBasePath("https://sandbox.api.sap.com/ml/featureextraction");
        //You can obtain your API key on the Settings page of SAP API Business Hub. In the Settings page, choose the Show API Key toggle button to display and copy your API key. You have to be logged in to view your API Key.
        defaultClient.addDefaultHeader("APIKey","KuOLPsQSA01teDWPT9xdHkWOrnMkEi1y");
        authentications.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));

        InferenceSyncApi apiInstance = new InferenceSyncApi();
        apiInstance.setApiClient(defaultClient);
        File files = new File("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\PalyGround\\src\\main\\java\\test.png");
        // File | This parameter is required. <br>The list of file(s) to be uploaded. <br>Either one Archive file, one image file, or multiple image files are supported: <ul> <li>Archive file - <i>one file with the format ''*_/zip', '*_/tar.gz', or '*_/tar'</i></li> <li>Image files - <i>different image formats, such as .jpeg, .png, .tif, or .bmp </i></li> </ul> <ul> </ul>
        try {
            Response result = apiInstance.inferenceSyncPost(files);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InferenceSyncApi#inferenceSyncPost");
            System.err.println("API Response : \n"+e.getResponseBody());
        }
    }
}
