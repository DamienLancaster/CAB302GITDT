package asgn2Exceptions;

/**
 * This class represents any exceptions thrown by any of the freight
 * container or cargo manifest classes.
 * 
 * @author CAB302
 * @version 1.0
 */
@SuppressWarnings("serial") // We're not interested in binary i/o here
public abstract class CargoException extends Exception {
	
	/**
	 * Constructs a new CargoException object with a helpful message
	 * describing the problem.  (The message can be retrieved from
	 * the exception object via the <code>getMessage</code> method inherited
	 * from class <code>Throwable</code>.)
	 * 
	 * @param message an informative message describing the problem encountered
	 */
	public CargoException(String message) {
		//Implementation Here
	}
}
