package revlotonight;

import java.util.ArrayList;

public class RedemptionsWrapper {
	private ArrayList<Redemption> redemptions;
	private Integer total;
	private Integer page_size;
	
	public ArrayList<Redemption> getRedemptions() {
		return redemptions;
	}
	public void setRedemptions(ArrayList<Redemption> redemptions) {
		this.redemptions = redemptions;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	
	
	
}
