package com.core.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record;


public class CSVTable {

    List<CSVTableRow> list = new ArrayList<CSVTableRow>();

    CSVWriter csvWriter = null;

    Writer writer = null;

    List<String> headerList;

    String csvFileName = "";

    int headerRow = 0;

    public CSVTable(String csvFileName) {
        CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvFileName));
			init(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public CSVTable(Reader sr) throws Exception {
        CSVReader reader = new CSVReader(sr);
        init(reader);
    }

    public CSVTable(String csvFileName, List<String> headerList) throws Exception {

        this.csvFileName = csvFileName;

        // Initiate writer
        File f = new File(csvFileName);
        updateWriters(new PrintWriter(f));
        //
        // writer = new PrintWriter(f);
        //
        // csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
        // CSVWriter.DEFAULT_QUOTE_CHARACTER,
        // CSVWriter.DEFAULT_ESCAPE_CHARACTER);
        //
        if (headerList != null) {
            csvWriter.writeNext(headerList.toArray(new String[0]));
        }
        this.headerList = headerList;
    }
     
    
    public CSVTableRow writeCellValue (String name,String value) throws Exception {
    	CSVTableRow csvTableRow = new CSVTableRow();
    	csvTableRow.setCell(name, value);
    	//getCsvWriter().writeNext(value);
		//writer.write(value);
		//getWriter().write(value);
    	//csvWriter.flushQuietly();
    	    	updateWriters(new PrintWriter(value));
    	save();
		return csvTableRow;
    }


	public void updateWriters(Writer writer) {
        this.writer = writer;
        csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER);
    }

    public CSVTableRow createRow() {
        CSVTableRow csvTableRow = new CSVTableRow(headerList);
        return csvTableRow;
    }

    public CSVTableRow createRow(String[] data) {
        CSVTableRow csvTableRow = new CSVTableRow(data, headerList);
        return csvTableRow;
    }

    public CSVTableRow createRow(List<String> data) {
        CSVTableRow csvTableRow = new CSVTableRow(data, headerList);
        return csvTableRow;
    }

    public void addRow(CSVTableRow csvTableRow) {
        list.add(csvTableRow);
    }

    public void save() throws Exception {
        for (CSVTableRow csvTableRow : list) {
            csvWriter.writeNext(csvTableRow.getData());
        }
        csvWriter.flush();
        csvWriter.close();

        writer.flush();
        writer.close();
    }

    public int getHeaderRow() {
        return headerRow;
    }

    public void setHeaderRow(int headerRow) {
        this.headerRow = headerRow;
    }

    private void init(CSVReader reader) throws Exception {
        List<String[]> listTransaction = reader.readAll();
        List<String> headerFieldList = new ArrayList<String>(Arrays.asList(listTransaction.get(headerRow)));

        for (String[] strings : listTransaction.subList(1, listTransaction.size())) {
            // Ignore record if empty
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < strings.length; i++) {
                result.append(strings[i]);
            }
            if (StringUtils.isBlank(result.toString())) {
                continue;
            }

            list.add(new CSVTableRow(strings, headerFieldList));
        }
    }

    public List<CSVTableRow> getRecords() {
        return list;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public CSVWriter getCsvWriter() {
    	
        return csvWriter;
    }

    
 public CSVWriter getCsvWriter( String csvFileName,Boolean flag ) throws IOException {
	   setCsvFileName(csvFileName);
	 
    	 csvWriter=new CSVWriter(new FileWriter(csvFileName,flag));
	 
        return csvWriter;
    }
 
 
    public Writer getWriter() {
    	
        return writer;
    }


    public static void main(String[] args) throws Exception {
    	
      CSVTable t = new CSVTable("F:\\IZMO FrameWork\\com.r1v2.com\\src\\main\\resource\\EuroFiles\\PagesData34.csv");
      {
    	  
    	  List<CSVTableRow> test=t.getRecords();
    	  Iterator<CSVTableRow> itr = test.iterator();
    		CSVTableRow currentRow = itr.next();
    		//String[] level1 = currentRow.getData();
    		String[] level1 = currentRow.getData();
    		for(String str:level1)
    		System.out.println(str);
    	  
    	  
    		
    	 }
    	 
      
    }
}
    
        	 		
        	
         
     
   
    

      
 
            


 