package FinalSato;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

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

public abstract class GameBase
{
	// GameBase HAS-A 5 final int to manage state of the game
	private static final int startGame = 0;
	private static final int stageStart = 1;
	private static final int stageClear = 2;
	private static final int gameOver = 3;
	private static final int gameMain = 4;

	// GameBase HAS-A int variable gamestate
	// This changes the value according to the game state
	// and invokes the process according to that value it has
	private int gamestate;

	// GameBase HAS-A BufferStrategy variable bufferStrategy provides fast
	// graphics processing
	private BufferStrategy bufferStrategy;

	// GameBase HAS-A int variable waittimer used to fix screen for a certain
	// period of time
	private int waittimer;

	// GameBase HAS-A boolean variable firstTime,
	// This is only used to give user time to read the description once at
	// start.
	private boolean firstTime = true;

	// GameBase HAS-A JFrame variable frame1
	protected JFrame frame1;

	/**
	 * Purpose: Constructor for the GameBase class
	 * 
	 * @param int    w and h to be assigned as a width and height of frame
	 * 
	 * @param string title to be assigned as a title of frame
	 * 
	 */

	protected GameBase(int w, int h, String title)
	{
		// Create new JFrame object with the given title
		frame1 = new JFrame(title);

		// Set close operation to exit the application
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set background color to white
		frame1.setBackground(Color.WHITE);

		// Set frame non-resizable
		frame1.setResizable(false);

		// Set frame visible
		frame1.setVisible(true);

		// Get insets of the frame
		Insets insets = frame1.getInsets();

		// Set size of the frame include given width and height and insets
		frame1.setSize(w + insets.left + insets.right,
				h + insets.top + insets.bottom);

		// set frame on center of the screen
		frame1.setLocationRelativeTo(null);

		// Create a buffer strategy for the frame with 2 buffers
		frame1.createBufferStrategy(2);

		// Get buffer strategy from the frame and store in field
		bufferStrategy = frame1.getBufferStrategy();

		// Set frame to ignore repaint requests of default JFrame
		frame1.setIgnoreRepaint(true);

		// Add key listener to the frame
		frame1.addKeyListener(new MyKeyAdapter());
	}

	/**
	 * Purpose: method prepare for start
	 * 
	 * @param no param
	 * 
	 * @return void
	 * 
	 */
	protected void beforeStart()
	{
		gamestate = startGame;
		// Create Timer object
		Timer t = new Timer();
		// Schedule MyTimerTask to run repeatedly every 30 milliseconds
		t.schedule(new MyTimerTask(), 0, 30);
	}

	// 2 abstract methods be implemented and written in child class extend this
	// class.

	protected abstract void beforeStageStart();

	protected abstract void beforeStageClear();

	/**
	 * Purpose: method to start stage, prepare for game to run
	 * 
	 * @param no param
	 * 
	 * @return void
	 * 
	 */
	protected void invokeStageStart()
	{
		if (firstTime)
		{
			beforeStageStart();
			waittimer = 100;
			gamestate = stageStart;
			firstTime = false;

		}
		else
		{
			beforeStageStart();
			waittimer = 25;
			gamestate = stageStart;
		}

	}

	/**
	 * Purpose: method prepare for game clear
	 * 
	 * @param no param
	 * 
	 * @return void
	 * 
	 */
	protected void invokeStageClear()
	{
		beforeStageClear();
		waittimer = 25;
		gamestate = stageClear;
	}

	/**
	 * Purpose: method to run game
	 * 
	 * @param no param
	 * 
	 * @return void
	 * 
	 */
	protected void invokeGameMain()
	{
		gamestate = gameMain;
	}

	/**
	 * Purpose: method to run gameover
	 * 
	 * @param no param
	 * 
	 * @return void
	 * 
	 */

	protected void invokeGameOver()
	{
		gamestate = gameOver;
	}

	// methods below are implemented and written in child class extend this
	// class.

	protected abstract void runStageStart(Graphics g);

	protected abstract void runStageClear(Graphics g);

	protected abstract void runGameMain(Graphics g);

	protected abstract void runGameOver(Graphics g);

	protected abstract void keyPressedWASD(int keycode);

	protected abstract void keyReleasedWASD(int keycode);

	/**
	 * Purpose: calculate and draw string at center of the frame
	 * 
	 * @param String       str to be written
	 * 
	 * @param Y-coodinates to string to be written
	 * 
	 * @param Graphics     g to to draw
	 * 
	 * @return void
	 */
	protected void drawStringCenter(String str, int y, Graphics g)
	{
		// Get center of the frame
		int fw = frame1.getWidth() / 2;

		// Get font metrics of the graphics object
		FontMetrics fm = g.getFontMetrics();

		// Calculate width of given string
		int strw = fm.stringWidth(str) / 2;

		// Draw the string at canter of frame but with given Y-coordinate
		g.drawString(str, fw - strw, y);
	}

	private class MyKeyAdapter extends KeyAdapter
	{
		/**
		 * Purpose: method with event handler that is called when a key is
		 * pressed on the keyboard.
		 * 
		 * @param Keyevent ev
		 * 
		 * @return void
		 */
		@Override
		public void keyPressed(KeyEvent ev)
		{
			// if gamestate is gameMain and receive any key pressed input,
			// invoke keyPressed WASD with ev as argument
			if (gamestate == gameMain)
			{
				keyPressedWASD(ev.getKeyCode());
			}
			// if gamestate is gameOver and receive R key pressed input,
			// invoke stageStart method to restart game
			if (gamestate == gameOver)
			{
				if (ev.getKeyCode() == KeyEvent.VK_R)
				{
					invokeStageStart();
				}
			}
		}

		/**
		 * Purpose: method with event handler that is called when a key is
		 * released on the keyboard.
		 * 
		 * @param Keyevent ev
		 * 
		 * @return void
		 */
		@Override
		public void keyReleased(KeyEvent ev)
		{
			// if gamestate is gameMain and receive any key releasede input,
			// invoke keyPressed WASD with ev as argument
			if (gamestate == gameMain)
			{
				keyReleasedWASD(ev.getKeyCode());
			}
		}
	}

	// inner class to override TimerTask and allow to be accessed/
	private class MyTimerTask extends TimerTask
	{
		/**
		 * Purpose: run method invoke every 30 milliseconds
		 * 
		 * @param no param
		 * 
		 * @return void
		 */
		@Override
		public void run()
		{
			// Get graphics from bufferstrategy
			Graphics g = bufferStrategy.getDrawGraphics();

			// Check if contents of the bufferstrategy is available to use
			if (bufferStrategy.contentsLost() == false)
			{
				// these 2 method shift frame by the number of pixels of the
				// title bar
				Insets insets = frame1.getInsets();
				g.translate(insets.left, insets.top);

				// codes below check the current gamestate in the field and
				// invokes the process according to that value it has
				if (gamestate == startGame)
				{
					invokeStageStart();
				}
				if (gamestate == stageStart)
				{
					runStageStart(g);
					waittimer = waittimer - 1;
					if (waittimer < 0) invokeGameMain();
				}
				if (gamestate == stageClear)
				{
					runStageClear(g);
					waittimer = waittimer - 1;
					if (waittimer < 0) invokeStageStart();
				}
				if (gamestate == gameMain)
				{
					runGameMain(g);
				}
				if (gamestate == gameOver)
				{
					runGameOver(g);
				}

				// Show the contents of the buffer strategy and dispose it here
				bufferStrategy.show();
				g.dispose();
			}
			else
			{
				// do nothing
			}
		}
	}
}