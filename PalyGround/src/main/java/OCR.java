import com.sap.apibhub.sdk.client.*;
import com.sap.apibhub.sdk.client.auth.*;
import com.sap.apibhub.sdk.ocr_api.model.*;
import com.sap.apibhub.sdk.ocr_api.api.OcrApi;

import java.io.File;
import java.util.*;

public class OCR {
    public static void main(String[] args) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        Map<String, Authentication> authentications = defaultClient.getAuthentications();

        //Currently Base Path points to sandbox system, change it to call your API Endpoint
        defaultClient.setBasePath("https://sandbox.api.sap.com/ml/ocr");
        //You can obtain your API key on the Settings page of SAP API Business Hub. In the Settings page, choose the Show API Key toggle button to display and copy your API key. You have to be logged in to view your API Key.
        defaultClient.addDefaultHeader("APIKey", "KuOLPsQSA01teDWPT9xdHkWOrnMkEi1y");
        authentications.put("APIBHUB_SANDBOX_APIKEY", new ApiKeyAuth("header", "APIKey"));

        OcrApi apiInstance = new OcrApi();
        apiInstance.setApiClient(defaultClient);
        String id = "6597c27e-10cb-4754-b667-7fe2d321aaa3"; // String | The identifier returned upon submitting the processing request.
        try {
            ResponseOk result = apiInstance.ocrJobsIdGet(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling OcrApi#ocrJobsIdGet");
            System.err.println("API Response : \n" + e.getResponseBody());
        }
    }
}
