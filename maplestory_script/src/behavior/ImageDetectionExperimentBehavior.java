package behavior;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.bytedeco.javacpp.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_highgui.*;

import moveset.ThreadManager;

public class ImageDetectionExperimentBehavior extends Behavior {
	public ImageDetectionExperimentBehavior(ThreadManager t){
		match_images();
	}
	
	public void match_images(){
		IplImage src = cvLoadImage("Images/Maple0000.jpg",0);
		IplImage tmp = cvLoadImage("Images/search_images/character.jpg",0);	
			
		IplImage result = cvCreateImage(cvSize(src.width()-tmp.width()+1, src.height()-tmp.height()+1), IPL_DEPTH_32F, 1);
//		cvShowImage("Lena Image", result);
		cvZero(result);
		 
		//Match Template Function from OpenCV4
		//cvShowImage("src", src);
		//cvShowImage("tmp", tmp);
		
		cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED);
		cvShowImage("result4", result);	
		cvThreshold(result, result, 0.94, 1, CV_THRESH_TOZERO);
		cvShowImage("result5", result);	
	//	cvShowImage("result4", result);	
		DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
		 
        //double min_val[] = new double[1];
        
		CvPoint minLoc = new CvPoint();
		CvPoint maxLoc = new CvPoint();
		 
		//Get the Max or Min Correlation Value	
	/*	ByteBuffer buffer = result.asByteBuffer();
		int rows = result.height(), cols = result.width();
		double img_vec[][] = new double[rows][cols];
		for (int i=0; i < rows; i++) {
		    for (int j =0; j < cols; j++){
		        int ind = i * result.widthStep() + j * result.nChannels() + 1;
		        img_vec[i][j] = (buffer.get(ind) & 0xFF);
		    }
		}
		
		//img_vec = addValues(img_vec, 3);
		
		findMinMax(img_vec);
		writeArray(img_vec, "values.txt");
		*/
		cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);
		
		/*if(max_val.get() >= .8){
			System.out.println("Pass");
		}*/
		
		System.out.println(String.format("Software found max at (%d, %d) and min at (%d, %d)", maxLoc.x(), maxLoc.y(), minLoc.x(), minLoc.y()));
		System.out.println(min_val.toString());
		System.out.println(max_val.toString());
		//System.out.println(Arrays.toString(min_val));
		//System.out.println(Arrays.toString(max_val));
				
		CvPoint point = new CvPoint();
		point.x(maxLoc.x()+tmp.width());
		point.y(maxLoc.y()+tmp.height());
		 
		cvRectangle(src, maxLoc, point, CvScalar.WHITE, 2, 8, 0);//Draw a Rectangle for Matched Region
		 
	//	cvShowImage("Lena Image", src);
		cvWaitKey(0);
		cvReleaseImage(src);
		cvReleaseImage(tmp);
		cvReleaseImage(result);
	}
	
	private void findMinMax(double img_vec[][]){
		int rows = img_vec.length, cols = 0;
		if(rows > 0)
			cols = img_vec[0].length;
		double max = 0, min = 99999999;
		int num_maxs = 0, num_mins = 0;
		Point min_index = new Point(-1,-1), max_index = new Point(-1,-1);
		for(int ii = 0; ii < rows; ii++){
			for(int jj = 0; jj < cols; jj++){
				if(img_vec[ii][jj] > max){
					max = img_vec[ii][jj];
					max_index = new Point(ii,jj);
					num_maxs = 1;
				} else if(img_vec[ii][jj] == max)
					num_maxs++;
				if(img_vec[ii][jj] < min){
					min = img_vec[ii][jj];
					min_index = new Point(ii, jj);
					num_mins = 0;
				} else if(img_vec[ii][jj] == min)
					num_mins++;
			}
		}
		System.out.println(String.format("Max = %.0f at (%d,%d) %d maxs found   Min = %.0f at (%d,%d) %d mins found", max, max_index.x(), max_index.y(), num_maxs, min, min_index.x(), min_index.y(), num_mins));
	}
	
	private double[][] addValues(double img_vec[][], int level){
		double new_vec[][] = new double[img_vec.length][img_vec[0].length];
		for(int ii = 0; ii < img_vec.length; ii++){
			for(int jj = 0; jj < img_vec[ii].length; jj++){
				double value = img_vec[ii][jj];
				for(int kk = 1; kk <= level; kk++){
					value += PointCheck(img_vec, new Point(ii+kk, jj));
					value += PointCheck(img_vec, new Point(ii, jj+kk));
					value += PointCheck(img_vec, new Point(ii-kk, jj));
					value += PointCheck(img_vec, new Point(ii, jj-kk));
					value += PointCheck(img_vec, new Point(ii+kk, jj+kk));
					value += PointCheck(img_vec, new Point(ii-kk, jj+kk));
					value += PointCheck(img_vec, new Point(ii-kk, jj-kk));
					value += PointCheck(img_vec, new Point(ii+kk, jj-kk));
				}
				new_vec[ii][jj] = value;
			}
		}	
		
		return new_vec;
	}
	
	private double PointCheck(double img_vec[][], Point p){
		if(p.x() >= 0 && p.y() >= 0 && p.x() < img_vec.length && p.y() < img_vec[0].length){
			return img_vec[p.x()][p.y()];
		}
		return 0;
	}
	
	private void writeArray(double img_vec[][], String filename){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
			String line = "";
			for(int ii = 0; ii < img_vec.length; ii++){
				line = "";
				for(int jj = 0; jj < img_vec[ii].length; jj++){
					line += String.format("%3.0f\t", img_vec[ii][jj]);
				}
				line += "\n";
				bw.write(line);
			}
			
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
