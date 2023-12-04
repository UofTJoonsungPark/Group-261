package view;

import interface_adapter.reward.RewardController;
import interface_adapter.reward.RewardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RewardView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "badges";
    private final RewardViewModel rewardViewModel;
    final JButton back;
    private final RewardController rewardController;
    public RewardView(RewardViewModel rewardViewModel, RewardController rewardController) {
        this.rewardViewModel = rewardViewModel;
        this.rewardController = rewardController;
        this.rewardViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Badge Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(rewardViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 if (evt.getSource().equals(back)) {
                     System.exit(0);
                 }
             }
            }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}

