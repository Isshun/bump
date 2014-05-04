package org.bluebox.space2;

import java.util.ArrayList;
import java.util.List;

import org.bluebox.space2.engine.Art;
import org.bluebox.space2.engine.ArtManager;
import org.bluebox.space2.game.Constants;
import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
 
 public class PhysicsDemo implements ApplicationListener {  
      World   world;
      Box2DDebugRenderer debugRenderer;  
      OrthographicCamera camera;
		private SpriteBatch mSpriteBatch;
		private Body mBody;
		private int[][] mWalls;
		private boolean mDebug;
      static final float BOX_STEP = 1/60f;  
      static final int BOX_VELOCITY_ITERATIONS = 6;  
      static final int BOX_POSITION_ITERATIONS = 2;  
//      static final float WORLD_TO_BOX=0.01f;  
//      static final float BOX_WORLD_TO=100f;
      
      private int mInitX;
      private int mInitY;
      
      
      @Override  
      public void create() {
      	final long start = System.currentTimeMillis();
      	
   		mSpriteBatch = new SpriteBatch(100);
   		
   		mWalls = new int[100][100];
   		
//   		Matrix4 projection = new Matrix4();
//   		projection.setToOrtho(0, , -1, 1);
//   		mSpriteBatch.setProjectionMatrix(projection);

      	world = new World(new Vector2(0, -200), true);
      	
           camera = new OrthographicCamera();  
           camera.viewportHeight = Constants.GAME_HEIGHT / 4;
           camera.viewportWidth = Constants.GAME_WIDTH / 4;
           //camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);  
           

           Art.init(new ArtManager());
           
           loadMap(48, 32);

           world.setContactListener(new ContactListener() {
				@Override
				public void beginContact (Contact contact) {
//					System.out.println((System.currentTimeMillis() - start));
				}

				@Override
				public void endContact (Contact contact) {
				}

				@Override
				public void preSolve (Contact contact, Manifold oldManifold) {
				}
				
				@Override
				public void postSolve (Contact contact, ContactImpulse impulse) {
				}
         	  
           });

           
//           MassData massData = new MassData();
//           massData.mass = 200;
//           body.setMassData(massData);
           
           //System.out.println("mass: " + body.getMass());
           
           debugRenderer = new Box2DDebugRenderer();  
      }  
      private void loadMap (int w, int h) {
      	int xo = 0;
      	int yo = 0;

         camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
      	//camera.position.set(0, 0, 0);
         camera.update();
      	
   		for (int y = 0; y < h; y++) {
   			for (int x = 0; x < w; x++) {
   				int col = (Art.level.getPixel(x + xo * 31, Art.level.getHeight() - y - yo * 23) & 0xffffff00) >>> 8;
   				byte wall = 0;

   				//Ground body
   				if (col == 0x000000) {
   					BodyDef groundBodyDef = new BodyDef();  
   					groundBodyDef.position.set(new Vector2(x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF, y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF));
   					//groundBodyDef.angle = (float)(Math.PI * 0.75);
   					Body groundBody = world.createBody(groundBodyDef);
   					PolygonShape groundBox = new PolygonShape();  
   					groundBox.setAsBox(Constants.WORLD_TILE_HALF, Constants.WORLD_TILE_HALF);
   					groundBody.createFixture(groundBox, 0.0f);
   					
   					mWalls[x][y] = 1;
   				}

   				//Ground body
   				else if (col == 0xffff00) {
   					BodyDef groundBodyDef = new BodyDef();  
   					groundBodyDef.position.set(new Vector2(x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF, y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF));
   					groundBodyDef.angle = (float)(Math.PI * 0.75);
   					Body groundBody = world.createBody(groundBodyDef);
   					PolygonShape groundBox = new PolygonShape();  
   					groundBox.setAsBox(Constants.WORLD_TILE_HALF / 0.75f, 1);
   					groundBody.createFixture(groundBox, 0.0f);
   					
   					mWalls[x][y] = 2;
   				}
   				
   				//Ground body
   				else if (col == 0xff0000) {
   					BodyDef groundBodyDef = new BodyDef();  
   					groundBodyDef.position.set(new Vector2(x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF, y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF));
   					groundBodyDef.angle = (float)(Math.PI * 0.25);
   					Body groundBody = world.createBody(groundBodyDef);
   					PolygonShape groundBox = new PolygonShape();  
   					groundBox.setAsBox(Constants.WORLD_TILE_HALF / 0.75f, 1);
   					groundBody.createFixture(groundBox, 0.0f);
   					
   					mWalls[x][y] = 3;
   				}
   				
   				//Ground body
   				else if (col == 0x00ffff) {
   					BodyDef groundBodyDef = new BodyDef();  
   					groundBodyDef.position.set(new Vector2(x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF, y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF - 2));
   					groundBodyDef.angle = (float)(Math.PI * 0.75);
   					Body groundBody = world.createBody(groundBodyDef);
   					PolygonShape groundBox = new PolygonShape();  
   					groundBox.setAsBox(2, 2);
   					groundBody.createFixture(groundBox, 0.0f);
   					
   					mWalls[x][y] = 4;
   				}
   				
   				// Goo
   				else if (col == 0x0000ff) {
   		           BodyDef bodyDef = new BodyDef();  
   		           bodyDef.type = BodyType.DynamicBody;  
   		           bodyDef.position.set(x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF, y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF);
   		           bodyDef.fixedRotation = false;
   		           bodyDef.angularDamping = 1f;
   		           //bodyDef.angularVelocity = 2f;
   		           Body body = world.createBody(bodyDef);
   		           mBody = body;
   		           CircleShape dynamicCircle = new CircleShape();  
   		           dynamicCircle.setRadius(Constants.WORLD_TILE / 2);  
   		           FixtureDef fixtureDef = new FixtureDef();  
   		           fixtureDef.shape = dynamicCircle;  
   		           fixtureDef.density = 1.0f;
   		           fixtureDef.friction = 1.0f;  
   		           fixtureDef.restitution = 0.8f;  
   		           body.createFixture(fixtureDef);
   		           
   		           mInitX = x * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF;
   		           mInitY = y * Constants.WORLD_TILE + Constants.WORLD_TILE_HALF;
   		           
   						System.out.println("camera: " + (x * 10) + " x " + (y * -10));
   				}

//   				else if (col == 0xFF00FF)
//   					wall = 2;
//   				else if (col == 0xffff00)
//   					wall = 3;
//   				else if (col == 0xff0000)
//   					wall = 4;
//	
   			}
   		}
      }
   			
		@Override  
      public void dispose() {  
      }  
      @Override  
      public void render() {            
      	Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
      	
      	mSpriteBatch.begin();

      	Array<Body> bodies = new Array<Body>();
      	world.getBodies(bodies);
      	int pos = 0;
      	int h = 64;
      	int w = 64;
   		for (int y = 0; y < h; y++) {
   			for (int x = 0; x < w; x++) {
	      		if (mWalls[x][y] == 1) {
		         	draw(Art.tile,
		      		camera.position.x - camera.viewportWidth/2 + x * 32,
		      		y * 32,
		      		//y + camera.viewportHeight/2 - camera.position.y,
		      		0);
	      		}
	      		else if (mWalls[x][y] == 2) {
		         	draw(Art.tile2,
		      		camera.position.x - camera.viewportWidth/2 + x * 32,
		      		y * 32,
		      		//y + camera.viewportHeight/2 - camera.position.y,
		      		0);
	      		}
	      		else if (mWalls[x][y] == 3) {
		         	draw(Art.tile3,
		      		camera.position.x - camera.viewportWidth/2 + x * 32,
		      		y * 32,
		      		//y + camera.viewportHeight/2 - camera.position.y,
		      		0);
	      		}
	      		else if (mWalls[x][y] == 4) {
		         	draw(Art.tile4,
		      		camera.position.x - camera.viewportWidth/2 + x * 32,
		      		y * 32,
		      		//y + camera.viewportHeight/2 - camera.position.y,
		      		0);
	      		}
	      		pos++;
   			}
      	}
      	
      	draw(Art.goo,
      		camera.position.x - camera.viewportWidth/2 + (mBody.getPosition().x * Constants.WORLD_RENDER_RATIO) - 16,
      		(mBody.getPosition().y * Constants.WORLD_RENDER_RATIO) + camera.viewportHeight/2 - camera.position.y - 16 + 4,
      		mBody.getAngle());

      	//+ camera.viewportHeight - 
      	System.out.println(camera.position.y);
      	
   		mSpriteBatch.end();

   		if (mDebug) {
   			debugRenderer.render(world, camera.combined);
   		}
      	   		
      	world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
      	
   		if (Gdx.input.isKeyPressed(Keys.R)) {
   			onReset();
   			return;
   		}
   		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
   			camera.position.set(camera.position.x, camera.position.y - 100, 0);
   			camera.update();
   			try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			return;
   		}
   		if (Gdx.input.isKeyPressed(Keys.UP)) {
   			camera.position.set(camera.position.x, camera.position.y + 100, 0);
   			camera.update();
   			try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			return;
   		}
   		if (Gdx.input.isKeyPressed(Keys.D)) {
   			mDebug = !mDebug;
   			return;
   		}
      }  

      private void onReset () {
      	world.clearForces();
      	//mBody.setTransform(-mBody.getPosition().x, -mBody.getPosition().y, -mBody.getAngle());
      	mBody.setTransform(mInitX, mInitY, 0);
      	mBody.setAngularVelocity(0);
      	mBody.setLinearVelocity(0, 0);
      	
      	System.out.println("pos: " + mBody.getPosition().x + " x " + mBody.getPosition().y);
		}
		private void draw (TextureRegion goo, float x, float y, float angle) {
      	float ratioX = Gdx.graphics.getWidth() / camera.viewportWidth;
      	float ratioY = Gdx.graphics.getHeight() / camera.viewportHeight;
      	
      	float height = goo.getRegionHeight();
      	float width = goo.getRegionWidth();
      	
//   		mSpriteBatch.draw(goo, x, y);
   		mSpriteBatch.draw(
   			goo,
   			x,
   			y,
   			width / 2,
   			height / 2,
   			width,
   			height,
   			1f,
   			1f,
   			(float)(angle/Math.PI) * 180);
		}
		@Override  
      public void resize(int width, int height) {  
      }  
      @Override  
      public void pause() {  
      }  
      @Override  
      public void resume() {  
      }  
 }  