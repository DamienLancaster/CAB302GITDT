package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;


public class ContainerTests {
	//Implementation Here - includes tests for ContainerCode and for the actual container classes. 
	//container code tests
	String validcode = "KOCU8090115";
	//ContainerCode ValidTestCode = new ContainerCode(validcode);
	@Test
	public void validcontainercodeentry() throws InvalidCodeException{
		String validcode = "MSCU6639871";
		ContainerCode testcode = new ContainerCode(validcode);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryTooShort() throws InvalidCodeException{
		String invalidcodetooshort = "MCU6639871"; //missing one 
		ContainerCode testcode = new ContainerCode(invalidcodetooshort);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryTooLong() throws InvalidCodeException{
		String invalidcodetoolong = "MSCDU6639871"; //has an extra
		ContainerCode testcode = new ContainerCode(invalidcodetoolong);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryToofewnumbers() throws InvalidCodeException{
		String invalidcodefewnumbers = "MSCDU663971"; //normal size but has 5 numbers instead 6 
		ContainerCode testcode = new ContainerCode(invalidcodefewnumbers);
	}
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryToomanynumbers() throws InvalidCodeException{
		String invalidcodetoomanynumbers = "MDU66398971"; //normal size but has 7 numbers instead 6
		ContainerCode testcode = new ContainerCode(invalidcodetoomanynumbers);
	}
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryToofewletters() throws InvalidCodeException{
		String invalidcodetoofewletters = "MDU6639871"; //normal size but has 2 letters(besides U ) instead 3
		ContainerCode testcode = new ContainerCode(invalidcodetoofewletters);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryToomanyletters() throws InvalidCodeException{
		String invalidcodetoomanyletters = "MSCDU663987"; //normal size but has 4 letters(besides U ) instead 3
		ContainerCode testcode = new ContainerCode(invalidcodetoomanyletters);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntrymissingU() throws InvalidCodeException{
		String invalidcodeEntrymissingU = "MSCD6639871"; //missing the U
		ContainerCode testcode = new ContainerCode(invalidcodeEntrymissingU);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryLettersislowercase() throws InvalidCodeException{
		String invalidcodeEntryLettersislowercase = "MScU6639871"; //one of the letters is lower case
		ContainerCode testcode = new ContainerCode(invalidcodeEntryLettersislowercase);
	}
	
	@Test(expected=InvalidCodeException.class)
	public void InvalidContainerCodeEntryincorrectcheckdigit() throws InvalidCodeException{
		String invalidcodeEntryincorrectcheckdigit = "CDQU3054385"; //check digit is wrong (source: http://en.wikipedia.org/wiki/ISO_6346#Check_Digit)
		ContainerCode testcode = new ContainerCode(invalidcodeEntryincorrectcheckdigit);
	}
	
	//not sure about the toString();
	
	@Test
	public void equalscorrect() throws InvalidCodeException {
		String validcode = "MSCU6639871";
		ContainerCode testcode = new ContainerCode(validcode);
		ContainerCode testcode2 = new ContainerCode(validcode); //double up on the code
		assertTrue("test to see if it detects both have the same code", testcode.equals(testcode2));
	}
	
	@Test
	public void equalsincorrect() throws InvalidCodeException {
		String validcode = "MSCU6639871";
		String Validcode2 = "XMCU9157391";
		ContainerCode testcode = new ContainerCode(validcode);
		ContainerCode testcode2 = new ContainerCode(Validcode2); //making sure it doesnt double up
		assertFalse("test to see if it detects both have different code", testcode.equals(testcode2));
	}
	
	//end of containercode tests
	
	//start of GeneralGoodsContainer tests
	
	
	@Test
	public void GeneralGoodsContainerValidEntry() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		GeneralGoodsContainer validGGCmax = new GeneralGoodsContainer(ValidTestCode, 29);
		GeneralGoodsContainer validGGCmin = new GeneralGoodsContainer(ValidTestCode, 5);
	}
	
	@Test(expected=InvalidContainerException.class)
	public void GeneralGoodsContainerinvalidgrossweightmax() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		GeneralGoodsContainer invalidGGCmax = new GeneralGoodsContainer(ValidTestCode, 30); //assuming freight containers gross weight is the same
	}
	
	@Test(expected=InvalidContainerException.class)
	public void GeneralGoodsContainerinvalidgrossweightmin() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		GeneralGoodsContainer invalidGGCmin = new GeneralGoodsContainer(ValidTestCode, 4);
	}
	
	//end of GeneralGoodsContainer tests
	
	//start of RefrigeratedContainer Tests
	@Test
	public void RefrigeratedContainerValidEntry() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		RefrigeratedContainer validRCmin = new RefrigeratedContainer(ValidTestCode,5,15 );
		RefrigeratedContainer validRCmax = new RefrigeratedContainer(ValidTestCode,29,15 );
	}
	
	@Test(expected=InvalidContainerException.class)
	public void RefrigeratedContainerinvalidgrossweightmax() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		RefrigeratedContainer invalidGGCmax = new RefrigeratedContainer(ValidTestCode, 30, 15); //assuming freight containers gross weight is the same
	}
	
	@Test(expected=InvalidContainerException.class)
	public void RefrigeratedContainerinvalidgrossweightmin() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		RefrigeratedContainer invalidGGCmin = new RefrigeratedContainer(ValidTestCode, 4, 15);
	}
	
