package service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import service.BaseService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XLSServiceImpl implements BaseService {
    private HSSFWorkbook workbook;
    private HSSFSheet curSheet;

    @Override
    public Workbook getWorkBook() {
        return workbook;
    }

    @Override
    public void createWorkBook() {
        workbook = new HSSFWorkbook();
    }

    @Override
    public void createWorkBook(String[] sheets) {
        workbook = new HSSFWorkbook();
        for (String sheet : sheets) {
            workbook.createSheet(sheet);
        }
    }

    @Override
    public void addSheet(String sheet) {
        workbook.createSheet(sheet);
    }

    @Override
    public void removeSheet(String sheet) {
        workbook.removeSheetAt(workbook.getSheetIndex(sheet));
    }

    @Override
    public void readFile(File file) {
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readSheet(String sheet) {
        curSheet = workbook.getSheet(sheet);
    }

    @Override
    public void writeFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRow(Object[] columns) {
        int index = curSheet.getLastRowNum();
        HSSFRow row = curSheet.createRow(index + 1);
        int count = 0;
        for (Object column : columns) {
            addCell(row.createCell(count),column);
            count++;
        }
    }

    public void addCell(HSSFCell cell, Object o){
        if(o instanceof String){
            cell.setCellValue((String) o);
        }else if(o instanceof Boolean){
            cell.setCellValue((Boolean) o);
        }else if(o instanceof Double){
            cell.setCellValue((Double) o);
        }else if(o instanceof Integer){
            cell.setCellValue(String.valueOf((Integer) o));
        }else if(o instanceof Date){
            cell.setCellValue((Date) o);
        }else if(o instanceof Calendar){
            cell.setCellValue((Calendar) o);
        }else if(o instanceof LocalDate){
            cell.setCellValue((LocalDate) o);
        }else if(o instanceof LocalDateTime){
            cell.setCellValue((LocalDateTime) o);
        }else if (o instanceof RichTextString){
            cell.setCellValue((RichTextString) o);
        }
    }
    @Override
    public void insertRow(int index, Object[] columns) {
        curSheet.shiftRows(index,curSheet.getLastRowNum(),1);
        HSSFRow row = curSheet.createRow(index);
        int count = 0;
        for (Object column : columns) {
            addCell(row.createCell(count),column);
            count++;
        }
    }

    @Override
    public void insertRows(int start, int rows, List<Object[]> columns) {
        curSheet.shiftRows(start,curSheet.getLastRowNum(),rows);
        for (int i = 0; i < rows; i++) {
            HSSFRow row = curSheet.createRow(i+start);
            int count = 0;
            for (Object o : columns.get(i)) {
                addCell(row.createCell(count),o);
                count++;
            }
        }
    }

    @Override
    public String getCell(int row, int cell) {
        return curSheet.getRow(row).getCell(cell).toString();
    }

    @Override
    public Cell getCellObject(int row, int cell) {
        return curSheet.getRow(row).getCell(cell);
    }

    @Override
    public String[] getRow(int row) {
        HSSFRow row1 = curSheet.getRow(row);
        int first = row1.getFirstCellNum();
        int last = row1.getLastCellNum();
        int count = last - first + 1;
        String[] cells = new String[count];
        for(int i = 0;i<=count;i++){
            cells[i] = row1.getCell(i+first).toString();
        }

        return cells;
    }

    @Override
    public void removeCell(int row, int cell) {
        curSheet.getRow(row).removeCell(curSheet.getRow(row).getCell(cell));
    }

    @Override
    public void removeRow(int row) {
        curSheet.removeRow(curSheet.getRow(row));
    }

    @Override
    public void batchProcess(BatchHandler handler) {
        int first = curSheet.getFirstRowNum();
        int last = curSheet.getLastRowNum();
        for(int i=first;i<=last;i++){
            handler.doHandler(i,curSheet.getRow(i));
        }
    }
}
