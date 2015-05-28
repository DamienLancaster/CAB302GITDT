package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.regex.Pattern;

import javax.swing.JFrame;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asgn2GUI.CargoFrame;
import asgn2GUI.CargoTextFrame;

/**
 * Defines FEST Swing tests for use cases of the Cargo Manifest system.
 *
 * @author Malcolm. Created 7 May 2015.
 */
public class CargoFrameTests {

    private static final String MAXIMUM_WEIGHT = "Maximum Weight";
    private static final String MAXIMUM_HEIGHT = "Maximum Height";
    private static final String NUMBER_OF_STACKS = "Number of Stacks";
    private static final String START_BARS = "|| ";
    private static final String END_BARS = " ||";
    private static final String START_FOUND_BARS = "||*";
    private static final String END_FOUND_BARS = "*||";
    private static final String NEW_LINE = "\n";
    private static final String TEMPERATURE2 = "Temperature";
    private static final String GOODS_CATEGORY = "Goods Category";
    private static final String CONTAINER_WEIGHT = "Container Weight";
    private static final String CONTAINER_CODE = "Container Code";
    private static final String CONTAINER_INFORMATION = "Container Information";
    private static final String CARGO_TEXT_AREA = "Cargo Text Area";
    private static final String FIND = "Find";
    private static final String NEW_MANIFEST = "New Manifest";
    private static final String UNLOAD = "Unload";
    private static final String LOAD = "Load";
    private static final String CONTAINER_TYPE = "Container Type";
    private static final String GENERAL_GOODS = "General Goods";
    private static final String REFRIGERATED_GOODS = "Refrigerated Goods";
    private static final String DANGEROUS_GOODS = "Dangerous Goods";
    private static final String CATEGORY_2 = "2";
    private static final String TEMPERATURE_MINUS_4 = "-4";
    private static final String CODE_1 = "ABCU1234564";
    private static final String CODE_2 = "ZZZU6549874";
    private static final String CODE_3 = "JHGU1716760";
    private static final String NEGATIVE = "-20";
    private static final String NOT_NUMERIC = "A";
    private static final String STACKS_1 = "1";
    private static final String STACKS_3 = "3";
    private static final String STACKS_5 = "5";
    private static final String WEIGHT_20 = "20";
    private static final String WEIGHT_30 = "30";
    private static final String WEIGHT_80 = "80";
    private static final String WEIGHT_100 = "100";
    private static final String HEIGHT_1 = "1";
    private static final String HEIGHT_5 = "5";

    private static final boolean USE_TEXT_VERSION = true;
    private static final int NO_PAUSE = 0;
    private static final int SHORT_PAUSE = 1500;
    private static final int LONG_PAUSE = 3000;

    private static final Pattern CARGO_EXCEPTION_PATTERN = Pattern.compile("CargoException:.+");

    private FrameFixture testFrame;
    private JFrame frameUnderTest;

    /**
     * Turn on automated check to verify all Swing component updates are done in the Swing Event
     * Dispatcher Thread (EDT). The EDT ensures that the application never loses responsiveness to
     * user gestures.
     */
    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    /**
     * Ensures that frame is launched through FEST to ensure the EDT is used properly. JUnit runs in
     * its own thread.
     */
    @Before
    public void setUp() {
        if (USE_TEXT_VERSION) {
            /* +----------------------------------------------+ */
            /* | Use a CargoTextFrame to test the application | */
            /* +----------------------------------------------+ */
            frameUnderTest = GuiActionRunner.execute(new GuiQuery<CargoTextFrame>() {
                @Override
                protected CargoTextFrame executeInEDT() {
                    CargoTextFrame cargo = new CargoTextFrame("Cargo Manifest 1.0");
                    return cargo;
                }
            });
        } else {
            /* +------------------------------------------+ */
            /* | Use a CargoFrame to test the application | */
            /* +------------------------------------------+ */
            frameUnderTest = GuiActionRunner.execute(new GuiQuery<CargoFrame>() {
                @Override
                protected CargoFrame executeInEDT() {
                    CargoFrame cargo = new CargoFrame("Cargo Manifest 1.0");
                    return cargo;
                }
            });
        }

        // Choose one of the above JFrames to use as the test fixture.
        testFrame = new FrameFixture(frameUnderTest);
    }

    /**
     * Unload the frame and associated resources.
     */
    @After
    public void tearDown() {
        delay(SHORT_PAUSE);
        testFrame.cleanUp();
    }

