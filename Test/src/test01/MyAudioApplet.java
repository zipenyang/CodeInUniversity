package test01;

import javax.swing.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAudioApplet extends JApplet {
    private AudioClip audioClip;
    @Override
    public void init(){
        try {
            audioClip = getAudioClip(new URL("https://music.163.com/#/artist?id=2116"));
        } catch (MalformedURLException e) { /* omitted for brevity */ }
    }
    @Override
    public void start() {
        audioClip.play();
    }
    @Override
    public void stop(){
        audioClip.stop();
    }
}
