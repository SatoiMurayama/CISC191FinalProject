package FinalSato;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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

public class Player
{
	// Player HAS-A int variable to hold initial and current X and Y coordinate
	// of player
	private int playerYCoodinate;
	private int playerXCoodinate;

	// Player HAS-A double array store current collision detection of player
	private int[] pCollision = new int[4];

	// Player HAS-A final int variables to set player movement speed in 4
	// directions
	private final int playerMoveSpeedUp = 6;
	private final int playerMoveSpeedDown = 8;
	private final int playerMoveSpeedRight = 7;
	private final int playerMoveSpeedLeft = 7;

	// Player class HAS-A ImageEditor object to use method to edit image of
	// player
	private ImageEditor imageEditor = new ImageEditor();

	// Player class HAS-A BufferedImage object to represent its image
	private BufferedImage playerImage;

	// Player class HAS-A boolean flippedLeft, initial is false not flipped left
	private boolean flippedLeft;

	/**
	 * Purpose: Constructor for the Player class
	 * 
	 * @param image The image of the enemy airplane.
	 * @param int   type variable show Initial X and Y coordinates
	 *              to be rewritten each time the game is run
	 * 
	 */
	public Player(int initialY, int initialX, BufferedImage pImage)
	{
		playerYCoodinate = initialY;
		playerXCoodinate = initialX;
		playerImage = pImage;
	}

	/**
	 * Purpose:Raise the player's Y-coordinate with the assignment operator
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	public void setMovementUp()
	{
		playerYCoodinate -= playerMoveSpeedUp;
	}

	/**
	 * Purpose:Down the player's Y-coordinate with the assignment operator
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	public void setMovementDown()
	{
		playerYCoodinate += playerMoveSpeedDown;
	}

	/**
	 * Purpose:Add the player's X-coordinate with the assignment operator
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	public void setMovementRight()
	{
		if (flippedLeft == true)
		{
			playerImage = imageEditor.flipHorizontally(playerImage);
			flippedLeft = false;
		}
		playerXCoodinate += playerMoveSpeedRight;
	}

	/**
	 * Purpose:Decrease the player's X-coordinate with the assignment operator
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	public void setMovementLeft()
	{
		if (flippedLeft == false)
		{
			playerImage = imageEditor.flipHorizontally(playerImage);
			flippedLeft = true;
		}
		playerXCoodinate -= playerMoveSpeedLeft;
	}

	/**
	 * Purpose: return player's crrent position with its collision data
	 * 
	 * @param no param
	 * @return int[]
	 */
	public int[] collisionOfPlayer()
	{
		pCollision[0] = playerXCoodinate + 14;
		pCollision[1] = playerXCoodinate + 36;
		pCollision[2] = playerYCoodinate + 14;
		pCollision[3] = playerYCoodinate + 36;
		return pCollision;
	}

	/**
	 * Purpose:getter of the player's X coordinate,
	 * Used to store coordinates when the player's game is over
	 * and to homing enemy to track player
	 * 
	 * @param no param
	 * 
	 * @return int Xcoordinate
	 */
	public int getPlayerXCoordinate()
	{
		return playerXCoodinate;
	}

	/**
	 * Purpose:getter of the player's X coordinate,
	 * Used to store coordinates when the player's game is over
	 * and to homing enemy to track player
	 * 
	 * @param no param
	 * 
	 * @return int Ycoordinate
	 */
	public int getPlayerYCoordinate()
	{
		return playerYCoodinate;
	}

	/**
	 * Purpose: Method to drawing player on the screen, but with limitation of
	 * movement range
	 * 
	 * @param Graphic object and ImageObserver object would be used togather to
	 *                draw image of enemy on the screen
	 * 
	 * @return void
	 */
	public void drawing(Graphics g, JFrame io)
	{
		if (playerYCoodinate < -20)
		{
			playerYCoodinate = -20;
		}
		if (playerYCoodinate > 390)
		{
			playerYCoodinate = 390;
		}
		if (playerXCoodinate < -20)
		{
			playerXCoodinate = -20;
		}
		if (playerXCoodinate > 580)
		{
			playerXCoodinate = 580;
		}
		g.drawImage(playerImage, playerXCoodinate, playerYCoodinate, io);
	}
}