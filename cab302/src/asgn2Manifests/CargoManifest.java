package asgn2Manifests;

import java.util.ArrayList;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;

/**
 * A class for managing a container ship's cargo manifest.  It
 * allows freight containers of various types to be loaded and
 * unloaded, within various constraints.
 * <p>
 * In particular, the ship's captain has set the following rules
 * for loading of new containers:
 * <ol>
 * <li>
 * New containers may be loaded only if doing so does not exceed
 * the ship's weight limit.
 * </li>
 * <li>
 * New containers are to be loaded as close to the bridge as possible.
 * (Stack number zero is nearest the bridge.)
 * </li>
 * <li>
 * A new container may be added to a stack only if doing so will
 * not exceed the maximum allowed stack height.
 * <li>
 * A new container may be loaded only if a container with the same
 * code is not already on board.
 * </li>
 * <li>
 * Stacks of containers must be homogeneous, i.e., each stack must
 * consist of containers of one type (general,
 * refrigerated or dangerous goods) only.
 * </li>
 * </ol>
 * <p>
 * Furthermore, since the containers are moved by an overhead
 * crane, a container can be unloaded only if it is on top of
 * a stack.
 *  
 * @author CAB302
 * @version 1.0
 */
public class CargoManifest {
int weightmax;
int stacknum;
int heightmax;
int totalspace;
int occupiedspace = 0;
ArrayList<FreightContainer> containerlist = new ArrayList<FreightContainer>();
//ArrayList<FreightContainer> containercode = new ArrayList<FreightContainer>();
ArrayList<Integer> containerstacknum = new ArrayList<Integer>();
ArrayList<Integer> containerstackheight = new ArrayList<Integer>();
ArrayList<Integer> stacktype = new ArrayList<Integer>();
ArrayList<Integer> stackheight = new ArrayList<Integer>();



	/**
	 * Constructs a new cargo manifest in preparation for a voyage.
	 * When a cargo manifest is constructed the specific cargo
	 * parameters for the voyage are set, including the number of
	 * stack spaces available on the deck (which depends on the deck configuration
	 * for the voyage), the maximum allowed height of any stack (which depends on
	 * the weather conditions expected for the
	 * voyage), and the total weight of containers allowed onboard (which depends
	 * on the amount of ballast and fuel being carried).
	 * 
	 * @param numStacks the number of stacks that can be accommodated on deck
	 * @param maxHeight the maximum allowable height of any stack
	 * @param maxWeight the maximum weight of containers allowed on board 
	 * (in tonnes)
	 * @throws ManifestException if negative numbers are given for any of the
	 * parameters
	 */
	public CargoManifest(Integer numStacks, Integer maxHeight, Integer maxWeight)
	throws ManifestException {
		//Implementation Here
		if (numStacks < 0 || maxHeight < 0 || maxWeight < 0) {
			throw new ManifestException("Negative value entered");
		}
		stacknum = numStacks;
		heightmax = maxHeight;
		weightmax = maxWeight;
		totalspace = maxHeight * numStacks;
		for (int i = 0; i < numStacks; i++) {
			stacktype.add(0); 
		}
	}

	/**
	 * Loads a freight container onto the ship, provided that it can be
	 * accommodated within the five rules set by the captain.
	 * 
	 * @param newContainer the new freight container to be loaded
	 * @throws ManifestException if adding this container would exceed
	 * the ship's weight limit; if a container with the same code is
	 * already on board; or if no suitable space can be found for this
	 * container
	 */
	public void loadContainer(FreightContainer newContainer) throws ManifestException {
		//Implementation Here
		if (newContainer.getGrossWeight() >= weightmax) {
			throw new ManifestException("Container is too heavy");
		}
		else if (occupiedspace == totalspace) {
			throw new ManifestException("The ship is full");			
		}
		for (int num = 0; num < containerlist.size();num++) {
			if (newContainer.getCode() == containerlist.get(num).getCode()) {
				throw new ManifestException("Container is already on board");	
			}
		}
		occupiedspace++;
		String containerclass = newContainer.getClass().getName();
		int i= 0;
		int continuesearch = 0;
		while (i < stacknum && continuesearch == 1) {
			if (stacktype.get(stacknum) == 0) {
				containerstacknum.add(stacknum);
				continuesearch = 0;
				if (containerclass == "GeneralGoodsContainer"){
					stacktype.set(1, stacknum);
				}
				else if (containerclass == "RefrigeratedContainer"){
					stacktype.set(2, stacknum);
				}
				if (containerclass == "RefrigeratedContainer"){
					stacktype.set(3, stacknum);
				}
				stacktype.set(1,stacknum);
			}
			else if (stacktype.get(stacknum) == 1 && containerclass == "GeneralGoodsContainer"){
				containerstacknum.add(stacknum);
				continuesearch = 0;
			}
			else if (stacktype.get(stacknum) == 2 && containerclass == "RefrigeratedContainer"){
				containerstacknum.add(stacknum);
				continuesearch = 0;
			}
			else if (stacktype.get(stacknum) == 3 && containerclass == "RefrigeratedContainer"){
				containerstacknum.add(stacknum);
				continuesearch = 0;
			}
			i++;
		}
		if (continuesearch == 1) {
			throw new ManifestException("no space available");
		}
		containerlist.add(newContainer);
		containerstacknum.add((occupiedspace + heightmax - 1) / heightmax);
		containerstackheight.add(occupiedspace % heightmax);
	}

