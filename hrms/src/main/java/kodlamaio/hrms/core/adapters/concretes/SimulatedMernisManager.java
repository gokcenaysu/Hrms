package kodlamaio.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.SimulatedMernisService;

@Service
public class SimulatedMernisManager implements SimulatedMernisService{
	
	@Override
	public boolean checkMernis(String firstName, String lastName, String identityNumber, String birthYear) {
		
		if(firstName.length()<2 || lastName.length()<2 ) {
			System.out.println("Name and surname cannot be blank or less than 2");
			return false;
		}
		
		if(identityNumber.length()!=11 || identityNumber.startsWith("0")) {
			System.out.println("ID number is not 11 digits or starts with 0");
			return false;
		}
		
		else {
			System.out.println("Authentication successful");
			return true;
		}
		
	}
}
