package mh.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mh.bean.JobsBag;
import mh.bean.jobs.JobBean;

public class Ca7Parser {

	private JobsBag bag = new JobsBag();
	
	public String deleteSpaces(String ligne){
		int i;
		StringBuilder sb = new StringBuilder();
		
		for(i=0;i<ligne.length();i++){
			
			if(ligne.charAt(i) == ' '){
				if(i!=0)
				sb.append(ligne.charAt(i));
				while(i+1<ligne.length() && ligne.charAt(i+1) == ' ')
					i++;
			}
			else
				sb.append(ligne.charAt(i));
		}
		
		return sb.toString();
		
	}
	
	public String deletePoints(String ligne){
		int i;
		StringBuilder sb = new StringBuilder();
		
		for(i=0;i<ligne.length();i++){
				
			if(ligne.charAt(i) == '.'){
				while(i+1<ligne.length() && ligne.charAt(i+1) == '.')
					i++;
			}
			else
				sb.append(ligne.charAt(i));
		}
		
		return sb.toString();
	}
	
	public void addDep(String ligne){
		if(ligne != null){
			String[] split = ligne.split("=");
			if(split[0].contains("JOB"))
				bag.add(new JobBean(null,null,null,null,null,null,null,null,split[1]));
			else if(split[0].contains("USR"))
				bag.add(new JobBean(null,null,null,null,null,null,null,null,split[1]));
		}
	}
	
	public void addJob(String ligne){
		ligne = deleteSpaces(ligne);
		if(ligne.contains("DRAPEAU")){
			String[] split = ligne.split(" ");
				for(int i=0;i<bag.getBag().size();i++){
					if(bag.getBag().get(i).getSid() != null || bag.getBag().get(i).getJid() != null)
					{
						if(bag.getBag().get(i).getJobstream() == null && bag.getBag().get(i).getJob().equals(split[4]))
							split[4] = bag.getBag().get(i).getJid();
						if(bag.getBag().get(i).getJob() == null && bag.getBag().get(i).getJobstream().equals(split[4]))
							split[4] = bag.getBag().get(i).getSid();
					}
				}
			bag.add(new JobBean(split[0],null,split[2],deletePoints(split[1]),null,split[2].toLowerCase(),null,split[4].equals("EXEC")?null:split[4],null));
		}
		else{
			String[] split = ligne.split(" ");
				for(int i=0;i<bag.getBag().size();i++){
					if(bag.getBag().get(i).getSid() != null || bag.getBag().get(i).getJid() != null)
					{
						if(bag.getBag().get(i).getJobstream() == null && bag.getBag().get(i).getJob().equals(split[3]))
							split[3] = bag.getBag().get(i).getJid();
						if(bag.getBag().get(i).getJob() == null && bag.getBag().get(i).getJobstream().equals(split[3]))
							split[3] = bag.getBag().get(i).getSid();
					}
				}
			bag.add(new JobBean(null,split[0],split[2],null,deletePoints(split[1]),split[2].toLowerCase(),split[10],split[3],null));
		}
	}

	public Ca7Parser(String file, String nameProject, String contactDSA, String desc){
		
		String[] infos = new String[3];
		infos[0] = nameProject;
		infos[1] = contactDSA;
		infos[2] = desc;
		
		InputStream flux;
		try {
			flux = new FileInputStream(file);
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while((ligne=buff.readLine())!=null){
				if(ligne.contains("- CALENDRIER -"))
				{
					infos[2] = infos[2]+ "\n" + ligne;
					ligne=buff.readLine();
					infos[2] = infos[2] + "\n" + ligne;
				}
				if(ligne.contains("DEPENDANCES"))
				{
					while(!((ligne=buff.readLine()).contains("EXEC")))
					addDep(ligne.equals("")?null:ligne);
				}
				if(ligne.contains("EXEC"))
				{
					addJob(ligne);
				}
			}
			
			buff.close();
			new ExcelCreator(bag,infos);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
