import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

//enum 
 
public class Voice {
  PApplet parent;
  Minim minim;  //Minim型変数であるminimの宣言
  AudioPlayer player;  //サウンドデータ格納用の変数
  static String[] mp3_name = {"hello2.wav","haveaniceday.wav","gomi_canbin2.wav", "gomi_fire2.wav", "gomi_kogata2.wav", "gomi_nonfire2.wav", "gomi_plastic2.wav"};
  int status = 0;
  int gomi_number = 0;
  
  public Voice(PApplet p){
    parent = p;
    this.minim = new Minim(p);  //初期化
    this.player = this.minim.loadFile(mp3_name[0]);
    gomi_number = (int)parent.random(0,5);
  }
  
  private void select_play(int index){
    //index 0 1 2 3 4 5
    
    this.player = this.minim.loadFile(mp3_name[index]);
    this.player.play();
  }
  
  public void play_start(){
    if(!this.player.isPlaying()){
       if(status==0){
         this.select_play(0);
         status++;
       }else if(status==1){
         this.select_play(2+gomi_number);
         status++;
       }
    }
  }
  
  public void play_end(){
    if(status>0){
      this.select_play(1);
      status=0;
    }
  }
  
  
  public void stop(){
    this.player.close();  //サウンドデータを終了
    this.minim.stop();
  }
}
