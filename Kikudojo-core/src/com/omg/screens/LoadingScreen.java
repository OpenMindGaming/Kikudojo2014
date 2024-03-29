package com.omg.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.omg.drawing.JSActor;
import com.omg.drawing.JSFont;
import com.omg.sfx.MusicManager;
import com.omg.sfx.SoundManager;
import com.omg.spriter.TextureProvider;
import com.omg.sswindler.GameManager;

public class LoadingScreen implements Screen, TextureProvider {

	GameManager gameManager;
	
	 MusicManager musicManager;
	 SoundManager soundManager;
	 
	 private Stage stage;
	 JSActor BASENODE;
	 private Viewport viewport;
	 
	 //JSFont menuText;
	 //JSFont touchToContinueText;
	 
	 //JSActor background;
	 
	 JSActor loadingText;
	 JSActor animalRing;


	 HashMap<String, Texture> textures;

	 Loadable screenToLoad;
	 
	 Thread T;
	 

    // constructor to keep a reference to the main Game class
     public LoadingScreen(GameManager gameManager, Loadable screenToLoad){
             this.gameManager = gameManager;
             this.screenToLoad = screenToLoad;
     }
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		OrthographicCamera camera = (OrthographicCamera)stage.getCamera();
		
		

		camera.position.set(0.0f,0.0f, 0.0f);
		camera.zoom = 1.0f;
		
		
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();
	        
	
	    
	    
	    if(!T.isAlive()){
	    	gameManager.setScreen((Screen) screenToLoad);
		} 
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//stage.setViewport(width, height, true);
		viewport.update(width, height);
	
	}

	@Override
	public void show() {
  		float w = Gdx.graphics.getWidth();
  		float h = Gdx.graphics.getHeight();


  		viewport = new FitViewport(1280, 720);
	  	 stage = new Stage(viewport);
	     Gdx.input.setInputProcessor(stage);
	  	  
        
        textures = new HashMap<String, Texture>();

        
  		BASENODE = new JSActor();
  		stage.addActor(BASENODE);
  		
  		BASENODE.setPosition(-300, 200);
  		
  		/*background = new JSActor(new TextureRegion(new Texture(Gdx.files.internal("data/loading background.png")),0,0,1280,720));
  		background.setPosition(-340, -560);
  		BASENODE.addActor(background);
  		
  		menuText = new JSFont("");
  		menuText.setPosition(100,0);
  		BASENODE.addActor(menuText);
  		
  		touchToContinueText = new JSFont("Loading ...");
  		touchToContinueText.setPosition(50,-150);
  		BASENODE.addActor(touchToContinueText);
  		*/
  		
  		loadingText = new JSActor(new TextureRegion(new Texture(Gdx.files.internal("data/ui/Loading.png")),0,0,500,200));
  		loadingText.setPosition(50, -175);
  		BASENODE.addActor(loadingText);
  		
  		animalRing = new JSActor(new TextureRegion(new Texture(Gdx.files.internal("data/ui/animal_circle.png")),0,0,150,150));
  		animalRing.setPosition(225, -375);
  		animalRing.setOrigin(75, 75);
  		animalRing.addAction(Actions.forever(Actions.rotateBy(360, 3)));
  		BASENODE.addActor(animalRing);
  		
  		
  	// create the music manager service
        musicManager = new MusicManager();
      //  musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( true);
        
        //musicManager.play(LucidMusic.SWINDLER);

        // create the sound manager service
        soundManager = new SoundManager();
       // soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( true);
        
        
       // screenToLoad;
        
        T = new Thread(new ScreenLoadThread(screenToLoad));
		T.start();
        
  		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	  public Texture getTexture(String filename) {
	    if (!textures.containsKey(filename)) {
	      textures.put(filename, new Texture(Gdx.files.internal(filename)));
	      System.out.println("Texture " + filename + " loaded");
	    }
	    return textures.get(filename);

	  }

	  @Override
	  public void disposeTexture(String filename) {
	    textures.get(filename).dispose();
	    textures.remove(filename);
	    System.out.println("Texture " + filename + " disposed");
	  }
	
}
