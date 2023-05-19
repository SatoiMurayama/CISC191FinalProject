package FinalSato;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class HomingEnemy implements Enemy
{
	// HomingEnemy class HAS-A 2 int to store current coordinate of this
	private int thisX, thisY;

	// HomingEnemy class HAS-A BufferedImage object to represent its image
	private BufferedImage image;

	// HomingEnemy class HAS-A int wait time to store How many frames of delay
	// will be there
	private int waittime;

	// EnemyAirPlane class HAS-A 2 int to store current coordinate of player
	private int playerX, playerY;

	// HomingEnemy class HAS-A ImageEditor object to use method to edit image of
	// this class
	// depending on the player's coordinates.
	private ImageEditor imageEditor = new ImageEditor();

	// HomingEnemy class HAS-A boolean flippedRight, initial is false not
	// flipped right
	private boolean flippedRight;

	// HomingEnemy HAS-A final int variables to set HomingEnemy's movement speed at 4 pixel per frame
	private final int moveSpeedOfHoming = 4;

	/**
	 * Purpose: Constructor for the HomingEnemy class
	 * 
	 * @param image The image of the enemy.
	 * @param index The index of the enemy.
	 * 
	 */

	public HomingEnemy(BufferedImage image, int index)
	{
		this.image = image;
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
		thisX = 620;
		thisY = 500;
		waittime = waittime + (int) (Math.random() * 200);
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
		//unless wait time is equal 0, return noithing mean, do nothing
		if (waittime >= 0)
		{
			waittime = waittime - 1;
			return;
		}

		//if player's Y coordinate is greater than this Y, make this move toward player
		if (thisY < playerY)
		{
			thisY += moveSpeedOfHoming;
		}

		//if player's Y coordinate is less than this Y, make this move toward player
		if (thisY > playerY)
		{
			thisY -= moveSpeedOfHoming;
		}

		//if player's Y coordinate is equal to this Y, make it only move on X
		else
		{
			thisY += 0;
		}

		//if player's X coordinate is greater than this X, make this move toward player
		if (thisX < playerX)
		{
			//if it is not flip right make it flip right
			if (flippedRight == false)
			{
				image = imageEditor.flipHorizontally(image);
				flippedRight = true;
			}

			thisX += moveSpeedOfHoming;
		}

		//if player's X coordinate is less than this X, make this move toward player
		if (thisX > playerX)
		{
			//if flipped right, make it flip left
			if (flippedRight == true)
			{
				image = imageEditor.flipHorizontally(image);
				flippedRight = false;
			}

			thisX -= moveSpeedOfHoming;
		}

		//if player's Y coordinate is equal to this X, make it only move on Y
		else
		{
			thisX += 0;
		}

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
		// 72*18
		int bx1 = thisX + 28;
		int by1 = thisY + 4;
		int bx2 = thisX + 44;
		int by2 = thisY + 14;
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

	/**
	 * Purpose: method to assign player's coordinates to field variable to
	 * homing
	 * 
	 * @param int playerX coordinate
	 * @param int playerY coordinate
	 * 
	 * @return void
	 */
	public void setPlayerCurrentCoodinate(int playerX, int playerY)
	{
		this.playerX = playerX;
		this.playerY = playerY + 4;
	}
}