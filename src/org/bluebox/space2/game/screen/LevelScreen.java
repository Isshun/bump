
package org.bluebox.space2.game.screen;

import java.util.ArrayList;
import java.util.List;

import org.bluebox.space2.engine.Art;
import org.bluebox.space2.engine.screen.BaseScreen;
import org.bluebox.space2.engine.screen.BaseScreenLayer;
import org.bluebox.space2.game.model.PlayerModel;
import org.bluebox.space2.game.model.PlayerModel;

public class LevelScreen extends BaseScreen {
	
	private List<PlayerModel> mPlayers;

	@Override
	protected void onCreate () {
		super.onCreate();
		
		mPlayers = new ArrayList<PlayerModel>();
		for (int i = 0; i < 1; i++) {
			mPlayers.add(new PlayerModel());
		}
		
		onReset();
	}

	@Override
	public void onDraw (BaseScreenLayer mainLayer, BaseScreenLayer UILayer) {
	}
	
	public void onRender(BaseScreenLayer dynamicLayer, int gameTime, int screenTime) {
		for (PlayerModel player: mPlayers) {
			player.update();
			player.draw(dynamicLayer);
		}
	}

	public void onReset () {
		for (PlayerModel player: mPlayers) {
			player.reset(200 + (int)(Math.random() * 400), 200 + (int)(Math.random() * 400));
		}
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
