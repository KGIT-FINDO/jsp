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
public class stockAPI  {
    public static  void main(String[] args) throws ParseException, IOException {

        String BaseUrl = "https://invest.zum.com/api/domestic/stock/";
        String testCode = "010660";
        String samsung = "005930";
        URL url = new URL(BaseUrl + samsung);
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
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        for(int i=0; i<array.size(); i++) {
            tmp = (JSONObject) array.get(i);
            out.append("tradeVolume("+i+") : "+ tmp.get("tradeVolume") +"\n");
            out.append("price("+i+") : "+ tmp.get("price")+"원"+"\n");
            out.append("time("+i+") : "+ millToDate.miltoDate((long)(tmp.get("time")))+"\n"); // (tmp.get("time")가 object타입이라 (long)으로 형변환 함.
            out.append("---------"+"\n");
            date.add(millToDate.miltoDate((long)(tmp.get("time"))));
            price.add(tmp.get("price").toString());



            // chart[] 배열 안에 있는 genres[] 데이터 꺼내기
//            JSONArray array2 = (JSONArray) tmp.get("genres");
//            out.append("genres("+i+"): ");
//            for(int j=0; j<array2.size(); j++) {
//                out.append(array2.get(j));
//                if(j!=array2.size()-1) {
//                    out.append(",");
//                }
//            }
//            out.append("\n");
//            out.append("\n");
        }
        //System.out.println(out.toString());
        for(int i=0; i < date.size(); i++){
            System.out.print(date);

        }
        //System.out.println(out.toString());
        System.out.println();
        for(int i=0; i < date.size(); i++){
            System.out.print(price);

        }

        System.out.println("-------");
        System.out.println((JSONObject) array.get(1));
//우선 메인 메소드에 종목 불러오는지만 콘솔창에 뜨도록 해둠
        //향후 따로 메소드와 클래스 분리핼 예정

    }

}
