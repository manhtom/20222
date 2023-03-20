import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:59:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputFile {
    InputStream fin;

    public InputFile() {
        fin = null;
    }

    public boolean openFile(String fn) {
        try {
            fin = new FileInputStream(fn);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String readLine() throws Exception {
        String line = "";
        char ch;

        try {
            ch = (char) fin.read();
        } catch (Exception e) {
            return null;
        }
        while ((ch != '\n') && (ch != -1)) {
            line += ch;
            try {
                ch = (char) fin.read();
            } catch (Exception e) {
                break;
            }
        }
        return line;
    }

    public void closeFile() {
        fin = null;
    }
}