import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDetails {
	
	// documentation @ http://www.javaxt.com/javaxt-core/javaxt.io.Image

	public static void main(String[] args) {
		// you will need to change the path but dataImg will be included in the repo
		String sampleDataImg ="C:/Users/debuiteb/Pictures/dataimg.jpg";
		javaxt.io.Image image = new javaxt.io.Image(sampleDataImg);
		System.out.println("width   height");
		System.out.println("--------------");
		System.out.println(image.getWidth() + "  x  " + image.getHeight());
		System.out.println();
		System.out.println();
		
		java.util.HashMap<Integer, Object> exif = image.getExifTags();
		//Print Camera Info
		// hex values in the get methods below found @ http://www.awaresystems.be/imaging/tiff/tifftags/privateifd/exif.html
		  System.out.println("EXIF Fields: " + exif.size());
		  System.out.println("-----------------------------");
		  System.out.println("Date: " + exif.get(0x0132)); //0x9003       
		  System.out.println("Camera: " + exif.get(0x0110));
		  System.out.println("Manufacturer: " + exif.get(0x010F));
		  System.out.println("Focal Length: " + exif.get(0x920A));
		  System.out.println("F-Stop: " + exif.get(0x829D));
		  System.out.println("Exposure Time (1 / Shutter Speed): " + exif.get(0x829A));
		  System.out.println("ISO Speed Ratings: " + exif.get(0x8827));
		  System.out.println("Shutter Speed Value (APEX): " + exif.get(0x9201));
		  System.out.println("Shutter Speed (Exposure Time): " + exif.get(0x9201));
		  System.out.println("Aperture Value (APEX): " + exif.get(0x9202));
		  
		  //
		  double[] coord = image.getGPSCoordinate();
		  if (coord!=null){
		      System.out.println("GPS Coordinate: " + coord[0] + ", " + coord[1]);
		      System.out.println("GPS Datum: " + image.getGPSDatum());
		  }
	}

}
