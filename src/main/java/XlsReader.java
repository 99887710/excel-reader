import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XlsReader implements Readable{
    private static final Logger logger = LoggerFactory.getLogger(XlsReader.class);

    @Override
    public FileInputStream openFile(String path) throws FileNotFoundException {
        FileInputStream file;
        try {
            file = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        logger.debug("read file successfully");
        return file;
    }

    @Override
    public void read() {

    }

    Workbook getWorkBook(FileInputStream file) throws IOException {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
        logger.debug("get work book successfully");
        return workbook;
    }

    Sheet getSheet(Workbook workbook, int index) {
        return workbook.getSheetAt(0);
    }

    void printCell(Sheet sheet) {
        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getArrayFormulaRange());
                        break;
                    default: data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        }
    }
}
