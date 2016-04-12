import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GEngine implements KeyListener{
    
    GPanel gp;
    private SpaceShip v;
    private Timer timer;    
    
    public GEngine(GPanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;      

        timer = new Timer(50, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                process();
            }
        });
        timer.setRepeats(true);     
    }

    public void start(){
        timer.start();
    }

    private void process(){
        gp.updateGameUI();
    }

    void controlVehicle(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) { 
            case KeyEvent.VK_UP:
                v.moveY(-1);
                break;
            case KeyEvent.VK_DOWN:
                v.moveY(1);
                break;
            case KeyEvent.VK_LEFT:
                v.moveX(-1);
                break;
            case KeyEvent.VK_RIGHT :
                v.moveX(1);
                break;
         }
    } 

    @Override
    public void keyPressed(KeyEvent e) {
        controlVehicle(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing        
    }


}


