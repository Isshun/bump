package org.bluebox.space2.engine.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class View {

	public static interface OnClickListener {
		void onClick();
	}

	public static final int GONE = 0;
	public static final int VISIBLE = 1;
	
	protected int 				mPosX;
	protected int 				mPosY;
	protected int 				mWidth;
	protected int 				mHeight;
	protected int 				mPadding;
	private OnClickListener mOnClickListener;
	private int 				mVisibility;
	protected boolean			mIsClickable;
	
	public View (int x, int y) {
		mPosX = x;
		mPosY = y;
		mVisibility = VISIBLE;
	}

	public void setPadding (int padding) {
		mPadding = padding;
	}

	public boolean isClickable () {
		return mIsClickable;
	}

	public boolean contains (int x, int y) {
		return x >= mPosX - mPadding && x <= mPosX + mWidth + mPadding && y >= mPosY - mPadding && y <= mPosY + mHeight + mPadding;
	}

	public void click () {
		if (mOnClickListener != null) {
			mOnClickListener.onClick();
		}
	}
	
	public int getPadding() {
		return mPadding;
	}
	
	public boolean isVisible() {
		return mVisibility == VISIBLE;
	}

	public void setVisibility (int state) {
		mVisibility = state;
	}

	public abstract void draw (SpriteCache spriteBatch);

	public void setOnClickListener (OnClickListener onClickListener) {
		mOnClickListener = onClickListener;
	}

}
