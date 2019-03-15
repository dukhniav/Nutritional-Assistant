package com.example.nutritionalassistant.helper;

/* Recipe Search Program
 * Uses https://www.edamam.com/ Recipe API
 *
 * CSCI 436/597G ICT For Social Good
 * Group 7 - NutrAssistant
 * 3/5/19
 * recipe_search.java
 */

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
        import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
        import java.util.Map;

public class recipe_search {



    //Recipe Search (Used by app)
    public static String recipeSearch(String[] options) throws IOException, JSONException{
        String app_id = "6e9971e2";
        String app_key = "e6245a4398d314405ebb6c0e9264d6df";

        String url_path = getURL(options, app_id, app_key);
        //return queryRecipes(url_path);

        JSONObject json = readJsonFromUrl(url_path);
        return json.toString();
    }

    //Get Search URL
    private static String getURL(String[] opts_array, String app_id, String app_key) {
        Map<String, String> opts = toMap(opts_array);
        StringBuffer path = new StringBuffer("https://api.edamam.com/search?");
        path.append("q=");
        if (opts.containsKey("q")) {
            path.append(opts.get("q").trim().replace(" ", "+"));
        }
        path.append("&app_id=" + app_id + "&app_key=" + app_key);
        if (opts.containsKey("from")) {
            path.append("&from=" + opts.get("from"));
        }
        if (opts.containsKey("ingr")) {
            if(Integer.parseInt(opts.get("ingr")) != 1) {
                path.append("&ingr=" + opts.get("ingr"));
            }
        }

        if (opts.containsKey("diet")) {
            path.append("&diet=" + opts.get("diet"));
        }
        if (opts.containsKey("health")) {
            String[] list = opts.get("health").split(",");
            path.append("&health=" + list[0]);
            for (int i = 1; i < list.length; i++) {
                path.append("&health=" + list[i]);
            }
        }
        if (opts.containsKey("calories")) {
            if(Integer.parseInt(opts.get("calories")) != 0) {
                // if null, don't add to url string
                path.append("&calories=" + opts.get("calories"));
            }
        }
        if (opts.containsKey("time")) {
            if(Integer.parseInt(opts.get("time")) != 1) {
                // if null, don't add to url string
                path.append("&time=" + opts.get("time").replace("+", "%2B"));
            }
        }
        if (opts.containsKey("excluded")) {
            String[] list = opts.get("excluded").split(",");
            path.append("&excluded=" + list[0]);
            for (int i = 1; i < list.length; i++) {
                path.append("&excluded=" + list[i]);
            }
        }

        Log.d("TAG","SEARCH: |" + opts.get("q") + "|");
        Log.d("TAG","Cook: " + opts.get("time"));
        Log.d("TAG","Calories: " + opts.get("calories"));
        Log.d("TAG","Ingredients: " + opts.get("ingr"));
        Log.d("TAG","path: " + path.toString());
        return path.toString();
    }

    //Convert String[] to Map
    private static Map<String, String> toMap(String[] opts_array) {
        Map<String, String> opts = new HashMap<String, String>();
        for (String s : opts_array) {
            String[] pair = s.split(":");
            if (pair.length > 1) {
                opts.put(pair[0], pair[1]);
            } else {
                opts.put(pair[0], "");
            }
        }
        return opts;
    }

    //Send Query And Get Response
    private static String queryRecipes(String url_path) {
        StringBuffer response = new StringBuffer();
        String line;
        try {
            URL url = new URL(url_path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                response.append(line);
                Log.d("TAG", line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG", "response: " + response.toString());
        Log.d("TAG", "response length: " + response.toString().length());
        return response.toString();
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