	@Test
	public void RefrigeratedContainerGetTemp() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		int temp = 15;
		RefrigeratedContainer validRC = new RefrigeratedContainer(ValidTestCode,5,temp );
		assertTrue("test to see if returns correct temperature", temp == validRC.getTemperature());
	}
	
	@Test
	public void RefrigeratedContainerChangeTemp() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		int temp = 15;
		int temp2 = 20;
		RefrigeratedContainer validRC = new RefrigeratedContainer(ValidTestCode,5,temp );
		assertTrue("test to see if returns correct temperature", temp == validRC.getTemperature());
		validRC.setTemperature(temp2);
		assertTrue("test to see if returns correct temperature", temp2 == validRC.getTemperature());
	}
	
	//end of RefrigeratedContainer Tests
	
	//start of FreightContainers Tests
	
	
	@Test
	public void FreightContainergetCode() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		RefrigeratedContainer validRC = new RefrigeratedContainer(ValidTestCode, 29, 15);
		assertTrue("test to see if returns correct code", ValidTestCode == validRC.getCode());
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode, 29);
		assertTrue("test to see if returns correct code", ValidTestCode == validRC.getCode());
		DangerousGoodsContainer validDC = new DangerousGoodsContainer(ValidTestCode, 29, 15);
		assertTrue("test to see if returns correct code", ValidTestCode == validDC.getCode());
	}
	
	@Test
	public void FreightContainergetGrossWright() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		int weight = 25;
		RefrigeratedContainer validRC = new RefrigeratedContainer(ValidTestCode, weight, 15);
		assertTrue("test to see if returns correct code", weight == validRC.getGrossWeight());
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode, weight);
		assertTrue("test to see if returns correct code", weight == validRC.getGrossWeight());
		DangerousGoodsContainer validDC = new DangerousGoodsContainer(ValidTestCode, 29, 15);
		assertTrue("test to see if returns correct code", ValidTestCode == validDC.getCode());
	}
	
	//end of FreightContainer tests
	
	//start of DangerousGoodsContainer tests
	
	@Test
	public void DangerousGoodsContainerValidEntry() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		DangerousGoodsContainer validDCmin = new DangerousGoodsContainer(ValidTestCode,5,1 );
		DangerousGoodsContainer validDCmax = new DangerousGoodsContainer(ValidTestCode,29,9 );
	}
	
	@Test(expected=InvalidContainerException.class)
	public void DangerousGoodsContainerinvalidgrossweightmax() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		DangerousGoodsContainer invalidGGCmax = new DangerousGoodsContainer(ValidTestCode, 30, 10); //assuming freight containers gross weight is the same
	}
	
	@Test(expected=InvalidContainerException.class)
	public void DangerousGoodsContainerinvalidgrossweightmin() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		DangerousGoodsContainer invalidGGCmin = new DangerousGoodsContainer(ValidTestCode, 4, 0);
	}//end of DangerousGoodsContainer test
	
	
	//start of testing Refrigerated dangerous containers
	@Test
	public void RefrigeratedContainerGetCatergory() throws InvalidContainerException, InvalidCodeException{
		ContainerCode ValidTestCode = new ContainerCode(validcode);
		int category = 5;
		DangerousGoodsContainer validDC = new DangerousGoodsContainer(ValidTestCode,5,category );
		assertTrue("test to see if returns correct category", category == validDC.getCategory());
	}
	//end of Refrigerated dangerous containers test
}
