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

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import interface_adapter.event.EventController;
import interface_adapter.event.EventViewModel;
import interface_adapter.signup.SignupViewModel;

public class EventView extends JPanel implements ActionListener, PropertyChangeListener {
    private final static int WIDTH = 30;
    public final String viewName = "event";
    private final EventViewModel eventViewModel;
    private final EventController eventController;
    final JButton create;
    final JButton back;
    private final JButton save;
    final JPanel gridPanel;
    final JPanel left;
    final JList<String> right;
    final JDialog createDialog;
    private final JTextField title = new JTextField(WIDTH);
    private final JTextField location = new JTextField(WIDTH);
    private final JTextArea description = new JTextArea(3, WIDTH);

    public EventView(EventViewModel eventViewModel, EventController eventController) {
        this.eventViewModel = eventViewModel;
        this.eventController = eventController;

        left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right = new JList<>();

        DatePickerSettings settings = new DatePickerSettings();
        changeSizeCalendar(settings, 1.6);
        CalendarPanel calendarPanel = new CalendarPanel(settings);
        calendarPanel.setSelectedDate(LocalDate.now());
        calendarPanel.addCalendarListener(new SimpleCalendarListener());
        calendarPanel.setBorder(new LineBorder(Color.lightGray));

        JPanel buttons = new JPanel();
        create = new JButton(EventViewModel.CREATE_BUTTON_LABEL);
        back = new JButton(EventViewModel.BACK_BUTTON_LABEL);
        buttons.add(create);
        buttons.add(back);

        left.add(calendarPanel);
        left.add(buttons);

        this.gridPanel = new JPanel(new GridLayout(0, 2));
        gridPanel.add(left);
        gridPanel.add(right);
        this.add(gridPanel);

        save = new JButton(EventViewModel.SAVE_BUTTON_LABEL);
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

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            createDialog.setVisible(false);
                            eventController.execute(true);
                        }
                    }
                }
        );
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private class SimpleCalendarListener implements CalendarListener {
        private SimpleCalendarListener() {
        }

        public void selectedDateChanged(CalendarSelectionEvent event) {

        }

        public void yearMonthChanged(YearMonthChangeEvent event) {

        }
    }

    private JDialog buildCreateDialog()  {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog createDialog = new JDialog(topFrame, "Create a event");
        createDialog.setMinimumSize(new Dimension(500, 400));

        JPanel mainPanel = new JPanel();
        createDialog.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel startPicker = new JPanel();
        startPicker.add(new JLabel("START"));
        DatePickerSettings startDateSettings = new DatePickerSettings();
        changeSizeCalendar(startDateSettings, 1.3);

        TimePickerSettings startTimeSettings = new TimePickerSettings();
        startTimeSettings.setAllowEmptyTimes(false);
        DateTimePicker startDateTimePicker = new DateTimePicker(startDateSettings, startTimeSettings);
        startDateTimePicker.getDatePicker().setDateToToday();
        startPicker.add(startDateTimePicker);

        JPanel endPicker = new JPanel();
        DatePickerSettings endDateSEttings = startDateSettings.copySettings();
        TimePickerSettings endTimeSettings = new TimePickerSettings();
        DateTimePicker endDateTimePicker = new DateTimePicker(endDateSEttings, endTimeSettings);
        endDateTimePicker.getDatePicker().setDateToToday();
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

    private void changeSizeCalendar(DatePickerSettings setting, double ratio) {
        int newHeight = (int) (setting.getSizeDatePanelMinimumHeight() * ratio);
        int newWidth = (int) (setting.getSizeDatePanelMinimumWidth() * ratio);
        setting.setSizeDatePanelMinimumHeight(newHeight);
        setting.setSizeDatePanelMinimumWidth(newWidth);
    }
}
