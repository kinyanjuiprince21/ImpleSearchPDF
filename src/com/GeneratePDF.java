package com;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;


public class GeneratePDF {
    public static void exportPDF(ObservableList<Person> people) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("people.pdf");
        File file = fileChooser.showSaveDialog(null);
        if(file == null) {
            return;
        }
        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Paragraph paragraph = new Paragraph("Data for People");
        paragraph.setBold().setFontSize(15).setMarginBottom(20.0f);

        float[] colWidth = {200f,200f,200f};
        Table table = new Table(colWidth);

        table.addCell(new Cell().add(new Paragraph("name")));
        table.addCell(new Cell().add(new Paragraph("age")));
        table.addCell(new Cell().add(new Paragraph("gender")));
        people.forEach(person -> {
            table.addCell(new Cell().add(new Paragraph(person.getName())));
            table.addCell(new Cell().add(new Paragraph(person.getAge() + "")));
            table.addCell(new Cell().add(new Paragraph(person.getGender())));
        });
        document.add(paragraph);
        document.add(table);
        document.close();
    }
}
