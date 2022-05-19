package DataAccess;

import BusinessLogic.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileWriter {
    public FileWriter() {
    }

    public void makeBill(Order order) {
        Document doc = new Document();
        try {
            //generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Bill_" + order.getOrderId() + ".pdf"));
            //opens the PDF
            doc.open();
            //adds paragraph to the PDF file
            doc.add(new Paragraph(order + ""));
            //close the PDF file
            doc.close();
            //closes the writer
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fileWrite(String path, String message) {
        Document doc = new Document();
        try {
            //generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
            //opens the PDF
            doc.open();
            //adds paragraph to the PDF file
            doc.add(new Paragraph(message));
            //close the PDF file
            doc.close();
            //closes the writer
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
