package view;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import interface_adapter.event.EventController;
import interface_adapter.event.EventState;
import interface_adapter.event.EventViewModel;
import interface_adapter.signup.SignupViewModel;

/**
 * The EventView class is responsible for the UI of the event page.
 */
public class EventView extends JPanel implements ActionListener, PropertyChangeListener {
    private final static int WIDTH = 30;
    public final String viewName = "event";
    private final EventViewModel eventViewModel;
    private final EventController eventController;
    final JButton create;
    final JButton back;
    final JButton delete;
    private final JButton save;
    final JPanel gridPanel;
    final JPanel left;
    private final JList<String> right;
    final JDialog createDialog;
    private final CalendarPanel calendarPanel;
    private final JTextField title = new JTextField(WIDTH);
    private final JTextField location = new JTextField(WIDTH);
    private final JTextArea description = new JTextArea(3, WIDTH);

    private final DateTimePicker startDateTimePicker = new DateTimePicker();
    private final DateTimePicker endDateTimePicker = new DateTimePicker();

    /**
     * Constructor to initialize EventView instance
     *
     * @param eventViewModel    EventViewModel class
     * @param eventController   EventController class
     */
    public EventView(EventViewModel eventViewModel, EventController eventController) {
        this.eventViewModel = eventViewModel;
        this.eventController = eventController;
        this.eventViewModel.addPropertyChangeListener(this);

        left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right = new JList<>();

        DatePickerSettings settings = new DatePickerSettings();
        changeSizeCalendar(settings, 1.6);
        calendarPanel = new CalendarPanel(settings);
        calendarPanel.setSelectedDate(LocalDate.now());
        calendarPanel.addCalendarListener(new SimpleCalendarListener());
        calendarPanel.setBorder(new LineBorder(Color.lightGray));

        JPanel buttons = new JPanel();
        create = new JButton(eventViewModel.CREATE_BUTTON_LABEL);
        delete = new JButton(eventViewModel.DELETE_BUTTON_LABEL);
        back = new JButton(eventViewModel.BACK_BUTTON_LABEL);
        buttons.add(create);
        buttons.add(delete);
        buttons.add(back);

        left.add(calendarPanel);
        left.add(buttons);

        this.gridPanel = new JPanel(new GridLayout(0, 2));
        gridPanel.add(left);
        gridPanel.add(right);
        this.add(gridPanel);

        save = new JButton(eventViewModel.SAVE_BUTTON_LABEL);
        createDialog = buildCreateDialog();


        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)) {
                            createDialog.setVisible(true);
                        }
                    }
                }
        );

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete) && calendarPanel.getSelectedDate() != null) {
                            eventController.delete(calendarPanel.getSelectedDate(), right.getSelectedIndices());
                        }
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            createDialog.setVisible(false);
                            eventViewModel.getState().setUseCase(eventViewModel.BACK_USE_CASE);
                            eventController.execute(eventViewModel.BACK_USE_CASE);
                        }
                    }
                }
        );

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save) && isInputValid()) {
                            eventController.execute(eventViewModel.SAVE_USE_CASE, title.getText(),
                                    location.getText(), description.getText(),
                                    startDateTimePicker.getDatePicker().getDate(),
                                    startDateTimePicker.getTimePicker().getTime(),
                                    endDateTimePicker.getDatePicker().getDate(),
                                    endDateTimePicker.getTimePicker().getTime());
                            eventController.query(calendarPanel.getSelectedDate());
                            createDialog.setVisible(false);
                            resetField();
                        }
                    }
                }
        );
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EventState state = (EventState) evt.getNewValue();
        String useCase = state.getUseCase();
        if (state.getError() != null) {
            showErrorMessage(state.getError());
            state.setError(null);
            return;
        }
        else if (useCase.equals(eventViewModel.INITIALIZE_USE_CASE)) {
            state.setUseCase("");
            String username = eventViewModel.getState().getUsername();
            eventController.initialize(username);
        }
