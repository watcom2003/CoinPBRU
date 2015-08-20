package appewtc.masterung.thailand;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {

	//Explicit
	private SpriteBatch batch;
	private Texture wallpaperTexture, cloudTexture, pigTexture;
	private OrthographicCamera objOrthographicCamera;
	private BitmapFont nameBitmapFont;
	private int xCloudAnInt, yCloudAnInt=600, direction = 1;
	private Rectangle pigRectangle;
	private Vector3 objVector3;
	private Sound pigSound;

	@Override
	public void create() {

		batch = new SpriteBatch();

		//คือการกำหนดขนาดของจอที่ต้องการ
		objOrthographicCamera = new OrthographicCamera();
		objOrthographicCamera.setToOrtho(false, 1200, 800);

		//SetUP WallPaper
		wallpaperTexture = new Texture("background.png");
		cloudTexture = new Texture("cloud.png");

		//SetUP BitMapFont
		nameBitmapFont = new BitmapFont();
		nameBitmapFont.setColor(Color.RED);
		nameBitmapFont.setScale(4);

		// Setup pig
		pigTexture = new Texture("pig.png");

		// Setup Rectangle Pig
		pigRectangle = new Rectangle();
		pigRectangle.x = 568;
		pigRectangle.y = 100;
		pigRectangle.width = 64;
		pigRectangle.height = 64;

		// Setup Pig Sound
		pigSound = Gdx.audio.newSound(Gdx.files.internal("pig.wav"));


	}    // create เอาไว้กำหนดค่า

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Setup Screen
		objOrthographicCamera.update();
		batch.setProjectionMatrix(objOrthographicCamera.combined);



		//เอาไว้วาด Object
		batch.begin();

		//Drawable Wallpaper
		batch.draw(wallpaperTexture, 0, 0);

		batch.draw(cloudTexture, xCloudAnInt,yCloudAnInt);

		//Drawable BitMapFont
		nameBitmapFont.draw(batch, "Coins PBRU", 50, 750);

		// Drawable Pig
		batch.draw(pigTexture,pigRectangle.x,pigRectangle.y);

		batch.end();

		// Move Cloud
		moveCloud();

		// Active When Touch Screen
		activeTouchScreen();




	}	// render ตัวนี่คือ loop

	private void activeTouchScreen() {
		if (Gdx.input.isTouched()) {
			// Sound Effect Pig
			pigSound.play();

			objVector3 = new Vector3();
			objVector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (objVector3.x < 600) {
				pigRectangle.x -= (pigRectangle.x < 0) ? 0 : 10;
			} else {
				pigRectangle.x += (pigRectangle.x > 1136) ? 0 : 10;
			}
		}
	}

	private void moveCloud() {
		if ((xCloudAnInt<0)||(xCloudAnInt>931)) {
			direction *= -1;
		}
		xCloudAnInt += 200 * Gdx.graphics.getDeltaTime()*direction;
	}  // moveCloud
}	// Main Class
