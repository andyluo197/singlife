/**
 * 
 */
package net.andyluo.singlife.jsf.demo;

/**
 * @author Luo Ming
 *
 */
public class UserBean 
{
	private String id;
	private String pwd;
	private String errMsg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String verify()
	{
		if(id.equals("andyluo"))
		{
			return "success";
		}
		else
		{
			setErrMsg("userID should be andyluo");
			return "failed";
		}
	}

}