//        } else if (useCase.equals(EventViewModel.CLEAR_USE_CASE)) {
//            state.setUsername("");
//        }
        if (calendarPanel.getSelectedDate() != null) {
            updateList();
        }
    }

    /**
     * This class is responsible for observing the DatePicker instance
     */
    private class SimpleCalendarListener implements CalendarListener {
        private SimpleCalendarListener() {
        }

        public void selectedDateChanged(CalendarSelectionEvent event) {
            eventController.query(event.getNewDate());
            updateList();
        }

        public void yearMonthChanged(YearMonthChangeEvent event) {

        }
    }

    /**
     * The method buildCreateDialog is executed in the constructor of the EventView class
     * This method is used to create an instance of JDialog, which serves as a pop-up window for creating an Event.
     *
     * @return an instance of JDialog (pop-up window)
     */
    private JDialog buildCreateDialog()  {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog createDialog = new JDialog(topFrame, "Create an event");
        createDialog.setMinimumSize(new Dimension(500, 400));

        JPanel mainPanel = new JPanel();
        createDialog.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        initializeDatePicker();

        JPanel startPicker = new JPanel();
        JPanel endPicker = new JPanel();
        startPicker.add(new JLabel("START"));
        startPicker.add(startDateTimePicker);
        endPicker.add(new JLabel("END"));
        endPicker.add(endDateTimePicker);

        JPanel pickerPanel = new JPanel();
        pickerPanel.add(startPicker);
        pickerPanel.add(endPicker);
        mainPanel.add(pickerPanel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("Title"), title);
        LabelTextPanel locationInfo = new LabelTextPanel(new JLabel("Location"), location);

        textPanel.add(titleInfo);
        textPanel.add(locationInfo);

        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.add(new JLabel("Description"));
        JScrollPane jScrollPane = new JScrollPane(description);
        descriptionPanel.add(jScrollPane);
        textPanel.add(descriptionPanel);

        mainPanel.add(textPanel);
        mainPanel.add(save);

        return createDialog;
    }

    /**
     * Change the size of the Calendar.
     *
     * @param setting   The setting that should be changed
     * @param ratio     The ratio of the original size to the new size
     */
    private void changeSizeCalendar(DatePickerSettings setting, double ratio) {
        int newHeight = (int) (setting.getSizeDatePanelMinimumHeight() * ratio);
        int newWidth = (int) (setting.getSizeDatePanelMinimumWidth() * ratio);
        setting.setSizeDatePanelMinimumHeight(newHeight);
        setting.setSizeDatePanelMinimumWidth(newWidth);
    }

    /**
     * The method is used to initialize both the start and end DatePicker for creating an Event.
     */
    private void initializeDatePicker() {
        DatePickerSettings startDateSettings = new DatePickerSettings();
        changeSizeCalendar(startDateSettings, 1.4);
        startDateSettings.setAllowEmptyDates(false);
        startDateTimePicker.getDatePicker().setSettings(startDateSettings);
        startDateTimePicker.getDatePicker().setDateToToday();

        DatePickerSettings endDateSettings = startDateSettings.copySettings();
        endDateTimePicker.getDatePicker().setSettings(endDateSettings);
    }

    /**
     * This method is responsible to show error message.
     * @param message error message
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * This method is to check if the given input is valid.
     * @return true iff all the input is valid
     */

    private boolean isInputValid() {
        // date and time check
        LocalDate startDate = startDateTimePicker.getDatePicker().getDate();
        LocalTime startTime = startDateTimePicker.getTimePicker().getTime();
        LocalDate endDate = endDateTimePicker.getDatePicker().getDate();
        LocalTime endTime = endDateTimePicker.getTimePicker().getTime();
        if (startTime == null && endTime == null) {
            if (startDate.isAfter(endDate)) {
                showErrorMessage("invalid date and time");
                return false;
            }
        } else if (startTime == null || endTime == null) {
            showErrorMessage("Start and end times must be either both provided or both omitted.");
            return false;
        } else {
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            if (!endDateTime.isAfter(startDateTime)) {
                showErrorMessage("invalid date and time");
                return false;
            }
        }
        // check if the title is empty
        if (title.getText().isEmpty()) {
            showErrorMessage("empty title");
            return false;
        }
        return true;
    }

    /**
     * This method is to reset all the text fields after saving an event
     */
    private void resetField() {
        title.setText("");
        location.setText("");
        description.setText("");
    }

    /**
     * The method is called when to refresh the list of Event
     */
    private void updateList() {
        java.util.List<String> result = eventViewModel.getState().getEvents();
        right.setListData(result.toArray(new String[0]));
    }
}
