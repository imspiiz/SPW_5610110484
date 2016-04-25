import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class GEngine implements KeyListener, GReport{
    
    private GPanel gp;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private SpaceShip v;
    private Health hp;
    private Timer timer;   
    private long score = 0;
    private double difficulty = 0.1;
    
    public GEngine(GPanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;      
        gp.sprites.add(v);
        initHP();
        timer = new Timer(50, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                process();
            }
        });
        timer.setRepeats(true);     
    }
    private void initHP(){
        hp = new Health(100);
        gp.sprites.add(hp);
    }
    
    private void generateEnemy(){
        Enemy e = new Enemy((int)(Math.random() * 400), 10);
        gp.sprites.add(e);
        enemies.add(e);
    }

    private void generateItem(){
        Item itm = new Item((int)(Math.random() * 400), 10);
        gp.sprites.add(itm);
        items.add(itm);
    }

    public void start(){
        timer.start();
    }

    private void process(){

        if(hp.getHP() == 0){
            die();
        }

        // ---------------- Generate --------------- //
        if(Math.random() < difficulty){
            generateEnemy();
        }

        if(Math.random() * 400 < 2){
            generateItem();
        }

        // --------------- Iterator --------------- //
        Iterator<Enemy> e_iter = enemies.iterator();
        while(e_iter.hasNext()){
            Enemy e = e_iter.next();
            e.eMove();

            if(!e.isAlive()){
                e_iter.remove();
                gp.sprites.remove(e);
                score += 50;
            }
        }

        Iterator<Item> itm_iter = items.iterator();
        while( itm_iter.hasNext()){
            Item itm = itm_iter.next();
            itm.itmMove();

            if(!itm.isGet()){
                itm_iter.remove();
                gp.sprites.remove(itm);
            }
        }

        // -------------- Intersects -------------- //
        Rectangle2D.Double obj_v = v.getRectangle();
        Rectangle2D.Double obj_e;
        Rectangle2D.Double obj_itm;
        for(Enemy e : enemies){
            obj_e = e.getRectangle();
            if(obj_e.intersects(obj_v)){
                e.dead();
                hit();
                return;
            }
        }

        for(Item itm : items){
            obj_itm = itm.getRectangle();
            if(obj_itm.intersects(obj_v)){
                itm.getItem();
                buf();
                return;
            }
        }

        gp.updateGameUI(this);
    }

    public void hit(){
        hp.dec_hp();
    }

    public void buf(){
        hp.inc_hp();
    }

    public void die(){
        timer.stop();
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
            case KeyEvent.VK_N :
                start();
                initHP();
                break;
         }
    } 

    public long getScore(){
        return score;
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


