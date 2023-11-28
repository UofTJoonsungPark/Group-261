package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.reward.RewardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;

    JLabel username;

    final JButton logOut;
    final JButton task;
    final JButton event;
    final JButton badge;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        this.loggedInController = loggedInController;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);


        task = new JButton(loggedInViewModel.TASK_BUTTON_LABEL);
        event = new JButton(loggedInViewModel.EVENT_BUTTON_LABEL);
        badge = new JButton(loggedInViewModel.BADGES_BUTTON_LABEL);

        buttons.add(event);
        buttons.add(task);
        buttons.add(badge);
        buttons.add(logOut);

        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);

        event.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(event)) {
                            loggedInController.execute(true, false, false);
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logOut)) {
                            loggedInController.execute(false, false, true);
                        }
                    }
                }
        );
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}