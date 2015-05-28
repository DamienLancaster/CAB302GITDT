package asgn2Containers;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.InvalidContainerException;

/**
 * A refrigerated container is a special type of container for
 * transporting perishable goods.  It has a thermostat mounted
 * on the outside for setting the desired temperature inside the
 * container.  (Of course, a real refrigerated container also
 * has an externally-readable thermometer to display the internal
 * temperature, but we omit this feature here.)
 * 
 * @author CAB302
 * @version 1.0
 */
public class RefrigeratedContainer extends FreightContainer {

	/**
	 * Constructs a refrigerated goods container object with the given
	 * container code, gross weight and desired temperature.  (In practice
	 * we should check that the temperature is within an achievable range,
	 * but to keep the assignment simple we omit this requirement here.)
	 * See the constructor in class FreightContainer for details about
	 * valid container weights.
	 * 
	 * @param code the container's code
	 * @param grossWeight the container's weight in tonnes
	 * @param temperature the temperature set on the container's thermostat
	 * (in degrees Celsius)
	 * @throws InvalidContainerException if the gross weight is invalid
	 */
	public RefrigeratedContainer(ContainerCode code, Integer grossWeight, Integer temperature)
	throws InvalidContainerException {
		//Implementation Here
	}

	/**
	 * Returns the temperature currently set on the container's thermostat.
	 * (Whether this matches the actual temperature inside the container
	 * is not known, since we have not provided a thermometer as part of
	 * our design!)
	 * 
	 * @return the temperature (in degrees Celsius)
	 */
	public Integer getTemperature() {
		//Implementation Here
	}

	/**
	 * Resets the desired temperature on the container's thermostat.
	 * 
	 * @param temperature the new temperature (in degrees Celsius)
	 */
	public void setTemperature(Integer temperature) {
		//Implementation Here
	}

}
