package com.tudor.sdm.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import bizcal.common.Calendar;
import bizcal.common.CalendarModel;
import bizcal.common.DayViewConfig;
import bizcal.common.Event;
import bizcal.swing.DayView;
import bizcal.swing.PopupMenuCallback;
import bizcal.util.DateInterval;
import bizcal.util.DateUtil;

public class CalendarUI extends JFrame {

	public CalendarUI() throws Exception {
		setSize(1000, 900);

		DayView dayView = new DayView(new DayViewConfig());
		dayView.setModel(new ThisModel());
		dayView.refresh();
		dayView.setPopupMenuCallback(new PopupMenuCallback() {
			
			@Override
			public JPopupMenu getProjectPopupMenu(Object arg0) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public JPopupMenu getEventPopupMenu(Object arg0, Event arg1)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public JPopupMenu getEmptyPopupMenu(Object arg0, Date arg1)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public JPopupMenu getCalendarPopupMenu(Object arg0) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});
		this.setContentPane(dayView.getComponent());
			
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new CalendarUI();
	}

	private static class ThisModel extends CalendarModel.BaseImpl {
		private List<Event> events = new ArrayList<Event>();
		private DateInterval interval;
		private Calendar cal;

		@SuppressWarnings("unchecked")
		public ThisModel() throws Exception {
			Date date = DateUtil.round2Week(new Date());
			date = new Date(date.getTime() + 8 * 60 * 60 * 1000);
			for (int i = 0; i < 7; i++) {
				Event event = new Event();
				event.setStart(date);
				event.setEnd(new Date(date.getTime() + 90 * 60 * 1000));
				event.setSummary("Test " + i);
				events.add(event);
				date = DateUtil.getDiffDay(date, +1);
				date = new Date(date.getTime() + 60 * 60 * 1000);
			}
			Date start = DateUtil.round2Week(new Date());
			Date end = DateUtil.getDiffDay(start, +5);
			interval = new DateInterval(start, end);
			cal = new Calendar();
			cal.setId(1);
			cal.setSummary("Peter");
		}

		public List<Event> getEvents(Object calId) throws Exception {
			return events;
		}

		public List getSelectedCalendars() throws Exception {
			return Collections.nCopies(1, cal);
		}

		public DateInterval getInterval() {
			return interval;
		}
	}

}
