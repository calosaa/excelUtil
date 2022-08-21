package service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

public interface BaseService {
    void createWorkBook();
    void createWorkBook(String[] sheets);

    Workbook getWorkBook();
    void addSheet(String sheet);
    void removeSheet(String sheet);

    void readFile(File file);
    void readSheet(String sheet);
    void writeFile(File file);

    void addRow(Object[] columns);
    void insertRow(int index, Object[] columns);
    void insertRows(int start,int rows,List<Object[]> columns);

    String getCell(int row,int cell);

    Cell getCellObject(int row,int cell);
    String[] getRow(int row);

    void removeCell(int row,int cell);
    void removeRow(int row);

    void batchProcess(BatchHandler handler);

    interface BatchHandler{
        void doHandler(int index,Row row);  // index/row start with 0
    }

}
