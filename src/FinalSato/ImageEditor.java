package FinalSato;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;

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

public class ImageEditor
{
	public BufferedImage monoImage(BufferedImage image) throws IOException
	{
		// Create a ColorSpace object to grayscale
		ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);

		// Create a ColorConvertOp object using the object above
		ColorConvertOp colorConverter = new ColorConvertOp(colorSpace, null);

		// Create a new BufferedImage object have same width and height
		// as the original image given with an ARGB color, which is
		// applied
		// background transparency.
		BufferedImage grayImage = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// use filter method of colorConverter to copy given image but
		// grayscaled
		colorConverter.filter(image, grayImage);
		// return grayscale image
		return grayImage;
	}

	public BufferedImage qualityReduce(BufferedImage originalimage,
			double divedeBy) throws IOException
	{
		// Get the width and height of the given image
		int width = originalimage.getWidth();
		int height = originalimage.getHeight();

		// Create a new BufferedImage object with
		// the height and width of given image devided by given double
		// and an ARGB color
		BufferedImage scaledImage = new BufferedImage((int) (width / divedeBy),
				(int) (height / divedeBy), BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object from the image above
		Graphics2D g2d = scaledImage.createGraphics();

		// apply bi-order completion, a completion that improves image quality
		// when scaling images. The result is very beautiful when this is
		// applied.
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		// Draw the given original image on the the scaledImage
		g2d.drawImage(originalimage, 0, 0, (int) (width / divedeBy),
				(int) (height / divedeBy), null);

		// dispose g2d create with scaledImage is dipsosing here
		g2d.dispose();

		// Create a new BufferedImage object with same width and height as the
		// original
		// and an ARGB color
		BufferedImage enlargedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object from the image above
		g2d = enlargedImage.createGraphics();

		// apply bi-order completion, a completion that improves image quality
		// when scaling images. The result is very beautiful when this is
		// applied.
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

		// Draw the given original image on the the enlargedImage
		g2d.drawImage(scaledImage, 0, 0, width, height, null);

		// dispose second g2d create with enlargedImage is disposing here.
		g2d.dispose();

		return enlargedImage;
	}

	/**
	 * Purpose:Raise the player's Y-coordinate with the assignment operator
	 * 
	 * @param no param
	 * 
	 * @return void
	 */
	public BufferedImage flipHorizontally(BufferedImage image)
	{
		// scale image by -1 on the x-direction and 1 in the y-direction, flip
		// horizontally
		AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);

		// Translate the width of the image to nagative
		affineTransform.translate(-image.getWidth(null), 0);

		// Create an AffineTransformOp object using the object above
		AffineTransformOp op = new AffineTransformOp(affineTransform,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		// use filter method of AffineTransformOp generate flipped image and
		// return it.
		return op.filter(image, null);
	}
}
