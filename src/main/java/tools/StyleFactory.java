package tools;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.Map;

public class StyleFactory {

    private  static Map<Short,CellStyle> foregroundColors = new HashMap<>();

    public static void setForegroundColor(Workbook workbook, Cell cell, short color){
        if(!foregroundColors.containsKey(color)){
            CellStyle style = workbook.createCellStyle();
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(color);
            foregroundColors.put(color,style);
            cell.setCellStyle(style);
        }else{
            cell.setCellStyle(foregroundColors.get(color));
        }
    }
}
