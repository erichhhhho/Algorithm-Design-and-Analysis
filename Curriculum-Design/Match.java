package Algorism;
/**
 * 
 * @Project：Algorithm  
 * @File：Match  
 * @Author：何唯   
 * @Date：2017年5月25日 下午3:26:44      
 * @Description：
 */
public class Match {

	private int origin;
	private int value;
	
	public Match(){
		this.origin=0;
		this.value=0;
	}
	public Match (int a,int b){
		this.origin=a;
		this.value=b;
	}
	
	public int getValue() {
	
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getOrigin() {
	
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public void copy(Match temp)
	{
		this.setOrigin(temp.getOrigin());
		this.setValue(temp.getValue());
	}
}
