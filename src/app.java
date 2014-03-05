import ui.main_ui.LeagueWindow;

public class app {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LeagueWindow();
            }
        });
    }
}
