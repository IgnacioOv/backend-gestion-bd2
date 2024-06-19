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
            Paragraph estadoProyectoTitle = new Paragraph("Estado del proyecto")
                    .setFontSize(18)  // Tamaño de la fuente
                    .setBold()        // Texto en negrita
                    .setUnderline();  // Subrayado
            document.add(estadoProyectoTitle);
            document.add(new Paragraph("Nombre del proyecto: " + project.getName()));
            document.add(new Paragraph("Descripción: " + project.getDescription()));
            document.add(new Paragraph("Estado: " + project.getStatus() + "%" ));
            document.add(new Paragraph("Fecha de inicio: " + project.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            document.add(new Paragraph("Fecha de fin: " + project.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            // Aquí debes definir el formato específico para la información del proyecto

            // Información de tareas
            Paragraph infoTareasTitle = new Paragraph("Información de tareas")
                    .setFontSize(16)  // Tamaño de la fuente
                    .setBold()        // Texto en negrita
                    .setUnderline();  // Subrayado
            document.add(infoTareasTitle);
            for (Tasks task : tasks) {
                Paragraph tareaInfo = new Paragraph("Nombre de la tarea: " + task.getName() +
                        " : " + task.getStatus() + "% y Responsable: " + task.getUser().getName())
                        .setMarginLeft(20);  // Indentación para mayor claridad
                document.add(tareaInfo);
            }

            // Personal Asignado Al proyecto
            Paragraph personalTitle = new Paragraph("Personal Asignado Al proyecto")
                    .setFontSize(16)  // Tamaño de la fuente
                    .setBold()        // Texto en negrita
                    .setUnderline();  // Subrayado
            document.add(personalTitle);
            for (Users user : users) {
                Paragraph usuarioInfo = new Paragraph("Empleado: " + user.getName() + " " + user.getLast_name() +
                        " - (" + user.getEmail() + ")")
                        .setMarginLeft(20);  // Indentación para mayor claridad
                document.add(usuarioInfo);
            }

            // Listado de comentarios
            Paragraph comentariosTitle = new Paragraph("Listado de comentarios")
                    .setFontSize(16)  // Tamaño de la fuente
                    .setBold()        // Texto en negrita
                    .setUnderline();  // Subrayado
            document.add(comentariosTitle);
            for (Comments comment : comments) {
                Paragraph comentarioInfo = new Paragraph("Comentario: " + comment.getComment() + " - (" + comment.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")")
                        .setMarginLeft(20);  // Indentación para mayor claridad
                document.add(comentarioInfo);
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
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Reporte del Proyecto");

            // Estilo para los títulos
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);

            // Estado del proyecto
            Row estadoProyectoRow = sheet.createRow(0);
            Cell estadoProyectoCell = estadoProyectoRow.createCell(0);
            estadoProyectoCell.setCellValue("Estado del proyecto");
            estadoProyectoCell.setCellStyle(titleStyle);

            Row nombreProyectoRow = sheet.createRow(1);
            nombreProyectoRow.createCell(0).setCellValue("Nombre del proyecto:");
            nombreProyectoRow.createCell(1).setCellValue(project.getName());

            Row descripcionRow = sheet.createRow(2);
            descripcionRow.createCell(0).setCellValue("Descripción:");
            descripcionRow.createCell(1).setCellValue(project.getDescription());

            Row estadoRow = sheet.createRow(3);
            estadoRow.createCell(0).setCellValue("Estado:");
            estadoRow.createCell(1).setCellValue(project.getStatus() + "%");

            Row fechaInicioRow = sheet.createRow(4);
            fechaInicioRow.createCell(0).setCellValue("Fecha de inicio:");
            fechaInicioRow.createCell(1).setCellValue(project.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            Row fechaFinRow = sheet.createRow(5);
            fechaFinRow.createCell(0).setCellValue("Fecha de fin:");
            fechaFinRow.createCell(1).setCellValue(project.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            // Información de tareas
            int rowNum = 7;
            Row infoTareasRow = sheet.createRow(rowNum++);
            Cell infoTareasCell = infoTareasRow.createCell(0);
            infoTareasCell.setCellValue("Información de tareas");
            infoTareasCell.setCellStyle(titleStyle);

            for (Tasks task : tasks) {
                Row tareaRow = sheet.createRow(rowNum++);
                tareaRow.createCell(0).setCellValue("Nombre de la tarea: " + task.getName());
                tareaRow.createCell(1).setCellValue("Porcentaje de tarea: " + task.getStatus() + "%");
                tareaRow.createCell(2).setCellValue("Responsable: " + task.getUser().getName());
            }

            // Personal asignado al proyecto
            Row personalRow = sheet.createRow(rowNum++);
            Cell personalCell = personalRow.createCell(0);
            personalCell.setCellValue("Personal asignado al proyecto");
            personalCell.setCellStyle(titleStyle);

            for (Users user : users) {
                Row usuarioRow = sheet.createRow(rowNum++);
                usuarioRow.createCell(0).setCellValue("Empleado: " + user.getName() + " " + user.getLast_name());
                usuarioRow.createCell(1).setCellValue("Email: " + user.getEmail());
            }

            // Listado de comentarios
            Row comentariosRow = sheet.createRow(rowNum++);
            Cell comentariosCell = comentariosRow.createCell(0);
            comentariosCell.setCellValue("Listado de comentarios");
            comentariosCell.setCellStyle(titleStyle);

            for (Comments comment : comments) {
                Row comentarioRow = sheet.createRow(rowNum++);
                comentarioRow.createCell(0).setCellValue("Comentario: " + comment.getComment());
                comentarioRow.createCell(1).setCellValue("Fecha: " + comment.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }

            // Ajustar anchos de columnas
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(baos);
            workbook.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo Excel", e);
        }

    }
}