	/**
	 * Unloads a particular container from the ship, provided that
	 * it is accessible (i.e., on top of a stack).
	 * 
	 * @param containerId the code of the container to be unloaded
	 * @throws ManifestException if the container is not accessible because
	 * it's not on the top of a stack (including the case where it's not on board
	 * the ship at all)
	 */
	public void unloadContainer(ContainerCode containerId) throws ManifestException {
		//Implementation Here
		int containernum = 0;
		int error = 1;
		for (int num = 0; num < containerlist.size(); num++ ){
			if (containerId == containerlist.get(num).getCode()) {
				containernum = num;
				error = 0;
			}
		}
		if (containernum != heightmax || containernum != occupiedspace) {
			throw new ManifestException("The container is not at the top of the stack");
		}
		else if (error == 1) {
			throw new ManifestException("The container is not on board");
		}
		containerlist.remove(containernum );
		containerstacknum.remove(containernum);
		containerstackheight.add(containernum);
	}
	

	
	/**
	 * Returns which stack holds a particular container, if any.  The
	 * container of interest is identified by its unique
	 * code.  Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the number of the stack with the container in it, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer whichStack(ContainerCode queryContainer) {
		//Implementation Here
		int error = 1;
		int containernum = 0;
		for (int num = 0; num < containerlist.size(); num++ ){
			if (queryContainer == containerlist.get(num).getCode()) {
				containernum = num;
				error = 0;
			}
		}
		if (error == 0) {
			return containerstacknum.get(containernum);
		}
		else {
			return null;
		}
		
	}

	
	/**
	 * Returns how high in its stack a particular container is.  The container of
	 * interest is identified by its unique code.  Height is measured in the
	 * number of containers, counting from zero.  Thus the container at the bottom
	 * of a stack is at "height" 0, the container above it is at height 1, and so on.
	 * Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the container's height in the stack, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer howHigh(ContainerCode queryContainer) {
		//Implementation Here
		int error = 1;
		int containernum = 0;
		for (int num = 0; num < containerlist.size(); num++ ){
			if (queryContainer == containerlist.get(num).getCode()) {
				containernum = num;
				error = 0;
			}
		}
		if (error == 0) {
			return containerstackheight.get(containernum);
		}
		else {
			return null;
		}
	}


	/**
	 * Returns the contents of a particular stack as an array,
	 * starting with the bottommost container at position zero in the array.
	 * 
	 * @param stackNo the number of the stack of interest
	 * @return the stack's freight containers as an array
	 * @throws ManifestException if there is no such stack on the ship
	 */
	@SuppressWarnings("null")
	public FreightContainer[] toArray(Integer stackNo) throws ManifestException {
		//Implementation Here
		if (stackNo > stacknum || stackNo < 0) {
			throw new ManifestException("No such stack");
		}
		FreightContainer[] stacklist = null;
		for (int i = 0; i < occupiedspace; i++) {
			if (containerstacknum.get(i) == stackNo){
				stacklist[i] = containerlist.get(i);
			}
		}
		return stacklist;
	}

		
	

	
	/* ***** toString methods added to support the GUI ***** */
	/*
	public String toString(ContainerCode toFind) {
		//Some variables here are used and not declared. You can work it out 
		String toReturn = "";
		for (int i = 0; i < manifest.size(); ++i) {
			ArrayList<FreightContainer> currentStack = manifest.get(i);
			toReturn += "|";
			for (int j = 0; j < currentStack.size(); ++j) {
				if (toFind != null && currentStack.get(j).getCode().equals(toFind))
					toReturn += "|*" + currentStack.get(j).getCode().toString() + "*|";
				else
					toReturn += "| " + currentStack.get(j).getCode().toString() + " |";
			}
			if (currentStack.size() == 0)
				toReturn +=  "|  ||\n";
			else
				toReturn += "|\n";
		}
		return toReturn;
	}

	@Override
	public String toString() {
		return toString(null);
	}
*/

}