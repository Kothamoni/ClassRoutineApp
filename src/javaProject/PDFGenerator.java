package javaProject;
//student
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	  PDFGenerator(){
  try {
      String file_name ="C:\\Users\\mutta\\Desktop\\CRApp_pdf\\routineA.pdf";
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(file_name));
      document.open();

      Paragraph para = new Paragraph("Routine of Section A");
      document.add(para);

      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
      String query = "SELECT * FROM routinea";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();

      PdfPTable table = new PdfPTable(6);

      // Add column headers to the table
      table.addCell("Day");
      table.addCell("08.30");
      table.addCell("10.15");
      table.addCell("Break");
      table.addCell("14.00");
      table.addCell("15.45");

      while (rs.next()) {
          // Add data from the database to the table
          table.addCell(rs.getString(1));
          table.addCell(rs.getString(2));
          table.addCell(rs.getString(3));
          table.addCell(rs.getString(4));
          table.addCell(rs.getString(5));
          table.addCell(rs.getString(6));
      }

      document.add(table);

      statement.close();
      connection.close();
      document.close();
  } catch (Exception e) {
      e.printStackTrace();
  }
  System.out.println("tata");
}


public static void main(String[] args) {
new PDFGenerator();
}
}
