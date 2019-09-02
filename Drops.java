import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Drops{
  
  private List<Drop> drops;
  private PApplet parent;
  private int strength;
  
  public Drops(PApplet p){
    parent = p;
  }

  public void setup(int s) {
    
    parent.background(0);
    parent.smooth();
    strength = s;
    drops = new ArrayList<Drop>();
  }
  
  public void draw() {
    parent.background(0);
    
    if(parent.frameCount % 48
    /strength == 0){
      drops.add(new Drop(parent));
    }
    
    Iterator<Drop> iterator = drops.iterator();
    while(iterator.hasNext()) {
      Drop drop = iterator.next();
      if(-1 == drop.draw()){
        iterator.remove();
      }
    }
  }
}
