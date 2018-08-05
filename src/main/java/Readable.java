import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface Readable {
    FileInputStream openFile(String path) throws FileNotFoundException;
    void read();
}
