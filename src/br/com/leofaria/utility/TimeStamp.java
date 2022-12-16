package br.com.leofaria.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    public String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return (formatterData.format(now));
    }
    public String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        return (formatterHora.format(now));
    }
    public String getTimeStamp() {
        return (getDate() + ", " + getTime());
    }
}
