package pl.migibud.projection;

import java.time.LocalDateTime;

public interface LocationView {
    Long getId();
    String getCity();
    String getRegion();
    String getCountry();
    Double getLongitude();
    Double getLatitude();

}