package paint;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = f.getName();
		  if (extension.lastIndexOf('.') > 0) {
			  extension = extension.replaceAll(".*\\.", "");
		  }
        if (extension != null) {
            if (extension.equals("tiff") ||
                extension.equals("tif") ||
                extension.equals("gif") ||
                extension.equals("jpeg") ||
                extension.equals("jpg") ||
                extension.equals("png")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Obrazki";
    }
}

