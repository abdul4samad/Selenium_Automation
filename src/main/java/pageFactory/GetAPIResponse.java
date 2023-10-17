package pageFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

import static io.restassured.RestAssured.given;

public class GetAPIResponse {

    public static String[] getBeneDetails(String beneficiaryID, String authKey){
        RequestSpecification requestSpecification = given()
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Referer", "https://app.cowin.gov.in/")
                .header("Authorization", authKey)
                .header("Content-Type", "application/json")
                //.header("Content-Length", "52")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "no-cors")
                .header("Origin", "https://app.cowin.gov.in")
                .header("Connection", "keep-alive")
                .header("Sec-Fetch-Site", "same-site")
                .header("Pragma", "no-cache")
                .header("TE", "trailers")
                .header("Cache-Control", "no-cache")
                .header("Sec-Fetch-Mode","cors")
                ;

        Response response = requestSpecification.when().body("{\"search_value\":\""+beneficiaryID+"\",\"flag\":1,\"precaution_dose\":true}")
                .post("https://api.cowin.gov.in/api/v1/registration/beneficiary/v2/getBeneficiaries");
        String x = response.getBody().asString();
        System.out.println(x);
        String photoIDNumber = "";
        String name = "";
        int dosePending = 0;
        int year = 0;
        try{
            JSONArray jsonArray = new JSONArray(x);
            x=jsonArray.getJSONObject(0).getString("beneficiary_id");
            beneficiaryID=x;
            photoIDNumber=jsonArray.getJSONObject(0).getString("photo_id_number");
            year = jsonArray.getJSONObject(0).getInt("year");
            dosePending = jsonArray.getJSONObject(0).getInt("vaccination_doses_due");
            name = jsonArray.getJSONObject(0).getString("ben_name");
        }catch (Exception e){
            x=null;
            System.out.println(e.getMessage());;
        }
         String[] testy= {name,photoIDNumber,year+"",dosePending+""};
        return testy;
    }

}
