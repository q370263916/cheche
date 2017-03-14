package com.chechemotel.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 导出Excel工具类
 * @author zhouzhiyuan
 * 
 * <ul>
 * <li><b>example : <b></li>
 * <li>ExportExcelUtils<OrderVo> exportExcelUtils = new ExportExcelUtils<OrderVo>(data, "/home/", "excel", 1)</li>
 * <li>exportExcelUtils.createWorkbook();</li>
 * <li>String[] fieldName = {"订单号", "订单名称"};</li>
 * <li>String[] valueName = {"orderId", "orderName"};</li>
 * <li>exportExcelUtils.exportExcel(fieldName, valueName);</li>
 * 
 */
public class ExcelOutPutUtil<T> {
	
	private static final Logger logger = Logger.getLogger(ExcelOutPutUtil.class);
	
	/**
	 * 导出至文件的数据
	 */
	private List<T> data;
	/**
	 * 导出路径(不包含文件名)
	 */
	private String path;
	/**
	 * 导出文件名(不包含后缀)
	 */
	private String fileName;
	/**
	 * 导出文件后缀. 1.cls  2.csv
	 */
	private String suffix;
	
	/**
	 * 文件工作簿
	 */
	private HSSFWorkbook workbook;

	public ExcelOutPutUtil(List<T> data, String path, String fileName, Integer suffix){
		this.data = data;
		this.path = path;
		this.fileName = fileName;
		
		
		switch(suffix){
		case 1: this.suffix = ".xls"; break;
		case 2: this.suffix = ".csv"; break;
		default: this.suffix = ".xls"; break;
		}
		
		workbook = null;
	}
	
	public ExcelOutPutUtil(){
		this(null, null, null, 1);
	}
	
	/**
	 * 创建表格工作簿
	 * @return
	 */
	public Boolean createWorkbook(){
		if(data == null || StringUtils.isBlank(fileName) || StringUtils.isBlank(path) || StringUtils.isBlank(suffix)){
			logger.error("ExportExcelUtils - 参数验证失败(data, fileName, path, suffix)");
			return false;
		}
		workbook = new HSSFWorkbook();
		return true;
	}
	
	/**
	 * 导出表格
	 * 
	 * @param fieldName
	 * 		表头名
	 * @return
	 */
	public Boolean exportExcel(String[] fieldName, String[] valueName){
		
		//1. 参数验证
		if(fieldName == null || valueName == null){
			logger.error("ExportExcelUtils(writeExcel) - 参数验证失败, 有参数为空");
			return false;
		}
		
		//2. 必要属性验证
		if(workbook == null){
			logger.error("ExportExcelUtils(writeExcel) - 没有创建workbook");
			return false;
		}
		if(data.isEmpty()){
			logger.error("ExportExcelUtils(writeExcel) - 没有导入数据");
			return false;
		}
		if(fieldName.length != valueName.length){
			logger.error("ExportExcelUtils(writeExcel) - 表头和数据数量不等.");
			return false;
		}
		
		//3. 创建表格SHEET
		HSSFSheet sheet = workbook.createSheet();
		
		//4. 创建表头
		this.setHead(fieldName, sheet);
		
		//5. 将数据写入表格
		this.setValue(sheet, valueName);
		
		//6. 将工作薄导出为文件.
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path + fileName + suffix, false);
			workbook.write(out);
			out.close();
			
		} catch (FileNotFoundException e) {
			logger.error("创建文件输出流错误");
			e.printStackTrace();
			return false;
		}catch (IOException e) {
			logger.error("文件流关闭错误");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 创建表格表头
	 * @param tags
	 * @param s
	 */
	private void setHead(String [] tags, HSSFSheet s)
	{
		HSSFRow row = s.createRow(0); 
        HSSFCell cell = null;
        for(int i=0;i<tags.length;i++)
        {
        	cell = row.createCell(i);
        	cell.setCellValue(tags[i]);
        }
	}
	
	@SuppressWarnings("rawtypes")
	private Boolean setValue(HSSFSheet sheet, String[] valueName){
		
		for(int i = 0; i < data.size(); ++i){
			//循环读取数据对象, 一个数据对象表示一行数据
			Map<String,Object> map= (Map<String, Object>) data.get(i);
			List<Object> newList=new ArrayList<Object>();
			for(String key:map.keySet()){
				newList.add(map.get(key));
				
			}
			HSSFRow row = sheet.createRow(i + 1);
			
			for(int j = 0; j < valueName.length ; ++j){
				try {
					HSSFCell cell = row.createCell(j);					//在"行"对象上创建"格"属性
					cell.setCellValue(String.valueOf(newList.get(j)));	//为"格"属性赋值	
					
				} catch (SecurityException e) {
					logger.error("ExportExcelUtils(writeExcel) - 没有读取属性的权限");
					e.printStackTrace();
					return false;
				}catch (IllegalArgumentException e) {
					
					e.printStackTrace();
					return false;
				} 
			}
		}
		
		return true;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 设置导出文件后缀
	 * 		1.cls  2.csv 
	 * @param suffix
	 */
	public void setSuffix(Integer suffix) {
		switch(suffix){
		
		case 1: this.suffix = ".xls"; break;
		case 2: this.suffix = ".csv"; break;
		default: this.suffix = ".xls"; break;
		
		}
	}
	
	
}
