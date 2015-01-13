package behavior;

import java.awt.Dimension;
import java.util.ArrayList;

import org.bytedeco.javacpp.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_highgui.*;

import moveset.ThreadManager;

public class ImageDetectionBehavior extends Behavior{
	public ImageDetectionBehavior(ThreadManager t){
		
	}
	
	public Dimension TemplateMatch(String base_image, ArrayList<String> templates, double threshold){
		Dimension rectangle = null;
		IplImage src = cvLoadImage(base_image);
		for(String template : templates){
			IplImage tmp = cvLoadImage(template, 0);
			
		}
		
		return rectangle;
	}
}
