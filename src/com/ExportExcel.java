package com;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportExcel {
    public static void exportExcel(TableView<?> tableView) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow colNames = sheet.createRow(0);
        int colNums = tableView.getColumns().size();

        for(int i = 0; i < colNums; i++){
            colNames.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
        }

        for(int i = 0; i < tableView.getItems().size(); i++){
            HSSFRow row = sheet.createRow(i + 1);
            for(int j=0; j < colNums; j++){
                row.createCell(j).setCellValue(String.valueOf(tableView.getColumns().get(j).getCellObservableValue(i).getValue()));
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("people.xls");
        File file = fileChooser.showSaveDialog(null);
        if(file != null) {
            try {
                workbook.write(new FileOutputStream(file.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
