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
import com.mygdx.game.model.World;
import com.mygdx.game.controller.AuberController;

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
    private Image bomb;
    private Texture healPadImage; // Healing Pad
    private Texture teleportPadMapImage; // TeleportPad Map

    // Abilities
    private Stage bomb_stage;

    // Camera location
    private boolean isRoom1 = true;
    private boolean isRoom2, isRoom3, isRoom4 = false;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world);
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage = new Texture(Gdx.files.internal("System.png"));
        healthImg = new Texture(Gdx.files.internal("health.png"));
        horizWallImage = new Texture(Gdx.files.internal("HorizontalWall.png"));
        vertiWallImage = new Texture(Gdx.files.internal("VerticalWall.png"));
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));
        teleportPadImage = new Texture(Gdx.files.internal("TeleportPad.png"));
        teleportPadMapImage = new Texture(Gdx.files.internal("map.png"));
        bombImage= new Texture(Gdx.files.internal("bomb2.jpg"));
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

        // render systems and health bars if they arent destroyed
        for (int i = 0; i < world.getSystems().size; i++){
            if (!world.getSystems().get(i).isDestroyed()) { //systems
                batch.draw(systemImage,world.getSystems().get(i).getX(),world.getSystems().get(i).getY());


                    if (world.getSystems().get(i).getHealth()>=70){ batch.setColor(Color.GREEN); }
                    else if (world.getSystems().get(i).getHealth()>=40){batch.setColor(Color.ORANGE);}
                    else {batch.setColor(Color.RED);}
                    if (i==3) {
                        batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-320);
                    }
                    if ((i==2)|| (i==9) ||(i==4)||(i==11)||(i==13)){
                        batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-220);
                    }
                    else{
                        batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY()-100);
                    }

                    batch.setColor(Color.WHITE);


            }
        }


        batch.draw(healPadImage, world.getHealingPad().getX(), world.getHealingPad().getY());

        for (int i = 0; i < world.getTelePads().size; i++) {
            batch.draw(teleportPadImage, world.getTelePads().get(i).getX(), world.getTelePads().get(i).getY());
        }

        for (int x = 0; x < world.getSystems().size; x++) {
            batch.draw(systemImage, world.getSystems().get(x).getX(), world.getSystems().get(x).getY());
        }

        for (int i = 0; i < world.getHorizWall().size; i++) {
            batch.draw(horizWallImage, world.getHorizWall().get(i).getX(), world.getHorizWall().get(i).getY());
        }

        for (int i = 0; i < world.getVertiWall().size; i++) {
            batch.draw(vertiWallImage, world.getVertiWall().get(i).getX(), world.getVertiWall().get(i).getY());
        }

        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());

        if (world.getAuber().getHealth()>=70){ batch.setColor(Color.GREEN); }
        else if (world.getAuber().getHealth()>=40){batch.setColor(Color.ORANGE);}
        else {batch.setColor(Color.RED);}
        batch.draw(healthImg,world.getAuber().getX(),world.getAuber().getY()-130);
        batch.setColor(Color.WHITE);

        if (auberController.getStandingOnTelePad() && isRoom1) {
            batch.draw(teleportPadMapImage, 120,60);
        } else if (auberController.getStandingOnTelePad() && isRoom3) {
            batch.draw(teleportPadMapImage, 1320,60);
        } else if (auberController.getStandingOnTelePad() && isRoom2) {
            batch.draw(teleportPadMapImage, 120,660);
        } else if (auberController.getStandingOnTelePad() && isRoom4) {
            batch.draw(teleportPadMapImage, 1320,660);
        }


        batch.draw(infiltratorImage, world.getInfiltrators().get(0).getX(),world.getInfiltrators().get(0).getY());
        for (int i=0;i<world.getInfiltrators().size;i++){ //just drawing the first hostile for now
            if (world.getInfiltrators().get(i).getAbility()=="bombs"){
                if (i==0){
                    batch.draw(bombImage,world.getInfiltrators().get(i).getX()-30,world.getInfiltrators().get(i).getY());
                }

            }
        }



        batch.end();
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
