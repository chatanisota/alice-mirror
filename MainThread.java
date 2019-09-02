import processing.core.PApplet;



public class MainThread extends Thread{ 
  
    static boolean singleton = false;
    static PApplet parent;
    static WebCamera webCamera;
    static WeatherAPI weatherAPI;
    static DarkCurtain darkCurtain;
    MainThread subthread;
    static Voice voice;
    int onFace = 0;
    int PLAY_NUM = 2;  //Renzoku kaoninshiki
    int FRAME_PER_DETECT = 25;
    int weather;
    int strength;
    Clouds clouds;
    Drops drops;
    Sun sun;
    
    public MainThread(PApplet p){
      
      if(singleton==false){
        parent = p;
        
        // GET WEATHER
        weatherAPI = new WeatherAPI(parent);
        weatherAPI.setup();
        weatherAPI.getAPI();
        weather = weatherAPI.getWeather();
        strength = weatherAPI.getStrong();
        parent.println("WEATHER: "+weather);
        parent.println("STRENGTH: "+strength);

        // SET EFFECT
        if(weather==3){
          clouds = new Clouds(parent);
          clouds.setup(strength);
        }else if(weather==0){
          drops = new Drops(parent);
          drops.setup(strength);
        }else if(weather==2){
          sun = new Sun(parent);
          sun.setup();
        }else{
          drops = new Drops(parent);
          drops.setup(strength);
        }
        voice = new Voice(parent);
        darkCurtain = new DarkCurtain(parent);
        
        singleton = true;
      }else{      
        webCamera = new WebCamera(parent);
        webCamera.setup();
      }
    }
    
  
    public void setup() { 
      subthread = new MainThread(parent);
      subthread.start();    
    }
    
    public void draw(){
      
      if(weather==3){clouds.draw();}
      else if(weather==2){sun.draw();}
      else{drops.draw();}
      darkCurtain.draw();    
    }
    
    //Detect from Camera
    @Override
    public void run(){
      
      try{
        while(true){
          parent.print("subthread");
          
          if(webCamera.isFace()){
            //face is detected
            if(onFace < PLAY_NUM){onFace++;} 
          }else{
            if(onFace > 0){onFace--;}
          }
                   
          //
          if(onFace >= PLAY_NUM){
             darkCurtain.unsetBlack();
             voice.play_start();
          }else if(onFace<=0){
             darkCurtain.setBlack();
             voice.play_end();
          }
          Thread.sleep(5);
          
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    
    public void stop_processing(){
      voice.stop();
    }
}
