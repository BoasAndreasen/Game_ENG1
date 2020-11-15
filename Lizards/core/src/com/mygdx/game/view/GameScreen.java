package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controller.HostileController;
import com.mygdx.game.model.World;
import com.mygdx.game.controller.AuberController;

import java.awt.*;

public class GameScreen implements Screen {
    private World world;
    private AuberController auberController;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    // Notification label
    private Timer timer;
    private Timer.Task task;
    private Label.LabelStyle notify_style;
    private BitmapFont my_font;
    private Label notify_label;
    private Stage stage;
    private Skin skin;



    // Images
    private Texture bucketImage; //Auber
    private Texture infiltratorImage; //Infiltrator
    private Texture systemImage; //System
    private Texture healthImg; //System/Auber Health Bar
    private Texture horizWallImage; //Wall
    private Texture vertiWallImage; //Wall
    private Texture teleportPadImage; // TeleportPad
    private Texture bombImage; //bomb
    private Texture shieldImage; //shield
    private Texture corruptImage; //corrupt
    private Texture healPadImage; // Healing Pad
    private Texture teleportPadMapImage; // TeleportPad Map



    // Camera location
    private boolean isRoom1 = true;
    private boolean isRoom2, isRoom3, isRoom4 = false;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world);

        //TEXTURES
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage = new Texture(Gdx.files.internal("System.png"));
        healthImg = new Texture(Gdx.files.internal("health.png"));
        horizWallImage = new Texture(Gdx.files.internal("HorizontalWall.png"));
        vertiWallImage = new Texture(Gdx.files.internal("VerticalWall.png"));
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));
        teleportPadImage = new Texture(Gdx.files.internal("TeleportPad.png"));
        teleportPadMapImage = new Texture(Gdx.files.internal("map.png"));
        bombImage= new Texture(Gdx.files.internal("bomb2.jpg"));
        shieldImage= new Texture(Gdx.files.internal("shield.png"));
        corruptImage= new Texture(Gdx.files.internal("corrupt.jpg"));
        healPadImage = new Texture(Gdx.files.internal("HealPad.png"));

        
        //NOTIFICATION LABEL
        timer = new Timer();
        notify_style = new Label.LabelStyle();
        my_font = new BitmapFont(Gdx.files.internal("my_font.fnt"));
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        notify_style.font = my_font;
        notify_style.background = skin.getDrawable("button");
        notify_label = new Label("NOTIFY",notify_style);
        notify_label.setSize(900,100);
        notify_label.setAlignment(Align.left);
        if ( (world.getAuber().getX()<1200) && (world.getAuber().getY()<600)){
            notify_label.setPosition(0,0);
        }
        stage.addActor(notify_label);




        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 600);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(auberController);
    }

    // Game loop. Update game logic and draw onto the screen.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        //HEALPAD RENDER
        batch.draw(healPadImage, world.getHealingPad().getX(), world.getHealingPad().getY());

        //TELEPAD RENDER
        for (int i = 0; i < world.getTelePads().size; i++) {
            batch.draw(teleportPadImage, world.getTelePads().get(i).getX(), world.getTelePads().get(i).getY());
        }

       //SYSTEMS RENDER
        for (int x = 0; x < world.getSystems().size; x++) {
            if (world.getSystems().get(x).isDestroyed()==false){
                batch.draw(systemImage, world.getSystems().get(x).getX(), world.getSystems().get(x).getY());
            }

        }

        //SYSTEM HEALTHBAR RENDER
        for (int i = 0; i < world.getSystems().size; i++){
            if (world.getSystems().get(i).isDestroyed()==false){
                if (world.getSystems().get(i).getHealth()>=70){ batch.setColor(Color.GREEN); }
                else if (world.getSystems().get(i).getHealth()>=40){batch.setColor(Color.ORANGE);}
                else {batch.setColor(Color.RED);}
                if (i==3) {
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-320);
                    my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX()+20,world.getSystems().get(i).getY()+140);
                }
                if ((i==2)|| (i==9) ||(i==4)||(i==11)||(i==13)){
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-220);
                    if (i==13){
                        my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-30);
                    }
                    else{
                        if (i==4){
                            my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX(),world.getSystems().get(i).getY()+140);
                        }
                        else{
                            my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-20);
                        }

                    }

                }
                else{
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-100);
                    if (i==1){
                        my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX()+20,world.getSystems().get(i).getY()+140);
                    }
                    else{
                        if (i==10){
                            my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX()+30,world.getSystems().get(i).getY()-30);
                        }
                        else{
                            if (i==14){
                                my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX()-30,world.getSystems().get(i).getY()+150);
                            }
                            else{
                                if (i==5){
                                    my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX()-30,world.getSystems().get(i).getY()+150);
                                }
                                else{
                                    if((i==0)||(i==6)||(i==7)||(i==8)||(i==12)){
                                        my_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-30);
                                    }

                                }
                            }

                        }

                    }


                }


            }
            batch.setColor(Color.WHITE);
        }

        //WALLS RENDER
        for (int i = 0; i < world.getHorizWall().size; i++) {
            batch.draw(horizWallImage, world.getHorizWall().get(i).getX(), world.getHorizWall().get(i).getY());
        }
        for (int i = 0; i < world.getVertiWall().size; i++) {
            batch.draw(vertiWallImage, world.getVertiWall().get(i).getX(), world.getVertiWall().get(i).getY());
        }

        //AUBER RENDER
        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());

        //AUBER HEALTH BAR RENDER
        if (world.getAuber().getHealth()>=70){ batch.setColor(Color.GREEN); }
        else if (world.getAuber().getHealth()>=40){batch.setColor(Color.ORANGE);}
        else {batch.setColor(Color.RED);}
        batch.draw(healthImg,world.getAuber().getX(),world.getAuber().getY()-130);
        my_font.draw(batch,String.valueOf(world.getAuber().getHealth()),world.getAuber().getX(),world.getAuber().getY()-10);
        batch.setColor(Color.WHITE);

        //TELEPORTING
        if (auberController.getStandingOnTelePad() && isRoom1) {
            batch.draw(teleportPadMapImage, 120,60);
        } else if (auberController.getStandingOnTelePad() && isRoom3) {
            batch.draw(teleportPadMapImage, 1320,60);
        } else if (auberController.getStandingOnTelePad() && isRoom2) {
            batch.draw(teleportPadMapImage, 120,660);
        } else if (auberController.getStandingOnTelePad() && isRoom4) {
            batch.draw(teleportPadMapImage, 1320,660);
        }

        //HOSTILES RENDER
        for (int a=0;a<world.getInfiltrators().size;a++){
            if (world.getInfiltrators().get(a).isCurrent()==true){
                batch.draw(infiltratorImage, world.getInfiltrators().get(a).getX(),world.getInfiltrators().get(a).getY());
            }
        }

        //HOSTILE ABILITIES
        for (int i=0;i<world.getInfiltrators().size;i++) {
            if (world.getInfiltrators().get(i).isCurrent()==true){
                if (world.getInfiltrators().get(i).getAbility() == "bombs") { //bomb image on hostile
                    batch.draw(bombImage, world.getInfiltrators().get(i).getX() - 30, world.getInfiltrators().get(i).getY());

                }
                if (world.getInfiltrators().get(i).getAbility() == "shield") { //shield image on hostile
                    batch.draw(shieldImage, world.getInfiltrators().get(i).getX() - 30, world.getInfiltrators().get(i).getY());

                }
                if (world.getInfiltrators().get(i).getAbility()=="corrupt"){ //corrupts if close to hostile
                    batch.draw(corruptImage,world.getInfiltrators().get(i).getX()-30,world.getInfiltrators().get(i).getY());
                    if (world.getAuber().closeToHostile(world.getInfiltrators().get(0))){
                        batch.draw(corruptImage,world.getAuber().getX()-30,world.getAuber().getY());
                    }


                }

            }


        }

        batch.end();

        //NOTIFICATION
        for (int i = 0; i < world.getSystems().size; i++){
            if(world.getSystems().get(i).notifyPlayer()){ //show notification
                if (i==0){
                    notify_label.setText("The Armoury System is being sabotaged!");
                }
                if (i==1){
                    notify_label.setText("The Storage System is being sabotaged!");
                }
                if ((i==2)||(i==3)){
                    notify_label.setText("A Control Room System is being sabotaged!");
                }
                if ((i==4)||(i==5)){
                    notify_label.setText("A Cantine System is being sabotaged!");
                }

                if (i==6){
                    notify_label.setText("The Dorms System is being sabotaged!");
                }
                if (i==7){
                    notify_label.setText("A System near the brig is being sabotaged!");
                }
                if ((i==8)||(i==9)){
                    notify_label.setText("An Engine Room System is being sabotaged!");
                }
                if (i==10){
                    notify_label.setText("The Shield Room System is being sabotaged!");
                }
                if ((i==11)||(i==12)){
                    notify_label.setText("A Reactor Room System is being sabotaged!");
                }
                if (i==13){
                    notify_label.setText("The Cargo Bay System is being sabotaged!");
                }
                if (i==14){
                    notify_label.setText("A System near the cargo bay is being sabotaged!");
                }

                stage.draw();
                task= new Timer.Task() {
                    @Override
                    public void run() {
                        stage.clear();
                    }
                };
                timer.scheduleTask(task,3); //clear after 5 seconds
            }}


        //UPDATES

        world.updateInfiltratorLocationX();
        world.updateInfiltratorLocationY();
        auberController.updateAuberLocation();
        updateCameraRoomLocation();
        camera.update();
    }



    private void updateCameraRoomLocation() {
        if (isRoom1 && world.getAuber().getY() >= 600) {
            camera.translate(0,600,0);
            isRoom2 = true;
            isRoom1 = false;
        } if (isRoom1 && world.getAuber().getX() >= 1200) {
            camera.translate(1200,0,0);
            isRoom3 = true;
            isRoom1 = false;
        } if (isRoom2 && world.getAuber().getY() <= 600) {
            camera.translate(0,-600,0);
            isRoom2 = false;
            isRoom1 = true;
        } if (isRoom2 && world.getAuber().getX() >= 1200) {
            camera.translate(1200,0,0);
            isRoom2 = false;
            isRoom4 = true;
        } if (isRoom3 && world.getAuber().getX() <= 1200) {
            camera.translate(-1200,0,0);
            isRoom3 = false;
            isRoom1 = true;
        } if (isRoom3 && world.getAuber().getY() > 600) {
            camera.translate(0,600,0);
            isRoom3 = false;
            isRoom4 = true;
        } if (isRoom4 && world.getAuber().getX() < 1200) {
            camera.translate(-1200,0,0);
            isRoom4 = false;
            isRoom2 = true;
        } if (isRoom4 && world.getAuber().getY() < 600) {
            camera.translate(0,-600,0);
            isRoom4 = false;
            isRoom3 = true;
        }
    }

    // Called once when gate starts and when resized
    public void resize(int width, int height) {
        // TODO
    }

    // Called when game ends, just before dispose. Save game state here.
    public void pause() {
        // TODO
    }

    @Override
    public void resume() {
        // TODO
    }

    // Called when another screen becomes active
    @Override
    public void hide() {
        // TODO
    }

    // Called once when the application exits
    @Override
    public void dispose() {
        //TODO
    }
}
