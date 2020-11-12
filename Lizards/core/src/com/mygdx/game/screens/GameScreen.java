package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.World;
import com.mygdx.game.controller.AuberController;

public class GameScreen implements Screen {
    private World world;
    private AuberController auberController;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //notification label
    private Timer timer;
    private Timer.Task task;
    private Label.LabelStyle notify_style;
    private BitmapFont my_font;
    private Label notify_label;
    private Stage stage;
    private Skin skin;

    //IMAGES
    private Texture bucketImage; //Auber
    private Texture infiltratorImage; //Infiltrator
    private Texture systemImage; //System
    private Texture healthImg; //System Health Bar
    private Texture blockImage; //Wall

    
    private boolean isRoom1 = true;
    private boolean isRoom2, isRoom3, isRoom4 = false;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world);
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage = new Texture(Gdx.files.internal("systemsImage.jpg"));
        healthImg = new Texture(Gdx.files.internal("health.png"));
        blockImage = new Texture(Gdx.files.internal("block.png"));

        //NOTIFICATION LABEL
        timer= new Timer();
        notify_style=new Label.LabelStyle();
        my_font= new BitmapFont(Gdx.files.internal("my_font.fnt"));
        stage=new Stage();
        skin=new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        notify_style.font=my_font;
        notify_style.background= skin.getDrawable("button");
        notify_label= new Label("NOTIFY",notify_style);
        notify_label.setSize(600,100);
        notify_label.setPosition(100,500);
        notify_label.setAlignment(Align.center);
        stage.addActor(notify_label);



        //INFILTRATOR IMAGE - Brian
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));



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
        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());



        // render systems and health bars if they arent destroyed
        for (int x=0;x<world.getSystems().size; x++){
            if (!world.getSystems().get(x).isDestroyed()) { //systems
                batch.draw(systemImage,world.getSystems().get(x).getX(),world.getSystems().get(x).getY());

                //render health bars
                if (world.getSystems().get(x).getHealth()>=70){ batch.setColor(Color.GREEN); }
                else if (world.getSystems().get(x).getHealth()>=40){batch.setColor(Color.ORANGE);}
                else {batch.setColor(Color.RED);}
                batch.draw(healthImg,world.getSystemhealthbars().get(x).getX(),world.getSystemhealthbars().get(x).getY());
                batch.setColor(Color.WHITE);
            }

            // render notifications 
            if(world.getSystems().get(x).notifyPlayer()==true){ //show notification
                notify_label.setText("System " + x + " is being sabotaged!");
                stage.draw();
                task= new Timer.Task() {
                    @Override
                    public void run() {
                        stage.clear();
                    }
                };
                timer.scheduleTask(task,5); //clear after 5 seconds
            }

        }


        for (int i = 0; i < world.getBlocks().size; i++) {
            batch.draw(blockImage, world.getBlocks().get(i).getX(), world.getBlocks().get(i).getY());
        }
		




        //INFILTRATOR - Brian
        batch.draw(infiltratorImage, world.getInfiltrator().getX(),world.getInfiltrator().getY());
        batch.end();
        world.updateInfiltratorLocationX();
        world.updateInfiltratorLocationY();
        updateAuberLocation();
        updateCameraRoomLocation();
        camera.update();
    }

    private void updateAuberLocation() {
        if (auberController.isUpPressed()) {
            if (!(world.getAuber().getY() >= 1100)) {
                if (!(auberController.checkUpBlockCollission(10))) {
                    world.getAuber().addY(10);
                }
            }
        } else if (auberController.isDownPressed()) {
            if (!(world.getAuber().getY() <= 0)) {
                world.getAuber().addY(-10);
            }
        } else if (auberController.isLeftPressed()) {
            if (!(world.getAuber().getX() <= 0)) {
                world.getAuber().addX(-10);
            }
        } else if (auberController.isRightPressed()) {
            if (!(world.getAuber().getX() >= 2300)) {
                world.getAuber().addX(10);
            }
        }
        
        //TESTING CODE - Use for tracing Auber's Location
        System.out.println(world.getAuber().getX() + " " + world.getAuber().getY());
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
