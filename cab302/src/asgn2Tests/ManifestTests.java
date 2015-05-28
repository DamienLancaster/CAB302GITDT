package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 * QUTU7200318
 * IBMU4882351
 */

		//String validcode = "MSCU6639871";
		//ContainerCode testcode = new ContainerCode(validcode);

import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;
import static org.junit.Assert.*;

public class ManifestTests {
	//Implementation Here	
	//CargoManifest testmanifest = new CargoManifest(1,1,1);	
	@Test
	public void validCargoManifestentry() throws ManifestException{
		CargoManifest validmanifest = new CargoManifest(1,1,1);	
	}

	@Test(expected=ManifestException.class)
	public void invalidCargoManifestentrynegativestacks() throws ManifestException{
		CargoManifest invalidmanifest = new CargoManifest(-1,1,1);	
	}
	@Test(expected=ManifestException.class)
	public void invalidCargoManifestentrynegativeheight() throws ManifestException{
		CargoManifest invalidmanifest = new CargoManifest(1,-1,1);	
	}
	@Test(expected=ManifestException.class)
	public void invalidCargoManifestentrynegativeWeight() throws ManifestException{
		CargoManifest invalidmanifest = new CargoManifest(1,1,-1);	
	}
	@Test
	public void validloadcontainerentry() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,1,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		validmanifest.loadContainer(validGGC);
	}
	
	@Test(expected=ManifestException.class)
	public void invalidloadcontainerentryTooheavy() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,1,5);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		validmanifest.loadContainer(validGGC);
	}
	
	@Test(expected=ManifestException.class)
	public void invalidloadcontainerentryToofull() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,1,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(testcode2,15);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
	}
	@Test(expected=ManifestException.class)
	public void invalidloadcontainerentrysamecontainer() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(2,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(ValidTestCode,15);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
	}
	
	@Test(expected=ManifestException.class)
	public void invalidloadcontainerentrynotenoughstacks() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		RefrigeratedContainer validGGC2 = new RefrigeratedContainer(testcode2,15,4);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
	}
	
	@Test
	public void validunloadcontainerentry() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(testcode2,15);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
		validmanifest.unloadContainer(testcode2);
	}
	
	@Test
	public void invalidunloadcontainerentryNottopofstack() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(testcode2,15);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
		validmanifest.unloadContainer(ValidTestCode);
	}
	
	@Test
	public void invalidunloadcontainerentrynocontainer() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		//ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		//RefrigeratedContainer validGGC2 = new RefrigeratedContainer(testcode2,15,4);
		validmanifest.loadContainer(validGGC);
		//validmanifest.loadContainer(validGGC2);
		validmanifest.unloadContainer(ValidTestCode);
	}
	
	@Test
	public void whichstacktest() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(2,1,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		RefrigeratedContainer validGGC2 = new RefrigeratedContainer(testcode2,15,4);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
		assertTrue("to see if finds correct stack",2 ==validmanifest.whichStack(ValidTestCode));
	}
	
	@Test
	public void howHightest() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(testcode2,1);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
		assertTrue("to see if finds correct stack",2 ==validmanifest.howHigh(ValidTestCode));
	}
	
	@Test
	public void validtoarray() throws ManifestException, InvalidContainerException, InvalidCodeException{
		CargoManifest validmanifest = new CargoManifest(1,2,20);
		ContainerCode ValidTestCode = new ContainerCode("MSCU6639871");
		ContainerCode testcode2 = new ContainerCode("XMCU9157391");
		GeneralGoodsContainer validGGC = new GeneralGoodsContainer(ValidTestCode,15);
		GeneralGoodsContainer validGGC2 = new GeneralGoodsContainer(testcode2,1);
		validmanifest.loadContainer(validGGC);
		validmanifest.loadContainer(validGGC2);
		FreightContainer[] test = null;
		test[0] = validGGC;
		test[1] = validGGC2;
		assertTrue("to see if finds correct stack",test[0] ==validmanifest.toArray(0)[0]);
		assertTrue("to see if finds correct stack",test[1] ==validmanifest.toArray(0)[1]);
	}
	
	
	
}
