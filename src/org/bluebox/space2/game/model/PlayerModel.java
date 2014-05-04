package org.bluebox.space2.game.model;

import org.bluebox.space2.engine.Art;
import org.bluebox.space2.engine.screen.BaseScreenLayer;
import org.bluebox.space2.game.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PlayerModel {

	private double		mPosX;
	private double		mPosY;

	private double		mVelocityX;
	private double		mVelocityY;

	private double		mRotate;
	private double		mRotatePos;
	
	public void draw (BaseScreenLayer layer) {
		layer.draw(Art.goo, (int)mPosX, (int)mPosY, (float)(mRotatePos));
	}

	public void update () {

		// Friction
		{
//			if (mVelocityY > 0) { mVelocityY -= Constants.FRICTION; }
//			if (mVelocityX > 0) { mVelocityX -= Constants.FRICTION; }
			mVelocityY *= 0.98;
			mVelocityX *= 0.98;
			
			if (mVelocityX > 0) { mVelocityX = Math.max(mVelocityX - 0.04, 0); }
			if (mVelocityX < 0) { mVelocityX = Math.min(mVelocityX + 0.04, 0); }
			
//			if (mVelocityY > 0) { mVelocityY *= 0.98; }
//			if (mVelocityX > 0) { mVelocityX *= 0.98; }
			//if (mVelocityX > 0) { mVelocityX *= Constants.FRICTION; }
			
//			if (mVelocityY < 0) { mVelocityY *= Constants.FRICTION; }
//			else { mVelocityY *= Constants.FRICTION; }
		}
		
		// Bound
		if (mPosY + mVelocityY <= 0) {
			mVelocityY = -mVelocityY * Constants.BOUND_COOEF;
			//mRotate = Math.min(mRotate + mVelocityX + mVelocityY, Constants.MAX_ROTATE);

			bound();
		}

		// Bound right
		if (mPosX + mVelocityX > Gdx.graphics.getWidth()) {
			mVelocityX = -mVelocityX * Constants.BOUND_COOEF;

			bound();
		}

		// Bound left
		if (mPosX + mVelocityX < 0) {
			mVelocityX = -mVelocityX * Constants.BOUND_COOEF;

			bound();
		}
		
		// Rotate
		{
			mRotate = Math.min(mRotate + mVelocityX + mVelocityY, Constants.MAX_ROTATE);
			mRotate = mRotate * Constants.ROTATE_DIMINUTION;
			if (Math.abs(mRotate) < 1) {
				mRotate = 0;
			}
			
		}
		
		// Floor
		if (mPosY + mVelocityY <= 0) {
			mVelocityY = 0;
			mPosY = 0;
		}
		
		// Gravity
		{
			mVelocityY = Math.max(mVelocityY - Constants.G_FORCE, Constants.MIN_VELOCITY_Y);
		}
		
		// Update position
		{
			mPosY += mVelocityY;
			mPosX += mVelocityX;
			mRotatePos += 0;
		}
		
		//System.out.println("Velocity: " + mRotate + ", " + mVelocityX + " x " + mVelocityY);
		System.out.println("Velocity: " + mRotate);
	}

	private void bound () {
		if (mVelocityX > 0) { mVelocityX += Math.abs(mRotate * 0.02); }
		else if (mVelocityX < 0) { mVelocityX -= Math.abs(mRotate * 0.02); }
		else { mVelocityX += Math.abs(mRotate * 0.02); }
		mRotate = 0;
	}

	public void reset (int x, int y) {
		mVelocityX = 0;
		mVelocityY = 0;
		mPosX = x;
		mPosY = y;
		mRotate = 0;
	}

	public void setPosition (Vector2 position) {
		mPosX = position.x;
		mPosY = position.y;
	}

}
