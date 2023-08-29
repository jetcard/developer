package com.company.inventory.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.company.inventory.model.Category;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Category> category;
	
	
	public CategoryExcelExporter (List<Category> categories) {
		this.category = categories;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Reporte de códigos de cuotas generadas al  ");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(11);
		style.setFont(font);
		
		createCell(row, 0, "N°", style);
		createCell(row, 1, "CÓDIGO", style);
		createCell(row, 2, "Moneda", style);
		createCell(row, 3, "N° CUOTA", style);
		createCell(row, 4, "FECHA DESEMBOLSO", style);
		createCell(row, 5, "FECHA DE PAGO", style);
		createCell(row, 6, "CAPITAL ADEUDADO CRONOGRAMA", style);
		createCell(row, 7, "TEA", style);
		createCell(row, 8, "DÍAS TRANSC AL CIERRE", style);
		createCell(row, 9, "INTERÉS COMPENSATORIO", style);
		createCell(row, 10, "INTERÉS PROVISIÓN", style);
		createCell(row, 11, "IGV", style);
		createCell(row, 12, "TOTAL", style);
		
	}
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		
		if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if(value instanceof Date) {
			cell.setCellValue(new Date());
		} else {
			cell.setCellValue((String) value);
		}
		
		cell.setCellStyle(style);
		
	}
	
	
	private void writeDataLines() {
		
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(10);
		style.setFont(font);

		CellStyle styleDate = workbook.createCellStyle();
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		styleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

		for( Category result: category) {
			
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, String.valueOf(result.getId()), style);
			createCell(row, columnCount++, result.getCodigo(), style);
			createCell(row, columnCount++, result.getMoneda(), style);
			createCell(row, columnCount++, result.getNroCuota(), style);
			createCell(row, columnCount++, result.getFechaDesembolso(), styleDate);
			createCell(row, columnCount++, result.getFechaPago(), styleDate);
			createCell(row, columnCount++, result.getMonto(), style);
			createCell(row, columnCount++, result.getTea(), style);
			createCell(row, columnCount++, result.getInteresCompensatorio(), style);
			createCell(row, columnCount++, result.getInteresProvision(), style);
			createCell(row, columnCount++, result.getIgv(), style);
			createCell(row, columnCount++, result.getDiasTranscurridos(), style);
			createCell(row, columnCount++, result.getTotal(), style);
		}
	}
	
	
	public void export(HttpServletResponse response) throws IOException {
		
		writeHeaderLine(); //write the header
		writeDataLines(); //write the data
		
		ServletOutputStream servletOutput = response.getOutputStream();
		workbook.write(servletOutput);
		workbook.close();
		
		servletOutput.close();
		
		
	}
}
