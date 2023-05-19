package FinalSato;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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

public class ImageManeger
{
	// ImageManager class HAS-A enemy_Image_AirPlane of BufferedImage type
	private BufferedImage enemy_Image_AirPlane;

	// ImageManager class HAS-A enemy_Image1_AirShip of BufferedImage type
	private BufferedImage enemy_Image1_AirShip;

	// ImageManager class HAS-A backGround_Image of BufferedImage type
	private BufferedImage backGround_Image;

	// ImageManager class HAS-A player_Image of BufferedImage type
	private BufferedImage player_Image;

	// ImageManager class HAS-A enemy_Image_Homing of BufferedImage type
	private BufferedImage enemy_Image_Homing;

	/**
	 * Purpose: Constructor for the ImageManeger class
	 * 
	 * @param no param
	 * 
	 */
	public ImageManeger()
	{
		// try-catch statement catch IOException and when catch it, exit system
		try
		{
			ImageEditor edit = new ImageEditor();

			// all BufferedImage object that the variable initially referenced
			// is overwritten,
			// so the reference is removed,
			// the object is subject to garbage collection, and memory is freed.

			player_Image = ImageIO.read(getClass().getResource("witch.png"));
			player_Image = edit.monoImage(player_Image);
			player_Image = edit.qualityReduce(player_Image, 2.5);

			enemy_Image_Homing = ImageIO
					.read(getClass().getResource("HomingEnemy.png"));
			enemy_Image_Homing = edit.monoImage(enemy_Image_Homing);
			enemy_Image_Homing = edit.qualityReduce(enemy_Image_Homing, 2);

			enemy_Image_AirPlane = ImageIO
					.read(getClass().getResource("enemy.png"));
			enemy_Image_AirPlane = edit.monoImage(enemy_Image_AirPlane);
			enemy_Image_AirPlane = edit.qualityReduce(enemy_Image_AirPlane,
					2.5);

			enemy_Image1_AirShip = ImageIO
					.read(getClass().getResource("AirShipEnemy.png"));
			enemy_Image1_AirShip = edit.monoImage(enemy_Image1_AirShip);
			enemy_Image1_AirShip = edit.qualityReduce(enemy_Image1_AirShip,
					3.2);

			backGround_Image = ImageIO
					.read(getClass().getResource("BackGround.png"));
			backGround_Image = edit.monoImage(backGround_Image);
			backGround_Image = edit.qualityReduce(backGround_Image, 4);
		}
		// if image does not exist display message and exit system
		catch (IOException e)
		{
			e.printStackTrace();
			System.out
					.println("Couldn't find image resources, Exit the system");
			System.exit(0);
		}
	}

	/**
	 * Purpose:getter to returns the enemy image of an airplane.
	 * 
	 * @param no param
	 * 
	 * @return a BufferedImage of an Airplane
	 */
	public BufferedImage getEnemyImageAirPlane()
	{
		return enemy_Image_AirPlane;
	}

	/**
	 * Purpose:getter to returns the enemy image of an AirShip.
	 * 
	 * @param no param
	 * 
	 * @return a BufferedImage of an AirShip
	 */
	public BufferedImage getEnemyImageAirShip()
	{
		return enemy_Image1_AirShip;
	}

	/**
	 * Purpose:getter to returns the enemy image of BackGroundImage.
	 * 
	 * @param no param
	 * 
	 * @return a BufferedImage of BackGroundImage
	 */
	public BufferedImage getBackGroundImage()
	{
		return backGround_Image;
	}

	/**
	 * Purpose:getter to returns the enemy image of PlayerImage.
	 * 
	 * @param no param
	 * 
	 * @return a BufferedImage of PlayerImage
	 */
	public BufferedImage getPlayerImage()
	{
		return player_Image;
	}

	/**
	 * Purpose:getter to returns the enemy image of EnemyHoming.
	 * 
	 * @param no param
	 * 
	 * @return a BufferedImage of an EnemyHoming
	 */
	public BufferedImage getEnemyHoming()
	{
		return enemy_Image_Homing;
	}

}
