package clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Clip {
	public static String getTextOnClipboard(){
		try{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			return (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch(IOException | UnsupportedFlavorException e){
			e.printStackTrace();
		}
		return "";
	}
}
