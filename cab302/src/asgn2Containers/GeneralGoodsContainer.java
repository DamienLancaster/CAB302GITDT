package asgn2Containers;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.InvalidContainerException;

/**
 * A general-purpose, or "dry", container is a freight container with
 * no special characteristics.  It is used for carrying furniture, boxes,
 * etc.
 * 
 * @author CAB302
 * @version 1.0
 */
public class GeneralGoodsContainer extends FreightContainer {
	ContainerCode freightcode;
	int weight;
	//super (freightcode, weight);
	/**
	 * Constructs a general-purpose freight container object with the given
	 * container code and gross weight.  See the constructor in class
	 * FreightContainer for details about valid container weights.
	 * 
	 * @param code the container's code
	 * @param grossWeight the container's weight in tonnes
	 * @throws InvalidContainerException if the gross weight is invalid
	 */
	public GeneralGoodsContainer(ContainerCode code, Integer grossWeight)
	throws InvalidContainerException {
		//Implementation Here
		super (code, grossWeight);
		if (grossWeight < 5 || grossWeight > 29) {
			throw new InvalidContainerException("invalid weight(must be between 4 and 30");
		}
		
		freightcode = code;
		weight = grossWeight;
	}

}
