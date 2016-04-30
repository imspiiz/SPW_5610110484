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
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private SpaceShip v;
    private Health hp;
    private Timer timer;   
    private long score = 0;
    private int status = 0;
    private int level = 1;
    private int speed = 50;
    private double difficulty;
    private boolean genItem = true; 
    
    public GEngine(GPanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;      
        gp.sprites.add(v);
        initHP();
        timer = new Timer(speed, new ActionListener() {
            
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
    // ---------------- Setup Generate --------------- //
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

    private void generateBullet(){
        Bullet b = new Bullet(v.x + (v.width / 2) - 2, v.y);
        gp.sprites.add(b);
        bullets.add(b);
    }

    public void start(){
        timer.start();
    }

    private void process(){

        if(hp.getHP() == 0){
            gameLose();
        }

        scoreProcess();
        bulletProcess();


    // ---------------- Generate --------------- //
        if(genItem){
            if(Math.random() < difficulty){
                generateEnemy();
            }
            if(Math.random() * 400 < 2){
                generateItem();
            }   
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
        Rectangle2D.Double obj_bullet;

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

        for(Bullet b : bullets){
            for(Enemy e : enemies){
                obj_bullet = b.getRectangle();
                obj_e = e.getRectangle();
                if(obj_bullet.intersects(obj_e)){
                    b.shoot();
                    e.dead();
                    b.isShoot();
                    return;
                }
            }
        }



        gp.updateGameUI(this, status);
    }

    private void scoreProcess(){
        if(score >= 100000){
            gameWin();
        }
        if(score < 10000){
            level = 1;
            difficulty = 0.2;
            speed = 45;
        } 
        else if(score < 25000){
            level = 2;
            difficulty = 0.4;
            speed = 40;
        } 
        else if(score < 45000){
            level = 3;
            difficulty = 0.6;
            speed = 35;
        } 
        else if(score < 70000){
            level = 4;
            difficulty = 0.8;
            speed = 30;
        } 
        else if(score < 100000){
            level = 5;
            difficulty = 1.0;
            speed = 25;
        } 
    }

    private void bulletProcess(){
        Iterator<Bullet> b_iter = bullets.iterator();
        while(b_iter.hasNext()){
            Bullet b = b_iter.next();
            b.bMove();
            
            if(!b.isShoot()){
                b_iter.remove();
                gp.sprites.remove(b);
            }
        }
    }

    public void hit(){
        hp.dec_hp();
    }

    public void buf(){
        hp.inc_hp();
    }

    public void gameWin(){
        status = 1;
        genItem = false;
        timer.stop();   
    }

    public void gameLose(){
        status = 2;
        genItem = false;
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
            case KeyEvent.VK_SPACE:
                generateBullet();
                break;
         }
    } 

    public long getScore(){
        return score;
    }

    public int getLevel(){
        return level;
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


