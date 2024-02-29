import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AutoClicker extends JFrame {

    private final JTextField clickCountField;
    private final JLabel statusLabel;
    private final JButton startRCButton;
    private final JTextField delay;
    public AutoClicker() {
        setTitle("AutoClicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        clickCountField = new JTextField("Num of clicks", 10);
        panel.add(clickCountField);
        delay = new JTextField("delay)(msO", 7);
        panel.add(delay);
        startRCButton = new JButton("Start");
        startRCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clickCount = Integer.parseInt(clickCountField.getText());
                int delayCount= Integer.parseInt(delay.getText());
                statusLabel.setText("Running...");
                startRCButton.setEnabled(false);
                new Thread(() -> {
                    try {
                        Robot robot = new Robot();
                        Thread.sleep(3000);
                        for (int i = 0; i < clickCount; i++) {
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                            Thread.sleep(delayCount);
                        }
                        statusLabel.setText("Finished.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        statusLabel.setText("Error: " + ex.getMessage());
                    } finally {
                        startRCButton.setEnabled(true);
                    }
                }).start();
            }
        });
        panel.add(startRCButton);

        statusLabel = new JLabel(" ");
        panel.add(statusLabel);

        add(panel);

        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        AutoClicker app = new AutoClicker();
    }
}

