package com.uade.backendgestionbd2.service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.uade.backendgestionbd2.model.Comments;
import com.uade.backendgestionbd2.model.Projects;
import com.uade.backendgestionbd2.model.Tasks;
import com.uade.backendgestionbd2.model.Users;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;

@Service
public class FilesService {

    public byte[] generateProjectReportPDF(Projects project, List<Tasks> tasks, List<Users> users, List<Comments> comments) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Estado del proyecto
            document.add(new Paragraph("** Estado del proyecto **"));
            document.add(new Paragraph(project.toString())); // Reemplaza con el formato que desees

            // Información de tareas
            document.add(new Paragraph("** Información de tareas **"));
            for (Tasks task : tasks) {
                document.add(new Paragraph("Nombre de la tarea: " + task.getName() + " : " + task.getStatus() + "% y Responsable: " + task.getUser().getName()));
            }

            // Personal Asignado Al proyecto
            document.add(new Paragraph("** Personal Asignado Al proyecto **"));
            for (Users user : users) {
                document.add(new Paragraph(user.getName()));
            }

            // Listado de comentarios
            document.add(new Paragraph("** Listado de comentarios **"));
            for (Comments comment : comments) {
                document.add(new Paragraph(comment.toString())); // Reemplaza con el formato que desees
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }


    public byte[] generateProjectReportExcel(Projects project, List<Tasks> tasks, List<Users> users, List<Comments> comments) {
        // Implementación de la generación de un archivo Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            // Crear una nueva hoja de Excel
            Sheet sheet = workbook.createSheet("Project Report");

            // Estilo para las cabeceras
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);

            // Contador para las filas
            int rowNum = 0;

            // Estado del proyecto
            Row projectRow = sheet.createRow(rowNum++);
            projectRow.createCell(0).setCellValue("Estado del proyecto");
            // Aquí puedes agregar más celdas con la información específica del proyecto
             projectRow.createCell(1).setCellValue(project.getName());
            projectRow.createCell(2).setCellValue(project.getDescription());
            projectRow.createCell(3).setCellValue(project.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            projectRow.createCell(4).setCellValue(project.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            projectRow.createCell(5).setCellValue(project.getStatus());

            // Información de tareas
            Row tasksHeaderRow = sheet.createRow(rowNum++);
            tasksHeaderRow.createCell(0).setCellValue("Información de tareas");
            // Crear celdas para cada tarea
            for (Tasks task : tasks) {
                Row taskRow = sheet.createRow(rowNum++);
                taskRow.createCell(0).setCellValue("Nombre de la tarea: " + task.getName());

                // Puedes agregar más detalles de la tarea, como el porcentaje y el responsable
                 taskRow.createCell(1).setCellValue("Procetaje de Completitud:" + task.getStatus() + "%" );
                taskRow.createCell(2).setCellValue("Responsable: " + task.getUser().getName() + " " + task.getUser().getLast_name());
            }

            // Personal asignado al proyecto
            Row usersHeaderRow = sheet.createRow(rowNum++);
            usersHeaderRow.createCell(0).setCellValue("Personal asignado al proyecto");
            // Crear celdas para cada usuario asignado
            for (Users user : users) {
                Row userRow = sheet.createRow(rowNum++);
                userRow.createCell(0).setCellValue(user.getUsername() + " " + user.getLast_name());
            }

            // Listado de comentarios
            Row commentsHeaderRow = sheet.createRow(rowNum++);
            commentsHeaderRow.createCell(0).setCellValue("Listado de comentarios");
            // Crear celdas para cada comentario
            for (Comments comment : comments) {
                Row commentRow = sheet.createRow(rowNum++);
                commentRow.createCell(0).setCellValue(comment.getComment());
            }

            // Ajustar automáticamente el ancho de las columnas
            for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Convertir el libro de Excel a bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de excepciones si ocurre algún problema al crear el archivo Excel
            throw new RuntimeException("Error al generar el reporte en Excel: " + e.getMessage());
        }

    }
}
