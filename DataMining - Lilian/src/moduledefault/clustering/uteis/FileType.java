/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author marcelo-note
 */
public class FileType extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(".ydt");
    }

    //Set description for the type of file that should be display  
    @Override
    public String getDescription() {
        return "*.ydt(YADMT)";
    }

}
