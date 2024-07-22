package utilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONToMap {

    public ArrayList<HashMap<String, String>> getData(String filename) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try (FileReader reader = new FileReader(filename+".json")) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String, String> map = new HashMap<>();

                for (String key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);
                    map.put(key, value != null ? value.toString() : null);
                }

                list.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
