package mh.bean.jobs;

public class JobBean {

	private String sid;
	private String jid;
	private String workstation;
	private String jobstream;
	private String job;
	private String user;
	private String scriptname;
	private String follows;
	private String dep;
		
	
	public JobBean(String sid, String jid, String workstation, String jobstream,
			String job, String user, String scriptname, String follows,
			String dep) {
		super();
		this.sid = sid;
		this.jid = jid;
		this.workstation = workstation;
		this.jobstream = jobstream;
		this.job = job;
		this.user = user;
		this.scriptname = scriptname;
		this.follows = follows;
		this.dep = dep;
	}
	
	@Override
	public String toString() {
		return "JobBean [sid="+ sid +", jid=" + jid + ", workstation=" + workstation
				+ ", jobstream=" + jobstream + ", job=" + job + ", user="
				+ user + ", scriptname=" + scriptname + ", follows=" + follows
				+ ", dep=" + dep + "]";
	}
	

	public String getWorkstation() {
		return workstation;
	}
	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}
	public String getJobstream() {
		return jobstream;
	}
	public void setJobstream(String jobstream) {
		this.jobstream = jobstream;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getScriptname() {
		return scriptname;
	}
	public void setScriptname(String scriptname) {
		this.scriptname = scriptname;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getFollows() {
		return follows;
	}
	public void setFollows(String follows) {
		this.follows = follows;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	
}
