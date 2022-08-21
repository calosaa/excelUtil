import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import service.BaseService;
import service.impl.XLSServiceImpl;
import service.impl.XLSXServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class RenshiExcel {
    public static final String path = "E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\input\\";
    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File(path+"2. 关于档案材料出生日期不一致的说明.docx");
        File file2 = new File(path+"干部人事档案专项审核情况登记表及填表说明.doc");
        File file3 = new File(path+"干部人事档案专项审核认定表.doc");
        File list = new File("C:\\Users\\86136\\Desktop\\人事档案\\xqz");
        if (!list.isDirectory()) {
            System.out.println("error");
            return;
        }
        File excel = new File(path+"bbb.xlsx");
        BaseService service = new XLSXServiceImpl();
        service.readFile(excel);
        service.readSheet("Sheet2");
//        System.out.println(service.getCell(22, 0));
//        System.out.println(service.getCell(22, 6));
        service.batchProcess(new BaseService.BatchHandler() {
            @Override
            public void doHandler(int index, Row row) {
                System.out.println(index);
                Cell type = row.getCell(6);
                Cell name = row.getCell(0);
                if (index>0 && type!=null && "生日不符".equals(type.toString())) {
                    System.out.println(name.toString());
                    String staffPath = "C:\\Users\\86136\\Desktop\\人事档案\\xqz\\"+name.toString();
                    File staff = new File(staffPath);
                    staff.mkdir();
                    try {
                        Files.copy(file1.toPath(),new File(staffPath+"\\2. 关于档案材料出生日期不一致的说明.docx").toPath());
                        Files.copy(file2.toPath(),new File(staffPath+"\\干部人事档案专项审核情况登记表及填表说明.doc").toPath());
                        Files.copy(file3.toPath(),new File(staffPath+"\\干部人事档案专项审核认定表.doc").toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    finally {
                        System.out.println("do success");
                    }


                }
            }
        });

    }
}
