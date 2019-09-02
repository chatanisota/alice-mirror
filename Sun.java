import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Sun{
  PApplet parent;
  float a;
  float seed;
  float b;
  float c;
  int kaiten_k;
  float kaiten_r;
  float kaiten_d;
  float wait_frame;
  
  public Sun(PApplet p){
    parent = p;
  }
  
  public void setup() {
    parent.stroke(255, 0, 0);
    parent.strokeWeight(10);
    a = 0;
    seed = 0;
    b = 0;
    c = 0;
    kaiten_k = 240;
    kaiten_r = parent.TWO_PI * kaiten_k/360;
    kaiten_d = 1100;
    wait_frame = 100;
  }
  
  public void draw() {
    parent.background(0);
    
    kaiten_r = parent.TWO_PI * kaiten_k/360;
    float kaiten_x = kaiten_d * parent.sin(kaiten_r);
    float kaiten_y = kaiten_d * parent.cos(kaiten_r);
    float sun_x = parent.width * 3 / 4 + kaiten_x;
    float sun_y = parent.height * 3 / 4 + kaiten_y;
  
    
    //float sun_x = width + width * sin(c);
    //float sun_y = height + 100 + width * cos (c); 
    float sun_r = 200;
    
    parent.stroke(255, 165, 0);
    for(float i = 0; i < parent.PI; i += 0.03) {
      //float line_r = random(0.5, 1.2);
      //float line_r = noise(seed)*1.2 + 0.5;
      float line_r = 1-(float)0.3*parent.abs(parent.sin(i*5+b));
      parent.line(sun_x + sun_r * line_r * parent.cos(i), sun_y + sun_r * line_r * parent.sin(i), sun_x - sun_r * line_r * parent.cos(i), sun_y - sun_r * line_r * parent.sin(i));
      seed += 0.1;
    }
    a += 0.1;
    b += 0.1;
    if (parent.sin(a) <= 0) {
      c += 0.1;
    } 
      
    
    
    parent.fill(255, 110, 15);
    
    //ellipse(sun_x, sun_y, sun_r, sun_r);
    parent.ellipse(sun_x, sun_y, 200, 200);
    
    if (kaiten_k % 360 == 240) {
      wait_frame --;
      if (wait_frame <= 0) {
        wait_frame = 100;
        kaiten_k += 5;
      }
    } else {
      kaiten_k += 5;
    }
  }
}
