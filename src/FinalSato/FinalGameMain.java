package FinalSato;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Lead Author(s):Satoi Murayama
 * 
 * @author
 * @author
 *         <<add additional lead authors here, with a full first and last name>>
 * 
 *         Other contributors:
 *         <<add additional contributors (mentors, tutors, friends) here, with
 *         contact information>>
 * 
 *         References:
 *         Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented
 *         Problem Solving.
 *         Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *         -reference book for learning foundations such as screen transition
 *         using variables,
 *         how to utilize BufferStrategy and Graphics to display
 * 
 *         1５歳からはじめるＪＡＶＡわくわくゲームプログラミング教室
 *         Retrieved May 9, 2023,
 *         from https://honto.jp/netstore/pd-book_02539046.html
 * 
 * 
 *         -cite read to know how to gray scale PNG image
 * 
 *         ColorConvertOpで画像をグレースケールに変換. (2022, December 9). Java Swing Tips.
 *         Retrieved May 9, 2023, from
 *         https://ateraimemo.com/Swing/ColorConvertOp.html
 * 
 * 
 *         -2 cite below to know how should I scalimg PNG to make it more blocky
 * 
 *         BufferedImage (Java platform SE 6). (2009, September 28). Moved.
 *         Retrieved May 9, 2023, from
 *         https://docs.oracle.com/javase/jp/6/api/java/awt/image/BufferedImage.html
 * 
 *         Javaで画像の縮小・拡大をする方法を現役エンジニアが解説【初心者向け】.
 *         (2020, April 22). TechAcademyマガジン - 教育×テクノロジーのWebメディア.
 *         Retrieved May 9, 2023, from
 *         https://magazine.techacademy.jp/magazine/34655#sec3
 * 
 *         -cite used to know how I can retrieve images in the same folder
 *         independent of the environment
 *         GetResourceが便利。今まで知らなかったのが悔やまれる。. (n.d.). その手の平は尻もつかめるさ.
 *         Retrieved May 9, 2023, from
 *         https://moznion.hatenadiary.com/entry/20120130/1327944185
 * 
 * 
 *         -I did not use the way this cite describe but this cite was really
 *         helpful
 *         プログラミング応用a 第14&14回(n.d.).
 *         Retrieved May 9, 2023, from
 *         https://www.ohshiro.tuis.ac.jp/~ohshiro/progaa/dxjava/main10.html
 *         Version/date:
 * 
 *         Responsibilities of class:
 * 
 */
/**
 */

// FinalGameMain IS-A part-of GameBase class
public class FinalGameMain extends GameBase
{
	// GameBase HAS-A 5 booleans to
	private boolean wkey = false;
	private boolean akey = false;
	private boolean skey = false;
	private boolean dkey = false;

	// FinalGameMain class HAS-A ImageMnager where I store all images to be used
	private ImageManeger imageManeger;

	// FinalGameMain class HAS-A ArrayList of EnemyAirplane, slow to add but
	// fast access allow
	// me to draw 30 times in a second
	private List<EnemyAirPlane> enemyPlaneList = new ArrayList<>();

	// FinalGameMain class HAS-A enemyAirShip which manage enemyAirship on the
	// screen
	private EnemyAirShip enemyAirShip;

	// FinalGameMain class HAS-A HomingEnemy which manage enemyAirship on the
	// screen
	private HomingEnemy emenyHoming;

	// FinalGameMain class HAS-A player class, as enemies, manage player on the
	// screen
	private Player player;

	// FinalGameMain class HAS-A int array Used to send the collisions
	// calculated from player coordinates, which are updated every frame, to
	// other class
	private int[] pCollision = new int[4];

	// FinalGameMain class HAS-A long variable to Stores miliseconds retrieved
	// from System.currentTimeMillis();
	// retrieved the time the stage was started and keeps it until game over or
	// clear.
	private long stagetimer;

	// FinalGameMain class HAS-A int variable stageNumber
	// Each time the 15,000 millisecond condition is achieved, thi will be
	// added,
	// and number of enemy planes is also increased
	private int stageNumber = 1;

	// FinalGameMain class HAS-A int variable FrameCounter use only to invoke a
	// method every 12 frames that sends the coordinates of the player to a
	// homing enemy class to homing
	private int FrameCounter = 0;

	// FinalGameMain class HAS-A ２ int variables to store current coordinate of
	// player
	// even before gameover and gameclear
	private int playerLastX = 300;
	private int playerLastY = 200;

	/**
	 * Purpose: Constructor for the FinalGameMain class
	 * 
	 * @param no param
	 * 
	 */

	FinalGameMain()
	{
		// use super constructor to invoke parent class with width, height and
		// title of the frame to be displayed
		super(600, 400, "Run,Witch!");
		ImageManeger imageManeger = new ImageManeger();
		this.imageManeger = imageManeger;
		beforeStart();
	}

	/**
	 * Purpose: set true to assigned button when pushed
	 * 
	 * @param keyEvent ev a = 65 ～ z = 90
	 *                 this time, w = 88, a = 65, s = 83,d = 68
	 * 
	 * @return void
	 */
	protected void keyPressedWASD(int keycode)
	{
		if (keycode == KeyEvent.VK_W)
		{
			wkey = true;
		}
		if (keycode == KeyEvent.VK_A)
		{
			akey = true;
		}
		if (keycode == KeyEvent.VK_S)
		{
			skey = true;
		}
		if (keycode == KeyEvent.VK_D)
		{
			dkey = true;
		}
	}

