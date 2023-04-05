package test01;

import javax.swing.*;
import java.awt.*;

public class MyApplet extends JApplet {
    private String str = "YangZiPeng";
    @Override
    public void init() {
        setBackground(Color.BLUE);
    }
    @Override
    public void destroy() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(1,1,330,150);
        g.setColor(Color.blue);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
        g.drawString(str, 10, 80);
    }
}

