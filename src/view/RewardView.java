package view;

import data_access.FileUserDataAccessObject;
import entity.Badge;
import entity.BadgeFactory;
import entity.CommonUser;
import entity.User;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.reward.RewardController;
import interface_adapter.reward.RewardViewModel;
import use_case.login.LoginUserDataAccessInterface;
import use_case.reward.RewardDataAccessInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RewardView extends JPanel implements ActionListener, PropertyChangeListener{

    public final int point;
    public final String viewName = "badges";
    private final RewardViewModel rewardViewModel;
    final JButton back;
    final JPanel completePanel;
    final RewardDataAccessInterface rewardDataAccessObject;
    final LoginUserDataAccessInterface userDataAccessObject;
    private String username = null;

    private ArrayList<Badge> images = new ArrayList<Badge>();
    private final RewardController rewardController;
    public RewardView(RewardViewModel rewardViewModel, RewardController rewardController,
                      LoginUserDataAccessInterface userDataAccessObject, LoggedInState loggedInState) throws IOException {
        this.rewardViewModel = rewardViewModel;
        this.rewardController = rewardController;
        this.rewardViewModel.addPropertyChangeListener(this);
        this.userDataAccessObject = userDataAccessObject;
        this.username = loggedInState.getUsername();
        this.point = userDataAccessObject.get(username).getPoint();
        this.rewardDataAccessObject = new RewardDataAccessInterface() {
            @Override
            public ArrayList<Badge> getBadges(int point) {
                return null;
            }
        };

        this.images = rewardDataAccessObject.getBadges(point);

        JLabel title = new JLabel("Badge Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(rewardViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        completePanel = new JPanel();
        completePanel.setLayout(new BoxLayout(completePanel, BoxLayout.Y_AXIS));

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel badgesPanel = new JPanel();
        badgesPanel.setLayout(new BorderLayout());

        Image image = null;
        URL url = null;
        int val = 0;
        while (images.size() > val) {
            url = new URL(images.get(val).getImage());
            image = ImageIO.read(url);
            JLabel label = new JLabel(new ImageIcon(image));
            badgesPanel.add(label, BorderLayout.CENTER);

            frame.getContentPane().add(badgesPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            val++;
        }

        completePanel.add(frame);
        completePanel.add(buttons);

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

