package pl.migibud.forecast;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.migibud.forecast.exception.ForecastError;
import pl.migibud.forecast.exception.ForecastException;

class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 404:
                return new ForecastException(ForecastError.LOCATION_NOT_FOUND);
            default:
                return new RuntimeException("Generic error");
        }
    }
}
