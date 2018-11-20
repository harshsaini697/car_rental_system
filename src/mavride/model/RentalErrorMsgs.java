package mavride.model;

public class RentalErrorMsgs {
	
	private String errorMsg;
	private String startDateError;
	private String endDateError;
	private String occupantsError;
	private String timeError;
	private String confirmRentalError;
	private String addRentalError;
	private String addCarError;
	
	public RentalErrorMsgs() {
		this.errorMsg = "";
		this.startDateError = "";
		this.endDateError = "";
		this.occupantsError = "";
		this.timeError = "";
		this.confirmRentalError = "";
		this.addRentalError = "";
		this.addCarError = "";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String action) {
		if(action.equals("reserveRental")) {
			if(!startDateError.equals("") || !endDateError.equals("") || !occupantsError.equals(""))
				this.errorMsg = "Please correct the following errors";
		}
		else if(action.equalsIgnoreCase("listCars")) {
			if(!timeError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}
		}
		else if(action.equalsIgnoreCase("confirmRental")) {
			if(!confirmRentalError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}
		}
		else if(action.equalsIgnoreCase("addRental")) {
			if(!addRentalError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}
		}
		
			if(!addCarError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}

	}
	
	public void setStartDateError(String startDateError) {
		this.startDateError = startDateError;
	}
	
	public String getStartDateError() {
		return startDateError;
	}
	
	public void setEndDateError(String endDateError) {
		this.endDateError = endDateError;
	}
	
	public String getEndDateError() {
		return endDateError;
	}

	public String getOccupantsError() {
		return occupantsError;
	}

	public void setOccupantsError(String occupantsError) {
		this.occupantsError = occupantsError;
	}
	
	public String getTimeError() {
		return timeError;
	}

	public void setTimeError(String timeError) {
		this.timeError = timeError;
	}

	public String getConfirmRentalError() {
		return confirmRentalError;
	}

	public void setConfirmRentalError(String confirmRentalError) {
		this.confirmRentalError = confirmRentalError;
	}

	public String getAddRentalError() {
		return addRentalError;
	}

	public void setAddRentalError(String addRentalError) {
		this.addRentalError = addRentalError;
	}

	public String getAddCarError() {
		return addCarError;
	}

	public void setAddCarError(String addCarError) {
		this.addCarError = addCarError;
	}

}
