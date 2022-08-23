package stock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import unixTime.MillToDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StockInfo {
    public JSONObject getStockDaily(String stockCode) throws ParseException,IOException{

    };
    public JSONObject getStockMonthly(String stockCode) throws ParseException,IOException{

    };
    public JSONObject getStockMonthly3(String stockCode) throws ParseException,IOException{

    };
    public JSONObject getStockYearly(String stockCode) throws ParseException,IOException{

    };
    public JSONObject getStockYearly3(String stockCode) throws ParseException,IOException{

    };
    public JSONObject getStock(String stockCode)throws ParseException, IOException{
        String BaseUrl = "https://invest.zum.com/api/domestic/stock/";
        String code = stockCode;
        URL url = new URL(BaseUrl + code);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();


        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);


        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while (br.ready()){
            sb.append(br.readLine());
        }
        conn.disconnect();



        while(br.ready()) {
            sb.append(br.readLine());
            //System.out.println(sb);
        }
        result = (JSONObject) new JSONParser().parse(sb.toString());
        StringBuilder out = new StringBuilder();
        JSONObject chart= (JSONObject) result.get("chart");
        JSONObject yearly = (JSONObject) chart.get("MONTHLY");
//        JSONObject yearly = (JSONObject) chart.get("DAILY"); 당일 하루치 분당 데이터 가져옴
        //JSONObject yearly = (JSONObject) chart.get("YEARLY"); 1년치 데이터 1일간격으로 데이터 가져옴
        //JSONObject yearly = (JSONObject) chart.get("MONTHLY"); 한달간 데이터 1인간격으로 가져옴.
        //JSONObject yearly = (JSONObject) chart.get("YEARLY3"); 3년치 데이터 1일간격으로 데이터 가져옴.
        //JSONObject data = (JSONObject) yearly.get("chart");
        //out.append(yearly.get("chart"));
        JSONArray array = (JSONArray) yearly.get("chart");


//        JSONArray array = (JSONArray) chart.get("stocks");
//        System.out.println(out.toString());

        JSONObject tmp;
        MillToDate millToDate = new MillToDate();
//        ArrayList<String> date = new ArrayList<>();
//        ArrayList<String> price = new ArrayList<>();
        LinkedHashMap<String, String> stockDatePrice = new LinkedHashMap<>();

        for(int i=0; i<array.size(); i++) {
            tmp = (JSONObject) array.get(i);
            out.append("tradeVolume("+i+") : "+ tmp.get("tradeVolume") +"\n");
            out.append("price("+i+") : "+ tmp.get("price")+"원"+"\n");
            out.append("time("+i+") : "+ millToDate.miltoDate((long)(tmp.get("time")))+"\n"); // (tmp.get("time")가 object타입이라 (long)으로 형변환 함.
            out.append("---------"+"\n");
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            stockDatePrice.put(date, price);
        }

       System.out.println(stockDatePrice);
        return new JSONObject(stockDatePrice);

    }
}
