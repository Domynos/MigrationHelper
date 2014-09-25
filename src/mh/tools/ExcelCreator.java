package mh.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mh.bean.JobsBag;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCreator {

	public ExcelCreator(JobsBag bag, String[] infos)
	{
		try {
			String[][] tab = bagToTab(bag);
			String[] csv = tabToCSV(tab);
			csvToXls("","C:\\Users\\m416374\\Documents\\Migration\\Test",csv,infos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[][] bagToTab(JobsBag bag){
		int i;
		String[][] tab = new String[bag.getBag().size()][60];
		
		for(i=0;i<tab.length;i++)
		{
			if(bag.getBag().get(i).getDep() != null)
			{
				tab[i][3] = "R";
				tab[i][57] = bag.getBag().get(i).getDep();
			}
			else
			{
				tab[i][1] = bag.getBag().get(i).getSid();
				tab[i][2] = bag.getBag().get(i).getJid();
				tab[i][3] = bag.getBag().get(i).getDep();
				tab[i][4] = bag.getBag().get(i).getWorkstation();
				tab[i][5] = bag.getBag().get(i).getJobstream();
				tab[i][6] = bag.getBag().get(i).getJob();
				tab[i][8] = bag.getBag().get(i).getUser();
				tab[i][9] = bag.getBag().get(i).getScriptname();
				tab[i][11] = bag.getBag().get(i).getFollows();
			}
		}
		
		return tab;
	}
	
	private String[] tabToCSV(String[][] tab)
	{
		int i,y;

		String[] output = new String[tab.length];

		for(i=0;i<tab.length;i++)
			for(y=0;y<tab[i].length;y++)
			{
				if(tab[i][y] == null)
					tab[i][y] = "";
				
				if(y==0)
					output[i]= tab[i][y];
				else
					output[i]+= ","+tab[i][y];
			}
		

		return output;
	}

	private boolean csvToXls(String path,String name,String[] tab, String[] infos) throws IOException
	{
		ArrayList<ArrayList<String>> arList = new ArrayList<ArrayList<String>>();
		ArrayList<String> al = null;

		int i;

		for(i=0;i<tab.length;i++) {
			al = new ArrayList<String>();
			String strar[] = tab[i].split(",");

			for (int j = 0; j < strar.length; j++) {
				String edit = strar[j].replace('\n', ' ');
				al.add(edit);	
			}

			arList.add(al);
		}

		try {
			Workbook workbook;
		    workbook = new XSSFWorkbook(
		        OPCPackage.open("C:\\Migration\\protected2.xlsm")
		    );
		    
		    Sheet sheet = workbook.getSheet("Projet");

		    sheet.getRow(0).createCell(1).setCellValue(infos[0]);
		    sheet.getRow(2).createCell(1).setCellValue(infos[1]);
		    sheet.getRow(4).createCell(1).setCellValue(infos[2]);
		    
		    sheet = workbook.getSheet("jobstreams");

			for (int k = 0; k < arList.size(); k++) {
				ArrayList<String> ardata = (ArrayList<String>) arList.get(k);
				Row row = sheet.createRow((short) 0 + k + 1);

				for (int p = 0; p < ardata.size(); p++) {
					Cell cell = row.createCell((short) p);
					cell.setCellValue(ardata.get(p).toString());
				}
			}

			FileOutputStream fileOut = new FileOutputStream(path+name+".xlsm");
			workbook.write(fileOut);
			fileOut.close();

			JOptionPane.showMessageDialog(null, "L'OCAB a bien été généré !", "Migration Helper", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
}
