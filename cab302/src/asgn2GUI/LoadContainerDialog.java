package asgn2GUI;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.CargoException;

/**
 * Creates a dialog box allowing the user to enter information required for loading a container.
 *
 * @author CAB302
 */
public class LoadContainerDialog extends AbstractDialog implements ActionListener, ItemListener {

    private static final int HEIGHT = 200;
    private static final int WIDTH = 350;

    private JPanel pnlCards;

    private JTextField txtDangerousGoodsType;
    private JTextField txtTemperature;
    private JTextField txtWeight;
    private JTextField txtCode;

    private JComboBox<String> cbType;
    private static String comboBoxItems[] = new String[] { "Dangerous Goods", "General Goods", "Refrigerated Goods" };

    private FreightContainer container;

    /**
     * Constructs a modal dialog box that gathers information required for loading a container.
     *
     * @param parent the frame which created this dialog box.
     */
    private LoadContainerDialog(JFrame parent) {
        super(parent, "Container Information", WIDTH, HEIGHT);
        setResizable(false);
        setName("Container Information");

    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
    	//Left intact as a basis but feel free to modify 
        createCards();

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new GridBagLayout());
        addToPanel(pnlContent, createCommonControls(), constraints, 0, 0, 2, 1);
        constraints.weighty = 10;
        addToPanel(pnlContent, pnlCards, constraints, 0, 1, 2, 1);

        return pnlContent;
    }

    private JPanel createCommonControls() {
    	//Left intact as a basis but feel free to modify - except for the 
        JPanel pnlCommonControls = new JPanel();
        pnlCommonControls.setLayout(new GridBagLayout());

        // add compents to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 100;
        constraints.weighty = 100;

        //Don't modify - START 
        cbType = new JComboBox<String>(comboBoxItems);
        cbType.setEditable(false);
        cbType.addItemListener(this);
        cbType.setName("Container Type");
      //Don't modify - END 

        txtWeight = createTextField(5, "Container Weight");
        txtCode = createTextField(11, "Container Code");

        addToPanel(pnlCommonControls, new JLabel("Container Type:"), constraints, 0, 0, 2, 1);
        addToPanel(pnlCommonControls, new JLabel("Container Code:"), constraints, 0, 2, 2, 1);
        addToPanel(pnlCommonControls, new JLabel("Container Weight:"), constraints, 0, 4, 2, 1);
        constraints.anchor = GridBagConstraints.WEST;
        addToPanel(pnlCommonControls, cbType, constraints, 3, 0, 2, 1);
        addToPanel(pnlCommonControls, txtCode, constraints, 3, 2, 2, 1);
        addToPanel(pnlCommonControls, txtWeight, constraints, 3, 4, 2, 1);

        return pnlCommonControls;
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

    private void createCards() {
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 45;
        constraints.weighty = 100;

        JPanel cardDangerousGoods = new JPanel();
        cardDangerousGoods.setLayout(new GridBagLayout());
        txtDangerousGoodsType = createTextField(5, "Goods Category");

        JPanel cardRefrigeratedGoods = new JPanel();
        cardRefrigeratedGoods.setLayout(new GridBagLayout());
        txtTemperature = createTextField(5, "Temperature");

        //Finish here 
    }

    /**
     * @see java.awt.ItemListener.itemStateChanged(ItemEvent e)
     */
    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout cl = (CardLayout) pnlCards.getLayout();
        //Finish here - show cards and set text fields
    }

    /**
     * @see AbstractDialog.dialogDone()
     */
    @Override
    protected boolean dialogDone() {
        //Implementation here - create the container and set parameters, 
    	//But handle the exceptions properly 
    }

    /**
     * Shows a <code>LoadContainerDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>FreightContainer</code> instance with valid values.
     */
    public static FreightContainer showDialog(JFrame parent) {
       //Implementation here. 
    }

}
