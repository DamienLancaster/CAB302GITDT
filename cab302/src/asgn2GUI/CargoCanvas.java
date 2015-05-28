package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Manifests.CargoManifest;

/**
 * Creates a JPanel in which graphical components are laid out to represent the cargo manifest.
 *
 * @author CAB302.
 */
public class CargoCanvas extends JPanel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;
    private static final int HSPACE = 10;
    private static final int VSPACE = 20;

    private final CargoManifest cargo;

    private ContainerCode toFind;

    /**
     * Constructor
     *
     * @param cargo The <code>CargoManifest</code> on which the graphics is based so that the
     * number of stacks and height can be adhered to.
     */
    public CargoCanvas(CargoManifest cargo) {
        this.cargo = cargo;
        setName("Canvas");
    }

    /**
     * Highlights a container.
     *
     * @param code ContainerCode to highlight.
     */
    public void setToFind(ContainerCode code) {
        //implementation here - don't forget to repaint
    }

    /**
     * Draws the containers in the cargo manifest on the Graphics context of the Canvas.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paint(Graphics g) {
    	//Implementation here 
    }

    /**
     * Draws a container at the given location.
     *
     * @param g The Graphics context to draw on.
     * @param container The container to draw - the type determines the colour and ContainerCode is
     *            used to identify the drawn Rectangle.
     * @param x The x location for the Rectangle.
     * @param y The y location for the Rectangle.
     */
    private void drawContainer(Graphics g, FreightContainer container, int x, int y) {
    	//Implementation here 
    	//Feel free to use some other method structure here, but this is the basis for the demo. 
    	//Obviously you need the graphics context and container as parameters. 
    	//But you can also use images if you wish. 
    }
}
