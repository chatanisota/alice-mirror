import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Clouds{
  
  private List<Cloud> clouds;
  private PApplet parent;
  private int strength;
  
  public Clouds(PApplet p){
    parent = p;
  }

  public void setup(int s) {
    //
    parent.background(0);
    parent.smooth();
    strength = s;
    clouds = new ArrayList<Cloud>();
  }
  
  public void draw() {
    parent.background(0);
    
    if(parent.frameCount % 15*strength == 0){
      clouds.add(new Cloud(parent));
    }
    
    Iterator<Cloud> iterator = clouds.iterator();
    while(iterator.hasNext()) {
      Cloud cloud = iterator.next();
      if(-1 == cloud.draw()){
        iterator.remove();
      }
    }
  }
}
