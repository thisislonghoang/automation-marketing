package amg.database

public class InsiderOutCardWhiteListModel {
	String iid
	String cif
	String whitelist_product
	String approval_status
	String time
	String segment

	public def setWhitelist_product(String product) {
		if(product == "\\N") {
			this.whitelist_product = null
		}else {
			this.whitelist_product = product
		}
	}

	public def setApproval_status(String status) {
		if(status.equalsIgnoreCase("\\N")) {
			this.approval_status = null
		}else {
			this.approval_status = status
		}
	}

	public def setTime(String time) {

		if(time.equalsIgnoreCase("\\N")) {
			this.time = null
		}else {
			this.time = time
		}
	}
}
