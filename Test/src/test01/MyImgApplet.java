package test01;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MyImgApplet extends JApplet {
    private Image img;
    @Override
    public void init(){
        try {
            img = getImage(new URL("https://img2.baidu.com/it/u=3202947311,1179654885&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500"));
        } catch (MalformedURLException e) { /* omitted for brevity */ }
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}

