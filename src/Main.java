import util.SwingUtils;

import java.util.Locale;

public class Main {

    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 24);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });

    }

    public static void main(String[] Args) throws Exception {
        winMain();
    }
}
