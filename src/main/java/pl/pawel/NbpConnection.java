package pl.pawel;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.DoubleBuffer;
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
    private static Double getJsonObject(JSONObject json) {

        JSONArray rates = json.getJSONArray("rates");
        System.out.println(rates.toString());

        double sr = 0, sum=0;
        int i=0;
        for(i=0; i<rates.length(); i++){
            JSONObject value = rates.getJSONObject(i);
            double id = value.getDouble("mid");
            sum += id;
        }
        sr = sum/i;
        System.out.print("srednia kursu wynosi: " + round(sr, 4) );
    return sr;
    }



    public static void main(String[] args) throws IOException, InvalidDataException, InvalidDataException {

        //System.out.println("http://api.nbp.pl/api/exchangerates/tables/a/"+ dateOf() + "/" + dateTo() + "/?format=json");
        JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/" + getKurs() + "/" + dateOf() + "/" + dateTo() + "/?format=json");
                //+ getKurs() + "/" + dateOf() + "/" + dateTo() + "/?format=json");

        getJsonObject(json);


        }
                //
        //http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
        // http:api.nbp.pl/api/exchangerates/rates/a/" + KURS_WALUTY + "/" + /{endDate}/  2012-01-01
       // throw new InvalidDataException();
        //System.out.println(id);
        //System.out.println(getKurs());
        //System.out.println(dateOf());
        //System.out.println(dateTo());
    }
