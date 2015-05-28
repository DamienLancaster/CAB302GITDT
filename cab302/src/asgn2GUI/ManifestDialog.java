package asgn2GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asgn2Exceptions.CargoException;
import asgn2Manifests.CargoManifest;

/**
 * Creates a dialog box allowing the user to enter parameters for a new <code>CargoManifest</code>.
 *
 * @author CAB302
 */
public class ManifestDialog extends AbstractDialog {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 250;

    private JTextField txtNumStacks;
    private JTextField txtMaxHeight;
    private JTextField txtMaxWeight;

    private CargoManifest manifest;

    /**
     * Constructs a modal dialog box that gathers information required for creating a cargo
     * manifest.
     *
     * @param parent the frame which created this dialog box.
     */
    private ManifestDialog(JFrame parent) {
        super(parent, "Create Manifest", WIDTH, HEIGHT);
        setName("New Manifest");
        setResizable(false);
        manifest = null;
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {

        txtNumStacks = createTextField(8, "Number of Stacks");
        txtMaxHeight = createTextField(8, "Maximum Height");
        txtMaxWeight = createTextField(8, "Maximum Weight");

        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());

       //Implementation here
    }

    /*
     * Factory method to create a named JTextField
     */
    private JTextField createTextField(int numColumns, String name) {
        JTextField text = new JTextField();
        text.setColumns(numColumns);
        text.setName(name);
        return text;
    }

    @Override
    protected boolean dialogDone() {
        //Implementation here 
    	//Parameters and building a new manifest, all the while handling exceptions 
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>CargoManifest</code> instance with valid values.
     */
    public static CargoManifest showDialog(JFrame parent) {
        //Implementation again 
    }
}
