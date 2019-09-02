import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

import gab.opencv.*;  //ライブラリをインポート
import processing.video.*;
import java.awt.*;

MainThread mainThread;



void setup() {
  fullScreen(2);
  mainThread = new MainThread(this); 
  mainThread.setup(); 
}
 
void draw() {
  mainThread.draw();
}

void stop(){
  mainThread.stop_processing();
  super.stop();
}

void captureEvent(Capture c) {
  c.read();
}
