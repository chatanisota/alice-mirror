import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherAPI{
  PApplet parent;
  Map<Integer, Integer> mapWeather;
  Map<Integer, Integer> mapStrong;
  int weatherId;
  
  public WeatherAPI(PApplet p){
    this.parent = p;
  }
  
  public void setup(){
      /* create hashmap */
      mapWeather = new HashMap<>();
      /* thunderstorm, 200-232: 0 */
      mapWeather.put(200, 0);
      mapWeather.put(201, 0);
      mapWeather.put(202, 0);
      mapWeather.put(210, 0);
      mapWeather.put(211, 0);
      mapWeather.put(212, 0);
      mapWeather.put(221, 0);
      mapWeather.put(230, 0);
      mapWeather.put(231, 0);
      mapWeather.put(232, 0);
     
      /* drizzle, 300-321: 0 */
      mapWeather.put(300, 0);
      mapWeather.put(301, 0);
      mapWeather.put(302, 0);
      mapWeather.put(310, 0);
      mapWeather.put(311, 0);
      mapWeather.put(312, 0);
      mapWeather.put(313, 0);
      mapWeather.put(314, 0);
      mapWeather.put(321, 0);
      
      /* rain, 500-531: 0 */
      mapWeather.put(500, 0);
      mapWeather.put(501, 0);
      mapWeather.put(502, 0);
      mapWeather.put(503, 0);
      mapWeather.put(504, 0);
      mapWeather.put(511, 0);
      mapWeather.put(520, 0);
      mapWeather.put(521, 0);
      mapWeather.put(522, 0);
      mapWeather.put(531, 0);
      
      /* snow, 600-622: 0 */
      mapWeather.put(600, 0);
      mapWeather.put(601, 0);
      mapWeather.put(602, 0);
      mapWeather.put(611, 0);
      mapWeather.put(612, 0);
      mapWeather.put(613, 0);
      mapWeather.put(615, 0);
      mapWeather.put(616, 0);
      mapWeather.put(620, 0);
      mapWeather.put(621, 0);
      mapWeather.put(622, 0);
      
      /* atmosphere, 701-781: 1 */
      mapWeather.put(701, 1);
      mapWeather.put(711, 1);
      mapWeather.put(721, 1);
      mapWeather.put(731, 1);
      mapWeather.put(741, 1);
      mapWeather.put(751, 1);
      mapWeather.put(761, 1);
      mapWeather.put(762, 1);
      mapWeather.put(771, 1);
      mapWeather.put(781, 1);
      
      /* clear, 800: 2 */
      mapWeather.put(800, 2);
      
      /* clouds, 801-804: 3 */
      mapWeather.put(801, 3);
      mapWeather.put(802, 3);
      mapWeather.put(803, 3);
      mapWeather.put(804, 3);
      
      /* strongness (0(clear), 1-3(1:light, 2:normal, 3:heavy)(the other))*/
      mapStrong = new HashMap<>();
      /* thunderstorm: 200-232 */
      mapStrong.put(200, 1);
      mapStrong.put(201, 2);
      mapStrong.put(202, 3);
      mapStrong.put(210, 1);
      mapStrong.put(211, 2);
      mapStrong.put(212, 3);
      mapStrong.put(221, 2);
      mapStrong.put(230, 1);
      mapStrong.put(231, 2);
      mapStrong.put(232, 3);
     
      /* drizzle: 300-321 */
      mapStrong.put(300, 1);
      mapStrong.put(301, 2);
      mapStrong.put(302, 3);
      mapStrong.put(310, 1);
      mapStrong.put(311, 2);
      mapStrong.put(312, 3);
      mapStrong.put(313, 2);
      mapStrong.put(314, 3);
      mapStrong.put(321, 2);
      
      /* rain: 500-531 */
      mapStrong.put(500, 1);
      mapStrong.put(501, 2);
      mapStrong.put(502, 3);
      mapStrong.put(503, 3);
      mapStrong.put(504, 3);
      mapStrong.put(511, 2);
      mapStrong.put(520, 1);
      mapStrong.put(521, 2);
      mapStrong.put(522, 3);
      mapStrong.put(531, 2);
      
      /* snow: 600-622 */
      mapStrong.put(600, 1);
      mapStrong.put(601, 2);
      mapStrong.put(602, 3);
      mapStrong.put(611, 2);
      mapStrong.put(612, 1);
      mapStrong.put(613, 2);
      mapStrong.put(615, 1);
      mapStrong.put(616, 2);
      mapStrong.put(620, 1);
      mapStrong.put(621, 2);
      mapStrong.put(622, 3);
      
      /* atmosphere: 701-781 */
      mapStrong.put(701, 2);
      mapStrong.put(711, 2);
      mapStrong.put(721, 2);
      mapStrong.put(731, 2);
      mapStrong.put(741, 2);
      mapStrong.put(751, 2);
      mapStrong.put(761, 2);
      mapStrong.put(762, 3);
      mapStrong.put(771, 3);
      mapStrong.put(781, 3);
      
      /* clear, 800: 0 */
      mapStrong.put(800, 0);
      
      /* clouds: 801-804 */
      mapStrong.put(801, 1);
      mapStrong.put(802, 1);
      mapStrong.put(803, 2);
      mapStrong.put(804, 3);
  }
  
  public int getAPI(){
    int weather=0; 
    URL url;
    
    try {     
      String requestURL = "http://api.openweathermap.org/data/2.5/forecast?q=Osaka-shi,jp&mode=json&cnt=3&appid=a43466d208700403739dc72d4886c0f4";
      url = new URL(requestURL);
      InputStream is = url.openConnection().getInputStream();
  
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      StringBuilder sb = new StringBuilder();
      String line;
      while (null != (line = reader.readLine())) {
          JSONObject jsonAll = parent.loadJSONObject(requestURL);
          JSONArray listArray = jsonAll.getJSONArray("list");
          JSONObject obj = listArray.getJSONObject(2);
          String cityName = obj.getString("name");
          JSONObject mainObj = obj.getJSONObject("main"); //<>//
          int humidity = mainObj.getInt("humidity");
          long time = obj.getLong("dt");
          JSONArray weatherArray = obj.getJSONArray("weather");
          JSONObject weatherObj = weatherArray.getJSONObject(0);
          String main = weatherObj.getString("main");
          //System.out.println("main: " + main);
          int id = weatherObj.getInt("id");
          //System.out.println("id: " + id);
          String description = weatherObj.getString("description");
          //System.out.println("description: "+description);
          weatherId = id;
      }
      reader.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    return weather;
  }
  
  public int getWeather(){
    return mapWeather.get(weatherId);
  }
  public int getStrong(){
    return mapStrong.get(weatherId);
  }
}