    /**
     * Add a delay so that screen status can be viewed between tests.
     * Select from NO_PAUSE, SHORT_PAUSE, LONG_PAUSE.
     *
     * @param milliseconds The amount of time fow which to pause.
     */
    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Ensure the frame used for testing has been instantiated.
     */
    @Test
    public void testConstruction() {
        assertNotNull(testFrame);
    }

    /**
     * Tests that only the "New Manifest" button is enabled when the application starts.
     */
    @Test
    public void buttonStateAtStart() {
        testFrame.button(NEW_MANIFEST).requireEnabled();
        testFrame.button(LOAD).requireDisabled();
        testFrame.button(UNLOAD).requireDisabled();
        testFrame.button(FIND).requireDisabled();
    }

    /*
     * Helper - Brings up a ManifestDilaog for further interaction in tests.
     */
    private DialogFixture prepareManifestDialog() {
        testFrame.button(NEW_MANIFEST).click();
        DialogFixture manifestFixture = testFrame.dialog(NEW_MANIFEST);
        return manifestFixture;
    }

    /*
     * Helper - Puts text in the relevant text areas of the ManifestDialog.
     */
    private void manifestDialogEnterText(DialogFixture dialog, String stacks, String height, String weight) {
        if (stacks != null) {
            dialog.textBox(NUMBER_OF_STACKS).enterText(stacks);
        }
        if (height != null) {
            dialog.textBox(MAXIMUM_HEIGHT).enterText(height);
        }
        if (weight != null) {
            dialog.textBox(MAXIMUM_WEIGHT).enterText(weight);
        }
        dialog.button("OK").click();
    }

