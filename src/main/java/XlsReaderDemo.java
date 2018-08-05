import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class XlsReaderDemo {
    private static final Logger logger = LoggerFactory.getLogger(XlsReaderDemo.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\j9872\\Desktop\\test-data\\sales.xls";
        XlsReader xlsReader = new XlsReader();
        FileInputStream fileInputStream;
        try {
            fileInputStream = xlsReader.openFile(filePath);
        } catch (IOException e) {
            logger.debug("file not found {}", e.getMessage());
            return;
        }
        Workbook workbook = null;
        try {
            workbook = xlsReader.getWorkBook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = xlsReader.getSheet(workbook, 0);
        xlsReader.printCell(sheet);
    }
}
