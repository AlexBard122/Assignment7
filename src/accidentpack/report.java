package accidentpack;

import java.time.LocalDate;

/**
 * @author abard & Devin C
 *
 */
public class report implements Comparable <report>{
	private String ID;
	private int Severity;
	private LocalDate StartTime;
	private LocalDate EndTime;
	private String Street;
	private String City;
	private String County;
	private String State;
	private int Temp;
	private int Humidity;
	private int Visibility;
	private String Weather;
	private boolean Crossing;
	private boolean DayNight;
	
	public report(String ID, int Severity, LocalDate startTime, LocalDate endTime, String Street,
			String City, String County, String State, int Temp,int Humidity, int Visibility,
			String Weather, boolean Crossing, boolean DayNight) {
		this.ID = ID;
		this.Severity = Severity;
		this.StartTime = startTime;
		this.EndTime = endTime;
		this.Street = Street;
		this.City = City;
		this.County = County;
		this.State = State;
		this.Temp = Temp;
		this.Humidity = Humidity;
		this.Visibility = Visibility;
		this.Weather = Weather;
		this.Crossing = Crossing;
		this.DayNight = DayNight;
	}

	
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public int getSeverity() {
		return Severity;
	}
	public void setSeverity(int Severity) {
		this.Severity = Severity;
	}
	public LocalDate getStartTime() {
		return StartTime;
	}
	public void setStartTime(LocalDate StartTime) {
		this.StartTime = StartTime;
	}
	public LocalDate getEndTime() {
		return EndTime;
	}
	public void setEndTime(LocalDate EndTime) {
		this.EndTime = EndTime;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String Street) {
		this.Street = Street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String City) {
		this.City = City;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String Country) {
		this.County = Country;
	} 
	public String getState() {
		return State;
	}
	public void setState(String State) {
		this.State = State;
	}
	public int getTemp() {
		return Temp;
	}
	public void setTemp(int Temp) {
		this.Temp = Temp;
	}
	public int getHumidity() {
		return Humidity;
	}
	public void setHumidity(int Humidity) {
		this.Humidity = Humidity;
	}
	public int getVisibility() {
		return Visibility;
	}
	public void setVisibility(int Visibility) {
		this.Visibility = Visibility;
	}
	public String getWeather() {
		return Weather;
	}
	public void setWeather(String Weather) {
		this.Weather = Weather;
	}
	public boolean getCrossing() {
		return Crossing;
	}
	public void setCrossing(boolean Crossing) {
		this.Crossing = Crossing;
	}
	public boolean getDayNight() {
		return DayNight;
	}
	public void setDayNight(boolean DayNight) {
		this.DayNight = DayNight;
	}

    @Override
    public int compareTo(report r) {
        return this.StartTime.compareTo(r.getStartTime());
    } 
}
