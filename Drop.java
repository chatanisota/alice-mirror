import processing.core.PApplet;
import processing.core.*;


class Drop{
  PApplet parent;
  float drop_x;
  float pool_a;
  float pool_x,pool_y;
  float drop_y;
  float ground_x;
  float drop_n;
  float drop_v;
  float drop_c;
  //int[] drop_colors = {parent.color(192, 224, 224), parent.color(160, 224, 224), parent.color(128, 192, 224), parent.color(96, 160, 192), parent.color(96, 192, 192), parent.color(64, 160, 192)};
  int drop_colors[][] = {{192, 224, 224}, {160, 224, 224}, {128, 192, 224}, {96, 160, 192}, {96, 192, 192}, {64, 160, 192}};
  int col_n;
  
  
  public Drop(PApplet p){
    parent = p;
    
    pool_a = 100;
    drop_x = 0;
    drop_y = parent.random(0, parent.height);
    ground_x = parent.random(parent.width / 8 * 7, parent.width);
    drop_n = parent.map(ground_x, parent.width/8*7, parent.width, 6, 9);
    drop_v = parent.random(10, 20);
    col_n = (int)parent.random(0, 5);
    drop_c = parent.color(drop_colors[col_n][0], drop_colors[col_n][1], drop_colors[col_n][2]);
    //parent.println(drop_c);
  }
  
  public int draw(){
    parent.noStroke();
    if(drop_x <= ground_x){
      parent.fill(drop_colors[col_n][0], drop_colors[col_n][1], drop_colors[col_n][2], 70);
      draw_drop(drop_x, drop_y, drop_n);
      drop_x += drop_v;
    } else if (drop_x > ground_x && pool_a > 0) {
      pool_x = drop_x;
      pool_y = drop_y;
      draw_pool(pool_x, pool_y, pool_a);
      pool_a -= 1;
    } else {
      return -1;
    }
    
    return 0; 
  }
  
  private void draw_drop(float drop_x, float drop_y, float drop_n) {
  
    for (int i = 0; i < drop_n*10; i += 5) {
      float circle_x = drop_x + i;
      float circle_y = drop_y; 
      float circle_d = i;
      parent.ellipse(circle_x, circle_y, circle_d, circle_d);
    }
  }
  
  private void draw_pool(float pool_x, float pool_y, float pool_a) {
    float dx = parent.map(ground_x, parent.width/8 * 7, parent.width, 70, 150);
    parent.fill(drop_colors[col_n][0], drop_colors[col_n][1], drop_colors[col_n][2], pool_a);
    parent.ellipse(pool_x, pool_y, dx, dx * 3);
    
  }
  
}
