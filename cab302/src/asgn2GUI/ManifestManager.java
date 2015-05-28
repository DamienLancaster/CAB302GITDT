package asgn2GUI;

/**
 * Main programme for the CargoFrame 
 *
 * @author CAB302
 */
public class ManifestManager {

    /**
     * @param args
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CargoFrame("Cargo Manager 1.0");
            }
        });
    }

}