    /**
     * Tests for an error message when no text is entered in a ManifestDialog and "OK" is pressed.
     */
    @Test
    public void newManifestNoData() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestFixture.button("OK").click();
        testFrame.optionPane().requireErrorMessage();
    }

    /**
     * Tests for an error message when non-numeric data is entered in "Number of Stacks" text field
     * in a ManifestDialog.
     */
    @Test
    public void newManifestInvalidStacks() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, NOT_NUMERIC, null, null);
        manifestFixture.optionPane().requireErrorMessage();
    }

    /**
     * Tests for an error message when non-numeric data is entered in "Max Stack Height" text
     * field in a ManifestDialog.
     */
    @Test
    public void newManifestValidStacksInvalidHeight() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, STACKS_5, NOT_NUMERIC, null);
        manifestFixture.optionPane().requireErrorMessage();
    }

    /**
     * Tests for an error message when non-numeric data is entered in "Max Weight" text
     * field in a ManifestDialog.
     */
    @Test
    public void newManifestValidStacksValidHeightInvalidWeight() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, STACKS_5, HEIGHT_5, NOT_NUMERIC);
        manifestFixture.optionPane().requireErrorMessage();
    }

    /**
     * Tests for an error message when negative data is entered in "Number of Stacks" text
     * field in a ManifestDialog.
     */
    @Test
    public void newManifestNegativeStacks() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, NEGATIVE, HEIGHT_5, WEIGHT_100);
        manifestFixture.optionPane().requireErrorMessage();
        manifestFixture.optionPane().requireMessage(CARGO_EXCEPTION_PATTERN);
    }

    /**
     * Tests for an error message when negative data is entered in "Max Stack Height" text
     * field in a ManifestDialog.
     */
    @Test
    public void newManifestNegativeHeight() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, STACKS_5, NEGATIVE, WEIGHT_100);
        manifestFixture.optionPane().requireErrorMessage();
        manifestFixture.optionPane().requireMessage(CARGO_EXCEPTION_PATTERN);
    }

    /**
     * Tests for an error message when negative data is entered in "Max Weight" text
     * field in a ManifestDialog.
     */
    @Test
    public void newManifestNegativeWeight() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, STACKS_5, HEIGHT_5, NEGATIVE);
        manifestFixture.optionPane().requireErrorMessage();
        manifestFixture.optionPane().requireMessage(CARGO_EXCEPTION_PATTERN);
    }

    /**
     * Tests that all buttons are enabled on the window after a valid CargoManifest has been
     * created.
     */
    @Test
    public void newManifestValidDataButtonCheck() {
        DialogFixture manifestFixture = prepareManifestDialog();
        manifestDialogEnterText(manifestFixture, STACKS_5, HEIGHT_5, WEIGHT_100);
        testFrame.button(NEW_MANIFEST).requireEnabled();
        testFrame.button(LOAD).requireEnabled();
        testFrame.button(UNLOAD).requireEnabled();
        testFrame.button(FIND).requireEnabled();
    }

    /**
     * Tests that the Cargo Text Area holds the correct string representation for a single empty
     * stack.
     */
    @Test
    public void newManifestOneEmptyStack() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
            assertEquals("||  ||\n", testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /**
     * Tests that the Cargo Text Area holds the correct string representation for three empty
     * stacks.
     */
    @Test
    public void newManifestThreeEmptyStacks() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_3, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
            assertEquals("||  ||\n||  ||\n||  ||\n", testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /*
     * Helper - Enters data into a Create Manifest dialog
     */
    private void loadContainer(String containerType, String code, String weight,
            String goodsCat, String temperature) {
        testFrame.button(LOAD).click();
        DialogFixture containerDialog = testFrame.dialog(CONTAINER_INFORMATION);
        containerDialog.comboBox(CONTAINER_TYPE).selectItem(containerType);
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.textBox(CONTAINER_WEIGHT).enterText(weight);
        if (containerType.equals(DANGEROUS_GOODS)) {
            containerDialog.textBox(GOODS_CATEGORY).enterText(goodsCat);
        } else if (containerType.equals(REFRIGERATED_GOODS)) {
            containerDialog.textBox(TEMPERATURE2).enterText(temperature);
        }
        containerDialog.button("OK").click();
    }

    /**
     * Tests that a valid General Goods container is added and displayed.
     */
    @Test
    public void addGeneralGoods() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CODE_1, WEIGHT_20, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_1 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /**
     * Tests that a valid Dangerous Goods container is added and displayed.
     */
    @Test
    public void addDangerousGoods() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_30);
        loadContainer(DANGEROUS_GOODS, CODE_3, WEIGHT_20, CATEGORY_2, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_3 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /**
     * Tests that a valid Refrigerated Goods container is added and displayed.
     */
    @Test
    public void addRefrigeratedGoods() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_30);
        loadContainer(REFRIGERATED_GOODS, CODE_2, WEIGHT_20, null, TEMPERATURE_MINUS_4);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_2 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /**
     * Tests that one valid container of each container type is added to the canvas.
     */
    @Test
    public void addOneOfEach() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_3, HEIGHT_1, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CODE_1, WEIGHT_20, null, null);
        loadContainer(REFRIGERATED_GOODS, CODE_2, WEIGHT_20, null, TEMPERATURE_MINUS_4);
        loadContainer(DANGEROUS_GOODS, CODE_3, WEIGHT_20, CATEGORY_2, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_1 + END_BARS + NEW_LINE
                    + START_BARS + CODE_2 + END_BARS + NEW_LINE
                    + START_BARS + CODE_3 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /**
     * Tests that adding three 30 tonne containers overloads a manifest of 80 tonnes.
     */
    @Test
    public void overLoad() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_3, HEIGHT_1, WEIGHT_80);
        loadContainer(GENERAL_GOODS, CODE_1, WEIGHT_30, null, null);
        loadContainer(REFRIGERATED_GOODS, CODE_2, WEIGHT_30, null, TEMPERATURE_MINUS_4);
        loadContainer(DANGEROUS_GOODS, CODE_3, WEIGHT_30, CATEGORY_2, null);
        testFrame.optionPane().requireErrorMessage();
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_1 + END_BARS + NEW_LINE
                    + START_BARS + CODE_2 + END_BARS + NEW_LINE
                    + START_BARS + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /*
     * Helper - Clicks the Unload button and enters a valid container code.
     */
    private void unloadContainer(String code) {
        testFrame.button(UNLOAD).click();
        DialogFixture containerDialog = testFrame.dialog("Container Dialog");
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.button("OK").click();
    }

    /**
     * Tests that a loaded container can then be unloaded.
     */
    @Test
    public void loadGeneralGoodsThenUnload() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CODE_1, WEIGHT_20, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_1 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        unloadContainer(CODE_1);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }

    /*
     * Helper - Clicks the Find button and enters a valid container code.
     */
    private void findContainer(String code) {
        testFrame.button(FIND).click();
        DialogFixture containerDialog = testFrame.dialog("Container Dialog");
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.button("OK").click();
    }

    /**
     * Tests that a loaded container can then be unloaded.
     */
    @Test
    public void loadGeneralGoodsThenFind() {
        manifestDialogEnterText(prepareManifestDialog(), STACKS_1, HEIGHT_1, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CODE_1, WEIGHT_20, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_BARS + CODE_1 + END_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        findContainer(CODE_1);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expected = START_FOUND_BARS + CODE_1 + END_FOUND_BARS + NEW_LINE;
            assertEquals(expected, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
}
