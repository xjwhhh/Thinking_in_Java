import com.sap.apibhub.sdk.client.*;
import com.sap.apibhub.sdk.client.auth.*;
import com.sap.apibhub.sdk.image_classification_api.model.*;
import com.sap.apibhub.sdk.image_classification_api.api.DefaultApi;

import java.io.File;
import java.util.*;

public class ImageClassifier {
    public static void main(String[] args) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        Map<String, Authentication> authentications = defaultClient.getAuthentications();

        //Currently Base Path points to sandbox system, change it to call your API Endpoint
        defaultClient.setBasePath("https://sandbox.api.sap.com/ml/imageclassifier");
        //You can obtain your API key on the Settings page of SAP API Business Hub. In the Settings page, choose the Show API Key toggle button to display and copy your API key. You have to be logged in to view your API Key.
        defaultClient.addDefaultHeader("APIKey", "KuOLPsQSA01teDWPT9xdHkWOrnMkEi1y");
        authentications.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));

        DefaultApi apiInstance = new DefaultApi();
        apiInstance.setApiClient(defaultClient);
        File files = new File("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\PalyGround");
        // File | The set of image files to be classified. Must be either one archive file containing image files,  or one or several image files.  Accepted file extensions:    * Archive file: zip tar gz tgz   * Image file: jpg jpe jpeg png gif bmp tif tiff
        String options = "{\"modelName\": \"model_ice\", \"modelVersion\": \"3\"}";
        // String | A ModelParameter object  in json format. Specification of the model Name and model Version to be used for  classification. This is for use with retrained models. Optional parameters:   * modelName - The model name that should be used for inference   * modelVersion - The model version that should be used for inference   Example: `{\"modelName\": \"model_ice\", \"modelVersion\": \"3\"}
        try {
            ResponseOk result = apiInstance.pOSTInferenceSync(files, options);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#pOSTInferenceSync");
            System.err.println("API Response : \n" + e.getResponseBody());
        }
    }
}
