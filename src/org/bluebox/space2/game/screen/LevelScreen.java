
package org.bluebox.space2.game.screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bluebox.space2.engine.Art;
import org.bluebox.space2.engine.screen.BaseScreen;
import org.bluebox.space2.engine.screen.BaseScreenLayer;
import org.bluebox.space2.game.Constants;
import org.bluebox.space2.game.model.PlayerModel;
import org.bluebox.space2.game.model.PlayerModel;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class LevelScreen extends BaseScreen {
	
	private PlayerModel mPlayer;

	@Override
	protected void onCreate () {
		super.onCreate();
		
		addBody();
		
		addGround();
		
		mPlayer = new PlayerModel();
		
//		mPlayers = new ArrayList<PlayerModel>();
//		for (int i = 0; i < 1; i++) {
//			mPlayers.add(new PlayerModel());
//		}
		
		onReset();
	}

	private void addGround () {
		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyType.StaticBody;
		// Set its world position
		groundBodyDef.position.set(new Vector2(0, 10));  

		// Create a body from the defintion and add it to the world
		Body groundBody = mWorld.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(1000, 10.0f);
		// Create a fixture from our polygon shape and add it to our ground body
		
//		groundBody.createFixture(groundBox, 1f);
		
		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = groundBox;
		fixtureDef.density = 1f; 
		fixtureDef.friction = 0.2f;
		fixtureDef.restitution = 0.8f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = groundBody.createFixture(fixtureDef);

		
		// Clean up after ourselves
		groundBox.dispose();
	}

	private void addBody () {
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(100, 300);
		

		// Create our body in the world using our body definition
		Body body = mWorld.createBody(bodyDef);
		body.applyLinearImpulse(0.80f, 0, 100, 300, true);


		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(26f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1f; 
		fixtureDef.friction = 0.2f;
		fixtureDef.restitution = 0.8f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		
		//body.applyForce(10.0f, 0.0f, 100, 300, true);
	}

	@Override
	public void onDraw (BaseScreenLayer mainLayer, BaseScreenLayer UILayer) {
	}
	
	public void onRender(BaseScreenLayer dynamicLayer, int gameTime, int screenTime) {
//		for (PlayerModel player: mPlayers) {
//			player.update();
//			player.draw(dynamicLayer);
//		}
//		

//		Array<Body> bi = new Array<Body>();
//		mWorld.getBodies(bi);

//		for (Body body: bi) {
//			mPlayer.setPosition(body.getPosition());
//			mPlayer.draw(dynamicLayer);
//		}
		
//		while (bi.hasNext()){
//		    Body b = bi.next();
//
//		    // Get the body's user data - in this example, our user 
//		    // data is an instance of the Entity class
//		    Entity e = (Entity) b.getUserData();
//
//		    if (e != null) {
//		        // Update the entities/sprites position and angle
//		        e.setPosition(b.getPosition().x, b.getPosition().y);
//		        // We need to convert our angle from radians to degrees
//		        e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
//		    }
//		}
		
	}

	public void onReset () {
//		for (PlayerModel player: mPlayers) {
//			player.reset(200 + (int)(Math.random() * 400), 200 + (int)(Math.random() * 400));
//		}
	}
	
	@Override
	public void onTouch (int x, int y) {
	}

	@Override
	public void onMove (int startX, int startY, int offsetX, int offsetY) {
	}

	@Override
	public void onLongTouch (int x, int y) {
	}

}
