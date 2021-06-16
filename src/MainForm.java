import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainForm extends JFrame {

    private JPanel MainPanel;
    private JLabel InputLabel;
    private JLabel OutputLabel;
    private JButton ExecuteButton;
    private JLabel WinProbability;
    private JSpinner spinner1;
    private JButton CleanButton;

    public MainForm() {
        this.setTitle("Граф");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        TicTacToeGraph graph = new TicTacToeGraph();
        graph.createGraphOfState();
        //      doDrawing(750);

        // теперь нарисовали состояние на формочке InputLabel
        // нашли в графе
        // нашли ребенка с наибольшим winFactor
        // отрисовали его в OutputLabel

        this.pack();
        DrawField input = new DrawField();
        DrawField output = new DrawField();
        input.paintBackGround();
        output.paintBackGround();
        doDrawing(InputLabel,input);
        doDrawing(OutputLabel,output);

        MainPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() - 104;
                int y = e.getY() - InputLabel.getY();
                input.drawFigure(x,y);
                input.addField(x,y);
                doDrawing(InputLabel,input);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }


        });

        ExecuteButton.addActionListener(e -> {
            output.paintBackGround();
            output.inputField = graph.getTheBestNeighbor(input.inputField).value;
            spinner1.setValue(graph.getTheBestNeighbor(input.inputField).prob);
            output.paintBasedOnField();
            doDrawing(OutputLabel,output);
        });

        CleanButton.addActionListener(e -> {
            input.cleanField();
            output.cleanField();
            doDrawing(OutputLabel,output);
            doDrawing(InputLabel,input);
        });

    }




    public void doDrawing(JLabel label, DrawField field) {
        label.setIcon(new ImageIcon(field.image));


    }
}
