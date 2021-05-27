package kodlamaio.hrms.core.adapters.abstracts;

public interface SimulatedMernisService {

	boolean checkMernis(String firstName, String lastName,
			String identityNumber, String birthYear);
}
