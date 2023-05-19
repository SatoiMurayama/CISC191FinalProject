package FinalSato;
import java.awt.Graphics;
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

public interface Enemy
{

	/**
	 * Purpose: invoke to set enemy on the screen
	 * 
	 * @param no param
	 * @return void
	 */

	public void appear();

	/**
	 * Purpose: invoke in every frame to draw image of enemy
	 * 
	 * @param Graphic object and ImageObserver object would be used togather to
	 *                draw image of enemy on the screen
	 * @return void
	 */

	public void drawing(Graphics g, ImageObserver io);

	/**
	 * Purpose: invoke in every frame to check whether game is over or keep it
	 * run,
	 * by determine if the enemy and player have collided
	 * 
	 * @param int type array Player's collision data
	 * @return boolean
	 */

	public boolean enemyCollision(int[] pCollision);
}
