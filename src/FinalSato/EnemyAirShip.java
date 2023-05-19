package FinalSato;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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

public class EnemyAirShip implements Enemy
{
	// EnemyAirPlane class HAS-A 2 int to store current coordinate of this
	private int thisX, thisY;
	
	// EnemyAirPlane class HAS-A BufferedImage object to represent its image
	private BufferedImage image;
	
	// EnemyAirPlane class HAS-A int wait time to store How many frames of delay
		// will be there
	private int waittime;
	
	// EnemyAirPlane class HAS-A indexOfThis store index of this class in the
		// array
	private int indexOfThis;

	/**
	 * Purpose: Constructor for the EnemyAirShip class
	 * 
	 * @param image The image of the enemy AirShip.
	 * @param index The index of the enemy AirShip.
	 * 
	 */

	public EnemyAirShip(BufferedImage image, int index)
	{
		this.image = image;

		// Assign the value of index + 1 to the field variable indexOfThis
		indexOfThis = index + 1;

		appear();
	}

	/**
	 * Purpose: method to assign data use to move enemy to field variables.
	 * 
	 * @param no param
	 * 
	 * @return void
	 */

	@Override
	public void appear()
	{
		thisX = 605;
		thisY = (int) (Math.random() * 350);
		waittime = waittime + (int) (Math.random() * 40 * indexOfThis);

	}

	/**
	 * Purpose: Method to drawing enemy on the screen
	 * 
	 * @param Graphic object and ImageObserver object would be used togather to
	 *                draw image of enemy on the screen
	 *                
	 * @return void
	 */
	
	@Override
	public void drawing(Graphics g, ImageObserver io)
	{
		// if waittime is greater than 0, return nothing, do nothing for this
		// frame.
		if (waittime > 0)
		{
			waittime = waittime - 1;
			return;
		}

		// if this enemy go beyond this coodinate, recall appear method
		if (thisX < -700)
		{
			appear();
		}

		// set recent X coodinate of this enemy
		thisX = thisX - 3;

		// invoke drawImage method of Graphics with X and Y coordinate and frame
		// to be drawn.
		g.drawImage(image, thisX, thisY, io);

	}

	/**
	 * Purpose: invoke in every frame to check whether game is over or keep it
	 * run,
	 * by determine if the enemy and player have collided
	 * 
	 * @param int type array Player's collision data
	 * @return boolean
	 */
	@Override
	public boolean enemyCollision(int[] pCollision)
	{
		// Calculate the x and y coordinates of the top left and bottom right
				// corners of the enemy airplane
		int bx1 = thisX + 70;
		int by1 = thisY + 30;
		int bx2 = thisX + 510;
		int by2 = thisY + 100;
		if (pCollision[0] < bx2 && bx1 < pCollision[1] && pCollision[2] < by2
				&& by1 < pCollision[3])
		{
			// If there is a collision, return true
			return true;
		}
		else
		{
			// If there is no collision, return false
			return false;
		}
	}
}
