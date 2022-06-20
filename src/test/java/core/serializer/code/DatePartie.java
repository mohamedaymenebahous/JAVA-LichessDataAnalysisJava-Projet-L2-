package core.serializer.code;

import java.io.Serializable;

public class DatePartie implements Serializable {

    private String year;
    private String month;
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String toString() {
        return this.getYear() + "." + this.getMonth() + "." + this.getDay();
    }

    //method String.split() not working in this case, so i made this method
    public DatePartie splitDate(String dateInput) {
        DatePartie date = new DatePartie();
        String y = "";
        String m = "";
        String d = "";
        for(int i = 0; i < 4; i++) {
            y = y + dateInput.charAt(i);
        }
        for(int i = 5; i < 7; i++) {
            m = m + dateInput.charAt(i);
        }
        for(int i = 8; i < 10; i++) {
            d = d + dateInput.charAt(i);
        }
        date.setYear(y);
        date.setMonth(m);
        date.setDay(d);
        return date;
    }

}
