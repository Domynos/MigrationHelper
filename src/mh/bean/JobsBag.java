package mh.bean;

import java.util.LinkedList;
import java.util.List;

import mh.bean.jobs.JobBean;

public class JobsBag {

	private List<JobBean> bag = new LinkedList<JobBean>();
	
	public void add(JobBean bean){
		bag.add(bean);
	}

	@Override
	public String toString() {
		int i;
		StringBuilder sb = new StringBuilder();
		
		for(i=0;i<bag.size();i++){
			sb.append(bag.get(i).toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public List<JobBean> getBag() {
		return bag;
	}

}
