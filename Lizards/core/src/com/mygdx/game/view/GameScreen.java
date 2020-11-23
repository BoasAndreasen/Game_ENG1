package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controller.InfiltratorController;
import com.mygdx.game.model.World;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.AuberController;

public class GameScreen implements Screen {
	private final MyGame game;
    private World world;
    private AuberController auberController;
    private InfiltratorController infiltratorController;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    // Notification labels
    private Label notify_label;
    private Stage stage;
    private boolean AllDestroyed;
    private boolean AuberWin;
    private int numberArrested;

   //Health font
    private BitmapFont health_font;

    // Images
    private Texture auberImage; //Auber
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
    private Texture jailImage; //JailImage for the brig

    // Number to slow down game loop
    private int update_num = 0;
    private int shield_num = 0;
    private int infil_render = 0;
    private int infil_count=0;

    // Camera location
    private boolean isRoom1 = true;
    private boolean isRoom2, isRoom3, isRoom4 = false;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world, this);
        infiltratorController = new InfiltratorController(world);
        AuberWin = false;
        infil_count = 3;
        numberArrested=0;

        //TEXTURES
        auberImage = new Texture(Gdx.files.internal("Auber.png"));
        systemImage = new Texture(Gdx.files.internal("System.png"));
        healthImg = new Texture(Gdx.files.internal("health.png"));
        horizWallImage = new Texture(Gdx.files.internal("HorizontalWall.png"));
        vertiWallImage = new Texture(Gdx.files.internal("VerticalWall.png"));
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));
        teleportPadImage = new Texture(Gdx.files.internal("TeleportPad.png"));
        teleportPadMapImage = new Texture(Gdx.files.internal("map.png"));
        bombImage = new Texture(Gdx.files.internal("bomb2.jpg"));
        shieldImage = new Texture(Gdx.files.internal("shield.png"));
        corruptImage = new Texture(Gdx.files.internal("corrupt.jpg"));
        healPadImage = new Texture(Gdx.files.internal("HealPad.png"));
        jailImage = new Texture(Gdx.files.internal("jail.jpg"));

        //NOTIFICATION LABELS
        Label.LabelStyle notify_style = new Label.LabelStyle();
        BitmapFont my_font = new BitmapFont(Gdx.files.internal("my_font.fnt"));
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        notify_style.font = my_font;
        notify_style.background = skin.getDrawable("button");
        notify_label = new Label("NOTIFY", notify_style);
        notify_label.setSize(900,100);
        notify_label.setAlignment(Align.left);
        if (isRoom1|| isRoom2){
            notify_label.setPosition(0,0);
        }
        else{
            notify_label.setPosition(0,600);
        }
        stage.addActor(notify_label);

        health_font = new BitmapFont(Gdx.files.internal("Healthfont.fnt"));
        AllDestroyed = false;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 600);
        batch = new SpriteBatch();

        //InputProcessors
        InputMultiplexer multiplexer = new InputMultiplexer(); //Set up multiplexer to deal with multi input processors
        multiplexer.addProcessor(auberController); //add auberController
        multiplexer.addProcessor(new InputAdapter(){

            public boolean keyDown(int keycode)
            {
                if (keycode == 131) {
                    game.setScreen(new PauseScreen(game));
                }
                return false;
            }
        }); //this is for calling out the PauseScreen

        Gdx.input.setInputProcessor(multiplexer);
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
        if ((auberController.getStandingOnHealPad())&& (isRoom1)) {
            world.getAuber().setHealth(100);
        }

        //TELEPAD RENDER
        for (int i = 0; i < world.getTelePads().size; i++) {
            batch.draw(teleportPadImage, world.getTelePads().get(i).getX(), world.getTelePads().get(i).getY());
        }

       //SYSTEMS RENDER
        for (int x = 0; x < world.getSystems().size; x++) {
            if (!world.getSystems().get(x).isDestroyed()){
                batch.draw(systemImage, world.getSystems().get(x).getX(), world.getSystems().get(x).getY());
            }
            else{
                world.getSystems().removeIndex(x);
            }
        }
        health_font.draw(batch,world.getSystems().size+" systems remaining",900,60);
        health_font.draw(batch,"Press ESC to exit or restart",900,30);

        //SYSTEM HEALTHBAR RENDER
        for (int i = 0; i < world.getSystems().size; i++){
            if (!world.getSystems().get(i).isDestroyed()){
                if (world.getSystems().get(i).getHealth()>=70){ batch.setColor(Color.GREEN); }
                else if (world.getSystems().get(i).getHealth()>=40){batch.setColor(Color.ORANGE);}
                else {batch.setColor(Color.RED);}
                if (i == 3) {
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY() - 320);
                    health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                            world.getSystems().get(i).getX()+20,world.getSystems().get(i).getY() + 140);
                }
                if ((i == 2)|| (i == 9) ||(i == 4)||(i == 11)||(i == 13)){
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY() - 220);
                    if (i == 13){
                        health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                world.getSystems().get(i).getX(),world.getSystems().get(i).getY() - 30);
                    }
                    else{
                        if (i == 4){
                            health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                    world.getSystems().get(i).getX(),world.getSystems().get(i).getY() + 140);
                        }
                        else{
                            health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                    world.getSystems().get(i).getX(),world.getSystems().get(i).getY() - 20);
                        }
                    }
                }
                else{

                    if (i == 1){
                        health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                world.getSystems().get(i).getX()+20,world.getSystems().get(i).getY() + 140);
                    }
                    else{
                        if (i == 10){
                            health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                    world.getSystems().get(i).getX()+30,world.getSystems().get(i).getY() - 30);
                        }
                        else{
                            if (i == 14){
                                health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                        world.getSystems().get(i).getX()-30,world.getSystems().get(i).getY()+150);
                            }
                            else{
                                if (i == 5){
                                    health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                            world.getSystems().get(i).getX()-30,
                                            world.getSystems().get(i).getY()+150);
                                }
                                else{
                                    if((i == 0)||(i == 6)||(i == 7)||(i == 8)||(i == 12)){
                                        health_font.draw(batch,String.valueOf(world.getSystems().get(i).getHealth()),
                                                world.getSystems().get(i).getX(),
                                                world.getSystems().get(i).getY()-20);
                                    }
                                }
                            }
                        }
                    }
                    batch.draw(healthImg,world.getSystems().get(i).getX(),world.getSystems().get(i).getY() - 100);
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

        //JAIL and NUMBER INFILTRATORS ARRESTED
        batch.draw(jailImage,1810,700);
        numberArrested=0;
        for (int a=0;a<world.getInfiltrators().size;a++) {
            if (world.getInfiltrators().get(a).isArrested()){
                numberArrested+=1;
            }
        }
        health_font.draw(batch,numberArrested + "/8 arrested",2100,790);


        //AUBER RENDER
        batch.draw(auberImage, world.getAuber().getX(), world.getAuber().getY());

        //AUBER HEALTH BAR RENDER
        if (world.getAuber().getHealth()>=70){ batch.setColor(Color.GREEN); }
        else if (world.getAuber().getHealth()>=40){batch.setColor(Color.ORANGE);}
        else {batch.setColor(Color.RED);}
        batch.draw(healthImg,world.getAuber().getX(),world.getAuber().getY()-130);
        health_font.draw(batch,String.valueOf(world.getAuber().getHealth()),
                world.getAuber().getX(),world.getAuber().getY()-10);
        batch.setColor(Color.WHITE);

        //HOSTILES RENDER
        //Renders 1 initially , then one every 300 renders
        for (int a = 0; a < world.getInfiltrators().size; a++){
            if (world.getInfiltrators().get(a).isCurrent()){
                batch.draw(infiltratorImage, world.getInfiltrators().get(a).getX(),
                        world.getInfiltrators().get(a).getY());
            }
        }
        while (infil_render > 400){ //renders a new hostile every 700 renders
            while ((infil_count<world.getInfiltrators().size-1) &&
                    (world.getInfiltrators().get(infil_count).isCurrent())){
                infil_count += 1;
            }
            if (!world.getInfiltrators().get(infil_count).isCurrent()){
                world.getInfiltrators().get(infil_count).setCurrent(true);
            }
            infil_render = 0;
        }
        infil_render += 1;

        //SHIELD ABILITY UPDATES
        if ((shield_num > 300)){
            infiltratorController.updateInfiltratorShield();
            this.shield_num = 0;
        }
        shield_num += 1;

        //HOSTILE ABILITIES
        for (int i = 0; i < world.getInfiltrators().size;i++) {
            if (world.getInfiltrators().get(i).isCurrent()){
                if (world.getInfiltrators().get(i).getAbility().equals("bombs")) { //bomb image on hostile
                    batch.draw(bombImage, world.getInfiltrators().get(i).getX() - 30,
                            world.getInfiltrators().get(i).getY());
                }
                if (world.getInfiltrators().get(i).getAbility().equals("shield") &&
                        world.getInfiltrators().get(i).getShieldUp()) {
                    batch.draw(shieldImage, world.getInfiltrators().get(i).getX() - 30,
                            world.getInfiltrators().get(i).getY());
                }
                if (world.getInfiltrators().get(i).getAbility().equals("corrupt")){ //corrupts if close to hostile
                    batch.draw(corruptImage,world.getInfiltrators().get(i).getX()-30,
                            world.getInfiltrators().get(i).getY());
                    if (world.getAuber().closeToCharacterNPC(world.getInfiltrators().get(i))){
                        batch.draw(corruptImage,world.getAuber().getX()-30,world.getAuber().getY());
                    }
                }
            }
        }

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

        batch.end();

        //SYS NOTIFICATION
        Timer timer = new Timer();
        for (int i = 0; i < world.getSystems().size; i++) {
            for (int c = 0; c < world.getInfiltrators().size; c++){
                if ((world.getInfiltrators().get(c).closeToSystem(world.getSystems().get(i))) &&
                        (!world.getInfiltrators().get(c).isArrested())
                && (world.getInfiltrators().get(c).isCurrent())){
                    if (isRoom2||isRoom4){
                        notify_label.setPosition(0,500);
                    }
                    if (isRoom1||isRoom3){
                        notify_label.setPosition(0,0);
                    }
                    if ((world.getSystems().get(i).notifyPlayer())&& (!world.getSystems().get(i).isDestroyed())) {
                        //show notification
                        if (i == 0) {
                            stage.addActor(notify_label);
                            notify_label.setText("The Armoury System is being sabotaged!");
                            stage.draw();}
                        if (i == 1) {
                            stage.addActor(notify_label);
                            notify_label.setText("The Storage System is being sabotaged!");
                            stage.draw();
                        }
                        if ((i == 2) || (i == 3)) {
                            stage.addActor(notify_label);
                            notify_label.setText("A Control Room System is being sabotaged!");
                            stage.draw();
                        }
                        if ((i == 4) || (i == 5)) {
                            stage.addActor(notify_label);
                            notify_label.setText("A Cantine System is being sabotaged!");
                            stage.draw();
                        }
                        if (i == 6) {
                            stage.addActor(notify_label);
                            notify_label.setText("The Dorms System is being sabotaged!");
                            stage.draw();
                        }
                        if (i == 7) {
                            stage.addActor(notify_label);
                            notify_label.setText("A System near the brig is being sabotaged!");
                            stage.draw();
                        }
                        if ((i == 8) || (i == 9)) {
                            stage.addActor(notify_label);
                            notify_label.setText("An Engine Room System is being sabotaged!");
                            stage.draw();
                        }
                        if (i == 10) {
                            stage.addActor(notify_label);
                            notify_label.setText("The Shield Room System is being sabotaged!");
                            stage.draw();
                        }
                        if ((i == 11) || (i == 12)) {
                            stage.addActor(notify_label);
                            notify_label.setText("A Reactor Room System is being sabotaged!");
                            stage.draw();
                        }
                        if (i == 13) {
                            stage.addActor(notify_label);
                            notify_label.setText("The Cargo Bay System is being sabotaged!");
                            stage.draw();
                        }
                        if (i == 14) {
                            stage.addActor(notify_label);
                            notify_label.setText("A System near the cargo bay is being sabotaged!");
                            stage.draw();
                        } }

                }
            }
            }
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                stage.clear();
            }
        };
        timer.scheduleTask(task,3);

        //GAME LOST CONDITION
        if ((world.getSystems().size) <= 0) {
            AllDestroyed = true;
        }
        if ((world.getAuber().getHealth() <= 0) || (AllDestroyed)) {
            notify_label.setAlignment(Align.center);
            notify_label.setPosition(200, 200);
            notify_label.setColor(Color.RED);
            notify_label.setText("GAME OVER: You Lose - Press any key ");
            stage.addActor(notify_label);
            stage.draw();

            Gdx.input.setInputProcessor(new InputAdapter() {
                public boolean keyDown (int keycode) {
                    game.setScreen(new MainScreen(game));
                    return false;
                }
            });
        }

        //GAME WIN CONDITION
        if (numberArrested>=8){
            AuberWin=true;
        }
        else{
            AuberWin=false;
        }
        if (AuberWin) {
            notify_label.setAlignment(Align.center);
            notify_label.setPosition(200, 200);
            notify_label.setColor(Color.GREEN);
            notify_label.setText("GAME OVER: You Win - Press any key ");
            stage.addActor(notify_label);
            stage.draw();

            Gdx.input.setInputProcessor(new InputAdapter() {
                public boolean keyDown(int keycode) {
                    game.setScreen(new MainScreen(game));
                    return false;
                }
            });
        }

        //DAMAGE UPDATES
        if (update_num > 400) {
            infiltratorController.Abilities();
            infiltratorController.normalDamage();
            this.update_num = 0;
        }
        update_num += 1;

        infiltratorController.updateInfiltratorLocation();
        auberController.updateAuberLocation();
        updateCameraRoomLocation();
        camera.update();
    }

    public boolean getIsRoom1() {
        return isRoom1;
    }

    public boolean getIsRoom2() {
        return isRoom2;
    }

    public boolean getIsRoom3() {
        return isRoom3;
    }

    public boolean getIsRoom4() {
        return isRoom4;
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
        game.dispose();
    }
}
