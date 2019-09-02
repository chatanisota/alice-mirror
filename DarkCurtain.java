import processing.core.PApplet;


class DarkCurtain{
   private PApplet parent;
   private int alpha = 0;
   private int alphaMAX;
   private int DELTA = 20;
   private boolean isBlack = true;
   
   public DarkCurtain(PApplet p){
     parent = p;
     alphaMAX = 255;
     alpha = alphaMAX;
   }
   
   public void setBlack(){
     //parent.print("setBlack");
     if(isBlack==false){
       alpha = 0;
       isBlack = true;
     }
   }
   
   public void unsetBlack(){
     //parent.print("unsetBlack");
     if(isBlack==true){
       alpha = 255;
       isBlack = false;
     }
   }
   
   public int draw(){
      
      if(isBlack==true && alpha<alphaMAX){
        alpha += DELTA;
        if(alpha>alphaMAX){ alpha = alphaMAX; }
      }else if(isBlack==false && alpha>0){
        alpha -= DELTA;
        if(alpha<0){ alpha = 0; }
      }
      //parent.println("DARK ALPHA: "+alpha);
      parent.noStroke();
      parent.fill(0, alpha);
      parent.rect(0, 0, parent.width, parent.height);
      return 0;
   } 
}
