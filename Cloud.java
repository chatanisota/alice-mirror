import processing.core.PApplet;
import processing.core.PShape;

class Cloud{
  float cloud_x;
  float cloud_y;
  float cloud_v;
  PApplet parent;
  PShape cloud;

  private static String svgNames[] = {
    "cloud3.svg",
    "cloud4.svg",
    "cloud5.svg",
    "cloud10.svg",
  };
  
  public Cloud(PApplet p){
     parent = p;
     cloud_x = parent.random(0, parent.width);
     cloud_y = -800;
     cloud_v = parent.random(5, 11);
     cloud = new PShape();
     cloud = parent.loadShape(svgNames[(int)parent.random(0,100) % svgNames.length]);
  }
  
  public int draw(){
    parent.shape(cloud, cloud_x, cloud_y); 
  
    cloud_y += cloud_v;
    
    if (cloud_y >= 1200) {
      cloud_y = -1200;
      cloud_x = parent.random(0, parent.width);
      return -1;
    }
    return 0;
  }
  
  
}
