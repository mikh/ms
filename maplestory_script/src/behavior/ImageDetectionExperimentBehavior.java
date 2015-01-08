package behavior;

import java.nio.ByteBuffer;

import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import moveset.ThreadManager;

public class ImageDetectionExperimentBehavior extends Behavior {
	public ImageDetectionExperimentBehavior(ThreadManager t){
		match_images();
	}
	
	public void match_images(){
		IplImage src = cvLoadImage("Images/Maple0003.jpg",0);
		IplImage tmp = cvLoadImage("Images/search_images/golem1.jpg",0);	
			
		IplImage result = cvCreateImage(cvSize(src.width()-tmp.width()+1, src.height()-tmp.height()+1), IPL_DEPTH_32F, 1);
//		cvShowImage("Lena Image", result);
		cvZero(result);
		 
		//Match Template Function from OpenCV
		//cvShowImage("src", src);
		//cvShowImage("tmp", tmp);
		
		cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED);
		cvShowImage("result4", result);	
		DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
		 
		CvPoint minLoc = new CvPoint();
		CvPoint maxLoc = new CvPoint();
		 
		//Get the Max or Min Correlation Value	
		ByteBuffer buffer = result.getByteBuffer();
		double img_vec[][] = new double[rows][cols];
		for (int i=0; i < rows; i++) {
		    for (int j =0; j < cols; j++){
		        int ind = i * img.widthStep() + j * img.nChannels() + 1;
		        img_vec[i][j] = (buffer.get(ind) & 0xFF);
		    }
		}
		cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);
		 
		System.out.println(min_val.toString());
		System.out.println(max_val.toString());
		//System.out.println(Arrays.toString(min_val));
		//System.out.println(Arrays.toString(max_val));
				
		CvPoint point = new CvPoint();
		point.x(maxLoc.x()+tmp.width());
		point.y(maxLoc.y()+tmp.height());
		 
		cvRectangle(src, maxLoc, point, CvScalar.WHITE, 2, 8, 0);//Draw a Rectangle for Matched Region
		 
		cvShowImage("Lena Image", src);
		cvWaitKey(0);
		cvReleaseImage(src);
		cvReleaseImage(tmp);
		cvReleaseImage(result);
	}
}
