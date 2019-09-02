import processing.core.PApplet;
import processing.video.*;
import gab.opencv.*;  //ライブラリをインポート
import java.awt.Rectangle;


public class WebCamera {
  
  //Please check and select a number of connected camera by printCameraList() 18:WebCamera
  private static final int CAMERA_NUMBER = 1;
  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;
  
  PApplet parent;
  
  Capture video;
  OpenCV cv;

	public WebCamera(PApplet p){
    this.parent = p;	
      
	}

  public void setup(){
    String[] cams = Capture.list();
    
    if(CAMERA_NUMBER >= cams.length){
      this.parent.println("Sorry. CAMERA_NUMBER's camera is not avairable.");
      this.parent.exit();
    }
    this.parent.println("We use this camera:\n"+cams[CAMERA_NUMBER]);
    this.video = new Capture(this.parent, WIDTH, HEIGHT, cams[CAMERA_NUMBER]);
    this.cv = new OpenCV(this.parent, WIDTH, HEIGHT);
    this.cv.loadCascade(OpenCV.CASCADE_FRONTALFACE);
    parent.print(OpenCV.CASCADE_FRONTALFACE);
    this.video.start();   
  }

  
  public void printCameralist(){
    String[] cams = Capture.list();
    for(int i=0; i < cams.length; i++){
      this.parent.println("\n\n<"+i+">-----------------------------------------\n"+cams[i]);
    }
  }
  
  public void draw(){
    cv.loadImage(video);
    this.parent.image(video, 0, 0 );
    
    Rectangle[] faces = this.cv.detect();
    
    for(int i=0; i<faces.length; i++){
      this.parent.stroke(255,0,0);
      //this.parent.noFill();
      this.parent.fill(255,0,0,100);
      this.parent.rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
    }  
  }
  
  public boolean isFace(){
    cv.loadImage(video);
    
    //this.parent.image(video, 0, 0);
    
    Rectangle[] faces = this.cv.detect();
    
    if(faces.length > 0){
      parent.println("FACE!");
    }else{
      parent.println("no face...");
    }
    
    return faces.length > 0;
  }
  
  
}
