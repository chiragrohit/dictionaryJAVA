package url;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.JsonException;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class urlreturn {


    public String dictionaryEntries(String w) {
        final String language = "en";
        final String word = w;
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}

public class url {

    // Gets word from search textfield
    public String meaning (String w)
    {
        //API Authentication Keys
        final String app_id = //Enter app id;
        final String app_key = //Enter app key;

        String word_meaning = "";
        String word = w;


        // Passes to dictionaryEntries() to create url
        urlreturn obj = new urlreturn();
        String oxfLink = obj.dictionaryEntries(word);   // Stores url response as String



        try {
            // API Connection
            URL url = new URL(oxfLink); // API's URL response
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer result = new StringBuffer();

            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }

            // StringBuffer to String
            String finalResult = result.toString();


            //Returns Description After Parsing
            JSONParser parser = new JSONParser();

            JSONObject resultobj = (JSONObject) parser.parse(finalResult);
            JSONArray results = (JSONArray) resultobj.get("results");


            ArrayList<String> list = new ArrayList<String>();
            if (results != null) {
                int len = results.size();
                for (int i=0;i<len;i++){
                    list.add(results.get(i).toString());
                }
            }

            StringBuilder builder = new StringBuilder();
            for (String value : list) {
                builder.append(value +"\n");
            }

            // Parse only definitions
            String[] mydef = builder.toString().split("definitions\":", 0);


            // Remove []
            for (int i = 1; i< mydef.length; i++)
            {
                String mean = mydef[i].split("]")[0].substring(1);
                word_meaning = word_meaning + "\n>> " + mean ;
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return word_meaning ;       // Returns final parsed meanings
    }
}
