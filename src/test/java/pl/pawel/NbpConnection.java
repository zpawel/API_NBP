package pl.pawel;

import org.json.JSONArray;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class NbpConnection {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, InvalidDataException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, InvalidDataException, InvalidDataException {
        JSONArray json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/tables/a/?format=json");
        //http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
        // http:api.nbp.pl/api/exchangerates/rates/a/" + KURS_WALUTY + "/" + /{endDate}/
//      2012-01-01
        throw new InvalidDataException();
//       System.out.println(json.toString());
    }

    
}