package pl.pawel;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;


public class NbpConnection {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, InvalidDataException {
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
    private static String getKurs(){
        System.out.println("Podaj kurs walut:");
        String kurs;
        Scanner odczyt = new Scanner(System.in);
        kurs = odczyt.nextLine();

        return kurs;
    }
    private static String dateOf(){
        System.out.println("Podaj od jakiej daty pobrac kursy (data w formacie RRRR-MM-DD)");
        String date;
        Scanner odczyt = new Scanner(System.in);
        date = odczyt.nextLine();

        return date;
    }

    private static String dateTo(){
        System.out.println("Podaj do jakiej daty pobrac kursy (data w formacie RRRR-MM-DD)");
        String date;
        Scanner odczyt = new Scanner(System.in);
        date = odczyt.nextLine();

        return date;
    }


    public static void main(String[] args) throws IOException, InvalidDataException, InvalidDataException {

        //System.out.println("http://api.nbp.pl/api/exchangerates/tables/a/"+ dateOf() + "/" + dateTo() + "/?format=json");
        JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/usd/2015-01-01/2015-01-10");
                //+ getKurs() + "/" + dateOf() + "/" + dateTo() + "/?format=json");
        JSONArray rates = json.getJSONArray("rates");
        //JSONArray countries = (JSONArray)json.get("mid");
        //JSONArray arr = json.getJSONArray("mid");
        //JSONArray age = json.getJSONArray("mid");
                //+ getKurs() + "/" + dateOf() + "/" + dateTo() + "/?format=json");
        //http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
        // http:api.nbp.pl/api/exchangerates/rates/a/" + KURS_WALUTY + "/" + /{endDate}/  2012-01-01
       // throw new InvalidDataException();
      System.out.println(json.toString());
        System.out.println(rates.toString());
        //System.out.println(arr);
        //System.out.println(getKurs());
        //System.out.println(dateOf());
        //System.out.println(dateTo());
    }
}