package org.bluebox.space2.engine.screen;

import org.bluebox.space2.engine.ui.View;
import org.bluebox.space2.game.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class DynamicScreenLayer extends BaseScreenLayer {
	private SpriteBatch 				mSpriteBatch;
	
	public DynamicScreenLayer() {
		mSpriteBatch = new SpriteBatch(100);
	}

	public void draw (int offsetX, int offsetY, float zoom) {
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

		Matrix4 projection = new Matrix4();
		projection.setToOrtho(-mRealPosX + offsetX, Constants.GAME_WIDTH * zoom - mRealPosX + offsetX, Constants.GAME_HEIGHT * zoom - mRealPosY + offsetY, -mRealPosY + offsetY, -1, 1);

		mSpriteBatch.setProjectionMatrix(projection);  
	}

	@Override
	void draw (Sprite sprite) {
		sprite.draw(mSpriteBatch);
	}

	@Override
	void draw (View view) {
	}

	@Override
	void draw (TextureRegion region, int x, int y, int width, int height, float rotate) {
		mSpriteBatch.draw(region, x, Gdx.graphics.getHeight() - height - y, width / 2, height / 2, width, height, 1f, 1f, rotate * 360);
	}

	public void clear () {
	}

	public void begin() {
		mSpriteBatch.begin();
	}

	public void end() {
		mSpriteBatch.end();
	}

	public boolean isChange () {
		return true;
	}

	@Override
	public void dispose () {
		mSpriteBatch.dispose();
	}

	@Override
	void notifyChange () {
	}

}
