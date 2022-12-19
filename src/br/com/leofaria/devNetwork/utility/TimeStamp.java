package br.com.leofaria.devNetwork.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    public static String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return (formatterData.format(now));
    }
    public static String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        return (formatterHora.format(now));
    }
    public static String getTimeStamp() {
        return (getDate() + ", " + getTime());
    }
}