	/**
	 * Purpose: set false to assigned button when released
	 * 
	 * @param keyEvent ev a = 65 ～ z = 90
	 *                 this time, w = 88, a = 65, s = 83,d = 68
	 * 
	 * @return void
	 * 
	 */
	protected void keyReleasedWASD(int keycode)
	{
		if (keycode == KeyEvent.VK_W)
		{
			wkey = false;
		}
		if (keycode == KeyEvent.VK_A)
		{
			akey = false;
		}
		if (keycode == KeyEvent.VK_S)
		{
			skey = false;
		}
		if (keycode == KeyEvent.VK_D)
		{
			dkey = false;
		}
	}

	/**
	 * Purpose: Method executed before the stage starts Mainly for resetting
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	protected void beforeStageStart()
	{
		Player player = new Player(playerLastY, playerLastX,
				imageManeger.getPlayerImage());
		this.player = player;

		wkey = false;

		akey = false;

		skey = false;

		dkey = false;

		// remove all elements in arrayList named enemyPlaneList
		enemyPlaneList.removeAll(enemyPlaneList);

		// for loop to assign certain number of enemy planes to arrayList
		for (int i = 0; i < stageNumber; i++)
		{
			enemyPlaneList.add(
					new EnemyAirPlane(imageManeger.getEnemyImageAirPlane(), i));
		}

		enemyAirShip = new EnemyAirShip(imageManeger.getEnemyImageAirShip(), 0);

		emenyHoming = new HomingEnemy(imageManeger.getEnemyHoming(), 0);

		// began to count here till 15000 miliseconds
		stagetimer = System.currentTimeMillis();

	}

	/**
	 * Purpose: method to start stage, display message show what this stage
	 * number is/
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	protected void runStageStart(Graphics g)
	{
		g.setColor(Color.BLACK);

		g.fillRect(0, 0, 600, 400);

		g.setColor(Color.WHITE);

		g.setFont(new Font("SansSerif", Font.BOLD, 50));

		if (stageNumber == 1)
		{
			drawStringCenter("Stage" + "\s" + stageNumber + "\s" + "Start", 150,
					g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));

			drawStringCenter("Use W,A,S,D keys to control", 200, g);
			drawStringCenter("avoid enemies for as long as you can", 240, g);
		}

		else
		{
			drawStringCenter("Stage" + "\s" + stageNumber + "\s" + "Start", 220,
					g);
		}
	}

	/**
	 * Purpose: method to start stage, display message show what this stage
	 * number is
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	protected void runStageClear(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 400);
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		drawStringCenter("Stage" + "\s" + stageNumber + "\s" + "Clear", 220, g);
	}

	/**
	 * Purpose: Methods for moving enemies and players, judging hits, and
	 * displaying on the screen during game execution
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	protected void runGameMain(Graphics g)
	{
		pCollision = player.collisionOfPlayer();

		// if any key boolean is true, invoke method of player to move
		if (wkey == true)
		{
			player.setMovementUp();
		}
		if (skey == true)
		{
			player.setMovementDown();
		}
		if (dkey == true)
		{
			player.setMovementRight();
		}
		if (akey == true)
		{
			player.setMovementLeft();
		}

		// draw background, most backside
		g.drawImage(imageManeger.getBackGroundImage(), 0, 0, frame1);

		// draw Airship 2nd most backside
		enemyAirShip.drawing(g, frame1);

		// after move player and before draw homing enemy, assign player's x and
		// y coordinate to field

		playerLastCoodinate();

		// every 12 frames, send the coordinate of the player above to homing
		// class, this allows homing enemy daring to reduce tracking performance
		if (FrameCounter == 12)
		{
			emenyHoming.setPlayerCurrentCoodinate(playerLastX, playerLastY);
			FrameCounter = 0;
		}

		// for loop to draw every enemy planes in arrayList
		for (int i = 0; i < stageNumber; i = i + 1)
		{
			enemyPlaneList.get(i).drawing(g, frame1);
		}

		// draw homing enemy, reason why I draw this after enemyplane is that
		// the earlier the draw method is written, the more it will be displayed
		// on the back.
		emenyHoming.drawing(g, frame1);

		// since I want to display player as a most frontside object, I wrote
		// this here.
		player.drawing(g, frame1);

		// after draw images, check whether player collide with enemies or not

		// collide checker for every enemyPlaneList
		for (int i = 0; i < stageNumber; i++)
		{
			if (enemyPlaneList.get(i).enemyCollision(pCollision) == true)
			{
				invokeGameOver();
			}
		}

		// collide checker for enemyAirShip
		if (enemyAirShip.enemyCollision(pCollision) == true)
		{
			invokeGameOver();
		}

		// collide checker for eneemenyHomingmyAirShip
		if (emenyHoming.enemyCollision(pCollision) == true)
		{
			invokeGameOver();
		}

		// after check collide, check current time subtract stagetimer retrieved
		// when stage was started has more than 15000 miliseconds(15 seconds)
		// different,
		// invoke method to tell user clear the stage
		if (System.currentTimeMillis() - stagetimer > 15000)
		{
			invokeStageClear();
		}
		// if condition above not met, add FrameCounter by 1
		else
		{
			FrameCounter++;
		}

	}

	/**
	 * Purpose: method to run gameover, display message and ask user continue or not,
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	protected void runGameOver(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 80));
		drawStringCenter("GAMEOVER", 220, g);
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		drawStringCenter("Push R Key To Continue", 280, g);
	}

	/**
	 * Purpose: method to run gameover, display message and ask user continue or not,
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	public static void main(String[] args)
	{
		FinalGameMain gtm = new FinalGameMain();
	}

	@Override
	protected void beforeStageClear()
	{
		stageNumber += 1;
	}

	public void playerLastCoodinate()
	{
		playerLastX = player.getPlayerXCoordinate();
		playerLastY = player.getPlayerYCoordinate();
	}
}