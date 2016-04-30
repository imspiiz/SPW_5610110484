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
    private ArrayList<BossBullet> bossbullets = new ArrayList<BossBullet>();

    private SpaceShip v;
    private Health hp;
    private BossHealth bhp;
    private Boss _boss;
    private Timer timer;   
    private long score = 0;
    private int status = 0;
    private int level = 1;
    private int speed = 50;
    private double difficulty;
    private boolean genItem = true; 
    private boolean bossBorn = false;
    
    public GEngine(GPanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;      
        gp.sprites.add(v);
        _boss = new Boss(100, 40);
        initHP();
        initBHP();
        timer = new Timer(speed, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                process();
                bossProcess();
            }
        });
        timer.setRepeats(true);     
    }
    private void initHP(){
        hp = new Health(200);
        gp.sprites.add(hp);
    }
    private void initBHP(){
        bhp = new BossHealth(200);
    }
// ---------------- Setup Generate --------------- //
    private void generateEnemy(){
        Enemy e = new Enemy((int)(Math.random() * 400), 10);
        gp.sprites.add(e);
        enemies.add(e);
    }

    private void generateItem(){
        Item itm = new Item((int)(Math.random() * 400), 25);
        gp.sprites.add(itm);
        items.add(itm);
    }

    private void generateBullet(){
        Bullet b = new Bullet(v.x + (v.width / 2), v.y);
        gp.sprites.add(b);
        bullets.add(b);
    }

    private void generateBossBullet(){
        BossBullet bb = new BossBullet(_boss.x + (_boss.width / 2), _boss.y+80);
        gp.sprites.add(bb);
        bossbullets.add(bb);
    }

    private void generateBoss(){
        _boss = new Boss(100, 25);
        gp.sprites.add(_boss);
    }

    public void start(){
        timer.start();
    }
// ---------------- Process --------------- //
    private void process(){

        if(hp.getHP() == 0){
            gameLose();
        }
        if(bhp.getHP() <= 0){
            gameWin();
        }

        scoreProcess();
        bulletProcess();
        bossBulletProcess();

    // ---------------- Generate --------------- //
        if(genItem){
            if(!(level == 6)){
                if(Math.random() < difficulty){
                    generateEnemy();
                }  
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
                score += 100;
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
        Rectangle2D.Double obj_bb;

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
                    score += 50;
                    return;
                }
            }
        }
        if(bossBorn){
            Rectangle2D.Double obj_boss = _boss.getRectangle();
            for(Bullet b : bullets){
                obj_bullet = b.getRectangle();
                if(obj_bullet.intersects(obj_boss)){
                    bhp.dec_hp();
                    b.shoot();
                    score += 100;
                    return;
                  }  
             }
            for(BossBullet bb : bossbullets){
                obj_bb = bb.getRectangle();
                if(obj_bb.intersects(obj_v)){
                    bb.shoot();
                    hit();
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
            speed = 48;
        } 
        else if(score < 25000){
            level = 2;
            difficulty = 0.3;
            speed = 46;
        } 
        else if(score < 45000){
            level = 3;
            difficulty = 0.4;
            speed = 44;
        } 
        else if(score < 70000){
            level = 4;
            difficulty = 0.5;
            speed = 42;
        } 
        else if(score < 100000){
            level = 5;
            difficulty = 0.6;
            speed = 40;
        } 
        else{
            level = 6;
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

    private void bossBulletProcess(){
        Iterator<BossBullet> bb_iter = bossbullets.iterator();
        while(bb_iter.hasNext()){
            BossBullet bb = bb_iter.next();
            bb.bbMove();
            
            if(!bb.isShoot()){
                bb_iter.remove();
                gp.sprites.remove(bb);
            }
        }
    }

    public void checkBoss(){
        if(level == 6){
            bossBorn = true;
        }
    }
    private void bossProcess(){
        checkBoss();
        if(bossBorn){
            gp.sprites.add(_boss);
            gp.sprites.add(bhp);
            _boss.bossMove();
            if(_boss.x % 10 == 0)
                generateBossBullet();
            if(!_boss.isAlive()){
                bossBorn = false;
                score += 5000;
            }

        }else {
            gp.sprites.remove(_boss);
        }
    }

    public void hit(){
        hp.dec_hp();
    }

    public void buf(){
        hp.inc_hp();
    }

    public boolean bossBorn(){
        return bossBorn;
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


